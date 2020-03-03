import Vue from 'vue'
import VueRouter from 'vue-router'
// 使用懒加载的方式配置各个组件之间的路由映射
const Login = () => import('views/user/Login')

Vue.use(VueRouter)

// 定义路由映射关系
const routes = [
  {
    path: '/login',
    component: Login
  }
]

const router = new VueRouter({
  routes,
  // 使用history的模式进行页面跳转
  mode: 'history'
})

export default router
