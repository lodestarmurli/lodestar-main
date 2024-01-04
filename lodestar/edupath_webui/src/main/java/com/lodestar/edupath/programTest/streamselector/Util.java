package com.lodestar.edupath.programTest.streamselector;

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

public class Util extends BaseAction {
	
	private static final Logger	OUT					= LoggerFactory.getLogger( Util.class);

	@SuppressWarnings("null")
	public static String validatePostHeader() {

		 OUT.debug("Excute the Util validatePostHeader======================= {}", Util.class);
		try 
		{
//			Date date = new Date();
//			JSONObject jsonobjSS =new JSONObject();
//			jsonobjSS.put("partnerID", "3");
//			jsonobjSS.put("partnerName", "STREAMSELECTOR");
//			jsonobjSS.put("type", "test");
//			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//			jsonobjSS.put("date",formatter.format(date));
//			String keySS= new EncryptionDecryptionData().Encrypt(String.valueOf(jsonobjSS));
//			OUT.debug("Header json jsonobj:{}",jsonobjSS);
//			OUT.debug("Header json keySS:{}",keySS);
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
					String[] headersStr = headers.split("STREAMSELECTOR-API", 2);
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
	
	
}
