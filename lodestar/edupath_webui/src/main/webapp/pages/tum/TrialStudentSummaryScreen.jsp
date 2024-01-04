<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="c.tld" prefix="c" %>

<div class="row filter-margin-top">
	<div class="col-md-12 college-search-header">
		<s:text name="com.edupath.summary.report.header1"/> 
	</div>
</div>

<div class="report-underprocess-card">
	<div class="report-underprocess-text">
		<p>
			Thank you for completing the Occupation Interest Assessment. You can download your personalised  report <a href="${pageContext.request.contextPath}/myapp/doDownloadTrialReportInterestAction">here</a>.
		</p>
		<p>This report will help you understand the overall Career Interests and a few related Career choices.</p>
		<p>
			Identifying your Occupation Interest Area is the first step in making a 'Smart Career Decision'!
		</p>
	</div>
	<div class="report-underprocess-text text-center">
	<img alt="student" src="${pageContext.request.contextPath}/images/smartdecision.png">
	</div>
	<br>
	<p>At Lodestar Career Guidance, we have researched and scientifically mapped more than 250+ Career choices with different combinations of Student's Interests , Strengths, Passion and Abilities. Lodestar has a unique 3 Step Career decision making process - 3D Approach - Discover, Determine and Decide. </p>
	<p>Having completed the Step-1 of student assessment, we recommend taking up the comprehensive 3- Step program (3D Approach).</p>
</div>
