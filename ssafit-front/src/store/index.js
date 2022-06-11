import { createApi } from "@/api";
import router from "@/router";
import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex)

const api = createApi();

export default new Vuex.Store({
  state: {
    scores: [],
    searches: null,
    videos: [],
    reviews: [],
    video: {},
    user: {},
    isLogin: false,
    todos: [],
  },
  getters: {
    loginCheck: state => state.isLogin,
  },
  mutations: {
    GET_VIDEOS(state, payload) {
      state.videos = payload;
    },
    GET_VIDEO(state, payload) {
      state.video = payload;
    },
    GET_REVIEWS(state, payload) {
      state.reviews = payload;
    },
    USER_JOIN(state,payload) {
      state.reviews = payload;
    },
    USER_LOGIN(state, payload) {
      console.log("토큰", payload["auth-token"]);
      console.log("유저 이메일", payload["userId"]);
      console.log("권한", payload["grade"]);
      state.isLogin = true;
      state.user.userId = payload["userId"];
      state.user.grade = payload["grade"];
      sessionStorage.setItem("auth-token", payload["auth-token"]);
      api.defaults.headers["auth-token"] = payload["auth-token"]; // JWT Header
    },
    USER_LOGOUT(state) {
      state.isLogin = false;
      state.user.userId = '';
      sessionStorage.removeItem("auth-token");
      api.defaults.headers["auth-token"] = "";
      router.push("/account/login");
    },
    GET_USER(state, payload) {
      state.user = payload;
    },
    GET_SEARCHES(state, payload){
      state.searches = payload;
    },
    VALIDATE(state, payload) {
      state.isLogin = true;
      state.user.userId = payload.userId;
      if(payload.userId == "admin@ssafy.com") state.user.grade = 0;
      else state.user.grade = 1;
      console.log("재발급 받은 토큰", payload["auth-token"]);
      console.log("유저 이메일", state.user.userId);
      console.log("로그인 상태", state.isLogin);
      console.log("권한", state.user.grade);
      // 토큰 재발급
      sessionStorage.setItem("auth-token", payload["auth-token"]);
      api.defaults.headers["auth-token"] = payload["auth-token"];
    },
    GET_SCORE_ALL(state, payload) {
      state.scores = payload;
    },
    WRITE_TODO(state, payload) {
      state;
      payload;
      // state.todos.push(payload);
      // console.log(state.todos);
    },
    GET_TODOS(state, payload) {
      state.todos = payload;
      console.log(payload);
    },
    REMOVE_TODO(state, payload) {
      state.todos = payload;
    },
    COMMIT(state, payload) {
      console.log("[COMMIT]", payload);
    }
  },
  actions: {
    // Score
    getScoreAll({ commit }) {
      const API_URL = `/score/all`;
      api({
        url: API_URL,
        method: 'GET',
      })
        .then((res) => {
          commit('GET_SCORE_ALL', res.data);
        })
        .catch((err) => {
          console.log("[SCORE ERROR] : ", err);
        });
    },

    // Video
    getVideos({ commit }, payload) {
      let params = null;
      if(payload && payload.searchText) {
        params = payload;
      }
      else{
        params = { searchText: "" }
      }

      params.userId = this.state.user.userId;

      const API_URL = `/video`;
      api({
        url: API_URL,
        method: 'POST',
        data: params
      })
        .then((res) => {
          commit('GET_VIDEOS', res.data);
        })
        .catch((err) => {
          console.log("[VIDEOS ERROR] : ", err);
        });
    },
    getVideo({ commit }, payload) { // payload { videoId, userId }
      const API_URL = `/video/detail`;
      console.log("페이로드 값", payload);
      api({
        url: API_URL,
        method: 'POST',
        data: payload
      })
        .then((res) => {
          console.log("비디오 정보값", res.data);
          commit('GET_VIDEO', res.data);
        })
        .catch((err) => {
          console.log("[VIDEO ERROR] : ", err);
        });
    },

    // Follow
    getFollow({ commit }) {
      const API_URL = `/follow/${ this.state.user.userId }`;
      api({
        url: API_URL,
        method: 'GET',
      })
        .then((res) => {
          commit('GET_VIDEOS', res.data);
        })
        .catch((err) => {
          console.log("[FOLLOW ERROR] : ", err);
        });
    },
    insertFollow({ commit }, payload) { // payload { userId, videoId, channelId }
      const API_URL = `/follow`;
      console.log(router.currentRoute);
      api({
        url: API_URL,
        method: 'POST',
        data: payload
      })
        .then((res) => {
          commit("COMMIT", res.data);
          if(router.currentRoute.path == "/search/mark"){
            this.dispatch("getMark");
          }
          else if(router.currentRoute.path == "/search/follow"){
            this.dispatch("getFollow");
          }
          else {
            this.dispatch("getVideos", { searchText: router.currentRoute.query.q });
          }
          this.dispatch("getVideo", { videoId: payload.videoId, userId: payload.userId });
        })
        .catch((err) => {
          console.log("[INSERT FOLLOW ERROR] : ", err);
        });
    },
    deleteFollow({ commit }, payload) { // payload { userId, videoId, channelId }
      const API_URL = `/follow`;
      api({
        url: API_URL,
        method: 'DELETE',
        data: payload
      })
        .then((res) => {
          commit("COMMIT", res.data);
          if(router.currentRoute.path == "/search/mark"){
            this.dispatch("getMark");
          }
          else if(router.currentRoute.path == "/search/follow"){
            this.dispatch("getFollow");
          }
          else {
            this.dispatch("getVideos", { searchText: router.currentRoute.query.q });
          }
          this.dispatch("getVideo", { videoId: payload.videoId, userId: payload.userId });
        })
        .catch((err) => {
          console.log("[DELETE FOLLOW ERROR] : ", err);
        });
    },

    // Mark
    getMark({ commit }) {
      const API_URL = `/mark/${ this.state.user.userId }`;
      api({
        url: API_URL,
        method: 'GET',
      })
        .then((res) => {
          commit('GET_VIDEOS', res.data);
        })
        .catch((err) => {
          console.log("[MARK ERROR] : ", err);
        });
    },
    insertMark({ commit }, payload) { // payload { userId, videoId }
      const API_URL = `/mark`;
      api({
        url: API_URL,
        method: 'POST',
        data: payload
      })
        .then((res) => {
          commit("COMMIT", res.data);
          if(router.currentRoute.path == "/search/mark"){
            this.dispatch("getMark");
          }
          else if(router.currentRoute.path == "/search/follow"){
            this.dispatch("getFollow");
          }
          else {
            this.dispatch("getVideos", { searchText: router.currentRoute.query.q });
          }
          this.dispatch("getVideo", { videoId: payload.videoId, userId: payload.userId });
        })
        .catch((err) => {
          console.log("[INSERT MARK ERROR] : ", err);
        });
    },
    deleteMark({ commit }, payload) { // payload { userId, videoId }
      const API_URL = `/mark`;
      api({
        url: API_URL,
        method: 'DELETE',
        data: payload
      })
        .then((res) => {
          commit("COMMIT", res.data);
          if(router.currentRoute.path == "/search/mark"){
            this.dispatch("getMark");
          }
          else if(router.currentRoute.path == "/search/follow"){
            this.dispatch("getFollow");
          }
          else {
            this.dispatch("getVideos", { searchText: router.currentRoute.query.q });
          }
          this.dispatch("getVideo", { videoId: payload.videoId, userId: payload.userId });
        })
        .catch((err) => {
          console.log("[DELETE MARK ERROR] : ", err);
        });
    },

    // Review
    getReviews({ commit }, payload) {
      const API_URL = `/review/${ payload.videoId }`;
      api({
        url: API_URL,
        method: 'GET',
      })
        .then((res) => {
          commit('GET_REVIEWS', res.data);
        })
        .catch((err) => {
          console.log("[REVIEWS ERROR] : ", err);
        });
    },
    insertReview({ commit }, payload){
      const API_URL = `/review`;
      api({
        url: API_URL,
        method: 'POST',
        data: payload
      })
        .then((res) => {
          commit('GET_REVIEWS', res.data);
        })
        .catch((err) => {
          console.log("[INSERT REVIEWS ERROR] : ", err);
        });
    },
    updateReview({ commit }, payload){
      const API_URL = `/review`;
      api({
        url: API_URL,
        method: 'PUT',
        data: payload
      })
        .then((res) => {
          commit('GET_REVIEWS', res.data);
        })
        .catch((err) => {
          console.log("[UPDATE REVIEWS ERROR] : ", err);
        });
    },
    deleteReview({ commit }, payload){
      const API_URL = `/review/`;
      api({
        url: API_URL,
        method: 'DELETE',
        data: payload
      })
        .then((res) => {
          commit('GET_REVIEWS', res.data);
        })
        .catch((err) => {
          console.log("[DELETE REVIEWS ERROR] : ", err);
        });
    },

    // User
    userLogin({ commit }, { user, call }) {
      const API_URL = `/login`;
      api({
        url: API_URL,
        method: "POST",
        data: user,
      })
        .then(({ data }) => {
          commit("USER_LOGIN", data);
          this.dispatch("getUser");

          if(call) {
            router.push(call); // 온 곳으로
          }
          else {
            router.push({ name: "home" }); // 홈페이지로
          }
        })
        .catch((err) => {
          console.log("[LOGIN ERROR] : ", err);
          alert("login error");
        });
    },
    userJoin({ dispatch },payload){
      const API_URL = `/join`;
      console.dir(payload);
      api({
        url: API_URL,
        method: "POST",
        data: payload,
      })
        .then(({ data }) => {
          console.log(data);
          dispatch("userLogin", { user: payload });
          // commit("USER_JOIN", data);
          // router.push({ name: "home" }); // 홈페이지로
        })
        .catch((err) => {
          console.log("[JOIN ERROR] : ", err);
          alert("login error");
        });
    },
    getUser({ commit }){
      const API_URL = `/myPage/${this.state.user.userId}`;
      api({
        url: API_URL,
        method: "GET",
      })
        .then(({ data }) => {
          commit("GET_USER", data);
        })
    },
    validate({ commit }, { token }){
      const payload = {
        "auth-token": token
      }
      const API_URL = `/validate`;
      api.defaults.headers["auth-token"] = token;
      api({
        url: API_URL,
        method: "POST",
        data: payload
      })
        .then(({ data }) => {
          commit("VALIDATE", data);
          this.dispatch("getUser");
        })
    },

    // Search Ranking
    getSearches({ commit }){
      const API_URL = `/ranking/`;
      api({
        url: API_URL,
        method: "GET",
      })
        .then(({ data }) => {
          commit("GET_SEARCHES", data);
        })
        .catch((err) => {
          console.log("[SEARCHES ERROR] : ", err);
        });
    },

    // Todo
    writeTodo({ commit }, todoItem) {
      const API_URL = `/todo`;
      api({
        url: API_URL,
        method: "POST",
        data: todoItem,
      })
      .then(({data}) => {
        commit("WRITE_TODO", data);
        console.log(todoItem.todoDate);
        this.dispatch("getTodos", todoItem.todoDate)
      })
    },
    getTodos({ commit }, payload) {
      console.log(payload);
      console.log(this.state.user.userId);
      const API_URL = `/todo?userId=${ this.state.user.userId }&todoDate=${ payload }`;
      api({
        url: API_URL,
        method: 'GET',
      })
        .then((res) => {
          commit('GET_TODOS', res.data);
          console.dir(res)
        })
        .catch((err) => {
          console.log("[TODOS ERROR] : ", err);
        });
    },
    removeTodo({ commit }, payload) {
      const API_URL = `/todo`;
      api({
        url: API_URL,
        method: "DELETE",
        data: payload,
      })
      .then(({data}) => {
        commit("REMOVE_TODO", data);
        console.dir(data);
      })
    }
  },
  modules: {
  }
})
