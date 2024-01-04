package com.lodestar.edupath.APIS.dailyHunt.AptitudeResultDownload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.DocumentDTO;
import com.lodestar.edupath.document.service.DocumentService;

public class OccupationViewerAction extends BaseAction{

	
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(OccupationViewerAction.class);

	private String occid;
	private String datastr;
	private String docType="OCCUPATION";
	private static final String	CURRENT_DOC_ID		= "CURRENT_DOC_ID";

	private String				docURL;

	private static final String	SUCCESS				= "SUCCESS";
	private static final String	ERROR				= "ERROR";
	private String				status;
	private int					pageNumber;
	private String				local_dataStr;
	
	
	
	@Override
	public String execute() throws Exception
	{
		datastr = "occupationId="+occid+"&industryId=0";
		return SUCCESS;
	}

	
	public String getdocumentURL() throws Exception
	{
		try
		{
//			int occupationId = Integer.parseInt(local_dataStr.split("=",1));
			int occupationId =41;	
			DocumentService service = new DocumentService();
			DocumentDTO document = null;
			if (occupationId > 0)
			{
				document = service.getDocumentByOccId(occupationId);
			}
			request.getSession(false).setAttribute(CURRENT_DOC_ID, document.getId());

			docURL = service.getDoucmentURL(document.getDocumentPath());
			// docURL = "https://view-api.box.com/1/sessions/ea93967a05414067be0fd8fa34ae3f4b/assets/";
			status = SUCCESS;
		}
		catch (Throwable e)
		{
			OUT.error("Exception", e);
			request.getSession(false).setAttribute(CURRENT_DOC_ID, 0);
			status = ERROR;
			throw e;
		}
		return "data";
	}

	public String lastPageVisited()
	{
		int docId = (int) request.getSession(false).getAttribute(CURRENT_DOC_ID);
		try
		{
			pageNumber = new DocumentService().getLastPageNumber(getUserSessionObject().getId(), docId);
			status = SUCCESS;
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			status = ERROR;
		}
		return "data";
	}

	public String updateCurrentPage()
	{
		int docId = (int) request.getSession(false).getAttribute(CURRENT_DOC_ID);
		try
		{
			new DocumentService().updateVisitedDocument(getUserSessionObject().getId(), docId, pageNumber);
			status = SUCCESS;
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			status = ERROR;
		}
		return "data";
	}


	public String getDatastr() {
		return datastr;
	}


	public void setDatastr(String datastr) {
		this.datastr = datastr;
	}


	public String getOccid() {
		return occid;
	}


	public void setOccid(String occid) {
		this.occid = occid;
	}


	public String getDocType() {
		return docType;
	}


	public void setDocType(String docType) {
		this.docType = docType;
	}


	public String getDocURL() {
		return docURL;
	}


	public void setDocURL(String docURL) {
		this.docURL = docURL;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getPageNumber() {
		return pageNumber;
	}


	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}


	public String getLocal_dataStr() {
		return local_dataStr;
	}


	public void setLocal_dataStr(String local_dataStr) {
		this.local_dataStr = local_dataStr;
	}
	
	
	
}
