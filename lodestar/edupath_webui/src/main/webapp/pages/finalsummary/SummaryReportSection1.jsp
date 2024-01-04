<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="commonutil.tld" prefix="utils"%>

<div class="summary-report-clear-both summary-section1">
	<div class="summary-report-heading summary-report-clear-both">
		<em><s:text name="com.edupath.summary.report.section1.stud.profile.label"/></em>
	</div>
	<div class="summary-report-clear-both summary-section1-report">
		<div class="summary-section1-row">
			<div class="summary-section1-title">
				<s:text name="com.edupath.summary.report.section1.name.label"/>
			</div>
			<div class="summary-section1-value">
				${utils:replaceXMLEntities(bean.section1.name)}
			</div>
		</div>
		<div class="summary-section1-row">
			<div class="summary-section1-title">
				<s:text name="com.edupath.summary.report.section1.school.label"/>
			</div>
			<div class="summary-section1-value">
				${utils:replaceXMLEntities(bean.section1.schoolName)}
			</div>
		</div>
		<div class="summary-section1-row">
			<div class="summary-section1-title">
				<s:text name="com.edupath.summary.report.section1.class.label"/>
			</div>
			<div class="summary-section1-value">
				${utils:replaceXMLEntities(bean.section1.className)}
			</div>
		</div>
		<div class="summary-section1-row">
			<div class="summary-section1-title">
				<s:text name="com.edupath.summary.report.section1.phno.label"/>
			</div>
			<div class="summary-section1-value">
				${utils:replaceXMLEntities(bean.section1.contactNumber)}
			</div>
		</div>
		<div class="summary-section1-row">
			<div class="summary-section1-title">
				<s:text name="com.edupath.summary.report.section1.email.label"/>
			</div>
			<div class="summary-section1-value">
				${utils:replaceXMLEntities(bean.section1.fatheremailId)}
			</div>
		</div>
		<div class="summary-section1-row">
			<div class="summary-section1-title">
				<s:text name="com.edupath.summary.report.section1.loginid.label"/>
			</div>
			<div class="summary-section1-value">
				${bean.section1.loginId}
			</div>
		</div>
		<div class="summary-section1-row">
			<div class="summary-section1-title">
				<s:text name="com.edupath.summary.report.section1.facil.label"/>
			</div>
			<div class="summary-section1-value">
				${utils:replaceXMLEntities(bean.section1.facilitatorName)}
			</div>
		</div>
		<div class="summary-section1-row">
			<div class="summary-section1-title">
				<s:text name="com.edupath.summary.report.section1.1stsessiondate.label"/>
			</div>
			<div class="summary-section1-value">
				${bean.section1.session1DateStr}
			</div>
		</div>
		<div class="summary-section1-row">
			<div class="summary-section1-title">
				<s:text name="com.edupath.summary.report.section1.2ndsessiondate.label"/>
			</div>
			<div class="summary-section1-value">
				${bean.section1.session2DateStr}
			</div>
		</div>
		<div class="summary-section1-row">
			<div class="summary-section1-title">
				<s:text name="com.edupath.summary.report.section1.3rdsessiondate.label"/>
			</div>
			<div class="summary-section1-value">
				${bean.section1.session3DateStr}
			</div>
		</div>
	</div>
</div>