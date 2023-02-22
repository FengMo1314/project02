package com.ccit.dao;


import com.ccit.DB.DruidConnect;
import com.ccit.bean.UserBean;

import javax.sql.DataSource;
import java.sql.*;
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
    /**
     * 登录
     */
    public boolean login(String uname, String upass) throws SQLException {
        con = ds.getConnection();
        //3. 定义sql
        String sql = "select * from tb_user where username = ? and password = ?";

        //4. 获取statement对象
        //Statement stmt = con.createStatement();
        PreparedStatement stmt = con.prepareStatement(sql);

        // 设置？的值
        stmt.setString(1, uname);
        stmt.setString(2, upass);

        //5. 执行sql
//        ResultSet rs = stmt.executeQuery(sql);
        ResultSet rs = stmt.executeQuery();

        boolean hasUser = false;

        //6. 处理结果， 遍历rs中的所有数据
        // 6.1 光标向下移动一行，并且判断当前行是否有数据
        while (rs.next()) {
            //获取数据
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String pass = rs.getString(3);
            System.out.println("id: " + id + " 用户: " + name + " 密码: " + pass + "登录成功~~");
            hasUser = true;
        }
        con.close();
        return hasUser;
    }

    /**
     * 注册
     */
    public boolean regist(String uname, String upass) throws SQLException {
         con = ds.getConnection();
        //3. 定义sql
        String sql = "INSERT INTO tb_user (username, password) VALUES ('" + uname + "','" + upass + "')";
        String sql2 = "select * from tb_user where username='" + uname + "'";

        //4. 获取statement对象
        Statement stmt = con.createStatement();
        //注册验证
        if (uname == "" ||
                upass == "" ||
                (uname == "" && upass == "") ||
                stmt.executeQuery(sql2).next() == true
        ) {
            return false;
        }
        int count = stmt.executeUpdate(sql);
        con.close();
        if (count > 0) {
            //注册成功
            return true;
        } else {
            //注册失败
            return false;
        }


    }

    /**
     * 根据用户名查询id
     * 获取id
     * @return
     */
    public String getIdByUsername(String uname) throws SQLException {
        Connection con = ds.getConnection();
        String sql3 = "select id from tb_user where username='" + uname + "'";
        Statement stmt = con.createStatement();

        String id = null;
        ResultSet rs = stmt.executeQuery(sql3);
        while (rs.next()) {
            id = rs.getString(1);
        }
        return id;
    }

    /**
     * 根据用户名查询用户
     * 查询用户
     */
    public List<UserBean> getUserByUsername(String uname) throws SQLException {
        useerBeanlist = new ArrayList<UserBean>();
        //1.定义连接
        con = ds.getConnection();
        //2. 定义sql
        String sql = "select * from tb_user where id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        // 设置？的值
        stmt.setString(1, uname);
        //5. 执行sql
        ResultSet rs = stmt.executeQuery();
        System.out.println("in UserLoginSafe:"+sql);
        boolean hasUser = false;
        //6. 处理结果， 遍历rs中的所有数据
        // 6.1 光标向下移动一行，并且判断当前行是否有数据
        while (rs.next()) {
            //6.2 获取数据  getXxx()
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String pass = rs.getString(3);
            String filename = rs.getString(4);

            UserBean user = new UserBean(id, name,pass, filename);
            useerBeanlist.add(user);
        }
        con.close();
        return useerBeanlist;
    }

    /**
     * 根据id删除用户
     * 删除
     */
    public boolean deletById(int id) throws SQLException {
         con = ds.getConnection();
        //3. 定义sql
        String sql = "delete from tb_user where id='" + id + "'";

        //4. 获取statement对象
        Statement stmt = con.createStatement();

        //5. 执行sql
        int num = stmt.executeUpdate(sql);
        con.close();
        stmt.close();

        if (num > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 更新
     */
    public boolean updateById(int id, String uname, String upass) throws Exception {
         con = ds.getConnection();

        //3. 定义sql
        String sql = "update tb_user set username = '" + uname + "',password = '" + upass + "' where id = '" + id + "'";
        String sql2 = "select * from tb_user where username='" + uname + "'";

        //4. 获取statement对象
        Statement stmt = con.createStatement();
        //5. 执行sql
        ResultSet rs = stmt.executeQuery(sql2);
        int sid = 0;
        while (rs.next()) {
            sid = rs.getInt(1);
        }
        //更新验证
        if (uname == "" ||
                upass == "" ||
                (uname == "" && upass == "") ||
                //
                stmt.executeQuery(sql2).next() == true && Integer.parseInt(String.valueOf(id)) != sid
        ) {
            return false;
        }

        int count = stmt.executeUpdate(sql);
        con.close();
        stmt.close();
        rs.close();

        if (count > 0) {
            //更新成功
            return true;
        } else {
            //更新失败
            return false;
        }
    }

    /**
     * 上传
     */
    public boolean upload(String uname, String filename) throws Exception {
        con = ds.getConnection();

        //3. 定义sql
        String sql = "UPDATE `tb_user` SET `filename`='"+filename+"' WHERE (`username`='"+uname+"')";

        //4. 获取statement对象
        Statement stmt = con.createStatement();
        int count = stmt.executeUpdate(sql);
        con.close();
        stmt.close();
        if (count > 0) {
            //更新成功
            return true;
        } else {
            //更新失败
            return false;
        }
    }
    /**
     * 获取信息
     */
    public List<UserBean> getAlluser() throws Exception {
         useerBeanlist = new ArrayList<UserBean>();
        //1.定义连接
        Connection con = ds.getConnection();
        //2. 定义sql
        String sql = "select * from tb_user";
        //4. 获取statement对象
        Statement stmt = con.createStatement();
        //5. 执行sql
        ResultSet rs = stmt.executeQuery(sql);
        //6. 处理结果， 遍历rs中的所有数据
        // 6.1 光标向下移动一行，并且判断当前行是否有数据
        while (rs.next()) {
            //6.2 获取数据  getXxx()
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String pass = rs.getString(3);
            String filename = rs.getString(4);
            UserBean user = new UserBean(id, name, pass,filename);
            useerBeanlist.add(user);

        }
        con.close();
        stmt.close();
        rs.close();
        return useerBeanlist;
    }
}
