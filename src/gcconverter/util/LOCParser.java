package gcconverter.util;

import gcconverter.entity.Waypoint;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author stuchlanek
 */
public class LOCParser {

    public static ArrayList<Waypoint> parse(File file) {
	ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
	try {
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    Document doc = db.parse(file);
	    doc.getDocumentElement().normalize();
	    NodeList wpNodes = doc.getElementsByTagName("waypoint");
	    for (int i=0; i<wpNodes.getLength(); i++) {
		Node waypoint = wpNodes.item(i);
		if (waypoint.getNodeType() == Node.ELEMENT_NODE) {
		    Element waypointElement = (Element) waypoint;

		    // Name
		    NodeList names = waypointElement.getElementsByTagName("name");
		    Node nameNode = names.item(0);
		    if (nameNode == null) return null;
		    String name = nameNode.getTextContent();

		    // ID
		    NamedNodeMap attributes = nameNode.getAttributes();
		    Node idNode = attributes.getNamedItem("id");
		    String id = idNode.getNodeValue();

		    // Lat and Lon
		    NodeList coords = waypointElement.getElementsByTagName("coord");
		    Node coordNode = coords.item(0);
		    if (coordNode == null) return null;
		    attributes = coordNode.getAttributes();
		    Node lat = attributes.getNamedItem("lat");
		    Node lon = attributes.getNamedItem("lon");
		    if (lon == null || lat == null) return null;
		    float latitude = Float.parseFloat(lat.getNodeValue());
		    float longitude = Float.parseFloat(lon.getNodeValue());

		    // create a waypoint
		    Waypoint w = new Waypoint(id, name, latitude, longitude);

		    /*
		    HTMLParser parser = new HTMLParser("http://www.geocaching.com/seek/cache_details.aspx?wp="+id);
		    parser.connect();
		    w.setSize(parser.getCacheSize());
		    w.setType(parser.getCacheType());
		    w.setHint(parser.getCacheHint());
		     *
		     */

		    waypoints.add(w);
		}
	    }
	} catch (SAXException ex) {
	    Logger.getLogger(LOCParser.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(LOCParser.class.getName()).log(Level.SEVERE, null, ex);
	} catch (ParserConfigurationException ex) {
	    Logger.getLogger(LOCParser.class.getName()).log(Level.SEVERE, null, ex);
	}
	return waypoints;
    }

}
