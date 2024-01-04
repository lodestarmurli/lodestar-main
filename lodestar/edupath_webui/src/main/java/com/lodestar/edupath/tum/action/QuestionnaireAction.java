package com.lodestar.edupath.tum.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;

public class QuestionnaireAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(QuestionnaireAction.class);
	
	@Override
	public String execute() throws Exception
	{
		OUT.debug("Inside QuestionnaireAction");
		return SUCCESS;
	}
}
