package com.qst.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/laji")
public class laji extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


        //获取前台提交的email和密码
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String type = request.getParameter("type");

        System.out.println(email+" "+password);
        if (type != null) {
            PrintWriter out = response.getWriter();
            if (type.equals("false")) {
                out.print("email格式不正确！");
            } else {
                out.print("email格式正确！");
            }
        } else {
            //根据email和密码查询申请人

        }
    }

}
