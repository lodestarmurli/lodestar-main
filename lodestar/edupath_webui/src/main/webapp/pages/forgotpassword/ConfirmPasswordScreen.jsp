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
	<s:set name="passwordLabel"><s:text name="com.edupath.loginscreen.password.label"/></s:set>
	<s:set name="confirmPasswordLabel"><s:text name="com.edupath.confirmscreen.confirm.password.label"/></s:set>
	<s:set name="submitLabel"><s:text name="com.edupath.common.label.submit"/></s:set>		
	<s:form action="" name="passwordValidateForm" id="passwordValidateForm" method="POST" >
		<div>
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
			  	<input type="hidden" id="userId" value="%{userId}">
			  	<s:hidden name="userId" id="userId" value="%{userId}"/>
			    <h2><s:text name="com.edupath.forgotscreen.email.required.label"/> </h2>
			 	<s:password name="password" id="password" cssClass="form-control input-sm" placeholder="${passwordLabel}"/>
	    		<span class="error-block"></span>
	    		
	    		<s:password name="confirmPassword" id="confirmPassword" cssClass="form-control input-sm" placeholder="${confirmPasswordLabel}"/>
				<span class="error-block"></span>
			  </div>
			    <s:submit cssClass="btn btn-sm button "  value="%{submitLabel}"/>
			</div>
		</div>
	</s:form>	
</BODY>
<script>
$(document).ready(function() {  
	id = ${userId};
	$('#passwordValidateForm').validate({
    	errorElement: 'span', 
        errorClass: 'error-block',
        rules : {
        	password:{
				required : true,
				minlength: 6
			},
			confirmPassword:{
				required : true,
				equalTo: "#password"
			}
		},
        invalidHandler: function (event, validator) {
        },
        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').removeClass("has-success").addClass('has-error'); // set error class to the control group
        },
        success: function (label,element) {
        	$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            label.remove();
        	$(".centered-form .panel").css("height","320px")
        },
        submitHandler: function (form) {
        	if (id == 0) {
				return false
			}
        	 submitForm();
        }
    });
	
	$.validator.messages.required="This is a required field";
});

function submitForm(){
	
	form=document.passwordValidateForm;
	form.action = "${pageContext.request.contextPath}/updatePasswordForgotPassword";
	form.submit();
}


</script>