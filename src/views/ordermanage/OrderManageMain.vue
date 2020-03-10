<!-- 我是订单管理主组件 -->
<template>
  <div>
    <OrderManageItem>
      <OrderTable></OrderTable>
    </OrderManageItem>
  </div>
</template>

<script>

import OrderManageItem from 'views/ordermanage/OrderManageItem'
import OrderTable from 'views/ordermanage/OrderTable'

export default {
  data () {
    return {
    }
  },

  components: {
    OrderManageItem,
    OrderTable
  },

  computed: {},

  methods: {}
}

</script>
<style scoped>
</style>
