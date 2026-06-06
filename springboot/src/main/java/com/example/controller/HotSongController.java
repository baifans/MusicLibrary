package com.example.controller;

import com.example.common.Result;
import com.example.entity.Song;
import com.example.service.HotSongService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotSong")
public class HotSongController {

    @Resource
    private HotSongService hotSongService;

    /**
     * 分页查询热歌（可按歌曲名/歌手名条件查询）
     * @param songName   歌曲名称（可选）
     * @param singerName 歌手名称（可选）
     * @param pageNum    页码
     * @param pageSize   每页条数
     */
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(required = false) String songName,
                             @RequestParam(required = false) String singerName,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Song> page = hotSongService.selectPage(songName, singerName, pageNum, pageSize);
        return Result.success(page);
    }
}