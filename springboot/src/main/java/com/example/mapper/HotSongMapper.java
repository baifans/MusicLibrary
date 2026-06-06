package com.example.mapper;

import com.example.entity.Song;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 热歌数据访问层（基于视图 v_hot_song）
 */
public interface HotSongMapper {

    /**
     * 分页条件查询热歌（支持歌曲名、歌手名模糊查询）
     * @param songName   歌曲名称（可选）
     * @param singerName 歌手名称（可选）
     * @return 热歌列表
     */
    List<Song> selectPage(@Param("songName") String songName,
                          @Param("singerName") String singerName);
}