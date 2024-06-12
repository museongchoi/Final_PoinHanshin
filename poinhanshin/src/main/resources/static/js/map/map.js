    var mapContainer = document.getElementById('map');
    var mapOption = {
    center: new kakao.maps.LatLng(37.5665, 126.9780), // 지도 중심 좌표 (서울)
    level: 4 // 지도 확대 레벨
};

    // 지도 생성
    var map = new kakao.maps.Map(mapContainer, mapOption);

    var placesList = document.getElementById('placesList');
    var addPlaceButton = document.getElementById('addPlaceButton');
    var newPlaceName = document.getElementById('newPlaceName');
    var newPlaceLat = document.getElementById('newPlaceLat');
    var newPlaceLng = document.getElementById('newPlaceLng');

    // 팝업 디자인을 위한 임시코드 --------------------------------
    var places = [
    {
        name: '강아지',
        latlng: new kakao.maps.LatLng(37.5665, 126.9780), // 예시 좌표


    },
    {
        name: '레스토랑 B',
        latlng: new kakao.maps.LatLng(37.5670, 126.9790), // 예시 좌표
        description: '레스토랑 B는 현지 식재료를 사용한 다양한 요리를 제공합니다.'
    },
    // 추가적인 장소 정보 추가
    ];

    // 마커와 팝업 생성
    function createMarkerAndPopup(place) {
    var marker = new kakao.maps.Marker({
    map: map,
    position: place.latlng,
    title: place.name
});

    var infowindow = new kakao.maps.InfoWindow({
    content: `
                <div class="popupbox"">
                <div style="display: flex; flex-direction: row;  justify-content: space-between;">
                  <h5>품종: ${place.name}</h5>
                  <button class="icon-btn" onclick="location.href='findmaplistdetail.html'" title="이동">
                   <i class="fa-solid fa-chevron-right fa-2xl" style="color: #7605ff;"></i>
                  </button>
                  </div>
                  <img src="image_url_here" alt="${place.name}" style="max-width: 100%;">
                   <span class="address">실종/발견 장소: </span>
                    <span class="datetime" >실종/발견 날짜 및 시간: </span>
                      <span class="mapboard_reg_date">작성일: </span>
              </div>
            `
});

    kakao.maps.event.addListener(marker, 'click', function() {
    infowindow.open(map, marker);
});

    kakao.maps.event.addListener(map, 'click', function() {
    infowindow.close();
});

    return marker;
}

    // 마커 생성
    function renderPlaces() {
    placesList.innerHTML = '';
    for (var i = 0; i < places.length; i++) {
    var listItem = document.createElement('li');
    listItem.textContent = places[i].name;
    listItem.addEventListener('click', function() {
    var index = Array.from(placesList.children).indexOf(this);
    map.setCenter(places[index].latlng);
});
    placesList.appendChild(listItem);

    createMarkerAndPopup(places[i]);
}
}

    // 초기 렌더링
    renderPlaces();
    // 팝업 디자인을 위한 임시코드 -------------------------------- 끝

