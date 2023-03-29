package com.noly.forum.service;

import com.noly.forum.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
// 默认Bean对象都是singleton：单例，整个应用程序中只存在一个实例对象。
// @Scope("prototype")，prototype：原型，每次获取Bean对象时都会创建一个新的实例对象。
//@Scope("prototype")
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

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

}
