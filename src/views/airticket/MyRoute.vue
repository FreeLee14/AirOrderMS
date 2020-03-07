<!-- 我的行程组件 -->
<template>
  <div>
    <MyRouteItem></MyRouteItem>
  </div>
</template>

<script>
// 引入“我的行程详情”组件
import MyRouteItem from 'views/airticket/MyRouteItem'

export default {
  data () {
    return {
    }
  },

  components: {
    MyRouteItem
  },

  computed: {},

  methods: {}
}

</script>
<style lang='scss' scoped>
</style>
