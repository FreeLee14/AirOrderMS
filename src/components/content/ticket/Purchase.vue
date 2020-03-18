<!-- 机票购买组件 -->
<template>
  <div>
    <div class="bottom">
        <div class="name"><i class="el-icon-s-promotion">菲航航空&nbsp;{{ticketInfo.flight}}</i></div>
        <div class="starttime"><font class="font">{{ticketInfo.startTime}}</font></div>
        <div class="consume">{{ticketInfo.consume}}</div>
        <div class="endtime"><font class="font">{{ticketInfo.endTime}}</font></div>
        <div class="departure">{{ticketInfo.departure}}</div>
        <div class="destination">{{ticketInfo.destination}}</div>
        <div class="money"><font class="font">￥{{ticketInfo.money}}</font></div>
        <div class="btn"><el-button type="warning" @click="purchase(ticketInfo.id)" round>订票</el-button></div>
    </div>
  </div>
</template>

<script>

export default {
  props: ['ticketView'],
  data () {
    return {
      ticketInfo: this.ticketView
    }
  },

  components: {},

  computed: {},

  methods: {
    purchase (id) {
      // 在进行订票时会进行校验当前用户信息，如果没有登录则提示登录，如果完善信息则提示需要完善信息
      console.log(id)
    }
  }
}

</script>
<style  scoped>
.bottom{
  background-color: #EBEEF5;
  position: relative;
  top: 150px;
  left: 103px;
  width: 1340px;
  height: 250px;
}
.name{
  position: relative;
  top: 100px;
  left: 50px;
  float: left;
}
.starttime{
  position: relative;
  top: 60px;
  left: 300px;
  float: left;
}
.consume{
  position: relative;
  float: left;
  top: 65px;
  left: 370px;
}
.endtime{
  position: relative;
  float: left;
  top: 60px;
  left: 450px;
}
.departure{
  position: relative;
  float: left;
  top: 120px;
  left: 155px;
}
.destination{
  position: relative;
  float: left;
  top: 120px;
  left: 380px;
}
.money{
  position: relative;
  top: 90px;
  left: 500px;
  float: left;
}
.btn{
  position: relative;
  top: 85px;
  left: 600px;
  float: left;
}
.font{
  font-family: PingFang SC;
  font-size: 20px;
  float: left;
  font-weight: bold;
}

</style>
