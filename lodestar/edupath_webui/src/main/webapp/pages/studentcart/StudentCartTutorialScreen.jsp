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
<c:set var="tutorialName" value=""/>
<div id="studCartTutDetailsDiv" class="cust-marg-top-10">
	<div id="studCartTutorialDetails">
		<div class="row">
			<div class="col-md-10">
				<c:if test="${not empty tutorialList}">
					<div id="tutorialDetailsInsideDiv">
					<c:forEach items="${tutorialList}" var="tutorialDetail">
							<c:set var="tutorialName" value="${tutorialDetail.name}" />
							<div id="tutorialId" > 
								<div class="row">
									<span class="pagetitle">${tutorialDetail.name}</span>
								</div>
								<div class="row" style="margin-top: 10px;">
									<span class="occupationname"><s:text name="com.edupath.tutorial.detail.noofcentersinindia"/></span>
									<p class="entr_exam_details">${tutorialDetail.centersInIndia}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.tutorial.detail.noofcentersincity"/></span>
									<p class="entr_exam_details">${tutorialDetail.centersInCity}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.tutorial.detail.yearofestab"/></span>
									<p class="entr_exam_details">${tutorialDetail.yearOfEst}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.tutorial.detail.examnames"/></span>
									<p class="entr_exam_details">
										<c:forEach items="${tutorialDetail.examTutorialList}" var="examTutDetails" varStatus="loop">
											<c:if test="${loop.index ne 0}">
												,
											</c:if>
											<a href="#showExamId_${examTutDetails.id}" onclick="fnShowExamTutDetails('${examTutDetails.id}')">
												${examTutDetails.name} 
											</a>
										</c:forEach>
									</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.tutorial.detail.programtype"/></span>
									<p class="entr_exam_details">${tutorialDetail.programNames}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.tutorial.detail.admissioncriteria"/></span>
									<p class="entr_exam_details">${tutorialDetail.admissionCriteria}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.tutorial.detail.highestrank"/></span>
									<p class="entr_exam_details">${tutorialDetail.highestRank}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.tutorialcenters.ageofcenter"/></span>
									<p class="entr_exam_details">${tutorialDetail.ageOfCenter} years</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.tutorialcenters.nooffaculty"/></span>
									<p class="entr_exam_details">${tutorialDetail.noOfFaculty}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.tutorialcenters.timings"/></span>
									<p class="entr_exam_details">${tutorialDetail.timings}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.tutorialcenters.address"/></span>
									<p class="entr_exam_details">${tutorialDetail.address}</p>
								</div>
								<div class="row">
									<span class="occupationname"><s:text name="com.edupath.tutorialcenters.contact"/></span>
									<p class="entr_exam_details">${tutorialDetail.contactNumbers}</p>
								</div>
							</div>
					</c:forEach>
					</div>
				</c:if>
			</div>
			<div class="col-md-2" id="retuId">
				<div id="backTotutorialId" class="entr_exam_back">
					<a href="#" onclick="fnReturnBackToCart();">
						<s:text name="com.edupath.common.label.return.button"></s:text>
					</a>
				</div>
			</div>
		</div>
	</div>
	<div id="studCartExamTutDetailsDiv" style="display: none;" class="row">
		<div class="col-md-10">
			<c:forEach items="${examTutList}" var="examTut">
				<div id="showExamId_${examTut.id}" style="display: none;" class="exam-details">
					<div class="row" style="margin-top: 10px;">
						<span class="pagetitle">${tutorialName}</span>
						<p class="entr_exam_details"  style="font-size:11pt!important;">${examTut.name}</p>
					</div>
					<div class="row">
						<span class="occupationname"><s:text name="com.edupath.tutorial.detail.ranksseatsachieved"/></span>
						<p class="entr_exam_details">${examTut.noOfStudentsAchived}</p>
					</div>
					<div class="row">
						<span class="occupationname"><s:text name="com.edupath.tutorial.detail.noofstuenrolled"/></span>
						<p class="entr_exam_details">${examTut.noOfStudentsEnroll}</p>
					</div>
					<div class="row">
						<span class="occupationname"><s:text name="com.edupath.tutorial.detail.throghputratio"/></span>
						<p class="entr_exam_details">${examTut.throughPutRatio}</p>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="col-md-2">
			<div id="backTotutorialDetailsId" class="entr_exam_back">
				<a href="#" onclick="fnReturnBackToTutorial();">
					<s:text name="com.edupath.common.label.return.button"></s:text>
				</a>
			</div>
		</div>
	</div>	
</div>

<script>

function fnShowExamTutDetails(divId)
{
	$('.exam-details').hide();
	$("#studCartExamTutDetailsDiv").show();	
	$("#showExamId_"+divId).show();
	$("#studCartTutorialDetails").hide();
	$("#myCart").scrollTop(0);
}

function fnReturnBackToTutorial()
{
	$("#studCartTutorialDetails").show();
	$("#studCartExamTutDetailsDiv").hide();
}

function fnReturnBackToCart()
{
	$('#studCartTutContentDiv').hide();
	$('#cartDIV').show();
	$("#myCart").scrollTop(0);
}

</script>