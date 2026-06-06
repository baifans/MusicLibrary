package com.example.entity;

/**
 * 歌曲实体类
 */
public class Song {
     /** 歌曲ID，主键自增 */
    private Integer songId;

    /** 歌曲名称 */
    private String songName;

    /** 歌手ID，外键关联 singer 表 */
    private Integer singerId;

    /** 时长（分） */
    private Float duration;

    /** 播放次数，默认0 */
    private Integer playCount;

    /** 歌手名称，用于联表查询展示，不在数据库表中 */
    private String singerName;

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }
    
    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Integer getPlayCount() {
        return playCount;
    }
    
    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    
    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }
    
    
}
