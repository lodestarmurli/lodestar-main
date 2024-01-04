package com.lodestar.edupath.datatransferobject.dto.sessionfeedback;

public class SessionFeedBackStatusDTO {
	private static final long	serialVersionUID	= 1L;
	
	private int 		SessionOneFeedBackStatus;
	private int			SessionTwoFeedBackStatus;
	
	private int 		SessiononefeedbackCount;
	private int 		SessiontwofeedbackCount;
	
	public int getSessiononefeedbackCount() {
		return SessiononefeedbackCount;
	}
	public void setSessiononefeedbackCount(int sessiononefeedbackCount) {
		SessiononefeedbackCount = sessiononefeedbackCount;
	}
	public int getSessiontwofeedbackCount() {
		return SessiontwofeedbackCount;
	}
	public void setSessiontwofeedbackCount(int sessiontwofeedbackCount) {
		SessiontwofeedbackCount = sessiontwofeedbackCount;
	}
	public int getSessionOneFeedBackStatus() {
		return SessionOneFeedBackStatus;
	}
	public void setSessionOneFeedBackStatus(int sessionOneFeedBackStatus) {
		SessionOneFeedBackStatus = sessionOneFeedBackStatus;
	}
	public int getSessionTwoFeedBackStatus() {
		return SessionTwoFeedBackStatus;
	}
	public void setSessionTwoFeedBackStatus(int sessionTwoFeedBackStatus) {
		SessionTwoFeedBackStatus = sessionTwoFeedBackStatus;
	}
	

}
