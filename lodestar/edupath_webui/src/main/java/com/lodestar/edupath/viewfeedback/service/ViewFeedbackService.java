package com.lodestar.edupath.viewfeedback.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.util.ApplicationProperties;
import com.lodestar.edupath.viewfeedback.FeedbackExcelGeneratorEngine;

public class ViewFeedbackService
{

	public List<StudentDetailsDTO> getFeedbackList(StudentDetailsDTO studentDetailsDTO, boolean isAdmin, int loggedInUserId) throws Exception
	{
		if (!isAdmin)
		{
			FacilitatorDetailsDAO facilitatorDetailsDAO = new FacilitatorDetailsDAO();
			UserDetailDTO userDetailDTO = new UserDetailDTO();
			userDetailDTO.setId(loggedInUserId);
			FacilitatorDetailsDTO facilitatorDetailsDTO = facilitatorDetailsDAO.getFacilitatorDetailsByUserId(userDetailDTO);
			studentDetailsDTO.setFacilitatorId(facilitatorDetailsDTO.getId());
		}
		List<StudentDetailsDTO> feedbackDetailsList = new SessionScheduleDetailsDAO().getFeedbackDetails(studentDetailsDTO);
		for (StudentDetailsDTO studentDetails : feedbackDetailsList)
		{
			try
			{
				StringBuilder answer = new StringBuilder();
				JSONArray jsonArray = new JSONArray(studentDetails.getAnswer());
				for (int i = 0; i < jsonArray.length(); i++)
				{
					if (i != 0)
					{
						answer.append(", ");
					}
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					for (String key : JSONObject.getNames(jsonObject))
					{
						answer.append(key + " = " + jsonObject.getString(key));
					}
				}
				studentDetails.setAnswer(answer.toString());
			}
			catch (Exception e)
			{

			}
		}
		return feedbackDetailsList;
	}

	/**
	 * @param reportList
	 * @param isAdmin
	 * @return
	 */
	private Map<Integer, List<String>> prepareColumnLists(List<StudentDetailsDTO> reportList, boolean isAdmin)
	{
		int firstLevelHeader = 1;
		int seconLevelHeader = 2;
		Map<Integer, List<String>> headerMap = new HashMap<Integer, List<String>>();
		headerMap.put(firstLevelHeader, new ArrayList<String>());
		headerMap.put(seconLevelHeader, new ArrayList<String>());

		ApplicationProperties propInstance = ApplicationProperties.getInstance();
		
		//Start Sasedeve Edited By Mrutyunjaya on Date 08-09-2017
		
		headerMap.get(firstLevelHeader).add("Date");
		headerMap.get(seconLevelHeader).add("");
		
		//END Sasedeve Edited By Mrutyunjaya on Date 08-09-2017
		
		headerMap.get(firstLevelHeader).add(propInstance.getProperty("com.edupath.viewfeedback.summary.student.name"));
		headerMap.get(seconLevelHeader).add("");

		headerMap.get(firstLevelHeader).add(propInstance.getProperty("com.edupath.viewfeedback.summary.student.loginid"));
		headerMap.get(seconLevelHeader).add("");
		if (isAdmin)
		{
			headerMap.get(firstLevelHeader).add(propInstance.getProperty("com.edupath.viewfeedback.summary.student.facilitator.loginid"));
			headerMap.get(seconLevelHeader).add("");

			headerMap.get(firstLevelHeader).add(propInstance.getProperty("com.edupath.viewfeedback.summary.facilitator.name"));
			headerMap.get(seconLevelHeader).add("");
		}
		headerMap.get(firstLevelHeader).add(propInstance.getProperty("com.edupath.viewfeedback.summary.school"));
		headerMap.get(seconLevelHeader).add("");

		headerMap.get(firstLevelHeader).add(propInstance.getProperty("com.edupath.viewfeedback.summary.class"));
		headerMap.get(seconLevelHeader).add("");

		headerMap.get(firstLevelHeader).add(propInstance.getProperty("com.edupath.viewfeedback.summary.gender"));
		headerMap.get(seconLevelHeader).add("");
		
		

		if (reportList != null)
		{
			boolean isMultiAns = false;
			int questionNo = 1;
			int studentId = 0;
			for (StudentDetailsDTO studentDetailsDTO : reportList)
			{
				if (studentId != 0 && studentId != studentDetailsDTO.getId())
				{
					break;
				}
				isMultiAns = false;
				try
				{
					JSONArray ansArray = new JSONArray(studentDetailsDTO.getAnswer());
					for (int i = 0; i < ansArray.length(); i++)
					{
						headerMap.get(firstLevelHeader).add(
								propInstance.getProperty("com.edupath.viewfeedback.summary.questionno") + " " + questionNo + "." + (i + 1));
						headerMap.get(seconLevelHeader).add(studentDetailsDTO.getQuestion());
					}
					isMultiAns = true;
				}
				catch (Exception e)
				{
					isMultiAns = false;
					// Do nothing
				}
				if (!isMultiAns)
				{
					headerMap.get(firstLevelHeader).add(propInstance.getProperty("com.edupath.viewfeedback.summary.questionno") + " " + questionNo);
					headerMap.get(seconLevelHeader).add(studentDetailsDTO.getQuestion());
				}
				questionNo++;
				studentId = studentDetailsDTO.getId();
			}
		}
		return headerMap;
	}

	/**
	 * @param fileName
	 * @param destination
	 * @param isAdmin
	 * @param studentDetailsDTO
	 * @return
	 * @throws Exception
	 */
	public File reportExcel(String fileName, String destination, boolean isAdmin, StudentDetailsDTO studentDetailsDTO) throws Exception
	{
		List<StudentDetailsDTO> feedbackDetailsList = new SessionScheduleDetailsDAO().getFeedbackDetails(studentDetailsDTO);
		Map<Integer, List<String>> headerMap = prepareColumnLists(feedbackDetailsList, isAdmin);
		return new FeedbackExcelGeneratorEngine().reportExcel(feedbackDetailsList, headerMap, fileName + ".xls", destination, isAdmin);
	}
}
