package com.lodestar.edupath.integratedcourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.IntegratedCourseDTO;
import com.lodestar.edupath.datatransferobject.dto.ShortListCourseDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.integratedcourse.service.IntegratedCourseService;
import com.lodestar.edupath.path.service.EduPathSessionScheduleService;
import com.lodestar.edupath.studentcart.service.StudentCartDetailService;

public class IntegratedCourseAction extends BaseAction
{

	private static final long						serialVersionUID		= 1L;
	private static final Logger						OUT						= LoggerFactory.getLogger(IntegratedCourseAction.class);
	private int										occId;
	private Integer									occIndusryId;
	private boolean									flag;
	private String									name;
	private String									industryName;
	private Integer									industryId;
	private List<IntegratedCourseDTO>				courseList				= new ArrayList<IntegratedCourseDTO>();
	private Map<String, List<IntegratedCourseDTO>>	courseMap				= new HashMap<String, List<IntegratedCourseDTO>>();
	private List<OccupationDTO>						occWithCourseList		= new ArrayList<OccupationDTO>();
	private List<OccupationDTO>						occWithoutCourseList	= new ArrayList<OccupationDTO>();
	private int										count;
	private boolean									edupathExists;

	public String execute()
	{
		try
		{
			OUT.debug("IntegratedCourseAction : inside execute method");
			IntegratedCourseService integratedCourseService = new IntegratedCourseService();
			courseList = integratedCourseService.getCourseDetailsByOccId(occId);
			List<IntegratedCourseDTO> list = null;
			if (null != courseList && !courseList.isEmpty())
			{
				// list = new ArrayList<EntranceExamsDTO>();
				for (IntegratedCourseDTO courseDTO : courseList)
				{
					if (null != courseMap && courseMap.containsKey(courseDTO.getProgramType()))
					{
						list = courseMap.get(courseDTO.getProgramType());
					}
					else
					{
						list = new ArrayList<IntegratedCourseDTO>();
						courseMap.put(courseDTO.getProgramType(), list);
					}
					list.add(courseDTO);
					if (name == null)
					{
						name = courseDTO.getOccupationName();
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
			count = integratedCourseService.getCourseCount(getStudentSessionObject().getId());
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
			List<ShortListCourseDTO> shortListCourseList = new ArrayList<ShortListCourseDTO>();
			ShortListCourseDTO shortListCourseDTO;
			String ids = request.getParameter("courseIds");
			String[] intgeCourseIds = ids.split(",");
				for (String id : intgeCourseIds)
				{
					shortListCourseDTO = new ShortListCourseDTO();
					shortListCourseDTO.setOccupationId(occId);
					if (null != occIndusryId && occIndusryId > 0)
					{
						shortListCourseDTO.setOccIndustryId(occIndusryId);
					}
					shortListCourseDTO.setIntegratedCourseId(Integer.parseInt(id));
					shortListCourseDTO.setStudentId(getStudentSessionObject().getId());
					shortListCourseList.add(shortListCourseDTO);
				}
				new IntegratedCourseService().addAllIntegCourseDetails(shortListCourseList, getStudentSessionObject().getFullName(), getUserSessionObject().getLoginId());
				new EduPathSessionScheduleService().checkAndUpdateSessionSchedule(getStudentSessionObject().getId());
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}

	public String getOccWithCourse()
	{
		try
		{
			OUT.debug("IntegratedCourseAction : inside getOccWithCourse method");
			List<OccupationDTO> occList = null;
			Set<Integer> idSet = new HashSet<>();
					
			if (null != industryId)
			{
				occList = new IntegratedCourseService().getOccWithCourseByIndusId(industryId);
			}
			if (null != occList && !occList.isEmpty())
			{
				for (OccupationDTO occupationDTO : occList)
				{
					if(idSet.contains(occupationDTO.getId()))
					{
						continue;
					}
					
					if (occupationDTO.getIntegratedCourseId() != 0 && occupationDTO.getIntegratedCourseId() > 0)
					{
						occWithCourseList.add(occupationDTO);
					}
					else
					{
						occWithoutCourseList.add(occupationDTO);
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

	public List<IntegratedCourseDTO> getCourseList()
	{
		return courseList;
	}

	public void setCourseList(List<IntegratedCourseDTO> courseList)
	{
		this.courseList = courseList;
	}

	public Map<String, List<IntegratedCourseDTO>> getCourseMap()
	{
		return courseMap;
	}

	public void setCourseMap(Map<String, List<IntegratedCourseDTO>> courseMap)
	{
		this.courseMap = courseMap;
	}

	public String getIndustryName()
	{
		return industryName;
	}

	public void setIndustryName(String industryName)
	{
		this.industryName = industryName;
	}

	public Integer getIndustryId()
	{
		return industryId;
	}

	public void setIndustryId(Integer industryId)
	{
		this.industryId = industryId;
	}

	public List<OccupationDTO> getOccWithCourseList()
	{
		return occWithCourseList;
	}

	public void setOccWithCourseList(List<OccupationDTO> occWithCourseList)
	{
		this.occWithCourseList = occWithCourseList;
	}

	public List<OccupationDTO> getOccWithoutCourseList()
	{
		return occWithoutCourseList;
	}

	public void setOccWithoutCourseList(List<OccupationDTO> occWithoutCourseList)
	{
		this.occWithoutCourseList = occWithoutCourseList;
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

}
