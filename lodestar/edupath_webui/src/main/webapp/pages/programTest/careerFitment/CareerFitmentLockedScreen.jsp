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
				 
					
						<br>
						<p class="change_p_h4">Our representatives will get back to you soon</p>
						<br>
						
					   		
				 
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
		//$('.CareerReport1').removeAttr('disabled');
		//$('.CareerReport').removeAttr('disabled');
		var form = document.ReportForm;
		form.action = "${pageContext.request.contextPath}/myapp/CareerFitmentReport";
		form.submit();
		
	}
	
	
	 
</script>