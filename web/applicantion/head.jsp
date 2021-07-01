<%--
  Created by IntelliJ IDEA.
  User: 古添添
  Date: 2021/1/2
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/head.css">
    <title></title>
</head>
<body style="min-width: 1150px">
<div class="header" id="header">
    <div class="toolbar">
        <div class="toolbar_auto">
            <ul class="toolbar_left">
                <li onclick="oo(0)" class=""><a href="${appPath}/applicantion/mainPage.jsp">宠物乐园</a></li>
                <li onclick="oo(1)" class=""><a href="${appPath}/ThingServlet">宠物商场</a></li>
                <li onclick="oo(2)" class=""><a href="#">宠物百科</a></li>
                <li onclick="oo(3)" class=""><a href="${appPath}/ShoppingCartServlet?action=show">购物车</a></li>
                <li onclick="oo(4)" class=""><a href="${appPath}/OrderServlet">我的订单</a></li>
                <li onclick="oo(5)"><p style="display: inline-block">在线人数${count}</p></li>
            </ul>
            <ul class="toolbar_right">
                <% if (user != null) { %>
                <li><%= user.getemail() %>
                </li>
                <% } %>
                <% if (user == null) { %>
                <li><a href="${appPath}/applicantion/signIn.jsp">登录</a></li>
                <li class="left"><a href="${appPath}/applicantion/register.jsp">注册</a></li>
                <% } else { %>
                <li><a onclick="cc()" href="${appPath}/LoginServlet?method=delete">注销</a></li>
                <% }%>
                <li class="left"><a href="#">微博</a></li>
                <li class="left"><a href="#">微信</a></li>
            </ul>
        </div>
    </div>
</div>
<div style="height:44px;background-color: #ff6655;margin:30px 0 40px 0;"></div>
</body>
</html>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        document.getElementsByClassName("toolbar_left")[0].children[${head}].className = "toolbarColor";
    });

    function oo(j) {
        for (var i = 0; i < document.getElementsByClassName("toolbar_left")[0].children.length; i++) {
            document.getElementsByClassName("toolbar_left")[0].children[i].className = "";
        }
        document.getElementsByClassName("toolbar_left")[0].children[j].className = "toolbarColor";
    }


    $(".nav_auto li").hover(function () {
        $(this).children(".nav_list").addClass("show");
    }, function () {
        $(this).children(".nav_list").removeClass("show");
    });
</script>