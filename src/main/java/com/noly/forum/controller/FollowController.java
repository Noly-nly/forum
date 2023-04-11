package com.noly.forum.controller;

import com.noly.forum.entity.User;
import com.noly.forum.service.FollowService;
import com.noly.forum.util.ForumUtil;
import com.noly.forum.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FollowController {

    @Autowired
    private FollowService followService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/follow", method = RequestMethod.POST)
    @ResponseBody
    public String follow(int entityType, int entityId) {

        User user = hostHolder.getUser();

        // TODO： 应该增加拦截器拦截未登录用户的请求
        followService.follow(user.getId(), entityType,entityId);

        return ForumUtil.getJSONSting(0, "已关注！");
    }

    @RequestMapping(path = "/unfollow", method = RequestMethod.POST)
    @ResponseBody
    public String unfollow(int entityType, int entityId) {

        User user = hostHolder.getUser();

        // TODO： 应该增加拦截器拦截未登录用户的请求
        followService.unfollow(user.getId(), entityType,entityId);

        return ForumUtil.getJSONSting(0, "已取消关注！");
    }
}
