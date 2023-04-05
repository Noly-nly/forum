package com.noly.forum.dao;

import com.noly.forum.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 查询评论
    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);

    // 查询评论行数
    Integer selectCountByEntity(int entityType, int entityId);

    // 增加评论
    Integer insertComment(Comment comment);

}
