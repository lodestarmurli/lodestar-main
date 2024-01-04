package com.lodestar.edupath.datatransferobject.dto.streamselector;

public class RiasecStreamSelectorDTO {
	
	private int 		id;
	private String		riasecCode;
	private int 		sciencewithMath;
	private int 		sciencewithBio;
	private int 		sciencewithMathDesign;
	private int 		commercewithMath;
	private int 		commercewithoutMath;
	private int 		artswithLanguages;
	private int 		artswithDesign;
	private int 		artswithPsychology;
	private int 		generalArts;
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
	public int getSciencewithMath() {
		return sciencewithMath;
	}
	public void setSciencewithMath(int sciencewithMath) {
		this.sciencewithMath = sciencewithMath;
	}
	public int getSciencewithBio() {
		return sciencewithBio;
	}
	public void setSciencewithBio(int sciencewithBio) {
		this.sciencewithBio = sciencewithBio;
	}
	public int getSciencewithMathDesign() {
		return sciencewithMathDesign;
	}
	public void setSciencewithMathDesign(int sciencewithMathDesign) {
		this.sciencewithMathDesign = sciencewithMathDesign;
	}
	public int getCommercewithMath() {
		return commercewithMath;
	}
	public void setCommercewithMath(int commercewithMath) {
		this.commercewithMath = commercewithMath;
	}
	public int getCommercewithoutMath() {
		return commercewithoutMath;
	}
	public void setCommercewithoutMath(int commercewithoutMath) {
		this.commercewithoutMath = commercewithoutMath;
	}
	public int getArtswithLanguages() {
		return artswithLanguages;
	}
	public void setArtswithLanguages(int artswithLanguages) {
		this.artswithLanguages = artswithLanguages;
	}
	public int getArtswithDesign() {
		return artswithDesign;
	}
	public void setArtswithDesign(int artswithDesign) {
		this.artswithDesign = artswithDesign;
	}
	public int getArtswithPsychology() {
		return artswithPsychology;
	}
	public void setArtswithPsychology(int artswithPsychology) {
		this.artswithPsychology = artswithPsychology;
	}
	public int getGeneralArts() {
		return generalArts;
	}
	public void setGeneralArts(int generalArts) {
		this.generalArts = generalArts;
	}
	@Override
	public String toString() {
		return "RiasecStreamSelectorDTO [id=" + id + ", riasecCode=" + riasecCode + ", sciencewithMath="
				+ sciencewithMath + ", sciencewithBio=" + sciencewithBio + ", sciencewithMathDesign="
				+ sciencewithMathDesign + ", commercewithMath=" + commercewithMath + ", commercewithoutMath="
				+ commercewithoutMath + ", artswithLanguages=" + artswithLanguages + ", artswithDesign="
				+ artswithDesign + ", artswithPsychology=" + artswithPsychology + ", generalArts=" + generalArts + "]";
	}

	
	
}
