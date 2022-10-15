package com.servlet;
import com.pojo.fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class modifyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        System.out.println("数据提交到了modifyServlet");

        String feature="";
        String cheap=req.getParameter("cheap");
        String big=req.getParameter("big");
        String beautiful=req.getParameter("beautiful");
        String delicious=req.getParameter("delicious");
        if(cheap!=null&&cheap!="")
            feature=feature+cheap+"   ";
        if(big!=null&&big!="")
            feature=feature+big+"   ";
        if(delicious!=null&&delicious!="")
            feature=feature+delicious+"   ";
        if(beautiful!=null&&beautiful!="")
            feature=feature+beautiful;

        fruit fruit=new fruit();
        fruit.setFeature(feature);
        System.out.println(fruit.getFeature());
        fruit.setId(Integer.parseInt(req.getParameter("id")));
        fruit.setName(req.getParameter("name"));
        fruit.setWeight(req.getParameter("weight"));
        fruit.setPlace(req.getParameter("place"));
        fruit.setPrice(Double.parseDouble(req.getParameter("price")));
        fruit.setJurisdiction(Integer.parseInt(req.getParameter("jurisdiction")));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接数据库
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bigproject?characterEncoding=utf8&&serverTimezone=UTC", "root", "123456");
            Statement st=con.createStatement();
            String update="update message set name='"+fruit.getName()+"',price='"+fruit.getPrice()+"',weight='"+fruit.getWeight()+"'," +
                    "place='"+fruit.getPlace()+"',jurisdiction='"+fruit.getJurisdiction()+"',feature='"+fruit.getFeature()+"' where id='"+fruit.getId()+"'";
            int r=st.executeUpdate(update);
            if(r==0){
                resp.getWriter().print(r);
                System.out.println("修改失败");
            }
            else{
                resp.getWriter().print(r);
                System.out.println("修改成功");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }
}
