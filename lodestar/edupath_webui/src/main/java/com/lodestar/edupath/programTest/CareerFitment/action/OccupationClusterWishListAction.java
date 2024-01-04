package com.lodestar.edupath.programTest.CareerFitment.action;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.careerFitment.CareerFitmentDAO;
import com.lodestar.edupath.dataaccessobject.dao.careerFitment.WishListCareerFitementDAO;
import com.lodestar.edupath.datatransferobject.dto.occupation.IndustryDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.CareerFitmentOccNotFoundDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.ClusterOccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.StudentCareerFitmentDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.WishListCareerFitmentDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.WishListDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.induoccchoice.service.WishListService;
import com.lodestar.edupath.util.PasswordGeneratorService;

public class OccupationClusterWishListAction extends BaseAction
{
	private static final Logger		OUT					= LoggerFactory.getLogger(OccupationClusterWishListAction.class);
	private static final long		serialVersionUID	= 1L;

	private int						studentId;

	private int						wishListId;

	private String					wishId;

	private Integer					whistListSize;
	

	private List<ClusterOccupationDTO>	studentWishList;

	private int						excludeForOcc		= 6;

	Set<IndustryDTO>				industriesForOcc	= new TreeSet<>();
	StudentDetailsDTO student = null;
	StudentDetailsDAO studentDAO = new StudentDetailsDAO();

	public String execute()
	{
		OUT.debug("bharath inside OccupationClusterWishListAction execute");
		try
		{
			OUT.debug("bharath OccupationClusterWishListAction getUserSessionObject():{}", getUserSessionObject());
			if(getUserSessionObject()==null)
			{
				return "SessionExpired";
			}
			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
			studentId=student.getId();
			OUT.debug("bharath OccupationClusterWishListAction studentId:{}",studentId);
			WishListCareerFitmentDTO wcfDTO= new WishListCareerFitmentDTO();
			wcfDTO.setStudentId(studentId);
			wcfDTO.setOptionA(1);
			wcfDTO.setOptionB(0);
			studentWishList = new WishListCareerFitementDAO().getWishListCareerFitment(wcfDTO);
			whistListSize=studentWishList.size();
			OUT.debug("bharath OccupationClusterWishListAction whistListSize:{} studentWishList:{}",whistListSize,studentWishList);

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}
	
	// wishlist for optionB
	public String occupationList()
	{
		OUT.debug("bharath inside OccupationClusterWishListAction execute");
		try
		{
			OUT.debug("bharath OccupationClusterWishListAction getUserSessionObject():{}", getUserSessionObject());
			if(getUserSessionObject()==null)
			{
				return "SessionExpired";
			}
			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
			studentId=student.getId();
			OUT.debug("bharath OccupationClusterWishListAction studentId:{}",studentId);
			WishListCareerFitmentDTO wcfDTO= new WishListCareerFitmentDTO();
			wcfDTO.setStudentId(studentId);
			wcfDTO.setOptionA(0);
			wcfDTO.setOptionB(1);
			studentWishList = new WishListCareerFitementDAO().getWishListCareerFitment(wcfDTO);
			whistListSize=studentWishList.size();
			OUT.debug("bharath OccupationClusterWishListAction whistListSize:{} studentWishList:{}",whistListSize,studentWishList);

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String delete()
	{
		OUT.debug("bharath inside OccupationClusterWishListAction delete");
		
		try
		{
			String option =   request.getParameter("option");
			OUT.debug("bharath OccupationClusterWishListAction getUserSessionObject():{}", getUserSessionObject());
			if(getUserSessionObject()==null)
			{
				return "SessionExpired";
			}
			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
			studentId=student.getId();
			

			new WishListCareerFitementDAO().deleteWishListCFById(wishListId);
			StringBuilder auditMessage = new StringBuilder();
			auditMessage.append("Student's ").append(getStudentSessionObject().getFullName()).append(" CFwishlist deleted wishlistId: ").append(wishListId);
			insertAudit(auditMessage.toString(), getUserSessionObject());
			WishListCareerFitmentDTO wcfDTO= new WishListCareerFitmentDTO();
			wcfDTO.setStudentId(studentId);
			if(option.equalsIgnoreCase("optionA"))
			{
				wcfDTO.setOptionA(1);
				wcfDTO.setOptionB(0);
			}
			else if(option.equalsIgnoreCase("optionB"))
			{
				wcfDTO.setOptionA(0);
				wcfDTO.setOptionB(1);
			}
			
			studentWishList = new WishListCareerFitementDAO().getWishListCareerFitment(wcfDTO);
			whistListSize=studentWishList.size();
			OUT.debug("bharath OccupationClusterWishListAction whistListSize:{} studentWishList:{}",whistListSize,studentWishList);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String addOptionA()
	{
		OUT.debug("bharath inside OccupationClusterWishListAction add");
		try
		{
			OUT.debug("bharath CareerDegreeDiscoveryPDFReport getUserSessionObject():{}", getUserSessionObject());
			if(getUserSessionObject()==null)
			{
				return "SessionExpired";
			}
			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
			studentId=student.getId();
			WishListCareerFitmentDTO wishlistdto = new WishListCareerFitmentDTO();
			OUT.debug("bharath OccupationClusterWishListAction wishId:{},studentId:{}",wishId,studentId);
			wishlistdto.setStudentId(studentId);
			wishlistdto.setOptionA(1);
			wishlistdto.setOptionB(0);
			
			String substring = wishId.substring(excludeForOcc);
			OUT.debug("bharath OccupationClusterWishListAction substring:{}",substring);
			if (wishId.startsWith("occ"))
			{
				wishlistdto.setOccupationId(Integer.parseInt(substring));
				wishlistdto.setClusterId(0);
				new WishListCareerFitementDAO().deleteWishListCFByOcc(wishlistdto);
			}
			else if(wishId.startsWith("cls")) {
				wishlistdto.setOccupationId(0);
				wishlistdto.setClusterId(Integer.parseInt(substring));
				new WishListCareerFitementDAO().deleteWishListCFByClusters(wishlistdto);
			}
			
			OUT.debug("bharath OccupationClusterWishListAction wishlistdto:{}",wishlistdto);
			new WishListCareerFitementDAO().insertWishListCareerFitment(wishlistdto);
			
			StringBuilder auditMessage = new StringBuilder();
			auditMessage.append("Student's ").append(getStudentSessionObject().getFullName()).append(" CFwishlist added Occ/cluster ").append(wishId);
			insertAudit(auditMessage.toString(), getUserSessionObject());
			
			WishListCareerFitmentDTO wcfDTO= new WishListCareerFitmentDTO();
			wcfDTO.setStudentId(studentId);
			wcfDTO.setOptionA(1);
			wcfDTO.setOptionB(0);
			new WishListCareerFitementDAO().deleteOptionBWishListCFByStudentId(studentId);
			studentWishList = new WishListCareerFitementDAO().getWishListCareerFitment(wcfDTO);
			whistListSize=studentWishList.size();
			OUT.debug("bharath OccupationClusterWishListAction whistListSize:{} studentWishList:{}",whistListSize,studentWishList);

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String addOptionB()
	{
		OUT.debug("bharath inside OccupationClusterWishListAction add");
		try
		{
			OUT.debug("bharath CareerDegreeDiscoveryPDFReport getUserSessionObject():{}", getUserSessionObject());
			if(getUserSessionObject()==null)
			{
				return "SessionExpired";
			}
			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
			studentId=student.getId();
			WishListCareerFitmentDTO wishlistdto = new WishListCareerFitmentDTO();
			OUT.debug("bharath OccupationClusterWishListAction wishId:{},studentId:{}",wishId,studentId);
			wishlistdto.setStudentId(studentId);
			wishlistdto.setOptionA(0);
			wishlistdto.setOptionB(1);
			
			String substring = wishId.substring(excludeForOcc);
			OUT.debug("bharath OccupationClusterWishListAction substring:{}",substring);
			if (wishId.startsWith("occ"))
			{
				wishlistdto.setOccupationId(Integer.parseInt(substring));
				wishlistdto.setClusterId(0);
				new WishListCareerFitementDAO().deleteWishListCFByOcc(wishlistdto);
			}
			
			OUT.debug("bharath OccupationClusterWishListAction wishlistdto:{}",wishlistdto);
			new WishListCareerFitementDAO().insertWishListCareerFitment(wishlistdto);
			
			StringBuilder auditMessage = new StringBuilder();
			auditMessage.append("Student's ").append(getStudentSessionObject().getFullName()).append(" CFwishlist added Occ ").append(wishId);
			insertAudit(auditMessage.toString(), getUserSessionObject());
			
			WishListCareerFitmentDTO wcfDTO= new WishListCareerFitmentDTO();
			wcfDTO.setStudentId(studentId);
			wcfDTO.setOptionA(0);
			wcfDTO.setOptionB(1);
			new WishListCareerFitementDAO().deleteOptionAWishListCFByStudentId(studentId);
			studentWishList = new WishListCareerFitementDAO().getWishListCareerFitment(wcfDTO);
			whistListSize=studentWishList.size();
			OUT.debug("bharath OccupationClusterWishListAction whistListSize:{} studentWishList:{}",whistListSize,studentWishList);

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String submit()
	{
		SqlSession session = null;
		try
		{
			String option =   request.getParameter("option");
			OUT.debug("bharath CareerDegreeDiscoveryPDFReport getUserSessionObject():{}", getUserSessionObject());
			if(getUserSessionObject()==null)
			{
				return "SessionExpired";
			}
			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
			studentId=student.getId();
			WishListCareerFitmentDTO wcfDTO= new WishListCareerFitmentDTO();
			wcfDTO.setStudentId(studentId);
			studentWishList = new WishListCareerFitementDAO().getAllWishListCareerFitment(wcfDTO);
			whistListSize=studentWishList.size();
			OUT.debug("bharath OccupationClusterWishListAction whistListSize:{}  ",whistListSize);
			String clusterIds = null;
			String OccIds = null;
			int optionA=0;
			int optionB=0;
			 
			for(ClusterOccupationDTO coDTO:studentWishList)
			{
				optionA=coDTO.getOptionA();
				optionB=coDTO.getOptionB();
				if(coDTO.getClusterId() != 0)
				{
					if (clusterIds != null)
					{
						clusterIds += ", ";
						
					}
					else
					{
						clusterIds = new String();
					}
					clusterIds+=coDTO.getClusterId();
				}
				if(coDTO.getOccupationId() != 0)
				{
					if (OccIds != null)
					{
						OccIds += ", ";
							
					}
					else
					{
						OccIds = new String();
					}
					OccIds+=coDTO.getOccupationId();
				}
			}
			OUT.debug("bharath OccupationClusterWishListAction whistListSize:{} studentWishList:{}",whistListSize,studentWishList);
			CareerFitmentDAO _CFDAO = new CareerFitmentDAO();
			StudentCareerFitmentDTO _SCFDTO = new StudentCareerFitmentDTO();
			_SCFDTO.setStudentId(student.getId());
			_SCFDTO.setOptionA(optionA);
			_SCFDTO.setOptionB(optionB);
			_SCFDTO.setClusterId(clusterIds);
			_SCFDTO.setOccupationId(OccIds);
			_SCFDTO.setApproved(1);
			session = MyBatisManager.getInstance().getSession();
			OUT.debug("bharath OccupationClusterWishListAction submit _SCFDTO:{}",_SCFDTO);
			int result = _CFDAO.insertORUpdateStudentCareerFitment(session, _SCFDTO);
			session.commit();
			
		}
		catch (Exception e)
		{
			if (session != null) {
				session.close();
			}
			OUT.error("Exception", e);
			
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return "WELCOMESCREEN";
	}
	
	public String industries() throws Exception
	{
		OUT.debug("bharath inside OccupationClusterWishListAction industries");
		try
		{
			WishListService wishListService = new WishListService();

			if (wishId.startsWith("occ"))
			{
				String substring = wishId.substring(excludeForOcc);
				industriesForOcc = wishListService.getIndustriesForOcc(Integer.parseInt(substring));
			}

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			throw e;
		}
		return SUCCESS;
	}
	
	public String mailOccupationNameToLodestar()
	{
		SqlSession session = null;
		try
		{
			if(getUserSessionObject()==null)
			{
//				return "SessionExpired";
			}
			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
			studentId = student.getId();
			OUT.debug("Get Occupation golossar details for studentId:{}", student);
			String searchval =   request.getParameter("occNameMail");
			OUT.debug("Get Occupation for mail searchval:{}", searchval);
			CareerFitmentOccNotFoundDTO dto = new CareerFitmentOccNotFoundDTO();
			CareerFitmentDAO dao = new CareerFitmentDAO(); 
			dto.setStudentId(studentId);
			dto.setOccName(searchval);
			dto.setLocked(1);
			session = MyBatisManager.getInstance().getSession();
			OUT.debug("bharath OccupationClusterWishListAction mailOccupationNameToLodestar CareerFitmentOccNotFoundDTO:{}",dto);
			int result =dao.insertCFOccNotFound(session,dto);
			session.commit();
			
			PasswordGeneratorService.sendOccNameMessageToLodestar("Admin",student.getName(),student.getStudentemailId(),student.getFatherEmailId(),student.getContactNumber(),2,searchval);
		}
		catch (Exception e)
		{
			if (session != null) {
				session.close();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return SUCCESS;
	}

	private void insertAudit(String auditMessage, UserSessionObject userSessionObject)
	{
		AuditTrailLogger.addAuditInfo(ModuleNameEnum.OCCUPATION_WISH_LIST, auditMessage, userSessionObject.getLoginId());
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public  List<ClusterOccupationDTO> getStudentWishList()
	{
		return studentWishList;
	}

	 

	public int getWishListId()
	{
		return wishListId;
	}

	public void setWishListId(int wishListId)
	{
		this.wishListId = wishListId;
	}

	public String getWishId()
	{
		return wishId;
	}

	public void setWishId(String wishId)
	{
		this.wishId = wishId;
	}

	public Integer getWhistListSize() {
		return whistListSize;
	}

	public void setWhistListSize(Integer whistListSize) {
		this.whistListSize = whistListSize;
	}

	 

	 

}
