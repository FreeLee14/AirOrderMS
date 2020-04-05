<!-- 公共的查询，订购机票组件 -->
<template>
  <div>
    <el-form :rules="rules" ref="conditions" :model="conditions" >
      <table align="center" >
        <tr>
          <td>
            <el-form-item prop="value">
              <el-select v-model="conditions.value" clearable placeholder="请选择">
                <el-option
                  v-for="item in conditions.options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </td>
          <td>
            <el-form-item prop="departure">
              <el-input
                placeholder="请输出发地"
                v-model="conditions.departure"
                clearable>
              </el-input>
            </el-form-item>
          </td>
          <td>
            <el-form-item>
              <i class="el-icon-s-promotion" ></i>
            </el-form-item>
          </td>
          <td>
            <el-form-item prop="destination">
              <el-input
                placeholder="请输出目的地"
                v-model="conditions.destination"
                clearable>
              </el-input>
            </el-form-item>
          </td>
          <td></td>
          <td>
            <el-form-item prop="date">
              <el-date-picker
                v-model="conditions.date"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                @change="chooseDate"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd"
                >
              </el-date-picker>
            </el-form-item>
          </td>
          <td>
            <el-form-item >
              <font class="font">人数 :</font>
            </el-form-item>
          </td>
          <td>
            <el-form-item prop="number">
              <el-input-number v-model="conditions.number" @change="handleChange" :min="1" :max="10" label="请选择人数"></el-input-number>
            </el-form-item>
          </td>
          <td>&nbsp;</td>
          <td>
            <el-form-item>
              <el-button type="info" round @click="search('conditions')">查询</el-button>
            </el-form-item>
          </td>
        </tr>
      </table>
    </el-form>
    <!-- 显示精准查询的机票信息，点击查询时显示出这个子组件 -->
    <el-collapse-transition>
      <Purchase v-if="flag" v-bind:ticketsView="ticketsInfo"></Purchase>
    </el-collapse-transition>
  </div>
</template>

<script>
// 引入机票信息组件
import Purchase from 'components/content/ticket/Purchase'
import { findByConditions } from 'network/ticket'
import qs from 'qs'
export default {
  props: ['ticketView'],
  data () {
    return {
      conditions: {
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
        value: 0,
        // 出发地
        departure: '',
        // 目的地
        destination: '',
        // 往返日期
        date: [],
        // 人数
        number: ''
      },
      // 进行表单的整体校验
      rules: {
        value: [
          { required: true, message: '请选择具体的机票类型', trigger: 'change' }
        ],
        departure: [
          { required: true, message: '出发地不能为空', trigger: 'blur' }
        ],
        destination: [
          { required: true, message: '目的地不能为空', trigger: 'blur' }
        ],
        date: [
          { required: true, message: '开始结束日期不能为空', trigger: 'blur' }
        ]
      },
      flag: false,
      // 接收精准查询异步请求传递过来的机票信息
      ticketsInfo: []
    }
  },

  components: {
    Purchase
  },

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
      console.log(this.conditions.number)
    },
    chooseDate () {
      console.log(this.conditions.date)
    },
    // 测试方法
    // ensure () {
    //   console.log(this.value)
    // }
    // 把表单对象传递过来
    search (conditions) {
      this.$refs[conditions].validate((valid) => {
        if (valid) {
          findByConditions(
            [
              function (data) {
                return qs.stringify(data)
              }
            ],
            {
              isreturn: this.conditions.value,
              departure: this.conditions.departure,
              destination: this.conditions.destination,
              startTime: this.conditions.date[0],
              endTime: this.conditions.date[1]
            }
          ).then(res => {
            if (res !== null) {
              this.ticketsInfo = res
            }
            this.flag = true
            // 子组件传值给父组件的方法
            this.$emit('transflag', this.flag)
          })
        }
      })
    },
    initInfo () {
      // 根据typeof 进行对象的判断是否定义
      if (typeof this.ticketView !== 'undefined') {
        this.conditions.value = this.ticketView.isreturn
        this.conditions.departure = this.ticketView.departure
        this.conditions.destination = this.ticketView.destination
        this.conditions.date = [this.ticketView.startDate, this.ticketView.startDate]
      }
    },
    test () {
      this.$router.replace({
        path: '/search/purchase'
      })
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
