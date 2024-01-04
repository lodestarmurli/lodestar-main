package com.lodestar.edupath.APIS;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

public class CreateRazorpayOrder extends BaseAction{
	
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(CreateRazorpayOrder.class);
	

	private String   orderid;
	private String   keyid;
	private String	 status;
	private String	 emailid;
	private int   Amount;
	private int   type;
	
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getKeyid() {
		return keyid;
	}
	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}
	public String execute()
	{
		OUT.debug("Inside CreateRazorpayOrder class: execute Method");
		
		try
		{
			GlobalSettingDTO RazorpayKey = new GlobalSettingDTO();
			GlobalSettingDTO RazorpaySecretKey = new GlobalSettingDTO();
			GlobalSettingDTO OfferID = new GlobalSettingDTO();
			
			
			
			RazorpayKey.setPropertyName(ApplicationConstants.GlobalSettings.Razorpay_key.getProperty());
			RazorpayKey = new GlobalSttingDAO().getPropertesValueByName(RazorpayKey);
			
			RazorpaySecretKey.setPropertyName(ApplicationConstants.GlobalSettings.Razorpay_secret_key.getProperty());
			RazorpaySecretKey = new GlobalSttingDAO().getPropertesValueByName(RazorpaySecretKey);
			
			
			OfferID.setPropertyName(ApplicationConstants.GlobalSettings.Offer_ID.getProperty());
			OfferID = new GlobalSttingDAO().getPropertesValueByName(OfferID);
			
			OUT.error("Email ID===>"+emailid);
			if(type>0 && emailid!=null && !emailid.trim().equals("") && null != RazorpayKey.getPropertyValue() && !RazorpayKey.getPropertyValue().isEmpty() && null != RazorpaySecretKey.getPropertyValue() && !RazorpaySecretKey.getPropertyValue().isEmpty())
			{
				
				String Razorpay_key=RazorpayKey.getPropertyValue();
				String Razorpay_secret_key=RazorpaySecretKey.getPropertyValue();
				
				String Offer=null;
				if(type==1)
				{
					if(null != OfferID.getPropertyValue() && !OfferID.getPropertyValue().isEmpty())
					{
						Offer=OfferID.getPropertyValue();
					}
				
				}
				
				
				RazorpayClient razorpayClient = new RazorpayClient(Razorpay_key, Razorpay_secret_key);
				
				
				JSONObject options = new JSONObject();
				if(type==1)
				{
					options.put("amount", 999 * 100);
				}
				else
				{
					options.put("amount", 2000 * 100);
				}
				options.put("currency", "INR");
				options.put("receipt", emailid);
				options.put("payment_capture", 1);
				if(Offer!=null)
				{
					options.put("offer_id", Offer);
					int newamount= 999 * 100;
					Amount=newamount;
				}
				else
				{
					int newamount= 2000 * 100;
					Amount=newamount;
				}
				Order order = razorpayClient.Orders.create(options);
				
				orderid=order.get("id");
				keyid=Razorpay_key;
				
				status="success";
				
				OUT.error("Order ID===>"+orderid);
				OUT.error("Amount ===>"+Amount);
				
			}
			else
			{
				OUT.error("Required Field Missing.");
				status="ERROR";
			}
			
			
			
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			status="ERROR";
			
		}
		return SUCCESS;
	}
	
}
