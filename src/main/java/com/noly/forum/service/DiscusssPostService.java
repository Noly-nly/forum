package com.noly.forum.service;

import com.noly.forum.dao.DiscussPostMapper;
import com.noly.forum.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscusssPostService {

    @Autowired DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {

        return discussPostMapper.selectDiscussPosts(userId, offset,limit);
    }

    public int findDiscussPostRows(int userId){

        return discussPostMapper.selectDiscussPostRows(userId);
    }

}
