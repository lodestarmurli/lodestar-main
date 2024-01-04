package com.jamochatech.tools.excelengine.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jamochatech.tools.excelengine.dao.DBManager;
import com.jamochatech.tools.excelengine.dao.DBOperations;

public class ExcelUtil
{

	public static final Logger	OUT	= LoggerFactory.getLogger(ExcelUtil.class.getName());

	public static void nullValidation(int rowIndex, String sheetName, int columnIndex, String xlColumnName, String allowNull, String allowDuplicate,
			boolean isNullCell) throws Exception
	{
		if (!"true".equalsIgnoreCase(allowNull) || !"true".equalsIgnoreCase(allowDuplicate))
		{
			if (isNullCell)
			{
				OUT.error("No content found. Location in excel : [Sheet : {}, Column : {}, Row : {}, Col : {}]", sheetName, xlColumnName, rowIndex, columnIndex);
				throw new ExcelEngineValidationException("Null Validation failed.");
			}
		}
	}

	public static void duplicateValidation(int rowIndex, String sheetName, String tableName, int columnIndex, String tableColumnName, String xlColumnName,
			String allowDuplicate, CellVO cell) throws Exception
	{
		if (!"true".equalsIgnoreCase(allowDuplicate))
		{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(Constants.TABLE_NAME, tableName);
			paramMap.put(Constants.TABLE_COLUMN_NAME, tableColumnName);
			paramMap.put(Constants.VALUE, cell.getValue());

			Long count = DBManager.getInstance().getSumOfCount(DBOperations.CHECK_DUPLICATES, paramMap);
			if (count > 0)
			{
				OUT.error("Value \'{}\' aleratdy exist in table \'{}\' tablecolumn \'{}\' Location in excel : [Sheet : {}, Column : {}, Row : {}, Col : {}] ",
						cell.getValue(), tableName, tableColumnName, sheetName, xlColumnName, rowIndex, columnIndex);
				throw new ExcelEngineValidationException("Duplicate Validation failed.");
			}
		}
	}

	public static void maxLengthValidation(int rowIndex, String sheetName, int columnIndex, String xlColumnName, String dataType, int maxLength, CellVO cell,
			boolean isNullCell) throws Exception
	{
		if (Constants.DATA_TYPE_VARCHAR.equalsIgnoreCase(dataType) && maxLength > 0)
		{
			if (!isNullCell)
			{
				String cellVal = cell.getValue().toString();
				if (cellVal.length() > maxLength)
				{
					OUT.error("Value length is more than \'{}\'. Location in excel : [Sheet : {}, Column : {}, Row : {}, Col : {}] ", maxLength, sheetName,
							xlColumnName, rowIndex, columnIndex);
					throw new ExcelEngineValidationException("MAX Length Validation failed.");
				}
			}
		}
	}

	public static void dataTypeValidation(int rowIndex, String sheetName, int columnIndex, String xlColumnName, String dataType, CellVO cell, boolean isNullCell,
			String lookupTable) throws Exception
	{
		if ((lookupTable == null || lookupTable.trim().isEmpty()) && !isNullCell)
		{
			boolean isDataTypeInvalid = false;
			if (Constants.DATA_TYPE_DATE.equalsIgnoreCase(dataType) && !cell.isDate())
			{
				isDataTypeInvalid = true;
			}
			else if (Constants.DATA_TYPE_NUMERIC.equalsIgnoreCase(dataType) && cell.getType() != Cell.CELL_TYPE_NUMERIC)
			{
				isDataTypeInvalid = true;
			}
			// else if (Constants.DATA_TYPE_VARCHAR.equalsIgnoreCase(dataType) && cell.getType() != Cell.CELL_TYPE_STRING)
			// {
			// isDataTypeInvalid = true;
			// }
			if (isDataTypeInvalid)
			{
				OUT.error("Invalid data type. Location in excel : [Sheet : {}, Column : {}, Row : {}, Col : {}] ", sheetName, xlColumnName, rowIndex, columnIndex);
				throw new ExcelEngineValidationException("Data Type Validation failed.");
			}
		}
	}

	public static RowVO getRow(String sheetName, int rowIndex, List<String> headerRow, Sheet xlSheet, FormulaEvaluator formulaEvaluator)
	{
		RowVO rowVO = new RowVO();
		rowVO.setRowIndex(rowIndex);
		rowVO.setSheetName(sheetName);
		Row row = xlSheet.getRow(rowIndex);
		if (row == null)
			return null;
		Cell cell;
		CellVO cellVO;
		for (int i = 0; i < headerRow.size(); i++)
		{
			cell = row.getCell(i);
			if (cell == null)
				continue;
			cellVO = ExcelUtil.getCellValue(cell, formulaEvaluator);
			cellVO.setHeader(headerRow.get(i));
			rowVO.addCell(cellVO);
		}

		return rowVO;
	}

	public static List<String> getHeaderRow(int firstRow, Sheet xlSheet, FormulaEvaluator formulaEvaluator)
	{
		List<String> headers = new ArrayList<String>();

		Row row = xlSheet.getRow(firstRow);
		String headerName = "";
		String cellValue;
		for (Cell cell : row)
		{
			cellValue = ExcelUtil.getCellValueAsString(cell, formulaEvaluator);
			headerName = !cellValue.trim().isEmpty() ? cellValue : headerName;
			headers.add(headerName);
		}
		return headers;
	}

	public static String getCellValueAsString(Cell cell, FormulaEvaluator formulaEvaluator)
	{
		formulaEvaluator.evaluateInCell(cell);

		String value = "";

		switch (cell.getCellType())
		{
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell))
				{
					value = "" + cell.getDateCellValue();
				}
				else
				{
					value = "" + cell.getNumericCellValue();
				}
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = "" + cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_ERROR:
				value = "" + cell.getErrorCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				break;

			default:
				break;
		}
		return value;
	}

	public static CellVO getCellValue(Cell cell, FormulaEvaluator formulaEvaluator)
	{
		formulaEvaluator.evaluateInCell(cell);
		CellVO cellVO = new CellVO();
		cellVO.setColumnIndex(cell.getColumnIndex());
		cellVO.setRowIndex(cell.getRowIndex());
		cellVO.setType(cell.getCellType());
		switch (cell.getCellType())
		{
			case Cell.CELL_TYPE_STRING:
				cellVO.setValue(cell.getStringCellValue().trim());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell))
				{
					cellVO.setValue(cell.getDateCellValue());
					cellVO.setDate(true);
				}
				else
				{
					cellVO.setValue(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellVO.setValue(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_ERROR:
				cellVO.setValue(cell.getErrorCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				break;

			default:
				break;
		}
		return cellVO;
	}

	public static void populateColumnCollection(String sheetName, Sheet xlSheet, int firstRow, int lastRow, List<String> headerRow, int processFromColumnIndex,
			int processToColumnIndex, FormulaEvaluator formulaEvaluator)
	{
		Row xlRow;

		for (int rowIndex = firstRow + 1; rowIndex <= lastRow; rowIndex++)
		{
			// row = ExcelUtil.getRow(sheetName, rowIndex, headerRow, xlSheet);
			xlRow = xlSheet.getRow(rowIndex);
			if (xlRow == null)
				continue;

			for (int columnIndex = processFromColumnIndex; columnIndex <= processToColumnIndex; columnIndex++)
			{
				ColumnVO columnVO = ColumnCollection.getInstance().getColumn(sheetName, columnIndex);
				if (columnVO == null)
				{
					columnVO = new ColumnVO();
					columnVO.setColumnHeader(headerRow.get(columnIndex));
					columnVO.setColumnIndex(columnIndex);
					columnVO.setSheetName(sheetName);
				}
				columnVO.addCell(getCellValue(xlRow.getCell(columnIndex), formulaEvaluator));

				ColumnCollection.getInstance().addColumn(columnVO);
			}
		}
	}

	public static boolean isNullOrEmpty(String text)
	{
		return (text == null || text.isEmpty());
	}
}
