package com.lodestar.edupath.school.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;

import com.lodestar.edupath.dataaccessobject.dao.school.SchoolDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.exception.EdupathException;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class ManageSchoolService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ManageSchoolService.class);

	public List<SchoolDTO> getAllSchools() throws Exception
	{
		return new SchoolDAO().getAllSchool();
	}

	public SchoolDTO getSchoolById(int id) throws Exception
	{
		return new SchoolDAO().getSchoolById(id);
	}

	public boolean insert(SchoolDTO schoolDTO, String userLoginId) throws Exception
	{
		SchoolDAO schoolDAO = new SchoolDAO();
		validateIsExist(schoolDTO, schoolDAO);
		SqlSession session = null;
		Integer id = 41;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			 id=schoolDAO.insert(session, schoolDTO);

			 if (id != null)
				{
				 schoolDTO.setSchoolId(id);
				 schoolDAO.insertSchoolMapping(session, schoolDTO);
				}
			
			AuditTrailLogger.addAuditInfo(session, ModuleNameEnum.USER_DEFINITION, "School :" + schoolDTO.getName() + " created", userLoginId);
			session.commit();
			return true;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			if (session != null)
			{
				session.rollback();
			}
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

	private void validateIsExist(SchoolDTO schoolDTO, SchoolDAO schoolDAO) throws Exception
	{
		if (schoolDAO.isExist(schoolDTO))
		{
			throw new EdupathException("com.edupath.action.add.msg.already.exist", "");
		}

	}

	public boolean update(SchoolDTO schoolDTO, String userLoginId) throws Exception
	{
		SchoolDAO schoolDAO = new SchoolDAO();
		validateIsExist(schoolDTO, schoolDAO);
		SqlSession session = null;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			schoolDAO.update(session, schoolDTO);
			AuditTrailLogger.addAuditInfo(session, ModuleNameEnum.USER_DEFINITION, "School :" + schoolDTO.getName() + " updated", userLoginId);
			session.commit();
			return true;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			if (session != null)
			{
				session.rollback();
			}
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

	public boolean updatewithoutpath(SchoolDTO schoolDTO, String userLoginId) throws Exception
	{
		SchoolDAO schoolDAO = new SchoolDAO();
		validateIsExist(schoolDTO, schoolDAO);
		SqlSession session = null;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			schoolDAO.updateWOPath(session, schoolDTO);
			AuditTrailLogger.addAuditInfo(session, ModuleNameEnum.USER_DEFINITION, "School :" + schoolDTO.getName() + " updated", userLoginId);
			session.commit();
			return true;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			if (session != null)
			{
				session.rollback();
			}
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

	
	
	public boolean checkDependency(int id) throws Exception
	{
		return new StudentDetailsDAO().isExistBySchoolId(id);
	}

	public void delete(int id, String userLoginId)
	{
		SqlSession session = null;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			new SchoolDAO().delete(session, id);
			AuditTrailLogger.addAuditInfo(session, ModuleNameEnum.USER_DEFINITION, "School Id :" + id + " deleted", userLoginId);
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

	public List<CityDTO> getCityNameWithId()
	{
		List<CityDTO> list = null;
		try
		{
			list = new SchoolDAO().getCityNameWithId();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		return list;
	}

}
