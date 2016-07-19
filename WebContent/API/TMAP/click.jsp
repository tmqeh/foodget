<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<style type="text/css">
#menu1{
           position:absolute;
           display:none;
           width:220px;
           height:100px;
           background-color:#FFFFFF;
           border:8px solid #C1C1C1;
           z-index:2000;
           overflow-x:hidden; 
           overflow-y:auto; 
           *overflow-y:scroll; 
} ﻿ 
</style>
<script language="javascript" src="https://apis.skplanetx.com/tmap/js?version=1&format=javascript&appKey=5064adfe-57cd-35d4-b5dd-3d46c557ad0e"></script>
<script>
var markers;
var map;
var kmlLayer;
var kmlForm;
var marker;
var size;
var offset;
var icon;

function initialize(){
	myX='14363856.085492350';
	myY='4178405.946508492';
	map = new Tmap.Map({div:'map_div', width:'100%',height:document.documentElement.clientHeight-30+"px"});
	map.setCenter(new Tmap.LonLat(myX, myY),15);

	var lonlat = new Tmap.LonLat(myX, myY);
    markers = new Tmap.Layer.Markers( "MarkerLayer" );
    map.addLayer(markers);
    size = new Tmap.Size(24,38);
    offset = new Tmap.Pixel(-(size.w/2), -size.h);
    icon = new Tmap.Icon('https://developers.skplanetx.com/upload/tmap/marker/pin_b_m_a.png', size, offset);  
	marker = new Tmap.Marker(lonlat, icon);
	markers.addMarker(marker);
	map.events.register("click", map, onClickMap); 
	
}
window.onload = function() {
    initialize();
}
function onClickMap(e){ 
	if(markers !=null){
		markers.destroy();
	}
    var lonlat = map.getLonLatFromViewPortPx(e.xy); 
    markers = new Tmap.Layer.Markers( "MarkerLayer" );
    map.addLayer(markers);
     
     
    var marker = new Tmap.Marker(new Tmap.LonLat(lonlat.lon, lonlat.lat), icon);
    markers.addMarker(marker);
    
    alert(lonlat); 
} 
</script>
</head>
<body>
<div id="map_div"></div>
<div id = "menu1" style="text-decoration:none;"></div>
<br/>
<div id = "menu1" style="text-decoration:none;">
<br/>
<div>&nbsp;<a style="text-decoration:none;" href="javascript:selectMenu('1')"><font color="black">&nbsp;>>현재 위치 주변 poi 검색</font></a></div>
<input type="hidden" id="pixelX"/>
<input type="hidden" id="pixelY"/>
<br/>
</div>
<br/>
<div id="out"></div>
</body>
</html>