<!-- 此为创建、下单、支付成功的基本订单组件 -->
<template>
  <div>
    <el-steps :active="active" class="steps" >
      <el-step title="步骤 1" description="创建订单"></el-step>
      <el-step title="步骤 2" description="确认订单并支付"></el-step>
      <el-step title="步骤 3" description="支付成功"></el-step>
    </el-steps>
    <keep-alive><router-view/></keep-alive>
  </div>
</template>

<script>
export default {
  data () {
    return {
      // 因为本组件使用渲染完毕后的钩子函数又进行了一次路由跳转，所以我们把上一次路由跳转的数据在执行新的路由跳转之前先赋值到当前组件的对象当中
      tickInfo: this.$route.query
    }
  },

  components: {},

  computed: {
    active () {
      return this.$store.state.active
    }
  },
  mounted () {
    this.$nextTick(function () {
      this.init()
    })
  },

  methods: {
    init () {
      this.$router.replace({
        path: '/ordermain/createOrder',
        // 再将机票显示的页面传递过来的机票信息通过路由传递到创建订单组件中去
        query: this.tickInfo
      })
    }
  }
}

</script>
<style  scoped>
.steps{
  width: 1500px;
}
</style>
