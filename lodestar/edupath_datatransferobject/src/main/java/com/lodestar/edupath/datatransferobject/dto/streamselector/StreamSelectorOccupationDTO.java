package com.lodestar.edupath.datatransferobject.dto.streamselector;

public class StreamSelectorOccupationDTO {
	
	private int 		id;
	private String		riasecCode;
	private String 		sciencewithMath;
	private String 		sciencewithBio;
	private String 		sciencewithMathDesign;
	private String 		commercewithMath;
	private String 		commercewithoutMath;
	private String 		artswithLanguages;
	private String 		artswithDesign;
	private String 		artswithPsychology;
	private String 		generalArts;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRiasecCode() {
		return riasecCode;
	}
	public void setRiasecCode(String riasecCode) {
		this.riasecCode = riasecCode;
	}
	public String getSciencewithMath() {
		return sciencewithMath;
	}
	public void setSciencewithMath(String sciencewithMath) {
		this.sciencewithMath = sciencewithMath;
	}
	public String getSciencewithBio() {
		return sciencewithBio;
	}
	public void setSciencewithBio(String sciencewithBio) {
		this.sciencewithBio = sciencewithBio;
	}
	public String getSciencewithMathDesign() {
		return sciencewithMathDesign;
	}
	public void setSciencewithMathDesign(String sciencewithMathDesign) {
		this.sciencewithMathDesign = sciencewithMathDesign;
	}
	public String getCommercewithMath() {
		return commercewithMath;
	}
	public void setCommercewithMath(String commercewithMath) {
		this.commercewithMath = commercewithMath;
	}
	public String getCommercewithoutMath() {
		return commercewithoutMath;
	}
	public void setCommercewithoutMath(String commercewithoutMath) {
		this.commercewithoutMath = commercewithoutMath;
	}
	public String getArtswithLanguages() {
		return artswithLanguages;
	}
	public void setArtswithLanguages(String artswithLanguages) {
		this.artswithLanguages = artswithLanguages;
	}
	public String getArtswithDesign() {
		return artswithDesign;
	}
	public void setArtswithDesign(String artswithDesign) {
		this.artswithDesign = artswithDesign;
	}
	public String getArtswithPsychology() {
		return artswithPsychology;
	}
	public void setArtswithPsychology(String artswithPsychology) {
		this.artswithPsychology = artswithPsychology;
	}
	public String getGeneralArts() {
		return generalArts;
	}
	public void setGeneralArts(String generalArts) {
		this.generalArts = generalArts;
	}
	@Override
	public String toString() {
		return "StreamSelectorOccupationDTO [id=" + id + ", riasecCode=" + riasecCode + ", sciencewithMath="
				+ sciencewithMath + ", sciencewithBio=" + sciencewithBio + ", sciencewithMathDesign="
				+ sciencewithMathDesign + ", commercewithMath=" + commercewithMath + ", commercewithoutMath="
				+ commercewithoutMath + ", artswithLanguages=" + artswithLanguages + ", artswithDesign="
				+ artswithDesign + ", artswithPsychology=" + artswithPsychology + ", generalArts=" + generalArts + "]";
	}	
	
	
}
