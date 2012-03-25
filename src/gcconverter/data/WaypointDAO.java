package gcconverter.data;

import gcconverter.GCConverter;
import gcconverter.entity.Waypoint;
import gcconverter.util.HTMLParser;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WaypointDAO {

    private static WaypointDAO instance;

    private WaypointDAO() {
	try {
	    Class.forName("org.sqlite.JDBC");
	    Connection conn = DriverManager.getConnection("jdbc:sqlite:gcconverter.db");
	    Statement stat = conn.createStatement();
	    //stat.executeUpdate("DROP TABLE IF EXISTS waypoint;");
	    stat.executeUpdate("CREATE TABLE IF NOT EXISTS waypoint (id, name, type, size, lat, lon, hint, found);");
	    //stat.executeUpdate("UPDATE waypoint SET hint = NULL, type = NULL, size = NULL");
	    conn.close();
	} catch (Exception ex) {
	    Logger.getLogger(WaypointDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public static WaypointDAO get() {
	if (instance == null) {
	    instance = new WaypointDAO();
	}
	return instance;
    }

    public void save(Waypoint w) {
	if (w == null) {
	    return;
	}
	System.out.println(w);
	try {
	    Connection conn = DriverManager.getConnection("jdbc:sqlite:gcconverter.db");
	    Statement stat = conn.createStatement();
	    PreparedStatement prep = null;
	    if (!exists(w)) {
		prep = conn.prepareStatement("INSERT INTO waypoint VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
		prep.setString(1, w.getId());
		prep.setString(2, w.getName());
		prep.setString(3, w.getType());
		prep.setString(4, w.getSize());
		prep.setFloat(5, w.getLatitude());
		prep.setFloat(6, w.getLongitude());
		prep.setString(7, w.getHint());
		prep.setBoolean(8, w.isFound());
	    } else {
		prep = conn.prepareStatement("UPDATE waypoint SET name = ?, type = ?, size = ?, lat = ?, lon = ?, hint = ?, found = ? WHERE id = ?;");
		prep.setString(1, w.getName());
		prep.setString(2, w.getType());
		prep.setString(3, w.getSize());
		prep.setFloat(4, w.getLatitude());
		prep.setFloat(5, w.getLongitude());
		prep.setString(6, w.getHint());
		prep.setBoolean(7, w.isFound());
		prep.setString(8, w.getId());
	    }

	    prep.addBatch();
	    conn.setAutoCommit(false);
	    prep.executeBatch();
	    conn.setAutoCommit(true);
	    conn.close();
	} catch (Exception ex) {
	    Logger.getLogger(WaypointDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public boolean exists(Waypoint w) {
	if (w == null) {
	    return true;
	}
	ArrayList<Waypoint> waypoints = loadAll();
	for (Waypoint wp : waypoints) {
	    if (wp.getId().equalsIgnoreCase(w.getId())) {
		return true;
	    } else if (wp.getName().equalsIgnoreCase(w.getName())
		    && wp.getLatitude() == w.getLatitude()
		    && wp.getLongitude() == w.getLongitude()) {
		return true;
	    }
	}
	return false;
    }

    public ArrayList<Waypoint> loadAll() {
	ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
	try {
	    Connection conn = DriverManager.getConnection("jdbc:sqlite:gcconverter.db");
	    Statement stat = conn.createStatement();
	    ResultSet rs = stat.executeQuery("SELECT * FROM waypoint;");
	    while (rs.next()) {
		Waypoint w = new Waypoint(rs.getString("id"), rs.getString("name"), rs.getString("type"), rs.getString("size"), rs.getFloat("lat"), rs.getFloat("lon"), rs.getString("hint"), rs.getBoolean("found"));
		//System.out.println(w);
		waypoints.add(w);
	    }
	    rs.close();
	    conn.close();
	} catch (Exception ex) {
	    Logger.getLogger(WaypointDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return waypoints;
    }

    public void delete(Waypoint w) {
	try {
	    Connection conn = DriverManager.getConnection("jdbc:sqlite:gcconverter.db");
	    Statement stat = conn.createStatement();
	    PreparedStatement prep = conn.prepareStatement("DELETE FROM waypoint WHERE id = ?;");
	    prep.setString(1, w.getId());
	    prep.addBatch();
	    conn.setAutoCommit(false);
	    prep.executeBatch();
	    conn.setAutoCommit(true);
	    conn.close();
	} catch (SQLException ex) {
	    Logger.getLogger(WaypointDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public boolean reloadWaypoint(Waypoint w) {
        HTMLParser parser = new HTMLParser(GCConverter.CACHE_DETAILS_BASE_URL + w.getId());
        if (!parser.connect()) {
            System.out.println("could not connect to gc.com");
            return false;
        }
        w.setType(parser.getCacheType());
        w.setSize(parser.getCacheSize());
        w.setHint(parser.getCacheHint());
        save(w);
        return true;
    }
}
