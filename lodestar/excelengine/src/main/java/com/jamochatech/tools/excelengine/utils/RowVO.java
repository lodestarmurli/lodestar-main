package com.jamochatech.tools.excelengine.utils;

import java.util.HashMap;
import java.util.Map;

public class RowVO
{

	private int					rowIndex;
	private String 				sheetName;

	private Map<String, CellVO>	cells	= new HashMap<String, CellVO>();

	public int getRowIndex()
	{
		return rowIndex;
	}

	public void setRowIndex(int rowIndex)
	{
		this.rowIndex = rowIndex;
	}

	public void addCell(CellVO cell)
	{
		if (cell != null)
			cells.put(cell.getHeader().trim().toLowerCase(), cell);
	}

	public CellVO getCellVOByHeading(String header)
	{
		if(header == null)
			return null;
		
		return cells.get(header.trim().toLowerCase());
	}

	
	public String getSheetName()
	{
		return sheetName;
	}

	public void setSheetName(String sheetName)
	{
		this.sheetName = sheetName;
	}

	@Override
	public String toString()
	{
		return "RowVO [rowIndex=" + rowIndex + ", cells=" + cells + "]";
	}

	public CellVO getCellVOForMultiLookupColumn(String xlColumnName)
	{
		String[] xlColumns = xlColumnName.split(",");
		String value = "";
		CellVO cell = new CellVO();
		for (String colHeader : xlColumns)
		{
			cell = getCellVOByHeading(colHeader);
			
			if(cell != null && cell.getValue() != null)
			{
				if (value != null && (!value.isEmpty()))
				{
					value = value + "|" +cell.getValue();
				}
				else
				{
					value = "" + cell.getValue();
				}
			}
		}
		
		cell.setValue(value);
		
		return cell;
	}


}
