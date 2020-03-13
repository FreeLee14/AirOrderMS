<!-- 查看机票组件主要内容 -->
<template>
  <div>
    <!-- 查看机票组件预留查询机票组件的插槽以及默认显示所有机票的组件的插槽 -->
    <slot name="search"></slot>
    <slot name="tickets"></slot>
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
