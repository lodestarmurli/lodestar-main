package com.lodestar.edupath.datatransferobject.dto.student;

import java.util.Date;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class VisitedDocument implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					studentId;
	private int					documentId;
	private int					pageNumber;
	private Date				lastVisitedTime;

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public int getDocumentId()
	{
		return documentId;
	}

	public void setDocumentId(int documentId)
	{
		this.documentId = documentId;
	}

	public int getPageNumber()
	{
		return pageNumber;
	}

	public void setPageNumber(int pageNumber)
	{
		this.pageNumber = pageNumber;
	}

	public Date getLastVisitedTime()
	{
		return lastVisitedTime;
	}

	public void setLastVisitedTime(Date lastVisitedTime)
	{
		this.lastVisitedTime = lastVisitedTime;
	}

}
