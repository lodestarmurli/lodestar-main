package com.lodestar.edupath.datatransferobject.dto.occupation;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientEdupathDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEdupathDTO;

public class OccupationDTO implements IModel
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
	
	//BEGIN Sasedeve Added to set new triffic light by Mrutyunjaya on date 22-02-2017
	private int					newfitment;
	
//by bharath
	private int					dhStream;
	private int 				dhPriority;
	private DHEdupathDTO		dheduDTO;
	private int 				engineeringPriority;
	private String 				favSubject;
	private int 				favSubPriority;
	private String 				engineeringDegree;
	private String 				engineeringDegreeDescription;
	private String 				engineeringDegreeSubjects;
	private String 				engineeringDegreeCareer;
	private String 				engineeringDegreeStudyArea;
	private String 				engineeringDegreeSkills;
	private String 				engineeringRelatedCourses;
	private boolean				isCluster;
	private String				clusterName;
	private int 				clusterId;
	private String				clusterDegree;
	private String				clusterEntExam;
	private int 				careerFitmentPriority;
	private ClientEdupathDTO		clienteduDTO;
	
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

	public int getDhStream() {
		return dhStream;
	}

	public void setDhStream(int dhStream) {
		this.dhStream = dhStream;
	}

	

	public int getDhPriority() {
		return dhPriority;
	}

	public void setDhPriority(int dhPriority) {
		this.dhPriority = dhPriority;
	}

	public DHEdupathDTO getDheduDTO() {
		return dheduDTO;
	}

	public void setDheduDTO(DHEdupathDTO dheduDTO) {
		this.dheduDTO = dheduDTO;
	}

	public int getEngineeringPriority() {
		return engineeringPriority;
	}

	public void setEngineeringPriority(int engineeringPriority) {
		this.engineeringPriority = engineeringPriority;
	}

	public String getEngineeringDegree() {
		return engineeringDegree;
	}

	public void setEngineeringDegree(String engineeringDegree) {
		this.engineeringDegree = engineeringDegree;
	}

	public String getFavSubject() {
		return favSubject;
	}

	public void setFavSubject(String favSubject) {
		this.favSubject = favSubject;
	}

	public int getFavSubPriority() {
		return favSubPriority;
	}

	public void setFavSubPriority(int favSubPriority) {
		this.favSubPriority = favSubPriority;
	}
	
	public String getAbilityfitment()
	{
		if(this.fitment==4)
		{
			return "Green";
		}
		else if(this.fitment==3)
		{
			return "Yellow";
		}
		else if(this.fitment==2)
		{
			return "Orange";
		}
		else if(this.fitment==1)
		{
			return "Red";
		}
		return "";
	}

	public String getInterestfitment()
	{
		if(this.newfitment==4)
		{
			return "Green";
		}
		else if(this.newfitment==3)
		{
			return "Yellow";
		}
		else if(this.newfitment==2)
		{
			return "Orange";
		}
		else if(this.newfitment==1)
		{
			return "Red";
		}
		return "";
	}
	
	
	
	public String getEngineeringDegreeDescription() {
		return engineeringDegreeDescription;
	}

//	public String getRequiredAbilityStr() {
//		return requiredAbilityStr;
//	}


	public void setEngineeringDegreeDescription(String engineeringDegreeDescription) {
		this.engineeringDegreeDescription = engineeringDegreeDescription;
	}

	public String getEngineeringDegreeSubjects() {
		return engineeringDegreeSubjects;
	}

	public void setEngineeringDegreeSubjects(String engineeringDegreeSubjects) {
		this.engineeringDegreeSubjects = engineeringDegreeSubjects;
	}

	public String getEngineeringDegreeCareer() {
		return engineeringDegreeCareer;
	}

	public void setEngineeringDegreeCareer(String engineeringDegreeCareer) {
		this.engineeringDegreeCareer = engineeringDegreeCareer;
	}

	public String getEngineeringDegreeStudyArea() {
		return engineeringDegreeStudyArea;
	}

	public void setEngineeringDegreeStudyArea(String engineeringDegreeStudyArea) {
		this.engineeringDegreeStudyArea = engineeringDegreeStudyArea;
	}

	public String getEngineeringDegreeSkills() {
		return engineeringDegreeSkills;
	}

	public void setEngineeringDegreeSkills(String engineeringDegreeSkills) {
		this.engineeringDegreeSkills = engineeringDegreeSkills;
	}

	public String getEngineeringRelatedCourses() {
		return engineeringRelatedCourses;
	}

	public void setEngineeringRelatedCourses(String engineeringRelatedCourses) {
		this.engineeringRelatedCourses = engineeringRelatedCourses;
	}
 
	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	

	@Override
	public String toString() {
		return "OccupationDTO [id=" + id + ", name=" + name + ", rollOverContent=" + rollOverContent + ", description="
				+ description + ", isActive=" + isActive + ", industryId=" + industryId + ", industryName="
				+ industryName + ", pathWayId=" + pathWayId + ", pathWayName=" + pathWayName + ", industries="
				+ industries + ", entranceExamId=" + entranceExamId + ", integratedCourseId=" + integratedCourseId
				+ ", requiredRAISEC=" + requiredRAISEC + ", requiredAbility=" + requiredAbility + ", fitment=" + fitment
				+ ", newfitment=" + newfitment + ", dhStream=" + dhStream + ", dhPriority=" + dhPriority + ", dheduDTO="
				+ dheduDTO + ", engineeringPriority=" + engineeringPriority + ", favSubject=" + favSubject
				+ ", favSubPriority=" + favSubPriority + ", engineeringDegree=" + engineeringDegree
				+ ", engineeringDegreeDescription=" + engineeringDegreeDescription + ", engineeringDegreeSubjects="
				+ engineeringDegreeSubjects + ", engineeringDegreeCareer=" + engineeringDegreeCareer
				+ ", engineeringDegreeStudyArea=" + engineeringDegreeStudyArea + ", engineeringDegreeSkills="
				+ engineeringDegreeSkills + ", engineeringRelatedCourses=" + engineeringRelatedCourses + ", isCluster="
				+ isCluster + ", clusterName=" + clusterName + ", clusterId=" + clusterId + ", clusterDegree="
				+ clusterDegree + ", clusterEntExam=" + clusterEntExam + ", careerFitmentPriority="
				+ careerFitmentPriority + "]";
	}

	public boolean isCluster() {
		return isCluster;
	}

	public void setCluster(boolean isCluster) {
		this.isCluster = isCluster;
	}

	public int getClusterId() {
		return clusterId;
	}

	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}

	public String getClusterDegree() {
		return clusterDegree;
	}

	public void setClusterDegree(String clusterDegree) {
		this.clusterDegree = clusterDegree;
	}

	public String getClusterEntExam() {
		return clusterEntExam;
	}

	public void setClusterEntExam(String clusterEntExam) {
		this.clusterEntExam = clusterEntExam;
	}

	public int getCareerFitmentPriority() {
		return careerFitmentPriority;
	}

	public void setCareerFitmentPriority(int careerFitmentPriority) {
		this.careerFitmentPriority = careerFitmentPriority;
	}

	public ClientEdupathDTO getClienteduDTO() {
		return clienteduDTO;
	}

	public void setClienteduDTO(ClientEdupathDTO clienteduDTO) {
		this.clienteduDTO = clienteduDTO;
	}

	 
	
}
