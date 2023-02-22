package com.ccit.web;

import com.alibaba.fastjson2.JSON;
import com.ccit.bean.GoodsBean;
import com.ccit.dao.GoodsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AllGoodsServlet", value = "/AllGoodsServlet")
public class AllGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        List<GoodsBean> list = GoodsDao.selectAll();
        PrintWriter out = response.getWriter();
        String json = JSON.toJSONString(list);
        out.write(json);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          this.doGet(request,response);
    }
}
