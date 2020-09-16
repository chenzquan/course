import Vue from 'vue'
import App from './App.vue'
import router from './router.js'
import filter from './filter/filter.js'


import axios from 'axios'
Vue.prototype.$ajax = axios;

Vue.config.productionTip = false;


/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
  // console.log("请求：", config);
  // let token = Tool.getLoginUser().token;
  // if (Tool.isNotEmpty(token)) {
  //   config.headers.token = token;
  //   console.log("请求headers增加token:", token);
  // }
  return config;
}, error => {});
axios.interceptors.response.use(function (response) {
  console.log("返回结果：", response);
  return response;
}, error => {});


// 全局过滤器
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key])
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

// export const http = Vue.http;

console.log("环境：",process.env.NODE_ENV);
