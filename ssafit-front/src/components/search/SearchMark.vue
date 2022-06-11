<template>
  <div>
    <v-dialog
      v-model="dialog"
      max-height="80vh"
      max-width="80vw"
    >
      <template v-slot:activator="{ attrs }">
        <v-container>
          <v-row>
            <v-col v-for="video in videos" :key=video.videoId lg="3" md="4" sm="6">
              <div class="card blur rounded-lg mx-auto">
                <div
                  v-bind="attrs"
                  @click="setVideoId(video.videoId)"
                >
                  <v-img
                    :src="'https://img.youtube.com/vi/' + video.videoId + '/hqdefault.jpg'"
                    height="200px"
                  ></v-img>

                  <div style="margin: 5px; font-size: 12pt; font-weight: bolder; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">{{ video.title }}</div>
                  <div style="margin: 5px; font-size: 10pt; font-weight: lighter; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">{{ video.channelName }}</div>
                </div>
              </div>
            </v-col>
          </v-row>
        </v-container>
      </template>

      <v-card style="background: none">
        <div class="blur rounded-lg d-flex flex-column align-center dialogCard">
          <div class="my-2" style="width: 75vw"><hr /></div>
          <iframe
            style="width: 75vw; min-height: 35vw;"
            :src="'https://www.youtube.com/embed/' + videoId" 
            title="YouTube video player" 
            frameborder="0" 
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
            allowfullscreen
          ></iframe>
          <div
            v-if="isLogin"
            class="d-flex align-center"
            style="width: 75vw"
          >
            <span class="flex-grow-1">{{ video.channelName }}</span>
            
            <v-btn v-if="video.is_mark" icon color="#9DC3E6" @click="mark(false, video.videoId)">
              <v-icon>bookmark</v-icon>
            </v-btn>
            <v-btn v-else icon color="#9DC3E6" @click="mark(true, video.videoId)">
              <v-icon>bookmark_border</v-icon>
            </v-btn>
            
            <v-btn v-if="video.is_follow" icon color="#9DC3E6" @click="follow(false, video.videoId, video.channelId)">
              <v-icon>favorite</v-icon>
            </v-btn>
            <v-btn v-else icon color="#9DC3E6" @click="follow(true, video.videoId, video.channelId)">
              <v-icon>favorite_border</v-icon>
            </v-btn>

            <v-chip class="ma-2" color="##FF0066">{{ video.tagName }}</v-chip>
          </div>
          <div class="my-2" style="width: 75vw"><hr /></div>

          <div v-if="isLogin" style="width: 75vw">
            <v-container class="mt-8">
              <!-- 댓글 입력 창 -->
              <v-row>
                <v-col cols="1" sm="1" md="1" style="text-align: center;">
                  <v-avatar size="36px">
                    <img :src="'http://localhost:9999/file/'+ user.userId +'.jpg'" />
                  </v-avatar>
                </v-col>
                <v-col>
                  <v-text-field
                    placeholder="댓글 추가..."
                    v-model="message"
                    filled
                    rounded
                    dense
                    outlined
                    @keydown.enter="insertReview"
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-row justify="center">
                <v-expansion-panels popout>
                  <v-expansion-panel
                    v-for="(review, index) in reviews"
                    :key="index"
                    hide-actions
                  >
                    <v-expansion-panel-header color="grey darken-1">
                      <div class="d-flex">
                        <!-- Avata Image -->
                        <v-avatar class="mr-4" size="36px">
                            <img :src="'http://localhost:9999/file/'+ review.userId +'.jpg'" />
                        </v-avatar>
                        <div class="flex-shrink-1 d-flex flex-column justify-space-between">
                          <div class="white--text" style="max-width: 50vw; text-align: left; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
                            {{ review.userId }}
                          </div>
                          <div class="black--text" style="max-width: 40vw; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
                            {{ review.content }}
                          </div>
                        </div>
                      </div>

                    </v-expansion-panel-header>

                    <v-expansion-panel-content color="grey darken-1">
                      <v-divider></v-divider>

                      <v-card-text v-if="user.grade != 0 && review.userId != user.userId">{{ review.content }}</v-card-text>
                      <div v-else style="text-align: right;">
                        <v-textarea
                          v-model="review.content"
                          label=""
                          counter
                          maxlength="500"
                          full-width
                          single-line
                        ></v-textarea>
                        <v-btn
                          class="mx-2"
                          fab
                          dark
                          small
                          color="#757575"
                          @click="updateReview(review.content, review.reviewNo)"
                        >
                          <v-icon dark>
                            edit
                          </v-icon>
                        </v-btn>
                        <v-btn
                          class="mx-2"
                          fab
                          dark
                          small
                          color="#757575"
                          @click="deleteReview(review.reviewNo)"
                        >
                          <v-icon dark>
                            delete
                          </v-icon>
                        </v-btn>
                      </div>
                    </v-expansion-panel-content>
                  </v-expansion-panel>
                </v-expansion-panels>
              </v-row>
            </v-container>

            <div class="my-2" style="width: 75vw"><hr /></div>

          </div>

          <div v-else class="my-16">
            로그인 후에 댓글을 볼 수 있습니다.
          </div>
        </div>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import {mapState} from 'vuex';

export default {
  name: 'SearchList',
  data: () => ({
    message: '',
    dialog: false,
    videoId: ''
  }),
  computed: {
    ...mapState([
      "isLogin", "videos", "video", "reviews", "searches", "user"
    ])
  },
  watch: {
    dialog: function() {
      if(!this.dialog) {
        // 영상 끄기
        this.videoId = '';

        // 영상 가장 위로 올리기
        document.getElementsByClassName('dialogCard')[0].scrollTop = 0;
      }
    }
  },
  methods: {
    setVideoId(videoId) {
      this.videoId = videoId;
      this.$store.dispatch("getVideo", { videoId: videoId, userId: this.user.userId });
      if(this.isLogin) this.$store.dispatch("getReviews", { videoId: videoId });
      setTimeout(() => this.dialog = true, 200);
    },
    insertReview() {
      const payload = {
        content: this.message,
        userId: this.user.userId,
        videoId: this.videoId
      }
      this.message = "";
      this.$store.dispatch("insertReview", payload);
    },
    updateReview(content, no) {
      if(confirm("해당 댓글을 수정합니다.\n정말로 수정하시겠습니까?")) {
        const payload = {
          content: content,
          reviewNo: no,
          videoId: this.videoId
        }
        this.$store.dispatch("updateReview", payload);
      }
    },
    deleteReview(no) {
      if(confirm("해당 댓글을 삭제합니다.\n정말로 삭제하시겠습니까?")) {
        const payload = {
          reviewNo: no,
          videoId: this.videoId
        }
        this.$store.dispatch("deleteReview", payload);
      }
    },
    mark(flag, videoId) {
      const payload = {
        videoId: videoId,
        userId: this.user.userId
      }

      console.log("mark-payload", payload);

      if(flag) {
        alert(videoId + " 북마크를 추가합니다.");
        this.$store.dispatch("insertMark", payload);
      }
      else {
        alert(videoId + " 북마크를 해제합니다.");
        this.$store.dispatch("deleteMark", payload);
      }
    },
    follow(flag, videoId, channelId) {
      const payload = {
        videoId: videoId,
        channelId: channelId,
        userId: this.user.userId
      }

      console.log("follow-payload", payload);

      if(flag) {
        alert(channelId + " 팔로우를 추가합니다.");
        this.$store.dispatch("insertFollow", payload);
      }
      else {
        alert(channelId + " 팔로우를 해제합니다.");
        this.$store.dispatch("deleteFollow", payload);
      }
    }
  },
  created() {
    this.$store.dispatch("getMark");
  }
}
</script>

<style>
</style>