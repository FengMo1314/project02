package com.ccit.DB;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Druid连接池的工具类
 */
public class DruidConnect {
    //1.定义成员变量 DataSource
    private static DataSource ds;
    private String propertiesPath;

    public DruidConnect(String propertiesPath) {
        this.propertiesPath=propertiesPath;
        {
            try {
                //1.加载配置文件
                Properties pro = new Properties();
//            2.加载配置文件的路径
                pro.load(DruidConnect.class.getClassLoader().getResourceAsStream(this.propertiesPath));
                System.out.println("SQL：相对路径" + DruidConnect.class.getClassLoader().getResource("").getPath());
                //测试配置文件是否加载成功
                System.out.println("成功加载数据库用户名为：" + pro.getProperty("username") + "的数据库");
                //2.获取DataSource
                ds = DruidDataSourceFactory.createDataSource(pro);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取连接
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 释放资源
     */
    public static void close(Statement stmt, Connection conn) {
       /* if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();//归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/

        close(null, stmt, conn);
    }


    public static void close(ResultSet rs, Statement stmt, Connection conn) {


        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();//归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取连接池方法
     */

    public static DataSource getDataSource() {
        return ds;
    }

    public static void main(String[] args) throws SQLException {
        DruidConnect dc=new DruidConnect("../User.properties");

        ds = getDataSource();
        System.out.println(ds.getConnection());

        Connection con = ds.getConnection();

        String uname = "root";
        String upass = "123";


        String sql = "select * from userinfo02 where username=? and password=?";

        //2. 获取statement对象
        //Statement stmt = con.createStatement();
        PreparedStatement pstmt = con.prepareStatement(sql);

        System.out.println("in UserLoginSafe:" + sql);

        //3. 设置参数
//        pstmt.setInt();
        pstmt.setString(1, uname);
        pstmt.setString(2, upass);

        //4. 执行sql
        ResultSet rs = pstmt.executeQuery();

        boolean hasUser = false;

        //6. 处理结果， 遍历rs中的所有数据
        // 6.1 光标向下移动一行，并且判断当前行是否有数据
        while (rs.next()) {
            //6.2 获取数据  getXxx()
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String pass = rs.getString(3);
            System.out.println(id);
            System.out.println(name);
            System.out.println(pass);

            System.out.println("--------------");
            hasUser = true;

        }
//        return uname.equals("Admin") && upass.equals("123456");
        con.close();
        System.out.println("Has user!" + hasUser);

    }

}
