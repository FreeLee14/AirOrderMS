<!-- 查看机票组件 -->
<template>
  <div>
    <AirTicketDetailItem>
      <div slot="search" class="search">
        <Search></Search>
      </div>
    </AirTicketDetailItem>
  </div>
</template>

<script>
import AirTicketDetailItem from 'views/airticket/AirTicketDetailItem'
// 引入查询机票组件
import Search from 'components/content/search/Search'
export default {
  data () {
    return {
    }
  },

  components: {
    AirTicketDetailItem,
    Search
  },

  computed: {},

  methods: {}
}

</script>
<style  scoped>
.search{
  position: relative;
  top: 40px;
}
</style>
