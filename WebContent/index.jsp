<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/common.jsp"%>

<script>
var loginFail = "${loginFail}";
</script>
<script src="${root}/js/member/member.js"></script>
<script type="text/javascript">
var checkFirst = false;
var lastkeyword = "";
var loopSendKeyoword = false;
var firstKey = "";

function startsearch() {
   if(document.getElementById("keyword").value == "") {
      checkFirst = false;
   }
   if(checkFirst == false) {
      firstKey = "first";
      loopSendKeyoword = true;
      window.setTimeout("sendkeyword();", 50);
   } else {
      firstKey = "";
   }
   checkFirst = true;
}

function sendkeyword() {
   if(loopSendKeyoword == false)
      return;
   
   var keyword = document.getElementById("keyword").value;
   if(keyword == "") {
      checkFirst = false;
      lastkeyword = "";
      $("#autosearch").hide();
	} else if(keyword != lastkeyword) {
      lastkeyword = keyword;
      if(lastkeyword != "") {
         $.ajax({
            type : 'POST',
            dataType : 'json',
            url : '${root}/store/autoSearch.html',
            data : {'first' : firstKey, 'keyword' : lastkeyword},
            success : function(data) {
               showKeyword(data);
            }
         });
      } else {
         $("#autosearch").hide();
      }
   }
   window.setTimeout("sendkeyword();", 50);
}

function showKeyword(data) {
   var len = data.keylist.length;
   var result = "";
   if(len > 0) {
      for(var i=0;i<len;i++) {
         result += "<a>" + data.keylist[i].keyword+ "</a><br>";
      }
      var sl = document.getElementById("searchList");
      sl.innerHTML = result;
      $("#autosearch").show();
   } else {
	  $("#autosearch").hide();
	}   
}
function selectkeyword(keyword){
    document.getElementById("keyword").value=keyword;
    checkFirst= false;
    loopSendKeyoword = false;
    hide("search");
}
function show(elementid) {
   var element = document.getElementById(elementid);
   if(element)
      element.style.display = "";
}
function hide(elementid) {
   var element = document.getElementById(elementid);
   if(element)
      element.style.display = "none";
    $("#autosearch").hide();
}
</script>

<body id="page-top">
<%@ include file="/common/header/header_main.jsp"%>
    <header>
    	<form id="storeinsertform" name ="storeinsertform" method="post" action="">
    		<input type="hidden" id="storeinfo"  name="storeinfo" value="">
	        <div class="header-content">
	            <div class="header-content-inner row">
	                <img src="${root}/img/logo-main.png"><hr>
					<div class="row searchbar">
						<div class="col-sm-1"></div>
						<div class="col-sm-7 row">
							<div class="col-xs-10 row">
			                	<input type="text" id="keyword" name="keyword" class="search"  placeholder="예 : 구로디지털단지 김치찌개" onkeydown="javascript:startsearch();" value="" >
				                <div class="autosearch" id="autosearch">
				                	<div id="searchList"></div>
				                </div>
			                </div>
			                <div class="col-xs-2">
			               		<a id="searchbtn" class="search-btn"><img src="${root}/img/search.png" style="height:25px"></a>
			               	</div>	                
			            </div>
			            
			            <div class="col-sm-3 row">
			                <div class="col-xs-12 keyword-rank">실시간 검색어</div>
		                </div>
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