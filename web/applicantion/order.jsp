<%@ page import="com.qst.bean.User" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@include file="../app.jsp" %>
<html>
<head>
    <title>商品页面</title>
    <% User user = (User) request.getSession().getAttribute("SESSION_USER"); %>
    <link rel="stylesheet" href="${appPath}/css/head.css">
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
    <c:if test="${not empty requestScope.pageOrder.list}">
        <c:forEach items="${requestScope.pageOrder.list}" var="pet" varStatus="s">
            <div class="lala" style="display: inline-block;padding: 5px;margin: 5px 15px;">
                <a style="" href="${appPath}/BuyServlet?petId=${pet.petId}">
                    <div style="display: inline-block">
                        <img style="width: 240px;height: 240px;" src="${appPath}/images/${pet.img}" alt="">
                        <div style="border-top: 1px solid #e6e6e6;">
                            <p style="display: block">${pet.petName} 单价:${pet.value}</p>
                            <p class="money" style="display: inline-block;color: red;">￥${pet.value*pet.number}</p>
                            <p> 数量 ${pet.number}</p>
                            <p>已经付款</p>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </c:if>
</div>


<br><br>
<div align="center">
    <a href="javascript:beforePage('${pageOrder.beforePage}')">上一页</a>
    共${pageOrder.totalPages}页 共${pageOrder.totalRows}位数据
    第${pageOrder.currentPage}页
    每页${pageOrder.pageSize}位
    <a href="javascript:afterPage('${pageOrder.afterPage}')">下一页</a>

</div>
<br>
<br>
</body>
</html>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script>

    var xhr = false;

    function createXHR() {
        try {
            xhr = new XMLHttpRequest();
        } catch (e) {
            try {
                xhr = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e1) {
                xhr = null;
            }
        }
        if (!xhr) {
            alter("初始化XMLHttpRequest对象失败");
        }
    }

    //位置   petId
    function out(i, petId, number) {
        console.log(i, petId);
        createXHR();
        var url = "${appPath}/ShoppingCartServlet";
        var content = "petId=" + petId + "&action=change&number=" + number;
        console.log(content);
        xhr.open("post", url, false);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById("emailValidate").innerHTML = xhr.responseText;
                console.log("aaa");
            }
        };
        /*xhr.setRequestHeader("Content-length", content.length);*/
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.send(content);
    }

    var ins = document.getElementsByClassName("ins");
    var del = document.getElementsByClassName("del");

    //数量   位置  价值
    function inss(index, val, petId) {
        document.getElementsByClassName("in")[index].value++;
        document.getElementsByClassName("money")[index].innerHTML = "￥" + val * document.getElementsByClassName("in")[index].value;
        out(index, petId, document.getElementsByClassName("in")[index].value);
    }

    function dell(index, val, petId) {
        if (document.getElementsByClassName("in")[index].value <= 1) {
        } else {
            document.getElementsByClassName("in")[index].value--;
            document.getElementsByClassName("money")[index].innerHTML = "￥" + val * document.getElementsByClassName("in")[index].value;
            out(index, petId, document.getElementsByClassName("in")[index].value);
        }
    }

    function beforePage(page) {
        window.location = "${appPath}/ThingServlet?currentPage=" + page;
    }

    function afterPage(page) {
        window.location = "${appPath}/ThingServlet?currentPage=" + page;
    }
</script>

