package com.lodestar.edupath.document.action;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.DocumentDTO;
import com.lodestar.edupath.datatransferobject.dto.student.DocumentHighlightsDTO;
import com.lodestar.edupath.document.service.DocumentService;

public class GetDocumentForReading extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(GetDocumentForReading.class);
	private static final String	CURRENT_DOC_ID		= "CURRENT_DOC_ID";

	private int					industryId;
	private int					occupationId;
	private int					subjectId;
	private boolean				lastWishListDoc;

	private String				highlight;
	private String				status;
	private int					pageNumber;
	private boolean				related				= false;

	private Map<String, Object>	details;

	private String				docURL;

	private static final String	SUCCESS				= "SUCCESS";
	private static final String	ERROR				= "ERROR";

	public String documentURL() throws Exception
	{
		try
		{
			OUT.debug("bharath Getting the document for occ:{}, industry{}, subject:{}, lastvisted:{}, for UserId:{}", occupationId, industryId, subjectId, lastWishListDoc,
					getUserSessionObject().getId());
			UserSessionObject userSessionObject = getUserSessionObject();
			if (userSessionObject == null)
			{
				throw new Exception("Invalid user");
			}

			DocumentService service = new DocumentService();
			DocumentDTO document = null;
			if (occupationId > 0)
			{
				document = service.getDocumentByOccId(occupationId);
			}
			else if (industryId > 0)
			{
				document = service.getDocumentByIndusId(industryId);
			}
			else if (subjectId > 0)
			{
				document = service.getDocumentBySubjectId(subjectId);
			}
			else if (lastWishListDoc)
			{
				document = service.getLastVisitedWishListDocument(getUserSessionObject().getId());
			}
			OUT.debug("bharath Returning the documentId:{}", document.getId());
			request.getSession(false).setAttribute(CURRENT_DOC_ID, document.getId());

			docURL = service.getDoucmentURL(document.getDocumentPath());
			OUT.debug("bharath  GetDocumentForReading docURL:{}",docURL);
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

	public String occIndDetails() throws Exception
	{
		try
		{
			int docId = (int) request.getSession(false).getAttribute(CURRENT_DOC_ID);
			OUT.debug("bharath Getting additional details docId:{}, realted:{}", docId, related);
			details = new DocumentService().getDetailsForRP(getUserSessionObject().getId(), docId, related);
			status = SUCCESS;
		}
		catch (Throwable e)
		{
			OUT.error("Exception", e);
			throw e;
		}

		return "data";
	}

	public String docHighlights()
	{
		int docId = (int) request.getSession(false).getAttribute(CURRENT_DOC_ID);
		try
		{
			OUT.debug("bharath Getting highlights for docId:{}", docId);
			DocumentHighlightsDTO documentHighlights = new DocumentService().getDocumentHighlights(getUserSessionObject().getId(), docId);
			if (documentHighlights != null)
			{
				highlight = documentHighlights.getHighlights();
			}
			OUT.debug("bharath Highlights: {}", highlight);
			status = SUCCESS;
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			status = ERROR;
		}
		return "data";
	}

	public String lastPageVisited()
	{
		int docId = (int) request.getSession(false).getAttribute(CURRENT_DOC_ID);
		try
		{
			OUT.debug("bharath Getting lastVisitedPage for docId:{}", docId);
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
			OUT.debug("bharath Update lastVisitedPage for docId:{}, pageNumber:{}", docId, pageNumber);
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

	public String updateHighlight()
	{
		int docId = (int) request.getSession(false).getAttribute(CURRENT_DOC_ID);
		try
		{
			OUT.debug("bharath Update highlights for docId:{}, highlights:{}", docId, highlight);
			new DocumentService().updateDocumentHighlights(getUserSessionObject().getId(), docId, highlight);
			status = SUCCESS;
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			status = ERROR;
		}
		return "data";
	}

	public String subjectDetails()
	{
		try
		{
			int docId = (int) request.getSession(false).getAttribute(CURRENT_DOC_ID);
			OUT.debug("bharath Getting additional details docId:{}", docId);
			details = new DocumentService().getSubjectDetails(docId);
			status = SUCCESS;
		}
		catch (Throwable e)
		{
			OUT.error("Exception", e);
			status = ERROR;
		}
		return "data";
	}

	public int getIndustryId()
	{
		return industryId;
	}

	public void setIndustryId(int industryId)
	{
		this.industryId = industryId;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	public int getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(int subjectId)
	{
		this.subjectId = subjectId;
	}

	public int getPageNumber()
	{
		return pageNumber;
	}

	public void setPageNumber(int pageNumber)
	{
		this.pageNumber = pageNumber;
	}

	public String getHighlight()
	{
		return highlight;
	}

	public void setHighlight(String highlight)
	{
		this.highlight = highlight;
	}

	public String getStatus()
	{
		return status;
	}

	public Map<String, Object> getDetails()
	{
		return details;
	}

	public void setLastWishListDoc(boolean lastWishListDoc)
	{
		this.lastWishListDoc = lastWishListDoc;
	}

	public void setRelated(boolean related)
	{
		this.related = related;
	}

	public String getDocURL()
	{
		return docURL;
	}
}
