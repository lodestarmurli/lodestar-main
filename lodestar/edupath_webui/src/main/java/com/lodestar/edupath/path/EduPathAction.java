package com.lodestar.edupath.path;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.city.CityDAO;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentCityLockDTO;
import com.lodestar.edupath.datatransferobject.dto.student.ShortListDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.path.service.EduPathService;
import com.lodestar.edupath.path.service.ViewElectiveSerivce;
import com.lodestar.edupath.util.ApplicationProperties;

public class EduPathAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long			serialVersionUID	= 1L;

	private static final Logger			OUT					= LoggerFactory.getLogger(EduPathAction.class);

	private EduPathService				service;

	private List<ShortListDTO>			shortList;

	private String						topTwoSelected;

	private ShortListDTO				shortListDTO;

	private Map<String, Object>			edupathDetailMap;

	private String						occupationIds;

	private String						industryIds;

	private int							showOccId			= 0;
	private int							showIndId			= 0;
	private SortedMap<String, String>	occupationTitleMap	= new TreeMap<String, String>();
	private SortedMap<String, String>	industryTitleMap	= new TreeMap<String, String>();
	private String						occupationNameStr;
	private String						industryNameStr;
	private String						topTwoNameStr;
	private String						topTwoActive		= "N";
	private Map<String, String>			industryIdMap		= new HashMap<String, String>();
	private Map<String, String>			occIndustryIdMap	= new HashMap<String, String>();

	private int							occOrderId;

	private int							inOrderId;

	private ApplicationProperties		properties			= ApplicationProperties.getInstance();
	
	private List<CityDTO>				cityList			= new ArrayList<CityDTO>();
	private int							cityId			= 0;
	private int							checkEdupath			= 0;
	private int							statecheck			= 0;
	
	
	public String execute()
	{
		OUT.debug("Inside EduPathAction");
		try
		{
			service = new EduPathService();
			shortList = service.getTopShortList(getStudentSessionObject().getId());
			ViewElectiveSerivce viewElectiveSerivce = new ViewElectiveSerivce();
			//vyankatesh 14-4-2017
			StudentCityLockDTO studentcityById = new EntranceExamsDAO().getCityIdByStudentId(getStudentSessionObject().getId());
			
			if(null != studentcityById  && !studentcityById.toString().isEmpty())
			{
				cityId = studentcityById.getCityLockId();
			}
			else
			{
				cityId = getStudentSessionObject().getCityId();
			}
			
			OUT.debug("Inside EduPathAction"+getStudentSessionObject().getCityId());
			if (null != shortList && !shortList.isEmpty())
			{

				Map<Integer, Integer> occIndIdMap = new HashMap<Integer, Integer>();
				SortedMap<Integer, String> nameOrderMap = new TreeMap<Integer, String>();
				for (ShortListDTO dto : shortList)
				{
					if (dto.getOccIndustryId() != null)
					{
						occIndIdMap.put(dto.getOccupationId(), dto.getOccIndustryId());
					}
				}
				getStudentSessionObject().setOccIndIdMap(occIndIdMap);

				shortListDTO = shortList.get(0);
				if (shortListDTO.getIndustryId() == null)
				{
					topTwoSelected = "oc_" + String.valueOf(shortListDTO.getOccupationId());
					occupationIds = String.valueOf(shortListDTO.getOccupationId());
					showOccId = 1;
					occupationTitleMap.put("OCCUPATION_OPTION_1", properties.getProperty("com.eupath.path.option.one.title") + " " + shortListDTO.getOccupationName());
					
					
					occIndustryIdMap.put("OPTION_1", String.valueOf(shortListDTO.getOccIndustryId()));
					topTwoNameStr = "oc_" + shortListDTO.getOccupationName();
					
					//BEGIN Sasedeve Added by Mrutyunjaya on Date 19-05-2017
					
									
					nameOrderMap.put(1, "oc_" + shortListDTO.getOccupationName()+"_fitment_"+shortListDTO.getFitment()+"_newfitment_"+shortListDTO.getNewfitment());
					
					//END Sasedeve Added by Mrutyunjaya on Date 19-05-2017
				}
				else
				{
					topTwoSelected = "in_" + String.valueOf(shortListDTO.getIndustryId());
					industryIds = String.valueOf(shortListDTO.getIndustryId());
					showIndId = 1;
					industryTitleMap.put("INDUSTRY_OPTION_1", properties.getProperty("com.eupath.path.option.one.title") + " " + shortListDTO.getIndustryName());
					industryIdMap.put("OPTION_1", String.valueOf(shortListDTO.getIndustryId()));
					occIndustryIdMap.put("OPTION_1", "0");
					topTwoNameStr = "in_" + shortListDTO.getIndustryName();
					nameOrderMap.put(1, "in_" + shortListDTO.getIndustryName());
				}

				if (shortList.size() > 1)
				{
					shortListDTO = shortList.get(1);
					if (shortListDTO.getIndustryId() == null)
					{
						topTwoSelected = topTwoSelected + "," + "oc_" + String.valueOf(shortListDTO.getOccupationId());
						if (null != occupationIds)
						{
							occupationIds = occupationIds + "," + shortListDTO.getOccupationId();
						}
						else
						{
							occupationIds = String.valueOf(shortListDTO.getOccupationId());
						}
						showOccId = showOccId + 1;
						occIndustryIdMap.put("OPTION_2", String.valueOf(shortListDTO.getOccIndustryId()));
						topTwoNameStr = topTwoNameStr + "#" + "oc_" + shortListDTO.getOccupationName();
						
						
						//BEGIN Sasedeve Added by Mrutyunjaya on Date 19-05-2017
						
						
						nameOrderMap.put(2, "oc_" + shortListDTO.getOccupationName()+"_fitment_"+shortListDTO.getFitment()+"_newfitment_"+shortListDTO.getNewfitment());
						
						//END Sasedeve Added by Mrutyunjaya on Date 19-05-2017

					}
					else
					{
						topTwoSelected = topTwoSelected + "," + " in_" + String.valueOf(shortListDTO.getIndustryId());
						if (null != industryIds)
						{
							industryIds = industryIds.trim() + "," + shortListDTO.getIndustryId();
							industryIdMap.put("OPTION_2", String.valueOf(shortListDTO.getIndustryId()));
						}
						else
						{
							industryIds = String.valueOf(shortListDTO.getIndustryId());
							industryIdMap.put("OPTION_1", String.valueOf(shortListDTO.getIndustryId()));
						}
						showIndId = showIndId + 1;
						if (industryTitleMap.containsKey("INDUSTRY_OPTION_1"))
						{
							industryTitleMap.put("INDUSTRY_OPTION_2", properties.getProperty("com.eupath.path.option.two.title") + " " + shortListDTO.getIndustryName());
						}
						else
						{
							industryTitleMap.put("INDUSTRY_OPTION_1", properties.getProperty("com.eupath.path.option.two.title") + " " + shortListDTO.getIndustryName());
						}
						occIndustryIdMap.put("OPTION_2", "0");
						topTwoNameStr = topTwoNameStr + "#" + "in_" + shortListDTO.getIndustryName();
						nameOrderMap.put(2, "in_" + shortListDTO.getIndustryName());
					}
					shortList.remove(1);
				}
				if (!viewElectiveSerivce.checkEdupathExists(getStudentSessionObject().getId()))
				{
					checkEdupath =0;
				}
				else
				{
					checkEdupath=1;
				}
				
				
				shortList.remove(0);
				topTwoActive = "Y";
				getStudentSessionObject().setOrderNameMap(nameOrderMap);
				setResopnse();
				setNameTitle();
				cityList = getCityListFilter();
				//statecheck=1;
				//cityId=studentcityById.getCityLockId();

			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String getOccupation()
	{
		OUT.debug("EduPathAction Inside getOccupation ");
		try
		{
			
			String dataStr = "";
			dataStr = request.getParameter("oldData");
			service = new EduPathService();
			JSONArray jsonArr = service.getOccupationName(dataStr);
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.write(jsonArr.toString());
			out.close();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return null;
	}

	public String getDetails()
	{
		OUT.debug("EduPathAction Inside getDetails ");
		
		try
		{
			setResopnse();
			setNameTitle();
			setIndIdMap();
			setOccIndId();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return ERROR;
		}
		return "EDUPATH";
	}

	//vyankatesh null added   edupathDetailMap.put("INDUSTRY", service.createEduPathDetails(idsArr, "INDUSTRY",null));
	private void setResopnse()
	{
		ViewElectiveSerivce viewElectiveSerivce = new ViewElectiveSerivce();
		try
		{
			edupathDetailMap = new HashMap<String, Object>();
			service = new EduPathService();
			if (null != industryIds && !industryIds.isEmpty())
			{
				String[] idsArr = industryIds.split(",");
				showIndId = idsArr.length;
				edupathDetailMap.put("INDUSTRY", service.createEduPathDetails(idsArr, "INDUSTRY",null));
			}
			if (null != occupationIds && !occupationIds.isEmpty())
			{
				//vyankatesh Kudtarkar
				StudentCityLockDTO studentcityById = new EntranceExamsDAO().getCityIdByStudentId(getStudentSessionObject().getId());
				if(null != studentcityById  && !studentcityById.toString().isEmpty())
				{
					cityId = studentcityById.getCityLockId();
				}
				else
				{
					cityId = getStudentSessionObject().getCityId();
				}
				
				
				String[] idsArr = occupationIds.split(",");
				showOccId = idsArr.length;
				//System.out.println("getStudentSessionObject().getCityId()"+getStudentSessionObject().getCityId());
				edupathDetailMap.put("OCCUPATION", service.createEduPathDetails(idsArr, "OCCUPATION",cityId));
				
				//vyankatesh Kudtarkar
				//StudentCityLockDTO studentcityById = new EntranceExamsDAO().getCityIdByStudentId(getStudentSessionObject().getId());
				cityList = getCityListFilter();
				//if(null != studentcityById  && !studentcityById.toString().isEmpty())
				//{
				//	cityId = studentcityById.getCityLockId();
				//}
				//else
				//{
				//	cityId = getStudentSessionObject().getCityId();
				//}
				
				if (!viewElectiveSerivce.checkEdupathExists(getStudentSessionObject().getId()))
				{
					checkEdupath =0;
				}
				else
				{
					checkEdupath=1;
				}
			}

			if ((null != industryIds && !industryIds.isEmpty()) && null != occupationIds && !occupationIds.isEmpty())
			{
				edupathDetailMap = service.isSameStreamAndSubject(edupathDetailMap, "");
			}
			else if ((showOccId > 0 && showOccId < 2) && showIndId <= 0)
			{
				edupathDetailMap = service.isSameStreamAndSubject(edupathDetailMap, "");
			}

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	private void setNameTitle()
	{
		if (topTwoActive.equalsIgnoreCase("Y"))
		{
			if (null != topTwoNameStr && !topTwoNameStr.isEmpty())
			{
				occupationTitleMap = new TreeMap<String, String>();
				industryTitleMap = new TreeMap<String, String>();
				SortedMap<Integer, String> orderName = getStudentSessionObject().getOrderNameMap();
				int order1 = 1, order2 = 1;
				for (Map.Entry<Integer, String> map : orderName.entrySet())
				{
					if (map.getValue().startsWith("oc_"))
					{
						
						
						String name[] = map.getValue().split("_");
						
					
						occupationTitleMap.put("OCCUPATION_OPTION_" + order1, map.getKey() + "," + " " + name[1]);
						
						//BEGIN Sasedeve Added by Mrutyunjaya on Date 19-05-2017
						//System.out.println("=============================>"+Arrays.toString(name));
						
						
						String ColorCodeFitment="fitment_low";
						String ColorCodeNewFitment="fitment_low";
						
						
						if(Integer.parseInt(name[3])==4)
						{
							ColorCodeFitment="fitment_high";
						}
						else if(Integer.parseInt(name[3])==3)
						{
							ColorCodeFitment="fitment_above_average";
						}
						else if(Integer.parseInt(name[3])==2)
						{
							ColorCodeFitment="fitment_average";
						}
						
						
						
						if(Integer.parseInt(name[5])==4)
						{
							ColorCodeNewFitment="fitment_high";
						}
						else if(Integer.parseInt(name[5])==3)
						{
							ColorCodeNewFitment="fitment_above_average";
						}
						else if(Integer.parseInt(name[5])==2)
						{
							ColorCodeNewFitment="fitment_average";
						}
						
						
						occupationTitleMap.put("FITMENT_OPTION_" + order1, ColorCodeFitment);
						occupationTitleMap.put("NEWFITMENT_OPTION_" + order1, ColorCodeNewFitment);
						
						//END Sasedeve Added by Mrutyunjaya on Date 19-05-2017
						
						order1++;
						occOrderId = map.getKey();
					}

					if (map.getValue().startsWith("in_"))
					{
						String name[] = map.getValue().split("_");
						industryTitleMap.put("INDUSTRY_OPTION_" + order2, map.getKey() + "," + " " + name[1]);
						order2++;
						inOrderId = map.getKey();
					}
				}
			}
		}
		else
		{
			if (null != occupationNameStr && !occupationNameStr.isEmpty())
			{
				occupationTitleMap = new TreeMap<String, String>();
				occupationTitleMap.put("OCCUPATION_OPTION_1", properties.getProperty("com.eupath.path.option.one.title") + " " + occupationNameStr.trim());
			}
			if (null != industryNameStr && !industryNameStr.isEmpty())
			{
				industryTitleMap = new TreeMap<String, String>();
				industryTitleMap.put("INDUSTRY_OPTION_1", properties.getProperty("com.eupath.path.option.one.title") + " " + industryNameStr.trim());
			}

		}
	}

	private void setIndIdMap()
	{
		if (null != industryIds && !industryIds.isEmpty())
		{
			String[] idArr = industryIds.split(",");
			int opt = 1;
			for (String str : idArr)
			{
				industryIdMap.put("OPTION_" + opt, String.valueOf(str.trim()));
				opt++;
			}
		}
	}

	private void setOccIndId()
	{
		if (null != occupationIds && !occupationIds.isEmpty())
		{
			String[] idsArr = occupationIds.split(",");
			int opt = 1;
			for (String id : idsArr)
			{
				if (getStudentSessionObject().getOccIndIdMap().containsKey(Integer.valueOf(id)))
				{
					occIndustryIdMap.put("OPTION_" + opt, String.valueOf(getStudentSessionObject().getOccIndIdMap().get(Integer.valueOf(id))));
					opt++;
				}
				else
				{
					occIndustryIdMap.put("OPTION_1", "0");
				}
			}
		}
	}

	public List<ShortListDTO> getShortList()
	{
		return shortList;
	}

	public void setShortList(List<ShortListDTO> shortList)
	{
		this.shortList = shortList;
	}

	public String getTopTwoSelected()
	{
		return topTwoSelected;
	}

	public void setTopTwoSelected(String topTwoSelected)
	{
		this.topTwoSelected = topTwoSelected;
	}

	public Map<String, Object> getEdupathDetailMap()
	{
		return edupathDetailMap;
	}

	public void setEdupathDetailMap(Map<String, Object> edupathDetailMap)
	{
		this.edupathDetailMap = edupathDetailMap;
	}

	public String getOccupationIds()
	{
		return occupationIds;
	}

	public void setOccupationIds(String occupationIds)
	{
		this.occupationIds = occupationIds;
	}

	public String getIndustryIds()
	{
		return industryIds;
	}

	public void setIndustryIds(String industryIds)
	{
		this.industryIds = industryIds;
	}

	public int getShowIndId()
	{
		return showIndId;
	}

	public void setShowIndId(int showIndId)
	{
		this.showIndId = showIndId;
	}

	public int getShowOccId()
	{
		return showOccId;
	}

	public void setShowOccId(int showOccId)
	{
		this.showOccId = showOccId;
	}

	public Map<String, String> getOccupationTitleMap()
	{
		return occupationTitleMap;
	}

	public void setOccupationTitleMap(TreeMap<String, String> occupationTitleMap)
	{
		this.occupationTitleMap = occupationTitleMap;
	}

	public Map<String, String> getIndustryTitleMap()
	{
		return industryTitleMap;
	}

	public void setIndustryTitleMap(TreeMap<String, String> industryTitleMap)
	{
		this.industryTitleMap = industryTitleMap;
	}

	public String getIndustryNameStr()
	{
		return industryNameStr;
	}

	public void setIndustryNameStr(String industryNameStr)
	{
		this.industryNameStr = industryNameStr;
	}

	public String getOccupationNameStr()
	{
		return occupationNameStr;
	}

	public void setOccupationNameStr(String occupationNameStr)
	{
		this.occupationNameStr = occupationNameStr;
	}

	public String getTopTwoNameStr()
	{
		return topTwoNameStr;
	}

	public void setTopTwoNameStr(String topTwoNameStr)
	{
		this.topTwoNameStr = topTwoNameStr;
	}

	public String getTopTwoActive()
	{
		return topTwoActive;
	}

	public void setTopTwoActive(String topTwoActive)
	{
		this.topTwoActive = topTwoActive;
	}

	public Map<String, String> getIndustryIdMap()
	{
		return industryIdMap;
	}

	public void setIndustryIdMap(Map<String, String> industryIdMap)
	{
		this.industryIdMap = industryIdMap;
	}

	public Map<String, String> getOccIndustryIdMap()
	{
		return occIndustryIdMap;
	}

	public void setOccIndustryIdMap(Map<String, String> occIndustryIdMap)
	{
		this.occIndustryIdMap = occIndustryIdMap;
	}

	public void setOccupationTitleMap(SortedMap<String, String> occupationTitleMap)
	{
		this.occupationTitleMap = occupationTitleMap;
	}

	public void setIndustryTitleMap(SortedMap<String, String> industryTitleMap)
	{
		this.industryTitleMap = industryTitleMap;
	}

	public int getOccOrderId()
	{
		return occOrderId;
	}

	public void setOccOrderId(int occOrderId)
	{
		this.occOrderId = occOrderId;
	}

	public int getInOrderId()
	{
		return inOrderId;
	}

	public void setInOrderId(int inOrderId)
	{
		this.inOrderId = inOrderId;
	}
	//vyankatesh started yyyy
	public List<CityDTO> getCityListFilter() {
		List<CityDTO> cityList = new ArrayList<CityDTO>();
		try {
			cityList = new CityDAO().getAllCityList();
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return cityList;
	}
	public List<CityDTO> getCityList()
	{
		return cityList;
	}

	public void setCityList(List<CityDTO> cityList)
	{
		this.cityList = cityList;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getCheckEdupath() {
		return checkEdupath;
	}

	public void setCheckEdupath(int checkEdupath) {
		this.checkEdupath = checkEdupath;
	}

	public int getStatecheck() {
		return statecheck;
	}

	public void setStatecheck(int statecheck) {
		this.statecheck = statecheck;
	}
	
	
}
