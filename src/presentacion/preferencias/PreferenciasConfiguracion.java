
package presentacion.preferencias;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.layout.GroupLayout;
import presentacion.Mediador;
import presentacion.comandos.CBAbrirCarpeta;
import presentacion.comandos.CBAceptar;
import presentacion.comandos.Comando;
import presentacion.propiedadesVentana.CentrarVentana;

/**
 * Clase PreferenciasConfiguracion.
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,
 * @version 1.2
 */
public class PreferenciasConfiguracion extends JDialog {

	private static final long serialVersionUID = 214336178097545692L;

	Mediador mediador;
	Comando jButton1;
	Comando jButton2;
	Comando jButton3;
	Comando jButton4;
	Comando jButton5;
	Comando jButton7;
	private JButton jButton6;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel7;
	private JPanel jPanel1;

	private static JTextField jTextField1;
	private static JTextField jTextField2;
	private static JTextField jTextField3;
	private static JTextField jTextField4;
	private static JTextField jTextField7;

	public PreferenciasConfiguracion() {
		super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Configuración", true);
		this.mediador = new Mediador();
		initComponents();
		setResizable(false);
		new CentrarVentana(this);
	}

	public PreferenciasConfiguracion(Mediador med) {
		super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Configuración...", true);
		this.mediador = med;
		initComponents();
		setResizable(false);
		new CentrarVentana(this);
	}

	private void initComponents() {
		this.jPanel1 = new JPanel();
		this.jLabel1 = new JLabel();
		this.jLabel2 = new JLabel();
		this.jLabel3 = new JLabel();
		this.jLabel4 = new JLabel();
// this.jLabel7 = new JLabel();
		jTextField1 = new JTextField();
		jTextField2 = new JTextField();
		jTextField3 = new JTextField();
		jTextField4 = new JTextField();
// jTextField7 = new JTextField();
		this.jButton6 = new JButton();

		setDefaultCloseOperation(2);
		this.jPanel1.setBorder(BorderFactory.createTitledBorder("Sniffer III - Directorios de Usuario"));
		this.jLabel1.setText("Capturas:");
		this.jLabel2.setText("Exportaciones: ");
		this.jLabel3.setText("Scripts:");
		this.jLabel4.setText("Parametrización:");

// this.jLabel7.setText("Intrusos:");

		this.jButton1 = new CBAbrirCarpeta(this.mediador, "Capturas");
		this.jButton2 = new CBAbrirCarpeta(this.mediador, "Exportaciones");
		this.jButton3 = new CBAbrirCarpeta(this.mediador, "Scripts");
		this.jButton4 = new CBAbrirCarpeta(this.mediador, "Paremetrización");
// this.jButton7 = new CBAbrirCarpeta(this.mediador, "Resultado");

		GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup()
				.addContainerGap()
				.add(jPanel1Layout
						.createParallelGroup(1).add(this.jLabel4).add(this.jLabel3).add(this.jLabel2).add(this.jLabel1))
				.addPreferredGap(0)
				.add(jPanel1Layout.createParallelGroup(1, false).add(jTextField4, -2, 220, -2)
						.add(jTextField3, -2, 220, -2).add(jTextField2, -2, 220, -2).add(jTextField1, -2, 220, -2))

				.add(6, 6, 6)
				.add(jPanel1Layout.createParallelGroup(1, false).add((CBAbrirCarpeta) this.jButton2, 0, 0, 32767)
						.add((CBAbrirCarpeta) this.jButton3, -2, 33, 32767)
						.add((CBAbrirCarpeta) this.jButton4, -2, 33, 32767)
						.add((CBAbrirCarpeta) this.jButton1, 0, 0, 32767))
				.addContainerGap(-1, 32767)));

		jPanel1Layout
				.setVerticalGroup(
						jPanel1Layout.createParallelGroup(1)
								.add(jPanel1Layout.createSequentialGroup().addContainerGap()
										.add(jPanel1Layout.createParallelGroup(3).add(this.jLabel1)
												.add(jTextField1, -2, -1, -2).add((CBAbrirCarpeta) this.jButton1))
										.add(11, 11, 11)
										.add(jPanel1Layout.createParallelGroup(3).add(this.jLabel2)
												.add(jTextField2, -2, -1, -2).add((CBAbrirCarpeta) this.jButton2))
										.add(14, 14, 14)
										.add(jPanel1Layout.createParallelGroup(3).add(this.jLabel3)
												.add(jTextField3, -2, -1, -2).add((CBAbrirCarpeta) this.jButton3))
										.add(14, 14, 14)
										.add(jPanel1Layout.createParallelGroup(3).add(this.jLabel4)
												.add(jTextField4, -2, -1, -2).add((CBAbrirCarpeta) this.jButton4))
										.add(14, 14, 14).addContainerGap(-1, 32767)));

		this.jButton5 = new CBAceptar(this.mediador, "AceptarConfiguracion");

		this.jButton6.setText("Cancelar");
		this.jButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				PreferenciasConfiguracion.this.jButton6ActionPerformed(evt);
			}
		});
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(1)
				.add(layout.createSequentialGroup()
						.add(layout.createParallelGroup(2)
								.add(layout.createSequentialGroup().addContainerGap().add(this.jButton6)
										.addPreferredGap(0).add((CBAceptar) this.jButton5))
								.add(1, layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2)))
						.addContainerGap(11, 32767)));

		layout.setVerticalGroup(layout.createParallelGroup(1)
				.add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).add(21, 21, 21)
						.add(layout.createParallelGroup(3).add((CBAceptar) this.jButton5).add(this.jButton6))
						.addContainerGap(-1, 32767)));

		pack();
	}

	private void jButton6ActionPerformed(ActionEvent evt) {
		dispose();
	}

	public static void setCapturas(String aux) {
		jTextField1.setText(aux);
	}

	public static String getCapturas() {
		return jTextField1.getText();
	}

	public static void setExportaciones(String aux) {
		jTextField2.setText(aux);
	}

	public static String getExportaciones() {
		return jTextField2.getText();
	}

	public static void setScripts(String aux) {
		jTextField3.setText(aux);
	}

	public static String getScripts() {
		return jTextField3.getText();
	}

	public static void setParametrizacion(String aux) {
		jTextField4.setText(aux);
	}

	public static String getParametrizacion() {
		return jTextField4.getText();
	}

}
