<template>
  <v-app-bar 
    app
    clipped-left
    absolute
    color="black"
    elevate-on-scroll
    elevation="24"
    scroll-target="#scroll"
  >
    <router-link v-if="logoActive" to="/">
      <img :src="require('@/assets/logo.png')" alt="logo.png" style="height: 25px"/>
    </router-link>
    <v-spacer></v-spacer>

    <div v-if="searchActive" class="d-flex justify-center" style="width: 50vw">
      <div class="d-flex flex-fill white rounded-pill justify-center align-content-center" style="height: 40px;">
        <v-text-field
          class="ml-5"
          hide-details="auto"
          v-model="searchText"
          @keydown.enter="search"
        ></v-text-field>
        <v-icon class="mx-3" @click="search">search</v-icon>
      </div>
    </div>
    <v-spacer></v-spacer>

    <div v-if="buttonActive">
      <v-btn icon v-if="isLogin" @click="logOut">
        <v-icon class="white--text">power_settings_new</v-icon>
      </v-btn>
      <v-btn icon v-else link :to="{ path: '/account/login' }">
        <v-icon class="white--text">login</v-icon>
      </v-btn>
    </div>
  </v-app-bar>
</template>

<script>
import { mapState, mapMutations } from "vuex";

export default {
    name: 'HeaderNav',
    data() {
      return {
        searchText: ''
      }
    },
    props: {
      logoActive: {
        type: Boolean,
        default: true
      },
      searchActive: {
        type: Boolean,
        default: true
      },
      buttonActive: {
        type: Boolean,
        default: true
      },
    },
    computed: {
      ...mapState(["isLogin"]),
    },
    methods: {
      ...mapMutations(["USER_LOGOUT"]),
      search() {
        const payload = {
          searchText: this.searchText
        };
        this.$store.dispatch("getVideos", payload);
        if(this.$router.currentRoute.query.q != this.searchText){
          this.$router.push(`/search?q=${this.searchText}`);
        }
      },
      logOut(){
        this.USER_LOGOUT();
      }
    },
    created() {
      if(this.$router.currentRoute.query.q) this.searchText = this.$router.currentRoute.query.q;
    }
}
</script>

<style>

</style>