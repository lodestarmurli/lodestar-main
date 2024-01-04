package com.lodestar.edupath.datatransferobject.dto.elective;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class EduPathShortListDTO implements IModel
{
	private static final long	serialVersionUID	= -1137683288594995071L;
	private int					id;
	private Integer				industryId;
	private Integer				occupationId;
	private Integer				puStreamId;
	private Integer				priority;
	private Integer				studentId;
	private Integer				occIndustryId;
	private String				degreeStream;
	private Integer				edupathId;

	// requiredfield
	private String				industryName;
	private String				occupationName;
	private String				streamName;
	private String				combinationName;
	private Integer				combinationId;
	private String				edulevelName;
	private Integer				noOfCollege;

	private String				pgStream;
	
	private String pgSubject;
	private String degreeSubject;
	
	//BEGIN Sasedeve Added by Mrutyunjaya on Date 25-05-2017
			private String				requiredRAISEC;
			private String				requiredAbility;
			private int					fitment;
			
			
			private int					newfitment;
			
			
			
			
			
			public String getRequiredRAISEC() {
				return requiredRAISEC;
			}

			public void setRequiredRAISEC(String requiredRAISEC) {
				this.requiredRAISEC = requiredRAISEC;
			}

			public String getRequiredAbility() {
				return requiredAbility;
			}

			public void setRequiredAbility(String requiredAbility) {
				this.requiredAbility = requiredAbility;
			}

			public int getFitment() {
				return fitment;
			}

			public void setFitment(int fitment) {
				this.fitment = fitment;
			}

			public int getNewfitment() {
				return newfitment;
			}

			public void setNewfitment(int newfitment) {
				this.newfitment = newfitment;
			}	
			
			
			
			
			public int getRequiredAbilityCount()
			{
				int count = 0;
				if(requiredAbility != null && !requiredAbility.trim().isEmpty())
				{
					count = requiredAbility.split(",").length;
				}
				return count;
			}	
			
			
			
			
			
			
			
			
			
  //END Sasedeve Added by Mrutyunjaya on Date 25-05-2017
	
	
	
	
	
	
	
	

	public int getId()
	{
		return id;
	}



	public void setId(int id)
	{
		this.id = id;
	}

	public Integer getIndustryId()
	{
		return industryId;
	}

	public void setIndustryId(Integer industryId)
	{
		this.industryId = industryId;
	}

	public Integer getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(Integer occupationId)
	{
		this.occupationId = occupationId;
	}

	public Integer getPuStreamId()
	{
		return puStreamId;
	}

	public void setPuStreamId(Integer puStreamId)
	{
		this.puStreamId = puStreamId;
	}

	public Integer getPriority()
	{
		return priority;
	}

	public void setPriority(Integer priority)
	{
		this.priority = priority;
	}

	public Integer getStudentId()
	{
		return studentId;
	}

	public void setStudentId(Integer studentId)
	{
		this.studentId = studentId;
	}

	public String getIndustryName()
	{
		return industryName;
	}

	public void setIndustryName(String industryName)
	{
		this.industryName = industryName;
	}

	public String getOccupationName()
	{
		return occupationName;
	}

	public void setOccupationName(String occupationName)
	{
		this.occupationName = occupationName;
	}

	public Integer getOccIndustryId()
	{
		return occIndustryId;
	}

	public void setOccIndustryId(Integer occIndustryId)
	{
		this.occIndustryId = occIndustryId;
	}

	public String getStreamName()
	{
		return streamName;
	}

	public void setStreamName(String streamName)
	{
		this.streamName = streamName;
	}

	public String getCombinationName()
	{
		return combinationName;
	}

	public void setCombinationName(String combinationName)
	{
		this.combinationName = combinationName;
	}

	public Integer getCombinationId()
	{
		return combinationId;
	}

	public void setCombinationId(Integer combinationId)
	{
		this.combinationId = combinationId;
	}

	public Integer getNoOfCollege()
	{
		return noOfCollege;
	}

	public void setNoOfCollege(Integer noOfCollege)
	{
		this.noOfCollege = noOfCollege;
	}

	public String getEdulevelName()
	{
		return edulevelName;
	}

	public void setEdulevelName(String edulevelName)
	{
		this.edulevelName = edulevelName;
	}

	public String getDegreeStream()
	{
		return degreeStream;
	}

	public void setDegreeStream(String degreeStream)
	{
		this.degreeStream = degreeStream;
	}

	public Integer getEdupathId()
	{
		return edupathId;
	}

	public void setEdupathId(Integer edupathId)
	{
		this.edupathId = edupathId;
	}

	public String getPgStream()
	{
		return pgStream;
	}

	public void setPgStream(String pgStream)
	{
		this.pgStream = pgStream;
	}

	public String getPgSubject()
	{
		return pgSubject;
	}

	public void setPgSubject(String pgSubject)
	{
		this.pgSubject = pgSubject;
	}

	public String getDegreeSubject()
	{
		return degreeSubject;
	}

	public void setDegreeSubject(String degreeSubject)
	{
		this.degreeSubject = degreeSubject;
	}
	
}
