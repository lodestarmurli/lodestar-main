<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:form name="manualSearchForm" method="post" id="manualSearchForm" >
<s:hidden name="studentId" id="studentId" />
<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
<s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
<s:hidden name="selectedAnswer" id="selectedAnswer"/>
<c:set var="questionNo" value="0"></c:set>
<div style="word-wrap: break-word;" class="edupath-padding">
	<div class="row">
		<div class="col-md-8">	
			<div id="contentdiv">
				
				<c:choose>
					<c:when test="${actiontype eq 'searchResult' }">
						<jsp:include page="/pages/induoccchoice/ManualSearchResultScreen.jsp">
							<jsp:param value="" name="arg0"/>
						</jsp:include>
					</c:when>
					<c:otherwise>
				<div class="pagetitle">
					<s:text name="com.lodestar.edupath.navigation.tab.Manualsearch"></s:text>
				</div>
					
					<div style="padding-top: 20px;">
						<input type="hidden" id="hidden_answer_${questionNo}" class="interested_subjects">
						<div class="questiondiv">
							<span class="questionspan" style="padding-left:30px;">Search by keyword (Separated by comma)</span>
							<div class="pull-right">
								<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls arrow_question_${questionNo}" style="margin-right:6px;"data-question="${questionNo}"></i>
								<input type="hidden" id="toggle_${questionNo}" value="YES"/>
							</div>
						</div>
						<div id="answer_${questionNo}" >
							<div class="txt-field " style="padding-left: 45px; width:98%;">
								<s:textarea name="occName" id="occName" class="form-control" data-question="${questionNo}" />
							</div>
						</div>
					</div>
						<c:set var="questionNo" value="${questionNo + 1}"></c:set>
						
					<div>
						<div class="topicheading">
							<s:text name="com.edupath.manualsearch.leble.education"></s:text>
						</div>
						
						<div>
							<input type="hidden" id="hidden_answer_${questionNo}" class="interested_subjects">
							<div class="questiondiv">
								<i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
								<i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i>
								<span class="questionspan">Which subjects do you like the most? (Max of 2)</span>
								<div class="pull-right">
									<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls arrow_question_${questionNo}"  style="margin-right:6px; data-question="${questionNo}"></i>
									<input type="hidden" id="toggle_${questionNo}" value="YES"/>
								</div>
							</div>
							<div id="answer_${questionNo}">
								<div class="txt-field " style="padding-left: 40px">
									<%
										Map subjectMap = new LinkedHashMap();
										subjectMap.put("Physics","Physics");
										subjectMap.put("Chemistry","Chemistry");
										subjectMap.put("Mathematics","Math");
										subjectMap.put("Biology","Biology");
										subjectMap.put("Home Science","Home");
										subjectMap.put("Economics","Economics");
										subjectMap.put("English","English");
										subjectMap.put("Geography","Geography");
										subjectMap.put("History","History");
										subjectMap.put("Environmental Science","Environmental");
										subjectMap.put("Technical drawing","drawing");
										subjectMap.put("Art","Art");
										subjectMap.put("Psychology","Psychology");
										subjectMap.put("Computer Science","Computer");
										subjectMap.put("Electronics","Electronics");
										
										request.setAttribute("subjectMap", subjectMap);
									%>
									<div class="row">
										<c:forEach var="entry" items="${subjectMap}" varStatus="count">
											<c:if test="${count.index gt 0 and count.index %4 eq 0 }">
												</div>
												<div class="row">
											</c:if>
									  			<div class="col-md-3 custom-box-ft-subject">
													<div class="checkbox checkbox-primary">
														<input id="checkbox_answer_${questionNo}_${count.index}" name="favSubject"
															data-serial="${count.index}" data-question="${questionNo}" value="${entry.value}"
															class="auto_save styled selected_checkbox_subject checkbox_answer_${questionNo}" type="checkbox"> <label
															for="checkbox_answer_${questionNo}_${count.index}"
															class="Manual_Education_answer_txt_label">${fn:trim(entry.key)}</label>
													</div>
												</div>
										</c:forEach>
									</div>
									<div>
										<span class="error-block"></span>
									</div>
								</div>
							</div>
						</div>
						
						<c:set var="questionNo" value="${questionNo + 1}"></c:set>
						
						<div>
						<input type="hidden" id="hidden_answer_${questionNo}" class="interested_qualification">
						<div class="questiondiv">
							<i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
							<i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i>
							<span class="questionspan">What is the maximum qualification you are looking at?</span>
							<div class="pull-right">
								<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls arrow_question_${questionNo}"  style="margin-right:6px; data-question="${questionNo}"></i>
								<input type="hidden" id="toggle_${questionNo}" value="YES"/>
							</div>
						</div>
						<div id="answer_${questionNo}">
							<div class="txt-field  " style="padding-left: 35px">
							<c:set var="start" value="1"></c:set>
							<c:set var="end" value="1"></c:set>
							<c:set var="length" value="${fn:length(educationLevelList)}"></c:set>
							<c:forEach items="${educationLevelList}" var="qualificationName" varStatus="count">
								<c:if test="${start eq end }" >
									<div class="row">
									<c:set var="end" value="${end + 4}"></c:set>
								</c:if>
									<div class="col-md-3 custom-box-ft-subject">
										 <div class="radio radio-success radio-inline">
											<input type="radio" id="checkbox_answer_${questionNo}_${qualificationName.id}" name="strength"
												data-serial="${qualificationName.id}" data-question="${questionNo}" value="${qualificationName.id}"
												class="auto_save selected_checkbox_qualification"> <label
												for="checkbox_answer_${questionNo}_${qualificationName.id}" class="Manual_Education_answer_txt_label">${fn:trim(qualificationName.name)}</label>
										</div>
									</div>
								<c:if test="${start eq end - 1 || length eq start }">
									</div>
								</c:if>
								<c:set var="start" value="${start + 1}"></c:set>
							</c:forEach>
								<div>
									<span class="error-block"></span>
								</div>
							</div>
						</div>
						<c:set var="questionNo" value="${questionNo + 1}"></c:set>
					</div>
				</div>
					<div>
						<div class="topicheading">
							<s:text name="com.edupath.manualsearch.leble.individual"></s:text>
						</div>
						<div>
							<div class="col-md-12" style="padding-right: 7px !important">
							     <div class="col-md-9" style="padding:7px 0px 0px 0px !important">
							         <div class="qusetion_txt">
							            <div class="serial_left_div ">
							               <i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
											<i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i>
							            </div>
							            <div class="qusetion_right_div">
							             <p class="question_p questionspan">Would you prefer jobs with high people interaction?</p>
							            </div>
							          </div>
							     </div>
							    <div class="col-md-3 btn_manual_qt">
							       <div class="btn-group">
							         <input type="hidden" id="hidden_answer_${questionNo}" class="prefer_job">
							         <button type="button" class="btn btn_qt" data-answer="YES" data-question="${questionNo}" id="yes_${questionNo}" value="yes"><span class="Manual_question_btn"><s:text name="com.edupath.questionnarie.yes"/></span></button>
							         <button type="button" class="btn btn_qt" data-answer="Not Yes" data-question="${questionNo}" id="no_${questionNo}" value="no"><span class="Manual_question_btn"><s:text name="com.edupath.questionnarie.no"/></span></button>
							       </div>
							    </div>
							</div>
						</div>
						<c:set var="questionNo" value="${questionNo + 1}"></c:set>
						<div>
							<div  class="col-md-12" style="padding-top: 10px;padding-right: 7px !important">
							     <div class="col-md-9 col-xs-12" style="padding:7px 0px 0px 0px !important"><!-- added col-sm-12 28/03/18 -->
							         <div class="qusetion_txt">
							            <div class="serial_left_div ">
							              <i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
										  <i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i>
							            </div>
							            <div class="qusetion_right_div">
							             <p class="question_p questionspan">Are you interested in team work?</p>
							            </div>
							          </div>
							     </div>
							    <div class="col-md-3 btn_manual_qt">
							       <div class="btn-group">
							         <input type="hidden" id="hidden_answer_${questionNo}" class="interested_team_work">
							         <button type="button" class="btn btn_qt" data-answer="YES" data-question="${questionNo}" id="yes_${questionNo}"><span class="Manual_question_btn"><s:text name="com.edupath.questionnarie.yes"/></span></button>
							         <button type="button" class="btn btn_qt" data-answer="Not Yes" data-question="${questionNo}" id="no_${questionNo}"><span class="Manual_question_btn"><s:text name="com.edupath.questionnarie.no"/></span></button>
							       </div>
							    </div>
							</div>
						</div>
						<c:set var="questionNo" value="${questionNo + 1}"></c:set>
						<div class="row top-buffer">
								<c:forEach items="${categoryMap}" var="category">
								<div class="col-md-12 col-xs-12" style="padding-top: 10px;">	<!-- added col-sm-12 28/03/18 -->
									<input type="hidden" id="hidden_answer_${questionNo}" class="${category.key}">
									<div class="questiondiv" style="">
										 <i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
													  <i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i>
											<span class="questionspan">
												<c:if test="${category.key eq 'Skills' }">
													What are your specific strengths?
												</c:if>
												<c:if test="${category.key eq 'Interests' }">
													What are your special interests?
												</c:if>
											</span>
										<div class="pull-right">
											<i class="fa fa-arrow-circle-o-up arrow_up_icon common_cls arrow_question_${questionNo}"  style="margin-right: 21px;" data-question="${questionNo}"></i>  <!-- added 28/03/18 "margin-right: 21px -->
											<input type="hidden" id="toggle_${questionNo}" value="YES"/>
										</div>
									</div>
									<div id="answer_${questionNo}" class="answerDiv" style="padding-left: 40px;">
										<div class="txt-field">
											<c:set var="start" value="1"></c:set>
											<c:set var="end" value="1"></c:set>
											<c:set var="length" value="${fn:length(category.value)}"></c:set>
											<c:forEach items="${category.value}" var="subcategory" varStatus="count">
												<c:if test="${start eq end }" >
													<div class="row">
													<c:set var="end" value="${end + 4}"></c:set>
												</c:if>
													<div class="col-md-3 custom-box-ft-subject" style="padding-top: 10px">
														<div class="checkbox checkbox-primary" style="padding-left: 0px">
															<input type="checkbox" id="subCategory_${subcategory.value[0].subCategoryDTO.id}" name="subCategory" data-subCategoryId="${subcategory.value[0].subCategoryDTO.id}"
																data-serial="${start}" value="${subcategory.key}"
																class="auto_save styled subCategory_check subCategory_get_${questionNo}" data-question="${questionNo}"> <label
																for="subCategory_${subcategory.value[0].subCategoryDTO.id}" class="Manual_Individual_subCategory_txt_label">${fn:trim(subcategory.key)}</label>
														</div>
														<div class="txt-field  " style="padding-left: 10px">
															<c:forEach items="${subcategory.value}" var="area">
																<div class="col-md-12" style="  padding-left: 0px !important;">
																	<div class="checkbox checkbox-primary" style="padding-left: 0px;" >
																		<input type="checkbox" id="area_${area.id}" name="area" data-subCategoryId="${subcategory.value[0].subCategoryDTO.id}" data-areaId="${area.id}"
																			data-serial="${area.id}" value="${area.name}"
																			class="auto_save styled area_check subCategory_${subcategory.value[0].subCategoryDTO.id}" > <label
																			for="area_${area.id}" class="Manual_Individual_area_txt_label subCategory_${start}">${fn:trim(area.name)}</label>
																	</div>
																</div>
															</c:forEach>
														</div>
													</div>
												<c:if test="${start eq end - 1 || length eq start }">
													</div>
												</c:if>
												<c:set var="start" value="${start + 1}"></c:set>
											</c:forEach>
										</div>
									</div>
									<c:set var="questionNo" value="${questionNo + 1}"></c:set>
								</div>
								</c:forEach>
						</div>
						</div>
							<div>
								<div class="topicheading">
									<s:text name="com.edupath.manualsearch.leble.occupation"></s:text>
								</div>
								<div>
									<div  class="col-md-12" style="padding-right: 7px !important">
									     <div class="col-md-9" style="padding:7px 0px 0px 0px !important">
									         <div class="qusetion_txt">
									             <div class="serial_left_div ">
									              <i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
												  <i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i>
									            </div>	
									            <div class="qusetion_right_div">
									             <p class="question_p questionspan">Is salary the most important factor for you?</p>
									            </div>
									          </div>
									     </div>
									    <div class="col-md-3 btn_manual_qt">
									       <div class="btn-group">
									         <input type="hidden" id="hidden_answer_${questionNo}" class="salary">
									         <button type="button" class="btn sl_btn_qt" data-answer="YES"  data-question="${questionNo}" id="yes_${questionNo}" onclick="fnSetSalaryAnswer(this);"><span class="Manual_question_btn"><s:text name="com.edupath.questionnarie.yes"/></span></button>
									         <button type="button" class="btn sl_btn_qt" data-answer="NONE" data-question="${questionNo}" id="no_${questionNo}" onclick="fnSetSalaryAnswer(this);"><span class="Manual_question_btn"><s:text name="com.edupath.questionnarie.no"/></span></button>
									       </div>
									    </div>
									</div>
								</div>
								<c:set var="questionNo" value="${questionNo + 1}"></c:set>
								<div>
									<div  class="col-md-12" style="padding-top: 10px;padding-right: 7px !important">
									     <div class="col-md-9 col-xs-12" style="padding:7px 0px 0px 0px !important">	<!-- added col-sm-12 28/03/18 -->
									         <div class="qusetion_txt">
									            <div class="serial_left_div ">
									              <i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
												  <i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i>
									            </div>
									            <div class="qusetion_right_div">
									             <p class="question_p questionspan">Do you prefer to be self-employed?</p>
									            </div>
									          </div>
									     </div>
									    <div class="col-md-3 btn_manual_qt">
									      <div class="btn-group">
									         <input type="hidden" id="hidden_answer_${questionNo}" class="self_employed">
									         <button type="button" class="btn btn_qt" data-answer="YES" data-question="${questionNo}" id="yes_${questionNo}"><span class="Manual_question_btn"><s:text name="com.edupath.questionnarie.yes"/></span></button>
									         <button type="button" class="btn btn_qt" data-answer="Not Yes" data-question="${questionNo}" id="no_${questionNo}"><span class="Manual_question_btn"><s:text name="com.edupath.questionnarie.no"/></span></button>
									       </div>
									    </div>
									</div>
								</div>
								<c:set var="questionNo" value="${questionNo + 1}"></c:set>
								<div>
									<div  class="col-md-12" style="padding-top: 10px;padding-right: 7px !important">
									     <div class="col-md-9 col-xs-12" style="padding:7px 0px 0px 0px !important">	<!-- added col-sm-12 28/03/18 -->
									         <div class="qusetion_txt">
									             <div class="serial_left_div ">
										              <i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
													  <i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i>
									             </div>
									            <div class="qusetion_right_div">
									             <p class="question_p questionspan">Do you prefer a job with a lot of travel?</p>
									            </div>
									          </div>
									     </div>
									    <div class="col-md-3 btn_manual_qt">
									       <div class="btn-group">
									         <input type="hidden" id="hidden_answer_${questionNo}" class="travel">
									         <button type="button" class="btn btn_qt" data-answer="YES" data-question="${questionNo}" id="yes_${questionNo}"><span class="Manual_question_btn"><s:text name="com.edupath.questionnarie.yes"/></span></button>
									         <button type="button" class="btn btn_qt" data-answer="NO" data-question="${questionNo}" id="no_${questionNo}"><span class="Manual_question_btn"><s:text name="com.edupath.questionnarie.no"/></span></button>
									       </div>
									    </div>
									</div>
								</div>
								<c:set var="questionNo" value="${questionNo + 1}"></c:set>
								<div>
								 <input type="hidden" id="hidden_answer_${questionNo}">
									<div  class="col-md-12" style="padding-top: 10px;padding-right: 7px !important">
									     <div class="col-md-9 col-xs-12" style="padding:7px 0px 0px 0px !important">	<!-- added col-sm-12 28/03/18 -->
									         <div class="qusetion_txt">
									            <div class="serial_left_div ">
									               <i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
													  <i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i>
									            </div>
									            <div class="qusetion_right_div">
									             <p class="question_p questionspan">How important is job security to you?</p>
									            </div>
									          </div>
									     </div>
									    <div class="col-md-3 btn_manual_qt">
									       <div class="selectdiv_mn">
								               <s:select list="#{-1:'-- SELECT --','High' :'High', 'Medium':'Medium','Low':'Low'}" data-question="${questionNo}"  name="jobSecurity" id="jobSecurity" cssClass="input-selectbox auto_save selectboxdiv select_text chenge_select" ></s:select>
								               <div class="out"></div>
								           </div>
									    </div>
									</div>
								</div>
								<c:set var="questionNo" value="${questionNo + 1}"></c:set>
								<div>
								<input type="hidden" id="hidden_answer_${questionNo}" >
									<div  class="col-md-12" style="padding-top: 10px;padding-right: 7px !important">
									     <div class="col-md-9" style="padding:7px 0px 0px 0px !important">
									         <div class="qusetion_txt">
									             <div class="serial_left_div ">
									                <i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
													  <i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i>
									            </div>
									            <div class="qusetion_right_div">
									             <p class="question_p questionspan">What type of work environment would you prefer?</p>
									            </div>
									          </div>
									     </div>
									    <div class="col-md-3 btn_manual_qt">
									       <div class="selectdiv_mn">
								               <s:select list="#{-1:'-- SELECT --', 'Office' :'Office', 'Field':'Field','Factory':'Factory', 'Office-Field' :'Office N Field'}" data-question="${questionNo}"  name="workEnvironment" id="workEnvironment" cssClass="input-selectbox auto_save selectboxdiv select_text chenge_select"></s:select>
								               <div class="out"></div>
								           </div>
									    </div>
									</div>
								</div>
								<c:set var="questionNo" value="${questionNo + 1}"></c:set>
								<div>
								<input type="hidden" id="hidden_answer_${questionNo}" >
									<div  class="col-md-12" style="padding-top: 10px;padding-right: 7px !important">
									     <div class="col-md-9" style="padding:7px 0px 0px 0px !important">
									         <div class="qusetion_txt">
									            <div class="serial_left_div ">
									               <i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
													  <i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i>
									            </div>
									            <div class="qusetion_right_div">
									             <p class="question_p questionspan">Do you have a strong preference for regular working hours?</p>
									            </div>
									          </div>
									     </div>
									    <div class="col-md-3 btn_manual_qt">
									       <div class="selectdiv_mn">
								               <s:select list="#{-1:'-- SELECT --','Regular' :'Regular', 'Shift':'Shifts'}" data-question="${questionNo}" name="regularworkinghours" id="regularworkinghours" cssClass="input-selectbox auto_save selectboxdiv select_text chenge_select"></s:select>
								               <div class="out"></div>
								           </div>
									    </div>
									</div>
								</div>
								<c:set var="questionNo" value="${questionNo + 1}"></c:set>
								<div>
								<input type="hidden" id="hidden_answer_${questionNo}" >
									<div  class="col-md-12" style="padding-top: 10px;padding-right: 7px !important">
									     <div class="col-md-9" style="padding:7px 0px 0px 0px !important">
									         <div class="qusetion_txt">
									             <div class="serial_left_div ">
									                <i class="fa fa-star-o fav_question" id="fav_question_${questionNo}" data-question="${questionNo}"></i>
													  <i class="fa fa-star fav_question_selected" id="fav_question_${questionNo}_Selectd" data-question="${questionNo}" style="display:none;"></i>
									            </div>
									            <div class="qusetion_right_div">
									             <p class="question_p questionspan">In your occupation, you would prefer</p>
									            </div>
									          </div>
									     </div>
									    <div class="col-md-3 btn_manual_qt">
									       <div class="selectdiv_mn">
								               <s:select list="#{-1:'-- SELECT --','13.1' :'Social impact', '13.2':'Prestige & Influence'}" data-question="${questionNo}" name="occupationprefer" id="occupationprefer" cssClass="input-selectbox auto_save selectboxdiv select_text chenge_select"></s:select>
								               <div class="out"></div>
								           </div>
									    </div>
									</div>
								</div>
							</div>
							<div class="col-md-12 action-div" style="padding-left: 0px !important; padding-top: 30px !important;">
						      <button class="btn btn_action" onclick="return fnSearch();"><span class="next-spn"><s:text name="com.edupath.common.search"></s:text></span></button>
						   </div> 
				</div>
				</c:otherwise>
				</c:choose>
			
				<div id="occupationdetails" style="display: none;">
					
				</div>
			</div>
		<div class="col-md-4">
			<jsp:include page="/pages/induoccchoice/LeftOccupationWishList.jsp"></jsp:include>					
		</div>
	</div>	
</div>
</s:form>
<script type="text/javascript">
var form = document.manualSearchForm;
	var fav_question_count = 0;
	$(document).ready(function(){
		fnReloadWishList();
		//fnInitRollOver('occpopover','right');
		if($(window).width() < 720)
	     {
			 fnInitRollOver('occpopover','bottom');
	     }
	     else
	     {
	    	
	    	 fnInitRollOver('occpopover','right');
	    	 
	     }
		
		
	});
	
	 $('.btn_qt').click(function()
	{
		var answer = $(this).data('answer');
		var slNo = $(this).data('question');
		var hiddenAnswer= $('#hidden_answer_' + slNo).val();
		if(null == hiddenAnswer || hiddenAnswer == "" || hiddenAnswer == undefined || hiddenAnswer != answer)
			{
				$('#hidden_answer_' + slNo).val(answer);
				if (answer == 'YES') {
					$('#no_' + slNo).not(this).removeClass('btn_no');
					$('#yes_' + slNo).addClass('btn_yes');
				} else {
					$('#yes_' + slNo).not(this).removeClass('btn_yes');
					$('#no_' + slNo).addClass('btn_no');
				}
			}
		else
		{
			$('#hidden_answer_' + slNo).val("");
			if (hiddenAnswer == 'YES') {
				$('#yes_' + slNo).removeClass('btn_yes');
			} else {
				$('#no_' + slNo).removeClass('btn_no');
			}
		}			
	});
	 
	
	
$('.fa-star-o').click(function(){
	if(fav_question_count >= 3)
	{
		alert("Max 3 questions are allowed to be prefered");
		return;
	}
		
	$("#"+this.id).hide();
	$("#"+this.id+"_Selectd").show();
	++fav_question_count;
});

$('.fa-star').click(function(){
	$("#"+this.id).hide();
	$("#fav_question_"+$(this).data("question")).show();
	--fav_question_count;
});

$('.common_cls').click(function(){
	var id = $(this).data("question");
	var toggle = $('#toggle_'+id).val();;

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
	
	$("#answer_"+$(this).data("question")).slideToggle();
});

$('.selected_checkbox_subject').change(function(){
	var checkbox_count = 0;
	var isExceedMax = false;
	$('.selected_checkbox_subject').each(function(){
		if($("#"+this.id).is(":checked"))
			{
				++checkbox_count;
				if(checkbox_count > 2)
					{
						isExceedMax = true;
						return;
					}
			}
	});
	if(isExceedMax)
	{
		alert("Max 2 answer are allowed for subject");
		$("#"+this.id).attr("checked", false);
	}
	var value=$('.selected_checkbox_subject:checked').map(function () {
		  return this.value;
	}).get();
	$('#hidden_answer_' + $(this).data("question")).val(value);
	
});

$(".selected_checkbox_qualification").change(function(){
	var value=$('.selected_checkbox_qualification:checked').map(function () {
		  return this.value;
	}).get();
	$('#hidden_answer_' + $(this).data("question")).val(value);
});

$('.subCategory_check').change(function()
{
	if($("#"+this.id).is(":checked"))
	{
		$('.'+this.id).prop("checked", true);
		$('.'+this.id).prop("disabled", true);
	}
	else
	{
		$('.'+this.id).prop("checked", false);
		$('.'+this.id).prop("disabled", false);
	}
	
	var value=$('.area_check:checked').map(function () {
		  return this.value;
	}).get();
	$('#hidden_answer_' + $(this).data("question")).val(value);	
	
});

$('.area_check').change(function()
{
	var value=$('.area_check:checked').map(function () {
		  return this.value;
	}).get();
	$('#hidden_answer_' + $(this).data("question")).val(value);	
});

$('.chenge_select').change(function(){
	$('#hidden_answer_' + $(this).data("question")).val($('#'+this.id).val());
});

$('#occName').keyup(function(){
	$('#hidden_answer_' + $(this).data("question")).val($('#occName').val());
});

function fnSearch(){
		
	 var finalJsonArray = []; 
	 var JsonObject = new Object();
		if(fav_question_count > 0)
		{
			var isSelectedStarDataValid = true;
			$('.fa-star').each(function(){
				//$("#fav_question_"+$(this).data("question")).show();
				if( $("#"+this.id).is(':visible') )
				{
					var data = $('#hidden_answer_'+$(this).data("question")).val();
					if(null == data || data == '' || data == undefined)
					 {
						isSelectedStarDataValid = false;
					 	return;
					 }
					 var json = new Object();
					 if($(this).data("question") == '13')
					 {
						 json.slNo = $('#hidden_answer_13').val();
					 }
					 else{
					     json.slNo = $(this).data("question");
					 }
				     //json.answer = data;
					 finalJsonArray.push(json);
				}
			});
			if(!isSelectedStarDataValid)
			{
				alert("please select value for prefered question");
				return false;
			}
		}
		
	 JsonObject.preferedQuestion=finalJsonArray;
	 finalJsonArray = []; 
	 for(var count = 0; count <= '${questionNo}'; count++)
	 {
		 var answer = $('#hidden_answer_'+count).val();
		 if($( "input[type=checkbox][name=subCategory]" ).hasClass( 'subCategory_get_'+count))
		 {
			answer = "";
			$('.subCategory_get_'+count).each(function(){
				var subCategoryId = "";
				var areaId = "";
				//var isSubcategoryChecked = true;
				$('.'+this.id).each(function(){
					if(this.id != "")
						{
						if($("#"+this.id).is(":checked"))
							{
								if(areaId != "")
								{
									areaId = areaId + ",";
								}
								areaId = areaId +$(this).attr("data-areaId");
							}
						}
				});
				if(areaId != "" )
				{
					subCategoryId = $(this).attr("data-subCategoryId");
					if(answer != "")
					{
						answer = answer+"&&";
					}
					/* if(isSubcategoryChecked)
					{
						answer = answer + subCategoryId+":"+ -1;
					}
					else
					{ */
						answer = answer + subCategoryId+":"+ areaId;
					/* } */
				}
				
			});
		 }
		 if(null != answer && answer != '' && answer != undefined && answer != -1)
		 {
			 var json = new Object();
			 	if(count == 13)
			 	{
					 json.slNo = $('#hidden_answer_' + count).val();
					 json.answer = "YES";
			 	}	
			 	else{
				     json.slNo = count;
				     json.answer = answer;
			 	}
			 finalJsonArray.push(json);
		 }	 
	 }
	if(null == JSON.stringify(finalJsonArray) || finalJsonArray.length == 0)
	{
		alert("please select at least one value for search result");
		return false;
	}
	JsonObject.selectedAnswer=finalJsonArray;
	$("#selectedAnswer").val(JSON.stringify(JsonObject));
	 form.action = '${pageContext.request.contextPath}/myapp/getSearchResultManualsearchAction';
	 form.submit();
}
 function fnResetSearch(){
	 form.action = '${pageContext.request.contextPath}/myapp/ManualsearchAction';
	 form.submit();
 }
 
 function fnSetSalaryAnswer(obj)
 {
	var answer = $(obj).data('answer');
	var slNo = $(obj).data('question');
	$('#hidden_answer_' + slNo).val(answer);
	if (answer == 'YES') {
		$('#no_' + slNo).not(obj).removeClass('btn_no');
		$('#yes_' + slNo).addClass('btn_yes');
	} else {
		$('#yes_' + slNo).not(obj).removeClass('btn_yes');
		$('#no_' + slNo).addClass('btn_no');
	}
 }
</script>
<style type="text/css">
.common_cls{
	cursor: pointer;
}
</style>