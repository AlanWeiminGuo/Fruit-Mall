package com.servlet;
import com.datebaseConnection.ConnectionPool;
import com.pojo.merchant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class loginServlet extends HttpServlet {
    static ConnectionPool pool=new ConnectionPool();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int status = -999;
        merchant merchant=new merchant();
        merchant.setName(req.getParameter("userName"));
        merchant.setPassword(req.getParameter("password"));
        Connection con=pool.getCon();
       if(merchant.getPassword()==""){
            status=-3;//没有输入密码
            resp.getWriter().print(status);
        }
       else{
           try {
               Statement st = con.createStatement();
               String sel = "select * from merchant where name='" + merchant.getName() + "'";
               ResultSet res = st.executeQuery(sel);
               if (res.next()) {
                       String password = res.getString("password");
                       if (merchant.getPassword().equals(password)) {
                           merchant.setId(res.getInt("name"));
                           status = merchant.getId();
                           HttpSession session= req.getSession();;
                           session.setAttribute("status", status);
                           System.out.println(status);
                           resp.getWriter().print(status);//账号密码正确
                       } else {
                           status = -2;//密码错误、
                           resp.getWriter().print(status);
                       }
               }
               else {
                   status = -1;//账号不存在
                   resp.getWriter().print(status);
               }
               res.close();
               st.close();
           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }
           pool.releaseCon(con);
       }
       }

}
