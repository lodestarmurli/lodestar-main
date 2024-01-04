package com.lodestar.edupath.payment.action;



import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.UploadStatusEnum;
import com.lodestar.edupath.bulkupload.service.EduPathBulkUploadService;
import com.lodestar.edupath.datatransferobject.dto.BulkUploadStatusDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.school.ManageSchoolSummaryAction;


public class ManagePaymentAction extends BaseAction 
{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(ManageSchoolSummaryAction.class);
	
	

	@Override
	public String execute()
	{
		OUT.debug("ManagePaymentaction : inside execute method");
		try
		{
			show();
			UserSessionObject userSessionObject = getUserSessionObject();
			if (null == userSessionObject)
			{
				return "SessionExpired";
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}
	
	public String show()
	{
		boolean isInProgress = false;
		try
		{
			
			BulkUploadStatusDTO excelUploadDTO = new EduPathBulkUploadService().getExcelUploadDetails("Payment");
			JSONObject json = new JSONObject();
			if (excelUploadDTO != null)
			{
				json.put("fileName", excelUploadDTO.getFileName());
				json.put("uploadedAt", TimeUtil.getDateFromMillis(excelUploadDTO.getUploadedAt().getTime(), ""));
				json.put("uploadedBy", excelUploadDTO.getUploadedBy());
				json.put("completedAt",
						excelUploadDTO.getCompletedAt() != null ? TimeUtil.getDateFromMillis(excelUploadDTO.getCompletedAt().getTime(), TimeUtil.REPORT_DATE_FORMAT)
								: "-");
				json.put("status", excelUploadDTO.getStatus());
				json.put("message", excelUploadDTO.getMessage());
				if (excelUploadDTO.getStatus().equalsIgnoreCase(UploadStatusEnum.INPROGRESS.getValue()))
				{
					isInProgress = true;
				}
				request.setAttribute("uploadJson", json.toString());
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		request.setAttribute("isInProgress", isInProgress);
		request.setAttribute("moduleName", "Payment");
		return SUCCESS;
	}

}
