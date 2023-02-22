package com.ccit.dao;

import com.ccit.DB.DruidConnect;
import com.ccit.bean.GoodsBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsDao {
    private static final DruidConnect dc = new DruidConnect(".../../Goods.properties");
    private static final DataSource ds =dc.getDataSource();//得到一个连接池
    static Connection con;
    static PreparedStatement pstmt;
    static ResultSet rs;
    static GoodsBean goodsBean;
    static List<GoodsBean> goodsBeanlist;
    public static List<GoodsBean> selectAll() {
        goodsBeanlist = new ArrayList<>();
        String sql = "SELECT * FROM  tb_pioneer_mall_goods_info;";
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, goods_name);
            rs = pstmt.executeQuery();
            //获取结果并对结果进行遍历封装
            while (rs.next()) {
                int goods_id = rs.getInt("goods_id");
                String goods_name = rs.getString("goods_name");
                String goods_intro = rs.getString("goods_intro");
                int goods_category_id = rs.getInt("goods_category_id");
                String goods_cover_img = rs.getString("goods_cover_img");
                String goods_carousel = rs.getString("goods_carousel");
                String goods_detail_content = rs.getString("goods_detail_content");
                int original_price = rs.getInt("original_price");
                int selling_price = rs.getInt("selling_price");
                int stock_num = rs.getInt("stock_num");
                String tag = rs.getString("tag");
                int goods_sell_status = rs.getInt("goods_sell_status");
                int create_user = rs.getInt("create_user");
                Date create_time = rs.getTime("create_time");
                int update_user = rs.getInt("update_user");
                Date update_time = rs.getTime("update_time");
                goodsBean = new GoodsBean(goods_id, goods_name, goods_intro, goods_category_id, goods_cover_img, goods_carousel, goods_detail_content, original_price, selling_price, stock_num, tag, goods_sell_status, create_user, create_time, update_user, update_time);
                goodsBeanlist.add(goodsBean);
            }
            con.close();
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("通过SQL语句" + sql + "传入参数" + "" + "返回了结果集合\n" + goodsBeanlist.toString());
        }
        return goodsBeanlist;
    }
    /*
       通过Goods_Name模糊查询
        */
    public static List<GoodsBean> selectSomeByGoods_Name(String somegoods_name) {
        goodsBeanlist = new ArrayList<>();
        String sql = "SELECT * FROM tb_pioneer_mall_goods_info WHERE goods_name LIKE ?;";
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + somegoods_name + "%");
            rs = pstmt.executeQuery();
            //获取结果并对结果进行遍历封装
            while (rs.next()) {
                int goods_id = rs.getInt("goods_id");
                String goods_name = rs.getString("goods_name");
                String goods_intro = rs.getString("goods_intro");
                int goods_category_id = rs.getInt("goods_category_id");
                String goods_cover_img = rs.getString("goods_cover_img");
                String goods_carousel = rs.getString("goods_carousel");
                String goods_detail_content = rs.getString("goods_detail_content");
                int original_price = rs.getInt("original_price");
                int selling_price = rs.getInt("selling_price");
                int stock_num = rs.getInt("stock_num");
                String tag = rs.getString("tag");
                int goods_sell_status = rs.getInt("goods_sell_status");
                int create_user = rs.getInt("create_user");
                Date create_time = rs.getTime("create_time");
                int update_user = rs.getInt("update_user");
                Date update_time = rs.getTime("update_time");
                goodsBean = new GoodsBean(goods_id, goods_name, goods_intro, goods_category_id, goods_cover_img, goods_carousel, goods_detail_content, original_price, selling_price, stock_num, tag, goods_sell_status, create_user, create_time, update_user, update_time);
                goodsBeanlist.add(goodsBean);
            }
            con.close();
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("通过SQL语句" + sql + "传入参数" + somegoods_name + "返回了结果集合\n" + goodsBeanlist.toString());
        }
        return goodsBeanlist;
    }
    public static void main(String[] args) {
        for (GoodsBean gb:selectSomeByGoods_Name("hu")
             ) {
            System.out.println(gb.toString());
        } 
    }
}
