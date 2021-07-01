package com.qst.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebFilter(urlPatterns = {"/*"})
public class filter implements Filter {

    private String attribute = null;
    private FilterConfig filterConfig = null;

    public filter() {
    }

//结束前执行的释放方法

    @Override
    public void destroy() {
        this.attribute = null;
        this.filterConfig = null;
    }

//执行过滤功能的方法

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
/*        response.setCharacterEncoding("charset=UTF-8");//这样会把CSS文件和JS文件转化为html文件,但是在if中排除了
        response.setContentType("text/html;charset=UTF-8");*/

        //获取会话对象
        HttpSession session = request.getSession();
        //获取请求资源路径（不包含请求参数）
        String requestPath = request.getServletPath() + request.getQueryString();
        if (requestPath.contains("action=buy") ||
                requestPath.contains("OrderServlet") ||
                requestPath.contains("ShoppingCartServlet")) {
            if (request.getSession().getAttribute("SESSION_USER") == null) {
                System.out.println("kong" + requestPath);
                response.sendRedirect("/PetParadise/applicantion/signIn.jsp");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    //先执行
    @Override
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }
}


