package com.lodestar.edupath.service.scheduler;

import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.datatransferobject.dto.CronSchedulerDTO;
import com.lodestar.edupath.scheduler.JobSchedulerHandler;
import com.lodestar.edupath.service.scheduler.custschedulerfactory.CustStdSchedulerFactory;



public class JobScheduler
{
	private static final Logger			OUT			= LoggerFactory.getLogger(JobScheduler.class.getName());
	private static final JobScheduler	INSTANCE	= new JobScheduler();
	private static Scheduler			scheduler	= null;

	public static JobScheduler getInstance()
	{
		return INSTANCE;
	}

	public JobScheduler()
	{
		startScheduler();
	}

	private boolean startScheduler()
	{
		if (OUT.isInfoEnabled())
		{
			OUT.info("Scheduler Start to sync data.");
		}
		boolean startFlag = false;
		try
		{
			synchronized (this)
			{
				if (scheduler == null)
				{
					SchedulerFactory sf = CustStdSchedulerFactory.getCustStdSchedulerFactoryInstance().getStdSchedulerFactory();
					scheduler = sf.getScheduler();
					scheduler.start();
					if (OUT.isInfoEnabled())
					{
						OUT.info("INIT: Scheduler instance Id: " + scheduler.getSchedulerInstanceId() + " , name: " + scheduler.getSchedulerName());
					}

					loadSchedulesFromDB();
					startFlag = true;
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception - startScheduler()", e);
		}
		return startFlag;
	}

	/**
	 * @throws Exception
	 */
	private void loadSchedulesFromDB() throws Exception
	{
			List<CronSchedulerDTO> resultList = MyBatisManager.getInstance().getResultList(CronSchedulerDTO.GET_CRON_ALL_JOBS, null);
			if(OUT.isDebugEnabled())
			{
				OUT.debug("Total {} corn schedule found in WEB.", resultList.size());
			}
			JobSchedulerHandler jobHandler = new JobSchedulerHandler();
			for (CronSchedulerDTO schedulerDetailTO : resultList)
			{
				if(OUT.isDebugEnabled())
				{
					OUT.debug("Adding corn for task {}", schedulerDetailTO.getTaskName());
				}
				jobHandler.scheduleJobs(scheduler, schedulerDetailTO);
			}
	}
}
