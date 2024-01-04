package com.jamochatech.tools.excelengine.utils;

import java.util.HashMap;
import java.util.Map;

public class ColumnCollection
{
	private Map<String, Map<Integer, ColumnVO>>	columnCollection	= new HashMap<String, Map<Integer, ColumnVO>>();

	private static final ColumnCollection		INSTANCE			= new ColumnCollection();

	private ColumnCollection()
	{

	}

	public static ColumnCollection getInstance()
	{
		return INSTANCE;
	}

	public void addColumn(ColumnVO column)
	{
		if (column == null)
			return;

		Map<Integer, ColumnVO> columns = columnCollection.get(column.getSheetName());
		if (columns == null)
			columns = new HashMap<Integer, ColumnVO>();

		columns.put(column.getColumnIndex(), column);
		columnCollection.put(column.getSheetName(), columns);
	}

	public Map<Integer, ColumnVO> getColumns(String sheetName)
	{
		return columnCollection.get(sheetName);
	}

	public ColumnVO getColumn(String sheetName, int columnIndex)
	{
		Map<Integer, ColumnVO> columns = columnCollection.get(sheetName);
		if (columns == null)
			return null;

		return columns.get(columnIndex);
	}
}
