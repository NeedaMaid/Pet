<%@ page import="com.qst.bean.User" %>
<%@ page import="com.qst.bean.Pet" %><%--
  Created by IntelliJ IDEA.
  User: 古添添
  Date: 2021/1/2
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@include file="../app.jsp" %>
<% User user = (User) request.getSession().getAttribute("SESSION_USER"); %>
<html>
<head>
    <title>购买</title>
    <style>
        p {
            margin: 5px auto;
        }

        a {
            text-decoration: none; /*去掉下划线*/
            list-style: none;
        }

        #del, #ins {
            font-size: 18px;
            position: absolute;
            border: 1px solid #e7e7e7;
            background: #f7f7f7;
            width: 30px;
            height: 36px;
            line-height: 38px;
            text-align: center;
            display: inline-block;
            color: #000;
        }

        #del.disable, #ins.disable {
            color: #e0e0e0;
            cursor: default;
        }

        input {
            font-size: 18px;
            position: relative;
            left: 30px;
            width: 60px;
            height: 38px;
            padding: 0;
            line-height: 40px;
            text-align: center;
            color: #595959;
            border: 1px solid #dedede;
            outline: none;
        }

        .a {
            margin-top: 10px;
        }
    </style>

</head>
<body style="font-size:14px;min-width: 1000px;align:center;">
<%@include file="head.jsp" %>

<div style="width: 600px;margin:40px auto;padding: 5px;border: 1px solid #e6e6e6;align:center;">
    <div style="display: inline-block">
        <div style="float:left;display: inline-block">
            <img style="width: 300px;height: 300px;" src="${appPath}/images/${choicePet.img}" alt="">
        </div>
        <div style="position: relative;width: 200px; padding: 5px 10px; margin-left: 50px; float:left;align:top;display:inline-block;border-left: 1px solid #e6e6e6;">
            <p style="display: block">${choicePet.petName}</p>
            <p style="display: inline-block;color: red;">￥${choicePet.value}</p></p>
            <p id="buy" class="left" style="display: block">剩余数量${choicePet.number}</p>
            <br>
            <a onclick="dell()" class="disable " id="del" href="javascript:;">-</a>
            <input id="in" type="text" value="1">
            <c:if test="${choicePet.number==1}">
                <a onclick="inss() " class="disable" id="ins" style="left: 95px" href="javascript:;">+</a>
            </c:if>
            <c:if test="${choicePet.number>1}">
                <a onclick="inss() " class="" id="ins" style="left: 95px" href="javascript:;">+</a>
            </c:if>
            <div class="a">
                <a onmouseover="buyq()" class="buy" href="javascript:;">立即购买</a>
                <a onclick="ajaxValidate()" class="buy"
                   href="javascript:;">加入购物车</a>
                <label style="color: red;display: block" id="emailValidate"></label>
            </div>
        </div>
    </div>
</div>


</body>
</html>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script type="text/javascript">
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

    function ajaxValidate() {

        var value = document.getElementById("in");
        value = value.value;
        createXHR();
        var url = "${appPath}/ShoppingCartServlet";
        var content = "petId=${choicePet.petId}&action=add&number=" + value;
        xhr.open("post", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById("emailValidate").innerHTML = xhr.responseText;
            }
        };
        /*xhr.setRequestHeader("Content-length", content.length);*/
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.send(content);
    }
<<<<<<< HEAD
function a() {
    $.ajax()

}
=======

>>>>>>> e51e93c0e320e603ba49992929ce361421bc77d9
    function buyq() {
        <% if(request.getSession().getAttribute("SESSION_USER")==null){

        }%>
        var a = document.getElementsByClassName("buy")[0];
        var value = document.getElementById("in");
        value = value.value;
        a.href = "${appPath}/BuyServlet?petId=${choicePet.petId}&action=buy&number=" + value;
    }

    var ins = document.getElementById("ins");
    var del = document.getElementById("del");

    function inss() {
        if (document.getElementById("in").value >=${choicePet.number}) {
            ins.className = "disable";
        } else {
            ins.className = " ";
            document.getElementById("in").value++;
            if (document.getElementById("in").value >=${choicePet.number}) {
                ins.className = "disable";
            }
            del.className = " ";
        }
    }

    function dell() {

        if (document.getElementById("in").value <= 1) {
            del.className = "disable";
            console.log("lallal");
        } else {
            del.className = " ";
            document.getElementById("in").value--;
            if (document.getElementById("in").value <= 1) {
                del.className = "disable";
            }
            ins.className = " ";
        }
    }


</script>