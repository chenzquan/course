import Vue from 'vue'
import App from './App.vue'
import router from './router.js'
import filter from './filter/filter.js'


import axios from 'axios'
Vue.prototype.$ajax = axios;

Vue.config.productionTip = false;


// 解决每次ajax请求 对应的sessionId 不一致 问题
axios.defaults.withCredentials = true;

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
  console.log("请求：", config);
  let token = Tool.getLoginUser().token;
  if (Tool.isNotEmpty(token)) {
    config.headers.token = token;
    console.log("请求headers增加token:", token);
  }
  return config;
}, error => {});
axios.interceptors.response.use(function (response) {
  console.log("返回结果：", response);
  return response;
}, error => {});


// 全局过滤器  是为了 做枚举
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key])
});


// 路由登录拦截
router.beforeEach((to, from, next) => {
  // 要不要对meta.loginRequire属性做监控拦截
  if (to.matched.some(function (item) {
    return item.meta.loginRequire
  })) {
    let loginUser = Tool.getLoginUser();
    if (Tool.isEmpty(loginUser)) {
      next('/login');
    } else {
      next();
    }
  } else {
    next();
  }
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

// export const http = Vue.http;

console.log("环境：",process.env.NODE_ENV);
