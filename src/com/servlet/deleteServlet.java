package com.servlet;
import com.dao.fruitDao;
import com.daoImpl.fruitDaoImpl;
import com.factory.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.datebaseConnection.*;
public class deleteServlet extends HttpServlet {
    static ConnectionPool pool=new ConnectionPool();
    @Override
   public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String []del=req.getParameterValues("delList");
     int []delList=new int[del.length];
     for(int i=0;i<del.length;i++){
         String id=del[i];
         delList[i]=Integer.parseInt(id);
         System.out.println(delList[i]);
     }
     int result=0;
     fruitDao deleteFruit=fruitFactory.getFruitDaoInstance();
     for(int i=0;i<delList.length;i++){
         try {
             deleteFruit.deleteFruit(delList[i]);
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
     }

        resp.getWriter().print(true);

    }
}
