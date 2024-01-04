package com.lodestar.edupath.datatransferobject.dto.college;

import java.io.Serializable;
import java.util.Date;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CollegeDTO implements Serializable, IModel
{

	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				name;
	private String				zone;
	private String				address;
	private int					cityId;
	private String				locality;
	private String				mobNo;
	private String				offNo;
	private String				email;
	private String				website;
	private double				distFromRailway;
	private double				distFromCentalBustop;
	private Date				yearOfEst;
	private String				collegeType;
	private Boolean				recogByKA;
	private String				recogByOth;
	private int					affiliatedToBoardId;
	private String				addFormSubmTo;
	private String				addFormSubmAddr;
	private String				addFormSubmOnline;
	private String				addFormSubmDate;
	private String				sexPref;
	private int					noSeatsState;
	private int					noSeatsICSE;
	private int					noSeatsCBSE;
	private int					noSeatsIGSE;
	private int					noSeatsISC;
	private Boolean				coachingFacility;
	private String				coachingCentreName;
	private Boolean				carrerCounselling;
	private double				latitude;
	private double				longitude;
	private double				tuitionFee;
	private double				booksAndSupplies;
	private double				coachingFee;
	private double				labFee;
	private boolean				whetherMaintainQuota;
	private String				saleOfAppFormStart;
	private boolean				admAppFromAdmOffice;
	private Boolean				isActive;

	// non table column

	private int					streamId;
	private int					combinationId;

	public int getCombinationId()
	{
		return combinationId;
	}

	public void setCombinationId(int combinationId)
	{
		this.combinationId = combinationId;
	}

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

	public String getZone()
	{
		return zone;
	}

	public void setZone(String zone)
	{
		this.zone = zone;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public int getCityId()
	{
		return cityId;
	}

	public void setCityId(int cityId)
	{
		this.cityId = cityId;
	}

	public String getLocality()
	{
		return locality;
	}

	public void setLocality(String locality)
	{
		this.locality = locality;
	}

	public String getMobNo()
	{
		return mobNo;
	}

	public void setMobNo(String mobNo)
	{
		this.mobNo = mobNo;
	}

	public String getOffNo()
	{
		return offNo;
	}

	public void setOffNo(String offNo)
	{
		this.offNo = offNo;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getWebsite()
	{
		return website;
	}

	public void setWebsite(String website)
	{
		this.website = website;
	}

	public double getDistFromRailway()
	{
		return distFromRailway;
	}

	public void setDistFromRailway(double distFromRailway)
	{
		this.distFromRailway = distFromRailway;
	}

	public double getDistFromCentalBustop()
	{
		return distFromCentalBustop;
	}

	public void setDistFromCentalBustop(double distFromCentalBustop)
	{
		this.distFromCentalBustop = distFromCentalBustop;
	}

	public Date getYearOfEst()
	{
		return yearOfEst;
	}

	public void setYearOfEst(Date yearOfEst)
	{
		this.yearOfEst = yearOfEst;
	}

	public String getCollegeType()
	{
		return collegeType;
	}

	public void setCollegeType(String collegeType)
	{
		this.collegeType = collegeType;
	}

	public Boolean getRecogByKA()
	{
		return recogByKA;
	}

	public void setRecogByKA(Boolean recogByKA)
	{
		this.recogByKA = recogByKA;
	}

	public String getRecogByOth()
	{
		return recogByOth;
	}

	public void setRecogByOth(String recogByOth)
	{
		this.recogByOth = recogByOth;
	}

	public int getAffiliatedToBoardId()
	{
		return affiliatedToBoardId;
	}

	public void setAffiliatedToBoardId(int affiliatedToBoardId)
	{
		this.affiliatedToBoardId = affiliatedToBoardId;
	}

	public String getAddFormSubmTo()
	{
		return addFormSubmTo;
	}

	public void setAddFormSubmTo(String addFormSubmTo)
	{
		this.addFormSubmTo = addFormSubmTo;
	}

	public String getAddFormSubmAddr()
	{
		return addFormSubmAddr;
	}

	public void setAddFormSubmAddr(String addFormSubmAddr)
	{
		this.addFormSubmAddr = addFormSubmAddr;
	}

	public String getSexPref()
	{
		return sexPref;
	}

	public void setSexPref(String sexPref)
	{
		this.sexPref = sexPref;
	}

	public int getNoSeatsState()
	{
		return noSeatsState;
	}

	public void setNoSeatsState(int noSeatsState)
	{
		this.noSeatsState = noSeatsState;
	}

	public int getNoSeatsICSE()
	{
		return noSeatsICSE;
	}

	public void setNoSeatsICSE(int noSeatsICSE)
	{
		this.noSeatsICSE = noSeatsICSE;
	}

	public int getNoSeatsCBSE()
	{
		return noSeatsCBSE;
	}

	public void setNoSeatsCBSE(int noSeatsCBSE)
	{
		this.noSeatsCBSE = noSeatsCBSE;
	}

	public int getNoSeatsIGSE()
	{
		return noSeatsIGSE;
	}

	public void setNoSeatsIGSE(int noSeatsIGSE)
	{
		this.noSeatsIGSE = noSeatsIGSE;
	}

	public int getNoSeatsISC()
	{
		return noSeatsISC;
	}

	public void setNoSeatsISC(int noSeatsISC)
	{
		this.noSeatsISC = noSeatsISC;
	}

	public Boolean getCoachingFacility()
	{
		return coachingFacility;
	}

	public void setCoachingFacility(Boolean coachingFacility)
	{
		this.coachingFacility = coachingFacility;
	}

	public Boolean getCarrerCounselling()
	{
		return carrerCounselling;
	}

	public void setCarrerCounselling(Boolean carrerCounselling)
	{
		this.carrerCounselling = carrerCounselling;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public Boolean getIsActive()
	{
		return isActive;
	}

	public void setIsActive(Boolean isActive)
	{
		this.isActive = isActive;
	}

	public String getCoachingCentreName()
	{
		return coachingCentreName;
	}

	public void setCoachingCentreName(String coachingCentreName)
	{
		this.coachingCentreName = coachingCentreName;
	}

	public double getTuitionFee()
	{
		return tuitionFee;
	}

	public void setTuitionFee(double tuitionFee)
	{
		this.tuitionFee = tuitionFee;
	}

	public double getBooksAndSupplies()
	{
		return booksAndSupplies;
	}

	public void setBooksAndSupplies(double booksAndSupplies)
	{
		this.booksAndSupplies = booksAndSupplies;
	}

	public double getCoachingFee()
	{
		return coachingFee;
	}

	public void setCoachingFee(double coachingFee)
	{
		this.coachingFee = coachingFee;
	}

	public double getLabFee()
	{
		return labFee;
	}

	public void setLabFee(double labFee)
	{
		this.labFee = labFee;
	}

	public int getStreamId()
	{
		return streamId;
	}

	public void setStreamId(int streamId)
	{
		this.streamId = streamId;
	}

	@Override
	public String toString()
	{
		return "CollegeDTO [id=" + id + ", name=" + name + ", zone=" + zone + ", address=" + address + ", cityId=" + cityId + ", locality=" + locality + ", mobNo="
				+ mobNo + ", offNo=" + offNo + ", email=" + email + ", website=" + website + ", distFromRailway=" + distFromRailway + ", distFromCentalBustop="
				+ distFromCentalBustop + ", yearOfEst=" + yearOfEst + ", collegeType=" + collegeType + ", recogByKA=" + recogByKA + ", recogByOth=" + recogByOth
				+ ", affiliatedToBoardId=" + affiliatedToBoardId + ", addFormSubmTo=" + addFormSubmTo + ", addFormSubmAddr=" + addFormSubmAddr
				+ ", addFormSubmOnline=" + addFormSubmOnline + ", addFormSubmDate=" + addFormSubmDate + ", sexPref=" + sexPref + ", noSeatsState=" + noSeatsState
				+ ", noSeatsICSE=" + noSeatsICSE + ", noSeatsCBSE=" + noSeatsCBSE + ", noSeatsIGSE=" + noSeatsIGSE + ", noSeatsISC=" + noSeatsISC
				+ ", coachingFacility=" + coachingFacility + ", coachingCentreName=" + coachingCentreName + ", carrerCounselling=" + carrerCounselling
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", tuitionFee=" + tuitionFee + ", booksAndSupplies=" + booksAndSupplies + ", coachingFee="
				+ coachingFee + ", labFee=" + labFee + ", isActive=" + isActive + ", streamId=" + streamId + ", combinationId=" + combinationId + "]";
	}

	public String getAddFormSubmDate()
	{
		return addFormSubmDate;
	}

	public void setAddFormSubmDate(String addFormSubmDate)
	{
		this.addFormSubmDate = addFormSubmDate;
	}

	public boolean isWhetherMaintainQuota()
	{
		return whetherMaintainQuota;
	}

	public void setWhetherMaintainQuota(boolean whetherMaintainQuota)
	{
		this.whetherMaintainQuota = whetherMaintainQuota;
	}

	public String getSaleOfAppFormStart()
	{
		return saleOfAppFormStart;
	}

	public void setSaleOfAppFormStart(String saleOfAppFormStart)
	{
		this.saleOfAppFormStart = saleOfAppFormStart;
	}

	public boolean isAdmAppFromAdmOffice()
	{
		return admAppFromAdmOffice;
	}

	public void setAdmAppFromAdmOffice(boolean admAppFromAdmOffice)
	{
		this.admAppFromAdmOffice = admAppFromAdmOffice;
	}

	public String getAddFormSubmOnline()
	{
		return addFormSubmOnline;
	}

	public void setAddFormSubmOnline(String addFormSubmOnline)
	{
		this.addFormSubmOnline = addFormSubmOnline;
	}

}
