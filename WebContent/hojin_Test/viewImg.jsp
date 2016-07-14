<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.List"%>
 <% List<String> list = (List<String>)request.getAttribute("list"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
for(int i=0;i<list.size();i++){
	String src = list.get(i);
	%>
	<img src="<%=src%>" width="200" height="200">
	<%
	if(i%4==0){
		%>
		<br>
		<%
	}
}
%>

</body>
</html>