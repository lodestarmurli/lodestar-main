package com.lodestar.edupath.datatransferobject.dto.cgt;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class StudentCGTResult implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					studentId;
	private String				personalityCode;
	private String				abilities;
	private String				occupationIds;
	private double				score;
	private String 				ssFactor;
	private String				appScore;
	private String				aptitudeComplete;
	private long				remainigTime;

	public String getPersonalityCode()
	{
		return personalityCode;
	}

	public void setPersonalityCode(String personalityCode)
	{
		this.personalityCode = personalityCode;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public double getScore()
	{
		return score;
	}

	public void setScore(double score)
	{
		this.score = score;
	}

	public String getOccupationIds()
	{
		return occupationIds;
	}

	public void setOccupationIds(String occupationIds)
	{
		this.occupationIds = occupationIds;
	}

	public String getAptitudeComplete()
	{
		return aptitudeComplete;
	}

	public void setAptitudeComplete(String aptitudeComplete)
	{
		this.aptitudeComplete = aptitudeComplete;
	}

//	@Override
//	public String toString()
//	{
//		return "StudentCGTResult [studentId=" + studentId + ", personalityCode=" + personalityCode + ", occupationIds=" + occupationIds + ", score=" + score
//				+ ", aptitudeComplete=" + aptitudeComplete + ", remainigTime=" + remainigTime + "]";
//	}

	public long getRemainigTime()
	{
		return remainigTime;
	}

	public void setRemainigTime(long remainigTime)
	{
		this.remainigTime = remainigTime;
	}

	public String getAppScore()
	{
		return appScore;
	}

	public void setAppScore(String appScore)
	{
		this.appScore = appScore;
	}

	public String getAbilities()
	{
		return abilities;
	}

	public void setAbilities(String abilities)
	{
		this.abilities = abilities;
	}

	public String getSsFactor()
	{
		return ssFactor;
	}

	public void setSsFactor(String ssFactor)
	{
		this.ssFactor = ssFactor;
	}

	@Override
	public String toString() {
		return "StudentCGTResult [studentId=" + studentId + ", personalityCode=" + personalityCode + ", abilities="
				+ abilities + ", occupationIds=" + occupationIds + ", score=" + score + ", ssFactor=" + ssFactor
				+ ", appScore=" + appScore + ", aptitudeComplete=" + aptitudeComplete + ", remainigTime=" + remainigTime
				+ "]";
	}
	

}
