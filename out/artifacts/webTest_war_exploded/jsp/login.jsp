
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();  //获取绝对路径地址
%>
<html>
<head>
    <title>userLogin</title>
    <script src="../jquery/jquery-1.7.2.js"></script><!--导入jquery-->
    <script>

        function login(){
            $.ajax({
                url:"http://localhost:8080/loginServlet",
                type:"post",
                data:{
                    userName:$("[name = 'username']").val(),
                    password:$("[name = 'password']").val()
                },

                success:function (status){
                  if(status>0){
                      alert("登入成功");//显示登陆成功
                     location.href="http://localhost:8080/showFruitMessage?merchantID="+status;
                  }
                  else if(status==-1){
                      alert("账号不存在");
                  }
                  else if(status==-2){
                      alert("密码错误")
                  }
                  else if(status==-3){
                      alert("请输入密码")
                  }
                  else{
                      alert(status);
                  }
                },
                dataType:"text",

            });
        }

      function test(){
          var userName=document.getElementById("userName").value;
          var password=document.getElementById("password").value;

          alert(userName);
          alert(password);
      }
    </script>
</head>
<link rel="stylesheet" href="<%=path%>/css/css.css">
<body>
<table>
       <tr>
           <td>用户名：</td>
           <td><input type="text" name="username" id="userName" value="${param.id}"></td>
       </tr>

       <tr>
           <td>密码：</td>
           <td><input type="password" name="password" id="password"></td>
       </tr>

       <tr>
           <td colspan="10" align="center">
               <input type="button" value="登录" onclick="login()">
           </td>

       </tr>
       </table>

</body>
</html>
