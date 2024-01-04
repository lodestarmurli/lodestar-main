package com.lodestar.edupath.induoccchoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.ManualSearch;
import com.lodestar.edupath.datatransferobject.dto.EducationLevelDTO;
import com.lodestar.edupath.datatransferobject.dto.manualSearch.ManualSearchDTO;
import com.lodestar.edupath.datatransferobject.dto.manualSearch.ManualSearchQuestionMappingDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.tags.AlertsDTO;
import com.lodestar.edupath.datatransferobject.dto.tags.AreaDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;
import com.lodestar.edupath.induoccchoice.service.ManualSearchService;
import com.lodestar.edupath.util.ApplicationProperties;

public class ManualSearchAction extends BaseAction
{
	private static final long						serialVersionUID	= 1L;
	private static final Logger						OUT					= LoggerFactory.getLogger(SystemRecommendationAction.class);
	private int										studentId;
	private List<EducationLevelDTO>					educationLevelList	= new ArrayList<EducationLevelDTO>();
	private Map<String, Map<String, List<AreaDTO>>>	categoryMap			= new HashMap<String, Map<String, List<AreaDTO>>>();
	private String									selectedAnswer;
	private Map<String, List<OccupationDTO>>		manualSearchMap		= new HashMap<String, List<OccupationDTO>>();
	private Map<Long, List<AlertsDTO>>				alerts				= new HashMap<Long, List<AlertsDTO>>();
	private ManualSearchService						service;
	//START Sasedeve  by Mrutyunjaya on date 03-05-2017
		private static HashMap<Integer, String>	fitmentcolors			= new HashMap<Integer, String>();

		static
		{
			fitmentcolors.put(ApplicationConstants.FitMent.HIG.getScore(), ApplicationConstants.FitMent.HIG.getColor());
			fitmentcolors.put(ApplicationConstants.FitMent.A_AVG.getScore(), ApplicationConstants.FitMent.A_AVG.getColor());
			fitmentcolors.put(ApplicationConstants.FitMent.AVG.getScore(), ApplicationConstants.FitMent.AVG.getColor());
			fitmentcolors.put(ApplicationConstants.FitMent.LOW.getScore(), ApplicationConstants.FitMent.LOW.getColor());
		}
		
		//END Sasedeve  by Mrutyunjaya on date 03-05-2017
	public ManualSearchAction()
	{
		service = new ManualSearchService();
	}

	public String execute()
	{
		try
		{
			if (studentId == 0)
			{
				studentId = getStudentSessionObject().getId();
			}
			UserSessionObject sessionObject = getUserSessionObject();
			if (null == sessionObject)
			{
				return "SessionExpired";
			}
			OUT.debug("Get manual search feilds for studentId:{}", studentId);
			requiredField();
			// SystemRecommendationService service = new SystemRecommendationService();
			// systemRecommendation = service.getSystemRecommendation(studentId);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	private void requiredField()
	{
		educationLevelList = service.getQualification();
		service.getCategoryMap(categoryMap);
	}

	public String getSearchResult()
	{
		OUT.debug("Getting Manual Search for search:{}", selectedAnswer);
		if (null == selectedAnswer || selectedAnswer.isEmpty())
		{
			addActionError(ApplicationProperties.getInstance().getProperty("com.edupath.action.internal.error"));
			return ERROR;
		}
		ManualSearchDTO manualSearchDTO = createManualSearchDTO();
		try
		{
			//START Sasedeve  by Mrutyunjaya on date 03-04-2017
			manualSearchMap = new ManualSearch().getManualSearchResult(manualSearchDTO,getStudentSessionObject().getId());
			//END Sasedeve  by Mrutyunjaya on date 03-05-2017
			alerts = service.getAlertDetails(manualSearchMap);
			request.setAttribute("preferedQuestions", getPreferedQuestionsHeader(manualSearchDTO));
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			addActionError(ApplicationProperties.getInstance().getProperty("com.edupath.action.internal.error"));
			requiredField();
			return ERROR;
		}
		request.setAttribute("actiontype", "searchResult");
		return SUCCESS;
	}

	private String getPreferedQuestionsHeader(ManualSearchDTO manualSearchDTO)
	{
		StringBuilder preferedQuestionLable = new StringBuilder();
		List<ManualSearchQuestionMappingDTO> questionSlList = null;
		if (null != manualSearchDTO && null != manualSearchDTO.getMandatoryQuestions() && !manualSearchDTO.getMandatoryQuestions().isEmpty())
		{
			try
			{
				questionSlList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_MANUAL_SEARCH_MAP,
						new ManualSearchQuestionMappingDTO());
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
			if (null != questionSlList && !questionSlList.isEmpty())
			{
				List<Double> mandatoryQuestionList = CommonUtil.convertCommaSeperatedToDoubleList(manualSearchDTO.getMandatoryQuestions());
				for (ManualSearchQuestionMappingDTO manualSearchQuestionMappingDTO : questionSlList)
				{
					if (mandatoryQuestionList.contains(manualSearchQuestionMappingDTO.getQuestionSlNo()))
					{
						if (!preferedQuestionLable.toString().isEmpty())
						{
							preferedQuestionLable.append(" + ");
						}
						preferedQuestionLable.append(manualSearchQuestionMappingDTO.getDisplayString());
					}
				}
			}
		}

		return preferedQuestionLable.toString();

	}

	private ManualSearchDTO createManualSearchDTO()
	{
		ManualSearchDTO manualSearchDTO = null;
		JSONObject jsonObject = new JSONObject(selectedAnswer);
		StringBuilder mandatoryQuestions = null;
		Map<Double, String> searchQuestions = null;
		if (null != jsonObject && jsonObject.has("preferedQuestion"))
		{
			mandatoryQuestions = new StringBuilder();
			JSONArray jsonArray = jsonObject.getJSONArray("preferedQuestion");
			JSONObject eachTransferData = null;
			for (int i = 0; i < jsonArray.length(); i++)
			{
				eachTransferData = jsonArray.getJSONObject(i);
				if (eachTransferData.has("slNo"))
				{
					if (null != mandatoryQuestions && !mandatoryQuestions.toString().isEmpty())
					{
						mandatoryQuestions.append(",");
					}
					mandatoryQuestions.append(eachTransferData.getDouble("slNo"));
				}
			}
		}
		if (null != mandatoryQuestions && !mandatoryQuestions.toString().isEmpty())
		{
			manualSearchDTO = new ManualSearchDTO();
			manualSearchDTO.setMandatoryQuestions(mandatoryQuestions.toString());
		}
		if (null != jsonObject && jsonObject.has("selectedAnswer"))
		{
			searchQuestions = new LinkedHashMap<Double, String>();
			JSONArray jsonArray = jsonObject.getJSONArray("selectedAnswer");
			JSONObject eachTransferData = null;
			for (int i = 0; i < jsonArray.length(); i++)
			{
				eachTransferData = jsonArray.getJSONObject(i);
				if (eachTransferData.has("slNo") && eachTransferData.has("answer"))
				{
					searchQuestions.put(eachTransferData.getDouble("slNo"), eachTransferData.getString("answer"));
				}
			}
		}
		if (null != searchQuestions && !searchQuestions.isEmpty())
		{
			if (null == manualSearchDTO)
			{
				manualSearchDTO = new ManualSearchDTO();
			}
			manualSearchDTO.setSearchQuestions(searchQuestions);
		}

		return manualSearchDTO;

	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public List<EducationLevelDTO> getEducationLevelList()
	{
		return educationLevelList;
	}

	public void setEducationLevelList(List<EducationLevelDTO> educationLevelList)
	{
		this.educationLevelList = educationLevelList;
	}

	public Map<String, Map<String, List<AreaDTO>>> getCategoryMap()
	{
		return categoryMap;
	}

	public void setCategoryMap(Map<String, Map<String, List<AreaDTO>>> categoryMap)
	{
		this.categoryMap = categoryMap;
	}

	public String getSelectedAnswer()
	{
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer)
	{
		this.selectedAnswer = selectedAnswer;
	}

	public Map<String, List<OccupationDTO>> getManualSearchMap()
	{
		return manualSearchMap;
	}

	public void setManualSearchMap(Map<String, List<OccupationDTO>> manualSearchMap)
	{
		this.manualSearchMap = manualSearchMap;
	}

	public Map<Long, List<AlertsDTO>> getAlerts()
	{
		return alerts;
	}

	public void setAlerts(Map<Long, List<AlertsDTO>> alerts)
	{
		this.alerts = alerts;
	}
	//START Sasedeve  by Mrutyunjaya on date 03-05-2017
		public HashMap<Integer, String> getFitmentcolors()
		{
			return fitmentcolors;
		}
	//END Sasedeve  by Mrutyunjaya on date 03-05-2017

}
