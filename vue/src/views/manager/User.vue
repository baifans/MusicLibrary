<template>
  <div>

    <div class="card">
      <div style="font-size: 20px; font-weight: bold; margin-bottom: 20px">
        个人信息
      </div>

      <el-form :model="data.user" label-width="100px">

        <el-form-item label="用户名">
          <el-input v-model="data.user.username" disabled />
        </el-form-item>

        <el-form-item label="姓名">
          <el-input v-model="data.user.name" disabled />
        </el-form-item>

        <el-form-item label="邮箱">
          <el-input v-model="data.user.email" />
        </el-form-item>

        <el-form-item label="角色">
          <el-input v-model="data.user.role" disabled />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="update">
            保存修改
          </el-button>
        </el-form-item>

      </el-form>

    </div>

  </div>
</template>

<script setup>
import { reactive } from "vue";
import request from "@/utils/request";
import { ElMessage } from "element-plus";

const user = JSON.parse(localStorage.getItem("system-user"))

const data = reactive({
  user: {}
})

// 查询当前用户信息
const load = () => {
  request.get("/user/selectById/" + user.id).then(res => {
    if (res.code === '200') {
      data.user = res.data
    }
  })
}

// 修改个人信息
const update = () => {
  request.put("/user/update", data.user).then(res => {
    if (res.code === '200') {
      ElMessage.success("保存成功")

      // 更新本地缓存
      localStorage.setItem(
          "system-user",
          JSON.stringify(data.user)
      )

      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

load()
</script>