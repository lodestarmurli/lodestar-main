package com.lodestar.edupath.dataaccessobject.dao.newscoringlogic;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.datatransferobject.dto.elective.EduPathShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.ClusterDTO;
import com.lodestar.edupath.datatransferobject.dto.student.ShortListDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.FitMent;



public class NewScoringLogicForTrafficLight {
	private static final Logger							OUT						= LoggerFactory.getLogger(NewScoringLogicForTrafficLight.class);
	
	
	public OccupationDTO GetNewTrafficLight(OccupationDTO occupationDTO, String PersonalityCod)
	{
		
		OUT.debug("Inside GetNewTrafficLight method of NewScoringLogicForTrafficLight class occupationDTO:{}, PersonalityCod:{}",occupationDTO,PersonalityCod);
		
		//HIG===>Green
		//AVG===>Amber
		//A_AVG===>Yellow
		//LOW====>Red
		int fitment1 = FitMent.LOW.getScore();
		
		//OUT.debug("Occupation===========>"+occupationDTO.getRequiredRAISEC());
		//OUT.debug("PersonalityCod===========>"+PersonalityCod);
		try
		{
			if(!PersonalityCod.equals(null) && !PersonalityCod.equals(""))
			{
				
				String RAISECChar=PersonalityCod.replace(",","");
				
				//OUT.debug("PersonalityCod 1===========>"+RAISECChar);
				
				
				
				String OccupationRAISEC=occupationDTO.getRequiredRAISEC();
				String[] OccupationRAISECSplit=OccupationRAISEC.split(",");
				
				
				
				String Position1=null,Position2=null,Position3=null,Position1String="",Position2String="",Position3String="";
				
				int FitmentCount[]=new int[OccupationRAISECSplit.length];
				int FitmentCountValue=0;
				
				for(String OccupationRAISECCode:OccupationRAISECSplit)
				{
					
					
					int PositionString=0;
					
					//OUT.debug("PositionString===========>"+PositionString);
					
					for(char OccupationRAISECCodechar:OccupationRAISECCode.trim().toCharArray())
					{
						if(PositionString==0)
						{
							Position1String=Position1String+OccupationRAISECCodechar;
						}
						else if(PositionString==1)
						{
							Position2String=Position2String+OccupationRAISECCodechar;
						}
						else if(PositionString==2)
						{
							Position3String=Position3String+OccupationRAISECCodechar;
						}
						PositionString++;
					}
//				}
				Position1String=Position1String.trim();
				Position2String=Position2String.trim();
			    Position3String=Position3String.trim();
				//OUT.debug("Position1String==============>"+Position1String);
				//OUT.debug("Position2String==============>"+Position2String);
				//OUT.debug("Position3String==============>"+Position3String);
//				String OccupationRAISECCode=OccupationRAISECSplit[0];
				
			//	OUT.debug("1st OccupationRAISECCode===========>"+OccupationRAISECCode);
//				for(String OccupationRAISECCode: OccupationRAISECSplit)
//				{
				int PersonalityPosition=0;
					
					for(char raisec: RAISECChar.trim().toCharArray())
					{
						PersonalityPosition++;
						int index1=Position1String.indexOf(raisec);
						int index2=Position2String.indexOf(raisec);
						int index3=Position3String.indexOf(raisec);
						if(PersonalityPosition==1)
						{
							if(index1!=-1)
							{
								Position1="High";
							}
							else if(index2!=-1)
							{
								Position1="Average";
							}
							else if(index3!=-1)
							{
								Position1="Low";
							}
						}
						else if(PersonalityPosition==2)
						{
							if(index1!=-1)
							{
								Position2="Average";
							}
							else if(index2!=-1)
							{
								Position2="High";
							}
							else if(index3!=-1)
							{
								Position2="Low";
							}
						}
						else if(PersonalityPosition==3)
						{
							if(index1!=-1)
							{
								Position3="Low";
							}
							else if(index2!=-1)
							{
								Position3="Average";
							}
							else if(index3!=-1)
							{
								Position3="High";
							}
						}
					}
//				}
					
					//OUT.debug("Position1==============>"+Position1);
					//OUT.debug("Position2==============>"+Position2);
					//OUT.debug("Position3==============>"+Position3);
					//OUT.debug("Occupation Name==============>"+occupationDTO.getName());
					
				    if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.HIG.getScore(); //Green
					}
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("High")) && Position3==null)
				    {
				    	fitment1 = FitMent.A_AVG.getScore(); //Yellow
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("Average")) && (Position3!=null && Position3.equals("Low")))
					{
						fitment1 = FitMent.A_AVG.getScore(); //Yellow
					}
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("Average")) && Position3==null)
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null &&  Position2.equals("High")) && Position3==null)
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && (Position3!=null &&  Position3.equals("Average")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && (Position3!=null &&  Position3.equals("Low")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && Position3==null)
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null && Position1.equals("Average")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("Low")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("Average")) && (Position2!=null &&  Position2.equals("High")) && Position3==null)
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null && Position1.equals("Average")) && (Position2!=null && Position2.equals("Low")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Average")) && (Position2!=null && Position2.equals("Low")) && Position3==null)
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("Average")) && Position2==null && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("Average")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("High")) && Position3==null)
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("Average")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("Average")) && (Position3!=null && Position3.equals("Average")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("Low")) && Position2==null && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if(Position1==null && (Position2!=null && Position2.equals("High")) && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    
				    FitmentCount[FitmentCountValue]=fitment1;
				    FitmentCountValue++;
				    Position1=null;
				    Position2=null;
				    Position3=null;
				    Position1String="";
				    Position2String="";
				    Position3String="";
				    
				}
					
				Arrays.sort(FitmentCount);
				int max=FitmentCount[FitmentCount.length -1];
				if(max==4)
				{
					fitment1 = FitMent.HIG.getScore(); //Green
				}
				else if(max==3)
				{
					fitment1 = FitMent.A_AVG.getScore(); //Yellow
				}
				else if(max==2)
				{
					fitment1 = FitMent.AVG.getScore(); //Amber
				}
				else
				{
					fitment1 = FitMent.LOW.getScore();// Red
				}
				
				OUT.debug("Array List OF Fitment==============>"+Arrays.toString(FitmentCount));
				OUT.debug("Selected Fitment==============>"+fitment1);
			}
		}
		catch(Exception ex)
		{
			OUT.error(ApplicationConstants.EXCEPTION, ex);
		}
		
		
		
		occupationDTO.setNewfitment(fitment1);
		return occupationDTO;
	}
	
	public ClusterDTO GetNewTrafficLightForCluster(ClusterDTO occupationDTO, String PersonalityCod)
	{
		
		OUT.debug("Inside GetNewTrafficLight method of NewScoringLogicForTrafficLight class occupationDTO:{}, PersonalityCod:{}",occupationDTO,PersonalityCod);
		
		//HIG===>Green
		//AVG===>Amber
		//A_AVG===>Yellow
		//LOW====>Red
		int fitment1 = FitMent.LOW.getScore();
		
		//OUT.debug("Occupation===========>"+occupationDTO.getRequiredRAISEC());
		//OUT.debug("PersonalityCod===========>"+PersonalityCod);
		try
		{
			if(!PersonalityCod.equals(null) && !PersonalityCod.equals(""))
			{
				
				String RAISECChar=PersonalityCod.replace(",","");
				
				//OUT.debug("PersonalityCod 1===========>"+RAISECChar);
				
				
				
				String OccupationRAISEC=occupationDTO.getRequiredRAISEC();
				String[] OccupationRAISECSplit=OccupationRAISEC.split(",");
				
				
				
				String Position1=null,Position2=null,Position3=null,Position1String="",Position2String="",Position3String="";
				
				int FitmentCount[]=new int[OccupationRAISECSplit.length];
				int FitmentCountValue=0;
				
				for(String OccupationRAISECCode:OccupationRAISECSplit)
				{
					
					
					int PositionString=0;
					
					//OUT.debug("PositionString===========>"+PositionString);
					
					for(char OccupationRAISECCodechar:OccupationRAISECCode.trim().toCharArray())
					{
						if(PositionString==0)
						{
							Position1String=Position1String+OccupationRAISECCodechar;
						}
						else if(PositionString==1)
						{
							Position2String=Position2String+OccupationRAISECCodechar;
						}
						else if(PositionString==2)
						{
							Position3String=Position3String+OccupationRAISECCodechar;
						}
						PositionString++;
					}
//				}
				Position1String=Position1String.trim();
				Position2String=Position2String.trim();
			    Position3String=Position3String.trim();
				//OUT.debug("Position1String==============>"+Position1String);
				//OUT.debug("Position2String==============>"+Position2String);
				//OUT.debug("Position3String==============>"+Position3String);
//				String OccupationRAISECCode=OccupationRAISECSplit[0];
				
			//	OUT.debug("1st OccupationRAISECCode===========>"+OccupationRAISECCode);
//				for(String OccupationRAISECCode: OccupationRAISECSplit)
//				{
				int PersonalityPosition=0;
					
					for(char raisec: RAISECChar.trim().toCharArray())
					{
						PersonalityPosition++;
						int index1=Position1String.indexOf(raisec);
						int index2=Position2String.indexOf(raisec);
						int index3=Position3String.indexOf(raisec);
						if(PersonalityPosition==1)
						{
							if(index1!=-1)
							{
								Position1="High";
							}
							else if(index2!=-1)
							{
								Position1="Average";
							}
							else if(index3!=-1)
							{
								Position1="Low";
							}
						}
						else if(PersonalityPosition==2)
						{
							if(index1!=-1)
							{
								Position2="Average";
							}
							else if(index2!=-1)
							{
								Position2="High";
							}
							else if(index3!=-1)
							{
								Position2="Low";
							}
						}
						else if(PersonalityPosition==3)
						{
							if(index1!=-1)
							{
								Position3="Low";
							}
							else if(index2!=-1)
							{
								Position3="Average";
							}
							else if(index3!=-1)
							{
								Position3="High";
							}
						}
					}
//				}
					
					//OUT.debug("Position1==============>"+Position1);
					//OUT.debug("Position2==============>"+Position2);
					//OUT.debug("Position3==============>"+Position3);
					//OUT.debug("Occupation Name==============>"+occupationDTO.getName());
					
				    if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.HIG.getScore(); //Green
					}
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("High")) && Position3==null)
				    {
				    	fitment1 = FitMent.A_AVG.getScore(); //Yellow
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("Average")) && (Position3!=null && Position3.equals("Low")))
					{
						fitment1 = FitMent.A_AVG.getScore(); //Yellow
					}
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("Average")) && Position3==null)
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null &&  Position2.equals("High")) && Position3==null)
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && (Position3!=null &&  Position3.equals("Average")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && (Position3!=null &&  Position3.equals("Low")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && Position3==null)
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null && Position1.equals("Average")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("Low")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("Average")) && (Position2!=null &&  Position2.equals("High")) && Position3==null)
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null && Position1.equals("Average")) && (Position2!=null && Position2.equals("Low")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Average")) && (Position2!=null && Position2.equals("Low")) && Position3==null)
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("Average")) && Position2==null && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("Average")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("High")) && Position3==null)
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("Average")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("Average")) && (Position3!=null && Position3.equals("Average")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("Low")) && Position2==null && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if(Position1==null && (Position2!=null && Position2.equals("High")) && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    
				    FitmentCount[FitmentCountValue]=fitment1;
				    FitmentCountValue++;
				    Position1=null;
				    Position2=null;
				    Position3=null;
				    Position1String="";
				    Position2String="";
				    Position3String="";
				    
				}
					
				Arrays.sort(FitmentCount);
				int max=FitmentCount[FitmentCount.length -1];
				if(max==4)
				{
					fitment1 = FitMent.HIG.getScore(); //Green
				}
				else if(max==3)
				{
					fitment1 = FitMent.A_AVG.getScore(); //Yellow
				}
				else if(max==2)
				{
					fitment1 = FitMent.AVG.getScore(); //Amber
				}
				else
				{
					fitment1 = FitMent.LOW.getScore();// Red
				}
				
				OUT.debug("Array List OF Fitment==============>"+Arrays.toString(FitmentCount));
				OUT.debug("Selected Fitment==============>"+fitment1);
			}
		}
		catch(Exception ex)
		{
			OUT.error(ApplicationConstants.EXCEPTION, ex);
		}
		
		
		
		occupationDTO.setNewfitment(fitment1);
		return occupationDTO;
	}
	
	public ShortListDTO GetNewTrafficLightForShortList(ShortListDTO occupationDTO, String PersonalityCod)
	{
		
		OUT.debug("Inside GetNewTrafficLightForShortList method of NewScoringLogicForTrafficLight class");
		
		//HIG===>Green
		//AVG===>Amber
		//A_AVG===>Yellow
		//LOW====>Red
		int fitment1 = FitMent.LOW.getScore();
		
		//OUT.debug("Occupation===========>"+occupationDTO.getRequiredRAISEC());
		//OUT.debug("PersonalityCod===========>"+PersonalityCod);
		try
		{
			if(!PersonalityCod.equals(null) && !PersonalityCod.equals(""))
			{
				
				String RAISECChar=PersonalityCod.replace(",","");
				
			//	OUT.debug("PersonalityCod 1===========>"+RAISECChar);
				
				
				
				String OccupationRAISEC=occupationDTO.getRequiredRAISEC();
				
				String[] OccupationRAISECSplit=OccupationRAISEC.split(",");
				
				String Position1=null,Position2=null,Position3=null,Position1String="",Position2String="",Position3String="";
				
				int FitmentCount[]=new int[OccupationRAISECSplit.length];
				int FitmentCountValue=0;
				for(String OccupationRAISECCode:OccupationRAISECSplit)
				{
					int PositionString=0;
					for(char OccupationRAISECCodechar:OccupationRAISECCode.trim().toCharArray())
					{
						if(PositionString==0)
						{
							Position1String=Position1String+OccupationRAISECCodechar;
						}
						else if(PositionString==1)
						{
							Position2String=Position2String+OccupationRAISECCodechar;
						}
						else if(PositionString==2)
						{
							Position3String=Position3String+OccupationRAISECCodechar;
						}
						PositionString++;
					}
//				}
				Position1String=Position1String.trim();
				Position2String=Position2String.trim();
			    Position3String=Position3String.trim();
				//OUT.debug("Position1String==============>"+Position1String);
				//OUT.debug("Position2String==============>"+Position2String);
				//OUT.debug("Position3String==============>"+Position3String);
//				String OccupationRAISECCode=OccupationRAISECSplit[0];
				
			//	OUT.debug("1st OccupationRAISECCode===========>"+OccupationRAISECCode);
//				for(String OccupationRAISECCode: OccupationRAISECSplit)
//				{
				int PersonalityPosition=0;
					for(char raisec: RAISECChar.trim().toCharArray())
					{
						PersonalityPosition++;
						int index1=Position1String.indexOf(raisec);
						int index2=Position2String.indexOf(raisec);
						int index3=Position3String.indexOf(raisec);
						
						if(PersonalityPosition==1)
						{
							if(index1!=-1)
							{
								Position1="High";
							}
							else if(index2!=-1)
							{
								Position1="Average";
							}
							else if(index3!=-1)
							{
								Position1="Low";
							}
						}
						else if(PersonalityPosition==2)
						{
							if(index1!=-1)
							{
								Position2="Average";
							}
							else if(index2!=-1)
							{
								Position2="High";
							}
							else if(index3!=-1)
							{
								Position2="Low";
							}
						}
						else if(PersonalityPosition==3)
						{
							if(index1!=-1)
							{
								Position3="Low";
							}
							else if(index2!=-1)
							{
								Position3="Average";
							}
							else if(index3!=-1)
							{
								Position3="High";
							}
						}		
					}
					
//				}
					
					//OUT.debug("Position1==============>"+Position1);
					//OUT.debug("Position2==============>"+Position2);
					//OUT.debug("Position3==============>"+Position3);
					//OUT.debug("Occupation Name==============>"+occupationDTO.getName());
					
				    if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.HIG.getScore(); //Green
					}
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("High")) && Position3==null)
				    {
				    	fitment1 = FitMent.A_AVG.getScore(); //Yellow
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("Average")) && (Position3!=null && Position3.equals("Low")))
					{
						fitment1 = FitMent.A_AVG.getScore(); //Yellow
					}
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("Average")) && Position3==null)
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null &&  Position2.equals("High")) && Position3==null)
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && (Position3!=null &&  Position3.equals("Average")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && (Position3!=null &&  Position3.equals("Low")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && Position3==null)
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null && Position1.equals("Average")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("Low")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("Average")) && (Position2!=null &&  Position2.equals("High")) && Position3==null)
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null && Position1.equals("Average")) && (Position2!=null && Position2.equals("Low")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Average")) && (Position2!=null && Position2.equals("Low")) && Position3==null)
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("Average")) && Position2==null && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("Average")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("High")) && Position3==null)
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("Average")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("Average")) && (Position3!=null && Position3.equals("Average")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("Low")) && Position2==null && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if(Position1==null && (Position2!=null && Position2.equals("High")) && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    
				    
				    FitmentCount[FitmentCountValue]=fitment1;
				    FitmentCountValue++;
				    
				    Position1=null;
				    Position2=null;
				    Position3=null;
				    Position1String="";
				    Position2String="";
				    Position3String="";
				    
				}
					
				Arrays.sort(FitmentCount);
				int max=FitmentCount[FitmentCount.length -1];
				
				if(max==4)
				{
					fitment1 = FitMent.HIG.getScore(); //Green
				}
				else if(max==3)
				{
					fitment1 = FitMent.A_AVG.getScore(); //Yellow
				}
				else if(max==2)
				{
					fitment1 = FitMent.AVG.getScore(); //Amber
				}
				else
				{
					fitment1 = FitMent.LOW.getScore();// Red
				}
				
				OUT.debug("Array List OF Fitment==============>"+Arrays.toString(FitmentCount));
				OUT.debug("Selected Fitment==============>"+fitment1);
			}
		}
		catch(Exception ex)
		{
			OUT.error(ApplicationConstants.EXCEPTION, ex);
		}
		
		
		
		occupationDTO.setNewfitment(fitment1);
		
		return occupationDTO;
	}
	
	
	public EduPathShortListDTO GetNewTrafficLightForEdupathShortList(EduPathShortListDTO occupationDTO, String PersonalityCod)
	{
		
		OUT.debug("Inside GetNewTrafficLight method of NewScoringLogicForTrafficLight class");
		
		//HIG===>Green
		//AVG===>Amber
		//A_AVG===>Yellow
		//LOW====>Red
		int fitment1 = FitMent.LOW.getScore();
		
		//OUT.debug("Occupation===========>"+occupationDTO.getRequiredRAISEC());
		//OUT.debug("PersonalityCod===========>"+PersonalityCod);
		try
		{
			if(!PersonalityCod.equals(null) && !PersonalityCod.equals(""))
			{
				
				String RAISECChar=PersonalityCod.replace(",","");
				
			//	OUT.debug("PersonalityCod 1===========>"+RAISECChar);
				
				
				
				String OccupationRAISEC=occupationDTO.getRequiredRAISEC();
				
				String[] OccupationRAISECSplit=OccupationRAISEC.split(",");
				
				String Position1=null,Position2=null,Position3=null,Position1String="",Position2String="",Position3String="";
				
				int FitmentCount[]=new int[OccupationRAISECSplit.length];
				int FitmentCountValue=0;
				for(String OccupationRAISECCode:OccupationRAISECSplit)
				{
					int PositionString=0;
					for(char OccupationRAISECCodechar:OccupationRAISECCode.trim().toCharArray())
					{
						if(PositionString==0)
						{
							Position1String=Position1String+OccupationRAISECCodechar;
						}
						else if(PositionString==1)
						{
							Position2String=Position2String+OccupationRAISECCodechar;
						}
						else if(PositionString==2)
						{
							Position3String=Position3String+OccupationRAISECCodechar;
						}
						PositionString++;
					}
//				}
				Position1String=Position1String.trim();
				Position2String=Position2String.trim();
			    Position3String=Position3String.trim();
				//OUT.debug("Position1String==============>"+Position1String);
				//OUT.debug("Position2String==============>"+Position2String);
				//OUT.debug("Position3String==============>"+Position3String);
//				String OccupationRAISECCode=OccupationRAISECSplit[0];
				
			//	OUT.debug("1st OccupationRAISECCode===========>"+OccupationRAISECCode);
//				for(String OccupationRAISECCode: OccupationRAISECSplit)
//				{
				int PersonalityPosition=0;
					for(char raisec: RAISECChar.trim().toCharArray())
					{
						PersonalityPosition++;
						int index1=Position1String.indexOf(raisec);
						int index2=Position2String.indexOf(raisec);
						int index3=Position3String.indexOf(raisec);
						
						if(PersonalityPosition==1)
						{
							if(index1!=-1)
							{
								Position1="High";
							}
							else if(index2!=-1)
							{
								Position1="Average";
							}
							else if(index3!=-1)
							{
								Position1="Low";
							}
						}
						else if(PersonalityPosition==2)
						{
							if(index1!=-1)
							{
								Position2="Average";
							}
							else if(index2!=-1)
							{
								Position2="High";
							}
							else if(index3!=-1)
							{
								Position2="Low";
							}
						}
						else if(PersonalityPosition==3)
						{
							if(index1!=-1)
							{
								Position3="Low";
							}
							else if(index2!=-1)
							{
								Position3="Average";
							}
							else if(index3!=-1)
							{
								Position3="High";
							}
						}		
					}
					
//				}
					
					//OUT.debug("Position1==============>"+Position1);
					//OUT.debug("Position2==============>"+Position2);
					//OUT.debug("Position3==============>"+Position3);
					//OUT.debug("Occupation Name==============>"+occupationDTO.getName());
					
				    if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.HIG.getScore(); //Green
					}
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("High")) && Position3==null)
				    {
				    	fitment1 = FitMent.A_AVG.getScore(); //Yellow
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("Average")) && (Position3!=null && Position3.equals("Low")))
					{
						fitment1 = FitMent.A_AVG.getScore(); //Yellow
					}
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null && Position2.equals("Average")) && Position3==null)
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("High")) && (Position2!=null &&  Position2.equals("High")) && Position3==null)
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && (Position3!=null &&  Position3.equals("Average")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && (Position3!=null &&  Position3.equals("Low")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null &&  Position1.equals("High")) && Position2==null && Position3==null)
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null && Position1.equals("Average")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("Low")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("Average")) && (Position2!=null &&  Position2.equals("High")) && Position3==null)
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null && Position1.equals("Average")) && (Position2!=null && Position2.equals("Low")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Average")) && (Position2!=null && Position2.equals("Low")) && Position3==null)
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("Average")) && Position2==null && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("High")) && (Position3!=null && Position3.equals("Average")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("High")) && Position3==null)
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("Average")) && (Position3!=null && Position3.equals("High")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null && Position1.equals("Low")) && (Position2!=null && Position2.equals("Average")) && (Position3!=null && Position3.equals("Average")))
					{
						fitment1 = FitMent.AVG.getScore(); //Amber
					}
				    else if((Position1!=null &&  Position1.equals("Low")) && Position2==null && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    else if(Position1==null && (Position2!=null && Position2.equals("High")) && (Position3!=null &&  Position3.equals("High")))
				    {
				    	fitment1 = FitMent.AVG.getScore(); //Amber
				    }
				    
				    
				    FitmentCount[FitmentCountValue]=fitment1;
				    FitmentCountValue++;
				    
				    Position1=null;
				    Position2=null;
				    Position3=null;
				    Position1String="";
				    Position2String="";
				    Position3String="";
				    
				}
					
				Arrays.sort(FitmentCount);
				int max=FitmentCount[FitmentCount.length -1];
				
				if(max==4)
				{
					fitment1 = FitMent.HIG.getScore(); //Green
				}
				else if(max==3)
				{
					fitment1 = FitMent.A_AVG.getScore(); //Yellow
				}
				else if(max==2)
				{
					fitment1 = FitMent.AVG.getScore(); //Amber
				}
				else
				{
					fitment1 = FitMent.LOW.getScore();// Red
				}
				
				OUT.debug("Array List OF Fitment==============>"+Arrays.toString(FitmentCount));
				OUT.debug("Selected Fitment==============>"+fitment1);
			}
		}
		catch(Exception ex)
		{
			OUT.error(ApplicationConstants.EXCEPTION, ex);
		}
		
		
		
		occupationDTO.setNewfitment(fitment1);
		
		return occupationDTO;
	}
	
	
}
