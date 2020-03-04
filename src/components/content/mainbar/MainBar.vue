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
          <el-row>
            <el-col :span="12">
              <!-- el-menu 中router定义上即可在每个item中的index写入路由路径 router本身定义为true-->
              <el-menu
                router
                class="elmenu"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b">
                <SideBarItem>
                  <el-menu-item index="/regist" >
                    <template slot="title">
                      <i class="el-icon-location"></i>
                      <span>机票业务</span>
                    </template>
                  </el-menu-item>
                </SideBarItem>
                <SideBarItem>
                  <el-menu-item index="2">
                    <i class="el-icon-menu"></i>
                    <span slot="title">导航二</span>
                  </el-menu-item>
                </SideBarItem>
                <SideBarItem>
                  <el-menu-item index="3" >
                    <i class="el-icon-document"></i>
                    <span slot="title">导航三</span>
                  </el-menu-item>
                </SideBarItem>
                <SideBarItem>
                  <el-menu-item index="4">
                    <i class="el-icon-setting"></i>
                    <span slot="title">导航四</span>
                  </el-menu-item>
                </SideBarItem>
              </el-menu>
          </el-col>
        </el-row>
        </SideBar>
      </el-aside>
      <!-- 主体内容布局 -->
      <el-main>
        <Main>
            <!-- 路由显示部分为主体部分 -->
            <router-view/>
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
    Main
  },

  computed: {},

  methods: {}
}

</script>
<style  scoped>
.elmenu{
  width: 260px;
}
</style>
