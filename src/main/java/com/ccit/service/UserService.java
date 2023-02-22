package com.ccit.service;

import com.ccit.dao.UserDao;
import com.ccit.bean.UserBean;

import java.util.List;

public class UserService {
    UserDao userDao = new UserDao();
    //登录
//    public boolean isLogined(String uname, String upass) throws Exception {
//    //'or '1' = '1
//        return new DruidDemo().UserLoginSafe(uname, upass);
//    }
    public boolean isLogined(String uname, String upass) throws Exception {
        //'or '1' = '1
        return userDao.login(uname, upass);
    }

    //注册
//    public boolean registUser(String uname, String upass) throws Exception {
//
//        return new DruidDemo().UserRegist(uname, upass);
//    }
    public boolean registUser(String uname, String upass) throws Exception {

        return userDao.regist(uname, upass);
    }

    //删除
//    public boolean deletUser(String id) throws Exception {
//        return new DruidDemo().UserDelete(Integer.valueOf(id));
//    }
    public boolean deletUser(String id) throws Exception {
        return userDao.deletById(Integer.valueOf(id));
    }

    //获取id
    public String getId(String uname) throws Exception {
        return userDao.getIdByUsername(uname);
    }

    //查询用户
    public List<UserBean> getUser(String uname) throws Exception {
        return userDao.getUserByUsername(uname);
    }

    //更新
//    public boolean updateUser(String id, String uname, String upass) throws Exception {
//
//        return new DruidDemo().UserUpdate(id, uname, upass);
//    }
    public boolean updateUser(int id, String uname, String upass) throws Exception {

        return userDao.updateById(id, uname, upass);
    }
    //上传
    public boolean upload(String uname, String filenam) throws Exception {

        return userDao.upload(uname,filenam);
    }

    //获取列表
//    public List<UserBean> getUserList() throws Exception {
//        return new DruidDemo().getUserList();
//    }
//    public List<UserBean> getUserList() throws Exception {
//        return userDao.getAlluser();
//    }
}
