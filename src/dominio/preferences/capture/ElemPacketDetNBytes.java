package dominio.preferences.capture;

import org.jdom.Element;

import dominio.preferences.preferencesBeanDetallePaquete;
import dominio.preferences.preferencesOperation;

public class ElemPacketDetNBytes extends Element
{
	private static final long serialVersionUID = -8391803643988381843L;
	
	
	public ElemPacketDetNBytes(preferencesBeanDetallePaquete pBDetallePaquete)
	{
		super("NBytes");
		pOperation = new preferencesOperation();
		setPBDetallePaquete(pBDetallePaquete);
		setComplete();
		setNBytes();
	}
	
	private void setComplete()
	{
		addContent((new Element("Complete")).setText(pOperation.validate(getPBDetallePaquete().isTotalBytes())));
	}
	
	private void setNBytes()
	{
		addContent((new Element("FirstBytes")).setText(getPBDetallePaquete().getBytes()));
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
