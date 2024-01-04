package com.lodestar.edupath.subadmin.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.subadmin.SubAdminDAO;
import com.lodestar.edupath.dataaccessobject.dao.userdetails.UserDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.userrole.UserRoleDAO;
import com.lodestar.edupath.datatransferobject.dto.SubAdminDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserRoleDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.UserTypeEnum;
import com.lodestar.edupath.datatransferobject.util.AESCipher;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.util.PasswordGeneratorService;

public class SubAdminService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(SubAdminService.class);

	private String				auditDateStr;

	public List<SubAdminDTO> getAllSubAdminList()
	{
		List<SubAdminDTO> resultlist = null;
		OUT.debug("Inside SubAdminService (getAllSubAdminList method)");
		try
		{
			SubAdminDAO subAdminDAO = new SubAdminDAO();
			resultlist = subAdminDAO.getAllSubAdmin();
		}
		catch (Exception e)
		{
			OUT.error("Exception ", e);
		}
		return resultlist;
	}

	public SubAdminDTO getSubadminById(SubAdminDTO subadminDTO)
	{
		SubAdminDTO result = null;
		try
		{
			SubAdminDAO subAdminDAO = new SubAdminDAO();
			result = subAdminDAO.getSubAdminDetailsById(subadminDTO);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return result;
	}

	public Boolean insertSubAdmin(SubAdminDTO subAdminDTO)
	{
		SqlSession session = null;
		String password = null;
		try
		{
			UserDetailDTO userDTO = new UserDetailDTO();
			userDTO.setLoginId(subAdminDTO.getEmailId());

			UserDetailsDAO userDAO = new UserDetailsDAO();
			userDTO = userDAO.getUserDetailByLoginId(userDTO);
			if (null != userDTO)
			{
				return false;
			}

			session = MyBatisManager.getInstance().getSession();

			UserRoleDTO roleDTO = new UserRoleDTO();
			roleDTO.setName(UserTypeEnum.SUBADMIN.getDisplayName());

			UserRoleDAO roleDAO = new UserRoleDAO();
			roleDTO = roleDAO.getUserRoleByName(roleDTO);

			password = PasswordGeneratorService.getRandomAlphanumeric();

			UserDetailDTO user = new UserDetailDTO();
			user.setLoginId(subAdminDTO.getEmailId());
			user.setRoleId(roleDTO.getRoleTypeId());
			user.setUserType(UserTypeEnum.SUBADMIN.getDisplayName());
			user.setIsActive("Y");
			user.setPassword(AESCipher.encrypt(password.getBytes()));

			int userId = userDAO.insertUserDetail(session, user);
			if (userId == 0)
			{
				return false;
			}

			SubAdminDAO subDAO = new SubAdminDAO();
			subAdminDTO.setUserId(userId);
			int id = subDAO.insertSubAdmin(session, subAdminDTO);
			if (id == 0)
			{
				return false;
			}
			session.commit();
			if (user.getCreatedOn() != null)
			{
				auditDateStr = TimeUtil.getDateFromMillis(user.getCreatedOn().getTime(), TimeUtil.QUERY_DATE_FORMAT);
			}
			// send mail
			PasswordGeneratorService.sendNewNotification(userId, subAdminDTO.getName(), subAdminDTO.getEmailId(), roleDTO.getRoleTypeId(), password);
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error("Exception ", e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		return true;
	}

	public Boolean updateSubAdminDetailsById(SubAdminDTO subAdminDTO)
	{
		SqlSession session = null;
		try
		{
			UserDetailDTO userDTO = new UserDetailDTO();
			userDTO.setLoginId(subAdminDTO.getEmailId());
			userDTO.setId(subAdminDTO.getUserId());

			UserDetailsDAO userDAO = new UserDetailsDAO();
			userDTO = userDAO.getUserDetailByLoginIdAndId(userDTO);
			if (null != userDTO)
			{
				return false;
			}

			session = MyBatisManager.getInstance().getSession();

			UserDetailDTO user = new UserDetailDTO();
			user.setLoginId(subAdminDTO.getEmailId());
			user.setId(subAdminDTO.getUserId());
			user.setIsActive("Y");
			int userId = userDAO.updateUserDetailsById(session, user);
			if (userId == 0)
			{
				return false;
			}
			SubAdminDAO subDAO = new SubAdminDAO();
			subAdminDTO.setUserId(subAdminDTO.getUserId());
			int id = subDAO.updateSubAdminById(session, subAdminDTO);
			if (id == 0)
			{
				return false;
			}
			session.commit();
			if (user.getUpdatedOn() != null)
			{
				auditDateStr = TimeUtil.getDateFromMillis(user.getUpdatedOn().getTime(), TimeUtil.QUERY_DATE_FORMAT);
			}
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error("Exception ", e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		return true;
	}

	public Boolean deleteSubAdminById(SubAdminDTO subAdminDTO)
	{
		try
		{
			SubAdminDAO subDAO = new SubAdminDAO();
			UserDetailsDAO userDAO = new UserDetailsDAO();

			UserDetailDTO user = new UserDetailDTO();
			user.setId(subAdminDTO.getUserId());

			userDAO.deleteUserDetailsById(user);
			subDAO.deleteSubAdminById(subAdminDTO);
			auditDateStr = TimeUtil.getDateFromMillis(System.currentTimeMillis(), TimeUtil.QUERY_DATE_FORMAT);
		}
		catch (Exception e)
		{
			OUT.error("Exception ", e);
		}
		return true;
	}

	public static void main(String[] args) throws Exception
	{
		// Map<String, Object> parameterObject = new HashMap<>();
		// parameterObject.put("schoolId", 2);
		// List<UserDetailDTO> resultList = MyBatisManager.getInstance().getResultList("UserDetail.tempQuery", parameterObject);
		// for (UserDetailDTO userDetailDTO : resultList)
		// {
		// System.out.println(userDetailDTO.getLoginId()+"/"+new String(AESCipher.decrypt(userDetailDTO.getPassword())));
		// }

		SessionScheduleDetailsDTO scheduleDetailsDTO = new SessionScheduleDetailsDAO().getSessionByUserId(2, 106);
		System.out.println(scheduleDetailsDTO);
	}

	public String getAuditDateStr()
	{
		return auditDateStr;
	}
}
