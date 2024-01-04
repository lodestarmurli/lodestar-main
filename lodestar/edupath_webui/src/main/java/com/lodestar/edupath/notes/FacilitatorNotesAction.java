package com.lodestar.edupath.notes;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.notes.FacilitatorNotesDTO;
import com.lodestar.edupath.notes.service.FacilitatorNotesService;
import com.opensymphony.xwork2.ModelDriven;

public class FacilitatorNotesAction extends BaseAction implements ModelDriven<FacilitatorNotesDTO>
{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;

	private static final Logger		OUT					= LoggerFactory.getLogger(FacilitatorNotesAction.class);

	private FacilitatorNotesDTO		notesDTO			= new FacilitatorNotesDTO();
	private FacilitatorNotesService	service;

	public String execute()
	{
		OUT.debug("Inside FacilitatorNotesAction");
		try
		{
			notesDTO.setStudentId(getStudentSessionObject().getId());
			notesDTO.setUserId(getUserSessionObject().getId());
			service = new FacilitatorNotesService();
			notesDTO = service.getNotes(notesDTO);
			if(null !=notesDTO)
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
		OUT.debug("Inside FacilitatorNotesAction (insert method)");
		try
		{
			notesDTO.setStudentId(getStudentSessionObject().getId());
			notesDTO.setUserId(getUserSessionObject().getId());
			service = new FacilitatorNotesService();
			service.insertNotes(notesDTO);
		}
		catch (Exception e)
		{
			OUT.debug("Exception", e);
		}
		return null;
	}

	@Override
	public FacilitatorNotesDTO getModel()
	{
		return notesDTO;
	}

	public FacilitatorNotesDTO getNotesDTO()
	{
		return notesDTO;
	}

	public void setNotesDTO(FacilitatorNotesDTO notesDTO)
	{
		this.notesDTO = notesDTO;
	}

}
