package com.lodestar.edupath.scheduler;

public interface SchedulerTask {

	public void setId(Integer id);
	public void setName(String taskName);
	public void setGroupName(String taskGroupName);
	public void setParams(String taskParams);
	public Integer getId();
	public String getName();
	public String getGroupName();
	public String getParams();
	public void execute();
}
