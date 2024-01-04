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

	<div style="position: fixed;top: 0px;width: 100%;z-index:2;background-color:#0f75bf ; ">
		<div class="logo_div"
			style="border-bottom: 4px solid #0f75bf; padding-left: 30px; padding-top: 11px; height: 90px;width:260px;background-color:white">
			<img alt="LodeStar"
				src="${pageContext.request.contextPath}/images/lodestar_logo.gif">
		</div>
	</div>
	<div class="bodyDiv">
		<section class="cd-faq">
			<ul class="cd-faq-categories">
				<li><a class="selected sidebar_spn_txt" href="#basics">Pre- Session</a></li>
				

				<li style="height: 280px; width: 260px"></li>

			</ul>
			<!-- cd-faq-categories -->

			<div class="cd-faq-items" style="margin-left: 100px;width: 68%; margin-top:90px;">
				<ul id="basics" class="cd-faq-group"">
					<li class="cd-faq-title" style="padding-top: 75px;"><h2>Pre- Session</h2></li>
					 <li><a class="cd-faq-trigger" href="#0">What if my report does not download?</a>
						<div class="cd-faq-content">
							
							<br>
							<p>If you have completed and submitted the test then report will be ready. Do not worry. 
							   Please refresh the page and try again.  
						       If you still do not get the report – mail us at guidance@lodestar.guru
							</p>
							<br>
							
						</div> <!-- cd-faq-content --> </li>

					<li><a class="cd-faq-trigger" href="#0" style="">Till when will this login ID be active </a>
						<div class="cd-faq-content">
						<br>
							<p>This login ID will be active for 1 month from the time you receive the email. 
								You can login, Take the test and also download the report anytime in this one month. </p>
							<br>
							
						</div> <!-- cd-faq-content --></li>

					<li><a class="cd-faq-trigger" href="#0">Till when will my report be available </a>
						<div class="cd-faq-content">
						<br>
							<p>This login ID will be active for 1 month from the time you receive the email. 
								You can login, Take the test and also download the report anytime in this one month. </p>
						</div> <!-- cd-faq-content --></li>
						
					<li><a class="cd-faq-trigger" href="#0">I could not complete my Test - what can I do? </a>
						<div class="cd-faq-content">
						<br>
							<p>You can login and take your Tests once again. You can start from the point where you stopped earlier. Most of your answers till then will be saved and will be visible to you. If your internet connection has been inactive for a long time before you realized, then some of the answers may not have got saved.</p>
							<br>
							<p>For the Personality (Interest) test there is no time limit - hence you will not have any problem. For the Aptitude test there is a time limit - the system will also keep track of the time balance at the time of stopping your test and allot the balance time to you when you re-start the test. </p>
							<br>
							<p>In case you have any queries or issues you can mail us at guidance@lodestar.guru and we will try and help you.</p>

						</div> <!-- cd-faq-content --></li>


				</ul>
				<!-- cd-faq-group -->

			 
				<!-- cd-faq-group -->

			 
				<!-- cd-faq-group -->

			 
				<!-- cd-faq-group -->
				<ul>
					<li style="height: 300px"></li>
				</ul>

				<!-- cd-faq-group -->

				<!-- cd-faq-group -->
			</div>
			<!-- cd-faq-items -->
			<a href="#0" class="cd-close-panel">Close</a>
		</section>
		<script
			src="${pageContext.request.contextPath}/scripts/help/jquery-2.1.1.js"></script>
		<script src="${pageContext.request.contextPath}/scripts/help/main.js"></script>
	</div>

</BODY>
</HTML>
