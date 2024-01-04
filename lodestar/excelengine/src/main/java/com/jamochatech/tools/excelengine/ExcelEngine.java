package com.jamochatech.tools.excelengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jamochatech.tools.excelengine.dao.DBManager;
import com.jamochatech.tools.excelengine.dao.DBOperations;
import com.jamochatech.tools.excelengine.utils.CellVO;
import com.jamochatech.tools.excelengine.utils.ColumnCollection;
import com.jamochatech.tools.excelengine.utils.ColumnVO;
import com.jamochatech.tools.excelengine.utils.Constants;
import com.jamochatech.tools.excelengine.utils.ExcelEngineValidationException;
import com.jamochatech.tools.excelengine.utils.ExcelUtil;
import com.jamochatech.tools.excelengine.utils.RowVO;

public class ExcelEngine
{

	public static final Logger	OUT			= LoggerFactory.getLogger(ExcelEngine.class.getName());

	private static ExcelEngine	INSTANCE	= new ExcelEngine();
	
	private static FormulaEvaluator formulaEvaluator;

	private ExcelEngine()
	{
		// Do Nothing
	}

	public static ExcelEngine getInstance()
	{
		return INSTANCE;
	}

	private static void parseExcel(String excelFilePath, String parseRuleFilePath)
	{
		Workbook wb = null;
		BufferedReader reader = null;
		InputStream fis = null;
		try
		{
			wb = WorkbookFactory.create(new File(excelFilePath));
			
			formulaEvaluator =  wb.getCreationHelper().createFormulaEvaluator();
			fis = new FileInputStream(new File(parseRuleFilePath));

			reader = new BufferedReader(new InputStreamReader(fis));
			String line = "";
			StringBuilder builder = new StringBuilder("");
			while ((line = reader.readLine()) != null)
			{
				builder.append(line);
			}

			JSONObject parsingRules = new JSONObject(builder.toString());

			JSONArray sheets = parsingRules.getJSONArray(Constants.SHEETS);
			JSONObject sheet;
			JSONArray tables;
			String sheetName;
			Sheet xlSheet;
			int firstRow;
			int lastRow;
			List<String> headerRow;

			String processColums;
			String processFromColumn;
			String processToColumn;
			int startingRow;
			RowVO row;
			JSONArray col_tables;
			int processFromColumnIndex = -1;
			int processToColumnIndex = -1;
			Map<String, Object> retainedIdsMap;
			Map<Integer, ColumnVO> columnsMap;
			for (int i = 0; i < sheets.length(); i++)
			{
				sheet = sheets.getJSONObject(i);
				tables = sheet.optJSONArray(Constants.TABLES);
				sheetName = sheet.optString(Constants.SHEET_NAME);
				processColums = sheet.optString(Constants.PROCESS_COLUMNS, Constants.FALSE);
				processFromColumn = sheet.optString(Constants.PROCESS_FROM_COLUMN);
				processToColumn = sheet.optString(Constants.PROCESS_TO_COLUMN);
				startingRow = sheet.optInt(Constants.STARTING_ROW);
				retainedIdsMap = new HashMap<>();

				xlSheet = wb.getSheet(sheetName);

				firstRow = xlSheet.getFirstRowNum();
				lastRow = xlSheet.getLastRowNum();

				headerRow = ExcelUtil.getHeaderRow(firstRow, xlSheet, formulaEvaluator);

				if (Constants.TRUE.equalsIgnoreCase(processColums))
				{
					processFromColumnIndex = CellReference.convertColStringToIndex(processFromColumn);
					processToColumnIndex = CellReference.convertColStringToIndex(processToColumn);

					ExcelUtil.populateColumnCollection(sheetName, xlSheet, firstRow, lastRow, headerRow, processFromColumnIndex, processToColumnIndex, formulaEvaluator);
					col_tables = sheet.optJSONArray(Constants.COL_TABLES);

					if (col_tables == null || col_tables.length() <= 0)
						continue;

					columnsMap = ColumnCollection.getInstance().getColumns(sheetName);

					if (columnsMap == null)
						continue;
					for (int columnIndex = processFromColumnIndex; columnIndex <= processToColumnIndex; columnIndex++)
					{
						ColumnVO columnVO = columnsMap.get(columnIndex);
						if (columnVO == null)
							continue;

						processRow(col_tables, null, columnVO, true, -1, processFromColumnIndex, processToColumnIndex, retainedIdsMap);
					}

				}

				if (tables == null)
					continue;

				if (startingRow > 0)
					firstRow = startingRow - 2;

				for (int rowIndex = firstRow + 1; rowIndex <= lastRow; rowIndex++)
				{
					row = ExcelUtil.getRow(sheetName, rowIndex, headerRow, xlSheet, formulaEvaluator);
					if (row == null)
						continue;

					processRow(tables, row, null, false, rowIndex, processFromColumnIndex, processToColumnIndex, retainedIdsMap);
				}
			}

			// int numberOfSheets = wb.getNumberOfSheets();
		}
		catch (Exception e)
		{
			OUT.error("Exception : ", e);
		}
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (IOException e)
				{
				}
			}
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (Exception e)
				{
				}
			}

			if (fis != null)
			{
				try
				{
					fis.close();
				}
				catch (Exception e)
				{
				}
			}

			if (wb != null)
			{
				try
				{
					wb.close();
				}
				catch (Exception e)
				{
				}
			}
		}
	}

	private static void processRow(JSONArray tables, RowVO row, ColumnVO columnVO, boolean isColTable, int rowIndex, int processFromColumnIndex,
			int processToColumnIndex, Map<String, Object> retainedIdsMap)
	{
		String sheetName;
		JSONObject table;
		String tableName;
		Map<String, Object> insertRow = new HashMap<String, Object>();
		List<String> columNames;
		List<Object> values;
		Map<String, Object> updateMap = new HashMap<String, Object>();
		Map<String, Object> conditionFields = new HashMap<String, Object>();
		Map<String, Object> updateFields = new HashMap<String, Object>();
		Set<String> uniqieColIDSet = new HashSet<String>();
		boolean isMatrixTable;
		if (isColTable)
		{
			sheetName = columnVO.getSheetName();
		}
		else
		{
			sheetName = row.getSheetName();
		}

		for (int tableIndex = 0; tableIndex < tables.length(); tableIndex++)
		{
			columNames = new ArrayList<String>();
			values = new ArrayList<Object>();
			table = tables.getJSONObject(tableIndex);
			tableName = table.optString(Constants.TABLE_NAME);
			isMatrixTable = Constants.TRUE.equalsIgnoreCase(table.optString(Constants.MATRIX_TABLE, Constants.FALSE));
			try
			{
				if (!isMatrixTable)
				{
					processTable(row, columnVO, isColTable, rowIndex, sheetName, table, tableName, insertRow, columNames, values, updateMap, conditionFields,
							updateFields, uniqieColIDSet, tableIndex, retainedIdsMap);
				}
				else
				{
					for (int columnIndex = processFromColumnIndex; columnIndex <= processToColumnIndex; columnIndex++)
					{
						columnVO = ColumnCollection.getInstance().getColumn(sheetName, columnIndex);
						processTable(row, columnVO, isColTable, rowIndex, sheetName, table, tableName, insertRow, columNames, values, updateMap, conditionFields,
								updateFields, uniqieColIDSet, tableIndex, retainedIdsMap);
					}
				}
			}
			catch (ExcelEngineValidationException e)
			{
			}
		}
	}

	private static void processTable(RowVO row, ColumnVO columnVO, boolean isColTable, int rowIndex, String sheetName, JSONObject table, String tableName,
			Map<String, Object> insertRow, List<String> columNames, List<Object> values, Map<String, Object> updateMap, Map<String, Object> conditionFields,
			Map<String, Object> updateFields, Set<String> uniqieColIDSet, int tableIndex, Map<String, Object> retainedIdsMap) throws ExcelEngineValidationException
	{
		JSONArray columns;
		String uniqueColumnIdentifier;
		String[] unqIdentifiers;
		String xlcolumnheader = table.optString(Constants.EXCEL_COLUMN_HEADER);
		boolean isRetainId = "true".equalsIgnoreCase(table.optString(Constants.RETAIN_ID, "false"));
		String retainIdWithName = table.optString(Constants.RETAIN_ID_WITH_NAME);

		if (isColTable)
		{
			if (!columnVO.getColumnHeader().equalsIgnoreCase(xlcolumnheader))
				throw new ExcelEngineValidationException("");
		}

		if (tableName == null || tableName.trim().isEmpty())
		{
			OUT.error("No table name specified in parsing rule at table index : {}", tableIndex);
			throw new ExcelEngineValidationException("");
		}

		columns = table.optJSONArray(Constants.COLUMNS);

		if (columns == null || columns.length() == 0)
		{
			OUT.error("No columns are specified in parsing rule for table : {}", tableName);
			throw new ExcelEngineValidationException("");
		}

		uniqueColumnIdentifier = table.optString(Constants.UNIQUE_COLUMN_IDENTIFIERS);

		if (uniqueColumnIdentifier != null && !uniqueColumnIdentifier.trim().isEmpty())
		{
			unqIdentifiers = uniqueColumnIdentifier.split(",");
			for (String identifier : unqIdentifiers)
			{
				uniqieColIDSet.add(identifier.trim());
			}
		}

		insertRow.put(Constants.TABLE_NAME, tableName);
		updateMap.put(Constants.TABLE_NAME, tableName);

		try
		{

			for (int columnIndex = 0; columnIndex < columns.length(); columnIndex++)
			{
				createInsertRow(row, sheetName, tableName, columns, insertRow, columNames, values, columnIndex, uniqieColIDSet, updateMap, conditionFields,
						updateFields, columnVO, isColTable, rowIndex, xlcolumnheader, isRetainId, retainIdWithName, retainedIdsMap);
			}
		}
		catch (ExcelEngineValidationException e)
		{
			if (!e.getMessage().contains("Ignore"))
				OUT.error("Exception: {}", e.getMessage());
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		finally
		{
			insertRow.clear();
			columNames.clear();
			values.clear();
			uniqieColIDSet.clear();
			updateMap.clear();
			conditionFields.clear();
			updateFields.clear();
		}
	}

	private static void createInsertRow(RowVO row, String sheetName, String tableName, JSONArray columns, Map<String, Object> insertRow, List<String> columNames,
			List<Object> values, int columnIndex, Set<String> uniqieColIDSet, Map<String, Object> updateMap, Map<String, Object> conditionFields,
			Map<String, Object> updateFields, ColumnVO columnVO, boolean isColTable, int rowIndex, String xlcolumnheader, boolean isRetainId,
			String retainIdWithName, Map<String, Object> retainedIdsMap) throws Exception
	{
		JSONObject column;
		String tableColumnName;
		String xlColumnName;
		String allowNull;
		String allowDuplicate;
		String dataType;
		String lookupTable;
		String lookupColumnName;
		String lookupFatchColumn;
		String isDelimited;
		String isMultiRow;
		int rowEndIndex;
		String[] delimitedValues;
		int maxLength;
		// int rowIndex;
		Object constantValue = null;
		CellVO cell;
		boolean isNullCell;
		column = columns.getJSONObject(columnIndex);

		tableColumnName = column.optString(Constants.TABLE_COLUMN_NAME);
		xlColumnName = column.optString(Constants.EXCEL_COLUMN_NAME);
		allowNull = column.optString(Constants.ALLOW_NULL, Constants.TRUE);
		allowDuplicate = column.optString(Constants.ALLOW_DUPLICATE, Constants.TRUE);
		dataType = column.optString(Constants.DATA_TYPE);
		lookupTable = column.optString(Constants.LOOKUP_TABLE_NAME);
		lookupColumnName = column.optString(Constants.LOOKUP_COLUMN_NAME);
		lookupFatchColumn = column.optString(Constants.LOOKUP_FETCH_COLUMN);
		isDelimited = column.optString(Constants.IS_DELIMITED, Constants.FALSE);
		isMultiRow = column.optString(Constants.IS_MULTIROW, Constants.FALSE);
		rowEndIndex = column.optInt(Constants.ROW_END_INDEX);
		maxLength = column.optInt(Constants.MAX_LENGTH);
		int colCellIndex = column.optInt(Constants.COLUMN_CELL_INDEX, -1);
		String replaceValue = column.optString(Constants.REPLACE_VALUE);
		boolean isReplaceValue = false;
		boolean isUseRetainedId = "true".equalsIgnoreCase(column.optString(Constants.USE_RETAINED_ID_VALUE, "false"));
		boolean isMultiColumnLookup = "true".equalsIgnoreCase(column.optString(Constants.IS_MULTI_COLUMN_LOOKUP, "false"));
		boolean isAllowNull = Constants.TRUE.equalsIgnoreCase(allowNull);
		if (column.has(Constants.CONSTANT_VALUE))
			constantValue = column.optString(Constants.CONSTANT_VALUE);

		if (isUseRetainedId)
		{
			String retainedWithName = column.optString(Constants.RETAINED_WITH_NAME);
			constantValue = retainedIdsMap.get(retainedWithName);
		}
		if (isColTable)
		{
			rowIndex = column.optInt(Constants.ROW_INDEX);
			cell = columnVO.getCellVOByRowIndex(rowIndex);
		}
		else if (colCellIndex != -1)
		{
			if (!columnVO.getColumnHeader().equalsIgnoreCase(xlcolumnheader))
				throw new ExcelEngineValidationException("Ignore this exception.");
			if (columnVO == null)
				throw new ExcelEngineValidationException("Ignore this exception.");
			CellVO currentRowCell = columnVO.getCellVOByRowIndex(rowIndex);

			if (currentRowCell == null || currentRowCell.getValue() == null || currentRowCell.getValue().toString().trim().isEmpty())
				throw new ExcelEngineValidationException("Ignore this exception.");

			if (colCellIndex == Constants.CURRENT_ROW_CELL_INDEX)
			{
				cell = currentRowCell;
			}
			else
			{
				cell = columnVO.getCellVOByRowIndex(colCellIndex);
			}
		}
		else
		{
			if (!isMultiColumnLookup)
				cell = row.getCellVOByHeading(xlColumnName);
			else
				cell = row.getCellVOForMultiLookupColumn(xlColumnName);
		}
		isNullCell = false;
		if (cell == null || cell.getValue() == null || cell.getValue().toString().trim().isEmpty())
		{
			isNullCell = true;
		}
		if (replaceValue != null && !replaceValue.trim().isEmpty())
		{
			if (!isNullCell && cell.getValue() != null && cell.getValue().toString().trim().equalsIgnoreCase(replaceValue))
			{
				cell.setValue(column.opt(Constants.REPLACE_WITH));
				isReplaceValue = true;
			}
		}
		if (constantValue == null && !Constants.TRUE.equalsIgnoreCase(isMultiRow))
		{
			// Null check
			ExcelUtil.nullValidation(rowIndex, sheetName, columnIndex, xlColumnName, allowNull, allowDuplicate, isNullCell);
		}
		// Is duplicate check.
		ExcelUtil.duplicateValidation(rowIndex, sheetName, tableName, columnIndex, tableColumnName, xlColumnName, allowDuplicate, cell);

		// Max Length
		ExcelUtil.maxLengthValidation(rowIndex, sheetName, columnIndex, xlColumnName, dataType, maxLength, cell, isNullCell);

		// Data type validation
		ExcelUtil.dataTypeValidation(rowIndex, sheetName, columnIndex, xlColumnName, dataType, cell, isNullCell, lookupTable);

		// Lookup table
		if (lookupTable != null && !lookupTable.trim().isEmpty() && (!isNullCell || isAllowNull) && !Constants.TRUE.equalsIgnoreCase(isDelimited)
				&& !Constants.TRUE.equalsIgnoreCase(isMultiRow) && !isReplaceValue)
		{
			handleLookup(columNames, values, uniqieColIDSet, conditionFields, updateFields, tableColumnName, lookupTable, lookupColumnName, lookupFatchColumn,
					cell.getValue(), sheetName, xlColumnName, rowIndex, columnIndex, isAllowNull,isMultiColumnLookup);
		}
		else if (!isNullCell && !Constants.TRUE.equalsIgnoreCase(isDelimited) && !Constants.TRUE.equalsIgnoreCase(isMultiRow))
		{
			columNames.add(tableColumnName);
			values.add(cell.getValue());
			updateUniqueIdentifiers(uniqieColIDSet, conditionFields, updateFields, tableColumnName, cell.getValue());
		}
		else if (constantValue != null && !Constants.TRUE.equalsIgnoreCase(isDelimited) && !Constants.TRUE.equalsIgnoreCase(isMultiRow))
		{
			columNames.add(tableColumnName);
			values.add(constantValue);
			updateUniqueIdentifiers(uniqieColIDSet, conditionFields, updateFields, tableColumnName, constantValue);
		}

		// Delimited column - This should be last column

		if ((Constants.TRUE.equalsIgnoreCase(isDelimited) && !isNullCell) || Constants.TRUE.equalsIgnoreCase(isMultiRow))
		{
			if (Constants.TRUE.equalsIgnoreCase(isDelimited))
			{
				delimitedValues = cell.getValue().toString().split(Constants.DELIMITER);
			}
			else
			{
				String ignValStr = column.optString(Constants.IGNORE_VALUES);
				String[] ignValArr = ignValStr.split(",");
				Set<String> ignoreValuesSet = new HashSet<String>();

				for (String ignoreValue : ignValArr)
				{
					if (ignoreValue.trim().isEmpty())
						continue;
					ignoreValuesSet.add(ignoreValue.toLowerCase().trim());
				}

				delimitedValues = getMultiRowValues(columnVO, rowIndex, rowEndIndex, ignoreValuesSet);
				if (delimitedValues == null)
					return;
			}
			List<String> tempColumnNames;
			List<Object> tempValues;
			Map<String, Object> tempConditionFields;
			Map<String, Object> tempUpdateFields;
			try
			{
				for (int i = 0; i < delimitedValues.length; i++)
				{
					String value = delimitedValues[i].trim();
					tempColumnNames = new ArrayList<String>(columNames);
					tempValues = new ArrayList<Object>(values);
					tempConditionFields = new HashMap<String, Object>(conditionFields);
					tempUpdateFields = new HashMap<String, Object>(updateFields);
					try
					{
						if (lookupTable != null && !lookupTable.trim().isEmpty())
						{
							handleLookup(tempColumnNames, tempValues, uniqieColIDSet, conditionFields, updateFields, tableColumnName, lookupTable, lookupColumnName,
									lookupFatchColumn, value, sheetName, xlColumnName, rowIndex, columnIndex, isAllowNull, isMultiColumnLookup);
						}
						else
						{
							tempColumnNames.add(tableColumnName);
							tempValues.add(value);
							updateUniqueIdentifiers(uniqieColIDSet, conditionFields, updateFields, tableColumnName, value);
						}
						boolean isUpdated = false;
						if (uniqieColIDSet.size() == conditionFields.size())
							isUpdated = updateData(updateMap, conditionFields, updateFields);

						if (!isUpdated)
						{
							insertRow.put(Constants.KEYS, tempColumnNames);
							insertRow.put(Constants.VALUES, tempValues);
							if (isRetainId)
							{
								insertRow.put("id", -1);
								DBManager.getInstance().insert(DBOperations.INSERT_DATA_GET_ID, insertRow);
								retainedIdsMap.put(retainIdWithName, insertRow.get("id"));
							}
							else
							{
								DBManager.getInstance().insert(DBOperations.INSERT_DATA, insertRow);
							}
						}
						else if (isRetainId)
						{
							long id = DBManager.getInstance().getId(DBOperations.GET_ID_BY_UNIQUE_COLUMN_IDENTIFIERS, updateMap);
							retainedIdsMap.put(retainIdWithName, id);
						}
					}
					catch (ExcelEngineValidationException e)
					{
						// Do Nothing
					}
					catch (Exception e)
					{
						OUT.error("Exception", e);
					}
					finally
					{
						tempColumnNames.clear();
						tempValues.clear();
						tempConditionFields.clear();
						tempUpdateFields.clear();
					}
				}
			}
			catch (Exception e)
			{
				OUT.error("Exception", e);
			}
			finally
			{
				insertRow.clear();
				columNames.clear();
				values.clear();
			}
		}
		else if (columnIndex == columns.length() - 1)
		{

			try
			{
				boolean isUpdated = false;
				isUpdated = updateData(updateMap, conditionFields, updateFields);
				if (!isUpdated)
				{
					insertRow.put(Constants.KEYS, columNames);
					insertRow.put(Constants.VALUES, values);
					if (isRetainId)
					{
						insertRow.put("id", -1);
						DBManager.getInstance().insert(DBOperations.INSERT_DATA_GET_ID, insertRow);
						retainedIdsMap.put(retainIdWithName, insertRow.get("id"));
					}
					else
					{
						DBManager.getInstance().insert(DBOperations.INSERT_DATA, insertRow);
					}
				}
				else if (isRetainId)
				{
					long id = DBManager.getInstance().getId(DBOperations.GET_ID_BY_UNIQUE_COLUMN_IDENTIFIERS, updateMap);
					retainedIdsMap.put(retainIdWithName, id);
				}
			}
			catch (Exception e)
			{
				OUT.error("Exception", e);
			}
			finally
			{
				insertRow.clear();
				columNames.clear();
				values.clear();
			}
		}
	}

	private static String[] getMultiRowValues(ColumnVO columnVO, int rowStartIndex, int rowEndIndex, Set ignoreValuesSet)
	{
		if (columnVO == null)
			return null;

		StringBuilder sb = new StringBuilder("");

		for (int rowIndex = rowStartIndex; rowIndex <= rowEndIndex; rowIndex++)
		{
			CellVO cell = columnVO.getCellVOByRowIndex(rowIndex);
			if (cell == null || cell.getValue() == null || cell.getValue().toString().trim().isEmpty()
					|| ignoreValuesSet.contains(cell.getValue().toString().toLowerCase().trim()))
				continue;

			sb.append(cell.getValue()).append("|");
		}

		if (sb.toString().trim().isEmpty())
			return null;
		return sb.toString().split(Constants.DELIMITER);
	}

	private static void handleLookup(List<String> columNames, List<Object> values, Set<String> uniqieColIDSet, Map<String, Object> conditionFields,
			Map<String, Object> updateFields, String tableColumnName, String lookupTable, String lookupColumnName, String lookupFatchColumn, Object value,
			String sheetName, String xlColumnName, int rowIndex, int columnIndex, boolean isAllowNull, boolean isMultiColumnLookup) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> columnsList = new ArrayList<>();
		List<String> valuesList = new ArrayList<>();

		if (isMultiColumnLookup)
		{
			columnsList = Arrays.asList(lookupColumnName.split(","));
			valuesList = Arrays.asList(value.toString().split(Constants.DELIMITER));
		}
		else
		{
			if (null != value)
			{
				columnsList.add(lookupColumnName);
				valuesList.add(value.toString());
			}
		}

		Map<String, String> conditions = new HashMap<>();
		
		for (int i = 0; i < columnsList.size(); i++)
		{
			conditions.put(columnsList.get(i), valuesList.get(i));
		}

		Map<String, Object> result = null;
		if (conditions.size() > 0 )
		{

			paramMap.put(Constants.LOOKUP_TABLE_NAME, lookupTable);
			paramMap.put(Constants.CONDITION_FIELDS, conditions);
			paramMap.put(Constants.LOOKUP_FETCH_COLUMN, lookupFatchColumn);

			result = DBManager.getInstance().getResultAsMap(DBOperations.GET_LOOKUP_VALUE, paramMap);
		}
		if (result == null)
		{
			if (isAllowNull)
				return;
			OUT.error(
					"No lookup record found! Lookup table = \'{}\', Lookup column = \'{}\', Lookup Value = \'{}\' . Location in excel : [Sheet : {}, Column : {}, Row : {}, Col : {}] ",
					lookupTable, lookupColumnName, value, sheetName, xlColumnName, rowIndex, columnIndex);
			throw new ExcelEngineValidationException("No lookup record found!");
		}
		columNames.add(tableColumnName);
		values.add(result.get(lookupFatchColumn));
		updateUniqueIdentifiers(uniqieColIDSet, conditionFields, updateFields, tableColumnName, result.get(lookupFatchColumn));
	}

	private static boolean updateData(Map<String, Object> updateMap, Map<String, Object> conditionFields, Map<String, Object> updateFields) throws Exception
	{
		if (conditionFields.size() > 0)
		{
			updateMap.put(Constants.CONDITION_FIELDS, conditionFields);
			updateMap.put(Constants.UPDATE_FIELDS, updateFields);
			long isExist = DBManager.getInstance().getSumOfCount(DBOperations.GET_COUNT_BY_UNIQUE_COLUMN_IDENTIFIERS, updateMap);
			if (isExist > 0)
			{
				if (updateFields.size() > 0)
					DBManager.getInstance().updateObject(DBOperations.UPDATE_DATE, updateMap);
				return true;
			}
		}
		return false;
	}

	private static void updateUniqueIdentifiers(Set<String> uniqieColIDSet, Map<String, Object> conditionFields, Map<String, Object> updateFields,
			String tableColumnName, Object value)
	{
		if (uniqieColIDSet.size() > 0)
		{
			if (uniqieColIDSet.contains(tableColumnName))
			{
				conditionFields.put(tableColumnName, value);
			}
			else
			{
				updateFields.put(tableColumnName, value);
			}
		}
	}

	public static void main(String[] args)
	{
		if (args == null || args.length < 2)
		{
			OUT.debug("USAGES : ExcelEngine <Excel file> <Rules file> ");
		}
		else
		{
			 ExcelEngine.getInstance().parseExcel(args[0], args[1]);
		}
		 //ExcelEngine.getInstance().parseExcel("./src/SpecialCourseSW.xlsx", "./src/IntegratedCourses_rules.txt");
		// ExcelEngine.getInstance().parseExcel("./src/Lodestart - DataUploadFormat_v2.xlsx", "./src/OCCTags.txt");

//		ExcelEngine.getInstance().parseExcel("./src/test.xls", "./src/testrule.txt");

	}
}
