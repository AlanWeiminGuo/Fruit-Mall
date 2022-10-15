package com.servlet;
import com.pojo.fruit;
import com.datebaseConnection.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class addServlet extends HttpServlet {
    static ConnectionPool pool=new ConnectionPool();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int merchantID=Integer.parseInt(req.getParameter("merchantID"));
        if(req.getParameter("jurisdiction")!=null){
            HttpSession session =req.getSession();
            fruit fruit=new fruit();
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
            System.out.println(feature);
            fruit.setFeature(feature);
            fruit.setJurisdiction(Integer.parseInt(req.getParameter("jurisdiction")));
            fruit.setMerchantID(merchantID);
            fruit.setName(req.getParameter("name"));
            fruit.setPrice(Double.parseDouble(req.getParameter("price")));
            fruit.setWeight(req.getParameter("weight"));
            fruit.setPlace(req.getParameter("place"));
            String releaseDate=req.getParameter("releaseDate");
            Connection con1=pool.getCon();
            try {
                Statement st=con1.createStatement();
                String addFruit="insert into message(name,place,weight,price,merchantID,jurisdiction,releaseDate,feature)" +
                        "values('"+fruit.getName()+"','"+fruit.getPlace()+"','"+fruit.getWeight()+"','"+fruit.getPrice()+"','"+fruit.getMerchantID()+"','"+fruit.getJurisdiction()+"','"+releaseDate+"','"+fruit.getFeature()+"')";
                int res=st.executeUpdate(addFruit);
                if(res==0){
                    resp.getWriter().print("false");
                    System.out.println("添加失败");
                }
                else {
                    resp.getWriter().print("true");
                    System.out.println("添加成功");

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            //.out.println("showFruitMessage传来的"+merchantID);
            resp.getWriter().print(merchantID);
        }
    }
}
