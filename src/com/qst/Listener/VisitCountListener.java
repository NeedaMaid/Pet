package com.qst.Listener;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.*;

@WebListener()
public class VisitCountListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public VisitCountListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    @Override
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed).
         You can initialize servlet context related data here.
      */
        /*web初始化时，容器调用此方法啊*/
        //获取ServletContext对象
        ServletContext context = sce.getServletContext();
        try {

            //从文件中读取计数器的数值
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResourceAsStream("/count.txt")));
            String strcount = reader.readLine();
            int count;
            if (strcount == null || "".equals(strcount)) {
                count = 1;
            } else {
                count = Integer.parseInt(strcount) + 1;
            }
            System.out.println(count);
            reader.close();
            //把计数器对象保存到web应用范围
            context.setAttribute("count", count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context
         (the Web application) is undeployed or
         Application Server shuts down.
      */
        /*
        web停止时，容器调用此方法
        */

        //获取ServletContext对象
        ServletContext context = sce.getServletContext();
        //从web应用范围获得计算器对象
        Integer counter = (Integer) context.getAttribute("count") - 1;
        if (counter != null) {
            try {
                //把计数器的数值写到项目发布目录下的count.text文件中
                String filepath = context.getRealPath("/") + "/count.txt";
                PrintWriter pw = new PrintWriter(filepath);
                pw.println(counter.intValue());
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------
    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is added to a session.
      */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
