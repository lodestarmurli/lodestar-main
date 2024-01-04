package com.lodestar.edupath.contextListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.service.scheduler.JobScheduler;

public class ApplicationContextListener implements ServletContextListener
{

	private static final Logger	OUT	= LoggerFactory.getLogger(ApplicationContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		OUT.info("*******************************Starting Service**************************");
		JobScheduler.getInstance();

		OUT.info("*******************************Service Start Completed**************************");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		// TODO Auto-generated method stub

	}

}
