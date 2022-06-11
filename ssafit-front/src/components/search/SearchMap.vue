<template>
  <div>
    <div class="searchbox">
      <div><input type="text" @keyup.enter="searchPlace" placeholder="찾고 싶은 동네를 검색하세요" style="background-color:white; padding: 10px;"></div>
      <!-- <div><v-text-field label="Search" placeholder="EX) XX시 XX동 헬스장" outlined @keyup.enter="searchPlace"></v-text-field></div> -->
      <div class="results">
        <div class="place" v-for="rs in search.results" :key="rs.id" @click="showPlace(rs)">
          <h4>{{rs.place_name}}</h4>
          <div class="addr">{{rs.road_address_name}}</div>
        </div>
      </div>
    </div>
    <div ref="map" id="map"></div>
    <div class="controll">
        <button @click="zoom(-1)"><v-icon style="color: white; font-size: 50px">zoom_in</v-icon></button>
        <button @click="zoom(1)"><v-icon style="color: white; font-size: 50px">zoom_out</v-icon></button>
    </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
            map: null,
            mapOption: {
              center: {
                lat: 36.3553675622378,
                lng: 127.298408300646,
              },
              level: 3,
            },
            markers: [],
            search: {
              keyword: null,
              pgn: null,
              results: [],
            }
        }
    },
    mounted() {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_MAP_KEY}&libraries=services`

      /* global kakao */
      script.addEventListener("load", () => {
          kakao.maps.load(this.initMap);
      })

      document.head.appendChild(script);
    },
    methods: {
        initMap() {
            var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
            var options = { //지도를 생성할 때 필요한 기본 옵션
                center: new kakao.maps.LatLng(this.mapOption.center.lat, this.mapOption.center.lng), //지도의 중심좌표.
                level: this.level //지도의 레벨(확대, 축소 정도)
            };

            this.map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
        },
        zoom(delta) {
          // delta : 1 or -1
          const level = Math.max(3, this.mapOption.level + delta); // min level 3
          this.mapOption.level = level;
        },
        searchPlace(e) {
          const keyword = e.target.value.trim();
          if(keyword.length === 0) {
            return;
          }

          // 장소 검색 객체를 생성합니다
          const ps = new kakao.maps.services.Places(); 

          // 키워드로 장소를 검색합니다
          ps.keywordSearch(keyword, (data, status, pgn) => {
            this.search.keyword = keyword;
            status;
            this.search.pgn = pgn;
            this.search.results = data;
            console.log(data);

            for(let i = 0; i < this.markers.length; i++) {
              this.markers[i].setMap(null);
            }

            this.markers.splice(0);
            for(let i = 0; i < data.length; i++) {
              const marker = new kakao.maps.Marker({
                  position: new kakao.maps.LatLng(data[i].y, data[i].x)
              });

              // marker.setMap(this.map);
              this.markers.push(marker);
              this.mapOption.center = {
                lat: data[0].y,
                lng: data[0].x,
              }
            }
            this.setMarkers(this.map);
          });
        },
        setMarkers(map) {
          for(let i = 0; i < this.markers.length; i++) {
            this.markers[i].setMap(map);
          }
        },
        showPlace(place) {
          console.log(place);
          this.mapOption.center = {
            lat: place.y,
            lng: place.x,
          }
        }
    },
    watch: {
      'mapOption.level'(cur, prev) {
        console.log(`${prev} => ${cur}`)
        this.map.setLevel(cur);
      },
      'mapOption.center'(cur, prev) {
        console.log(`${prev.lng} => ${cur.lng}, ${prev.lat} => ${cur.lat}`)
        this.map.setCenter(new kakao.maps.LatLng(cur.lat, cur.lng));

        // const marker = new kakao.maps.Marker({
        //     position: new kakao.maps.LatLng(cur.lat, cur.lng)
        // });

        // marker.setMap(this.map);
      },
    }
}
</script>

<style>
#map {
    width: 92vw;
    height: 82vh;
    
}
.controll {
    display: flex;
    justify-content: flex-end;
    align-items: flex-end;
    z-index: 100;
}
input {
  width: 18vw;
  height: 50px;
  border-radius: 5px;
  background-color: aliceblue;
}
.searchbox {
  position: absolute;
  width: 18vw;
  height: 82vh;
  z-index: 10;
  background-color: #ffffffaa;
  display: flex;
  flex-direction: column;
}
.results {
  flex: 1 1 auto;
  overflow-y: auto;
  color: black;
}
.place {
  padding: 8px;
  cursor: pointer;
}
.place:hover {
  background-color: aliceblue;
}
</style>