package com.noly.forum.service;

import com.noly.forum.dao.AlphaDao;
import com.noly.forum.dao.DiscussPostMapper;
import com.noly.forum.dao.UserMapper;
import com.noly.forum.entity.DiscussPost;
import com.noly.forum.entity.User;
import com.noly.forum.util.ForumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

@Service
// 默认Bean对象都是singleton：单例，整个应用程序中只存在一个实例对象。
// @Scope("prototype")，prototype：原型，每次获取Bean对象时都会创建一个新的实例对象。
//@Scope("prototype")
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public AlphaService() {
        System.out.println("实例化AlphaService");
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化AlphaService");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁AlphaService");
    }

    public String find() {

        return alphaDao.select();
    }

    // 演示声明式事务：基于注解
    // REQUIRED: 支持当前事务(外部事务)，如果不存在则创建新事物。
    // REQUIRES_NEW: 创建一个新事务，并且暂停当前事务(外部事务)。
    // NESTED: 如果当前存在事务(外部事务)，则嵌套在该事务中执行(独立的提交和回滚)，否则就会和REQUIRED一样。
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Object save1() {

        // 增加用户
        User user = new User();
        user.setUsername("alpha");
        user.setSalt(ForumUtil.generateUUID().substring(0, 5));
        user.setPassword(ForumUtil.md5("123" + user.getSalt()));
        user.setEmail("alpha@qq.com");
        user.setHeaderUrl("http://image.nowcoder.com/head/33.png");
        user.setCreateTime(new Date());
        userMapper.insertUser(user);

        // 增加帖子
        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle("alpha");
        post.setContent("新人报道");
        post.setCreateTime(new Date());
        discussPostMapper.insertDiscussPost(post);

        // 错误示例，验证报错后事务会回滚
        Integer.valueOf("abc");

        return "ok";
    }

    // 演示编程式事务
    public Object save2() {

        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        return transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                // 增加用户
                User user = new User();
                user.setUsername("beta");
                user.setSalt(ForumUtil.generateUUID().substring(0, 5));
                user.setPassword(ForumUtil.md5("123" + user.getSalt()));
                user.setEmail("beta@qq.com");
                user.setHeaderUrl("http://image.nowcoder.com/head/55.png");
                user.setCreateTime(new Date());
                userMapper.insertUser(user);

                // 增加帖子
                DiscussPost post = new DiscussPost();
                post.setUserId(user.getId());
                post.setTitle("beta");
                post.setContent("新人报道");
                post.setCreateTime(new Date());
                discussPostMapper.insertDiscussPost(post);

                // 错误示例，验证报错后事务会回滚
                Integer.valueOf("abc");

                return "ok";
            }
        });

    }



}
