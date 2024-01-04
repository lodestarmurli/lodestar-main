package com.lodestar.edupath.tutorialsearch.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.EntranceExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentCityLockDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentTutorialCentreShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialCityCentersVO;
import com.lodestar.edupath.datatransferobject.dto.tutorialsearch.TutorialSearchVO;
import com.lodestar.edupath.datatransferobject.enumtype.SessionTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.tutorialsearch.bean.TutorialSearchFilterVO;
import com.lodestar.edupath.tutorialsearch.service.TutorialSearchService;
import com.lodestar.edupath.tutorialshortlist.TutorialCentersShortListService;
import com.opensymphony.xwork2.ActionContext;

public class TutorialSearchAction extends BaseAction
{
	private static final long			serialVersionUID			= 1L;
	private static final Logger			OUT							= LoggerFactory.getLogger(TutorialSearchAction.class);
	private int							cityId;
	private List<EntranceExamsDTO>		examList					= new ArrayList<EntranceExamsDTO>();
	private List<CityDTO>				cityList					= new ArrayList<CityDTO>();
	private int							studentId;
	private int							examId;
	// private String cityIdStr;
	private int							totalCount;
	private boolean						countExist					= false;
	private int							pageNumber;
	private String						locality;
	private List<TutorialSearchVO>		tutorialsDetails			= new ArrayList<TutorialSearchVO>();
	private int							pageSizeCount;
	private JSONObject					jsonObject					= new JSONObject();
	private Boolean						loadFurther					= false;
	private int							totalRecords;
	private String						tutorialIdStr;
	private List<TutorialCityCentersVO>	tutorialCityCenterDetails	= new ArrayList<TutorialCityCentersVO>();
	private int							tutorialCentreSelectCount;
	private boolean						tutCentreCountExist			= false;
	private Map<Integer, Integer>		tutorialCenterObject		= new HashMap<Integer, Integer>();
	private JSONObject					tutCenterShortListObject;

	@Override
	public String execute()
	{
		OUT.debug("Inside TutorialSearchAction execute method");
		try
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			sessionMap.put(ApplicationConstants.SUB_MENU_REF_NAME, ApplicationConstants.APP_MENU_TUTORIAL_REFNAME);
			setSelectedId(SessionTypeEnum.SESSION_3_FACI.getSessionName(), sessionMap);
			sessionMap.remove(ApplicationConstants.SUB_MENU_REF_NAME);

			TutorialSearchService tutorialSearchService = new TutorialSearchService();
			//cityId = getStudentSessionObject().getCityId();
			
			//vyankatesh 17-4-2017
			cityList = tutorialSearchService.getAllCity();
			StudentCityLockDTO studentcityById = new EntranceExamsDAO().getCityIdByStudentId(getStudentSessionObject().getId());
			if(null != studentcityById  && !studentcityById.toString().isEmpty())
			{
				cityId = studentcityById.getCityLockId();
			}
			else
			{
				cityId = getStudentSessionObject().getCityId();
			}
		
			//vyankatesh 17-4-2017
			
			studentId = getStudentSessionObject().getId();
			examList = tutorialSearchService.getExamsByStudentId(studentId);
			//cityList = tutorialSearchService.getAllCity();
			Map<Integer, List<String>> tutorialCityLocalityMap = null;
			if (cityList != null && !cityList.isEmpty())
			{
				tutorialCityLocalityMap = tutorialSearchService.getCityLocalityMap(cityList);
			}
			pageSizeCount = tutorialSearchService.getTutorialPageCount();
			jsonObject.put("tutorialCityLocalityMap", tutorialCityLocalityMap);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}

	public String getTutorialByFilter()
	{
		try
		{
			OUT.debug("examid - {} and cityIds - {} and locality - {}", examId, cityId, locality);
			TutorialSearchService tutorialSearchService = new TutorialSearchService();
			List<Integer> cityIds = new ArrayList<Integer>();
			cityIds.add(cityId);
			TutorialSearchFilterVO searchFilterVO = new TutorialSearchFilterVO();
			searchFilterVO.setCityIds(cityIds);
			searchFilterVO.setStudentId(studentId);
			searchFilterVO.setExamId(examId);
			if (null != locality && !locality.equals(""))
			{
				searchFilterVO.setLocality(locality);
			}
			ObjectMapper mapper = new ObjectMapper();
			Map<Object, Object> convertValue = mapper.convertValue(searchFilterVO, Map.class);
			if (!countExist)
			{
				searchFilterVO.setGetTotalCount(true);
				totalCount = tutorialSearchService.getTutorialCountByFilter(convertValue);
				pageNumber = 0;
				pageSizeCount = tutorialSearchService.getTutorialPageCount();
			}

			searchFilterVO.setGetTotalCount(false);
			tutorialsDetails = tutorialSearchService.getTutorialsByFilter(convertValue, pageNumber, pageSizeCount);
			if (null != tutorialsDetails && !tutorialsDetails.isEmpty())
			{
				totalRecords = tutorialsDetails.size();
				loadFurther = true;
			}
			else
			{
				loadFurther = false;
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return "showContentScreen";
	}

	public String getTutorialCenters()
	{
		OUT.debug("Inside getTutorialCenters method");
		try
		{
			List<Integer> tutorialIdList = new ArrayList<Integer>();
			List<Integer> cityIds = new ArrayList<Integer>();
			TutorialSearchFilterVO searchFilterVO = new TutorialSearchFilterVO();
			if (null != tutorialIdStr && !tutorialIdStr.equals(""))
			{
				for (String tutId : tutorialIdStr.split(","))
				{
					tutorialIdList.add(Integer.parseInt(tutId));
				}
				searchFilterVO.setTutorialIds(tutorialIdList);
			}
			if (examId > 0)
			{
				searchFilterVO.setExamId(examId);
			}
			if (cityId > 0)
			{
				cityIds.add(cityId);
				searchFilterVO.setCityIds(cityIds);
			}
			if (null != locality && !locality.equals(""))
			{
				searchFilterVO.setLocality(locality);
			}
			searchFilterVO.setStudentId(studentId);

			ObjectMapper mapper = new ObjectMapper();
			Map<Object, Object> convertValue = mapper.convertValue(searchFilterVO, Map.class);
			TutorialSearchService tutorialSearchService = new TutorialSearchService();
			tutorialsDetails = tutorialSearchService.getTutorialCityCentersByTutorialAndExamId(convertValue);
			List<StudentTutorialCentreShortListDTO> centreShortListDTOs = new TutorialCentersShortListService().getShortListedTutorialCenters(studentId);
			if (null != centreShortListDTOs && !centreShortListDTOs.isEmpty())
			{
				Map<Integer, Integer> tutCenterMap = getTutCenterMap(centreShortListDTOs);
				tutCenterShortListObject = new JSONObject(tutCenterMap);
			}
			if (!tutCentreCountExist)
			{
				tutorialCentreSelectCount = tutorialSearchService.getTutorialCentreSelectCount();
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return "showTutorialCenterScreen";
	}

	private Map<Integer, Integer> getTutCenterMap(List<StudentTutorialCentreShortListDTO> centreShortListDTOs)
	{
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (StudentTutorialCentreShortListDTO centreShortListDTO : centreShortListDTOs)
		{
			map.put(centreShortListDTO.getTutorialCityCentersId(), centreShortListDTO.getTutorialId());
		}
		return map;
	}

	public String insertCentresToCart()
	{
		PrintWriter writer = null;
		try
		{
			OUT.debug("Inside insertCentresToCart method : {}", tutorialCenterObject);
			TutorialCentersShortListService tutorialCentersShortListService = new TutorialCentersShortListService();
			writer = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			JSONObject tutorialCenterMapObject = null;
			List<StudentTutorialCentreShortListDTO> tutCenterShortList = tutorialCentersShortListService.getShortListedTutorialCenters(getStudentSessionObject()
					.getId());
			int tutCenterShortListedCount = (tutCenterShortList != null ? tutCenterShortList.size() : 0);
			OUT.debug("totalCount : {} , count in database : {} , count inserting : {}", tutorialCentreSelectCount, tutCenterShortListedCount,
					tutorialCenterObject.size());
			if ((tutCenterShortListedCount + tutorialCenterObject.size()) > tutorialCentreSelectCount)
			{
				jsonObject.put("TutShortListMaxCount", "exceed");
			}
			else if (checkDuplicateData(tutCenterShortList, tutorialCenterObject, jsonObject))
			{
				jsonObject.put("tutorialExist", "tutorialExist");
			}
			else
			{
				boolean isInserted = false;
				if (null != tutorialCenterObject && !tutorialCenterObject.isEmpty())
				{
					isInserted = tutorialCentersShortListService.insertShortListTutorialCenters(tutorialCenterObject, getStudentSessionObject().getId(),
							getUserSessionObject().getLoginId());
				}
				if (isInserted)
				{
					tutorialCenterMapObject = new JSONObject(tutorialCenterObject);
					jsonObject.put("status", "success");
				}
				else
				{
					jsonObject.put("status", "error");
				}
				jsonObject.put("tutorialCenterMap", tutorialCenterMapObject);
			}
			response.setContentType("application/json");
			writer.write(jsonObject.toString());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (writer != null)
			{
				writer.close();
			}
		}
		return null;
	}

	private boolean checkDuplicateData(List<StudentTutorialCentreShortListDTO> tutCenterShortList, Map<Integer, Integer> tutorialCenterObject2, JSONObject jsonObject)
	{
		boolean isExist = false;
		if (null != tutCenterShortList && !tutCenterShortList.isEmpty())
		{
			for (Entry<Integer, Integer> tutCenterDetails : tutorialCenterObject2.entrySet())
			{
				for (StudentTutorialCentreShortListDTO tutCenterShortListDetail : tutCenterShortList)
				{
					if (tutCenterDetails.getKey() == tutCenterShortListDetail.getTutorialCityCentersId()
							&& tutCenterDetails.getValue() == tutCenterShortListDetail.getTutorialId())
					{
						jsonObject.put("tutorialName", tutCenterShortListDetail.getTutorialName());
						return true;
					}
				}
			}
		}
		return isExist;
	}

	public int getCityId()
	{
		return cityId;
	}

	public void setCityId(int cityId)
	{
		this.cityId = cityId;
	}

	public List<EntranceExamsDTO> getExamList()
	{
		return examList;
	}

	public void setExamList(List<EntranceExamsDTO> examList)
	{
		this.examList = examList;
	}

	public List<CityDTO> getCityList()
	{
		return cityList;
	}

	public void setCityList(List<CityDTO> cityList)
	{
		this.cityList = cityList;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public int getExamId()
	{
		return examId;
	}

	public void setExamId(int examId)
	{
		this.examId = examId;
	}

	public int getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}

	public boolean isCountExist()
	{
		return countExist;
	}

	public void setCountExist(boolean countExist)
	{
		this.countExist = countExist;
	}

	public int getPageNumber()
	{
		return pageNumber;
	}

	public void setPageNumber(int pageNumber)
	{
		this.pageNumber = pageNumber;
	}

	public String getLocality()
	{
		return locality;
	}

	public void setLocality(String locality)
	{
		this.locality = locality;
	}

	public List<TutorialSearchVO> getTutorialsDetails()
	{
		return tutorialsDetails;
	}

	public void setTutorialsDetails(List<TutorialSearchVO> tutorialsDetails)
	{
		this.tutorialsDetails = tutorialsDetails;
	}

	public int getPageSizeCount()
	{
		return pageSizeCount;
	}

	public void setPageSizeCount(int pageSizeCount)
	{
		this.pageSizeCount = pageSizeCount;
	}

	public JSONObject getJsonObject()
	{
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject)
	{
		this.jsonObject = jsonObject;
	}

	public Boolean getLoadFurther()
	{
		return loadFurther;
	}

	public void setLoadFurther(Boolean loadFurther)
	{
		this.loadFurther = loadFurther;
	}

	public int getTotalRecords()
	{
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords)
	{
		this.totalRecords = totalRecords;
	}

	public String getTutorialIdStr()
	{
		return tutorialIdStr;
	}

	public void setTutorialIdStr(String tutorialIdStr)
	{
		this.tutorialIdStr = tutorialIdStr;
	}

	public List<TutorialCityCentersVO> getTutorialCityCenterDetails()
	{
		return tutorialCityCenterDetails;
	}

	public void setTutorialCityCenterDetails(List<TutorialCityCentersVO> tutorialCityCenterDetails)
	{
		this.tutorialCityCenterDetails = tutorialCityCenterDetails;
	}

	public int getTutorialCentreSelectCount()
	{
		return tutorialCentreSelectCount;
	}

	public void setTutorialCentreSelectCount(int tutorialCentreSelectCount)
	{
		this.tutorialCentreSelectCount = tutorialCentreSelectCount;
	}

	public Map<Integer, Integer> getTutorialCenterObject()
	{
		return tutorialCenterObject;
	}

	public void setTutorialCenterObject(Map<Integer, Integer> tutorialCenterObject)
	{
		this.tutorialCenterObject = tutorialCenterObject;
	}

	public JSONObject getTutCenterShortListObject()
	{
		return tutCenterShortListObject;
	}

	public void setTutCenterShortListObject(JSONObject tutCenterShortListObject)
	{
		this.tutCenterShortListObject = tutCenterShortListObject;
	}

}
