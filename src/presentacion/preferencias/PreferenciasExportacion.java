
package presentacion.preferencias;

/* Aqui se crea la pantalla de elección de la captura .pcap a export a xml*/
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.jdesktop.layout.GroupLayout;
import presentacion.Mediador;
import presentacion.comandos.CBAbrirFichero;
import presentacion.comandos.CBAceptar;
import presentacion.comandos.CBGuardarFichero;
import presentacion.comandos.Comando;
import presentacion.propiedadesVentana.CentrarVentana;

/**
 * Clase Preferencias.
 * 
 * @author Jose Manuel Saiz, Rodrigo Sanchez Gonzalez
 * @author jmsaizg@gmail.com, rsg0040@alu.ubu.es
 * @version 1.3
 */
public class PreferenciasExportacion extends JDialog {

	private static final long serialVersionUID = -410943735259253089L;
	
	Mediador mediador;
	private boolean tipo;
	private String title;
	private Comando jButtonOrigen;
	private Comando jButtonDestino;
	private Comando jButtonAbrir;
	private Comando jButtonGuardar;
	private Comando jButtonAceptar;
	private ButtonGroup buttonGroup1;
	private ButtonGroup buttonGroup2;
	private ButtonGroup buttonGroup3;
	private JButton jButtonCancelar;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JPanel jPanel2;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	private static JTextField jTextFieldDestino;
	private static JTextField jTextFieldOrigen;
	private static JCheckBox jCheckBox1;

	public PreferenciasExportacion() {
		super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Exportar desde fichero a XML", true);
		this.mediador = new Mediador();
		initComponents();
		setResizable(false);
		new CentrarVentana(this);
		setVisible(true);
	}

	public PreferenciasExportacion(boolean tipo, Mediador med) {
		super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Exportar desde fichero a XML", true);
		this.mediador = med;
		this.tipo = tipo;
		if (tipo)
			this.title = "Exportar desde fichero a XML";
		else
			this.title = "Preferencias de Export";
		initComponents();
		setResizable(false);
		new CentrarVentana(this);
	}

	private void initComponents() {
		this.buttonGroup1 = new ButtonGroup();
		this.jScrollPane1 = new JScrollPane();
		this.jTextArea1 = new JTextArea();
		this.buttonGroup2 = new ButtonGroup();
		this.buttonGroup3 = new ButtonGroup();
		this.jPanel2 = new JPanel();
		this.jLabel1 = new JLabel();
		jTextFieldOrigen = new JTextField();

		this.jButtonOrigen = new CBAbrirFichero(this.mediador, "GenerarFromFileXML");
		this.jLabel2 = new JLabel();
		jTextFieldDestino = new JTextField();

		this.jButtonDestino = new CBGuardarFichero(this.mediador, "GenerarFromFileXML");
		this.jButtonCancelar = new JButton();

		jCheckBox1 = new JCheckBox();

		this.jButtonAbrir = new CBAbrirFichero(this.mediador, "GenerarFromFileXMLOpenXML");
		this.jButtonGuardar = new CBGuardarFichero(this.mediador, "GenerarFromFileXMLSaveXML");
		if (this.tipo) {
			this.jButtonAceptar = new CBAceptar(this.mediador, "GenerarXML");
		} else {
			this.jButtonCancelar.setVisible(false);
			this.jButtonAceptar = new CBAceptar(this.mediador, "Salir");
		}
		this.jTextArea1.setColumns(20);
		this.jTextArea1.setRows(5);
		this.jScrollPane1.setViewportView(this.jTextArea1);

		setDefaultCloseOperation(2);
		setTitle(this.title);

		this.jPanel2.setBorder(BorderFactory.createTitledBorder("Datos"));
		this.jLabel1.setText("Origen: ");

		this.jLabel2.setText("Destino: ");

		jCheckBox1.setText("Asigno fichero");
		jCheckBox1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		jCheckBox1.setMargin(new Insets(0, 0, 0, 0));

		GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
		this.jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(
						jPanel2Layout.createParallelGroup(1)
								.add(jPanel2Layout.createSequentialGroup().addContainerGap()
										.add(jPanel2Layout
												.createParallelGroup(1).add(
														jPanel2Layout.createSequentialGroup()
																.add(jPanel2Layout.createParallelGroup(1)
																		.add(this.jLabel1).add(this.jLabel2))
																.addPreferredGap(0)
																.add(jPanel2Layout.createParallelGroup(1)
																		.add(jTextFieldOrigen, -2, 189, -2)
																		.add(jTextFieldDestino, -1, 189, 32767))
																.addPreferredGap(0)
																.add(jPanel2Layout.createParallelGroup(1)
																		.add((CBGuardarFichero) this.jButtonDestino, 0,
																				0, 32767)
																		.add((CBAbrirFichero) this.jButtonOrigen, -2,
																				31, -2)))
												.add(jCheckBox1, -2, 106, -2))
										.addContainerGap()));

		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(1)
				.add(jPanel2Layout.createSequentialGroup().addContainerGap()
						.add(jPanel2Layout.createParallelGroup(3).add(this.jLabel1)
								.add((CBAbrirFichero) this.jButtonOrigen).add(jTextFieldOrigen, -2, -1, -2))
						.add(18, 18, 18)
						.add(jPanel2Layout.createParallelGroup(3).add(jTextFieldDestino, -2, -1, -2).add(this.jLabel2)
								.add((CBGuardarFichero) this.jButtonDestino))
						.addPreferredGap(0, 19, 32767).add(jCheckBox1).addContainerGap()));

		this.jButtonCancelar.setText("Cancelar");
		this.jButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				PreferenciasExportacion.this.jButtonCancelarActionPerformed(evt);
			}
		});
		GroupLayout layout = new GroupLayout(getContentPane());
		if (this.tipo) {
			getContentPane().setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(1)
					.add(layout.createSequentialGroup().add(layout.createParallelGroup(2)
							.add(layout.createSequentialGroup().addContainerGap(161, 32767).add(this.jButtonCancelar)
									.add(18, 18, 18).add((CBAceptar) this.jButtonAceptar))
							.add(1, layout.createSequentialGroup().addContainerGap().add(this.jPanel2, -2, -1, -2)))
							.addContainerGap()));

			layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap()
					.add(this.jPanel2, -2, -1, -2).addPreferredGap(0)
					.add(layout.createParallelGroup(3).add(this.jButtonCancelar).add((CBAceptar) this.jButtonAceptar))
					.addContainerGap()));
		} else {
			getContentPane().setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(1)
					.add(layout.createSequentialGroup().addContainerGap().add(this.jPanel2, -2, -1, -2)
							.addContainerGap())
					.add(2, layout.createSequentialGroup().addContainerGap(164, 32767).add(layout
							.createParallelGroup(1, false)
							.add(2, layout.createSequentialGroup().add((CBGuardarFichero) this.jButtonGuardar)
									.addPreferredGap(0, -1, 32767).add((CBAbrirFichero) this.jButtonAbrir, -2, 74, -2))
							.add(2, layout.createSequentialGroup().add(this.jButtonCancelar).add(15, 15, 15)
									.add((CBAceptar) this.jButtonAceptar)))
							.addContainerGap()));

			layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap()
					.add(this.jPanel2, -2, -1, -2).addPreferredGap(0)
					.add(layout.createParallelGroup(3).add((CBAbrirFichero) this.jButtonAbrir)
							.add((CBGuardarFichero) this.jButtonGuardar))
					.addPreferredGap(0, 14, 32767)
					.add(layout.createParallelGroup(3).add(this.jButtonCancelar).add((CBAceptar) this.jButtonAceptar))
					.addContainerGap()));
		}
		pack();
	}

	private void jButton3ActionPerformed(ActionEvent evt) {
		dispose();
	}

	private void jButtonCancelarActionPerformed(ActionEvent evt) {
		dispose();
	}

	public static String getOrigen() {
		return jTextFieldOrigen.getText();
	}

	public static void setOrigen(String aux) {
		jTextFieldOrigen.setText(aux);
	}

	public static String getDestino() {
		return jTextFieldDestino.getText();
	}

	public static void setDestino(String aux) {
		jTextFieldDestino.setText(aux);
	}

	public static boolean getMultiFile() {
		if (jCheckBox1.isSelected()) {
			return false;
		}
		return true;
	}

	public static void setMultiFile(boolean aux) {
		if (aux) {
			jCheckBox1.setSelected(false);
		} else {
			jCheckBox1.setSelected(true);
		}
	}
}