<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="c.tld"%>
<s:hidden name="parentSelectedSidebarMenuId"
	id="parentSelectedSidebarMenuId" />
<s:hidden name="childSelectedSidebarMenuId"
	id="childSelectedSidebarMenuId" />
<s:form name="ReportForm" id="ReportForm" theme="simple">
	<s:hidden name="ReportstudentId" id="ReportstudentId" ></s:hidden>
	<s:hidden name="cddReportGenerated" id="cddReportGenerated" ></s:hidden>
</s:form>
<style>

.directionphone
	{
		display:none;
	}
@media only screen and (max-width: 1200px)  {

  .directionDesktop{
 		display:none;
 		      
    }
	.directionphone
	{
		display:block;
	}
}



</style>
<div class="edupath-padding-nonchild">
	<div class="row">
		<div class="col-md-12">
		 
				<span class="pagetitle">Welcome to the Lodestar Career Fitment Test!</span>
			
		</div>
	</div>
	<div id="mainPage_1">


		<div class="row">
			<div class="col-md-12 change_page_div_1">
				 
						<p class="change_p_h6"><strong class="change_p_h4"><b>Congratulations</b> </strong> you have chosen your 3 Career choices for matching and they are<b> ${wishListStr}</b>  </p>
						<br>
						<p class="change_p_h6">Next step is to evaluate your <b>Vocational Personality</b> and <b>Aptitude</b> and match it to  these 3 careers. </p>
						<p class="change_p_h4 directionDesktop"><b>Click on the links on the left to take the two tests and get your detailed recommendation report.</b>.</p>
						<p class="change_p_h4 directionphone"><b>Please click on the Menu button above to access the test</b>.</p>
						<br>
						<p class="change_p_h3" style="color: #63a3d1;">What is Vocational Personality?</p>
						<br>
						<p class="change_p_h6">Vocational Personality or Vocational Interest is that personality that is linked to the type of work that will suit you in the future. This is based on an internationally well recognized theory called Hollands Code. This theory categorizes your personality on six parameters - <strong class="change_p_h4"><b>RIASEC</b></strong></p>
						<br>
						<ul style="list-style: disc;">
							<li>
								<p class="change_p_h4">
									<b>R</b>ealistic (The Doers)
								</p>
							</li>
							<li>
								<p class="change_p_h4">
									<b>I</b>nvestigative (The Thinkers)
								</p>
							</li>
							<li>
								<p class="change_p_h4">
									<b>A</b>rtistic (The Creators)
								</p>
							</li>
							<li>
								<p class="change_p_h4">
									<b>S</b>ocial (The Helpers)
								</p>
							</li>
							<li>
								<p class="change_p_h4">
									<b>E</b>nterprising (The Influencers)
								</p>
							</li>
							<li>
								<p class="change_p_h4">
									<b>C</b>onventional (The Organisers)
								</p>
							</li>
						</ul>
						<br>
						<p class="change_p_h6">The test will help to understand what
							are your top 3 traits in this list.</p>
						<br>
						<p class="change_p_h6">The test has 60 simple yes and no questions which are about daily life situations. This test does not have a time limit. You can ideally complete this in 20-25 minutes.</p>
						<br>
						<p class="change_p_h3" style="color: #63a3d1;">What is Aptitude?</p>
						<br>
						<p class="change_p_h6">Aptitudes are inherent skills that you have developed over a period of time and during your education. The Lodestar test will measure five aptitudes which are:</p>
						<br>
						
						<ul style="list-style: disc;">
							<li>
								<p class="change_p_h4">
									<b>Numerical Ability</b> -  Ability to understand and handle numbers and basic arithmetic. 	
								</p>
							</li>
							<li>
								<p class="change_p_h4">
									<b>Reasoning Ability</b> - Logical thinking
								</p>
							</li>
							<li>
								<p class="change_p_h4">
									<b>Verbal Ability</b> - English or language skills
								</p>
							</li>
							<li>
								<p class="change_p_h4">
									<b>Spatial Ability</b> - Ability to understand figures, spaces and patterns
								</p>
							</li>
							<li>
								<p class="change_p_h4">
									<b>Mechanical Ability</b> - Ability to work and understand machines
								</p>
							</li>
							 
						</ul>
						 
						<p class="change_p_h6">This test has a time limit and will have 60 questions to be completed in 60 minutes. Be ready with a pen and paper and also ensure that you have a clear 60 minutes available to complete the test in one sitting.</p>
						<br>
						<p class="change_p_h6">In case of any query please mail us at guidance@lodestar.guru</p>
						<br>
						<p class="change_p_h6">All the best!</p>
						<p class="change_p_h6">Team Lodestar.</p>
					
				 
			</div>
		</div>
	</div>




</div>
<s:form name="WelcomeForm" id="WelcomeForm" method="post">
	<s:hidden name="actionNameValue" id="actionNameValue"
		value="PersonalAndAcademicInfoAction"></s:hidden>
</s:form>
<style>
.change_back_btn {
	display: none;
}
</style>

<script>
	var pageShow = 1;
	var dynPageShow = 1;
	var mainPageShow = 1;
	var form = document.WelcomeForm;
	var startShow, iconChange = 1, pgBar, pgBarCounter = 1;
	var animation = [ 'blind', 'slide', 'clip', 'drop' ];
	var flashIntervalTime = 1 * 10 * 1000;
	$(document).ready(
			function() {
				
				
			 
			});

 
 

	function fnShowPage() {
		$('#page_' + dynPageShow).hide();
		if (dynPageShow > 4) {
			dynPageShow = 0;
		}
		dynPageShow = dynPageShow + 1;
		$('#page_' + dynPageShow).show(animation[dynPageShow]);
		$('.custom-change-font').each(
				function() {
					if ($(this).data('page') == dynPageShow) {
						$(this).removeClass('fa fa-circle-o');
						$(this).addClass('fa fa-circle');
						$('.custom-change-font').not(this).removeClass(
								'fa-circle').addClass('fa-circle-o');
						return false;
					}
				});
	}
	
	function fnDownloadCareerFitmentReport()
	{
		$('.CareerReport1').removeAttr('disabled');
		$('.CareerReport').removeAttr('disabled');
		var form = document.ReportForm;
		//form.action = "${pageContext.request.contextPath}/myapp/";
		//form.submit();
		
	}
	
	
	 
</script>