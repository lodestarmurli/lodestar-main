<%@ taglib uri="/struts-tags" prefix="s"%>   

<s:set var="filterErrorMsg">Please select some filter to apply</s:set>

<div class="row filter-margin-top">
	<div class="col-md-1 common-filter-header">
		<span>
			<s:text name="com.edupath.common.filter"/>
		</span> 
		<i class="fa fa-filter fa-lg common-filter-icon"></i>
	</div>
	<div class="col-md-11 common-search-header">
		<s:text name="com.edupath.college.selectcollege.header"/>
	</div>
</div>

<div class="college-filters col-xs-12" style="display: none;">		<!--added 27/03/18-->
	<s:iterator value="allCollegeParams" var="collegeParam" status="paramCounter">
		<div class="college-filters-header">
			<div>${collegeParam.filterDisplayName}</div>
			<s:iterator value="%{#collegeParam.values}" var="collegeParamValues" status="valueCounter">
				<div class="college-filters-value">
					<div class="checkbox checkbox-success cust_checkbox">
		            	<input type="checkbox" id="checkbox_${paramCounter.index}_${valueCounter.index}" value="${collegeParamValues.actualValue}" name="${collegeParam.id}"
		            		data-param-name="${collegeParam.paramName}" data-range-param="${collegeParam.isRangeParam}" data-college-param-id="${collegeParam.id}" 
		            		data-ismulti-select="${collegeParam.isMultiSelect}">
		            	<label for="checkbox_${paramCounter.index}_${valueCounter.index}">${collegeParamValues.displayValue}</label>
		        	</div>	
		        </div>	
			</s:iterator>
		</div>
	</s:iterator>
	<div class="college-filters-header">
		<div class="college-filters-value reset-btn-div">
			<button id="reset_btn" class="btn btn-sm reset-btn college-filter-btn">
				<s:text name="com.edupath.common.reset"/>
			</button>		
		</div>
		<div class="college-filters-value">
			<button id="cancel_btn" class="btn btn-sm cancel-btn college-filter-btn">
				<s:text name="com.edupath.common.cancel"/>
			</button>
		</div>
		<div class="college-filters-value ">
			<button id="apply_btn" class="btn btn-sm apply-btn college-filter-btn">
				<s:text name="com.edupath.common.apply"/>
			</button>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		applyLastSelectedFilter(false, '${isBackAction}');
	});
	
	function applyLastSelectedFilter(isLastChange, isBackAction)
	{
		var selectedFilter = null;
		if(!isBackAction && !isLastChange && EdupathLocalStore.doGet(EdupathLocalStore.getCollegeSelectName(), '${studentId}') && 
				(selectedFilter = EdupathLocalStore.doGet(EdupathLocalStore.getCollegeSelectName(), '${studentId}')).length > 0)
		{
			checkFilterByCollegeParamData(selectedFilter);
			// On click of reset/on ready reload data based on selected college parameters
			$("#apply_btn").click();
		}
		else if(EdupathLocalStore.doGet(EdupathLocalStore.getCollegeLastSelectName(), '${studentId}') && 
				(selectedFilter = EdupathLocalStore.doGet(EdupathLocalStore.getCollegeLastSelectName(), '${studentId}')).length > 0)
		{
			checkFilterByCollegeParamData(selectedFilter);
			if(isBackAction)
			{
				$("#apply_btn").click();
			}
		}
		else
		{
			alert('${pageScope.filterErrorMsg}');
		}
	}
	
	function checkFilterByCollegeParamData(selectedFilter)
	{
		selectedFilter = JSON.parse(selectedFilter);
		$.each($('.college-filters-value').children("div").find("input"), function(){
			var $inputObjTag = this;
			$($inputObjTag).prop('checked', false);
			$.each(selectedFilter, function(collegeParamId, paramValue){
				if(collegeParamId == $($inputObjTag).data("collegeParamId"))
				{
					if(Array.isArray(paramValue))
					{
						$.each(paramValue, function(index, arrayParamVal){
							if(arrayParamVal == $($inputObjTag).val())
							{
								$($inputObjTag).click();
							}
						});
					}
					else if(paramValue == $($inputObjTag).val())
					{
						$($inputObjTag).click();
					}
				}
			});
		});
	}

	$(".common-filter-header").click(function (){
		$(".common-filter-header").toggleClass( "bgcolor" );
		if($(".college-filters").is(":visible"))
		{
			$(".college-filters").hide("blind");
		}
		else
		{
			$(".college-filters").show("blind");
		}
	});
	
	$("#apply_btn").click(function(){
		//Save to local store
		$(".common-filter-header").toggleClass( "bgcolor" );
		if($('.college-filters-value').children("div").find("input").length > 0)
		{
			var isFilterChecked = false;
			var paramJSON = {};
			var collegeParmJSON = {};
			$.each($('.college-filters-value').children("div").find("input"), function(){
				if($(this).is(":checked")){
					CollegeSelectFilter.genGetCollegeValue(this, collegeParmJSON);
					CollegeSelectFilter.genFilterValue(this, paramJSON);
					isFilterChecked = true;
				}
			});	
			if(isFilterChecked)
			{
				LodeCollege.resetPageSize();
				EdupathLocalStore.doInsert(EdupathLocalStore.getCollegeName(), '${studentId}', paramJSON);
				EdupathLocalStore.doInsert(EdupathLocalStore.getCollegeLastSelectName(), '${studentId}', collegeParmJSON);
				getColleges();
				$(".college-filters").hide("blind");
			}
			else
			{
				alert("${pageScope.filterErrorMsg}");
			}
		}
	});
	
	$("#cancel_btn").click(function(){
		applyLastSelectedFilter(true);
	});
	
	$("#reset_btn").click(function(){
		applyLastSelectedFilter(false);
	});
	
	$('.checkbox.checkbox-success.cust_checkbox').click(function(){
		var $input = $(this).find("input");
		if(!$input.data("ismultiSelect"))
		{
			$('input[name="'  + $input.attr('name') + '"]').not($input).prop('checked', false);
		}
	});
</script>