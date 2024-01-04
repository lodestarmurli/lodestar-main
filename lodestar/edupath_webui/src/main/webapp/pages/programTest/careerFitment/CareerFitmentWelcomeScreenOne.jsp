<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="c.tld"%>
<s:hidden name="parentSelectedSidebarMenuId"
	id="parentSelectedSidebarMenuId" />
<s:hidden name="childSelectedSidebarMenuId"
	id="childSelectedSidebarMenuId" />
 
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
				 <p class="change_p_h6"><strong class="change_p_h4"><b>Congratulations</b> </strong> you have chosen to make a scientific decision to understand your <b> "Fitment" </b> to 3 careers of your choice.  </p>
						<br>
						<p class="change_p_h6">To get your fitment or match, you have to do the following:</p>
						<br>
						<ol>
							<li>a)	Choose the 3 Careers for which you want your "Fitment" to be assessed.</li>
							<li>b)	Take the Vocational Personality test (approx. 20-25 mins) </li>
							<li>c)	Take the Aptitude test (60 mins) </li>
							<li>d)	Download Career-Fitment Report </li>
						</ol>
						<br>
						<button class="btn btn_action"  id ="" onclick="fnOccupationGlossary();" style="margin-right: 50px;" >
				    		<span class="next-spn " style="padding-left:0px; padding-right:0px"><s:text name="Click here to choose 3 Careers." ></s:text></span>
				   		</button>
					 
					  
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
	
	function fnOccupationGlossary()
	{
		 
		var form = document.WelcomeForm;
		form.action = "${pageContext.request.contextPath}/myapp/CareerFitmentClusterOccGlossary";
		form.submit();
		
	}
	
	
	 
</script>