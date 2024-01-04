package com.lodestar.edupath.vark.action;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.vark.VarkStudentDAO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.vark.VarkStudentDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;
import com.lodestar.edupath.util.EncryptionDecryptionData;
import com.lodestar.edupath.vark.service.WelcomeScreenService;

public class WelcomeScreen extends BaseAction{

	private static final long			serialVersionUID	= 6090415257988873149L;
	private static Logger				OUT					= LoggerFactory.getLogger(WelcomeScreen.class);
	
	
	WelcomeScreenService service = new WelcomeScreenService();
	



	@Override
	public String execute() throws Exception
	{
		OUT.info("Inside Vark WelcomeScreen");
		try
		{
		
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		
		return SUCCESS;
	}
	
	
}
