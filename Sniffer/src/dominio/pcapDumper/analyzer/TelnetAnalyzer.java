
package dominio.pcapDumper.analyzer;

//import jpcap.packet.Packet;
//import jpcap.packet.TCPPacket;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.tcpip.Tcp;

/**
 * Clase TelnetAnalyzer.
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
 */
// Referenced classes of package dominio.pcapDumper.analyzer:
//            JDPacketAnalyzer

public class TelnetAnalyzer extends JDPacketAnalyzer {
	private static final String[] valueNames = {};
	Tcp tcppacket = new Tcp();

	public TelnetAnalyzer() {
		layer = APPLICATION_LAYER;
	}

	/**
	 * Metodo donde se analiza el paquete recibido y se sabe su protocolo es o no de
	 * tipo TelNet.
	 * 
	 * @param PcapPacket p
	 * @return boolean
	 * @exception exceptions Ningún error (Excepción) definida
	 */
	public boolean isAnalyzable(PcapPacket p) {
		return (p.hasHeader(tcppacket) && (tcppacket.source() == 23 || tcppacket.destination() == 23));
	}

	public String getProtocolName() {
		return "Telnet";
	}

	public String[] getValueNames() {
		return valueNames;
	}

	/**
	 * Metodo donde se analiza el paquete recibido convierte a un paquete objeto de
	 * con un tipo de protocolo TelNet.
	 * 
	 * @param PcapPacket p
	 * @return sin valor de retorno
	 * @exception exceptions Ningún error (Excepción) definida
	 */
	public void analyze(PcapPacket packet) {
	}

	public Object getValue(String s) {
		for (int i = 0; i < valueNames.length; i++)
			if (valueNames[i].equals(s))
				return getValueAt(i);

		return null;
	}

	public Object getValueAt(int i) {
		if (i == 0)
			return null;
		return null;
	}

	public Object[] getValues() {
		Object v[] = new Object[valueNames.length];
		for (int i = 0; i < valueNames.length; i++)
			v[i] = getValueAt(i);

		return v;
	}
}
