package com.lodestar.edupath.notes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.notes.StudentNotesDAO;
import com.lodestar.edupath.datatransferobject.dto.notes.StudentNotesDTO;

public class StudentNotesService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(StudentNotesService.class);

	public StudentNotesDTO getNotes(StudentNotesDTO notesDTO)
	{
		try
		{
			StudentNotesDAO notesDAO = new StudentNotesDAO();
			notesDTO = notesDAO.getNotesById(notesDTO);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return notesDTO;
	}

	public boolean insertNotes(StudentNotesDTO notesDTO)
	{
		try
		{
			StudentNotesDAO notesDAO = new StudentNotesDAO();
			int id = notesDAO.updateNotes(notesDTO);
			if (id <= 0)
			{
				notesDAO.insertNotes(notesDTO);
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return true;
	}

}
