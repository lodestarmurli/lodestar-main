<%@ taglib uri="/struts-tags" prefix="s"%>   
<%@ taglib uri="commonutil.tld" prefix="utils"%>
<%@ taglib prefix="c" uri="c.tld"%>

<c:set var="GOOGLE_APIKEY">AIzaSyDeh82D6z3OFrJNwNpDyfmydEu4-zzq-6w</c:set>

 <style>
  #map { 
	height: 100%; border-radius: 12px !important;  
	padding-top: 1px;
	padding-bottom: 1px;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3); 
	overflow: hidden;
	border-radius: 2px;
 }
 #legend {
    background: white;
    padding: 10px;
    max-width: 150px;
    opacity: 0.7;
  }
  #legend div{
  	margin-bottom: 10px;
  }
  #legend img {
	width: 30% !important;
}
</style>

<div class="row filter-margin-top">
	<div class="col-md-11 col-xs-9 common-search-header"><!-- added 27/03/18 -->
		<s:text name="com.edupath.finalize.header"/>
	</div>
	<div class="col-md-1 col-xs-3 map-filter-header bgcolor"><!-- added 27/03/18 -->
		<span>
			<s:text name="com.edupath.common.map.label"/>
		</span> 
		<i class="fa fa-map-marker fa-lg common-filter-icon"></i>
	</div>
</div>
<div class="final-details-action-bar">
	<div class="tut-procceed-button-div">
		<button class="btn" onclick="fnGetBackToTutorials()"><s:text name="com.edupath.tutorialfilter.backtotutorial"/></button>
	</div>
	<div class="tut-collegeback-button-div">
		<button class="btn" onclick="fnRedirectToCollegeSelect()"><s:text name="com.edupath.tutorialfilter.backtocollege"/></button>
	</div>
</div>
<div class="finalize-map-card display-hide">
	<div id="map"></div>
	<div id="legend"></div>
</div>
<div class="col-xs-11 col-md-11 finalize-card">  <!-- added 29/03/18 -->
	<div class="finalize-col-text">
		<s:text name="com.edupath.finalize.college.name"></s:text>
	</div>
	<c:forEach items="${collegeShortListDetails}" var="colShortDetail">
		<div class="finalize-main-deta-div" style="margin: 20px 0px 0px 22px; " id="colShortListId_${colShortDetail.id}"> <!-- added styletag 29/03/18 -->
			<div class="finalize-deta-div" data-icon="COLLEGE_ICON" data-name="${utils:replaceJSONEntities(colShortDetail.collegeName)}" data-address="${utils:replaceJSONEntities(colShortDetail.collegeAddress)}">
				${colShortDetail.collegeName}
			</div>
			<div class="finalize-deta-close-div" onclick="fnRemoveColShortList('${colShortDetail.id}')">
				<i class="glyphicon glyphicon-remove"></i>
			</div>
		</div>
	</c:forEach>
	
	<div class="finalize-tut-text">
		<s:text name="com.edupath.finalize.tutorialcenters.name"></s:text>
	</div>
	<c:forEach items="${tutorialCenterShortListDetails}" var="tutCenterShortDetail">
		<div class="finalize-main-deta-div" style="margin: 20px 0px 0px 22px; id="tutCenterShortListId_${tutCenterShortDetail.id}">   <!-- added styletag 29/03/18 -->
			<div class="finalize-deta-div" data-icon="TUTORIAL_ICON" data-name="${utils:replaceJSONEntities(tutCenterShortDetail.tutorialName)}", data-address="${utils:replaceJSONEntities(tutCenterShortDetail.tutorialCenterAddress)}">
				${tutCenterShortDetail.tutorialName}&nbsp;(${tutCenterShortDetail.cityName}&nbsp;-&nbsp;${tutCenterShortDetail.locality})
			</div>
			<div class="finalize-deta-close-div" onclick="fnRemoveTutCenterShortList('${tutCenterShortDetail.id}')">
				<i class="glyphicon glyphicon-remove"></i>
			</div>
		</div>
	</c:forEach>
</div>
<div class="finalize-confirm-div">
	<button class="btn btn-sm finalize-confirm-buttton" onclick="fnShowSummary()">
		<s:text name="com.edupath.common.confirm"></s:text>
	</button>
</div>

<s:form name="finalizeForm" id="finalizeForm" action="" method="POST">
	<s:hidden name="parentSelectedSidebarMenuId"/>
	<s:hidden name="childSelectedSidebarMenuId"/>
</s:form>

<%-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDeh82D6z3OFrJNwNpDyfmydEu4-zzq-6w&libraries=places&callback=initMap" async defer></script> --%>
<script type="text/javascript">
$(document).ready(function(){
	userLocation.initLocation();
});

var map;
var infoWindow;
var service;
var bounds;
var reloadMap = true;
var markers = [];
var icons = {"HOME_ICON" : {legend: "Home",icon:"http://maps.google.com/mapfiles/kml/paddle/grn-circle.png"},
			"COLLEGE_ICON" : {legend: "College",icon:"http://maps.google.com/mapfiles/kml/paddle/blu-circle.png"}, 
			"TUTORIAL_ICON" : {legend: "Tutorial",icon:"http://maps.google.com/mapfiles/kml/paddle/red-circle.png"}};

function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
	  center: {lat: userLocation.getCurrentLat(), lng: userLocation.getCurrentLng()},
	  zoom : 14,
	  streetViewControl: false
	});
	map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(document.getElementById('legend'));
	
	infoWindow = new google.maps.InfoWindow();
	service = new google.maps.places.PlacesService(map);
	bounds = new google.maps.LatLngBounds();
	performSearch();
	addLegend();
}

var searchStatus = {};
function performSearch() {
	searchStatus = {};
	if(markers.length > 0)
	{
		$.each(markers, function(index, marker){
			marker.setMap(null);
		});
		markers = [];
	}
	$(".finalize-deta-div").each(function(index){
		var name = $(this).data("name");
		var icon = $(this).data("icon");
		var address = $(this).data("address");
		fnSearch(name, icon, address, index, $(".finalize-deta-div").size());
	});
	// Home addrs
	if('${not empty studAddr}' == 'true')
	{
		var homeAddr = "${utils:replaceJSONEntities(studAddr)}";
		fnSearch(homeAddr , 'HOME_ICON', homeAddr, 0, 1, true);
	}
}

function fnSearch(name, icon, address, index, totalSize, isHome)
{
	if(name)
	{
		var request = {
		  location: userLocation.getPosition(),
		  bounds: map.getBounds(),
		  radius: '50000',
		  name: name,
		  query : address
		};
		
		service.textSearch(request, function(results, status){
			var callbackStatus = callback(results, status, icon, name);
			if(callbackStatus)
			{
				if(searchStatus[callbackStatus])
				{
					searchStatus[callbackStatus] = searchStatus[callbackStatus] + 1;
				}
				else
				{
					searchStatus[callbackStatus] = 1;
				}
			}
			if((index + 1) == totalSize)
			{
				map.initialZoom = true;
				map.fitBounds(bounds);
				if(markers.length == 1)
				{
					map.setZoom(14);
				}
				
				if(!isHome && searchStatus.ZERO_RESULTS && searchStatus.ZERO_RESULTS < totalSize)
				{
					alert('Unable to locate few college/tutorial');
				}
				else if(!isHome && searchStatus.ZERO_RESULTS && searchStatus.ZERO_RESULTS == totalSize)
				{
					alert('Unable to locate any college/tutorial');
				}
				
				if(searchStatus.OVER_QUERY_LIMIT)
				{
					alert('Try again after some time');   
				}
			}
		});
	}
}

function callback(results, status, icon, name) {
	if (status !== google.maps.places.PlacesServiceStatus.OK) {
    	console.error(status);
    	return status;
  	}
  	for (var i = 0, result; result = results[i]; i++) {
    	addMarker(result, icon, name);
    	return;
  	}
}

function addMarker(place, icon, name) {
  var marker = new google.maps.Marker({
    map: map,
    position: place.geometry.location,
    icon: {
        url: icons[icon].icon
//         ,anchor: new google.maps.Point(10, 10)
       , scaledSize: new google.maps.Size(30, 40)
      }
  });
  markers.push(marker);
  bounds.extend(place.geometry.location);

  google.maps.event.addListener(marker, 'mouseover', function() {
    service.getDetails(place, function(result, status) {
      if (status !== google.maps.places.PlacesServiceStatus.OK) {
        console.error(status);
        return;
      }
      infoWindow.setContent("<b>" + name + "</b>" + "<br/>" + result.adr_address);
      infoWindow.open(map, marker);
    });
  });
}

function addLegend()
{
	var legend = document.getElementById('legend');
	$.each(icons, function(key, value) {
	  var div = document.createElement('div');
	  div.innerHTML = '<img src="' + value.icon + '"> <b>' + value.legend+"</b>";
	  legend.appendChild(div);
	});	
}

//--------------------------------------
	var doMapInit = true;
	$(".map-filter-header").click(function (){
		$(".map-filter-header").toggleClass( "bgcolor" );
		if($(".finalize-map-card").is(":visible"))
		{
			$(".finalize-map-card").hide("blind");
			$(".finalize-card, .finalize-confirm-div").show();
		}
		else
		{
			$(".finalize-map-card").show("blind");
			if(doMapInit)
			{
				$.getScript("https://maps.googleapis.com/maps/api/js?key=${GOOGLE_APIKEY}&libraries=places&callback=initMap", function(){
					doMapInit = false;
				});
			}
			else
			{

				performSearch();
			}
			$(".finalize-card, .finalize-confirm-div").hide();
		}
	});
	
	function fnRemoveColShortList(colShortListId)
	{
		if (confirm('Are you sure you want to delete?')) 
		{
			url = '${pageContext.request.contextPath}/myapp/deleteColShortListFinalizeAction';
			$.ajax({
				url : url,
				type: 'POST',
				data : ({
					'colShortListId' : colShortListId
				}),
				success : function(resp)
				{
					if(resp != "" && resp != null)
		 			{
						if(resp.status = 'success')
						{
							$("#colShortListId_"+colShortListId).remove();
						}
						
		 			}
				},
				error : function(msg,arg1,arg2)
				{
					alert("page load error");
				}
			});
		}
	}
	
	function fnRemoveTutCenterShortList(tutCenterShortListId)
	{
		if (confirm('Are you sure you want to delete?')) 
		{
			url = '${pageContext.request.contextPath}/myapp/deleteTutCenterShortListFinalizeAction';
			$.ajax({
				url : url,
				type: 'POST',
				data : ({
					'tutCenterShortListId' : tutCenterShortListId
				}),
				success : function(resp)
				{
					if(resp != "" && resp != null)
		 			{
						if(resp.status = 'success')
						{
							$("#tutCenterShortListId_"+tutCenterShortListId).remove();
						}
		 			}
				},
				error : function(msg,arg1,arg2)
				{
					alert("page load error");
				}
			});
		}
	}
	
	function fnShowSummary()
	{
		var form = document.finalizeForm;
		form.action = '${pageContext.request.contextPath}/myapp/FinalSummaryAction';
		form.submit();
	}
	
	function fnGetBackToTutorials()
	{
		var form = document.finalizeForm;
		form.action = '${pageContext.request.contextPath}/myapp/SelectTutorialsAction';
		form.submit();
	}
	
	function fnRedirectToCollegeSelect()
	{
		var form = document.finalizeForm;
		form.action = '${pageContext.request.contextPath}/myapp/getFilterCollegeSelectionAction';
		form.submit();
	}
</script>