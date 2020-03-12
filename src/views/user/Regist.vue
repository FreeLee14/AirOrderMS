<!-- 注册组件 -->
<template>
  <div class="regist">
    <el-form :rules="rules" ref="regist"  label-width="80px" :model="regist">
      <el-form-item label="用户名" prop="name" :readonly="readonlyInput" @focus="cancelReadOnly">
        <el-input v-model="regist.name"></el-input>
      </el-form-item>
      <el-form-item label="电话" prop="phone" :readonly="readonlyInput" @focus="cancelReadOnly">
        <el-input v-model="regist.phone"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email" :readonly="readonlyInput" @focus="cancelReadOnly">
        <el-input v-model="regist.email"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password" :readonly="readonlyInput" @focus="cancelReadOnly">
        <el-input type="password" v-model="regist.password"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="ensurepassword" :readonly="readonlyInput" @focus="cancelReadOnly">
        <el-input type="password" v-model="regist.ensurepassword"></el-input>
      </el-form-item>
      <el-form-item>
        <div class="divRight"><el-checkbox v-model="agree">同意用户注册协议</el-checkbox></div>
      </el-form-item>
      <el-form-item >
          <!-- 添加disabled属性当同意用户注册协议时才可点击注册 -->
          <div class="divRight"><el-button @click="registForm('regist')" :disabled="!agree" type="primary">注 册</el-button></div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
// 引入qs对axios封装成的数据解析为json格式的字符串
import qs from 'qs'
// 引入网络请求函数_注册用户函数
import { registUser } from 'network/user'

export default {
  name: 'regist',
  data () {
    // 验证用户名不能为空
    var validUsername = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'))
      } else {
        callback()
      }
    }
    // 验证电话号码
    var validPhone = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入电话号码'))
      } else {
        callback()
      }
    }
    // 验证邮箱
    var validEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮箱'))
      } else {
        callback()
      }
    }
    // 第一次录入密码校验规则
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.regist.ensurepassword !== '') {
          this.$refs.regist.validateField('ensurepassword')
        }
        callback()
      }
    }
    // 第二次确认密码校验规则
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.regist.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      regist: {
        name: '',
        phone: '',
        email: '',
        password: '',
        ensurepassword: ''
      },
      agree: false,
      // 定义校验规则
      rules: {
        name: [
          { validator: validUsername, trigger: 'blur' }
        ],
        phone: [
          { validator: validPhone, trigger: 'blur' }
        ],
        email: [
          { validator: validEmail, trigger: 'blur' }
        ],
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        ensurepassword: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      },
      // 设定页面的表单输入款开始为只读属性，防止浏览器自动填充内容
      readonlyInput: true
    }
  },

  components: {},

  computed: {},

  methods: {
    // 当聚焦输入框是取消其只读属性
    cancelReadOnly () {
      this.readonlyInput = false
    },
    // 注册方法
    registForm (formName) {
      // 先进行整体表单的校验,进行校验时，表单内部的属性值必须都要有与之对应的校验方法
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 向后端api请求注册方法，将前端的数据使用qs转换为json字符串
          registUser(
            [
              function (data) {
                return qs.stringify(data)
              }
            ],
            {
              name: this.regist.name,
              password: this.regist.password,
              phone: this.regist.phone,
              email: this.regist.email,
              ensurepassword: this.regist.ensurepassword
            }
          ).then(res => {
            // 成功执行回调函数
            if (res) {
              this.$message({
                message: '恭喜你，注册成功',
                type: 'success'
              })
              // 注册成功延迟3s跳转至首页
              setTimeout(() => {
                this.$router.replace({
                  path: '/'
                })
              }, 3000)
            }
          }).catch(err => {
            // 失败执行的回调函数
            if (err) {
              this.$message({
                message: '注册失败',
                type: 'warning'
              })
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}

</script>
<style  scoped>
.regist{
  position: relative;
  left: 450px;
  top: 150px;
  width: 30%;
}
.divRight{
  position: relative;
  left: 100px;
}
</style>
