<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib uri="commonutil.tld" prefix="utils"%>

<div class="summary-report-clear-both summary-section">
	<div class="summary-report-heading summary-report-clear-both">
		<em><s:text name="com.edupath.summary.report.section2.activity.label"/></em>
	</div>
	<div class="summary-section-content">
		<!-- industries -->
		<c:if test="${!bean.isStudent || (not empty bean.section2OccNInd.industries)}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.section2.industry.report.label"/>
				</div>
				<div class="col-xs-6 col-md-12 section-list">		<!--added 30/3/18 -->
					<ol class="summary-report-clear-both">
						<c:forEach items="${bean.section2OccNInd.industries}" var="industrie" varStatus="counter">
							<c:if test="${counter.index gt 0 and counter.index mod 4 eq 0}">
								<s:text name="</ol><ol class='summary-report-clear-both section-list-item-right'>"/> 
							</c:if>
							<li class="section-list-item">${counter.index + 1}. ${utils:replaceXMLEntities(industrie.name)}</li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</c:if>
		<!-- occupations -->
		<c:if test="${!bean.isStudent || (not empty bean.section2OccNInd.occupations)}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.section2.occupation.report.label"/>
				</div>
				<div class="col-xs-6 col-md-12 section-list">	<!--added 30/3/18 -->
					<ol class="summary-report-clear-both">
						<c:forEach items="${bean.section2OccNInd.occupations}" var="occupation" varStatus="counter">
							<c:if test="${counter.index gt 0 and counter.index mod 21 eq 0}">
								<s:text name="</ol><ol class='summary-report-clear-both section-list-item-right'>"/> 
							</c:if>
							<li class="section-list-item">${counter.index + 1}. ${utils:replaceXMLEntities(occupation.name)}</li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</c:if>
		<!-- Course Read  -->
		<c:if test="${!bean.isStudent || (not empty bean.section2CourseRead)}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.section2.course.report.label"/>
				</div>
				<div class="col-xs-6 col-md-12 section-list">		<!--added 30/3/18 -->
					<ol class="summary-report-clear-both">
						<c:forEach items="${bean.section2CourseRead}" var="subject" varStatus="counter">
							<c:if test="${counter.index gt 0 and counter.index mod 4 eq 0}">
								<s:text name="</ol><ol class='summary-report-clear-both section-list-item-right'>"/> 
							</c:if>
							<li class="section-list-item">${counter.index + 1}. ${utils:replaceXMLEntities(subject.name)}</li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</c:if>
		<!-- college params -->
		<c:if test="${!bean.isStudent || (not empty bean.selectedParam.collegeParams)}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header summary-section-header1">
					<s:text name="com.edupath.summary.report.section2.parameters.report.label"/>
				</div>
				<div class="col-xs-6 col-md-12 section-list">		<!--added 30/3/18 -->
					<ol class="summary-report-clear-both">
						<c:forEach items="${bean.selectedParam.collegeParams}" var="parameter" varStatus="counter">
							<c:if test="${counter.index gt 0 and counter.index mod 4 eq 0}">
								<s:text name="</ol><ol class='summary-report-clear-both section-list-item-right'>"/> 
							</c:if>
							<li class="section-list-item">${counter.index + 1}. ${utils:replaceXMLEntities(parameter.parameter)}</li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</c:if>
	</div>
</div>