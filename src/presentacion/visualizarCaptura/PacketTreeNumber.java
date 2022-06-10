package presentacion.visualizarCaptura;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.Mediador;
import presentacion.comandos.CBAbrirCarpeta;
import presentacion.comandos.Comando;
import presentacion.preferencias.PreferenciasConfiguracion;
import presentacion.propiedadesVentana.CentrarVentana;

public class PacketTreeNumber extends JFrame {

	private static final long serialVersionUID = -5605099331734601684L;
	
	private VisualizarCaptura vc;

	private JButton jButton1;
	private JButton jButton2;

	private JPanel jPanel1;
	private JPanel jPanel2;

	private JLabel jLabel1;
	private JLabel jLabel2;

	private JTextField jTextField1;
	private JTextField jTextField2;

	public PacketTreeNumber(VisualizarCaptura vc) {
		this.vc = vc;
		initComponents();
		setResizable(false);
		new CentrarVentana(this);
	}

	private void initComponents() {

		this.jPanel1 = new JPanel();
		this.jPanel2 = new JPanel();

		this.jLabel1 = new JLabel();
		this.jLabel2 = new JLabel();

		this.jTextField1 = new JTextField();
		this.jTextField2 = new JTextField();

		this.jButton1 = new JButton();
		this.jButton2 = new JButton();

		getContentPane().setLayout(new GridLayout(3, 2));
		
		setDefaultCloseOperation(2);
		setTitle("Distribución paquetes");

		this.jLabel1.setText("Vertical:");
		this.jLabel2.setText("Horizontal:");
		
		this.jTextField1.setText(""+vc.getNumVertical());
		this.jTextField2.setText(""+vc.getNumHorizontal());

		this.jButton1.setText("Aceptar");
		this.jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int minV = Integer.parseInt(jTextField1.getText());
				int minH = Integer.parseInt(jTextField2.getText());
				if(minV > vc.getNumVertical())
					vc.setNumVertical(minV);
				if(minH > vc.getNumHorizontal())
					vc.setNumHorizontal(minH);
				dispose();
			}
		});
		
		this.jButton2.setText("Cancelar");
		this.jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		
		this.jLabel1.setHorizontalAlignment(JLabel.CENTER);
		this.jLabel2.setHorizontalAlignment(JLabel.CENTER);

		this.jTextField1.setHorizontalAlignment(JLabel.CENTER);
		this.jTextField2.setHorizontalAlignment(JLabel.CENTER);
		
		// Mostrar elementos
		getContentPane().add(this.jLabel1);
		getContentPane().add(this.jTextField1);
		getContentPane().add(this.jLabel2);
		getContentPane().add(this.jTextField2);
		getContentPane().add(this.jPanel1);
		this.jPanel2.add(jButton1);
		this.jPanel2.add(jButton2);
		getContentPane().add(this.jPanel2);
		pack();
	}
}
