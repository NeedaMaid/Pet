package com.qst.Listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/ContextAttributeServlet")
public class ContextAttributeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ContextAttributeServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应到客户端的文本类型
        response.setContentType("text/html;charset=UTF-8");
        //获取ServletContext对象
        ServletContext context = super.getServletContext();
        //从ServletContext对象获取count属性存储的计数值
        Integer count = (Integer) context.getAttribute("count");
        if (count == null) {
            count = 1;
        } else {
            count = count + 1;
        }
        //将更新后的数据存储到ServletContext对象中的count属性中
        context.setAttribute("count", count);
        //获取输出流
        PrintWriter out = response.getWriter();
        //输出计数信息
        out.println("<p>本地请求地址目前访问人数是：" + count + "</p>");
    }
}
