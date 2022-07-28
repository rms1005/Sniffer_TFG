package org.jnetpcap.protocol.network;

import org.jnetpcap.packet.JHeader;
import org.jnetpcap.packet.JHeaderChecksum;
import org.jnetpcap.packet.JHeaderMap;
import org.jnetpcap.packet.annotate.Dynamic;
import org.jnetpcap.packet.annotate.Field;
import org.jnetpcap.packet.annotate.Header;
import org.jnetpcap.protocol.JProtocol;
import org.jnetpcap.util.checksum.Checksum;

@Header
public class Igmp extends JHeader {
	
	/** The Constant ID. */
	public static final int ID = JProtocol.IGMP_ID;
	
	@Field(offset = 0 * 8, length = 8, format = "%x")
	public int type() {
		return super.getUByte(0);
	}
	
	public enum IgmpType {
		
		MEMBERSHIP_QUERY(0x11, "Membership Query"),
		V1_MEMBERSHIP_REPORT(0x12, "V1 Membership Report"),
		V2_MEMBERSHIP_REPORT(0x16, "V2 Membership Report"),
		V2_LEAVE_GROUP(0x17, "V2 Leave Group"),
		V3_MEMBERSHIP_REPORT(0x22, "V3 Membership Report")
		;
		
		/**
		 * To string.
		 * 
		 * @param id
		 *          the id
		 * @return the string
		 */
		public static String toString(int id) {
			for (IgmpType t : values()) {
				if (t.id == id) {
					return t.description;
				}
			}
			return null;
		}
		
		/**
		 * Value of.
		 * 
		 * @param type
		 *          the type
		 * @return the igmp type
		 */
		public static IgmpType valueOf(int type) {
			for (IgmpType t : values()) {
				if (t.id == type) {
					return t;
				}
			}
			return null;
		}

		/** The description. */
		private final String description;

		/** The id. */
		public final int id;

		/**
		 * Instantiates a new igmp type.
		 * 
		 * @param id
		 *          the id
		 */
		private IgmpType(int id) {
			this.id = id;
			this.description = name().toLowerCase().replace('_', ' ');
		}

		/**
		 * Instantiates a new igmp type.
		 * 
		 * @param id
		 *          the id
		 * @param description
		 *          the description
		 */
		private IgmpType(int id, String description) {
			this.id = id;
			this.description = description;

		}

		/**
		 * Gets the description.
		 * 
		 * @return the description
		 */
		public final String getDescription() {
			return this.description;
		}

		/**
		 * Gets the id.
		 * 
		 * @return the id
		 */
		public final int getId() {
			return this.id;
		}
	}
	
	public int version() {
		int aux = 0;
		
		switch(type()) {
		case 0x11:
			aux = 3;
			break;
		case 0x12:
			aux = 1;
			break;
		case 0x16:
		case 0x17:
			aux = 2;
			break;
		case 0x22:
			aux = 3;
			break;
		default:
			aux = -1;
			break;
		}
		
		return aux;
	}
	
	public class Query extends JHeaderMap<Igmp> implements JHeaderChecksum {
		
		@Field(offset = 0, length = 8, format = "%d")
		public int maxRespCode() {
			return Igmp.super.getUByte(1);
		}
		
		public String maxRespTime() {
			int code = maxRespCode();
			//TODO
			return "";
		}
		
		@Field(offset = 1 * 8, length = 2 * 8, format = "%x")
		public int checksum() {
			return Igmp.super.getUShort(2);
		}
		
		@Override
		public int calculateChecksum() {

			if (getIndex() == -1) {
				throw new IllegalStateException("Oops index not set");
			}

			final int ipOffset = getPreviousHeaderOffset();

			return Checksum.inChecksumShouldBe(checksum(), Checksum.icmp(packet,
			    ipOffset, this.getOffset()));
		}

		@Override
		public boolean isChecksumValid() {

			if (isFragmented()) {
				return true;
			}

			if (getIndex() == -1) {
				throw new IllegalStateException("Oops index not set");
			}

			final int ipOffset = getPreviousHeaderOffset();

			return Checksum.icmp(packet, ipOffset, this.getOffset()) == 0;
		}

		@Dynamic(Field.Property.DESCRIPTION)
		public String checksumDescription() {

			if (isFragmented()) {
				return "supressed for fragments";
			}

			if (isPayloadTruncated()) {
				return "supressed for truncated packets";
			}

			final int crc16 = calculateChecksum();
			if (checksum() == crc16) {
				return "correct";
			} else {
				return "incorrect: 0x" + Integer.toHexString(crc16).toUpperCase();
			}
		}
		
		@Field(offset = 3 * 8, length = 4 * 8, format = "#ip4#")
		public byte[] groupAdress() {
			return Igmp.super.getByteArray(4, 4);
		}
		
		@Field(offset = 7 * 8, length = 4)
		public int resv() {
			return Igmp.super.getUByte(8) >> 4;
		}
		
		@Field(offset = 7 * 8 + 4, length = 1)
		public int s() {
			return (Igmp.super.getUByte(8) & 0x8) >> 3;
		}
		
		@Field(offset = 7 * 8 + 5, length = 3, format = "%x")
		public int qrv() {
			return Igmp.super.getUByte(8) & 0x7;
		}
		
		@Field(offset = 8 * 8, length = 8, format = "%x")
		public int qqic() {
			return Igmp.super.getUByte(9);
		}
		
		@Field(offset = 9 * 8, length = 2 * 8)
		public int nSources() {
			return Igmp.super.getUShort(10);
		}
		
		@Field(offset = 11 * 8)
		public byte[][] sources() {
			final int count = nSources();
			
			final byte[][] sources = new byte[count][4];
			
			int i = 0;
			while(i < sources.length) {
				byte[] aux = Igmp.super.getByteArray(12 + i*4, 4);
				sources[i][0] = aux[0];
				sources[i][1] = aux[1];
				sources[i][2] = aux[2];
				sources[i][3] = aux[3];
				i += 1;
			}
			
			return sources;
		}
	
	}
	
	public class MembershipReportv1 extends JHeaderMap<Igmp> implements JHeaderChecksum {
		
		@Field(offset = 0, length = 8)
		public int unused() {
			return Igmp.super.getUByte(1);
		}

		@Field(offset = 8, length = 2 * 8)
		public int checksum() {
			return Igmp.super.getUShort(2);
		}
		
		@Override
		public int calculateChecksum() {

			if (getIndex() == -1) {
				throw new IllegalStateException("Oops index not set");
			}

			final int ipOffset = getPreviousHeaderOffset();

			return Checksum.inChecksumShouldBe(checksum(), Checksum.icmp(packet,
			    ipOffset, this.getOffset()));
		}

		@Override
		public boolean isChecksumValid() {

			if (isFragmented()) {
				return true;
			}

			if (getIndex() == -1) {
				throw new IllegalStateException("Oops index not set");
			}

			final int ipOffset = getPreviousHeaderOffset();

			return Checksum.icmp(packet, ipOffset, this.getOffset()) == 0;
		}

		@Dynamic(Field.Property.DESCRIPTION)
		public String checksumDescription() {

			if (isFragmented()) {
				return "supressed for fragments";
			}

			if (isPayloadTruncated()) {
				return "supressed for truncated packets";
			}

			final int crc16 = calculateChecksum();
			if (checksum() == crc16) {
				return "correct";
			} else {
				return "incorrect: 0x" + Integer.toHexString(crc16).toUpperCase();
			}
		}

		@Field(offset = 8, length = 2 * 8, format = "#ip4#")
		public byte[] groupAdress() {
			return Igmp.super.getByteArray(4, 4);
		}
		
	}
	
	public class MembershipReportv2 extends JHeaderMap<Igmp> implements JHeaderChecksum {
		
		@Field(offset = 0, length = 8)
		public int maxResponseTime() {
			return Igmp.super.getUByte(1);
		}

		@Field(offset = 8, length = 2 * 8)
		public int checksum() {
			return Igmp.super.getUShort(2);
		}
		
		@Override
		public int calculateChecksum() {

			if (getIndex() == -1) {
				throw new IllegalStateException("Oops index not set");
			}

			final int ipOffset = getPreviousHeaderOffset();

			return Checksum.inChecksumShouldBe(checksum(), Checksum.icmp(packet,
			    ipOffset, this.getOffset()));
		}

		@Override
		public boolean isChecksumValid() {

			if (isFragmented()) {
				return true;
			}

			if (getIndex() == -1) {
				throw new IllegalStateException("Oops index not set");
			}

			final int ipOffset = getPreviousHeaderOffset();

			return Checksum.icmp(packet, ipOffset, this.getOffset()) == 0;
		}

		@Dynamic(Field.Property.DESCRIPTION)
		public String checksumDescription() {

			if (isFragmented()) {
				return "supressed for fragments";
			}

			if (isPayloadTruncated()) {
				return "supressed for truncated packets";
			}

			final int crc16 = calculateChecksum();
			if (checksum() == crc16) {
				return "correct";
			} else {
				return "incorrect: 0x" + Integer.toHexString(crc16).toUpperCase();
			}
		}

		@Field(offset = 8, length = 2 * 8, format = "#ip4#")
		public byte[] groupAdress() {
			return Igmp.super.getByteArray(4, 4);
		}
		
	}
	
	public class MembershipReportv3 extends JHeaderMap<Igmp> implements JHeaderChecksum {
		
		@Field(offset = 0, length = 8)
		public int reserved() {
			return Igmp.super.getUByte(1);
		}
		
		@Field(offset = 1 * 8, length = 2 * 8)
		public int checksum() {
			return Igmp.super.getUShort(2);
		}
		
		@Override
		public int calculateChecksum() {

			if (getIndex() == -1) {
				throw new IllegalStateException("Oops index not set");
			}

			final int ipOffset = getPreviousHeaderOffset();

			return Checksum.inChecksumShouldBe(checksum(), Checksum.icmp(packet,
			    ipOffset, this.getOffset()));
		}

		@Override
		public boolean isChecksumValid() {

			if (isFragmented()) {
				return true;
			}

			if (getIndex() == -1) {
				throw new IllegalStateException("Oops index not set");
			}

			final int ipOffset = getPreviousHeaderOffset();

			return Checksum.icmp(packet, ipOffset, this.getOffset()) == 0;
		}

		@Dynamic(Field.Property.DESCRIPTION)
		public String checksumDescription() {

			if (isFragmented()) {
				return "supressed for fragments";
			}

			if (isPayloadTruncated()) {
				return "supressed for truncated packets";
			}

			final int crc16 = calculateChecksum();
			if (checksum() == crc16) {
				return "correct";
			} else {
				return "incorrect: 0x" + Integer.toHexString(crc16).toUpperCase();
			}
		}
		
		@Field(offset = 3 * 8, length = 2 * 8)
		public int reserved2() {
			return Igmp.super.getUShort(4);
		}
		
		@Field(offset = 5 * 8, length = 2 * 8)
		public int nGroupRecords() {
			return Igmp.super.getUShort(6);
		}
		
		public class GroupRecord {
			
			@Field(offset = 0, length = 8)
			public int recordType(int aux) {
				return Igmp.super.getUByte(8 + aux);
			}
			
			public enum GroupRecordType {
				MODE_IS_INCLUDE(1),
				MODE_IS_EXCLUDE(2),
				CHANGE_TO_INCLUDE_MODE(3),
				CHANGE_TO_EXCLUDE_MODE(4),
				ALLOW_NEW_SOURCES(5),
				BLOCK_OLD_SOURCES(6)
				;
				
				private GroupRecordType(int id) {
					this.id = id;
					this.description = name().toLowerCase().replace('_', ' ');
				}
				
				/** The description. */
				private final String description;

				/** The id. */
				public final int id;
				
				/**
				 * Gets the description.
				 * 
				 * @return the description
				 */
				public final String getDescription() {
					if(description == null)
						return name();
					return this.description;
				}

				/**
				 * Gets the id.
				 * 
				 * @return the id
				 */
				public final int getId() {
					return this.id;
				}
				
				/**
				 * To string.
				 * 
				 * @param id
				 *          the id
				 * @return the string
				 */
				public static String toString(int id) {
					for (GroupRecordType t : values()) {
						if (t.id == id) {
							if(t.description == null)
								return t.name();
							return t.description;
						}
					}

					return null;
				}

				/**
				 * Value of.
				 * 
				 * @param type
				 *          the type
				 * @return the group record type
				 */
				public static GroupRecordType valueOf(int type) {
					for (GroupRecordType t : values()) {
						if (t.id == type) {
							return t;
						}
					}

					return null;
				}
			}
			
			@Field(offset = 8, length = 8)
			public int auxDataLen(int aux) {
				return Igmp.super.getUByte(9 + aux);
			}
			
			@Field(offset = 2 * 8, length = 2 * 8)
			public int nSources(int aux) {
				return Igmp.super.getUShort(10 + aux);
			}
			
			@Field(offset = 4 * 8, length = 4 * 8)
			public byte[] groupAdress(int aux) {
				return Igmp.super.getByteArray(12 + aux, 4);
			}
			
			@Field(offset = 8 * 8)
			public byte[][] sourceAdresses(int aux) {
				final int count = nSources(aux);
				
				final byte[][] adresses = new byte[count][4];
				
				int i = 0;
				while(i < adresses.length) {
					byte[] aux2 =
							Igmp.super.getByteArray(16 + aux + i*4, 4);
					adresses[i][0] = aux2[0];
					adresses[i][1] = aux2[1];
					adresses[i][2] = aux2[2];
					adresses[i][3] = aux2[3];
					i += 1;
				}
				
				return adresses;
			}
			
			@Dynamic(Field.Property.LENGTH)
			public long[] auxiliaryData(int aux) {
				final int count = auxDataLen(aux);
				
				final long[] data = new long[count];
				
				if (count == 0)
					return data;
				else {
					for(int i = 0; i < data.length; i++) {
						data[i] = Igmp.super.getLong(aux + i * 4);
					}
				}
				
				return data;
			}
			
		}
		
	}

}
