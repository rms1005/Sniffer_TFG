package dominio.pcapDumper;

/** 
 * Clase Save PacketHandler, Clase de captura de paquetes 
 *  
 * @author Jose Manuel Saiz, Rodrigo S�nchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3 
 */
import java.io.File;
import java.io.IOException;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapDumper;
import org.jnetpcap.PcapHeader;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

import presentacion.preferencias.PreferenciasCaptura;
import presentacion.preferencias.PreferenciasFromFile;

import dominio.export.xml_PcapLib.CrearXMLOffline;
import dominio.export.xml_PcapLib.XmlPacketHandler;

public class SavePacketHandler {

	private File fileXML;

	StringBuilder errbuf = new StringBuilder();

	static Pcap jpcap;
	Thread myThread;
	public static String ruta = "";
	Pcap jpcap_writer;
	public long time;
	public static long space;
	private SaveTime STime;
	private SaveSpace SSpace;
	public static SaveFileName SFName;
	public static String strFile;
	public static String auxStrFile;
	public static long contPacket;
	public static long numPacket;
	public static CountPacketHandler RCountPH;
	public static Captura venpadre;
	static long contSpaceLen;
	private static boolean controlPacket;
	private static boolean multiFile = false;
	private static boolean otroFile = false;
	private Thread captureThread;
	protected static PcapPacket packet;
	/* Donde dejaremos el nombre del archivo XML */
	public CrearXMLOffline crearxmloffline;
	/* En este objeto agregaremos paquete a paquete de la captura a el XML */
	public static XmlPacketHandler ficheroxmlenconstruccion;
	private static String nombreficheroxml;

	private static PcapDumper dumper;
	private static String strF;
	private static Pcap pcap;

	static long aux;

	/**
	 * Metodo Gestor de los paquetes capturados
	 * 
	 * @param Captura
	 *            ,SaveFileName (Nombre de archivo),
	 *            CountPacketHandler,JpcapCaptor
	 * @exception exceptions
	 *                Ning�n error (Excepci�n) definida
	 */

	public SavePacketHandler(Captura cap, SaveFileName SFN,
			CountPacketHandler CPH, Pcap jpcap) {
		this.jpcap = jpcap;
		this.venpadre = cap;
		String aux = "./files/Capturas";
		strF = "capturaJpacpLib.pcap";

		SFN.setSaveFileName(aux, strF);
		this.SFName = SFN;

		setTime(0);
		setSpace(0);
		setPila(0);
		setFile(this.SFName.getFile());

		setNumPacket(0);
		setContPacket(0);
		this.RCountPH = CPH;
		contSpaceLen = 0;
		this.controlPacket = false;

		if (!isMultiFile()) {
			DefinirXML(aux + SFN.getFile().toString() + ".XML");
			setDumper(jpcap.dumpOpen(SFN.getFullPath()));
		}
	}

	/**
	 * Metodo Gestor de los paquetes capturados
	 * 
	 * @param Captura
	 *            ,SaveFileName (Nombre de archivo),
	 *            CountPacketHandler,JpcapCaptor, fullPath
	 * @exception exceptions
	 *                Ning�n error (Excepci�n) definida
	 */
	public SavePacketHandler(Captura cap, SaveFileName SFN,
			CountPacketHandler CPH, Pcap jpcap, String fullPath) {
		this.jpcap = jpcap;
		this.venpadre = cap;

		SFN.setSaveFileName(fullPath);
		this.SFName = SFN;

		setTime(0);
		setSpace(0);
		setPila(0);
		setFile(this.SFName.getFile());

		setNumPacket(0);
		setContPacket(0);
		this.RCountPH = CPH;
		contSpaceLen = 0L;
		this.controlPacket = false;
		if (!isMultiFile()) {
			DefinirXML(SFN.getPath() + "\\" + SFN.getNameFile() + ".XML");
			setDumper(jpcap.dumpOpen(SFN.getFullPath()));
		}
	}

	/**
	 * Metodo Gestor de los paquetes capturados
	 * 
	 * @param SaveFileName
	 *            (Nombre de archivo), fullPath
	 * @exception exceptions
	 *                Ning�n error (Excepci�n) definida
	 */
	public SavePacketHandler(SaveFileName SFN, String fullPath) {
		this.SFName = SFN;
		this.SFName.setSaveFileName(fullPath);
		setTime(0);
		setSpace(0);
		setPila(0);
		setFile(this.SFName.getFile());
		setContPacket(0);
		this.RCountPH = new CountPacketHandler();
		contSpaceLen = 0;
		this.controlPacket = false;
		if (!isMultiFile()) {
			DefinirXML(SFN.getPath() + "\\" + SFN.getNameFile() + ".XML");
			setDumper(jpcap.dumpOpen(SFName.getFullPath()));
		}
	}

	/**
	 * Metodo donde se Define los datos del Fichero XML a Crear se invocan los
	 * objetos que lo van a componer.
	 * 
	 * @param nombrefichero
	 * @exception exceptions
	 *                Ning�n error (Excepci�n) definida
	 */
	public static void DefinirXML(String nombrefichero) {

		nombreficheroxml = nombrefichero;
		/*
		 * En esta Clase CrearXMLOffline creamos los datos (Nombre) del archivo
		 * XML a crear a la vez que el pcap
		 */
		CrearXMLOffline crearxmloffline = new CrearXMLOffline(nombreficheroxml);
		/* En esta clase podemos crear XmlPacketHandler */

		ficheroxmlenconstruccion = new XmlPacketHandler(crearxmloffline);

	}

	/**
	 * Metodo donde se Define los hilos para las capturas como algunas detalles
	 * de la captura, si es archivos multiples.
	 * 
	 * @exception exceptions
	 *                Ning�n error (Excepci�n) definida
	 */
	public void runHilos() {
		
		if (getTime() !=0){
			this.STime = new SaveTime(this, getTime(), this.SFName);
			this.STime.start();
			this.multiFile = true;
		}
		else if (getSpace() != 0) {
			if (getNumPacket() != 0) {
				this.captureThread = new Thread(new Runnable() {
					public void run() {
						while (SavePacketHandler.this.captureThread != null) {
						}
					}
				});
				this.captureThread.setPriority(1);
				this.captureThread.start();
			}
			this.SFName.setNext();
			setTcpDumpWriter_first(this.SFName.getNameTime());
			this.SFName.saveStateMulti(true);
			System.out.println("\n----> " + this.SFName.getNameTime());
			this.setMultiFile(true);

		} else {
			if (getNumPacket() != 0L) {
				this.captureThread = new Thread(new Runnable() {
					public void run() {
						while (SavePacketHandler.this.captureThread != null) {
						}
					}
				});
				this.captureThread.setPriority(1);
				this.captureThread.start();
				
			}else {
				setTcpDumpWriter_first();
				this.SFName.saveState(true);
		}
			} 
	}

	public static PcapDumper getDumper() {
		return dumper;
	}

	public static void setDumper(PcapDumper dumper) {
		SavePacketHandler.dumper = dumper;
	}

	/**
	 * Metodo se paran las conexi�nes con la red para para el proceso de
	 * captura.
	 * 
	 * @exception exceptions
	 *                Ning�n error (Excepci�n) definida
	 */
	public void stopHilos() {
		if (this.STime != null) {
			this.STime.stop();
		}
		if (this.SSpace != null) {
			this.captureThread.stop();
		}
		if (this.captureThread != null) {
			this.captureThread.stop();
		}
	}

	/**
	 * Metodo donde prepara la apertura del fichero *.pcap, donde se guardaran
	 * las capturas, se guardan en un objeto file.
	 * 
	 * @exception exceptions
	 *                por si falla la carga en el objeto
	 */
	public void setTcpDumpWriter_first() {
		try {
			this.jpcap = Pcap.openOffline(getFullName(), errbuf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTcpDumpWriter() {
		try {
			this.jpcap.close();
			this.jpcap = Pcap.openOffline(getFullName(), errbuf);

			resetSpaceLen();
			this.otroFile = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setTcpDumpWriter(String strAuxName) {
		try {
			setAuxFile(strAuxName);
			otroFile = true;

			resetSpaceLen();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setFile(strAuxName);
	}

	public void setTcpDumpWriter_first(String strAuxName) {
		try {
			setAuxFile(strAuxName);

			this.jpcap = Pcap.openOffline(getAuxFullName(), errbuf);
			resetSpaceLen();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setFile(strAuxName);
	}

	/**
	 * Metodo donde se recive los paquetes capturados aqui se guardan en un
	 * objeto para su posterior guardado en un archivo.
	 * 
	 * @param jpcap2
	 * @param jpcap2
	 * @param packet
	 * @exception exceptions
	 *                si falla el guardado secuencial de la captura
	 */
	public static void receivePacket(Pcap jpcap2) {
		
		if (isMultiFile()){
			
		if ((!(SFName.getPath() + SFName.getSeparator() + getFile()).equalsIgnoreCase(ruta) )|| SaveTime.isCambiaArchivo()==true  ) {
			
			DefinirXML(SFName.getPath() + SFName.getSeparator()+ SFName.getNameFile() + "_" + SFName.getDateTime() + "_"+SFName.getContador() + ".xml");
			setDumper(jpcap2.dumpOpen(SFName.getPath() + SFName.getSeparator()+ getFile()));
			
			SaveTime.setCambiaArchivo(false);
		
		}}

 		PcapPacketHandler<PcapDumper> dumpHandler = new PcapPacketHandler<PcapDumper>() {

			public void nextPacket(PcapPacket packet, PcapDumper dumper) {

				PcapHeader header = packet.getCaptureHeader();
				dumper.dump(header, packet);
				if (controlPacket) {
					RCountPH.nextPacket(packet);
					ficheroxmlenconstruccion.receivePacket(packet);
					contSpaceLen += packet.size();

				}
			}
		};

		jpcap2.loop(1, dumpHandler, getDumper());
		nextContPacket();
		/* Se crean en Multiarchivos */
		

		
		if (contSpaceLen >= getSpace() && (getSpace() != 0)) {
			SFName.setNext();
			if (SFName.getNext() == -1) {
				aux = 0;
				stopCaptura();
				savefichero();
				dumper.close();

			} else {
				savefichero();
				setTcpDumpWriter(SFName.getNameTime());
				SFName.saveStateMulti(true);
			}

			}else {
			ruta = (SFName.getPath() + SFName.getSeparator() + getFile());

			}

		/* Una vez grabado podemos vaciar el buffer de memoria */
		try {
			System.in.skip(System.in.available());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if ((getContPacket() >= getNumPacket()) && (getNumPacket() != 0L)) {
			aux = 0;

			stopCaptura();
			savefichero();
			dumper.close();
		}

	}

	static void savefichero() {

		
		/*
		 * Cerramos Objeto para que se guarde completamente las capturas en el
		 * fichero xml
		 */
		ficheroxmlenconstruccion.finEntrada();
		System.out.println("\n Se ha realizado el archivo en XML en "
				+ nombreficheroxml);
		// dumper.close();

	}

	/**
	 * Metodo donde se para la captura de paquetes, ya sea por orden del usuario
	 * o por que ya ha llegado al numero de paquetes pedido por el usuario.
	 * 
	 * @exception exceptions
	 *                si falla el guardado secuencial de la captura
	 */
	public static void stopCaptura() {

		if (isMultiFile()) {
			SFName.saveStateMulti(false);
		} else {
			SFName.saveState(false);
		}
		setSpace(0);

		getVenPadre().stopCaptureThread();
		savefichero();

	}

	public static Captura getVenPadre() {
		return venpadre;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getTime() {
		return this.time;
	}

	public static void setSpace(long spaces) {
		space = spaces;
	}

	public static long getSpace() {
		return space;
	}

	public static void setFile(String file) {
		strFile = file;
	}

	public static String getFile() {
		return strFile;
	}

	public void setPila(int pila) {
		this.SFName.setPila(pila);
	}

	public int getPila() {
		return this.SFName.getPila();
	}

	public void setMaximo(int max) {
		this.SFName.setMaximo(max);
	}

	public int getMaximo() {
		return this.SFName.getMaximo();
	}

	public static void setAuxFile(String file) {
		auxStrFile = file;
	}

	public String getAuxFile() {
		return this.auxStrFile;
	}

	public static String getFullName() {
		return SFName.getPath() + SFName.getSeparator() + getFile();
	}

	public String getAuxFullName() {
		return this.SFName.getPath() + this.SFName.getSeparator()
				+ getAuxFile();
	}

	public void setNumPacket(long aux) {
		this.numPacket = aux;
	}

	public static long getNumPacket() {
		return numPacket;
	}

	public void setContPacket(long aux) {
		this.contPacket = aux;
	}

	public static long getContPacket() {
		return contPacket;
	}

	public static void nextContPacket() {
		contPacket += 1;
	}

	public static void resetSpaceLen() {
		contSpaceLen = 0;
	}

	public static long getspaceLen() {
		return contSpaceLen;
	}

	public void setControlPacket(boolean aux) {
		this.controlPacket = aux;
	}

	public void setWriter(Pcap jWriter) {
		this.jpcap_writer = jWriter;
	}

	public static boolean isMultiFile() {
		return multiFile;
	}

	public static void setMultiFile(boolean multiFile) {
		SavePacketHandler.multiFile = multiFile;
	}

}