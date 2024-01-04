package com.lodestar.edupath.datatransferobject.dto.student;

public class StudentCGTForEvalDTO
{
	private int		id;
	private int		studentId;
	private int		questionId;
	private String	answer;

	private String	question;
	private String	section;
	private String	slNo;
	private String	factor;
	private String	correctAnswer;

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

	public int getQuestionId()
	{
		return questionId;
	}

	public void setQuestionId(int questionId)
	{
		this.questionId = questionId;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getSection()
	{
		return section;
	}

	public void setSection(String section)
	{
		this.section = section;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getFactor()
	{
		return factor;
	}

	public void setFactor(String factor)
	{
		this.factor = factor;
	}

	public String getCorrectAnswer()
	{
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer)
	{
		this.correctAnswer = correctAnswer;
	}

	@Override
	public String toString() {
		return "StudentCGTForEvalDTO [id=" + id + ", studentId=" + studentId + ", questionId=" + questionId
				+ ", answer=" + answer + ", question=" + question + ", section=" + section + ", slNo=" + slNo
				+ ", factor=" + factor + ", correctAnswer=" + correctAnswer + "]";
	}

}
