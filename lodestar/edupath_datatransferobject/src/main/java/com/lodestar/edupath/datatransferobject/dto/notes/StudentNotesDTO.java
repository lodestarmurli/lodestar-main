package com.lodestar.edupath.datatransferobject.dto.notes;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class StudentNotesDTO implements IModel
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					studentId;
	private String				noteText;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public String getNoteText()
	{
		return noteText;
	}

	public void setNoteText(String noteText)
	{
		this.noteText = noteText;
	}

	@Override
	public String toString()
	{
		return "StudentNotesDTO [id=" + id + ", studentId=" + studentId + ", noteText=" + noteText + "]";
	}

}
