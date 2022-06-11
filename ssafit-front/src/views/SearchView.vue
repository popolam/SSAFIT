<template>
  <v-app>
    <!-- v-app-bar -->
    <header-nav></header-nav>

    <!-- v-navigation-drawer -->
    <side-nav></side-nav>

    <!-- v-main -->
    <v-main
      id="scroll"
      class="white--text pl-16"
    >
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-main>

  </v-app>
</template>

<script>
  import { mapState } from "vuex";
  import HeaderNav from '@/components/common/HeaderNav.vue';
  import SideNav from '@/components/common/SideNav.vue';

  export default {
    name: 'SearchView',
    components: {
      HeaderNav,
      SideNav
    },
    data() {
      return {
        dialog: false,
      }
    },
    computed: {
      ...mapState(["isLogin"]),
    },
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
  #scroll {
    height: 100vh;
    background: none;
  } 
</style>