package com.lodestar.edupath.tum.questionnaire;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.TumCgtResultDAO;
import com.lodestar.edupath.dataaccessobject.dao.careerFitment.CareerFitmentDAO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.StudentCareerFitmentDTO;
import com.lodestar.edupath.datatransferobject.dto.role.ApplicationMenuDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.TumCgtResultDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.PROGRAMTEST;
import com.lodestar.edupath.tum.questionnaire.service.InterestService;
import com.lodestar.edupath.tum.service.StudentTUMService;
import com.opensymphony.xwork2.ActionContext;

public class InterestSubmitAction extends BaseAction
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(InterestSubmitAction.class);
	private int					pageAction			= 1;
	private InterestService		service;
	private List<String>		questionList;
	private String				answerList;
	private int					studentId;
	private boolean				isInterestCompleted;
	StudentDetailsDTO	student				= new StudentDetailsDTO();

	@Override
	public String execute()
	{
		OUT.debug("Inside InterestSubmitAction");
		try
		{
			OUT.debug("bharath InterestSubmitAction getUserSessionObject:{}",getUserSessionObject());
			student = new StudentDetailsDAO().getStudentDetailsByUserId(getUserSessionObject().getId());
			if(getUserSessionObject().isTrial() && student.getSource().equalsIgnoreCase(PROGRAMTEST.CAREERFITMENT.getSource()))
			{
				CareerFitmentDAO _CFDAO = new CareerFitmentDAO();
				StudentCareerFitmentDTO _SCFdto = _CFDAO.getStudentCareerFitment(student.getId());
				if(_SCFdto == null)
				{
					return "WELCOMESCREEN";
				}
			}
			service = new InterestService();
			// setQuestionList(service.createQuestionList());
			isInterestCompleted = service.isIntrestCompleted(getUserSessionObject().getId());
			JSONArray jsonArray = null;
			if (!isInterestCompleted)
			{
				jsonArray = service.getStudentCGTBySection(getUserSessionObject().getId());
			}
			pageAction = 1;
			setSelectedMenu();
			request.setAttribute("studentAnswerList", jsonArray);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String insert()
	{
		OUT.debug("Inside InterestSubmitAction");
		try
		{
			OUT.debug("bharath InterestSubmitAction getUserSessionObject:{}",getUserSessionObject());
			service = new InterestService();
			boolean result = service.insertAnswer(answerList, getUserSessionObject().getId());
			StudentTUMService tumservice = new StudentTUMService();
			tumservice.updateTUMSession(getUserSessionObject().getId());
			if (result)
			{
				AuditTrailLogger.addAuditInfo(ModuleNameEnum.ANSWER_STUDENT, getUserSessionObject().getFullName() + " answer the interest question. Answers: "
						+ answerList, getUserSessionObject().getLoginId());
			}
			
			if (getUserSessionObject().isTrial() && !getUserSessionObject().isProgramTestStudent())
			{
				service.sendMailForTrialStudent(getUserSessionObject().getId(), getUserSessionObject().getRoleTypeId(), null);
				return doGetTrialSummary();
			}
			
			//Start Sasedeve edited by Mrutyunjaya on date 03-08-2017
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			sessionMap.put(ApplicationConstants.SessionProperty.Is_IntresttestCompleted.getProperty(),true);
			//End Sasedeve edited by Mrutyunjaya on date 03-08-2017
			
			//start by bharath on 6/7/2019
			service.savetoReferenceTable(getUserSessionObject().getId());
			//END by bharath on 6/7/2019
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return "NEXT";
	}

	public String insertAjax()
	{
		OUT.debug("Inside InterestSubmitAction");
		try
		{
			OUT.debug("bharath InterestSubmitAction getUserSessionObject:{}",getUserSessionObject());
			service = new InterestService();
			boolean result = service.insertAnswer(answerList, studentId);
			StudentTUMService tumservice = new StudentTUMService();
			tumservice.updateTUMSession(getUserSessionObject().getId());
			if (result)
			{
				AuditTrailLogger.addAuditInfo(ModuleNameEnum.ANSWER_STUDENT, getUserSessionObject().getFullName() + " answer the interest question. Answers: "
						+ answerList, getUserSessionObject().getLoginId());
			}
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
			@SuppressWarnings("unchecked")
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
							if (menu.getActionPath().equals("InterestAction"))
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

	public String doGetTrialSummary()
	{
		try
		{
			//start by bharath on 6/7/2019
			service.savetoReferenceTable(getUserSessionObject().getId());
			
			//END by bharath on 6/7/2019
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return "TRIALSUMMARY";
	}

	public void doDownloadTrialReport()
	{
		try
		{
			service = new InterestService();
			File file = service.getTrialReportContent(getUserSessionObject().getId());
			sendFile(file);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	private void sendFile(File file) throws IOException, FileNotFoundException
	{
		String fileName = file.getName().replace(" ", ":");
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);

		// render the output file
		ServletOutputStream out = response.getOutputStream();
		FileInputStream fis = new FileInputStream(file);
		byte buff[] = new byte[2048];
		while (fis.read(buff) != -1)
		{
			out.write(buff);
		}
		fis.close();
		out.close();
		if (null != file)
		{
			file.delete();
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

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
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
