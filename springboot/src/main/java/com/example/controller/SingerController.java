package com.example.controller;

import com.example.common.Result;
import com.example.entity.Singer;
import com.example.service.SingerService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 歌手管理前端接口
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Resource
    private SingerService singerService;

    /**
     * 删除歌手（事务控制下的删除操作，调用存储过程 sp_delete_singer）
     */
    @DeleteMapping("/delete/{singerId}")
    public Result deleteById(@PathVariable Integer singerId) {
        singerService.deleteById(singerId);
        return Result.success();
    }

    /**
     * 查询所有歌手（支持按歌手名条件查询）
     * @param singerName 歌手名称
     */
    @GetMapping("/selectAll")
    public Result selectAll(@RequestParam(required = false) String singerName) {
        List<Singer> list = singerService.selectAll(singerName);
        return Result.success(list);
    }
}