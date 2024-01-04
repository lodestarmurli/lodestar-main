
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
				<div class="pagetitle">
					<s:text name="com.edupath.exploremoreoccupations.header.exploremoreoccupationsresult"></s:text>
					
				</div>
				<div class="sectionheading">
					<s:text name="com.edupath.exploremoreoccupations.addoccnote"></s:text>
					<div class="pull-right">
				   		<a class="readmore" href="#" onclick="return fnResetSearch();" style="text-decoration: underline;"><span class="next-spn"><s:text name="com.edupath.common.resetsearch"></s:text></span></a>
				   	</div>
				   	<!-- Start SASEDEVE edited by Mrutyunjaya on date 14-07-2017 -->  
									 <br/> <br/>
		                            <span class="fa fa-circle-o fa-1x" style="color:black;"></span>
								    <span class="sectionheading">&nbsp;&nbsp;Abilities fitment</span>&nbsp;&nbsp;
									<span class="fa fa-square-o fa-1x" style="color:black;"></span><span class="sectionheading">&nbsp;&nbsp;Interest fitment</span>
									<br/><br/>
									<span class="fa fa-tag fa-lg" style="color:green"></span><span class="sectionheading">&nbsp;&nbsp;High</span>
									<span class="fa fa-tag fa-lg" style="color:yellow"></span><span class="sectionheading">&nbsp;&nbsp;Above Average</span>
									<span class="fa fa-tag fa-lg" style="color:orange"></span><span class="sectionheading">&nbsp;&nbsp;Average</span>
									<span class="fa fa-tag fa-lg" style="color:red"></span><span class="sectionheading">&nbsp;&nbsp;Low</span>
									<br/>
									<!-- END SASEDEVE edited by Mrutyunjaya on date 14-07-2017 -->  
				   	
				</div>
				<div>
					<div class="questiondiv">
						<span class="questionspan">${preferedQuestions}</span>
						
					</div>
					<div class="row top-buffer" style="min-height: 350px">
						<div class="col-md-12">
								<c:forEach items="${manualSearchMap}" var="manualanswers">
									<div class="col-md-4">
										<div class="industryTitle">
											${utils:replaceXMLEntities(manualanswers.key)}
										</div>
										<c:forEach items="${manualanswers.value}" var="occupation">
											<c:forEach items="${occupation.industries}" var="industry" varStatus="slno">
												<c:choose>
													<c:when test="${industry.isPrimary}">
														<c:set var="industryName">
															${industry.name}
														</c:set>
														<c:set var="industryId">
															${industry.id}
														</c:set>
													</c:when>
												</c:choose>
											</c:forEach>	
											<div class="checkbox checkbox-primary img-rounded occupationnamediv">
												<span class="occupationname">
													<c:set var="rollOverContent">
													<!-- Start Sasedeve edited by Mrutyunjaya on date 14-07-2017 -->
					 <b><s:text name="com.edupath.system.recommendation.requiredcode"/></b>:  ${occupation.requiredRAISEC}
					</br>
					 <b><s:text name="com.edupath.system.recommendation.requiredability"/></b>: ${occupation.requiredAbility}
					</br></br>
													
					<!-- END Sasedeve edited by Mrutyunjaya on date 14-07-2017 -->
													
													
													
														${occupation.rollOverContent}
													</c:set>
												
													<input type="checkbox" id="occ_c_${occupation.id}" class="occupation_chackbox" data-indusid="${industryId}">
													
										
													
													<label for="occ_c_${occupation.id}" data-toggle="occpopover" data-content="${utils:replaceXMLEntities(rollOverContent)}" id="${occupation.id}" class="occ_c_${occupation.id}">
														
														
														<!-- BEGIN Sasedeve Added by Mrutyunjaya on Date 15-05-2017 -->
																<i class="fa fa-circle fa-fw ${fitmentcolors[occupation.fitment]}" aria-hidden="true"></i>
													
													
												 	<i class="fa fa-square fa-fw ${fitmentcolors[occupation.newfitment]}" aria-hidden="true"></i> 
													
													<!-- END Sasedeve Added by Mrutyunjaya on Date 15-05-2017 -->
														
														${utils:replaceXMLEntities(occupation.name)}
													</label>
												</span>	
											</div>
										</c:forEach>
									</div>
								</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div style="display: none;">
				<jsp:include page="/pages/induoccchoice/LeftOccupationWishList.jsp"></jsp:include>
			</div>
			<div class="modal fade" id="timerPOPUP" role="dialog" style="width: 400px;position: fixed;left: auto;top: -120;right: 10px">
			    <div class="modal-dialog">
			      <div class="modal-content" style="width: 300px !important;">
			
			        <div class="modal-body" style="text-align: center; font-size: 11pt;">
					 <label id="message_lbl"></label>
			        </div>
			      </div>
			    </div>
			</div>
<script>
$(document).ready(function(){
	fnInitRollOver('occpopover','right');
});

 $('input[type="checkbox"]').change(function() {
	 	var msg="";
		if (this.checked) {
			fnAddWishList($(this).attr("id"), $(this).data("indusid"), 1, false);
			fnStartTimer("Added to Wishlist");
		} 
}); 
 
 function fnCloseModal()
 {
 	clearInterval(startTimer);
 	$('#timerPOPUP').modal('hide');
 	fnDiscard();
 }

 function fnStartTimer(msg)
 {
 	$('#message_lbl').html(msg);
 	$('#timerPOPUP').modal();
 	startTimer = setInterval(fnCloseModal,2000);
 }
 
</script>