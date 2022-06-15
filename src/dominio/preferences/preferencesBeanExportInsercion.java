
package dominio.preferences;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase preferencesBeanExportInsercion.
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,
 * @version 1.2
 */
public class preferencesBeanExportInsercion implements Serializable {

	private static final long serialVersionUID = -9145883526138646849L;
	
	
	public preferencesBeanExportInsercion() {
		setDefaultSettings();
	}

	public void setDefaultSettings() {
		ipOrigen = new ArrayList<String>();
		ipDestino = new ArrayList<String>();
		macOrigen = new ArrayList<String>();
		macDestino = new ArrayList<String>();
	}

	public void setIpOrigen(String text) {
		ipOrigen.add(text);
	}

	public void setIpDestino(String text) {
		ipDestino.add(text);
	}

	public void setMacOrigen(String text) {
		macOrigen.add(text);
	}

	public void setMacDestino(String text) {
		macDestino.add(text);
	}

	public ArrayList<String> getIpOrigen() {
		return ipOrigen;
	}

	public ArrayList<String> getIpDestino() {
		return ipDestino;
	}

	public ArrayList<String> getMacOrigen() {
		return macOrigen;
	}

	public ArrayList<String> getMacDestino() {
		return macDestino;
	}

	ArrayList<String> ipOrigen;
	ArrayList<String> ipDestino;
	ArrayList<String> macOrigen;
	ArrayList<String> macDestino;
}
