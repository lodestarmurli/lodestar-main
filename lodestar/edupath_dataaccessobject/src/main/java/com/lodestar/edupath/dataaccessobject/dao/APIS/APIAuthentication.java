package com.lodestar.edupath.dataaccessobject.dao.APIS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.APIS.AuthCodeValidationDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.PartnerDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.sessionfeedback.SessionFeedBackStatusDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;



public class APIAuthentication {
	private static final Logger	OUT	= LoggerFactory.getLogger(APIAuthentication.class);
	
	public boolean APIAuthentication(AuthCodeValidationDTO authcodevalidate)
	{
		boolean Apivalied=false;
		
		
		try
		{
			
			if(authcodevalidate!=null && authcodevalidate.getAuthCode()!=null && !authcodevalidate.getAuthCode().equals("") && authcodevalidate.getEmailID()!=null && !authcodevalidate.getEmailID().equals("") && authcodevalidate.getPassword()!=null && !authcodevalidate.getPassword().equals(""))
			{
				PartnerDetailsDTO partnerdetails=(PartnerDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.APIValidate, authcodevalidate);
				
				
				if(partnerdetails!=null)
				{
					Apivalied=true;
				}
				
				
			}
			
			
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		
		
		return Apivalied;
	}
	
	
}
