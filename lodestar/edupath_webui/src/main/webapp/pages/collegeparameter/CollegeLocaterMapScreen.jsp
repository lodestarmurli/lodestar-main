<%@ taglib prefix="c" uri="c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/commonutil.tld"%> 

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<s:hidden id="addresId"></s:hidden>
<div>
	<div class="map-row">
		<div>
			Address map
		</div>
	</div>
	<div style="margin-top:5px;">
		Note: To move the marker to different location right and click on OK button below to update the address
	</div>
	<div style="margin-top:5px; margin-bottom: 10px;" >
		<div id="collegeMap" style="height:500px; width: auto;" >
		</div>
	</div>
	<div class="map-btn-div">
		<div>
		  <button class="btn map-btn" onclick="fnSaveAddress();">OK</button>
		</div>
	</div>
	<div class="map-row">
		<div>
		</div>
	</div>
</div>
<style>
.map-row
{
    background-color: #0970BB;
    margin-left: -7px;
    margin-right: -12px;
    margin-top: -8px;
    height: 30px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 10px;
    color: #D1DBDB;
    font-size: 13pt;
    overflow: hidden;
}
.map-btn-div
{
   text-align: right;
   margin-bottom: 13px;
   padding-right: 7px;
   
}
.map-btn
{
    background-color: #F0483B !important;
    color: #fff;
}
</style>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDeh82D6z3OFrJNwNpDyfmydEu4-zzq-6w&libraries=places&callback=fnLoadMap" async defer></script>
<script type="text/javascript">
var map;
var marker;
var infowindow;
var zoomLevel = 15;

function fnLoadMap()
{
	var latlng = new google.maps.LatLng(12.91, 77.95);
	var opt =
	{
		center:latlng,
		zoom:6,
		minZoom:2,
		mapTypeId: google.maps.MapTypeId.ROADMAP,
		disableAutoPan:false,
		navigationControl:true,
		navigationControlOptions: {style:google.maps.NavigationControlStyle.LARGE},
		mapTypeControl:true,
		mapTypeControlOptions: {style:google.maps.MapTypeControlStyle.DROPDOWN_MENU}
	};
	map = new google.maps.Map(document.getElementById("collegeMap"), opt);
	  var geocoder = new google.maps.Geocoder;
	  var infowindow = new google.maps.InfoWindow;
		google.maps.event.addListener(map, 'rightclick', function(event) 
		{
		 	fnGeocodeLatLng(geocoder, map, infowindow, event.latLng);
		});
		
		var tempMap = map;
		var mapAddr = window.opener.fnGetAddress();
		 if( $.trim(mapAddr)!= '')
		 {
			geocoder = new google.maps.Geocoder();
			geocoder.geocode( { 'address': mapAddr}, 
				function(results, status) {
				  if (status == google.maps.GeocoderStatus.OK) {
				 	tempMap.setCenter(results[0].geometry.location);
				 	tempMap.setZoom(zoomLevel);
				    fnCreateMarker(results[0].geometry.location, tempMap, mapAddr);
				  }
				});
		} 
	return map;
}

 function fnCreateMarker(latLng, map, addressStr)
 {
	 $('#addresId').val(addressStr);
	if(marker)
		marker.setMap(null);

	marker= new google.maps.Marker({
		position: latLng,
		map: map
		});
	google.maps.event.addListener(marker, 'mouseover', function()
		{
			if(infowindow)
				infowindow.close();		
			var contentStr = getInfoWindowContent(addressStr);
			var infowindow = new google.maps.InfoWindow({
			    content: contentStr,
			    disableAutoPan: false
			});
			infowindow.open(map, marker);
		});
} 

function getInfoWindowContent(addressStr)
{
	var tableStr = "";
	tableStr += "<table cellpadding='0' cellspacing='2' border='0' style='font-family: verdana; font-size: 10px; height: 50px;'>";
	tableStr += "<tr><td>"+addressStr+"</td></tr>";
	return tableStr;
}

function fnClose()
{
	self.close();
}

function fnSaveAddress()
{
	if(marker)
	{
		window.opener.fnSetAddress($('#addresId').val());
		self.close();
	}
	else
		alert('Select a location before saving');
}

function fnGeocodeLatLng(geocoder, map, infowindow, latLng) 
{
	  geocoder.geocode({'location': latLng}, function(results, status) 
	  {
		    if (status === google.maps.GeocoderStatus.OK) 
		    {
		      if (results[0]) 
		      {
		        map.setZoom(zoomLevel);
		        fnCreateMarker(latLng, map, results[0].formatted_address);
		        infowindow.setContent(results[0].formatted_address);
		        infowindow.open(map, marker);
		      } 
		      else 
		      {
		        window.alert('No results found');
		      }
		    } 
		    else 
		    {
		      window.alert('Geocoder failed due to: ' + status);
		    }
	  });
	}
</script>
