package com.ccit.web;

import com.ccit.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 登录
 */
@WebServlet("/login4")
public class LoginServlet4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 针对post请求，设置允许接收中文
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //获取session中的验证码,也就是CodeServlet中生成的四个字符
        String sessionCode = (String) session.getAttribute("sCode");

        String uname =null;
        String pwd =null;
        String checkCode =null;
        String remember =null;
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            List items = upload.parseRequest(request);
            Map params = new HashMap();
            for (Object object : items) {
                FileItem fileItem = (FileItem) object;
                if (fileItem.isFormField()) {
                    params.put(fileItem.getFieldName(), fileItem.getString("utf-8"));//如果你页面编码是utf-8的
                }
            }
            //使用params.get获取参数值 send_time就是key
            uname = (String) params.get("username");
            pwd = (String) params.get("password");
            checkCode = (String) params.get("checkCode");
            remember = (String) params.get("remember");

        } catch (FileUploadException e1) {
            e1.printStackTrace();
        }
        System.out.println(uname);
        System.out.println(pwd);
        System.out.println(checkCode);
        System.out.println(remember);
        System.out.println("验证码"+sessionCode);


        //验证码正确
        if (checkCode.equals(sessionCode)) {
            //登录验证
            UserService userService = new UserService();
            boolean Islogin = false;
            try {
                Islogin = userService.isLogined(uname, pwd);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //登录失败
            if (Islogin == false) {
                response.getWriter().write("" + "1");
            }
            //登录成功
            else {
                String id=null;
                //记住账号
                if ("true".equals (remember)) {

                    try {
                        id=userService.getId(uname);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //1. 创建Cookie对象
                    Cookie c_username = new Cookie("username", uname);
//                Cookie c_password = new Cookie("password",pwd);
                    // 设置Cookie的存活时间
                    c_username.setMaxAge(60 * 60);
//                c_password.setMaxAge( 60 * 60 * 24 * 7);
                    //2. 发送
                    response.addCookie(c_username);
//                    response.getWriter().write("" + "3");

//                response.addCookie(c_password);
                }

                session.setAttribute("username",id);
                response.getWriter().write("" + "2");
            }
        } else {
            //验证码不正确
            response.getWriter().write("" + "0");
        }
    }


}
