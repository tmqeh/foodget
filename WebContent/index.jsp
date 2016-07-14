<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/common.jsp"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script>
var loginFail = "${loginFail}";
</script>
<script src="${root}/js/member/member.js"></script>

<title>푸드득 FoodGet</title>

</head>

<body id="page-top">

    <nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top"><img src="${root}/img/title.png" style="height:35px"></a>
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
								<a href='#'>프로필 수정</a>
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

    <header>
    	<form id="storeinsertform" name ="storeinsertform" method="post" action="">
    		<input type="hidden" id="storeinfo"  name="storeinfo" value="">
			<input type="hidden" id="addresskeyword"  name="addresskeyword" value="">
	        <div class="header-content">
	            <div class="header-content-inner row">
	                <img src="${root}/img/logo-main.png"><hr>
					<div class="row">
						<div class="col-sm-2"></div>
		                <input type="text" id="keyword" class="search col-sm-5"  placeholder="예 : 구로디지털단지 김치찌개" value="">
		                <a id="searchbtn" class="search-btn col-sm-1"><img src="${root}/img/search.png" style="height:25px"></a>	                
		                <div class="col-sm-2 keyword-rank">실시간 검색어</div>
		                <div class="col-sm-1"></div>
	                </div>                  
	            </div>
	        </div>
        </form>
    </header>

    <section class="bg-primary" id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 text-center">
                    <h2 class="section-heading">We've got what you need!</h2>
                    <hr class="light">
                    <p class="text-faded">Start Bootstrap has everything you need to get your new website up and running in no time! All of the templates and themes on 
 are open source, free to download, and easy to use. No strings attached!</p>
                    <a href="#services" class="page-scroll btn btn-default btn-xl sr-button">Get Started!</a>
                </div>
            </div>
        </div>
    </section>

    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 text-center">
                    <h2 class="section-heading">Let's Get In Touch!</h2>
                    <hr class="primary">
                    <p>Ready to start your next project with us? That's great! Give us a call or send us an email and we will get back to you as soon as possible!</p>
                </div>
                <div class="col-lg-4 col-lg-offset-2 text-center">
                    <i class="fa fa-phone fa-3x sr-contact"></i>
                    <p>123-456-6789</p>
                </div>
                <div class="col-lg-4 text-center">
                    <i class="fa fa-envelope-o fa-3x sr-contact"></i>
                    <p><a href="mailto:your-email@your-domain.com">feedback@startbootstrap.com</a></p>
                </div>
            </div>
        </div>
        <div id="map" style="display:none;">
        </div>
    </section>
    
    
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
	      	<button type="button" class="btn btn-priamry" onclick="javascript:join();">회원가입</button>
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
	    <div class="modal-content">
	      <div class="modal-header">
	      	<div id="loginfail" class="logincheck"><span class="glyphicon glyphicon-exclamation-sign"></span> 아이디와 비밀번호를 확인해주세요.</div>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <button type="button" class="btn btn-priamry" data-dismiss="modal" style="background-color: #f9e000">카카오 계정으로 로그인</button>
	      </div>
	      <div class="modal-body row join-modal-body">
	        <input id="email_login" name="email" type="text" class="joinmodal-input" placeholder="이메일 주소" value="">
	        <input id="password_login" name="password" type="password" class="joinmodal-input" placeholder="비밀번호" value="">
	        <hr>
	      	<button type="button" class="btn btn-priamry" onclick="javascript:login();">로그인</button>
	      </div>
	      <div class="modal-footer row join-modal-body">
	      	<h4 class="col-xs-8">푸드득 계정이 없으세요?</h4>
	        <button type="button" class="col-xs-4 btn btn-modal-sub">회원가입</button>
	      </div>
	    </div>
	    </form>
	
	  </div>
	</div>
<script src="${root}/js/map/map.js"></script>	

</body>

</html>