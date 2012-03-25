package gcconverter.control;

import gcconverter.entity.Waypoint;
import gcconverter.util.GCCipher;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stuchl4n3k
 */
public class ExportController {

    private static ExportController instance;

    private ExportController() {
    }

    public static ExportController get() {
        if (instance == null) {
            instance = new ExportController();
        }
        return instance;
    }
    
    public boolean exportLMX(File outputFile, Waypoint w) {
        ArrayList<Waypoint> al = new ArrayList<>();
        al.add(w);
        return exportLMX(outputFile, al);
    }

    public boolean exportLMX(File outputFile, ArrayList<Waypoint> waypoints) {
        if (outputFile == null || waypoints == null) {
            return false;
        }
        String content = "";


        content += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        content += "\r\n<lm:lmx xmlns:lm=\"http://www.nokia.com/schemas/location/landmarks/1/0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.nokia.com/schemas/location/landmarks/1/0/lmx.xsd\">";
        content += "\r\n<lm:landmarkCollection>";
        for (Waypoint w : waypoints) {
            content += "\r\n<lm:landmark>";
            content += "\r\n<lm:name>" + w.getName() + "</lm:name>";
            content += "\r\n<lm:description>Size:" + w.getSize() + ", Hint: " + GCCipher.encrypt(w.getHint()) + "</lm:description>";
            content += "\r\n<lm:coordinates>";
            content += "\r\n<lm:latitude>" + w.getLatitude() + "</lm:latitude>";
            content += "\r\n<lm:longitude>" + w.getLongitude() + "</lm:longitude>";
            content += "\r\n</lm:coordinates>";
            content += "\r\n</lm:landmark>";
        }
        content += "\r\n</lm:landmarkCollection>";
        content += "\r\n</lm:lmx>";


        FileWriter fw;
        try {
            fw = new FileWriter(outputFile);
            fw.write(content);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }
}
