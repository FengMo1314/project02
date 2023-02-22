package com.ccit.web;

import com.ccit.service.UserService;
import com.ccit.util.AboutCookies;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 注册
 */
@WebServlet("/regist4")
public class RegistServlet4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决post请求中文乱码
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        //获取session中的验证码,也就是CodeServlet中生成的四个字符
        String sessionCode = (String) session.getAttribute("sCode");
        response.setContentType("text/html;charset=utf-8");
        // 创建文件工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置临时文件目录
        File f = new File("D:\\MyProgrammingLanguagesWorkspace\\project02\\project02\\src\\main\\java\\in");
        if (!f.exists()) {
            f.mkdir();
        }
        //设置文件的缓存路径
        factory.setRepository(f);
        System.out.println("f"+f);

        // 创建文件解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");

        try {
            // 解析请求
            List<FileItem> fileitems = upload.parseRequest(request);
//            System.out.println(fileitems);
            Map params = new HashMap();
            //
            String uname = null;
            String pwd = null;
            String checkCode = null;
            //
            String fileName = null;
            String fieldName = null;
            // 遍历请求中的所有文件
            FileItem item = null;
            for (FileItem fileitem : fileitems) {
                // 判断当前文件是否是普通表单字段
                if (fileitem.isFormField()) {

                    params.put(fileitem.getFieldName(), fileitem.getString("utf-8"));
                    uname = (String) params.get("username");
                    pwd = (String) params.get("password");
                    checkCode = (String) params.get("checkCode");
                    fieldName = fileitem.getFieldName();

                } else if (!fileitem.isFormField()) {
                    item = fileitem;
                    // 获取文件名
                    fileName = fileitem.getName();
                    boolean isInMemory = fileitem.isInMemory();
                    long sizeInBytes = fileitem.getSize();
                    System.out.println("fieldName:" + fieldName);
                    System.out.println("fileName:" + fileName);
                    System.out.println("isInMemory:" + isInMemory);
                    System.out.println("sizeInBytes:" + sizeInBytes);
                }
            }
            System.out.println(uname);
            System.out.println(pwd);
            System.out.println(checkCode);
            System.out.println("验证码: " + sessionCode);

            //验证码正确
            if (checkCode.equals(sessionCode)) {
                //注册验证
                UserService userService = new UserService();
                boolean IsRegist = false;
                try {
                    IsRegist = userService.registUser(uname, pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //注册失败
                if (IsRegist == false) {
                    response.getWriter().write("" + "1");
                }
                //注册成功
                else {
                    // 写入文件
                    //避免同名
                    String newfilename = null;
                    if (item != null) {
                        AboutCookies ac=new AboutCookies(request,response);

                        newfilename = fileName.substring(fieldName.lastIndexOf("\\") + 1);
                        String webPath = "/upload/";
                        newfilename = UUID.randomUUID().toString() + "_" + newfilename;  //避免同名
                        String filepath = getServletContext().getRealPath(webPath + newfilename);
                        System.out.println("filepath:"+filepath);
                        ac.setCoookie("imgpath",filepath);
                        File file = new File(filepath);
                        System.out.println(file);
                        item.write(file);
                        //删除临时文件
                        item.delete();
                    }
                    try {
                        userService.upload(uname, newfilename);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    response.getWriter().write("" + "2");
                }
            } else {
                //验证码不正确
                response.getWriter().write("" + "0");
            }


        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
