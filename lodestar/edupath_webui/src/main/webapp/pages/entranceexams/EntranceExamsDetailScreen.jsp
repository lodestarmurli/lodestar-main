<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib prefix="fn" uri="fn.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 

<div id="entranceMainDiv" class="edupath-padding">
	<%-- <s:form name="entranceExamForm" method="post"> --%>
	<s:hidden name="occId" id="occId"/>
	<s:hidden name="occIndusryId" id="occIndusryId"/>
	<div id="examDetailsId">
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
					<c:when test="${not empty examMap}">
		        		<c:forEach items="${examMap}" var="entranceExamMap">
							<div class="row" style="margin-top: 20px;">
								<span class="level_title">${entranceExamMap.key}</span>
							</div>
							<div class="row table-responsive SPlcoureswith" style="margin-top: 20px;">
								<table class="table table-bordered exam_table">
							        <thead>
							            <tr>
							                <th class="col-md-1 col-xs-1" style="white-space:normal"><s:text name="com.edupath.common.select"/></th>
							                <th class="col-lg-2 col-md-2 col-xs-2" style="white-space:normal"><s:text name="com.edupath.entranceexam.detail.examname"/></th>
							                <th class="col-md-1 col-xs-2" style="white-space:normal"><s:text name="com.edupath.entranceexam.detail.exammonth"/></th>
							                <th class="col-md-1 col-xs-1" style="white-space:normal"><s:text name="com.edupath.entranceexam.detail.when"/></th>
							                <th class="col-md-1 col-xs-1" style="white-space:normal"><s:text name="com.edupath.entranceexam.detail.noseats"/></th>
							                <th class="col-md-1 col-xs-1" style="white-space:normal"><s:text name="com.edupath.entranceexam.detail.seatsratio"/></th>
							                <th class="col-md-2 col-xs-2" style="white-space:normal"><s:text name="com.edupath.entranceexam.detail.eligibility"/></th>
							                <th class="col-md-2 col-xs-2" style="white-space:normal"><s:text name="com.edupath.entranceexam.detail.institute"/></th>
							            </tr>
							        </thead>
							        <tbody>
										 <c:forEach items="${entranceExamMap.value}" var="entranceExamDetails">
								            <tr>
												<td class="col-xs-1" align="center" style="white-space:normal">
													<div class="checkbox checkbox-primary">
														<input id="checkbox_${entranceExamDetails.id}" name="examIds" value="${entranceExamDetails.id}" class="styled" type="checkbox"> 
														<label for="checkbox_${entranceExamDetails.id}"></label>
													</div> 
												</td>
												<td class="col-lg-2 col-md-2 col-xs-12"style="white-space:normal">
								                	<a href="#" onclick="fnShowExamDetails('${entranceExamDetails.id}');"> 
								                		${entranceExamDetails.examName}
							                		</a>
							                	</td >
								                <td class="col-lg-1 col-md-1 col-xs-1" style="white-space:normal">${entranceExamDetails.monthOfExam}</td>
								                <td class="col-xs-1" style="white-space:normal">${entranceExamDetails.examWhen}</td>
								                <td class="col-xs-1" style="white-space:normal">${entranceExamDetails.noOfSeats}</td>
								                <td class="col-xs-1" style="white-space:normal">${entranceExamDetails.ratio}</td>
								                <td class="col-lg-3 col-md-3 col-xs-3" style="white-space:normal">${entranceExamDetails.eligibility}</td>
								                <td class="col-xs-2" style="white-space:normal">${entranceExamDetails.insititutesAcceptScore}</td>
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
				<div class="row">
					<button class="btn btn_action" onclick="fnAdd();" id="saveBtnId"><span><s:text name="com.edupath.entranceexam.detail.save"/></span></button>
				</div>
			</div>
			<div class="col-md-1">
				
			</div> 	
		</div>	
	</div>
	<div id="entranceexamdetails">
		<div class="row">
			<div class="col-md-10">
				<c:forEach items="${examList}" var="entrExamDetails">
						<div id="examId_${entrExamDetails.id}" style="display: none"> 
							<div class="row">
								<span class="pagetitle">${entrExamDetails.examName}</span>
							</div>
							<div class="row" style="margin-top: 10px;">
								<span class="occupationname"><s:text name="com.edupath.entranceexam.detail.when"/></span>
								<p class="entr_exam_details">${entrExamDetails.examWhen}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.entranceexam.detail.aboutexam"/></span>
								<p class="entr_exam_details">${entrExamDetails.aboutExam}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.entranceexam.detail.eligibility"/></span>
								<p class="entr_exam_details">${entrExamDetails.eligibility}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.entranceexam.detail.subnmarks"/></span>
								<p class="entr_exam_details">${entrExamDetails.subNMarks}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.entranceexam.detail.exammonth"/></span>
								<p class="entr_exam_details">${entrExamDetails.monthOfExam}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.entranceexam.detail.institute"/></span>
								<p class="entr_exam_details">${entrExamDetails.insititutesAcceptScore}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.entranceexam.detail.noseats"/></span>
								<p class="entr_exam_details">
									<c:choose>
										<c:when test="${entrExamDetails.noOfSeats ne null}">
											${entrExamDetails.noOfSeats}
										</c:when>
										<c:otherwise>
											-
										</c:otherwise>
									</c:choose>
								</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.entranceexam.detail.candidateappear"/></span>
								<p class="entr_exam_details">
									<c:choose>
										<c:when test="${entrExamDetails.candidateAppearing ne null}">
											${entrExamDetails.candidateAppearing}
										</c:when>
										<c:otherwise>
											-
										</c:otherwise>
									</c:choose>
								</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.entranceexam.detail.annualfee"/></span>
								<p class="entr_exam_details">${entrExamDetails.annualFee}</p>
							</div>
						</div>
				</c:forEach>
			</div>
			<div class="col-md-2" style="display: none" id="returnId">
				<div id="backToEntrId" class="occhoverreturnlabel">
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
			$("#saveBtnId").hide();
			$(".checkbox").hide();
		}

		if('${edupathExists}' == 'false')
		{
			$("#saveBtnId").prop('disabled', true);
		}
		
		$("#occId").val(occid);
		$("#occIndusryId").val(occIndusryId);
	});
	var divId = 0;
	
	function fnShowExamDetails(id)
	{
		divId = id
		$("#examId_"+divId).show();
		$("#examDetailsId").hide();
		$("#returnId").show();
		$("#my_scrolling_pane").scrollTop(0);
	}
	
	function fnReturnBackToEntrance()
	{
		$("#returnId").hide();
		$("#examId_"+divId).hide();
		$("#examDetailsId").show();
		$("#my_scrolling_pane").scrollTop(0);
	}
	
	function fnAdd() 
	{
		occid = ${occId};
		occIndusryId = ${occIndusryId}
		count = ${count}
		arr = [];
		$('input[name="examIds"]:checked').each(function() {
			arr.push(this.value);
		});
		if (arr.toString() == "") 
		{
			alert("Please select atleast one")
			return false;
		}
		if ((arr.length + count) > 6) 
		{
			alert("max allowed exams is 6 , please check the student cart");
			return false;
		}
		url = '${pageContext.request.contextPath}/myapp/saveDetailsEntranceExams';
		$.ajax({
	 		url: url,
	 		type: "POST",
	 		data:({ 
	 			"occId" : occid,
	 			"occIndusryId" : occIndusryId,
	 			"examIds" : arr.toString()
	 		}),
	 		success: function(resp)
	 		{
	 			if(resp != "" && resp != null)
	 			{
	 				$("#eduPathDiv").show();
	 				$("#entranceMainDiv").hide();
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
		$("#entranceMainDiv").hide();
		$("#eduPathDiv").show();
	}
</script>

<style type="text/css">
.exam_table tr td
{
	color:#777c7f !important;
}
</style>