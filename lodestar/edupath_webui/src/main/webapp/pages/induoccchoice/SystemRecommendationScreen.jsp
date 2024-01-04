<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>   

<s:hidden name="studentId" id="studentId" />
<s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
<s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 

<div class="edupath-padding">
	<div class="row">
		<div class="col-md-8">	
			<div id="contentdiv">
				<div class="pagetitle">
					<s:text name="com.edupath.system.recommendation.title"></s:text>
				</div>
				<div>
					<div class="sectionheading">
						<s:text name="com.edupath.system.recommendation.guidance.heading"/>
						 <!-- Start SASEDEVE edited by Mrutyunjaya on date 14-07-2017 --> 
						 <br/> <br/>
									<span class="fa fa-circle-o fa-1x" style="color:black;"></span>
									<span class="sectionheading">Abilities fitment</span>
									<span class="fa fa-square-o fa-1x" style="color:black;"></span>  <span class="sectionheading">Interest fitment</span> 
									<br/><br/>
									<span class="fa fa-tag fa-lg" style="color:green"></span><span class="sectionheading">&nbsp;&nbsp;High</span>
									<span class="fa fa-tag fa-lg" style="color:yellow"></span><span class="sectionheading">&nbsp;&nbsp;Above Average</span>
									<span class="fa fa-tag fa-lg" style="color:orange"></span><span class="sectionheading">&nbsp;&nbsp;Average</span>
									<span class="fa fa-tag fa-lg" style="color:red"></span><span class="sectionheading">&nbsp;&nbsp;Low</span>
									<br/><br/>
									<!-- END SASEDEVE edited by Mrutyunjaya on date 14-07-2017 -->
						
						
					</div>
					<div class="traitcode">
						<s:text name="com.edupath.system.recommendation.personality"/> ${systemRecommendation.PESONALITY_CODE} 
						<span class="traitnote">
							<c:if test="${systemRecommendation.DISCLAIMER}">
								<s:text name="com.edupath.system.recommendation.personality.disclaimer"/>
							</c:if>
						</span>
									
									
					</div>
					<div class="traitcode1">
						<s:text name="com.edupath.system.recommendation.ability"></s:text> ${systemRecommendation.APP_SCORE}
					</div>
					<div class="row" style="display: inline;">
						<div class="col-md-12">
							<c:forEach items="${systemRecommendation.CGT_MAP}" var="occupationEntry">
								<div class="col-md-4">
									<div class="industryTitle">
										 ${utils:replaceXMLEntities(occupationEntry.key)}
									</div>
									<div class="row">
										<div class="col-md-12">
											<c:forEach items="${occupationEntry.value}" var="occupation">
												<div class="checkbox checkbox-primary img-rounded occupationnamediv" style="margin-right: 5px;">
													<span class="occupationname">
														<c:set var="rollOverContent">
															<b><s:text name="com.edupath.system.recommendation.requiredcode"/></b>:  ${occupation.requiredRAISEC}
															</br>
															<b><s:text name="com.edupath.system.recommendation.requiredability"/></b>: ${occupation.requiredAbility}
															</br></br>
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
															<c:if test="${not empty systemRecommendation.alerts[occupation.longId]}">
																<c:set value="${systemRecommendation.alerts[occupation.longId]}" var="alertList"/>
																<b><s:text name="com.edupath.occupation.wish.alert"></s:text></b>: <br> 
																<c:forEach items="${alertList}" var="alert" varStatus="slno">
																	 &nbsp;${alert.name}:&nbsp;<b>${alert.alertValue}</b> &nbsp; |
																</c:forEach>
																<br><br>
																<b><s:text name="com.edupath.occupation.wish.occupation"></s:text></b>:<br>
															</c:if>
															${occupation.rollOverContent}
														</c:set>
													
														<input type="checkbox" id="occ_c_${occupation.id}" data-indsize="${fn:length(occupation.industries)}" data-indusid="${industryId}">
														<label for="occ_c_${occupation.id}" data-toggle="occpopover" data-content="${utils:replaceXMLEntities(rollOverContent)}" id="${occupation.id}">
															<c:if test="${not empty systemRecommendation.alerts[occupation.longId]}">
																<span class="fa fa-exclamation" style="color:orange"></span>
															</c:if>
															
															
															
															<i class="fa fa-circle fa-fw ${fitmentcolors[occupation.fitment]}" aria-hidden="true"></i>
															
															
															<!-- Start Sasedeve Edited By Mrutyunjaya on Date 14-07-2017 -->
															<i class="fa fa-square fa-fw ${fitmentcolors[occupation.newfitment]}" aria-hidden="true"></i>
															
															<!-- END Sasedeve Edited By Mrutyunjaya on Date 14-07-2017 -->
															
															
															 ${utils:replaceXMLEntities(occupation.name)}
														</label>
													</span>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>					
							</c:forEach>
						</div>
					</div>
				</div>
				<div>
					<div class="sectionheading">
						<s:text name="com.edupath.system.recommendation.tum"></s:text>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-4">
								<div class="industryTitle">
									<s:text name="com.edupath.system.recommendation.interests"></s:text>
								</div>
								<div>
									<c:forEach items="${systemRecommendation.INTERESTS}" var="occupation">
										<div class="checkbox checkbox-primary img-rounded occupationnamediv" >
											<span class="occupationname">
												<c:set var="rollOverContent">
													<b><s:text name="com.edupath.system.recommendation.requiredcode"/></b>:  ${occupation.requiredRAISEC}
													</br>
													<b><s:text name="com.edupath.system.recommendation.requiredability"/></b>: ${occupation.requiredAbility}
													</br></br>
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
													<c:if test="${not empty systemRecommendation.alerts[occupation.longId]}">
														<c:set value="${systemRecommendation.alerts[occupation.longId]}" var="alertList"/>
														<b><s:text name="com.edupath.occupation.wish.alert"></s:text></b>: <br> 
														<c:forEach items="${alertList}" var="alert" varStatus="slno">
															&nbsp;${alert.name}:&nbsp;<b>${alert.alertValue}</b> &nbsp; |
														</c:forEach>
														<br><br>
														<b><s:text name="com.edupath.occupation.wish.occupation"></s:text></b>:<br>
													</c:if>
													${occupation.rollOverContent}
												</c:set>
											
												<input type="checkbox" id="occ_i_${occupation.id}" data-indsize="${fn:length(occupation.industries)}" data-indusid="${industryId}"> 
												<label for="occ_i_${occupation.id}" data-toggle="occpopover" data-content="${utils:replaceXMLEntities(rollOverContent)}" id="${occupation.id}">
													<c:if test="${not empty systemRecommendation.alerts[occupation.longId]}">
														<span class="fa fa-exclamation" style="color:orange"></span>
													</c:if>
													<i class="fa fa-circle fa-fw ${fitmentcolors[occupation.fitment]}" aria-hidden="true"></i>
													<!-- BEGIN Sasedeve Added by Mrutyunjaya on Date 24-02-2017 -->
													
												 	<i class="fa fa-square fa-fw ${fitmentcolors[occupation.newfitment]}" aria-hidden="true"></i> 
													
													<!-- END Sasedeve Added by Mrutyunjaya on Date 24-02-2017 -->
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
							</div>
							<div class="col-md-4">
								<div class="industryTitle">
									<s:text name="com.edupath.system.recommendation.strengths"></s:text>
								</div>
								<div>
									<c:forEach items="${systemRecommendation.STRENGTHS}" var="occupation">
										<div class="checkbox checkbox-primary img-rounded occupationnamediv" >
											<span class="occupationname">
												<c:set var="rollOverContent">
													<b><s:text name="com.edupath.system.recommendation.requiredcode"/></b>:  ${occupation.requiredRAISEC}
													</br>
													<b><s:text name="com.edupath.system.recommendation.requiredability"/></b>: ${occupation.requiredAbility}
													</br></br>
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
													<c:if test="${not empty systemRecommendation.alerts[occupation.longId]}">
														<c:set value="${systemRecommendation.alerts[occupation.longId]}" var="alertList"/>
														<b><s:text name="com.edupath.occupation.wish.alert"></s:text></b>: <br> 
														<c:forEach items="${alertList}" var="alert" varStatus="slno">
															&nbsp;${alert.name}:&nbsp;<b>${alert.alertValue}</b> &nbsp; |
														</c:forEach>
														<br><br>
														<b><s:text name="com.edupath.occupation.wish.occupation"></s:text></b>:<br>
													</c:if>
													${occupation.rollOverContent}
												</c:set>
											
												<input type="checkbox" id="occ_s_${occupation.id}" data-indsize="${fn:length(occupation.industries)}" data-indusid="${industryId}"> 
												<label for="occ_s_${occupation.id}" data-toggle="occpopover" data-content="${utils:replaceXMLEntities(rollOverContent)}" id="${occupation.id}">
													<c:if test="${not empty systemRecommendation.alerts[occupation.longId]}">
														<span class="fa fa-exclamation" style="color:orange"></span>
													</c:if>
													<i class="fa fa-circle fa-fw ${fitmentcolors[occupation.fitment]}" aria-hidden="true"></i>
													<!-- BEGIN Sasedeve Added by Mrutyunjaya on Date 24-02-2017 -->
													
													<i class="fa fa-square fa-fw ${fitmentcolors[occupation.newfitment]}" aria-hidden="true"></i> 
													
													<!-- END Sasedeve Added by Mrutyunjaya on Date 24-02-2017 -->
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
							</div>
							<div class="col-md-4">
								<div class="industryTitle">
									<s:text name="com.edupath.system.recommendation.aspirations"></s:text>
								</div>
								<div>
									<c:forEach items="${systemRecommendation.ASPIRATIONS}" var="occupation">
										<div class="checkbox checkbox-primary img-rounded occupationnamediv" >
											<span class="occupationname">
												<c:set var="rollOverContent">
													<b><s:text name="com.edupath.system.recommendation.requiredcode"/></b>:  ${occupation.requiredRAISEC}
													</br>
													<b><s:text name="com.edupath.system.recommendation.requiredability"/></b>: ${occupation.requiredAbility}
													</br></br>
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
													<c:if test="${not empty systemRecommendation.alerts[occupation.longId]}">
														<c:set value="${systemRecommendation.alerts[occupation.longId]}" var="alertList"/>
														<b><s:text name="com.edupath.occupation.wish.alert"></s:text></b>: <br> 
														<c:forEach items="${alertList}" var="alert" varStatus="slno">
															&nbsp;${alert.name}:&nbsp;<b>${alert.alertValue}</b> &nbsp; |
														</c:forEach>
														<br><br>
														<b><s:text name="com.edupath.occupation.wish.occupation"></s:text></b>:<br>
													</c:if>
													${occupation.rollOverContent}
												</c:set>
											
												<input type="checkbox" id="occ_a_${occupation.id}" data-indsize="${fn:length(occupation.industries)}" data-indusid="${industryId}"> 
												<label for="occ_a_${occupation.id}" data-toggle="occpopover" data-content="${utils:replaceXMLEntities(rollOverContent)}" id="${occupation.id}">
													<c:if test="${not empty systemRecommendation.alerts[occupation.longId]}">
														<span class="fa fa-exclamation" style="color:orange"></span>
													</c:if>
													<i class="fa fa-circle fa-fw ${fitmentcolors[occupation.fitment]}" aria-hidden="true"></i>
													<!-- BEGIN Sasedeve Added by Mrutyunjaya on Date 24-02-2017 -->
													
													 <i class="fa fa-square fa-fw ${fitmentcolors[occupation.newfitment]}" aria-hidden="true"></i> 
													
													<!-- END Sasedeve Added by Mrutyunjaya on Date 24-02-2017 -->
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
							</div>	
						</div>
					</div>	
					<br>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-4">
								<div class="industryTitle">
									<s:text name="com.edupath.system.recommendation.subject"></s:text>
								</div>
								<div>
									<c:forEach items="${systemRecommendation.SUBJECTS}" var="occupation">
										<div class="checkbox checkbox-primary img-rounded occupationnamediv" >
											<span class="occupationname">
												<c:set var="rollOverContent">
													<b><s:text name="com.edupath.system.recommendation.requiredcode"/></b>:  ${occupation.requiredRAISEC}
													</br>
													<b><s:text name="com.edupath.system.recommendation.requiredability"/></b>: ${occupation.requiredAbility}
													</br></br>
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
													<c:if test="${not empty systemRecommendation.alerts[occupation.longId]}">
														<c:set value="${systemRecommendation.alerts[occupation.longId]}" var="alertList"/>
														<b><s:text name="com.edupath.occupation.wish.alert"></s:text></b>: <br> 
														<c:forEach items="${alertList}" var="alert" varStatus="slno">
															<c:if test="${slno.index gt 0}">
																&nbsp; |
															</c:if>
															&nbsp;${alert.name}:&nbsp;<b>${alert.alertValue}</b>
														</c:forEach>
														<br><br>
														<b><s:text name="com.edupath.occupation.wish.occupation"></s:text></b>:<br>
													</c:if>
													${occupation.rollOverContent}
												</c:set>
											
												<input type="checkbox" id="occ_p_${occupation.id}" data-indsize="${fn:length(occupation.industries)}" data-indusid="${industryId}"> 
												<label for="occ_p_${occupation.id}" data-toggle="occpopover" data-content="${utils:replaceXMLEntities(rollOverContent)}" id="${occupation.id}">
													<c:if test="${not empty systemRecommendation.alerts[occupation.longId]}">
														<span class="fa fa-exclamation" style="color:orange"></span>
													</c:if>
													<i class="fa fa-circle fa-fw ${fitmentcolors[occupation.fitment]}" aria-hidden="true"></i>
													<!-- BEGIN Sasedeve Added by Mrutyunjaya on Date 24-02-2017 -->
													
													 <i class="fa fa-square fa-fw ${fitmentcolors[occupation.newfitment]}" aria-hidden="true"></i> 
													
													<!-- END Sasedeve Added by Mrutyunjaya on Date 24-02-2017 -->
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
							</div>
						</div>
					</div>	
				</div>
			</div>
			<div id="occupationdetails" style="display: none;">
				
			</div>
		</div>	
		<div class="col-md-4">
			<jsp:include page="/pages/induoccchoice/LeftOccupationWishList.jsp"></jsp:include>
		</div>
	</div>	
</div>

<script type="text/javascript">
	
	$(document).ready(function(){
		fnReloadWishList();
		
		if($(window).width() < 720)
	     {
			 fnInitRollOver('occpopover','bottom');
	     }
	     else
	     {
	    	
	    	 fnInitRollOver('occpopover','right');
	    	 
	     }
	});
	
	$('input[type="checkbox"]').change(function() {
		if(this.checked)
		{
			fnAddWishList($(this).attr("id"), $(this).data("indusid"), $(this).data("indsize"));
		}	
	});
	
	function fnUnCheckRemoved()
	{
		
	}
</script>