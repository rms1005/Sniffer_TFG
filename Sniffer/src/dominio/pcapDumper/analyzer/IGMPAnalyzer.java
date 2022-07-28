package dominio.pcapDumper.analyzer;

import java.util.Hashtable;

import org.jnetpcap.packet.PcapPacket;

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
		
		switch(Igmp.IgmpType.valueOf(igmp.type())) {
		case Igmp.IgmpType.MEMBERSHIP_QUERY:
			values.put(valueNames[0], 3);
			values.put(valueNames[1], typeNames[0]);
			values.put(valueNamesQuery[0], Igmp.maxRespTime());
			values.put(valueNamesQuery[1], org.jnetpcap.packet.format.FormatUtils.ip(Igmp.Query.groupAdress()));
			values.put(valueNamesQuery[2], Igmp.s());
			values.put(valueNamesQuery[3], Igmp.qrv());
			values.put(valueNamesQuery[4], Igmp.qqic());
			values.put(valueNamesQuery[5], Igmp.nSources());
			if(Igmp.nSources() != 0) {
				byte[][] sources = Igmp.sources();
				for(int i = 0; i < sources.length; i++)
					values.put(valueNamesQuery[6], org.jnetpcap.packet.format.FormatUtils.ip(sources[i]));
			}
			break;
		case Igmp.IgmpType.V1_MEMBERSHIP_REPORT:
			values.put(valueNames[0], 1);
			values.put(valueNames[1], typeNames[1]);
			values.put(valueNamesv1[0], org.jnetpcap.packet.format.FormatUtils.ip(Igmp.MembershipReportv1.groupAdress()));
			break;
		case Igmp.IgmpType.V2_MEMBERSHIP_REPORT:
			values.put(valueNames[0], 2);
			values.put(valueNames[1], typeNames[2]);
			values.put(valueNamesv2[0], Igmp.MembershipReportv2.maxResponseTime());
			values.put(valueNamesv2[1], org.jnetpcap.packet.format.FormatUtils.ip(Igmp.MembershipReportv2.groupAdress()));
			break;
		case Igmp.IgmpType.V2_LEAVE_GROUP:
			values.put(valueNames[0], 2);
			values.put(valueNames[1], typeNames[3]);
			values.put(valueNamesv2[0], Igmp.MembershipReportv2.maxResponseTime());
			values.put(valueNamesv2[1], org.jnetpcap.packet.format.FormatUtils.ip(Igmp.MembershipReportv2.groupAdress()));
			break;
		case Igmp.IgmpType.V3_MEMBERSHIP_REPORT:
			values.put(valueNames[0], 3);
			values.put(valueNames[1], typeNames[4]);
			values.put(valueNamesv3[0], Igmp.MembershipReportv3.nGroupRecords());
			Hashtable<String, Object> valuesRecords;
			int offset = 0;
			int valueNSources = 0;
			int valueDataLen = 0;
			for(int i = 0; i < Igmp.MembershipReportv3.nGroupRecords(); i++) {
				valuesRecords = new Hashtable<String, Object>();
				valuesRecords.put(valueNamesv3Records[0], Igmp.MembershipReportv3.GroupRecord.GroupRecordType(Igmp.MembershipReportv3.GroupRecord.recordType(offset)));
				offset += 1;
				valuesRecords.put(valueNamesv3Records[1], Igmp.MembershipReportv3.GroupRecord.auxDataLen(offset));
				valueDataLen = Igmp.MembershipReportv3.GroupRecord.auxDataLen(offset);
				offset += 1;
				valuesRecords.put(valueNamesv3Records[2], Igmp.MembershipReportv3.GroupRecord.nSources(offset));
				valueNSources = Igmp.MembershipReportv3.GroupRecord.nSources(offset);
				offset += 2;
				valuesRecords.put(valueNamesv3Records[3], org.jnetpcap.packet.format.FormatUtils.ip(Igmp.MembershipReportv3.GroupRecord.groupAdress(offset)));
				offset += 4;
				if(Igmp.nSources() != 0) {
					byte[][] sources = Igmp.MembershipReportv3.GroupRecord.sourceAdresses(offset);
					for(int j = 0; j < sources.length; j++)
						values.put(valueNamesv3Records[4], org.jnetpcap.packet.format.FormatUtils.ip(sources[i]));
				}
				offset += 4 * valueNSources;
				offset += 4 * valueDataLen;
			}
			break;
		}
		
		
	}

	@Override
	public String getProtocolName() {
		return "IGMP";
	}

	@Override
	public String[] getValueNames() {
		String[] valueNamesAux;
	
		switch(Integer.valueOf(valueNames[0])) {
		case 1:
			valueNamesAux = new String[valueNames.length + valueNamesv1.length];
			for (int i = 0; i < valueNamesAux.length; i++) {
				if(i < valueNames.length)
					valueNamesAux[i] = valueNames[i];
				else
					valueNamesAux[i] = valueNamesv1[i-2];
			}
			break;
		case 2:
			valueNamesAux = new String[valueNames.length + valueNamesv2.length];
			for (int i = 0; i < valueNamesAux.length; i++) {
				if(i < valueNames.length)
					valueNamesAux[i] = valueNames[i];
				else
					valueNamesAux[i] = valueNamesv2[i-2];
			}
			break;
		case 3:
			if(valueNames[1].equals(typeNames[0])) {
				valueNamesAux = new String[valueNames.length + valueNamesQuery.length];
				for (int i = 0; i < valueNamesAux.length; i++) {
					if(i < valueNames.length)
						valueNamesAux[i] = valueNames[i];
					else
						valueNamesAux[i] = valueNamesQuery[i-2];
				}
			} else {
				valueNamesAux = new String[valueNames.length + valueNamesv3.length];
				for (int i = 0; i < valueNamesAux.length; i++) {
					if(i < valueNames.length)
						valueNamesAux[i] = valueNames[i];
					else
						valueNamesAux[i] = valueNamesv3[i-2];
				}
			}
			break;
		default:
			break;
		}
		return valueNamesAux;
	}

	@Override
	public Object getValue(String valueName) {
		return values.get(valueName);
	}

	@Override
	Object getValueAt(int index) {
		switch(Integer.valueOf(valueNames[0])) {
		case 1:
			if (index < 0 || index >= valueNames.length + valueNamesv1.length)
				return null;
			else if (index >= valueNames.length)
				return values.get(valueNamesv1[index]);
			else
				return values.get(valueNames[index]);
		case 2:
			if (index < 0 || index >= valueNames.length + valueNamesv2.length)
				return null;
			else if (index >= valueNames.length)
				return values.get(valueNamesv2[index]);
			else
				return values.get(valueNames[index]);
		case 3:
			if(valueNames[1].equals(typeNames[0])) {
				if (index < 0 || index >= valueNames.length + valueNamesQuery.length)
					return null;
				else if (index >= valueNames.length)
					return values.get(valueNamesQuery[index]);
				else
					return values.get(valueNames[index]);
			} else {
				if (index < 0 || index >= valueNames.length + valueNamesv3.length)
					return null;
				else if (index >= valueNames.length)
					return values.get(valueNamesv3[index]);
				else
					return values.get(valueNames[index]);
			}
		default:
			return null;
		}
	}

	@Override
	public Object[] getValues() {
		Object v[];
		switch(Integer.valueOf(valueNames[0])) {
		case 1:
			v = new Object[valueNames.length + valueNamesv1.length];
			for (int i = 0; i < v.length; i++) {
				if(i < valueNames.length)
					v[i] = values.get(valueNames[i]);
				else
					v[i] = values.get(valueNamesv1[i-2]);
			}
			break;
		case 2:
			v = new Object[valueNames.length + valueNamesv2.length];
			for (int i = 0; i < v.length; i++) {
				if(i < valueNames.length)
					v[i] = values.get(valueNames[i]);
				else
					v[i] = values.get(valueNamesv2[i-2]);
			}
			break;
		case 3:
			if(valueNames[1].equals(typeNames[0])) {
				v = new Object[valueNames.length + valueNamesQuery.length];
				for (int i = 0; i < v.length; i++) {
					if(i < valueNames.length)
						v[i] = values.get(valueNames[i]);
					else
						v[i] = values.get(valueNamesQuery[i-2]);
				}
			} else {
				v = new Object[valueNames.length + valueNamesv3.length];
				for (int i = 0; i < v.length; i++) {
					if(i < valueNames.length)
						v[i] = values.get(valueNames[i]);
					else
						v[i] = values.get(valueNamesv3[i-2]);
				}
			}
			break;
		default:
			v = new Object[1];
			v[0] = "ERROR en la version";
			break;
		}

		return v;
	}
	
	private static final String valueNames[] = { "IGMP Version", "Type" };
	private static final String typeNames[] = { "Membership Query (0x11)", "Membership Report v1 (0x12)", "Membership Report v2 (0x16)", "Leave Group v2 (0x17)", "Membership Report v3 (0x22)" };
	private static final String valueNamesQuery[] = { "Max Response Time", "Multicast Adress", "S", "QRV", "QQIC", "Num Src", "Source Adress" };
	private static final String valueNamesv1[] = { "Multicast Adress" };
	private static final String valueNamesv2[] = { "Max Response Time", "Multicast Adress" };
	private static final String valueNamesv3[] = { "Num Group Records" };
	private static final String valueNamesv3Records[] = { "Record Type", "Aux Data Len", "Num Src", "Multicast Adress", "Source Adress" };
	private Hashtable<String, Object> values;

}
