package presentacion.preferencias;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jdesktop.layout.GroupLayout;

import presentacion.Mediador;
import presentacion.comandos.CBGuardarFichero;
import presentacion.comandos.Comando;
import presentacion.propiedadesVentana.CentrarVentana;

/**
 * Clase PreferenciasPaqueteDetalle.
 * 
 * @author Raul Merinero Sanz
 * @author rms10050@alu.ubu.es
 * @version 1.0
 */
public class PreferenciasPaqueteDetalle extends JDialog {

	private static final long serialVersionUID = -5388305841147857317L;

	private Mediador mediador;

	private JPanel jPanelNVent;
	private JLabel jLabelNVent1;
	private JLabel jLabelNVent2;
	private static JTextField jTextFieldNVent1;
	private static JTextField jTextFieldNVent2;

	private JPanel jPanelNBytes;
	private ButtonGroup buttonGroupNBytes;
	private static JRadioButton jRadioButtonNBytes1;
	private static JRadioButton jRadioButtonNBytes2;
	private static JTextField jTextFieldNBytes;
	private JLabel jLabelNBytes;

	private JPanel jPanelBytesType;
	private ButtonGroup buttonGroupBytesType;
	private static JRadioButton jRadioButtonBytesType1;
	private static JRadioButton jRadioButtonBytesType2;

	private Comando jButtonAceptar;
	private JButton jButtonSalir;

	public PreferenciasPaqueteDetalle() {
		super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Detalle paquetes", true);
		this.mediador = new Mediador();
		initComponents();
		setResizable(false);
		new CentrarVentana(this);
		setVisible(true);
	}

	public PreferenciasPaqueteDetalle(Mediador med) {
		super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Detalle paquetes", true);
		this.mediador = med;
		initComponents();
		setResizable(false);
		new CentrarVentana(this);
		setVisible(false);
	}

	private void initComponents() {
		this.jPanelNVent = new JPanel();
		this.jLabelNVent1 = new JLabel();
		this.jLabelNVent2 = new JLabel();
		jTextFieldNVent1 = new JTextField();
		jTextFieldNVent2 = new JTextField();

		this.jPanelNBytes = new JPanel();
		this.buttonGroupNBytes = new ButtonGroup();
		jRadioButtonNBytes1 = new JRadioButton();
		jRadioButtonNBytes2 = new JRadioButton();
		jTextFieldNBytes = new JTextField();
		this.jLabelNBytes = new JLabel();

		this.jPanelBytesType = new JPanel();
		this.buttonGroupBytesType = new ButtonGroup();
		jRadioButtonBytesType1 = new JRadioButton();
		jRadioButtonBytesType2 = new JRadioButton();

		this.jButtonAceptar = new CBGuardarFichero(this.mediador, "DetallePaquetes");
		this.jButtonSalir = new JButton();

		setDefaultCloseOperation(2);

		this.jPanelNVent.setBorder(BorderFactory.createTitledBorder("Distribución de árboles de paquetes"));

		this.jLabelNVent1.setText("Filas de paquetes:");
		this.jLabelNVent2.setText("Columnas de paquetes:");

		GroupLayout jPanelLayout1 = new GroupLayout(this.jPanelNVent);
		this.jPanelNVent.setLayout(jPanelLayout1);

		jPanelLayout1
				.setHorizontalGroup(jPanelLayout1.createParallelGroup(1)
						.add(jPanelLayout1.createSequentialGroup().addContainerGap()
								.add(jPanelLayout1.createParallelGroup(1).add(this.jLabelNVent2).add(this.jLabelNVent1))
								.addPreferredGap(0).add(jPanelLayout1.createParallelGroup(1, false)
										.add(jTextFieldNVent1, -2, 165, -2).add(jTextFieldNVent2))
								.addContainerGap(-1, 32767)));

		jPanelLayout1.setVerticalGroup(jPanelLayout1.createParallelGroup(1).add(jPanelLayout1.createSequentialGroup()
				.addContainerGap()
				.add(jPanelLayout1.createParallelGroup(2).add(this.jLabelNVent1).add(jTextFieldNVent1, -2, -1, -2))
				.addPreferredGap(0, 19, 32767)
				.add(jPanelLayout1.createParallelGroup(2).add(this.jLabelNVent2).add(jTextFieldNVent2, -2, -1, -2))
				.add(20, 20, 20)));

		this.jPanelNBytes.setBorder(BorderFactory.createTitledBorder("Cantidad de bytes mostrados"));

		this.jLabelNBytes.setText(" bytes");
		jTextFieldNBytes.setHorizontalAlignment(JLabel.CENTER);

		this.buttonGroupNBytes.add(jRadioButtonNBytes1);
		jRadioButtonNBytes1.setMnemonic(1);
		jRadioButtonNBytes1.setText("Primeros ");
		jRadioButtonNBytes1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		jRadioButtonNBytes1.setMargin(new Insets(0, 0, 0, 0));
		this.buttonGroupNBytes.add(jRadioButtonNBytes2);
		jRadioButtonNBytes2.setMnemonic(2);
		jRadioButtonNBytes2.setText("Fichero completo");
		jRadioButtonNBytes2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		jRadioButtonNBytes2.setMargin(new Insets(0, 0, 0, 0));

		GroupLayout jPanelLayout2 = new GroupLayout(this.jPanelNBytes);
		this.jPanelNBytes.setLayout(jPanelLayout2);
		jPanelLayout2.setHorizontalGroup(jPanelLayout2.createParallelGroup(1)
				.add(jPanelLayout2.createSequentialGroup().add(26, 26, 26)
						.add(jPanelLayout2.createParallelGroup(1)
								.add(jPanelLayout2.createSequentialGroup().add(jRadioButtonNBytes1)
										.add(jTextFieldNBytes).add(this.jLabelNBytes))
								.add(jRadioButtonNBytes2))
						.addContainerGap(36, 32767)));

		jPanelLayout2.setVerticalGroup(jPanelLayout2.createParallelGroup(1)
				.add(jPanelLayout2.createSequentialGroup().addContainerGap()
						.add(jPanelLayout2.createParallelGroup(1).add(jRadioButtonNBytes1).add(jTextFieldNBytes)
								.add(this.jLabelNBytes))
						.addPreferredGap(0).add(jRadioButtonNBytes2).addContainerGap(-1, 32767)));

		this.jPanelBytesType.setBorder(BorderFactory.createTitledBorder("Tipo representación bytes"));

		this.buttonGroupBytesType.add(jRadioButtonBytesType1);
		jRadioButtonBytesType1.setMnemonic(3);
		jRadioButtonBytesType1.setText("Hexadecimal");
		jRadioButtonBytesType1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		jRadioButtonBytesType1.setMargin(new Insets(0, 0, 0, 0));
		this.buttonGroupBytesType.add(jRadioButtonBytesType2);
		jRadioButtonBytesType2.setMnemonic(4);
		jRadioButtonBytesType2.setText("Decimal");
		jRadioButtonBytesType2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		jRadioButtonBytesType2.setMargin(new Insets(0, 0, 0, 0));

		GroupLayout jPanelLayout3 = new GroupLayout(this.jPanelBytesType);
		this.jPanelBytesType.setLayout(jPanelLayout3);
		jPanelLayout3.setHorizontalGroup(jPanelLayout3.createParallelGroup(1)
				.add(jPanelLayout3.createSequentialGroup().add(26, 26, 26).add(
						jPanelLayout3.createParallelGroup(1).add(jRadioButtonBytesType1).add(jRadioButtonBytesType2))
						.addContainerGap(36, 32767)));

		jPanelLayout3.setVerticalGroup(jPanelLayout3.createParallelGroup(1)
				.add(jPanelLayout3.createSequentialGroup().addContainerGap().add(jRadioButtonBytesType1)
						.addPreferredGap(0).add(jRadioButtonBytesType2).addContainerGap(-1, 32767)));

		this.jButtonSalir.setText("Salir");
		this.jButtonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setAutocreateGaps(true);
		layout.setAutocreateContainerGaps(true);
		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(this.jPanelNVent)
				.add(this.jPanelNBytes).add(this.jPanelBytesType)
				.add(layout.createSequentialGroup().add((CBGuardarFichero) jButtonAceptar).add(this.jButtonSalir))));

		layout.setVerticalGroup(layout.createSequentialGroup().add(this.jPanelNVent).add(this.jPanelNBytes)
				.add(this.jPanelBytesType)
				.add(layout.createParallelGroup(1).add((CBGuardarFichero) this.jButtonAceptar).add(this.jButtonSalir)));

		pack();
	}

	public static String getFilasPaquetes() {
		return jTextFieldNVent1.getText();
	}

	public static void setFilasPaquetes(String filas) {
		jTextFieldNVent1.setText(filas);
	}

	public static String getColumnasPaquetes() {
		return jTextFieldNVent2.getText();
	}

	public static void setColumnasPaquetes(String columnas) {
		jTextFieldNVent2.setText(columnas);
	}

	public static boolean getPacketTotalBytes() {
		return jRadioButtonNBytes2.isSelected();
	}

	public static void setPacketTotalBytes(boolean flag) {
		jRadioButtonNBytes1.setSelected(!flag);
		jRadioButtonNBytes2.setSelected(flag);
	}

	public static String getPacketBytes() {
		return jTextFieldNBytes.getText();
	}

	public static void setPacketBytes(String firstXBytes) {
		jTextFieldNBytes.setText(firstXBytes);
	}

	public static boolean getBytesHex() {
		return jRadioButtonBytesType1.isSelected();
	}

	public static void setBytesHex(boolean flag) {
		jRadioButtonBytesType1.setSelected(flag);
		jRadioButtonBytesType2.setSelected(!flag);
	}
}
