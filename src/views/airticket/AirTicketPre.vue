<!-- 机票业务的机票预定组件 -->
<template>
  <div>
    <ScrollBarMain>
      <ScrollBarItem></ScrollBarItem>
    </ScrollBarMain>
    <AirTicketPreItem>
      <Search slot="search"></Search>
      <MainNominate slot="nominate"></MainNominate>
    </AirTicketPreItem>
  </div>
</template>

<script>
// 引入滚动条组件
import ScrollBarMain from 'components/common/scrollbar/ScrollBarMain'
import ScrollBarItem from 'components/common/scrollbar/ScrollBarItem'
// 引入机票预定主要内容组件
import AirTicketPreItem from 'views/airticket/AirTicketPreItem'
// 引入查询机票组件
import Search from 'components/content/search/Search'
// 引入推荐组件
import MainNominate from 'components/content/mainbar/MainNominate'
export default {
  data () {
    return {
    }
  },

  components: {
    ScrollBarMain,
    ScrollBarItem,
    AirTicketPreItem,
    Search,
    MainNominate
  },

  computed: {},

  methods: {}
}

</script>
<style lang='scss' scoped>
</style>
