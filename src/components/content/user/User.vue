<!-- 用户组件 -->
<template>
  <div>
    <el-button round @click="login">登录</el-button>
    <el-button round @click="regist">注册</el-button>
    <el-button round @click="main">主页</el-button>
  </div>
</template>

<script>
export default {
  data () {
    return {}
  },

  components: {},

  computed: {},

  methods: {
    login () {
      // 请求登录的组件
      this.$router.replace({
        path: '/login',
        query: {
          dialogFormVisible: true
        }
      })
    },
    regist () {
      // 请求注册组件
      this.$router.replace({
        path: '/regist'
      })
    },
    main () {
      // 请求显示默认主页，因为时单页面富应用所以尽量将操作凝聚在按钮上
      this.$router.replace({
        path: '/'
      })
    }
  }
}

</script>
<style scoped>
</style>
