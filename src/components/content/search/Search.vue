<!-- 公共的查询，订购机票组件 -->
<template>
  <div>
    <table align="center" >
      <tr>
        <td>
          <el-select v-model="value" clearable placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </td>
        <td>
          <el-input
            placeholder="请输出发地"
            v-model="departure"
            clearable>
          </el-input>
        </td>
        <td>
          <i class="el-icon-s-promotion" ></i>
        </td>
        <td>
          <el-input
            placeholder="请输出目的地"
            v-model="destination"
            clearable>
          </el-input>
        </td>
        <td></td>
        <td>
           <el-date-picker
            v-model="date"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            @change="chooseDate"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd"
            >
          </el-date-picker>
        </td>
        <td><font class="font">人数 :</font></td>
        <td>
          <el-input-number v-model="number" @change="handleChange" :min="1" :max="10" label="请选择人数"></el-input-number>
        </td>
        <td>&nbsp;</td>
        <td>
           <el-button type="info" round @click="search">查询</el-button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  props: ['ticketView'],
  data () {
    return {
      options: [
        {
          value: 0,
          label: '单程'
        },
        {
          value: 1,
          label: '往返'
        }
      ],
      // 单反程选项
      value: '',
      // 出发地
      departure: '',
      // 目的地
      destination: '',
      // 往返日期
      date: [],
      // 人数
      number: ''
    }
  },

  components: {},

  computed: {},

  mounted () {
    // 初始化数据显示，从父组件获取子组件的数据
    this.$nextTick(function () {
      setTimeout(() => {
        this.initInfo()
      }, 2000)
    })
  },

  methods: {
    handleChange () {
      console.log(this.number)
    },
    chooseDate () {
      console.log(this.date)
    },
    // 测试方法
    // ensure () {
    //   console.log(this.value)
    // }
    search () {
      alert('查询数据跳转到查看机票组件')
    },
    initInfo () {
      console.log(this.ticketView)
      this.value = this.ticketView.isreturn
      this.departure = this.ticketView.departure
      this.destination = this.ticketView.destination
      this.date = [this.ticketView.startDate, this.ticketView.startDate]
    }
  }
}

</script>
<style  scoped>
.font{
  font-family: "PingFang SC";
  color: #606266;
}
</style>
