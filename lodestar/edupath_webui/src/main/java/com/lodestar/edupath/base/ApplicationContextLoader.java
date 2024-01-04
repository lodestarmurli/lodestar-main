package com.lodestar.edupath.base;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationContextLoader implements ServletContextListener
{

	private static final Logger	OUT	= LoggerFactory.getLogger(ApplicationContextLoader.class);
	@Override 
	public void contextInitialized(ServletContextEvent sce)
	{
		OUT.info("*******************************************************");
		OUT.info("    		EduPath initialized successfully");
		OUT.info("*******************************************************");
		
	}

	@Override 
	public void contextDestroyed(ServletContextEvent sce)
	{
		OUT.info("*******************************************************");
		OUT.info("    		EduPath uninitialized successfully");
		OUT.info("*******************************************************");
		
	}

}
