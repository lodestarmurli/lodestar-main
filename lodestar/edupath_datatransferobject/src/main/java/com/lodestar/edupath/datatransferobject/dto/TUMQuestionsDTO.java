package com.lodestar.edupath.datatransferobject.dto;

public class TUMQuestionsDTO implements IModel
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					slNo;
	private String				question;
	private String				sectionName;
	private String				isActive;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getSlNo()
	{
		return slNo;
	}

	public void setSlNo(int slNo)
	{
		this.slNo = slNo;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getSectionName()
	{
		return sectionName;
	}

	public void setSectionName(String sectionName)
	{
		this.sectionName = sectionName;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	@Override
	public String toString()
	{
		return "TUMQuestions [id=" + id + ", slNo=" + slNo + ", question=" + question + ", sectionName=" + sectionName + ", isActive=" + isActive + "]";
	}

}
