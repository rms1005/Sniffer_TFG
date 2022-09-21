package dominio.pcapDumper.analyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Http.Request;

//import jpcap.packet.Packet;
//import jpcap.packet.TCPPacket;

/**
 * Clase HTTPAnalyzer.
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
 */
public class HTTPAnalyzer extends JDPacketAnalyzer {
	private static final String[] valueNames = { "Method", "Header", "Version", "Host", "Request Url" };
	String method;
	Vector<String> headers = new Vector<String>();
	Tcp tcppacket = new Tcp();
	Http httpheader = new Http();

	public HTTPAnalyzer() {
		this.layer = APPLICATION_LAYER;
	}

	/**
	 * Metodo donde se analiza el paquete recibido y se sabe su protocolo es o no de
	 * tipo ARP.
	 * 
	 * @param PcapPacket p
	 * @return boolean
	 * @exception exceptions Ningún error (Excepción) definida
	 */
	public boolean isAnalyzable(PcapPacket p) {
		/*
		 * if(p.hasHeader(httpheader)) System.out.println(httpheader.toString()); return
		 * p.hasHeader(tcppacket) && p.hasHeader(httpheader);
		 */
		return p.hasHeader(tcppacket) && (tcppacket.source() == 80 || tcppacket.destination() == 80);
	}

	public String getProtocolName() {
		return "HTTP";
	}

	public String[] getValueNames() {
		return valueNames;
	}

	/**
	 * Metodo donde se analiza el paquete recibido convierte a un paquete objeto de
	 * con un tipo de protocolo HTTP.
	 * 
	 * @param PcapPacket p
	 * @return sin valor de retorno
	 * @exception exceptions Ningún error (Excepción) definida
	 */
	public void analyze(PcapPacket p) {
		this.method = "";
		this.headers.removeAllElements();
		if (!isAnalyzable(p)) {
			return;
		}
		p.getHeader(httpheader);
		try {
			BufferedReader in = new BufferedReader(new StringReader(new String(p.toString())));

			this.method = in.readLine();
			if ((this.method == null) || (this.method.indexOf("HTTP") == -1)) {
				this.method = httpheader.fieldValue(Request.RequestMethod);
				return;
			}
			String l;
			while ((l = in.readLine()).length() > 0) {

				this.headers.addElement(l);
			}
		} catch (IOException localIOException) {
		} catch (Exception localException) {
		}
	}

	public Object getValue(String valueName) {
		for (int i = 0; i < valueNames.length; i++)
			if (valueNames[i].equals(valueName))
				return getValueAt(i);

		return null;
	}

	Object getValueAt(int index) {
		if (index == 0)
			return this.method;
		else if (index == 1)
			return this.headers;
		else if (index == 2)
			return httpheader.fieldValue(Request.RequestVersion);
		else if (index == 3)
			return httpheader.fieldValue(Request.Host);
		else if (index == 4)
			return httpheader.fieldValue(Request.RequestUrl);
		return null;
	}

	public Object[] getValues() {
		Object v[] = new Object[valueNames.length];
		for (int i = 0; i < valueNames.length; i++)
			v[i] = getValueAt(i);

		return v;
	}
}
