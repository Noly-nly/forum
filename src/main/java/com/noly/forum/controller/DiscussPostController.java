package com.noly.forum.controller;

import com.noly.forum.entity.DiscussPost;
import com.noly.forum.entity.User;
import com.noly.forum.service.DiscusssPostService;
import com.noly.forum.util.ForumUtil;
import com.noly.forum.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController {

    @Autowired
    private DiscusssPostService discusssPostService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title, String content) {
        User user = hostHolder.getUser();
        if (user == null) {
            return ForumUtil.getJSONSting(403, "你还没有登录，请登录后再发帖？！");
        }

        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        discusssPostService.addDiscussPost(post);

        // 报错的情况，将来统一处理

        return ForumUtil.getJSONSting(0, "发布成功！");
    }
}
