<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@include file="../app.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%
        if (request.getSession().getAttribute("preUrl") == null) {
            String preUrl = request.getHeader("Referer");
            request.getSession().setAttribute("preUrl", preUrl);
            System.out.println(preUrl);
        }
    %>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="../css/signIn.css">
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
            var url = "${appPath}/LoginServlet";
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

</head>
<body>
<form action="${appPath}/LoginServlet" method="post">
    <p>欢迎登录</p>

    <input class="input email" name="email" type="text" onblur="ajaxValidate()" placeholder="请输入邮箱号">
    <label style="color: red;" id="emailValidate"></label>
    <input class="input password" name="password" type="password" placeholder="请输入密码">
    <input class="input submit" type="submit" value="登录">
    <p>没有账户？<a href="register.jsp">点此注册</a></p>
</form>

</body>
</html>