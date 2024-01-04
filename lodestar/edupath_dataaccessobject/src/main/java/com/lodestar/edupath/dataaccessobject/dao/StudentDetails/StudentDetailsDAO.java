package com.lodestar.edupath.dataaccessobject.dao.StudentDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.PaymentDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.TrialStudentExtraDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.VenueDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialCityCentersDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class StudentDetailsDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(StudentDetailsDAO.class);

	public StudentDetailsDTO getStudentDetailsByUserId(UserDetailDTO userDetailDTO) throws Exception
	{
		StudentDetailsDTO detailsDTO = (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_DETAIL_BY_USERID,
				userDetailDTO.getId());
		return detailsDTO;
	}

	public static List<StudentDetailsDTO> getStudentList(int userId) throws Exception
	{
		List<StudentDetailsDTO> studentList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_STUDENT_DETAIL_AND_SESSION_BY_USERID, userId);
		OUT.debug("Student List Size :{}", null != studentList ? studentList.size() : 0);
		return studentList;
	}

	public StudentDetailsDTO insertStudent(SqlSession session, StudentDetailsDTO studentDTO) throws Exception
	{
		int primaryKeyId = -1;
		//Start Sasedeve Edited By Mrutyunjaya On Date 26-04-2018
		
		if(studentDTO.getSchoolId()==126 &&  studentDTO.getOtherSchool()!= null && !studentDTO.getOtherSchool().equals(""))
		{
			SchoolDTO schoolDTO=new SchoolDTO();
			
			schoolDTO.setName(studentDTO.getOtherSchool());
			schoolDTO.setCode("1");
			
			int insertId = 0;
			
			try
			{
				insertId =session.insert(MyBatisMappingConstants.INSERT_SCHOOL_DETAILS, schoolDTO);
				OUT.debug("Insert School id: {}", insertId);
				
				
				studentDTO.setSchoolId(schoolDTO.getId());
				studentDTO.setOtherSchool(null);
				int insertId1 = 0;
				
				try
				{
					SchoolDTO schoolDTO1=new SchoolDTO();
					
					
					schoolDTO1.setSchoolId(schoolDTO.getId());
					schoolDTO1.setCityId(studentDTO.getCityId());
					
					insertId1 =session.insert(MyBatisMappingConstants.INSERT_SCHOOLMAPING_CITY, schoolDTO1);
					OUT.debug("Insert SchoolMapping id: {}", insertId);

				}
				catch (Exception e)
				{
					OUT.error(ApplicationConstants.EXCEPTION, e);
				}

			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
		}
		
		
		//END Sasedeve Edited By Mrutyunjaya On Date 26-04-2018
			
		primaryKeyId = session.insert(MyBatisMappingConstants.INSERT_STUDENT_DETAILS, studentDTO);
		OUT.debug("Insert {}", primaryKeyId <= 0 ? "FAILUER" : "SUCESSFULLY");
		return studentDTO;
	}

	public StudentDetailsDTO getStudentObjectById(Integer studentId) throws Exception
	{
		StudentDetailsDTO studentDetailsDTO = null;
		studentDetailsDTO = (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_STUDENT_DETAIL_BY_ID,
				studentId.intValue());
		return studentDetailsDTO;
	}

	public void deleteUserDetailsById(int id) throws Exception
	{
		MyBatisManager.getInstance().deleteObjectById(MyBatisMappingConstants.DELETE_STUDENT_BY_ID, id);
	}

	public void updateStudent(StudentDetailsDTO studentDTO) throws Exception
	{
		
		//Start Sasedeve Edited By Mrutyunjaya On Date 26-04-2018
		 SqlSession session=MyBatisManager.getInstance().getSession();
				if(studentDTO.getSchoolId()==126 &&  studentDTO.getOtherSchool()!= null && !studentDTO.getOtherSchool().equals(""))
				{
					SchoolDTO schoolDTO=new SchoolDTO();
					
					schoolDTO.setName(studentDTO.getOtherSchool());
					schoolDTO.setCode("1");
					
					int insertId = 0;
					
					try
					{
						insertId =session.insert(MyBatisMappingConstants.INSERT_SCHOOL_DETAILS, schoolDTO);
						OUT.debug("Insert School id: {}", insertId);
						
						
						studentDTO.setSchoolId(schoolDTO.getId());
						studentDTO.setOtherSchool(null);
						int insertId1 = 0;
						
						try
						{
							SchoolDTO schoolDTO1=new SchoolDTO();
							
							
							schoolDTO1.setSchoolId(schoolDTO.getId());
							schoolDTO1.setCityId(studentDTO.getCityId());
							
							insertId1 =session.insert(MyBatisMappingConstants.INSERT_SCHOOLMAPING_CITY, schoolDTO1);
							OUT.debug("Insert SchoolMapping id: {}", insertId);
							session.commit();

						}
						catch (Exception e)
						{
							OUT.error(ApplicationConstants.EXCEPTION, e);
						}

					}
					catch (Exception e)
					{
						OUT.error(ApplicationConstants.EXCEPTION, e);
					}
				}
				
				
				//END Sasedeve Edited By Mrutyunjaya On Date 26-04-2018
		
		MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_STUDENT_BY_ID, studentDTO);
		
		//Start Sasedeve Edited By Mrutyunjaya On Date 25-07-2018
		
		UserDetailDTO userdetaildto= new UserDetailDTO();
		
		userdetaildto.setId(studentDTO.getUserId());
		userdetaildto.setIsActive("Y");
		Date date = new Date();
		userdetaildto.setUpdatedOn(date);
		
		MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_USER_DETAILS_ISACTIVE_BY_ID, userdetaildto);
		
		//END Sasedeve Edited By Mrutyunjaya On Date 25-07-2018
	}

	public List<StudentDetailsDTO> getAllStudentDetailsListByEmailId(StudentDetailsDTO studentDetailsDTO)
	{
		List<StudentDetailsDTO> resultAsList = null;
		try
		{
			resultAsList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_STUDENT_DETAILS_BY_FATHER_EMAIL_ID, studentDetailsDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return resultAsList;
	}

	public List<StudentDetailsDTO> getAllStudentSessionDetailsByFacilitatorId(StudentDetailsDTO studentDetailsDTO)
	{
		List<StudentDetailsDTO> resultAsList = null;
		try
		{
			resultAsList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_STUDENT_DETAILS_BY_FACILITATOR_ID, studentDetailsDTO);
			OUT.debug("Student List Size :{}", null != resultAsList ? resultAsList.size() : 0);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return resultAsList;
	}

	public List<StudentDetailsDTO> getAllStudentSessionDetailsByDate(StudentDetailsDTO studentDetailsDTO)
	{
		List<StudentDetailsDTO> resultAsList = null;
		try
		{
			resultAsList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_STUDENT_DETAILS_BY_DATE, studentDetailsDTO);
			OUT.debug("Student List Size :{}", null != resultAsList ? resultAsList.size() : 0);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return resultAsList;
	}

	public StudentDetailsDTO getStudentDetailsById(int id)
	{

		StudentDetailsDTO studentDetailsDTO = null;
		try
		{
			studentDetailsDTO = (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_INFO_DETAILS_BY_ID, id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return studentDetailsDTO;
	}

	public List<StudentDetailsDTO> getStudentListBySearchValue(String searchValue) throws Exception
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("searchValue", searchValue);
		List<StudentDetailsDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_STUDENT_LIST_BY_SERCH_VALUE, param);
		return list;
	}

	public List<StudentDetailsDTO> getStudentListForSummary(Map<String, Object> paramMap, int skipStrat, int maxLimit) throws Exception
	{
		OUT.debug("Get Student Details For Summary With serach Param : {} skipStrat:{},maxLimit:{}", paramMap,skipStrat,maxLimit);
		maxLimit=10000;
		List<StudentDetailsDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.STUDENT_GET_SUMMARY, paramMap, skipStrat, maxLimit);
		OUT.debug("Founded Student Details For Summary Size : {}", null != list ? list.size() : 0);
		return (null != list ? list : new ArrayList<StudentDetailsDTO>(1));
	}
	
	public List<StudentDetailsDTO> getStudentListForSummaryForExcel(Map<String, Object> paramMap, int skipStrat, int maxLimit) throws Exception
	{
		OUT.debug("Get Student Details For Summary With serach Param : {} skipStrat:{},maxLimit:{}", paramMap,skipStrat,maxLimit);
		maxLimit=30000;
		List<StudentDetailsDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.STUDENT_GET_SUMMARY_FOR_EXCEL, paramMap, skipStrat, maxLimit);
		OUT.debug("Founded Student Details For Summary Size : {}", null != list ? list.size() : 0);
		return (null != list ? list : new ArrayList<StudentDetailsDTO>(1));
	}
	
	//start by bharath 4/7/2019
	public StudentDetailsDTO getStudentForSummaryById(int UserId) throws Exception
	{
		OUT.debug("Get Student Details For Summary With serach Param : {}", UserId);
		StudentDetailsDTO studentDetailsDTO = (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.STUDENT_GET_SUMMARY_BY_ID, UserId);
		return studentDetailsDTO;
	}
	//end by bharath 4/7/2019
	
	//start by bharath 10/9/2019
	public List<StudentDetailsDTO> getCompletedStudentListForSummary(Map<String, Object> paramMap, int skipStrat, int maxLimit) throws Exception
	{
		OUT.debug("Inside getCompletedStudentListForSummary ");
		OUT.debug("Get Student Details For Summary With serach Param : {}", paramMap);
		List<StudentDetailsDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.COMPLETED_STUDENT_GET_SUMMARY, paramMap, skipStrat, maxLimit);
		OUT.debug("Founded Completed Student Details For Summary Size : {}", null != list ? list.size() : 0);
		return (null != list ? list : new ArrayList<StudentDetailsDTO>(1));
	}
	
	public int getCompletedStudentSummaryCount(Map<String, Object> paramMap) throws Exception
	{
		OUT.debug("Get Student Details For Summary With serach Param : {}", paramMap);
		Integer count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.COMPLETED_STUDENT_GET_SUMMARY_COUNT, paramMap);
		OUT.debug("Founded Completed Student Summary COUNT : {}", (null != count ? count : 0));
		return null != count ? count : 0;
	}
	//end by bharath 10/9/2019
	
	//start by bharath 12/9/2019
		public List<StudentDetailsDTO> getTrailStudentListForSummary(Map<String, Object> paramMap, int skipStrat, int maxLimit) throws Exception
		{
			OUT.debug("Inside getTrailStudentListForSummary ");
			OUT.debug("Get Student Details For Summary With serach Param : {}", paramMap);
			List<StudentDetailsDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.TRAIL_STUDENT_GET_SUMMARY, paramMap, skipStrat, maxLimit);
			OUT.debug("Founded Trail Student Details For Summary Size : {}", null != list ? list.size() : 0);
			return (null != list ? list : new ArrayList<StudentDetailsDTO>(1));
		}
		
		public int getTrailStudentSummaryCount(Map<String, Object> paramMap) throws Exception
		{
			OUT.debug("Get Trail Student Details For Summary With serach Param : {}", paramMap);
			Integer count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.TRAIL_STUDENT_GET_SUMMARY_COUNT, paramMap);
			OUT.debug("Founded Trail Student Summary COUNT : {}", (null != count ? count : 0));
			return null != count ? count : 0;
		}
		
		//end by bharath 12/9/2019
	public StudentDetailsDTO getStudentDetailsByUserId(int id) throws Exception
	{
		StudentDetailsDTO studentDetailsDTO = (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_BY_USERID,
				id);
		return studentDetailsDTO;
	}

	public static StudentDetailsDTO getStudentDetailsByStudentId(int id) throws Exception
	{
		StudentDetailsDTO studentDetailsDTO = (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(
				MyBatisMappingConstants.GET_STUDENT_DETAIL_BY_STUDENTID, id);
		return studentDetailsDTO;
	}

	public StudentDetailsDTO getStudentSessionDetailsByStudentLoginId(StudentDetailsDTO detailsDTO)
	{
		StudentDetailsDTO dto = null;
		try
		{
			dto = (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_STUDENT_DETAILS_BY_STUDENT_LOGINID, detailsDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}

	/**
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public StudentDetailsDTO getStudentFaciNSessionDetailsById(int studentId) throws Exception
	{
		OUT.debug("Getting student details by student Id : {}", studentId);
		StudentDetailsDTO studentDetailsDTO = (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(
				MyBatisMappingConstants.STUDENT_DETAILS_GET_BY_STUDENT_ID, studentId);
		OUT.debug("Student details {} for student Id: {}", studentDetailsDTO != null ? " FOUND " : " NOT FOUND ", studentId);
		return studentDetailsDTO;
	}

	public List<StudentDetailsDTO> getStudentSessionDetailsForReviewByFacilitatorId(StudentDetailsDTO studentDetailsDTO) throws Exception
	{
		List<StudentDetailsDTO> resultAsList = MyBatisManager.getInstance().getResultAsList(
				MyBatisMappingConstants.GET_ALL_STUDENT_DETAILS_FOR_REVIEW_BY_FACILITATOR_ID, studentDetailsDTO);
		OUT.debug("Student List Size :{}", null != resultAsList ? resultAsList.size() : 0);
		return resultAsList;
	}

	public boolean isExistBySchoolId(int schoolId) throws Exception
	{
		int count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.IS_EXIST_BY_SCHOOL_ID, schoolId);
		if (count > 0)
		{
			return true;
		}
		return false;
	}

	public List<StudentDetailsDTO> getStudentForInterestTestBulk() throws Exception
	{
		List<StudentDetailsDTO> studentList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.STUDENT_GET_FOR_INTEREST_TEST_BULK, null);
		OUT.debug("Founded Student List Size :{} For Interest Bulk", null != studentList ? studentList.size() : 0);
		return null != studentList ? studentList : new ArrayList<StudentDetailsDTO>(1);
	}

	public StudentDetailsDTO getStudentForTrialReport(int id) throws Exception
	{
		OUT.debug("Get Student For Trial Report with id : {}", id);
		return (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.STUDENT_GET_FOR_TRIAL_REPORT_BYID, id);
	}

	public int getStudentSummaryCount(Map<String, Object> paramMap) throws Exception
	{
		OUT.debug("Get Student Details For Summary With serach Param : {}", paramMap);
		Integer count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.STUDENT_GET_SUMMARY_COUNT, paramMap);
		OUT.debug("Founded Student Summary COUNT : {}", (null != count ? count : 0));
		return null != count ? count : 0;
	}

	public List<VenueDetailDTO> getAllvenueDetail() throws Exception {
		
		List<VenueDetailDTO> list=MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.VENUE_ADDRESS_GET_ALL, null);
		// TODO Auto-generated method stub
		OUT.debug("Venue Adress  founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public int insertTrialStudentExtraDetail(SqlSession session, TrialStudentExtraDetailDTO trialStudentExtraDetailDTO) {
		int id = 0;
		try
		{
			
			id = session.insert(MyBatisMappingConstants.ADD_TRIAL_STUDENT_EXTRA_DETAIL, trialStudentExtraDetailDTO);
			OUT.debug("User Trial student Extra  Detail insert id :{}", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return id;
		
		
	}

	public List<TrialStudentExtraDetailDTO> getAllStudentIDFromTrialStudentExtraDetail() {
		try
		{
			OUT.debug("TrialStudentExtraDetailDTO: Getting all ExtraStudent Details");
			List<TrialStudentExtraDetailDTO> resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_StudentID_FROM_TRIAL_EXTRA_STUDENT, null);
			OUT.debug("TrialStudentExtraDetailDTO: Number of ExtraStudent Details found: {}", resultList != null ? resultList.size() : 0);
			return resultList;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public void UpdateTrialStudentExtraDetail(SqlSession session,TrialStudentExtraDetailDTO trialStudentExtraDetailDTO) throws Exception {
		
		OUT.debug("TrialStudentExtraDetailDTO: Getting all TrialStudentExtraDetail Details");
		MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_TRIAL_EXTRA_STUDENT_BY_ID, trialStudentExtraDetailDTO);
		
		
	}
	
	
	public static void CompleteStudentservice()
	{
		try
		{
			OUT.info("Inside StudentDetailsDAO CompleteStudentservice(): Running service");
			StudentDetailsDTO studentdetailsdto = new StudentDetailsDTO();
			MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_COMPLETE_STUDENT, studentdetailsdto);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	}
	
//	start by bharath on 11-10-2019
	public StudentDetailsDTO getStudentDetailsByLoginId(String studentLoginId)
	{
		StudentDetailsDTO dto = null;
		try
		{
			dto = (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_STUDENT_DETAILS_BY_LOGINID, studentLoginId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}
//	end by bharath on 11-10-2019
	
//	start by bharath on 15-10-2019
	
	public List<StudentDetailsDTO> getStudentDetailsByReportGenerated(StudentDetailsDTO studentDetailsDTO) throws Exception
	{
		studentDetailsDTO=null;
		List<StudentDetailsDTO> resultAsList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_STUDENT_DETAILS_BY_REPORT_GENERATED, studentDetailsDTO);
		OUT.debug("Student List Size :{}", null != resultAsList ? resultAsList.size() : 0);
		return resultAsList;
	}
	public void updateStudentClass(SqlSession session, StudentDetailsDTO student)throws Exception
	{
		MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_CLASSID_STUDENT_BY_ID, student);
		 
	}
//	end by bharath on 15-10-2019	
	
	
}
