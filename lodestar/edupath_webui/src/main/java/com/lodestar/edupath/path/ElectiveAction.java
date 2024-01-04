package com.lodestar.edupath.path;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.datatransferobject.dto.StudentCityLockDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.CombinationDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.path.service.EduPathSessionScheduleService;
import com.lodestar.edupath.path.service.ViewElectiveSerivce;

public class ElectiveAction extends BaseAction
{
	private static final Logger		OUT					= LoggerFactory.getLogger(ElectiveAction.class);
	private static final long		serialVersionUID	= 1L;

	private String					option1Id;
	private int						option1EdupathId;
	private int						option1OccIndusId;
	private String					option1ManStreamId;
	private String					option1PreStreamIds;
	private String					option1ManSubjectId;
	private String					option1PreSubjectIds;
	private String					option2Id;
	private int						option2EdupathId;
	private int						option2OccIndusId;
	private String					option2ManStreamId;
	private String					option2PreStreamIds;
	private String					option2ManSubjectId;
	private String					option2PreSubjectIds;
	private boolean					allowSelect;
	private String					selectedStreamId;
	private String					selectedSubjectId;
	private int						cityId;

	private String					status;
	private int						streamId;
	private String					option1DegreeStream;
	private String					option2DegreeStream;
	private String					selectedCombinations;

	private Map<String, Object>		electiveData;
	private List<CombinationDTO>	combinationData;

	public String view() throws Exception
	{
		int studentId = getStudentSessionObject().getId();
		OUT.debug("Getting the elective data for student :{}, option1Id:{}, option2Id:{}, cityId:{}", studentId, option1Id, option2Id,cityId);
		try
		{
			electiveData = new ViewElectiveSerivce().getElectiveData(option1Id, option1ManStreamId, option1PreStreamIds, option1ManSubjectId, option1PreSubjectIds,
					option2Id, option2ManStreamId, option2PreStreamIds, option2ManSubjectId, option2PreSubjectIds, selectedStreamId, selectedSubjectId, cityId,
					studentId);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			throw e;
		}
		return SUCCESS;
	}

	public String subjectCombinationDetails() throws Exception
	{
		//vyankatesh
//		StudentCityLockDTO StudentCityLock =new StudentCityLockDTO();
//		SqlSession session = null;
//		EntranceExamsDAO EntranceExamsDAO = new EntranceExamsDAO();
		try
		{
		
//			List<StudentCityLockDTO> studentIdList = EntranceExamsDAO.getAllStudentIdFromLock();
//			Set<String> StudentIDSet = getAllStudentIDSet(studentIdList);
//			if (StudentIDSet.contains(String.valueOf(getStudentSessionObject().getId())))
//			{
//				StudentCityLock.setCityLockId(cityId);
//				StudentCityLock.setStudentLockId(getStudentSessionObject().getId());
//				EntranceExamsDAO.updateLockDate(session, StudentCityLock);
//				//session.commit();
//			}
//			else
//			{
//				//StudentCityLock.setId(5);
//				StudentCityLock.setCityLockId(cityId);
//				StudentCityLock.setStudentLockId(getStudentSessionObject().getId());
//				EntranceExamsDAO.insertLockDate(session, StudentCityLock);
//				
//				
//			}
			
			
			OUT.debug("Getting the subject combinations for stream:{}", streamId);
			combinationData = new ViewElectiveSerivce().getElectiveCombinationWithCollegeCount(streamId, selectedSubjectId != null
					&& !selectedSubjectId.trim().isEmpty() ? Integer.valueOf(selectedSubjectId) : -1, cityId);
			return "electivecomb";
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			throw e;
		}
	}

	public String addTOCart()
	{
		int studentId = getStudentSessionObject().getId();
		 
		//vyankatesh 25-4-2017
		
		StudentCityLockDTO StudentCityLock =new StudentCityLockDTO();
		SqlSession session = null;
	EntranceExamsDAO EntranceExamsDAO = new EntranceExamsDAO();
	//vyankatesh 25-4-2017
		try
		{
			ViewElectiveSerivce viewElectiveSerivce = new ViewElectiveSerivce();
			if (!viewElectiveSerivce.checkEdupathExists(studentId))
			{
				viewElectiveSerivce.addEduNElectiveShortList(studentId, selectedStreamId, option1Id, option1ManStreamId, option1OccIndusId, option1DegreeStream,
						option1EdupathId, option2Id, option2ManStreamId, option2OccIndusId, option2DegreeStream, option2EdupathId, selectedCombinations);
				status = ApplicationConstants.SUCCESS;
				new EduPathSessionScheduleService().checkAndUpdateSessionSchedule(getStudentSessionObject().getId());
				StringBuilder auditMessage = new StringBuilder();
				auditMessage.append("Student's ").append(getStudentSessionObject().getFullName()).append(" edupath added");
				
				
				insertAudit(auditMessage.toString(), getUserSessionObject());
				
				//vyankatesh 25-4-2017
				List<StudentCityLockDTO> studentIdList = EntranceExamsDAO.getAllStudentIdFromLock();
			Set<String> StudentIDSet = getAllStudentIDSet(studentIdList);
				if (StudentIDSet.contains(String.valueOf(getStudentSessionObject().getId())))
				{
					//StudentCityLock.setCityLockId(cityId);
					//StudentCityLock.setStudentLockId(getStudentSessionObject().getId());
					//EntranceExamsDAO.updateLockDate(session, StudentCityLock);
					
				}
				else
				{
					
					StudentCityLock.setCityLockId(getStudentSessionObject().getCityId());
					StudentCityLock.setStudentLockId(getStudentSessionObject().getId());
					EntranceExamsDAO.insertLockDate(session, StudentCityLock);
					
					
				}
				//vyankatesh 25-4-2017
				
			}
			else
			{
				status = "error";
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			status = ApplicationConstants.EXCEPTION;
		}
		return "status";
	}

	private void insertAudit(String auditMessage, UserSessionObject userSessionObject)
	{
		AuditTrailLogger.addAuditInfo(ModuleNameEnum.EDU_PATH, auditMessage, userSessionObject.getLoginId());
	}

	public String getOption1Id()
	{
		return option1Id;
	}

	public void setOption1Id(String option1Id)
	{
		this.option1Id = option1Id;
	}

	public String getOption1ManStreamId()
	{
		return option1ManStreamId;
	}

	public void setOption1ManStreamId(String option1ManStreamId)
	{
		this.option1ManStreamId = option1ManStreamId;
	}

	public String getOption1PreStreamIds()
	{
		return option1PreStreamIds;
	}

	public void setOption1PreStreamIds(String option1PreStreamIds)
	{
		this.option1PreStreamIds = option1PreStreamIds;
	}

	public String getOption1ManSubjectId()
	{
		return option1ManSubjectId;
	}

	public void setOption1ManSubjectId(String option1ManSubjectId)
	{
		this.option1ManSubjectId = option1ManSubjectId;
	}

	public String getOption1PreSubjectIds()
	{
		return option1PreSubjectIds;
	}

	public void setOption1PreSubjectIds(String option1PreSubjectIds)
	{
		this.option1PreSubjectIds = option1PreSubjectIds;
	}

	public String getOption2Id()
	{
		return option2Id;
	}

	public void setOption2Id(String option2Id)
	{
		this.option2Id = option2Id;
	}

	public String getOption2ManStreamId()
	{
		return option2ManStreamId;
	}

	public void setOption2ManStreamId(String option2ManStreamId)
	{
		this.option2ManStreamId = option2ManStreamId;
	}

	public String getOption2PreStreamIds()
	{
		return option2PreStreamIds;
	}

	public void setOption2PreStreamIds(String option2PreStreamIds)
	{
		this.option2PreStreamIds = option2PreStreamIds;
	}

	public String getOption2ManSubjectId()
	{
		return option2ManSubjectId;
	}

	public void setOption2ManSubjectId(String option2ManSubjectId)
	{
		this.option2ManSubjectId = option2ManSubjectId;
	}

	public String getOption2PreSubjectIds()
	{
		return option2PreSubjectIds;
	}

	public void setOption2PreSubjectIds(String option2PreSubjectIds)
	{
		this.option2PreSubjectIds = option2PreSubjectIds;
	}

	public boolean isAllowSelect()
	{
		return allowSelect;
	}

	public void setAllowSelect(boolean allowSelect)
	{
		this.allowSelect = allowSelect;
	}

	public String getSelectedStreamId()
	{
		return selectedStreamId;
	}

	public void setSelectedStreamId(String selectedStreamId)
	{
		this.selectedStreamId = selectedStreamId;
	}

	public String getSelectedSubjectId()
	{
		return selectedSubjectId;
	}

	public void setSelectedSubjectId(String selectedSubjectId)
	{
		this.selectedSubjectId = selectedSubjectId;
	}

	public int getCityId()
	{
		return cityId;
	}

	public void setCityId(int cityId)
	{
		this.cityId = cityId;
	}

	public String getStatus()
	{
		return status;
	}

	public int getOption1EdupathId()
	{
		return option1EdupathId;
	}

	public void setOption1EdupathId(int option1EdupathId)
	{
		this.option1EdupathId = option1EdupathId;
	}

	public int getOption1OccIndusId()
	{
		return option1OccIndusId;
	}

	public void setOption1OccIndusId(int option1OccIndusId)
	{
		this.option1OccIndusId = option1OccIndusId;
	}

	public int getOption2EdupathId()
	{
		return option2EdupathId;
	}

	public void setOption2EdupathId(int option2EdupathId)
	{
		this.option2EdupathId = option2EdupathId;
	}

	public int getOption2OccIndusId()
	{
		return option2OccIndusId;
	}

	public void setOption2OccIndusId(int option2OccIndusId)
	{
		this.option2OccIndusId = option2OccIndusId;
	}

	public int getStreamId()
	{
		return streamId;
	}

	public void setStreamId(int streamId)
	{
		this.streamId = streamId;
	}

	public String getOption1DegreeStream()
	{
		return option1DegreeStream;
	}

	public void setOption1DegreeStream(String option1DegreeStream)
	{
		this.option1DegreeStream = option1DegreeStream;
	}

	public String getOption2DegreeStream()
	{
		return option2DegreeStream;
	}

	public void setOption2DegreeStream(String option2DegreeStream)
	{
		this.option2DegreeStream = option2DegreeStream;
	}

	public Map<String, Object> getElectiveData()
	{
		return electiveData;
	}

	public String getSelectedCombinations()
	{
		return selectedCombinations;
	}

	public void setSelectedCombinations(String selectedCombinations)
	{
		this.selectedCombinations = selectedCombinations;
	}

	public List<CombinationDTO> getCombinationData()
	{
		return combinationData;
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

}
