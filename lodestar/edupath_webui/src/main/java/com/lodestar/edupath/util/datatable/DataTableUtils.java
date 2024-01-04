/**
 * 
 */
package com.lodestar.edupath.util.datatable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.jackson.SerializableString;
import org.codehaus.jackson.io.CharacterEscapes;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

/**
 * @author yogesh singh
 *         Jul 9, 2016
 *         DataTableUtils.java
 */
public class DataTableUtils
{
	private static final Logger	OUT						= LoggerFactory.getLogger(DataTableUtils.class);

	private static final String	DRAW					= "draw";
	private static final String	START					= "start";
	private static final String	COLUMN					= "[data]";
	private static final String	ORDER					= "order";
	private static final String	INDEX					= "[column]";
	private static final String	ORDER_BY				= "[dir]";
	private static final String	SEARCH					= "search[value]";
	private static final String	LENGTH					= "length";

	private static final String	FIELD_DATA_KEY			= "data";

	private static final String	DATA_KEY				= "data";
	private static final String	RECORDS_TOTAL_KEY		= "recordsTotal";
	private static final String	RECORDS_FILTERED_KEY	= "recordsFiltered";

	private static final int[]	esc;

	static
	{
		// to be used to replace xml entities in ObjectMapper
		esc = CharacterEscapes.standardAsciiEscapesForJSON();
		esc[(int) '&'] = CharacterEscapes.ESCAPE_CUSTOM;
		esc[(int) '>'] = CharacterEscapes.ESCAPE_CUSTOM;
		esc[(int) '<'] = CharacterEscapes.ESCAPE_CUSTOM;
		esc[(int) '\''] = CharacterEscapes.ESCAPE_CUSTOM;
		esc[(int) '\"'] = CharacterEscapes.ESCAPE_CUSTOM;
	}

	/**
	 * This method create ObjectMapper instance with escaped XML entities to be used in Data Table
	 */
	public static ObjectMapper getCustomObjectMapper()
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.getJsonFactory().setCharacterEscapes(new CharacterEscapes()
		{

			@Override
			public SerializableString getEscapeSequence(final int ch)
			{
				return new SerializableString()
				{
					private final String	value	= Character.toString((char) ch);

					public String getValue()
					{
						return StringEscapeUtils.escapeXml(value);
					}

					public int charLength()
					{
						return value.length();
					}

					public byte[] asUnquotedUTF8()
					{
						return new byte[0];
					}

					public byte[] asQuotedUTF8()
					{
						return new byte[0];
					}

					public char[] asQuotedChars()
					{
						return new char[0];
					}
				};
			}

			@Override
			public int[] getEscapeCodesForAscii()
			{
				return esc;
			}
		});
		return mapper;
	}

	public static DataTableVO setDataTableVO(HttpServletRequest request, Class<?> clzVO)
	{
		DataTableVO tableVO = new DataTableVO();
		try
		{
			Map<String, String> tableColumnMap = createTableColumnNameMap(clzVO);
			Map<String, RefTableVO> refTableColumnMap = createRefTableNameMap(clzVO);
			int orderIndex = -1;
			List<String> columnNames = new ArrayList<String>(10);
			List<RefTableVO> refTableList = new ArrayList<RefTableVO>(10);
			String paramName = null;
			Enumeration<?> paramEnum = request.getParameterNames();
			while (paramEnum.hasMoreElements())
			{
				paramName = (String) paramEnum.nextElement();
				if (paramName.contains(DRAW))
				{
					tableVO.setDraw(Integer.valueOf(request.getParameter(paramName)));
				}
				else if (paramName.contains(START))
				{
					tableVO.setStart(Integer.valueOf(request.getParameter(paramName)));
				}
				else if (paramName.contains(LENGTH))
				{
					tableVO.setPageLength(Integer.valueOf(request.getParameter(paramName)));
				}
				else if (paramName.contains(SEARCH))
				{
					tableVO.setSearchValue(request.getParameter(paramName));
				}
				else if (paramName.contains(ORDER) && paramName.contains(INDEX))
				{
					orderIndex = Integer.valueOf(request.getParameter(paramName));
				}
				else if (paramName.contains(ORDER) && paramName.contains(ORDER_BY))
				{
					tableVO.setOrderBy(request.getParameter(paramName));
					if (!DataTableEnumInterface.DataTableEnum.ORDER_ASC.getOrder().equalsIgnoreCase(tableVO.getOrderBy()))
					{
						tableVO.setOrderBy("DESC");
					}
				}
				else if (paramName.contains(COLUMN))
				{
					String columnName = request.getParameter(paramName);
					if (tableColumnMap.containsKey(columnName))
					{
						columnNames.add(tableColumnMap.get(columnName));
					}
					if (refTableColumnMap.containsKey(columnName))
					{
						refTableList.add(refTableColumnMap.get(columnName));
					}
				}
			}
			if (orderIndex > -1)
			{
				tableVO.setOrderColumnName(columnNames.get(orderIndex));
				if (!refTableList.isEmpty())
				{
					if (refTableColumnMap.containsKey(tableVO.getOrderColumnName()))
					{
						tableVO.setRefTable(refTableColumnMap.get(tableVO.getOrderColumnName()));
					}
				}
			}
			tableVO.setColumnNameList(columnNames);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return tableVO;
	}

	private static Map<String, RefTableVO> createRefTableNameMap(Class<?> clsName)
	{
		Map<String, RefTableVO> refTableColumnMap = new HashMap<String, RefTableVO>();
		try
		{
			if (clsName != null && null != clsName.getDeclaredFields())
			{
				Field[] classFileds = clsName.getDeclaredFields();
				DataTableAnnotation dbAnnotation = null;
				RefTableAnnotation refAnnotation = null;
				String displayName = null, tableColName = null;
				RefTableVO refTableVO = null;
				for (Field field : classFileds)
				{
					refAnnotation = field.getAnnotation(RefTableAnnotation.class);
					if (refAnnotation != null)
					{
						refTableVO = new RefTableVO();
						refTableVO.setRefTableName(refAnnotation.refTable());

						dbAnnotation = field.getAnnotation(DataTableAnnotation.class);
						if (null != dbAnnotation)
						{
							if (!dbAnnotation.exclude() && (null != refAnnotation.refColumn() && !refAnnotation.refColumn().isEmpty()))
							{
								tableColName = (null != dbAnnotation.tableColumnName() && !dbAnnotation.tableColumnName().isEmpty() ? dbAnnotation.tableColumnName()
										: field.getName());
								displayName = (null != dbAnnotation.displayColumnName() && !dbAnnotation.displayColumnName().isEmpty() ? dbAnnotation
										.displayColumnName() : tableColName);
								refTableVO.setRefNameColumn(refAnnotation.refColumn());
								refTableColumnMap.put(displayName, refTableVO);
							}
							else if (null != refAnnotation.refColumn() && !refAnnotation.refColumn().isEmpty())
							{
								refTableVO.setRefNameColumn(refAnnotation.refColumn());
								refTableColumnMap.put(field.getName(), refTableVO);
							}
						}
						else if (null != refAnnotation.refColumn() && !refAnnotation.refColumn().isEmpty())
						{
							refTableVO.setRefNameColumn(refAnnotation.refColumn());
							refTableColumnMap.put(field.getName(), refTableVO);
						}
						tableColName = null;
						displayName = null;
					}
				}
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return refTableColumnMap;
	}

	private static Map<String, String> createTableColumnNameMap(Class<?> clsName)
	{
		Map<String, String> tableColumnMap = new HashMap<String, String>();
		try
		{
			if (clsName != null && null != clsName.getDeclaredFields())
			{
				Field[] classFileds = clsName.getDeclaredFields();
				DataTableAnnotation dbAnnotation = null;
				boolean exclude = false;
				String displayName = null, tableColName = null;
				for (Field field : classFileds)
				{
					dbAnnotation = field.getAnnotation(DataTableAnnotation.class);
					if (dbAnnotation != null)
					{
						exclude = dbAnnotation.exclude();
						displayName = dbAnnotation.displayColumnName();
						tableColName = dbAnnotation.tableColumnName();
					}
					if (!exclude)
					{
						tableColName = (null != tableColName && !tableColName.isEmpty() ? tableColName : field.getName());
						displayName = (null != displayName && !displayName.isEmpty() ? displayName : tableColName);
						tableColumnMap.put(displayName, tableColName);
					}
					exclude = false;
					tableColName = null;
					displayName = null;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return tableColumnMap;
	}

	public static JSONArray getDataTableFieldName(Class<?> clsName)
	{
		JSONArray filedsArr = new JSONArray();
		try
		{
			Field[] fileds = clsName.getDeclaredFields();
			if (null != fileds)
			{
				JSONObject fieldNameJson = null;
				boolean exclude = false;
				Annotation annotation = null;
				DataTableAnnotation dbAno = null;
				String displayName = null;
				for (Field field : fileds)
				{
					annotation = field.getAnnotation(DataTableAnnotation.class);
					if (annotation != null)
					{
						dbAno = (DataTableAnnotation) annotation;
						exclude = dbAno.exclude();
						displayName = dbAno.displayColumnName();
					}
					if (!exclude)
					{
						displayName = (null != displayName && !displayName.isEmpty() ? displayName : field.getName());
						fieldNameJson = new JSONObject();
						fieldNameJson.put(FIELD_DATA_KEY, displayName);
						filedsArr.put(fieldNameJson);
					}
					exclude = false;
					displayName = null;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return filedsArr;
	}

	public static JSONObject createDataTableJSON(long draw, long recordsTotalCount, long recordsFilteredCount, JSONArray dataArr)
	{
		OUT.debug("Data Table RecordsTotalCount : {}, RecordsFilteredCount : {} and Draw : {} ", recordsTotalCount, recordsFilteredCount, draw);
		JSONObject tableJSON = new JSONObject();
		tableJSON.put(DRAW, draw);
		tableJSON.put(DATA_KEY, dataArr);
		tableJSON.put(RECORDS_TOTAL_KEY, recordsTotalCount);
		tableJSON.put(RECORDS_FILTERED_KEY, recordsFilteredCount);
		return tableJSON;
	}
}
