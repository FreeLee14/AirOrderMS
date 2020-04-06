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
// 封装进行精准查询的异步请求
export function findByConditions (trans, data) {
  return request({
    url: '/airorderms/ticket',
    method: 'post',
    // 针对后端接受表单数据的类型，修改请求头中传递数据的类型
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: trans,
    data: data
  })
}
// 封装分页查询所有机票信息的异步请求
export function showAllByPage (currentPage, pageCounts) {
  return request({
    url: '/airorderms/ticket/' + currentPage + '/' + pageCounts
  })
}
