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
    
    <!-- -------------------------------------------더미값 수정 작업 필요함---------------------------------------------- -->
    	<div id="map" style="width:100%;height:450px; position:relative;overflow:hidden;"></div>
		<div class="storeinfo row">
        	<div class="col-md-8 col-lg-offset-2">
        		<div class="row">
	        		<div class="storetitle col-md-8"> ${storeInfo.store_name }
	        		</div>		        		
	        		<div class="storetitle col-md-4">
	        			<a><span class="glyphicon glyphicon-star"></span>&nbsp; 비교하기</a>
	        			<a><span class="glyphicon glyphicon-heart"></span>&nbsp; 좋아요</a>
	        		</div>
        		</div>
        		<div class="row">
        			<div class="col-md-6 storedetail">
        				<h5><span class="glyphicon glyphicon-map-marker"></span>&nbsp; 카테고리</h5>
						<h5><span class="glyphicon glyphicon-phone-alt"></span>&nbsp; 메뉴</h5>
        			</div>
        			<div class="col-md-6 storedetail">
	        			<h5><span class="glyphicon glyphicon-map-marker"></span>&nbsp; ${storeInfo.store_address}</h5>
						<h5><span class="glyphicon glyphicon-phone-alt"></span>&nbsp; ${storeInfo.store_phone }</h5>
        			</div>      			
        		</div>
				<div class="storescore">
	        		<div class="col-xs-2">1<br><label>랭크</label></div>
	        		<div class="col-xs-2">79<br><label>점수</label></div>
	        		<div class="col-xs-2">65<br><label>좋아요</label></div>
	        		<div class="col-xs-2">4.7<br><label>별점</label></div>
	        		<div class="col-xs-2">718<br><label>블로그</label></div>
				</div>	        					        		
        	</div>
       	</div>	        
       	
	<div class="store-bg-primary row">
		<div class="col-md-8 col-lg-offset-2 storepage">
			<div class="row">
				<div class="storeimgbox"></div>
				<div class="storeimgbox"></div>
				<div class="storeimgbox"></div>
				<div class="storeimgbox"></div>			
			</div>
			<div class="storeblog row">
				<h2>추천 블로그</h2>
				
				<div class="blogbox">
					<div class="col-sm-2 blogimgbox"></div>
					<div class="col-sm-10">
						<h4>블로그제목</h4>
						<h5>블로그내용</h5>
						<h6>by 닉네임 2016-06-16</h6>
					</div>
				</div>
			</div>
		</div>
<!-- -------------------------------------------더미값 수정 작업 필요함---------------------------------------------- -->
	</div>
	<div>
	<form id="storeinsertform" name ="storeinsertform" method="post" action="">
		<input type="hidden" id="storeinfo"  name="storeinfo" value="">
		<input type="hidden" id="addresskeyword"  name="addresskeyword" value="">
	</form>
	</div>
	
<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=0eb86b00e8e9aeddcb40e702ac62d099&libraries=services"></script>

<script>
$(document).ready(function(){
});
  $( "#map" ).resizable({
    maxHeight: 900,
    minHeight: 150,
    minWidth: $(this).width,
    resize:function() {	    	
  		map.relayout(); 
  		setBounds();
    }
  });
</script>

<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = { 
    center: new daum.maps.LatLng("${storeInfo.store_latitude}", "${storeInfo.store_longitude}"), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};

var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

//마커가 표시될 위치입니다 
var bounds = new daum.maps.LatLngBounds();
var markerPosition  = new daum.maps.LatLng("${storeInfo.store_latitude}", "${storeInfo.store_longitude}"); 
bounds.extend(markerPosition);
//마커를 생성합니다
var marker = new daum.maps.Marker({
position: markerPosition
});

//마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

function setBounds() {
    // LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
    // 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
    map.setBounds(bounds);
}
</script>

</body>

</html>