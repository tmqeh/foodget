<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/common.jsp"%>
<%@ include file="/common/header/header_other.jsp"%>
<body style="background-color: #fbfbfb; margin-top: 101px">
<div class="infomenu-bar">
	<div class="col-md-6 col-md-offset-3">
		<ul class="col-md-2"><a>나의 맛집</a></ul>
		<ul class="col-md-2"><a>프로필</a></ul>
	</div>
</div>

<form name ="modifyform" method="post" action="" enctype="multipart/form-data">
<div class="col-md-4 col-md-offset-4 profilebox">
	<div class="profilebox-header">
		<h5>프로필</h5>
	</div>
	<div class="profileimgbox">
		<div class="profile row">
			<img src="${root}/picture/${userInfo.member_savefolder}/${userInfo.member_saveimg}" id="profileimg">
			<div>
				<label for="picture" class="labelicon"><span class="glyphicon glyphicon-camera" aria-hidden="true"></span></label>
			</div>
	      	<input type="file" accept="image/*" onchange="loadFile(event)" name="picture" id="picture" class="fileinput">
			<script>
			  var loadFile = function(event) {
			    var output = document.getElementById('profileimg');
			    output.src = URL.createObjectURL(event.target.files[0]);
			  };
			</script>
		</div>
	</div>
	<div class="row" style="border-top: 1px solid #dce0e0; padding-top: 30px;">
		<label class="col-sm-3">이메일</label>
		<div class="col-sm-9">
			<input type="text" class="col-md-10 col-md-offset-1 readonly" id="email" name="email" value="${userInfo.email}" readonly>
		</div>
	</div>
	<div class="row">
		<label class="col-sm-3">이름(별명)</label>
		<div class="col-sm-9">
			<input type="text" class="col-md-10 col-md-offset-1" id="name" name="name" value="${userInfo.name}">
		</div>
	</div>
	<div class="row">
		<label class="col-sm-3">비밀번호</label>
		<div class="col-sm-9">
			<input type="password" class="col-md-10 col-md-offset-1" id="password" name="password" value="${userInfo.password}">
		</div>
	</div>
</div>
</form>
<div class="col-md-4 col-md-offset-4">
	<div class="col-md-6 col-md-offset-3">
		<a class="btn btn-primary btn-profile" id="modify">저장하기</a>
	</div>
</div>

<script>
$(document).ready(function(){
    $("#modify").click(function(){
    	document.modifyform.action = root+"/member/modify.html";
		document.modifyform.submit();
    });
});
</script>
</body>
</html>