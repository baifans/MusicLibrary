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
      <div style="margin-bottom: 10px">
        <el-button type="primary" @click="handleAdd">新增歌曲</el-button>
      </div>

      <el-table :data="data.tableData" stripe>
        <el-table-column label="歌曲名称" prop="songName"></el-table-column>
        <el-table-column label="歌手" prop="singerName"></el-table-column>
        <el-table-column label="时长（分）" prop="duration"></el-table-column>
        <el-table-column label="播放次数" prop="playCount"></el-table-column>
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" @click="handleDelete(scope.row.songId)">删除</el-button>
          </template>
        </el-table-column>
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

    <!-- 弹窗新增/编辑 -->
    <el-dialog
      title="歌曲信息"
      width="40%"
      v-model="data.formVisible"
      :close-on-click-modal="false"
      destroy-on-close
    >
      <el-form :model="data.form" label-width="100px">
        <el-form-item label="歌曲名称">
          <el-input v-model="data.form.songName" :disabled="!!data.form.songId" />
        </el-form-item>
        <el-form-item label="歌手ID">
          <el-input v-model="data.form.singerId" />
        </el-form-item>
         <el-form-item v-if="!data.form.songId" label="时长（分）">
          <el-input v-model="data.form.duration" type="number" />
        </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="data.formVisible = false">取消</el-button>
          <el-button type="primary" @click="save">保存</el-button>
        </span>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import { reactive } from "vue";
import request from "@/utils/request";
import { ElMessage, ElMessageBox } from "element-plus";

const data = reactive({
  pageNum: 1,
  pageSize: 10,
  total: 0,
  tableData: [],
  songName: null,
  singerName: null,
  formVisible: false,
  form: {}
});

// 查询歌曲列表
const load = () => {
  request.get("/song/selectPage", {
      params: {
        pageNum: data.pageNum,
        pageSize: data.pageSize,
        songName: data.songName,
        singerName: data.singerName
      }
    })
    .then(res => {
      if (res.code === '200') {
        data.tableData = res.data?.list
        data.total = res.data?.total
      } else {
        ElMessage.error(res.msg)
      }
    });
};

// 重置查询条件
const reset = () => {
  data.songName = null;
  data.singerName = null;
  load();
};

// 弹出新增
const handleAdd = () => {
  data.form = {};
  data.formVisible = true;
};

// 编辑
const handleEdit = row => {
  data.form = JSON.parse(JSON.stringify(row));
  data.formVisible = true;
};

// 保存（新增或编辑）
const save = () => {
  if (data.form.songId) {
    // 编辑
    request.put('/song/changeSinger', null, {
      params: {
        songId: data.form.songId,
        newSingerId: data.form.singerId
      }
    })
    .then(res => {
      if (res.code === "200") {
        ElMessage.success("保存成功");
        data.formVisible = false;
        load();
      } else {
        ElMessage.error(res.msg);
      }
    });
  } else {
    // 新增
    request.post("/song/add", data.form).then(res => {
      if (res.code === "200") {
        ElMessage.success("新增成功");
        data.formVisible = false;
        load();
      } else {
        ElMessage.error(res.msg);
      }
    });
  }
};

// 删除
const handleDelete = songId => {
  ElMessageBox.confirm("删除后数据无法恢复，您确定删除吗？", "删除确认", { type: "warning" })
    .then(() => {
      request.delete(`/song/delete/${songId}`).then(res => {
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