<!-- 机票预订主题信息组件 -->
<template>
  <div>
    <!-- 设定添加查询机票组件的插槽和推荐模块的插槽 -->
    <slot name="search"></slot>
    <slot name="nominate"></slot>
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
<style scoped>

</style>
