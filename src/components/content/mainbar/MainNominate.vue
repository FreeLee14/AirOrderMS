<!-- 默认主页MainItem.Vue显示的推荐机票信息的子组件 -->
<template>
  <div class="main-nominate">
    <h1>我是推荐部分</h1>
    <div v-for="item in 8 " :key="item" class="item-nominate">
      <span class="departure">北 京 <i class="el-icon-s-promotion"></i></span>
      <span class="destination">天 津</span>
      <span class="money">￥256</span>
      <el-button class="buying" type="warning" @click="buying" round>立 抢</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  },

  components: {},

  computed: {},

  methods: {
    buying () {
      alert('点击抢购')
    }
  }
}

</script>
<style scoped>
.departure{
  position: relative;
  top: 20px;
  left: 50px;
}
.destination{
  position: relative;
  top: 100px;
  left: -7px;
}
.money{
  position: relative;
  top: 20px;
  left: 150px;
}
.buying{
  position: relative;
  top:100px;
  left: 90px;
}
.main-nominate{
  /* background-color: tomato; */
  height: 500px;
}
.item-nominate{
  background-color: #EBEEF5;
  float: left;
  position: relative;
  left: 20px;
  width: 350px;
  height: 150px;
  margin-left: 20px;
  margin-top: 20px;
}
</style>
