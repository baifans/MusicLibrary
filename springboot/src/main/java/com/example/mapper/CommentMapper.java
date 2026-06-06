package com.example.mapper;

import com.example.entity.Comment;

import java.util.List;

/**
 * 评论数据访问层
 */
public interface CommentMapper {

    /**
     * 查询所有评论
     * @return 评论列表
     */
    List<Comment> selectAll();
}