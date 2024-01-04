<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib prefix="fn" uri="fn.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<style type="text/css">
	p
	{
		margin-top: 5px;
		margin-left: 20px;
	}
</style>
<c:set var="seatCount" value="0"></c:set>
<c:set var="staffCount" value="0"></c:set>
<c:set var="minFee" value="0"></c:set>

<div id="studCartColDetailsDiv" style="margin-left: 30px; margin-top: 10px;">
		<div class="row">
			<div class="col-md-10">
				<c:if test="${not empty collegeList}">
				<div id="collegeDetails">
					<c:forEach items="${collegeList}" var="collegeDetails">
							<div id="collegeId" > 
								<div class="row">
									<span class="pagetitle">${collegeDetails.name}</span>
								</div>
								<div class="row"  style="margin-top: 10px;">
									<span class="occupationname"><s:text name="com.edupath.college.detail.affiliation"/></span>
									<p class="entr_exam_details">${collegeDetails.boardDTO.name}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.college.detail.ageofinstitute"/></span>
									<p class="entr_exam_details">${collegeDetails.ageOfTheInstitute}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.college.detail.collegetype"/></span>
									<p class="entr_exam_details">${collegeDetails.collegeType}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.college.detail.coachingfacility"/></span>
									<p class="entr_exam_details">
										<c:choose>
											<c:when test="${collegeDetails.coachingFacility == true}">
												Yes ( ${collegeDetails.coachingCentreName} )
											</c:when>
											<c:otherwise>
												NO
											</c:otherwise>
										</c:choose>
									</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.college.detail.streams"/></span>
									<p class="entr_exam_details">
										<c:forEach items="${collegeDetails.streamsList}" var="streams" varStatus="loop">
											<c:if test="${loop.index ne 0}">
												,
											</c:if>
											${streams.name}
										</c:forEach>
									</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.college.detail.combinations"/></span>
									<p class="entr_exam_details">
										<c:forEach items="${collegeDetails.combinationList}" var="combination" varStatus="loop">
											<c:if test="${loop.index ne 0}">
												,
											</c:if>
											${combination.name} 
										</c:forEach>
									</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.college.detail.noofseats"/></span>
									<p class="entr_exam_details" id="colSeatId"></p>
								</div>
								<c:forEach items="${collegeDetails.streamsList}" var="stream">
									<div class="row">
										<span class="occupationname"><s:text name="com.edupath.college.detail.cutoffpercentage"/> ${stream.name}</span>
										<p class="entr_exam_details" id="cutOff_${stream.id}">
											<c:forEach items="${collegeDetails.collegeCourseFeeSeatsList}" var="course">
											  <c:if test="${course.streamId eq stream.id }">
											  	 <%-- ${course.cutoff} --%>
											  	 <c:set var="seatCount" value="${seatCount + course.seats}"></c:set>
											  </c:if>
											</c:forEach>
											<c:forEach items="${collegeDetails.collegeStreamDetailsList}" var="colStreamDetails">
											  <c:if test="${colStreamDetails.streamId eq stream.id }">
											  	  ${colStreamDetails.lastCutOff}
											  </c:if>
											</c:forEach>
										</p>
									</div>
								</c:forEach>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.college.detail.minfee"/></span>
									<c:forEach items="${collegeDetails.collegeCourseFeeSeatsList}" var="colCourFee" varStatus="loop">
										<c:if test="${loop.index eq 0}">
											<c:set var="minFee" value="${colCourFee.fees}"></c:set>
										</c:if>
										<c:if test="${colCourFee.fees < minFee}">
											<c:set var="minFee" value="${colCourFee.fees}"></c:set>
										</c:if>
									</c:forEach>	
									<p class="entr_exam_details" id="minFeeId">
									</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.college.detail.staff"/></span>
									<p class="entr_exam_details" id="staffId">
										<c:forEach items="${collegeDetails.collegeStreamDetailsList}" var="collegeStreams">
											<c:set var="staffCount" value="${staffCount + collegeStreams.noteachingStaff}"></c:set>
										</c:forEach>
									</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.college.detail.address"/></span>
									<p class="entr_exam_details">${collegeDetails.address}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.college.detail.website"/></span>
									<p class="entr_exam_details">${collegeDetails.website}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.college.detail.applastdate"/></span>
									<p class="entr_exam_details">${collegeDetails.appLastDateStr}</p>
								</div>
								
								<c:forEach items="${collegeActivitiesDetails}" var="colActivityDetails">
									<div class="row">
										<span class="occupationname">${colActivityDetails.name}</span>
										<c:set var="name" value="${colActivityDetails.name}"/>
										<c:set var="exists" value="No"/>
										<c:forEach items="${collegeActivityMap}" var="colActivity">
											<c:if test="${colActivity.key eq name}">
												<c:set var="exists" value="Yes"/>
											</c:if>
										</c:forEach>
										<p class="entr_exam_details">${exists}</p>
									</div>
								</c:forEach>
								
								<c:forEach items="${collegeInfraDetails}" var="colInfrDetails">
									<div class="row">
										<span class="occupationname">${colInfrDetails.name}</span>
										<c:set var="name" value="${colInfrDetails.name}"/>
										<c:set var="exists" value="No"/>
										<c:forEach items="${collegeInfraMap}" var="colInfra">
											<c:if test="${colInfra.key eq name}">
												<c:set var="exists" value="Yes"/>
											</c:if>
										</c:forEach>
										<p class="entr_exam_details">${exists}</p>
									</div>
									<c:if test="${colInfrDetails.count > 0 and colInfrDetails.capacity> 0}">
										<div class="row">
											<span class="occupationname">No of ${colInfrDetails.name} and Capacity</span>
											<p class="entr_exam_details">${colInfrDetails.count} and ${colInfrDetails.capacity}</p>
										</div>
									</c:if>
								</c:forEach>
								
							</div>
					</c:forEach>
					</div>
				</c:if>
			</div>
			<div class="col-md-2" id="retuId">
				<div id="backTocollegeId" class="entr_exam_back">
					<a href="#" onclick="fnReturnBackToCart();">
						<s:text name="com.edupath.common.label.return.button"></s:text>
					</a>
				</div>
			</div>
		</div>
</div>

<script>
$(document).ready(function() 
{
	$("#colSeatId").text('${seatCount}')
	$("#staffId").text('${staffCount}');
	$("#minFeeId").text('Rs. '+'${minFee}');
});

function fnReturnBackToCart()
{
	$('#studCartColContentDiv').hide();
	$('#cartDIV').show();
	$("#myCart").scrollTop(0);
}

</script>