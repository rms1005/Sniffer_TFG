package dominio.preferences.detalle;

import org.jdom.Element;

import dominio.preferences.preferencesBeanDetallePaquete;

/**
 * Clase ElemPacketDetNWindows.
 * 
 * @author Jose Manuel Saiz, Raul Merinero Sanz
 * @author jmsaizg@gmail.com, rms1005@alu.ubu.es
 * @version 1.0
 */

public class ElemPacketDetNWindows extends Element {
	private static final long serialVersionUID = -8403961950222386223L;

	public ElemPacketDetNWindows(preferencesBeanDetallePaquete pBDetallePaquete) {
		super("NWindows");
		setPBDetallePaquete(pBDetallePaquete);
		setRows();
		setColumns();
	}

	private void setRows() {
		addContent((new Element("Rows")).setText(getPBDetallePaquete().getRows()));
	}

	private void setColumns() {
		addContent((new Element("Columns")).setText(getPBDetallePaquete().getColumns()));
	}

	private void setPBDetallePaquete(preferencesBeanDetallePaquete aux) {
		this.pBDetallePaquete = aux;
	}

	private preferencesBeanDetallePaquete getPBDetallePaquete() {
		return pBDetallePaquete;
	}

	private preferencesBeanDetallePaquete pBDetallePaquete;

}
