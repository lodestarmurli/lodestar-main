package com.lodestar.edupath.document.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;

import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.document.DocumentsDAO;
import com.lodestar.edupath.dataaccessobject.dao.industry.IndustryDAO;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.datatransferobject.dto.DocumentDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.IndustryDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.DocumentHighlightsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.VisitedDocument;
import com.lodestar.edupath.util.BoxViewClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DocumentService
{
	private static final Logger	OUT					= LoggerFactory.getLogger(DocumentService.class);
	public DocumentDTO getDocumentByOccId(int occupationId) throws Exception
	{
		return new DocumentsDAO().getDocumentByOccId(occupationId);
	}

	public DocumentDTO getDocumentByIndusId(int industryId) throws Exception
	{
		return new DocumentsDAO().getDocumentByIndustryId(industryId);
	}

	public DocumentDTO getDocumentBySubjectId(int subjectId) throws Exception
	{
		return new DocumentsDAO().getDocumentBySubjectId(subjectId);
	}

	public DocumentDTO getLastVisitedWishListDocument(int userId) throws Exception
	{
		return new DocumentsDAO().getLastVisitedWishListDocument(userId);
	}

	public int getLastPageNumber(int userId, int docId) throws Exception
	{
		int pageNumber = 0;
		VisitedDocument lastPageOfDocument = new DocumentsDAO().getLastPageOfDocument(new StudentDetailsDAO().getStudentDetailsByUserId(userId).getId(), docId);
		if (lastPageOfDocument != null)
		{
			pageNumber = lastPageOfDocument.getPageNumber();
		}
		return pageNumber;
	}

	public void updateVisitedDocument(int userId, int documentId, int pageNumber) throws Exception
	{
		VisitedDocument visitedDocument = new VisitedDocument();
		visitedDocument.setDocumentId(documentId);
		visitedDocument.setStudentId(new StudentDetailsDAO().getStudentDetailsByUserId(userId).getId());
		visitedDocument.setPageNumber(pageNumber);
		new DocumentsDAO().updateLastVisitedTime(visitedDocument);
	}

	public DocumentHighlightsDTO getDocumentHighlights(int userId, int documentId) throws Exception
	{
		DocumentHighlightsDTO documentHighlights = new DocumentHighlightsDTO();
		documentHighlights.setDocumentId(documentId);
		documentHighlights.setStudentId(new StudentDetailsDAO().getStudentDetailsByUserId(userId).getId());

		documentHighlights = new DocumentsDAO().getDocumentHighlights(documentHighlights);
		return documentHighlights;
	}

	public void updateDocumentHighlights(int userId, int documentId, String highlights) throws Exception
	{
		DocumentHighlightsDTO documentHighlights = new DocumentHighlightsDTO();
		documentHighlights.setDocumentId(documentId);
		documentHighlights.setStudentId(new StudentDetailsDAO().getStudentDetailsByUserId(userId).getId());
		documentHighlights.setHighlights(highlights);
		new DocumentsDAO().updateDocumentHighlights(documentHighlights);
	}

	public Map<String, Object> getDetailsForRP(int userId, int documentId, boolean getRelated) throws Exception
	{
		Map<String, Object> finalMap = new HashMap<>();
		try
		{
			DocumentDTO document = new DocumentsDAO().getDocumentNShortListId(new StudentDetailsDAO().getStudentDetailsByUserId(userId).getId(), documentId);
			String name = document.getOccupationId() > 0 ? "OCCUPATION :  " + document.getOccupationName().toUpperCase() : "INDUSTRY :  "
					+ document.getIndustryName().toUpperCase();
	
			if (getRelated && document.getOccupationId() > 0)
			{
				List<OccupationDTO> relatedOccupationDetails = new OccupationDAO().getRelatedOccupationDetails(document.getOccupationId());
				if (relatedOccupationDetails != null && !relatedOccupationDetails.isEmpty())
				{
					finalMap.put("relatedOcc", relatedOccupationDetails);
				}
			}
			if (!getRelated && document.getOccupationId() > 0)
			{
				List<IndustryDTO> industryList = new IndustryDAO().getListOfIndustryForOccFromWishOrPrimary(document.getOccupationId());
				if (!industryList.isEmpty())
				{
					finalMap.put("industryId", industryList.get(0).getId());
				}
			}
	
			String id = document.getOccupationId() > 0 ? "occ_" + document.getOccupationId() : "ind_" + document.getIndustryId();
	
			finalMap.put("occupationSummary", document.getOccupationSummary());
			finalMap.put("name", name);
			finalMap.put("id", id);
			finalMap.put("shortListId", document.getShortListId());
		}
		catch (Throwable e)
		{
			OUT.error("Exception", e);
			throw e;
		}
		return finalMap;
	}

	public Map<String, Object> getSubjectDetails(int docId) throws Exception
	{
		DocumentDTO document = new DocumentsDAO().getSubjectDetailById(docId);
		Map<String, Object> finalMap = new HashMap<>();
		if (null != document)
		{
			String name = "SUBJECT :  " + document.getSubjectName().toUpperCase();
			finalMap.put("name", name);
			finalMap.put("id", document.getSubjectId());
		}
		return finalMap;
	}

	public String getDoucmentURL(String documentBoxViewId) throws ClientProtocolException, IOException
	{
		return new BoxViewClient().getSessionURL(documentBoxViewId);
	}
	

}
