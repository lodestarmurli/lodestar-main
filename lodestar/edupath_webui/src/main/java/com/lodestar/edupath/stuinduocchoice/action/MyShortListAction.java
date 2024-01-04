package com.lodestar.edupath.stuinduocchoice.action;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.student.ShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.studentcart.service.StudentCartDetailService;
import com.lodestar.edupath.stuinduocchoice.service.MyShortListService;
import com.opensymphony.xwork2.ModelDriven;

public class MyShortListAction extends BaseAction implements ModelDriven<ShortListDTO>
{

	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(MyShortListAction.class);
	private int					studentId;
	private ShortListDTO		shortListDTO		= new ShortListDTO();
	private List<ShortListDTO>	shortListDTOList;
	private String				shortListIds;
	private int					shortListCount;
	private boolean				edupathExists;
	//START Sasedeve  by Mrutyunjaya on date 14-07-2017
	private static HashMap<Integer, String>	fitmentcolors			= new HashMap<Integer, String>();

	static
	{
		fitmentcolors.put(ApplicationConstants.FitMent.HIG.getScore(), ApplicationConstants.FitMent.HIG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.A_AVG.getScore(), ApplicationConstants.FitMent.A_AVG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.AVG.getScore(), ApplicationConstants.FitMent.AVG.getColor());
		fitmentcolors.put(ApplicationConstants.FitMent.LOW.getScore(), ApplicationConstants.FitMent.LOW.getColor());
	}
	
	//END Sasedeve  by Mrutyunjaya on date 14-07-2017
	public String execute()
	{
		OUT.debug("ShortListAction : inside execute method");
		try
		{
			if (studentId == 0)
			{
				StudentDetailsDTO studentDetailsDTO = new MyShortListService().getStudentIdByUserId(getUserSessionObject().getId());
				if (studentDetailsDTO != null)
				{
					studentId = studentDetailsDTO.getId();
				}
			}
			OUT.debug("getting short list details for student id : {}", studentId);
			shortListDTOList = new MyShortListService().getShortListByStudentId(studentId);
			shortListCount = shortListDTOList.size();
			
			List<EduPathShortListDTO> edupathShortList = new StudentCartDetailService().getEdupathShortList(studentId);
			if(edupathShortList == null || edupathShortList.isEmpty())
			{
				edupathExists = false;
			}
			else
			{
				edupathExists = true;
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}

	public String save()
	{
		OUT.debug("MyShortListAction : inside save method");
		try
		{
			new MyShortListService().updateMyShortlistById(new MyShortListService().getStudentIdByUserId(getUserSessionObject().getId()).getId(), shortListIds);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		execute();
		return SUCCESS;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public ShortListDTO getShortListDTO()
	{
		return shortListDTO;
	}

	public void setShortListDTO(ShortListDTO shortListDTO)
	{
		this.shortListDTO = shortListDTO;
	}

	@Override
	public ShortListDTO getModel()
	{
		return shortListDTO;
	}

	public List<ShortListDTO> getShortListDTOList()
	{
		return shortListDTOList;
	}

	public void setShortListDTOList(List<ShortListDTO> shortListDTOList)
	{
		this.shortListDTOList = shortListDTOList;
	}

	public String getShortListIds()
	{
		return shortListIds;
	}

	public void setShortListIds(String shortListIds)
	{
		this.shortListIds = shortListIds;
	}

	public int getShortListCount()
	{
		return shortListCount;
	}

	public void setShortListCount(int shortListCount)
	{
		this.shortListCount = shortListCount;
	}

	public boolean getEdupathExists()
	{
		return edupathExists;
	}

	//START Sasedeve  by Mrutyunjaya on date 14-07-2017
	public HashMap<Integer, String> getFitmentcolors()
	{
		return fitmentcolors;
	}
//END Sasedeve  by Mrutyunjaya on date 14-07-2017
}
