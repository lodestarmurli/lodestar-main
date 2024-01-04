package com.lodestar.edupath.entranceexam.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.ShortListExams.ShortListExamsDAO;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.datatransferobject.dto.EntranceExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.ShortListExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class EntranceExamsService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(EntranceExamsService.class);

	public List<EntranceExamsDTO> getExamDetailsByOccId(int occId)
	{
		List<EntranceExamsDTO> entranceExamlist = new ArrayList<EntranceExamsDTO>();
		try
		{
			List<EntranceExamsDTO> list = new EntranceExamsDAO().getEntranceExamsDetailsByOccupatioId(occId);
			for (EntranceExamsDTO examsDTO : list)
			{
				examsDTO.setExamName(CommonUtil.replaceXMLEntities(examsDTO.getExamName()));
				examsDTO.setExamWhen(CommonUtil.replaceXMLEntities(examsDTO.getExamWhen()));
				examsDTO.setAboutExam(CommonUtil.replaceXMLEntities(examsDTO.getAboutExam()));
				examsDTO.setEligibility(CommonUtil.replaceXMLEntities(examsDTO.getEligibility()));
				examsDTO.setSubNMarks(CommonUtil.replaceXMLEntities(examsDTO.getSubNMarks()));
				examsDTO.setMonthOfExam(CommonUtil.replaceXMLEntities(examsDTO.getMonthOfExam()));
				examsDTO.setInsititutesAcceptScore(CommonUtil.replaceXMLEntities(examsDTO.getInsititutesAcceptScore()));
				examsDTO.setAnnualFee(CommonUtil.replaceXMLEntities(examsDTO.getAnnualFee()));
				examsDTO.setRatio(CommonUtil.replaceXMLEntities(examsDTO.getRatio()));
				examsDTO.setExamLevel(CommonUtil.replaceXMLEntities(examsDTO.getExamLevel()));

				entranceExamlist.add(examsDTO);
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return entranceExamlist;
	}

	public void addAllEntrExamsDetails(List<ShortListExamsDTO> shortListExamsList, String studentLoginId, String userLoginId, int i, int LockcityId)
	{
		SqlSession session = null;
		
		 
		boolean isTrue = false;
		try
		{
			List<EntranceExamsDTO> entranceExamList = new EntranceExamsDAO().getAllEntranceExam();
			Map<Integer, String> examMap = new HashMap<Integer, String>();
			if (entranceExamList != null && !entranceExamList.isEmpty())
			{
				for (EntranceExamsDTO entranceExamsDTO : entranceExamList)
				{
					examMap.put(entranceExamsDTO.getId(), entranceExamsDTO.getExamName());
				}
			}
			session = MyBatisManager.getInstance().getSession();
			
			ShortListExamsDAO shortListExamsDAO = new ShortListExamsDAO();
			
			//Vyankatesh 14-4-2017
			
			//String stuCityId = shortListExamsDAO.getExamStudentCityId(i);
			
			//System.out.println("===================stuCityId"+stuCityId);
			
			
			
			for (ShortListExamsDTO shortListExamsDTO : shortListExamsList)
			{
				isTrue = shortListExamsDAO.isIdExist(shortListExamsDTO);
				if (!isTrue)
				{
					shortListExamsDAO.insertShortListEntrExams(session, shortListExamsDTO);
					AuditTrailLogger.addAuditInfo(ModuleNameEnum.EDU_PATH,
							"student: " + studentLoginId + " , " + "Exam name: " + examMap.get(shortListExamsDTO.getEntranceExamId()) + " is saved into Short list exams",
							userLoginId);
				}
			}
			session.commit();
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

	}

	

	public List<OccupationDTO> getOccWithExamsByIndusId(int industryId)
	{
		List<OccupationDTO> occList = null;
		try
		{
			occList = new OccupationDAO().getOccupationWithExamsByIndustryid(industryId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return occList;
	}

	public int getExamsCount(int studentId)
	{
		int examsCount = 0;
		try
		{
			 examsCount = new ShortListExamsDAO().getShortListExamsCount(studentId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return examsCount;
	}

	
	//added vyankatesh
	public List<EntranceExamsDTO> getExamDetailsByOccId(int occId, Integer statenational) {
		List<EntranceExamsDTO> entranceExamlist = new ArrayList<EntranceExamsDTO>();
		List<EntranceExamsDTO> list=null;
		try
		{
			if(statenational==0)
			{
				 list = new EntranceExamsDAO().getEntranceExamsDetailsByOccupatioIdNational(occId);
			}
			else
			{
				 list = new EntranceExamsDAO().getEntranceExamsDetailsByOccupatioIdstate(occId,statenational);
			}
			
			//List<EntranceExamsDTO> list = new EntranceExamsDAO().getEntranceExamsDetailsByOccupatioId(occId);
			for (EntranceExamsDTO examsDTO : list)
			{
				examsDTO.setExamName(CommonUtil.replaceXMLEntities(examsDTO.getExamName()));
				examsDTO.setExamWhen(CommonUtil.replaceXMLEntities(examsDTO.getExamWhen()));
				examsDTO.setAboutExam(CommonUtil.replaceXMLEntities(examsDTO.getAboutExam()));
				examsDTO.setEligibility(CommonUtil.replaceXMLEntities(examsDTO.getEligibility()));
				examsDTO.setSubNMarks(CommonUtil.replaceXMLEntities(examsDTO.getSubNMarks()));
				examsDTO.setMonthOfExam(CommonUtil.replaceXMLEntities(examsDTO.getMonthOfExam()));
				examsDTO.setInsititutesAcceptScore(CommonUtil.replaceXMLEntities(examsDTO.getInsititutesAcceptScore()));
				examsDTO.setAnnualFee(CommonUtil.replaceXMLEntities(examsDTO.getAnnualFee()));
				examsDTO.setRatio(CommonUtil.replaceXMLEntities(examsDTO.getRatio()));
				examsDTO.setExamLevel(CommonUtil.replaceXMLEntities(examsDTO.getExamLevel()));

				entranceExamlist.add(examsDTO);
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return entranceExamlist;
		
	}
	
	
	//added Vyankatesh
	public void removeFromShortListExams(int i, int lockcityId) throws Exception {
		ShortListExamsDTO shortListExamsDTO = new ShortListExamsDTO();
		shortListExamsDTO.setStudentId(i);
		shortListExamsDTO.setCityId(lockcityId);

		new ShortListExamsDAO().removeFromShortListExams(shortListExamsDTO);
		
	}
	
	
}
