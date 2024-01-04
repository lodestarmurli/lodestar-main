package com.lodestar.edupath.dataaccessobject.dao.notes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.notes.StudentNotesDTO;

public class StudentNotesDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(StudentNotesDAO.class);

	public int insertNotes(StudentNotesDTO notesDTO) throws Exception
	{
		int id = MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_STUDENT_NOTES, notesDTO);
		OUT.debug("StudentNotes inserted id: {}", notesDTO.getId());
		return id;
	}

	public StudentNotesDTO getNotesById(StudentNotesDTO notesDTO) throws Exception
	{
		StudentNotesDTO result = (StudentNotesDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_STUDENT_NOTES, notesDTO);
		return result;
	}

	public int updateNotes(StudentNotesDTO notesDTO) throws Exception
	{
		int id = MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_STUDENT_NOTES, notesDTO);
		OUT.debug("StudentNotes updated id: {}", notesDTO.getId());
		return id;
	}
}
