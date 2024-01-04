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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/ApplicationStyles.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/thirdparty/jquery/js/jquery.min.js"></script>

<link href="${pageContext.request.contextPath}/styles/help/reset.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/styles/help/style.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/scripts/help/modernizr.js"></script>
<!-- Modernizr -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/ApplicationStyles.css"
	type="text/css">
</HEAD>
<BODY>

	<div style="position: fixed;top: 0px;width: 100%;z-index:1;background-color:#0f75bf ; ">
		<div class="logo_div"
			style="border-bottom: 4px solid #0f75bf; padding-left: 30px; padding-top: 11px; height: 90px;width:260px;background-color:white">
			<img alt="LodeStar"
				src="${pageContext.request.contextPath}/images/lodestar_logo.gif">
		</div>
	</div>
	<div align="center" style="margin-top:100px;">
	<img alt="Under_Construction"
				src="${pageContext.request.contextPath}/images/Page_Under_Construction.png"></div>
			<!-- cd-faq-items -->
			<a href="#0" class="cd-close-panel">Close</a>
		
		<script
			src="${pageContext.request.contextPath}/scripts/help/jquery-2.1.1.js"></script>
		<script src="${pageContext.request.contextPath}/scripts/help/main.js"></script>
	</div>

</BODY>
</HTML>
