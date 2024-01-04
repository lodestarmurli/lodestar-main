package com.lodestar.edupath.APIS.client.hobby;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.client.Util;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.exploremoreoccupation.ExploreMoreOccupationsAction;

public class Hobby extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(Hobby.class);

	private String Status = "ERROR";

	private String Message = "";

	HobbyService service;

	public Hobby() {
		service = new HobbyService();
	}

	@Override
	public String execute() throws Exception {
		OUT.debug("Excute the Hobby ======================= {}", Hobby.class);

		ClientStudentDetailsDTO studentDTO = new ClientStudentDetailsDTO();
		String authorizationHeader = HttpHeaders.AUTHORIZATION;
		Map<String, List<OccupationDTO>> occList = new HashMap<String, List<OccupationDTO>>();

		try {
			String hearderResult_partnerName = Util.validatePostHeader();
			if (!hearderResult_partnerName.equalsIgnoreCase("error")
					&& !hearderResult_partnerName.equalsIgnoreCase("errorMethod")) {
				String Data = Util.getDataForStudentCreation();
				OUT.debug("Data Receive=>" + Data);
				if (Data != null && !Data.equals("") && !Data.equals("error")) {

					String JsonData = Data;
					OUT.debug("bharath Inside Hobby JsonData:{}", JsonData);
					JSONObject jsonDataObject = new JSONObject(Data.trim());
					OUT.debug("jsonDataObject Receive=>" + jsonDataObject);
					if (jsonDataObject.has("selectedOccupation")) {
						ExploreMoreOccupationsAction exploreMoreOccupationsAction = new ExploreMoreOccupationsAction();
						// Map<String, List<OccupationDTO>> occList =
						// exploreMoreOccupationsAction.getSearchResultForAPi(JsonData);
						occList = service.getSearchResult(jsonDataObject.get("selectedOccupation").toString());
					}
					Status = "200";
					Message = occList.toString();
				} else {
					Status = "204";
					Message = "Parameter null";
					return SUCCESS;
				}
			} else if (hearderResult_partnerName.equalsIgnoreCase("errorMethod")) {
				Status = "204";
				Message = "Invalid Method";
				return SUCCESS;
			} else {
				Status = "204";
				Message = "Header invalid ";
				return SUCCESS;
			}

		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			Status = "ERROR";
			Message = "Internal Exception Level 1";
		}

		return SUCCESS;

	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

}
