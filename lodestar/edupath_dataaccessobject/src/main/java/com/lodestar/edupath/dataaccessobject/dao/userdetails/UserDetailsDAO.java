package com.lodestar.edupath.dataaccessobject.dao.userdetails;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class UserDetailsDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(UserDetailsDAO.class);

	/*
	 * public List<UserDetailDTO> getAllUserDetails() throws Exception {
	 * List<UserDetailDTO> resultList =
	 * MyBatisManager.getInstance().getResultAsList
	 * (MyBatisMappingConstants.GET_ALL_GLOBAL_SETTINGS, null);
	 * OUT.debug("User Details Founded :", resultList.size()); return
	 * resultList; }
	 */

	public UserDetailDTO getUserDetailByLoginId(UserDetailDTO userDetailDTO) throws Exception
	{
		UserDetailDTO userDTO = (UserDetailDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_USER_BY_LOGINID, userDetailDTO);
		return userDTO;
	}

	public UserDetailDTO getUserDetailByLoginIdAndId(UserDetailDTO userDetailDTO) throws Exception
	{
		UserDetailDTO userDTO = (UserDetailDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_USER_BY_LOGINID_AND_ID, userDetailDTO);
		return userDTO;
	}

	public int insertUserDetail(SqlSession session, UserDetailDTO userDetailDTO)
	{
		int id = 0;
		try
		{
			userDetailDTO.setCreatedOn(new Date());
			id = session.insert(MyBatisMappingConstants.ADD_USER_DETAILS, userDetailDTO);
			OUT.debug("User Details insert id :{}", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return userDetailDTO.getId();
	}

	public int updateUserDetailsById(SqlSession session, UserDetailDTO userDetailDTO)
	{
		int id = 0;
		try
		{
			userDetailDTO.setUpdatedOn(new Date());
			id = session.update(MyBatisMappingConstants.UPDATE_USER_DETAILS_BYID, userDetailDTO);
			OUT.debug("User Details update id :{}", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return userDetailDTO.getId();
	}

	public void deleteUserDetailsById(UserDetailDTO userDetailDTO) throws Exception
	{
		OUT.debug("User Details id :{}", userDetailDTO.getId());
		MyBatisManager.getInstance().deleteObjectByModel(MyBatisMappingConstants.DELETE_USER_DETAILS_BYID, userDetailDTO);
	}

	public boolean deleteUserDetailsById(int id)
	{
		try
		{
			MyBatisManager.getInstance().deleteObjectById(MyBatisMappingConstants.DELETE_USER_DETAILS_BY_ID, id);
			return true;
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return false;
		}
	}

	public void deleteUserDetailsByLoginId(UserDetailDTO userDetailDTO) throws Exception
	{
		MyBatisManager.getInstance().deleteObjectByModel(MyBatisMappingConstants.DELETE_STUDENT_DETAIL_BY_LOGINID, userDetailDTO);

	}

	public int lastUserDetailId() throws Exception
	{
		return MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.GET_MAX_USER_DETAILS_ID, new UserDetailDTO());
	}

	public int updatePasswordForUserDetailsByid(SqlSession session, UserDetailDTO userDetailDTO)
	{
		int id = 0;
		try
		{
			userDetailDTO.setUpdatedOn(new Date());
			id = session.update(MyBatisMappingConstants.UPDATE_PASSWORD_FOR_USER_DETAILS_BYID, userDetailDTO);
			OUT.debug("User Details update id :{}", id);
			return id;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return id;
	}

	public UserDetailDTO getUserDetailByIdAndRoleId(UserDetailDTO userDetailDTO)
	{
		UserDetailDTO detailDTO = null;
		try
		{
			detailDTO = (UserDetailDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_USER_DETAILS_BY_ID_AND_ROLEID, userDetailDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return detailDTO;
	}

	public boolean updateUserDetailsById(UserDetailDTO userDetailDTO)
	{
		boolean isUpdated = false;
		try
		{
			userDetailDTO.setUpdatedOn(new Date());
			int id = MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_PASSWORD_FOR_USER_DETAILS_BYID, userDetailDTO);
			if (id > 0)
			{
				isUpdated = true;
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return isUpdated;
	}

	public void updateUserDetailsIsActiveById(SqlSession session, UserDetailDTO userDetailDTO)
	{
		OUT.debug("Updating user details isActive by id : {}", userDetailDTO.getId());
		userDetailDTO.setUpdatedOn(new Date());
		session.update(MyBatisMappingConstants.UPDATE_USER_DETAILS_ISACTIVE_BY_ID, userDetailDTO);
	}

}
