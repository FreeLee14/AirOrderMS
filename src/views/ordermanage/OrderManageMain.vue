<!-- 我是订单管理主组件 -->
<template>
  <div>
    <OrderManageItem >
      <!-- 进行父子组件通信使用v-bind -->
      <OrderTable v-bind:attribute="orders"></OrderTable>
    </OrderManageItem>
  </div>
</template>

<script>

import OrderManageItem from 'views/ordermanage/OrderManageItem'
import OrderTable from 'views/ordermanage/OrderTable'
import { findAllOrder } from 'network/order'
export default {
  data () {
    return {
      orders: []
    }
  },

  components: {
    OrderManageItem,
    OrderTable
  },

  computed: {},

  mounted () {
    this.$nextTick(function () {
      findAllOrder(localStorage.getItem('id'))
        .then(res => {
          this.orders = res
          console.log(this.orders)
        })
    })
  },

  methods: {}
}

</script>
<style scoped>
</style>
