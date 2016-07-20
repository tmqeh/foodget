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

