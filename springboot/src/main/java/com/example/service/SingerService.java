package com.example.service;

import com.example.entity.Singer;
import com.example.mapper.SingerMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 歌手业务处理
 */
@Service
public class SingerService {

    @Resource
    private SingerMapper singerMapper;

    /**
     * 删除歌手（事务控制下的删除操作，调用存储过程 sp_delete_singer）
     * @param singerId 歌手ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer singerId) {
        singerMapper.callDeleteSinger(singerId);
    }

    /**
     * 查询所有歌手（支持按歌手名条件查询）
     * @param singerName 歌手名称
     * @return 歌手列表
     */
    public List<Singer> selectAll(String singerName) {
        return singerMapper.selectAll(singerName);
    }
}