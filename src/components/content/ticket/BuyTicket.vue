<!-- 公共购买机票组件 -->
<template>
  <div>
    <BuyTicketItem>
      <div class="search" slot="search">
        <Search v-bind:ticketView="ticketView"></Search>
      </div>
      <div class="purchase" slot="purchase">
        <Purchase v-bind:ticketView="ticketView"></Purchase>
      </div>
    </BuyTicketItem>
  </div>
</template>

<script>
import BuyTicketItem from 'components/content/ticket/BuyTicketItem'
import Search from 'components/content/search/Search'
import Purchase from 'components/content/ticket/Purchase'
import { findById } from 'network/ticket'
export default {
  data () {
    return {
      ticketView: {
        id: 0,
        parentid: 0,
        flight: '',
        seat: '0',
        departure: '',
        destination: '',
        startDate: '',
        startTime: '',
        consume: '',
        endTime: '',
        counts: 0,
        money: 0,
        isreturn: 0

      }
    }
  },

  components: {
    BuyTicketItem,
    Search,
    Purchase
  },
  mounted () {
    this.initInfo()
  },

  computed: {},

  methods: {
    initInfo () {
      findById(this.$route.params.id)
        .then(res => {
          console.log(res)
          if (res != null) {
            this.ticketView.id = res[0].id
            this.ticketView.parentid = res[0].parentid
            this.ticketView.flight = res[0].flight
            this.ticketView.seat = res[0].seat
            this.ticketView.departure = res[0].departure
            this.ticketView.startDate = res[0].startDate
            this.ticketView.destination = res[0].destination
            this.ticketView.startTime = res[0].startTime
            this.ticketView.consume = res[0].consume
            this.ticketView.endTime = res[0].endTime
            this.ticketView.counts = res[0].counts
            this.ticketView.money = res[0].money
            this.ticketView.isreturn = res[0].isreturn
          }
        })
    }
  }
}

</script>
<style  scoped>
.search{
  position: relative;
  top: 100px;
}
</style>
