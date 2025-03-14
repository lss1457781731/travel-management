package com.travel.management.mapper;

import com.travel.management.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 添加评论
     */
    int insert(Comment comment);

    /**
     * 根据景点ID查询评论列表
     */
    List<Comment> selectByScenicId(@Param("scenicId") Long scenicId);

    /**
     * 根据ID删除评论
     */
    int deleteById(@Param("id") Long id);

    /**
     * 更新评论状态
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 根据ID查询评论
     */
    Comment selectById(@Param("id") Long id);
}