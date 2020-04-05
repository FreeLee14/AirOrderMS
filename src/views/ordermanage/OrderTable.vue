<!-- 订单管理表组件 -->
<template>
  <div class="order">
    <el-table
      :data="allOrders"
      border
      style="width: 95%">
      <el-table-column
        prop="orderId"
        label="订单号"
        width="150">
      </el-table-column>
      <el-table-column
        prop="seat"
        label="座位"
        width="150">
      </el-table-column>
      <el-table-column
        prop="money"
        label="金额"
        width="150">
      </el-table-column>
      <el-table-column
        prop="purchasetime"
        label="下单时间"
        width="200">
      </el-table-column>
      <el-table-column
        prop="invalidtime"
        label="出发时间"
        width="200">
      </el-table-column>
      <el-table-column
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="search(scope.row)" type="text" size="small">查看</el-button>
          <el-button @click="unsubscribe(scope.row)" type="text" size="small">退订</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { deleteOrder } from 'network/order'
export default {
  // 从父组件获取到的数据
  props: ['attribute'],
  data () {
    return {

    }
  },

  components: {},

  computed: {
    // 父子组件传递过来的值需要使用计算属性进行回显到子组件，才能实现响应式的显示
    allOrders () {
      return this.attribute
    }
  },

  methods: {
    // 查询方法
    search (row) {
      console.log(row)
      // console.log(row.ticketId)
    },
    // 退订方法
    unsubscribe (row) {
      console.log(row.ticketId)
      deleteOrder({
        userId: localStorage.getItem('id'),
        orderId: row.orderId
      }).then(res => {
        if (res) {
          this.$message({
            message: '退订成功',
            type: 'success'
          })
          window.reload()
        }
      })
    }
  }
}

</script>
<style  scoped>
.order{
  width: 1001px;
  position: relative;
  top: 100px;
  left: 200px;
}
</style>
