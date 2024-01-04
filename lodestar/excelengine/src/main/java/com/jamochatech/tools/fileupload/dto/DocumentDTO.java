package com.jamochatech.tools.fileupload.dto;

import java.io.Serializable;

import com.jamochatech.tools.excelengine.dto.IModel;

public class DocumentDTO implements IModel, Serializable
{
	private static final long	serialVersionUID	= 1L;
	private Integer				id;
	private Integer				industryId;
	private Integer				occupationId;
	private Integer				subjectId;
	private String				documentPath;

	// non table
	private String				type;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getIndustryId()
	{
		return industryId;
	}

	public void setIndustryId(Integer industryId)
	{
		this.industryId = industryId;
	}

	public Integer getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(Integer occupationId)
	{
		this.occupationId = occupationId;
	}

	public Integer getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(Integer subjectId)
	{
		this.subjectId = subjectId;
	}

	public String getDocumentPath()
	{
		return documentPath;
	}

	public void setDocumentPath(String documentPath)
	{
		this.documentPath = documentPath;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return "DocumentDTO [id=" + id + ", industryId=" + industryId + ", occupationId=" + occupationId + ", subjectId=" + subjectId + ", documentPath="
				+ documentPath + ", type=" + type + "]";
	}

}
