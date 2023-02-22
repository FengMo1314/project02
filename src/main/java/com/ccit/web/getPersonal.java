package com.ccit.web;

import com.alibaba.fastjson.JSON;
import com.ccit.bean.UserBean;
import com.ccit.bean.UserBean;
import com.ccit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理列表
 */
@WebServlet("/getPersonal")
public class getPersonal extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String uname = request.getParameter("name");
        HttpSession session = request.getSession();
        String uname = (String) session.getAttribute("username");
        System.out.println(uname);
        //创建列表
        List<UserBean> users = new ArrayList();
        //获取数据
        UserService userService = new UserService();
        try {
            users = userService.getUser(uname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String strings= JSON.toJSONString(users);
        System.out.println(strings);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(strings);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
