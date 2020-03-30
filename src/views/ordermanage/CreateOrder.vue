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
                  <el-input :disabled="true" v-model="order.orderPersonName" size="mini"></el-input>
                </el-form-item>
              </td>
              <td>
                <el-form-item label="联系人电话:">
                  <el-input :disabled="true" v-model="order.orderPersonPhone" size="mini"></el-input>
                </el-form-item>
              </td>
            </tr>
            <tr>
              <td>
                <el-form-item label="联系人邮箱:">
                  <el-input :disabled="true" v-model="order.orderPersonEmail"  size="mini"></el-input>
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
      </div>
    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      order: {
        userId: 0,
        ticketId: 0,
        money: 0,
        // 乘机人数，默认是一个，后期要进行拓展功能可添加多个乘客
        number: 1,
        // 在创建订单后，将状态置为订购中
        status: 1,
        passName: '',
        passPhone: '',
        passIdcard: '',
        orderPersonName: localStorage.getItem('name'),
        orderPersonEmail: localStorage.getItem('email'),
        orderPersonPhone: localStorage.getItem('phone')
      },
      ticketInfo: this.$route.query
    }
  },

  components: {},

  mounted () {
    console.log(this.ticketInfo)
  },

  computed: {},

  methods: {}
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
  left: 150px;
  top: 50px;
}
.user{
  width: 50%;
  background-color: #F2F6FC;
  position: relative;
  top: 200px;
  left: 150px;
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
</style>
