package com.qst.servlet;

import com.qst.bean.User;
import com.qst.dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
重定向时名字输出为空
        重定向
            response.sendRedirect("/offers");
        1.重定向是两次请求，不能访问之前放在请求作用域的数据
        2.地址栏会改变 变成ServletB

        请求转发
            RequestDispatcher dispatcher = request.getRequestDispatcher("/signIn.html");
            dispatcher.forward(request,response);
        重定向地址（地址栏不会变成ServletB）
        1.可以访问应用之外的资源（部署的其他项目资源）

 */
@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println(request.getSession().getAttribute("preUrl"));
        String method = request.getParameter("method");
        if ("delete".equals(method)) {
            request.getSession().removeAttribute("SESSION_USER");
            response.sendRedirect("/PetParadise/applicantion/mainPage.jsp");
            return;
        }

        //获取前台提交的email和密码
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String type = request.getParameter("type");

        if (type != null) {
            PrintWriter out = response.getWriter();
            if (type.equals("false")) {
                out.print("email格式不正确！");
            } else {
                out.print("email格式正确！");
            }
        } else {
            //根据email和密码查询申请人
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByEmailAndPass(email, password);

            //判断applicant==null，返回登录页面，不为空就进入主页面
            if (user != null) {
                //将登录用户对象保存到session
                request.getSession().setAttribute("SESSION_USER", user);
                //重定向
                String a = (String) request.getSession().getAttribute("preUrl");
                response.sendRedirect((String) request.getSession().getAttribute("preUrl"));
                request.getSession().removeAttribute("preUrl");
                System.out.println("删除");
            } else {

                PrintWriter writer = response.getWriter();
                writer.write("<script>");
                writer.write("    alert('用户名或者密码错误');");
                writer.write("    window.location.href='/PetParadise/applicantion/signIn.jsp';");
                writer.write("</script>");
                writer.flush();
                writer.close();

                //请求转发
            }
        }
    }
}
