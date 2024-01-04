package com.lodestar.edupath.dataaccessobject.dao.forgotpassword;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.ForgotPasswordRequestDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class ForgotPasswordDAO
{
	private static final Logger OUT = LoggerFactory.getLogger(ForgotPasswordDAO.class);

	public int insertForgotPasswordDetails(ForgotPasswordRequestDTO passwordRequestDTO)
	{
		int id = 0;
		try
		{
			id = MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_FORGOT_PASSWORD_Details, passwordRequestDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return id;
	}

	public ForgotPasswordRequestDTO getDetailsByCode(ForgotPasswordRequestDTO passwordRequestDTO)
	{
		ForgotPasswordRequestDTO dto = null;
		try
		{
			dto = (ForgotPasswordRequestDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_FORGOT_PASSWORD_Details_BY_CODE, passwordRequestDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}

	public boolean updateForgotPasswordById(SqlSession session, ForgotPasswordRequestDTO passwordRequestDTO)
	{
		boolean isUpdated = false;
		try
		{
			int id = session.update(MyBatisMappingConstants.UPDATE_FORGOT_PASSWORD_DETAILS_BY_USERID, passwordRequestDTO);
			if (id>0)
			{
				isUpdated = true;
			}
			OUT.debug("User Details update id :{}", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return isUpdated;
	}
	
	
}
