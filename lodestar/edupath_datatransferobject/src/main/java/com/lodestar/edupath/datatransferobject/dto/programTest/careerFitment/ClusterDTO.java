package com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class ClusterDTO  implements IModel
{
	private static final long	serialVersionUID	= 1L;
	private int 		id;
	private String 		name ;

	private String		degree;
	private String 		entExam;
	private String		requiredRAISEC;
	private String		requiredAbility;
	private String 		rollOverContent;
	private int			newfitment;
	private int			fitment;
	private int 		priority;
	
	
	
	public int getRequiredAbilityCount()
	{
		int count = 0;
		if(requiredAbility != null && !requiredAbility.trim().isEmpty())
		{
			count = requiredAbility.split(",").length;
		}
		return count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getEntExam() {
		return entExam;
	}

	public void setEntExam(String entExam) {
		this.entExam = entExam;
	}

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

	public int getNewfitment() {
		return newfitment;
	}

	public void setNewfitment(int newfitment) {
		this.newfitment = newfitment;
	}

	public int getFitment() {
		return fitment;
	}

	public void setFitment(int fitment) {
		this.fitment = fitment;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getRollOverContent() {
		return rollOverContent;
	}

	public void setRollOverContent(String rollOverContent) {
		this.rollOverContent = rollOverContent;
	}

	@Override
	public String toString() {
		return "ClusterDTO [id=" + id + ", name=" + name + ", degree=" + degree + ", entExam=" + entExam
				+ ", requiredRAISEC=" + requiredRAISEC + ", requiredAbility=" + requiredAbility + ", rollOverContent="
				+ rollOverContent + ", newfitment=" + newfitment + ", fitment=" + fitment + ", priority=" + priority
				+ "]";
	}

	
	   
	
}
