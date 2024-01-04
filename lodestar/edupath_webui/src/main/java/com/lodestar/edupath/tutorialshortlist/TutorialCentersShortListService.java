package com.lodestar.edupath.tutorialshortlist;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.tutorialshortlist.StudentTutorialCentreShortListDAO;
import com.lodestar.edupath.datatransferobject.dto.StudentTutorialCentreShortListDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class TutorialCentersShortListService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(TutorialCentersShortListService.class);

	public boolean insertShortListTutorialCenters(Map<Integer, Integer> tutorialCenterObject, int studentId, String userLoginId)
	{
		SqlSession session = null;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			StudentTutorialCentreShortListDTO centreShortListDTO;
			for (Entry<Integer, Integer> tutorialCenterSet : tutorialCenterObject.entrySet())
			{
				centreShortListDTO = new StudentTutorialCentreShortListDTO();
				centreShortListDTO.setStudentId(studentId);
				centreShortListDTO.setTutorialId(tutorialCenterSet.getValue());
				centreShortListDTO.setTutorialCityCentersId(tutorialCenterSet.getKey());
				new StudentTutorialCentreShortListDAO().insertShortListTutorialCenter(session, centreShortListDTO);
				AuditTrailLogger.addAuditInfo(ModuleNameEnum.TUTORIAL_CART, "Tutorial id : " + tutorialCenterSet.getValue() + ", Tutorial Center Id : "
						+ tutorialCenterSet.getKey() + " is saved into StudentTutorialCentersShortList for student Id : " + studentId, userLoginId);
			}
			session.commit();
			return true;
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return false;
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
	}

	public List<StudentTutorialCentreShortListDTO> getShortListedTutorialCenters(int studentId) throws Exception
	{
		return new StudentTutorialCentreShortListDAO().getShortListedTutorialCentersByStudentId(studentId);
	}

	public void deleteTutCenterShortListById(int tutCenterShortListId, int studentId, String userLoginId) throws Exception
	{
		SqlSession session = null;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			new StudentTutorialCentreShortListDAO().deleteTutCenterShortListById(session, tutCenterShortListId);
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.TUTORIAL_CART, "Tutorial Center ShortList id : " + tutCenterShortListId +  " is deleted from StudentTutorialCentersShortList for student Id : " + studentId, userLoginId);
			session.commit();
		}
		catch (Exception e)
		{
			if(null != session)
			{
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (null != session)
			{
				session.close();
			}
		}
	}
}
