package dominio.preferences.detalle;

import org.jdom.Element;

import dominio.preferences.preferencesBeanDetallePaquete;

/**
 * Clase ElemPacketDetBytesLength.
 * 
 * @author Jose Manuel Saiz, Raul Merinero Sanz
 * @author jmsaizg@gmail.com, rms1005@alu.ubu.es
 * @version 1.0
 */

public class ElemPacketDetBytesLength extends Element {

	private static final long serialVersionUID = -1513065009834352912L;
	
	public ElemPacketDetBytesLength(preferencesBeanDetallePaquete pBDetallePaquete) {
		super("BytesLength");
		setPBDetallePaquete(pBDetallePaquete);
		setBytesLength();
	}
	
	private void setBytesLength() {
		addContent((new Element("Length")).setText(getPBDetallePaquete().getBytesLength()));
	}

	private void setPBDetallePaquete(preferencesBeanDetallePaquete aux) {
		this.pBDetallePaquete = aux;
	}

	private preferencesBeanDetallePaquete getPBDetallePaquete() {
		return pBDetallePaquete;
	}

	private preferencesBeanDetallePaquete pBDetallePaquete;

}
