package com.lodestar.edupath.scheduler;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.datatransferobject.dto.CronSchedulerDTO;

public class JobSchedulerHandler
{
	private static final Logger	OUT				= LoggerFactory.getLogger(JobSchedulerHandler.class.getName());
	private Scheduler			scheduler		= null;

	private static final String	TRIGGER_PREFIX	= "TRG_";
	private static final String	JOB_PREFIX		= "JOB_";

	public void updateScheduler(CronSchedulerDTO schedulerDetailTO) throws Exception
	{
		// TODO
	}

	public void removeScheduledJob(Scheduler scheduler, CronSchedulerDTO schedulerDetailTO) throws Exception
	{
		try
		{
			this.scheduler = scheduler;
			// Remove the scheduled Job
			JobKey jobKey = new JobKey(JOB_PREFIX + schedulerDetailTO.getTaskName() + "_" + schedulerDetailTO.getId(), schedulerDetailTO.getTaskGroupName());
			this.scheduler.deleteJob(jobKey);
			if (OUT.isInfoEnabled())
			{
				OUT.info("Removing: " + schedulerDetailTO.getTaskName());
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception - removeScheduledTicketJob()", e);
		}

	}

	public void scheduleJobs(Scheduler scheduler, CronSchedulerDTO schedulerDetailTO) throws Exception
	{
		try
		{
			this.scheduler = scheduler;
			if (OUT.isInfoEnabled())
			{
				StringBuilder schedulerInfo = new StringBuilder();
				schedulerInfo.append("Scheduling for: " + schedulerDetailTO.getTaskName() + " for Cron expression: " + schedulerDetailTO.getTaskCronExp());
				OUT.info(schedulerInfo.toString());
			}

			CronTrigger ticketTrigger = null;

			// Create trigger with unique name for the predefined scheduler group
			ticketTrigger = newTrigger()
					.withIdentity(TRIGGER_PREFIX + schedulerDetailTO.getTaskName() + "_" + schedulerDetailTO.getId(), schedulerDetailTO.getTaskGroupName())
					.withSchedule(CronScheduleBuilder.cronSchedule(schedulerDetailTO.getTaskCronExp()).withMisfireHandlingInstructionDoNothing()).build();

			Class<?> forName = Class.forName(schedulerDetailTO.getTaskClass());

			// Assign Ticket schedule details

			SchedulerTask creationTask = (SchedulerTask) forName.newInstance();
			creationTask.setId(schedulerDetailTO.getId());
			creationTask.setName(schedulerDetailTO.getTaskName());
			creationTask.setParams(schedulerDetailTO.getTaskParam());
			creationTask.setGroupName(schedulerDetailTO.getTaskGroupName());

			// Create Job with unique name for the pre-defined scheduler group
			JobDetail schedulerJob = newJob(ScheduleJob.class).withIdentity(JOB_PREFIX + schedulerDetailTO.getTaskName() + "_" + schedulerDetailTO.getId(),
					schedulerDetailTO.getTaskGroupName()).build();

			Map<String, Object> dataMap = schedulerJob.getJobDataMap();
			dataMap.put("runSchedulerTask", creationTask);

			// Schedule the job
			Date ft = this.scheduler.scheduleJob(schedulerJob, ticketTrigger);

			if (OUT.isInfoEnabled())
			{
				OUT.info(ticketTrigger.getKey().getName() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
						+ ticketTrigger.getCronExpression());
			}

			if (schedulerDetailTO.isTrigerOnStartUp())
			{
				if (OUT.isInfoEnabled())
				{
					OUT.info("Triggering while start up");
				}
				scheduler.triggerJob(ticketTrigger.getJobKey());
			}
		}
		catch (SchedulerException e)
		{
			OUT.error("Scheduler Exception- dailyScheduler()", e);
		}
		catch (Exception e)
		{
			OUT.error("Exception- dailyScheduler()", e);
		}
	}
}
