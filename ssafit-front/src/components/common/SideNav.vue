<template>
    <v-navigation-drawer
      app
      clipped
      permanent
      expand-on-hover
      class="ma-0 pa-0"
      style="z-index: 100;"
    >
      <v-list>
        <v-list-item link :to="{ path: '/search/mypage'}">
          <v-list-item-avatar size="25px">
            <!-- <v-img v-if="isLogin" :src="require('@/assets/sampleUserPhoto.png')"></v-img> -->
            <v-img v-if="isLogin" :src="'http://localhost:9999/file/'+ user.userId +'.jpg'"></v-img>
            <v-img v-else :src="require('@/assets/unknownUserPhoto.jpg')"></v-img>
          </v-list-item-avatar>

          <v-list-item-content v-if="isLogin">
            <v-list-item-title class="text-h6">
              {{ user.name }}
            </v-list-item-title>
            <v-list-item-subtitle>
              {{ user.userId }}
            </v-list-item-subtitle>
            <v-list-item-subtitle style="font-size: 1pt">
              {{ user.areaName }} {{ user.classNum }}반 {{ user.nickname }}
            </v-list-item-subtitle>
          </v-list-item-content>
          <v-list-item-content v-else>
            <v-list-item-title>
              로그인이 필요합니다.
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-divider></v-divider>

      <v-list nav dense shaped>
        <v-list-item-group color="#FFC400">
          <v-list-item link :to="{ path: '/search/leaderboard'}">
              <v-list-item-icon>
                <v-icon>emoji_events</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Leaderboard</v-list-item-title>
            </v-list-item>
        </v-list-item-group>
      </v-list>

      <v-list nav dense shaped>
        <v-list-group
          :value="false"
          prepend-icon="fitness_center"
        >

          <template v-slot:activator>
            <v-list-item-title>Fitness</v-list-item-title>
          </template>
          <v-list-item-group color="#FFC400">
            <v-list-item link :to="{ path: '/search/mypage'}">
              <v-list-item-icon>
                <v-icon>perm_identity</v-icon>
              </v-list-item-icon>
              <v-list-item-title>My Page</v-list-item-title>
            </v-list-item>

            <v-list-item link :to="{ path: '/test'}">
              <v-list-item-icon>
                <v-icon>thumb_up_off_alt</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Today's recommendation</v-list-item-title>
            </v-list-item>

            <v-list-item link :to="{ path: '/search/mark'}">
              <v-list-item-icon>
                <v-icon>bookmark_border</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Marked Video</v-list-item-title>
            </v-list-item>

            <v-list-item link :to="{ path: '/search/follow'}">
              <v-list-item-icon>
                <v-icon>favorite_border</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Followed Channel</v-list-item-title>
            </v-list-item>

            <v-list-item link :to="{ path: '/search/map'}">
              <v-list-item-icon>
                <v-icon>place</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Near Gym</v-list-item-title>
            </v-list-item>
          </v-list-item-group>
        </v-list-group>
      </v-list>
    </v-navigation-drawer>
</template>

<script>
import { mapState } from "vuex";
export default {
    computed: {
      ...mapState(["isLogin", "user"]),
    },
}
</script>

<style>
</style>