package com.lodestar.edupath.bulkupload.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.chanakya.StudentWithInterestService;
import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.dao.CGT.StudentCGTDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.ClassDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.GenderTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StreamTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.student.service.StudentDetailService;
import com.lodestar.edupath.util.ApplicationProperties;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelCellVO;
import netvalence.commons.excel.utils.NetValenceExcelRowObject;

public class ChanakyaStudentInterestTestExcelUploadDBUtil {

	private static final Logger OUT = LoggerFactory.getLogger(ChanakyaStudentInterestTestExcelUploadDBUtil.class);
	public static final int excelNumberOfRows = 1;
	private StudentCGTDAO studentCGTDAO;
	ChanakyaStudentDetailsDTO resultDHStudentDTO;
	private List<String> studentCGTList ;
	private Map<String, StudentDetailsDTO> userLoginIdNIdMap;

	public EActionStatus persistExcelObjects(XLSEngine xlsEngine, List<NetValenceExcelRowObject> excelRowObjList,
			List<CityDTO> cityList, List<SchoolDTO> schoolList, List<ClassDTO> calssList,
			List<FacilitatorDetailsDTO> facilitatorList, String loggedInUserName) {
		ApplicationProperties propInstance = ApplicationProperties.getInstance();
		int totalRecords = 0;
		int successfull = 0;
		int failed = 0;
		int invalid = 0;
		try {
	//		studentCGTDAO = new StudentCGTDAO();
	//		studentCGTList = studentCGTDAO.getStudentCGTForBulk();
	//		userLoginIdNIdMap = new StudentDetailService().getStudentMapForIntersetTestBulk();
		} catch (Exception e) {
			OUT.error("Exception occured while fetching students bulk records", e);
		}
		Map<String, NetValenceExcelCellVO> rowDataMap=null;
		for (NetValenceExcelRowObject excelRowObject : excelRowObjList) {
			rowDataMap = excelRowObject.getRowData();
			totalRecords++;
			String newinterestLoginID = addDetails(rowDataMap, xlsEngine, propInstance, cityList, schoolList, calssList,
					facilitatorList, loggedInUserName);
			if (newinterestLoginID != null) {
				try {
					EActionStatus persistStatus = new ChanakyaInterestStudentTestUplodeDBUtil().persistExcelObjects(xlsEngine,
							rowDataMap, newinterestLoginID, loggedInUserName, userLoginIdNIdMap, studentCGTList,
							studentCGTDAO,resultDHStudentDTO);
					if (persistStatus == EActionStatus.SUCCESS) {
						successfull++;
					} else {
						failed++;
					}
				} catch (Exception e) {
					OUT.error("exception :{}" + e);
					failed++;
				}
			} else {
				failed++;
			}

		}
		if (totalRecords == successfull) {
			return EActionStatus.SUCCESS;
		} else if (totalRecords == invalid || totalRecords == failed) {
			return EActionStatus.FAILURE;
		} else {
			return EActionStatus.SUCCESS;
		}
	}

	private String addDetails(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine,
			ApplicationProperties propInstance, List<CityDTO> cityList, List<SchoolDTO> schoolList,
			List<ClassDTO> calssList, List<FacilitatorDetailsDTO> facilitatorList, String loggedInUserName) {
		NetValenceExcelCellVO statusCellVO = rowDataMap
				.get(propInstance.getProperty("com.edupath.bulkupload.column.status"));
		String streamType = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.streamType"))
				.getValue().trim();
		NetValenceExcelCellVO LDID = rowDataMap.get("LODESTARID");
		try {
			//StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
			ChanakyaStudentDetailsDTO studentDetailsDTO = new ChanakyaStudentDetailsDTO();
			prepareStudentDTO(rowDataMap, studentDetailsDTO, propInstance, cityList, schoolList, calssList,
					facilitatorList);
		//	KAStudentinteresttestbuluploadservice service = new KAStudentinteresttestbuluploadservice();
			StudentWithInterestService service = new StudentWithInterestService();
			//	long t1= System.currentTimeMillis();
			/*
			 * String interestLoginID =
			 * service.insertStudentDetailForInterstBulkUplode(studentDetailsDTO, null,
			 * null, streamType);
			 */
			resultDHStudentDTO = service.studentCreation(studentDetailsDTO);
		//	long t2= System.currentTimeMillis();
		//	System.out.println("time taken by student: "+studentDetailsDTO.getName() + " in ms" + (t2-t1));
			//if (interestLoginID != null) {
				/** add students details in map to be used by later and do not retrieve from db**/
			//	userLoginIdNIdMap.put(interestLoginID.toLowerCase(), studentDetailsDTO);
				StringBuilder auditMessage = new StringBuilder();
				auditMessage.append(" CAHANAKYA Student ").append(studentDetailsDTO.getName())
						.append(" details has been created successfully and created on : ");
				insertAudit(auditMessage.toString(), loggedInUserName);
			//	xlsEngine.setCellValueExternally(statusCellVO.getRowIndex(), statusCellVO.getColumnIndex(), "SUCCESS");
				//xlsEngine.setCellValueExternally(LDID.getRowIndex(), LDID.getColumnIndex(), interestLoginID);
				return "SUCCESS";
			//}
		} catch (Exception e) {
			OUT.error("Exception", e);
		}
		//xlsEngine.setCellValueExternally(statusCellVO.getRowIndex(), statusCellVO.getColumnIndex(), "FAILURE");
		return null;
	}

	private void insertAudit(String auditMessage, String loggedInUserName) {
		AuditTrailLogger.addAuditInfo(ModuleNameEnum.BULK_UPLOAD_STUDENT, auditMessage, loggedInUserName);

	}

	private void prepareStudentDTO(Map<String, NetValenceExcelCellVO> rowDataMap, ChanakyaStudentDetailsDTO studentDetailsDTO,
			ApplicationProperties propInstance, List<CityDTO> cityList, List<SchoolDTO> schoolList,
			List<ClassDTO> calssList, List<FacilitatorDetailsDTO> facilitatorList) {
		//synchronized (this) {
			try {
				if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.name")).getValue()
						.trim() != null
						&& !rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.name")).getValue()
								.trim().isEmpty()) {
					studentDetailsDTO.setName(rowDataMap
							.get(propInstance.getProperty("com.edupath.bulkupload.column.name")).getValue().trim());
					studentDetailsDTO.setFathername(rowDataMap
							.get(propInstance.getProperty("com.edupath.bulkupload.column.name")).getValue().trim());
					studentDetailsDTO.setCHNKID(rowDataMap.get("LODESTARID").getValue().trim());
					studentDetailsDTO.setClassStr(rowDataMap.get("CLASS").getValue().trim());
					for (StreamTypeEnum stenum : StreamTypeEnum.values()) {
						if (rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.column.streamType"))
								.getValue().trim().equalsIgnoreCase(stenum.name())) {
							studentDetailsDTO.setStream(stenum.getWeight());
						}
					}
					
				
					if(rowDataMap.get("GENDER").getValue().trim().equalsIgnoreCase(GenderTypeEnum.M.name()) || rowDataMap.get("GENDER").getValue().trim().equalsIgnoreCase(GenderTypeEnum.M.getGender()) || rowDataMap.get("GENDER").getValue().trim().equalsIgnoreCase("Male"))
					{
						studentDetailsDTO.setGender(GenderTypeEnum.M.name());
					}
					else if(rowDataMap.get("GENDER").getValue().trim().equalsIgnoreCase(GenderTypeEnum.F.name()) || rowDataMap.get("GENDER").getValue().trim().equalsIgnoreCase(GenderTypeEnum.F.getGender()) || rowDataMap.get("GENDER").getValue().trim().equalsIgnoreCase("Female"))
					{
						studentDetailsDTO.setGender(GenderTypeEnum.F.name());
					}
					
				}

				
			} catch (Exception e) {
				OUT.error("Exception", e);
		//	}
		}

	}
}
