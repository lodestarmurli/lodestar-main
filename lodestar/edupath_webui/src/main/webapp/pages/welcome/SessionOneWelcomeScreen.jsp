<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>

<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
<s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 

	<div class="edupath-padding">
		<div class="col-md-12">
			<p class="change_p_h6">Dear ${studentDTO.name}</p>
			
			
			
			
			<p class="change_p_h6">You have completed your first discussion with ${studentDTO.facilitatorName}. You are now about to begin the exciting part of exploring the Careers that you are interested in!! </p>
		
		<!-- Start Sasedeve Edited By Mrutyujaya on date 25-07-2017 -->
			<c:if test="${IsSessionOneFeedBack}">
			<p style="color:red;"><b>Message to Parent : Kindly fill in session 1 "FeedBack" to continue to the next step.</b></p>
			</c:if>
			<!-- End Sasedeve Edited By Mrutyujaya on date 25-07-2017 -->
		
		<br>
			<p class="change_p_h6">Before your next session on ${studentDTO.session2DateStr} you have to:</p>
			<div class="instruction_txt_div">
            	<div class="change_icon_left_div" style="width: 3%; margin-left: 15px;">
                   <i class="fa fa-circle" style="font-size: 8px;"></i>
                </div>
                <div class="change_right_div change_p_h6">
                  <b> Read</b> the documents which you chose in Session 1
                </div>
			</div>
			
			<div class="instruction_txt_div">
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
			</div>
			
			<div class="instruction_txt_div">
            	<div class="change_icon_left_div" style="width: 3%; margin-left: 15px;">
                   <i class="fa fa-circle" style="font-size: 8px;"></i>
                </div>
                <div class="change_right_div change_p_h6">
                   The <b>Explore More Occupations</b> link is an easy way to search for more occupations of your interest and add them to your WishList - In case you wish to.
                </div>
			</div>
			<div style="clear: both;"></div><br/>
			
			<p class="change_p_h6">Your next sessions are on</p>
   			<div class="col-md-12">
					<div class="col-md-5">
						<p class="change_p_h6" style="line-height: 30px;">
							<i class="fa fa-calendar"></i> &nbsp;&nbsp; Session 2:
							&nbsp;&nbsp; <span class="date_spn">${studentDTO.session2DateStr}</span>
						</p>
					</div>
					<div class="col-md-7">
						<p class="change_p_h6" style="line-height: 30px;"> <i class="fa fa-map-marker"></i> &nbsp;&nbsp;Venue :&nbsp;&nbsp;<span class="date_spn">${studentDTO.venue}</span></p>
						
					</div>
				</div>
				<c:if test="${studentDTO.session3DateStr != null }">
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
			<p class="change_p_h6" style="margin-left:15px">Enjoy Discovering your True Career!</p>
			
			<c:if test="${studentDTO.dueAmount != null && studentDTO.dueAmount != '0.00'}">
			   <p class="change_p_h6" style="line-height:30px;">&nbsp;&nbsp;&nbsp;&nbsp; Balance Course Fee to be paid ,  Rs
			     &nbsp;<span class="date_spn" style="color:red"> &nbsp; ${studentDTO.dueAmount}  &nbsp; &nbsp; </span>
			    ( Please ignore if already paid ). </p>
							    
			   
			   
			 </c:if>

			
			
		</div> 
	</div>