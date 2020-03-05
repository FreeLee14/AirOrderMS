<!-- 侧栏具体内容 -->
<template>
  <div class="side-bar-item">
    <slot></slot>
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

  methods: {
  }
}

</script>
<style scoped>
  .side-bar-item{
    height: 185px;
    width: 260px;
    position: relative;
  }
</style>
