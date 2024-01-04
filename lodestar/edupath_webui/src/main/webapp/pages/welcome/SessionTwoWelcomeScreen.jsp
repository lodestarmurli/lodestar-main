<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
<s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
<div class="edupath-padding">
	<div class="rows">
		<div class="col-md-12">
			<p class="change_p_h6">Dear ${studentDTO.name}</p>
			
			
			
			
			<p class="change_p_h6">You have completed your second discussion with ${studentDTO.facilitatorName} and prioritised your career options. I am sure you are also now quite clear about the education path to reach your True Career.  Now it is time for you to fine-tune the details of your education path and for that you have to complete the following tasks before your third session on ${studentDTO.session3DateStr}</p>
			
			<!-- Start Sasedeve Edited By Mrutyujaya on date 25-07-2017 -->
			<c:if test="${IsSessiontwoFeedBack}">
			<p style="color:red;"><b>Message to Parent : Kindly fill in session 2 "FeedBack" to continue to the next step.</b></p>
			</c:if>
			<!-- End Sasedeve Edited By Mrutyujaya on date 25-07-2017 -->
			
			<br>
			
			<p class="change_p_h6"><b>Read</b> the <b>Course reports</b> for the different elective combinations you have chosen in the last session - click on Review Electives link for this.</p>
			<div class="instruction_txt_div">
            	<div class="change_icon_left_div" style="width: 3%; margin-left: 15px;">
                   <i class="fa fa-circle" style="font-size: 8px;"></i>
                </div>
                <div class="change_right_div change_p_h6">
                   <b>Prioritise</b> the Elective combinations based on your reading and understanding of the subjects.
                   <p><b></>Select College / School parameters</b> you would like to use to shortlist colleges/schools in the next session. You can do this by clicking on the College Parameter link. When you do this we suggest that you discuss with your parents also, as these parameter are very important for your next decision.</p>
                </div>
			</div>
			
			<!-- <div class="instruction_txt_div">
            	<div class="change_icon_left_div" style="width: 3%; margin-left: 15px;">
                   <i class="fa fa-circle" style="font-size: 8px;"></i>
                </div>
                <div class="change_right_div change_p_h6">
                   <b>Shortlist</b> upto 6 areas which you like
                </div>
			</div>
			
			<div class="instruction_txt_div">
            	<div class="change_icon_left_div" style="width: 3%; margin-left: 15px;">
                   <i class="fa fa-circle" style="font-size: 8px;"></i>
                </div>
                <div class="change_right_div change_p_h6">
                   <b>Prioritise</b> upto 2 out of the 6 shortlist in the <b>My ShortList</b> link
                </div>
			</div>
			<div style="clear: both;"></div><br/>
			<p class="change_p_h6">Click on <b>My WishList</b> to view documents and read them.</p> 
			<div class="instruction_txt_div">
            	<div class="change_icon_left_div" style="width: 3%; margin-left: 15px;">
                   <i class="fa fa-circle" style="font-size: 8px;"></i>
                </div>
                <div class="change_right_div change_p_h6">
                   The documents make take a few minutes to download - please wait till they all load.
                </div>
			</div>
			
			<div class="instruction_txt_div">
            	<div class="change_icon_left_div" style="width: 3%; margin-left: 15px;">
                   <i class="fa fa-circle" style="font-size: 8px;"></i>
                </div>
                <div class="change_right_div change_p_h6">
                   We have provided many interesting features to help you read and shortlist like <b> Related Occupations, Notes, Highlight, Adding Summary of document</b> to Notes, <b>Resume Report Reading</b> and the most important <b>Add to Shortlist</b>.  Make full use of these to make your experience easy and enjoyable.
                </div>
			</div> -->
			
			<div class="instruction_txt_div">
			<c:if test="${studentDTO.session3DateStr != null }">
            	<div class="change_icon_left_div" style="width: 3%; margin-left: 15px;">
                  
                </div>
                
                <div class="change_right_div change_p_h6">
                <br>
                   In your next session your career guide will share detailed information related to +2 Schools/PU colleges and also relevant Tutorial institutes. 
				<br>
                </div>
                </c:if>
			</div>
			<div style="clear: both;"></div><br/>
			<c:if test="${studentDTO.session3DateStr != null }">
			<p class="change_p_h6">Your next sessions are on</p>
   			<%-- <p class="change_p_h6" style="padding-left: 20px; line-height: 0; padding-top: 5px;">Session 2: &nbsp;&nbsp;  <b>${studentDTO.session2DateStr}</b></p> --%>
    		<div class="col-md-12">
					<div class="col-md-5">
						<p class="change_p_h6" style="line-height: 30px;">
							<i class="fa fa-calendar"></i> &nbsp;&nbsp; Session 3:
							&nbsp;&nbsp; <span class="date_spn">${studentDTO.session3DateStr}</span>
						</p>
					</div>
					<div class="col-md-7">
						<p class="change_p_h6" style="line-height: 30px;"> <i class="fa fa-map-marker"></i> &nbsp;&nbsp;Venue :&nbsp;&nbsp;<span class="date_spn">${studentDTO.venue}</span></p>
						
					</div>
					 
				</div>
				</c:if>
				<br>
			<p class="change_p_h6" style="margin-left:15px">Enjoy Discovering your True Career!</p>
			<c:if test="${studentDTO.dueAmount != null && studentDTO.dueAmount != '0.00'}">
			   <p class="change_p_h6" style="line-height:30px;">&nbsp;&nbsp;&nbsp; Balance Course Fee to be paid ,  Rs
			     &nbsp;<span class="date_spn" style="color:red">${studentDTO.dueAmount}&nbsp; &nbsp; </span>
			    ( Please ignore if already paid ). </p>
							    
			   
			   
			 </c:if>

			
			
		</div> 
	</div>
</div>