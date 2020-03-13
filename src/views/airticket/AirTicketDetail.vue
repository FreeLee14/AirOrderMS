<!-- 查看机票组件 -->
<template>
  <div>
    <AirTicketDetailItem>
      <div slot="search" class="search">
        <Search></Search>
      </div>
      <div slot="tickets" class="tickets">
        <h1>分页查询显示所有的数据</h1>
        <TicketsByPage ></TicketsByPage>
      </div>
    </AirTicketDetailItem>
  </div>
</template>

<script>
import AirTicketDetailItem from 'views/airticket/AirTicketDetailItem'
// 引入查询机票组件
import Search from 'components/content/search/Search'
// 引入查看全部机票的分页组件
import TicketsByPage from 'views/airticket/TicketsByPage'

export default {
  data () {
    return {
    }
  },

  components: {
    AirTicketDetailItem,
    Search,
    TicketsByPage
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
.tickets{
  position: relative;
  top: 40px;
}
</style>
