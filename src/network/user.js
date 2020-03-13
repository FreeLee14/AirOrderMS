import { request } from './request'
// 封装注册的异步请求
export function registUser (trans, data) {
  return request({
    url: '/airorderms/user',
    method: 'post',
    // 针对后端接收数据的请求头类型进行修改
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: trans,
    data: data
  })
}
// 封装登录的异步请求,不定义method默认为get请求,登录时需要携带令牌进行验证,以及是否登录的状态
export function loginUser (name, password, token, isLogin) {
  return request({
    url: '/airorderms/user',
    params: {
      name,
      password,
      token,
      isLogin
    }
  })
}
// 封装注销的异步请求，将redis缓存的令牌进行清空
export function logoutUser (name) {
  return request({
    url: '/airorderms/user',
    method: 'delete',
    params: {
      name
    }
  })
}
