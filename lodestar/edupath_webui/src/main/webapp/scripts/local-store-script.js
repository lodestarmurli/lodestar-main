var EdupathLocalStore = function () {
	var TUTORIAL_LOCALSTORE = "TUTORIAL_LOCALSTORE";
	var COLLEGE_LOCALSTORE = "COLLEGE_LOCALSTORE";
	var COLLEGE_SELECT_LOCALSTORE = "COLLEGE_SELECT_LOCALSTORE";
	var COLLEGE_LAST_SELECT_LOCALSTORE = "COLLEGE_LAST_SELECT_LOCALSTORE";
	return {
		doInsert : function(storeName, key, data)
		{
			localStorage.setItem(storeName + "_" + key, JSON.stringify(data));
		},
		doUpdate : function(storeName, key, data)
		{
			localStorage.setItem(storeName + "_" + key, JSON.stringify(data));
		},
		doDelete : function(storeName, key, data)
		{
			localStorage.removeItem(storeName + "_" + key);
		},
		doGet: function(storeName, key)
		{
			return localStorage.getItem(storeName + "_" + key);
		},
		getTutorialName : function()
		{
			return TUTORIAL_LOCALSTORE;
		},
		getCollegeName : function()
		{
			return COLLEGE_LOCALSTORE;
		},
		getCollegeSelectName : function()
		{
			return COLLEGE_SELECT_LOCALSTORE;
		},
		getCollegeLastSelectName : function()
		{
			return COLLEGE_LAST_SELECT_LOCALSTORE;
		}
	};
}();

var CollegeSelectFilter = function (){
	return {
		genFilterValue : function(tagObj, paramJSON)
		{
			var jsonKey = $(tagObj).data("paramName");
			var jsonVal =  $(tagObj).val();
			if(jsonVal && jsonVal.indexOf(":::") > 0)
			{
				var splitedValue = jsonVal.split(":::");
				jsonKey = splitedValue[0];
				jsonVal = splitedValue[1];
			}
			if($(tagObj).data("rangeParam"))
			{
				var rangeSplitVal = jsonVal.split("-"); 
				paramJSON[jsonKey+"From"] = rangeSplitVal[0];
				paramJSON[jsonKey+"To"] = rangeSplitVal[1];  
			}
			else
			{
				if(CollegeSelectFilter.checkIsMultiSelect(tagObj))
				{
					if(paramJSON[jsonKey])
					{
						paramJSON[jsonKey] += ","+jsonVal;
					}
					else
					{
						paramJSON[jsonKey] = jsonVal;
					}
				}
				else
				{
					paramJSON[jsonKey] = jsonVal;
				}
			}
		},
		checkIsMultiSelect : function(tagObj){
			if($(tagObj).data("ismultiSelect"))
			{
				return true;
			}
			return false;
		},
		genGetCollegeValue : function(tagObj, paramJSON)
		{
			var jsonVal = $(tagObj).val();
			if(CollegeSelectFilter.checkIsMultiSelect(tagObj))
			{
				var valueArray = new Array();
				if(paramJSON[$(tagObj).data("collegeParamId")])
				{
					valueArray = paramJSON[$(tagObj).data("collegeParamId")];
				}
				valueArray.push(jsonVal);
				paramJSON[$(tagObj).data("collegeParamId")] = valueArray;
			}
			else
			{
				paramJSON[$(tagObj).data("collegeParamId")] = jsonVal;
			}
			if($('#location_preference').val() && $('#location_preference').val() != '')
			{
				paramJSON["zone"] = $('#location_preference').val();
			}
		}
	};
}();

var CollegeShortListScript = function(){
	return{
		submitToCollegeCompareScreen : function(minCount, maxCount){
			
			var collegeIds = null;
			var collegeCounter = 0;
			$.each($('.college-check.styled'), function(){
				if($(this).is(":checked"))
				{
					if(!collegeIds)
					{
						collegeIds = $(this).val();
					}
					else
					{
						collegeIds += "," + $(this).val();
					}
					collegeCounter++;
				}
			});
			
			if(collegeCounter == 0)
			{
				$('#errorModalMsg').html("Please select some colleges to compare");
				$('#errorMsgModal').modal("show");
			}
			else if(collegeCounter < minCount)
			{
				$('#errorModalMsg').html("Please select atleast two colleges to compare");
				$('#errorMsgModal').modal("show");
			}
			else if(collegeCounter > maxCount)
			{
				$('#errorModalMsg').html("Max " + LodeCollege.getMaxCollegeCompare() + " colleges can be comapared");
				$('#errorMsgModal').modal("show");
			}
			else
			{
				CollegeShortListScript.submitToCompareScreen(collegeIds, true);
			}
		},
		submitToCompareScreen : function(collegeIds, isCompareAction){
			$('#isCompareAction').val(isCompareAction);
			$('#collegeIdsStr').val(collegeIds);
			$('#CollegeCompareNShortList').submit();
		}
	};
}();

var userLocation = function(){
	var currentLat = 12.9756;
	var currentLng = 77.5935;
	return {
		initLocation : function()
		{
//			if(navigator.geolocation)	
//			{
//		 		navigator.geolocation.getCurrentPosition(function(position){
//		 			currentLat = position.coords.latitude;
//					currentLng = position.coords.longitude;
//		 		});
//			}
		},
		getCurrentLat : function()
		{
			return currentLat;
		},
		getCurrentLng: function()
		{
			return currentLng;
		},
		getPosition : function()
		{
			return new google.maps.LatLng(currentLat, currentLng);
		}
	};
}();