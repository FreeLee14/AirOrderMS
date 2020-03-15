import { request } from './request'

// 封装查询推荐机票的异步请求
export function findRandom (address) {
  return request({
    url: '/airorderms/ticket',
    params: {
      address
    }
  })
}
// 封装点击抢订时的显示订票页面详细信息的异步请求
export function findById (id) {
  return request({
    url: '/airorderms/ticket/' + id
  })
}
