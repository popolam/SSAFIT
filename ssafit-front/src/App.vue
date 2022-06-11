<template>
  <v-app id="app">
    <router-view/>
  </v-app>
</template>

<script>
export default {
  created() {
    const payload = { token: sessionStorage.getItem("auth-token")}
    this.$store.dispatch("validate", payload);
  }
}
</script>

<style>
  html {
    height: 100vh;
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    /* text-align: center; */
    color: #2c3e50;
  }
  .search-tag {
    margin: 5px; 
    font-size: 12pt; 
    font-weight: bolder; 
    overflow: hidden; 
    text-overflow: ellipsis; 
    white-space: nowrap;
    transition: all .1s linear;
  }
  .search-tag:hover {
    transform: scale(1.1);
  }
  .card{
    -webkit-filter: grayscale(100%);
    -webkit-transition: .1s ease-in-out;
    -moz-filter: grayscale(100%);
    -moz-transition: .1s ease-in-out;
    -o-filter: grayscale(100%);
    -o-transition: .1s ease-in-out;
    padding: 5px; 
    overflow: hidden; 
    min-height: 10vw; 
    min-width: 10vw;
    transition: all .1s linear;
    z-index: 1;
  }
  .card:hover {
    -webkit-filter: grayscale(0%);
    -webkit-transition: .1s ease-in-out;
    -moz-filter: grayscale(0%); 
    -moz-transition: .1s ease-in-out;
    -o-filter: grayscale(0%); 
    -o-transition: .1s ease-in-out;
    transform: scale(1.1);
    z-index: 5;
  }
  .dialogCard {
    -ms-overflow-style: none;
    color: white;
    overflow-y: auto; 
    overflow-x: hidden;
    height: 80vh; 
    width: 80vw;
  }
  .dialogCard::-webkit-scrollbar{ 
    display:none; 
  }
  .blur{
    -webkit-backdrop-filter: blur(50px);
    backdrop-filter: blur(50px);
    background-blend-mode: overlay;
    box-shadow: 2px 2px 5px 4px black; 
	}
  #scroll {
    -ms-overflow-style: none;
    overflow-y: auto; 
    overflow-x: hidden;
    scroll-behavior: smooth;
  }
  #scroll::-webkit-scrollbar{ 
    display:none; 
  }

</style>
