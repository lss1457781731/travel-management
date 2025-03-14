package com.travel.management.service;

import com.travel.management.entity.Comment;
import java.util.List;

public interface CommentService {
    /**
     * 添加评论
     */
    Comment addComment(Comment comment);

    /**
     * 根据景点ID查询评论列表
     */
    List<Comment> getCommentsByScenicId(Long scenicId);

    /**
     * 删除评论
     */
    boolean deleteComment(Long id);

}