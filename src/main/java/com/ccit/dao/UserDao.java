package com.ccit.dao;


import com.ccit.DB.DruidConnect;
import com.ccit.bean.UserBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final DruidConnect dc = new DruidConnect(".../../User.properties");
    private static final DataSource ds =dc.getDataSource();//得到一个连接池
    static Connection con;
    static PreparedStatement pstmt;
    static ResultSet rs;
    static UserBean userBean;
    static List<UserBean> useerBeanlist;
    public List<UserBean> selectAllUser() {
        List<UserBean> userBeanList = null;
//        String[] sqls = new String[2];
//        sqls[0] = "alter table userinfo02 drop id;";
//        sqls[1] = "alter TABLE userinfo02 add id int primary key auto_increment FIRST;";

        String sql = "SELECT * FROM userinfo02";

        try {
            con = ds.getConnection();

//            for (String s : sqls) {
//                stmt.addBatch(s);// 将所有的SQL语句添加到Statement中
//            }
//            stmt.executeBatch();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            //获取结果并对结果进行遍历封装
            userBeanList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String pass = rs.getString("password");
//                userBeanList.add(new userBeanList(id, username, pass));
            }
            con.close();
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("通过SQL语句" + sql + "传入参数" + "" + "返回了结果集合\n" + userBeanList.toString());
        }

        return userBeanList;
    }

}
