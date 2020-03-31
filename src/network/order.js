import { request } from './request'

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
