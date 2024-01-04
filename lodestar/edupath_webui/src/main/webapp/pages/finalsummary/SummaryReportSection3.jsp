<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib uri="commonutil.tld" prefix="utils"%>

<div class="summary-report-clear-both">
	<div class="summary-report-heading summary-report-clear-both">
		<em><s:text name="com.edupath.summary.report.section3.career.label"/></em>
	</div>
	<div class="summary-section-content">
		<!-- industry choice  -->
		<c:if test="${!bean.isStudent || (not empty bean.section3OccNInd.industries)}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.section3.industry.choice.label"/>
				</div>
				<div class="col-xs-6 col-md-12 section-list"> <!--added 30/3/18 -->
					<ol class="summary-report-clear-both">
						<c:forEach items="${bean.section3OccNInd.industries}" var="industrie" varStatus="counter">
							<c:if test="${counter.index gt 0 and counter.index mod 4 eq 0}">
								<s:text name="</ol><ol class='summary-report-clear-both section-list-item-right'>"/> 
							</c:if>
							<li class="section-list-item">${counter.index + 1}. ${utils:replaceXMLEntities(industrie.name)}</li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</c:if>
		<!-- occupation choice  -->
		<c:if test="${!bean.isStudent || (not empty bean.section3OccNInd.occupations)}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.section3.occupation.choice.label"/>
				</div>
				<div class="col-xs-6 col-md-12 section-list"> <!--added 30/3/18 -->
					<ol class="summary-report-clear-both">
						<c:forEach items="${bean.section3OccNInd.occupations}" var="occupation" varStatus="counter">
							<c:if test="${counter.index gt 0 and counter.index mod 21 eq 0}">
								<s:text name="</ol><ol class='summary-report-clear-both section-list-item-right'>"/> 
							</c:if>
							<li class="section-list-item">${counter.index + 1}. ${utils:replaceXMLEntities(occupation.name)}</li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</c:if>
		<!-- Edu path -->
		<c:if test="${!bean.isStudent || (not empty bean.section3Edupath)}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.section3.education.pathway.label"/>
				</div>
				<div class="section-list section3-edupath-item-main">
					<div class="summary-report-clear-both">
						<c:forEach items="${bean.section3Edupath}" var="edupath" varStatus="counter">
							<c:if test="${not empty edupath.streamName}">
								<div class="section3-edupath-item-group">
									<div class="section3-edupath-item">${utils:replaceXMLEntities(edupath.streamName)}</div>
								</div>
							</c:if>
							<c:if test="${not empty edupath.combinationName}">
								<div class="section3-edupath-item-group">
									<i class="glyphicon glyphicon-arrow-down"></i>
									<div class="section3-edupath-item">${utils:replaceXMLEntities(edupath.combinationName)}</div>
								</div>
							</c:if>
							<c:if test="${not empty edupath.degreeStream}">
								<div class="section3-edupath-item-group">
									<i class="glyphicon glyphicon-arrow-down"></i>
									<div class="section3-edupath-item">
										${utils:replaceXMLEntities(edupath.degreeStream)}&nbsp;
										<c:if test="${edupath.degreeSubject != null and edupath.degreeSubject != ''}">
											- ${utils:replaceXMLEntities(edupath.degreeSubject)}
										</c:if> 
									</div>
								</div>
							</c:if>
							<c:if test="${not empty edupath.pgStream}">
								<div class="section3-edupath-item-group">
									<i class="glyphicon glyphicon-arrow-down"></i>
									<div class="section3-edupath-item">
										${utils:replaceXMLEntities(edupath.pgStream)}
										<c:if test="${edupath.pgSubject != null and edupath.pgSubject != ''}">
											- ${utils:replaceXMLEntities(edupath.pgSubject)}
										</c:if> 
									</div>
								</div>
							</c:if>
							<c:if test="${not empty edupath.occupationName}">
								<div class="section3-edupath-item-group">
									<i class="glyphicon glyphicon-arrow-down"></i>
									<div class="section3-edupath-item">${utils:replaceXMLEntities(edupath.occupationName)}</div>
								</div>	
							</c:if>
							<c:if test="${not empty edupath.industryName}">
								<div class="section3-edupath-item-group">
									<i class="glyphicon glyphicon-arrow-down"></i>
									<div class="section3-edupath-item">${utils:replaceXMLEntities(edupath.industryName)}</div>
								</div>	
							</c:if>
							<c:if test="${counter.index mod 1 eq 0}">
								<s:text name="</div><div class='summary-report-clear-both section-list-item-right'>"/> 
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
		<!-- subject choice  -->
		<c:if test="${!bean.isStudent || (not empty bean.section3StreamNElective.combinations)}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.section3.subject.choice.label"/>
				</div>
				<div class="col-xs-6 col-md-12 section-list"> <!--added 30/3/18 -->
					<ul class="summary-report-clear-both">
						<li>${bean.section3StreamNElective.streamName} (
							<c:forEach items="${bean.section3StreamNElective.combinations}" var="combinaton" varStatus="counter">
								<c:if test="${counter.index lt 2}">
									${utils:replaceXMLEntities(combinaton.name)}<c:if test="${counter.index lt 1}">,</c:if>
								</c:if>
							</c:forEach>
							)
						</li>
					</ul>
				</div>
			</div>
		</c:if>
		<!-- course details -->
		<c:if test="${!bean.isStudent || (not empty bean.section3Courses)}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.section4.course.prepare.label"/>
				</div>
				<div class="col-xs-6 col-md-12  section-list">  <!--added 30/3/18 -->
					<ol class="summary-report-clear-both">
						<c:forEach items="${bean.section3Courses}" var="courseDetails" varStatus="counter">
							<c:if test="${counter.index gt 0 and counter.index mod 4 eq 0}">
								<s:text name="</ol><ol class='summary-report-clear-both section-list-item-right'>"/> 
							</c:if>
							<li class="section-list-item">${counter.index + 1}. ${utils:replaceXMLEntities(courseDetails.programName)}</li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</c:if>		
		<!-- exam details -->
		<c:if test="${!bean.isStudent || (not empty bean.section3Exams)}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.section3.exam.prepare.label"/>
				</div>
				<div class="col-xs-6 col-md-12 section-list">   <!--added 30/3/18 -->
					<ol class="summary-report-clear-both">
						<c:forEach items="${bean.section3Exams}" var="examDetails" varStatus="counter">
							<c:if test="${counter.index gt 0 and counter.index mod 4 eq 0}">
								<s:text name="</ol><ol class='summary-report-clear-both section-list-item-right'>"/> 
							</c:if>
							<li class="section-list-item">${counter.index + 1}. ${utils:replaceXMLEntities(examDetails.examName)}</li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</c:if>
		<!-- tutorial shortlist -->
		<c:if test="${!bean.isStudent || (not empty bean.section3Tutorials)}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.section3.tutorials.selection.label"/>
				</div>
				<div class="col-xs-6 col-md-12 section-list">			<!--added 30/3/18 -->
					<ol class="summary-report-clear-both">
						<c:forEach items="${bean.section3Tutorials}" var="tutorialShortlist" varStatus="counter">
							<c:if test="${counter.index gt 0 and counter.index mod 4 eq 0}">
								<s:text name="</ol><ol class='summary-report-clear-both section-list-item-right'>"/> 
							</c:if>
							<li class="section-list-item">${counter.index + 1}. ${utils:replaceXMLEntities(tutorialShortlist.tutorialName)}</li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</c:if>
		<!-- college shortlist  -->
		<c:if test="${!bean.isStudent || (not empty bean.section3Colleges)}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.section3.college.school.comparison.label"/>
				</div>
				<div class="col-xs-6 col-md-12 section-list">		<!--added 30/3/18 -->
					<ol class="summary-report-clear-both">
						<c:forEach items="${bean.section3Colleges}" var="collegeShortlist" varStatus="counter">
							<c:if test="${counter.index gt 0 and counter.index mod 4 eq 0}">
								<s:text name="</ol><ol class='summary-report-clear-both section-list-item-right'>"/> 
							</c:if>
							<li class="section-list-item">${counter.index + 1}. ${utils:replaceXMLEntities(collegeShortlist.collegeName)}</li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</c:if>
		<!-- Additional comments -->
		
		
		<!-- Start SASEDEVE edited by Mrutyunjaya on Date 06-06-2017 -->
		
		
		
		<c:if test="${not empty bean.comments and bean.isStudent}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.Observations.label"/>
				</div>
				<div class="section-list additional-comments">
					<ul class="summary-report-clear-both ">
						<p>${utils:replaceXMLEntities(bean.comments)}</p>
					</ul>
				</div>
			</div>
		</c:if>
		
		
		
		
		<c:if test="${not empty bean.challenges and bean.isStudent}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.Challenges.label"/>
				</div>
				<div class="section-list additional-comments">
					<ul class="summary-report-clear-both ">
						<p>${utils:replaceXMLEntities(bean.challenges)}</p>
					</ul>
				</div>
			</div>
		</c:if>
		
		
		
		<c:if test="${not empty bean.approachtopathway1 and bean.isStudent}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.Approachtopathway1.label"/>
				</div>
				<div class="section-list additional-comments">
					<ul class="summary-report-clear-both ">
						<p>${utils:replaceXMLEntities(bean.approachtopathway1)}</p>
					</ul>
				</div>
			</div>
		</c:if>
		
		
		
		<c:if test="${not empty bean.approachtopathway2 and bean.isStudent}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.Approachtopathway2.label"/>
				</div>
				<div class="section-list additional-comments">
					<ul class="summary-report-clear-both ">
						<p>${utils:replaceXMLEntities(bean.approachtopathway2)}</p>
					</ul>
				</div>
			</div>
		</c:if>
		
		
		
		<c:if test="${not empty bean.finalnotes and bean.isStudent}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.Finalnotes.label"/>
				</div>
				<div class="section-list additional-comments">
					<ul class="summary-report-clear-both ">
						<p>${utils:replaceXMLEntities(bean.finalnotes)}</p>
					</ul>
				</div>
			</div>
		</c:if>
		
		
		<c:if test="${not empty bean.supplementaryinformation and bean.isStudent}">
			<div class="summary-report-clear-both">
				<div class="summary-section-header">
					<s:text name="com.edupath.summary.report.SUPPLEMENTARYINFORMATION.label"/>
				</div>
				<div class="section-list additional-comments">
					<ul class="summary-report-clear-both ">
						<p>${utils:replaceXMLEntities(bean.supplementaryinformation)}</p>
					</ul>
				</div>
			</div>
		</c:if>
		
		
		
		
		
		
		
		
		
		<!-- Start SASEDEVE edited by Mrutyunjaya on Date 06-06-2017 -->
		
		
	</div>
</div>