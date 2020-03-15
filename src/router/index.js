import Vue from 'vue'
import VueRouter from 'vue-router'
// 使用懒加载的方式配置各个组件之间的路由映射
const Login = () => import('views/user/Login')
const Regist = () => import('views/user/Regist')
const MainItem = () => import('components/common/main/MainItem')
const AirTicketMain = () => import('views/airticket/AirTicketMain')
const OrderManageMain = () => import('views/ordermanage/OrderManageMain')
const TravelMain = () => import('views/travel/TravelMain')
const AirDynamicMain = () => import('views/airdynamic/AirDynamicMain')
const SettingsMain = () => import('views/settings/SettingsMain')
const AirTicketPre = () => import('views/airticket/AirTicketPre')
const AirTicketDetail = () => import('views/airticket/AirTicketDetail')
const MyRoute = () => import('views/airticket/MyRoute')
const TicketsByPage = () => import('views/airticket/TicketsByPage')
const BuyTicket = () => import('components/content/ticket/BuyTicket')

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
  },
  {
    // 机票业务
    path: '/airticketmain',
    component: AirTicketMain,
    // 嵌套路由
    children: [
      {
        // 默认显示机票预定页面
        path: '',
        component: AirTicketPre
      },
      {
        // 机票预定页面
        path: 'preorder',
        component: AirTicketPre
      },
      {
        // 查看机票页面
        path: 'airticketdetail',
        component: AirTicketDetail
      },
      {
        // 我的行程页面
        path: 'myroute',
        component: MyRoute
      }
    ]
  },
  {
    // 订单管理
    path: '/ordermanage',
    component: OrderManageMain
  },
  {
    // 旅游宝典
    path: '/travel',
    component: TravelMain
  },
  {
    // 航班动态
    path: '/airdynamic',
    component: AirDynamicMain
  },
  {
    // 设置
    path: '/settings',
    component: SettingsMain
  },
  {
    // 请求分页显示机票组件
    path: '/ticketsbypage',
    component: TicketsByPage
  },
  {
    // 购买机票组件
    path: '/buyticket/:id',
    component: BuyTicket
  }
]

const router = new VueRouter({
  routes,
  // 使用history的模式进行页面跳转
  mode: 'history'
})

export default router
