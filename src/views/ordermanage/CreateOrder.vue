<!-- 此为创建订单组件 -->
<template>
  <div>
    <el-form :model="order" ref="order">
      <div class="left">
        <el-card class="passenger" shadow="hover">
          <table class="table">
            <tr>
              <td>
                <el-form-item label="乘机人姓名:">
                  <el-input v-model="order.passName" size="mini"></el-input>
                </el-form-item>
              </td>
              <td>
                <el-form-item label="乘机人电话:">
                  <el-input v-model="order.passPhone" size="mini"></el-input>
                </el-form-item>
              </td>
            </tr>
            <tr>
              <td>
                <el-form-item label="乘机人身份证号:">
                  <el-input v-model="order.passIdcard" size="mini"></el-input>
                </el-form-item>
              </td>
            </tr>
          </table>
        </el-card>
        <el-card class="user" shadow="hover">
          <table class="table">
            <tr>
              <td>
                <el-form-item label="订单联系人:">
                  <el-input :disabled="true" v-model="orderPersonName" size="mini"></el-input>
                </el-form-item>
              </td>
              <td>
                <el-form-item label="联系人电话:">
                  <el-input :disabled="true" v-model="orderPersonPhone" size="mini"></el-input>
                </el-form-item>
              </td>
            </tr>
            <tr>
              <td>
                <el-form-item label="联系人邮箱:">
                  <el-input :disabled="true" v-model="orderPersonEmail"  size="mini"></el-input>
                </el-form-item>
              </td>
            </tr>
          </table>
        </el-card>
      </div>
      <div class="right">
        <!-- 使用卡片样式 -->
        <el-card class="ticket" shadow="hover">
          <div class="name"><i class="el-icon-s-promotion">菲航航空&nbsp;{{ticketInfo.flight}}</i></div>
          <div class="starttime"><font class="font">{{ticketInfo.startTime}}</font></div>
          <div class="consume">{{ticketInfo.consume}}</div>
          <div class="endtime"><font class="font">{{ticketInfo.endTime}}</font></div>
          <div class="departure">{{ticketInfo.departure}}</div>
          <div class="destination">{{ticketInfo.destination}}</div>
          <div class="money"><font class="font">￥{{ticketInfo.money}}</font></div>
        </el-card>
        <div class="btn">
          <el-checkbox v-model="agree">已阅读购票须知</el-checkbox>
          <el-button :disabled="!agree" type="warning" round @click="next">下一步</el-button>
          <!-- <el-button type="success" @click="test">测试</el-button> -->
        </div>
      </div>
    </el-form>
  </div>
</template>

<script>
import qs from 'qs'
import { createOrder } from 'network/order'
// 定义订单编号（根据当前时间戳进行生成）
var time = new Date()

export default {
  data () {
    return {
      order: {
        userId: localStorage.getItem('id'),
        ticketId: 0,
        money: 0,
        // 乘机人数，默认是一个，后期要进行拓展功能可添加多个乘客
        number: 1,
        // 在创建订单后，将状态置为订购中
        status: 1,
        passName: '',
        passPhone: '',
        passIdcard: '',
        // 根据当前日期生成订单号
        orderId: 'Air' + '' + time.getFullYear() + '' + time.getMonth() + 1 + '' + time.getDay()
      },
      orderPersonName: localStorage.getItem('name'),
      orderPersonEmail: localStorage.getItem('email'),
      orderPersonPhone: localStorage.getItem('phone'),
      ticketInfo: this.$route.query,
      agree: false
    }
  },

  components: {},

  mounted () {
    console.log(this.ticketInfo)
    this.order.ticketId = this.ticketInfo.id
    this.order.money = this.ticketInfo.money
  },

  computed: {
  },

  methods: {
    // test () {
    //   this.$store.commit('changeActive')
    //   this.$router.replace({
    //     path: '/ordermain/ensureOrder'
    //     // query: this.order
    //   })
    // },
    next () {
      // 执行保存订单的异步请求
      createOrder(
        [
          function (data) {
            return qs.stringify(data)
          }
        ],
        this.order
      ).then(res => {
        console.log(res)
        if (res) {
          console.log(this.order)
          this.$router.replace({
            path: '/ordermain/ensureOrder',
            query: {
              order: this.order,
              ticketInfo: this.ticketInfo,
              orderPerson: {
                orderPersonName: this.orderPersonName,
                orderPersonEmail: this.orderPersonEmail,
                orderPersonPhone: this.orderPersonPhone
              }
            }
          })
          this.$store.commit('changeActive')
        }
      }).catch(fail => {
        console.log(fail)
        this.$message({
          message: '下单失败',
          type: 'warning'
        })
      })
    }
  }
}

</script>
<style  scoped>
.left{
  /* background-color: aqua; */
  float: left;
  width: 65%;
  height: 700px;
}
.right{
  /* background-color: blue; */
  height: 700px;
  float: left;
}
.passenger{
  background-color: #F2F6FC;
  width: 50%;
  height: 250px;
  position: relative;
  left: 70px;
  top: 50px;
}
.user{
  width: 50%;
  background-color: #F2F6FC;
  position: relative;
  top: 200px;
  left: 70px;
  height: 250px;
}
.ticket{
  background-color: #F2F6FC;
  position: relative;
  top: 50px;
  left: -70px;
  width: 520px;
  height: 250px;
}
.table{
  width: 400px;
  height: 150px;
}
.name{
  position: relative;
  top: 100px;
  left: -15px;
  float: left;
}
.starttime{
  position: relative;
  top: 60px;
  left: 10px;
  float: left;
}
.consume{
  position: relative;
  float: left;
  top: 65px;
  left: 40px;
}
.endtime{
  position: relative;
  float: left;
  top: 60px;
  left: 80px;
}
.departure{
  position: relative;
  float: left;
  top: 120px;
  left: -140px;
}
.destination{
  position: relative;
  float: left;
  top: 120px;
  left: -5px;
}
.money{
  position: relative;
  top: 160px;
  left: 20px;
  float: left;
}
.font{
  font-family: PingFang SC;
  font-size: 20px;
  float: left;
  font-weight: bold;
}
.btn{
  width: 100px;
  position: relative;
  left: 300px;
  top: 380px;
}
</style>
