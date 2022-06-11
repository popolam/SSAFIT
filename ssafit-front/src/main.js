import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'

// vuetify
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

Vue.use(Vuetify);

Vue.config.productionTip = false

new Vue({
  store,
  router,
  vuetify: new Vuetify({
    icons: {
      iconfont: 'md',
    },
  }),
  render: h => h(App)
}).$mount('#app')
