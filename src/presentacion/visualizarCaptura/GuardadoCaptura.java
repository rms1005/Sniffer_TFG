package presentacion.visualizarCaptura;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import presentacion.propiedadesVentana.CentrarVentana;

public class GuardadoCaptura extends JFrame {

	private static final long serialVersionUID = -6704138973878341288L;

	private presentacion.capturandoDumper.Fcaptura fcapD;
	private presentacion.capturando.Fcaptura fcap;

	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;

	public GuardadoCaptura(presentacion.capturandoDumper.Fcaptura fcap) {
		initComponents();
		this.fcapD = fcap;
		this.fcap = null;
		setResizable(false);
		new CentrarVentana(this);
	}

	public GuardadoCaptura(presentacion.capturando.Fcaptura fcap) {
		initComponents();
		this.fcapD = null;
		this.fcap = fcap;
		setResizable(false);
		new CentrarVentana(this);
	}

	private void initComponents() {
		this.jButton1 = new JButton();
		this.jButton2 = new JButton();
		this.jButton3 = new JButton();

		getContentPane().setLayout(new GridLayout(3, 1));

		setDefaultCloseOperation(2);
		setTitle("Guardar captura...");

		this.jButton1.setText("Archivo Pcap");
		this.jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});

		this.jButton2.setText("Archivo XML");
		this.jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (fcapD == null)
					fcap.salir();
				else
					fcapD.salir();
				dispose();
			}
		});

		this.jButton3.setText("No guardar");
		this.jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});

		// Mostrar elementos
		getContentPane().add(jButton1);
		getContentPane().add(jButton2);
		getContentPane().add(jButton3);
		pack();
	}
}
