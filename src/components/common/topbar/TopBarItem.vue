<!-- 顶栏的元素内容 -->
<template>
  <div class="top-bar-item" >
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'topbaritem',
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
  .top-bar-item {
    flex: 1;
    text-align: center;
    height: 49px;
    font-size: 14px;
    margin-left: 10px;
  }
  .top-bar-item img {
    width: 24px;
    height: 24px;
    margin-top: 3px;
    vertical-align: middle;
    margin-bottom: 2px;
  }
</style>
