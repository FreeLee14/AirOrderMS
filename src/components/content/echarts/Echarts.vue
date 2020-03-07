<!-- 使用Echarts构建的图表组件，用于显示对应的出发地和目的地之间不同班次的余票数量 -->
<template>
  <div id="echarts" class="echarts">
  </div>
</template>

<script>

let tickites = {
  // 航班班次
  flight: [],
  // 票数
  number: []
}
export default {
  data () {
    return {
      tickites
    }
  },

  components: {},

  computed: {},

  mounted () {
    // Vue渲染结束后加载echarts
    this.buildEcharts()
  },

  methods: {
    buildEcharts () {
      // 基于准备好的dom，初始化echarts实例
      const echarts = this.$echarts.init(document.getElementById('echarts'))
      echarts.setOption({
        title: {
          text: '航班余票动态显示'
        },
        tooltip: {},
        legend: {
          data: ['余票数量']
        },
        xAxis: {
          data: []
        },
        yAxis: {},
        series: [{
          name: '余票数量',
          type: 'bar',
          data: []
        }]
      })
      echarts.showLoading()
      // 模拟异步请求，实现异步请求填充图表数据，开发后端接口时在此处进行修改
      new Promise((resolve, reject) => {
        setTimeout(() => {
          tickites = {
            flight: ['z901', 'b707', 'h787', 'y510', 's200', 's303'],
            number: [5, 20, 36, 10, 10, 20]
          }
          // 成功执行的方法
          resolve(
            echarts.setOption({
              xAxis: {
                data: tickites.flight
              },
              series: [{
                // 根据名字对应到相应的系列
                name: '余票数量',
                data: tickites.number
              }]
            })
          )
        }, 3000)
      }).then(() => {
        echarts.hideLoading()
        console.log('成功')
      })
    }
  }
}

</script>
<style  scoped>
.echarts{
  height: 600px;
  width: 1000px;
  position: relative;
  top: 100px;
  left: 200px;
}
</style>
