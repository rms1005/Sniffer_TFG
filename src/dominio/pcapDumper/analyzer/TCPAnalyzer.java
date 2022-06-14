
package dominio.pcapDumper.analyzer;

import java.util.Hashtable;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.tcpip.Tcp;

/** 
 * Clase TCPAnalyzer. 
 * 
 * @author Jose Manuel Saiz, Rodrigo S�nchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
*/
// Referenced classes of package dominio.pcapDumper.analyzer:
//            JDPacketAnalyzer

public class TCPAnalyzer extends JDPacketAnalyzer
{
	Tcp tcp = new Tcp();
    public TCPAnalyzer()
    {
        values = new Hashtable();
        layer = TRANSPORT_LAYER;
    }
    /** Metodo  donde se analiza el paquete recibido y se sabe su protocolo es o no de tipo TCP.
     * @param PcapPacket p 
     * @return boolean 
     * @exception exceptions Ning�n error (Excepci�n) definida
     */
    public boolean isAnalyzable(PcapPacket p)
    {
        return p.hasHeader(tcp);
    }

    public String getProtocolName()
    {
        return "TCP";
    }

    public String[] getValueNames()
    {
        return valueNames;
    }
    /** Metodo  donde se analiza el paquete recibido convierte a un paquete objeto de 
     * con un tipo de protocolo TCP.
     * @param PcapPacket p 
     * @return sin valor de retorno
     * @exception exceptions Ning�n error (Excepci�n) definida
     */  
    public void analyze(PcapPacket p)
    {
        values.clear();
        if(!isAnalyzable(p))
        {
            return;
        } else
        {
            values.put(valueNames[0], Integer.valueOf(tcp.source()));
            values.put(valueNames[1], Integer.valueOf(tcp.destination()));
            values.put(valueNames[2], Long.valueOf(tcp.seq()));
            values.put(valueNames[3], Long.valueOf(tcp.ack()));
            values.put(valueNames[4], Boolean.valueOf(tcp.flags_URG()));
            values.put(valueNames[5], Boolean.valueOf(tcp.flags_ACK()));
            values.put(valueNames[6], Boolean.valueOf(tcp.flags_PSH()));
            values.put(valueNames[7], Boolean.valueOf(tcp.flags_RST()));
            values.put(valueNames[8], Boolean.valueOf(tcp.flags_SYN()));
            values.put(valueNames[9], Boolean.valueOf(tcp.flags_FIN()));
            values.put(valueNames[10], Integer.valueOf(tcp.window()));
            return;
        }
    }

    public Object getValue(String valueName)
    {
        return values.get(valueName);
    }

    Object getValueAt(int index)
    {
        if(index < 0 || index >= valueNames.length)
            return null;
        else
            return values.get(valueNames[index]);
    }

    public Object[] getValues()
    {
        Object v[] = new Object[valueNames.length];
        for(int i = 0; i < valueNames.length; i++)
            v[i] = values.get(valueNames[i]);

        return v;
    }

    private static final String valueNames[] = {
        "Source Port", "Destination Port", "Sequence Number", "Ack Number", "URG Flag", "ACK Flag", "PSH Flag", "RST Flag", "SYN Flag", "FIN Flag", 
        "Window Size"
    };
    Hashtable values;

}
