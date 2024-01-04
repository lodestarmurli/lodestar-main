package com.lodestar.edupath.school;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.exception.EdupathException;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.facilitator.service.FacilitatorDetailsService;
import com.lodestar.edupath.school.service.ManageSchoolService;
import com.lodestar.edupath.util.ApplicationProperties;
import com.opensymphony.xwork2.ModelDriven;

public class ManageSchoolSubmitAction extends BaseAction implements ModelDriven<SchoolDTO>
{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(ManageSchoolSubmitAction.class);

	private SchoolDTO			schoolDTO			= new SchoolDTO();
	private Map<Integer, String>		cityMap					= new LinkedHashMap<Integer, String>();

	private String logopath;
	private String displaylogo;
	
	
	private String destPath;
	
	

	private File				image;

	private String				imageContentType;

	private String				imageFileName;
	
	private String 				path;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String execute()
	{
		OUT.debug("ManageSchoolSubmitAction : inside execute method");
		try
		{
			if (schoolDTO != null && schoolDTO.getId() > 0)
			{
				schoolDTO = new ManageSchoolService().getSchoolById(schoolDTO.getId());

				if(schoolDTO!=null && schoolDTO.getCityId() > 0)
				{}
				else
				{
					schoolDTO.setCityId(326);
				}

			}
			setCityMap();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}

	public String insert()
	{
		//start bharath on 16-05-2019
		destPath= ApplicationProperties.getInstance().getProperty("com.edupath.school.logo.destination.view");
		
		String destPath_report= ApplicationProperties.getInstance().getProperty("com.edupath.school.logo.destination");
		
		UserSessionObject sessionObj = (UserSessionObject) request.getSession().getAttribute(
				ApplicationConstants.SessionProperty.USER_SESSION_DETAILS_PROPERTY.getProperty());

		OUT.debug("ManageSchoolSubmitAction : inside insert method");
		try
		{
			
			image= schoolDTO.getImage();
			imageFileName =schoolDTO.getName()+".png";
			if(image == null || imageFileName == null || imageFileName == "undefined"  || imageFileName =="")
			{
				logopath="";
			}else
			{
			 File destFile  = new File(destPath, imageFileName);
			 File destFile_report  = new File(destPath_report, imageFileName);
	         FileUtils.copyFile(image, destFile);
	         FileUtils.copyFile(image, destFile_report);

	         if(destFile.exists())
	         {
	        	 logopath = imageFileName;
	         }
			}
			
			schoolDTO.setLogopath(logopath);
			//end  bharath on 17-05-2019
			boolean isInserted =  new ManageSchoolService().insert(schoolDTO, getUserSessionObject().getLoginId());
			if (isInserted)
			{
				addActionMessage(getText("com.edupath.action.add.msg.successful", new String[]
				{
					"School"
				}));
			}
			else 
			{
				addActionError(getText("com.edupath.common.error.message"));
			}
		}
		catch (EdupathException e)
		{
			addActionError(getText(e.getMessage(), new String[]
			{
				getText("com.edupath.school.summary.name")
			}));
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return SUCCESS;
		}
		catch (Exception e)
		{
			addActionError(getText("com.edupath.common.error.message"));
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return SUCCESS;
		}
		return "showSummary";
	}
	
	public String update()
	{
		//start bharath on 18-05-2019
		destPath= ApplicationProperties.getInstance().getProperty("com.edupath.school.logo.destination.view");
		String destPath_report= ApplicationProperties.getInstance().getProperty("com.edupath.school.logo.destination");
		boolean isUpdated;
		OUT.debug("ManageSchoolSubmitAction : inside update method");
		try
		{
			
			image= schoolDTO.getImage();
			imageFileName =schoolDTO.getName()+".png";
			if(image == null || imageFileName == null || imageFileName == "undefined"  || imageFileName =="")
			{
				logopath="";
				isUpdated =  new ManageSchoolService().updatewithoutpath(schoolDTO, getUserSessionObject().getLoginId());
			}else
			{
				 File destFile  = new File(destPath, imageFileName);
				 File destFile_report  = new File(destPath_report, imageFileName);
		         FileUtils.copyFile(image, destFile);
		         FileUtils.copyFile(image, destFile_report);
		         
		         if(destFile.exists())
		         {
		        	 logopath = imageFileName;
		         }
		         
		         schoolDTO.setLogopath(logopath);
		         isUpdated =  new ManageSchoolService().update(schoolDTO, getUserSessionObject().getLoginId());
			}
			
			
			//end bharath on 18-05-2019
			
			if (isUpdated)
			{
				addActionMessage(getText("com.edupath.action.edit.msg.successful", new String[]
				{
					"School"
				}));
			}
			else 
			{
				addActionError(getText("com.edupath.common.error.message"));
			}
		}
		catch (EdupathException e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			addActionError(getText(e.getMessage(), new String[]
			{
				getText("com.edupath.school.summary.name")
			}));
			return SUCCESS;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			addActionError(getText("com.edupath.common.error.message"));
			return SUCCESS;
		}
		return "showSummary";
	}

	public SchoolDTO getSchoolDTO()
	{
		return schoolDTO;
	}

	public void setSchoolDTO(SchoolDTO schoolDTO)
	{
		this.schoolDTO = schoolDTO;
	}

	@Override
	public SchoolDTO getModel()
	{
		return schoolDTO;
	}
	
	private void setCityMap()
	{
		List<CityDTO> cityList = new ManageSchoolService().getCityNameWithId();
		for (CityDTO cityDTO : cityList)
		{
			cityMap.put(cityDTO.getId(), cityDTO.getName());
		}
	}

	public Map<Integer, String> getCityMap() {
		return cityMap;
	}

	public void setCityMap(Map<Integer, String> cityMap) {
		this.cityMap = cityMap;
	}

}
