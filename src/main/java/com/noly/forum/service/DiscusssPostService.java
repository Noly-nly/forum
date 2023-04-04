package com.noly.forum.service;

import com.noly.forum.dao.DiscussPostMapper;
import com.noly.forum.entity.DiscussPost;
import com.noly.forum.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class DiscusssPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;


    // 查询用户帖子
    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {

        return discussPostMapper.selectDiscussPosts(userId, offset,limit);
    }

    // 查询用户帖子行数
    public int findDiscussPostRows(int userId){

        return discussPostMapper.selectDiscussPostRows(userId);
    }

    // 增加帖子
    public Integer addDiscussPost(DiscussPost post) {
        if (post == null) {
            throw new IllegalArgumentException("参数不能为空");
        }

        // 标签处理(转义HTML标记)
        post.setTitle(HtmlUtils.htmlEscape(post.getTitle()));
        post.setContent(HtmlUtils.htmlEscape(post.getContent()));
        // 过滤敏感词
        post.setTitle(sensitiveFilter.filter(post.getTitle()));
        post.setContent(sensitiveFilter.filter(post.getContent()));

        return discussPostMapper.insertDiscussPost(post);
    }

    public DiscussPost findDiscussPostById(int id) {
        return discussPostMapper.selectDiscussPostById(id);

    }

}
