package com.lodestar.edupath.scheduler;

import java.util.Date;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleJob implements Job
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ScheduleJob.class);

	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException
	{
		if (OUT.isInfoEnabled())
		{
			OUT.info("In ScheduleJob - executing its scheduler at " + new Date() + " by " + jobContext.getTrigger().getJobKey().getName());
		}
		Map<String, Object> dataMap = jobContext.getJobDetail().getJobDataMap();
		SchedulerTask task = (SchedulerTask) dataMap.get("runSchedulerTask");
		try
		{
			task.execute();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}
}
