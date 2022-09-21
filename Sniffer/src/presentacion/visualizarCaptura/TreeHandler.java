
package presentacion.visualizarCaptura;

import dominio.pcapDumper.analyzer.*;
import dominio.preferences.identificacion.PrefIdentificacion;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.application.DNS;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Icmpv6;
import org.jnetpcap.protocol.network.Igmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

/**
 * Clase TreeHanler.
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
 */

public class TreeHandler {
	Tcp tcpPacket = new Tcp();
	Icmp icmpPacket = new Icmp();
	Icmpv6 icmpv6Packet = new Icmpv6();
	Igmp igmpPacket = new Igmp();
	DNS dnsPacket = new DNS();
	Udp udpPacket = new Udp();
	Arp arpPacket = new Arp();
	Ip4 ipPacket = new Ip4();
	Ip6 ip6Packet = new Ip6();
	Ethernet ethernetPacket = new Ethernet();

	public TreeHandler(PcapPacket paquete) {
		PrefIdentificacion pref = new PrefIdentificacion();
		try {
			if (paquete instanceof PcapPacket)
				TreePacket.treepk(paquete);
			if (paquete.hasHeader(ethernetPacket))
				TreePacket.treepkether(paquete);
			if (paquete.hasHeader(arpPacket))
				TreePacket.treepkARP(paquete);
			if (paquete.hasHeader(ipPacket))
				TreePacket.treepkIP(paquete);
			if (paquete.hasHeader(ip6Packet))
				TreePacket.treepkIP6(paquete);
			if (paquete.hasHeader(icmpPacket))
				TreePacket.treepkICMP(paquete);
			if (paquete.hasHeader(icmpv6Packet))
				TreePacket.treepkICMPv6(paquete);
			if (paquete.hasHeader(igmpPacket))
				TreePacket.treepkIGMP(paquete);
			if (paquete.hasHeader(dnsPacket))
				TreePacket.treepkDNS(paquete);
			if (paquete.hasHeader(tcpPacket))
				TreePacket.treepkTCP(paquete);
			if (paquete.hasHeader(udpPacket))
				TreePacket.treepkUDP(paquete);

			JDPacketAnalyzer packetAnalyzer = new FTPAnalyzer();
			if (packetAnalyzer.isAnalyzable(paquete))
				TreePacket.treepkFTP(paquete);
			packetAnalyzer = new HTTPAnalyzer();
			if (packetAnalyzer.isAnalyzable(paquete))
				TreePacket.treepkHTTP(paquete);
			packetAnalyzer = new POP3Analyzer();
			if (packetAnalyzer.isAnalyzable(paquete))
				TreePacket.treepkPOP3(paquete);
			packetAnalyzer = new SMTPAnalyzer();
			if (packetAnalyzer.isAnalyzable(paquete))
				TreePacket.treepkSMTP(paquete);
			packetAnalyzer = new SSHAnalyzer();
			if (packetAnalyzer.isAnalyzable(paquete))
				TreePacket.treepkSSH(paquete);
			packetAnalyzer = new TelnetAnalyzer();
			if (packetAnalyzer.isAnalyzable(paquete))
				TreePacket.treepkTelnet(paquete);
			OtroAnalyzer otro = new OtroAnalyzer();
			if (otro.isAnalyzable(paquete) && !packetAnalyzer.isAnalyzable(paquete))
				TreePacket.treepkOtro(paquete, pref);
			TreePacket.mostrarBytesIniciales(paquete);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
