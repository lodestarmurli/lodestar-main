package com.lodestar.edupath.datatransferobject.dto.notes;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class FacilitatorNotesDTO implements IModel
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					userId;
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

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
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
		return "FacilitatorNotesDTO [id=" + id + ", userId=" + userId + ", studentId=" + studentId + ", noteText=" + noteText + "]";
	}

}
