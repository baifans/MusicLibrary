<template>
  <div>

    <!-- 查询条件 -->
    <div class="card" style="margin-bottom: 5px;">
      <el-input
        v-model="data.songName"
        placeholder="请输入歌曲名称"
        style="width: 200px; margin-right: 10px"
      />
      <el-input
        v-model="data.singerName"
        placeholder="请输入歌手名称"
        style="width: 200px; margin-right: 10px"
      />
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <!-- 歌曲列表 -->
    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tableData" stripe>
        <el-table-column label="歌曲名称" prop="songName"></el-table-column>
        <el-table-column label="歌手" prop="singerName"></el-table-column>
        <el-table-column label="时长（分）" prop="duration"></el-table-column>
        <el-table-column label="播放次数" prop="playCount"></el-table-column>
      </el-table>
    </div>

    <!-- 分页 -->
    <div class="card">
      <el-pagination
        background
        layout="prev, pager, next"
        v-model:current-page="data.pageNum"
        v-model:page-size="data.pageSize"
        :total="data.total"
        @current-change="load"
        @size-change="load"
      />
    </div>

  </div>
</template>

<script setup>
import { reactive } from "vue";
import request from "@/utils/request";
import { ElMessage } from "element-plus";

const data = reactive({
  pageNum: 1,
  pageSize: 10,
  total: 0,
  tableData: [],
  songName: null,
  singerName: null
});

// 查询热歌列表（分页 + 条件）
const load = () => {
  request.get("/hotSong/selectPage", {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      songName: data.songName,
      singerName: data.singerName
    }
  }).then(res => {
    if (res.code === '200') {
      data.tableData = res.data?.list || [];
      data.total = res.data?.total || 0;
    } else {
      ElMessage.error(res.msg);
    }
  });
};

// 重置查询条件
const reset = () => {
  data.songName = null;
  data.singerName = null;
  data.pageNum = 1;
  load();
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