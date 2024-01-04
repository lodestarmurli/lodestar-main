package com.lodestar.edupath.studentcart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.ShortListExams.ShortListExamsDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.calculatefitmentcolor.CalculateFitmentColor;
import com.lodestar.edupath.dataaccessobject.dao.collegeshortlist.StudentCollegeShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.CombinationDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.EduPathElectiveShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.EduPathShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.SubjectDAO;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.dataaccessobject.dao.industry.IndustryDAO;
import com.lodestar.edupath.dataaccessobject.dao.integratedcourse.IntegratedCourseDAO;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.dataaccessobject.dao.path.EduPathDegreesDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.shortlist.ShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.shortlistcourse.ShortListCourseDAO;
import com.lodestar.edupath.dataaccessobject.dao.stuinduocchoice.MyShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.tutorialshortlist.StudentTutorialCentreShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.wishlist.WishListDAO;
import com.lodestar.edupath.datatransferobject.dto.EntranceExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.IntegratedCourseDTO;
import com.lodestar.edupath.datatransferobject.dto.ShortListCourseDTO;
import com.lodestar.edupath.datatransferobject.dto.ShortListExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.CombinationDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathDegreeSpecializationDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathDegreesDTO;
import com.lodestar.edupath.datatransferobject.dto.student.ShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.WishListDTO;
import com.lodestar.edupath.datatransferobject.enumtype.EduPathEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class StudentCartDetailService
{
	private static Logger	OUT	= LoggerFactory.getLogger(StudentCartDetailService.class);

	public void getStudentWishOccupationMap(Integer studentId, Map<String, List<WishListDTO>> occupationWishMap)
	{
		List<WishListDTO> occupationlist = null;
		List<WishListDTO> finalList = null;
		try
		{
			occupationlist = new WishListDAO().getStudentWishList(studentId.intValue());
			if (null != occupationlist && !occupationlist.isEmpty())
			{
				
				
				//BEGIN Sasedeve Added by Mrutyunjaya on Date 10-05-2017
				CalculateFitmentColor calfit=new CalculateFitmentColor();
				for (WishListDTO wishListDTO : occupationlist)
				{
					
					
					wishListDTO.setOccupation(calfit.getOccupationFitmentForGlossary(wishListDTO.getOccupation(), studentId));
					//END Sasedeve Added by Mrutyunjaya on Date 10-05-2017
					
					if (!occupationWishMap.isEmpty() && occupationWishMap.containsKey(wishListDTO.getOccupation().getIndustryName()))
					{
						finalList = occupationWishMap.get(wishListDTO.getOccupation().getIndustryName());
					}
					else
					{
						finalList = new ArrayList<WishListDTO>();
						occupationWishMap.put(wishListDTO.getOccupation().getIndustryName(), finalList);
					}
					finalList.add(wishListDTO);
				}
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	}

	public List<ShortListDTO> getStudentShortList(Integer studentId)
	{
		List<ShortListDTO> shortListDTO = null;
		
		//BEGIN Sasedeve Added by Mrutyunjaya on Date 10-05-2017
		List<ShortListDTO> shortListDTO1 = null;
		try
		{
			shortListDTO = new MyShortListDAO().getShortListDetailsByStudentId(studentId.intValue());
			
			
			CalculateFitmentColor calfit=new CalculateFitmentColor();
			
			shortListDTO1=calfit.getOccupationFitmentForShortList(shortListDTO,studentId.intValue());
			//END Sasedeve Added by Mrutyunjaya on Date 10-05-2017
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return shortListDTO1;
	}

	public Integer getStudentIdByUserId(int studentUserId)
	{
		int studentId = 0;
		if (studentUserId == 0)
		{
			return studentId;
		}
		try
		{

			StudentDetailsDTO studentDetailsDTO = new StudentDetailsDAO().getStudentDetailsByUserId(studentUserId);
			if (null != studentDetailsDTO)
			{
				studentId = studentDetailsDTO.getId();
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return studentId;
	}

	public Map<Integer, EntranceExamsDTO> getStudentShortListExam(Integer studentId)
	{
		Map<Integer, EntranceExamsDTO> finalMap = new HashMap<Integer, EntranceExamsDTO>();
		try
		{
			OUT.debug("Entrance Exams studentId : {}", studentId);

			List<EntranceExamsDTO> list = new EntranceExamsDAO().getShortListExamByStudentId(studentId);

			if (null != list && !list.isEmpty())
			{
				Set<Integer> occId = new HashSet<Integer>();
				for (EntranceExamsDTO entranceExamsDTO : list)
				{
					occId.add(entranceExamsDTO.getOccupationId());
				}

				ShortListDTO dto = new ShortListDTO();
				dto.setStudentId(studentId);
				dto.setIdsList(occId);
				List<ShortListDTO> shortList = new ShortListDAO().getShortListDetailsByStudentId(dto);

				if (null != shortList && !shortList.isEmpty())
				{
					List<EntranceExamsDTO> finalList = null;
					EntranceExamsDTO entranceDTO = null;
					for (ShortListDTO shortListDTO : shortList)
					{
						finalList = new ArrayList<EntranceExamsDTO>();
						entranceDTO = new EntranceExamsDTO();
						entranceDTO.setOccupationName(shortListDTO.getOccupationName());
						for (EntranceExamsDTO entranceExamsDTO : list)
						{
							if (shortListDTO.getOccupationId() == entranceExamsDTO.getOccupationId())
							{
								finalList.add(entranceExamsDTO);
							}
						}
						entranceDTO.setExamList(finalList);
						finalMap.put(shortListDTO.getOrderNo(), entranceDTO);
					}
				}

			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return finalMap;
	}

	public Map<Integer, IntegratedCourseDTO> getStudentShortListIntegratedCourse(Integer studentId)
	{
		Map<Integer, IntegratedCourseDTO> finalMap = new HashMap<Integer, IntegratedCourseDTO>();
		try
		{
			OUT.debug("Integrated Course studentId : {}", studentId);

			List<IntegratedCourseDTO> list = new IntegratedCourseDAO().getShortListIntegratedCourseByStudentId(studentId);

			if (null != list && !list.isEmpty())
			{
				Set<Integer> occId = new HashSet<Integer>();
				for (IntegratedCourseDTO entranceExamsDTO : list)
				{
					occId.add(entranceExamsDTO.getOccupationId());
				}

				ShortListDTO dto = new ShortListDTO();
				dto.setStudentId(studentId);
				dto.setIdsList(occId);
				List<ShortListDTO> shortList = new ShortListDAO().getShortListDetailsByStudentId(dto);

				if (null != shortList && !shortList.isEmpty())
				{
					List<IntegratedCourseDTO> finalList = null;
					IntegratedCourseDTO integratedDTO = null;
					for (ShortListDTO shortListDTO : shortList)
					{
						finalList = new ArrayList<IntegratedCourseDTO>();
						integratedDTO = new IntegratedCourseDTO();
						integratedDTO.setOccupationName(shortListDTO.getOccupationName());
						for (IntegratedCourseDTO entranceExamsDTO : list)
						{
							if (shortListDTO.getOccupationId() == entranceExamsDTO.getOccupationId())
							{
								finalList.add(entranceExamsDTO);
							}
						}
						integratedDTO.setCourseList(finalList);
						finalMap.put(shortListDTO.getOrderNo(), integratedDTO);
					}
				}
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return finalMap;
	}

	public void removeExamShortList(int id, int studentId) throws Exception
	{
		ShortListExamsDTO shortListExamsDTO = new ShortListExamsDTO();
		shortListExamsDTO.setEntranceExamId(id);
		shortListExamsDTO.setStudentId(studentId);

		new ShortListExamsDAO().removeShortListExamByExamIdNStudentId(shortListExamsDTO);
	}

	public void removeCourseShortList(int id, int studentId) throws Exception
	{
		ShortListCourseDTO shortListCourseDTO = new ShortListCourseDTO();
		shortListCourseDTO.setIntegratedCourseId(id);
		shortListCourseDTO.setStudentId(studentId);

		new ShortListCourseDAO().removeShortListCourseByCourseIdNStudentId(shortListCourseDTO);
	}

	public List<EduPathShortListDTO> getEdupathShortList(Integer studentId)
	{
		List<EduPathShortListDTO> eduPathShortListDTOs = null;
		List<CombinationDTO> combinationDTOs = null;
		List<Integer> edupathIdsList = null;
		try
		{
			eduPathShortListDTOs = new EduPathShortListDAO().getEduPathShortListForCartByStudentId(studentId);
			combinationDTOs = new CombinationDAO().getCombinationListByStudentId(studentId);
			if (null != eduPathShortListDTOs && null != combinationDTOs)
			{
				StringBuilder combinationName = new StringBuilder();
				for (CombinationDTO combinationDTO : combinationDTOs)
				{
					if (!combinationName.toString().equals("") && !combinationName.toString().isEmpty())
					{
						combinationName.append(", ");
					}
					combinationName.append(combinationDTO.getName());
				}
				edupathIdsList = new ArrayList<Integer>();
				for (EduPathShortListDTO eduPathShortListDTO : eduPathShortListDTOs)
				{
					if (!combinationName.toString().equals("") && !combinationName.toString().isEmpty())
					{
						eduPathShortListDTO.setCombinationName(combinationName.toString());
					}
					if (null != eduPathShortListDTO.getEdupathId() && !edupathIdsList.contains(eduPathShortListDTO.getEdupathId())
							&& (null == eduPathShortListDTO.getDegreeStream() || eduPathShortListDTO.getDegreeStream().isEmpty()))
					{
						edupathIdsList.add(eduPathShortListDTO.getEdupathId());
					}
				}
				if (null != edupathIdsList && !edupathIdsList.isEmpty())
				{
					String edupathIds = CommonUtil.convertArrayToCommaString(edupathIdsList);
					List<EduPathDegreesDTO> edupathDegreeDTOList = new EduPathDegreesDAO().getDegreesByEdupathId(edupathIds);
					if (null != edupathDegreeDTOList && !edupathDegreeDTOList.isEmpty())
					{
						String cString = "";
						String pString = "";
						StringBuilder degreeStreem = new StringBuilder();
						for (EduPathShortListDTO eduPathShortListDTO : eduPathShortListDTOs)
						{
							if (null != eduPathShortListDTO.getEdupathId())
							{
								cString = "";
								pString = "";
								degreeStreem = new StringBuilder();
								for (EduPathDegreesDTO eduPathDegreesDTO : edupathDegreeDTOList)
								{
									if (eduPathShortListDTO.getEdupathId() == eduPathDegreesDTO.getEduPathId())
									{
										if (eduPathDegreesDTO.getType().equalsIgnoreCase(EduPathEnum.COMPULSORY.getValue()))
										{
											cString = eduPathDegreesDTO.getName();
										}
										if (eduPathDegreesDTO.getType().equalsIgnoreCase(EduPathEnum.PREFERRED.getValue()))
										{
											pString = eduPathDegreesDTO.getName();
										}
									}
								}
								if (null != cString && !cString.trim().isEmpty())
								{
									degreeStreem.append(cString);
									if (cString.equalsIgnoreCase(EduPathEnum.ANY.getValue()))
									{
										if (null != pString && !cString.trim().isEmpty() && !pString.equalsIgnoreCase(EduPathEnum.ANY.getValue()))
										{
											degreeStreem.append(" (").append(pString).append(")");
										}
									}
									eduPathShortListDTO.setDegreeStream(degreeStreem.toString());
								}
							}
						}
					}
				}

				// edupath degree specialization
				if (null != edupathIdsList && !edupathIdsList.isEmpty())
				{
					String edupathIds = CommonUtil.convertArrayToCommaString(edupathIdsList);
					List<EduPathDegreeSpecializationDTO> edupathDegreeSpecializationDTOList = new EduPathDegreesDAO()
							.getDegreesSpecializationByEdupathIds(edupathIds);
					if (null != edupathDegreeSpecializationDTOList && !edupathDegreeSpecializationDTOList.isEmpty())
					{
						String cString = "";
						String pString = "";
						StringBuilder degreeSubject = new StringBuilder();
						for (EduPathShortListDTO eduPathShortListDTO : eduPathShortListDTOs)
						{
							if (null != eduPathShortListDTO.getEdupathId())
							{
								cString = "";
								pString = "";
								degreeSubject = new StringBuilder();
								for (EduPathDegreeSpecializationDTO eduPathDegreesSpecializationsDTO : edupathDegreeSpecializationDTOList)
								{
									if (eduPathShortListDTO.getEdupathId().equals(eduPathDegreesSpecializationsDTO.getEduPathId()))
									{
										if (eduPathDegreesSpecializationsDTO.getType().equalsIgnoreCase(EduPathEnum.COMPULSORY.getValue()))
										{
											cString = eduPathDegreesSpecializationsDTO.getName();
										}
										if (eduPathDegreesSpecializationsDTO.getType().equalsIgnoreCase(EduPathEnum.PREFERRED.getValue()))
										{
											pString = eduPathDegreesSpecializationsDTO.getName();
										}
									}
								}
								if (null != cString && !cString.trim().isEmpty())
								{
									degreeSubject.append(cString);
									if (!cString.isEmpty() && cString.equalsIgnoreCase(EduPathEnum.ANY.getValue()))
									{
										if (null != pString && !pString.equalsIgnoreCase(EduPathEnum.ANY.getValue()))
										{
											degreeSubject.append(" / ").append(pString);
										}
									}
									eduPathShortListDTO.setDegreeSubject(degreeSubject.toString());
								}
							}
						}
					}
				}
				// ends here

			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		return eduPathShortListDTOs;
	}

	public void removeEdupathShortList(int studentId) throws Exception
	{
		SqlSession session = MyBatisManager.getInstance().getSession();
		try
		{
			removeEdupathShortList(session, studentId);
			session.commit();
		}
		finally
		{
			session.close();
		}
	}

	public void removeEdupathShortList(SqlSession session, int studentId) throws Exception
	{
		new EduPathElectiveShortListDAO().removeEdupathElectiveShortList(session, studentId);

		ShortListCourseDTO shortListCourseDTO = new ShortListCourseDTO();
		shortListCourseDTO.setStudentId(studentId);
		new ShortListCourseDAO().removeShortListCourseByStudentId(session, shortListCourseDTO);

		ShortListExamsDTO shortListExamsDTO = new ShortListExamsDTO();
		shortListExamsDTO.setStudentId(studentId);
		new ShortListExamsDAO().removeShortListExamByStudentId(session, shortListExamsDTO);

		new SessionScheduleDetailsDAO().updateSessionTwoThreeIncomplete(session, studentId);

		new EduPathShortListDAO().removeEdupathShortListByIdNStudentId(session, studentId);

		// TODO remove college and tutorials
	}

	public void removeTutorialCenterShortList(int id) throws Exception
	{
		new StudentTutorialCentreShortListDAO().deleteTutCenterShortListById(id);
	}

	public void removeCollegeShortList(int id) throws Exception
	{
		new StudentCollegeShortListDAO().deleteColShortListById(id);
	}

	public Map<String, List<Object>> getReportsByStudentId(Integer studentId) throws Exception
	{
		Map<String, List<Object>> reportMap = new TreeMap<String, List<Object>>();
		List<Object> occupationList = new OccupationDAO().getOccDetailsByStudentId(studentId);
		List<Object> industryList = new IndustryDAO().getIndustryDetailsByStudentId(studentId);
		List<Object> subjectList = new SubjectDAO().getSubjectDetailsByStudentId(studentId);
		if (occupationList != null && !occupationList.isEmpty())
		{
			reportMap.put("occupation", occupationList);
		}
		if (industryList != null && !industryList.isEmpty())
		{
			reportMap.put("industry", industryList);
		}
		if (subjectList != null && !subjectList.isEmpty())
		{
			reportMap.put("subject", subjectList);
		}
		return reportMap;
	}

}