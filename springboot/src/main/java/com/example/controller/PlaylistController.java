package com.example.controller;

import com.example.common.Result;
import com.example.entity.Playlist;
import com.example.entity.Song;
import com.example.service.PlaylistService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 歌单管理前端接口
 */
@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Resource
    private PlaylistService playlistService;

    /**
     * 查询所有歌单（联查用户名）
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<Playlist> list = playlistService.selectAll();
        return Result.success(list);
    }

    /**
     * 查询指定歌单下的所有歌曲（包含歌曲名、歌手名、时长）
     * @param playlistId 歌单ID
     */
    @GetMapping("/songs/{playlistId}")
    public Result getSongsByPlaylistId(@PathVariable Integer playlistId) {
        List<Song> songs = playlistService.getSongsByPlaylistId(playlistId);
        return Result.success(songs);
    }
}