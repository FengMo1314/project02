package com.ccit.bean;

public class UserBean {

    private int id;
    private String UserName;
    private String PassWord;
    private String filename;


    public UserBean(int id, String userName, String passWord) {
        this.id = id;
        this.UserName = userName;
        this.PassWord = passWord;
    }

    public UserBean(String userName) {
        this.UserName = userName;
    }

    public UserBean() {

    }
    public UserBean(int id, String userName, String passWord, String filename) {
        this.id = id;
        this.UserName = userName;
        this.PassWord = passWord;
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", UserName='" + UserName + '\'' +
                ", PassWord='" + PassWord + '\'' +
                '}';
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
