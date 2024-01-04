<%@taglib prefix="s" uri="/struts-tags"%>

<div>
	<jsp:include page="CollegeSelectFilterScreen.jsp">
		<jsp:param value="${allCollegeParams}" name="allCollegeParams"/>
	</jsp:include>
</div>
<div id="collegeDetailsDiv" class="college-details-div">
</div>
<div class="nomore-records-center" style="display: none">
	<div>
		<s:text name='com.edupath.common.nomorerecords.text'/>
	</div>
</div>

<jsp:include page="CollegeShortListModalScreen.jsp">
	<jsp:param value="${parentSelectedSidebarMenuId}" name="parentSelectedSidebarMenuId"/>
	<jsp:param value="${childSelectedSidebarMenuId}" name="childSelectedSidebarMenuId"/>
	<jsp:param value="${isCompareAction}" name="isCompareAction"/>
</jsp:include>

<script type="text/javascript">
	
	function getColleges(doAppend)
	{
		if(LodeCollege.doLodeFurther() && !LodeCollege.isPageLoaded(LodeCollege.getPageNo()))
		{
			var filterParam = getDataFromLocalStore();
			var collegeParam = EdupathLocalStore.doGet(EdupathLocalStore.getCollegeSelectName(), '${studentId}');
			// Setting zone filter value if zone selected in college parameter screen
			if(collegeParam)
			{
				collegeParam = JSON.parse(collegeParam);
				if(collegeParam.zone)
				{
					filterParam.zone = collegeParam.zone;
				}
			}
			filterParam.pageNo = LodeCollege.getPageNo();
			LodeCollege.pushPageSise(LodeCollege.getPageNo());
			$("#collegeFilterLoading").show();
			$.ajax({
				url : '${pageContext.request.contextPath}/myapp/getCollegesCollegeSelectionAction',
				type : "POST",
				async: true,
				data : filterParam,
				success : function (resp){
					if(resp != null)
					{
						if(doAppend)
						{
							$('#collegeDetailsDiv').append(resp);
						}
						else
						{
							$('#collegeDetailsDiv').html(resp);
						}
					}
					$("#collegeFilterLoading").hide();
				},
				error : function (arg0, arg1, arg2){
					$("#collegeFilterLoading").hide();
					alert(arg1);
				}
			});		
		}
// 		else
// 		{
// 			console.log("not loading"+LodeCollege.getPageNo()+"::"+LodeCollege.doLodeFurther());
// 		}
	}
	
	function getDataFromLocalStore()
	{
		return JSON.parse(EdupathLocalStore.doGet(EdupathLocalStore.getCollegeName(), '${studentId}'));
	}
	
	var maxPos = 0;
	$("#my_scrolling_pane").scroll(function()
	{
		var windowpos = $("#my_scrolling_pane").scrollTop();
        if(windowpos >= maxPos)
		{
        	maxPos = ($('.nomore-records-center').position().top - 600);
        	getColleges(true);
		}
	});
	var noOfRecordsLoaded = 0;
	var lodeFurther = true;
	var totalRecords = 0;
	var pageNo = 0;
	var pageStack = [];
	var maxCollegeCompare = '${maxCollegeCompare}';
	var LodeCollege = function (){
		return {
			init : function(){
				noOfRecordsLoaded = 0;
				lodeFurther = true;
				totalRecords = 0;
				pageNo = 0;
				pageStack = [];
				maxPos = 0;
			},
			setRecordsDetails : function(noOfRecords, totalRecordsArg, pageNoArg){
				noOfRecordsLoaded += parseInt(noOfRecords);
				totalRecords = parseInt(totalRecordsArg);
				pageNo = (parseInt(pageNoArg) + 1);
				if(noOfRecordsLoaded >= totalRecords)
				{
					lodeFurther = false;
					if(pageNoArg > 0)
					{
						$('.nomore-records-center').show();
					}
				}
			},
			doLodeFurther : function(){
				return lodeFurther;
			},
			getPageNo : function(){
				return pageNo;
			},
			pushPageSise : function(pageSize){
				pageStack.push(pageSize);
			},
			isPageLoaded : function(pageSize){
				if($.inArray(pageSize, pageStack) > 0)
				{
					return true;
				}
				return false;
			},
			resetPageSize : function(){
				LodeCollege.init();
				$('.nomore-records-center').hide();
			},
			getMaxCollegeCompare : function(){
				return maxCollegeCompare;
			}
		};
	}();
</script>