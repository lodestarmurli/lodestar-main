package com.lodestar.edupath.facilitator.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.userdetails.UserDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.userrole.UserRoleDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.EducationLevelDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorCityMapDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.LanguagesDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserRoleDTO;
import com.lodestar.edupath.datatransferobject.enumtype.UserTypeEnum;
import com.lodestar.edupath.datatransferobject.util.AESCipher;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.util.PasswordGeneratorService;

public class FacilitatorDetailsService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(FacilitatorDetailsService.class);
	private String				auditDateStr;

	public List<FacilitatorDetailsDTO> getAllFacilitatorDetails()
	{
		try
		{
			OUT.info("FacilitatorDetailsService : getting facilitator details from dao");
			List<FacilitatorDetailsDTO> facilitatorDetailsList1 = new ArrayList<FacilitatorDetailsDTO>();
			FacilitatorDetailsDTO facilitatorDetailsDTO1;
			List<FacilitatorDetailsDTO> facilitatorDetailsList = new FacilitatorDetailsDAO().getAllFacilitatorsDetailsList();
			for (FacilitatorDetailsDTO facilitatorDetailsDTO : facilitatorDetailsList)
			{
				facilitatorDetailsDTO1 = new FacilitatorDetailsDTO();
				facilitatorDetailsDTO1.setId(facilitatorDetailsDTO.getId());
				facilitatorDetailsDTO1.setName(CommonUtil.replaceXMLEntities(facilitatorDetailsDTO.getName()));
				facilitatorDetailsDTO1.setEmailId(CommonUtil.replaceXMLEntities(facilitatorDetailsDTO.getEmailId()));
				facilitatorDetailsDTO1.setPhoneNumber(CommonUtil.replaceXMLEntities(facilitatorDetailsDTO.getPhoneNumber()));
				if (facilitatorDetailsDTO.getType().equalsIgnoreCase("P"))
				{
					facilitatorDetailsDTO1.setType("Part Time");
				}
				else
				{
					facilitatorDetailsDTO1.setType("Full Time");
				}
				facilitatorDetailsDTO1.setAreaAddr(CommonUtil.replaceXMLEntities(facilitatorDetailsDTO.getAreaAddr()));
				facilitatorDetailsDTO1.setIsActive(facilitatorDetailsDTO.getIsActive());
				facilitatorDetailsDTO1.setIsReviewer(facilitatorDetailsDTO.getIsReviewer());
				facilitatorDetailsDTO1.setUserId(facilitatorDetailsDTO.getUserId());
				facilitatorDetailsList1.add(facilitatorDetailsDTO1);
			}
			return facilitatorDetailsList1;
		}
		catch (Throwable e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public FacilitatorDetailsDTO getFacilitatorDetailsById(int id)
	{
		FacilitatorDetailsDTO facilitatorDetailsDTO = null;
		try
		{
			facilitatorDetailsDTO = new FacilitatorDetailsDAO().getFacilitatorDetailsById(id);
			if (facilitatorDetailsDTO.getDob() != null)
			{
				String dob = TimeUtil.convertTimeAsString(facilitatorDetailsDTO.getDob().getTime(), TimeUtil.FILTER_DATE_FORMAT);
				facilitatorDetailsDTO.setDobStr(dob);
			}
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return facilitatorDetailsDTO;
	}

	public List<EducationLevelDTO> getEducationLevelNameWithId()
	{
		List<EducationLevelDTO> list = null;
		try
		{
			list = new FacilitatorDetailsDAO().getEducationLevelNameWithId();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		return list;
	}

	public List<CityDTO> getCityNameWithId()
	{
		List<CityDTO> list = null;
		try
		{
			list = new FacilitatorDetailsDAO().getCityNameWithId();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		return list;
	}

	public boolean insertFacilitatorDetails(FacilitatorDetailsDTO facilitatorDTO)
	{
		SqlSession session = null;
		String password = null;
		try
		{

			UserDetailDTO userDTO = new UserDetailDTO();
			FacilitatorCityMapDTO facilitatorCityMapDTO = new FacilitatorCityMapDTO();
			userDTO.setLoginId(facilitatorDTO.getEmailId());

			UserDetailsDAO userDAO = new UserDetailsDAO();
			userDTO = userDAO.getUserDetailByLoginId(userDTO);

			if (null != userDTO)
			{
				return false;
			}

			session = MyBatisManager.getInstance().getSession();

			UserRoleDTO roleDTO = new UserRoleDTO();
			roleDTO.setName(UserTypeEnum.FACILITATOR.getDisplayName());

			UserRoleDAO roleDAO = new UserRoleDAO();
			roleDTO = roleDAO.getUserRoleByName(roleDTO);

			password = PasswordGeneratorService.getRandomAlphanumeric();

			UserDetailDTO user = new UserDetailDTO();
			user.setLoginId(facilitatorDTO.getEmailId());
			user.setRoleId(roleDTO.getRoleTypeId());
			user.setUserType(UserTypeEnum.FACILITATOR.getDisplayName());
			if (facilitatorDTO.getIsActive())
			{
				user.setIsActive("Y");
			}
			else
			{
				user.setIsActive("N");
			}
			user.setPassword(AESCipher.encrypt(password.getBytes()));

			int userId = userDAO.insertUserDetail(session, user);
			if (userId == 0)
			{
				return false;
			}

			facilitatorDTO.setUserId(userId);
			
			//System.out.println("1====================================="+facilitatorDTO);
			
			int id = new FacilitatorDetailsDAO().insertFacilitatorDetails(session, facilitatorDTO);
			if (id == 0)
			{
				return false;
			}
			// send mail
			facilitatorCityMapDTO.setFacilitatorId(id);
			List<Integer> conmmonCityId = new ArrayList<>(facilitatorDTO.getFaceToFaceCityId());
			List<Integer> removeonCallCityId = new ArrayList<>(facilitatorDTO.getOnCallCityId());
			List<Integer> removeFaceToFaceCityId = new ArrayList<>(facilitatorDTO.getFaceToFaceCityId());
			
			conmmonCityId.retainAll(facilitatorDTO.getOnCallCityId());
			
			for (Integer integer : conmmonCityId) {
				
				facilitatorCityMapDTO.setCityId(integer);
				
				facilitatorCityMapDTO.setFaceToFaceCity(1);
				facilitatorCityMapDTO.setOncallCity(1);
				
				new FacilitatorDetailsDAO().insertFacilitatorCityDetails(session, facilitatorCityMapDTO);
				//System.out.println("common=========="+integer);
			}
			
			removeonCallCityId.removeAll(facilitatorDTO.getFaceToFaceCityId());
			for (Integer integer : removeonCallCityId) {
				
				facilitatorCityMapDTO.setCityId(integer);
				
				facilitatorCityMapDTO.setFaceToFaceCity(0);
				facilitatorCityMapDTO.setOncallCity(1);
				
				
				new FacilitatorDetailsDAO().insertFacilitatorCityDetails(session, facilitatorCityMapDTO);
				
				//System.out.println("Faceto faceonly==========="+integer);
				
			}
			
			removeFaceToFaceCityId.removeAll(facilitatorDTO.getOnCallCityId());
			for (Integer integer : removeFaceToFaceCityId) {
				//System.out.println("Face to faceonly==========="+integer);
				
				facilitatorCityMapDTO.setCityId(integer);
				
				facilitatorCityMapDTO.setFaceToFaceCity(1);
				facilitatorCityMapDTO.setOncallCity(0);
				new FacilitatorDetailsDAO().insertFacilitatorCityDetails(session, facilitatorCityMapDTO);
			}
			
			
			
			PasswordGeneratorService.sendNewNotification(userId, facilitatorDTO.getName(), facilitatorDTO.getEmailId(), roleDTO.getRoleTypeId(), password);
			session.commit();
			if (user.getCreatedOn() != null)
			{
				auditDateStr = TimeUtil.getDateFromMillis(user.getCreatedOn().getTime(), TimeUtil.QUERY_DATE_FORMAT);
			}

		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		return true;

	}

	public boolean updateFacilitatorDetailsById(FacilitatorDetailsDTO facilitatorDetailsDTO)
	{
		SqlSession session = null;
		try
		{
			UserDetailDTO userDTO = new UserDetailDTO();
			FacilitatorCityMapDTO facilitatorCityMapDTO = new FacilitatorCityMapDTO();
			userDTO.setLoginId(facilitatorDetailsDTO.getEmailId());
			userDTO.setId(facilitatorDetailsDTO.getUserId());
			if (facilitatorDetailsDTO.getIsActive())
			{
				userDTO.setIsActive("Y");
			}
			else
			{
				userDTO.setIsActive("N");
			}

			UserDetailsDAO userDAO = new UserDetailsDAO();
			userDTO = userDAO.getUserDetailByLoginIdAndId(userDTO);
			if (null != userDTO)
			{
				return false;
			}

			session = MyBatisManager.getInstance().getSession();

			UserDetailDTO user = new UserDetailDTO();
			user.setLoginId(facilitatorDetailsDTO.getEmailId());
			user.setId(facilitatorDetailsDTO.getUserId());
			if (facilitatorDetailsDTO.getIsActive())
			{
				user.setIsActive("Y");
			}
			else
			{
				user.setIsActive("N");
			}
			int userId = userDAO.updateUserDetailsById(session, user);
			if (userId == 0)
			{
				return false;
			}
			
			/////////////////////////////////////////////////
			int faciid = facilitatorDetailsDTO.getId();
			facilitatorCityMapDTO.setFacilitatorId(faciid);
			new FacilitatorDetailsDAO().updateFacilitatorCityDetails(session, facilitatorCityMapDTO);
			
			List<Integer> conmmonCityId = new ArrayList<>(facilitatorDetailsDTO.getFaceToFaceCityId());
			List<Integer> removeonCallCityId = new ArrayList<>(facilitatorDetailsDTO.getOnCallCityId());
			List<Integer> removeFaceToFaceCityId = new ArrayList<>(facilitatorDetailsDTO.getFaceToFaceCityId());
			
conmmonCityId.retainAll(facilitatorDetailsDTO.getOnCallCityId());
			
			for (Integer integer : conmmonCityId) {
				
				facilitatorCityMapDTO.setCityId(integer);
				
				facilitatorCityMapDTO.setFaceToFaceCity(1);
				facilitatorCityMapDTO.setOncallCity(1);
				
				new FacilitatorDetailsDAO().insertFacilitatorCityDetails(session, facilitatorCityMapDTO);
				//System.out.println("common=========="+integer);
			}
			
			removeonCallCityId.removeAll(facilitatorDetailsDTO.getFaceToFaceCityId());
			for (Integer integer : removeonCallCityId) {
				
				facilitatorCityMapDTO.setCityId(integer);
				
				facilitatorCityMapDTO.setFaceToFaceCity(0);
				facilitatorCityMapDTO.setOncallCity(1);
				
				
				new FacilitatorDetailsDAO().insertFacilitatorCityDetails(session, facilitatorCityMapDTO);
				
				//System.out.println("Faceto faceonly==========="+integer);
				
			}
			
			removeFaceToFaceCityId.removeAll(facilitatorDetailsDTO.getOnCallCityId());
			for (Integer integer : removeFaceToFaceCityId) {
				//System.out.println("Face to faceonly==========="+integer);
				
				facilitatorCityMapDTO.setCityId(integer);
				
				facilitatorCityMapDTO.setFaceToFaceCity(1);
				facilitatorCityMapDTO.setOncallCity(0);
				new FacilitatorDetailsDAO().insertFacilitatorCityDetails(session, facilitatorCityMapDTO);
			}
			
				
			
			
			
			
			
			///////////////////////////////////////////////////
			int id = new FacilitatorDetailsDAO().updateFacilitatorDetailsById(session, facilitatorDetailsDTO);
			if (id == 0)
			{
				return false;
			}
			session.commit();
			if (user.getUpdatedOn() != null)
			{
				auditDateStr = TimeUtil.getDateFromMillis(user.getUpdatedOn().getTime(), TimeUtil.QUERY_DATE_FORMAT);
			}
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error("Exception ", e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		return true;
	}

	public boolean deleteFacilitatorDetailsById(FacilitatorDetailsDTO facilitatorDetailsDTO)
	{

		FacilitatorDetailsDAO facilitatorDetailsDAO = new FacilitatorDetailsDAO();
		boolean isDeleted = false;
		boolean isTrue = false;
		try
		{
			isTrue = facilitatorDetailsDAO.isIdExist(facilitatorDetailsDTO.getId());
			if (isTrue)
			{
				return isDeleted;
			}
			else
			{
				isDeleted = new UserDetailsDAO().deleteUserDetailsById(facilitatorDetailsDTO.getUserId());
				if (isDeleted)
				{
					isDeleted = facilitatorDetailsDAO.deleteFacilitatorDetailsById(facilitatorDetailsDTO.getId());
					auditDateStr = TimeUtil.getDateFromMillis(System.currentTimeMillis(), TimeUtil.QUERY_DATE_FORMAT);
				}
			}

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return isDeleted;
	}

	public List<LanguagesDTO> getLanguages()
	{
		List<LanguagesDTO> languagesList = null;
		try
		{
			languagesList = new FacilitatorDetailsDAO().getAllLanguages();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return languagesList;
	}

	public String getAuditDateStr()
	{
		return auditDateStr;
	}

	public List<FacilitatorDetailsDTO> getfaceToFaceCityId(int id) {
		// TODO Auto-generated method stub
		List<FacilitatorDetailsDTO> faceToFaceCityId = null;
		try
		{
			faceToFaceCityId = new FacilitatorDetailsDAO().getAllfaceToFaceCityId(id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return faceToFaceCityId;
	}

	public List<FacilitatorDetailsDTO> getonCallCityId(int id) {
		// TODO Auto-generated method stub
		List<FacilitatorDetailsDTO> onCallCityId = null;
		try
		{
			onCallCityId = new FacilitatorDetailsDAO().getAllonCallCityId(id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return onCallCityId;
	}

	public List<FacilitatorDetailsDTO> getfacilatormapping(int id) {
		// TODO Auto-generated method stub
		List<FacilitatorDetailsDTO> facilatormapping = null;
		try
		{
			facilatormapping = new FacilitatorDetailsDAO().getFacilatormapping(id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return facilatormapping;
	}
}
