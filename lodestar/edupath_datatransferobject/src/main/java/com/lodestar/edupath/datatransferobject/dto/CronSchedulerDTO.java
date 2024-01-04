package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;


public class CronSchedulerDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= 5554961840265716131L;

	public final static String	GET_CRON_ALL_JOBS	= "CronScheduler.getAllCronJobs";

	private Integer	id;
	private String	taskName;
	private String	taskGroupName;
	private String	taskClass;
	private String	taskParam;
	private String	taskCronExp;
	private boolean trigerOnStartUp;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

	public String getTaskGroupName()
	{
		return taskGroupName;
	}

	public void setTaskGroupName(String taskGroupName)
	{
		this.taskGroupName = taskGroupName;
	}

	public String getTaskClass()
	{
		return taskClass;
	}

	public void setTaskClass(String taskClass)
	{
		this.taskClass = taskClass;
	}

	public String getTaskParam()
	{
		return taskParam;
	}

	public void setTaskParam(String taskParam)
	{
		this.taskParam = taskParam;
	}

	public String getTaskCronExp()
	{
		return taskCronExp;
	}

	public void setTaskCronExp(String taskCronExp)
	{
		this.taskCronExp = taskCronExp;
	}

	public boolean isTrigerOnStartUp() {
		return trigerOnStartUp;
	}

	public void setTrigerOnStartUp(boolean trigerOnStartUp) {
		this.trigerOnStartUp = trigerOnStartUp;
	}
}
