package com.lodestar.edupath.CDDBulkDownload.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.CGT.StudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.CareerDegreeDiscovery.CareerDegreeDiscoveryDAO;
import com.lodestar.edupath.dataaccessobject.dao.careerFitment.CareerFitmentDAO;
import com.lodestar.edupath.dataaccessobject.dao.careerFitment.WishListCareerFitementDAO;
import com.lodestar.edupath.dataaccessobject.dao.engineeringselector.EngineeringSelectorDAO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.programTest.CareerDegreeDiscovery.CDDStreamDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.CareerDegreeDiscovery.CareerDegreeDiscoveryResultDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.CareerFitmentOccNotFoundDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.ClusterOccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.StudentCareerFitmentDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.WishListCareerFitmentDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.engineeringSelector.EBFavouriteSelectorDTO;
import com.lodestar.edupath.datatransferobject.dto.role.ApplicationMenuDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.PROGRAMTEST;
import com.lodestar.edupath.tum.service.WelcomeService;

public class CDDBulkDownloadAction extends BaseAction
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(CDDBulkDownloadAction.class);
	private StudentDetailsDTO	student				= new StudentDetailsDTO();
	private boolean favSubCompleted=false;
	private boolean streamSubmit=false;
	private boolean cddReportGenerated=false;
	private String wishListStr="";

	public String execute()
	{
		return SUCCESS;
	}


	
}
