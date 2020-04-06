<!-- 查看机票组件 -->
<template>
  <div>
    <AirTicketDetailItem>
      <div slot="search"  class="search">
        <Search @transflag="transflag"></Search>
      </div>
      <div slot="tickets" class="tickets">
        <TicketsByPage v-if="flag" ></TicketsByPage>
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
      flag: true
    }
  },

  components: {
    AirTicketDetailItem,
    Search,
    TicketsByPage
  },

  computed: {},

  methods: {
    transflag (data) {
      this.flag = !data
      console.log(this.flag)
    }
  }
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
