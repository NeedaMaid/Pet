package com.qst.servlet;

import com.qst.bean.User;
import com.qst.dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/*
   重定向
            response.sendRedirect("/PetParadise");
        1.重定向是两次请求，不能访问之前放在请求作用域的数据
        2.地址栏会改变 变成ServletB

        请求转发
            RequestDispatcher dispatcher = request.getRequestDispatcher("/signIn.html");
            dispatcher.forward(request,response);
        重定向地址（地址栏不会变成ServletB）
        1.可以访问应用之外的资源（部署的其他项目资源）
*/
@WebServlet(urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String type = request.getParameter("type");

        if (type != null) {
            PrintWriter out = response.getWriter();
            if (type.equals("false")) {
                out.print("email格式不正确！");
            } else {

                //根据email和密码查询申请人
                UserDAO userDAO = new UserDAO();
                Integer count = userDAO.selectUserEmailCount(email);

                if (count <= 0) {
                    System.out.println(email + " " + count);
                    out.print(" 邮箱可以使用");
                } else {
                    out.print(" 邮箱已被使用");
                }
            }
        } else {
            UserDAO userDAO = new UserDAO();

            //开始插入数据库
            //对数据进行封装

            User user = new User(email, password, null, 0);


            //保存applicant到数据库 ApplicantDAO

            //判断是否有相同的email

            Integer count = userDAO.selectUserEmailCount(email);
            if (count <= 0) {

                //flag是否注册成功(保存到数据库)
                boolean flag = userDAO.saveUser(user);

                //注册成功就跳转到登录页面 失败就回到注册页面
                if (flag) {
                    //重定向
                    response.sendRedirect("/PetParadise/applicantion/signIn.jsp");
                } else {
                    //请求转发
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/PetParadise/applicantion/register.jsp");
                    dispatcher.forward(request, response);
                }
            }
        }
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
