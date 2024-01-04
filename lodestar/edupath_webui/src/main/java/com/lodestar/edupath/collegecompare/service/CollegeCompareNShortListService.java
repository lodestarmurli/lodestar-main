package com.lodestar.edupath.collegecompare.service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.collegecompare.bean.CollegeCompareBean;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.college.CollegeActivitiesDAO;
import com.lodestar.edupath.dataaccessobject.dao.college.CollegeDAO;
import com.lodestar.edupath.dataaccessobject.dao.college.CollegeInfraDAO;
import com.lodestar.edupath.dataaccessobject.dao.collegeshortlist.StudentCollegeShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.EduPathShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.datatransferobject.dto.StudentCollegeShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.collegedetails.CollegeActivityVO;
import com.lodestar.edupath.datatransferobject.dto.collegedetails.CollegeDetailsVO;
import com.lodestar.edupath.datatransferobject.dto.collegedetails.CollegeInfraStructVO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.exception.EdupathException;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class CollegeCompareNShortListService
{
	private static final String	BASIC_DETAILS		= "BASIC_DETAILS";
	private static final String	ACTIVITY_DETAILS	= "ACTIVITY_DETAILS";
	private static final String	INFRA_DETAILS		= "INFRA_DETAILS";

	@SuppressWarnings("unchecked")
	public Map<String, Map<String, List<Object>>> getCollegeDetailsByIds(CollegeCompareBean bean) throws Exception
	{
		bean.setStreamId(new EduPathShortListDAO().getStreamIdByStudentId(bean.getStudentId()));
		bean.setCollegeIds(CommonUtil.convertCommaSeperatedToIntegerList(bean.getCollegeIdsStr()));

		ObjectMapper objectMapper = new ObjectMapper();
		Map<Object, Object> convertValue = (Map<Object, Object>) objectMapper.convertValue(bean, Map.class);
		List<CollegeDetailsVO> collegeDetails = new CollegeDAO().getCollegeDetailsByCollegeIds(convertValue);

		Map<String, Map<String, List<Object>>> finalCollegeDetailsMap = new HashMap<String, Map<String, List<Object>>>();
		if (collegeDetails != null)
		{
			Map<String, List<Object>> collegeBasicDetailsMap = new LinkedHashMap<String, List<Object>>();
			Map<String, List<Object>> collegeActivityDetailsMap = new LinkedHashMap<String, List<Object>>();
			Map<String, List<Object>> collegeInfraDetailsMap = new LinkedHashMap<String, List<Object>>();
			initMapKeyValue(collegeBasicDetailsMap, collegeActivityDetailsMap, collegeInfraDetailsMap);
			setMapKeyValue(collegeDetails, collegeBasicDetailsMap, collegeActivityDetailsMap, collegeInfraDetailsMap);
			finalCollegeDetailsMap.put(BASIC_DETAILS, collegeBasicDetailsMap);
			finalCollegeDetailsMap.put(ACTIVITY_DETAILS, collegeActivityDetailsMap);
			finalCollegeDetailsMap.put(INFRA_DETAILS, collegeInfraDetailsMap);
		}
		return finalCollegeDetailsMap;

	}

	/**
	 * @param collegeDetails
	 * @param collegeBasicDetailsMap
	 * @param collegeActivityDetailsMap
	 * @param collegeInfraDetailsMap
	 */
	private void setMapKeyValue(List<CollegeDetailsVO> collegeDetails, Map<String, List<Object>> collegeBasicDetailsMap,
			Map<String, List<Object>> collegeActivityDetailsMap, Map<String, List<Object>> collegeInfraDetailsMap)
	{
		Set<String> activitySet = null;
		Map<String, CollegeInfraStructVO> infraMap = null;
		CollegeInfraStructVO collegeInfraExist = new CollegeInfraStructVO("com.edupath.college.compare.yes.label");
		CollegeInfraStructVO collegeInfraNotExist = new CollegeInfraStructVO("com.edupath.college.compare.no.label");
		for (CollegeDetailsVO collegeDetailsVO : collegeDetails)
		{
			collegeBasicDetailsMap.get("collegeId").add(collegeDetailsVO);
			collegeBasicDetailsMap.get("com.edupath.college.compare.collegename.label").add(collegeDetailsVO.getCollegeName());
			collegeBasicDetailsMap.get("com.edupath.college.compare.ageofinstitute.label").add(collegeDetailsVO.getAgeOfTheInstitute());
			collegeBasicDetailsMap.get("com.edupath.college.compare.collegetype.label").add(collegeDetailsVO.getCollegeType());
			collegeBasicDetailsMap.get("com.edupath.college.compare.sexfor.label").add(collegeDetailsVO.getSexPref());
			collegeBasicDetailsMap.get("com.edupath.college.compare.streamsavailable.label").add(collegeDetailsVO.getAvailableStreams());
			collegeBasicDetailsMap.get("com.edupath.college.compare.electivecombination.label").add(collegeDetailsVO.getAvailableCombinations());
			collegeBasicDetailsMap.get("com.edupath.college.compare.seatsforselectedstream.label").add(String.valueOf(collegeDetailsVO.getNoStudents()));
			collegeBasicDetailsMap.get("com.edupath.college.compare.cutoffforselectedstream.label").add(String.valueOf(collegeDetailsVO.getLastCutOff()));
			collegeBasicDetailsMap.get("com.edupath.college.compare.streamfee.label").add(collegeDetailsVO.getFees());
			collegeBasicDetailsMap.get("com.edupath.college.compare.staff.label").add(String.valueOf(collegeDetailsVO.getNoTeachingStaff()));
			collegeBasicDetailsMap.get("com.edupath.college.compare.address.label").add(collegeDetailsVO.getAddress());
			collegeBasicDetailsMap.get("com.edupath.college.compare.website.label").add(collegeDetailsVO.getWebsite());
			collegeBasicDetailsMap.get("com.edupath.college.compare.applicationcost.label").add(String.valueOf(collegeDetailsVO.getApplicationFormFees()));
			collegeBasicDetailsMap.get("com.edupath.college.compare.applicationlastdate.label").add(collegeDetailsVO.getAddFormSubmDate());
			collegeBasicDetailsMap.get("com.edupath.college.compare.admission.label").add(collegeDetailsVO.getAddFormSubmAddr());

			activitySet = new HashSet<String>();
			if (collegeDetailsVO.getActivities() != null)
			{
				for (CollegeActivityVO activityVO : collegeDetailsVO.getActivities())
				{
					activitySet.add(activityVO.getActivity());
				}
			}

			infraMap = new HashMap<String, CollegeInfraStructVO>();
			if (collegeDetailsVO.getInfraStructs() != null)
			{
				for (CollegeInfraStructVO infraStructVO : collegeDetailsVO.getInfraStructs())
				{
					infraMap.put(infraStructVO.getInfra(), infraStructVO);
				}
			}
			// Setting all extra activity yes/no
			for (Entry<String, List<Object>> entry : collegeActivityDetailsMap.entrySet())
			{
				if (activitySet.contains(entry.getKey()))
				{
					collegeActivityDetailsMap.get(entry.getKey()).add("com.edupath.college.compare.yes.label");
				}
				else
				{
					entry.getValue().add("com.edupath.college.compare.no.label");
				}
			}

			// Setting all extra infra yes/no
			for (Entry<String, List<Object>> entry : collegeInfraDetailsMap.entrySet())
			{
				if (infraMap.containsKey(entry.getKey()))
				{
					if (infraMap.get(entry.getKey()).getCapacity() != null)
					{
						collegeInfraDetailsMap.get(entry.getKey()).add(infraMap.get(entry.getKey()));
					}
					else if (infraMap.get(entry.getKey()).getCount() > -1)
					{
						collegeInfraDetailsMap.get(entry.getKey()).add(infraMap.get(entry.getKey()));
					}
					else
					{
						entry.getValue().add(collegeInfraExist);
					}
				}
				else
				{
					entry.getValue().add(collegeInfraNotExist);
				}
			}

		}
	}

	/**
	 * @param collegeBasicDetailsMap
	 * @param collegeActivityDetailsMap
	 * @param collegeInfraDetailsMap
	 * @throws Exception
	 */
	private void initMapKeyValue(Map<String, List<Object>> collegeBasicDetailsMap, Map<String, List<Object>> collegeActivityDetailsMap,
			Map<String, List<Object>> collegeInfraDetailsMap) throws Exception
	{
		collegeBasicDetailsMap.put("collegeId", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.collegename.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.ageofinstitute.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.collegetype.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.sexfor.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.streamsavailable.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.electivecombination.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.seatsforselectedstream.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.cutoffforselectedstream.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.streamfee.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.staff.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.address.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.website.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.applicationcost.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.applicationlastdate.label", new LinkedList<Object>());
		collegeBasicDetailsMap.put("com.edupath.college.compare.admission.label", new LinkedList<Object>());

		List<String> distinctActivities = new CollegeActivitiesDAO().getDistinctActivities();
		if (distinctActivities != null)
		{
			for (String activity : distinctActivities)
			{
				collegeActivityDetailsMap.put(activity, new LinkedList<Object>());
			}
		}

		List<String> distinctInfra = new CollegeInfraDAO().getDistinctInfra();
		if (distinctInfra != null)
		{
			for (String infra : distinctInfra)
			{
				collegeInfraDetailsMap.put(infra, new LinkedList<Object>());
			}
		}
	}

	/**
	 * @param performedBy
	 * @param collegeIds
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public void insertCollegeShortList(CollegeCompareBean bean, String performedBy) throws Exception
	{
		StudentCollegeShortListDAO studentCollegeShortListDAO = new StudentCollegeShortListDAO();
		List<StudentCollegeShortListDTO> collegeByStudentId = studentCollegeShortListDAO.getShortListedCollegeByStudentId(bean.getStudentId());

		if (bean.getCollegeIdsStr() != null && !bean.getCollegeIdsStr().isEmpty())
		{
			bean.setCollegeIds(CommonUtil.convertCommaSeperatedToIntegerList(bean.getCollegeIdsStr()));
			int maxCollegeShortList = getMaxCollegeShortList();
			if (collegeByStudentId != null && (collegeByStudentId.size() + bean.getCollegeIds().size()) > maxCollegeShortList)
			{
				throw new EdupathException("com.edupath.college.shortlist.college.maxreached.error", new String[]
				{
					String.valueOf(maxCollegeShortList)
				});
			}

			for (StudentCollegeShortListDTO studentCollegeShortListDTO : collegeByStudentId)
			{
				if (bean.getCollegeIds().contains(studentCollegeShortListDTO.getCollegeId()))
				{
					throw new EdupathException("com.edupath.college.shortlist.college.exist.error", new String[]
					{
						studentCollegeShortListDTO.getCollegeName()
					});
				}
			}
			SqlSession sqlSession = MyBatisManager.getInstance().getSession();
			studentCollegeShortListDAO.insertStudentCollegeShortList(sqlSession, bean.getCollegeIds(), bean.getStudentId());
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.COLLEGE_CART,
					MessageFormat.format("College short listed college ids : {0} for student : {1}", bean.getCollegeIdsStr(), bean.getStudentId()), performedBy);
			sqlSession.commit();
		}
		else
		{
			throw new Exception();
		}
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public int getMaxCollegeShortList() throws Exception
	{
		String propertyValue = new GlobalSttingDAO().getPropertesValueByName(ApplicationConstants.GlobalSettings.COLLEGE_PAGE_SIZE.getProperty());
		return (propertyValue != null ? Integer.parseInt(propertyValue) : -1);
	}
}
