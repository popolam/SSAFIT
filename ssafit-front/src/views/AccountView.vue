<template>
<v-app>
  <!-- v-app-bar -->
  <header-nav :searchActive="false" :buttonActive="false"></header-nav>

  <!-- v-main -->
  <v-main
    app
    id="scroll"
    class="white--text account-main"
  >
    <v-container fluid>
      <router-view></router-view>
    </v-container>
  </v-main>

</v-app>
</template>

<script>
  import HeaderNav from '@/components/common/HeaderNav.vue';
  export default {
    name: 'AccoutView',
    components: {
      HeaderNav
    },
  }
</script>

<style scoped>
  #app {
    height: 100vh;
    overflow: hidden;
    margin:0;
    background-image: url("@/assets/background/background1.jpg");
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
  } 
</style>