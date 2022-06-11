<template>
  <v-app>
    <header-nav :searchActive="false"></header-nav>
    
    <v-container id="scroll">
      <v-col align="center" style="margin-top: 30vh">
        <v-img :src="require('@/assets/logo.png')" contain height="10vh"></v-img>
        <span style="color: #9DC3E6; font-style: italic"><strong>SSAFY</strong></span>
        <span style="color: white; font-style: italic">인이여,&nbsp;</span>
        <span style="color: #FF0066; font-style: italic"><strong>코딩</strong></span>
        <span style="color: white; font-style: italic">체력을 키워라!</span>
      </v-col>
      <v-col>
        <div class="d-flex justify-center">
          <div class="d-flex white rounded-pill mt-5 justify-center align-content-center" style="height: 60px; width: 50vw;">
            <v-text-field
              class="ml-5"
              label="지금 바로 운동을 검색하세요"
              hide-details="auto"
              v-model="searchText"
              @keydown.enter="search"
            ></v-text-field>
            <v-icon class="mx-3" @click="search">search</v-icon>
          </div>
        </div>
      </v-col>
    </v-container>
  </v-app>
</template>

<script>
import HeaderNav from '@/components/common/HeaderNav.vue';
export default {
  name: 'HomeView',
  components: {
    HeaderNav
  },
  data(){
    return {
      searchText: ''
    }
  },
  methods: {
    search() {
      const payload = {
        searchText: this.searchText
      }
      this.$store.dispatch("getVideos", payload)
      this.$router.push(`/search?q=${this.searchText}`);
    }
  }
}
</script>

<style scoped>
   #app {
    height: 100vh;
    overflow: hidden;
    margin:0;
    background-image: url("@/assets/background/background0.png");
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
  } 
</style>