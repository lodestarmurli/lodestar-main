package com.lodestar.edupath.dataaccessobject.dao.collegeshortlist;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.elective.EduPathShortListDAO;
import com.lodestar.edupath.datatransferobject.dto.CombinatioListDTO;
import com.lodestar.edupath.datatransferobject.dto.CombinationIdDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentCollegeShortListDTO;

import com.lodestar.edupath.datatransferobject.dto.elective.CombinationDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;

public class StudentCollegeShortListDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(StudentCollegeShortListDAO.class);

	/**
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public List<StudentCollegeShortListDTO> getShortListedCollegeByStudentId(int studentId) throws Exception
	{
		OUT.debug("Getting shortlisted college for student id : {}", studentId);
		List<StudentCollegeShortListDTO> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.STUDENT_COLLEGE_SHORTLIST_GET_BY_STUDENTID,
				studentId);
		
		
		//Start SASEDEVE Edited By Mrutyunjay on Date 07-06-2017
		
		List<StudentCollegeShortListDTO> resultList1=new ArrayList<StudentCollegeShortListDTO>();
		if(resultList!=null)
		{
			
			List<CombinationIdDTO> combinationlist = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.List_Combination_report, studentId);
			
			
			CombinatioListDTO CombinationId=new CombinatioListDTO();
			List<Integer> comlist=new ArrayList<Integer>();
			for (CombinationIdDTO combination : combinationlist)
			{
				comlist.add(new Integer(combination.getCombinationId()));
				
			}
			CombinationId.setIdsList(comlist);
			
			List<EduPathShortListDTO> eduPathShortListDTOs = null;
			eduPathShortListDTOs = new EduPathShortListDAO().getEduPathShortListForCartByStudentId(studentId);
			for (EduPathShortListDTO eduPathShortListDTO : eduPathShortListDTOs)
			{
				CombinationId.setStreamId(eduPathShortListDTO.getPuStreamId());
				break;
			}
			
			
			
			
			for(StudentCollegeShortListDTO clg:resultList)
			{
				
				CombinationId.setCollegeId(clg.getCollegeId());
				
				StudentCollegeShortListDTO clgtemp_seat= (StudentCollegeShortListDTO)MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.STUDENT_COLLEGE_SHORTLIST_Seat,CombinationId);
				
				StudentCollegeShortListDTO clgtemp_fee= (StudentCollegeShortListDTO)MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.STUDENT_COLLEGE_SHORTLIST_Free,CombinationId);
				
				StudentCollegeShortListDTO clgtemp_appcost_pass_teaching_Cutof=(StudentCollegeShortListDTO)MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.STUDENT_COLLEGE_SHORTLIST_appcost_pass_teaching_Cutof,CombinationId);
				
				
				if(clgtemp_seat!=null && clgtemp_seat.getSeats()!=null)
				{
					clg.setSeats(clgtemp_seat.getSeats());
				}
				
				
				if(clgtemp_fee!=null && clgtemp_fee.getFee()!=null)
				{
					clg.setFee(clgtemp_fee.getFee());
				}
				
				
				if(clgtemp_appcost_pass_teaching_Cutof!=null && clgtemp_appcost_pass_teaching_Cutof.getCut()!=null)
				{
					clg.setCut(clgtemp_appcost_pass_teaching_Cutof.getCut());
				}
				
				
				
				if(clgtemp_appcost_pass_teaching_Cutof!=null && clgtemp_appcost_pass_teaching_Cutof.getPass()!=null)
				{
					clg.setPass(clgtemp_appcost_pass_teaching_Cutof.getPass());
				}
				
				
				
				if(clgtemp_appcost_pass_teaching_Cutof!=null && clgtemp_appcost_pass_teaching_Cutof.getApplicationCost()!=null)
				{
					clg.setApplicationCost(clgtemp_appcost_pass_teaching_Cutof.getApplicationCost());
				}
				
				
				
				if(clgtemp_appcost_pass_teaching_Cutof!=null && clgtemp_appcost_pass_teaching_Cutof.getTeachingStaff()!=null)
				{
					clg.setTeachingStaff(clgtemp_appcost_pass_teaching_Cutof.getTeachingStaff());
				}
				
				resultList1.add(clg);
				
			}
		}
		
		
		
		
		OUT.debug("Number of shortlisted college found : {}", resultList != null ? resultList.size() : 0);
		return resultList1;
		
		
		//END SASEDEVE Edited By Mrutyunjay on Date 07-06-2017
		
	}

	/**
	 * @param session
	 * @param id
	 * @throws Exception
	 */
	public void deleteColShortListById(SqlSession session, int id) throws Exception
	{
		OUT.debug("deleting shortlisted college for id : {}", id);
		session.delete(MyBatisMappingConstants.STUDENT_COLLEGE_SHORTLIST_DELETE_BY_ID, id);
	}

	public void deleteColShortListById(int id) throws Exception
	{
		OUT.debug("deleting shortlisted college for id : {}", id);
		MyBatisManager.getInstance().deleteObjectById(MyBatisMappingConstants.STUDENT_COLLEGE_SHORTLIST_DELETE_BY_ID, id);
	}
	

	/**
	 * @param sqlSession
	 * @param collegeShortListDTO
	 * @throws Exception
	 */
	public void insertStudentCollegeShortList(SqlSession sqlSession, StudentCollegeShortListDTO collegeShortListDTO) throws Exception
	{
		OUT.debug("Inserting student college short list details studentId: {}. collegeId: {}", collegeShortListDTO.getStudentId(),
				collegeShortListDTO.getCollegeId());
		if (sqlSession == null)
		{
			MyBatisManager.getInstance().insert(MyBatisMappingConstants.STUDENT_COLLEGE_SHORTLIST_INSERT, collegeShortListDTO);
		}
		else
		{
			sqlSession.insert(MyBatisMappingConstants.STUDENT_COLLEGE_SHORTLIST_INSERT, collegeShortListDTO);
		}
	}

	/**
	 * @param sqlSession
	 * @param collegeIds
	 * @param studentId
	 * @throws Exception
	 */
	public void insertStudentCollegeShortList(SqlSession sqlSession, List<Integer> collegeIds, int studentId) throws Exception
	{
		StudentCollegeShortListDTO studentCollegeShortListDTO = null;
		for (Integer collegeId : collegeIds)
		{
			studentCollegeShortListDTO = new StudentCollegeShortListDTO();
			studentCollegeShortListDTO.setCollegeId(collegeId);
			studentCollegeShortListDTO.setStudentId(studentId);
			insertStudentCollegeShortList(sqlSession, studentCollegeShortListDTO);
		}
	}
}
