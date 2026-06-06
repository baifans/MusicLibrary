package com.example.mapper;

import com.example.entity.Song;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 歌曲数据访问层
 */
public interface SongMapper {

    /**
     * 新增歌曲
     */
    int insert(Song song);

    /**
     * 删除歌曲
     */
    @Delete("delete from song where song_id = #{songId}")
    int deleteById(Integer songId);

    /**
     * 删除歌曲（存储过程控制下的删除操作）
     */
    void callDeleteSong(@Param("songId") Integer songId);

    /**
     * 修改歌曲
     */
    int updateById(Song song);

    /**
     * 修改歌曲所属歌手（存储过程控制下的更新操作）
     */
    @Transactional(rollbackFor = Exception.class)
    void callChangeSongSinger(@Param("songId") Integer songId, @Param("newSingerId") Integer newSingerId);

    /**
     * 根据ID查询单首歌曲
     */
    @Select("SELECT song_id, song_name, singer_id, duration, play_count FROM song WHERE song_id = #{songId}")
    Song selectById(Integer songId);

    /**
     * 查询全部
     */
    List<Song> selectAll(Song song);

}