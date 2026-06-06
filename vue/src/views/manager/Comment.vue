<template>
  <div>

    <!-- 评论列表卡片 -->
    <div class="card">
      <el-table :data="data.tableData" stripe>
        <el-table-column label="评论内容" prop="content"></el-table-column>
        <el-table-column label="用户名" prop="username"></el-table-column>
        <el-table-column label="歌曲名称" prop="songName"></el-table-column>
      </el-table>
    </div>

  </div>
</template>

<script setup>
import { reactive } from "vue";
import request from "@/utils/request";
import { ElMessage } from "element-plus";

const data = reactive({
  tableData: []
});

// 查询所有评论（无条件、无分页）
const load = () => {
  request.get("/comment/selectAll").then(res => {
    if (res.code === '200') {
      data.tableData = res.data || [];
    } else {
      ElMessage.error(res.msg);
    }
  });
};

// 页面初始化加载
load();
</script>

<style scoped>
.card {
  background-color: #fff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>