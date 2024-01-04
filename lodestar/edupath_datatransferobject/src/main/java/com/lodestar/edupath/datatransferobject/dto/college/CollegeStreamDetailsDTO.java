package com.lodestar.edupath.datatransferobject.dto.college;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CollegeStreamDetailsDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					collegeId;
	private int					streamId;
	private double				applicationFormfees;
	private int					noteachingStaff;
	private int					nolabincharge;
	private boolean				admbasisOffCutOff;
	private String				admEntranceExam;
	private String				admPersonalInterview;
	private String				admFirstComeFirstServe;
	private int					lastCutOff;
	private String				perceptionRanking;
	private int					noStudents;
	private int					noStudentsPassed;
	private int					scored75Percent;
	private int					numOfStudScoreGtr80Percent;
	private int					numOfStudentsScoreGtr90Percent;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getCollegeId()
	{
		return collegeId;
	}

	public void setCollegeId(int collegeId)
	{
		this.collegeId = collegeId;
	}

	public int getStreamId()
	{
		return streamId;
	}

	public void setStreamId(int streamId)
	{
		this.streamId = streamId;
	}

	public double getApplicationFormfees()
	{
		return applicationFormfees;
	}

	public void setApplicationFormfees(double applicationFormfees)
	{
		this.applicationFormfees = applicationFormfees;
	}

	public int getNoteachingStaff()
	{
		return noteachingStaff;
	}

	public void setNoteachingStaff(int noteachingStaff)
	{
		this.noteachingStaff = noteachingStaff;
	}

	public int getNolabincharge()
	{
		return nolabincharge;
	}

	public void setNolabincharge(int nolabincharge)
	{
		this.nolabincharge = nolabincharge;
	}

	public String getAdmEntranceExam()
	{
		return admEntranceExam;
	}

	public void setAdmEntranceExam(String admEntranceExam)
	{
		this.admEntranceExam = admEntranceExam;
	}

	public String getAdmPersonalInterview()
	{
		return admPersonalInterview;
	}

	public void setAdmPersonalInterview(String admPersonalInterview)
	{
		this.admPersonalInterview = admPersonalInterview;
	}

	public String getAdmFirstComeFirstServe()
	{
		return admFirstComeFirstServe;
	}

	public void setAdmFirstComeFirstServe(String admFirstComeFirstServe)
	{
		this.admFirstComeFirstServe = admFirstComeFirstServe;
	}

	public int getNoStudents()
	{
		return noStudents;
	}

	public void setNoStudents(int noStudents)
	{
		this.noStudents = noStudents;
	}

	public int getNoStudentsPassed()
	{
		return noStudentsPassed;
	}

	public void setNoStudentsPassed(int noStudentsPassed)
	{
		this.noStudentsPassed = noStudentsPassed;
	}

	public int getLastCutOff()
	{
		return lastCutOff;
	}

	public void setLastCutOff(int lastCutOff)
	{
		this.lastCutOff = lastCutOff;
	}

	public int getScored75Percent()
	{
		return scored75Percent;
	}

	public void setScored75Percent(int scored75Percent)
	{
		this.scored75Percent = scored75Percent;
	}

	public int getNumOfStudScoreGtr80Percent()
	{
		return numOfStudScoreGtr80Percent;
	}

	public void setNumOfStudScoreGtr80Percent(int numOfStudScoreGtr80Percent)
	{
		this.numOfStudScoreGtr80Percent = numOfStudScoreGtr80Percent;
	}

	public int getNumOfStudentsScoreGtr90Percent()
	{
		return numOfStudentsScoreGtr90Percent;
	}

	public void setNumOfStudentsScoreGtr90Percent(int numOfStudentsScoreGtr90Percent)
	{
		this.numOfStudentsScoreGtr90Percent = numOfStudentsScoreGtr90Percent;
	}

	public boolean isAdmbasisOffCutOff()
	{
		return admbasisOffCutOff;
	}

	public void setAdmbasisOffCutOff(boolean admbasisOffCutOff)
	{
		this.admbasisOffCutOff = admbasisOffCutOff;
	}

	public String getPerceptionRanking()
	{
		return perceptionRanking;
	}

	public void setPerceptionRanking(String perceptionRanking)
	{
		this.perceptionRanking = perceptionRanking;
	}

}
