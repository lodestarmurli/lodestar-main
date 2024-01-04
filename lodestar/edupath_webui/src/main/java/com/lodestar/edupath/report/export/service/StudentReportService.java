package com.lodestar.edupath.report.export.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class StudentReportService
{
	private static Logger	OUT	= LoggerFactory.getLogger(StudentReportService.class);

	public List<StudentDetailsDTO> getStudentReportList(String searchValue, int recordFilter)
	{
		List<StudentDetailsDTO> finalList = null;
		try
		{
			Map<String, Object> paramMap = new HashMap<String, Object>(5);
			paramMap.put("searchValue", null);
			paramMap.put("orderColumn", null);
			paramMap.put("orderBy", null);
			if (null != searchValue && !searchValue.isEmpty() && !searchValue.equalsIgnoreCase("undefined"))
			{
				paramMap.put("searchValue", searchValue);
			}
			finalList = new StudentDetailsDAO().getStudentListForSummaryForExcel(paramMap, 0, recordFilter);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return finalList;
	}
}
