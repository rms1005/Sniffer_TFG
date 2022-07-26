
package dominio.preferences.fromfile;

import dominio.preferences.preferencesBeanFromFile;
import org.jdom.Element;

/**
 * Clase PrefFromFile.
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,
 * @version 1.2
 */
// Referenced classes of package dominio.preferences.capture:
//            ElemFromCapture, ElemFromFile, ElemFromStopCapture

public class PrefFromFile extends Element {

	private static final long serialVersionUID = -7121609877213565176L;
	

	public PrefFromFile(preferencesBeanFromFile pBFromFile) {
		super("FromFilePreferences");
		try {
			setPBFromFile(pBFromFile);
			setElemCapture();
			setElemFile();
			setElemStopCapture();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setElemCapture() {
		addContent(new ElemFromCapture(getPBFromFile()));
	}

	private void setElemFile() {
		addContent(new ElemFromFile(getPBFromFile()));
	}

	private void setElemStopCapture() {
		addContent(new ElemFromStopCapture(getPBFromFile()));
	}

	private void setPBFromFile(preferencesBeanFromFile aux) {
		pBFromFile = aux;
	}

	private preferencesBeanFromFile getPBFromFile() {
		return pBFromFile;
	}

	private preferencesBeanFromFile pBFromFile;
}
