package com.lodestar.edupath.tum.questionnaire;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.TumCgtResultDAO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.role.ApplicationMenuDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.TumCgtResultDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTestEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.tum.questionnaire.service.AptitudeService;
import com.lodestar.edupath.tum.questionnaire.service.InterestService;
import com.lodestar.edupath.tum.service.StudentTUMService;
import com.opensymphony.xwork2.ActionContext;

public class AptitudeSubmitAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private static final Logger	OUT					= LoggerFactory.getLogger(AptitudeSubmitAction.class);
	private int					pageAction			= 0;
	private AptitudeService		service;
	private List<String>		questionList;
	private String				answerList;
	private String				aptitudeComplete;
	private int					studentId;
	private long				remainigTime;
	private int					remainigSecondTime;
	private int					remainigMinuteTime;
	private boolean				isInterestCompleted;

	@Override
	public String execute()
	{
		OUT.debug("Inside AptitudeSubmitAction");
		try
		{
			pageAction = 0;
			service = new AptitudeService();
			String formatTime = null;
			StudentCGTResult cgtResult = service.getStudentCGTResult(getUserSessionObject().getId());
			JSONArray jsonArray = service.getStudentCGTBySection(getUserSessionObject().getId());
			isInterestCompleted = new InterestService().isIntrestCompleted(getUserSessionObject().getId());
			request.setAttribute("studentAnswerList", jsonArray);
			if (null != cgtResult)
			{
				double time = TimeUtil.getTotalMenutes(cgtResult.getRemainigTime());
				DecimalFormat df = new DecimalFormat("#.0");
				formatTime = df.format(time);
				String[] timeStr = formatTime.split("\\.");
				if (!timeStr[0].isEmpty())
				{
					remainigMinuteTime = Integer.valueOf(timeStr[0]);
				}
				remainigSecondTime = Integer.valueOf(timeStr[1]);
				if (remainigMinuteTime < ApplicationConstants.APTITUDE_TEST_TIME || remainigSecondTime > 0)
				{
					aptitudeComplete = StudentTestEnum.STARTED.getValue();
				}
				else
				{
					remainigMinuteTime = 0;
				}
			}
			else if (cgtResult == null)
			{
				remainigMinuteTime = 0;
				aptitudeComplete = StudentTestEnum.STARTED.getValue();
			}
			setSelectedMenu();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String insert()
	{
		OUT.debug("Inside AptitudeSubmitAction (insert method)");
		try
		{
			service = new AptitudeService();
			boolean result = service.insertAnswer(answerList, getUserSessionObject().getId(), remainigTime);
			StudentTUMService tumservice = new StudentTUMService();
			tumservice.updateTUMSession(getUserSessionObject().getId());
			if (result)
			{
				AuditTrailLogger.addAuditInfo(ModuleNameEnum.ANSWER_STUDENT, getUserSessionObject().getFullName()
						+ " answer the aptitude question. Remaining Time: " + remainigTime + ". Answers: " + answerList, getUserSessionObject().getLoginId());
			}
			else
			{
				service.insertStudentCGTResult(studentId, remainigTime);
			}
			
			
			//Start Sasedeve edited by Mrutyunjaya on date 03-08-2017
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			sessionMap.put(ApplicationConstants.SessionProperty.Is_ApptitudetestCompleted.getProperty(),true);
			//End Sasedeve edited by Mrutyunjaya on date 03-08-2017
			//start by bharath on 6/7/2019
			service.savetoReferenceTable(getUserSessionObject().getId());
			//END by bharath on 6/7/2019
			if(getUserSessionObject().isEngineeringBranchSelector())
			{
				return "EBSUBJECTPRIORITY";
			}
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return "NEXT";
	}

	public String insertAjax()
	{
		OUT.debug("Inside AptitudeSubmitAction (insertAjax method)");
		try
		{
			service = new AptitudeService();
			boolean result = service.insertAnswer(answerList, getUserSessionObject().getId(), remainigTime);
			StudentTUMService tumservice = new StudentTUMService();
			tumservice.updateTUMSession(getUserSessionObject().getId());
			if (result)
			{
				AuditTrailLogger.addAuditInfo(ModuleNameEnum.ANSWER_STUDENT, getUserSessionObject().getFullName()
						+ " answer the aptitude question. Remaining Time: " + remainigTime + ". Answers: " + answerList, getUserSessionObject().getLoginId());
			}
			else
			{
				service.insertStudentCGTResult(studentId, remainigTime);
				if(remainigTime==3600000)
				{
					service.savetoReferenceTable(getUserSessionObject().getId());
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return null;
	}

	public String insertStudentResult()
	{
		try
		{
			service = new AptitudeService();
			service.insertStudentCGTResult(studentId, remainigTime);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return null;
	}

	private void setSelectedMenu()
	{
		try
		{
			HttpSession mySession = request.getSession();
			Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> menuMap = (Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>) mySession.getAttribute("SideBarMenus");
			if (null != menuMap && !menuMap.isEmpty())
			{
				for (Map.Entry<ApplicationMenuDTO, List<ApplicationMenuDTO>> mapValue : menuMap.entrySet())
				{
					List<ApplicationMenuDTO> list = mapValue.getValue();
					if (null != list && !list.isEmpty())
					{
						for (ApplicationMenuDTO menu : list)
						{
							if (menu.getActionPath().equals("AptitudeAction"))
							{
								setChildSelectedSidebarMenuId(menu.getId());
								setParentSelectedSidebarMenuId(mapValue.getKey().getId());
								break;
							}
						}
					}
				}
			}

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	public int getPageAction()
	{
		return pageAction;
	}

	public void setPageAction(int pageAction)
	{
		this.pageAction = pageAction;
	}

	public List<String> getQuestionList()
	{
		return questionList;
	}

	public void setQuestionList(List<String> questionList)
	{
		this.questionList = questionList;
	}

	public String getAnswerList()
	{
		return answerList;
	}

	public void setAnswerList(String answerList)
	{
		this.answerList = answerList;
	}

	public String getAptitudeComplete()
	{
		return aptitudeComplete;
	}

	public void setAptitudeComplete(String aptitudeComplete)
	{
		this.aptitudeComplete = aptitudeComplete;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public long getRemainigTime()
	{
		return remainigTime;
	}

	public void setRemainigTime(long remainigTime)
	{
		this.remainigTime = remainigTime;
	}

	public int getRemainigSecondTime()
	{
		return remainigSecondTime;
	}

	public void setRemainigSecondTime(int remainigSecondTime)
	{
		this.remainigSecondTime = remainigSecondTime;
	}

	public int getRemainigMinuteTime()
	{
		return remainigMinuteTime;
	}

	public void setRemainigMinuteTime(int remainigMinuteTime)
	{
		this.remainigMinuteTime = remainigMinuteTime;
	}

	public boolean isInterestCompleated()
	{
		return isInterestCompleted;
	}

	public void setInterestCompleated(boolean isInterestCompleated)
	{
		this.isInterestCompleted = isInterestCompleated;
	}

	public boolean isInterestCompleted()
	{
		return isInterestCompleted;
	}

	public void setInterestCompleted(boolean isInterestCompleted)
	{
		this.isInterestCompleted = isInterestCompleted;
	}

}
