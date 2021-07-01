<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@include file="../app.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <%
        String preUrl = request.getHeader("Referer");
        request.getSession().setAttribute("preUrl", preUrl);
        System.out.println(preUrl);
    %>
    <link rel="stylesheet" href="../css/signIn.css">
</head>
<body>
<form action="${appPath}/RegisterServlet" method="post">
    <p>欢迎注册</p>

    <label>
        <input class="input email" name="email" type="text" onblur="ajaxValidate()" placeholder="请输入邮箱号">
    </label>
    <label style="color: red;" id="emailValidate"></label>
    <input class="input password" name="password" type="password" placeholder="请输入密码">
    <input class="input submit" type="submit" value="注册">
    <p>已有账户？<a href="${appPath}/applicantion/signIn.jsp">点此登录</a></p>
</form>

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
        var emailObj = document.getElementsByClassName("email")[0];
        emailObj = emailObj.value;
        createXHR();
        var pattern = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        var url = "${appPath}/RegisterServlet";
        var content = "type=" + pattern.test(emailObj) + "&email=" + emailObj;
        console.log(content);
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById("emailValidate").innerHTML = xhr.responseText;
            }
        };
        /*xhr.setRequestHeader("Content-length", content.length);*/
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.send(content);
    }

</script>