package com.lodestar.edupath.notes;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.notes.StudentNotesDTO;
import com.lodestar.edupath.notes.service.StudentNotesService;
import com.opensymphony.xwork2.ModelDriven;

public class StudentNotesAction extends BaseAction implements ModelDriven<StudentNotesDTO>
{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;

	private static final Logger		OUT					= LoggerFactory.getLogger(StudentNotesAction.class);

	private StudentNotesDTO			notesDTO			= new StudentNotesDTO();
	private StudentNotesService	service;

	public String execute()
	{
		OUT.debug("Inside StudentNotesAction");
		try
		{
			notesDTO.setStudentId(new StudentDetailsDAO().getStudentDetailsByUserId(getUserSessionObject().getId()).getId());
			service = new StudentNotesService();
			notesDTO = service.getNotes(notesDTO);
			if (null != notesDTO)
			{
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.print(notesDTO.getNoteText());
				out.flush();
				out.close();
			}
		}
		catch (Exception e)
		{
			OUT.debug("Exception", e);
		}
		return null;
	}

	public String insert()
	{
		OUT.debug("Inside StudentNotesAction (insert method)");
		try
		{
			notesDTO.setStudentId(new StudentDetailsDAO().getStudentDetailsByUserId(getUserSessionObject().getId()).getId());
			service = new StudentNotesService();
			service.insertNotes(notesDTO);
		}
		catch (Exception e)
		{
			OUT.debug("Exception", e);
		}
		return null;
	}

	@Override
	public StudentNotesDTO getModel()
	{
		return notesDTO;
	}

	public StudentNotesDTO getNotesDTO()
	{
		return notesDTO;
	}

	public void setNotesDTO(StudentNotesDTO notesDTO)
	{
		this.notesDTO = notesDTO;
	}

}
