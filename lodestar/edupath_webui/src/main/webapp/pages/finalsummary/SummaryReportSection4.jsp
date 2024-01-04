<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib uri="commonutil.tld" prefix="utils"%>

<c:if test="${!bean.isStudent || (not empty bean.section3Exams) || (not empty bean.section3Courses)}">
	<div class="summary-report-clear-both summary-section">
		<div class="summary-report-heading summary-report-secion4-heading summary-report-clear-both">
			<em><s:text name="com.edupath.summary.report.section4.exam.label"/></em>
		</div>
		
		<!-- Courses -->
		<div class="summary-report-clear-both">
			<div class="summary-exams-report-subtitle">
				<s:text name="com.edupath.summary.report.section4.course.toprepare.label"/>
			</div>
		</div>
		<c:forEach items="${bean.section3Courses}" var="courseDetails" varStatus="counter">
			<div class="col-xs-7 col-md-12 summary-report-clear-both summary-exams-report-main">
				<div class="summary-report-clear-both">
					<div class="summary-exams-report-lagend summary-course-subtile">
						<s:text name="com.edupath.summary.report.section4.course.label"/>
					</div>
				</div>
				<div class="summary-report-clear-both summary-exams-report-card">
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.course.coursedetails.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty courseDetails.programName? courseDetails.programName : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.course.institutename.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty courseDetails.institute ? courseDetails.institute : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.course.institutelocation.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty courseDetails.location ? courseDetails.location : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.course.eligibility.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty courseDetails.eligibility ? courseDetails.eligibility : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.course.selectionprocess.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty courseDetails.selectionProcess ? courseDetails.selectionProcess : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.course.courseduration.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty courseDetails.courseDuration ? courseDetails.courseDuration : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.course.details.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty courseDetails.description ? courseDetails.description : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.course.noofseats.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty courseDetails.noOfSeats ? courseDetails.noOfSeats : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.course.feestructure.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty courseDetails.feeStructure ? courseDetails.feeStructure : '-' }
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		
		<!-- Exams -->
		<div class="summary-report-clear-both">
			<div class="summary-exams-report-subtitle">
				<s:text name="com.edupath.summary.report.section4.exams.toprepare.label"/>
			</div>
		</div>
		<c:forEach items="${bean.section3Exams}" var="examDetails" varStatus="counter">
			<div class="col-xs-7 col-md-12 summary-report-clear-both summary-exams-report-main">
				<div class="summary-report-clear-both">
					<div class="summary-exams-report-lagend">
						<s:text name="com.edupath.summary.report.section4.examinations.label"/>
					</div>
				</div>
				<div class="summary-report-clear-both summary-exams-report-card">
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.examinations.testdetails.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty examDetails.examName? examDetails.examName : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.examinations.scoresacceptedin.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty examDetails.insititutesAcceptScore ? examDetails.insititutesAcceptScore : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.examinations.eligibility.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty examDetails.eligibility ? examDetails.eligibility : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.examinations.examinationmonth.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty examDetails.monthOfExam ? examDetails.monthOfExam : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.examinations.subjectsandmarks.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty examDetails.subNMarks ? examDetails.subNMarks : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.examinations.details.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty examDetails.aboutExam ? examDetails.aboutExam : '-' }
						</div>
					</div>
					<div class="summary-exams-report-row">
						<div class="summary-exams-report-label">
							<s:text name="com.edupath.summary.report.section4.examinations.noofseats.label"/>
						</div>
						<div class="summary-exams-report-value">
							${not empty examDetails.noOfSeats ? examDetails.noOfSeats : '-' }
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>