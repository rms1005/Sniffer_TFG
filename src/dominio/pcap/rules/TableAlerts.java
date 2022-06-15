
package dominio.pcap.rules;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import net.sourceforge.jpcap.net.Packet;

/**
 * Clase TableAlerts.
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,
 * @version 1.2
 */
// Referenced classes of package dominio.pcap.rules:
//            MiTablaModeloAlerts, Rule

public class TableAlerts extends JFrame {

	private static final long serialVersionUID = 6148114121619041606L;
	
	
	public TableAlerts() {
		super("TypeAlert");
		column = null;
		modelo = new MiTablaModeloAlerts();
		table = new JTable(modelo);
		table.setPreferredScrollableViewportSize(new Dimension(950, 50));
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, "Center");
		pack();
	}

	public void DatosTablaAlerts(Rule objetoRule, int numeropaquete, Packet paquete, String IpOrigen, String IpDestino,
			String portsrc, String portdest) {
		modelo.addRow(objetoRule, paquete, numeropaquete, IpOrigen, IpDestino, portsrc, portdest);
	}

	public JTableHeader Cabezera() {
		return table.getTableHeader();
	}

	public JTable Tabla() {
		return table;
	}

	private MiTablaModeloAlerts modelo;
	private JTable table;
	private TableColumn column;
}
