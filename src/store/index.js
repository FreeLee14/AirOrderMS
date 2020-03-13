import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
// 若想改变公共属性，需要使用在vuex这个仓库对象中定义的函数和异步函数来修改公共变量，并且是响应式实现
const store = new Vuex.Store({
  state: {
    // 定义单点登录机制的令牌属性
    token: '',
    // 定义一个是否登录的状态属性0代表未登录，1代表已登录
    isLogin: 0,
    // 登陆成功后用户名称需要在各个显示组件中进行展示所以需要定义未公共变量
    name: ''
  },
  // 公共的计算属性
  getters: {

  },
  // 同步函数
  mutations: {
    // 用于登录时后给state中的数据更新状态为已登录
    changeState (state, payload) {
      state.token = payload.token
      state.isLogin = payload.isLogin
      state.name = payload.name
    },
    logoutState (state) {
      state.token = ''
      state.isLogin = 0
      state.name = ''
    }
  },
  // 异步方法
  actions: {

  }
})

console.log(store.state.token)
export default store
