package presentacion.seleccionFicheros;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.filechooser.FileFilter;

/**
 * Clase FiltroFileChooser.
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,
 * @version 1.2
 */
public class FiltroFileChooser extends FileFilter {
	private static String TYPE_UNKNOWN = "Type Unknown";
	private static String HIDDEN_FILE = "Hidden File";
	private Hashtable<String, FiltroFileChooser> filters = null;
	private String description = null;
	private String fullDescription = null;
	private boolean useExtensionsInDescription = true;

	public FiltroFileChooser() {
		this.filters = new Hashtable<String, FiltroFileChooser>();
	}

	public FiltroFileChooser(String extension) {
		this(extension, null);
	}

	public FiltroFileChooser(String extension, String description) {
		this();
		if (extension != null) {
			addExtension(extension);
		}
		if (description != null) {
			setDescription(description);
		}
	}

	public FiltroFileChooser(String[] filters) {
		this(filters, null);
	}

	public FiltroFileChooser(String[] filters, String description) {
		this();
		for (int i = 0; i < filters.length; i++) {
			addExtension(filters[i]);
		}
		if (description != null) {
			setDescription(description);
		}
	}

	public boolean accept(File f) {
		if (f != null) {
			if (f.isDirectory()) {
				return true;
			}
			String extension = getExtension(f);
			if ((extension != null) && (this.filters.get(getExtension(f)) != null)) {
				return true;
			}
		}
		return false;
	}

	public String getExtension(File f) {
		if (f != null) {
			String filename = f.getName();
			int i = filename.lastIndexOf('.');
			if ((i > 0) && (i < filename.length() - 1)) {
				return filename.substring(i + 1).toLowerCase();
			}
		}
		return null;
	}

	public void addExtension(String extension) {
		if (this.filters == null) {
			this.filters = new Hashtable<String, FiltroFileChooser>(5);
		}
		this.filters.put(extension.toLowerCase(), this);
		this.fullDescription = null;
	}

	public String getDescription() {
		if (this.fullDescription == null) {
			if ((this.description == null) || (isExtensionListInDescription())) {
				this.fullDescription = (this.description + " (");

				Enumeration<String> extensions = this.filters.keys();
				if (extensions != null) {
					this.fullDescription = (this.fullDescription + "." + (String) extensions.nextElement());
					while (extensions.hasMoreElements()) {
						this.fullDescription = (this.fullDescription + ", ." + (String) extensions.nextElement());
					}
				}
				this.fullDescription += ")";
			} else {
				this.fullDescription = this.description;
			}
		}
		return this.fullDescription;
	}

	public void setDescription(String description) {
		this.description = description;
		this.fullDescription = null;
	}

	public void setExtensionListInDescription(boolean b) {
		this.useExtensionsInDescription = b;
		this.fullDescription = null;
	}

	public boolean isExtensionListInDescription() {
		return this.useExtensionsInDescription;
	}
}
