<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>   

<s:form name="occupationGlossary" id="occupationGlossary" method="post">
	<s:hidden name="studentId" id="studentId" />
	<s:hidden name="pageNumber" id = "pageNumber"/>
    <s:hidden name="parentSelectedSidebarMenuId" id="parentSelectedSidebarMenuId"/> 
    <s:hidden name="childSelectedSidebarMenuId" id="childSelectedSidebarMenuId"/> 
</s:form>

<div class="edupath-padding">
	<div class="row">
		<div class="col-md-8">
			<div id="contentdiv">
				<div class="row" style="display: inline;">
					<div class="col-md-12">
						<div class="pagetitle">
							Page&nbsp;<span id="pageIndex"></span>&nbsp;:&nbsp;Occupation glossary (showing&nbsp;<span id="showNum">
							<s:text	name="com.edupath.occupation.glossary.page.count"></s:text></span>&nbsp;of&nbsp;<span id="totNum">${occupCount}</span>)
							
							 <!-- Start SASEDEVE edited by Mrutyunjaya on date 14-07-2017 -->  
									<br/>
		                            <span class="fa fa-circle-o fa-1x" style="color:black;"></span>
								    <span class="sectionheading">&nbsp;&nbsp;Abilities fitment</span>&nbsp;&nbsp;
									<span class="fa fa-square-o fa-1x" style="color:black;"></span><span class="sectionheading">&nbsp;&nbsp;Interest fitment</span>
									<br/>
									<span class="fa fa-tag fa-lg" style="color:green"></span><span class="sectionheading">&nbsp;&nbsp;High</span>
									<span class="fa fa-tag fa-lg" style="color:yellow"></span><span class="sectionheading">&nbsp;&nbsp;Above Average</span>
									<span class="fa fa-tag fa-lg" style="color:orange"></span><span class="sectionheading">&nbsp;&nbsp;Average</span>
									<span class="fa fa-tag fa-lg" style="color:red"></span><span class="sectionheading">&nbsp;&nbsp;Low</span>
									<br/><br/>
									<!-- END SASEDEVE edited by Mrutyunjaya on date 14-07-2017 -->  
							
							
						</div>
					</div>	
				</div>			
				<div class="row top-buffer" style="min-height: 350px">
					<div class="col-md-12">
						<c:forEach items="${occupGlossMap}" var="industry">
							<div class="col-md-4">
								<div class="industryTitle">
									 ${utils:replaceXMLEntities(industry.key)}
								</div>
								<c:forEach items="${industry.value}" var="pathWay">
									<div class="pathWayTitle">
										${utils:replaceXMLEntities(pathWay.key)}
									</div>
									<div>
										<c:forEach items="${pathWay.value}" var="occupation">
											<div class="checkbox checkbox-primary img-rounded occupationnamediv">
												<span class="occupationname">
													<c:set var="rollOverContent">
													
													<!-- Start Sasedeve edited by Mrutyunjaya on date 14-07-2017 -->
					 <b><s:text name="com.edupath.system.recommendation.requiredcode"/></b>:  ${occupation.requiredRAISEC}
					</br>
					 <b><s:text name="com.edupath.system.recommendation.requiredability"/></b>: ${occupation.requiredAbility}
					</br></br>
													
					<!-- END Sasedeve edited by Mrutyunjaya on date 14-07-2017 -->
													
													
													
													
														<c:if test="${not empty alerts[occupation.longId]}">
															<c:set value="${alerts[occupation.longId]}" var="alertList"/>
															<b><s:text name="com.edupath.occupation.wish.alert" /></b>: <br> 
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
												
													<input type="checkbox" id="occ_c_${occupation.id}" data-indsize="1" data-indusid="${occupation.industryId}">
													<label for="occ_c_${occupation.id}" data-toggle="occpopover" data-content="${utils:replaceXMLEntities(rollOverContent)}" id="${occupation.id}">
														<c:if test="${not empty alerts[occupation.longId]}">
															<span class="fa fa-exclamation" style="color:orange"></span>
														</c:if>
														
														<!-- BEGIN Sasedeve Added by Mrutyunjaya on Date 04-05-2017 -->
																<i class="fa fa-circle fa-fw ${fitmentcolors[occupation.fitment]}" aria-hidden="true"></i>
													
													
												 	<i class="fa fa-square fa-fw ${fitmentcolors[occupation.newfitment]}" aria-hidden="true"></i> 
													
													<!-- END Sasedeve Added by Mrutyunjaya on Date 04-05-2017 -->
														
														
														
														${utils:replaceXMLEntities(occupation.name)}
													</label>
												</span>	
											</div>
										</c:forEach>
									</div>
								</c:forEach>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="row">
			      <div class="row action-div">
		     		<button class="btn btn_action btn-back" onclick="fnBackSubmit();" id="back"><span class="back-spn"><s:text name="com.edupath.common.label.back"></s:text></span></button>
		     		<button class="btn btn_action" onclick="fnNextSubmit();" id="next"><span class="next-spn"><s:text name="com.edupath.common.next"></s:text></span></button>
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
	
	var pageNumber = parseInt($("#pageNumber").val());
	$("#pageIndex").html(pageNumber+1);
	
	
	if (pageNumber == 0) {
		$("#back").hide();
	}else {
		$("#back").show();
	}
	var pageCount = parseInt($("#showNum").html());
	var count = ${occupCount} - (pageNumber*pageCount+pageCount);
	if (count < 1) {
		$("#showNum").html(count + pageCount);
		$("#next").hide();
	}
	
	var form = document.occupationGlossary;
	
	function fnNextSubmit()
	{
		$("#pageNumber").val(pageNumber+1);
		form.action = "${pageContext.request.contextPath}/myapp/OccupationglossaryAction";
		form.submit();	
	}
	
	function fnBackSubmit()
	{
		$("#pageNumber").val(pageNumber-1);
		form.action = "${pageContext.request.contextPath}/myapp/OccupationglossaryAction";
		form.submit();	
	}
</script>