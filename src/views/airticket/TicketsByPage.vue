<!-- 根据条件动态分页显示机票的组件 -->
<template>
  <div>
    <table>
      <tr>
        <div v-for="(item,index) in tableData" :key="index" class="item-nominate">
          <h2>{{item.name}}</h2>
        </div>
     </tr>
     <tr>
       <!-- current-change是当前标签发生变化时触发此方法 -->
       <el-pagination
        :hide-on-single-page="value"
        :page-size="pageSize"
        :total="tableDateLength"
        @current-change="changePage"
        layout="prev, pager, next">
      </el-pagination>
     </tr>
    </table>
  </div>
</template>

<script>

export default {
  data () {
    return {
      // 控制分页当只有一页的时候不显示分页控件
      value: false,
      // 当前页数
      page: 0,
      // 每一页显示的数量
      pageSize: 8,
      tableData: [{
        date: '2016-05-03',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-02',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-04',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-01',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-08',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-06',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-07',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-07',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }]
    }
  },

  components: {},

  mounted () {
    this.$nextTick(function () {
      if (this.tableData.length <= 8) {
        this.value = true
      }
    })
  },

  computed: {
    tableDateLength () {
      return this.tableData.length
    }
  },
  // 本组件方法实现，首先我们进行默认请求后台接口查询到所有数据，把所有数据赋值给分页组件，这样可以显示准确的页数
  // 之后我们还有一个请求是默认查询第一页数据的请求，将默认显示在第一页，之后在分页组件中定义的changePage方法则依次去执行后台分页查询的方法
  methods: {
    changePage (val) {
      this.page = val
      console.log(this.page)
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
</style>
