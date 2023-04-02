package com.noly.forum.controller;

import com.noly.forum.service.AlphaService;
import com.noly.forum.util.ForumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {

        //获取请求数据
        System.out.println("request,getMethod():" + request.getMethod());
        System.out.println("request.getServletPath():" + request.getServletPath());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            String value = request.getHeader(headerName);
            System.out.println(headerName + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        // 返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter writer = response.getWriter();
        ){
            writer.write("<h1>论坛</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO:Get请求处理，用于获取某些数据，默认请求方式

    // 查询所有学生路径: /students?current=2&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {

        System.out.println(current);
        System.out.println(limit);
        return "Some students:" + current + ":" + limit;
    }

    // 查询一个学生: /student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {

        System.out.println(id);
        return "A student:" + id;
    }

    // TODO:Post请求处理
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {

        System.out.println(name);
        System.out.println(age);
        return "success:" + "name=" + name + ", age=" + age;
    }

    // TODO:响应HTML数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "张三");
        modelAndView.addObject("age", "30");
        modelAndView.setViewName("/demo/view");
        return  modelAndView;
    }


    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {

        model.addAttribute("name", "ECUST");
        model.addAttribute("age", "80");
        return "demo/view";
    }

    // TODO：响应JSON数据（异步请求）
    // Java对象 -> Json字符串数据 -> JS对象

    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap();
        emp.put("name", "李四");
        emp.put("age", 33);
        emp.put("salary", "8000");
        return emp;
    }

    // 集合形式的JSON字符串
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {

        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap();
        emp.put("name", "李四");
        emp.put("age", 33);
        emp.put("salary", "8000");
        list.add(emp);

        emp = new HashMap();
        emp.put("name", "王五");
        emp.put("age", 40);
        emp.put("salary", "9999");
        list.add(emp);
        return list ;
    }

    // Cookie示例
    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        // 创建cookie
        Cookie cookie = new Cookie("code", ForumUtil.generateUUID());
        // 设置cookie生效范围
        cookie.setPath("/forum/alpha");
        // 设置cookie的生存时间
        cookie.setMaxAge(60 * 10);
        // 发送cookie
        response.addCookie(cookie);
        return "set cookie";
    }

    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) {
        System.out.println(code);
        return "get cookie";
    }

    // session示例
    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("id", 1);
        session.setAttribute("name", "test");
        return "set session";
    }

    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }

}
