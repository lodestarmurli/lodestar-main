package com.lodestar.edupath.notes.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.notes.FacilitatorNotesDAO;
import com.lodestar.edupath.datatransferobject.dto.notes.FacilitatorNotesDTO;

public class FacilitatorNotesService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(FacilitatorNotesService.class);

	public FacilitatorNotesDTO getNotes(FacilitatorNotesDTO notes)
	{
		try
		{
			FacilitatorNotesDAO notesDAO = new FacilitatorNotesDAO();
			notes = notesDAO.getNotesById(notes);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return notes;
	}

	public boolean insertNotes(FacilitatorNotesDTO notes)
	{
		SqlSession session = MyBatisManager.getInstance().getSession();
		try
		{
			FacilitatorNotesDAO notesDAO = new FacilitatorNotesDAO();
			int id = notesDAO.updateNotes(session, notes);
			if (id <= 0)
			{
				notesDAO.insertNotes(session, notes);
			}
			session.commit();
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error("Exception", e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		return true;
	}

}
