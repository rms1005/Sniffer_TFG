
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;

/**
 * Clase CJMPreferenciasGenerar.
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,
 * @version 1.2
 */
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMPreferenciasGenerar extends JMenuItem implements Comando {

	private static final long serialVersionUID = 2528620663836361798L;
	

	public CJMPreferenciasGenerar(Mediador mediador) {
		super("Generar Script...", 83);
		KeyStroke ctrlB = KeyStroke.getKeyStroke(66, 2);
		setAccelerator(ctrlB);
		this.mediador = mediador;

		addActionListener(mediador);

	}

	public void ejecutar() {
		mediador.irAGenerarBat();
	}

	private Mediador mediador;
}
