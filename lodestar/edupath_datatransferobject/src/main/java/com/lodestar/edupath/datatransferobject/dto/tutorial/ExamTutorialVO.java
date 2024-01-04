package com.lodestar.edupath.datatransferobject.dto.tutorial;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class ExamTutorialVO implements Serializable, IModel
{

	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				name;
	private int					noOfStudentsEnroll;
	private int					noOfStudentsAchived;
	private double				throughPutRatio;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getNoOfStudentsEnroll()
	{
		return noOfStudentsEnroll;
	}

	public void setNoOfStudentsEnroll(int noOfStudentsEnroll)
	{
		this.noOfStudentsEnroll = noOfStudentsEnroll;
	}

	public int getNoOfStudentsAchived()
	{
		return noOfStudentsAchived;
	}

	public void setNoOfStudentsAchived(int noOfStudentsAchived)
	{
		this.noOfStudentsAchived = noOfStudentsAchived;
	}

	public double getThroughPutRatio()
	{
		return throughPutRatio;
	}

	public void setThroughPutRatio(double throughPutRatio)
	{
		this.throughPutRatio = throughPutRatio;
	}

	@Override
	public String toString()
	{
		return "ExamTutorialVO [id=" + id + ", name=" + name + ", noOfStudentsEnroll=" + noOfStudentsEnroll + ", noOfStudentsAchived=" + noOfStudentsAchived
				+ ", throughPutRatio=" + throughPutRatio + "]";
	}

}
