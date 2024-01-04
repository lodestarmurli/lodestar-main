package com.lodestar.edupath.datatransferobject.dto.student;

import java.util.Set;

import org.apache.commons.codec.binary.Base64;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class ShortListDTO implements IModel
{

	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					studentId;
	private Integer				industryId;
	private Integer				occupationId;
	private Integer				occIndustryId;
	private Integer				orderNo;
	private boolean				isSystemRecommended;

	// non table data
	private String				industryName;
	private String				occupationName;
	
	
	private byte[]				occupationImage;
	
	//BEGIN Sasedeve Added by Mrutyunjaya on Date 10-05-2017
		private String				requiredRAISEC;
		private String				requiredAbility;
		private int					fitment;
		
		
		private int					newfitment;
		
		//END Sasedeve Added by Mrutyunjaya on Date 10-05-2017
		
		//BEGIN Sasedeve Added by Mrutyunjaya on Date 20-05-2017
		private int                 fitmentforstudent;
	
	
	
	public int getFitmentforstudent() {
			return fitmentforstudent;
		}

		public void setFitmentforstudent(int fitmentforstudent) {
			this.fitmentforstudent = fitmentforstudent;
		}
		
		
		//END Sasedeve Added by Mrutyunjaya on Date 20-05-2017
		
		//BEGIN Sasedeve Added by Mrutyunjaya on Date 15-07-2017
		
		private String                 improveability;
		
		

	public String getImproveability() {
			return improveability;
		}

		public void setImproveability(String improveability) {
			this.improveability = improveability;
		}
		//END Sasedeve Added by Mrutyunjaya on Date 15-07-2017	
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
	
	
	
	
	//END Sasedeve Added by Mrutyunjaya on Date 10-05-2017
	private Set<Integer>        idsList;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
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

	public Integer getOrderNo()
	{
		return orderNo;
	}

	public void setOrderNo(Integer orderNo)
	{
		this.orderNo = orderNo;
	}

	public boolean isSystemRecommended()
	{
		return isSystemRecommended;
	}

	public void setSystemRecommended(boolean isSystemRecommended)
	{
		this.isSystemRecommended = isSystemRecommended;
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

	public byte[] getOccupationImage()
	{
		return occupationImage;
	}

	public void setOccupationImage(byte[] occupationImage)
	{
		this.occupationImage = occupationImage;
	}

	@Override
	public String toString()
	{
		return "ShortListDTO [id=" + id + ", studentId=" + studentId + ", industryId=" + industryId + ", occupationId=" + occupationId + ", orderNo=" + orderNo
				+ ", isSystemRecommended=" + isSystemRecommended + ", industryName=" + industryName + ", occupationName=" + occupationName + "]";
	}

	public String getBase64img()
	{
		String base64String = null;
		if (occupationImage != null)
		{
			try
			{
				base64String = Base64.encodeBase64String(occupationImage);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return base64String;
	}

	public Integer getOccIndustryId()
	{
		return occIndustryId;
	}

	public void setOccIndustryId(Integer occIndustryId)
	{
		this.occIndustryId = occIndustryId;
	}

	public Set<Integer> getIdsList()
	{
		return idsList;
	}

	public void setIdsList(Set<Integer> idsList)
	{
		this.idsList = idsList;
	}

}
