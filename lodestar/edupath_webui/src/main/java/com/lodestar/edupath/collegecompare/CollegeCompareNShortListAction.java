package com.lodestar.edupath.collegecompare;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.collegecompare.bean.CollegeCompareBean;
import com.lodestar.edupath.collegecompare.service.CollegeCompareNShortListService;
import com.lodestar.edupath.datatransferobject.exception.EdupathException;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.opensymphony.xwork2.ModelDriven;

public class CollegeCompareNShortListAction extends BaseAction implements ModelDriven<CollegeCompareBean>
{
	private static final long						serialVersionUID	= 1L;
	private static final Logger						OUT					= LoggerFactory.getLogger(CollegeCompareNShortListAction.class);

	private CollegeCompareBean						compareBean			= new CollegeCompareBean();
	private Boolean									isCompareAction;
	private Map<String, Map<String, List<Object>>>	collegeDetailsMap	= new HashMap<String, Map<String, List<Object>>>();

	@Override
	public String execute() throws Exception
	{
		try
		{
			int studentId = 0;
			OUT.debug("Getting college details for: {}, studentId: {}", compareBean.getCollegeIds(), (studentId = getStudentSessionObject().getId()));
			compareBean.setStudentId(studentId);
			CollegeCompareNShortListService collegeCompareService = new CollegeCompareNShortListService();
			collegeDetailsMap = collegeCompareService.getCollegeDetailsByIds(compareBean);
			return SUCCESS;
		}
		catch (Exception e)
		{
			OUT.debug(ApplicationConstants.EXCEPTION, e);
			return ERROR;
		}
	}

	public String doShortList()
	{
		PrintWriter writer = null;
		JSONObject responsBuilder = new JSONObject();
		try
		{
			writer = response.getWriter();
			int studentId = 0;
			OUT.debug("Getting college details for: {}, studentId: {}", compareBean.getCollegeIds(), (studentId = getStudentSessionObject().getId()));
			compareBean.setStudentId(studentId);
			new CollegeCompareNShortListService().insertCollegeShortList(compareBean, getUserSessionObject().getLoginId());
			responsBuilder.put(ApplicationConstants.STATUS, "SUCCESS");
			responsBuilder.put(ApplicationConstants.MESSAGE, getText("com.edupath.college.shortlist.college.success.msg"));
		}
		catch (final EdupathException e)
		{
			responsBuilder.put(ApplicationConstants.STATUS, "ERROR");
			String message = getText(e.getErrorMessage(), e.getErrorCode());
			responsBuilder.put(ApplicationConstants.MESSAGE, message);
			OUT.error(ApplicationConstants.EXCEPTION, message);
		}
		catch (Exception e)
		{
			responsBuilder.put(ApplicationConstants.STATUS, "ERROR");
			responsBuilder.put(ApplicationConstants.MESSAGE, getText("com.edupath.action.internal.error"));
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (writer != null)
			{
				writer.write(responsBuilder.toString());
				writer.close();
			}
		}
		return null;
	}

	@Override
	public CollegeCompareBean getModel()
	{
		return compareBean;
	}

	public Map<String, Map<String, List<Object>>> getCollegeDetailsMap()
	{
		return collegeDetailsMap;
	}

	public void setCollegeDetailsMap(Map<String, Map<String, List<Object>>> collegeDetailsMap)
	{
		this.collegeDetailsMap = collegeDetailsMap;
	}

	public Boolean getIsCompareAction()
	{
		return isCompareAction;
	}

	public void setIsCompareAction(Boolean isCompareAction)
	{
		this.isCompareAction = isCompareAction;
	}

}
