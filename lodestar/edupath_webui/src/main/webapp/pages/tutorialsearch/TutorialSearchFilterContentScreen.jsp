<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%>
<%@ taglib prefix="c" uri="c.tld"%>

<s:if test="totalCount > 0">
	<div>
		<div class='tut-total-records-div'>
			<s:text name="com.edupath.common.totalrecords" >
				<s:param><b>${totalCount}</b></s:param>
			</s:text>
		</div>
		<div class="tut-procceed-button-div">
			<button class="btn" onclick="fnGetTutorialsCenters()"><s:text name="com.edupath.tutorialfilter.choosecentre"/></button>
		</div>
		<div class="tut-skip-button-div">
			<button class="btn" onclick="fnSkip()"><s:text name="com.edupath.tutorialfilter.skip"/></button>
		</div>
		<div class="tut-collegeback-button-div">
			<button class="btn" onclick="fnRedirectToCollegeSelect()"><s:text name="com.edupath.tutorialfilter.backtocollege"/></button>
		</div>
	</div>
</s:if>

<s:if test="totalCount == 0 and totalRecords == 0 and loadFurther == false and pageNumber == 0">
	<div class="tut-total-records-div" id="noTutRecordId">
		<s:text name="com.edupath.common.totalrecords" >
			<s:param><b>${totalCount}</b></s:param>
		</s:text>
	</div>
</s:if>
 <div class="edupath-main">
	<c:forEach items="${tutorialsDetails}" var="tutorialDetail">
			<div class="edupath-card edupath-card-${pageNumber}">
				<div class="edupath-card-header">
					<div class="edupath-card-title edupath-card-title-cursernone">
						${tutorialDetail.name}
					</div>
					<div class="edupath-card-checkbox">
						<div class="col-md-2 chk_box_custom checkbox  checkbox-success">
				       		<input type="checkbox" class="styled" name="tutorialId" id="tutorialId_${tutorialDetail.tutorialId}" value="${tutorialDetail.tutorialId}">
							<label for="tutorialId_${tutorialDetail.tutorialId}"></label>
					    </div>
				    </div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorial.detail.noofcentersinindia"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.centerAllIndia}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorial.detail.noofcentersincity"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.noOfCentersInCity}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorial.detail.highestrank"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.classroom}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorial.detail.ranksseatsachieved"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.noOfStudentsClear}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorial.detail.noofstuenrolled"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.noOfStudentsEnroll}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorial.detail.throghputratio"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.throughPutRatio}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorial.detail.admissioncriteria"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.admissionCriteria}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorial.detail.yearofestab"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.yearOfEst}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorial.detail.programtype"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.programNames}
					</div>
				</div>
				<div class="edupath-card-row">
					<div class="edupath-card-col1">
						<s:text name="com.edupath.tutorial.detail.examnames"></s:text>
					</div>
					<div class="edupath-card-col2">
						${tutorialDetail.examNames}
					</div>
				</div>
			</div>
	</c:forEach>
</div>
<script>
	$(document).ready(function(){
		
		var count = '${totalCount}';
		$("#loadFurther").val('${loadFurther}');
		$("#pageNumber").val('${pageNumber}');
		 $("#pageSizeCount").val('${pageSizeCount}');
		if(count > 0)
		{
			//$("#totalTutorialRecordDiv").show();
			//$("#totalRecordId").text(count);
			//$("#noRecordDiv").hide();
		} 	
		else
		{
			//$("#totalTutorialRecordDiv").hide();
			//$("#noRecordDiv").show();
		}
		fnAdjustDivHeight('${pageNumber}');
	});
	
	function fnAdjustDivHeight(pageNo)
	{
		var noOfRecords = '${not empty tutorialsDetails ? tutorialsDetails.size() : 0}';
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