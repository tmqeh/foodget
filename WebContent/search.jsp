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

    <title>푸드득 FoodGet</title>

</head>

<body id="page-top">

    <nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-primary">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header col-sm-3">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top"><img src="${root}/img/title.png" style="height:35px"></a>
            </div>
            
           	<div class="col-sm-6 searchbar">
		            <input id="keyword" type="text" class="search col-sm-10"  placeholder="예 : 구로디지털단지 김치찌개">
		            	<a id="searchbtn" class="search-btn col-sm-2"><img src="${root}/img/search.png" style="height:25px"></a>	                
			</div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse col-sm-3" id="bs-example-navbar-collapse-1">
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
    	<div id="map" style="width:100%;height:450px; position:relative;overflow:hidden;"></div>

	        <div class="option">
	        </div>
	        <hr>
	        <ul id="placesList"></ul>

	<!--<div id="scrollbtn" class="scrollbtn"></div> -->
	<div id="scrollbar">
	</div>
	<div class="store-bg-primary row">
		<div class="col-md-6 col-lg-offset-3 storepage">
<c:if test="${slist.size() != 0  }">
<c:forEach var="slist" items="${slist }">
			<div class="row storepage_sub">
				<div class="col-sm-4 storeimg_box">
					<img src="${root}/img/food1.JPG" class="storeimg">
				</div>
				<div class="col-sm-4">
					<a href="javascript:viewStore('${slist.store_seq }');"><h2>${slist.store_name }</h2></a>
					<h4>메뉴이름</h4><br>
					<h5>점수 | 좋아요 | 평가 | 블로그</h5>
				</div>
				<div class="col-sm-4 storepage_info">
					<h5><span class="glyphicon glyphicon-map-marker"></span>&nbsp; ${slist.store_address }</h5><br>
					<h5><span class="glyphicon glyphicon-phone-alt"></span>&nbsp; ${slist.store_phone }</h5><br>
				</div>
			</div>
</c:forEach>			
</c:if>
		</div>
	</div>
	<div>
	<form id="storeinsertform" name ="storeinsertform" method="post" action="">
		<input type="hidden" id="storeinfo"  name="storeinfo" value="">
		<input type="hidden" id="addresskeyword"  name="addresskeyword" value="">
	</form>
	
	<form id="storeInfoform" name ="storeInfoform" method="post" action="">
		<input type="hidden" id="store_seq"  name="store_seq" value="">
	</form>
	</div>
	

<script src="${root}/js/map/map.js"></script>

<script>
$(document).ready(function(){
});
  $( "#map" ).resizable({
    maxHeight: 900,
    minHeight: 150,
    minWidth: $(this).width,
    resize:function() {	    	
  		map.relayout(); 
  		
  		
  		
	    map.setBounds(bounds);
    }
  });
</script>

<script>
var plascPosition;
var marker;
var bounds = new daum.maps.LatLngBounds();
</script>

<c:forEach var="slist" items="${slist }">
<script>
    // 마커를 생성하고 지도에 표시합니다
    placePosition = new daum.maps.LatLng("${slist.store_latitude}", "${slist.store_longitude}");
    marker = addMarker(placePosition, "${slist.store_name}"); 
    bounds.extend(placePosition);
</script>
</c:forEach>

<script>
function viewStore(seq) {
	$("#store_seq").val(seq);
    document.storeInfoform.action = root+"/store/storeInfo.html";
	document.storeInfoform.submit();
}
</script>
</body>
</html>