<!-- 机票购买组件 -->
<template>
  <div>
    <div v-if="typeof ticketsInfo !== 'undefined' " class="tickets" >
      <div v-for="(item,index) in ticketsInfo" :key="index" >
        <div class="name"><i class="el-icon-s-promotion">菲航航空&nbsp;{{item.flight}}</i></div>
        <div class="starttime"><font class="font">{{item.startTime}}</font></div>
        <div class="consume">{{item.consume}}</div>
        <div class="endtime"><font class="font">{{item.endTime}}</font></div>
        <div class="departure">{{item.departure}}</div>
        <div class="destination">{{item.destination}}</div>
        <div class="money"><font class="font">￥{{item.money}}</font></div>
        <div class="btn"><el-button type="warning" @click="purchase(item.id)" round>订票</el-button></div>
      </div>
    </div>
    <!-- 当父子组件通信的数据没有传递过来时就不显示这个模块 -->
    <div v-if="typeof ticketInfo !== 'undefined'" class="bottom">
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
  props: ['ticketView', 'ticketsView'],
  data () {
    return {
      ticketInfo: this.ticketView
    }
  },

  components: {},

  computed: {
    // ticketsView根据搜索组件筛选出的机票信息传递到当前购买组件,在此我们需要在计算属性中定义，因为我们会随时修改检索条件，所以需要响应式的改变机票信息
    ticketsInfo () {
      return this.ticketsView
    }
  },

  mounted () {
    this.$nextTick(function () {
      console.log(this.ticketInfo)
      console.log(this.ticketsInfo)
    })
  },

  methods: {
    purchase (id) {
      // 在进行订票时会进行校验当前用户信息，如果没有登录则提示登录，如果完善信息则提示需要完善信息
      console.log(id)
      const isLogin = localStorage.getItem('isLogin')
      // 注意我们从localStorage中获取到的值都为字符串所以在进行对比时要注意写成字符串形式
      console.log(isLogin)
      if (isLogin === '1') {
        this.$router.replace({
          path: '/ordermain',
          query: this.ticketInfo
        })
      } else {
        this.$message({
          message: '您尚未登录，请先登录',
          type: 'warning'
        })
        // 跳转到登录组件
        this.$router.replace({
          path: '/login',
          query: {
            dialogFormVisible: true
          }
        })
      }
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
.tickets{
  background-color: #EBEEF5;
  position: relative;
  top: 50px;
  left: 103px;
  width: 1340px;
  height: 250px;
}
</style>
