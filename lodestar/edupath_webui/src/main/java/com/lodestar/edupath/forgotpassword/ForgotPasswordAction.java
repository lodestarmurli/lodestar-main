package com.lodestar.edupath.forgotpassword;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.ForgotPasswordRequestDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.forgotpassword.service.ForgotPasswordService;

public class ForgotPasswordAction extends BaseAction
{

	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(ForgotPasswordAction.class);
	private String				emailId;
	private String				user;
	private int 				userId;
	
	public String execute()
	{

		OUT.info("ForgotPasswordAction: inside execute method");

		return SUCCESS;
	}

	public String authenticate()
	{
		OUT.info("ForgotPasswordAction : authenticating email id");
		try
		{
			emailId = request.getParameter("emailId");
			boolean isValidEmail = new ForgotPasswordService().validateEmailId(emailId);
			if (isValidEmail)
			{
				user = "validUser";
			}
			else
			{
				addActionError(getText("com.edupath.forgotscreen.emailid.invalid.error"));
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;

	}
	
	public String reset()
	{
		try
		{
			OUT.info("ForgotPasswordAction: inside reset method");
			String code = request.getParameter("code");
			ForgotPasswordRequestDTO forgotPasswordRequestDTO = new ForgotPasswordService().verifyCode(code);
			if (forgotPasswordRequestDTO!=null)
			{
				userId = forgotPasswordRequestDTO.getUserId();
			}
			else 
			{
				userId = 0;
				addActionError(getText("com.edupath.forgotscreen.time.invalid.error"));
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return "validData";
		
	}
	
	public String updatePassword()
	{
		try
		{
			OUT.info("ForgotPasswordAction: inside update method");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirmPassword");
			userId = Integer.parseInt(request.getParameter("userId"));
			
			if (password.equals(confirmPassword) && password!= null && password.length()> 5)
			{
				boolean isUpdated = new ForgotPasswordService().updateUserDetailsById(userId , password);
				if (isUpdated)
				{
					return "passconfirm";
				}
				else 
				{
					addActionError(getText("com.edupath.action.internal.error"));
				}
			}
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			addActionError(getText("com.edupath.action.internal.error"));
		}
		return "validData";
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

}
