package org.jnetpcap.protocol.network;

import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.JHeaderChecksum;
import org.jnetpcap.packet.JHeaderMap;
import org.jnetpcap.packet.JSubHeader;
import org.jnetpcap.packet.annotate.Dynamic;
import org.jnetpcap.packet.annotate.Field;
import org.jnetpcap.packet.annotate.Header;
import org.jnetpcap.packet.annotate.HeaderLength;
import org.jnetpcap.protocol.JProtocol;
import org.jnetpcap.protocol.network.Icmp.Echo;
import org.jnetpcap.protocol.network.Icmp.IcmpType;
import org.jnetpcap.protocol.network.Icmp.Reserved;
import org.jnetpcap.util.checksum.Checksum;

@Header
public class Icmpv6 extends JHeaderMap<Icmp> implements JHeaderChecksum {

	/** The Constant ID. */
	public static final int ID = JProtocol.ICMPv6_ID;
	
	/**
	 * Type.
	 * 
	 * @return the int
	 */
	@Field(offset = 0, length = 8)
	public int type() {
		return super.getUByte(0);
	}

	/**
	 * Type description.
	 * 
	 * @return the string
	 */
	@Dynamic(Field.Property.DESCRIPTION)
	public String typeDescription() {
		return Icmpv6Type.valueOf(type()).getDescription();
	}

	/**
	 * Type enum.
	 * 
	 * @return the icmp type
	 */
	public Icmpv6Type typeEnum() {
		return Icmpv6Type.valueOf(type());
	}
	
	/**
	 * A table of Icmpv6Types and their names.
	 * 
	 * @author Raul Merinero
	 */
	public enum Icmpv6Type {
		
		DESTINATION_UNREACHABLE(3, "Destination Unreachable"),
		PACKET_TOO_BIG(2, "Packet Too Big"),
		TIME_EXCEEDED(3, "Time Exceeded"),
		PARAMETER_PROBLEM(4, "Parameter Problem"),
		PRIVATE_EXPERIMENTATION_1(100, "Private experimentation"),
		PRIVATE_EXPERIMENTATION_2(101, "Private experimentation"),
		EXPANSION_ERROR_MESSAGES(127, "Reserved for expansion of ICMPv6 error messages"),
		ECHO_REQUEST(128, "Echo Request"),
		ECHO_REPLY(129, "Echo Reply"),
		PRIVATE_EXPERIMENTATION_3(200, "Private experimentation"),
		PRIVATE_EXPERIMENTATION_4(201, "Private experimentation"),
		EXPANSION_INFORMATIONAL_MESSAGES(255, "Reserved for expansion of ICMPv6 informational messages")
		;
		
		public final static int DESTINATION_UNREACHABLE_ID = 1;
		public final static int PACKET_TOO_BIG_ID = 2;
		public final static int TIME_EXCEEDED_ID = 3;
		public final static int PARAMETER_PROBLEM_ID = 4;
		public final static int PRIVATE_EXPERIMENTATION_1_ID = 100;
		public final static int PRIVATE_EXPERIMENTATION_2_ID = 101;
		public final static int EXPANSION_ERROR_MESSAGES_ID = 127;
		public final static int ECHO_REQUEST_ID = 128;
		public final static int ECHO_REPLY_ID = 129;
		public final static int PRIVATE_EXPERIMENTATION_3_ID = 200;
		public final static int PRIVATE_EXPERIMENTATION_4_ID = 201;
		public final static int EXPANSION_INFORMATIONAL_MESSAGES_ID = 255;
		
		/**
		 * To string.
		 * 
		 * @param id
		 *          the id
		 * @return the string
		 */
		public static String toString(int id) {
			for (Icmpv6Type t : values()) {
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
		 * @return the icmpv6 type
		 */
		public static Icmpv6Type valueOf(int type) {
			for (Icmpv6Type t : values()) {
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
		 * Instantiates a new icmpv6 type.
		 * 
		 * @param id
		 *          the id
		 */
		private Icmpv6Type(int id) {
			this.id = id;
			this.description = name().toLowerCase().replace('_', ' ');
		}

		/**
		 * Instantiates a new icmpv6 type.
		 * 
		 * @param id
		 *          the id
		 * @param description
		 *          the description
		 */
		private Icmpv6Type(int id, String description) {
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
	
	/**
	 * Code.
	 * 
	 * @return the int
	 */
	@Field(offset = 1 * 8, length = 8)
	public int code() {
		return super.getUByte(1);
	}

	/**
	 * Code enum.
	 * 
	 * @return the icmp code
	 */
	public Icmpv6Code codeEnum() {
		return Icmpv6Code.valueOf(type(), code());
	}
	
	/**
	 * A table of Icmpv6 sub-codes per Icmpv6 type.
	 * 
	 * @author Raul Merinero
	 */
	public enum Icmpv6Code {
		
		NO_ROUTE_TO_DESTINATION(Icmpv6Type.DESTINATION_UNREACHABLE, 0),
		COMMUNICATION_WITH_DESTINATION_ADMINISTRATIVELY_PROHIBITED(
				Icmpv6Type.DESTINATION_UNREACHABLE, 1),
		BEYOND_SCOPE_OF_SOURCE_ADRESS(Icmpv6Type.DESTINATION_UNREACHABLE, 2),
		ADDRESS_UNREACHABLE(Icmpv6Type.DESTINATION_UNREACHABLE, 3),
		PORT_UNREACHABLE(Icmpv6Type.DESTINATION_UNREACHABLE, 4),
		SOURCE_ADRESS_FAILED(Icmpv6Type.DESTINATION_UNREACHABLE, 5),
		REJECT_ROUTE_TO_DESTINATION(Icmpv6Type.DESTINATION_UNREACHABLE, 6),
		ERROR_IN_SOURCE_ROUTING_HEADER(Icmpv6Type.DESTINATION_UNREACHABLE, 7),
		HOP_LIMIT_EXCEEDED_IN_TRANSIT(Icmpv6Type.TIME_EXCEEDED, 0),
		FRAGMENT_REASSEMBLY_TIME_EXCEEDED(Icmpv6Type.TIME_EXCEEDED, 1),
		ERRONEOUS_HEADER_FIELD_ENCOUNTERED(Icmpv6Type.PARAMETER_PROBLEM, 0),
		UNRECOGNIZED_NEXT_HEADER_TYPE_ENCOUNTERED(Icmpv6Type.PARAMETER_PROBLEM, 1),
		UNRECOGNIZED_IPV6_OPTION_ENCOUNTERED(Icmpv6Type.PARAMETER_PROBLEM, 2)
		;
		
		/**
		 * To string.
		 * 
		 * @param type
		 *          the type
		 * @param code
		 *          the code
		 * @return the string
		 */
		public static String toString(int type, int code) {
			for (Icmpv6Code t : values()) {
				if (t.type.id == type && t.code == code) {
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
		 * @param code
		 *          the code
		 * @return the icmp code
		 */
		public static Icmpv6Code valueOf(int type, int code) {
			for (Icmpv6Code t : values()) {
				if (t.type.id == type && t.code == code) {
					return t;
				}
			}

			return null;
		}

		/** The code. */
		private final int code;

		/** The description. */
		private final String description;

		/** The type. */
		private final Icmpv6Type type;

		/**
		 * Instantiates a new icmp code.
		 * 
		 * @param type
		 *          the type
		 * @param code
		 *          the code
		 */
		private Icmpv6Code(Icmpv6Type type, int code) {
			this.type = type;
			this.code = code;
			this.description = name().toString().toLowerCase().replace('_', ' ');
		}

		/**
		 * Instantiates a new icmp code.
		 * 
		 * @param type
		 *          the type
		 * @param code
		 *          the code
		 * @param description
		 *          the description
		 */
		private Icmpv6Code(Icmpv6Type type, int code, String description) {
			this.type = type;
			this.code = code;
			this.description = description;
		}

		/**
		 * Gets the code.
		 * 
		 * @return the code
		 */
		public final int getCode() {
			return this.code;
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
		 * Gets the type.
		 * 
		 * @return the type
		 */
		public final Icmpv6Type getType() {
			return this.type;
		}
	}

	/**
	 * Retrieves the header's checksum.
	 * 
	 * @return header's stored checksum
	 */
	@Override
	@Field(offset = 2 * 8, length = 16)
	public int checksum() {
		return super.getUShort(2);
	}

	/**
	 * Calculates a checksum using protocol specification for a header. Checksums
	 * for partial headers or fragmented packets (unless the protocol alows it)
	 * are not calculated.
	 * 
	 * @return header's calculated checksum
	 */
	@Override
	public int calculateChecksum() {

		if (getIndex() == -1) {
			throw new IllegalStateException("Oops index not set");
		}

		final int ipOffset = getPreviousHeaderOffset();

		return Checksum.inChecksumShouldBe(checksum(), Checksum.icmp(packet,
		    ipOffset, this.getOffset()));
	}

	/**
	 * Checks if the checksum is valid, for un-fragmented packets. If a packet is
	 * fragmented, the checksum is not verified as data to is incomplete, but the
	 * method returns true none the less.
	 * 
	 * @return true if checksum checks out or if this is a fragment, otherwise if
	 *         the computed checksum does not match the stored checksum false is
	 *         returned
	 */
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
	
	/**
	 * Checksum description.
	 * 
	 * @return the string
	 */
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jnetpcap.packet.JHeaderMap#decodeUniqueSubHeaders()
	 */
	/**
	 * Decode header.
	 * 
	 * @see org.jnetpcap.packet.JHeader#decodeHeader()
	 */
	@Override
	protected void decodeHeader() {
		final int id = type();
		optionsOffsets[id] = 4;
		optionsBitmap = (1 << id);
		optionsLength[id] = getLength() - 4;
	}
	
	/**
	 * Header length.
	 * 
	 * @param buffer
	 *          the buffer
	 * @param offset
	 *          the offset
	 * @return the int
	 */
	@HeaderLength
	public static int headerLength(JBuffer buffer, int offset) {
		switch (buffer.getUByte(offset)) {
			case 0: // EchoReply
			case 8: // EchoRequest
				return buffer.size() - offset - 4;

			case 4: // SourceQuench
			case 5: // Redirect
			case 11: // Timestamp
			default:
				return 4;
		}
	}
	
	/**
	 * ICMP Destination Unreachable header definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	@Header(length = 4, id = IcmpType.DESTINATION_UNREACHABLE_ID, nicname = "unreach")
	public static class DestinationUnreachable
	    extends
	    Reserved {
	}

	/**
	 * ICMP Echo header (ping) baseclass definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	public static abstract class Echo
	    extends
	    JSubHeader<Icmp> {

		/**
		 * Id.
		 * 
		 * @return the int
		 */
		@Field(offset = 0, length = 16, format = "%x")
		public int id() {
			return super.getUShort(0);
		}

		/**
		 * Sequence.
		 * 
		 * @return the int
		 */
		@Field(offset = 16, length = 16, format = "%x")
		public int sequence() {
			return super.getUShort(2);
		}
	};

	/**
	 * ICMP Echo Reply header definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	@Header(id = IcmpType.ECHO_REPLY_ID, length = 4, nicname = "reply")
	public static class EchoReply
	    extends
	    Echo {

	}

	/**
	 * ICMP Echo Request header definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	@Header(id = IcmpType.ECHO_REQUEST_ID, length = 4, nicname = "request")
	public static class EchoRequest
	    extends
	    Echo {

	}
	
	/**
	 * ICMP Paramater Protoblem header definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	@Header(length = 4, id = IcmpType.PARAM_PROBLEM_ID)
	public static class ParamProblem
	    extends
	    JSubHeader<Icmp> {

		/**
		 * Pointer.
		 * 
		 * @return the int
		 */
		@Field(offset = 0, length = 8)
		public int pointer() {
			return getUByte(0);
		}

		/**
		 * Reserved.
		 * 
		 * @return the int
		 */
		@Field(offset = 8, length = 24)
		public int reserved() {
			return (int) (getUInt(0) & 0x00FFFFFFL);
		}
	}

	/**
	 * ICMP Redirect header definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	@Header(length = 4, id = IcmpType.REDIRECT_ID)
	public static class Redirect
	    extends
	    JSubHeader<Icmp> {

		/**
		 * Gateway.
		 * 
		 * @return the byte[]
		 */
		public byte[] gateway() {
			return getByteArray(0, 4);
		}
	}

	/**
	 * Base class for various ICMP Headers that contain a reserved field.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	public static abstract class Reserved
	    extends
	    JSubHeader<Icmp> {

		/**
		 * Reserved.
		 * 
		 * @return the long
		 */
		public long reserved() {
			return getUInt(0);
		}
	}

	/**
	 * ICMP Source Quence header definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	@Header(length = 4, id = IcmpType.SOURCE_QUENCH_ID)
	public static class SourceQuench
	    extends
	    Reserved {

	}
	
}
