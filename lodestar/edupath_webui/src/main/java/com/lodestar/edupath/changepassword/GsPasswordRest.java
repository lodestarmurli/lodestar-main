package com.lodestar.edupath.changepassword;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.changepassword.service.ChangePasswordService;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.util.AESCipher;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class GsPasswordRest  extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(GsPasswordRest.class);
	
	private String				newPassword="L0destar@1234";
	
	FacilitatorDetailsDAO facilitatorDAO= new FacilitatorDetailsDAO();
	
	public String execute()
	{
		OUT.debug("GsPasswordRest : inside execute method");
		try
		{
			
			List<FacilitatorDetailsDTO> facilitatorDTOList = facilitatorDAO.getAllFacilitatorsDetailsList();
			
			for(FacilitatorDetailsDTO facilitatorDTO: facilitatorDTOList )
			{
				UserDetailDTO userDetailDTO = new UserDetailDTO();
				userDetailDTO.setId(facilitatorDTO.getUserId());
				userDetailDTO.setRoleId(3);
				UserDetailDTO detailDTO = new ChangePasswordService().getPasswordToChange(userDetailDTO);
				
				if (detailDTO != null)
				{
					userDetailDTO.setPassword(AESCipher.encrypt(newPassword.getBytes()));
					boolean isUpdated = new ChangePasswordService().saveNewPassword(userDetailDTO);
					if (isUpdated)
					{
						OUT.debug("GsPasswordRest : Password updated for Gs Id: {}, Name: {} ",facilitatorDTO.getId(),facilitatorDTO.getName());
					}
					else
					{
						OUT.debug("GsPasswordRest : Password Not updated for Gs Id: {}, Name: {} ",facilitatorDTO.getId(),facilitatorDTO.getName());
					}
					
				}
				else
				{
					OUT.debug("GsPasswordRest : detailDTO null for Gs Id : {} ",facilitatorDTO.getId());
				}
				
			}
			
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}
	

}
