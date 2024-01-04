<%-- 
	@(#) LoginScreen.jsp     
	
    This software is the confidential and proprietary information of
    JaMocha Tech Private Limited ("Confidential Information").
    You shall not disclose such 'Confidential Information' and shall
    use it only in accordance with the terms of the license agreement
    you entered into with JaMocha Tech Private Limited.
 	
 	Code Change Control:
    Date                     Developer                           Remarks
    ----------               ------------------                  -------------------
    dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 	
--%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<HTML>
 <HEAD>
  <TITLE><s:text name="com.edupath.webtitle" /></TITLE>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/ApplicationStyles.css" type="text/css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/CommonScripts.js"></script>
  
  <script type="text/javascript" src="https://use.fontawesome.com/b9bdbd120a.js"></script>

<style>
	.showpassword {
		position: absolute;
		top: 47%;
		left: 82%;
		background-color: white;
		width: 25px;
		color: #33b5e5;
		z-index: 2;
	}
</style>
</HEAD>
<BODY>
	<s:form action="Authentication" namespace="/" name="AuthenticationForm" method="POST" theme="simple" onsubmit="return fnSubmitForm(this);">
		<s:set var="nameLabel"><s:text name="com.edupath.loginscreen.user.name.label" /></s:set>
		<s:set var="passwordLabel"><s:text name="com.edupath.loginscreen.password.label"/></s:set>
		<s:set var="forgotPasswordLabel"><s:text name="com.edupath.loginscreen.forgotpassword.label"/></s:set>
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
			    <h2><s:text name="com.edupath.loginscreen.user.welcome"/> </h2>
			      <input placeholder="${pageScope.nameLabel}" type="text" name="loginId" id="loginId" value="">
			      <input placeholder="${pageScope.passwordLabel}" type="password" name="password" id="password" value="">
			         <span toggle="#password" class="showpassword fa fa-fw fa-eye toggle-password" style="background: transparent;"></span>
			     <input id="sub" type="submit" value="<s:text name='com.edupath.loginscreen.login.button.label'/>" name="submit1" class="button">
				<a href="#" id="forgotpass" onclick="fnForgotPass();" >${forgotPasswordLabel}</a>
			  </div>
			</div>
		</div>
	</s:form>	
</BODY>
<SCRIPT>
function fnSubmitForm(form)
{
	var loginId=$("#loginId").val();
	var password=$.trim($("#password").val());
	if((loginId == "") && (password == ""))
	{
		alert("Login Id & Password is required information");
		form.loginId.focus();
		return false;
	}
	if(loginId == "")
	{
		alert("Login Id is required information");
		form.loginId.focus();
		return false;
	}
	if(password == "")
	{
		alert("Password is required information");
		form.password.focus();
		return false;
	}
	return true;
}

function fnForgotPass()
{
	var form1 = document.AuthenticationForm;
	form1.action ="${pageContext.request.contextPath}/ForgotPassword";
	form1.submit();
}

$(".toggle-password").click(function() {
	  $(this).toggleClass("fa-eye fa-eye-slash");
	  var input = $($(this).attr("toggle"));
	  if (input.attr("type") == "password") {
	    input.attr("type", "text");
	  } else {
	    input.attr("type", "password");
	  }
	});

/* document.onkeydown =  function (e) 
{
	 var eventObject;
	 if(e)
	 {
		 eventObject = e;
	 }
	 else if(event)
	 {
		 eventObject = event;
	 }
	  if(eventObject.keyCode == 13)
	 {          
		 fnSubmitForm(document.AuthenticationForm);
	 } 
} */
</SCRIPT>
<SCRIPT for=window event=onload>
	document.AuthenticationForm.loginId.focus();
  	var url = '<%=request.getContextPath()%>/';  
	if(self.name != "" && self.name != "indexFrame")
	{
		window.top.location = url;
	}
</SCRIPT>
</HTML>
