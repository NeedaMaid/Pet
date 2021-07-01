<%@ page import="com.qst.bean.User" %>
<%--
  Created by IntelliJ IDEA.
  User: 古添添
  Date: 2021/1/2
  Time: 7:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@include file="../app.jsp" %>
<html>
<head>
<<<<<<< HEAD

=======
>>>>>>> e51e93c0e320e603ba49992929ce361421bc77d9
    <title>商品页面</title>
    <% User user = (User) request.getSession().getAttribute("SESSION_USER"); %>
    <link rel="stylesheet" href="css/head.css">
    <style>
        p {
            margin: 5px auto;
        }

        .lala {
            border: 1px solid #e6e6e6;
        }

        .lala:hover {
            border-color: #ff6655;
        }

    </style>

</head>
<body style="font-size: 14px;min-width:1150px;">

<%@include file="head.jsp" %>


<div style="margin: 10px auto;align:center;width:1150px;padding-top:30px;border-top: 2px solid #cccccc;">
    <c:if test="${not empty requestScope.pageBean.list}">

        <c:forEach items="${requestScope.pageBean.list}" var="pet">

            <div class="lala" style="display: inline-block;padding: 5px;margin: 5px 15px;">
                <a style="display: inline-block" href="${appPath}/BuyServlet?action=show&petId=${pet.petId}">
                    <div style="display: inline-block">
                        <img style="width: 240px;height: 240px;" src="${appPath}/images/${pet.img}" alt="">
                        <div style="border-top: 1px solid #e6e6e6;">
                            <p style="display: block">${pet.petName}</p>
                            <p style="display: inline-block;color: red;">￥${pet.value}</p></p>
                            <p style="display: inline-block">剩余数量${pet.number}</p>
                            <c:if test="${pet.number==0}"><p style="display: inline-block">已售罄</p></c:if>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </c:if>
</div>


<br><br>
<div align="center">
    <a href="javascript:beforePage('${pageBean.beforePage}')">上一页</a>
    共${pageBean.totalPages}页 共${pageBean.totalRows}位数据
    第${pageBean.currentPage}页
    每页${pageBean.pageSize}位
    <a href="javascript:afterPage('${pageBean.afterPage}')">下一页</a>

</div>
<br>
<br>
</body>
</html>
<script>
    function beforePage(page) {
        window.location = "${appPath}/ThingServlet?currentPage=" + page + "&name=${name}";
    }

    function afterPage(page) {
        window.location = "${appPath}/ThingServlet?currentPage=" + page + "&name=${name}";
    }
</script>

