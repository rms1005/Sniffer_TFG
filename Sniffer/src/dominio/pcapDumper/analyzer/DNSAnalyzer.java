package dominio.pcapDumper.analyzer;

import java.util.Hashtable;

import org.jnetpcap.packet.PcapPacket;

public class DNSAnalyzer extends JDPacketAnalyzer {

	Dns dnsPacket = new Dns();
	
	private Dns dns;
	
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
		
		values.put(valueNames[0], DNS.identification());
		values.put(valueNames[1], DNS.flags_QR());
		values.put(valueNames[2], DNS.flags_Opcode());
		values.put(valueNames[3], DNS.flags_TC());
		values.put(valueNames[4], DNS.flags_RD());
		values.put(valueNames[5], DNS.flags_Z());
		values.put(valueNames[6], DNS.flags_RCODE());
		values.put(valueNames[7], DNS.qdCount());
		values.put(valueNames[8], DNS.anCount());
		values.put(valueNames[9], DNS.nsCount());
		values.put(valueNames[10], DNS.arCount());
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
	private static final String typeNames[] = { "Queries", "Answers", "Authorities", "Additionals" };
	private Hashtable<String, Object> values;

}
