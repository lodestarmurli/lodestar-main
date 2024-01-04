<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="c.tld"%>

<div class="row">
	<div class="col-md-12 col-xs-12 college-search-header filter-margin-top">
		<s:if test="!isCompareAction">
			<s:text name="com.edupath.college.collegedetails.header"/>
		</s:if>
		<s:else>
			<s:text name="com.edupath.college.collegecompare.header"/>
		</s:else> 
	</div>
</div>

<div class="college-action-div">
	<div class="compare-button-div">
		<button class="btn" id='back_button'><s:text name="com.edupath.common.label.back"/></button>
	</div>
	<div class="short-button-div">
		<button class="btn" id="shortlist_button"><s:text name="com.edupath.common.shortlist.button"/></button>
	</div>
</div>

<div class="college-compare">
	<s:iterator value="collegeDetailsMap['BASIC_DETAILS']" var="collegeDetails" status="counter">
		<s:if test="%{#collegeDetails.key eq 'collegeId'}">
			<s:set name="collegeId" value="%{#collegeDetails.value}"/>
		</s:if>
		<s:else>
			<div class="college-compare-row">
				<div class="college-compar-attr ">
					<s:text name="%{#collegeDetails.key}"/>
				</div>
				<s:if test="%{#collegeDetails.value.size() > 0}">
					<s:iterator value="%{#collegeDetails.value}" var="collegeDetailsValue" status="collegeCounter">
						<s:if test="%{#counter.index == 1}">
							<div class="college-compar-value">
								<div class="college-compar-value-header">
									<s:property value="%{#collegeDetailsValue}"/>
								</div>
								<div class="shortlist-card-checkbox">
									<div class="checkbox checkbox-success">
							       		<s:textfield type="checkbox" class="college-check styled" id="collegeId_%{#collegeId.get(#collegeCounter.index).id}" name="collegeId_%{#collegeId.get(#collegeCounter.index).id}" 
							       			value="%{#collegeId.get(#collegeCounter.index).id}" data-college-name="%{#collegeId.get(#collegeCounter.index).collegeName}"/>
										<s:label for="collegeId_%{#collegeId.get(#collegeCounter.index).id}"/>
								    </div>
								</div>
							</div>
						</s:if>
						<s:else>
							<div class="college-compar-value">
							
							<!-- START Sasedeve edited By Mrutyunjaya On Date 2-09-2017 -->
							
							<!-- For cut off -->
							
							<c:set var="Varbfordetails" value="${collegeDetailsValue}"/>
							<c:if test="${collegeDetails.key == 'com.edupath.college.compare.cutoffforselectedstream.label'}">
							   
							 <c:set var="Varbfordetails" value="NA"/>
							 
							 <c:if test="${collegeDetailsValue gt 0}">
							  <c:set var="Varbfordetails" value="${collegeDetailsValue}"/>
							 </c:if>
							
							</c:if>
							
							
							<!-- For Seats for selected stream -->
							
							
							<c:if test="${collegeDetails.key == 'com.edupath.college.compare.seatsforselectedstream.label'}">
							   
							 <c:set var="Varbfordetails" value="NA"/>
							 
							 <c:if test="${collegeDetailsValue gt 0}">
							  <c:set var="Varbfordetails" value="${collegeDetailsValue}"/>
							 </c:if>
							
							</c:if>
							
							<!-- Fee for selected stream (Rs./annum) -->
							
							
							<c:if test="${collegeDetails.key == 'com.edupath.college.compare.streamfee.label'}">
							   
							 <c:set var="Varbfordetails" value="NA"/>
							 
							 <c:if test="${collegeDetailsValue gt 0}">
							  <c:set var="Varbfordetails" value="${collegeDetailsValue}"/>
							 </c:if>
							
							</c:if>
							
							<!-- Teaching Staff for selected stream -->
							
							
							<c:if test="${collegeDetails.key == 'com.edupath.college.compare.staff.label'}">
							   
							 <c:set var="Varbfordetails" value="NA"/>
							 
							 <c:if test="${collegeDetailsValue gt 0}">
							  <c:set var="Varbfordetails" value="${collegeDetailsValue}"/>
							 </c:if>
							
							</c:if>
							
							<!-- Application last date -->
							
						
							<c:if test="${collegeDetails.key == 'com.edupath.college.compare.applicationlastdate.label'}">
							   
							 <c:set var="Varbfordetails" value="NA"/>
							 
							 <c:if test="${collegeDetailsValue ne null and collegeDetailsValue ne '0.0'}">
							  <c:set var="Varbfordetails" value="${collegeDetailsValue}"/>
							 </c:if>
							
							</c:if>
							
							
							${Varbfordetails}
							
							<!-- END Sasedeve edited By Mrutyunjaya On Date 2-09-2017 -->
							
							
							</div>
						</s:else>
					</s:iterator>
				</s:if>
			</div>
		</s:else>		
	</s:iterator>
<s:if test="!isCompareAction">
	<s:text name="</div><div class='college-compare-extra'>"></s:text>
</s:if>
	<s:iterator value="collegeDetailsMap['ACTIVITY_DETAILS']" var="collegeDetails">
		<div class="college-compare-row">
			<div class="college-compar-attr college-compar-attr1">
				<s:property value="%{#collegeDetails.key}"/>
			</div>
			<s:iterator value="%{#collegeDetails.value}" var="collegeDetailsValue">
				<div class="college-compar-value">
					<s:text name="%{#collegeDetailsValue}"/>
				</div>
			</s:iterator>
		</div>		
	</s:iterator>
	<s:iterator value="collegeDetailsMap['INFRA_DETAILS']" var="collegeDetails">
		<div class="college-compare-row college-compare-row1">
			<div class="college-compar-attr college-compar-attr1">
				<s:set name="isCapacityInfra" value="false"/>
				<s:iterator value="%{#collegeDetails.value}" var="collegeDetailsValue">
					<c:if test="${(not empty collegeDetailsValue.capacity) and (isCapacityInfra ne true)}">
						<s:set name="isCapacityInfra" value="true"/>
					</c:if>
				</s:iterator>
				<s:if test="%{#isCapacityInfra eq false}">
					<s:property value="%{#collegeDetails.key}"/>
				</s:if>
				<s:else>
					<s:text name="com.edupath.college.compare.offcountkey.label">
						<s:param value="%{#collegeDetails.key}"/>
					</s:text>
				</s:else>				
			</div>
			<s:iterator value="%{#collegeDetails.value}" var="collegeDetailsValue">
				<div class="college-compar-value">
					<s:if test="%{(#isCapacityInfra eq true) && (#collegeDetailsValue.capacity eq null)}">
						-
					</s:if>
					<s:elseif test="%{#isCapacityInfra}">
						<s:property value="%{#collegeDetailsValue.capacity}"/>
					</s:elseif>
					<s:else>
<%-- 						<s:text name="com.edupath.college.compare.offcountvalue.label"> --%>
<%-- 							<s:param value="%{#collegeDetailsValue.count}"/> --%>
							<s:text name="%{#collegeDetailsValue.infraExist}"/>
<%-- 						</s:text> --%>
					</s:else>
				</div>
			</s:iterator>
		</div>		
	</s:iterator>
</div>
<form action="${pageContext.request.contextPath}/myapp/getFilterCollegeSelectionAction" name="BackButtonAction" id="BackButtonAction" method="POST">
	<s:hidden name="parentSelectedSidebarMenuId"/>
	<s:hidden name="childSelectedSidebarMenuId"/>
	<s:hidden name="isBackAction" value="true"/>
</form>
<jsp:include page="/pages/finaliseelectives/CollegeShortListModalScreen.jsp">
	<jsp:param value="${parentSelectedSidebarMenuId}" name="parentSelectedSidebarMenuId"/>
	<jsp:param value="${childSelectedSidebarMenuId}" name="childSelectedSidebarMenuId"/>
	<jsp:param value="${isCompareAction}" name="isCompareAction"/>
</jsp:include>

<script>
$(document).ready(function(){
	$('#back_button').click(function(){
		$('#BackButtonAction').submit();
	});
	
	$('#shortlist_button').click(function(){
		shortListColleges();
	});	
});
</script>