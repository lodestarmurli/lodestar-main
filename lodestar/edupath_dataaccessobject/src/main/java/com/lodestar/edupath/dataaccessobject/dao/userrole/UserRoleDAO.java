package com.lodestar.edupath.dataaccessobject.dao.userrole;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.role.UserRoleDTO;

public class UserRoleDAO
{
	private static final Logger	OUT  = LoggerFactory.getLogger(UserRoleDAO.class);
	
	public UserRoleDTO getUserRoleByName(UserRoleDTO userRoleDTO) throws Exception
	{
		UserRoleDTO userRole = (UserRoleDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_USERROLE_BY_NAME, userRoleDTO);
		return userRole;
	}
	
	
}
