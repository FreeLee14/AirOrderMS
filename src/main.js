import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import echarts from 'echarts'
import axios from 'axios'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(echarts)
// 全局引入echarts
Vue.prototype.$echarts = echarts
Vue.prototype.$axios = axios

new Vue({
  // 挂载router实例到vue实例上
  router,
  // 挂载vuex创建的store实例到vue实例上来
  store,
  render: h => h(App)
}).$mount('#app')
