package com.jamochatech.tools.excelengine.utils;

import java.util.HashMap;
import java.util.Map;

public class ColumnVO
{
	private int columnIndex;;
	private String sheetName;
	private String columnHeader;
	
	private Map<Integer, CellVO>	cells	= new HashMap<Integer, CellVO>();
	
	public void addCell(CellVO cell)
	{
		if (cell != null)
			cells.put(cell.getRowIndex(), cell);
	}

	public CellVO getCellVOByRowIndex(int rowIndex)
	{
		return cells.get(rowIndex);
	}

	public int getColumnIndex()
	{
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex)
	{
		this.columnIndex = columnIndex;
	}

	public String getSheetName()
	{
		return sheetName;
	}

	public void setSheetName(String sheetName)
	{
		this.sheetName = sheetName;
	}

	public String getColumnHeader()
	{
		return columnHeader;
	}

	public void setColumnHeader(String columnHeader)
	{
		this.columnHeader = columnHeader;
	}

	@Override
	public String toString()
	{
		return "ColumnVO [columnIndex=" + columnIndex + ", sheetName=" + sheetName + ", columnHeader=" + columnHeader + ", cells=" + cells + "]";
	}
	
}
