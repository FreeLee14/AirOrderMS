<!-- 登录组件 -->
<template>
  <div>
    <!-- 通过dialog组件进行登录框弹出 -->
    <el-dialog title="用户登录" :visible.sync="dialogFormVisible" :before-close="handleClose" width="20%">
      <el-form :model="login" size="medium">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="login.name" autocomplete="off" placeholder="请输入手机号或邮箱地址"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input type="password" v-model="login.password" autocomplete="off" placeholder="请输密码"></el-input>
        </el-form-item>
        <el-form-item label-position="left" :label-width="formLabelWidth">
            <div class="regist"><el-link @click="toRegist" type="primary">立即注册</el-link></div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle">取 消</el-button>
        <el-button type="primary" @click="ensure">确 定</el-button>
        <el-button @click="forgetPassword">忘记密码</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'login',
  data () {
    return {
      dialogFormVisible: this.$route.query.dialogFormVisible,
      login: {
        name: '',
        password: ''
      },
      formLabelWidth: '20%'
    }
  },

  components: {
  },

  computed: {
  },

  methods: {
    // 确认登录方法
    ensure () {
      this.dialogFormVisible = false
      this.$router.replace('/')
      console.log(this.form.name)
      console.log(this.form.region)
      console.log(this.form.password)
    },
    // 取消登录方法
    cancle () {
      this.dialogFormVisible = false
      this.$router.replace('/')
    },
    // 关闭提示框前的方法
    handleClose () {
      this.$confirm('确认关闭？')
        .then(_ => {
          // 如果确定关闭，再将路由路基恢复为根路径
          this.$router.replace('/')
        })
        .catch(_ => {})
    },
    // 忘记密码的方法定义
    forgetPassword () {
    },
    // 去注册方法
    toRegist () {
      // 请求注册组件
      this.$router.replace({
        path: '/regist'
      })
    }
  }
}
</script>
<style scoped>
.link1{
  position: relative;;
  left:10px;
  top:10px;
}
.regist{
  float: right;
}
</style>
