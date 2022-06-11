<template>
  <v-data-table
    :headers="headers"
    :items="scores"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>
</template>

<script>
  import {mapState} from 'vuex';

  export default {
    data () {
      return {
        headers: [
          { text: '랭킹', value: 'no' },
          { text: '이메일', value: 'userId' },
          { text: '반', value: 'classNum' },
          { text: '총점', value: 'sum' },
          { text: '상체점수', value: 'scoreUpper' },
          { text: '하체점수', value: 'scoreLower' },
          { text: '복부점수', value: 'scoreBelly' },
          { text: '전신점수', value: 'scoreBody' },
        ]
      }
    },
    computed: {
      ...mapState([
        "scores"
      ]),
    },
    created() {
      this.$store.dispatch("getScoreAll");
    }
  }
</script>

<style>

</style>