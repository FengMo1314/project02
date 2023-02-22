package com.ccit.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.ccit.bean.GoodsBean;
import com.ccit.dao.GoodsDao;
import com.ccit.util.AboutJson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SomeGoodsServlet", value = "/SomeGoodsServlet")
public class SomeGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//请求编码类型
        /**
         * 接收json
         //         */
        JSONObject data = AboutJson.getJson(request);
        String name = "柳和";
        if (data != null) {
            name = String.valueOf(data.get("names"));
            System.out.println(name);
        }
        response.setCharacterEncoding("UTF-8");//响应编码类型
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        List<GoodsBean> list = GoodsDao.selectSomeByGoods_Name(name);
        String jsonout = JSON.toJSONString(list);
        out.write(jsonout);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         this.doGet(request,response);
    }
}
