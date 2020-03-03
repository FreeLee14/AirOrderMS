<!-- 上边栏组件 -->
<template>
  <div id="top-bar">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'topbar',
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
  #top-bar {
    display: flex;
    background-color: #f6f6f6;
    position: fixed;
    left: 0;
    right: 0;
    top: 10;
  }
</style>
