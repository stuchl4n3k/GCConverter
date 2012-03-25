package gcconverter.entity;

import java.net.URL;
import javax.swing.ImageIcon;


/**
 *
 * @author stuchlanek
 */
public class Waypoint {
    protected String id;
    protected String name;
    protected String type;
    protected String size;
    protected float latitude;
    protected float longitude;
    protected String hint;
    protected boolean found;

    public Waypoint(String id, String name, float latitude, float longitude) {
	this.id = id;
	this.name = name;
	this.latitude = latitude;
	this.longitude = longitude;
	this.hint = null;
	this.found = false;
    }

    public Waypoint(String id, String name, String type, String size, float latitude, float longitude, String hint, boolean found) {
	this.id = id;
	this.name = name;
	this.type = type;
	this.size = size;
	this.latitude = latitude;
	this.longitude = longitude;
	this.hint = hint;
	this.found = found;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public float getLatitude() {
	return latitude;
    }

    public void setLatitude(float latitude) {
	this.latitude = latitude;
    }

    public float getLongitude() {
	return longitude;
    }

    public void setLongitude(float longitude) {
	this.longitude = longitude;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getHint() {
	return hint;
    }

    public void setHint(String hint) {
	this.hint = hint;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public boolean isFound() {
	return found;
    }

    public void setFound(boolean found) {
	this.found = found;
    }

    public String getSize() {
	return size;
    }

    public void setSize(String size) {
	this.size = size;
    }
    
    public ImageIcon getSizeIcon() {
        if (getSize() == null) {
            return null;
        }
        URL resource = getClass().getResource("/gcconverter/resources/icons/" + getSize() + ".gif");
        if (resource == null) return null;
        return new ImageIcon(resource);
    }
    
    public ImageIcon getTypeIcon() {
        if (getType() == null) {
            return null;
        }
        URL resource = getClass().getResource("/gcconverter/resources/icons/" + getType() + ".gif");
        if (resource == null) return null;
        return new ImageIcon(resource);
    }

    @Override
    public String toString() {
	return "Waypoint{" + " | id=" + id + " | name=" + name + " | type=" + type + " | size=" + size + " | latitude=" + latitude + " | longitude=" + longitude + " | hint=" + hint + " | found=" + found + '}';
    }


    @Override
    public int hashCode() {
	int hash = 7;
	hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Waypoint other = (Waypoint) obj;
        
        if (this.id == null && other.id == null) {
            return super.equals(obj);
        } else if (this.id == null) {
            return false;
        } else if (other.id == null) {
            return false;
        } else {
            return this.id.equalsIgnoreCase(other.id);
        }
    }

    public boolean hasOnlineData() {
        return getType() != null;
    }

    

    




    
}
