package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presentacion.Mediador;

public class CJMPreferenciasDetallePaquetes extends JMenuItem implements Comando {
	
	private static final long serialVersionUID = 2505065998505288055L;

	
	public CJMPreferenciasDetallePaquetes(Mediador mediador) {
		super("Detalle paquetes", 80);
		KeyStroke ctrlP = KeyStroke.getKeyStroke(80, 2);
		setAccelerator(ctrlP);
		this.mediador = mediador;
		addActionListener(mediador);
	}

	@Override
	public void ejecutar() {
		mediador.irADetallePaquete();
	}

	private Mediador mediador;

}
