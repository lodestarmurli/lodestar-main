package com.lodestar.edupath.bulkupload.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelCellVO;
import netvalence.commons.excel.utils.NetValenceExcelRowObject;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.CGT.StudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.userrole.UserRoleDAO;
import com.lodestar.edupath.datatransferobject.dto.role.UserRoleDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.UserTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.tum.questionnaire.service.InterestService;
import com.lodestar.edupath.util.ApplicationProperties;

public class InterestTestExcelUploadDBUtil
{
	private static final Logger		OUT					= LoggerFactory.getLogger(InterestTestExcelUploadDBUtil.class);

	private static final String		QUSETION_COLUMN		= "QUESTION";

	private String					statusHeader;
	private String					loginIdHeader;
	private StudentCGTDTO			studentCGTDTO;
	private Map<Integer, String>	qusetionColmap;
	private SqlSession				session;
	private String					answer;
	private static final String		CGT_ANSWER_STR_YES	= "YES";
	private static final String		CGT_ANSWER_BOOLEAN	= "TRUE";
	private static final String		CGT_ANSWER_STR_NO	= "NO";
	private Map<String, JSONArray>	cgtAddMap;
	private Map<String, JSONArray>	cgtUpdateMap;
	private JSONObject				json;
	private JSONArray				jsonArr;
	private StudentDetailsDTO		studentDetailsDTO;

	public List<NetValenceExcelRowObject> prepareTemplate()
	{
		List<NetValenceExcelRowObject> rowObjectList = new ArrayList<NetValenceExcelRowObject>(5);
		try
		{

		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return rowObjectList;
	}

	public EActionStatus persistExcelObjects(XLSEngine xlsEngine, List<NetValenceExcelRowObject> excelRowObjList, Map<String, StudentDetailsDTO> userLoginIdNIdMap,
			String loggedInUserName) throws Exception
	{
		ApplicationProperties propInstance = ApplicationProperties.getInstance();
		EActionStatus actionStatus = null;
		int totalRecords = 0;
		int successfull = 0;
		int failed = 0;
		int invalid = 0;

		StudentCGTDAO studentCGTDAO = new StudentCGTDAO();
		List<String> studentCGTList = studentCGTDAO.getStudentCGTForBulk();

		statusHeader = propInstance.getProperty("com.edupath.bulkupload.column.status");
		loginIdHeader = propInstance.getProperty("com.edupath.bulkupload.column.loginid");
		setQusetionColumnName();
		session = MyBatisManager.getInstance().getSession();
		cgtAddMap = new HashMap<String, JSONArray>();
		cgtUpdateMap = new HashMap<String, JSONArray>();
		//Start SASEDEVE Edited by Mrutyunjaya on Date 25-05-2017
		
		List<String> ExcelLoginId=new ArrayList<String>();
		
		//END SASEDEVE Edited by Mrutyunjaya on Date 25-05-2017
		try
		{
			for (NetValenceExcelRowObject excelRowObject : excelRowObjList)
			{
				Map<String, NetValenceExcelCellVO> rowDataMap = excelRowObject.getRowData();
				totalRecords++;
				if (addOrUpdateDetails(rowDataMap, xlsEngine, propInstance, userLoginIdNIdMap, studentCGTList, loggedInUserName, studentCGTDAO))
				{
					OUT.debug("Student CGT added successfully");
					successfull++;
					
					//Start SASEDEVE Edited by Mrutyunjaya on Date 25-05-2017
					String loginId = rowDataMap.get(loginIdHeader).getValue().trim();
					loginId = null != loginId ? loginId.toLowerCase() : loginId;
					
					ExcelLoginId.add(loginId);
					
					//END SASEDEVE Edited by Mrutyunjaya on Date 25-05-2017
				}
				else
				{
					OUT.debug("Student insert Operation failed");
					failed++;
				}
			}
			OUT.debug("Total Records:" + totalRecords + ", Successful:" + successfull + ", Failed:" + failed + ", Invalid:" + invalid);
			if (session != null)
			{
				session.commit();
			}
			StringBuilder auditMessage = null;
			if (!cgtUpdateMap.isEmpty())
			{
				for (Map.Entry<String, JSONArray> jsonArrMap : cgtUpdateMap.entrySet())
				{
					auditMessage = new StringBuilder();
					auditMessage.append("Student CGT").append(" details has been updated successfully with studentId : ")
							.append(userLoginIdNIdMap.get(jsonArrMap.getKey()).getId()).append(", loginId : ")
							.append(jsonArrMap.getKey().toUpperCase() + " and data : ").append(jsonArrMap.getValue());
					insertAudit(auditMessage.toString(), loggedInUserName);
				}
			}
			if (!cgtAddMap.isEmpty())
			{
				for (Map.Entry<String, JSONArray> jsonArrMap : cgtAddMap.entrySet())
				{
					auditMessage = new StringBuilder();
					auditMessage.append("Student CGT").append(" details has been inserted successfully with studentId : ")
							.append(userLoginIdNIdMap.get(jsonArrMap.getKey()).getId()).append(", loginId : ")
							.append(jsonArrMap.getKey().toUpperCase() + " and data : ").append(jsonArrMap.getValue());
					insertAudit(auditMessage.toString(), loggedInUserName);
				}
			}

			if (totalRecords == successfull)
			{
				actionStatus = EActionStatus.SUCCESS;

				UserRoleDTO roleDTO = new UserRoleDTO();
				roleDTO.setName(UserTypeEnum.USER.getDisplayName());
				roleDTO = new UserRoleDAO().getUserRoleByName(roleDTO);
				InterestService interestService = new InterestService();
				for (Map.Entry<String, StudentDetailsDTO> stuentMap : userLoginIdNIdMap.entrySet())
				{
					studentDetailsDTO = stuentMap.getValue();
//					if (StudentTypeEnum.TRIAL == studentDetailsDTO.getStudentType())
//					{
//						interestService.sendMailForTrialStudent(-1, roleDTO.getRoleTypeId(), studentDetailsDTO);
//					}
					
					
					//Start SASEDEVE Edited by Mrutyunjaya on Date 25-05-2017
					if (StudentTypeEnum.TRIAL == studentDetailsDTO.getStudentType() && ExcelLoginId.contains(studentDetailsDTO.getLoginId().trim().toLowerCase()))
					{
							interestService.sendMailForTrialStudent(-1, roleDTO.getRoleTypeId(), studentDetailsDTO);
					}
					
					//END SASEDEVE Edited by Mrutyunjaya on Date 25-05-2017
				}
			}
			else if (totalRecords == invalid || totalRecords == failed)
			{
				actionStatus = EActionStatus.FAILURE;
			}
			else
			{
				actionStatus = EActionStatus.SUCCESS;
			}
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			throw e;
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

		return actionStatus;
	}

	private boolean addOrUpdateDetails(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance,
			Map<String, StudentDetailsDTO> userLoginIdNIdMap, List<String> studentCGTList, String loggedInUserName, StudentCGTDAO studentCGTDAO) throws Exception
	{
		NetValenceExcelCellVO statusCellVO = rowDataMap.get(statusHeader);
		String loginId = rowDataMap.get(loginIdHeader).getValue().trim();
		loginId = null != loginId ? loginId.toLowerCase() : loginId;
		if (userLoginIdNIdMap.containsKey(loginId))
		{
			studentDetailsDTO = userLoginIdNIdMap.get(loginId.toLowerCase());
			int studentId = studentDetailsDTO.getId();
			for (Map.Entry<Integer, String> map : qusetionColmap.entrySet())
			{
				answer = rowDataMap.get(map.getValue()).getValue();
				studentCGTDTO = new StudentCGTDTO();
				studentCGTDTO.setStudentId(studentId);
				studentCGTDTO.setQuestionId(map.getKey());
				if (CGT_ANSWER_STR_YES.equalsIgnoreCase(answer) || CGT_ANSWER_BOOLEAN.equalsIgnoreCase(answer))
				{
					studentCGTDTO.setAnswer(CGT_ANSWER_STR_YES);
				}
				else
				{
					studentCGTDTO.setAnswer(CGT_ANSWER_STR_NO);
				}
				if (studentCGTList.contains(studentId + ":" + map.getKey()))
				{
					studentCGTDAO.updateForBulkStudentCGT(session, studentCGTDTO);
					xlsEngine.setCellValueExternally(statusCellVO.getRowIndex(), statusCellVO.getColumnIndex(), "SUCCESS");
					if (cgtUpdateMap.containsKey(loginId))
					{
						jsonArr = cgtUpdateMap.get(loginId);
						json = new JSONObject();
						json.put("answer", studentCGTDTO.getAnswer());
						json.put("questionSLN", studentCGTDTO.getQuestionId());
						jsonArr.put(json);
						cgtUpdateMap.put(loginId, jsonArr);
					}
					else
					{
						jsonArr = new JSONArray();
						json = new JSONObject();
						json.put("answer", studentCGTDTO.getAnswer());
						json.put("questionSLN", studentCGTDTO.getQuestionId());
						jsonArr.put(json);
						cgtUpdateMap.put(loginId, jsonArr);
					}
				}
				else
				{
					studentCGTDAO.insertForBulkStudentCGT(session, studentCGTDTO);
					studentCGTList.add(studentId + ":" + map.getKey());
					xlsEngine.setCellValueExternally(statusCellVO.getRowIndex(), statusCellVO.getColumnIndex(), "SUCCESS");
					if (cgtAddMap.containsKey(loginId))
					{
						jsonArr = cgtAddMap.get(loginId);
						json = new JSONObject();
						json.put("answer", studentCGTDTO.getAnswer());
						json.put("questionSLN", studentCGTDTO.getQuestionId());
						jsonArr.put(json);
						cgtAddMap.put(loginId, jsonArr);
					}
					else
					{
						jsonArr = new JSONArray();
						json = new JSONObject();
						json.put("answer", studentCGTDTO.getAnswer());
						json.put("questionSLN", studentCGTDTO.getQuestionId());
						jsonArr.put(json);
						cgtAddMap.put(loginId, jsonArr);
					}
				}

			}
			return true;
		}
		else
		{
			xlsEngine.setCellValueExternally(statusCellVO.getRowIndex(), statusCellVO.getColumnIndex(), "FAILURE");
		}
		return false;
	}

	private void insertAudit(String auditMessage, String loggedInUserName)
	{
		AuditTrailLogger.addAuditInfo(ModuleNameEnum.BULK_UPLOAD_INTEREST_TEST, auditMessage, loggedInUserName);

	}

	private void setQusetionColumnName()
	{
		qusetionColmap = new HashMap<Integer, String>(61);
		for (int i = 1; i <= 60; i++)
		{
			qusetionColmap.put(i, QUSETION_COLUMN + " " + i);
		}
	}

	public static void main(String[] args)
	{
		for (int i = 0; i < 60; i++)
		{
			System.out.println("<column name=\"QUESTION " + (i + 1) + "\"></column>");
		}
	}

}
