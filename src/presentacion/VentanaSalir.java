
package presentacion;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import presentacion.ventanaMenuSniffer.MenuSniffer;
/** 
 * Clase VentanaSalir. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2
*/
// Referenced classes of package presentacion:
//            Mediador

public class VentanaSalir extends JDialog
{

    public VentanaSalir(Mediador aux)
    {
        super(MenuSniffer.getFrames()[0], "Salir", true);
        venpadre = aux;
        confirmarSalida();
        setResizable(false);
    }

    public void confirmarSalida()
    {
        int res = JOptionPane.showConfirmDialog(this, "\277Est\341 seguro de que desea Salir?", "Sniffer III", 0);
        String respuesta = null;
        if(res == 0)
        {
            respuesta = "Si";
            venpadre.grabarProperties();
            System.out.print("--- Se cierra la aplicaci\u00f3n. ---\n");
            System.exit(1);
        } else
        {
            respuesta = "No";
            System.out.print("No sale a\u00fan la aplicaci\u00f3n.\n");
            dispose();
        }
    }

    Mediador venpadre;
}
