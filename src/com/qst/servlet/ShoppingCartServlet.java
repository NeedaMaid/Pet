package com.qst.servlet;


import com.qst.bean.Favorite;
import com.qst.bean.PageBean;
import com.qst.bean.Pet;
import com.qst.bean.User;
import com.qst.dao.FavoriteDao;
import com.qst.dao.PetDao;
import com.qst.utils.DBUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


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
@WebServlet(urlPatterns = "/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        User user = (User) request.getSession().getAttribute("SESSION_USER");
        int id = user.getId();
        FavoriteDao favoriteDao = new FavoriteDao();
        int petId = Integer.parseInt(request.getParameter("petId"));
        favoriteDao.delete(id, petId);
        show(request, response);
    }

    protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        User user = (User) request.getSession().getAttribute("SESSION_USER");
        int id = user.getId();
        FavoriteDao favoriteDao = new FavoriteDao();
        favoriteDao.deleteAll(id);
        request.getSession().setAttribute("message", "购物车为空");
        show(request, response);
    }


    protected void add(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        FavoriteDao favoriteDao = new FavoriteDao();
        User user = (User) request.getSession().getAttribute("SESSION_USER");
        int id = user.getId();
        int petId = Integer.parseInt(request.getParameter("petId"));
        int number = Integer.parseInt(request.getParameter("number"));

        Integer count = favoriteDao.selectNumberBypetIdAndId(petId, id);

        //购物车没有
        if (count == null || count <= 0) {
            favoriteDao.savePetIdAndNumber(id, petId, number);
        } else {
            favoriteDao.updateNumberByPetIdAndId(number + count, petId, id);
        }
        request.getSession().removeAttribute("message");
        response.getWriter().write("加入购物车成功");

    }

    protected void change(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        User user = (User) request.getSession().getAttribute("SESSION_USER");
        int id = user.getId();
        int petId = Integer.parseInt(request.getParameter("petId"));
        int number = Integer.parseInt(request.getParameter("number"));
        FavoriteDao favoriteDao = new FavoriteDao();
        favoriteDao.updateNumberByPetIdAndId(number, petId, id);

        PageBean pageBean = (PageBean) request.getAttribute("pagePet");

        response.getWriter().write("修改成功");

        PrintWriter writer = response.getWriter();
        writer.write("<script>");
        writer.write("    alert('用户名或者密码错误');");
        writer.write("    window.location.href='/PetParadise/applicantion/signIn.jsp';");
        writer.write("</script>");
        writer.flush();
        writer.close();

    }

    protected void show(HttpServletRequest request, HttpServletResponse response) throws
            javax.servlet.ServletException, IOException {
        User user = (User) request.getSession().getAttribute("SESSION_USER");
        int id = user.getId();

        //1.每页多少行 pageSize
        String pageSizeStr = request.getParameter("pageSize");
        Integer pageSize = null;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.valueOf(pageSizeStr);
        } else {
            pageSize = 12;
        }

        //2.当前是第几页 currentPage
        String currentPageStr = request.getParameter("currentPage");
        Integer currentPage = null;//当前查询第几页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.valueOf(currentPageStr);
        } else {
            currentPage = 1;
        }
        //3.一共有多少行数据 totalRows
        FavoriteDao favoriteDao = new FavoriteDao();
        Integer totalRows = favoriteDao.getPetCount(id);

        if (totalRows == 0) {
            request.getSession().setAttribute("message", "购物车为空");
        } else { //5.起始行 startRow
            request.getSession().removeAttribute("message");
            Integer startRow = (currentPage - 1) * pageSize;

            //把所有宠物信息查询出来
            List<Favorite> petIdList = favoriteDao.getPetIdListByPage(startRow, pageSize, id);
            List<Pet> petList = new ArrayList<>();
            PetDao petDao = new PetDao();
            for (Favorite favorite : petIdList) {
                Pet pet = petDao.getPetById(favorite.getPetId());
                pet.setNumber(favorite.getNumber());
                System.out.println(pet);
                petList.add(pet);
            }

            PageBean pageBean = new PageBean(currentPage, pageSize, totalRows, petList);
            request.setAttribute("pagePet", pageBean);
        }
        request.getRequestDispatcher("/applicantion/shoppingCart.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.getSession().setAttribute("head", 3);

        if ("show".equals(request.getParameter("action"))) {
            show(request, response);
        } else if ("add".equals(request.getParameter("action"))) {
            add(request, response);
        } else if ("change".equals(request.getParameter("action"))) {
            change(request, response);
        } else if ("deleteAll".equals(request.getParameter("action"))) {
            deleteAll(request, response);
        } else if ("delete".equals(request.getParameter("action"))) {
            delete(request, response);
        }
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse
            response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
