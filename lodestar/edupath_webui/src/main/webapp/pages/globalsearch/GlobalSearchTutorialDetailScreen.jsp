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
<div id="tutorialMainDiv" style="margin-left: 30px; margin-top: 10px;">
	<div id="tutorialDetails">
		<div class="row">
			<div class="col-md-10">
				<c:choose>
					<c:when test="${not empty tutorialList}">
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
								</div>
							</c:forEach>
						</div>
					</c:when>
					<c:otherwise>
						<div class="row" style="margin-top: 20px;">
							<c:if test="${empty tutorialDetailsList}">
								<span class="occupationname">No records to display</span>
							</c:if>
						</div>
					</c:otherwise>
				</c:choose>
				<c:if test="${not empty tutorialList}">
					
				</c:if>
				<c:if test="${not empty tutorialDetailsList}">
						<div class="row" id="tutDetDiv">
							<span class="occupationname"><s:text name="com.edupath.tutorial.detail.name"/></span>
							<p class="entr_exam_details">
								<c:forEach items="${tutorialDetailsList}" var="tutorialDetails" varStatus="loop">
									<c:if test="${loop.index ne 0}">
										,
									</c:if>
									<a href="#" onclick="fnGetTutorialDetails('${tutorialDetails.id}')">
										${tutorialDetails.name}
									</a>
								</c:forEach>
							</p>
						</div>
				</c:if>
			</div>
			<div class="col-md-2" id="retuId">
				<div id="backTotutorialId" class="entr_exam_back">
					<a href="#" onclick="fnReturnBackToEduPath();">Back</a>
				</div>
			</div>
		</div>
	</div>
	<div id="examTutDetailsDiv" style="display: none;" class="row">
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
				<a href="#" onclick="fnReturnBackToTutorial();">Return</a>
			</div>
		</div>
	</div>	
</div>

<script>
var tutorialObject = {};
$(document).ready(function() 
{
	<c:forEach items="${tutorialDetailsList}" var="tutorialDetails">
		tutorialObject['${tutorialDetails.id}'] = '${tutorialDetails.name}';
	</c:forEach>
});

function fnReturnBackToEduPath()
{
	if ('${not empty tutorialDetailsList}' == 'true' && '${not empty tutorialList}' == 'true' && isTutorialReturn) {
		$("#tutorialDetailsInsideDiv").hide();
		$("#tutDetDiv").show();
		isTutorialReturn = false;
		return false;
	}
	$("#tutorialMainDiv").hide();
	$("#globalSearchMainDiv").show();
}

function fnShowExamTutDetails(divId)
{
	$('.exam-details').hide();
	$("#examTutDetailsDiv").show();	
	$("#showExamId_"+divId).show();
	$("#tutorialDetails").hide();
}

function fnReturnBackToTutorial()
{
	$("#tutorialDetails").show();
	$("#examTutDetailsDiv").hide();
}

function fnGetTutorialDetails(id)
{
	isTutorialReturn = true;
	url = '${pageContext.request.contextPath}/myapp/getTutorialByDataGlobalSearch';
	$.ajax({
		url : url,
		type : "POST",
		beforeSend: function( xhr ) 
 		{
 			$('.globalSearchLoading').show();
		},
		data : ({
			"tutorialId" : id,
			"tutorialObject" : tutorialObject
		}),
		success: function(resp)
 		{
			$('.globalSearchLoading').hide();
 			if(resp != "" && resp != null)
 			{
 				$("#tutorialDiv").html(resp);
 				$("#contentDiv").show();
 				$("#tutorialDiv").show();
 				$("#globalSearchMainDiv").hide();
 				$("#entrExamDiv").hide();
 				$("#collegeDiv").hide();
 				$("#integCourseDiv").hide();
 				$("#tutDetDiv").hide();
 			}	
 		},
 		error :  function(msg,arg1,arg2)
 		{
 			$('.globalSearchLoading').hide();
 			alert("page load error");
 			return false;
 		}
	});
}
</script>