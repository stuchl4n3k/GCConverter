package gcconverter.component;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author stuchlanek
 */
public class LmxFileFilter extends FileFilter {

    String description = "LMX files *.lmx";
    String extension = "lmx";

    @Override
    public boolean accept(File f) {
	if (f.isDirectory()) {
	    return true;
	} else {
	    String path = f.getAbsolutePath().toLowerCase();
	    if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public String getDescription() {
	return description;
    }
}
