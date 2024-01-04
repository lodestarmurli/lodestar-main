package com.jamochatech.tools.fileupload.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jamochatech.tools.excelengine.dao.DBManager;
import com.jamochatech.tools.fileupload.dto.DocumentDTO;
import com.jamochatech.tools.fileupload.dto.IndustryDTO;
import com.jamochatech.tools.fileupload.dto.SubjectDTO;


public class DocumentDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(DocumentDAO.class);

	public List<IndustryDTO> getIndustryList() throws Exception
	{
		List<IndustryDTO> list = DBManager.getInstance().getResultAsList(DBOperations.GET_ALL_INDUSTRY, null);
		OUT.debug("Industry fetched from DB :{}", (list != null ? list.size() : 0));
		return list;
	}

	public List<SubjectDTO> getSubjectList() throws Exception
	{
		List<SubjectDTO> list = DBManager.getInstance().getResultAsList(DBOperations.GET_ALL_SUBJECT, null);
		OUT.debug("Subject fetched from DB :{}", (list != null ? list.size() : 0));
		return list;
	}

	public List<DocumentDTO> getDocumentList() throws Exception
	{
		List<DocumentDTO> list = DBManager.getInstance().getResultAsList(DBOperations.GET_ALL_DOCUMENT, new DocumentDTO());
		OUT.debug("Document fetched from DB :{}", (list != null ? list.size() : 0));
		return list;
	}
	
	public Integer insertDocumentPath(Integer id, String documentPath, String type) throws Exception
	{
		Integer count = -1;
		try
		{
			DocumentDTO documentDTO = new DocumentDTO();
			documentDTO.setDocumentPath(documentPath);
			documentDTO.setType(type);
			if (type.equalsIgnoreCase("occupation"))
			{
				documentDTO.setOccupationId(id);
			}
			else if (type.equalsIgnoreCase("industry"))
			{
				documentDTO.setIndustryId(id);
			}
			else if (type.equalsIgnoreCase("subject"))
			{
				documentDTO.setSubjectId(id);
			}			
			count = DBManager.getInstance().insert(DBOperations.INSERT_DOCUMENT, documentDTO);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return count;
	}

	public boolean updateDocumentPath(Integer id, String documentPath, String type)
	{
		boolean updateStatus = true;
		try
		{
			DocumentDTO documentDTO = new DocumentDTO();
			documentDTO.setDocumentPath(documentPath);
			documentDTO.setType(type);
			if (type.equalsIgnoreCase("occupation"))
			{
				documentDTO.setOccupationId(id);
			}
			else if (type.equalsIgnoreCase("industry"))
			{
				documentDTO.setIndustryId(id);
			}
			else if (type.equalsIgnoreCase("subject"))
			{
				documentDTO.setSubjectId(id);
			}
			DBManager.getInstance().update(DBOperations.UPDATE_DOCUMENT, documentDTO);
		}
		catch (Exception e)
		{
			updateStatus = false;
			OUT.error("Exception", e);
		}
		return updateStatus;
	}
}
