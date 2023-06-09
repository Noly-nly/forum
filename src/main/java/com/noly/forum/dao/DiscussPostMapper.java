package com.noly.forum.dao;

import com.noly.forum.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface DiscussPostMapper {

    // 查询用户帖子
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit, int orderMode);

    // @Param注解用于给参数取别名,
    // sql语句中如果只有一个参数,并且在<if>里使用,则必须加别名.
    // 查询用户帖子行数
    int selectDiscussPostRows(@Param("userId") int userId);

    // 增加帖子
    Integer insertDiscussPost(DiscussPost post);

    DiscussPost selectDiscussPostById(int id);

    // 更新评论数量
    int updateCommentCount(int id, int commentCount);

    int updateType(int id, int type);

    int updateStatus(int id, int status);

    int updateScore(int id, double score);



}
