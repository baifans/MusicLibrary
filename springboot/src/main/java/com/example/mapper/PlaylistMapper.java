package com.example.mapper;

import com.example.entity.Playlist;
import com.example.entity.Song;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 歌单数据访问层
 */
public interface PlaylistMapper {

    /**
     * 查询所有歌单（联查用户名）
     */
    List<Playlist> selectAll();

    /**
     * 查询指定歌单下的所有歌曲（包含歌曲名、歌手名、时长）
     * @param playlistId 歌单ID
     */
    List<Song> selectSongsByPlaylistId(@Param("playlistId") Integer playlistId);
}