package com.lodestar.edupath.dataaccessobject.dao.elective;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.calculatefitmentcolor.CalculateFitmentColor;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathElectiveShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.summaryreport.OccupationIndustryVO;

public class EduPathShortListDAO
{
	private static Logger	OUT	= LoggerFactory.getLogger(EduPathShortListDAO.class);

	public List<EduPathShortListDTO> getEduPathShortListByStudentId(Integer studentId) throws Exception
	{
		List<EduPathShortListDTO> finalList = null;
		finalList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_EDUPATH_SHORTLIST_BY_STUDENTID, studentId.intValue());
		OUT.debug("Shortlist size : {}", null != finalList ? finalList.size() : 0);
		return finalList;
	}

	public List<EduPathShortListDTO> getEduPathShortListForCartByStudentId(Integer studentId) throws Exception
	{
		List<EduPathShortListDTO> finalList = null;
		List<EduPathShortListDTO> finalList1 = new ArrayList<EduPathShortListDTO>();
		finalList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_EDUPATH_SHORTLIST_FOR_CART_BY_STUDENTID, studentId.intValue());
		OUT.debug("Shortlist size : {}", null != finalList ? finalList.size() : 0);
		
		
		//Start SASEDEVE Edited By Mrutyunjaya on Date 25-05-2017
		
		CalculateFitmentColor Calfit=new CalculateFitmentColor();
		finalList1=Calfit.CalculatefitmentforEdupathShortList(finalList,studentId.intValue());
		
		
		
		
		
		//return finalList;
		
		return finalList1;
		//END SASEDEVE Edited By Mrutyunjaya on Date 25-05-2017
	}

	public void removeEdupathShortListByIdNStudentId(SqlSession session, int studentId) throws Exception
	{
		session.delete(MyBatisMappingConstants.DELETE_EDUPATH_SHORTLIST_BY_STUDENTID, studentId);
	}

	public void insertEdupathNElective(EduPathShortListDTO edupath1, EduPathShortListDTO edupath2, List<EduPathElectiveShortListDTO> shortListItems)
			throws Exception
	{
		SqlSession session = null;
		try
		{
			session = MyBatisManager.getInstance().getSession();

			session.insert(MyBatisMappingConstants.INSERT_EDUPATH_SHORTLIST, edupath1);
			if (edupath2 != null)
			{
				session.insert(MyBatisMappingConstants.INSERT_EDUPATH_SHORTLIST, edupath2);
			}
			for (EduPathElectiveShortListDTO shortListItem : shortListItems)
			{
				session.insert(MyBatisMappingConstants.INSERT_EDUPATH_ELECTIVE_SHORTLIST, shortListItem);
			}
			session.commit();
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
	}

	public List<EduPathShortListDTO> getEduPathShortListByActiveStudentId(int studentId) throws Exception
	{
		List<EduPathShortListDTO> finalList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_EDUPATH_SHORTLIST_ACTIVE_BY_STUDENTID,
				studentId);
		OUT.debug("Shortlist size : {}", null != finalList ? finalList.size() : 0);
		return finalList;
	}

	/**
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public int getStreamIdByStudentId(int studentId) throws Exception
	{
		OUT.debug("Getting stream id by studentId: {}", studentId);
		Integer streamId = (Integer) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_STREAM_ID_BY_STUDENT_ID, studentId);
		OUT.debug("Stream id : {} for student id : {}", streamId, studentId);
		return streamId != null ? streamId : 0;

	}

	/**
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public OccupationIndustryVO getOccNIndustryDetail(int studentId) throws Exception
	{
		OUT.debug("Getting edupath occupation and industry for studentId : {}", studentId);
		OccupationIndustryVO occupationIndustryVO = (OccupationIndustryVO) MyBatisManager.getInstance().getResultAsObject(
				MyBatisMappingConstants.GET_OCC_N_INDUSTRY_DETAILS_BY_STUDENT_ID, studentId);
		OUT.debug("Occupation and industry details {} for studentId: {}", null != occupationIndustryVO ? " FOUND " : " NOT FOUND ", studentId);
		return occupationIndustryVO;

	}
}
