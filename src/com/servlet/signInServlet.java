package com.servlet;
import com.datebaseConnection.ConnectionPool;
import com.pojo.merchant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class signInServlet extends HttpServlet {
    static ConnectionPool pool=new ConnectionPool();
    //使用连接池

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        merchant merchant=new merchant();
        int status=-1;
        String verifyPassword=req.getParameter("verifyPassword");
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        String phone=req.getParameter("phone");
        String address=req.getParameter("address");
        if(name==""||verifyPassword==""||password==""||phone==""||address==""){
            status=1;//填写信息不完全
            resp.getWriter().print(status);
            System.out.println("注册信息不完全"+status);
        }
        else{
            if(!verifyPassword.equals(password)){
                status=2;//两次密码不一致
                resp.getWriter().print(status);
                System.out.println("两次密码不一致"+status);
            }
            else{
                Connection con5=pool.getCon();
                int isSign=0;//用来判断是否已被注册过
                try {
                    Statement st5= con5.createStatement();
                    String select="select * from merchant where name='"+name+"'";
                     ResultSet ress=st5.executeQuery(select);
                     if(ress.next())
                         isSign=1;//说明有重复的
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                if(isSign==1){
                    status=3;
                    resp.getWriter().print(status);
                }
                else{
                    status=0;
                    resp.getWriter().print(status);
                    System.out.println("注册成功");
                    try {
                        //加载数据库驱动
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        //连接数据库
                        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bigproject?characterEncoding=utf8&&serverTimezone=UTC", "root", "123456");
                        Statement st=con.createStatement();
                        String signIn="insert into merchant(name,password,phone,address)values('"+name+"','"+password+"','"+phone+"','"+address+"')";
                        int res=st.executeUpdate(signIn);
                        st.close();
                        con.close();
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
