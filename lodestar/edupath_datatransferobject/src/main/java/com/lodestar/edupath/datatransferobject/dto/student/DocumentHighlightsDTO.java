package com.lodestar.edupath.datatransferobject.dto.student;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class DocumentHighlightsDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					documentId;
	private int					studentId;
	private String				highlights;

	public int getDocumentId()
	{
		return documentId;
	}

	public void setDocumentId(int documentId)
	{
		this.documentId = documentId;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public String getHighlights()
	{
		return highlights;
	}

	public void setHighlights(String highlights)
	{
		this.highlights = highlights;
	}

}
