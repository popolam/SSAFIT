import Vue from 'vue';
import VueRouter from 'vue-router';
import store from "@/store";

// View
import HomeView from '@/views/HomeView.vue';
import TestView from '@/views/TestView.vue';
import SearchView from '@/views/SearchView.vue';
import AccountView from '@/views/AccountView.vue';

// Account
import AccountLogin from '@/components/account/AccountLogin.vue';
import AccountJoin from '@/components/account/AccountJoin.vue';

// Search
import SearchFollow from '@/components/search/SearchFollow.vue';
import SearchLeaderboard from '@/components/search/SearchLeaderboard.vue';
import SearchList from '@/components/search/SearchList.vue';
import SearchMark from '@/components/search/SearchMark.vue';
import SearchMypage from '@/components/search/SearchMypage.vue';
import SearchMap from '@/components/search/SearchMap.vue';

Vue.use(VueRouter);

const checkLogin = () => (from, to, next) => {
  if(sessionStorage.getItem("auth-token") || store.state.isLogin) {
    next();
  } 
  else{
    if(confirm("로그인이 필요한 서비스입니다.\n로그인 페이지로 이동하시겠습니까?")) {
      next(`/account/login?call=${from.fullPath}`);
    }
  }
};

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/test',
    name: 'test',
    component: TestView
  },
  {
    path: '/account',
    name: 'account',
    component: AccountView,
    children: [
      {
        path: 'login',
        name: 'accountLogin',
        component: AccountLogin
      },
      {
        path: 'join',
        name: 'accountJoin',
        component: AccountJoin
      }
    ]
  },
  {
    path: '/search',
    component: SearchView,
    children: [
      {
        path: '',
        name: 'search',
        component: SearchList
      },
      {
        path: 'follow',
        name: 'searchFollow',
        component: SearchFollow,
        beforeEnter: checkLogin()
      },
      {
        path: 'leaderboard',
        name: 'searchLeaderboard',
        component: SearchLeaderboard,
        beforeEnter: checkLogin()
      },
      {
        path: 'mypage',
        name: 'searchMypage',
        component: SearchMypage,
        beforeEnter: checkLogin()
      },
      {
        path: 'mark',
        name: 'searchMark',
        component: SearchMark,
        beforeEnter: checkLogin()
      },
      {
        path: 'map',
        name: 'searchMap',
        component: SearchMap,
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
