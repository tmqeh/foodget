<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
function test(){
	document.hojin.action = "${root}/store/bloglist.html";
	document.hojin.submit();
}
function viewImg(){
	document.hojin.action = "${root}/store/viewImg.html";
	document.hojin.submit();
}
</script>
<body>
<form id="hojin" name="hojin" action="" method="POST">
<input type="text" id="blogSearch" name="blogSearch" value="">
<input type="button" onclick="javascript:test();" width="100" value="블로그 보기">

<input type="text" id="blogSrc" name="blogSrc" >
<input type="button" onclick="javascript:viewImg();" width="100" value="사진 보기">

</form>
</body>

</html>