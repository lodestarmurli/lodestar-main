<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
<s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 

<div class="edupath-padding-nonchild">
<div class="row">
 <div class="col-md-12">
  <span class="pagetitle">WELCOME TO THE DH-LODESTAR CAREER GUIDANCE SYSTEM</span>
 </div>
</div>
<div id="mainPage_1">
 

<div class="row">
 <div class="col-md-12 change_page_div_1">
  <p class="change_p_h6">  <b>You are about to begin the excting journey of finalising your career and education path with help from our Expert Guidance Counselor. </b> </p>
   <p class="change_p_h6">
    <b> In this process you will go through 2 sessions as per dates chosen by you below</b>
   </p>
   
  			<div class="col-md-12">
					<div class="col-md-5">
						<p class="change_p_h4" style="line-height: 30px;">
							<i class="fa fa-calendar"></i> &nbsp;&nbsp; Session 1:
							&nbsp;&nbsp; <span class="date_spn">${student.session1DateStr}</span>
						</p>
					</div>
					
					<div class="col-md-7">
						<p class="change_p_h4" style="line-height: 30px;"> <i class="fa fa-map-marker"></i> &nbsp;&nbsp;Venue :&nbsp;&nbsp;<span class="date_spn">${student.venue}</span></p>
						
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-5">
						<p class="change_p_h4" style="line-height: 30px;">
							<i class="fa fa-calendar"></i> &nbsp;&nbsp; Session 2:
							&nbsp;&nbsp; <span class="date_spn">${student.session2DateStr}</span>
						</p>
					</div>
					<div class="col-md-7">
						<p class="change_p_h4" style="line-height: 30px;"> <i class="fa fa-map-marker"></i> &nbsp;&nbsp;Venue :&nbsp;&nbsp;<span class="date_spn">${student.venue}</span></p>
						
					</div>
				</div>
				 

  
   
<p class="change_p_h6">Please click the TUM (Tell Us More) link  on left panel to fill in details about your academics, hobbies, interests and aspirations.</p>
						 <p class="change_p_h6">Once you finish this your session will be done as per dates mentioned above. This will be done using </p>
						 <p class="change_p_h4">Zoom/Googlemeet or WhatsAPP Video call. Please be ready at the scheduled date and time.   </p>
						 <p class="change_p_h4"> Our representative will contact you prior to the 1-1 sessions with the counsellor.  </p>
						 <p class="change_p_h6"> <b> What are these sessions about?</b></p>
						  <p class="change_p_h6"> <b> Session 1 : DISCOVER </b></p>
						   <p class="change_p_h6"> In this session, you will have an in-depth discussion with the Guidance Specialist and identify around 25 occupations suitable for you. The after the session you will  log into the account and read detailed reports of these 25 careers. You will then shortlist your top 6 options and come back for session 2 </p>
						    <p class="change_p_h6"><b>Session 2 : DETERMINE & DECIDE</b>  </p>
						    <p class="change_p_h6">  In this session, we will validate and discuss your top 6 choices. We will discuss education pathway for each of them and help you to identify your top 2 career and education choices. We will also help you identify your stream and elective choice, entrance exam choice and tutorial choices.</p>
						    
   
 </div>
</div>
</div>

 

 
</div>   
   <s:form name="WelcomeForm" id="WelcomeForm" method="post">
     <s:hidden name="actionNameValue" id="actionNameValue" value="PersonalAndAcademicInfoAction"></s:hidden>
   </s:form>
<style>
.change_back_btn
{
	display: none ;
}

</style>
 
 <script>
 var pageShow = 1;
 var dynPageShow = 1;
 var mainPageShow = 1;
 var form = document.WelcomeForm;
 var startShow, iconChange = 1, pgBar, pgBarCounter =  1;
 var animation = ['blind', 'slide', 'clip', 'drop'];
 var flashIntervalTime = 1 * 10 * 1000;
  $(document).ready(function()
  {
	  
	// pgBar  = setInterval(fnProgressBar, 210);
	startShow = setInterval(fnShowPage,  flashIntervalTime);	
	$('.custom-change-font').click(function()
	{
		var page = $(this).data('page');
		$(this).removeClass('fa fa-circle-o');
		$(this).addClass('fa fa-circle');
		$('.custom-change-font').not(this).removeClass('fa-circle').addClass('fa-circle-o');
		$('#page_'+dynPageShow).hide();
		$('#page_'+page).show("slide");
		dynPageShow = page;
		clearInterval(startShow);
		startShow = setInterval(fnShowPage, flashIntervalTime );	

	});  
  });
  
  function fnProgressBar()
  {
  	if(pgBarCounter >= 100)
  	{
  		pgBarCounter = 1;
       $('.pgbar').css("width", 0 +"%");
 	 }
 	 else
  	 {
       $('.pgbar').css("width", pgBarCounter +"%");
     }
  	pgBarCounter = pgBarCounter + 5;
  }
  
  function fnNext()
  {
	  
	 $('#mainPage_'+mainPageShow).hide();
	 mainPageShow = mainPageShow + 1;
	 $('#mainPage_'+mainPageShow).show();
	 $('.change_next').hide();
	 $('.change_back_btn').show();
	 
  }
  
  function fnBack()
  {
	  $('#mainPage_'+mainPageShow).hide();
	  mainPageShow = mainPageShow -1;
	  $('#mainPage_'+mainPageShow).show();
	  $('.change_next').show();
	  $('.change_back_btn').hide();
  }
  function fnGetStart()
  {
	  form.action = '${pageContext.request.contextPath}/myapp/PersonalAndAcademicInfoAction';
	  form.submit();
  }
  
  function fnShowPage()
  {
	    $('#page_'+dynPageShow).hide();
	    if(dynPageShow >4)
		{
		  dynPageShow = 0;
		}
		dynPageShow = dynPageShow + 1;
		$('#page_'+dynPageShow).show(animation[dynPageShow]); 
		$('.custom-change-font').each(function()
	    {
			 if($(this).data('page') == dynPageShow )
			 {
				$(this).removeClass('fa fa-circle-o');
				$(this).addClass('fa fa-circle');
				$('.custom-change-font').not(this).removeClass('fa-circle').addClass('fa-circle-o');
				return false;
			 }	 
		});
  }
 </script>