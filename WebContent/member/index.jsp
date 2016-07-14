<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>푸드득 FoodGet</title>

    <!-- Bootstrap Core CSS -->
    <link href="${root}/css/bootstrap.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>

    <!-- Plugin CSS -->
    <link href="${root}/css/magnific-popup.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="${root}/css/creative.css" rel="stylesheet">
    <link href="${root}/css/custom.css" rel="stylesheet">

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

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <header>
        <div class="header-content">
            <div class="header-content-inner row">
                <img src="${root}/img/logo-main.png"><hr>
				<div class="row">
					<div class="col-sm-2"></div>
	                <input type="text" class="search col-sm-5"  placeholder="예 : 구로디지털단지 김치찌개">
	                <a class="search-btn col-sm-1"><img src="${root}/img/search.png" style="height:25px"></a>	                
	                <div class="col-sm-2 keyword-rank">실시간 검색어</div>
	                <div class="col-sm-1"></div>
                </div>                  
            </div>
        </div>
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
	        <input id=email name="email" type="text" class="joinmodal-input" placeholder="이메일 주소" value="">
	        <input id=name name="name" type="text" class="joinmodal-input" placeholder="이름(별명)" value="">
	        <input id=password name="password" type="password" class="joinmodal-input" placeholder="비밀번호" value="">
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
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <button type="button" class="btn btn-priamry" data-dismiss="modal" style="background-color: #f9e000">카카오 계정으로 로그인</button>
	      </div>
	      <div class="modal-body row join-modal-body">
	        <input type="text" class="joinmodal-input" placeholder="이메일 주소">
	        <input type="password" class="joinmodal-input" placeholder="비밀번호">
	        <hr>
	      	<button type="button" class="btn btn-priamry" data-dismiss="modal">로그인</button>
	      </div>
	      <div class="modal-footer row join-modal-body">
	      	<h4 class="col-xs-8">푸드득 계정이 없으세요?</h4>
	        <button type="button" class="col-xs-4 btn btn-modal-sub">회원가입</button>
	      </div>
	    </div>
	
	  </div>
	</div>
	
	<script>
	function join() {
		if(document.getElementById("email").value == "") {
			alert("이메일 입력!");
			return;
		} else if(document.getElementById("name").value == "") {
			alert("이름 입력!");
			return;
		} else if(document.getElementById("password").value  == "") {
			alert("비밀번호 입력!");
			return;
		}else {
		document.joinform.action = "${root}/member/member.html";
		document.joinform.submit();
		}
	}
	</script>

    <!-- jQuery -->
    <script src="${root}/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${root}/js/bootstrap.js"></script>

    <!-- Plugin JavaScript -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="${root}/js/scrollreveal.js"></script>
    <script src="${root}/js/jquery.magnific-popup.js"></script>

    <!-- Theme JavaScript -->
    <script src="${root}/js/creative.js"></script>

</body>

</html>