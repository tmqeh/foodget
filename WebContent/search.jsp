<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <style>
      body {
        margin: 0px;
        padding: 0px;
      }
      .jbTitle {
        text-align: center;
      }
      .jbMenu {
        text-align: center;
  
        padding: 10px 0px;
        width: 100%;
      }
      .jbContent {
        height: 2000px;
      }
      .jbFixed {
        position: fixed;
        top: 0px;
      }
    .modal-dialog.modal-fullsize {
  width: 80%;
  height: auto;
  margin: 0;
  padding: 0;
}
.modal-content.modal-fullsize {
  height: auto;
  min-height: 100%;
  border-radius: 0; 
}
    </style>


<html>
<head>
<script src="${root}/js/api/Tmap.js"></script>
<script language="javascript" src="https://apis.skplanetx.com/tmap/js?version=1&format=javascript&appKey=5064adfe-57cd-35d4-b5dd-3d46c557ad0e"></script>
<script>
var markers;
var map;
var kmlLayer;
var kmlForm;
var marker;
var size;
var offset;
var icon;
//pr_3857 인스탄스 생성.
var pr_3857 = new Tmap.Projection("EPSG:3857");
//pr_4326 인스탄스 생성.
var pr_4326 = new Tmap.Projection("EPSG:4326");
function get3857LonLat(coordX, coordY){
    return new Tmap.LonLat(coordX, coordY).transform(pr_4326, pr_3857);
}
function initialize(){
	myX='14363856.085492350';
	myY='4178405.946508492';
	map = new Tmap.Map({div:'map_div', width:'100%',height:'400px'});
	map.setCenter(new Tmap.LonLat(myX, myY),15);

	var lonlat = new Tmap.LonLat(myX, myY);
    markers = new Tmap.Layer.Markers( "MarkerLayer" );
    map.addLayer(markers);
    size = new Tmap.Size(24,38);
    offset = new Tmap.Pixel(-(size.w/2), -size.h);
    icon = new Tmap.Icon('https://developers.skplanetx.com/upload/tmap/marker/pin_b_m_a.png', size, offset);  
	marker = new Tmap.Marker(lonlat, icon);
	markers.addMarker(marker);
	map.events.register("click", map, onClickMap); 
	
}
window.onload = function() {
    initialize();
}
function onClickMap(e){ 
	if(markers !=null){
		markers.destroy();
	}
    var lonlat = map.getLonLatFromViewPortPx(e.xy); 
    markers = new Tmap.Layer.Markers( "MarkerLayer" );
    map.addLayer(markers);
     
    var marker = new Tmap.Marker(new Tmap.LonLat(lonlat.lon, lonlat.lat), icon);
    markers.addMarker(marker);
    
    alert(lonlat); 
} 
</script>
<%@ include file="/common/common.jsp"%>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>푸드득 FoodGet</title>

</head>	

<body id="page-top">

<form id="getrootform" name="getrootform">
<input type="hidden" id="apikey" name="apikey">
<input type="hidden" id="q" name="q">
<input type="hidden" id="output" name="output">
</form>

<!--  여기는 장바구니 넣기전에 지도나오는부분 시작 -->


	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-fullsize">
	    <div class="modal-content modal-fullsize">
	      <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
		<h4 class="modal-title" id="exampleModalLabel">New message</h4>
	      </div>

	      <div class="modal-body">	      
		<form role="form">
		
		<div class="modal-body" style="border:1px solid;" id="map_div">
		
		</div>
		
		
				     	<div class="form-group"> 

							<input type="button" onclick="sample2_execDaumPostcode()" value="우편번호 찾기"><br>
							<label for="recipient-name" class="control-label">내 위치 :</label>
							<input type="text" id="sample2_address" size="40px" placeholder="한글주소">
							<input type="hidden" id="sample2_addressEnglish" placeholder="영문주소">
							<input type="hidden" id="sample2_postcode" placeholder="우편번호">
							<input type="hidden" class="modal-latitude" id="latitude">
        					<input type="hidden" class="modal-longitude" id="longitude">
        					<input type="hidden" class="modal-img" id="img">
        					<input type="hidden" class="modal-distance" id="distance">

								<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
								<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
								<img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
								</div>
												
				</div>
		  <div class="form-group">
		    <label for="recipient-name" class="control-label">가게이름 :</label>
		    <input type="text" class="modal-name" size="40" id="name">
		  </div>
		  <div class="form-group">
		    <label for="recipient-name" class="control-label">가게주소 :</label>
		    <input type="text" class="modal-address" size="40" id="address">
		  </div>
		  <div class="form-group">
		    <label for="recipient-name" class="control-label">가게연락처 :</label>
		    <input type="text" class="modal-phone" size="40" id="phone">
		  </div>
		   <div class="form-group">
		    <label for="recipient-name" class="control-label">거리 :</label>
		    <input type="text" class="modal-distance" size="40" id="distance">
		  </div>
		  <div class="form-group">
		    <label for="recipient-name" class="control-label">좌표 :</label>
		    <input type="text" class="modal-latitude" size="40" id="latitude">
		  </div>
		</form>
	      </div>
	      <div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		<button type="button" class="btn btn-danger" data-dismiss="modal" onclick="javascript:gocart();">장바구니에 담기</button>
	      </div>
	    </div>
	  </div>
	</div>	
<!--  여기는 장바구니 넣기전에 지도나오는부분 끝 -->




<%@ include file="/common/header/header_other.jsp"%>



<div id="map" style="width:100%;height:450px; position:relative;overflow:hidden;"></div>


<div class="option"></div>

<div class="jbMenu">
			<div>
				<div id="divTest"></div>					
				<input type="button" value="장바구니비우기" onclick="deletecookie();">
				<input type="button" value="카운터" onclick="cntview();">
			</div>

</div>

<ul id="placesList"></ul>

<!--<div id="scrollbtn" class="scrollbtn"></div> -->
<div id="scrollbar"></div>
<div class="store-bg-primary row">




	<div class="col-md-6 col-lg-offset-3 storepage">
	
	
	
		<c:if test="${slist.size() != 0  }">
		
		
			<c:forEach var="slist" items="${slist }">
				<div class="row storepage_sub">
					<div class="col-sm-4 storeimg_box">
					

	<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal" data-name="${slist.store_name }" data-address="${slist.store_address }" data-phone="${slist.store_phone}"data-latitude="${slist.store_latitude}" data-longitude="${slist.store_longitude}">장바구니</button>
		
<c:if test="${slist.store_img == null}">			
						<img src="${root}/img/food1.JPG" class="storeimg">
</c:if>
<c:if test="${slist.store_img != null}">					
						<img src=${slist.store_img } class="storeimg">
</c:if>						
					</div>
					<div class="col-sm-4">
						<a href="javascript:viewStore('${slist.store_seq }');"><h2>${slist.store_name }</h2></a>
						<h4>${slist.store_category}</h4><br>
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

<script src="${root}/js/jquery.cookie.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
//hojin
function getRoot(myLocation,endX,endY){
    $.ajax({
    	type : "post",
        url:root+'/store/getroot.html?apikey=57a86a7ab2ca08b43de7cb6d40fdd2ca&q='+myLocation+'&output=json',
        success:function(data){
        	var json = JSON.parse(data);
        	var starXY  = get3857LonLat(json.point_x,json.point_y);
        	$.ajax({
            	type : "post",
                url:root+'/store/tmapdistance.html?endX='+endX+'&endY='+endY+'&startX='+starXY.lon+'&startY='+starXY.lat+'',
                success:function(data){
                	document.getElementById('distance').value = data;
                }
            })
        }
    })
//	alert(myLocation);
}
    // 우편번호 찾기 화면을 넣을 element
    var element_layer = document.getElementById('layer');
    function closeDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_layer.style.display = 'none';
    }
    function sample2_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample2_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample2_address').value = fullAddr;
            	//이호진 작업 시작
            	var endXY  = get3857LonLat(document.getElementById('longitude').value ,document.getElementById('latitude').value );
            	getRoot(fullAddr,endXY.lon,endXY.lat);
            	
            	
                document.getElementById('sample2_addressEnglish').value = data.addressEnglish;
                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_layer.style.display = 'none';
            },
            width : '100%',
            height : '100%'
        }).embed(element_layer);

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        initLayerPosition();
    }

    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
    function initLayerPosition(){
        var width = 300; //우편번호서비스가 들어갈 element의 width
        var height = 460; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 5; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
    }
</script>

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
      $( document ).ready( function() {
        var jbOffset = $( '.jbMenu' ).offset();
        $( window ).scroll( function() {
          if ( $( document ).scrollTop() > jbOffset.top ) {
            $( '.jbMenu' ).addClass( 'jbFixed' );
          }
          else {
            $( '.jbMenu' ).removeClass( 'jbFixed' );
          }
        });
      } );
    </script>


<script>
var plascPosition;
var marker;
var bounds = new daum.maps.LatLngBounds();
</script>



<c:forEach var="slist" items="${slist }">
<script>
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


<!--  여기 임시 장바구니 토글창  -->

<script>
$(document).ready(function() {
	$('#exampleModal').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget); // Button that triggered the modal
		  var name = button.data('name'); // Extract info from data-* attributes
		  var address = button.data('address');
		  var phone = button.data('phone');
		  var modal = $(this);
		  var latitude = button.data('latitude');
	      var longitude = button.data('longitude');
	      var img = button.data('img');
	        
		  modal.find('.modal-title').text('자신의 위치를 정해주세요');
		  modal.find('.modal-name').val(name);
		  modal.find('.modal-address').val(address);
		  modal.find('.modal-phone').val(phone);
		  modal.find('.modal-latitude').val(latitude);
	      modal.find('.modal-longitude').val(longitude);
	      modal.find('.modal-img').val(img);
		  
	});
});

</script>




<script>
var cookiecnt=2;
    // html dom 이 다 로딩된 후 실행된다.
    $(document).ready(function(){
    	
    	if($.cookie('count')){
    		now = $.cookie('count');
    		cookiecnt=now;
    		innerHTML_add();
    	}else{
    		cookiecnt=2;
    	}
        // memu 클래스 바로 하위에 있는 a 태그를 클릭했을때
        $(".menu>a").click(function(){
            // 현재 클릭한 태그가 a 이기 때문에
            // a 옆의 태그중 ul 태그에 hide 클래스 태그를 넣던지 빼던지 한다.
            $(this).next("ul").toggleClass("hide");
        });
    });
    
    var strHTML = "";
    var count = 0;
    
    
    function innerHTML_add(){
    	
    	if(cookiecnt<3){
    	strHTML="";
    	}
    	else if(cookiecnt>=3){
    	
		strHTML = "";
		strHTML = "<center>";
        strHTML += "<table border='5' cellpadding='0'>";
        //strHTML += "<caption> 테스트" + count + "</caption>";
        //strHTML += "<tr><td colspan='2'></td></tr>";
        strHTML += "<tr bgcolor='#E2E2E2' align='center'>";
        strHTML += "<td style='width:25%' >사진</td>";
        strHTML += "<td style='width:25%' >상호명</td>";
        strHTML += "<td style='width:25%'>주소</td>";
        strHTML += "<td style='width:25%'>연락처</td>";
        strHTML += "<td style='width:25%'>거리</td>";
        strHTML += "</tr>";

        for(var j=2;j<= 50; j++){
        	var cntstr="\'"+j+"\'";
        	
        	
        	var cart_final = JSON.parse($.cookie(cntstr));
        	
        	if(cart_final){
            strHTML += "<tr align='center'>";
            //strHTML += "<td> <img src =" + cart_final.name + "> </td>";
            strHTML += "<td>" + cart_final.name + "</td>";
            strHTML += "<td>" + cart_final.address + "</td>";
            strHTML += "<td>" + cart_final.phone +"</td>";
            strHTML += "<td>여기다거리값넣기</td>";
            strHTML += "</tr>";
      	  }
        }

        strHTML += " </table>";
        strHTML += " </center>";
        
    	}
        console.log(strHTML);
        
        document.getElementById("divTest").innerHTML = strHTML;
        count++;
    }


    function deletecookie() {

	
	for(var i=2;i<= 50; i++){
    	var cntstr="\'"+i+"\'";
		$.cookie(cntstr, null, { path: '/' });
		cookiecnt=2;
	}
	
	innerHTML_add();
	
}


function gocart(){
	 var name=document.getElementById('name').value;
	 var address=document.getElementById('address').value;
	 var phone=document.getElementById('phone').value;
	 var img=document.getElementById('img').value;
	 var distance=document.getElementById('distance').value;

	
	 	
		var contact = new Object();
		contact.name = name;
		contact.address = address;
		contact.phone = phone;
		contact.img = img;
		contact.distance = distance;
		
		var jsonText = JSON.stringify(contact);
		
		var cntstr="\'"+cookiecnt+"\'";
		$.cookie(cntstr, jsonText, {expires:1, path:'/'});
		
		cookiecnt=Number(cookiecnt);
	 	cookiecnt=cookiecnt+1
	 	
	 	$.cookie('count',cookiecnt, {expires:1, path:'/'});
		
	 	console.log(jsonText);
	 	innerHTML_add();
	 	//alert(jsonText);
}

function cntview(){
	alert(cookiecnt);
	
}
</script>
<!--  여기 임시 장바구니 토글창 끝 -->

</body>
</html>