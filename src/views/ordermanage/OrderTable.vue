<!-- 订单管理表组件 -->
<template>
  <div class="order">
    <el-table
      :data="order"
      border
      style="width: 100%">
      <el-table-column
        prop="flight"
        label="航班"
        width="150">
      </el-table-column>
      <el-table-column
        prop="seat"
        label="座位"
        width="150">
      </el-table-column>
      <el-table-column
        prop="departure"
        label="出发地"
        width="150">
      </el-table-column>
      <el-table-column
        prop="destination"
        label="目的地"
        width="150">
      </el-table-column>
      <el-table-column
        prop="date"
        label="日期"
        width="300">
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
export default {
  data () {
    return {
      order: [
        {
          flight: 'z901',
          seat: 'a02',
          departure: '石家庄',
          destination: '北京',
          date: '2020-02-03'
        }
      ]
    }
  },

  components: {},

  computed: {},

  methods: {
    // 查询方法
    search (row) {
      console.log(row)
    },
    // 退订方法
    unsubscribe (row) {
      console.log(row)
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
