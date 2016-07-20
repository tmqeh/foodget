<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>    

<!-- ---------------------------------css 파일---------------------------------- -->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>푸드득 FoodGet</title>

	<!-- Bootstrap Core CSS -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link href="${root}/css/bootstrap.css" rel="stylesheet">
	
	<!-- Custom Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
	
	<!-- Plugin CSS -->
	<link href="${root }/css/jquery-ui.css" rel="stylesheet">
	
	<!-- Custom CSS -->
	<link href="${root}/css/creative.css" rel="stylesheet">
	<link href="${root}/css/custom.css" rel="stylesheet">
	
	<!-- Map CSS -->
	<link href="${root}/css/map/map.css" rel="stylesheet"> 
	
	
	<!-- ---------------------------------js 파일---------------------------------- -->
	
	<script>
	var root = "${root}";
	</script>
	
	<script src="${root}/js/jquery.js"></script>
	<script src="${root }/js/jquery-ui.js"></script>
	
	<!-- 다음지도 -->
	<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=0eb86b00e8e9aeddcb40e702ac62d099&libraries=services"></script>
	
	
	<!-- Bootstrap Core JavaScript -->
	<script src="${root}/js/bootstrap.js"></script>
	
	<!-- Plugin JavaScript -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="${root}/js/scrollreveal.js"></script>
	<script src="${root}/js/jquery.magnific-popup.js"></script>
	
	<!-- Theme JavaScript -->
	<script src="${root}/js/creative.js"></script>
	
	<!-- Kakao API -->
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<script src="/resource/Message.js"></script>
	<script src="/vassets/javascripts/demos_layout.js"></script>
</head>
