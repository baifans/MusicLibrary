package com.example.service;

import com.example.entity.Song;
import com.example.mapper.SongMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 歌曲业务处理
 */
@Service
public class SongService {

    @Resource
    private SongMapper songMapper;

    /**
     * 新增歌曲（触发器控制下的添加操作）
     */
    public void add(Song song) {
        if (song.getPlayCount() == null) song.setPlayCount(0);
        songMapper.insert(song);
    }

    /**
     * 删除歌曲
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer songId) {
        songMapper.callDeleteSong(songId);
    }

    /**
     * 修改歌曲
     */
    public void updateById(Song song) {
        songMapper.updateById(song);
    }

    /**
     * 修改歌曲所属歌手（存储过程控制下的更新操作）
     */
    public void changeSinger(Integer songId, Integer newSingerId) {
        songMapper.callChangeSongSinger(songId, newSingerId);
    }

    /**
     * 根据ID查询
     */
    public Song selectById(Integer songId) {
        return songMapper.selectById(songId);
    }

    /**
     * 查询所有
     */
    public List<Song> selectAll(Song song) {
        return songMapper.selectAll(song);
    }

    /**
     * 分页查询（支持按歌曲名或歌手名）
     */
    public PageInfo<Song> selectPage(Song song, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Song> list = songMapper.selectAll(song);
        return PageInfo.of(list);
    }
}