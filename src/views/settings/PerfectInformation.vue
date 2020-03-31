<!-- 信息维护组件，依附在settings上 需要进行头像的上传，完善姓名、性别、身份证号、出生年月、家庭住址 有保存按钮、修改按钮  -->
<template>
  <div>
    <el-upload
      action="#"
      list-type="picture-card"
      :auto-upload="false"
      :on-change="savePhoto">
      <img v-if="selfInfo.imgInfo!==''" width="150px" height="150px" :src="selfInfo.imgInfo">
        <i v-if="selfInfo.imgInfo===''" slot="default" class="el-icon-plus"></i>
        <div slot="file" slot-scope="{file}">
          <img
            class="el-upload-list__item-thumbnail"
            :src="file.url" alt=""
          >
          <span class="el-upload-list__item-actions">
            <span
              class="el-upload-list__item-preview"
              @click="handlePictureCardPreview(file)"
            >
              <i class="el-icon-zoom-in"></i>
            </span>
            <span
              v-if="!disabled"
              class="el-upload-list__item-delete"
              @click="handleDownload(file)"
            >
              <i class="el-icon-download"></i>
            </span>
            <span
              v-if="!disabled"
              class="el-upload-list__item-delete"
              @click="handleRemove(file)"
            >
              <i class="el-icon-delete"></i>
            </span>
          </span>
        </div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
    <div class="perfact">
      <el-form :model="selfInfo" ref="selfInfo" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input type="text" v-model="selfInfo.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="selfInfo.gender">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input type="text" v-model="selfInfo.idCard" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="出生年月" prop="birthday">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="selfInfo.birthday" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input type="text" v-model="selfInfo.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm(selfInfo)">提交</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
// 引入qs对axios封装成的数据解析为json格式的字符串
import qs from 'qs'
// 引入个人信息完善请求，以及查询个人详细信息的方法
import { prefactUser, findByName } from 'network/user'
export default {
  data () {
    return {
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,
      selfInfo: {
        name: '',
        gender: '',
        idCard: '',
        birthday: '',
        address: '',
        imgInfo: ''
      },
      mouth: '',
      day: ''
    }
  },

  components: {},

  computed: {},

  mounted () {
    // 等到页面元素渲染完毕，延迟1s向后台请求数据
    this.$nextTick(() => {
      setTimeout(() => {
        this.initInfo()
      }, 1000)
    })
  },

  methods: {
    // 上传图片的删除方法
    handleRemove (file) {
      console.log(file)
      this.dialogVisible = false
    },
    // 上传图片的放大方法
    handlePictureCardPreview (file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    // 上传后图片的下载方法
    handleDownload (file) {
      console.log(file)
    },
    // 上传图片的响应改变方法，将图片文件转换为base64格式
    savePhoto (file, fileList) {
      const reader = new FileReader() // html5读文件
      reader.readAsDataURL(file.raw) // 转BASE64
      reader.onload = (e) => {
        // 读取到的图片base64 数据编码 将此编码字符串传给后台即可
        var imgcode = e.target.result
        this.selfInfo.imgInfo = imgcode
      }
    },
    // 保存个人信息方法
    submitForm (selfInfo) {
      // 将我们获取到的出生年月转换为date类型数据，并获取具体的年月日，在拼接为字符串
      const birthdayDate = new Date(this.selfInfo.birthday)
      // 将获取的月份进行+1 得到真实月份并判断是否大于9 如果大于则保持原样，如果小于则在前面拼接一个0
      if (birthdayDate.getMonth() + 1 > 9) {
        this.mouth = birthdayDate.getMonth() + 1
      } else {
        this.mouth = '0' + (birthdayDate.getMonth() + 1)
      }
      // 将获取的日期判断是否大于9 如果大于则保持原样，如果小于则在前面拼接一个0
      if (birthdayDate.getDate() > 9) {
        this.day = birthdayDate.getDate()
      } else {
        this.day = '0' + birthdayDate.getDate()
      }
      // 重新组装出生年月
      this.selfInfo.birthday = birthdayDate.getFullYear() + this.mouth + this.day
      // 获取当前的性别
      const gender = this.selfInfo.gender
      // 如果是男，传递给后台数据为0  如果是女 传递给后台数据为1
      if (gender === '男') {
        this.selfInfo.gender = '0'
      } else if (gender === '女') {
        this.selfInfo.gender = '1'
      }
      console.log(this.selfInfo)
      console.log(this.$store.state.name)
      // 调用封装axios的完善信息的异步请求
      prefactUser(
        [
          function (data) {
            return qs.stringify(data)
          }
        ],
        {
          name: this.selfInfo.name,
          gender: this.selfInfo.gender,
          idCard: this.selfInfo.idCard,
          birthday: this.selfInfo.birthday,
          address: this.selfInfo.address,
          imgInfo: this.selfInfo.imgInfo
        },
        this.$store.state.name
      ).then(res => {
        console.log(res)
        if (res) {
          this.$message({
            message: '完善成功',
            type: 'success'
          })
        }
      }).catch(fail => {
        this.$message({
          message: '完善失败',
          type: 'warning'
        })
      })
    },
    // 初始化个人信息显示的函数
    initInfo () {
      const name = localStorage.getItem('name')
      if (name !== '') {
        findByName(name)
          .then(res => {
            setTimeout(() => {
              console.log(res)
              this.selfInfo.name = res.name
              if (res.gender === '0') {
                this.selfInfo.gender = '男'
              } else {
                this.selfInfo.gender = '女'
              }
              this.selfInfo.idCard = res.idCard
              this.selfInfo.birthday = res.birthday
              this.selfInfo.address = res.address
              this.selfInfo.imgInfo = res.imgInfo
              console.log(this.selfInfo)
            }, 1000)
          })
      }
    }
  }
}

</script>
<style  scoped>
.perfact{
  float: left;
  width: 500px;
  position: relative;
  top: 50px;
  left: -30px;
}
</style>
