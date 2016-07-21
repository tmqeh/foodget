<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/common.jsp"%>
<body id="page-top">
<%@ include file="/common/header/header_other.jsp"%>
    
<!-- -------------------------------------------더미값 수정 작업 필요함---------------------------------------------- -->


<div id="map" style="width:100%;height:450px; position:relative;overflow:hidden;"></div>
<div class="storeinfo row">
	<div class="col-md-8 col-lg-offset-2">
    	<div class="row">
	    	<div class="storetitle col-md-8"> ${blogDto.store_name}</div>		        		
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
	        	<h5><span class="glyphicon glyphicon-map-marker"></span>&nbsp; ${blogDto.old_address}</h5>
				<h5><span class="glyphicon glyphicon-phone-alt"></span>&nbsp; ${blogDto.store_phone }</h5>
        	</div>      			
        </div>
		<div class="storescore">
	     	<div class="col-xs-2">랭킹점수<br><label>랭킹</label></div>
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
<c:forEach items="${blogRankList}" var="blogRankDto">	
			<div class="blogbox">
				<div class="col-sm-2 blogimgbox">
				<c:set var="size" value="${blogRankDto.blogImgInfoDto.imgSrcList.size()}"></c:set>
				<img src="${blogRankDto.blogImgInfoDto.imgSrcList.get(0)}" width="180" height="98">
				</div>
				<div class="col-sm-10" onclick="window.open('${blogRankDto.url}')" style="cursor:pointer">
					<h4>${blogRankDto.title}</h4>
					<h5>${blogRankDto.description}</h5>
					<h6>by ${blogRankDto.blog_writer_id}, ${blogRankDto.log_time.substring(0,10)}</h6>
				</div>
			</div>
</c:forEach>
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