package com.lodestar.edupath.dataaccessobject.dao.induocchoice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.calculatefitmentcolor.CalculateFitmentColor;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.manualSearch.ManualSearchDTO;
import com.lodestar.edupath.datatransferobject.dto.manualSearch.ManualSearchQuestionMappingDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class ManualSearch
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ManualSearch.class);

	public Map<String, List<OccupationDTO>> getManualSearchResult(ManualSearchDTO search,int StudentID) throws Exception
	{
		Map<Double, String> searchQuestions = search.getSearchQuestions();

		String mandatoryQuestions = search.getMandatoryQuestions();

		OUT.debug("Search Questions: {}, mandatoryQuestions: {}", searchQuestions, mandatoryQuestions);

		List<ManualSearchQuestionMappingDTO> questionSlList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_MANUAL_SEARCH_MAP,
				new ManualSearchQuestionMappingDTO());
		Map<Double, ManualSearchQuestionMappingDTO> slNoMapping = new HashMap<Double, ManualSearchQuestionMappingDTO>();
		for (ManualSearchQuestionMappingDTO manualSearchQuestionMappingDTO : questionSlList)
		{
			slNoMapping.put(manualSearchQuestionMappingDTO.getQuestionSlNo(), manualSearchQuestionMappingDTO);
		}

		ManualSearchQuestionMappingDTO dto = null;

		StringBuilder mandatoryQuery = new StringBuilder();
		Set<Double> mandatoyrQuesSet = new HashSet<Double>();
		if (mandatoryQuestions != null && !mandatoryQuestions.trim().isEmpty())
		{
			for (String slNo : mandatoryQuestions.split(","))
			{
				dto = slNoMapping.get(Double.parseDouble(slNo));
				mandatoyrQuesSet.add(Double.parseDouble(slNo));
				mandatoryQuery.append(" AND ").append(getConditionQuery(dto, searchQuestions.get(Double.parseDouble(slNo))));
			}
		}

		Map<String, List<OccupationDTO>> resultMap = new HashMap<String, List<OccupationDTO>>();
		StringBuilder conditionQuery = null;
		List<OccupationDTO> resultList = null;
		if (searchQuestions.containsKey(0.0))
		{
			List<String> ansList = CommonUtil.convertCommaSeperatedToList(CommonUtil.replaceDBQuotes(searchQuestions.get(0.0).trim()));
			if (isvalidKeyWordForSearch(ansList))
			{
				resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_BY_KEYWORD_FOR_MANUAL_SEARCH, ansList);
			}
			
			//START Sasedeve  by Mrutyunjaya on date 03-05-2017
			CalculateFitmentColor calfit=new CalculateFitmentColor();
			
			resultList=calfit.GetFitementForManualSearch(resultList, StudentID);
			//END Sasedeve  by Mrutyunjaya on date 03-05-2017
			
			resultMap.put("Keyword Search", resultList);
			
			searchQuestions.remove(0.0); 
		}

		boolean breakAfterOne = false;
		for (Entry<Double, String> question : searchQuestions.entrySet())
		{
			// if search and mandatory are same then run query for one searchQuestion
			if (mandatoyrQuesSet.size() == searchQuestions.size())
			{
				breakAfterOne = true;
			}
			resultList = null;
			dto = slNoMapping.get(question.getKey());
			if (null != dto)
			{
				// if there are multiple search values don't search based on mandatory questions
				if (!breakAfterOne && mandatoyrQuesSet.contains(question.getKey().doubleValue()))
				{
					continue;
				}
				conditionQuery = getConditionQuery(dto, question.getValue());

				if (mandatoryQuestions != null && !mandatoryQuestions.trim().isEmpty())
				{
					conditionQuery.append(mandatoryQuery);
				}

				Map<String, Object> paramObject = new HashMap<String, Object>();
				paramObject.put("query", conditionQuery.toString());
				resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_BY_QUERY, paramObject);

				OUT.debug("Condition Query for {}, displayName:{}, {}", dto.getQuestionSlNo(), dto.getDisplayString(), conditionQuery);
				//START Sasedeve  by Mrutyunjaya on date 03-05-2017
				CalculateFitmentColor calfit=new CalculateFitmentColor();
				
				resultList=calfit.GetFitementForManualSearch(resultList, StudentID);
				//END Sasedeve  by Mrutyunjaya on date 03-05-2017
				resultMap.put(dto.getDisplayString(), resultList);

			}
			if (breakAfterOne)
			{
				break;
			}
		}

		return resultMap;
	}

	private boolean isvalidKeyWordForSearch(List<String> ansList)
	{
		boolean isValid = false;
		Iterator<String> iterator = ansList.iterator();
		while (iterator.hasNext())
		{
			String next = iterator.next();
			if (next.length() < 4)
			{
				iterator.remove();
			}
		}
		if (null != ansList && !ansList.isEmpty())
		{
			isValid = true;
		}
		return isValid;
	}

	private StringBuilder getConditionQuery(ManualSearchQuestionMappingDTO dto, String paramValue)
	{
		StringBuilder condition = new StringBuilder();
		if (null != dto.getLookupColumn() && !dto.getLookupColumn().trim().isEmpty() && null != dto.getValueId() && dto.getValueId().intValue() > 0)
		{
			String tagValue = dto.getTagValue();
			if (tagValue.equals("${param}"))
			{
				tagValue = paramValue;
			}

			condition = new StringBuilder(dto.getLookupColumn()).append(" = ").append(dto.getValueId());
			if (tagValue != null && !tagValue.trim().isEmpty())
			{
				condition.append(" AND (");

				String[] split = tagValue.split(",");
				for (int i = 0; i < split.length; i++)
				{
					if (i > 0)
					{
						condition.append(" OR ");
					}
					if (split[i].equalsIgnoreCase("Not Yes"))
					{
						condition.append("tagValue <> '").append("YES").append("' ");
					}
					else if (split[i].equalsIgnoreCase("NONE"))
					{
						condition.append(" 1 = 1 ");
					}
					else
					{
						condition.append("tagValue = '").append(split[i].trim()).append("' ");
					}
				}

				condition.append(") ");
			}
		}
		if (paramValue != null && !paramValue.trim().isEmpty() && dto.getParamJSON() != null && !dto.getParamJSON().trim().isEmpty())
		{
			JSONObject jsonObject = new JSONObject(dto.getParamJSON());

			String listseperator = jsonObject.getString("listseperator");
			String valuesseperator = jsonObject.getString("valuesseperator");
			String valuelistseperator = jsonObject.getString("valuelistseperator");
			JSONArray jsonArray = jsonObject.getJSONArray("valuecolumns");

			String[] valueList = paramValue.split(listseperator); // 1:asd,asdf,asdf | 2:assdssd,asdfasdf,sdsfsdf

			Map<String, ColumnValues> columnValuesMap = new HashMap<String, ManualSearch.ColumnValues>();
			for (String eachValues : valueList)
			{
				String[] values = eachValues.split(valuesseperator); // 1 //asd,asdf,asdf
				String column = null;
				for (int i = 0; i < jsonArray.length(); i++)
				{
					column = jsonArray.getString(i); // subcatId

					JSONObject jsonObject2 = jsonObject.getJSONObject(column);
					ColumnValues columnValues = columnValuesMap.get(column);
					if (columnValues == null)
					{
						columnValues = new ColumnValues();
						columnValues.columnName = column;
						columnValues.columnDetails = jsonObject2;
					}

					columnValues.values = columnValues.values == null ? values[i].trim() : columnValues.values + valuelistseperator + values[i].trim();

					columnValuesMap.put(column, columnValues);
				}
			}
			OUT.debug("ColumnValue Objects {}", columnValuesMap);

			for (Entry<String, ColumnValues> entry : columnValuesMap.entrySet())
			{
				ColumnValues value = entry.getValue();
				String columnName = value.columnName;
				JSONObject columnDetails = value.columnDetails;
				String values = value.values;

				String dataType = columnDetails.getString("valuedatatype");
				String operator = columnDetails.getString("operator");

				String preAppend = columnDetails.has("valuepreappend") ? columnDetails.getString("valuepreappend") : ""; //
				String append = columnDetails.has("valueappend") ? columnDetails.getString("valueappend") : ""; //

				String[] splitValues = values.split(valuelistseperator);
				if (condition.length() > 0)
				{
					condition.append(" AND ");
				}

				int counter = 0;
				if (columnDetails.has("multicondition") && columnDetails.getBoolean("multicondition"))
				{
					condition.append("( ");

					String queryValue = null;
					for (String string : splitValues)
					{
						if (counter > 0)
						{
							condition.append(" ").append(columnDetails.getString("multioperator")).append(" ");
						}

						condition.append(columnName).append(" ").append(operator);
						if (dataType.equalsIgnoreCase("string"))
						{
							queryValue = "\'" + preAppend + string.trim() + append + "\'";
						}
						else
						{
							queryValue += string.trim();
						}

						condition.append(queryValue);
						counter++;
					}

					condition.append(") ");
				}
				else
				{
					// columnName + " "+operator;
					condition.append(columnName).append(" ").append(operator);
					if (operator.equalsIgnoreCase("IN"))
					{
						condition.append("( ");
					}

					String queryValue = null;
					for (String string : splitValues)
					{
						if (queryValue == null)
						{
							queryValue = new String();
						}
						else
						{
							queryValue += ", ";
						}

						if (dataType.equalsIgnoreCase("string"))
						{
							queryValue += "\'" + preAppend + string.trim() + append + "\'";
						}
						else
						{
							queryValue += string.trim();
						}
					}
					condition.append(queryValue);

					if (operator.equalsIgnoreCase("IN"))
					{
						condition.append(" ) ");
					}
				}

			}
		}
		OUT.debug("Condition query: {}", condition);
		return condition;
	}

	class ColumnValues
	{
		String		columnName;
		JSONObject	columnDetails;
		String		values;

		@Override
		public String toString()
		{
			return "ColumnValues [columnName=" + columnName + ", columnDetails=" + columnDetails + ", values=" + values + "]";
		}

	}
}
