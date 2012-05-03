package gcconverter.control;

import gcconverter.Application;
import gcconverter.data.WaypointDAO;
import gcconverter.entity.Waypoint;
import java.util.ArrayList;

/**
 *
 * @author stuchlanek
 */
public class MainController {

    private Application application;
    private Waypoint currentWaypoint;
    private ArrayList<Waypoint> waypoints;

    public MainController(Application application) {
        this.application = application;
        currentWaypoint = null;
        waypoints = new ArrayList<>();
        loadWaypoints();
    }

    private void loadWaypoints() {
        waypoints = WaypointDAO.get().loadAll();
    }

    public boolean reloadCurrentWaypoint() {
        if (currentWaypoint == null) {
            return false;
        }
        return WaypointDAO.get().reloadWaypoint(currentWaypoint);
    }

    public boolean deleteCurrentWaypoint() {
        if (currentWaypoint == null) {
            return false;
        }
        WaypointDAO.get().delete(currentWaypoint);
        waypoints.remove(currentWaypoint);
        currentWaypoint = null;
        return true;
    }

    public void saveCurrentWaypoint() {
        WaypointDAO.get().save(currentWaypoint);
    }

    public void saveWaypoints() {
        for (Waypoint w : waypoints) {
            WaypointDAO.get().save(w);
        }
    }

    public void addWaypoints(ArrayList<Waypoint> newWaypoints) {
        for (Waypoint newWaypoint : newWaypoints) {
            if (!waypoints.contains(newWaypoint)) {
                if (!newWaypoint.hasOnlineData()) {
                    WaypointDAO.get().reloadWaypoint(newWaypoint);
                }
                waypoints.add(newWaypoint);
                WaypointDAO.get().save(newWaypoint);
            }
        }
    }

    /////////////// GETTERS & SETTERS //////////////////

    public Waypoint getCurrentWaypoint() {
        return currentWaypoint;
    }

    public void setCurrentWaypoint(Waypoint waypoint) {
        this.currentWaypoint = waypoint;
    }

    public ArrayList<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(ArrayList<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    public Waypoint getWaypointById(String id) {
        for (Waypoint w : waypoints) {
            if (w.getId().equalsIgnoreCase(id)) {
                return w;
            }
        }
        return null;
    }
}
