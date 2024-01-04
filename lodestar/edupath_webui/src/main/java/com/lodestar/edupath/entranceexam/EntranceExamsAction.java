package com.lodestar.edupath.entranceexam;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.datatransferobject.dto.EntranceExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.ShortListExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentCityLockDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.enumtype.EduPathEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.entranceexam.service.EntranceExamsService;
import com.lodestar.edupath.path.service.EduPathService;
import com.lodestar.edupath.path.service.EduPathSessionScheduleService;
import com.lodestar.edupath.studentcart.service.StudentCartDetailService;

public class EntranceExamsAction extends BaseAction
{
	private static final long					serialVersionUID	= 1L;
	private static final Logger					OUT					= LoggerFactory.getLogger(EntranceExamsAction.class);
	private int									occId;
	private Integer								occIndusryId;
	private Integer								statenational;
	private boolean								flag;
	private String								name;
	private String								industryName;
	private Integer								industryId;
	private List<EntranceExamsDTO>				examList			= new ArrayList<EntranceExamsDTO>();
	private Map<String, List<EntranceExamsDTO>>	examMap				= new HashMap<String, List<EntranceExamsDTO>>();
	private List<OccupationDTO>					occWithExamsList	= new ArrayList<OccupationDTO>();
	private List<OccupationDTO>					occWithoutExamsList	= new ArrayList<OccupationDTO>();
	private int									count;
	private boolean								edupathExists;
	
	//vyankateshAdded
	private int									cityId;


	public String execute()
	{
		OUT.debug("EntranceExamsAction : inside execute method");
		try
		{
			EntranceExamsService entranceExamsService = new EntranceExamsService();
			OUT.debug("occupation id : {} and occIndustry id : {} and statenational id : {}", occId, occIndusryId,statenational);
			//   change examList = entranceExamsService.getExamDetailsByOccId(occId);
			
			//vyankatesh
			if (null != statenational)
			{
				examList= entranceExamsService.getExamDetailsByOccId(occId,statenational);
			}
			else
			{
				examList= entranceExamsService.getExamDetailsByOccId(occId);
			}
			
			List<EntranceExamsDTO> list = null;
			if (null != examList && !examList.isEmpty())
			{
				for (EntranceExamsDTO examsDTO : examList)
				{
					if (null != examMap && examMap.containsKey(examsDTO.getExamLevel()))
					{
						list = examMap.get(examsDTO.getExamLevel());
					}
					else
					{
						list = new ArrayList<EntranceExamsDTO>();
						examMap.put(examsDTO.getExamLevel(), list);
					}
					list.add(examsDTO);
					if (name == null)
					{
						name = examsDTO.getOccupationName();
					}
				}
				
				List<EduPathShortListDTO> edupathShortList = new StudentCartDetailService().getEdupathShortList(getStudentSessionObject().getId());
				if(edupathShortList == null || edupathShortList.isEmpty())
				{
					edupathExists = false;
				}
				else
				{
					edupathExists = true;
				}
			}
			count = entranceExamsService.getExamsCount(getStudentSessionObject().getId());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}

	public String saveDetails()
	{
		try
		{
			StudentCityLockDTO studentcityById = new EntranceExamsDAO().getCityIdByStudentId(getStudentSessionObject().getId());
			//vyankatesh 
			//new EntranceExamsService().removeFromShortListExams(getStudentSessionObject().getId(),studentcityById.getCityLockId());
			List<ShortListExamsDTO> shortListExamsList = new ArrayList<ShortListExamsDTO>();
			ShortListExamsDTO shortListExamsDTO;
			String ids = request.getParameter("examIds");
			String[] entrExamIds = ids.split(",");
			
			for (String id : entrExamIds)
			{
				shortListExamsDTO = new ShortListExamsDTO();
				shortListExamsDTO.setOccupationId(occId);
				if (null != occIndusryId)
				{
					shortListExamsDTO.setOccIndustryId(occIndusryId);
				}
				shortListExamsDTO.setEntranceExamId(Integer.parseInt(id));
				shortListExamsDTO.setStudentId(getStudentSessionObject().getId());
				
				//vyankatesh 4-25-2017
				if (null == String.valueOf(studentcityById.getCityLockId()))
				{
					shortListExamsDTO.setCityId(getStudentSessionObject().getCityId());
				}
				else
				{
					shortListExamsDTO.setCityId(studentcityById.getCityLockId());
				}
				
				
				
				
				//vyankatesh 4-25-2017
				shortListExamsList.add(shortListExamsDTO);
			}
			
			
			//vyankatesh add student id
			//new EntranceExamsService().addAllEntrExamsDetails(shortListExamsList, getStudentSessionObject().getFullName(), getUserSessionObject().getLoginId());
			new EntranceExamsService().addAllEntrExamsDetails(shortListExamsList, getStudentSessionObject().getFullName(), getUserSessionObject().getLoginId(),getStudentSessionObject().getId(),studentcityById.getCityLockId());
			new EduPathSessionScheduleService().checkAndUpdateSessionSchedule(getStudentSessionObject().getId());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}

	public String getOccWithExams()
	{
		try
		{
			OUT.debug("EntranceExamsAction : inside getOccWithExams method");
			List<OccupationDTO> occList = null;
			Set<Integer> idSet = new HashSet<Integer>();

			if (null != industryId)
			{
				occList = new EntranceExamsService().getOccWithExamsByIndusId(industryId);
			}
			if (null != occList && !occList.isEmpty())
			{
				for (OccupationDTO occupationDTO : occList)
				{
					if (idSet.contains(occupationDTO.getId()))
					{
						continue;
					}
					if (occupationDTO.getEntranceExamId() != 0 && occupationDTO.getEntranceExamId() > 0)
					{
						occWithExamsList.add(occupationDTO);
					}
					else
					{
						occWithoutExamsList.add(occupationDTO);
					}
					if (industryName == null)
					{
						industryName = occupationDTO.getIndustryName();
					}
					idSet.add(occupationDTO.getId());
				}
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return "showOccExam";
	}

	public int getOccId()
	{
		return occId;
	}

	public void setOccId(int occId)
	{
		this.occId = occId;
	}

	public List<EntranceExamsDTO> getExamList()
	{
		return examList;
	}

	public void setExamList(List<EntranceExamsDTO> examList)
	{
		this.examList = examList;
	}

	public Integer getOccIndusryId()
	{
		return occIndusryId;
	}

	public void setOccIndusryId(Integer occIndusryId)
	{
		this.occIndusryId = occIndusryId;
	}

	public boolean isFlag()
	{
		return flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Map<String, List<EntranceExamsDTO>> getExamMap()
	{
		return examMap;
	}

	public void setExamMap(Map<String, List<EntranceExamsDTO>> examMap)
	{
		this.examMap = examMap;
	}

	public Integer getIndustryId()
	{
		return industryId;
	}

	public void setIndustryId(Integer industryId)
	{
		this.industryId = industryId;
	}

	public List<OccupationDTO> getOccWithExamsList()
	{
		return occWithExamsList;
	}

	public void setOccWithExamsList(List<OccupationDTO> occWithExamsList)
	{
		this.occWithExamsList = occWithExamsList;
	}

	public List<OccupationDTO> getOccWithoutExamsList()
	{
		return occWithoutExamsList;
	}

	public void setOccWithoutExamsList(List<OccupationDTO> occWithoutExamsList)
	{
		this.occWithoutExamsList = occWithoutExamsList;
	}

	public String getIndustryName()
	{
		return industryName;
	}

	public void setIndustryName(String industryName)
	{
		this.industryName = industryName;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public boolean getEdupathExists()
	{
		return edupathExists;
	}

	public Integer getStatenational() {
		return statenational;
	}

	public void setStatenational(Integer statenational) {
		this.statenational = statenational;
	}
// added vyanktesh 
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	//@SuppressWarnings("null")
	public String getStateExams()
	{
		//String result=null;
		SqlSession session = null;
		List<String> commonList = new ArrayList<String>();
		EntranceExamsDAO EntranceExamsDAO = new EntranceExamsDAO();
		
		StudentCityLockDTO StudentCityLock =new StudentCityLockDTO();
		
		try
		{
			
			EntranceExamsDTO dtoe = new EntranceExamsDTO();
			OUT.debug("EntranceExamsAction : inside getStateExams method");
			
			List<StudentCityLockDTO> studentIdList = EntranceExamsDAO.getAllStudentIdFromLock();
			Set<String> StudentIDSet = getAllStudentIDSet(studentIdList);
			if (StudentIDSet.contains(String.valueOf(getStudentSessionObject().getId())))
			{
				StudentCityLock.setCityLockId(cityId);
				StudentCityLock.setStudentLockId(getStudentSessionObject().getId());
				//EntranceExamsDAO.updateLockDate(session, StudentCityLock);
				//session.commit();
			}
			else
			{
				//StudentCityLock.setId(5);
				StudentCityLock.setCityLockId(cityId);
				StudentCityLock.setStudentLockId(getStudentSessionObject().getId());
				//EntranceExamsDAO.insertLockDate(session, StudentCityLock);
				
				
			}
			
			
			List<EntranceExamsDTO> stateexamList = new EntranceExamsDAO().getEntranceExamsDetailsByOccupatioIdstate(occId,cityId);

			boolean isRequirede = false;
			for (EntranceExamsDTO entranceExamsDTO : stateexamList)
			{
				if (entranceExamsDTO.getRequired().equalsIgnoreCase(EduPathEnum.REQUIRED_C.getValue()))
				{
					isRequirede = true;
				}
				commonList.add("\t" + entranceExamsDTO.getExamName());
			}

			
			if (isRequirede)
			{
				dtoe.setRequired(EduPathEnum.REQUIRED_C.getValue());
			}
			else
			{
				dtoe.setRequired(EduPathEnum.REQUIRED_P.getValue());
			}
			dtoe.setExamName(StringUtils.join(commonList, ","));
			commonList.clear();
			dtoe.setOccupationId(occId);
			dtoe.getExamName();
			
		
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			out.write(dtoe.getExamName());
			out.close();
			
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
		
		
	
	}

	private Set<String> getAllStudentIDSet(List<StudentCityLockDTO> studentIdList) {
		Set<String> studentIDSet = null;
		if(studentIdList != null) {
			
			studentIDSet = new HashSet<String>();
			for (StudentCityLockDTO StudentCityLockDTO : studentIdList) {
				studentIDSet.add(String.valueOf(StudentCityLockDTO.getStudentLockId()));
			}
			
		}
		return studentIDSet;
	}
	public String getStateExamsSecond()
	{
		//String result=null;
	
		
		SqlSession session = null;
		List<String> commonList = new ArrayList<String>();
		EntranceExamsDAO EntranceExamsDAO = new EntranceExamsDAO();
		
		StudentCityLockDTO StudentCityLock =new StudentCityLockDTO();
		try
		{
			EntranceExamsDTO dtoe = new EntranceExamsDTO();
			OUT.debug("EntranceExamsAction : inside getStateExams method");
			
			List<StudentCityLockDTO> studentIdList = EntranceExamsDAO.getAllStudentIdFromLock();
			Set<String> StudentIDSet = getAllStudentIDSet(studentIdList);
			if (StudentIDSet.contains(String.valueOf(getStudentSessionObject().getId())))
			{
				StudentCityLock.setCityLockId(cityId);
				StudentCityLock.setStudentLockId(getStudentSessionObject().getId());
				EntranceExamsDAO.updateLockDate(session, StudentCityLock);
				//session.commit();
			}
			else
			{
				//StudentCityLock.setId(5);
				StudentCityLock.setCityLockId(cityId);
				StudentCityLock.setStudentLockId(getStudentSessionObject().getId());
				EntranceExamsDAO.insertLockDate(session, StudentCityLock);
				
				
			}
			
			
			
			
			List<EntranceExamsDTO> stateexamList = new EntranceExamsDAO().getEntranceExamsDetailsByOccupatioIdstate(occId,cityId);

			boolean isRequirede = false;
			for (EntranceExamsDTO entranceExamsDTO : stateexamList)
			{
				if (entranceExamsDTO.getRequired().equalsIgnoreCase(EduPathEnum.REQUIRED_C.getValue()))
				{
					isRequirede = true;
				}
				commonList.add("\t" + entranceExamsDTO.getExamName());
			}

			
			if (isRequirede)
			{
				dtoe.setRequired(EduPathEnum.REQUIRED_C.getValue());
			}
			else
			{
				dtoe.setRequired(EduPathEnum.REQUIRED_P.getValue());
			}
			dtoe.setExamName(StringUtils.join(commonList, ","));
			commonList.clear();
			dtoe.setOccupationId(occId);
			dtoe.getExamName();
			
		
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			out.write(dtoe.getExamName());
			out.close();
			
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
		
	}
	
	public int lockOnlyCity()
	{
		
		SqlSession session = null;
		
		EntranceExamsDAO EntranceExamsDAO = new EntranceExamsDAO();
		
		StudentCityLockDTO StudentCityLock =new StudentCityLockDTO();
		try
		{
			
			OUT.debug("EntranceExamsAction : inside lockOnlyCity method");
			
			List<StudentCityLockDTO> studentIdList = EntranceExamsDAO.getAllStudentIdFromLock();
			Set<String> StudentIDSet = getAllStudentIDSet(studentIdList);
			if (StudentIDSet.contains(String.valueOf(getStudentSessionObject().getId())))
			{
				StudentCityLock.setCityLockId(cityId);
				StudentCityLock.setStudentLockId(getStudentSessionObject().getId());
				EntranceExamsDAO.updateLockDate(session, StudentCityLock);
				//session.commit();
			}
			else
			{
				//StudentCityLock.setId(5);
				StudentCityLock.setCityLockId(cityId);
				StudentCityLock.setStudentLockId(getStudentSessionObject().getId());
				EntranceExamsDAO.insertLockDate(session, StudentCityLock);
				
				
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		
		
		
		return cityId;
		
	}
	
	

}
