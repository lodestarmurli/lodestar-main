<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib prefix="c" uri="c.tld"%>

<div>
	<div class="tut-total-records-div">
		<s:text name="com.edupath.tutorialcenters.limitrecord" >
			<s:param><b>${tutorialCentreSelectCount}</b></s:param>
		</s:text>	
	</div>
	<div class="tut-procceed-button-div">
		<button class="btn" onclick="fnGetTutorials()"><s:text name="com.edupath.common.label.back"/></button>
	</div>
	<div class="tut-procceed-button-div">
		<button class="btn" onclick="fnShortListCenterConfirm()"><s:text name="com.edupath.tutorialcenters.shortlist"/></button>
	</div>
</div>
 <div class="edupath-main">
	<c:forEach items="${tutorialsDetails}" var="tutorialDetail">
			<div class="edupath-card">
				<div class="edupath-card-header">
					<div class="edupath-card-title edupath-card-title-cursernone">
						<span class="tut-cent-name-span">${tutorialDetail.name}</span>
						<span>${tutorialDetail.cityName}&nbsp;(&nbsp;${tutorialDetail.locality}&nbsp;)</span>
					</div>
					<div class="edupath-card-checkbox">
						<div class="col-md-2 chk_box_custom checkbox  checkbox-success" id="${tutorialDetail.tutorialId}_${tutorialDetail.tutorialCenterId}">
							<input type="hidden" id="center_tutorialId_${tutorialDetail.tutorialCenterId}" value="${tutorialDetail.tutorialId}">
				       		<input type="checkbox" class="styled" name="tutorialCenterId" id="tutorialCenterId_${tutorialDetail.tutorialCenterId}" value="${tutorialDetail.tutorialCenterId}" data-cetername="${tutorialDetail.name}">
							<label for="tutorialCenterId_${tutorialDetail.tutorialCenterId}"></label>
					    </div>
				    </div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorialcenters.ageofcenter"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.ageOfCenter}&nbsp;years
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorialcenters.noofenrollment"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.noOffEnrollments}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorialcenters.bestrank"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.classroom}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorialcenters.nooffaculty"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.noOfFaculty}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorialcenters.timings"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.timings}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorialcenters.courses"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.programNames}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorialcenters.exams"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.examNames}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorialcenters.address"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.address}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorialcenters.contact"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.contactNumbers}
					</div>
				</div>
			</div>
	</c:forEach>
</div>
<script>
var centerCount = '${tutorialCentreSelectCount}';
$(document).ready(function(){
	if('${not empty tutCenterShortListObject}' == 'true')
	{
		$.each(JSON.parse('${tutCenterShortListObject}'), function(key, val){
			$("#"+val+"_"+key).find("input:checkbox").prop('checked', true);
			$("#"+val+"_"+key).find("input:checkbox").prop('disabled', true);
		});
	}
	
	fnAdjustCenterDivHeight();
});

function fnAdjustCenterDivHeight()
{
	var noOfRecords = '${not empty tutorialsDetails ? tutorialsDetails.size() : 0}';
	var maxHeight = 0;
	var cardStack = [];
	$.each($('.edupath-card:visible'), function(index){
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
	for(var i = 0; i < cardStack.length; i++)
	{
		cardStack[i].css("height", maxHeight);
	}
}

function fnGetTutorials()
{
	var form = document.tutorialDetailForm;
	form.action = '${pageContext.request.contextPath}/myapp/SelectTutorialsAction';
	form.submit();
}
</script>