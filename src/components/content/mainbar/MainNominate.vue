<!-- 默认主页MainItem.Vue显示的推荐机票信息的子组件 -->
<template>
  <div v-loading="loading" class="main-nominate">
    <div v-for="(item, index) in nominateinfos " :key="index" class="item-nominate">
      <span class="departure">{{item.departure}} <i class="el-icon-s-promotion"></i></span>
      <span class="destination">{{item.destination}}</span>
      <span class="money">{{item.money}}</span>
      <el-button class="buying" type="warning" @click="buying(item.id)" round>立 抢</el-button>
    </div>
  </div>
</template>

<script>
// 导入机票推荐异步请求函数
import { findRandom } from 'network/ticket'

export default {
  data () {
    return {
      nominateinfos: [
        {
          id: 0,
          departure: '',
          destination: '',
          money: ''
        }
      ],
      address: '天津',
      loading: true
    }
  },

  components: {},

  computed: {},

  mounted () {
    this.$nextTick(() => {
      this.initInfo()
    })
  },

  methods: {
    buying (id) {
      this.$router.replace({
        path: '/buyticket/' + id
      })
    },
    initInfo () {
      findRandom(this.address)
        .then(res => {
          if (res !== null) {
            this.loading = false
          }
          this.nominateinfos = res
        })
        .catch(fail => {

        })
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
