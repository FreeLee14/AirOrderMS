<!-- 我们当前项目的主要布局组件 -->
<template>
  <div>
    <!-- 使用element的容器布局 -->
    <el-container>
      <el-header>
        <!-- 顶栏布局 -->
        <TopBar>
          <!-- 用户组件部分 -->
          <User></User>
          <!-- 顶栏具体导航内容显示部分 -->
          <TopBarItem>
          </TopBarItem>
        </TopBar>
      </el-header>
      <el-container>
      <!-- 侧栏布局 -->
      <el-aside>
        <SideBar>
          <SideBarItem>机票</SideBarItem>
          <SideBarItem>机票</SideBarItem>
          <SideBarItem>机票</SideBarItem>
          <SideBarItem>机票</SideBarItem>
          <SideBarItem>机票</SideBarItem>
        </SideBar>
      </el-aside>
      <!-- 主体内容布局 -->
      <el-main>
        <Main>
          <MainItem>
            <router-view/>
          </MainItem>
        </Main>
      </el-main>
    </el-container>
    </el-container>
  </div>
</template>

<script>
// 引入我们的顶栏的父组件和内容组件
import TopBar from 'components/common/topbar/TopBar'
import TopBarItem from 'components/common/topbar/TopBarItem'
import User from 'components/content/user/User'
import SideBar from 'components/common/sidebar/SideBar'
import SideBarItem from 'components/common/sidebar/SideBarItem'
import Main from 'components/common/main/Main'
import MainItem from 'components/common/main/MainItem'
export default {
  data () {
    return {
    }
  },

  components: {
    TopBar,
    TopBarItem,
    User,
    SideBar,
    SideBarItem,
    Main,
    MainItem
  },

  computed: {},

  methods: {}
}

</script>
<style  scoped>
</style>
