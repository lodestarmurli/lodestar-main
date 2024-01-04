package com.lodestar.edupath.dataaccessobject.dao.notes;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.notes.FacilitatorNotesDTO;

public class FacilitatorNotesDAO
{
   private static final Logger OUT = LoggerFactory.getLogger(FacilitatorNotesDAO.class);
   
   public int insertNotes(SqlSession session, FacilitatorNotesDTO notesDTO) throws Exception
   {
	   int id = session.insert(MyBatisMappingConstants.INSERT_FACILITATOR_NOTES, notesDTO);
	   OUT.debug("FacilitatorNotes inserted id: {}", notesDTO.getId() );
	   return id;
   }
   
   public FacilitatorNotesDTO getNotesById(FacilitatorNotesDTO notesDTO) throws Exception
   {
	   FacilitatorNotesDTO result = (FacilitatorNotesDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_FACILITATOR_NOTES, notesDTO);
	   return result;
   }
   
   public int updateNotes(SqlSession session, FacilitatorNotesDTO notesDTO) throws Exception
   {
	   int id = session.update(MyBatisMappingConstants.UPDATE_FACILITATOR_NOTES, notesDTO);
	   OUT.debug("FacilitatorNotes updated id: {}", notesDTO.getId());
	   return id;
   }
}
