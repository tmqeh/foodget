<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">


    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand page-scroll" href="${root }/index.jsp"><img src="${root}/img/title.png" style="height:35px"></a>
        </div>

        <!-- 비 로그인 -->
        <c:if test="${userInfo == null }">            
        <div class="collapse navbar-collapse menu" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a class="page-scroll" href="#about">푸드득 소개</a>
                </li>
                <li>
                    <a class="page-scroll" href="#services" data-toggle="modal" data-target="#joinModal">회원가입</a>
                </li>
                <li>
                    <a class="page-scroll" href="#portfolio" data-toggle="modal" data-target="#loginModal">로그인</a>
                </li>
            </ul>
        </div>            
        </c:if>
        <!-- /.비 로그인 -->
        
        <!-- 로그인 -->
        <c:if test="${userInfo != null }">            
        <div class="collapse navbar-collapse menu" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a class="page-scroll" href="#about">푸드득 소개</a>
                </li>
                <li>
                    <a class="page-scroll" href="#services" data-toggle="modal" data-target="#joinModal">맛집 추천</a>
                </li>
                <li>
                    <a class="page-scroll" href="#portfolio" data-toggle="modal" data-target="#loginModal">${userInfo.name}</a>
                    <ul>
                    	<li class='sub'>
				<a href='#'>나의 맛집</a>
			</li>
			<li class='sub'>
				<a href='${root }/member/memberInfo.jsp'>프로필 수정</a>
			</li>
			<li class='sub'>
				<a href='${root }/member/logout.html'>로그아웃</a>
			</li>
		</ul>
                </li>
            </ul>
        </div>            
        </c:if>
        <!-- /.로그인 -->
    </div>
    
    
    <!-- /.container-fluid -->
</nav>

     
    <!-- 회원 가입 Modal -->
	<div id="joinModal" class="modal fade" role="dialog">
	  <div class="modal-dialog join-dialog">
	
	    <!-- Modal content-->
	    <form name ="joinform" method="post" action="">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title"><a>카카오</a>로 회원 가입하세요</h4>
	      </div>
	      <div class="modal-body row join-modal-body">
	      	<div id = "emailEmpty" class="idcheck">이메일을 입력하세요.</div>
	      	<div id = "emailFormCheck" class="idcheck">이메일형식이 올바르지 않습니다.</div>
	      	<div id = "emailCheck "class="idcheck">중복된 이메일입니다.</div>
	        <input id="email" name="email" type="text" class="joinmodal-input" placeholder="이메일 주소" value="" onkeyup="javascript:emailcheck();">
	        <div id = nameEmpty class="idcheck">이름을 입력하세요.</div>
	        <input id="name" name="name" type="text" class="joinmodal-input" placeholder="이름(별명)" value="" onkeyup="javascript:namecheck();">
	        <div id = pwEmpty class="idcheck">비밀번호를 입력하세요.</div>
	        <div id = pwCheck class="idcheck">너무 짧은 비밀번호입니다.</div>
	        <input id="password" name="password" type="password" class="joinmodal-input" placeholder="비밀번호" value="" onkeyup="javascript:pwcheck();">
	        <hr>
	      	<h5>가입과 함께 푸드득의 서비스약관과 개인정보수집이용에 동의하시게 됩니다.</h5>
	      	<button type="button" class="btn btn-primary" onclick="javascript:join();">회원가입</button>
	      </div>
	      <div class="modal-footer row join-modal-body">
	      	<h4 class="col-xs-8">이미 푸드득 회원이신가요?</h4>
	        <button type="button" class="col-xs-4 btn btn-modal-sub" data-dismiss="modal" >로그인</button>
	      </div>
	    </div>
	    </form>
	
	  </div>
	</div>
	
	<!-- 로그인 Modal -->
	<div id="loginModal" class="modal fade" role="dialog">
	  <div class="modal-dialog join-dialog">
	
	    <!-- Modal content-->
	    <form name ="loginform" method="post" action="">
	    <input type="hidden" name="kakaoJson" id="kakaoJson" value="">
	    <input type="hidden" name="kakaoflag" id="kakaoflag" value="">
	    <div class="modal-content">
	      <div class="modal-header">
	      	<div id="loginfail" class="logincheck"><span class="glyphicon glyphicon-exclamation-sign"></span> 아이디와 비밀번호를 확인해주세요.</div>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <a  id="kakao-btn" class="btn btn-primary" style="background-color: #ffeb00; color: #3c1e1e;">카카오 계정으로 로그인</a>
	      </div>
	      <div class="modal-body row join-modal-body">
	        <input id="email_login" name="email" type="text" class="joinmodal-input" placeholder="이메일 주소" value="">
	        <input id="password_login" name="password" type="password" class="joinmodal-input" placeholder="비밀번호" value="">
	        <hr>
	      	<button type="button" class="btn btn-primary" onclick="javascript:login();">로그인</button>
	      </div>
	      <div class="modal-footer row join-modal-body">
	      	<h4 class="col-xs-8">푸드득 계정이 없으세요?</h4>
	        <button type="button" class="col-xs-4 btn btn-modal-sub">회원가입</button>
	      </div>
	    </div>
	    </form>
	
	  </div>
	</div>
<script src="${root}/js/member/kakaoAPI.js"></script>
