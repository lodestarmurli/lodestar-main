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
<div id="entranceMainDiv" style="padding-left: 30px; padding-top: 10px;">
	<%-- <s:form name="entranceExamForm" method="post"> --%>
	<s:hidden name="occId" id="occId"/>
	<s:hidden name="occIndusryId" id="occIndusryId"/>
	<div id="examDetailsId">
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
					<c:when test="${not empty examMap}">
		        		<c:forEach items="${examMap}" var="entranceExamMap">
							<div class="row" style="margin-top: 20px;">
								<span class="level_title">${entranceExamMap.key}</span>
							</div>
							<div class="row" style="margin-top: 20px;">
								<table class="table table-bordered exam_table">
							        <thead>
							            <tr>
							                <th class="col-md-2"><s:text name="com.edupath.entranceexam.detail.examname"/></th>
							                <th class="col-md-2"><s:text name="com.edupath.entranceexam.detail.exammonth"/></th>
							                <th class="col-md-1"><s:text name="com.edupath.entranceexam.detail.when"/></th>
							                <th class="col-md-1"><s:text name="com.edupath.entranceexam.detail.noseats"/></th>
							                <th class="col-md-1"><s:text name="com.edupath.entranceexam.detail.seatsratio"/></th>
							                <th class="col-md-2"><s:text name="com.edupath.entranceexam.detail.eligibility"/></th>
							                <th class="col-md-2"><s:text name="com.edupath.entranceexam.detail.institute"/></th>
							            </tr>
							        </thead>
							        <tbody>
										 <c:forEach items="${entranceExamMap.value}" var="entranceExamDetails">
								            <tr>
								                <td>
								                	<a href="#" onclick="fnShowExamDetails('${entranceExamDetails.id}');"> 
								                		${entranceExamDetails.examName}
							                		</a>
							                	</td>
								                <td>${entranceExamDetails.monthOfExam}</td>
								                <td>${entranceExamDetails.examWhen}</td>
								                <td>${entranceExamDetails.noOfSeats}</td>
								                <td>${entranceExamDetails.ratio}</td>
								                <td>${entranceExamDetails.eligibility}</td>
								                <td>${entranceExamDetails.insititutesAcceptScore}</td>
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
								<p class="entr_exam_details">${entrExamDetails.noOfSeats}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.entranceexam.detail.candidateappear"/></span>
								<p class="entr_exam_details">${entrExamDetails.candidateAppearing}</p>
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
		
		
	});
	var divId = 0;
	
	function fnShowExamDetails(id)
	{
		divId = id
		$("#examId_"+divId).show();
		$("#examDetailsId").hide();
		$("#returnId").show();
	}
	
	function fnReturnBackToEntrance()
	{
		$("#returnId").hide();
		$("#examId_"+divId).hide();
		$("#examDetailsId").show();
	}
	
	function fnReturnBackToEduPath()
	{
		$("#entranceMainDiv").hide();
		$("#globalSearchMainDiv").show();
	}
</script>

<style type="text/css">
.exam_table tr td
{
	color:#777c7f !important;
}
</style>