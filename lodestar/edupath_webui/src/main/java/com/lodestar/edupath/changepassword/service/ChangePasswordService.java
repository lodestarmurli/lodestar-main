package com.lodestar.edupath.changepassword.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.userdetails.UserDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;

public class ChangePasswordService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ChangePasswordService.class);

	public UserDetailDTO getPasswordToChange(UserDetailDTO userDetailDTO)
	{
		return new UserDetailsDAO().getUserDetailByIdAndRoleId(userDetailDTO);
	}

	public boolean saveNewPassword(UserDetailDTO userDetailDTO)
	{
		return new UserDetailsDAO().updateUserDetailsById(userDetailDTO);
	}

}
