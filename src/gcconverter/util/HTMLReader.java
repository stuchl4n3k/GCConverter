package gcconverter.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stuchlanek
 */
public class HTMLReader {

    public static final int MAX_FILE_SIZE = 100000;

    public static String getHTML(URL url) {
	if (url == null) {
	    return null;
	}
	String content = null;
	try {
	    System.out.println("Now connecting to: "+url);
	    URLConnection conn = url.openConnection();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                while (true) {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    if (content == null) {
                        content = line + "\n";
                    } else {
                        content += line + "\n";
                    }
                    if (content.length() > MAX_FILE_SIZE) {
                        break;
                    }
                }
            }
	} catch (IOException ex) {
	    Logger.getLogger(HTMLReader.class.getName()).log(Level.SEVERE, null, ex);
	}
	return content;
    }
}
