package dominio.preferences.detalle;

import org.jdom.Element;

import dominio.preferences.preferencesBeanDetallePaquete;

public class PrefDetallePaquete extends Element {

	private static final long serialVersionUID = -5701701986123880964L;

	public PrefDetallePaquete(preferencesBeanDetallePaquete pBDetallePaquete) {
		super("DetallePaquetePreferences");
		try {
			setPBDetallePaquete(pBDetallePaquete);
			setElemNWindows();
			setElemNBytes();
			setElemBytesRepresentation();
			setElemBytesLength();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setElemNWindows() {
		addContent(new ElemPacketDetNWindows(getPBDetallePaquete()));
	}

	private void setElemNBytes() {
		addContent(new ElemPacketDetNBytes(getPBDetallePaquete()));
	}

	private void setElemBytesRepresentation() {
		addContent(new ElemPacketDetBytesRepr(getPBDetallePaquete()));
	}
	
	private void setElemBytesLength() {
		addContent(new ElemPacketDetBytesLength(getPBDetallePaquete()));
	}

	private preferencesBeanDetallePaquete getPBDetallePaquete() {
		return pBDetallePaquete;
	}

	private void setPBDetallePaquete(preferencesBeanDetallePaquete pBDetallePaquete) {
		this.pBDetallePaquete = pBDetallePaquete;
	}

	private preferencesBeanDetallePaquete pBDetallePaquete;

}
