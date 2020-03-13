import axios from 'axios'

export function request (config) {
  const instance = axios.create({
    // 设置根路径为访问本地的后端接口
    baseURL: 'http://localhost:8081'
    // 设定超时时间
    // timeout: 5000
  })
  // 设置axios拦截器(请求拦截)
  instance.interceptors.request.use(config => {
    return config
  }, err => {
    console.log(err)
  })
  // 设置响应拦截
  instance.interceptors.response.use(config => {
    // 响应时我们只需要获取到返回体中的data数据即可
    return config.data
  }, err => {
    console.log(err)
  })
  // 发送真正的网络请求，这里返回的其实是一个promise对象，所以我们在使用这个request函数时，在进行回调函数的定义
  return instance(config)
}
