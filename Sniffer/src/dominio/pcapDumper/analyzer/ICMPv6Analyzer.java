
package dominio.pcapDumper.analyzer;

import java.util.Hashtable;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip6;
//import org.jnetpcap.protocol.lan.Ethernet.EthernetType;
//import org.jnetpcap.protocol.network.Icmp.DestinationUnreachable;
import org.jnetpcap.protocol.network.Icmp.IcmpType;

/**
 * Clase ICMAnalyzer.
 * 
 * @author Jose Manuel Saiz, Rodrigo S�nchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
 */

public class ICMPv6Analyzer extends JDPacketAnalyzer {
	Icmpv6 icmpv6packet = new Icmpv6();
	Icmpv6.EchoReply echo = new Icmpv6.EchoReply();
	Icmpv6.Redirect redirip = new Icmpv6.Redirect();

	Ip6 ip6 = new Ip6();
	private Icmpv6 icmpv6;

	public ICMPv6Analyzer() {
		values = new Hashtable<String, Object>();
		layer = TRANSPORT_LAYER;
		
		typeNames = new Hashtable<Integer, String>();
		typeNames.put(1, "Destination Unreachable (1)");
		typeNames.put(2, "Packet Too Big (2)");
		typeNames.put(3, "Time Exceeded (3)");
		typeNames.put(4, "Parameter Problem (4)");
		typeNames.put(100, "Private experimentation (100)");
		typeNames.put(101, "Private experimentation (101)");
		typeNames.put(127, "Reserved for expansion of ICMPv6 error messages (127)");
		typeNames.put(128, "Echo Request (128)");
		typeNames.put(129, "Echo Reply (129)");
		typeNames.put(200, "Private experimentation (200)");
		typeNames.put(201, "Private experimentation (201)");
		typeNames.put(255, "Reserved for expansion of ICMPv6 informational messages (255)");
		
		codes = new Hashtable<Integer, Hashtable<Integer,String>>();
		codes.put(1, new Hashtable<Integer, String>());
		codes.get(1).put(0, "no route to destination (0)");
		codes.get(1).put(1, "communication with destination administratively prohibited (1)");
		codes.get(1).put(2, "beyond scope of source address (2)");
		codes.get(1).put(3, "address unreachable (3)");
		codes.get(1).put(4, "port unreachable (4)");
		codes.get(1).put(5, "source address failed ingress/egress policy (5)");
		codes.get(1).put(6, "reject route to destination (6)");
		codes.get(1).put(7, "Error in Source Routing Header (7)");
		codes.put(3, new Hashtable<Integer, String>());
		codes.get(3).put(0, "hop limit exceeded in transit (0)");
		codes.get(3).put(1, "fragment reassembly time exceeded (1)");
		codes.put(4, new Hashtable<Integer, String>());
		codes.get(4).put(0, "erroneous header field encountered (0)");
		codes.get(4).put(1, "unrecognized Next Header type encountered (1)");
		codes.get(4).put(2, "unrecognized IPv6 option encountered (2)");
	}

	/**
	 * Metodo donde se analiza el paquete recibido y se sabe su protocolo es o no de
	 * tipo ICMP.
	 * 
	 * @param PcapPacket p
	 * @return boolean
	 * @exception exceptions Ningún error (Excepción) definida
	 */
	public boolean isAnalyzable(PcapPacket p) {
		return p.hasHeader(icmpv6packet);
	}

	public String getProtocolName() {
		return "ICMPv6";
	}

	public String[] getValueNames() {
		return valueNames;
	}

	/**
	 * Metodo donde se analiza el paquete recibido convierte a un paquete objeto de
	 * con un tipo de protocolo ICMP.
	 * 
	 * @param PcapPacket p
	 * @return sin valor de retorno
	 * @exception exceptions Ningún error (Excepción) definida
	 */
	public void analyze(PcapPacket p) {
		if (!isAnalyzable(p))
			return;
		values.clear();

		icmpv6 = icmpv6packet;
		
		int tipo;
		if (typeNames.containsKey(icmpv6.type())) {
			values.put(valueNames[0], Icmpv6.Icmpv6Type.toString(icmpv6.type()));
			if (codes.get(icmpv6.type()).containsKey(icmpv6.code()))
				values.put(valueNames[1], codes.get(icmpv6.type()).get(icmpv6.code()));
			else
				values.put(valueNames[1], "Unknown (" + icmpv6.code() + ")");
		} else {
			values.put(valueNames[0], "Unknown (" + icmpv6.type() + ")");
			values.put(valueNames[1], "Unknown (" + icmpv6.code() + ")");
		}
		
	}

	public Object getValue(String valueName) {
		return values.get(valueName);
	}

	public Object getValueAt(int index) {
		if (index < 0 || index >= valueNames.length)
			return null;
		else
			return values.get(valueNames[index]);
	}

	public Object[] getValues() {
		Object v[] = new Object[valueNames.length];
		for (int i = 0; i < valueNames.length; i++)
			v[i] = values.get(valueNames[i]);

		return v;
	}

	private static final String valueNames[] = { "Type", "Code" };
	private static final Hashtable<Integer, String> typeNames;
	private static final Hashtable<Integer, Hashtable<Integer, String>> codes;
	private Hashtable<String, Object> values;

}
