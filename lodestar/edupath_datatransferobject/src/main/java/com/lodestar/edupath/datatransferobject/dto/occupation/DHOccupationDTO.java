package com.lodestar.edupath.datatransferobject.dto.occupation;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class DHOccupationDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private String				name;

	private String				rollOverContent;
	private String				description;
	private byte[]				image;

	private boolean				isActive;

	// non table coulumn
	private int					industryId;
	private String				industryName;

	private int					pathWayId;
	private String				pathWayName;

	private List<IndustryDTO>	industries;

	private int					entranceExamId;
	private int					integratedCourseId;

	private String				requiredRAISEC;
	private String				requiredAbility;
	private int					fitment;
	private String				occupationList;
	
	//BEGIN Sasedeve Added to set new triffic light by Mrutyunjaya on date 22-02-2017
	private int					newfitment;
	
	public int getNewfitment() {
		return newfitment;
	}

	public void setNewfitment(int newfitment) {
		this.newfitment = newfitment;
	}
	
	
	//END Sasedeve Added to set new triffic light by Mrutyunjaya on date 22-02-2017
	public int getId()
	{
		return id;
	}
	
	
	
	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRollOverContent()
	{
		return rollOverContent;
	}

	public void setRollOverContent(String rollOverContent)
	{
		this.rollOverContent = rollOverContent;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getIndustryId()
	{
		return industryId;
	}

	public void setIndustryId(int industryId)
	{
		this.industryId = industryId;
	}

	public int getPathWayId()
	{
		return pathWayId;
	}

	public void setPathWayId(int pathWayId)
	{
		this.pathWayId = pathWayId;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public String getIndustryName()
	{
		return industryName;
	}

	public void setIndustryName(String industryName)
	{
		this.industryName = industryName;
	}

	public long getLongId()
	{
		return id;
	}

	public byte[] getImage()
	{
		return image;
	}

	public void setImage(byte[] image)
	{
		this.image = image;
	}

	public String getBase64img()
	{
		String base64String = null;
		if (image != null)
		{
			try
			{
				base64String = Base64.encodeBase64String(image);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return base64String;
	}

	public String getPathWayName()
	{
		return pathWayName;
	}

	public void setPathWayName(String pathWayName)
	{
		this.pathWayName = pathWayName;
	}

	public List<IndustryDTO> getIndustries()
	{
		return industries;
	}

	public void setIndustries(List<IndustryDTO> industries)
	{
		this.industries = industries;
	}

	public int getEntranceExamId()
	{
		return entranceExamId;
	}

	public void setEntranceExamId(int entranceExamId)
	{
		this.entranceExamId = entranceExamId;
	}

	public int getIntegratedCourseId()
	{
		return integratedCourseId;
	}

	public void setIntegratedCourseId(int integratedCourseId)
	{
		this.integratedCourseId = integratedCourseId;
	}

	public String getRequiredRAISEC()
	{
		return requiredRAISEC;
	}

	public void setRequiredRAISEC(String requiredRAISEC)
	{
		this.requiredRAISEC = requiredRAISEC;
	}

	public String getRequiredAbility()
	{
		return requiredAbility;
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
	
	public void setRequiredAbility(String requiredAbility)
	{
		this.requiredAbility = requiredAbility;
	}

	public int getFitment()
	{
		return fitment;
	}

	public void setFitment(int fitment)
	{
		this.fitment = fitment;
	}

	public String getOccupationList() {
		return occupationList;
	}

	public void setOccupationList(String occupationList) {
		this.occupationList = occupationList;
	}

	@Override
	public String toString() {
		return "DHOccupationDTO [id=" + id + ", name=" + name + ", rollOverContent=" + rollOverContent
				+ ", description=" + description + ", image=" + Arrays.toString(image) + ", isActive=" + isActive
				+ ", industryId=" + industryId + ", industryName=" + industryName + ", pathWayId=" + pathWayId
				+ ", pathWayName=" + pathWayName + ", industries=" + industries + ", entranceExamId=" + entranceExamId
				+ ", integratedCourseId=" + integratedCourseId + ", requiredRAISEC=" + requiredRAISEC
				+ ", requiredAbility=" + requiredAbility + ", fitment=" + fitment + ", occupationList=" + occupationList
				+ ", newfitment=" + newfitment + "]";
	}

	

//	@Override
//	public String toString() {
//		return "OccupationDTO [id=" + id + ", name=" + name + ", fitment=" + fitment + ", newfitment=" + newfitment
//				+ ", requiredAbility=" + requiredAbility + ", requiredRAISEC=" + requiredRAISEC + "]";
//	}

	/*@Override
	public String toString() {
		return "OccupationDTO [id=" + id + ", name=" + name + ", requiredAbility=" + requiredAbility + ", fitment="
				+ fitment + ", newfitment=" + newfitment + ", requiredRAISEC=" + requiredRAISEC + ", rollOverContent="
				+ rollOverContent + ", description=" + description + ", image=" + Arrays.toString(image) + ", isActive="
				+ isActive + ", industryId=" + industryId + ", industryName=" + industryName + ", pathWayId="
				+ pathWayId + ", pathWayName=" + pathWayName + ", industries=" + industries + ", entranceExamId="
				+ entranceExamId + ", integratedCourseId=" + integratedCourseId + "]";
	}*/
	
	
}
