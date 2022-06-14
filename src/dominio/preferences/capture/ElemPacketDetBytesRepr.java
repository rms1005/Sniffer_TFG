package dominio.preferences.capture;

import org.jdom.Element;

import dominio.preferences.preferencesBeanDetallePaquete;
import dominio.preferences.preferencesOperation;

public class ElemPacketDetBytesRepr extends Element
{
	private static final long serialVersionUID = -5097524564665501601L;
	
	
	public ElemPacketDetBytesRepr(preferencesBeanDetallePaquete pBDetallePaquete)
	{
		super("BytesRepresentation");
		pOperation = new preferencesOperation();
		setPBDetallePaquete(pBDetallePaquete);
		setRepresentation();
	}
	
	private void setRepresentation()
	{
		addContent((new Element("Hex")).setText(pOperation.validate(getPBDetallePaquete().isBytesHex())));
	}
	
	private void setPBDetallePaquete(preferencesBeanDetallePaquete aux)
	{
		this.pBDetallePaquete = aux;
	}
	
	private preferencesBeanDetallePaquete getPBDetallePaquete()
	{
		return pBDetallePaquete;
	}
	
	
	private preferencesBeanDetallePaquete pBDetallePaquete;
    private preferencesOperation pOperation;

}
