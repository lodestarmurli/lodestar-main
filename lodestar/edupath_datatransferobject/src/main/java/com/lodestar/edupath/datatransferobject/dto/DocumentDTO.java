package com.lodestar.edupath.datatransferobject.dto;

public class DocumentDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					industryId;
	private int					occupationId;
	private int					subjectId;
	private String				documentPath;

	// non table
	private String				occupationName;
	private String				industryName;
	private Integer				shortListId;
	private String				occupationSummary;
	private String				subjectName;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getIndustryId()
	{
		return industryId;
	}

	public void setIndustryId(int industryId)
	{
		this.industryId = industryId;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	public int getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(int subjectId)
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

	public String getOccupationName()
	{
		return occupationName;
	}

	public void setOccupationName(String occupationName)
	{
		this.occupationName = occupationName;
	}

	public String getIndustryName()
	{
		return industryName;
	}

	public void setIndustryName(String industryName)
	{
		this.industryName = industryName;
	}

	public Integer getShortListId()
	{
		return shortListId;
	}

	public void setShortListId(Integer shortListId)
	{
		this.shortListId = shortListId;
	}

	public String getOccupationSummary()
	{
		return occupationSummary;
	}

	public void setOccupationSummary(String occupationSummary)
	{
		this.occupationSummary = occupationSummary;
	}

	public String getSubjectName()
	{
		return subjectName;
	}

	public void setSubjectName(String subjectName)
	{
		this.subjectName = subjectName;
	}

	@Override
	public String toString() {
		return "DocumentDTO [id=" + id + ", industryId=" + industryId + ", occupationId=" + occupationId
				+ ", subjectId=" + subjectId + ", documentPath=" + documentPath + ", occupationName=" + occupationName
				+ ", industryName=" + industryName + ", shortListId=" + shortListId + ", occupationSummary="
				+ occupationSummary + ", subjectName=" + subjectName + "]";
	}

}
