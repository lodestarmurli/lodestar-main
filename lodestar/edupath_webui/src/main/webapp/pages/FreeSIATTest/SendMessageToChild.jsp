
<%@ taglib prefix="s" uri="/struts-tags"%>
<HTML>
<HEAD>
<TITLE><s:text name="com.edupath.webtitle" /></TITLE>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-175168258-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'UA-175168258-1');
</script>
  
  
  
  <!-- Facebook Pixel Code -->
<script>
  !function(f,b,e,v,n,t,s)
  {if(f.fbq)return;n=f.fbq=function(){n.callMethod?
  n.callMethod.apply(n,arguments):n.queue.push(arguments)};
  if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
  n.queue=[];t=b.createElement(e);t.async=!0;
  t.src=v;s=b.getElementsByTagName(e)[0];
  s.parentNode.insertBefore(t,s)}(window, document,'script',
  'https://connect.facebook.net/en_US/fbevents.js');
  fbq('init', '701085170100266');
  fbq('track', 'PageView');
</script>
<noscript><img height="1" width="1" style="display:none"
  src="https://www.facebook.com/tr?id=701085170100266&ev=PageView&noscript=1"
/></noscript>
<!-- End Facebook Pixel Code -->

<!-- Google Code for Remarketing Tag -->
<!--------------------------------------------------
Remarketing tags may not be associated with personally identifiable information or placed on pages related to sensitive categories. See more information and instructions on how to setup the tag on: http://google.com/ads/remarketingsetup
--------------------------------------------------->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 829623505;
var google_custom_params = window.google_tag_params;
var google_remarketing_only = true;
/* ]]> */
</script>
<script type="text/javascript" src="//www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="//googleads.g.doubleclick.net/pagead/viewthroughconversion/829623505/?guid=ON&amp;script=0"/>
</div>
</noscript>
  <!-- Google Tag Manager -->
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-KRPH3K2');</script>
<!-- End Google Tag Manager -->

<!-- Google Tag Manager (noscript) -->
<noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-KRPH3K2"
height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<!-- End Google Tag Manager (noscript) -->
 
 
<!--Google recaptcha start-->
 <script src="https://www.google.com/recaptcha/api.js"></script>
<!--Google recaptcha end -->
 
</HEAD>
<BODY style=" background-color:#f0f9ff">

	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="background-color:#fff;padding-top:5px;padding-bottom:5px" align="center">
	
	<img src="${pageContext.request.contextPath}/images/SIATTEST/lodestar_logo.gif" class="img-responsive">
	</div>
	
	<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12 col-md-push-6">
	<br><br>
<form action="${pageContext.request.contextPath}/Test/sendmessagetochildsuccessfull" method="post" id="sendmessage" name="sendmessage">
	<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12 col-lg-offset-3 col-md-offset-2">
	
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
	
	
	 <div class="form-group">
      <p>* The above information will be used only to send the test link and not for any other purpose.</p>
    </div>
	<!-- In form changes by Neualto Bharath  Start 
				<div  class="g-recaptcha"  data-sitekey="6LfoOdEZAAAAAKPi_1qDDYOVG6yLbyVNCC4s67wZ"></div>
				In form changes by Neualto Bharath  End -->
	
	
       <div id="sb1" class="col-sm-offset-3 col-md-offset-3 col-lg-offset-3 col-sm-6 col-md-6 col-lg-6" >
        <button type="button" onclick="SendMessagefn()" class="btn btn-info btn-lg btn-block">Send</button>
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
	        			Thanks for sending the test to your child. Once your child completes it, you will get a detailed report of his/her vocational personality test.
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

</BODY>

<script>
function SendMessagefn()
{
	var form = document.sendmessage;
	if(Validatinginput())
	{
		$('#sb1').hide();
		form.submit();
		
		
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
