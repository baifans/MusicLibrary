package com.example.mapper;

import com.example.entity.Singer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 歌手数据访问层
 */
public interface SingerMapper {

    /**
     * 根据ID查询歌手
     */
    @Select("SELECT singer_id, singer_name, country, song_count FROM singer WHERE singer_id = #{singerId}")
    Singer selectById(Integer singerId);

    /**
     * 查询所有歌手（支持按歌手名条件查询）
     * @param singerName 歌手名称（可选）
     */
    List<Singer> selectAll(@Param("singerName") String singerName);

    /**
     * 调用存储过程删除歌手（事务控制下的删除操作）
     */
    void callDeleteSinger(@Param("singerId") Integer singerId);
}