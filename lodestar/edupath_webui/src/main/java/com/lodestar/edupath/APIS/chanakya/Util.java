package com.lodestar.edupath.APIS.chanakya;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.partner.PartnerDeatilsDAO;
import com.lodestar.edupath.datatransferobject.dto.partner.PartnerDeatilsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.EncryptionDecryptionData;

public class Util extends BaseAction 
{
	private static final Logger	OUT					= LoggerFactory.getLogger( Util.class);

	@SuppressWarnings("null")
	public static String validatePostHeader() {

		 OUT.debug("Excute the Util validatePostHeader======================= {}", Util.class);
		try 
		{
//			Date date = new Date();
//			JSONObject jsonobjDH =new JSONObject();
//			jsonobjDH.put("partnerID", "1");
//			jsonobjDH.put("partnerName", "DAILYHUNT");
//			jsonobjDH.put("type", "test");
//			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//			jsonobjDH.put("date",formatter.format(date));
//			String keyDH= new EncryptionDecryptionData().Encrypt(String.valueOf(jsonobjDH));
//			OUT.debug("Header json jsonobj:{}",jsonobjDH);
//			OUT.debug("Header json keyDH:{}",keyDH);
//
//			JSONObject jsonobj =new JSONObject();
//			jsonobj.put("partnerID", "2");
//			jsonobj.put("partnerName", "Lodestar");
//			jsonobj.put("type", "test");
//			jsonobj.put("date",formatter.format(date));
//			String keylodestar= new EncryptionDecryptionData().Encrypt(String.valueOf(jsonobj));
//			OUT.debug("Header json jsonobj:{}",jsonobj);
//			OUT.debug("Header json keylodestar:{}",keylodestar);

			HttpServletRequest request = ServletActionContext.getRequest();
			OUT.info("request.getMethod()" + request.getMethod());
			if(request.getMethod().equals("POST"))
			{
				if (request.getHeader("Authorization") != null || !request.getHeader("Authorization").equalsIgnoreCase("")) {
					String headers = request.getHeader("Authorization");
					String[] headersStr = headers.split("CHANAKYA-API", 2);
					OUT.info("request.getMethod()" + request.getMethod());
					OUT.info("headersStr" + headersStr[1]);
					PartnerDeatilsDTO partnerDeatilsDTO = PartnerDeatilsDAO.getParnterDetails(headersStr[1].trim());
					OUT.debug("partnerDeatilsDTO:{}", partnerDeatilsDTO);
					if (partnerDeatilsDTO == null) {
						return "error";
					}
				} else {
					return "error";
				}
			}
			else {
				return "errorMethod";
			}
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return "error";
		}

		return "success";
	}

	public static String validateGetHeader() {

		 OUT.debug("Excute the Util validateGetHeader======================= {}", Util.class);
		try 
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			OUT.info("request.getMethod()" + request.getMethod());
			if(request.getMethod().equals("GET"))
			{
				if (request.getHeader("Authorization") != null || !request.getHeader("Authorization").equalsIgnoreCase("")) {
					String headers = request.getHeader("Authorization");
					String[] headersStr = headers.split("CHANAKYA-API", 2);
					OUT.info("request.getMethod()" + request.getMethod());
					OUT.info("headersStr" + headersStr[1]);
					PartnerDeatilsDTO partnerDeatilsDTO = PartnerDeatilsDAO.getParnterDetails(headersStr[1].trim());
					OUT.debug("partnerDeatilsDTO:{}", partnerDeatilsDTO);
					if (partnerDeatilsDTO == null) {
						return "error";
					}
				} else {
					return "error";
				}
			}
			else {
				return "errorMethod";
			}
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return "error";
		}

		return "success";
	}

	public static String getDataForStudentCreation() {

		 OUT.debug("Excute the Util getDataForStudentCreation======================= {}", Util.class);
		 String Data="";
		try 
		{
			
			HttpServletRequest request = ServletActionContext.getRequest();
			Data=IOUtils.toString(request.getReader());
			
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return "error";
		}

		return Data;
	}

	
	public static PartnerDeatilsDTO getPartnerFromHeader()
	{
		PartnerDeatilsDTO partnerDeatilsDTO=null;
		try 
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			if (request.getHeader("Authorization") != null || !request.getHeader("Authorization").equalsIgnoreCase("")) {
				String headers = request.getHeader("Authorization");
				String[] headersStr = headers.split("CHANAKYA-API", 2);
				OUT.info("" + headersStr[1]);
//				String str ="{\"partnerName\":\"CHANAKYA\",\"partnerID\":\"6\",\"date\":\"13/07/2022\",\"type\":\"test\"}";
//				String jsonstr1 = new EncryptionDecryptionData().Encrypt(str);
				String jsonstr = new EncryptionDecryptionData().Decrypt(headersStr[1]);
				JSONObject jsonobj = new JSONObject(jsonstr);
				partnerDeatilsDTO = PartnerDeatilsDAO.getParnterDetailsByID(Integer.parseInt(jsonobj.getString("partnerID")));
				OUT.debug("partnerDeatilsDTO:{}", partnerDeatilsDTO);
			}
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
//			return "error";
		}
		return partnerDeatilsDTO;
	}

	
	public static String getData() {

		 OUT.debug("Excute the Util getDataForStudentCreation======================= {}", Util.class);
		 String Data="";
		try 
		{
			
			HttpServletRequest request = ServletActionContext.getRequest();
			Data=IOUtils.toString(request.getReader());
			
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return "error";
		}

		return Data;
	}


	
	
	
}
