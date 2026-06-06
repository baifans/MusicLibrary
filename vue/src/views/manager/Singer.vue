<template>
  <div>

    <!-- 查询条件 -->
    <div class="card" style="margin-bottom: 5px;">
      <el-input
        v-model="data.singerName"
        placeholder="请输入歌手名称"
        style="width: 200px; margin-right: 10px"
      />
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <!-- 歌手列表 -->
    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tableData" stripe>
        <el-table-column label="歌手名称" prop="singerName"></el-table-column>
        <el-table-column label="国家/地区" prop="country"></el-table-column>
        <el-table-column label="歌曲数量" prop="songCount"></el-table-column>
        <el-table-column label="操作" align="center" width="120">
          <template #default="scope">
            <el-button type="danger" @click="handleDelete(scope.row.singerId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

  </div>
</template>

<script setup>
import { reactive } from "vue";
import request from "@/utils/request";
import { ElMessage, ElMessageBox } from "element-plus";

const data = reactive({
  tableData: [],
  singerName: null
});

// 查询歌手列表（支持条件查询，不分页）
const load = () => {
  request.get("/singer/selectAll", {
    params: {
      singerName: data.singerName
    }
  }).then(res => {
    if (res.code === '200') {
      data.tableData = res.data || [];
    } else {
      ElMessage.error(res.msg);
    }
  });
};

// 重置查询条件
const reset = () => {
  data.singerName = null;
  load();
};

// 删除歌手（调用存储过程，事务控制下的删除操作）
const handleDelete = singerId => {
  ElMessageBox.confirm("删除后该歌手的所有歌曲、评论、歌单关联都会被删除，是否确定？", "删除确认", { type: "warning" })
    .then(() => {
      request.delete(`/singer/delete/${singerId}`).then(res => {
        if (res.code === "200") {
          ElMessage.success("删除成功");
          load();
        } else {
          ElMessage.error(res.msg);
        }
      });
    })
    .catch(() => {});
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