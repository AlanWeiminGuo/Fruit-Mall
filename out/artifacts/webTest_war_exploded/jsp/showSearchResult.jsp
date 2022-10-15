<%--
  Created by IntelliJ IDEA.
  User: levono1
  Date: 2021/1/16
  Time: 11:17
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
    <title>Fruit Message</title>
    <script src="reference/js/jquery-3.2.0.min.js"></script>
    <script src="../jquery/jquery-1.7.2.js"></script><!--导入jquery-->
    <script>
        function redact(id){
            $.ajax({
                url:"http://localhost:8080/redactFruit",
                type:"post",
                dataType:"text",
                data:{
                    fruitID:id,
                    merchantID:-1,
                },
                success:function (){
                    location.href="http://localhost:8080/jsp/redactFruitMessage.jsp";
                    console.log("ajax回调成功")
                },
                error:function (){
                    console.log("ajax回调失败")
                }
            })
        }

        function showDetail(id){
            $.ajax({
                url:"http://localhost:8080/redactFruit",
                data:{
                    merchantID:${merchant.id},
                    fruitID:id,
                },
                type: "post",
                dataType: "text",
                success:function (){
                    location.href="http://localhost:8080/jsp/detailMessage.jsp";
                }
            })
        }

    </script>
    <style>
        .delbox:checked {
            background:#1673ff
        }
        .delbox {
            width:16px;
            height:16px;
            background-color:#ffffff;
            border:solid 1px #dddddd;
            -webkit-border-radius:50%;
            border-radius:50%;
            font-size:0.8rem;
            margin:0;
            padding:0;
            position:relative;
            display:inline-block;
            vertical-align:middle;
            cursor:default;
            -webkit-appearance:none;
            -webkit-user-select:none;
            user-select:none;
            -webkit-transition:background-color ease 0.1s;
            transition:background-color ease 0.1s;
            margin-left: 15px;
        }
        .delbox:checked::after {
            content:'';
            top:3.7px;
            left:3.7px;
            position:absolute;
            background:transparent;
            border:#fff solid 2px;
            border-top:none;
            border-right:none;
            height:3px;
            width:5px;
            -moz-transform:rotate(-45deg);
            -ms-transform:rotate(-45deg);
            -webkit-transform:rotate(-45deg);
            transform:rotate(-45deg);
        }
        .checkbox{
            border: solid #ccc 1px;
            -webkit-border-radius: 6px;
            border-radius: 6px;
            -webkit-box-shadow: 0 1px 1px #ccc;
            box-shadow: 0 1px 1px #ccc;
            background: #B0CC7F;
            margin: 0 2px 0;
        }
    </style>
</head>
<link rel="stylesheet" href="<%=path%>/css/css.css">
<body>
<div>
    <table>
        <tr>
            <td>编号</td>
            <td>出发地</td>
            <td>目的地</td>
            <td>天数</td>
            <td>价格</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${FruitList}" var="Fruit">
            <tr>
                <td>${Fruit.id}</td>
                <td>
                        ${Fruit.name}
                </td>
                <td>${Fruit.place}</td>
                <td>${Fruit.weight}</td>
                <td>${Fruit.price}</td>
                <c:if test="${Fruit.merchantID==merchantID}">
                    <td><input type="button" value="编辑" onclick="redact(${Fruit.id})"></td>
                    <td><input type="button" value="查看" onclick="showDetail(${Fruit.id})"></td>
                </c:if>
                <c:if test="${Fruit.merchantID!=merchantID}">
                    <td colspan="2">无操作权限</td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>