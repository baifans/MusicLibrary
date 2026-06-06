<template>
  <div>

    <!-- 歌单列表卡片 -->
    <div class="card">
      <el-table :data="data.tableData" stripe>
        <el-table-column label="歌单名称" prop="playlistName"></el-table-column>
        <el-table-column label="创建者" prop="username"></el-table-column>
        <el-table-column label="操作" align="center" width="100">
          <template #default="scope">
            <el-button type="primary" @click="handleDetail(scope.row)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 详情弹窗：显示歌单内的歌曲 -->
    <el-dialog
      :title="data.dialogTitle"
      width="50%"
      v-model="data.dialogVisible"
      :close-on-click-modal="false"
      destroy-on-close
    >
      <el-table :data="data.songList" stripe>
        <el-table-column label="歌曲名称" prop="songName"></el-table-column>
        <el-table-column label="歌手" prop="singerName"></el-table-column>
        <el-table-column label="时长（分）" prop="duration"></el-table-column>
      </el-table>
    </el-dialog>

  </div>
</template>

<script setup>
import { reactive } from "vue";
import request from "@/utils/request";
import { ElMessage } from "element-plus";

const data = reactive({
  tableData: [],      // 歌单列表
  dialogVisible: false,
  dialogTitle: "",
  songList: []        // 当前歌单的歌曲列表
});

// 查询所有歌单（联查用户名）
const load = () => {
  request.get("/playlist/selectAll").then(res => {
    if (res.code === '200') {
      data.tableData = res.data || [];
    } else {
      ElMessage.error(res.msg);
    }
  });
};

// 查看歌单详情
const handleDetail = (row) => {
  data.dialogTitle = `${row.playlistName} - 歌曲列表`;
  // 请求该歌单下的歌曲
  request.get(`/playlist/songs/${row.playlistId}`).then(res => {
    if (res.code === '200') {
      data.songList = res.data || [];
      data.dialogVisible = true;
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