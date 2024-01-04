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
<div id="courseMainDiv" style="margin-left: 30px; margin-top: 10px;">
	<%-- <s:form name="entranceExamForm" method="post"> --%>
	<s:hidden name="occId" id="occId"/>
	<s:hidden name="occIndusryId" id="occIndusryId"/>
	<div id="courseDetailsId">
		<div class="row">
			<div class="col-md-11">
				<div class="row">
					<div class="col-md-1">
						
					</div>
					<div class="col-md-10">
						
					</div>
					<div class="col-md-1">
						<div id="backToEdupath" class="entr_exam_back" style="margin-left: 45px; margin-top: 5px;">
							<a href="#" onclick="fnReturnBackToEduPath();">Back</a>
						</div>
					</div> 
				</div>
				<c:choose>
					<c:when test="${not empty courseMap}">
		        		<c:forEach items="${courseMap}" var="courseMap">
							<div class="row" style="margin-top: 20px;">
								<span class="level_title">${courseMap.key}</span>
							</div>
							<div class="row" style="margin-top: 20px;">
								<table class="table table-bordered course_table">
							        <thead>
							            <tr>
							                <th class="col-md-3"><s:text name="com.edupath.integratedcourse.detail.programname"/></th>
							                <th class="col-md-2"><s:text name="com.edupath.integratedcourse.detail.institute"/></th>
							                <th class="col-md-2"><s:text name="com.edupath.integratedcourse.detail.location"/></th>
							                <th class="col-md-3"><s:text name="com.edupath.integratedcourse.detail.eligibility"/></th>
							                <th class="col-md-1"><s:text name="com.edupath.integratedcourse.detail.courseduration"/></th>
							            </tr>
							        </thead>
							        <tbody>
										 <c:forEach items="${courseMap.value}" var="courseDetails">
								            <tr>
								                <td>
								                	<a href="#" onclick="fnShowExamDetails('${courseDetails.id}');"> 
								                		${courseDetails.programName}
							                		</a>
							                	</td>
								                <td>${courseDetails.institute}</td>
								                <td>${courseDetails.location}</td>
								                <td>${courseDetails.eligibility}</td>
								                <td>${courseDetails.courseDuration}</td>
								            </tr>
				    					</c:forEach>
					        		</tbody>
					    		</table>
							</div>
			            </c:forEach>
	            	</c:when>
			        <c:otherwise>
			        	<div class="row" style="margin-top: 20px;">
							<span class="occupationname">No records to display</span>
						</div>
			        </c:otherwise>    
            	</c:choose>
			</div>
			<div class="col-md-1">
				
			</div> 	
		</div>	
	</div>
	<div id="coursedetails">
		<div class="row">
			<div class="col-md-10">
				<c:forEach items="${courseList}" var="integCourseDetails">
						<div id="courseId_${integCourseDetails.id}" style="display: none"> 
							<div class="row">
								<span class="pagetitle">${integCourseDetails.programName}</span>
							</div>
							<div class="row" style="margin-top: 10px;">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.description"/></span>
								<p class="entr_exam_details">${integCourseDetails.description}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.institute"/></span>
								<p class="entr_exam_details">${integCourseDetails.institute}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.location"/></span>
								<p class="entr_exam_details">${integCourseDetails.location}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.eligibility"/></span>
								<p class="entr_exam_details">${integCourseDetails.eligibility}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.entrance"/></span>
								<p class="entr_exam_details">${integCourseDetails.entrance}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.selectionprocess"/></span>
								<p class="entr_exam_details">${integCourseDetails.selectionProcess}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.noofseats"/></span>
								<p class="entr_exam_details">${integCourseDetails.noOfSeats}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.feestructure"/></span>
								<p class="entr_exam_details">${integCourseDetails.feeStructure}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.programtype"/></span>
								<p class="entr_exam_details">${integCourseDetails.programType}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.courseduration"/></span>
								<p class="entr_exam_details">${integCourseDetails.courseDuration}</p>
							</div>
						</div>
				</c:forEach>
			</div>
			<div class="col-md-2" id="retuId" style="display: none">
				<div id="backToEntrId" class="occhoverreturnlabel">
					<a href="#" onclick="fnReturnBackToEntrance();">Return</a>
				</div>
			</div>
		</div>
	</div>	
</div>
<script>
	var form = document.entranceExamForm;
	$(document).ready(function() {
		
	});
	var divId = 0;
	
	function fnShowExamDetails(id)
	{
		divId = id
		$("#courseId_"+divId).show();
		$("#courseDetailsId").hide();
		$("#retuId").show();
	}
	
	function fnReturnBackToEntrance()
	{
		$("#retuId").hide();
		$("#courseId_"+divId).hide();
		$("#courseDetailsId").show();
	}
	
	function fnReturnBackToEduPath()
	{
		$("#courseMainDiv").hide();
		$("#globalSearchMainDiv").show();
	}
</script>

<style type="text/css">
.course_table tr td
{
	color:#777c7f !important;
}
</style>