<!-- 滚动条子组件 -->
<template>
  <div class="scroll-bar-item">
    <div class="textScroll" @mousemove="pauseAn" @mouseout="startAn" >
      <div class="scroll" :style="{marginLeft:  marginLeft + 'px' }">
          <span @click="itemClick(item,$event)" v-for="(item,index) in datas" :key="index" class="content">
              <span class="title">
              【特推公告：{{item.title}}】
              </span>
              <span class="text">{{item.text}}</span>
          </span>
      </div>
    </div>
  </div>
</template>

<script>
const datas = [
  { title: '我是标题', text: '我是内容' },
  { title: '我是标题', text: '我是内容' },
  { title: '我是标题', text: '我是内容' },
  { title: '我是标题', text: '我是内容' },
  { title: '我是标题', text: '我是内容' }
]
export default {
  name: 'scrollbaritem',

  data () {
    return {
      datas,
      marginLeft: 1600,
      prevLeft: 0,
      an: '',
      place: ''
    }
  },
  props: {
    data: {
      type: Array
    },
    time: {
      type: Number,
      default: 50
    },
    placement: {
      type: String,
      default: 'bottom'
    }
  },

  components: {},

  computed: {
  },

  mounted () {
    this.startAn()
  },
  beforeDestroy () {
    this.stopAn()
  },

  methods: {
    startAn () { // 开始
      const _this = this
      // const width = document.querySelector('.scroll').offsetWidth
      this.an = setInterval(function () {
        if (_this.marginLeft < -2500) {
          _this.marginLeft = 1600
        }
        _this.marginLeft -= 2
      }, _this.time)
    },
    stopAn () { // 停止
      this.prevLeft = this.marginLeft
      this.marginLeft = 0
      clearInterval(this.an)
      this.$emit('on-stop-An')
    },
    pauseAn () { // 暂停动画
      clearInterval(this.an)
    },
    itemClick (item, e) {
      this.$emit('on-item-click', item)
    }
  }
}

</script>
<style   scoped>
.scroll-bar-item{
  /* 隐藏底部滚动条 */
  overflow-x: hidden
}
.textScroll{
    position: relative;
    bottom: 0;
    left: 0;
    width: 100%;
}
.content{
    width: 100%;
    word-wrap: normal;
    margin-right: 140px;
}
.title{
    color: #ad6f0c;
    font-weight: bold;
}
.text {
    color: #805518;
}
.scroll{
  height: 32px;
  line-height: 28px;
  padding: 4px 0;
  white-space: nowrap;
}

</style>
