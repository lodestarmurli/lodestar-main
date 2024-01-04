<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<HTML>
 <HEAD>
  <TITLE><s:text name="com.edupath.webtitle" /></TITLE>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/ApplicationStyles.css" type="text/css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/thirdparty/jquery-ui/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/thirdparty/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
</HEAD>
<BODY>
	<s:set name="emailLabel"><s:text name="com.edupath.forgotscreen.email.label"/></s:set>
	<s:set name="submitLabel"><s:text name="com.edupath.common.label.submit"/></s:set>		
	<s:form action="" name="emailValidateForm" id="emailValidateForm" method="POST" >
		<div >
		  <div class="logo_div" style=" border-bottom: 4px solid #0f75bf;padding-left: 13px;padding-top: 11px; height: 67px;">
                <img alt="LodeStar" src="${pageContext.request.contextPath}/images/lodestar_logo.gif">
           </div>
		</div>
		<div class="bodyDiv">
			<div class="errorDiv">
				<s:if test="hasActionErrors()">
					<div id="messageId" class="messageId">
			  		    <s:actionmessage cssClass="loginsuccessTD" />
			 			<s:actionerror cssClass="loginerrorTD"/>
			 			<s:fielderror ></s:fielderror>
					</div>
				</s:if>
			</div>
			
			<div class="login_form aniamted bounceIn">
			  <div class="login">
			  	<c:if test="${user == 'validUser'}">
					<h5>Password reset link has been sent to your email, click <a href="${pageContext.request.contextPath}" >here</a> redirect to login page</h5> 
				</c:if>
			    <h2><s:text name="com.edupath.forgotscreen.email.required.label"/> </h2>
			 	<s:textfield cssClass="form-control input-sm" placeholder="${emailLabel}" name="emailId" id="emailId" type="text"/>
			    <span class="error-block"></span>
			  </div>
			    <s:submit cssClass="btn btn-sm button "  value="%{submitLabel}"/>
			</div>
		</div>
	</s:form>	
</BODY>
<script>
$(document).ready(function() {  
	
	var value = $('#emailId').val();
	var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	jQuery.validator.addMethod("emailValid", function(value, element){
	    if (filter.test(value)) {
	        return true;  // FAIL validation when REGEX matches
	    } else {
	        return false;   // PASS validation otherwise
	    };
	}, "Please enter a valid email id."); 
	
	$('#emailValidateForm').validate({
    	errorElement: 'span', 
        errorClass: 'error-block',
        rules : {
        	emailId: {
            	required: true,
            	emailValid: true
            },
		},
		 
        invalidHandler: function (event, validator) { //display error alert on form submit   
        },
        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').removeClass("has-success").addClass('has-error'); // set error class to the control group
        },
        success: function (label,element) {
        	$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            label.remove();
        },
        submitHandler: function (form) {
            submitForm();
        }
    });
	
	$.validator.messages.required="This is a required field";
});

function submitForm(){
	
	form=document.emailValidateForm;
	form.action = "${pageContext.request.contextPath}/authenticateForgotPassword";
	form.submit();
}


</script>