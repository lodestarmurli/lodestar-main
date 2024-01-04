package com.lodestar.edupath.tum.action;

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

public class StudentWelcomeAction extends BaseAction
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(StudentWelcomeAction.class);
	private StudentDetailsDTO	student				= new StudentDetailsDTO();
	private boolean favSubCompleted=false;
	private boolean streamSubmit=false;
	private boolean cddReportGenerated=false;
	private String wishListStr="";

	public String execute()
	{
		OUT.info("Inside StudentWelcomeAction");
		try
		{
			WelcomeService service = new WelcomeService();
			student = service.getStudentSession(getUserSessionObject().getId());
			setSelectedMenu();
			if(getUserSessionObject().isTrial() && student.getSource().equals(PROGRAMTEST.STREAMSELECTOR.getSource()))
			{
				StudentCGTDAO  dao = new StudentCGTDAO(); 
				StudentCGTResult result=  dao.getStudentCGTResultByStudentId(student.getId());
				if(result !=null)
				{
					getUserSessionObject().setAptitudeCompleted(true);
				}
				else
				{
					getUserSessionObject().setAptitudeCompleted(false);
				}
				return "STREAMSELECTORWELCOMEPAGE";
			}
			else if(getUserSessionObject().isTrial() && student.getSource().equalsIgnoreCase(PROGRAMTEST.ENGINEERINGBRANCHSELECTOR.getSource()))
			{
				StudentCGTDAO  dao = new StudentCGTDAO(); 
				StudentCGTResult result=  dao.getStudentCGTResultByStudentId(student.getId());
				if(result !=null)
				{
					getUserSessionObject().setAptitudeCompleted(true);
					EBFavouriteSelectorDTO eBFSdto = new EBFavouriteSelectorDTO();
					EngineeringSelectorDAO eBFSdao = new EngineeringSelectorDAO();
					eBFSdto=eBFSdao.getEBFavouriteSubject(student.getId());
					if(eBFSdto != null)
					{
						favSubCompleted=true;
					}
				}
				else
				{
					getUserSessionObject().setAptitudeCompleted(false);
				}
				return "EBSELECTORWELCOMEPAGE";
			}
			else if(getUserSessionObject().isTrial() && student.getSource().equalsIgnoreCase(PROGRAMTEST.CAREERDEGREEDISCOVERY.getSource()))
			{
				StudentCGTDAO  dao = new StudentCGTDAO(); 
				StudentCGTResult result=  dao.getStudentCGTResultByStudentId(student.getId());
				if(result !=null)
				{
					getUserSessionObject().setAptitudeCompleted(true);
					CDDStreamDTO _CDDdto = new CDDStreamDTO();
					CareerDegreeDiscoveryDAO _CDDdao = new CareerDegreeDiscoveryDAO();
					_CDDdto=_CDDdao.getCDDStream(student.getId());
					
//					OUT.debug("bharath cheching _CDDdto:{},_CDDdto.getStream():{}",_CDDdto,_CDDdto.getStream());
					if(_CDDdto == null)
					{
						streamSubmit=false;
						return "CDDSTREAM";
					}
					CareerDegreeDiscoveryDAO cddDAO = new CareerDegreeDiscoveryDAO();
					CareerDegreeDiscoveryResultDTO cddDTO = new CareerDegreeDiscoveryResultDTO();
					cddDTO=cddDAO.getCareerDegreeDiscoveryResult(student.getId());
					int studentId = student.getId();
					if(cddDTO != null)
					{
						cddReportGenerated=true;
					}
					if (studentId >= 24642 && studentId <= 24933) {
						return "NONSCIENCECDDWELCOMEPAGE";	
					}
				}
				else
				{
					getUserSessionObject().setAptitudeCompleted(false);
				}
//				CDDStreamDTO _CDDdto = new CDDStreamDTO();
//				CareerDegreeDiscoveryDAO _CDDdao = new CareerDegreeDiscoveryDAO();
//				_CDDdto=_CDDdao.getCDDStream(student.getId());
//				
//				int streamid = _CDDdto.getStream(); 
//				
//				if (streamid == 1 || streamid == 2 || streamid == 3) {
//					return "CDDWELCOMEPAGE";	
//				}
//				else {
//					return "NONSCIENCECDDWELCOMEPAGE";
//				}
				return "CDDWELCOMEPAGE";
				
			}
			else if(getUserSessionObject().isTrial() && student.getSource().equalsIgnoreCase(PROGRAMTEST.CAREERFITMENT.getSource()))
			{
				CareerFitmentDAO _CFDAO = new CareerFitmentDAO();
				StudentCareerFitmentDTO _SCFdto = _CFDAO.getStudentCareerFitment(student.getId());
				CareerFitmentOccNotFoundDTO _CFONFdto = new CareerFitmentOccNotFoundDTO();
				CareerFitmentDAO _CFONFdao = new CareerFitmentDAO(); 
				_CFONFdto=_CFONFdao.getCFOccNotFound(student.getId());
				if(_CFONFdto != null)
				{
					if(_CFONFdto.getLocked()==1)
					{
						return "CFLOCKEDSCREEN";
					}	
				}
				if(_SCFdto != null)
				{
					getUserSessionObject().setCareerFitmentOccSelection(true);
					StudentCGTResult result=  new StudentCGTDAO().getStudentCGTResultByStudentId(student.getId());
					OUT.debug("bharath Inside StudentWelcomeAction getUserSessionObject:{}",getUserSessionObject());
					WishListCareerFitmentDTO wcfDTO= new WishListCareerFitmentDTO();
					wcfDTO.setStudentId(student.getId());
					List<ClusterOccupationDTO>	studentWishList = new WishListCareerFitementDAO().getAllWishListCareerFitment(wcfDTO);
					if(studentWishList.size() != 0)
					{
						int i=1;
						for(ClusterOccupationDTO coDTO: studentWishList )
						{
							wishListStr+=coDTO.getName();
							if(i ==  studentWishList.size()-1)
							{
								wishListStr+=" and ";
								
							}
							else if(i < studentWishList.size())
							{
								wishListStr+=", ";
							}
							i++;
						}
						
					}
					OUT.debug("bharath Inside StudentWelcomeAction wishListStr:{}",wishListStr);
					if(result !=null)
					{
						getUserSessionObject().setAptitudeCompleted(true);
						return "CAREERFITMENTTHANKYOUSCREEN";
					}
					else
					{
						getUserSessionObject().setAptitudeCompleted(false);
						return "CAREERFITMENTWELCOMEPAGETWO";
					}
				}
				else
				{
					getUserSessionObject().setCareerFitmentOccSelection(false);
					WishListCareerFitmentDTO wcfDTO= new WishListCareerFitmentDTO();
					wcfDTO.setStudentId(student.getId());
					List<ClusterOccupationDTO>	studentWishList = new WishListCareerFitementDAO().getAllWishListCareerFitment(wcfDTO);
					OUT.debug("bharath Inside StudentWelcomeAction studentWishList:{}",studentWishList);
					if(studentWishList.size() != 0)
					{
						if(studentWishList.get(0).getOptionA() == 1)
						{
							return "CFOPTION1";
							
						}else if(studentWishList.get(0).getOptionB() == 1)
						{
							return "CFOPTION2";
						}
					}
					return "CAREERFITMENTWELCOMEPAGEONE";
				}
				
			}
			
			if(getUserSessionObject().isTrial())
			{
				return "TRIALSTUDENT";
			}
			if(student.getSource().equalsIgnoreCase("DH_FULL"))
			{
				return "DHWELCOMEPAGE";
			}
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	private void setSelectedMenu()
	{
		try
		{
			HttpSession mySession = request.getSession();
			Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> menuMap = (Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>) mySession.getAttribute("SideBarMenus");
			if (null != menuMap && !menuMap.isEmpty())
			{
				for (Map.Entry<ApplicationMenuDTO, List<ApplicationMenuDTO>> mapValue : menuMap.entrySet())
				{
					if (mapValue.getKey().getActionPath().equals("WelcomeStudentAction"))
					{
						setChildSelectedSidebarMenuId(0);
						setParentSelectedSidebarMenuId(mapValue.getKey().getId());
						break;
					}
					/*
					 * List<ApplicationMenuDTO> list = mapValue.getValue();
					 * if (null != list && !list.isEmpty())
					 * {
					 * for (ApplicationMenuDTO menu : list)
					 * {
					 * if (menu.getActionPath().equals("WelcomeStudentAction"))
					 * {
					 * setChildSelectedSidebarMenuId(menu.getId());
					 * setParentSelectedSidebarMenuId(mapValue.getKey().getId());
					 * break;
					 * }
					 * }
					 * }
					 */
				}
			}

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	public StudentDetailsDTO getStudent()
	{
		return student;
	}

	public boolean isFavSubCompleted() {
		return favSubCompleted;
	}

	public void setFavSubCompleted(boolean favSubCompleted) {
		this.favSubCompleted = favSubCompleted;
	}

	public boolean isStreamSubmit() {
		return streamSubmit;
	}

	public void setStreamSubmit(boolean streamSubmit) {
		this.streamSubmit = streamSubmit;
	}

	public boolean isCddReportGenerated() {
		return cddReportGenerated;
	}

	public void setCddReportGenerated(boolean cddReportGenerated) {
		this.cddReportGenerated = cddReportGenerated;
	}

	public String getWishListStr() {
		return wishListStr;
	}

	public void setWishListStr(String wishListStr) {
		this.wishListStr = wishListStr;
	}
	
	
}
