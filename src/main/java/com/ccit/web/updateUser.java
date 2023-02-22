package com.ccit.web;

import com.alibaba.fastjson.JSONObject;
import com.ccit.bean.UserBean;
import com.ccit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 根据id删除
 */
@WebServlet("/update")
public class updateUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求中文乱码
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        UserBean userBean = JSONObject.parseObject(params,UserBean.class);
        //1. 接收新用户名和新密码
        int id = userBean.getId();
        String uname = userBean.getUserName();
        String pwd = userBean.getPassWord();

        //更新验证
        UserService userService = new UserService();
        boolean IsUpdate = false;
        try {
            IsUpdate = userService.updateUser(id, uname, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //更新失败
        if (IsUpdate == false) {
            System.out.println("用户: " + uname + " 密码: " + pwd + " 更新失败~~ ");
            response.getWriter().write("" + "1");
        }
        //更新成功
        else {
            System.out.println("用户: " + uname + " 密码: " + pwd + " 更新成功~~ ");
            response.getWriter().write("" + "2");
        }

    }
}
