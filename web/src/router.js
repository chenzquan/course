import Vue from 'vue'
import Router from 'vue-router'

import Index from './views/index.vue'

Vue.use(Router);

//路由 子路由
export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes:[{
        path:"*",
        redirect:"/index"
    },{
        path: "/index",
        component:Index
    }]
})


