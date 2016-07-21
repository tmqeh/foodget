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
<script language="javascript" src="https://apis.skplanetx.com/tmap/js?version=1&format=javascript&appKey=5064adfe-57cd-35d4-b5dd-3d46c557ad0e"></script>
<script>
//pr_3857 인스탄스 생성.
var pr_3857 = new Tmap.Projection("EPSG:3857");
//pr_4326 인스탄스 생성.
var pr_4326 = new Tmap.Projection("EPSG:4326");

function get3857LonLat(coordX, coordY){
    return new Tmap.LonLat(coordX, coordY).transform(pr_4326, pr_3857);
}
function initialize(){
	var chXY = get3857LonLat(126.96855314582992, 37.56256639417874);
	console.log(chXY);
}
window.onload = function() {
    initialize();
}
</script>
</head>
<body>
<form id="rootform" action="" method="POST" name="rootform">
<input type="hidden" id="endX" name="endX" value="14363856.085492350">
<input type="hidden" id="endY" name="endY" value="4178405.946508492">
<input type="hidden" id="startX" name="startX" value="14135591.321772">
<input type="hidden" id="startY" name="startY" value="4518111.822511">

<input type="button" onclick="javascript:hojin();" value="!!!!!!!!!!!!">

</form>
<br/>
<br/>
</body>
</html>