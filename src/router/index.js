import Vue from 'vue'
import VueRouter from 'vue-router'
// 使用懒加载的方式配置各个组件之间的路由映射
const Login = () => import('views/user/Login')
const Regist = () => import('views/user/Regist')
const MainItem = () => import('components/common/main/MainItem')

Vue.use(VueRouter)

// 定义路由映射关系
const routes = [
  {
    // 主页默认显示的路由定义
    path: '/',
    component: MainItem
  },
  {
    // 登录
    path: '/login',
    component: Login
  },
  {
    // 注册
    path: '/regist',
    component: Regist
  }
]

const router = new VueRouter({
  routes,
  // 使用history的模式进行页面跳转
  mode: 'history'
})

export default router
