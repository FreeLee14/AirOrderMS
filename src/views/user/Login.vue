<!-- 登录组件 -->
<template>
  <div>
    <!-- 通过dialog组件进行登录框弹出 -->
    <el-dialog title="用户登录" :visible.sync="dialogFormVisible" :before-close="handleClose">
      <el-form :model="form">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="" :label-width="formLabelWidth">
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle">取 消</el-button>
        <el-button type="primary" @click="ensure">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      dialogFormVisible: this.$route.query.dialogFormVisible,
      form: {
        name: '',
        region: '',
        password: ''
      },
      formLabelWidth: '120px'
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
    }
  }
}
</script>
<style scoped>
</style>
