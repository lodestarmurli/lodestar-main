var Geolocation = function(){
	var mapsById = {};
	var servicesById = {};
	var boundsById = {};
	var infoWindow;
	var reloadMap = true;
	return {
		initMap : function(mapId)
		{
			mapsById[mapId] = new google.maps.Map(document.getElementById(mapId), {
			  center: {lat: userLocation.getCurrentLat(), lng: userLocation.getCurrentLng()},
			  zoom : 15
			});
			infoWindow = new google.maps.InfoWindow();
			servicesById[mapId] = new google.maps.places.PlacesService(mapsById[mapId]);
			boundsById[mapId] = new google.maps.LatLngBounds();
			
			var listener = google.maps.event.addListener(map, "idle", function() 
			{ 
				mapsById[mapId].fitBounds(boundsById[mapId]);
				google.maps.event.removeListener(listener);
			});
			Geolocation.performSearch(mapId);
		},
		performSearch : function (mapId)
		{
			$(".finalize-deta-div").each(function(){
				if($(this).data("name"))
				{
					var request = {
					  location: mapsById[mapId].getCenter(),
					  bounds: mapsById[mapId].getBounds(),
					  radius: '500000',
					  name: $(this).data("name")
					};
					servicesById[mapId].radarSearch(request, doCallback);
				}
			});
			
			function doCallback() {
				if (status !== google.maps.places.PlacesServiceStatus.OK) 
				{
			    	console.log(status);
			    	return;
			  	}
			  	for (var i = 0, result; result = results[i]; i++) 
			  	{
			    	addMarker(mapId, result);
			    	return;
			  	}
			}
		},
		addMarker : function(mapId, result){
			 var marker = new google.maps.Marker({
			    map: mapsById[mapId],
			    position: place.geometry.location
			 });
			 boundsById[mapId].extend(place.geometry.location);

			google.maps.event.addListener(marker, 'click', function() {
				servicesById[mapId].getDetails(place, function(result, status) {
					if (status !== google.maps.places.PlacesServiceStatus.OK) {
						console.error(status);
						return;
					}
					infoWindow.setContent("<b>" + result.name + "</b>" + "<br/>" + result.adr_address);
					infoWindow.open(mapsById[mapId]);
				});
			});
		}
		
	};
}();