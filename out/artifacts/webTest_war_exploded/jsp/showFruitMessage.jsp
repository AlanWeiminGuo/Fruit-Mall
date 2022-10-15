<%--
  Created by IntelliJ IDEA.
  User: levono1
  Date: 2021/1/10
  Time: 15:49
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

      function Add(id){
          $.ajax({
              url:"http://localhost:8080/addFruit",
              data:{
                merchantID:id,
              },
              type: "post",
              dataType: "text",
              success:function (res){
                location.href="http://localhost:8080/jsp/addFruit.jsp?merchantID="+res;
              }
          })
      }

      window.onload=function (){
          let delBoxList =document.getElementsByClassName("delbox");
          for(let i=0;i<delBoxList.length;i++){
              if(localStorage.getItem(delBoxList.item(i).id))
                  delBoxList.item(i).checked=true;
          }
      }

      function Delete(){
         var delList=new Array();
         for(var i=0;i<localStorage.length;i++){
             var getKey = localStorage.key(i);
             var getVal = localStorage.getItem(getKey);
             delList.push(getVal);//把被选中待删除的id传入delList中
         }
          $.ajax({
              url:"http://localhost:8080/deleteFruit",
              data:{
                  delList:delList,
              },
              type: "post",
              dataType: "text",
              traditional: true,
              success:function (res){
                  if(res){
                      for(var j=0;j<delList.length;j++)
                          localStorage.removeItem(delList[j]);
                      alert("删除成功");
                      location.href="http://localhost:8080/showFruitMessage?merchantID="+${merchant.id};
                  }
                  else{
                      alert("删除失败");
                  }
              }
          })
      }

      function checkthis(e){
         let obj=document.getElementById(e);
         if(obj.checked==false){
             localStorage.removeItem(e);
         }

         else {
             localStorage.setItem(e,e);
         }
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

      function search(){
          var data=document.getElementById("searchFruit").value;
          $.ajax({
              url:"http://localhost:8080/searchFruit",
              dataType:"text",
              type:"post",
              data:{
                  startDate:$("[name='startDate']").val(),
                  endDate:$("[name='endDate']").val(),
                  input:data,
                  merchantID:${merchant.id},
              },
              success:function (res){
                      location.href="http://localhost:8080/jsp/showSearchResult.jsp";
              }
          })

      }

      var  i=0;
      $(function(){
              $("#checkAll").click(
                  function (){
                      $("input[name='delbox']").prop("checked","checked");
                      let delBoxList =document.getElementsByClassName("delbox");
                      for(let i=0;i<delBoxList.length;i++){
                          if(delBoxList.item(i).checked==true)
                              localStorage.setItem(delBoxList.item(i).id,delBoxList.item(i).id);
                      }
                      if(i==1){
                          $("input[name='delbox']").prop("checked",false);
                          localStorage.clear();//全不选，清除localStorage里的全部数据
                      }
                      i++;
                      if(i>1){
                          i=0;
                      }
                  }
              );
          }
      );
    </script>
    <style>
        .delbox:checked {
            background:#cccccc;
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
            <td colspan="2">账户名: ${merchant.name}</td>
            <td colspan="2">地址: ${merchant.address}</td>
            <td colspan="2">联系电话： ${merchant.phone}</td>
            <td colspan="1"><input type="button" value='查看天气'onclick="location.href='getWeather/getWeather.html'" ></td>
        </tr>
        <tr>
            <td><input type="button" value="添加" name="addFruit" onclick="Add(${merchant.id})"></td>
            <td>
                <input type="button" value="删除" name="deleteFruit" onclick="Delete()">

                <button id="checkAll" class="checkbox">全选</button>
            </td>
            <td colspan="5">
                <input type="date" name="startDate">
                <input type="date" name="endDate">
                <input type="text" name="searchFruit" value="inputData" id="searchFruit">
                <input type="button" value="搜索" onclick="search()">
            </td>
        </tr>
        <tr>
            <td>编号</td>
            <td>出发地</td>
            <td>目的地</td>
            <td>天数</td>
            <td>价格</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${perFruitList}" var="Fruit">
            <tr>
                <td>${Fruit.id}</td>
                <td>
                        ${Fruit.name}
                    <c:if test="${Fruit.merchantID==merchant.id}">
                        <input type="checkbox" name="delbox" id="${Fruit.id}" class="delbox" value="${Fruit.id}" onclick="checkthis(${Fruit.id})">
                    </c:if>
                </td>
                <td>${Fruit.place}</td>
                <td>${Fruit.weight}</td>
                <td>${Fruit.price}</td>
                <c:if test="${Fruit.merchantID==merchant.id}">
                    <td><input type="button" value="编辑" onclick="redact(${Fruit.id})"></td>
                    <td><input type="button" value="查看" onclick="showDetail(${Fruit.id})"></td>
                </c:if>
                <c:if test="${Fruit.merchantID!=merchant.id}">
                    <td colspan="1">无操作权限</td>
                    <td><input type="button" value="查看" onclick="showDetail(${Fruit.id})"></td>
                </c:if>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="7" >
                ${TotalCount}条记录&nbsp&nbsp&nbsp
                共${TotalPage}页&nbsp&nbsp&nbsp
                当前第${pageCur}页&nbsp&nbsp&nbsp
                <c:url var="url_pre" value="http://localhost:8080/showFruitMessage">
                    <c:param name="pageCur" value="${pageCur-1 }"/>
                    <c:param name="merchantID" value="${merchant.id}"/>
                </c:url>
                <c:url var="url_next" value="http://localhost:8080/showFruitMessage">
                    <c:param name="pageCur" value="${pageCur+1 }"/>
                    <c:param name="merchantID" value="${merchant.id}"/>
                </c:url>

                <c:if test="${pageCur!=1 }">
                    <a href="${url_pre }">上一页</a>
                </c:if>
                <c:if test="${pageCur!=TotalPage&&TotalPage!=0 }">
                    <a href="${url_next }">下一页</a>
                </c:if>
            </td>
        </tr>
    </table>
</div>

</body>
</html>