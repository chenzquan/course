import Vue from 'vue'
import App from './App.vue'
import router from './router.js'
import axios from 'axios'

import filter from './filter/filter.js'

Vue.config.productionTip = false;
Vue.prototype.$ajax = axios;

//全局过滤器
Object.keys(filter).forEach(key => {
  Vue.filter(key,filter[key])
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
