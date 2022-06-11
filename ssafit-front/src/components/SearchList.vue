<template>
  <div class="searchList">
    <h2>검색 목록</h2>
    <hr />
    <div>
      <table class="table">
        <tr>
          <th>videoId</th>
          <th>title</th>
          <th>channelId</th>
          <th>channelName</th>
          <th>tagName</th>
        </tr>
        <tr v-for="video in videos" :key="video.videoId">
          <td>{{ video.videoId }}</td>
          <td>{{ video.title }}</td>
          <td>{{ video.channelId }}</td>
          <td>{{ video.channelName }}</td>
          <td>{{ video.tagName }}</td>
        </tr>
      </table>


    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex';

export default {
  name: 'SearchList',
  computed: {
    ...mapState([
      "videos"
    ])
  },

}
</script>
