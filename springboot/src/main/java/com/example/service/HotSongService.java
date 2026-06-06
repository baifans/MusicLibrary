package com.example.service;

import com.example.entity.Song;
import com.example.mapper.HotSongMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 热歌业务处理
 */
@Service
public class HotSongService {

    @Resource
    private HotSongMapper hotSongMapper;

    /**
     * 分页查询热歌（支持按歌曲名或歌手名条件查询）
     * @param songName   歌曲名称（可选，模糊匹配）
     * @param singerName 歌手名称（可选，模糊匹配）
     * @param pageNum    页码
     * @param pageSize   每页条数
     * @return 分页结果
     */
    public PageInfo<Song> selectPage(String songName, String singerName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Song> list = hotSongMapper.selectPage(songName, singerName);
        return PageInfo.of(list);
    }
}