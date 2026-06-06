package com.example.service;

import com.example.entity.Comment;
import com.example.mapper.CommentMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论业务处理
 */
@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    /**
     * 查询所有评论
     * @return 评论列表
     */
    public List<Comment> selectAll() {
        return commentMapper.selectAll();
    }
}