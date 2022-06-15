
package presentacion.comandos;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import presentacion.Mediador;

/**
 * Clase CJMFinInsert.
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,
 * @version 1.2
 */
// Referenced classes of package presentacion.comandos:
//            Comando

public class CJMFinInsert extends JMenuItem implements Comando {

	private static final long serialVersionUID = -8239746133940924825L;
	

	public CJMFinInsert(Mediador mediador) {
		super("Finalizar Insercion", 77);
		KeyStroke ctrlM = KeyStroke.getKeyStroke(77, 2);
		setAccelerator(ctrlM);
		this.mediador = mediador;
		addActionListener(mediador);
	}

	public void ejecutar() {
		mediador.freePantallaCaptura();
	}

	private Mediador mediador;
}
