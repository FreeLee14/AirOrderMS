<!-- 根据条件动态分页显示机票的组件 -->
<template>
  <div>
    <table>
      <tr>
        <div v-for="(item,index) in tableData" :key="index" class="item-nominate">
          <span class="departure">{{item.departure}} <i class="el-icon-s-promotion"></i></span>
          <span class="destination">{{item.destination}}</span>
          <span class="money">{{item.money}}</span>
          <el-button class="buying" type="warning" @click="buying(item.id)" round>立 抢</el-button>
        </div>
     </tr>
     <tr>
       <!-- current-change是当前标签发生变化时触发此方法 -->
        <el-pagination
          :hide-on-single-page="value"
          :page-size="pageCounts"
          :total="tableDateLength"
          @current-change="changePage"
          layout="prev, pager, next">
        </el-pagination>
     </tr>
    </table>
  </div>
</template>

<script>
import { showAllByPage } from 'network/ticket'
export default {
  data () {
    return {
      // 控制分页当只有一页的时候不显示分页控件
      value: false,
      // 当前页数
      currentPage: 0,
      // 每一页显示的数量
      pageCounts: 8,
      // 获取动态的分页数据
      tableData: [],
      // 总记录数（根据后台返回来确定）
      dateLength: 0
    }
  },

  components: {},

  mounted () {
    this.$nextTick(function () {
      // 页面渲染完毕加载分页查询的所有机票简略信息
      this.initTicketInfo()
    })
  },

  computed: {
    // 通过动态属性响应式的记录总记录数
    tableDateLength () {
      return this.dateLength
    }
  },
  // 本组件方法实现，首先我们进行默认请求后台接口查询到所有数据，把所有数据赋值给分页组件，这样可以显示准确的页数
  // 之后我们还有一个请求是默认查询第一页数据的请求，将默认显示在第一页，之后在分页组件中定义的changePage方法则依次去执行后台分页查询的方法
  methods: {
    changePage (val) {
      // 由于mybaitsplus的分页插件自动处理的当前页数和记录数之间的关系，所以我们这里只需要传递正确的页数即可
      this.currentPage = val
      showAllByPage(this.currentPage, this.pageCounts)
        .then(res => {
          if (res !== null) {
            this.tableData = res.records
            // 每次翻页也要获取总记录数，进行一个动态的更新
            this.tableDateLength = res.total
          }
        })
    },
    initTicketInfo () {
      // 初始化加载页面时查询limit 0，8 的数据（前8条记录）
      showAllByPage(this.currentPage, this.pageCounts)
        .then(res => {
          if (res !== null) {
            this.tableData = res.records
            this.dateLength = res.total
          }
          if (this.tableData.length <= 8) {
            this.value = true
          }
        })
        .catch(fail => {
          console.log(fail)
        })
    },
    // 立抢请求
    buying (id) {
      this.$router.replace({
        path: '/buyticket/' + id
      })
    }
  }
}

</script>
<style  scoped>
.item-nominate{
  background-color: #EBEEF5;
  float: left;
  position: relative;
  left: 20px;
  width: 350px;
  height: 200px;
  margin-left: 20px;
  margin-top: 20px;
}
.page{
  position: relative;
  top: 500px;
  left: -1450px;
}
.departure{
  position: relative;
  top: 20px;
  left: 50px;
}
.destination{
  position: relative;
  top: 100px;
  left: -7px;
}
.money{
  position: relative;
  top: 20px;
  left: 150px;
}
.buying{
  position: relative;
  top:100px;
  left: 90px;
}
.page{
  position: relative;
}
</style>
