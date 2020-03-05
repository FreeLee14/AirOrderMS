<!-- 机票业务显示页面 -->
<template>
  <div>
    <h1>我是机票业务页面</h1>
    <el-button  type="primary" round @click="preorder">机票预定</el-button>
    <el-button  type="primary" round @click="ticketdetail">查看机票</el-button>
    <el-button  type="primary" round @click="myroute">我的行程</el-button>
    <keep-alive><router-view/></keep-alive>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  },

  components: {},

  computed: {},

  methods: {
    preorder () {
      this.$router.replace({
        path: '/airticketmain/preorder'
      })
    },
    ticketdetail () {
      this.$router.replace({
        path: '/airticketmain/airticketdetail'
      })
    },
    myroute () {
      this.$router.replace({
        path: '/airticketmain/myroute'
      })
    }
  }
}

</script>
<style  scoped>
</style>
