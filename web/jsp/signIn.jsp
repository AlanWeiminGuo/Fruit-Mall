<%--
  Created by IntelliJ IDEA.
  User: levono1
  Date: 2021/1/10
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //获取绝对路径地址
    String path = request.getContextPath();

%>
<%--导入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>sign in</title>
    <script src="../jquery/jquery-1.7.2.js"></script><!--导入jquery-->
    <script>

        function signIn(){
            $.ajax({
                url:"http://localhost:8080/signInServlet",
                type:"post",
                data:{
                    name:$("[name='userName']").val(),
                    password:$("[name='password']").val(),
                    verifyPassword:$("[name='verifyPassword']").val(),
                    phone:$("[name='phone']").val(),
                    address:$("[name='address']").val(),
                },
                dataType:"text",
                success:function (status){
                   if(status=="1")
                       alert("填写信息不完整");
                   else if(status=="2")
                        alert("两次输入密码不同");
                   else if(status=="0"){
                       alert("注册成功");
                       location.href="http://localhost:8080/jsp/login.jsp?id="+$("[name='userName']").val();
                   }
                   else if(status=="3"){
                       alert("该账号已被注册");
                   }

                },
                error:function (){
                    alert("数据接受失败")
                }

            })
        }

    </script>
</head>
<link rel="stylesheet" href="<%=path%>/css/css.css">
<body>
<table>
<tr>
    <td>用户名：</td>
    <td><input type="text" name="userName" id="userName"></td>
</tr>

<tr>
    <td>密码：</td>
    <td><input type="password" name="password" id="password"></td>
</tr>
<tr>
    <td>确认密码：</td>
    <td><input type="password" name="verifyPassword" id="verifyPassword"></td>
</tr>
<tr>
    <td>联系电话：</td>
    <td><input type="text" name="phone" id="phone"></td>
</tr>
<tr>
    <td>联系地址：</td>
    <td><input type="text" name="address" id="address"></td>
</tr>
    <tr>
        <td colspan="2">
            <input type="button" onclick="signIn()" value="注册">
        </td>
    </tr>
</table>
</body>
</html>