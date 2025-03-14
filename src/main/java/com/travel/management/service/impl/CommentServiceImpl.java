package com.travel.management.service.impl;

import com.travel.management.entity.Comment;
import com.travel.management.entity.SysUser;
import com.travel.management.mapper.CommentMapper;
import com.travel.management.service.CommentService;
import com.travel.management.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TokenUtils tokenUtils;

    @Override
    @Transactional
    public Comment addComment(Comment comment) {
        comment.setCreateTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        comment.setStatus(1); // 1: 正常状态
        SysUser currentUser = tokenUtils.getCurrentUser();
        comment.setUserName(currentUser.getUsername());
        comment.setCreateTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        commentMapper.insert(comment);
        return comment;
    }

    @Override
    public List<Comment> getCommentsByScenicId(Long scenicId) {
        return commentMapper.selectByScenicId(scenicId);
    }

    @Override
    @Transactional
    public boolean deleteComment(Long id) {
        return commentMapper.deleteById(id) > 0;
    }

}