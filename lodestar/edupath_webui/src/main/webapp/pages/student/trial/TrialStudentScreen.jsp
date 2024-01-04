<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<HTMl>
<HEAD>
	<link href="${pageContext.request.contextPath}/thirdparty/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/thirdparty/select2/select2.css" type="text/css" />
	<link href="${pageContext.request.contextPath}/thirdparty/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/ApplicationStyles.css" type="text/css">
	<link href="${pageContext.request.contextPath}/thirdparty/datatables/css/components.css" rel="stylesheet" type="text/css"/>
	<script src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/thirdparty/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/thirdparty/select2/select2.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/thirdparty/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/CommonScripts.js"></script>
<style>
	label
	{
		font-weight: normal;
	}
	.required
	{
	  color: 
	}
	.trial-form
	{
		//border-radius: 3px;
	   // box-shadow: 0 0 0 2px #0f75bf;
	    border: 3px solid #0f75bf;
	    padding-bottom: 2%;
	    padding-top: 1%;
	}
	
	.radio-list
	{
		margin-right: 5px !important; 
	}
	.trial-content
	{
		border: 3px solid #0f75bf;
	    font-size: 14px;
	    margin-bottom: 20px;
	    margin-top: 25px;
	    padding: 15px;
	    border-right-width: 10px;
	    border-left-width: 10px;
	}
</style>
<title><s:text name="com.edupath.webtitle" /></title>
</HEAD>
<BODY class="container-fluid">
	<div class="row">
	  <div class="logo_div" style=" border-bottom: 4px solid #0f75bf;padding-left: 13px;padding-top: 11px; height: 81px;">
               <img alt="LodeStar" src="${pageContext.request.contextPath}/images/lodestar_logo.gif">
      </div>
	</div>
	<div  id="trialContentDiv">
		 <jsp:include page="/pages/student/trial/TrialStudentDetailScreen.jsp"></jsp:include> 
	</div>
</BODY>
</HTML>
