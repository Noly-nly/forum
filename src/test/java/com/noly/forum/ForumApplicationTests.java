package com.noly.forum;

import com.noly.forum.config.AlphaConfig;
import com.noly.forum.dao.AlphaDao;
import com.noly.forum.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
//import org.springframework.beans.SimpleBeanInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ForumApplication.class)
class ForumApplicationTests implements ApplicationContextAware {

    // 利用ApplicationContext对象可以手动获取Bean对象
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // 测试Bean对象的获取，dao包中的AlphaDao、AlphaDaoHibernateImpl、AlphaDaoMyBatisImpl
    @Test
    public void testApplicationContext() {

        System.out.println(applicationContext);

        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());

        alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);
        System.out.println(alphaDao.select());
    }

    // 测试Bean对象的管理方式，service包中的AlphaService
    @Test
    public void testBeanManagement() {

        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);

        alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
    }

    @Test
    public void testBeanConfig() {

        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    // 自动装配，依赖注入
    @Autowired
    @Qualifier("alphaHibernate")
    private AlphaDao alphaDao;
    @Autowired
    private AlphaService alphaService;

    @Autowired
    private AlphaConfig alphaConfig;

    @Test
    public void testDI() {
        System.out.println(alphaDao);
        System.out.println(alphaService);
        System.out.println(alphaConfig);
    }

}
