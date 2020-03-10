import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
// 若想改变公共属性，需要使用在vuex这个仓库对象中定义的函数和异步函数来修改公共变量，并且是响应式实现
const store = new Vuex.Store({
  state: {
    // 定义单点登录机制的令牌属性
    token: '',
    // 定义一个是否登录的状态属性
    isLogin: 0
  },
  // 公共的计算属性
  getters: {

  },
  // 同步函数
  mutations: {

  },
  // 异步方法
  actions: {

  }
})

export default store
