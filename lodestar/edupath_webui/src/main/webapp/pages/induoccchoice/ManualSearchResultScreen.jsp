
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

				<div class="pagetitle">
					<s:text name="com.edupath.manualsearch.header.manualsearchresult"></s:text>
				</div>
					
					<div>
						<div class="topicheading">
							<s:text name="com.edupath.manualsearchresult.lable.occupation"></s:text>
							<div class="pull-right">
						     	<%-- <button class="btn btn_action" onclick="return fnResetSearch();"><span class="next-spn"><s:text name="com.edupath.common.resetsearch"></s:text></span></button> --%>
						   		<a class="readmore" href="#" onclick="return fnResetSearch();" style="text-decoration: underline;"><span class="next-spn"><s:text name="com.edupath.common.resetsearch"></s:text></span></a>
						   	</div>
						   	 <!-- Start SASEDEVE edited by Mrutyunjaya on date 14-07-2017 -->  
									 <br/> <br/>
		                            <span class="fa fa-circle-o fa-1x" style="color:black;"></span>
								    <span class="sectionheading">&nbsp;&nbsp;Abilities fitment</span>&nbsp;&nbsp;
									<span class="fa fa-square-o fa-1x" style="color:black;"></span><span class="sectionheading">&nbsp;&nbsp;Interest fitment</span>
									<br/><br/>
									<span class="fa fa-tag fa-lg" style="color:green"></span><span class="sectionheading">&nbsp;&nbsp;High</span>
									<span class="fa fa-tag fa-lg" style="color:yellow"></span><span class="sectionheading">&nbsp;&nbsp;Above Average</span>
									<span class="fa fa-tag fa-lg" style="color:orange"></span><span class="sectionheading">&nbsp;&nbsp;Average</span>
									<span class="fa fa-tag fa-lg" style="color:red"></span><span class="sectionheading">&nbsp;&nbsp;Low</span>
									<br/>
									<!-- END SASEDEVE edited by Mrutyunjaya on date 14-07-2017 -->  
						   	
						   	
						</div>
						<div>
							<div class="questiondiv">
								<span class="questionspan">${preferedQuestions}</span>
								
							</div>
							<div class="row top-buffer" style="min-height: 350px">
								<div class="col-md-12">
										<c:forEach items="${manualSearchMap}" var="manualanswers">
											<div class="col-md-4">
												<div class="industryTitle">
													${utils:replaceXMLEntities(manualanswers.key)}
												</div>
												<c:forEach items="${manualanswers.value}" var="occupation">
													<div class="checkbox checkbox-primary img-rounded occupationnamediv">
														<span class="occupationname">
															<c:set var="rollOverContent">
															
															<!-- Start Sasedeve edited by Mrutyunjaya on date 14-07-2017 -->
					 <b><s:text name="com.edupath.system.recommendation.requiredcode"/></b>:  ${occupation.requiredRAISEC}
					</br>
					 <b><s:text name="com.edupath.system.recommendation.requiredability"/></b>: ${occupation.requiredAbility}
					</br></br>
													
					<!-- END Sasedeve edited by Mrutyunjaya on date 14-07-2017 -->
															
																<c:if test="${fn:length(occupation.industries) gt 1}">
																	<b><s:text name="com.edupath.occupation.wish.industry"></s:text></b>:  
																</c:if>
																<c:forEach items="${occupation.industries}" var="industry" varStatus="slno">
																	<c:choose>
																		<c:when test="${industry.isPrimary}">
																			<c:set var="industryName">
																				${industry.name}
																			</c:set>
																			<c:set var="industryId">
																				${industry.id}
																			</c:set>
																		</c:when>
																		<c:otherwise>
																			<c:if test="${slno.index gt 1}">
																				&nbsp;,
																			</c:if>
																			${industry.name}
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
																<c:if test="${fn:length(occupation.industries) gt 1}">
																	<br><br>
																</c:if>
																<c:if test="${not empty alerts[occupation.longId]}">
																	<c:set value="${alerts[occupation.longId]}" var="alertList"/>
																	<b><s:text name="com.edupath.occupation.wish.alert"/></b>: <br> 
																	<c:forEach items="${alertList}" var="alert" varStatus="slno">
																		 <c:if test="${slno.index gt 1}">
																			&nbsp;|
																		 </c:if>
																		 &nbsp;${alert.name}:<b>${alert.alertValue}</b>
																	</c:forEach>
																	<br><br>
																	<b><s:text name="com.edupath.occupation.wish.occupation"></s:text></b>:<br>
																</c:if>
																${occupation.rollOverContent}
															</c:set>
														
															<input type="checkbox" id="occ_c_${occupation.id}" class="occupation_chackbox" data-indsize="${fn:length(occupation.industries)}" data-indusid="${industryId}">
															<label for="occ_c_${occupation.id}" data-toggle="occpopover" data-content="${utils:replaceXMLEntities(rollOverContent)}" id="${occupation.id}">
																<c:if test="${not empty alerts[occupation.longId]}">
																	<span class="fa fa-exclamation" style="color:orange"></span>
																</c:if>
																
																<!-- BEGIN Sasedeve Added by Mrutyunjaya on Date 03-05-2017 -->
																<i class="fa fa-circle fa-fw ${fitmentcolors[occupation.fitment]}" aria-hidden="true"></i>
													
													
												 	<i class="fa fa-square fa-fw ${fitmentcolors[occupation.newfitment]}" aria-hidden="true"></i> 
													
													<!-- END Sasedeve Added by Mrutyunjaya on Date 03-05-2017 -->
																
																
																<span>
																 	${utils:replaceXMLEntities(occupation.name)} 
																</span>
																<span class="industrybracketname"> 
																 	(${utils:replaceXMLEntities(industryName)})
																</span> 
															</label>
														</span>	
													</div>
												</c:forEach>
											</div>
										</c:forEach>
								</div>
							</div>
						</div>
					</div>
					</div>
<script>

$('input[type="checkbox"]').change(function() {
	if (this.checked) {
		fnAddWishList($(this).attr("id"), $(this).data("indusid"), $(this).data("indsize"));
	} 
});
</script>