package presentacion.visualizarCaptura;

import dominio.pcap.rules.*;
import dominio.pcapDumper.Captura;
import dominio.preferences.preferencesBeanDetallePaquete;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.Collection;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import presentacion.Mediador;
import servicioAccesoDatos.FachadaFicheroDirectorios;

/**
 * Clase Sniffer, Clase Principal.
 * 
 * @author Jose Manuel Saiz, Rodrigo Sánchez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
 */

public class VisualizarCaptura extends JPanel implements Runnable {

	private static final long serialVersionUID = 1255618549349652261L;

	// private Thread captureThread2;
	// private boolean finOneFile;
	protected boolean isLiveCapture;
	protected int i;
	// private boolean finSaveMeta;

	public VisualizarCaptura(Mediador med) {
		contadorarbol = 0;
		mediador = med;
		pBDP = mediador.getPrefPacketDet();
		actualizarValoresPreferencias();
		hilo = null;
		mediador.setPanelEstado("Cargado datos desde fichero");
		VectorConexiones = new Vector<Conexion>();
		history = new Vector<PcapPacket>();
		VentanaCaptura();
		actualizarListaActuales(true);
	}

	public void VentanaCaptura() {
		Label = new JLabel();
		Label.setIcon(new ImageIcon(
				(new StringBuilder(String.valueOf(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES"))))
						.append(System.getProperty("file.separator")).append("tcp.png").toString()));
		Label.setText("Conexiones TCP");
		TablaPaquetes = new TablePane(this);
		TablaConexiones = new TableConexions(TablaPaquetes);
		Paneaux = new JPanel();
		Paneaux2 = new JPanel();

		Paneconexiones = new JPanel();
		Paneaux.setLayout(new GridLayout(0, 1));
		Paneaux2.setLayout(new GridLayout(0, 1));

		// Trees (packets on detail)
		Paneauxtree = new JPanel();
		Paneauxtree.setLayout(new GridLayout(numVertical, numHorizontal));
		Paneauxtree_list = new ArrayList<JPanel>(numVertical * numHorizontal);
		for (int i = 0; i < numVertical * numHorizontal; i++) {
			PaneauxtreeX = new JPanel();
			PaneauxtreeX.setLayout(new BorderLayout());
			Paneauxtree_list.add(PaneauxtreeX);
			Paneauxtree.add(PaneauxtreeX);
		}

		Paneconexiones.setBorder(new BevelBorder(0));
		Paneconexiones.setLayout(new BorderLayout());
		output = new JTextArea(37, 45);
		output2 = new JTextArea(12, 60);
		output2.setBackground(new Color(0, 0, 0));
		output2.setForeground(new Color(51, 255, 0));
		output.setEditable(false);
		output2.setEditable(false);
		scrollPane = new JScrollPane(TablaPaquetes.Tabla());
		scrollPane2 = new JScrollPane(TablaConexiones.Tabla());
		Paneconexiones.add(Label, "North");
		Paneconexiones.add(scrollPane2, "Center");
		JSplitPane splitPane = new JSplitPane(0);
		splitPane.setTopComponent(scrollPane);
		splitPane.setBottomComponent(Paneconexiones);
		splitPane.setDividerLocation(200);
		Paneaux.add(splitPane);
		JSplitPane splitPane2 = new JSplitPane(0);
		splitPane2.setTopComponent(Paneaux);
		splitPane2.setBottomComponent(Paneauxtree);
		splitPane2.setDividerLocation(300);
		Paneaux2.add(splitPane2);
		Paneaux2.setPreferredSize(new Dimension(1023, 760));
	}

	public JPanel getPanel() {
		return Paneaux2;
	}

	public void run() {
		resetGraficos();

		AbrirFile(getFile());
	}

	public void setFile(String aux) {
		fichero = aux;
	}

	public String getFile() {
		return fichero;
	}

	public void resetGraficos() {
		VectorConexiones.clear();
		history.clear();
		TablaPaquetes.clearTable();
		TablaConexiones.clearTable();
		for (int i = 0; i < Paneauxtree_list.size(); i++) {
			Paneauxtree_list.get(i).removeAll();
		}

		contadorarbol = 0;
		redimensionar(true);
	}

	private void redimensionar(boolean flag) {
		actualizarValoresPreferencias();
		Paneauxtree.removeAll();
		Paneauxtree.setLayout(new GridLayout(numVertical, numHorizontal));
		Paneauxtree_list = new ArrayList<JPanel>(numVertical * numHorizontal);
		for (int i = 0; i < numVertical * numHorizontal; i++) {
			PaneauxtreeX = new JPanel();
			PaneauxtreeX.setLayout(new BorderLayout());
			Paneauxtree_list.add(PaneauxtreeX);
			Paneauxtree.add(PaneauxtreeX);
		}
		antiguos = Arrays.copyOf(actuales, actuales.length);
		actualizarListaActuales(flag);
		contadorarbol = 0;
		for (int i = 0; i < antiguos.length; i++) {
			if (i < Paneauxtree_list.size() && antiguos[i] != -1)
				CrearArbol(antiguos[i]);
		}
		Paneauxtree.repaint();
	}

	public int getLinkLayer() {
		int Linklayer = -1;
		return Linklayer;
	}

	/**
	 * Metodo se abre el fichero pcap con capturas para su visualizaci�n.
	 * 
	 * @param String nombre
	 * @return sin valor de retorno
	 */
	public void AbrirFile(String nombre) {
		String fichero = nombre;
		System.out.println((new StringBuilder("1............")).append(fichero).toString());
		PacketHandlerPcapLib PacketHPL = new PacketHandlerPcapLib(this, TablaPaquetes);
		try {
			abrirFichero();
			System.out.println("5............");
			AddConexionesTabla();
			System.err.println((new StringBuilder("Establecio fichero leido => ")).append(fichero).toString());
			mediador.setFileReaded(fichero);
			mediador.getVentanaMenuSniffer().dispose();
			Captura.eCOFwithoutSaveMeta(pcap);
			System.out.println("...paso");
			if (Reglas != null)
				log.CrearLog();
		} catch (Exception exceptionfile) {
			exceptionfile.printStackTrace();
		}
	}

	private void abrirFichero() {
		isLiveCapture = true;
		StringBuilder errbuf = new StringBuilder();
		pcap = Pcap.openOffline(fichero, errbuf);

		do {
			i = 0;
			PcapPacketHandler<String> handler = new PcapPacketHandler<String>() {

				public void nextPacket(PcapPacket packet, String user) {
					PacketHandlerPcapLib.nextPacket(packet);
					i = 1;
				}
			};
			pcap.loop(1, handler, "jNetPcap rocks!");

		} while (i == 1 && (VisualizarCaptura.this.isLiveCapture));

	}

	public void CrearArbol(int numero) {
		contadorarbol++;
		if (contadorarbol > Paneauxtree_list.size())
			contadorarbol = 1;
		PcapPacket paquete = (PcapPacket) history.get(numero);
		Border emptyBorder = BorderFactory.createLoweredBevelBorder();
		Border selectBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red);
		Arbol = new TreePacket(numero, paquete, pBDP);
		scrollPane3 = new JScrollPane(Arbol.Arbol());
		scrollPane3.setBorder(selectBorder);

		actuales[contadorarbol - 1] = numero;
		PaneauxtreeX = Paneauxtree_list.get(contadorarbol - 1);
		PaneauxtreeX.removeAll();
		PaneauxtreeX.add(scrollPane3, "Center");

		if (PaneauxtreeX.getComponentCount() > 0) {
			JScrollPane auxJSPanel = null;
			if (contadorarbol == 1)
				try {
					auxJSPanel = (JScrollPane) Paneauxtree_list.get(Paneauxtree_list.size() - 1).getComponent(0);
					auxJSPanel.setBorder(emptyBorder);
				} catch (ArrayIndexOutOfBoundsException e) {}
			else {
				auxJSPanel = (JScrollPane) Paneauxtree_list.get(contadorarbol - 2).getComponent(0);
				auxJSPanel.setBorder(emptyBorder);
			}
		}

		mediador.repaintVentana(mediador.getVentanaMenuSniffer());
	}

	public void dumpHistory() {
	}

	public void refreshCapture() {
		Paneaux.repaint();
		Paneaux2.repaint();
		repaint();
		System.out.println("refres...paso");
	}

	public void LoadFileRules(File nombre) {
		try {
			String nombreficheroreglas = (new StringBuilder()).append(nombre.getAbsoluteFile()).toString();
			if (nombreficheroreglas.indexOf(".rules") == -1)
				throw new Exception("Fichero no de reglas");
			if (Reglas == null) {
				log = new XMLlog(1);
				TablaAlertas = new TableAlerts();
				FAlertsLoad = new FAlertsLoad();
				Reglas = new Rules(TablaAlertas, log);
			}
			Reglas.LoadRules(nombre);
			FAlertsLoad.DatosTablaAlertsLoad(nombre.getAbsolutePath());
			Reglas.OrdenarReglas();
			JOptionPane.showMessageDialog(frameinf, (new StringBuilder("Fichero de reglas "))
					.append(nombreficheroreglas).append(" añadido").toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frameinf, "Formato incorrecto de fichero de reglas");
		}
	}

	public Rules getReglas() {
		return Reglas;
	}

	public void clearHistory() {

		history.clear();
	}

	public static void addpackethistory(PcapPacket packet) {

		Paneaux2.repaint();

		history.add(packet);
	}

	public Vector<Conexion> getConexionesTCP() {
		return VectorConexiones;
	}

	public void DatosConexion(String IpOrigen, String IpDestino, int PuertoOrigen, int PuertoDestino,
			int numeropaquete) {
		int i = 0;
		boolean existente = false;
		NuevaConexion = new Conexion(IpOrigen, IpDestino, PuertoOrigen, PuertoDestino);
		if (VectorConexiones.size() != 0) {
			for (; i < VectorConexiones.size() && !existente; i++) {
				Conexion objetoConexion = (Conexion) VectorConexiones.elementAt(i);
				if ((objetoConexion.getIpSrc().compareTo(IpOrigen) == 0
						&& objetoConexion.getIpDest().compareTo(IpDestino) == 0
						|| objetoConexion.getIpSrc().compareTo(IpDestino) == 0
								&& objetoConexion.getIpDest().compareTo(IpOrigen) == 0)
						&& (objetoConexion.getPuertoSrc() == PuertoOrigen
								&& objetoConexion.getPuertoDst() == PuertoDestino
								|| objetoConexion.getPuertoSrc() == PuertoDestino
										&& objetoConexion.getPuertoDst() == PuertoOrigen)) {
					objetoConexion.addpaquete(numeropaquete);
					existente = true;
				}
			}

			if (i == VectorConexiones.size() && !existente) {
				NuevaConexion.addpaquete(numeropaquete);
				VectorConexiones.add(NuevaConexion);
			}
		} else {
			NuevaConexion.addpaquete(numeropaquete);
			VectorConexiones.add(NuevaConexion);
		}
	}

	public void AddConexionesTabla() {
		for (int i = 0; i < VectorConexiones.size(); i++) {
			Conexion objetoConexion = (Conexion) VectorConexiones.elementAt(i);
			TablaConexiones.DatosTablaConexion(String.valueOf(i), objetoConexion.getTimeEstablecimiento(),
					objetoConexion.getIpSrc(), objetoConexion.getIpDest(),
					String.valueOf(objetoConexion.getPuertoSrc()), String.valueOf(objetoConexion.getPuertoDst()),
					String.valueOf(objetoConexion.getnumeropaquetes()));
		}

	}

	public String leerPTV() {
		return mediador.leerPropertiesTableView();
	}

	public String getColumnPosition() {
		return TablaPaquetes.obtenerOrdenColumnas();
	}

	private void actualizarValoresPreferencias() {
		this.numVertical = Integer.valueOf(pBDP.getRows());
		this.numHorizontal = Integer.valueOf(pBDP.getColumns());
	}
	
	public void refreshPacketDetail() {
		redimensionar(false);
	}
	
	private void actualizarListaActuales(boolean flag) {
		actuales = new int[numVertical * numHorizontal];
		for(int i = 0; i < actuales.length; i++) {
			actuales[i] = -1;
		}
		if(flag)
			antiguos = Arrays.copyOf(actuales, actuales.length);
	}

	private Vector<Conexion> VectorConexiones;
	private Rules Reglas;
	private XMLlog log;
	// private Vector VectorPacket;
	protected Conexion NuevaConexion;
	private int contadorarbol;
	public static Vector<PcapPacket> history;
	protected JFrame frameinf;
	protected Pcap paquete;
	public boolean visualframe;
	protected Pcap pcap;
	protected Pcap pcapLectura;
	private String fichero;
	protected Thread hilo;
	// protected Collection Historial;
	public static TablePane TablaPaquetes;
	private TableConexions TablaConexiones;
	public TableAlerts TablaAlertas;
	public FAlertsLoad FAlertsLoad;
	public Mediador mediador;
	private TreePacket Arbol;
	// public Vector x;
	public JTabbedPane jTabbedPane1;
	public static JPanel Paneaux;
	public static JPanel Paneaux2;
	public JPanel Paneauxtree;
	public ArrayList<JPanel> Paneauxtree_list;
	public JPanel PaneauxtreeX;
	public JPanel Paneconexiones;
	public JTextArea output;
	public JTextArea output2;
	public JScrollPane scroll;
	public JScrollPane scrollPane;
	public JScrollPane scrollPane2;
	public JScrollPane scrollPane3;
	public JSplitPane splitPane;
	public JSplitPane splitPane2;
	public JToolBar toolBar;
	public JSeparator jSeparator;
	public JLabel Label;
	private int numVertical;
	private int numHorizontal;
	private preferencesBeanDetallePaquete pBDP;
	private int[] actuales;
	private int[] antiguos;
	// public static SavePacketHandler ficheroxmlenconstruccion;
}
