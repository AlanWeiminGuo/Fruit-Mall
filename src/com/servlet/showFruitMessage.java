package com.servlet;
import com.datebaseConnection.ConnectionPool;
import com.pojo.fruit;
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
import java.util.ArrayList;

public class showFruitMessage extends HttpServlet {
    static ConnectionPool pool=new ConnectionPool();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session=req.getSession();
        int merchantID=Integer.parseInt(req.getParameter("merchantID")) ;
        ArrayList<fruit> FruitList=new ArrayList<>();
        merchant merchant=new merchant();
        merchant.setId(merchantID);
        Connection con1=pool.getCon();//取出该商家的水果
        try {
            Statement st1=con1.createStatement();
            String select="select * from Message where merchantID='"+merchantID+"'";
            ResultSet res1=st1.executeQuery(select);

            while (res1.next()){
                fruit fruit=new fruit();
                fruit.setId(Integer.parseInt(res1.getString("id")));
                fruit.setName(res1.getString("name"));
                fruit.setPlace(res1.getString("place"));
                fruit.setPrice(Double.parseDouble(res1.getString("price")));
                fruit.setWeight(res1.getString("weight"));
                fruit.setJurisdiction(res1.getInt("jurisdiction"));
                fruit.setMerchantID(res1.getInt("merchantID"));
                FruitList.add(fruit);
            }
            res1.close();
            st1.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        pool.releaseCon(con1);

        Connection con2= pool.getCon();//取出该商家的信息
        try {
            Statement st=con2.createStatement();
            String select="select * from merchant where id='"+merchantID+"'";
            ResultSet res=st.executeQuery(select);
            while (res.next()){
                merchant.setName(res.getString("name"));
                merchant.setAddress(res.getString("address"));
                merchant.setPhone(res.getString("phone"));
            }
            res.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        pool.releaseCon(con2);

        Connection con3= pool.getCon();//取出信息状态公开的水果
        try {
            Statement st=con2.createStatement();
            String select="select * from Message where jurisdiction=1 and merchantID!='"+merchantID+"'";
            ResultSet res=st.executeQuery(select);
            while (res.next()){
                fruit fruit=new fruit();
                fruit.setId(Integer.parseInt(res.getString("id")));
                fruit.setName(res.getString("name"));
                fruit.setPlace(res.getString("place"));
                fruit.setPrice(Double.parseDouble(res.getString("price")));
                fruit.setWeight(res.getString("weight"));
                fruit.setMerchantID(res.getInt("merchantID"));
                fruit.setJurisdiction(res.getInt("jurisdiction"));
                FruitList.add(fruit);
            }
            res.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //分页显示
        int TotalCount=FruitList.size();
        //定义TotalPage统计页数，一页四条记录
        int TotalPage=(int)Math.ceil((double) TotalCount/4);
        //获取当前页面的页面号
        String pageCurll=req.getParameter("pageCur");
        if(pageCurll==null) {
            pageCurll="1";//默认起始页码为1
        }
        int pageCur=Integer.parseInt(pageCurll);
        if((pageCur-1)*4>TotalCount) {
            pageCur=pageCur-1;
        }
        int startIndex=(pageCur-1)*4;
        int perPageSize=4;//每页4条记录
        int endIndex=perPageSize+startIndex;
        if(endIndex>FruitList.size())
            endIndex=FruitList.size();
        ArrayList<fruit> perFruitList=new ArrayList<>();
        for(int t=startIndex;t<endIndex;t++){
            perFruitList.add(FruitList.get(t));
        }
        int []FruitID=new int[FruitList.size()];
        for(int i=0;i<FruitList.size();i++){
            FruitID[i]=FruitList.get(i).getId();
        }
        session.setAttribute("FruitID",FruitID);
        session.setAttribute("TotalCount", TotalCount);
        session.setAttribute("TotalPage", TotalPage);
        session.setAttribute("pageCur", pageCur);
        session.setAttribute("perFruitList", perFruitList);


        //session.setAttribute("FruitList",FruitList);
        session.setAttribute("merchant",merchant);
        resp.sendRedirect("http://localhost:8080/jsp/showFruitMessage.jsp");
    }
}
