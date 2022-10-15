<%--
  Created by IntelliJ IDEA.
  User: levono1
  Date: 2021/1/13
  Time: 19:15
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
    <title>AddFruit</title>
    <script src="../jquery/jquery-1.7.2.js"></script><!--导入jquery-->
    <script>
      function  add(){
          var featureList=document.getElementsByClassName("delbox");
          var cheap="";var beautiful="";var big="";var delicious="";
          for(var i=0;i<featureList.length;i++){
              if(featureList.item(i).checked==true){
                  if(i==0)
                      cheap='购物';
                  if(i==1)
                      delicious='博物馆';
                  if(i==2)
                      beautiful='历史古迹';
                  if(i==3)
                      big='自然风光';
              }
          }
          $.ajax({
              url:"http://localhost:8080/addFruit",
              dataType:"text",
              type:"post",
              data:{
                  name:$("[name='name']").val(),
                  place:$("[name='place']").val(),
                  price:$("[name='price']").val(),
                  weight:$("[name='weight']").val(),
                  releaseDate:$("[name='releaseDate']").val(),
                  jurisdiction:$("[name='jurisdiction']:checked").val(),
                  merchantID: ${param.merchantID},
                  cheap:cheap,
                  beautiful:beautiful,
                  big:big,
                  delicious:delicious,
              },
              success: function (res){
                 if(res){
                     alert("添加成功");
                     location.href="http://localhost:8080/showFruitMessage?merchantID="+ ${param.merchantID};
                 }
                 else{
                     alert("添加失败");
                 }
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
            <td>出发地</td>
            <td><input type="text"  name="name"></td>
        </tr>
        <tr>
            <td>目的地</td>
            <td><input type="text"  name="place"></td>
        </tr>
        <tr>
            <td>天数</td>
            <td><input type="text"  name="weight"></td>
        </tr>
        <tr>
            <td>价格</td>
            <td><input type="text"  name="price"></td>
        </tr>
        <tr>
            <td>旅游特色</td>
            <td>
                购物：<input type="checkbox" value="cheap" name="delbox" class="delbox" id="cheap">&nbsp&nbsp&nbsp
                博物馆：<input type="checkbox" value="delicious" name="delbox" class="delbox" id="delicious">&nbsp&nbsp&nbsp
                历史古迹：<input type="checkbox" value="beautiful" name="delbox" class="delbox" id="beautiful">&nbsp&nbsp&nbsp
                自然风光：<input type="checkbox" value="big" name="delbox" class="delbox" id="big">
            </td>
        </tr>
        <tr>
            <td>出发日期</td>
            <td><input type="date" name="releaseDate"></td>
        </tr>
        <tr>
            <td>查看权限</td>
                <td>
                    公开：<input type="radio" name="jurisdiction" value="1">
                    私有：<input type="radio" name="jurisdiction" value="0" checked>
                </td>
        </tr>
        <tr>
            <td colspan="2"><input type="button" value="确定添加" onclick="add()"></td>
        </tr>
    </table>
</div>
</body>
</html>