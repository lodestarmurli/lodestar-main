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
			<ul class="cd-faq-categories"                                                            >
				<li><a class="selected sidebar_spn_txt" href="#basics">Pre
						- Session</a></li>
				<li><a href="#mobile">After Session 1</a></li>
				<li><a href="#account">After Session 2 </a></li>
				<li><a href="#payments">After Session 3 </a></li>
				<li style="height: 280px; width: 260px"></li>

			</ul>
			<!-- cd-faq-categories -->

			<div class="cd-faq-items" style="margin-left: 100px;width: 68%; margin-top:90px;">
				<ul id="basics" class="cd-faq-group"">
					<li class="cd-faq-title" style="padding-top: 75px;"><h2>Pre
							- Session</h2></li>
					 <!--<li><a class="cd-faq-trigger" href="#0">I cannot login to
							lodestar software</a>
						<div class="cd-faq-content">
							<p>This can happen when:</p>
							<br>
							<p>a) No internet connectivity ;Please reset internet
								connectivity and try again.</p>
							<br>
							<p>b) A mobile device is used to access the software. This
								software is designed to work best on desktops and laptops and
								may not work well on mobiles.</p>
							<br>
							<p>c) The settings on firewall and internet are preventing
								access to the Lodestar url. Please check your internet settings
								and firewall settings to see if any setting is blocking access
								to the url.</p>
							<br>
							<p>d) You may also want to empty your system cache before
								trying to log in again</p>



							</ol>
						</div> <!-- cd-faq-content --><!-- </li> -->

					<li><a class="cd-faq-trigger" href="#0" style="">I am not able to
							take the test</a>
						<div class="cd-faq-content">
							<p>This can happen when:</p>
							<br>
							<p>a) There is no internet connectivity - Check the internet
								connectivity</p>
							<br>
							<p>b) When you try to access the test through a mobile device
								- Please take the test only through a laptop or a desktop.</p>
							<br>
							<p>c) When you have already the taken the test once and
								completed it- The test ID is valid only for taking the test once</p>
							<br>
							<p>d) You may have keyed in the wrong ID - Kindly check the
								ID you have keyed in</p>
						</div> <!-- cd-faq-content --></li>

					<li><a class="cd-faq-trigger" href="#0">I have logged in
							and my screen is blank</a>
						<div class="cd-faq-content">
							<p>a) If you have already logged in successfully and yet
								encounter a blank screen, try and refresh the screen once</p>
						</div> <!-- cd-faq-content --></li>


				</ul>
				<!-- cd-faq-group -->

				<ul id="mobile" class="cd-faq-group">
					<li class="cd-faq-title" style="padding-top: 80px;"><h2>After Session 1- Occupation report</h2></li>
					<li><a class="cd-faq-trigger" href="#0">Not able to read
							the report</a>
						<div class="cd-faq-content">
							<p>This can happen when:</p>
							<br>
							<p>a) Your report is not visible as your counsellor has not
								added the report into your cart - Please wait for your
								counsellor to add to your cart / Contact your counsellor for the
								same</p>
							<br>
							<p>b) There is no internet connectivity - Check the internet
								connectivity</p>
							<br>
							<p>c) When you try to access the test through a mobile device
								- Please take the test only through a laptop or a desktop</p>

						</div> <!-- cd-faq-content --></li>

					<li><a class="cd-faq-trigger" href="#0">Not able to flip
							the pages of your report</a>
						<div class="cd-faq-content">
							<p>This can happen when:</p>
							<br>

							<p>a) Your screen has hung due to low internet speed - Exit
								the screen and log back in</p>
							<br>
							<p>b) The arrow keys are not clicked on - Move your cursor to
								the arrow on the screen to flip pages</p>
						</div> <!-- cd-faq-content --></li>

					<li><a class="cd-faq-trigger" href="#0">Not able to
							shortlist</a>
						<div class="cd-faq-content">
							<p>Please click on the Add to shortlist button on the right
								hand corner of the report screen</p>
						</div> <!-- cd-faq-content --></li>
					<li><a class="cd-faq-trigger" href="#0">Not able to
							download the report</a>
						<div class="cd-faq-content">
							<p>The reports cannot be dowloaded</p>
						</div> <!-- cd-faq-content --></li>
				</ul>
				<!-- cd-faq-group -->

				<ul id="account" class="cd-faq-group">
					<li class="cd-faq-title" style="padding-top: 80px;"><h2>After Session 2 - Industrial  report</h2></li>
					<li><a class="cd-faq-trigger" href="#0">Cannot see the
							details of the entrance exam discussed by the Guidance Specialist</a>
						<div class="cd-faq-content">
							<p>This can happen when:</p>
							<br>

							<p>a) The counsellor has not entered the details in your cart
								- Please wait for the counsellor to add to your cart / Contact
								your counsellor for the same</p>
							<br>
							<p>b) The page needs to be refreshed</p>
							<br>
							<p>c) If the entrance exam is visible, please click on the
								name of the entrance exam for more details.</p>
						</div> <!-- cd-faq-content --></li>

					<li><a class="cd-faq-trigger" href="#0">Cannot see the Edu
							path for other than my top two choices</a>
						<div class="cd-faq-content">
							<p>The Edu path is discussed and given only for the top two
								career choices made</p>
						</div> <!-- cd-faq-content --></li>

					<li><a class="cd-faq-trigger" href="#0">Not able to
							download the report</a>
						<div class="cd-faq-content">
							<p>The reports cannot be downloaded</p>
						</div> <!-- cd-faq-content --></li>

				</ul>
				<!-- cd-faq-group -->

				<ul id="payments" class="cd-faq-group">
					<li class="cd-faq-title" style="padding-top: 80px;"><h2>After Session 3</h2></li>
					<li><a class="cd-faq-trigger" href="#0">I am not able to
							see the report</a>
						<div class="cd-faq-content">
							<p>This can happen when:</p>
							<br>
							<p>a) The feedback is not complete or submitted - Please fill
								in your feedback form and then try to access the report</p>
							<br>
							<p>b) The counsellor has not added the report into your cart
								- Please wait for your counsellor to add to your cart / Contact
								your counsellor for the same</p>
							<br>
						</div> <!-- cd-faq-content --></li>

					<li><a class="cd-faq-trigger" href="#0">For how long can I
							access my login</a>
						<div class="cd-faq-content">
							<p>The login is open for 3 months only to view your reports.
								This login cannot be used to take the test again or for any
								other purpose</p>
						</div> <!-- cd-faq-content --></li>

					<li><a class="cd-faq-trigger" href="#0">Not able to
							download the report</a>
						<div class="cd-faq-content">
							<p>We do not allow for downloading the reports. You can
								however, access the reports through your login at any point of
								time for a period of 3 months.</p>
						</div> <!-- cd-faq-content --></li>
				</ul>
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
