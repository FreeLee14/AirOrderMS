<!-- 此为确认订单组件 -->
<template>
  <div>
    <el-collapse-transition>
    <div v-show="show" v-if="show">
      <div class="card">
        <el-card >
          <table class="table">
            <tr>
              <th>航班</th>
              <th>始发地</th>
              <th>目的地</th>
              <th>起飞时间</th>
              <th>到达时间</th>
              <th>价格</th>
            </tr>
            <tr>
              <td>{{ticketInfo.ticketInfo.flight}}</td>
              <td>{{ticketInfo.ticketInfo.departure}}</td>
              <td>{{ticketInfo.ticketInfo.destination}}</td>
              <td>{{ticketInfo.ticketInfo.startTime}}</td>
              <td>{{ticketInfo.ticketInfo.endTime}}</td>
              <td>{{ticketInfo.ticketInfo.money}}</td>
            </tr>
          </table>
        </el-card>
      </div>
      <div class="card">
        <el-card >
          <table class="table">
            <tr>
              <th>乘机人姓名</th>
              <th>乘机人电话</th>
              <th>乘机人身份证号</th>
            </tr>
            <tr>
              <td>{{ticketInfo.order.passName}}</td>
              <td>{{ticketInfo.order.passPhone}}</td>
              <td>{{ticketInfo.order.passIdcard}}</td>
            </tr>
          </table>
        </el-card>
      </div>
      <div class="card">
        <el-card >
          <table class="table">
            <tr>
              <th>订单联系人</th>
              <th>联系人电话</th>
              <th>联系人邮箱</th>
            </tr>
            <tr>
              <td>{{ticketInfo.orderPerson.orderPersonName}}</td>
              <td>{{ticketInfo.orderPerson.orderPersonPhone}}</td>
              <td>{{ticketInfo.orderPerson.orderPersonEmail}}</td>
            </tr>
          </table>
        </el-card>
      </div>
      <div class="btn">
          <el-button  type="warning" round @click="ensureOrder">确认订单</el-button>
      </div>
    </div>
    </el-collapse-transition>
    <el-dialog
        title="请扫码付款"
        :visible.sync="dialogVisible"
        width="20%"
        :before-close="handleClose">
        <span><div id="qrCode" ref="qrCode" ></div></span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="cancle">取 消</el-button>
          <el-button type="primary" @click="ensure">确 定</el-button>
        </span>
      </el-dialog>
  </div>
</template>

<script>
// import QRCode from 'qrcodejs2'
import qs from 'qs'
import { ensureOrder } from 'network/order'
export default {
  data () {
    return {
      show: true,
      ticketInfo: this.$route.query,
      dialogVisible: false
    }
  },

  components: {},

  mounted () {
    setTimeout(() => {
      this.show = true
    }, 800)
    console.log(this.ticketInfo)
  },

  computed: {},

  methods: {
    // 这个确认订单方法是为了唤醒dialog弹出框而设定
    ensureOrder () {
      this.dialogVisible = true
      this.show = false
    },
    handleClose (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
          this.show = true
        })
        .catch(_ => {})
    },
    ensure () {
      // 调用确认订单异步请求函数
      ensureOrder(
        [
          function (data) {
            qs.stringify(data)
          }
        ],
        {
          orderId: this.ticketInfo.order.orderId,
          status: 2,
          ticketId: this.ticketInfo.order.ticketId
        }
      ).then(res => {
        // 如果成功则跳转到成功支付的页面
        if (res) {
          this.$router.replace({
            path: '/ordermain/paySuccess'
          })
          this.$store.commit('changeActive')
        }
      }).catch(fail => {
        console.log(fail)
        this.$message({
          message: '当前班次已无余票',
          type: 'warning'
        })
      })
      this.dialogVisible = false
      this.show = true
    },
    cancle () {
      this.dialogVisible = false
      this.show = true
    }
  }
}

</script>
<style  scoped>
.card{
  margin-top: 50px;
  width: 600px;
  position: relative;
  left: 450px;
  height: 200px;
}
.table{
  align-self: center;
  width: 550px;
  text-align: center;
  height: 100px;
}
.btn{
  width: 200px;
  position: relative;
  left: 1200px;
}
</style>
