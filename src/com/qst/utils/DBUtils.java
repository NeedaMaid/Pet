package com.qst.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.sql.Date;
import java.sql.*;
import java.util.*;

public class DBUtils {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "1838414a";
    private static final String url = "jdbc:mysql://localhost:3306/petparadise?serverTimezone=GMT&useSSL=true";
    //jdbc:mysql://localhost:3306/计算器?serverTimezone=GMT&user=root&password=1838414a&useSSL=true
    //jdbc:oracle:thin:@127.0.0.1:1521:orcl


    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //返回所有


    //保存对象方法
    public static boolean save(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        Integer count = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            //返回更新的记录数
            count = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeJDBC(null, ps, conn);
        }
        return count != null && count > 0 ? true : false;
    }

    //根据email和密码查询用户
    public static <T> T getSingleObj(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        T bean = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            rs = ps.executeQuery();

            //获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取当前结果集的列数
            int colnum = rsmd.getColumnCount();
            //数据存到map里
            //rs一行一行的来
            while (rs.next()) {
                //key存放列名，value存放列值，for循环完成之后，rowMap存放一条记录
                Map<String, Object> rowMap = new LinkedHashMap<>();
                for (int i = 1; i <= colnum; i++) {
                    String columnName = rsmd.getColumnLabel(i);
                    Object columnValue = rs.getObject(columnName);

                    if (columnValue instanceof Date) {
                        Date date = (Date) columnValue;
                        columnValue = new java.util.Date(date.getTime());
                    }
                    rowMap.put(columnName, columnValue);
                }
                //创建一个User对象，给这个User对象属性赋值；
                bean = clazz.getDeclaredConstructor().newInstance();

                //循环rowMap给所有属性赋值 entry {key,value}[{}]

                for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    //System.out.println(propertyName+"   "+propertyValue);
                    BeanUtils.setProperty(bean, propertyName, propertyValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeJDBC(rs, ps, conn);
        }
        return bean;
    }

    //更新操作并返回主键
    public static Integer updateForPrimary(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer primaryKey = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {

                    //判断日期类型是不是java.util.Date,转换成java.sql.Date
                    if (args[i] instanceof java.util.Date) {
                        java.util.Date date = (java.util.Date) args[i];
                        //转换成java.sql.Date
                        args[i] = new Date(date.getTime());
                    }
                    ps.setObject(i + 1, args[i]);
                }
            }
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                primaryKey = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeJDBC(rs, ps, conn);
        }
        return primaryKey;
    }

    //更新操作
    public static boolean update(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        Integer count = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {

                    //判断日期类型是不是java.util.Date,转换成java.sql.Date
                    if (args[i] instanceof java.util.Date) {
                        java.util.Date date = (java.util.Date) args[i];
                        //转换成java.sql.Date
                        args[i] = new Date(date.getTime());
                    }
                    ps.setObject(i + 1, args[i]);
                }
            }
            count = ps.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeJDBC(null, ps, conn);
        }
        return count > 0 ? true : false;
    }

    //删除操作
    public static boolean delete(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        Integer count = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {

                    //判断日期类型是不是java.util.Date,转换成java.sql.Date
                    if (args[i] instanceof java.util.Date) {
                        java.util.Date date = (java.util.Date) args[i];
                        //转换成java.sql.Date
                        args[i] = new Date(date.getTime());
                    }
                    ps.setObject(i + 1, args[i]);
                }
            }
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeJDBC(null, ps, conn);
        }
        return count > 0 ? true : false;
    }

    public static void closeJDBC(ResultSet rs, Statement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //查找所有
    //类型参数，类型变量类型是可以变化
    public static <T> List<T> getList(Class<T> clazz, String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> userList = null;
        try {

            conn = DBUtils.getConnection();
            //String sql = "select id,username,birthday from users where id = ? and username = ?";
            ps = conn.prepareStatement(sql);

            if (args != null && args.length > 0) {

                for (int i = 0; i < args.length; i++) {

                    ps.setObject(i + 1, args[i]);
                }
            }


            rs = ps.executeQuery();

            //获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取当前结果集的列数
            int colnum = rsmd.getColumnCount();

            userList = new ArrayList<>();

            while (rs.next()) {

                //key存放列名，value存放列值，for循环完成之后，rowmap存放了一条记录
                Map<String, Object> rowMap = new HashMap<String, Object>();
                for (int i = 1; i <= colnum; i++) {
                    String columnName = rsmd.getColumnLabel(i);
                    Object columnValue = rs.getObject(columnName);
                    //判断查询出来的列的类型，如果是java.sql.Date转换成java.util.Date
                    if (columnValue instanceof Date) {
                        Date date = (Date) columnValue;
                        columnValue = new java.util.Date(date.getTime());
                    }
                    rowMap.put(columnName, columnValue);
                }
                //创建一个User对象，给这个User对象赋值
                T bean = clazz.getDeclaredConstructor().newInstance();

                //循环rowMap给所有属性赋值
                for (Map.Entry<String, Object> entry : rowMap.entrySet()) {

                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    BeanUtils.setProperty(bean, propertyName, propertyValue);
                }

                userList.add(bean);
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            closeJDBC(rs, ps, conn);
        }

        return userList;
    }


    public static <T> List<T> getListLike(Class<T> clazz, String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> userList = null;
        try {

            conn = DBUtils.getConnection();
            //String sql = "select id,username,birthday from users where id = ? and username = ?";
            ps = conn.prepareStatement(sql);

            if (args != null && args.length > 0) {
                ps.setString(1, "%" + args[0] + "%");
                for (int i = 1; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            rs = ps.executeQuery();

            //获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取当前结果集的列数
            int colnum = rsmd.getColumnCount();

            userList = new ArrayList<>();

            while (rs.next()) {

                //key存放列名，value存放列值，for循环完成之后，rowmap存放了一条记录
                Map<String, Object> rowMap = new HashMap<String, Object>();
                for (int i = 1; i <= colnum; i++) {
                    String columnName = rsmd.getColumnLabel(i);
                    Object columnValue = rs.getObject(columnName);
                    //判断查询出来的列的类型，如果是java.sql.Date转换成java.util.Date
                    if (columnValue instanceof Date) {
                        Date date = (Date) columnValue;
                        columnValue = new java.util.Date(date.getTime());
                    }
                    rowMap.put(columnName, columnValue);
                }
                //创建一个User对象，给这个User对象赋值
                T bean = clazz.getDeclaredConstructor().newInstance();

                //循环rowMap给所有属性赋值
                for (Map.Entry<String, Object> entry : rowMap.entrySet()) {

                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    BeanUtils.setProperty(bean, propertyName, propertyValue);
                }

                userList.add(bean);
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            closeJDBC(rs, ps, conn);
        }

        return userList;
    }
    public static Integer getCountLike(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer count = null;
        try {
            conn = DBUtils.getConnection();

            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                ps.setString(1, "%" + args[0] + "%");
                for (int i = 1; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            rs = ps.executeQuery();

            //rs一行一行的来
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeJDBC(rs, ps, conn);
        }
        return count;
    }


    //查询记录数量
    public static Integer getCount(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer count = null;
        try {
            conn = DBUtils.getConnection();

            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            rs = ps.executeQuery();

            //rs一行一行的来
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeJDBC(rs, ps, conn);
        }
        return count;
    }

}
