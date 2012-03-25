package gcconverter.control;

import gcconverter.entity.Waypoint;
import gcconverter.util.LOCParser;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author stuchl4n3k
 */
public class ImportController {

    private static ImportController instance;

    private ImportController() {
    }

    public static ImportController get() {
        if (instance == null) {
            instance = new ImportController();
        }
        return instance;
    }

    public ArrayList<Waypoint> importLOC(File f) {
        if (f == null) {
            return null;
        }

        ArrayList<Waypoint> waypoints = LOCParser.parse(f);
        if (waypoints == null) {
            return null;
        }
        
        return waypoints;
    }
}
