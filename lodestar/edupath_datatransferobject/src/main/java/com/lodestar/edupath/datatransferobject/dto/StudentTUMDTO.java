package com.lodestar.edupath.datatransferobject.dto;

public class StudentTUMDTO implements IModel
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					studentId;
	private int					questionSlNo;
	private String				answer;

	// None table field
	private int					range;

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

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public int getQuestionSlNo()
	{
		return questionSlNo;
	}

	public void setQuestionSlNo(int questionSlNo)
	{
		this.questionSlNo = questionSlNo;
	}

	public String getAnswer()
	{
		return answer;
	}

	public int getRange()
	{
		return range;
	}

	public void setRange(int range)
	{
		this.range = range;
	}

	@Override
	public String toString()
	{
		return "StudentTUMDTO [id=" + id + ", studentId=" + studentId + ", questionSlNo=" + questionSlNo + ", answer=" + answer + ", range=" + range + "]";
	}

}
