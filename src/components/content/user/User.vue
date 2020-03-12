<!-- 用户组件 -->
<template>
  <div>
    <div v-if="isLogin === 0">
      <el-button round @click="login">登录</el-button>
      <el-button round @click="regist">注册</el-button>
      <el-button round @click="main">主页</el-button>
    </div>
    <div v-else>
      <div class="info"><h1>hi！{{name}}</h1></div>
      <div class="logout"><el-link @click="logout" type="info">注销</el-link></div>
      <div class="main"><el-link  @click="main" type="info">主页</el-link></div>
    </div>
  </div>
</template>

<script>
// 引入封装的注销异步方法
import { logoutUser } from 'network/user'

export default {
  data () {
    return {}
  },

  components: {},

  computed: {
    // 获取公共管理仓库中的登录状态,只有在计算属性中定义才能动态的获取更新后的数据
    isLogin () {
      return this.$store.state.isLogin
    },
    name () {
      return this.$store.state.name
    }
  },
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
    },
    logout () {
      logoutUser(this.$store.state.name)
        .then(res => {
          if (res) {
            setTimeout(() => {
              this.$message({
                message: '您已注销'
              })
            }, 1000)
            this.$store.commit('logoutState')
          } else {
            setTimeout(() => {
              this.$message({
                message: '注销失败',
                type: 'warning'
              })
            }, 1000)
          }
        })
    }
  }
}

</script>
<style scoped>
.logout{
  float: right;
  position: relative;
  left: 20px;
  top: 11px;
}
.info{
  float: left;
  position: relative;
  top: -17px
}
.main{
  float: right;
  position: relative;
  left: 15px;
  top: 11px;
}
</style>
