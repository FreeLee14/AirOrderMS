<!-- 侧边栏父组件 -->
<template>
  <div id="side-bar">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'sidebar',
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
  #side-bar {
    /* background-color: rgb(167, 190, 190); */
  }
</style>
