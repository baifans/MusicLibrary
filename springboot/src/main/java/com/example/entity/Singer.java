package com.example.entity;

/**
 * 歌手实体类，对应数据库表 `singer`
 */
public class Singer {

    /** 歌手ID，主键自增 */
    private Integer singerId;

    /** 歌手名称 */
    private String singerName;

    /** 国家/地区 */
    private String country;

    /** 歌曲数量，由触发器自动维护 */
    private Integer songCount;

    // ----- getter / setter -----
    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSongCount() {
        return songCount;
    }

    public void setSongCount(Integer songCount) {
        this.songCount = songCount;
    }
}