package dominio.pcapDumper.analyzer;

import java.util.Hashtable;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.network.Igmp;

/**
 * Clase IGMPAnalyzer.
 * 
 * @author Jose Manuel Saiz, Raul Merinero Sanz
 * @author jmsaizg@gmail.com, rms1005@alu.ubu.es
 * @version 1.3
 */

public class IGMPAnalyzer extends JDPacketAnalyzer {

	Igmp igmpPacket = new Igmp();
	//Igmp.Query query = new Igmp.Query();
	//Igmp.MembershipReportv1 memRrp1 = new Igmp.MembershipReportv1();
	//Igmp.MembershipReportv2 memRrp2 = new Igmp.MembershipReportv2();
	//Igmp.MembershipReportv3 memRrp3 = new Igmp.MembershipReportv3();
	//Igmp.MembershipReportv3.GroupRecord gRec = new Igmp.MembershipReportv3.GroupRecord();
	private Igmp igmp;
	
	public IGMPAnalyzer() {
		values = new Hashtable<String, Object>();
		layer = NETWORK_LAYER;
	}
	
	@Override
	public boolean isAnalyzable(PcapPacket p) {
		//return p.hasHeader(ipPacket) && Ip4.Ip4Type.valueOf(ipPacket.type()).toString().equals("IGMP");
		return p.hasHeader(igmpPacket);
	}

	@Override
	public void analyze(PcapPacket p) {
		if (!isAnalyzable(p))
			return;
		values.clear();
		
		igmp = igmpPacket;
		
		switch(Igmp.IgmpType.valueOf(igmp.type())) {
		case MEMBERSHIP_QUERY:
			values.put(valueNames[0], 3);
			values.put(valueNames[1], typeNames[0]);
			/*values.put(valueNamesQuery[0], query.maxRespTime());
			values.put(valueNamesQuery[1], org.jnetpcap.packet.format.FormatUtils.ip(query.groupAdress()));
			values.put(valueNamesQuery[2], query.s());
			values.put(valueNamesQuery[3], query.qrv());
			values.put(valueNamesQuery[4], query.qqic());
			values.put(valueNamesQuery[5], query.nSources());
			if(query.nSources() != 0) {
				byte[][] sources = query.sources();
				for(int i = 0; i < sources.length; i++)
					values.put(valueNamesQuery[6], org.jnetpcap.packet.format.FormatUtils.ip(sources[i]));
			}*/
			break;
		case V1_MEMBERSHIP_REPORT:
			values.put(valueNames[0], 1);
			values.put(valueNames[1], typeNames[1]);
			//values.put(valueNamesv1[0], org.jnetpcap.packet.format.FormatUtils.ip(memRrp1.groupAdress()));
			break;
		case V2_MEMBERSHIP_REPORT:
			values.put(valueNames[0], 2);
			values.put(valueNames[1], typeNames[2]);
			//values.put(valueNamesv2[0], memRrp2.maxResponseTime());
			//values.put(valueNamesv2[1], org.jnetpcap.packet.format.FormatUtils.ip(memRrp2.groupAdress()));
			break;
		case V2_LEAVE_GROUP:
			values.put(valueNames[0], 2);
			values.put(valueNames[1], typeNames[3]);
			//values.put(valueNamesv2[0], memRrp2.maxResponseTime());
			//values.put(valueNamesv2[1], org.jnetpcap.packet.format.FormatUtils.ip(memRrp2.groupAdress()));
			break;
		case V3_MEMBERSHIP_REPORT:
			values.put(valueNames[0], 3);
			values.put(valueNames[1], typeNames[4]);
			//values.put(valueNamesv3[0], memRrp3.nGroupRecords());
			/*Hashtable<String, Object> valuesRecords;
			int offset = 0;
			int valueNSources = 0;
			int valueDataLen = 0;
			for(int i = 0; i < memRrp3.nGroupRecords(); i++) {
				valuesRecords = new Hashtable<String, Object>();
				valuesRecords.put(valueNamesv3Records[0], gRec.GroupRecordType(memRrp3.GroupRecord.recordType(offset)));
				offset += 1;
				valuesRecords.put(valueNamesv3Records[1], gRec.auxDataLen(offset));
				valueDataLen = gRec.auxDataLen(offset);
				offset += 1;
				valuesRecords.put(valueNamesv3Records[2], gRec.nSources(offset));
				valueNSources = gRec.nSources(offset);
				offset += 2;
				valuesRecords.put(valueNamesv3Records[3], org.jnetpcap.packet.format.FormatUtils.ip(gRec.groupAdress(offset)));
				offset += 4;
				if(gRec.nSources() != 0) {
					byte[][] sources = gRec.sourceAdresses(offset);
					for(int j = 0; j < sources.length; j++)
						values.put(valueNamesv3Records[4], org.jnetpcap.packet.format.FormatUtils.ip(sources[i]));
				}
				offset += 4 * valueNSources;
				offset += 4 * valueDataLen;
			}*/
			break;
		}
		
		
	}

	@Override
	public String getProtocolName() {
		return "IGMP";
	}

	@Override
	public String[] getValueNames() {
		String[] valueNamesAux = null;
	
		/*switch(Integer.valueOf(valueNames[0])) {
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
		}*/
		
		valueNamesAux = new String[2];
		valueNamesAux[0] = valueNames[0];
		valueNamesAux[1] = valueNames[1];
		
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
	//private static final String valueNamesv3Records[] = { "Record Type", "Aux Data Len", "Num Src", "Multicast Adress", "Source Adress" };
	private Hashtable<String, Object> values;

}
