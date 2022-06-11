<template>
  <v-app id="inner-app">
    <header-nav></header-nav>
      <v-container
        fluid
        id="scroll"
        class="ma-0 pa-0 overflow-y-auto overflow-x-hidden"
        style="height: 100vh; width: 100vw; background: black"
      >
        <v-row
          v-scroll:#scroll-target="onScroll"
          align="center"
          justify="center"
          id="scroll-content"
        >
          <test-form componentNum="0" @buttonActive="component0On" @afterActive="scrollDown"></test-form>
          <test-form v-if="flag0" componentNum="1" @buttonActive="component1On" @afterActive="scrollDown"></test-form>
          <test-form v-if="flag1" componentNum="2" @buttonActive="component2On" @afterActive="scrollDown"></test-form>
          <test-form v-if="flag2" componentNum="3" @buttonActive="component3On" @afterActive="scrollDown"></test-form>
          <test-form v-if="flag3" componentNum="4" @buttonActive="component4On" @afterActive="scrollDown"></test-form>
          <test-form v-if="flag4" componentNum="5" @buttonActive="component5On" @afterActive="scrollDown"></test-form>
          <test-form v-if="flag5" componentNum="6" @buttonActive="componentSelect" @afterActive="scrollDown"></test-form>

          <test-form v-if="flag7" componentNum="7" @buttonActive="componentSelect" @afterActive="scrollDown"></test-form>
          <test-form v-if="flag8" componentNum="8" @buttonActive="componentSelect" @afterActive="scrollDown"></test-form>
          <test-form v-if="flag9" componentNum="9" @buttonActive="componentSelect" @afterActive="scrollDown"></test-form>
          <test-form v-if="flag10" componentNum="10" @buttonActive="componentSelect" @afterActive="scrollDown"></test-form>



        </v-row>
      </v-container>

  </v-app>
</template>

<script>
import HeaderNav from '@/components/common/HeaderNav.vue';
import TestForm from '@/components/test/TestForm.vue';

export default {
  name: 'TestView',
  components: {
    HeaderNav,
    TestForm,
  },
  data: () => ({
    weight: { "상체":1, "하체":1 , "복부":1, "전신":1 },
    score: { "상체":0, "하체":0 , "복부":0, "전신":0 },
    flag0: false,
    flag1: false,
    flag2: false,
    flag3: false,
    flag4: false,
    flag5: false,
    flag6: false,
    flag7: false,
    flag8: false,
    flag9: false,
    flag10: false,
  }),
  methods: {
      onScroll(e) {
        this.offsetTop = e.target.scrollTop
      },
      scrollUp() {
        document.getElementById('scroll').scrollTop = 0;
      },
      scrollDown() {
        document.getElementById('scroll').scrollTop = document.getElementById('scroll').scrollHeight;
      },
      sum(value) {
        switch(value){
          case 0:
            this.score["상체"]++;
            break;
          case 1:
            this.score["하체"]++;
            break;
          case 2:
            this.score["복부"]++;
            break;
          case 3:
            this.score["전신"]++;
            break;
        }

        console.log(this.score)
      },
      component0On(value) {
        console.log(value);
        this.flag0 = true;
      },
      component1On(value) {
        this.sum(value);
        this.flag1 = true;
      },
      component2On(value) {
        this.sum(value);
        this.flag2 = true;
      },
      component3On(value) {
        this.sum(value);
        this.flag3 = true;
      },
      component4On(value) {
        this.sum(value);
        this.flag4 = true;
      },
      component5On(value) {
        this.sum(value);
        this.flag5 = true;
      },
      componentSelect(value) {
        this.sum(value);
        let keys = Object.keys(this.score);
        let max = this.score[keys[0]] * this.weight[keys[0]];
        let maxIdx = 0;
        let i;

        for(i = 1; i < keys.length; i++) {
          const w = this.score[keys[i]] * this.weight[keys[i]];
          if(max < w) {
            max = w;
            maxIdx = i;
          }
        }

        console.log(keys[maxIdx]);

        switch(maxIdx){
          case 0:
            this.flag7 = true;
            break;
          case 1:
            this.flag8 = true;
            break;
          case 2:
            this.flag9 = true;
            break;
          case 3:
            this.flag10 = true;
            break;
        }
      },
    },
}
</script>

<style scoped>
   #app {
    height: 100vh;
    overflow: auto;
    margin:0;
  }

  .test-question {
    font-size: 200pt;
  }


</style>