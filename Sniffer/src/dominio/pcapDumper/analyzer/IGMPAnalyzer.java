package dominio.pcapDumper.analyzer;

import java.util.Hashtable;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.network.Icmp;

public class IGMPAnalyzer extends JDPacketAnalyzer {

	Igmp igmpPacket = new Igmp();
	
	private Igmp igmp;
	
	public IGMPAnalyzer() {
		values = new Hashtable<String, Object>();
		layer = NETWORK_LAYER;
	}
	
	@Override
	public boolean isAnalyzable(PcapPacket p) {
		return p.hasHeader(igmpPacket);
	}

	@Override
	public void analyze(PcapPacket p) {
		if (!isAnalyzable(p))
			return;
		values.clear();
		
		igmp = igmpPacket;
		
	}

	@Override
	public String getProtocolName() {
		return "IGMP";
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
