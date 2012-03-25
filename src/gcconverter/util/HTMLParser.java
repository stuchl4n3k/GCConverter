package gcconverter.util;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stuchlanek
 */
public class HTMLParser {
    protected URL url;
    protected String content;
    protected boolean connected;
    
    public HTMLParser(String url) {
	connected = false;
	try {
	    this.url = new URL(url);
	} catch (IOException ex) {
	    Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public boolean connect() {
	content = HTMLReader.getHTML(url);
	connected = (content != null);
	return connected;
    }

    public String getCacheSize() {
	if (!connected || content == null) return null;
	int startIndex = content.indexOf("alt=\"Size: ");
	if (startIndex < 0) return null;
	String tmp = content.substring(startIndex);
	int stopIndex = tmp.indexOf("\" ");
	if (stopIndex < 0) return null;
	tmp = tmp.substring(11, stopIndex);
	return tmp.trim().toLowerCase();
    }

    public String getCacheType() {
	if (!connected || content == null) return null;
	int startIndex = content.indexOf("<img src=\"/images/WptTypes/");
	if (startIndex < 0) return null;
	String tmp = content.substring(startIndex);
	startIndex = tmp.indexOf("alt=");
	int stopIndex = tmp.indexOf(" width=");
	if (startIndex < 0 || stopIndex < 0) return null;
	tmp = tmp.substring(startIndex+5, stopIndex-1);
	tmp = tmp.toLowerCase();
	stopIndex = tmp.indexOf("cache");
	if (stopIndex < 0) return null;
	tmp = tmp.substring(0, stopIndex);
	stopIndex = tmp.indexOf("-");
	if (stopIndex < 0) return tmp.trim();
	else return tmp.substring(0, stopIndex).trim();
    }
    
    public String getCacheHint() {
	if (!connected || content == null) return null;
	int startIndex = content.indexOf("id=\"div_hint\"");
	if (startIndex < 0) return null;
	String tmp = content.substring(startIndex);
	startIndex = tmp.indexOf(">");
	int stopIndex = tmp.indexOf("<");
	if (startIndex < 0 || stopIndex < 0) return null;
	tmp = tmp.substring(startIndex+1, stopIndex);
	return tmp.toLowerCase().trim();
    }



    public static void main(String[] args) {
	HTMLParser p = new HTMLParser("http://www.geocaching.com/seek/cache_details.aspx?guid=10d55dee-76ec-486f-bc74-78b3d9a3b11d");
	p.connect();
	System.out.println(p.getCacheHint());
	System.out.println(GCCipher.encrypt(p.getCacheHint()));
        System.out.println(p.getCacheSize());
    }
}
