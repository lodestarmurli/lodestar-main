package com.lodestar.edupath.dataaccessobject.dao.document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.DocumentDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.DocumentHighlightsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.VisitedDocument;

public class DocumentsDAO
{
	String	GET_DOCUMENT_BY_OCCID				= "Documents.getDocumentByOccId";
	String	GET_DOCUMENT_BY_INDUSID				= "Documents.getDocumentByIndustryId";
	String	GET_DOCUMENT_BY_SUBJECTID			= "Documents.getDocumentBySubjectId";
	String	GET_LAST_WISH_LIST_VISITED_DOCUMENT	= "Documents.getLastVisitedWishListDocument";

	public DocumentDTO getDocumentByOccId(int occId) throws Exception
	{
		return (DocumentDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_DOCUMENT_BY_OCCID, occId);
	}

	public DocumentDTO getDocumentByIndustryId(int indusId) throws Exception
	{
		return (DocumentDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_DOCUMENT_BY_INDUSID, indusId);
	}

	public DocumentDTO getDocumentBySubjectId(int subjecId) throws Exception
	{
		return (DocumentDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_DOCUMENT_BY_SUBJECTID, subjecId);
	}

	public DocumentDTO getLastVisitedWishListDocument(int userId) throws Exception
	{
		return (DocumentDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_LAST_WISH_LIST_VISITED_DOCUMENT, userId);
	}

	public void updateLastVisitedTime(VisitedDocument visitedDoc) throws Exception
	{
		MyBatisManager.getInstance().insert(MyBatisMappingConstants.UPDATE_DOCUMENT_LAST_VISITED, visitedDoc);
	}

	public void updateDocumentHighlights(DocumentHighlightsDTO documentHighlights) throws Exception
	{
		MyBatisManager.getInstance().insert(MyBatisMappingConstants.UPDATE_DOCUMENT_HIGHLIGHTS, documentHighlights);
	}

	public DocumentHighlightsDTO getDocumentHighlights(DocumentHighlightsDTO documentHighlights) throws Exception
	{
		return (DocumentHighlightsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_DOCUMENT_HIGHLIGHTS, documentHighlights);
	}

	public VisitedDocument getLastPageOfDocument(int studentId, int docId) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docId", docId);
		paramMap.put("studentId", studentId);
		return (VisitedDocument) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_VISITED_DOCUMENT_BYUSER_BYDOCID, paramMap);
	}

	public List<DocumentDTO> getVisitedOccupationDocs(int userId) throws Exception
	{
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_VISITED_OCC_DOCUMENT_BYUSER, userId);
	}

	public DocumentDTO getDocumentNShortListId(int studentId, int docId) throws Exception
	{
		Map<String, Object> paramObject = new HashMap<String, Object>();
		paramObject.put("studentId", studentId);
		paramObject.put("docId", docId);

		return (DocumentDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_DOCUMENT_N_SHORTLISTID_BY_DOCID_BY_USERID, paramObject);
	}

	public Integer insertDocumentPath(OccupationDTO occupationDTO, String documentPath) throws Exception
	{
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setDocumentPath(documentPath);
		documentDTO.setOccupationId(occupationDTO.getId());
		Integer count = -1;
		count = MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_DOCUMENT, documentDTO);
		return count;
	}

	public List<DocumentDTO> getDocumentList() throws Exception
	{
		List<DocumentDTO> list = null;
		list = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_DOCUMENT, new DocumentDTO());
		return list;
	}

	public DocumentDTO getSubjectDetailById(int docId) throws Exception
	{
		Map<String, Object> paramObject = new HashMap<String, Object>();
		paramObject.put("docId", docId);

		return (DocumentDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_SUBJECT_DETAIL_BY_DOC_ID, paramObject);
	}

}
