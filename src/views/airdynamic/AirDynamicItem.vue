<!-- 航班动态详情租价——预留查找插槽（根据始发地和事件进行查找）以及图表插槽 -->
<template>
  <div>
    <slot name="search"></slot>
    <slot name="echarts"></slot>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  }
}

</script>
<style  scoped>
</style>
