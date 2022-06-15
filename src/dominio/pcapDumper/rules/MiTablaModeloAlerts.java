
package dominio.pcapDumper.rules;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import net.sourceforge.jpcap.net.Packet;

/**
 * Clase MiTablaModeloAlerts.
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,
 * @version 1.2
 */
// Referenced classes of package dominio.pcap.rules:
//            Rule

class MiTablaModeloAlerts extends AbstractTableModel {

	private static final long serialVersionUID = 463484494973731072L;
	
	
	public MiTablaModeloAlerts() {
		tableColumns = new Vector<String>();
		tableData = new Vector<Vector<String>>();
		tableColumns.addElement(new String("Paquete"));
		tableColumns.addElement(new String("Timeval"));
		tableColumns.addElement(new String("Msg"));
		tableColumns.addElement(new String("IP Origen"));
		tableColumns.addElement(new String("IP Destino"));
		tableColumns.addElement(new String("Puerto Origen"));
		tableColumns.addElement(new String("Puerto Destino"));
		tableColumns.addElement(new String("Prioridad"));
		tableColumns.addElement(new String("Packet Dsc"));
	}

	public int getColumnCount() {
		return tableColumns.size();
	}

	public int getRowCount() {
		return tableData.size();
	}

	public String getColumnName(int col) {
		return (String) tableColumns.elementAt(col);
	}

	public Object getValueAt(int row, int col) {
		Vector<String> rowVector = (Vector<String>) tableData.elementAt(row);
		return rowVector.elementAt(col);
	}

	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public void addRow(Rule objetoRule, Packet paquete, int numeropaquete, String IpOrigen, String IpDestino,
			String portsrc, String portdest) {
		Vector<String> newRow = new Vector<String>();
		newRow.addElement(String.valueOf(numeropaquete));
		newRow.addElement(String.valueOf(paquete.getTimeval()));
		newRow.addElement(objetoRule.getMsg());
		newRow.addElement(IpOrigen);
		newRow.addElement(IpDestino);
		newRow.addElement(portsrc);
		newRow.addElement(portdest);
		newRow.addElement(objetoRule.getPriority());
		newRow.addElement(paquete.toString());
		tableData.addElement(newRow);
	}

	Vector<String> tableColumns;
	Vector<Vector<String>> tableData;
}
