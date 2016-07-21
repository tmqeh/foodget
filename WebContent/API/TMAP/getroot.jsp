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
<script src="${root}/js/api/Tmap.js"></script>
<!-- <script src="${root}/js/api/GPS.js"></script>  -->
<script language="javascript" src="https://apis.skplanetx.com/tmap/js?version=1&format=javascript&appKey=5064adfe-57cd-35d4-b5dd-3d46c557ad0e"></script>
<script>
var chXY;
var myX;
var myY;
var options = 
{
  enableHighAccuracy: true,
  timeout: 5000,
  maximumAge: 0
};

function success(pos) {
  var crd = pos.coords;
  chXY = get3857LonLat(crd.longitude,crd.latitude);
  initialize(chXY.lon,chXY.lat);
};
function error(err) {
  console.warn('ERROR(' + err.code + '): ' + err.message);
};
$(document).ready(function(){
 	navigator.geolocation.getCurrentPosition(success, error, options);
});
</script>
</head>
<body>
<form id="rootform" action="" method="POST" name="rootform">
</form>
<div id="map_div"></div>
<div id = "menu1" style="text-decoration:none;"></div>
<br/>
<div>&nbsp;<a style="text-decoration:none;" href="javascript:getRouteData(chXY.lon,chXY.lat,'14363856.085492350','4178405.946508492');"><font color="black">&nbsp;경로 탐색</font></a></div>
<input type="hidden" id="pixelX"/>
<input type="hidden" id="pixelY"/>
<br/>
<div id="out"></div>
</body>
</html>