<!-- 用户组件 -->
<template>
  <div>
    <el-button round @click="login">登录</el-button>
    <el-button round @click="regist">注册</el-button>
    <el-button round>注销</el-button>
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
    }
  }
}

</script>
<style scoped>
</style>
