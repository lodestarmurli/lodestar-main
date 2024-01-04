<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="commonutil.tld" prefix="utils"%>
<%@ taglib prefix="c" uri="c.tld"%>

<s:if test="pageNo == 0">
	<div class="page-action-bar">
		<div class="total-records-div">
			<s:text name="com.edupath.common.totalrecords" >
				<s:param><b>${totalSize}</b></s:param>
			</s:text>
		</div>
		<s:if test="totalSize > 0">
		<div class="college-action-div">
			<div class="compare-button-div">		<!-- added style27/03/18 -->
				<button class="btn" id='compare_button' style="margin-right:-9px;"><s:text name="com.edupath.common.compare.button"/></button>
			</div>
			<div class="short-button-div">			<!-- added style27/03/18 -->
				<button class="btn" id="shortlist_button" style="margin-right: 6px;"><s:text name="com.edupath.common.shortlist.button"/></button>
			</div>
		</div>
		</s:if>
	</div>
</s:if>
<div class="edupath-main" style="margin-left:6px;">  <!--added on 28/03/18 margin-left:6px  -->
<s:iterator value="collegeDetailsList" var="collegeDetails">
	<div class="edupath-card edupath-card-${pageNo}">
		<div class="edupath-card-header">
			<s:div class="edupath-card-title" data-college-id="%{#collegeDetails.collegeId}" onClick="fnShowDetails('%{#collegeDetails.collegeId}')">
				<s:property value="%{#collegeDetails.collegeName}"/>
			</s:div>
			<div class="edupath-card-checkbox">
				<div class="chk_box_custom checkbox checkbox-success">
		       		<s:textfield type="checkbox" class="college-check styled" name="collegeId_%{#collegeDetails.collegeId}" value="%{#collegeDetails.collegeId}"
		       		 data-college-name="%{#collegeDetails.collegeName}"/>
					<s:label for="collegeId_%{#collegeDetails.collegeId}"></s:label>
			    </div>
			</div>
		</div>
		<div class="edupath-card-row">
			<div class="edupath-card-col1">
				<s:text name="com.edupath.college.details.affiliationboard"/>
			</div>
			<div class="edupath-card-col2">
				<s:property value="%{#collegeDetails.affiliationToBoard}"/>
			</div>
		</div>
		<div class="edupath-card-row">
			<div class="edupath-card-col1">
				<s:text name="com.edupath.college.details.locaion"/>
			</div>
			<div class="edupath-card-col2">
				<s:property value="%{#collegeDetails.zone}"/>
			</div>
		</div>
		<div class="edupath-card-row">
			<div class="edupath-card-col1">
				<s:text name="com.edupath.college.details.ageofinstitute"/>
			</div>
			<div class="edupath-card-col2">
				<s:property value="%{#collegeDetails.ageOfTheInstitute}"/>
			</div>
		</div>
		<div class="edupath-card-row">
			<div class="edupath-card-col1">
				<s:text name="com.edupath.college.details.cutoff"/>
			</div>
			<div class="edupath-card-col2">
			
			<!-- START Sasedeve edited By Mrutyunjaya On Date 2-09-2017 -->
			
			
			<c:set var="LASTCUTOFVARB" value="NA"/>
			<c:if test="${collegeDetails.lastCutOff gt 0}">
			
			<c:set var="LASTCUTOFVARB" value="${collegeDetails.lastCutOff}"/>
			</c:if>
			${LASTCUTOFVARB} 
			
			<!-- END Sasedeve edited By Mrutyunjaya On Date 2-09-2017 -->
			
			</div>
		</div>
		<div class="edupath-card-row">
			<div class="edupath-card-col1">
				<s:text name="com.edupath.college.detail.collegefor"/>
			</div>
			<div class="edupath-card-col2">
				<s:property value="%{#collegeDetails.sexPref}"/>
			</div>
		</div>
		<div class="edupath-card-row">
			<div class="edupath-card-col1">
				<s:text name="com.edupath.college.details.streamsoffered"/>
			</div>
			<div class="edupath-card-col2">
				<s:if test="%{#collegeDetails.collegeStreamDetails != null}">
					<s:iterator value="%{#collegeDetails.collegeStreamDetails}" var="streamDetail" status="counter">
						<s:property value="%{#streamDetail.streamsOffered}"/><c:if test="${(counter.index + 1) lt collegeDetails.collegeStreamDetails.size()}">,</c:if>
					</s:iterator>
				</s:if>
			</div>
		</div>
		<div class="edupath-card-row">
			<div class="edupath-card-col1">
				<s:text name="com.edupath.college.details.electivecombinationsavailable"/>
			</div>
			<div class="edupath-card-col2">
				<s:if test="%{#collegeDetails.collegeCombinationDetails != null}">
					<s:iterator value="%{#collegeDetails.collegeCombinationDetails}" var="collegeCombination" status="counter">
						<s:property value="%{#collegeCombination.electiveCombAvailable}"/><c:if test="${counter.index + 1 lt collegeDetails.collegeCombinationDetails.size()}">,</c:if>
					</s:iterator>
				</s:if>
			</div>
		</div>
		<div class="edupath-card-row">
			<div class="edupath-card-col1">
				<s:text name="com.edupath.college.details.annualfee"/>
			</div>
			<div class="edupath-card-col2">
				<c:if test="${collegeDetails.collegeCombinationDetails != null}">
				<!-- START Sasedeve edited By Mrutyunjaya On Date 2-09-2017 -->
				<c:set var="minAnnualFee" value="0"/>
					<c:forEach  items="${collegeDetails.collegeCombinationDetails}" var="collegeCombination" varStatus="counter">
						<c:if test="${counter.index == 0 or minAnnualFee==0}">
							<c:set var="minAnnualFee" value="${collegeCombination.annualFee}"/>
						</c:if>
						<c:if test="${collegeCombination.annualFee gt 0 and collegeCombination.annualFee lt minAnnualFee}">
							<c:set var="minAnnualFee" value="${collegeCombination.annualFee}"/>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${minAnnualFee==0}">
							<c:set var="minAnnualFee" value="NA"/>
				</c:if>
				${minAnnualFee} 
				
				<!-- END Sasedeve edited By Mrutyunjaya On Date 2-09-2017 -->
				
			</div>
		</div>
		<div class="edupath-card-row">
			<div class="edupath-card-col1">
				<s:text name="com.edupath.college.details.integratedcoaching"/>
			</div>
			<div class="edupath-card-col2">
				${collegeDetails.integratedCoaching ? collegeDetails.coachingCenterName : 'NO'}
			</div>
		</div>
	</div>
</s:iterator>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var noOfRecords = '${not empty collegeDetailsList ? collegeDetailsList.size() : 0}';
		LodeCollege.setRecordsDetails(noOfRecords, '${totalSize}', '${pageNo}');
		if('${pageNo eq 0}' == 'true')
		{
			initClicks();
		}
		fnAdjustDivHeight('${pageNo}');
	});
	
	function initClicks()
	{
// 		$('.edupath-card-title').click(function(){
// 			CollegeShortListScript.submitToCompareScreen($(this).data("collegeId"), false);
// 		});
		
		$('#compare_button').click(function(){
			CollegeShortListScript.submitToCollegeCompareScreen(2, LodeCollege.getMaxCollegeCompare());
		});
		
		$('#shortlist_button').click(function(){
			shortListColleges();
		});
	}
	
	function fnShowDetails(collegeId)
	{
		CollegeShortListScript.submitToCompareScreen(collegeId, false);
	}
	
	function fnAdjustDivHeight(pageNo)
	{
		var noOfRecords = '${not empty collegeDetailsList ? collegeDetailsList.size() : 0}';
		var maxHeight = 0;
		var cardStack = [];
		$.each($('.edupath-card-'+pageNo), function(index){
			if(maxHeight < $(this).outerHeight())
			{
				maxHeight = $(this).outerHeight();
			}
			cardStack.push($(this));
			if((noOfRecords == (index + 1)) || (((index + 1) % 3) == 0))
			{
				for(var i = 0; i < cardStack.length; i++)
				{
					cardStack[i].css("height", maxHeight);
				}
				maxHeight = 0;
				cardStack = [];
			}
		});
	}
</script>