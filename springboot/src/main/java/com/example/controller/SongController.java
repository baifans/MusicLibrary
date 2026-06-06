package com.example.controller;

import com.example.common.Result;
import com.example.entity.Song;
import com.example.service.SongService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 歌曲管理前端接口
 */
@RestController
@RequestMapping("/song")
public class SongController {

    @Resource
    private SongService songService;

    /**
     * 新增歌曲（触发器控制下的添加操作）
     */
    @PostMapping("/add")
    public Result add(@RequestBody Song song) {
        songService.add(song);
        return Result.success();
    }

    /**
     * 删除歌曲
     */
    @DeleteMapping("/delete/{songId}")
    public Result deleteById(@PathVariable Integer songId) {
        songService.deleteById(songId);
        return Result.success();
    }

    /**
     * 修改歌曲
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Song song) {
        songService.updateById(song);
        return Result.success();
    }

    /**
     * 修改歌曲所属歌手（存储过程控制下的更新操作）
     * @param songId 歌曲ID
     * @param newSingerId 新歌手ID
     */
    @PutMapping("/changeSinger")
    public Result changeSinger(@RequestParam Integer songId, @RequestParam Integer newSingerId) {
        songService.changeSinger(songId, newSingerId);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{songId}")
    public Result selectById(@PathVariable Integer songId) {
        Song song = songService.selectById(songId);
        return Result.success(song);
    }

    /**
     * 查询所有歌曲
     */
    @GetMapping("/selectAll")
    public Result selectAll(Song song) {
        List<Song> list = songService.selectAll(song);
        return Result.success(list);
    }

    /**
     * 分页查询歌曲（可按歌曲名/歌手名查询）
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Song song,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        PageInfo<Song> page = songService.selectPage(song, pageNum, pageSize);
        return Result.success(page);
    }

}