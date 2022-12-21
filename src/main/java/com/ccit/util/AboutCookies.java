package com.ccit.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class AboutCookies {
    static HttpServletRequest request;
    static HttpServletResponse response;

    public AboutCookies(HttpServletRequest request, HttpServletResponse response) {
        AboutCookies.request = request;
        AboutCookies.response = response;
    }

    public String getCookie(String name) {

        Cookie[] strcookie = request.getCookies();//获取cookie字符串
        String[] arrcookie = strcookie.toString().split(";");//分割
        //遍历匹配
        for (int i = 0; i < arrcookie.length; i++) {
            String[] arr = arrcookie[i].split("=");
            if (arr[0] == name) {
                return arr[1];
            }
        }
        return "";
    }

    public String getCookieForJSP(String key) throws UnsupportedEncodingException {
        Cookie cookie = null;
        Cookie[] cookies = null;
        // 获取cookies的数据,是一个数组
        cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals(key)) {
                    return URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                }
            }
        }
        return "";
    }

    // 打印所有cookie
    public void printCookie() {
        Cookie[] strcookie = request.getCookies();//获取cookie字符串
        String[] arrcookie = strcookie.toString().split(";");//分割
        //遍历匹配
        for (int i = 0; i < arrcookie.length; i++) {
            String[] arr = arrcookie[i].split("=");
            String rest = arr[0] + ":" + arr[1];
            System.out.println(rest);
        }
    }

    public String printCookieForJSP() throws UnsupportedEncodingException {
        Cookie cookie = null;
        Cookie[] cookies = null;
        // 获取cookies的数据,是一个数组
        String html = "";
        cookies = request.getCookies();
        if (cookies != null) {
            html += "<h2> 查找 Cookie 名与值</h2>";
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                html += "参数名 : " + cookie.getName() + "<br>参数值: " + URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8) + "<br>" + "------------------------------------<br>";
            }
        }
        return html;
    }

    /**
     *
     * @param key cookie的名字
     * @param value 值
     */
    public void setCoookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(10000);
        cookie.setDomain("/");
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     *
     * @param keys 一组建
     * @param values 一组对应的值
     */
    public void setCookies(String[] keys, String[] values) {
        for (int i = 0; i < keys.length; i++) {
            setCoookie(keys[i], values[i]);
        }
    }

    /**
     * 删除本地客户端所有的cookies
     */
    public void dellAllCookies() {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            System.out.println("dd" + cookie);
        }
    }

    public void dellCookiesByKey(String key) {
        Cookie newCookie = new Cookie(key, null); //假如要删除名称为username的Cookie

        newCookie.setMaxAge(0); //立即删除型

        newCookie.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除

        response.addCookie(newCookie); //重新写入，将覆盖之前的
    }

}
