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
	
	private static final String valueNames[] = {  };
	private static final String typeNames[] = {  };
	private Hashtable<String, Object> values;

}
