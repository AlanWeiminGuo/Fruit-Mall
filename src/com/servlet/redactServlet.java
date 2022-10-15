package com.servlet;
import com.datebaseConnection.ConnectionPool;
import com.pojo.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

public class redactServlet extends HttpServlet {
    static ConnectionPool pool=new ConnectionPool();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("success");
        HttpSession session=req.getSession();
        fruit fruit=new fruit();
        fruit.setId(Integer.parseInt(req.getParameter("fruitID")));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bigproject?characterEncoding=utf8&&serverTimezone=UTC", "root", "123456");
            Statement st=con.createStatement();
            String sql="select * from message where id='"+fruit.getId()+"'";
            ResultSet res=st.executeQuery(sql);
            while (res.next()){
                fruit.setName(res.getString("name"));
                fruit.setPlace(res.getString("place"));
                fruit.setPrice(res.getDouble("price"));
                fruit.setWeight(res.getString("weight"));
                fruit.setJurisdiction(res.getInt("jurisdiction"));
                fruit.setMerchantID(res.getInt("merchantID"));
                fruit.setReleaseDate(res.getDate("releaseDate"));
                fruit.setFeature(res.getString("feature"));
            }
            session.setAttribute("Fruit",fruit);
            res.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        int merchantID=Integer.parseInt(req.getParameter("merchantID"));
        if(merchantID==-1){
            boolean cheap=false;boolean delicious=false;boolean big=false;boolean beautiful=false;
            String feature=fruit.getFeature();

            session.setAttribute("cheap",cheap);session.setAttribute("delicious",delicious);
            session.setAttribute("big",big);session.setAttribute("beautiful",beautiful);
            resp.sendRedirect("http://localhost:8080/jsp/redactFruitMessage.jsp");
        }

        else{
            merchant merchant=new merchant();
            merchant.setId(merchantID);
            Connection con= pool.getCon();
            try {
                Statement st=con.createStatement();
                String sel="select * from merchant where id='"+merchantID+"'";
                ResultSet res=st.executeQuery(sel);
                if(res.next()){
                    merchant.setName(res.getString("name"));
                    merchant.setId(res.getInt("id"));
                }
                session.setAttribute("merchantName",merchant.getName());
                session.setAttribute("merchantID",merchant.getId());
                resp.sendRedirect("http://localhost:8080/jsp/detailMessage.jsp");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
