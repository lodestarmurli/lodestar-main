<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="c.tld" prefix="c" %>

<div class="row filter-margin-top">
	<div class="col-md-12 col-xs-12 college-search-header">
		<s:text name="com.edupath.summary.report.header1"/> 
	</div>
</div>
<!-- BEGIN Sasedeve Added by Mrutyunjaya on date 20-02-2017  -->
<s:form name="ReportForm" id="ReportForm" theme="simple">
<s:hidden name="ReportstudentId" id="ReportstudentId" ></s:hidden>
</s:form>
<!-- <c:choose>
<c:when test="${!bean.isStudent}">

<div align="center">
<br/>
You can download your summary  report <a href="javascript:void(0)" onclick="fnDownloadStudentFinalReport('0');">here</a>.
</div>

</c:when>
<c:otherwise>

<div align="center">
<br/>
You can download your summary  report <a href="javascript:void(0)" onclick="fnDownloadStudentFinalReport('1');">here</a>.

</div>
</c:otherwise>

</c:choose> -->


<!-- END Sasedeve Added by Mrutyunjaya on date 20-02-2017  -->


<s:if test="%{!bean.isReportGenerated}">
	<div class="report-underprocess-card" style="margin-left:31px;">
		<div class="report-underprocess-text">
			<p>
				We are working on <b>Summary Report</b> of all session activities, career and education decisions.
				You will be notified via <b>e-mail</b> when your report is ready for viewing and download.<br/><br/>
				Click on <a href="#" onclick="fnShowStudentCart(this , '${bean.userId}', 'student');"><b>My Cart</b></a> to view all session activities discussed with you.
			</p>
		</div>
	</div>
</s:if>
<s:else>
	<div class="summary-report  col-sm-12 col-lg-12 col-xs-12 col-md-12" id="summary_report" style="padding-left:5px">
	<c:if test="${!bean.isStudent}">
	
 	<div class="summary-report-header summary-report-clear-both col-sm-12 col-lg-12 col-xs-12 col-md-12" style="padding-left:5px">
			<div class="summary-report-text1 col-xs-5 col-lg-8 col-sm-8 col-md-8  summary-width" >
				<s:text name="com.edupath.summary.report.header2"/>
			</div>
			<div class="summary-report-text2 col-xs-7 col-lg-4 col-sm-4 col-md-4">
				<s:text name="com.edupath.summary.report.lodestar.url"/>
			</div>
			<div class="summary-report-text1 col-xs-12  col-lg-8 col-sm-8 col-md-8">
				${bean.section1.name}
			</div>
			<div class="summary-report-text2 col-xs-12 col-lg-4 col-sm-4 col-md-4">
				<s:text name="com.edupath.summary.report.lodestar.email"/>
			</div>
		</div> 
		</c:if>
		<div class="col-xs-7 col-md-12 summary-report-preface summary-report-clear-both" style="padding-left:5px">
			<div class="summary-report-heading summary-report-clear-both">
		<!-- 		<s:text name="com.edupath.summary.report.preface.label"/>  -->
			</div>
			<div class="summary-report-clear-both summary-report-preface-text">
			<!--  	Thank you for completing all the sessions of LODESTAR Career Guidance Program. By this time you would have explored different industries, occupations and courses. 
				This would have helped you in getting clarity and right perspective on the academic courses and new career prospects.  -->
				
				Congratulations on successfully completing the Lodestar Career Guidance program!
				
				<c:choose>
					<c:when test="${!bean.isStudent}">
					
					
					<br/><br/>
					You may <a href="javascript:void(0)" onclick="fnDownloadStudentFinalReport('0');">Click Here</a> to download your Final Report.
					
					
					</c:when>
					<c:otherwise>
					
					
					<br/><br/>
					You may <a href="javascript:void(0)" onclick="fnDownloadStudentFinalReport('1');">Click Here</a> to download your Final Report.
					
					
					</c:otherwise>
					
				</c:choose>
				<br/>	<br/>
				We encourage you to reach out to your Guidance Specialist and the Lodestar team for any clarification.  We wish you the very best in your education and career journey.

				<br/><br/><br/>
				- Team Lodestar
				
				
				
			</div>
		</div>
		
		<c:if test="${!bean.isStudent}">
		
		<div class="summary-report-highlight summary-report-clear-both">
			<div class="summary-report-heading summary-report-clear-both">
				<s:text name="com.edupath.summary.report.highlights.label"/>
			</div>
			<div class=" col-xs-6 col-md-12 summary-report-clear-both summary-report-highlight-text">
				<p style="text-indent: 50px;">The Lodestar Career Guidance Program has taken you through a unique career exploration process to help you make a career decision. In this program you has done the following:</p>
				<ul class=" col-xs-12 summary-report-high-ul">
					<li>
						<p>Explored the world of different industries and jobs.</p>
					</li>
					<li>
						<p>Learnt how your interests in a particular industry or occupation pay a role in the career decisions you make and how to use this knowledge to your best advantage.</p>
					</li>
					<li>
						<p>Understood how a proper education pathway and course selection is linked to your career choice.</p>
					</li>
					<li>
						<p>Discover your secondary choices and alternative career options which you can look at in future</p>
					</li>
				</ul>
			</div>
			<div class="summary-report-clear-both summary-report-help-main summryleft ">
				<div class="summary-report-card summary-report-help ">
					<ul>
						<li>
							<p>Industry Report</p>
						</li>
						<li>
							<p>Occupation Report</p>
						</li>
						<li>
							<p>Course Report</p>
						</li>
						<li>
							<p>Special Courses</p>
						</li>
						<li>
							<p>Entrance Exams</p>
						</li>
						<li>
							<p>College Parameter Report</p>
						</li>
						<li>
							<p>Tutorial Centers</p>
						</li>
						<li>
							<p>Reports and data which helped you in identifying your interest area</p>
						</li>
					</ul>
				</div>
				<div class="summary-report-card summary-report-session col-xs-12">
					<ul>
						<li>
							<p>Sections of this report</p>
						</li>
						<li>
							<p>Section &#8544; :<span> Profile of the student</span></p>
						</li>
						<li>
							<p>Section &#8545; :<span> All session activities</span></p>
						</li>
						<li>
							<p>Section &#8546; :<span> All career decisions</span></p>
						</li>
						<li>
							<p>Section &#8547; :<span> All education pathway decisions</span></p>
						</li>
					</ul>
				</div>
			</div>
		</div>
		 
		 
<jsp:include page="SummaryReportSection1.jsp">
			<jsp:param value="${bean}" name="bean"/>
		</jsp:include>
		<jsp:include page="SummaryReportSection2.jsp">
			<jsp:param value="${bean}" name="bean"/>
		</jsp:include>
		<jsp:include page="SummaryReportSection3.jsp">
			<jsp:param value="${bean}" name="bean"/>
		</jsp:include>
		<jsp:include page="SummaryReportSection4.jsp">
			<jsp:param value="${bean}" name="bean"/>
		</jsp:include>
		 
		 
		 
		 
		 </c:if>
		 
	</div>
	<c:if test="${!bean.isStudent}">
		<c:choose>
			<c:when test="${session.isReview}">
				<jsp:include page="SummaryReviewerComments.jsp">
					<jsp:param value="${bean}" name="bean"/>
				</jsp:include>
			</c:when>
			<c:otherwise>
				<jsp:include page="SummaryCounsellorComments.jsp">
					<jsp:param value="${bean}" name="bean"/>
				</jsp:include>
			</c:otherwise>
		</c:choose>
	</c:if>
</s:else>
<!-- BEGIN Sasedeve Added by Mrutyunjaya on date 20-02-2017  -->


<script>
function fnDownloadStudentFinalReport(id)
{
	var form = document.ReportForm;
	$("#ReportstudentId").val(id);
	form.action = "${pageContext.request.contextPath}/myapp/getStudentFinalReport";
	form.submit();
}
</script>
<!-- END Sasedeve Added by Mrutyunjaya on date 20-02-2017  -->
