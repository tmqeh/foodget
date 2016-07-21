<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/common.jsp"%>

<script>
var loginFail = "${loginFail}";
</script>
<script src="${root}/js/member/member.js"></script>

<body id="page-top">
<%@ include file="/common/header/header_main.jsp"%>
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
<script src="${root}/js/map/map.js"></script>	

</body>

</html>