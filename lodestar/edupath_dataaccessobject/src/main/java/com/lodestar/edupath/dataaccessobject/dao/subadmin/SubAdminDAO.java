package com.lodestar.edupath.dataaccessobject.dao.subadmin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.SubAdminDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class SubAdminDAO
{

	private static final Logger	OUT	= LoggerFactory.getLogger(SubAdminDAO.class);

	public List<SubAdminDTO> getAllSubAdmin() throws Exception
	{
		List<SubAdminDTO> resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_SUBADMIN, null);
		OUT.debug("Number of SubAdmin  found: {}", resultList != null ? resultList.size() : 0);
		return resultList;
	}

	public SubAdminDTO getSubAdminDetailsById(SubAdminDTO subAdmin) throws Exception
	{
		SubAdminDTO subAdminDTO = (SubAdminDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_SUBADMIN_BY_ID, subAdmin.getId());
		return subAdminDTO;
	}

	public int insertSubAdmin(SqlSession session, SubAdminDTO subadminDTO)
	{
		int insertId = 0;
		try
		{
			insertId = session.insert(MyBatisMappingConstants.INSERT_SUBADMIN, subadminDTO);
			OUT.debug("Insert SubAdmin id: {}", insertId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return insertId;
	}

	public void deleteSubAdminById(SubAdminDTO subadminDTO) throws Exception
	{
		MyBatisManager.getInstance().deleteObjectByModel(MyBatisMappingConstants.DELETE_SUBADMIN_BY_ID, subadminDTO);
	}

	public int updateSubAdminById(SqlSession session, SubAdminDTO subadminDTO)
	{
		int id = 0;
		try
		{
			 id = session.update(MyBatisMappingConstants.UPDATE_SUBADMIN_BY_ID, subadminDTO);
			OUT.debug("Update SubAdmin id: {}", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return id;
	}

	public SubAdminDTO getSubAdminDetailsByUserId(UserDetailDTO userDetailDTO) throws Exception
	{
		SubAdminDTO subAdminDTO = (SubAdminDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_SUBADMIN_DETAIL_BY_USERID,
				userDetailDTO.getId());
		return subAdminDTO;
	}
	
	public SubAdminDTO getSubAdminByEmailId(SubAdminDTO subAdminDTO)
	{
		SubAdminDTO dto = null;
		try
		{
			dto = (SubAdminDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_SUBADMIN_BY_EMAIL_ID, subAdminDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION , e);
		}
		return dto;
	}
}
