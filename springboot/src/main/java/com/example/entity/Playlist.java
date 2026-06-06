package com.example.entity;

import java.time.LocalDateTime;

/**
 * 歌单实体类，对应数据库表 `playlist`
 */
public class Playlist {

    /** 歌单ID，主键自增 */
    private Integer playlistId;

    /** 歌单名称 */
    private String playlistName;

    /** 用户ID，外键关联 user 表 */
    private Integer userId;

    /** 创建时间 */
    private LocalDateTime createTime;

    // ---------- 扩展字段（用于联表查询展示） ----------
    /** 用户名（联查 user 表） */
    private String username;

    // ---------- getter / setter ----------
    public Integer getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Integer playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}