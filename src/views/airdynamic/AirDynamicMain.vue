<!-- 航班动态主组件 -->
<template>
  <div>
    <AirDynamicItem>
      <Search slot="search"></Search>
      <Echarts slot="echarts"></Echarts>
    </AirDynamicItem>
  </div>
</template>

<script>
import AirDynamicItem from 'views/airdynamic/AirDynamicItem'
// 引入查询机票组件
import Search from 'components/content/search/Search'
// 引入图表组件
import Echarts from 'components/content/echarts/Echarts'

export default {
  data () {
    return {
    }
  },

  components: {
    AirDynamicItem,
    Search,
    Echarts
  },

  computed: {},

  methods: {}
}

</script>
<style lang='scss' scoped>
</style>
