
var totalJson = "";
var cnt = 0;
var markers = [];
var listEl;

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new daum.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  
    

// 지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 



// 장소 검색 객체를 생성합니다
var ps = new daum.maps.services.Places();  
// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new daum.maps.InfoWindow({zIndex:1});

// 키워드로 장소를 검색합니다
$("#searchbtn").click(function(){
	searchPlaces();
});

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {

    var keyword = document.getElementById('keyword').value+" 맛집";

    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        return false;
    }
    
    if(listEl){
      	 removeAllChildNods(listEl);
      	 removeMarker();
      }

    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    for( var i=1;i<4;i++){
		var options = {
			page : i
	       };
		ps.keywordSearch( keyword, placesSearchCB, options);       
	}

}

function submitJson() {
	$("#storeinfo").val(totalJson);
	$("#addresskeyword").val($("#keyword").val());
    document.storeinsertform.action = root+"/store/storeinsert.html";
	document.storeinsertform.submit();
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(status, data, pagination) {
    if (status === daum.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data.places);
        //submitJson();
        // 페이지 번호를 표출합니다
        //displayPagination(pagination);
        cnt++;
                
        if(cnt==3){
        	submitJson();    	
        }
        
    } else if (status === daum.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === daum.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

    listEl = document.getElementById('placesList'), 
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(), 
    bounds = new daum.maps.LatLngBounds(), 
    listStr = '';
       
    // 검색 결과 목록에 추가된 항목들을 제거합니다
  //  removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    //removeMarker();
    
    for ( var i=0; i<places.length; i++ ) {

        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new daum.maps.LatLng(places[i].latitude, places[i].longitude);
            //marker = addMarker(placePosition, i), 
           // itemEl = getListItem(i, places[i], marker); // 검색 결과 항목 Element를 생성합니다

            makeStoreJson(places[i]);

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
       	// bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        /*
        (function(marker, title) {
            daum.maps.event.addListener(marker, 'mouseover', function() {
                displayInfowindow(marker, title);
            });

            daum.maps.event.addListener(marker, 'mouseout', function() {
                infowindow.close();
            });

            itemEl.onmouseover =  function () {
                displayInfowindow(marker, title);
            };

            itemEl.onmouseout =  function () {
                infowindow.close();
            };
        })(marker, places[i].title);

        fragment.appendChild(itemEl);*/

    }

    // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
   // listEl.appendChild(fragment);
    //menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    //map.setBounds(bounds);
}

function makeStoreJson(places) {
	var storeName = places.title;
	var storeAddress = places.address;
	var storePhone = places.phone;
	var storeLatitude = places.latitude;
	var storeLongitude= places.longitude;
	
	var storeJsonString = '{"storeName" :"' + storeName + '", "storeAddress" :"' + storeAddress + '", "storePhone" : "' + storePhone + '", "storeLatitude" : "' + storeLatitude + '","storeLongitude" : "' + storeLongitude + '" }';
	storeJson(storeJsonString);
}

function storeJson(storeJsonString) {
	totalJson += storeJsonString + "|";
}


// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

	var el = document.createElement('li'),
    itemStr = '<div id = "store_name" name="store_name">' + places.title + '</div>';


    if (places.newAddress) {
        itemStr += '<div id = "store_address" name="store_address">' + places.newAddress + '</div>';
    } else {
    	itemStr += '<div id = "store_address" name="store_address">' + places.address + '</div>';
    }
    itemStr += '<div id = "store_phone" name="store_phone">' + places.phone + '</div>';

    el.innerHTML = itemStr;
    el.className = 'item';
    

    return el;
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
	while (el.hasChildNodes()) {
		el.removeChild (el.lastChild);
	}
}
    
function addMarker(position, title) {
    var imageSrc = 'http://i1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new daum.maps.Size(36, 37),  // 마커 이미지의 크기
        markerImage = new daum.maps.MarkerImage(imageSrc, imageSize),
            marker = new daum.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage,
            title: title
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다
    map.setBounds(bounds);
    return marker;
}