<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>  

	<div class="row">
		<div class="col-md-11 " id="retuId" style="text-align: right;">
			<div id="backToEntrId" class="occhoverreturnlabel">
			<a href="#" onclick="fnReturnBack();">
				<s:text name="com.edupath.common.label.return.button"></s:text>
			</a>
			</div>
		</div>
	</div>
	<div id="coursedetails" style="display: none;">
			<div class="col-md-10">
						<div id="courseId_${integCourseDetails.id}" > 
							<div class="row">
								<span class="pagetitle">${utils:replaceXMLEntities(integCourseDetails.programName)}</span>
							</div>
							<div class="row" style="margin-top: 10px;">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.description"/></span>
								<p class="entr_exam_details">${utils:replaceXMLEntities(integCourseDetails.description)}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.institute"/></span>
								<p class="entr_exam_details">${utils:replaceXMLEntities(integCourseDetails.institute)}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.location"/></span>
								<p class="entr_exam_details">${utils:replaceXMLEntities(integCourseDetails.location)}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.eligibility"/></span>
								<p class="entr_exam_details">${utils:replaceXMLEntities(integCourseDetails.eligibility)}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.entrance"/></span>
								<p class="entr_exam_details">${utils:replaceXMLEntities(integCourseDetails.entrance)}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.selectionprocess"/></span>
								<p class="entr_exam_details">${utils:replaceXMLEntities(integCourseDetails.selectionProcess)}</p>
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
								<p class="entr_exam_details">${utils:replaceXMLEntities(integCourseDetails.programType)}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.integratedcourse.detail.courseduration"/></span>
								<p class="entr_exam_details">${utils:replaceXMLEntities(integCourseDetails.courseDuration)}</p>
							</div>
						</div>
			</div>
		</div>
	
	<div id="entranceexamdetails" style="display: none;">
		<div class="row">
			<div class="col-md-10">
						<div id="examId_${entrExamDetails.id}"> 
							<div class="row">
								<span class="pagetitle">${utils:replaceXMLEntities(entrExamDetails.examName)}</span>
							</div>
							<div class="row" style="margin-top: 10px;">
								<span class="occupationname"><s:text name="com.edupath.entranceexam.detail.when"/></span>
								<p class="entr_exam_details">${utils:replaceXMLEntities(entrExamDetails.examWhen)}</p>
							</div>
							<div class="row">
								<span class="occupationname"><s:text name="com.edupath.entranceexam.detail.aboutexam"/></span>
								<p class="entr_exam_details">${utils:replaceXMLEntities(entrExamDetails.aboutExam)}</p>
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
			</div>
		</div>
	</div>	
	
<script type="text/javascript">

var divShow = '${type}';

if(divShow == 'E')
{
	$('#entranceexamdetails').show();
	$('#coursedetails').hide();
}
else if(divShow == 'C')
{
	$('#coursedetails').show();
	$('#entranceexamdetails').hide();
	
}

function fnReturnBack()
{
	$('#courseAndExamDiv').hide();
	$('#cartDIV').show();
	$("#myCart").scrollTop(0);
}
</script>	