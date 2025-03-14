package com.travel.management.controller;


import com.travel.management.common.Result;
import com.travel.management.entity.Comment;
import com.travel.management.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping
    public Result<Comment> addComment(@Validated @RequestBody Comment comment) {
        commentService.addComment(comment);
        return Result.success(comment);
    }


    @GetMapping("/scenic/{scenicId}")
    public Result<List<Comment>> getCommentsByScenicId(@PathVariable Long scenicId) {
        List<Comment> comments = commentService.getCommentsByScenicId(scenicId);
        return Result.success(comments);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return Result.success();
    }

}