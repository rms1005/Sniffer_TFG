package dominio.pcapDumper.analyzer;

import java.util.Hashtable;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.application.DNS;

/**
 * Clase DNSAnalyzer.
 * 
 * @author Jose Manuel Saiz, Raul Merinero Sanz
 * @author jmsaizg@gmail.com, rms1005@alu.ubu.es
 * @version 1.4
 */

public class DNSAnalyzer extends JDPacketAnalyzer {

	DNS dnsPacket = new DNS();
	
	private DNS dns;
	
	public DNSAnalyzer() {
		values = new Hashtable<String, Object>();
		layer = APPLICATION_LAYER;
	}
	
	@Override
	public boolean isAnalyzable(PcapPacket p) {
		return p.hasHeader(dnsPacket);
	}

	@Override
	public void analyze(PcapPacket p) {
		if (!isAnalyzable(p))
			return;
		values.clear();
		
		dns = dnsPacket;
		
		values.put(valueNames[0], dns.identification());
		values.put(valueNames[1], dns.flags_QR());
		values.put(valueNames[2], dns.flags_Opcode());
		values.put(valueNames[3], dns.flags_TC());
		values.put(valueNames[4], dns.flags_RD());
		values.put(valueNames[5], dns.flags_Z());
		values.put(valueNames[6], dns.flags_RCODE());
		values.put(valueNames[7], dns.qdCount());
		values.put(valueNames[8], dns.anCount());
		values.put(valueNames[9], dns.nsCount());
		values.put(valueNames[10], dns.arCount());
	}

	@Override
	public String getProtocolName() {
		return "DNS";
	}

	@Override
	public String[] getValueNames() {
		return valueNames;
	}

	@Override
	public Object getValue(String valueName) {
		return values.get(valueName);
	}

	@Override
	Object getValueAt(int index) {
		if (index < 0 || index >= valueNames.length)
			return null;
		else
			return values.get(valueNames[index]);
	}

	@Override
	public Object[] getValues() {
		Object v[] = new Object[valueNames.length];
		for (int i = 0; i < valueNames.length; i++)
			v[i] = values.get(valueNames[i]);

		return v;
	}
	
	private static final String valueNames[] = { "Transaction ID", "Response", "Opcode", "Truncated", "Recursion desired", "Z", "Non-authenticated data", "Questions", "Answer RRs", "Authority RRs", "Additional RRs" };
	//private static final String typeNames[] = { "Queries", "Answers", "Authorities", "Additionals" };
	private Hashtable<String, Object> values;

}
