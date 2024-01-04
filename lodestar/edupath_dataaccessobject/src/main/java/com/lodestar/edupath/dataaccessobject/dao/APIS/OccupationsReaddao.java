package com.lodestar.edupath.dataaccessobject.dao.APIS;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.DocumentDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.VisitedDocument;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class OccupationsReaddao {
	
	private static final Logger	OUT	= LoggerFactory.getLogger(OccupationsReaddao.class);
	
	
	public List<OccupationDTO> GetStudentOccupationsRead(int studentid)
	{
		
		VisitedDocument vis_studentid=new VisitedDocument();
		
		List<OccupationDTO> listofoccupationreport=new ArrayList<OccupationDTO>();
		
		vis_studentid.setStudentId(studentid);
		
		
		try {
			
			
			 List<VisitedDocument> listofdocument = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.Get_List_Occupation_Read, vis_studentid);
			 
			 
			 for(VisitedDocument doc : listofdocument)
			 {
				 
				 DocumentDTO newdoc=new DocumentDTO();
				 newdoc.setId(doc.getDocumentId());
				 
				 
				 DocumentDTO newdoc1=new DocumentDTO();
				 
				 newdoc1=(DocumentDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.Get_List_Document, newdoc);
				 
				 
				 
				 
				 if(newdoc1!=null && newdoc1.getOccupationName()!=null && !newdoc1.getOccupationName().trim().equals(""))
				 {
					 OccupationDTO tempoc=new OccupationDTO();
					 tempoc.setName(newdoc1.getOccupationName());
					 listofoccupationreport.add(tempoc);
				 }
				 else if(newdoc1!=null && newdoc1.getIndustryName()!=null && !newdoc1.getIndustryName().trim().equals(""))
				 {
					 OccupationDTO tempoc=new OccupationDTO();
					 tempoc.setIndustryName(newdoc1.getIndustryName());
					 listofoccupationreport.add(tempoc);
				 }
				
			 } 
			 
			 
			 
			 
		} catch (Exception e) {
			
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		 
		
		return listofoccupationreport;
		
	}
	
	public int GetTotalIndustry(int studentid)
	{
		int totalno=0;
		
		
		try {
			totalno= MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.Get_Total_industry, studentid);
			
			
			
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	
		
		
		
		return totalno;
	}
	
	
	public int GetTotalOccupation(int studentid)
	{
		int totalno=0;
		
		
		try {
			totalno= MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.Get_Total_Occupation, studentid);
			
			
			
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	
		
		
		
		return totalno;
	}
	
	

}
