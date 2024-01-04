package com.jamochatech.tools.excelengine.utils;

public class CellVO
{

	private int		columnIndex;
	private int		rowIndex;
	private int		type;
	private Object	value;
	private String	header;
	private boolean	isDate	= false;

	public int getColumnIndex()
	{
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex)
	{
		this.columnIndex = columnIndex;
	}

	public int getRowIndex()
	{
		return rowIndex;
	}

	public void setRowIndex(int rowIndex)
	{
		this.rowIndex = rowIndex;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public Object getValue()
	{
		return value;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public String getHeader()
	{
		return header;
	}

	public void setHeader(String header)
	{
		this.header = header;
	}

	public boolean isDate()
	{
		return isDate;
	}

	public void setDate(boolean isDate)
	{
		this.isDate = isDate;
	}

	@Override
	public String toString()
	{
		return "CellVO [columnIndex=" + columnIndex + ", rowIndex=" + rowIndex + ", type=" + type + ", value=" + value + ", header=" + header + ", isDate=" + isDate
				+ "]";
	}

}
