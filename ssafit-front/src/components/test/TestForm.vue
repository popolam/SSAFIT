<template>
  <v-container class="test-container ma-0 pa-0" fluid>
    <div class="d-flex flex-column justify-center align-center white--text" style="height: 100%">

      <!-- 0 -->
      <div v-if="componentNum == 0" class="test-question rounded-xl blur d-flex flex-column justify-center align-center flex-wrap">
        <div><img :src="require('@/assets/logo.png')" alt="logo.png" style="height: 40px"/>의 빅데이터가</div>
        <div>오늘의 운동을 추천해 드립니다.</div>

        <v-btn-toggle 
          tile
          group
          v-model="value"
          class="mt-16 d-flex justify-center align-center"
          color="#FF0066"
        >
          <v-btn v-for="(testBtn, index) in testBtns[componentNum]" :key="index" class="ma-2 text-center">
            <v-chip class="ma-2 text-center" color="#FF0066">{{ testBtn }}</v-chip>
          </v-btn>
        </v-btn-toggle>
      </div>

      <!-- else -->
      <div v-else class="test-question rounded-xl blur d-flex flex-column justify-center align-center">
        <div>{{questions[componentNum]}}</div>

        <v-btn-toggle 
          tile
          group
          v-model="value"
          class="mt-16 d-flex justify-center align-center"
          color="#FF0066"
        >
          <v-btn v-for="(testBtn, index) in testBtns[componentNum]" :key="index" class="ma-2 text-center">
            <v-chip class="ma-2 text-center" style="width: 100px; text-align:center;" color="#FF0066">{{ testBtn }}</v-chip>
          </v-btn>
        </v-btn-toggle>
        <router-link to="/search?q=상체" style="text-decoration: none">
          <v-btn v-if="componentNum == 8"
            elevation="2"
          >
            상체 운동 하러가기
          </v-btn>
        </router-link>

        <router-link to="/search?q=하체" style="text-decoration: none">
          <v-btn v-if="componentNum == 9"
            elevation="2"
          >
            하체 운동 하러가기
          </v-btn>
        </router-link>

        <router-link to="/search?q=복부" style="text-decoration: none">
          <v-btn v-if="componentNum == 10"
            elevation="2"
          >
            복부 운동 하러가기
          </v-btn>
        </router-link>

        <router-link to="/search?q=전신" style="text-decoration: none">
          <v-btn v-if="componentNum == 11"
            elevation="2"
          >
            전신 운동 하러가기
          </v-btn>
        </router-link>
      </div>




    </div>

  </v-container>
</template>

<script>
import background0 from '@/assets/background/background0.png';
import background1 from '@/assets/background/background1.jpg';
import background2 from '@/assets/background/background2.jpg';
import background3 from '@/assets/background/background3.jpg';
import background4 from '@/assets/background/background4.png';
import background5 from '@/assets/background/background5.jpg';
import background6 from '@/assets/background/background6.jpg';
import background7 from '@/assets/background/background7.jpg';
import background8 from '@/assets/background/background7.jpg';
import background9 from '@/assets/background/background7.jpg';
import background10 from '@/assets/background/background7.jpg';

export default {
  name: 'TestForm',
  data() {
    return {
      value: null,
      background: [
        background0,
        background1,
        background2,
        background3,
        background4,
        background5,
        background6,
        background7,
        background8,
        background9,
        background10,
      ],
      testBtns: [
        ['시작하기'],
        ['자주', '조금 자주', '거의 안함', '전혀'],
        ['상체', '하체', '복부', '전신'],
        ['헬스장', '운동장', '집', '공원'],
        ['자주', '조금 자주', '거의 안함', '전혀'],
        ['아주', '많이', '조금', '전혀'],
        ['1시간 미만', '1시간', '2시간', '3시간'],
        ['아주', '많이', '조금', '전혀'],
      ],
      questions: [
        "Q0. 빈 질문",
        "Q1 평소에 운동을 자주 하시나요?",
        "Q2 오늘 유난히 끌리는 운동이 있나요?",
        "Q3 운동을 어디서 하실 생각인가요?",
        "Q4. 건강하신 편인가요?",
        "Q5. 전날 무리하게 운동하셨나요?",
        "Q6. 몇 시간 정도 운동하실 예정인가요?",
        "Q7. 집과 먼 곳에서 운동을 하나요?",
        "강한 당신에게 상체운동을 추천합니다.",
        "날싼 당신에게 하체운동을 추천합니다.",
        "멋진 당신에게 복부운동을 추천합니다.",
        "힘찬 당신에게 전신운동을 추천합니다.",
      ]
    }
  },
  props: {
    componentNum: String,
  },
  methods() {

  },
  mounted(){
    if(this.componentNum < 7)
      document.getElementsByClassName('test-container')[this.componentNum].style.backgroundImage = `url(${this.background[this.componentNum]})`;
    else
      document.getElementsByClassName('test-container')[7].style.backgroundImage = `url(${this.background[this.componentNum]})`;
    this.$emit("afterActive");
  },
  updated(){
    this.$emit("buttonActive", this.value);
    this.$emit("afterActive");
  },
  
}
</script>

<style scoped>
  .test-container {
    height: 100vh;
    width: 100vw;
    overflow: hidden;
    background-image: url('@/assets/background/background0.png');
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    text-align: center;
  }

  .test-question {
    height: 50%;
    width: 50%;
    font-size: 30pt;
    font-weight: bold;
  }

</style>