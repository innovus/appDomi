/**
 * 
 */

var map;
var bermudaTriangle;
var infoWindow;

function initialize() {
  var mapOptions = {
    zoom: 16,
    //mapTypeId: google.maps.MapTypeId.RoadMap
    mapTypeId : google.maps.MapTypeId.RoadMap,
	zoomControlOptions: {
        style: google.maps.ZoomControlStyle.LARGE,
        position: google.maps.ControlPosition.LEFT_CENTER
    },
    mapTypeControlOptions: {
        style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
        position: google.maps.ControlPosition.BOTTOM_CENTER
    },
    panControl: true,
    panControlOptions: {
        position: google.maps.ControlPosition.TOP_RIGHT
    },
  };
  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);


  
  if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(showPosition);
  } else {
     
  }
  function showPosition(position) {
	  var pos = new google.maps.LatLng(position.coords.latitude,
              position.coords.longitude);
	         //  map.setCenter(pos);
	           

	           var latitude  = position.coords.latitude;
	           var longitude = position.coords.longitude;
	           var triangleCoords = [
									  dest(latitude,longitude,45,0.5),
									  dest(latitude,longitude,-45,0.5),
									  dest(latitude,longitude,225,0.5),
									  dest(latitude,longitude,-225,0.5)
	                             ];
	           

	           // Construct the polygon.
               bermudaTriangle = new google.maps.Polygon({
                 paths: triangleCoords,
                 draggable : true,
         		 editable : true,
                 strokeColor: '#FF0000',
                 strokeOpacity: 0.8,
                 strokeWeight: 3,
                 fillColor: '#FF0000',
                 fillOpacity: 0.35
               });

               bermudaTriangle.setMap(map);
               map.setCenter(getCenterOfPolygon(bermudaTriangle));
               google.maps.event.addListener(bermudaTriangle, 'click', showArrays);
               infoWindow = new google.maps.InfoWindow();

	}
 //***************************************************
  function showArrays(event) {

	  // Since this polygon has only one path, we can call getPath()
	  // to return the MVCArray of LatLngs.
	  var vertices = this.getPath();

	  var contentString = '<b>Bermuda Triangle polygon</b><br>' +
	      'Clicked location: <br>' + event.latLng.lat() + ',' + event.latLng.lng() +
	      '<br>';

	  // Iterate over the vertices.
	  for (var i =0; i < vertices.getLength(); i++) {
	    var xy = vertices.getAt(i);
	    contentString += '<br>' + 'Coordinate ' + i + ':<br>' + xy.lat() + ',' +
	        xy.lng();
	  }

	  // Replace the info window's content and position.
	  infoWindow.setContent(contentString);
	  infoWindow.setPosition(event.latLng);

	  infoWindow.open(map);
	}


  var dest = function(lat, lng, brng, dist) {
  	this._radius = 6371;
  	dist = typeof (dist) == 'number' ? dist : typeof (dist) == 'string'
  			&& dist.trim() != '' ? +dist : NaN;
  	dist = dist / this._radius;
  	brng = brng* Math.PI / 180;
  	
  	var lat1 = lat * Math.PI / 180;
  	var lon1 = lng * Math.PI / 180;

  	var lat2 = Math.asin(Math.sin(lat1) * Math.cos(dist) + Math.cos(lat1)
  			* Math.sin(dist) * Math.cos(brng));
  	var lon2 = lon1
  			+ Math.atan2(Math.sin(brng) * Math.sin(dist) * Math.cos(lat1), Math
  					.cos(dist)
  					- Math.sin(lat1) * Math.sin(lat2));
  	lon2 = (lon2 + 3 * Math.PI) % (2 * Math.PI) - Math.PI;
  	return new google.maps.LatLng((lat2 * 180 / Math.PI), (lon2 * 180 / Math.PI));
  }

  function getCenterOfPolygon(polygon){
  	var PI=22/7;
  	var X=0;
  	var Y=0;
  	var Z=0;
  	polygon.getPath().forEach(function (vertex, inex) {
  		lat1=vertex.lat();
  		lon1=vertex.lng();
  		lat1 = lat1 * PI/180;
  		lon1 = lon1 * PI/180;
  		X += Math.cos(lat1) * Math.cos(lon1);
  		Y += Math.cos(lat1) * Math.sin(lon1);
  		Z += Math.sin(lat1);
  	});
  	Lon = Math.atan2(Y, X);
  	Hyp = Math.sqrt(X * X + Y * Y);
  	Lat = Math.atan2(Z, Hyp);
  	Lat = Lat * 180/PI;
  	Lon = Lon * 180/PI;
  	return new google.maps.LatLng(Lat,Lon);
  }

                  
}

google.maps.event.addDomListener(window, 'load', initialize);