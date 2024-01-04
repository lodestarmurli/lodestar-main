<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib prefix="fn" uri="fn.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 

<div class="courseMainDiv1" id="courseMainDiv" style="margin-left: 30px; margin-top: 10px;">
	<%-- <s:form name="entranceExamForm" method="post"> --%>
	<s:hidden name="occId" id="occId"/>
	<s:hidden name="occIndusryId" id="occIndusryId"/>
	<div id="courseDetailsId">
		<div class="row">
			<div class="col-md-11">
				<div class="row">
					<div class="col-md-1">
						<img alt="edupath-occ not found" src="${pageContext.request.contextPath}/images/icons/edupath-occupation-icon.gif" class="img-responsive">
					</div>
					<div class="col-md-10">
						<span class="pagetitle">${name}</span>
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
							<div class="row" style="margin-top: 5px;">
								<span class="level_title">${courseMap.key}</span>
							</div>
							<div class="row table-responsive SPlcoureswith" style="margin-top: 10px;">
								<table class="table table-bordered course_table" style="overflow-x:scroll;">
							        <thead>
							            <tr>
							                <th class="col-md-1 col-xs-1" style="text-align:center;"><s:text name="com.edupath.common.select"/></th>
							                <th class="col-md-3 col-xs-3 " style="white-space:normal"><s:text name="com.edupath.integratedcourse.detail.programname"/></th>
							                <th class="col-md-2 col-xs-2" style="white-space:normal"><s:text name="com.edupath.integratedcourse.detail.institute"/></th>
							                <th class="col-md-2 col-xs-2" style="white-space:normal"><s:text name="com.edupath.integratedcourse.detail.location"/></th>
							                <th class="col-md-3 col-xs-3" style="white-space:normal"><s:text name="com.edupath.integratedcourse.detail.eligibility"/></th>
							                <th class="col-md-1 col-xs-1" style="white-space:normal"><s:text name="com.edupath.integratedcourse.detail.courseduration"/></th>
							            </tr>
							        </thead>
							        <tbody style=" width: 90%; overflow: auto;">
										 <c:forEach items="${courseMap.value}" var="courseDetails">
								            <tr>
								                <td align="center" style="white-space:normal" class="col-md-1 col-xs-1">
								                	<div class="checkbox checkbox-primary">
														<input id="checkbox_${courseDetails.id}" name="courseIds" value="${courseDetails.id}" class="styled" type="checkbox"> 
														<label for="checkbox_${courseDetails.id}"></label>
													</div> 
								                </td>
								                <td class="col-md-3 col-xs-3" style="white-space:normal" >
								                	<a href="#" onclick="fnShowExamDetails('${courseDetails.id}');"> 
								                		${courseDetails.programName}
							                		</a>
							                	</td>
								                <td class="col-md-2 col-xs-2" style="white-space:normal" >${courseDetails.institute}</td>
								                <td class="col-md-2 col-xs-2" style="white-space:normal" >${courseDetails.location}</td>
								                <td class="col-md-3 col-xs-3" style="white-space:normal" >${courseDetails.eligibility}</td>
								                <td class="col-md-1 col-xs-1" style="white-space:normal" >${courseDetails.courseDuration}</td>
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
				<div class="row" style="margin-bottom: 50px;">
					<button class="btn btn_action" onclick="fnAdd();" id="saveBtnIdCourse"><span><s:text name="com.edupath.integratedcourse.detail.save"/></span></button>
				</div>
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
				<div id="backToEntrIdCourse" class="occhoverreturnlabel">
					<a href="#" onclick="fnReturnBackToEntrance();">Return</a>
				</div>
			</div>
		</div>
	</div>	
</div>
<script>
	$(document).ready(function() {
		occid = ${occId};
		occIndusryId = ${occIndusryId}
		flag = ${flag};
		if (flag == false) 
		{
			$("#saveBtnIdCourse").hide();
			$(".selChkbox").hide();
		}
		
		
		if('${edupathExists}' == 'false')
		{
			$("#saveBtnIdCourse").prop('disabled', true);
		}	
		
		$("#occId").val(occid);
		$("#occIndusryId").val(occIndusryId);
	});
	var divId = 0;
	
	function fnShowExamDetails(id)
	{
		divId = id
		$("#courseId_"+divId).show();
		$("#courseDetailsId").hide();
		$("#retuId").show();
		$("#my_scrolling_pane").scrollTop(0);
	}
	
	function fnReturnBackToEntrance()
	{
		$("#retuId").hide();
		$("#courseId_"+divId).hide();
		$("#courseDetailsId").show();
		$("#my_scrolling_pane").scrollTop(0);
	}
	
	function fnAdd() 
	{
		occid = ${occId};
		occIndusryId = ${occIndusryId}
		count = ${count}
		arr = [];
		$('input[name="courseIds"]:checked').each(function() {
			arr.push(this.value);
		});
		if (arr.toString() == "") 
		{
			alert("Please select atleast one")
			return false;
		}
		if ((arr.length + count) > 4) 
		{
			alert("max allowed course is 4 , please check the student cart");
			return false;
		}
		url = '${pageContext.request.contextPath}/myapp/saveDetailsIntegratedCourse';
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		data:({ 
	 			"occId" : occid,
	 			"occIndusryId" : occIndusryId,
	 			"courseIds" : arr.toString()
	 		}),
	 		success: function(resp)
	 		{
	 			if(resp != "" && resp != null)
	 			{
	 				$("#eduPathDiv").show();
	 				$("#courseMainDiv").hide();
	 			}	
	 		},
	 		error :  function(msg,arg1,arg2)
	 		{
	 			return false;
	 		}
	 	});
	}
	
	function fnReturnBackToEduPath()
	{
		$("#courseMainDiv").hide();
		$("#eduPathDiv").show();
	}
</script>

<style type="text/css">
.course_table tr td
{
	color:#777c7f !important;
}
</style>