
<%@ taglib prefix="s" uri="/struts-tags"%>
<HTML>
<HEAD>
<TITLE><s:text name="com.edupath.webtitle" /></TITLE>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
  <script src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery_form.js" type="text/javascript" ></script>
  
 <style>
  .input-lg {
    height: 30px;
    padding: 10px 16px;
    font-size: 18px;
    line-height: 1.3333333;
    border-radius: 6px;
}
</style>
<meta name="viewport" content="width=device-width">
</HEAD>
<BODY style=" background-color:#f0f9ff">

	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="background-color:#fff;padding-top:5px;padding-bottom:5px">
	
	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	
		<img src="${pageContext.request.contextPath}/images/SIATTEST/lodestar_logo.gif" class="img-responsive">
		
	</div>	
		
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" align="right">
	
		<img src="${pageContext.request.contextPath}/images/SIATTEST/hdfclogo.gif" class="img-responsive">
		
	</div>
		
	</div>
	
	
	
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-top:20px;">
	
	<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
	
	 <div class="col-lg-8 col-md-8 col-sm-9 col-xs-9 col-lg-offset-3 col-md-offset-3 col-sm-offset-3 col-xs-offset-1" >
  
   <p style="color:#1276bb;font-size:12pt"><b>WHAT YOU GET FROM THE LODESTAR PROGRAM</b></p>
   
   </div>
	
	 
   <div class="col-sm-9 col-lg-8 col-md-8 col-xs-10 col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-1">
   <hr style="border: 0.5px solid #b3cfe2;">
   </div>
	
	
	
		<div class="col-sm-12 col-lg-12 col-md-12 col-xs-12">
	
	
	
	
	
	<div class="col-sm-3 col-lg-2 col-md-3 col-xs-6  col-lg-offset-2">
	<img src="${pageContext.request.contextPath}/images/SIATTEST/1offer.png" class="img-responsive">
	</div>
	
	<div class="col-sm-3 col-lg-2 col-md-3 col-xs-6">
	<img src="${pageContext.request.contextPath}/images/SIATTEST/2offer.png" class="img-responsive">
	</div>
	
	<div class="col-sm-3 col-lg-2 col-md-3 col-xs-6">
	<img src="${pageContext.request.contextPath}/images/SIATTEST/3offer.png" class="img-responsive">
	</div>
	
	<div class="col-sm-3 col-lg-2 col-md-3 col-xs-6" >
	<img src="${pageContext.request.contextPath}/images/SIATTEST/4offer.png" class="img-responsive">
	</div>
	 
	
	</div>
	
	
	
	
	
	</div>
	
	
	
	
	
	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12" align="center">
	
	<img src="${pageContext.request.contextPath}/images/SIATTEST/offer.png" class="img-responsive">
	</div>
	
	
	
	
   
   </div>
   
   
   
   
   
  
   
   
   
   
   

	
	
	
	
	<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12 col-md-push-6">
	<br><br>
	
<form action="${pageContext.request.contextPath}/Test/sendtestlinktochildsuccessfull" method="post" id="sendmessage" name="sendmessage">
	<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12 col-lg-offset-3 col-md-offset-2">
	<br>
  <div class="form-group">
      <label for="inputlg">Parent email address* :</label>
      <input class="form-control input-lg" id="pemailid" name="pemailid" value="${email}" type="text">
    </div>
    <div class="form-group">
      <label for="inputlg">Parent phone number* :</label>
     <input class="form-control input-lg"  id="pcontactno" name="pcontactno" type="text" value="${contactno}" />
    </div>
    
    <span style="font-size:20pt;color:#1276bb">Send Test to Child *</span>
	<br/>
  <p></p>
    
	 <div class="form-group">
      <label for="inputlg">Child's phone number :</label>
      <input class="form-control input-lg" id="scontactno" name="scontactno" type="text">
    </div>
     <div class="form-group" align="center">
      <p><b>OR</b></p>
    </div>
	 <div class="form-group">
      <label for="inputlg">Child's email address :</label>
      <input class="form-control input-lg" id="semailid" name="semailid" type="text">
    </div>
	 
	
	
	<input  id="pname" name="pname" type="hidden" value="${name}" />
	
	<input  id="Paymentid" name="Paymentid" type="hidden" value="" />
	
	
	<div class="form-group">
      <p style="font-size:16px"><b>Total cost of the program is &#x20B9; 2000*</b></p>
       <p style="font-size:10px">* &#x20B9;999 for HDFC card and Net banking users</p>
    </div>
	
	
       <div id="sb1" class="col-sm-12 col-md-12 col-lg-12" >
        <button type="button" onclick="SendMessagefn(1)" class="btn btn-info btn-lg btn-block">Pay HDFC Bank and Send </button>
        <br>
		</div>
		
		<div id="sb2" class="col-sm-12 col-md-12 col-lg-12" >
        <button type="button" onclick="SendMessagefn(2)" class="btn btn-info btn-lg btn-block">Pay Other Bank and Send </button>
        <br><br><br><br>
		</div>
		
		
		
      </div>
      
     </form>




	</div>
	
	
	
		
	<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 col-md-pull-6">
<br><br>

	<div class="backgroungimg1" >
	<div class="col-sm-10 col-sm-offset-1">
   <span style="font-size:20pt;color:#1276bb">THE LODESTAR</span><br>
   <span style="font-size: 19pt;color:#1276bb;font-weight: 600;">CAREER GUIDANCE TEST</span>
	<br><br>
  </div>


  <div class="col-sm-10 col-lg-10 col-md-10 col-xs-10 col-sm-offset-1 col-md-offset-1 col-lg-offset-1 col-xs-offset-1 " style="color:#414042;font-size:10pt">
    Passion is often the difference between failure and success in all walks of life, including jobs and careers. To help your child progress towards a successful career, there are parameters that need to be considered and aligned:<br><br>
  </div>
  <div class="col-sm-10 col-lg-10 col-md-10 col-xs-10 col-sm-offset-1 col-md-offset-1 col-lg-offset-1 col-xs-offset-1 " style="color:#414042;font-size:10pt">
  <ul style="color:#1276bb;font-size:10pt;list-style:disc;">
			<li style="color:#1276bb "><font color="#414042">Your child's Interests & Personality</font></li>
			<li style="color:#1276bb"><font color="#414042">Cognitive abilities</font></li>
			<li style="color:#1276bb"><font color="#414042">Choice of Career</font></li>
			<li style="color:#1276bb"><font color="#414042">Education Path - College, Graduation, Post Graduation and so on</font></li>
			
		</ul>
		 </div>
  
   <div class="col-sm-10 col-lg-10 col-md-10 col-xs-10 col-sm-offset-1 col-md-offset-1 col-lg-offset-1 col-xs-offset-1 " style="color:#414042;font-size:10pt">
The test that your child is about to take is based on internationally proven Holland's theory of Vocational Personalities in Work Environments, commonly known as Holland codes theory. This theory propounds that people and occupations have unique characteristics and that both of these characteristics can be measured. The theory goes onto say that individuals have greater job satisfaction when they take up jobs that match the characteristics they hold. <font color="#1276bb"> The 6 personality types as per Holland Codes are - </font>
</div>
<div class="col-sm-10 col-lg-12 col-md-12 col-xs-10 col-sm-offset-1 col-md-offset-1 col-lg-offset-1 col-xs-offset-1">
<br>
<div class="col-sm-6 col-lg-4 col-md-6 col-xs-12" >
<br>
<img src="${pageContext.request.contextPath}/images/SIATTEST/r.png" class="img-responsive">
</div>
<div class="col-sm-6 col-lg-4 col-md-6 col-xs-12">
<br>
<img src="${pageContext.request.contextPath}/images/SIATTEST/i.png" class="img-responsive">
</div>
<div class="col-sm-6 col-lg-4 col-md-6 col-xs-12">
<br>
<img src="${pageContext.request.contextPath}/images/SIATTEST/a.png" class="img-responsive">
</div>
<div class="col-sm-6 col-lg-4 col-md-6 col-xs-12">
<br>
<img src="${pageContext.request.contextPath}/images/SIATTEST/s.png" class="img-responsive">
</div>
<div class="col-sm-6 col-lg-4 col-md-6 col-xs-12">
<br>
<img src="${pageContext.request.contextPath}/images/SIATTEST/e.png" class="img-responsive">
</div>
<div class="col-sm-6 col-lg-4 col-md-6 col-xs-12">
<br>
<img src="${pageContext.request.contextPath}/images/SIATTEST/c.png" class="img-responsive">
<br><br><br><br>
</div>


</div>




</div>
	
	</div>
	
	
	
	
	
	
	
	
	
	

	
	<div class="modal fade" role="dialog" id="msgModal">
	<div class="modal-dialog">
	    <!-- Modal content-->
	   	<div class="modal-content" style="">
	       	<div class="modal-header">
	         	<button type="button" class="close" data-dismiss="modal">&times;</button>
	         	<h4 class="modal-title">Message</h4>
	       	</div>
	       	<div class="modal-body">
	        	
	        		<p>
	        			Thank you for sending the test link to your child. Once he/she completes the test please schedule an hour session with our guidance specialist.
	        		</p>
	        	
	        	
	       	</div>
       		<div class="modal-footer">
         		<button class="btn btn-sm apply-btn college-filter-btn"  data-dismiss="modal">
					<s:text name="com.edupath.questionnarie.model.btn.label"/>
				</button>
       		</div>
	     </div>
	</div>
</div>
<s:form name="razorpayorder" id="razorpayorder" method="post">
<input type="hidden" name="emailid" id="emailid" />
<input type="hidden" name="type" id="type" />
</s:form>
</BODY>

<script>
var formRazorpay = document.razorpayorder;
var form = document.sendmessage;
function SendMessagefn(type)
{
	
	if(Validatinginput())
	{
		$('#emailid').val($('#pemailid').val());
		$('#type').val(type);
		
		formRazorpay.action = "${pageContext.request.contextPath}/APIS/RazorpayOrder";
		var options = { 
		        beforeSubmit:  showRequest,
		        success:       showResponse
		    };  
		$("#razorpayorder").ajaxSubmit(options);
		
	   
	}
	
	
}
function showRequest(formData, jqForm, options)
{
	//$('#testLoading').show();
	
	$('#sb1').hide();
	$('#sb2').hide();
} 

function showResponse(responseText, statusText, xhr, $form)
{
	
	//alert(JSON.stringify(responseText));
	if(responseText.status=="success")
	{
		//console.log("order id==>"+responseText.orderid);
		
		//console.log("amount==>"+JSON.stringify(responseText));
		
		var options = {
			    "key": responseText.keyid,
			    "amount": responseText.amount, // 2000 paise = INR 20
			    "name": "Lodestar",
			    "description": "",
			    "image": "/edupath_webui/images/SIATTEST/lodestar_logo.gif",
			    "handler": function (response){
			    	
			    		$('#sb1').hide();
			    		$('#sb2').hide();
				    	$('#Paymentid').val(response.razorpay_payment_id);
				        //alert(response.razorpay_payment_id);
				        form.submit();
			    	
			    	
			    },
			    "prefill": {
			        "email": $('#pemailid').val(),
			        "contact":$('#pcontactno').val(),
			        
			    },
			   
			    "theme": {
			        "color": "#F37254"
			    },
			    "order_id": responseText.orderid,
			    "modal":{
			    	"ondismiss": function(){
			    		$('#sb1').show();
			    		$('#sb2').show();
			    	}
			    	
			    }
			};
			var rzp1 = new Razorpay(options);
					rzp1.open();
		
		
	}
	else
	{
		alert("Error please try again.");
		$('#sb1').show();
		$('#sb2').show();
	}
}






function Validatinginput()
{
	var status=true;
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	 
	 	if($('#pemailid').val()!=null && $('#pemailid').val().trim()!="")
		{
			 if (reg.test($('#pemailid').val()) == false) 
		       {
				    alert('Invalid Parent Email Address');
				    $('#pemailid').addClass('has-error has-danger');
					$('#pemailid').focus();
					return false;
		       }
		}
		else
		{
			alert('Please enter Parent email address');
			$('#pemailid').addClass('has-error has-danger');
			$('#pemailid').focus();
			return false;
		}
	 
		if(($('#pcontactno').val()!=null && $('#pcontactno').val().trim()!=""))
		{
			var phoneno = /^\d{10}$/; 
			if($('#pcontactno').val().trim().match(phoneno))
			{}
			else
			{
				alert('Invalid Parent Phone Number');
			    $('#scontactno').addClass('has-error has-danger');
				$('#scontactno').focus();
				return false;
			}
		}
		else
		{
			alert('Please enter Parent phone number');
			return false;
			
		}
		
	 
	if(($('#scontactno').val()!=null && $('#scontactno').val().trim()!="") || ($('#semailid').val()!=null && $('#semailid').val().trim()!=""))
	{
		if($('#semailid').val()!=null && $('#semailid').val().trim()!="")
		{
			 if (reg.test($('#semailid').val()) == false) 
		       {
				    alert('Invalid Child Email Address');
				    $('#semailid').addClass('has-error has-danger');
					$('#semailid').focus();
					return false;
		       }
		}
		
		
		if($('#scontactno').val()!=null && $('#scontactno').val().trim()!="")
		{
			var phoneno = /^\d{10}$/; 
			
			if($('#scontactno').val().trim().match(phoneno))
			{}
			else
			{
				alert('Invalid Child Phone Number');
			    $('#scontactno').addClass('has-error has-danger');
				$('#scontactno').focus();
				return false;
			}
			
			 
		}
		
	}
	else
	{
		alert('Please enter your child phone number or email address');
		return false;
		
	}
	
	
	
	
	return status;
}
$(document).ready(function()
		{
	if("${linksendsuccess}"==1)
		{
		
		$('#msgModal').modal("show");
		
		}
		else if("${linksendsuccess}"==2)
		{
			alert('Unable send link please try again');
		}
	
});

</script>


</HTML>
