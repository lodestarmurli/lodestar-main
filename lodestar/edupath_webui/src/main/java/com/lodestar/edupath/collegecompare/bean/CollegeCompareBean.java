package com.lodestar.edupath.collegecompare.bean;

import java.io.Serializable;
import java.util.List;

public class CollegeCompareBean implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	private String				collegeIdsStr;
	private List<Integer>		collegeIds;
	private int					studentId;
	private int					streamId;

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public int getStreamId()
	{
		return streamId;
	}

	public void setStreamId(int streamId)
	{
		this.streamId = streamId;
	}

	public String getCollegeIdsStr()
	{
		return collegeIdsStr;
	}

	public void setCollegeIdsStr(String collegeIdsStr)
	{
		this.collegeIdsStr = collegeIdsStr;
	}

	public List<Integer> getCollegeIds()
	{
		return collegeIds;
	}

	public void setCollegeIds(List<Integer> collegeIds)
	{
		this.collegeIds = collegeIds;
	}

}
