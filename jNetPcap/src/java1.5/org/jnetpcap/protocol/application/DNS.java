package org.jnetpcap.protocol.application;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.jnetpcap.packet.JHeader;
import org.jnetpcap.packet.annotate.Dynamic;
import org.jnetpcap.packet.annotate.Field;
import org.jnetpcap.packet.annotate.Header;
import org.jnetpcap.packet.annotate.ProtocolSuite;
import org.jnetpcap.protocol.JProtocol;
import org.jnetpcap.protocol.network.Icmp.IcmpType;

@Header(nicname = "Dns", suite = ProtocolSuite.APPLICATION)
public class DNS extends JHeader {
	
	/** Constant numerical ID assigned to this protocol. */
	public final static int ID = JProtocol.DNS_ID;
	
	@Field(offset = 0, length = 2 * 8)
	public int identification() {
		return super.getUShort(0);
	}
	
	public final static int FLAG_QR = 0x8000;
	public final static int FLAG_OPCODE = 0x7800;
	public final static int FLAG_AA = 0x400;
	public final static int FLAG_TC = 0x200;
	public final static int FLAG_RD = 0x100;
	public final static int FLAG_RA = 0x80;
	public final static int FLAG_Z = 0x70;
	public final static int FLAG_RCODE = 0xF;
	
	@Field(offset = 2 * 8, length = 2 * 8, format = "%x")
	public int flags() {
		return getUShort(2);
	}
	
	@Field(parent = "flags", offset = 0, length = 1, display = "QR")
	public int flags_QR() {
		return (flags() & FLAG_QR) >> 15;
	}
	
	@Dynamic(Field.Property.DESCRIPTION)
	public String flags_QRDescription() {
		return (flags_QR() > 0) ? "1 (response)" : "0 (query)";
	}
	
	@Field(parent = "flags", offset = 1, length = 4, display = "Opcode")
	public int flags_Opcode() {
		return (flags() & FLAG_OPCODE) >> 11;
	}
	
	@Dynamic(Field.Property.DESCRIPTION)
	public String flags_OpcodeDescription() {
		String aux = "";
		switch(flags_Opcode()) {
		case 0:
			aux = "0 (standard query (QUERY))";
			break;
		case 1:
			aux = "1 (inverse query (IQUERY))";
			break;
		case 2:
			aux = "2 (server status request (STATUS))";
			break;
		default:
			aux = "3-15 (reserved for future use)";
			break;
		}
		return aux;
	}
	
	@Field(parent = "flags", offset = 5, length = 1, display = "AA")
	public int flags_AA() {
		return (flags() & FLAG_AA) >> 10;
	}
	
	@Dynamic(Field.Property.DESCRIPTION)
	public String flags_AADescription() {
		return (flags_AA() > 0) ? "1" : "0";
	}
	
	@Field(parent = "flags", offset = 6, length = 1, display = "TC")
	public int flags_TC() {
		return (flags() & FLAG_TC) >> 9;
	}
	
	@Dynamic(Field.Property.DESCRIPTION)
	public String flags_TCDescription() {
		return (flags_TC() > 0) ? "1" : "0";
	}
	
	@Field(parent = "flags", offset = 7, length = 1, display = "RD")
	public int flags_RD() {
		return (flags() & FLAG_RD) >> 8;
	}
	
	@Dynamic(Field.Property.DESCRIPTION)
	public String flags_RDDescription() {
		return (flags_RD() > 0) ? "1" : "0";
	}
	
	@Field(parent = "flags", offset = 8, length = 1, display = "RA")
	public int flags_RA() {
		return (flags() & FLAG_RA) >> 7;
	}
	
	@Dynamic(Field.Property.DESCRIPTION)
	public String flags_RADescription() {
		return (flags_RA() > 0) ? "1" : "0";
	}
	
	@Field(parent = "flags", offset = 9, length = 3, display = "Z")
	public int flags_Z() {
		return (flags() & FLAG_Z) >> 4;
	}
	
	@Dynamic(Field.Property.DESCRIPTION)
	public String flags_ZDescription() {
		return (flags_Z() > 0) ? "1 (error)" : "0";
	}
	
	@Field(parent = "flags", offset = 12, length = 4, display = "RCODE")
	public int flags_RCODE() {
		return flags() & FLAG_RCODE;
	}
	
	@Dynamic(Field.Property.DESCRIPTION)
	public String flags_RCODEDescription() {
		String aux = "";
		switch(flags_Opcode()) {
		case 0:
			aux = "0 (No error condition)";
			break;
		case 1:
			aux = "1 (Format error)";
			break;
		case 2:
			aux = "2 (Server failure)";
			break;
		case 3:
			aux = "3 (Name Error)";
			break;
		case 4:
			aux = "4 (Not Implemented)";
			break;
		case 5:
			aux = "5 (Refused)";
			break;
		default:
			aux = "6-15 (Reserved for future use)";
			break;
		}
		return aux;
	}
	
	@Field(offset = 4 * 8, length = 2 * 8)
	public int qdCount() {
		return super.getUShort(4);
	}
	
	@Field(offset = 6 * 8, length = 2 * 8)
	public int anCount() {
		return super.getUShort(6);
	}
	
	@Field(offset = 8 * 8, length = 2 * 8)
	public int nsCount() {
		return super.getUShort(8);
	}
	
	@Field(offset = 10 * 8, length = 2 * 8)
	public int arCount() {
		return super.getUShort(10);
	}
	
	public class QuestionSection {
		
		@Field(offset = 0)
		@Dynamic(Field.Property.LENGTH)
		public String qname(int offset) {
			int aux = -1;
			int len = 0;
			
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			
			while(aux != 0) {
				aux = DNS.super.getUByte(offset+len);
				len++;
			}
			
			byte[] array = DNS.super.getByteArray(offset, len);
			
			String cadena = new String(array, StandardCharsets.UTF_8);
			
			return cadena.substring(0, cadena.length() - 2);
		}
		
		@Field(length = 2 * 8)
		@Dynamic(Field.Property.OFFSET)
		public int qtype(int offset) {
			return DNS.super.getUShort(offset);
		}
		
		@Field(length = 2 * 8)
		@Dynamic(Field.Property.OFFSET)
		public int qclass(int offset) {
			return DNS.super.getUShort(offset);
		}
	
	}
	
	public class ResourceRecord {
	
		@Field(offset = 0)
		@Dynamic(Field.Property.LENGTH)
		public String name(int offset) {
			int aux = -1;
			int len = 0;
			
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			
			while(aux != 0) {
				aux = DNS.super.getUByte(offset+len);
				len++;
			}
			
			byte[] array = DNS.super.getByteArray(offset, len);
			
			String cadena = new String(array, StandardCharsets.UTF_8);
			
			return cadena.substring(0, cadena.length() - 2);
		}
		
		@Field(length = 2 * 8)
		@Dynamic(Field.Property.OFFSET)
		public int type(int offset) {
			return DNS.super.getUShort(offset);
		}
		
		public enum DnsType {
			
			A(1, "Adress record"),
			NS(2, "Name server record"),
			CNAME(5, "Canonical name record"),
			SOA(6, "Start of [a zone of] authority record"),
			PTR(12, "PTR Resource Record [de]"),
			HINFO(13, "Host Information"),
			MX(15, "Mail exchange record"),
			TXT(16, "Text record"),
			AAAA(28, "IPv6 address record"),
			DNAME(39, "Delegation name record"),
			DHCID(49, "DHCP identifier"),
			HTTPS(65, "HTTPS Binding")
			;
			
			public final static int A_ID = 1;
			public final static int NS_ID = 2;
			public final static int CNAME_ID = 5;
			public final static int SOA_ID = 6;
			public final static int PTR_ID = 12;
			public final static int HINFO_ID = 13;
			public final static int MX_ID = 15;
			public final static int TXT_ID = 16;
			public final static int AAAA_ID = 28;
			public final static int DNAME_ID = 39;
			public final static int DHCID_ID = 49;
			public final static int HTTPS_ID = 65;
			
			/** The description. */
			private final String description;
	
			/** The id. */
			public final int id;
			
			/**
			 * Instantiates a new icmp type.
			 * 
			 * @param id
			 *          the id
			 */
			private DnsType(int id) {
				this.id = id;
				this.description = name().toLowerCase().replace('_', ' ');
			}
	
			/**
			 * Instantiates a new icmp type.
			 * 
			 * @param id
			 *          the id
			 * @param description
			 *          the description
			 */
			private DnsType(int id, String description) {
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
			
			/**
			 * To string.
			 * 
			 * @param id
			 *          the id
			 * @return the string
			 */
			public static String toString(int id) {
				for (DnsType t : values()) {
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
			 * @return the dns type
			 */
			public static DnsType valueOf(int type) {
				for (DnsType t : values()) {
					if (t.id == type) {
						return t;
					}
				}
				return null;
			}
		}
		
		@Field(length = 2 * 8)
		@Dynamic(Field.Property.OFFSET)
		public int rClass(int offset) {
			return DNS.super.getUShort(offset);
		}
		
		public enum DnsClass {
			
			IN(1, "the Internet"),
			CS(2, "the CSNET class (Obsolete - used only for examples in some obsolete RFCs)"),
			CH(3, "the CHAOS class"),
			HS(4, "Hesiod [Dyer 87]")
			;
			
			public final static int IN_ID = 1;
			public final static int CS_ID = 2;
			public final static int CH_ID = 3;
			public final static int HS_ID = 4;
			
			/** The description. */
			private final String description;
	
			/** The id. */
			public final int id;
			
			/**
			 * Instantiates a new icmp type.
			 * 
			 * @param id
			 *          the id
			 */
			private DnsClass(int id) {
				this.id = id;
				this.description = name().toLowerCase().replace('_', ' ');
			}
	
			/**
			 * Instantiates a new icmp type.
			 * 
			 * @param id
			 *          the id
			 * @param description
			 *          the description
			 */
			private DnsClass(int id, String description) {
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
			
			/**
			 * To string.
			 * 
			 * @param id
			 *          the id
			 * @return the string
			 */
			public static String toString(int id) {
				for (DnsClass t : values()) {
					if (t.id == id) {
						return t.description;
					}
				}
				return null;
			}
	
			/**
			 * Value of.
			 * 
			 * @param class
			 *          the class
			 * @return the dns class
			 */
			public static DnsClass valueOf(int rClass) {
				for (DnsClass t : values()) {
					if (t.id == rClass) {
						return t;
					}
				}
				return null;
			}
		}
		
		@Field(length = 4 * 8)
		@Dynamic(Field.Property.OFFSET)
		public int ttl(int offset) {
			return DNS.super.getUShort(offset);
		}
		
		@Field(length = 2 * 8)
		@Dynamic(Field.Property.OFFSET)
		public int rdLength(int offset) {
			return DNS.super.getUShort(offset);
		}
		
		@Dynamic(Field.Property.OFFSET)
		//@Dynamic(Field.Property.LENGTH)
		public byte[] rData(int offset) {
			return DNS.super.getByteArray(offset, rdLength(offset-2));
		}
	
	}
	
}
