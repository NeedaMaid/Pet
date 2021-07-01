package com.qst.servlet;


import com.qst.bean.Favorite;
import com.qst.bean.PageBean;
import com.qst.bean.Pet;
import com.qst.bean.User;
import com.qst.dao.FavoriteDao;
import com.qst.dao.OrderDao;
import com.qst.dao.PetDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.Integer.parseInt;


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
@WebServlet(urlPatterns = "/BuyServlet")
public class BuyServlet extends HttpServlet {

    protected void buy(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        //修改pet数据库

        int id = ((User) request.getSession().getAttribute("SESSION_USER")).getId();
        int petid = Integer.parseInt(request.getParameter("petId"));
        int buynumber = Integer.parseInt(request.getParameter("number"));


        PetDao petDao = new PetDao();
        int number = petDao.getPetById(petid).getNumber();
        boolean flag = petDao.updateNumberByPetId(number - buynumber, petid);

        if (flag) {
            if (request.getHeader("Referer").endsWith("action=show")) {
                FavoriteDao favoriteDao = new FavoriteDao();
                favoriteDao.delete(id, petid);
                System.out.println("删除顶顶那");
            }
            OrderDao orderDao = new OrderDao();
            orderDao.saveOrder(id, petid, buynumber);
            String head = request.getHeader("Referer");
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write("    alert('购买成功');");
            writer.write("    window.location.href='" + head + "';");
            writer.write("</script>");
            writer.flush();
            writer.close();

        } else {
            response.getWriter().write("购买失败");
        }
    }

    protected void show(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String petid = request.getParameter("petId");
        PetDao petDao = new PetDao();
        Pet choicePet = petDao.getPetById(parseInt(petid));

        request.getSession().setAttribute("choicePet", choicePet);
        request.getRequestDispatcher("/applicantion/buy.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        if ("buy".equals(request.getParameter("action"))) {
            buy(request, response);
        } else {
            show(request, response);
        }
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
