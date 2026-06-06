package com.example.entity;

import java.time.LocalDateTime;

/**
 * 评论实体类，对应数据库表 `comment`
 */
public class Comment {

    /** 评论ID，主键自增 */
    private Integer commentId;

    /** 评论内容 */
    private String content;

    /** 用户ID，外键关联 user 表 */
    private Integer userId;

    /** 歌曲ID，外键关联 song 表 */
    private Integer songId;

    /** 评论时间 */
    private LocalDateTime commentTime;

    // ---------- 扩展字段（用于联表查询展示，不在数据库表中） ----------
    /** 用户名（联查 user 表） */
    private String username;

    /** 歌曲名称（联查 song 表） */
    private String songName;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}