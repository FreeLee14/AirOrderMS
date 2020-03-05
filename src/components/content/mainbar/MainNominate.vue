<!-- 默认主页MainItem.Vue显示的推荐机票信息的子组件 -->
<template>
  <div class="main-nominate">
    <h1>我是推荐部分</h1>
    <div v-for="item in 8 " :key="item" class="item-nominate">机票</div>
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

  methods: {}
}

</script>
<style scoped>
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
  height: 200px;
  margin-left: 20px;
  margin-top: 20px;
}
</style>
