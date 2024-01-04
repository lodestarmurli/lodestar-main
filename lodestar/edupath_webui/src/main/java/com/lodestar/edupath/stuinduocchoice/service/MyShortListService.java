package com.lodestar.edupath.stuinduocchoice.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.calculatefitmentcolor.CalculateFitmentColor;
import com.lodestar.edupath.dataaccessobject.dao.stuinduocchoice.MyShortListDAO;
import com.lodestar.edupath.datatransferobject.dto.student.ShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.studentcart.service.StudentCartDetailService;

public class MyShortListService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(MyShortListService.class);

	public List<ShortListDTO> getShortListByStudentId(int studentId)
	{
		List<ShortListDTO> shortListDTOList = null;
		List<ShortListDTO> shortListDTOList1 = null;
		List<ShortListDTO> shortListDTOList2 = null;
		try
		{
			shortListDTOList = new MyShortListDAO().getShortListDetailsByStudentId(studentId);
			
			//BEGIN Sasedeve Added by Mrutyunjaya on Date 20-05-2017
			
			CalculateFitmentColor calfitment=new CalculateFitmentColor();
			
			shortListDTOList1=calfitment.CalculatefitmentforstudentShortList(shortListDTOList,studentId);
			
			//END Sasedeve Added by Mrutyunjaya on Date 20-05-2017
			
			
			
			
			
			
			//BEGIN Sasedeve Added by Mrutyunjaya on Date 14-07-2017
			
			
			shortListDTOList2=calfitment.getOccupationFitmentForShortList(shortListDTOList1,studentId);
			
			
			
			//END Sasedeve Added by Mrutyunjaya on Date 14-07-2017
			
			
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return shortListDTOList2;
	}

	public StudentDetailsDTO getStudentIdByUserId(int id)
	{
		StudentDetailsDTO studentDetailsDTO = null;
		try
		{
			studentDetailsDTO = new StudentDetailsDAO().getStudentDetailsByUserId(id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return studentDetailsDTO;
	}

	public int insertMyShortList(int studentId, String id, Integer occIndustryId, int orderno) throws Exception
	{
		ShortListDTO shortListDTO = new ShortListDTO();
		shortListDTO.setStudentId(studentId);
		//Start Sasedeve edited By Mrutyunjaya on 19-12-2017
		
		shortListDTO.setOrderNo(orderno);
		//End Sasedeve edited By Mrutyunjaya on 19-12-2017
		
		int intValue = Integer.valueOf(id.substring(4));

		if (id.startsWith("occ"))
		{
			shortListDTO.setOccupationId(intValue);
			shortListDTO.setOccIndustryId(occIndustryId);
			if(occIndustryId == null || occIndustryId.intValue() <= 0)
			{
				throw new Exception("Invalid Occupation unable to get the occupationId");
			}
		}
		else if (id.startsWith("ind"))
		{
			shortListDTO.setIndustryId(intValue);
		}

		new MyShortListDAO().insertShortList(shortListDTO);
		return shortListDTO.getId();
	}

	public void deleteShortList(int id) throws Exception
	{
		new MyShortListDAO().deleteShortListById(id);
	}

	public void updateMyShortlistById(int studentId, String shortListIds)
	{
		SqlSession session = null;
		try
		{
			List<ShortListDTO> shortListByStudentId = getShortListByStudentId(studentId);
			session = MyBatisManager.getInstance().getSession();
			ShortListDTO newDto, dbShortListDTO;
			String[] idArray = shortListIds.split(",");
			boolean deleteEdupath = false;
			for (int i = 0; i < idArray.length; i++)
			{
				dbShortListDTO = shortListByStudentId.get(i);
				String shortListId = idArray[i];
				newDto = new ShortListDTO();
				newDto.setId(Integer.parseInt(shortListId));
				newDto.setOrderNo(i + 1);

				if (dbShortListDTO != null && i < 2 && dbShortListDTO.getId() != newDto.getId())
				{
					deleteEdupath = true;
					OUT.debug("Priority 1 and 2 has been modified, priority: {}, existing:{}, new:{}", (i + 1), dbShortListDTO.getId(), newDto.getId());
				}

				new MyShortListDAO().updateMyShortListById(session, newDto);
			}

			OUT.debug("Is Delete Edupath Required:{}",deleteEdupath);
			if (deleteEdupath)
			{
				OUT.info("Deleting the Edupath for the user has priority 1 and 2 has been modified for user:{}", studentId);
				new StudentCartDetailService().removeEdupathShortList(session, studentId);
			}

			session.commit();
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
	}
}
