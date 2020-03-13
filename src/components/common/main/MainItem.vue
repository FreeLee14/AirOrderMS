<!-- 默认主体内容子组件 -->
<template>
  <div>
    <!-- 默认主页面为展示滚动图片和一些基础的票据信息 -->
     <el-carousel :interval="4000" type="card" height="300px">
      <el-carousel-item v-for="item in 6" :key="item">
        <h3 class="medium">{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
    <MainNominate></MainNominate>
  </div>
</template>

<script>
import MainNominate from 'components/content/mainbar/MainNominate'
export default {
  data () {
    return {
    }
  },

  components: {
    MainNominate
  },

  computed: {},
  methods: {}
}

</script>

<style  scoped>
.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
