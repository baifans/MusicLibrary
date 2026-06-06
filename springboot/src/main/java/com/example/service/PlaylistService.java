package com.example.service;

import com.example.entity.Playlist;
import com.example.entity.Song;
import com.example.mapper.PlaylistMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌单业务处理
 */
@Service
public class PlaylistService {

    @Resource
    private PlaylistMapper playlistMapper;

    /**
     * 查询所有歌单（联查用户名）
     */
    public List<Playlist> selectAll() {
        return playlistMapper.selectAll();
    }

    /**
     * 查询指定歌单下的所有歌曲（包含歌曲名、歌手名、时长）
     * @param playlistId 歌单ID
     */
    public List<Song> getSongsByPlaylistId(Integer playlistId) {
        return playlistMapper.selectSongsByPlaylistId(playlistId);
    }
}