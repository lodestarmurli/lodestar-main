package com.lodestar.edupath.programTest.streamselector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.CGT.StudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.SystemRecommendationV2;
import com.lodestar.edupath.dataaccessobject.dao.streamselector.RiasecStreamSelectorDAO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.streamselector.RiasecStreamSelectorDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.streamselector.StreamSelectorDescriptionDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.streamselector.StreamSelectorOccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.STREAM;
import com.lodestar.edupath.datatransferobject.dto.programTest.streamselector.StreamSelectorResultDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.streamselector.StreamSelectorResultVO;


public class StreamSelectorRecommendation {

	private static final Logger		 OUT						= LoggerFactory.getLogger( StreamSelectorRecommendation.class);

	private List<StreamSelectorResultVO> streamSelectorVOList = new ArrayList<StreamSelectorResultVO>();
	private Map<String, Object>				systemRecommendation	= new HashMap<String, Object>();
	StreamSelectorResultDTO streamSelectorResultDTO = new  StreamSelectorResultDTO();

	SqlSession session = null;
//	 = new StudentCGTResult();
//	StudentCGTDAO cgtresultDAO = new StudentCGTDAO();
	Map<String, Object>		        resultMap		= new LinkedHashMap<String, Object>();
	RiasecStreamSelectorDAO dao = new RiasecStreamSelectorDAO();

	
	
	public List<StreamSelectorResultVO> getStreamSelectorRecommendation(int studentId,StudentCGTResult cgtresultDTO ) throws Exception
	{
		try 
		{
			session = MyBatisManager.getInstance().getSession();
				systemRecommendation = new SystemRecommendationV2().getSystemRecommendation(studentId, false);
	//		cgtresultDTO = cgtresultDAO.getStudentCGTResultByStudentId(studentId);
				String riasecCode = cgtresultDTO.getPersonalityCode();
				String subriasecCode = riasecCode.substring(0, 3);
	
				// create list of stream from enum
				for (STREAM streamtemp : STREAM.values()) {
					StreamSelectorResultVO _SSRVO = new StreamSelectorResultVO();
					_SSRVO.setStream(streamtemp.getName());
					_SSRVO.setMainStream(streamtemp.getMainStream());
					_SSRVO.setStreamPriority(streamtemp.getPriority());
					_SSRVO.setAbililtyRequired1(streamtemp.getAbililtyRequired1());
					_SSRVO.setAbililtyRequired2(streamtemp.getAbililtyRequired2());
					_SSRVO.setStudentId(studentId);
					_SSRVO.setRiasecCode(subriasecCode);
					_SSRVO.setCommonName(streamtemp.getCommonName());
					streamSelectorVOList.add(_SSRVO);
				}
	
				RiasecStreamSelectorDTO riasecStreamSelectordto = dao.getStreamByRiasecCode(subriasecCode);
	
				Map<String, String> app_score = new HashMap<String, String>();
				String[] keyVals = cgtresultDTO.getAppScore().split(",");
				for (String keyVal : keyVals) {
					String[] parts = keyVal.split(":", 2);
					app_score.put(parts[0].trim(), parts[1].trim());
				}
	
				// assigning AbilityOutcome to list of stream
				getAbilityScore(app_score);
				if (!app_score.isEmpty()) {
					getfitment(riasecStreamSelectordto);
				}
	
				// assigning Occupation to list of stream
				//getfitmentOccupation(subriasecCode);
	
				// sort list of stream by streamFitment StreamPriority
				Collections.sort(streamSelectorVOList, new StreamSelectorPrioritySorter());
	
				// picking top 5 after sorting
				streamSelectorVOList = streamSelectorVOList.subList(0, 5);
				// removing low streamFitment objects
				List<StreamSelectorResultVO> indexes = new ArrayList<StreamSelectorResultVO>();
				for (int count = 0; count < streamSelectorVOList.size(); count++) {
	
					if (streamSelectorVOList.get(count).getStreamFitment() == 1) {
						indexes.add(streamSelectorVOList.get(count));
					}
				}
				streamSelectorVOList.removeAll(indexes);
	
				// Saving final list.
				String finalfitmentStr = null;
				String finaloccStr = "{ }";
				for (StreamSelectorResultVO _SSRVO : streamSelectorVOList) {
					if (finalfitmentStr != null) {
						finalfitmentStr += ", ";
					} else {
						finalfitmentStr = new String();
						finalfitmentStr += "{";
					}
	
					finalfitmentStr += _SSRVO.getStream() + ": " + _SSRVO.getStreamFitment();
	
	//			if (finaloccStr != null)
	//			{
	//				finaloccStr += ", ";
	//			}
	//			else
	//			{
	//				finaloccStr = new String();
	//				finaloccStr +="{";
	//			}
	//
	//			finaloccStr += _SSRVO.getStream() + ": " + _SSRVO.getOccupation();
	
				}
				finalfitmentStr += "}";
				finaloccStr = "{ }";
				streamSelectorResultDTO.setStudentId(studentId);
				streamSelectorResultDTO.setStreamFitment(finalfitmentStr);
				streamSelectorResultDTO.setStreamOccupation(finaloccStr);
				RiasecStreamSelectorDAO dao = new RiasecStreamSelectorDAO();
				int result = dao.insertOrUpdateStreamSelectorResult(session, streamSelectorResultDTO);
	
				session.commit();
				for (StreamSelectorResultVO _SSRVO : streamSelectorVOList) {
					StreamSelectorDescriptionDTO streamdescrip = dao.getStreamSelectorDescription(_SSRVO.getStream());
					if (streamdescrip != null) {
						_SSRVO.setDescription(streamdescrip.getDescription());
						_SSRVO.setCourses(streamdescrip.getCourses());
						_SSRVO.setCareerPotential(streamdescrip.getCareerPotential());
	
					}
				}

		} catch (Exception e) {
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		OUT.debug("GetStreamSelectorRecommendation streamSelectorVOList:{}",streamSelectorVOList);
		return streamSelectorVOList;
	}
	
	
	
	
	
	

	
	
	public void getAbilityScore(Map<String,String> app_score)
	{
//		appScore=Mechanical: M, Numerical: H, Reasoning: H, Spatial: M, Verbal: H
		for(String name : app_score.keySet())
		{
			for(StreamSelectorResultVO _SSRVO : streamSelectorVOList)
			{
				if(_SSRVO.getAbililtyRequired1().trim().equalsIgnoreCase(name.trim()))
				{
					_SSRVO.setAbililtyValue1(app_score.get(name.trim()));
					
				}
				if(_SSRVO.getAbililtyRequired2().trim().equalsIgnoreCase(name.trim()))
				{
					_SSRVO.setAbililtyValue2(app_score.get(name.trim()));
					
				}
			}
				
		}
		
		for (StreamSelectorResultVO _SSRVO : streamSelectorVOList)  
		{
			if( (_SSRVO.getAbililtyValue1().trim().equalsIgnoreCase("H") && _SSRVO.getAbililtyValue2().trim().equalsIgnoreCase("H"))  || (_SSRVO.getAbililtyValue1().trim().equalsIgnoreCase("H") && _SSRVO.getAbililtyValue2().trim().equalsIgnoreCase("M")) )
			{
				_SSRVO.setAbililtyOutcome(ApplicationConstants.PRIORITY.HIGH.getName());
			}
			else if( (_SSRVO.getAbililtyValue1().trim().equalsIgnoreCase("M") && _SSRVO.getAbililtyValue2().trim().equalsIgnoreCase("H"))  || (_SSRVO.getAbililtyValue1().trim().equalsIgnoreCase("M") && _SSRVO.getAbililtyValue2().trim().equalsIgnoreCase("M")) )
			{
				_SSRVO.setAbililtyOutcome(ApplicationConstants.PRIORITY.MEDIUM.getName());
			}
			else if( (_SSRVO.getAbililtyValue1().trim().equalsIgnoreCase("H") && _SSRVO.getAbililtyValue2().trim().equalsIgnoreCase("L")) || (_SSRVO.getAbililtyValue1().trim().equalsIgnoreCase("M") && _SSRVO.getAbililtyValue2().trim().equalsIgnoreCase("L")) 
			      || (_SSRVO.getAbililtyValue1().trim().equalsIgnoreCase("L") && _SSRVO.getAbililtyValue2().trim().equalsIgnoreCase("L")) || (_SSRVO.getAbililtyValue1().trim().equalsIgnoreCase("L") && _SSRVO.getAbililtyValue2().trim().equalsIgnoreCase("H")) 
				  || (_SSRVO.getAbililtyValue1().trim().equalsIgnoreCase("L") && _SSRVO.getAbililtyValue2().trim().equalsIgnoreCase("M")) )
			{
				_SSRVO.setAbililtyOutcome(ApplicationConstants.PRIORITY.LOW.getName());
			}
			
					
		}		
			
	}
	
	
	
	public void getfitment(RiasecStreamSelectorDTO riasecStreamSelectordto)
	{
		for(StreamSelectorResultVO _SSRVO : streamSelectorVOList)
		{
			int code=0;
			if(_SSRVO.getStream().trim().equalsIgnoreCase(ApplicationConstants.STREAM.SCIENCEWITHMATH.getName()))
			{
				code=riasecStreamSelectordto.getSciencewithMath();
			}
			else if(_SSRVO.getStream().trim().equalsIgnoreCase(ApplicationConstants.STREAM.SCIENCEWITHBIO.getName()))
			{
				code=riasecStreamSelectordto.getSciencewithBio();
			}
			else if(_SSRVO.getStream().trim().equalsIgnoreCase(ApplicationConstants.STREAM.SCIENCEWITHMATHDESIGN.getName()))
			{
				code=riasecStreamSelectordto.getSciencewithMathDesign();
			}
			else if(_SSRVO.getStream().trim().equalsIgnoreCase(ApplicationConstants.STREAM.COMMERCEWITHMATH.getName()))
			{
				code=riasecStreamSelectordto.getCommercewithMath();
			}
			else if(_SSRVO.getStream().trim().equalsIgnoreCase(ApplicationConstants.STREAM.COMMERCEWITHOUTMATH.getName()))
			{
				code=riasecStreamSelectordto.getCommercewithoutMath();
			}
			else if(_SSRVO.getStream().trim().equalsIgnoreCase(ApplicationConstants.STREAM.ARTSWITHLANGUAGES.getName()))
			{
				code=riasecStreamSelectordto.getArtswithLanguages();
			}
			else if(_SSRVO.getStream().trim().equalsIgnoreCase(ApplicationConstants.STREAM.ARTSWITHDESIGN.getName()))
			{
				code=riasecStreamSelectordto.getArtswithDesign();
			}
			else if(_SSRVO.getStream().trim().equalsIgnoreCase(ApplicationConstants.STREAM.ARTSWITHPSYCHOLOGY.getName()))
			{
				code=riasecStreamSelectordto.getArtswithPsychology();
			}
			else if(_SSRVO.getStream().trim().equalsIgnoreCase(ApplicationConstants.STREAM.GENERALARTS.getName()))
			{
				code=riasecStreamSelectordto.getGeneralArts();
			}
			if(code==1)
			{
				_SSRVO.setCode(true);
			}
			else
			{
				_SSRVO.setCode(false);
			}
			
			if( (_SSRVO.isCode()==true && _SSRVO.getAbililtyOutcome().equalsIgnoreCase(ApplicationConstants.PRIORITY.HIGH.getName())) || 
					(_SSRVO.isCode()==true && _SSRVO.getAbililtyOutcome().equalsIgnoreCase(ApplicationConstants.PRIORITY.MEDIUM.getName())) )
			{
				_SSRVO.setStreamFitment(ApplicationConstants.PRIORITY.HIGH.getScore());
			}
			else if ( (_SSRVO.isCode()==true && _SSRVO.getAbililtyOutcome().equalsIgnoreCase(ApplicationConstants.PRIORITY.LOW.getName())) || 
					(_SSRVO.isCode()==false && _SSRVO.getAbililtyOutcome().equalsIgnoreCase(ApplicationConstants.PRIORITY.HIGH.getName())) )
			{
				_SSRVO.setStreamFitment(ApplicationConstants.PRIORITY.MEDIUM.getScore());
			}
			else if ( (_SSRVO.isCode()==false && _SSRVO.getAbililtyOutcome().equalsIgnoreCase(ApplicationConstants.PRIORITY.MEDIUM.getName())) || 
					(_SSRVO.isCode()==false && _SSRVO.getAbililtyOutcome().equalsIgnoreCase(ApplicationConstants.PRIORITY.LOW.getName())) )
			{
				_SSRVO.setStreamFitment(ApplicationConstants.PRIORITY.LOW.getScore());	
			}
			
			
		}	
		
	}
	
	public void getfitmentOccupation(String subriasecCode)
	{
		try
		{

			StreamSelectorOccupationDTO streamOccupationDTO = dao.getStreamSelectorOcc(subriasecCode);
			for(StreamSelectorResultVO _SSRVO : streamSelectorVOList)
			{
				if(_SSRVO.getStream().equalsIgnoreCase(ApplicationConstants.STREAM.SCIENCEWITHMATH.getName()))
				{
					_SSRVO.setOccupation(streamOccupationDTO.getSciencewithMath());
				}
				else if(_SSRVO.getStream().equalsIgnoreCase(ApplicationConstants.STREAM.SCIENCEWITHBIO.getName()))
				{
					_SSRVO.setOccupation(streamOccupationDTO.getSciencewithBio());
				}
				else if(_SSRVO.getStream().equalsIgnoreCase(ApplicationConstants.STREAM.SCIENCEWITHMATHDESIGN.getName()))
				{
					_SSRVO.setOccupation(streamOccupationDTO.getSciencewithMathDesign());
				}
				else if(_SSRVO.getStream().equalsIgnoreCase(ApplicationConstants.STREAM.COMMERCEWITHMATH.getName()))
				{
					_SSRVO.setOccupation(streamOccupationDTO.getCommercewithMath());
				}
				else if(_SSRVO.getStream().equalsIgnoreCase(ApplicationConstants.STREAM.COMMERCEWITHOUTMATH.getName()))
				{
					_SSRVO.setOccupation(streamOccupationDTO.getCommercewithoutMath());
				}
				else if(_SSRVO.getStream().equalsIgnoreCase(ApplicationConstants.STREAM.ARTSWITHLANGUAGES.getName()))
				{
					_SSRVO.setOccupation(streamOccupationDTO.getArtswithLanguages());
				}
				else if(_SSRVO.getStream().equalsIgnoreCase(ApplicationConstants.STREAM.ARTSWITHDESIGN.getName()))
				{
					_SSRVO.setOccupation(streamOccupationDTO.getArtswithDesign());
				}
				else if(_SSRVO.getStream().equalsIgnoreCase(ApplicationConstants.STREAM.ARTSWITHPSYCHOLOGY.getName()))
				{
					_SSRVO.setOccupation(streamOccupationDTO.getArtswithPsychology());
				}
				else if(_SSRVO.getStream().equalsIgnoreCase(ApplicationConstants.STREAM.GENERALARTS.getName()))
				{
					_SSRVO.setOccupation(streamOccupationDTO.getGeneralArts());
				}
				
				
				

			}
	
			
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

	}
	
	

}


class StreamSelectorPrioritySorter implements Comparator<StreamSelectorResultVO>
{
	private static final Logger	OUT	= LoggerFactory.getLogger(StreamSelectorPrioritySorter.class);
	public int compare(StreamSelectorResultVO s1, StreamSelectorResultVO s2)
	{		
		if(s1.getStreamFitment()== s2.getStreamFitment())
		{
			if(s1.getStreamPriority()==s2.getStreamPriority())
			{
//				return Integer.valueOf(s2.getStreamPriority()).compareTo(s1.getStreamPriority());
			}
			return Integer.valueOf(s1.getStreamPriority()).compareTo(s2.getStreamPriority());
		}
		return Integer.valueOf(s2.getStreamFitment()).compareTo(s1.getStreamFitment());
	}
}
