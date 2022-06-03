
package presentacion.visualizarCaptura;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
/** 
 * Clase MiTablaModelo. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
class MiTablaModelo extends AbstractTableModel
{

   
	public MiTablaModelo()
    {
        tableColumns = new Vector();
        tableData = new Vector();
        tableColumns.addElement(new String("Num"));
        tableColumns.addElement(new String("Time"));
        tableColumns.addElement(new String("Size"));
        tableColumns.addElement(new String("MAC Src"));
        tableColumns.addElement(new String("MAC Dest"));
        tableColumns.addElement(new String("Frame"));
        tableColumns.addElement(new String("Protocol"));
        tableColumns.addElement(new String("IP Src"));
        tableColumns.addElement(new String("IP Dest"));
        tableColumns.addElement(new String("Port Src"));
        tableColumns.addElement(new String("Port Dest"));
        tableColumns.addElement(new String("SEQ"));
        tableColumns.addElement(new String("ACK"));
        tableColumns.addElement(new String("Length"));
    }

    public int getColumnCount()
    {
        return tableColumns.size();
    }

    public int getRowCount()
    {
        return tableData.size();
    }

    public String getColumnName(int col)
    {
        return (String)tableColumns.elementAt(col);
    }

    public Object getValueAt(int row, int col)
    {
        Vector rowVector = (Vector)tableData.elementAt(row);
        return rowVector.elementAt(col);
    }

    public Class getColumnClass(int c)
    {
        return getValueAt(0, c).getClass();
    }

    public void newVector()
    {
        newRow = new Vector();
    }

    public void addRow(String macsource, String macdest, String frame, String protocol, String ipsrc, String ipdest, String portsrc, String portdest, String seq, String ack, String length)
    {
        newRow.addElement(macsource);
        newRow.addElement(macdest);
        try {
        	newRow.addElement(Integer.valueOf(frame));
        } catch(Exception e) {
        	newRow.addElement(frame);
        }
        newRow.addElement(protocol);
        newRow.addElement(ipsrc);
        newRow.addElement(ipdest);
        try {
        	newRow.addElement(Integer.valueOf(portsrc));
        } catch(Exception e) {
        	newRow.addElement(portsrc);
        }
        try {
        	newRow.addElement(Integer.valueOf(portdest));
        } catch(Exception e) {
        	newRow.addElement(portdest);
        }
        try {
        	newRow.addElement(Long.parseLong(seq, 10));
        } catch(Exception e) {
        	newRow.addElement(seq);
        }
        try {
        	newRow.addElement(Long.parseLong(ack, 10));
        } catch(Exception e) {
        	newRow.addElement(ack);
        }
        try {
        	newRow.addElement(Integer.valueOf(length));
        } catch(Exception e) {
        	newRow.addElement(length);
        }
    }

    public void addTimeRow(String numeropaquete, String time, String size)
    {
        newRow.addElement(Integer.valueOf(numeropaquete));
        newRow.addElement(time);
        newRow.addElement(Integer.valueOf(size));
    }

    public void addPacket()
    {
        tableData.addElement(newRow);
         }
        
        public void clearAll()
    {
        tableData.clear();
    }

    Vector tableColumns;
    Vector tableData;
    public Vector newRow;
}
