package com.lodestar.edupath.changepassword;

import java.io.PrintWriter;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.changepassword.service.ChangePasswordService;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.util.AESCipher;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class ChangePasswordAction extends BaseAction
{

	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(ChangePasswordAction.class);
	private String				oldPassword;
	private String				newPassword;
	private String				confirmNewPassword;

	public String execute()
	{
		OUT.debug("ChangePasswordAction : inside execute method");
		try
		{
			PrintWriter writer = response.getWriter();
			JSONObject json = new JSONObject();

			int roleId = getUserSessionObject().getRoleId();
			int id = getUserSessionObject().getId();

			UserDetailDTO userDetailDTO = new UserDetailDTO();
			userDetailDTO.setId(id);
			userDetailDTO.setRoleId(roleId);
			UserDetailDTO detailDTO = new ChangePasswordService().getPasswordToChange(userDetailDTO);
			if (detailDTO != null)
			{
				String password = new String(AESCipher.decrypt(detailDTO.getPassword()));

				if (oldPassword.equals(password))
				{
					userDetailDTO.setPassword(AESCipher.encrypt(newPassword.getBytes()));
					boolean isUpdated = new ChangePasswordService().saveNewPassword(userDetailDTO);
					if (isUpdated)
					{
						json.put("messageSucc", getText("com.edupath.change.password.new.update.successful"));
					}
					else
					{
						// addActionError(getText("com.edupath.action.internal.error"));
						json.put("messageErr", getText("com.edupath.action.internal.error"));
					}
				}
				else
				{
					// addActionError(getText("com.edupath.change.password.old.incorrect"));
					json.put("messageErr", getText("com.edupath.change.password.old.incorrect"));
				}
			}
			writer.write(json.toString());
			OUT.debug("change password status: {}", json.toString());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public String getOldPassword()
	{
		return oldPassword;
	}

	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword()
	{
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword)
	{
		this.confirmNewPassword = confirmNewPassword;
	}
}
