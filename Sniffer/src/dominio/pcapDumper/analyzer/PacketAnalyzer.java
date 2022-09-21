
package dominio.pcapDumper.analyzer;

import java.util.Date;

import org.jnetpcap.packet.PcapPacket;

//
//import jpcap.packet.Packet;
/**
 * Clase PacketAnalyzer.
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
 */
// Referenced classes of package dominio.pcapDumper.analyzer:
//            JDPacketAnalyzer

public class PacketAnalyzer extends JDPacketAnalyzer {

	public PacketAnalyzer() {
	}

	/**
	 * Metodo donde se analiza el paquete recibido y se sabe su protocolo es o no de
	 * tipo ARP.
	 * 
	 * @param PcapPacket p
	 * @return boolean
	 * @exception exceptions Ningún error (Excepción) definida
	 */
	public boolean isAnalyzable(PcapPacket packet) {
		return true;
	}

	public String getProtocolName() {
		return "Packet Information";
	}

	public String[] getValueNames() {
		return valueNames;
	}

	/**
	 * Metodo donde se analiza el paquete recibido convierte a un paquete objeto de
	 * con un tipo de protocolo ARP.
	 * 
	 * @param PcapPacket p
	 * @return sin valor de retorno
	 * @exception exceptions Ningún error (Excepción) definida
	 */
	public void analyze(PcapPacket p) {
		packet = p;
	}

	public Object getValue(String name) {
		if (name.equals(valueNames[0]))
			return (new Date(packet.getCaptureHeader().timestampInMillis()).toString());
		// return (new Date(packet.sec * 1000L + packet.usec / 1000L)).toString();
		if (name.equals(valueNames[1]))
			return Integer.valueOf(packet.getCaptureHeader().caplen());
		// return new Integer(packet.caplen);
		else
			return null;
	}

	Object getValueAt(int index) {
		switch (index) {
		case 0: // '\0'
			return String.valueOf(
					new Date(packet.getCaptureHeader().hdr_sec() + packet.getCaptureHeader().hdr_usec()).toString());

//            return (new Date(packet.sec * 1000L + packet.usec / 1000L)).toString();

		case 1: // '\001'
			return Integer.valueOf(packet.getCaptureHeader().caplen());
		// return new Integer(packet.caplen);
		}
		return null;
	}

	public Object[] getValues() {
		Object v[] = new Object[2];
		v[0] = (new Date(packet.getCaptureHeader().hdr_sec() + packet.getCaptureHeader().hdr_usec()).toString());
		// v[0] = (new Date(packet.sec * 1000L + packet.usec / 1000L)).toString();
		v[1] = Integer.valueOf(packet.getCaptureHeader().caplen());
		// v[1] = new Integer(packet.caplen);
		return v;
	}

	private static final String valueNames[] = { "Captured Time", "Captured Length" };
	private PcapPacket packet;

}
