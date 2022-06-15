package dominio.preferences;

import java.io.Serializable;

public class preferencesBeanDetallePaquete implements Serializable {

	private static final long serialVersionUID = -8436686830545248455L;

	public preferencesBeanDetallePaquete() {
		setDefaultSettings();
	}

	public void setDefaultSettings() {
		this.rows = 1;
		this.columns = 3;
		this.totalBytes = false;
		this.bytes = 200;
		this.bytesHex = true;
		;
	}

	public String getRows() {
		return "" + rows;
	}

	public void setRows(String rows) {
		this.rows = Integer.valueOf(rows);
	}

	public String getColumns() {
		return "" + columns;
	}

	public void setColumns(String columns) {
		this.columns = Integer.valueOf(columns);
	}

	public boolean isTotalBytes() {
		return totalBytes;
	}

	public void setTotalBytes(boolean totalBytes) {
		this.totalBytes = totalBytes;
	}

	public String getBytes() {
		return "" + bytes;
	}

	public void setBytes(String bytes) {
		this.bytes = Integer.valueOf(bytes);
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
