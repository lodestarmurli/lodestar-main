//package com.lodestar.edupath.programTest.CareerFitment;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.session.SqlSession;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.lodestar.edupath.base.BaseAction;
//import com.lodestar.edupath.dataaccessobject.MyBatisManager;
//import com.lodestar.edupath.dataaccessobject.dao.careerFitment.CareerFitmentDAO;
//import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
//import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
//import com.lodestar.edupath.dataaccessobject.dao.induocchoice.SystemRecommendationV2;
//import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
//import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
//import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.ClusterDTO;
//import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.StudentCareerFitmentDTO;
//import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
//import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
//
//public class CareerFitmentPDFReport extends BaseAction{
//	
//	private static final long serialVersionUID = 1L;
//	private static final Logger	OUT	= LoggerFactory.getLogger(CareerFitmentPDFReport.class);
//	
//	private String fileName;
//	private InputStream fileInputStream;
//	private ByteArrayOutputStream baos;
//	private SqlSession session = null;
//	private Map<String, Object>	systemRecommendation = new HashMap<String, Object>();
//	List<OccupationDTO> cgtOcc = new ArrayList<OccupationDTO>();
//	StudentDetailsDTO student = null;
//	StudentDetailsDAO studentDAO = new StudentDetailsDAO();
//	CareerFitmentRecommendation _CFRecommendation = new CareerFitmentRecommendation();
//	List<Integer> studentUserId	= new ArrayList<Integer>();
//	List<String[]> clusterOccList = new ArrayList<String[]>();
//	
//	public String execute() 
//	{
//		OUT.info("Inside CareerFitmentPDFReport execute");
//		try 
//		{
//			
////			if(getUserSessionObject()==null)
////			{
////				return "SessionExpired";
////			}
////			OUT.debug("bharath CareerFitmentPDFReport getUserSessionObject():{}", getUserSessionObject());
//			studentUserId.add(15337);
//			studentUserId.add(15370);
//			studentUserId.add(15407);
//			studentUserId.add(15875);
//			studentUserId.add(15937);
////			studentUserId.add(14746);
////			studentUserId.add(14767);
////			studentUserId.add(15106);
////			studentUserId.add(15143);
////			studentUserId.add(15188);
//			clusterOccList.add(new String[] {"Pure Science", "Data Analyst", "Geneticist"});
//			clusterOccList.add(new String[] {"Chef", "Sports & Event Manager", "Hotel management"});
//			clusterOccList.add(new String[] {"Data Analyst", "Pure Science", "IT- Software "});
//			clusterOccList.add(new String[] {"Automobile Engineer", "IT- Software ", "Chef"});
//			clusterOccList.add(new String[] {"Commercial Pilot", "Engineering", "Pure Science"});
////			clusterOccList.add(new String[] {"UX designer", "Architect", "Software Developer/Computer Programmer/Software Engineer"});
////			clusterOccList.add(new String[] {"Chartered Accountant", "Doctors", "Microbiologist"});
////			clusterOccList.add(new String[] {"Mechanical Engineers", "Investment Banker", "General Surgeon"});
//////			clusterOccList.add(new String[] {"Robotics Engineer", "Artificial Intelligence Specialist", "Marine Biologist"});
////			clusterOccList.add(new String[] {"Geneticist", "Automobile engineer", "Reporters & Correspondents"});
//
//			for(int userid: studentUserId)
//			{
//				student = studentDAO.getStudentDetailsByUserId(userid);
//				systemRecommendation = new SystemRecommendationV2().getSystemRecommendation(student.getId(), false);
//				OUT.debug("bharath CareerFitmentPDFReport student:{}",student);
//				StudentCGTResult cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT,student.getId());
//				OUT.debug("bharath CareerFitmentPDFReport cgtResult:{}",cgtResult);
//				for(String[] clocc : clusterOccList)
//				{
//					String clusterIds=null;
//					String OccIds=null;
//					for(String name: clocc ) {
//						ClusterDTO clDTO = (ClusterDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_CLUSTER_BY_NAME,name);
//						if(clDTO !=null ) 
//						{
//							if (clusterIds != null)
//							{
//								clusterIds += ", ";
//								
//							}
//							else
//							{
//								clusterIds = new String();
//							}
//							clusterIds+=clDTO.getId();
//						}
//						else
//						{
//							OccupationDTO occDTo = (OccupationDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_OCCUPATION_DEATILS_BY_NAME,name);
//							if(occDTo!=null)
//							{
//								if (OccIds != null)
//								{
//									OccIds += ", ";
//										
//								}
//								else
//								{
//									OccIds = new String();
//								}
//								OccIds+=occDTo.getId();	
//							}
//						}
//					}
//					CareerFitmentDAO _CFDAO = new CareerFitmentDAO();
//					StudentCareerFitmentDTO _SCFDTO = new StudentCareerFitmentDTO();
//					_SCFDTO.setStudentId(student.getId());
//					_SCFDTO.setOptionA(1);
//					_SCFDTO.setOptionB(0);
//					_SCFDTO.setClusterId(clusterIds);
//					_SCFDTO.setOccupationId(OccIds);
//					_SCFDTO.setApproved(1);
//					session = MyBatisManager.getInstance().getSession();
//					OUT.debug("bharath CareerFitmentPDFReport _SCFDTO:{}",_SCFDTO);
//					int result = _CFDAO.insertORUpdateStudentCareerFitment(session, _SCFDTO);
//					session.commit();
//					
//					String recommendationResult= _CFRecommendation.getCareerFitmentRecommendation(student,cgtResult);
//					if(recommendationResult.equalsIgnoreCase("Error"))
//					{
//						return ERROR;
//					}
//				}
//			}
//
//			
//		} catch (Exception e) {
//			if (session != null) {
//				session.close();
//			}
//			OUT.error(ApplicationConstants.EXCEPTION, e);
//			return ERROR;
//			
//		} finally {
//			if (session != null) {
//				session.close();
//			}
//		}
//			
//		
//		return SUCCESS;
//	
//	}
//}
