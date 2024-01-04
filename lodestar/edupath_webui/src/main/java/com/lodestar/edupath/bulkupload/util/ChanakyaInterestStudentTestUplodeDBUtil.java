package com.lodestar.edupath.bulkupload.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.chanakya.InterestResultReportService;
import com.lodestar.edupath.APIS.chanakya.InterestResultUtilService;
import com.lodestar.edupath.APIS.chanakya.StudentWithInterestService;
import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.CGT.StudentCGTDAO;
import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.RaisecCode;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.ApplicationProperties;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelCellVO;
import netvalence.commons.excel.utils.NetValenceExcelRowObject;

public class ChanakyaInterestStudentTestUplodeDBUtil {

	private static final Logger OUT = LoggerFactory.getLogger(ChanakyaInterestStudentTestUplodeDBUtil.class);

	private static final String QUSETION_COLUMN = "QUESTION";

	private String statusHeader;
	// private String loginIdHeader;
	//private StudentCGTDTO studentCGTDTO;
	private ChanakyaStudentCGTDTO studentCGTDTO;; 
	private Map<Integer, String> qusetionColmap;
	private SqlSession session;
	private String answer;
	private static final String CGT_ANSWER_STR_YES = "YES";
	private static final String CGT_ANSWER_BOOLEAN = "TRUE";
	private static final String CGT_ANSWER_STR_NO = "NO";
	private Map<String, JSONArray> cgtAddMap;
	private Map<String, JSONArray> cgtUpdateMap;
	private List<StudentCGTDTO> studentUpdateCgtList;
	//private List<StudentCGTDTO> studentInsertCgtList;
	List<ChanakyaStudentCGTDTO> studentInsertCgtList ;
	private JSONObject json;
	private JSONArray jsonArr;
	private StudentDetailsDTO studentDetailsDTO;

	public List<NetValenceExcelRowObject> prepareTemplate() {
		List<NetValenceExcelRowObject> rowObjectList = new ArrayList<NetValenceExcelRowObject>(5);
		try {

		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return rowObjectList;
	}

	public EActionStatus persistExcelObjects(XLSEngine xlsEngine, Map<String, NetValenceExcelCellVO> rowDataMap,
			String newinterestLoginID, String loggedInUserName, Map<String, StudentDetailsDTO> userLoginIdNIdMap,
			List<String> studentCGTList, StudentCGTDAO studentCGTDAO, ChanakyaStudentDetailsDTO resultDHStudentDTO) throws Exception {
		ApplicationProperties propInstance = ApplicationProperties.getInstance();
		EActionStatus actionStatus = null;
		int totalRecords = 0;
		int successfull = 0;
		int failed = 0;
		int invalid = 0;

		/*
		 * StudentCGTDAO studentCGTDAO = new StudentCGTDAO(); List<String>
		 * studentCGTList = studentCGTDAO.getStudentCGTForBulk(); Map<String,
		 * StudentDetailsDTO> userLoginIdNIdMap = new StudentDetailService()
		 * .getStudentMapForIntersetTestBulk();
		 *
		 */
		statusHeader = propInstance.getProperty("com.edupath.bulkupload.column.status");
		// loginIdHeader =
		// propInstance.getProperty("com.edupath.bulkupload.column.loginid");
		setQusetionColumnName();
		session = MyBatisManager.getInstance().getBatchSession();
		cgtAddMap = new HashMap<String, JSONArray>();
		cgtUpdateMap = new HashMap<String, JSONArray>();
		try {
			if (addOrUpdateDetails(rowDataMap, xlsEngine, propInstance, userLoginIdNIdMap, studentCGTList,
					loggedInUserName, studentCGTDAO, newinterestLoginID, resultDHStudentDTO)) {
				/*
				 * OUT.info("starting recommendation insert for :" + studentDetailsDTO.getId());
				 * new SystemRecommendationV2().searchNSaveCGTResult(studentDetailsDTO.getId());
				 * OUT.info("completed recommendation insert for :" +
				 * studentDetailsDTO.getId());
				 */
				///
				InterestResultReportService	service= new InterestResultReportService();
				InterestResultUtilService utliservice = new InterestResultUtilService();
				ChanakyaStudentDetailsDTO studentDTO = service.getStudentByCHNKID(resultDHStudentDTO.getCHNKID());
				NetValenceExcelCellVO statusCellVO = rowDataMap.get(statusHeader);
				NetValenceExcelCellVO RIASEC = rowDataMap.get("RIASEC");
				String personalityName="";
				if (studentDTO != null) 
				{
					studentDTO.setRaisecCode(utliservice.getStudentRAISEC_Code(studentDTO.getId()));
					
				

					for(RaisecCode RCenum: RaisecCode.values())
					{
						if(studentDTO.getRaisecCode().trim().equalsIgnoreCase(RCenum.name()))
						{
							personalityName=RCenum.Value();
						}
					}
					
					xlsEngine.setCellValueExternally(statusCellVO.getRowIndex(), statusCellVO.getColumnIndex(),
							"SUCCESS");
					xlsEngine.setCellValueExternally(RIASEC.getRowIndex(), RIASEC.getColumnIndex(), personalityName);
				} 
				
				///
				successfull++;
			} else {
				failed++;
			}
			StringBuilder auditMessage = null;
			/*
			 * if (!cgtUpdateMap.isEmpty()) { for (Map.Entry<String, JSONArray> jsonArrMap :
			 * cgtUpdateMap.entrySet()) { auditMessage = new StringBuilder();
			 * auditMessage.append("Student CGT")
			 * .append(" details has been updated successfully with studentId : ")
			 * .append(userLoginIdNIdMap.get(jsonArrMap.getKey()).getId()).
			 * append(", loginId : ") .append(jsonArrMap.getKey().toUpperCase() +
			 * " and data : ").append(jsonArrMap.getValue());
			 * insertAudit(auditMessage.toString(), loggedInUserName); } }
			 */
			/*
			 * if (!cgtAddMap.isEmpty()) { for (Map.Entry<String, JSONArray> jsonArrMap :
			 * cgtAddMap.entrySet()) { auditMessage = new StringBuilder();
			 * auditMessage.append("Student CGT")
			 * .append(" details has been inserted successfully with studentId : ")
			 * .append(userLoginIdNIdMap.get(jsonArrMap.getKey()).getId()).
			 * append(", loginId : ") .append(jsonArrMap.getKey().toUpperCase() +
			 * " and data : ").append(jsonArrMap.getValue());
			 * insertAudit(auditMessage.toString(), loggedInUserName); } }
			 */

			if (successfull == 1) {
				actionStatus = EActionStatus.SUCCESS;
				/*
				 * UserRoleDTO roleDTO = new UserRoleDTO();
				 * roleDTO.setName(UserTypeEnum.USER.getDisplayName()); roleDTO = new
				 * UserRoleDAO().getUserRoleByName(roleDTO); InterestService interestService =
				 * new InterestService();
				 * 
				 * for (Map.Entry<String, StudentDetailsDTO> stuentMap :
				 * userLoginIdNIdMap.entrySet()) { studentDetailsDTO = stuentMap.getValue(); if
				 * (StudentTypeEnum.TRIAL == studentDetailsDTO.getStudentType() &&
				 * studentDetailsDTO.getLoginId().trim().toLowerCase().equals(newinterestLoginID
				 * .toLowerCase().trim())) {
				 * interestService.sendMailForTrialStudentBulkUpload(-1,
				 * roleDTO.getRoleTypeId(), studentDetailsDTO); } }
				 */
			} else if (totalRecords == invalid || totalRecords == failed) {
				actionStatus = EActionStatus.FAILURE;
			} else {
				actionStatus = EActionStatus.SUCCESS;
			}
		} catch (Exception e) {
			if (session != null) {
				session.rollback();
			}
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return actionStatus;
	}

	private boolean addOrUpdateDetails(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine,
			ApplicationProperties propInstance, Map<String, StudentDetailsDTO> userLoginIdNIdMap,
			List<String> studentCGTList, String loggedInUserName, StudentCGTDAO studentCGTDAO,
			String newinterestLoginID, ChanakyaStudentDetailsDTO resultDHStudentDTO) throws Exception {
		NetValenceExcelCellVO statusCellVO = rowDataMap.get(statusHeader);
		String loginId = newinterestLoginID.toLowerCase().trim();
		studentInsertCgtList =  new ArrayList<ChanakyaStudentCGTDTO>();
		studentUpdateCgtList = new ArrayList<StudentCGTDTO>();
		//if (userLoginIdNIdMap.containsKey(loginId)) {
	//		studentDetailsDTO = userLoginIdNIdMap.get(loginId.toLowerCase());
			int studentId = resultDHStudentDTO.getId();
			for (Map.Entry<Integer, String> map : qusetionColmap.entrySet()) {
				answer = rowDataMap.get(map.getValue()).getValue();
				studentCGTDTO = new ChanakyaStudentCGTDTO();
				studentCGTDTO.setStudentId(studentId);
				studentCGTDTO.setQuestionId(map.getKey());
				if (CGT_ANSWER_STR_YES.equalsIgnoreCase(answer) || CGT_ANSWER_BOOLEAN.equalsIgnoreCase(answer)) {
					studentCGTDTO.setAnswer(CGT_ANSWER_STR_YES);
				} else {
					studentCGTDTO.setAnswer(CGT_ANSWER_STR_NO);
				}
				//if (studentCGTDTO.getQuestionId() > 60)
				//	studentCGTDTO.setAnswer(answer);
				
				/*
				 * if (studentCGTList.contains(studentId + ":" + map.getKey())) {
				 * studentUpdateCgtList.add(studentCGTDTO); //
				 * studentCGTDAO.updateForBulkStudentCGT(session, studentCGTDTO);
				 * xlsEngine.setCellValueExternally(statusCellVO.getRowIndex(),
				 * statusCellVO.getColumnIndex(), "SUCCESS");
				 * 
				 * createCgtUpdateMap(loginId); } else {
				 */

					studentInsertCgtList.add(studentCGTDTO);
					// studentCGTDAO.insertForBulkStudentCGT(session, studentCGTDTO);
					//studentCGTList.add(studentId + ":" + map.getKey());
			/*
			 * xlsEngine.setCellValueExternally(statusCellVO.getRowIndex(),
			 * statusCellVO.getColumnIndex(), "SUCCESS");
			 */
					//createCgtAddMap(loginId);
				//}

			}
			/*
			 * for (StudentCGTDTO studentcgt : studentUpdateCgtList) {
			 * studentCGTDAO.updateForBulkStudentCGT(session, studentcgt); }
			 */

			// for (StudentCGTDTO studentcgt : studentInsertCgtList) {
			// studentCGTDAO.insertForBulkStudentCGT(session, studentcgt);
			StudentWithInterestService service = new StudentWithInterestService();
			int result= service.uploadInterestTest(studentInsertCgtList);
			//studentCGTDAO.saveForBulkStudentCGT(session, studentInsertCgtList);
			// }

			if (session != null) {
				session.commit();
			} else {
				session = MyBatisManager.getInstance().getSession();
				session.commit();
		//	}
		//	return true;
		//} else {
		//	xlsEngine.setCellValueExternally(statusCellVO.getRowIndex(), statusCellVO.getColumnIndex(), "FAILURE");
		}
		return true;
	}

	private void createCgtAddMap(String loginId) {
		if (cgtAddMap.containsKey(loginId)) {
			jsonArr = cgtAddMap.get(loginId);
			json = new JSONObject();
			json.put("answer", studentCGTDTO.getAnswer());
			json.put("questionSLN", studentCGTDTO.getQuestionId());
			jsonArr.put(json);
			cgtAddMap.put(loginId, jsonArr);
		} else {
			jsonArr = new JSONArray();
			json = new JSONObject();
			json.put("answer", studentCGTDTO.getAnswer());
			json.put("questionSLN", studentCGTDTO.getQuestionId());
			jsonArr.put(json);
			cgtAddMap.put(loginId, jsonArr);
		}
	}

	/*
	 * private void createCgtUpdateMap(String loginId) { if
	 * (cgtUpdateMap.containsKey(loginId)) { jsonArr = cgtUpdateMap.get(loginId);
	 * json = new JSONObject(); json.put("answer", studentCGTDTO.getAnswer());
	 * json.put("questionSLN", studentCGTDTO.getQuestionId()); jsonArr.put(json);
	 * cgtUpdateMap.put(loginId, jsonArr); } else { jsonArr = new JSONArray(); json
	 * = new JSONObject(); json.put("answer", studentCGTDTO.getAnswer());
	 * json.put("questionSLN", studentCGTDTO.getQuestionId()); jsonArr.put(json);
	 * cgtUpdateMap.put(loginId, jsonArr); } }
	 */

	private void insertAudit(String auditMessage, String loggedInUserName) {
		AuditTrailLogger.addAuditInfo(ModuleNameEnum.BULK_UPLOAD_INTEREST_TEST, auditMessage, loggedInUserName);

	}

	private void setQusetionColumnName() {
		qusetionColmap = new HashMap<Integer, String>(33);
		for (int i = 1; i <= 33; i++) {
			qusetionColmap.put(i, QUSETION_COLUMN + " " + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 120; i++) {
			System.out.println("<column name=\"QUESTION " + (i + 1) + "\"></column>");
		}
	}

}
