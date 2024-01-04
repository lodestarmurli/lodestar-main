package com.lodestar.edupath.service.scheduler.custschedulerfactory;

import org.quartz.impl.StdSchedulerFactory;

public class CustStdSchedulerFactory {

	private final static CustStdSchedulerFactory INSTANCE=new CustStdSchedulerFactory();
	private static StdSchedulerFactory factory=new StdSchedulerFactory();
	
	private CustStdSchedulerFactory()
	{
		
	}
	
	public static CustStdSchedulerFactory getCustStdSchedulerFactoryInstance()
	{
		return INSTANCE;
	}
	
	public StdSchedulerFactory getStdSchedulerFactory()
	{
		return factory;
	}
}
