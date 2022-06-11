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
import presentacion.comandos.CBAceptar;
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
	private JTextField jTextFieldNVent1;
	private JTextField jTextFieldNVent2;

	private JPanel jPanelNBytes;
	private ButtonGroup buttonGroupNBytes;
	private JRadioButton jRadioButtonNBytes1;
	private JRadioButton jRadioButtonNBytes2;
	private JTextField jTextFieldNBytes;
	private JLabel jLabelNBytes;

	private JPanel jPanelBytesType;
	private ButtonGroup buttonGroupBytesType;
	private JRadioButton jRadioButtonBytesType1;
	private JRadioButton jRadioButtonBytesType2;

	private Comando jButtonAceptar;
	private JButton jButtonCancelar;

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
		setVisible(true);
	}

	private void initComponents() {
		this.jPanelNVent = new JPanel();
		this.jLabelNVent1 = new JLabel();
		this.jLabelNVent2 = new JLabel();
		this.jTextFieldNVent1 = new JTextField();
		this.jTextFieldNVent2 = new JTextField();

		this.jPanelNBytes = new JPanel();
		this.buttonGroupNBytes = new ButtonGroup();
		this.jRadioButtonNBytes1 = new JRadioButton();
		this.jRadioButtonNBytes2 = new JRadioButton();
		this.jTextFieldNBytes = new JTextField();
		this.jLabelNBytes = new JLabel();

		this.jPanelBytesType = new JPanel();
		this.buttonGroupBytesType = new ButtonGroup();
		this.jRadioButtonBytesType1 = new JRadioButton();
		this.jRadioButtonBytesType2 = new JRadioButton();

		this.jButtonAceptar = new CBAceptar(this.mediador, "DetallePaquetes");
		this.jButtonCancelar = new JButton();

		setDefaultCloseOperation(2);

		this.jPanelNVent.setBorder(BorderFactory.createTitledBorder("Distribución de árboles de paquetes"));

		this.jLabelNVent1.setText("Filas de paquetes:");
		this.jLabelNVent2.setText("Columnas de paquetes:");

		GroupLayout jPanelLayout1 = new GroupLayout(this.jPanelNVent);
		this.jPanelNVent.setLayout(jPanelLayout1);

		jPanelLayout1.setHorizontalGroup(
			jPanelLayout1.createParallelGroup(1)
				.add(jPanelLayout1.createSequentialGroup()
				.addContainerGap()
				.add(jPanelLayout1.createParallelGroup(1)
				.add(this.jLabelNVent2)
				.add(this.jLabelNVent1))
				.addPreferredGap(0)
				.add(jPanelLayout1.createParallelGroup(1, false)
				.add(this.jTextFieldNVent1, -2, 165, -2)
				.add(this.jTextFieldNVent2))
				.addContainerGap(-1, 32767)));

		jPanelLayout1.setVerticalGroup(
			jPanelLayout1.createParallelGroup(1)
				.add(jPanelLayout1.createSequentialGroup()
				.addContainerGap()
				.add(jPanelLayout1.createParallelGroup(2)
				.add(this.jLabelNVent1)
				.add(this.jTextFieldNVent1, -2, -1, -2))
				.addPreferredGap(0, 19, 32767)
				.add(jPanelLayout1.createParallelGroup(2)
				.add(this.jLabelNVent2)
				.add(this.jTextFieldNVent2, -2, -1, -2))
				.add(20, 20, 20)));

		this.jPanelNBytes.setBorder(BorderFactory.createTitledBorder("Cantidad de bytes mostrados"));

		this.jLabelNBytes.setText(" bytes");
		this.jTextFieldNBytes.setHorizontalAlignment(JLabel.CENTER);

		this.buttonGroupNBytes.add(this.jRadioButtonNBytes1);
		this.jRadioButtonNBytes1.setMnemonic(1);
		this.jRadioButtonNBytes1.setText("Primeros ");
		this.jRadioButtonNBytes1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		this.jRadioButtonNBytes1.setMargin(new Insets(0, 0, 0, 0));
		this.buttonGroupNBytes.add(this.jRadioButtonNBytes2);
		this.jRadioButtonNBytes2.setMnemonic(2);
		this.jRadioButtonNBytes2.setText("Fichero completo");
		this.jRadioButtonNBytes2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		this.jRadioButtonNBytes2.setMargin(new Insets(0, 0, 0, 0));

		GroupLayout jPanelLayout2 = new GroupLayout(this.jPanelNBytes);
		this.jPanelNBytes.setLayout(jPanelLayout2);
		jPanelLayout2.setHorizontalGroup(
			jPanelLayout2.createParallelGroup(1)
				.add(jPanelLayout2.createSequentialGroup()
				.add(26, 26, 26)
				.add(jPanelLayout2.createParallelGroup(1)
				.add(jPanelLayout2.createSequentialGroup()
				.add(this.jRadioButtonNBytes1)
				.add(this.jTextFieldNBytes)
				.add(this.jLabelNBytes))
				.add(this.jRadioButtonNBytes2))
				.addContainerGap(36, 32767)));

		jPanelLayout2.setVerticalGroup(
			jPanelLayout2.createParallelGroup(1)
				.add(jPanelLayout2.createSequentialGroup()
				.addContainerGap()
				.add(jPanelLayout2.createParallelGroup(1)
				.add(this.jRadioButtonNBytes1)
				.add(this.jTextFieldNBytes)
				.add(this.jLabelNBytes))
				.addPreferredGap(0)
				.add(this.jRadioButtonNBytes2)
				.addContainerGap(-1, 32767)));

		this.jPanelBytesType.setBorder(BorderFactory.createTitledBorder("Tipo representación bytes"));

		this.buttonGroupBytesType.add(this.jRadioButtonBytesType1);
		this.jRadioButtonBytesType1.setMnemonic(3);
		this.jRadioButtonBytesType1.setText("Hexadecimal");
		this.jRadioButtonBytesType1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		this.jRadioButtonBytesType1.setMargin(new Insets(0, 0, 0, 0));
		this.buttonGroupBytesType.add(this.jRadioButtonBytesType2);
		this.jRadioButtonBytesType2.setMnemonic(4);
		this.jRadioButtonBytesType2.setText("Decimal");
		this.jRadioButtonBytesType2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		this.jRadioButtonBytesType2.setMargin(new Insets(0, 0, 0, 0));

		GroupLayout jPanelLayout3 = new GroupLayout(this.jPanelBytesType);
		this.jPanelBytesType.setLayout(jPanelLayout3);
		jPanelLayout3.setHorizontalGroup(
			jPanelLayout3.createParallelGroup(1)
				.add(jPanelLayout3.createSequentialGroup()
				.add(26, 26, 26)
				.add(jPanelLayout3.createParallelGroup(1)
				.add(this.jRadioButtonBytesType1)
				.add(this.jRadioButtonBytesType2))
				.addContainerGap(36, 32767)));

		jPanelLayout3.setVerticalGroup(
				jPanelLayout3.createParallelGroup(1)
				.add(jPanelLayout3.createSequentialGroup()
				.addContainerGap()
				.add(this.jRadioButtonBytesType1)
				.addPreferredGap(0)
				.add(this.jRadioButtonBytesType2)
				.addContainerGap(-1, 32767)));

		this.jButtonCancelar.setText("Cancelar");
		this.jButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setAutocreateGaps(true);
		layout.setAutocreateContainerGaps(true);
		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.add(layout.createParallelGroup(1)
				.add(this.jPanelNVent)
				.add(this.jPanelNBytes)
				.add(this.jPanelBytesType)
				.add(layout.createSequentialGroup()
				.add((CBAceptar) jButtonAceptar)
				.add(this.jButtonCancelar))));

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.add(this.jPanelNVent)
				.add(this.jPanelNBytes)
				.add(this.jPanelBytesType)
				.add(layout.createParallelGroup(1)
				.add((CBAceptar) this.jButtonAceptar)
				.add(this.jButtonCancelar)));

		pack();
	}
}
