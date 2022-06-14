
package dominio.pcapDumper.analyzer;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.tcpip.Tcp;

//import jpcap.packet.Packet;
//import jpcap.packet.TCPPacket;
/** 
 * Clase POP3Analyzer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo S�nchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package dominio.pcapDumper.analyzer:
//            JDPacketAnalyzer

public class POP3Analyzer extends JDPacketAnalyzer
{
	private static final String[] valueNames = {};
	Tcp tcppacket = new Tcp();
	
    public POP3Analyzer()
    {
        layer = APPLICATION_LAYER;
    }
    /** Metodo  donde se analiza el paquete recibido y se sabe su protocolo es o no de tipo ARP.
     * @param PcapPacket p 
     * @return boolean 
     * @exception exceptions Ning�n error (Excepci�n) definida
     */
    public boolean isAnalyzable(PcapPacket p)
    {
    	
    	return (p.hasHeader(tcppacket) && (tcppacket.source() == 110 || tcppacket.destination() == 110));
    }    			
    public String getProtocolName()
    {
        return "POP3";
    }

    public String[] getValueNames()
    {
        return valueNames;
    }
    /** Metodo 
     * @param PcapPacket p 
     * @return sin valor de retorno
     * @exception exceptions Ning�n error (Excepci�n) definida
     */  
    public void analyze(PcapPacket packet)
    {
    }

    public Object getValue(String s)
    {
    	for (int i = 0; i < valueNames.length; i++)
			if (valueNames[i].equals(s))
				return getValueAt(i);

		return null;
    }

    public Object getValueAt(int i)
    {
    	if (i == 0)
			return null;
		return null;
    }

    public Object[] getValues()
    {
    	Object v[] = new Object[valueNames.length];
		for (int i = 0; i < valueNames.length; i++)
			v[i] = getValueAt(i);

		return v;
    }
}
