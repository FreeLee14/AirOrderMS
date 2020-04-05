import { request } from './request'
// 创建订单的异步请求
export function createOrder (trans, data) {
  return request({
    url: '/airorderms/orderinfo',
    method: 'post',
    // 针对后端接受表单数据的类型，修改请求头中传递数据的类型
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: trans,
    data: data
  })
}
// 确定订单的异步请求
export function ensureOrder (trans, data) {
  return request({
    url: '/airorderms/orderinfo',
    method: 'patch',
    // 针对后端接受表单数据的类型，修改请求头中传递数据的类型
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: trans,
    params: data
  })
}
// 查看所有已完成订单的异步请求
export function findAllOrder (userId) {
  return request({
    url: '/airorderms/orderinfo',
    params: {
      userId
    }
  })
}
// 退订机票业务
export function deleteOrder (params) {
  return request({
    url: '/airorderms/orderinfo',
    method: 'delete',
    params: params
  })
}
