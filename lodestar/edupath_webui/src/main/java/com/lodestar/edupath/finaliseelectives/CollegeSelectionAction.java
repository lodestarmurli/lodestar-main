package com.lodestar.edupath.finaliseelectives;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentCityLockDTO;
import com.lodestar.edupath.datatransferobject.dto.collegesearch.CollegeSearchVO;
import com.lodestar.edupath.datatransferobject.enumtype.SessionTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.finaliseelectives.service.CollegeSelectionService;
import com.lodestar.edupath.finaliseelectives.service.bean.CollegeSearchFilterVO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class CollegeSelectionAction extends BaseAction implements ModelDriven<CollegeSearchFilterVO>
{
	private static final long		serialVersionUID	= 1L;
	private static final Logger		OUT					= LoggerFactory.getLogger(CollegeSelectionAction.class);

	private Map<String, Object>		finalMap			= new HashMap<String, Object>();
	private CollegeSearchFilterVO	bean				= new CollegeSearchFilterVO();
	private List<CollegeSearchVO>	collegeDetailsList	= new ArrayList<CollegeSearchVO>();
	private int						totalSize			= 0;
	private int						maxCollegeCompare	= 0;
	//Vyankatesh 17-4-2017
	private int							cityId;
	private List<CityDTO>				cityList					= new ArrayList<CityDTO>();

	public String execute()
	{
		OUT.debug("Inside CollegeSelectionAction");
		try
		{
			CollegeSelectionService service = new CollegeSelectionService();
			bean.setStudentId(getStudentSessionObject().getId());
			finalMap = service.getCollegeParameterAndElective(bean.getStudentId());
			StudentCityLockDTO studentcityById = new EntranceExamsDAO().getCityIdByStudentId(getStudentSessionObject().getId());
			
			//vyankatesh 17-4-2017
			cityList = service.getAllCity();
			if(null != studentcityById  && !studentcityById.toString().isEmpty())
			{
				cityId = studentcityById.getCityLockId();
				System.out.println("============================ lock"+cityId);
			}
			else
			{
				cityId = getStudentSessionObject().getCityId();
				System.out.println("============================ no lock"+cityId);
			}
			
			//vyankatesh 17-4-2017
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String getFilter()
	{
		try
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			sessionMap.put(ApplicationConstants.SUB_MENU_REF_NAME, ApplicationConstants.APP_MENU_COLLEGE_REFNAME);
			setSelectedId(SessionTypeEnum.SESSION_3_FACI.getSessionName(), sessionMap);
			sessionMap.remove(ApplicationConstants.SUB_MENU_REF_NAME);

			int studentId = 0;
			OUT.info("Redirecting to college filter screen: studentId: {}", (studentId = getStudentSessionObject().getId()));
			bean.setStudentId(studentId);
			//===Start Sasedeve edited by Mrutyunjaya on date 17-04-2017
			bean.setHomeCityId(getStudentSessionObject().getCityId());
			
			CollegeSelectionService service = new CollegeSelectionService();
			bean.setAllCollegeParams(service.getAllCollegeParams(studentId));
			
			//===End Sasedeve edited by Mrutyunjaya on date 17-04-2017
			
			maxCollegeCompare = service.getMaxCompareCount();
			return "ShowCollegeDetailsScreen";
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
		}
	}

	@SuppressWarnings("unchecked")
	public String getColleges()
	{
		try
		{
			int studentId = 0;
			OUT.info("Getting college card: studentId: {}, filter: {}", (studentId = getStudentSessionObject().getId()), bean);
			bean.setStudentId(studentId);
			//===Start Sasedeve edited by Mrutyunjaya on date 17-04-2017
			
			bean.setHomeCityId(getStudentSessionObject().getCityId());
			//===End Sasedeve edited by Mrutyunjaya on date 17-04-2017
			
			CollegeSelectionService service = new CollegeSelectionService();
			Map<String, Object> collegeByFilters = service.getCollegeByFilters(bean);
			collegeDetailsList = (List<CollegeSearchVO>) collegeByFilters.get(CollegeSelectionService.getCollegeRecords());
			totalSize = (int) collegeByFilters.get(CollegeSelectionService.getTotalSize());
			return "ShowCollegeCard";
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
		}
	}

	public Map<String, Object> getFinalMap()
	{
		return finalMap;
	}

	public void setFinalMap(Map<String, Object> finalMap)
	{
		this.finalMap = finalMap;
	}

	@Override
	public CollegeSearchFilterVO getModel()
	{
		return bean;
	}

	public List<CollegeSearchVO> getCollegeDetailsList()
	{
		return collegeDetailsList;
	}

	public void setCollegeDetailsList(List<CollegeSearchVO> collegeDetailsList)
	{
		this.collegeDetailsList = collegeDetailsList;
	}

	public int getTotalSize()
	{
		return totalSize;
	}

	public void setTotalSize(int totalSize)
	{
		this.totalSize = totalSize;
	}

	public int getMaxCollegeCompare()
	{
		return maxCollegeCompare;
	}

	public void setMaxCollegeCompare(int maxCollegeCompare)
	{
		this.maxCollegeCompare = maxCollegeCompare;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public List<CityDTO> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityDTO> cityList) {
		this.cityList = cityList;
	}
}
