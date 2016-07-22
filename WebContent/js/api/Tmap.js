var tData;

var map;
var kmlLayer;
var userX;
var userY;
var kmlForm;
var size;
var offset;
var icon;
var markers;
var marker;

var clickX;
var clickY;
var markerLayer;


function initialize(myX,myY){
	userX=myX;
	userY=myY
	console.log(myX);
	map = new Tmap.Map({div:'map_div',width:'100%', height:'100%'});
	map.setCenter(new Tmap.LonLat(myX, myY),15);
	markerLayer = new Tmap.Layer.Markers();
	map.addLayer(markerLayer);
	 
	var lonlat = new Tmap.LonLat(myX, myY);
	 
	size = new Tmap.Size(24,38);

	offset = new Tmap.Pixel(-(size.w/2), -(size.h/2));
	icon = new Tmap.Icon('https://developers.skplanetx.com/upload/tmap/marker/pin_b_m_a.png', size, offset); 
	
	marker = new Tmap.Marker(lonlat, icon);
	markerLayer.addMarker(marker);
	
	markers = new Tmap.Layer.Markers( "MarkerLayer" );
	map.addLayer(markers);
	tData = new Tmap.TData();
	setLayers();
	map.events.register("click", map, onClickMap); 
}



function getRouteData(startX,startY,endX,endY){
	if(clickX!=null){
		startX =clickX;
		startY =clickY;
	}
    var startLon = "14135591.321772";
  
    var startLat = "4518111.822511";
	
	startLon=startX;
	startLat=startY;
	
	console.log("startX"+startX);
	console.log("startY"+startY);
	console.log("endX"+endX);
	console.log("endY"+endY);
	
    markers.clearMarkers();

    var startLonLat = new Tmap.LonLat(startLon, startLat);

    var endLonLat = new Tmap.LonLat(endX, endY);

    tData = new Tmap.TData();

	var option = {
		version:"1",
		format:'xml'                                                 
	}
	tData.getRoutePlan(startLonLat, endLonLat, option);
	tData.events.register("onComplete", tData, onLoadSuccess);
	tData.events.register("onProgress", tData, onProgressLoadData);
	tData.events.register("onError", tData, onErrorLoadData);                     
	
}
function onProgressLoadData(){
	console.log("로딩중 ");
}
function onErrorLoadData(){
	console.log("에러 ");
}
function onLoadSuccess(){

    jQuery('#loading').css('display', "block");
    if(kmlForm!=null){
    	for(var i=0;i<=kmlForm.length-1;i++){
            kmlLayer.removeFeatures([kmlForm[i]]);
	    }
    }
	kmlForm = new Tmap.Format.KML().read(this.responseXML);
	
    for(var i=0;i<=kmlForm.length-1;i++){
              kmlLayer.addFeatures([kmlForm[i]]);
     }
    setTimeout(stop, 500);
}
function setLayers(){

    if(!map){
    	var msg = "map객체가 초기화되기 전에 레이어가 등록되었습니다."
    	alert(msg);
    	return;                                 
    } 

    var context = {

	getColor: function(feature){

		var color = '#aaaaaa';
	
		if (feature.attributes.clazz&&feature.attributes.clazz === 4) {
		
			color = '#ee0000';
		
		} else if(feature.cluster) {
	
			var onlyFour = true;
	
			for (var i = 0; i<feature.cluster.length; i++) {
				if (onlyFour&&feature.cluster[i].attributes.clazz !== 4) {
					onlyFour = false;
				}
			}
	
		if (onlyFour === true) {
		          color = '#ee0000';
		    }
		}
		return color;
	
	}
};                                                                       

var style = new Tmap.Style({
	
	pointRadius: 5,
	fillColor: "${getColor}",
	fillOpacity: 1,
	strokeColor: "#FF0000",
	strokeWidth: 2,
	strokeOpacity: 1,
	graphicZIndex: 1
	}, {
	context: context
	});  	               
	var v_option = {renderers: ['Canvas', 'SVG', 'VML'], styleMap:style};                                       
	
	kmlLayer = new Tmap.Layer.Vector("kml", v_option);
	
	map.addLayer(kmlLayer);

} 
function getRouteDataOutSide(startX, startY,endX,endY){
	var starXY  = get3857LonLat(startX,startY);
	startX = starXY.lon;
	startY = starXY.lat;

	var endXY  = get3857LonLat(endX,endY);
	endX = endXY.lon;
	endY = endXY.lat;
	getRouteData(startX,startY,endX,endY);
	
}
function onClickMap(e){ 
	if(markers !=null){
		markerLayer.destroy();
	}
    var lonlat = map.getLonLatFromViewPortPx(e.xy); 
    
    markerLayer = new Tmap.Layer.Markers();
	map.addLayer(markerLayer);
	 
	marker = new Tmap.Marker(new Tmap.LonLat(lonlat.lon, lonlat.lat), icon);
	markerLayer.addMarker(marker);
	
	markers = new Tmap.Layer.Markers( "MarkerLayer" );
	map.addLayer(markers);
    
    clickX=lonlat.lon;
    clickY=lonlat.lat;
} 
