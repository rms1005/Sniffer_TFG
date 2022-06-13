package dominio.preferences;

import java.io.Serializable;

public class preferencesBeanDetallePaquete implements Serializable {

	private static final long serialVersionUID = -8436686830545248455L;
	
	public preferencesBeanDetallePaquete()
	{
		setDefaultSettings();
	}
	
	public void setDefaultSettings()
    {
        this.rows = 0;
        this.columns = 0;
        this.totalBytes = false;
        this.bytes = 200;
        this.bytesHex = true;;
    }
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public boolean isTotalBytes() {
		return totalBytes;
	}

	public void setTotalBytes(boolean totalBytes) {
		this.totalBytes = totalBytes;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public boolean isBytesHex() {
		return bytesHex;
	}

	public void setBytesHex(boolean bytesHex) {
		this.bytesHex = bytesHex;
	}


	private int rows;
	private int columns;
	private boolean totalBytes;
	private int bytes;
	private boolean bytesHex;

}
