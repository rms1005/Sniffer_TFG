
package dominio.preferences.insercion;

import dominio.FachadaDominio;
import dominio.preferences.preferencesFileRead;
import java.io.File;

/**
 * Clase PrefExpInsercion.
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,
 * @version 1.2
 */
public class PrefExpInsercion {

	public PrefExpInsercion() {
		leer = FachadaDominio.getPreferences();
	}

	private File fich;
	private preferencesFileRead leer;
}
