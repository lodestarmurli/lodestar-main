<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="toggleValue" value="1"></c:set>
<div style="width:100%; height: auto;" class="nano-content">
<div id="cartDIV">
	<div class="row" style="padding-right: 10px;">
		<div class="col-md-12">
			<div class="portlet box #ffffff" style="border: 1px solid #d8d8d8;">
				<div class="portlet-title" >
					<div class="caption" style="color: #000000;">
						
						<s:text name="com.edupath.studentcart.wish.occupation.title"></s:text>
					</div>
					<c:if test="${null != occupationWishMap and not empty occupationWishMap}">
						<div class="pull-right">
							<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls" data-toggleValue="${toggleValue}"></i>
							<input type="hidden" id="toggle_${toggleValue}" value="YES"/>
						</div>
					</c:if>
				</div>
				<c:if test="${null != occupationWishMap and not empty occupationWishMap}">
					<div class="portlet-body form toggleDiv_${toggleValue}" style="border-top: 1px solid #d8d8d8;">
						<div class="form-body">
							<div class="row">
								<c:forEach items="${occupationWishMap}" var="industry" varStatus="count">
								<div class="col-md-4 custom-box-ft-subject" style="padding-top: 10px">
										<div class="" style="padding-left: 0px">
											<label
												class="Manual_Individual_subCategory_txt_label">${fn:trim(industry.key)}</label>
										</div>
										<div class="txt-field  " style="padding-left: 10px">
											<c:forEach items="${industry.value}" var="occupation">
												<div class="col-md-12" style="  padding-left: 0px !important; margin-top: 10px">
													<div class="occupationnamediv" style="border-radius: 6px !important;" >
													
													<!-- BEGIN Sasedeve Added by Mrutyunjaya on Date 10-05-2017 -->
																<i class="fa fa-circle fa-fw ${fitmentcolors[occupation.occupation.fitment]}" aria-hidden="true"></i>
													
													
												 	<i class="fa fa-square fa-fw ${fitmentcolors[occupation.occupation.newfitment]}" aria-hidden="true"></i> 
													
													<!-- END Sasedeve Added by Mrutyunjaya on Date 10-05-2017 -->
													
													
														<label
															class="Manual_Individual_area_txt_label occupation_name_cart" style="">${utils:replaceXMLEntities(occupation.occupation.name)}</label>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</c:if>
			</div>
			<c:set var="toggleValue" value="${toggleValue + 1}"></c:set>
			<div class="portlet box #ffffff" style="border: 1px solid #d8d8d8;">
				<div class="portlet-title" >
					<div class="caption" style="color: #000000;">
						
						<s:text name="com.edupath.studentcart.shortlist.title"></s:text>
					</div>
					<c:if test="${null != shortListDTO and not empty shortListDTO}">
						<div class="pull-right">
							<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls" data-toggleValue="${toggleValue}"></i>
							<input type="hidden" id="toggle_${toggleValue}" value="YES"/>
						</div>
					</c:if>
				</div>
				<c:if test="${null != shortListDTO and not empty shortListDTO}">
					<div class="portlet-body form toggleDiv_${toggleValue}" style="border-top: 1px solid #d8d8d8;">
						<div class="form-body">
							<div class="row">
							<fmt:parseNumber var="i" integerOnly="true" type="number" value="${fn:length(shortListDTO) / 2}" />
							<c:set var="v" value="${i}"></c:set>
							<c:set var="start" value="0"></c:set>
							<c:set var="end" value="1"></c:set>
							<c:forEach begin="0" end="${v}" var="count">
							<div class="col-md-4 custom-box-ft-subject" style="padding-top: 10px">
								<c:forEach items="${shortListDTO}" var="shortList" begin="${start}" end="${end}" varStatus="count">
								<c:set var="priority" value="occupationnamediv"></c:set>
								<c:if test="${start < 2  and shortList.orderNo != 10}">
									<c:set var="priority" value="priorty_occupation"></c:set>
								</c:if>
									<div class="col-md-12" style="padding-left: 0px !important; margin-top: 10px;">
										<div class="${priority}" style="border-radius: 6px !important;" >
											<c:choose>
												<c:when test="${null != shortList.industryId}">
													<label
												class="Manual_Individual_area_txt_label occupation_name_cart" style="margin: 5px 10px 5px 10px; text-align: left; font-size:11pt;line-height:14.8pt;">${start+1})&nbsp;${utils:replaceXMLEntities(shortList.industryName)}</label>
												</c:when>
												<c:when test="${null != shortList.occupationId}">
												
												
												<!-- BEGIN Sasedeve Added by Mrutyunjaya on Date 10-05-2017 -->
																<i class="fa fa-circle fa-fw ${fitmentcolors[shortList.fitment]}" aria-hidden="true"></i>
													
													
												 	<i class="fa fa-square fa-fw ${fitmentcolors[shortList.newfitment]}" aria-hidden="true"></i> 
													
													<!-- END Sasedeve Added by Mrutyunjaya on Date 10-05-2017 -->
													<label
												class="Manual_Individual_area_txt_label occupation_shortlist_cart">
												<span class="occupation_name">${start+1})&nbsp;${utils:replaceXMLEntities(shortList.occupationName)}</span>&nbsp;(&nbsp;<span class="bracket_name" >${utils:replaceXMLEntities(shortList.industryName)}</span>&nbsp;)&nbsp;</label>
												</c:when>
											</c:choose>
										</div>
									</div>
								<c:set var="start" value="${start + 1}"></c:set>
							</c:forEach>
							</div>
							<c:set var="end" value="${end + 2}"></c:set>
							</c:forEach>
							</div>
						</div>
					</div>
				</c:if>
			</div>
			<c:set var="toggleValue" value="${toggleValue + 1}"></c:set>
			<div class="portlet box #ffffff" style="border: 1px solid #d8d8d8;">
				<div class="portlet-title" >
					<div class="caption" style="color: #000000;">
						
						<s:text name="com.edupath.studentcart.educationpathway.title"></s:text>
					</div>
					<c:if test="${null != edupathList and not empty edupathList}">
						<div class="pull-right">
							<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls toggle_edupath" data-toggleValue="${toggleValue}"></i>
							<input type="hidden" id="toggle_${toggleValue}" value="YES"/>
						</div>
					</c:if>
				</div>
				<c:if test="${null != edupathList and not empty edupathList}">
					<div class="portlet-body form edupath toggleDiv_${toggleValue}" style="border-top: 1px solid #d8d8d8;">
						<div class="form-body">
							<c:if test="${userType == 'facilitator' }">
								<div class="col-md-12" style="padding-left: 0px !important;">
							      	<button id="edupath-del-btn" class="btn btn_action pull-right display-hide" onclick="fnRemove('edupath',0);"><span class="next-spn"><s:text name="com.edupath.common.delete"></s:text>&nbsp;EDUPATH</span></button>
							   </div> 
							</c:if>
							<c:set var="start" value="1"></c:set>
							<c:set var="end" value="1"></c:set>
							<c:set var="length" value="${fn:length(edupathList)}"></c:set>
							<c:forEach items="${edupathList}" var="edupath" varStatus="count">
								<c:if test="${start eq end }" >
									<div class="row">
									<c:set var="end" value="${end + 2}"></c:set>
								</c:if>
									<div class="col-md-6  deleteedupath edupath_${edupath.id}" style="">
										<div class="occupationnamediv " style="border-radius: 6px !important;" >
										<div style="padding-left: 10px;">
											<div style="margin-bottom: 20px;padding-left: 15px;">
												<c:if test="${null != edupath.industryName}">
													<img alt="" style="height: 55px; width: 55px;" src="${pageContext.request.contextPath}/images/icons/edupath-industry-icon.gif">&nbsp;&nbsp;
													
													<span style=" font-size: 12pt;  /* font-weight: 600; */ font-style: normal; line-height: 10pt; letter-spacing: 0.03em;  color: black;">
														${pageScope.start})&nbsp;${edupath.industryName}
													</span>&nbsp;
												</c:if>
												<c:if test="${null != edupath.occupationName}">
													<img alt="" style="height: 55px; width: 55px;" src="${pageContext.request.contextPath}/images/icons/edupath-occupation-icon.gif">&nbsp;&nbsp;
													<span style=" font-size: 12pt;  /* font-weight: 600; */ font-style: normal; line-height: 10pt; letter-spacing: 0.03em;  color: black;">
														${pageScope.start})&nbsp;${edupath.occupationName}
													</span>&nbsp;
													
													<!-- BEGIN Sasedeve Added by Mrutyunjaya on Date 25-05-2017 -->
																<i class="fa fa-circle fa-fw ${fitmentcolors[edupath.fitment]}" aria-hidden="true"></i>
													
													
												 	<i class="fa fa-square fa-fw ${fitmentcolors[edupath.newfitment]}" aria-hidden="true"></i> 
													
													<!-- END Sasedeve Added by Mrutyunjaya on Date 25-05-2017 -->
													
													
													
												</c:if>
											</div>
											<c:if test="${null != edupath.streamName and edupath.streamName != ''}">
												<div style="margin-bottom: 10px;min-height: 35px;">
												<span></span>
												<span style="width:40px">
													<img alt="" style="height: 40px; width: 40px;" src="${pageContext.request.contextPath}/images/icons/edupath-PU.gif">&nbsp;&nbsp;
												</span>
													<span style="padding-left: 10px; font-size:10pt;">
														PU Stream : ${edupath.streamName}
													</span>
												</div>
											</c:if>
											<c:if test="${null != edupath.combinationName and edupath.combinationName != ''}">
												<div style="margin-bottom: 10px;border-left: 1px solid #A5C2D0;min-height: 30px;margin-left: 20px;"></div>
												<div style="margin-bottom: 10px;min-height: 35px;">
												<span style="width:40px">
													<img alt="" style="height: 40px; width: 40px;" src="${pageContext.request.contextPath}/images/icons/edupath-PU.gif">&nbsp;&nbsp;
												</span>
													<span style="padding-left: 10px; font-size:10pt;">
														PU Elective : ${edupath.combinationName}
													</span>
												</div>
											</c:if>
											<c:if test="${null != edupath.degreeStream and edupath.degreeStream != ''}">
												<div style="margin-bottom: 10px;border-left: 1px solid #A5C2D0;min-height: 30px;margin-left: 20px;"></div>
												<div style="margin-bottom: 10px;min-height: 35px;">
												<span style="width:40px">
													<img alt="" style="height: 40px; width: 40px;" src="${pageContext.request.contextPath}/images/icons/edupath-university-icon.gif">&nbsp;&nbsp;
												</span>
													<span style="padding-left: 10px; font-size:10pt;">
														Degree Stream : ${edupath.degreeStream}&nbsp;
														<c:if test="${edupath.degreeSubject != null and edupath.degreeSubject != ''}">
															- ${edupath.degreeSubject}
														</c:if> 
													</span>
												</div>
											</c:if>
											<c:if test="${null != edupath.pgStream and edupath.pgStream != ''}">
												<div style="margin-bottom: 10px;border-left: 1px solid #A5C2D0;min-height: 30px;margin-left: 20px;"></div>
												<div style="margin-bottom: 10px;min-height: 35px;">
												<span style="width:40px">
													<img alt="" style="height: 40px; width: 40px;" src="${pageContext.request.contextPath}/images/icons/edupath-university-icon.gif">&nbsp;&nbsp;
												</span>
													<span style="padding-left: 10px; font-size:10pt;">
														PG Stream : ${edupath.pgStream}&nbsp;
														<c:if test="${edupath.pgSubject != null and edupath.pgSubject != ''}">
															 - ${edupath.pgSubject}
														</c:if> 
													</span>
												</div>
											</c:if>
										</div>
										</div>
									</div>
								<c:if test="${start eq end - 1 || length eq start }">
									</div>
								</c:if>
								<c:set var="start" value="${start + 1}"></c:set>
							</c:forEach>
						</div>
					</div>
				</c:if>
			</div>
			<c:set var="toggleValue" value="${toggleValue + 1}"></c:set>
			<div class="portlet box #ffffff" style="border: 1px solid #d8d8d8;">
				<div class="portlet-title" >
					<div class="caption" style="color: #000000;">
						
						<s:text name="com.edupath.studentcart.integratedcourses.title"></s:text>
					</div>
					<c:if test="${null != integratedCourseExamMap and not empty integratedCourseExamMap}">
						<div class="pull-right">
							<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls toggle_integrated_cource" data-toggleValue="${toggleValue}"></i>
							<input type="hidden" id="toggle_${toggleValue}" value="YES"/>
						</div>
					</c:if>
				</div>
				<c:if test="${null != integratedCourseExamMap and not empty integratedCourseExamMap}">
					<div class="portlet-body form integrated_cource toggleDiv_${toggleValue}" style="border-top: 1px solid #d8d8d8;">
						<div class="form-body">
							<c:forEach items="${integratedCourseExamMap}" var="courseMap">
								<div class="exam-occ-name">
									${counter.index + 1}) ${utils:replaceXMLEntities(courseMap.value.occupationName)}
								</div>
								<c:set var="start" value="1"></c:set>
								<c:set var="end" value="1"></c:set>
								<c:set var="length" value="${fn:length(courseMap.value.courseList)}"></c:set>
								<c:forEach items="${courseMap.value.courseList}" var="integratedCourse" varStatus="count">
									<c:if test="${start eq end }" >
										<div class="row">
										<c:set var="end" value="${end + 4}"></c:set>
									</c:if>
										<div class="col-md-3 custom-box-ft-subject integrated_cource_${integratedCourse.id}" style="margin-top: 10px;">
											<div class="occupationnamediv " style="border-radius: 6px !important; cursor: pointer;" >
												<c:if test="${userType == 'facilitator' }">
													<div style="height:4%" class="display-hide">
														<i class="fa fa-times" style="float:right; cursor: pointer;padding: 0 8px 0 0; z-index:9999;" onclick="fnRemove('integrated_cource',${integratedCourse.id});"></i>
													</div>
												</c:if>
												<label class="Manual_Individual_area_txt_label occupation_name_cart" style="cursor: pointer;" 
													onclick="fnGetIntegratedCourse('${integratedCourse.id}');">
													${utils:replaceXMLEntities(integratedCourse.programName)}&nbsp;(&nbsp;<span class="bracket_name" >${utils:replaceXMLEntities(integratedCourse.programType)}</span>&nbsp;)&nbsp;
													<i class="fa fa-external-link fa-fw"></i>
												</label>
											</div>
										</div>
									<c:if test="${start eq end - 1 || length eq start }">
										</div>
									</c:if>
									<c:set var="start" value="${start + 1}"></c:set>
								</c:forEach>
							</c:forEach>
						</div>
					</div>
				</c:if>
			</div>
			<c:set var="toggleValue" value="${toggleValue + 1}"></c:set>
			<div class="portlet box #ffffff" style="border: 1px solid #d8d8d8;">
				<div class="portlet-title" >
					<div class="caption" style="color: #000000;">
						<s:text name="com.edupath.studentcart.exams.title"></s:text>
					</div>
					<c:if test="${null != entranceExamMap and not empty entranceExamMap}">
						<div class="pull-right">
							<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls toggle_exam" data-toggleValue="${toggleValue}"></i>
							<input type="hidden" id="toggle_${toggleValue}" value="YES"/>
						</div>
					</c:if>
				</div>
				<c:if test="${null != entranceExamMap and not empty entranceExamMap}">
					<div class="portlet-body form exam toggleDiv_${toggleValue}" style="border-top: 1px solid #d8d8d8;">
						<div class="form-body">
						<c:forEach items="${entranceExamMap}" var="examMap" varStatus="counter">
							<div class="exam-occ-name">
								${counter.index + 1}) ${utils:replaceXMLEntities(examMap.value.occupationName)}
							</div>
							<c:set var="start" value="1"></c:set>
							<c:set var="end" value="1"></c:set>
							<c:set var="length" value="${fn:length(examMap.value.examList)}"></c:set>
							<c:forEach items="${examMap.value.examList}" var="entranceExam" varStatus="count">
								<c:if test="${start eq end }" >
									<div class="row">
									<c:set var="end" value="${end + 4}"></c:set>
								</c:if>
									<div class="col-md-3 custom-box-ft-subject exam_${entranceExam.id}" style="margin-top: 10px;">
										<div class="occupationnamediv" style="border-radius: 6px !important; cursor: pointer;" >
											<c:if test="${userType == 'facilitator' }">
											<div style="height:4%" class="display-hide">
												<i class="fa fa-times" style="float:right; cursor: pointer;padding: 0 8px 0 0; z-index:9999;" onclick="fnRemove('exam',${entranceExam.id});"></i>
											</div>
											</c:if>
											<label
												class="Manual_Individual_area_txt_label occupation_name_cart" style="cursor: pointer;" 
												onclick="fnGetEntranceExam('${entranceExam.id}');">
												${utils:replaceXMLEntities(entranceExam.examName)}&nbsp;(&nbsp;<span class="bracket_name" >${utils:replaceXMLEntities(entranceExam.examLevel)}</span>&nbsp;)&nbsp;
												<i class="fa fa-external-link fa-fw"></i>
												</label>
										</div>
									</div>
								<c:if test="${start eq end - 1 || length eq start }">
									</div>
								</c:if>
								<c:set var="start" value="${start + 1}"></c:set>
							</c:forEach>
						</c:forEach>
						</div>
					</div>
				</c:if>
			</div>
			<c:set var="toggleValue" value="${toggleValue + 1}"></c:set>
			<div class="portlet box #ffffff" style="border: 1px solid #d8d8d8;">
				<div class="portlet-title" >
					<div class="caption" style="color: #000000;">
						<s:text name="com.edupath.studentcart.tutorials.title"></s:text>
					</div>
					<c:if test="${null != tutorialCenterShortListDetails and not empty tutorialCenterShortListDetails}">
						<div class="pull-right">
							<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls toggle_exam" data-toggleValue="${toggleValue}"></i>
							<input type="hidden" id="toggle_${toggleValue}" value="YES"/>
						</div>
					</c:if>
				</div>
				<c:if test="${null != tutorialCenterShortListDetails and not empty tutorialCenterShortListDetails}">
					<div class="portlet-body form toggleDiv_${toggleValue}" style="border-top: 1px solid #d8d8d8;">
						<div class="form-body">
							<div class="row">
								<c:forEach items="${tutorialCenterShortListDetails}" var="tutorialShortListDetail" varStatus="counter">
									<div class="col-md-3 custom-box-ft-subject student-cart-tut-col">
										<div class="occupationnamediv" style="border-radius: 6px !important; cursor: pointer; min-height: 74px !important;" >
											<c:if test="${userType == 'facilitator' }">
												<div style="height:4%" class="display-hide">
													<i class="fa fa-times" style="float:right; cursor: pointer;padding: 0 8px 0 0; z-index:9999;" onclick="fnRemove('tutorial',${tutorialShortListDetail.id});"></i>
												</div>
											</c:if>
											<label class="Manual_Individual_area_txt_label occupation_name_cart" style="cursor: pointer;" onclick="fnGetTutorialDetails('${tutorialShortListDetail.tutorialId}', '${tutorialShortListDetail.tutorialCityCentersId}');">
												${utils:replaceXMLEntities(tutorialShortListDetail.tutorialName)}&nbsp;
												(&nbsp;<span class="bracket_name" >${utils:replaceXMLEntities(tutorialShortListDetail.cityName)}</span>&nbsp;
												-&nbsp;<span class="bracket_name" >${utils:replaceXMLEntities(tutorialShortListDetail.locality)}</span>&nbsp;
												)&nbsp;
												<i class="fa fa-external-link fa-fw"></i>
											</label>
										</div>
									</div>
									<c:if test="${(counter.index + 1) % 4 eq 0}">
										<s:text name="</div><div class='row'>"/>																									
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
				</c:if>
			</div>
			<c:set var="toggleValue" value="${toggleValue + 1}"></c:set>
			<div class="portlet box #ffffff" style="border: 1px solid #d8d8d8;">
				<div class="portlet-title" >
					<div class="caption" style="color: #000000;">
						<s:text name="com.edupath.studentcart.colleges.title"></s:text>
					</div>
					<c:if test="${null != collegeShortListDetails and not empty collegeShortListDetails}">
						<div class="pull-right">
							<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls toggle_exam" data-toggleValue="${toggleValue}"></i>
							<input type="hidden" id="toggle_${toggleValue}" value="YES"/>
						</div>
					</c:if>
				</div>
				<c:if test="${null != collegeShortListDetails and not empty collegeShortListDetails}">
					<div class="portlet-body form toggleDiv_${toggleValue}" style="border-top: 1px solid #d8d8d8;">
						<div class="form-body">
							<div class="row">
								<c:forEach items="${collegeShortListDetails}" var="collegeShortListDetail" varStatus="counter">
									<div class="col-md-3 custom-box-ft-subject student-cart-tut-col">
										<div class="occupationnamediv" style="border-radius: 6px !important; cursor: pointer; min-height: 74px !important;" >
											<c:if test="${userType == 'facilitator' }">
												<div style="height:4%" class="display-hide">
													<i class="fa fa-times" style="float:right; cursor: pointer;padding: 0 8px 0 0; z-index:9999;" onclick="fnRemove('college',${collegeShortListDetail.id});"></i>
												</div>
											</c:if>
											<label class="Manual_Individual_area_txt_label occupation_name_cart" style="cursor: pointer;" onclick="fnGetCollegeDetails('${collegeShortListDetail.collegeId}');">
												${utils:replaceXMLEntities(collegeShortListDetail.collegeName)}
												<i class="fa fa-external-link fa-fw"></i>
											</label>
										</div>
									</div>
									<c:if test="${(counter.index + 1) % 4 eq 0}">
										<s:text name="</div><div class='row'>"/>																									
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
				</c:if>
			</div>
			<!-- Reports starts -->
			<c:set var="toggleValue" value="${toggleValue + 1}"></c:set>
			<div class="portlet box #ffffff" style="border: 1px solid #d8d8d8;">
				<div class="portlet-title" >
					<div class="caption" style="color: #000000;">
						<s:text name="com.edupath.studentcart.reports.title"></s:text>
					</div>
					<c:if test="${null != reportsMap and not empty reportsMap}">
						<div class="pull-right">
							<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls toggle_report" data-toggleValue="${toggleValue}"></i>
							<input type="hidden" id="toggle_${toggleValue}" value="YES"/>
						</div>
					</c:if>
				</div>
				<c:if test="${null != reportsMap and not empty reportsMap}">
					<div class="portlet-body form toggleDiv_${toggleValue}" style="border-top: 1px solid #d8d8d8;">
						<div class="form-body">
							<c:forEach items="${reportsMap}" var="reports">
								<c:if test="${reports.key == 'industry' and null != reports.value and not empty reports.value}">
									<div class="report-title-label">
										<s:text name="com.edupath.studentcart.reports.industry.title"></s:text>
									</div>
									<div class="row">
										<c:forEach items="${reports.value}" var="industry" varStatus="counter">
											<div class="col-md-3 custom-box-ft-subject report-col-div">
												<div class="occupationnamediv report-col-sub-div">
													<label class="Manual_Individual_area_txt_label occupation_name_cart" style="cursor: pointer;" onclick="fnShowIndustryReport('${industry.id}');">
														${utils:replaceXMLEntities(industry.name)}&nbsp;
														<i class="fa fa-external-link fa-fw"></i>
													</label>
												</div>
											</div>
											<c:if test="${(counter.index + 1) % 4 eq 0}">
												<s:text name="</div><div class='row'>"/>																									
											</c:if>
										</c:forEach>
									</div>
								</c:if>
								<c:if test="${reports.key == 'occupation' and null != reports.value and not empty reports.value}">
									<div class="report-title-label">
										<s:text name="com.edupath.studentcart.reports.occupation.title"></s:text>
									</div>
									<div class="row">
										<c:forEach items="${reports.value}" var="occupation" varStatus="counter">
											<div class="col-md-3 custom-box-ft-subject report-col-div">
												<div class="occupationnamediv report-col-sub-div">
													<label class="Manual_Individual_area_txt_label occupation_name_cart" style="cursor: pointer;" onclick="fnShowOccReport('${occupation.id}', '${occupation.industryId}');">
														${utils:replaceXMLEntities(occupation.name)}&nbsp;
														<i class="fa fa-external-link fa-fw"></i>
													</label>
												</div>
											</div>
											<c:if test="${(counter.index + 1) % 4 eq 0}">
												<s:text name="</div><div class='row'>"/>																									
											</c:if>
										</c:forEach>
									</div>
								</c:if>
								<c:if test="${reports.key == 'subject' and null != reports.value and not empty reports.value}">
									<div class="report-title-label">
										<s:text name="com.edupath.studentcart.reports.subject.title"></s:text>
									</div>
									<div class="row">
										<c:forEach items="${reports.value}" var="subject" varStatus="counter">
											<div class="col-md-3 custom-box-ft-subject report-col-div">
												<div class="occupationnamediv report-col-sub-div">
													<label class="Manual_Individual_area_txt_label occupation_name_cart" style="cursor: pointer;" onclick="fnShowSubjectReport('${subject.id}');">
														${utils:replaceXMLEntities(subject.name)}&nbsp;
														<i class="fa fa-external-link fa-fw"></i>
													</label>
												</div>
											</div>
											<c:if test="${(counter.index + 1) % 4 eq 0}">
												<s:text name="</div><div class='row'>"/>																									
											</c:if>
										</c:forEach>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	</div>
	<!-- Reports end -->
	
	
	<div id="courseAndExamDiv" style="padding-left:25px;">
	
	</div>
	<div id="studCartColContentDiv" style="padding-left:25px;">
	
	</div>
	<div id="studCartTutContentDiv" style="padding-left:25px;">
	
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	if('${StudentSessionObject.isCanChangeCart}' == 'true')
	{
		$('#edupath-del-btn').removeClass('display-hide');
		$('.fa.fa-times').parent().removeClass('display-hide');	
	}
});

function fnRemove(type,id){
	
	if (confirm('Are you sure you want to delete?')) {
		document.getElementById("cityId").disabled = false;
		$("#student_info_div").html('<div align="center"><img src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif"></div>');
		 $.ajax({
			  url: "${pageContext.request.contextPath}/myapp/removeFromCartStudentCartDetailAction",
			  data:{
				"id":id,
				"cartRowValue":type
			  },
			  success: function(result)
			  {
				  $("#student_info_div").html(result);
			  },
			  error: function(jqXHR, textStatus, errorThrown )
			  {
				  alert("Exception while deleting shortlist from cart");
			  } 
		}); 
	}
}

function fnUpdateCart(type, result){
	
	var totalCount=result.newResultList.length;
		var start=1;
		var end=1;
		var isResultEmpty = true;
		$('.'+type).html('');
		var updateHtml = '<div class="form-body">';
	 $(result.newResultList).each(function(i, val){
		 isResultEmpty = false;
		var name='';
		var bck_name='';
		if(type == 'exam')
			{
				name = val.examName;
				bck_name = val.examLevel;
			}
		else if(type == 'integrated_cource')
			{
				name = val.programName;
				bck_name = val.programType;
			}
		if(start == end)
			{
				updateHtml += '<div class="row">';
				if(type == 'exam' || type == 'integrated_cource')
				{
					end = end + 4;
				}
			}
		if(type == 'exam' || type == 'integrated_cource')
			{
			 updateHtml +=  '<div class="col-md-3 custom-box-ft-subject '+type+'_'+val.id+'" style="margin-top: 10px;">'
								+'<div class="occupationnamediv" style="border-radius: 6px !important;" >'
									+'<div style="height:4%" class="display-hide"><i class="fa fa-times remove_class" style="" onclick="fnRemove(\''+type+'\','+val.id+');"></i></div>'
										+'<label class="Manual_Individual_area_txt_label occupation_name_cart" style="">'
										+name+'&nbsp;(&nbsp;<span class="bracket_name" >'+bck_name+'</span>&nbsp;)&nbsp;<i class="fa fa-external-link fa-fw"></i></label>'
								+'</div>'
							+'</div>';
			}
		if(start == (end - 1) || totalCount == start )
			{
				updateHtml += '</div>';
			}
		start = start + 1;
	 });
	 updateHtml += "</div>";
	 if(!isResultEmpty)
		 {
			$('.'+type).html(updateHtml);
		 }
	 else
		 {
		 	$('.'+type).hide();
		 	$('.toggle_'+type).hide();
		 }
}

$('.common_cls').click(function(){
	var id = $(this).data("togglevalue");
	var toggle = $('#toggle_'+id).val();

	if(toggle == 'NO')
	{
		$(this).addClass('fa-arrow-circle-o-up');
		$(this).removeClass('fa-arrow-circle-o-down');
		$('#toggle_'+id).val('YES');
	}
	else if(toggle == 'YES')
	{
		$(this).removeClass('fa-arrow-circle-o-up');
		$(this).addClass('fa-arrow-circle-o-down');
		$('#toggle_'+id).val('NO');
	}
	
	$(".toggleDiv_"+id).slideToggle();
});

function fnGetEntranceExam(id)
{
	fnDoAjax(id, "E");
}

function fnGetIntegratedCourse(id)
{
	fnDoAjax(id, "C");
}

function fnGetTutorialDetails(tutorialId, tutorialCityCentersId)
{
	$.ajax({
		  url: "${pageContext.request.contextPath}/myapp/getTutorialDetailsStudentCartDetailAction",
		  data:
		  {
			"tutorialId" : tutorialId,
			"tutorialCityCentersId" : tutorialCityCentersId
		  },
		  success: function(result)
		  {
			  $('#cartDIV').hide();
			  $('#studCartTutContentDiv').html('');
			  $('#studCartTutContentDiv').html(result);
			  $('#studCartTutContentDiv').show();
			  $("#myCart").scrollTop(0);
		  },
		  error: function(jqXHR, textStatus, errorThrown )
		  {
			  alert("Page load error");
		  } 
	});		
}

function fnGetCollegeDetails(collegeId)
{
	 $.ajax({
		  url: "${pageContext.request.contextPath}/myapp/getCollegeDetailsStudentCartDetailAction",
		  data:
		  {
			"collegeId" : collegeId
		  },
		  success: function(result)
		  {
			  $('#cartDIV').hide();
			  $('#studCartColContentDiv').html('');
			  $('#studCartColContentDiv').html(result);
			  $('#studCartColContentDiv').show();
			  $("#myCart").scrollTop(0);
		  },
		  error: function(jqXHR, textStatus, errorThrown )
		  {
			  alert("Page load error");
		  } 
	});		
}

function fnDoAjax(id, type)
{
	 $.ajax({
		  url: "${pageContext.request.contextPath}/myapp/getStudentCourseAndExamStudentCartDetailAction",
		  data:
		  {
			"examOrCourseId" :id,
			"type" : type
		  },
		  success: function(result)
		  {
			  $('#cartDIV').hide();
			  $('#courseAndExamDiv').html('');
			  $('#courseAndExamDiv').html(result);
			  $('#courseAndExamDiv').show();
			  $("#myCart").scrollTop(0);
		  },
		  error: function(jqXHR, textStatus, errorThrown )
		  {
			  alert("Exception");
		  } 
	});	
}

/* Report documentation */
function fnShowSubjectReport(subjectId)
{
	fnGetContent("subjectId="+subjectId, 'SUBJECT');
}

function fnShowIndustryReport(indusId)
{
	fnGetContent("industryId="+indusId, "OCCUPATION");
}

function fnShowOccReport(occId, occIndusId)
{
	fnGetContent("occupationId="+occId+"&industryId="+occIndusId, "OCCUPATION");
}

function fnGetContent(dataStr, locDocType)
{
	if('${userType}' == 'facilitator')
	{
		alert("Only Student can view the report")
	}
	else{
		fnShowReadingPage();
		fnLoadContent(dataStr, locDocType);		
	}
}
function fnShowReadingPage()
{
	if(newWindow!=null)
    {
         if(!newWindow.closed)
         {
        	 newWindow.close();
         }
    }
	
	newWindow = window.open("${pageContext.request.contextPath}/pages/stuinduoccchoice/ReadingPage.jsp","ReadingPane"
			,"height=700px, width=1100px, left=0, top=0, toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no",true);
	newWindow.focus();
}

var datastr;
var docType;
var fromCart;
function fnLoadContent(dataStr, locDocType)
{
	datastr = dataStr;
	docType = locDocType;
	fromCart = true;
	newWindow.onload = function()
    {
		//newWindow.fnGetDocumentURLNLoad(true, dataStr);
    }
};

</script>
<style type="text/css">
.remove_class {
	float: right;
	cursor: pointer;
	padding: 0 8px 0 0;
	z-index: 9999;
}
.common_cls{
	padding: 13px 0 9px;
	cursor: pointer;
}
.exam-occ-name
{
  color: #67A7D9;
  margin-bottom: 10px;
  padding-top: 20px;
}
</style>