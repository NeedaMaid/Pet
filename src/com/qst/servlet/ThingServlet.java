package com.qst.servlet;


import com.qst.bean.PageBean;
import com.qst.bean.Pet;
import com.qst.dao.PetDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
@WebServlet(urlPatterns = "/ThingServlet")
public class ThingServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.getSession().setAttribute("head", 1);
        String petName = request.getParameter("name");

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
        PetDao petDao = new PetDao();
        Integer totalRows;
        //5.起始行 startRow
        Integer startRow = (currentPage - 1) * pageSize;

        //把所有宠物信息查询出来
        List<Pet> petList;
        if (petName != null) {
            request.setAttribute("name", petName);
            totalRows = petDao.getPetCountByName(petName);
            petList = petDao.getPetListNameByPage(startRow, pageSize, petName);
        } else {
            totalRows = petDao.getPetCount();
            petList = petDao.getPetListByPage(startRow, pageSize);
        }

        PageBean pageBean = new PageBean(currentPage, pageSize, totalRows, petList);
        request.setAttribute("pageBean", pageBean);

        request.getRequestDispatcher("/applicantion/thing.jsp").forward(request, response);
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
