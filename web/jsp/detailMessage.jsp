<%--
  Created by IntelliJ IDEA.
  User: levono1
  Date: 2021/1/15
  Time: 19:40
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
    <title>redactFruitMessage</title>
    <script src="../jquery/jquery-1.7.2.js"></script><!--导入jquery-->
    <script>
        function returnMain(){
            location.href="http://localhost:8080/showFruitMessage?merchantID="+${merchantID};
        }
    </script>

</head>
<link rel="stylesheet" href="<%=path%>/css/css.css">
<body>
<div>
    <table>
        <tr>
            <td>编号</td>
            <td><span>${Fruit.id}</span></td>
        </tr>
        <tr>
            <td>出发地</td>
            <td><span>${Fruit.name}</span></td>
        </tr>
        <tr>
            <td>目的地</td>
            <td><span>${Fruit.place}</span></td>
        </tr>
        <tr>
            <td>天数</td>
            <td><span>${Fruit.weight}</span></td>
        </tr>
        <tr>
            <td>价格</td>
            <td><span>${Fruit.price}</span></td>
        </tr>
        <tr>
            <td>查看权限</td>
            <c:if test="${Fruit.jurisdiction==0}">
                <td><span>仅自己可见</span></td>
            </c:if>
            <c:if test="${Fruit.jurisdiction==1}">
                <td><span>对所有人开放</span></td>
            </c:if>
        </tr>
        <tr>
            <td>出发日期</td>
            <td><span>${Fruit.releaseDate}</span></td>
        </tr>
        <tr>
            <td>旅游特色</td>
            <td><span>${Fruit.feature}</span></td>
        </tr>
        <tr>
            <td>开线旅社</td>
            <td><span>${Fruit.merchantID}</span></td>
        </tr>
        <tr>
            <td colspan="2"><input type="button" value="返回主页" onclick="returnMain()"></td>
        </tr>
    </table>
</div>
</body>
</html>