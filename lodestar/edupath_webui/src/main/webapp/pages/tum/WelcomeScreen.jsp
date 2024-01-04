<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
<s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 

<div class="edupath-padding-nonchild">
<div class="row">
 <div class="col-md-12">
  <span class="pagetitle"><s:text name="com.edupathstudent.tum.welcome"></s:text></span>
 </div>
</div>
<div id="mainPage_1">
<div class="row">
  <div class="col-md-12" style="padding-top: 20px;">
   <div id="change_page" class="change_page">
      <div id="page_1" class="page_cls">
        <%-- <span class="page_spn">In <b>4 Easy steps</b>, take an <b>informed decision</b> about your dream occupation!</span> --%>
        <div class="row" style="padding-top: 8px; padding-left: 5px;">
         <img alt="" src="../images/icons/welcome-slider-1.gif" >
        </div>
      </div>
     
      <div class="chenge_hidden page_cls" id="page_2">
        <div class="row " style="background: #A2A807;height: 211px;padding-top: 58px;color: #fff;">
          <div class="col-md-3" style="padding-left: 45px; padding-top: 15px;">
             <img alt="" src="../images/icons/welcome-TUM.gif" >
          </div>
          <div class="col-md-9">
            <span class="change_p_h3">Pre-Session</span>
            <!--  <p class="change_p_h3">Tell us more about yourself by filling up the TUM form and by taking a Career Guidance Session. </p> -->
             <!--  <p class="change_p_h3"> Career Guidance Test (CGT)</p> -->
               <p class="change_p_h6"> Tell us more about yourself by filling up the TUM form and by taking a Career Guidance Session. </p>
               
          </div>
        </div>
      </div>
      
      <div class="chenge_hidden page_cls" id="page_3">
        <div class="row " style="background: #484C65;height: 211px;padding-top: 58px;color: #fff;">
          <div class="col-md-3" style="padding-left: 45px; padding-top: 15px;">
            <img alt="" src="../images/icons/welcome-slide-1-discover-.gif" >
          </div>
          <div class="col-md-9">
          <span class="change_spn_txt">Session 1</span>
             <p class="change_p_h3">Discover</p>
               <p class="change_p_h6"> Explore more than 15 Industries and 250 Careers - online, at your convenience</p>
          </div>
        </div>
      </div>
      
      <div class="chenge_hidden page_cls" id="page_4">
        <div class="row " style="background: #55AA99;height: 211px;padding-top: 58px;color: #fff;">
          <div class="col-md-3" style="padding-left: 45px; padding-top: 10px;">
            <img alt="" src="../images/icons/welcome-slide-1-determine-.gif">
          </div>
          <div class="col-md-9">
           <span class="change_spn_txt">Session 2</span>
             <p class="change_p_h3">Determine</p>
               <p class="change_p_h6">Determine your Education path - find out about Stream, Electives, Exams and Institutes</p>
          </div>
        </div>
      </div>
      
      <div class="chenge_hidden page_cls" id="page_5">
        <div class="row " style="background: #7B4E7B;height: 211px;padding-top: 58px; color: #fff;">
          <div class="col-md-3" style="padding-left: 45px; padding-top: 10px;">
             <img alt="" src="../images/icons/welcome-slide-1-decide.gif" >
          </div>
          <div class="col-md-9">
           <span class="change_spn_txt">Session 3</span>
             <p class="change_p_h3">Decide</p>
              <p class="change_p_h6">Combine all information and make a final SMART Decision. A comprehensive plan from Career to College.</p>
          </div>
        </div>
      </div>
      
            <!-- <div class="custom-progress-bar">
              <div class="progress-bar  pgbar" >
              </div>
           </div> -->
      <div class="change_toggle">
        <i class="fa fa-circle custom-change-font"   data-page="1"></i>
        <i class="fa fa-circle-o custom-change-font" data-page="2"></i>
        <i class="fa fa-circle-o custom-change-font" data-page="3"></i>
        <i class="fa fa-circle-o custom-change-font" data-page="4"></i>
        <i class="fa fa-circle-o custom-change-font" data-page="5"></i>
      </div>
   </div>
  </div>
</div>

<div class="row">
 <div class="col-md-12 change_page_div_1">
  <p class="change_p_h6">You are about to begin an exciting journey of discovery and exploration of careers. Here you will be able to 
   explore more than 
   <b>250+ careers</b>
   and find out what suits you and what your passion is and where your dreams lie.  
   You will also be able to make a detailed and accurate plan to achieve the career of your dreams</p>
   <p class="change_p_h6">
     The lodestar Progam has three main sections - <b>Discover, Determine</b> and <b>Decide</b>. These are conducted over 3 sessions. Your three sessions will be on:
   </p>
   
   <!--   Start Sasedeve edited by vyankatesh  on Date 31-01-2017 -->
   
   <!-- Start Change Code  -->
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
				<c:if test="${student.session3DateStr != null }">
				<div class="col-md-12">
					<div class="col-md-5">
						<p class="change_p_h4" style="line-height: 30px;">
							<i class="fa fa-calendar"></i> &nbsp;&nbsp; Session 3:
							&nbsp;&nbsp; <span class="date_spn">${student.session3DateStr}</span>
						</p>
					</div>
					<div class="col-md-7">
						<p class="change_p_h4" style="line-height: 30px;"> <i class="fa fa-map-marker"></i> &nbsp;&nbsp;Venue :&nbsp;&nbsp;<span class="date_spn">${student.venue}</span></p>
						
					</div>
				</div>
				 </c:if>


				<!--  <table>
   		<tr align="left">
   		<td width="40%"><p class="change_p_h4" style="line-height:30px;"><i class="fa fa-calendar"></i> &nbsp;&nbsp; Session 1:&nbsp;&nbsp; <span class="date_spn">${student.session1DateStr}</span></p></td>
   		<td width="7%" class="change_p_h4">Venue :</td><td class="change_p_h4"> ${student.venue}</td>
   		</tr>
   		<tr align="left">
   		<td width="40%"><p class="change_p_h4" style="line-height:30px;"><i class="fa fa-calendar"></i> &nbsp;&nbsp; Session 1:&nbsp;&nbsp; <span class="date_spn">${student.session2DateStr}</span></p></td>
   		<td width="7%" class="change_p_h4">Venue :</td><td class="change_p_h4"> ${student.venue}</td>
   		</tr>
   		<tr align="left">
   		<td width="40%"><p class="change_p_h4" style="line-height:30px;"><i class="fa fa-calendar"></i> &nbsp;&nbsp; Session 1:&nbsp;&nbsp; <span class="date_spn">${student.session3DateStr}</span></p></td>
   		<td width="7%" class="change_p_h4">Venue :</td><td class="change_p_h4"> ${student.venue}</td>
   		</tr>
   
   
   </table> -->
   
   <!--End Change Code -->
   
   <!--Start Original Code  -->
   
   <!-- <p class="change_p_h4" style="line-height:30px;"><i class="fa fa-calendar"></i> &nbsp;&nbsp; Session 1:&nbsp;&nbsp; <span class="date_spn">${student.session1DateStr}</span></p>
   <p class="change_p_h4" style="line-height:30px;"><i class="fa fa-calendar"></i> &nbsp;&nbsp; Session 2:&nbsp;&nbsp; <span class="date_spn">${student.session2DateStr}</span></p>
   <p class="change_p_h4" style="line-height:30px;"><i class="fa fa-calendar"></i> &nbsp;&nbsp; Session 3:&nbsp;&nbsp; <span class="date_spn">${student.session3DateStr}</span></p>
 -->
  <!--End Original Code  -->
  
  <!-- End Sasedeve edited by vyankatesh  on Date 31-01-2017 -->
  
  
  
  
  
  
   <p class="change_p_h6">
     ${student.facilitatorName} is your Guidance Specialist for these three sessions. ${student.facilitatorName} is an expert in the Lodestar Career Guidance process and will hand hold you through this process to help you make the right decision - a Smart Career Decision.
   </p>
   
   <!-- Start sasedeve edited by vyankatesh on date 1-2-2012 -->
			    <c:if test="${student.dueAmount != null && student.dueAmount != '0.00'}">
			   <p class="change_p_h6" style="line-height:30px;">&nbsp;&nbsp;&nbsp; Balance Course Fee to be paid ,  Rs
			     &nbsp;<span class="date_spn" style="color:red">${student.dueAmount} </span>
			     ( Please ignore if already paid ). </p>
			   
			 </c:if>

   
  
   <!-- End sasedeve edited by vyankatesh on date 1-2-2012 -->
   
 </div>
</div>
</div>

<div id="mainPage_2" style="display: none;">
  <div class="row">
   <div class="col-md-12 change_page_div_1">
      <p class="change_p_h6">Before you begin session 1 on <b>${student.session1DateStr}</b>, you have to complete the following two tasks</p>
      <p class="change_p_h6">a) Fill the <b>Tell US More (TUM) form.</b></p> 
      <p class="change_p_h6"> b) Take the <b>Career Guidance Test (CGT)</b></p>
   </div>
    <!--Content 1  -->
   <div class="col-md-12" style="padding-top: 20;">
      <div class="instruction_txt_div">
             <div class="change_icon_left_div">
                 <img alt="" src="../images/icons/welcome-TUM.gif" >
             </div>
             <div class="change_right_div change_p_h6">
                <p class="change_p_h3" style="line-height: 36px;padding-top: 5px;">TUM form</p>
                <span class="change_p_h4">About yourself, your likes, dislikes, your strengths and your questions</span>
             </div>
         </div>
         
         <div class="instruction_txt_div" style="padding-top: 20px;">
             <div class="change_icon_left_div">
               &nbsp;
             </div>
             <div class="change_right_div change_p_h6">
                 <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-circle" style="font-size: 8px;"></i>
                    </div>
                   <div class="change_right_div change_p_h6">
                   	 Before you start kindly go through all the questions in the form and make a note of the information required to fill this in.
                   </div>
         		</div>
         		
         		 <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-circle" style="font-size: 8px;"></i>
                    </div>
                   <div class="change_right_div change_p_h6">
                    Take some time (say an hour) to think through some of the questions before you actually fill the answers in the form. You can also write down the answers on a piece of paper and then type them in.
                   </div>
         		</div>
             </div>
         </div>
         
         <div class="instruction_txt_div">
             <div class="change_icon_left_div">
                       &nbsp;
             </div>
             <div class="change_right_div change_p_h6">
                 <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-hand-o-right"></i>:&nbsp;
                    </div>
                   <div class="change_right_div change_p_h6">
                     TIP: It may be a good idea to discuss some parts of it with your parents before you fill this in -
                      though this is not mandatory. We want to hear from you, we want to know you as a person.
                   </div>
         		</div>
             </div>
         </div>
         
   </div> <!--Content 1 end  -->
   
   <!--Content 2  -->
      <div class="col-md-12" style="padding-top: 20;">
      <div class="instruction_txt_div">
             <div class="change_icon_left_div">
                  <img alt="" src="../images/icons/welcome-TUM.gif" >
             </div>
             <div class="change_right_div change_p_h6">
                <p class="change_p_h3" style="line-height: 36px;padding-top: 5px;">Career Guidance Test - CGT</p>
                <span class="change_p_h4">Test to understand your interest and aptitude</span>
             </div>
         </div>
         
        <!--  <div class="instruction_txt_div" style="padding-top: 20px;">
             <div class="change_icon_left_div">
               &nbsp;
             </div>
             <div class="change_right_div change_p_h6">
                <p class="change_p_h6">
                  <b>Career Guidance Test (CGT)</b>: This is a test which will again help us understand your interests and aptitude better. This test has two sections:
                </p>
               
             </div>
         </div> -->
         
           <div class="instruction_txt_div" style="padding-top: 20px;">
             <div class="change_icon_left_div">
               &nbsp;
             </div>
             <div class="change_right_div change_p_h6">
                <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-circle" style="font-size: 8px;"></i>
                    </div>
                  <div class="change_right_div change_p_h6">
                     This test has two sections:  Section A - Interest Inventory & Section B - Aptitude Assessment.
                  </div>
         	    </div>
         	    
         	     <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-circle" style="font-size: 8px;"></i>
                    </div>
                  <div class="change_right_div change_p_h6">
                     Both sections have 60 questions.
                  </div>
         	    </div>
         	    
         	     <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-circle" style="font-size: 8px;"></i>
                    </div>
                  <div class="change_right_div change_p_h6">
                     Section A - There is no time limit to finish this section, students usually take 20-25 minutes. This section helps us understand your interests (that we will link to possible career options). Hence, be very straightforward and truthful in telling us what you like and what you do not.
                  </div>
         	    </div>
         	    
         	     <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-circle" style="font-size: 8px;"></i>
                    </div>
                  <div class="change_right_div change_p_h6">
                     Section B - Aptitude Assessment is a timed section. You will be given 60 minutes to finish answering it. <b>You should ensure that you have this time before you start the test</b>.
                  </div>
         	    </div>
         	    
         	     <div class="instruction_txt_div">
                    <div class="change_icon_left_div" style="width: 3%">
                       <i class="fa fa-circle" style="font-size: 8px;"></i>
                    </div>
                  <div class="change_right_div change_p_h6">
                     In case your computer or test shuts down, you can re-login and take the test from the point where you stopped last.
                  </div>
         	    </div>
               
             </div>
         </div>
         
           <!--  <div class="instruction_txt_div" style="padding-top: 20px;">
             <div class="change_icon_left_div">
               &nbsp;
             </div>
             <div class="change_right_div change_p_h6">
                <p class="change_p_h6">
                  <b>Section B</b> - Aptitude Assessment is a timed section. You will be given > mins to finish this. You should ensure that you have this time before you start this test. This section is like any typical aptitude test and it has 
					questions related to verbal ability, reasoning, numerical ability, spatial ability and so on.  You do not need any 
					calculating device to attempt this test.  In case your computer or test shuts down - you can re-login and take 
					the test from the point where you stopped last.
                </p>
               
             </div>
         </div> -->
         
          <div class="instruction_txt_div" style="padding-top: 20px;">
            <div class="change_icon_left_div">
              &nbsp;
            </div>
            <div class="change_right_div change_p_h6">
               <p class="change_p_h6">
                 <b>Please note:&nbsp;</b>You have to fill in both the TUM form and complete the CGT before <b>${student.session1DateStr}</b>. In case you face any 
                difficulty in accessing the TUM form or CGT please call  ${student.facilitatorName}  or Kalpana on 080 26714555.
               </p>
              
            </div>
        </div>
     
         
   </div> <!--Content 2 end -->
   
   
   
   
  </div>
</div>

  <div class="row welcomescrennbottom" style="padding-left: 17px;">
      <button class="btn btn_action change_next" onclick="fnNext();"><span class="next-spn"><s:text name="com.edupath.common.next"></s:text></span></button>
      <button class="btn btn_action btn-back change_back_btn" onclick="fnBack();"><span class="back-spn"><s:text name="com.edupath.common.label.back"></s:text></span></button>
      <button class="btn btn_action btn-back change_back_btn" onclick="fnGetStart();"><span class="back-spn"><s:text name="com.edupathstudent.tum.welcome.getstart"></s:text></span></button>
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