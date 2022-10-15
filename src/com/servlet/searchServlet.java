package com.servlet;
import com.dao.fruitDao;
import com.factory.fruitFactory;
import com.pojo.fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class searchServlet extends HttpServlet {
    public boolean compareDate(String startDate,String endDate,String releaseDate){
        if(releaseDate.compareTo(startDate)>0&&endDate.compareTo(releaseDate)>0)//发布日期在时间内
            return true;
        else
            return false;
    }
    public  String getStringDate(Date releaseDate) {

          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
          String dateString = formatter.format(releaseDate);
          return dateString;
        }
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String input=req.getParameter("input");
        int merchantID=Integer.parseInt(req.getParameter("merchantID"));
        String startDate=req.getParameter("startDate");
        String endDate=req.getParameter("endDate");
        fruitDao searchFruit=fruitFactory.getFruitDaoInstance();
        ArrayList<fruit> fruitList=new ArrayList<>();
        ArrayList<fruit> publicFruit=new ArrayList<>();
        ArrayList<fruit> searchByDate=new ArrayList<>();
        System.out.println(input+" "+merchantID+"  "+startDate+"  "+ endDate);

            try {
                fruitList=searchFruit.searchFruit(input,merchantID);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                publicFruit=searchFruit.searchPublicFruit(input,merchantID);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            for(int i=0;i<publicFruit.size();i++){
                fruit fruit=new fruit();
                        fruit=publicFruit.get(i);
                        fruitList.add(fruit);
            }


        if(startDate!=null&&endDate!=null){//输入的日期不为空
            int i=endDate.compareTo(startDate);
            if(i>0){//输入起始和结束日期的大小正确
                try {
                    searchByDate=searchFruit.searchByDate(startDate,endDate,merchantID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        if(!input.equals("inputData")){//inputdata输入不为空
            if(startDate!=null&&endDate!=null){//有日期
                int z=0;
                for(int i=0;i<fruitList.size();i++){
                    if(!compareDate(startDate,endDate,getStringDate(fruitList.get(i).getReleaseDate()) )){
                        fruitList.remove(i);//不在时间区间内的移除
                        i--;
                    }else{z++;}
                    if(z==1)
                    {
                        fruitList.remove(i);//不在时间区间内的移除
                        i--;
                    }
                }
            }
        }
        else {//输入数据为null
            if(startDate!=null&&endDate!=null){//输入数据为null但是输入时间时正确的
                int i=endDate.compareTo(startDate);
                if(i>0){
                    for(int k=0; k<searchByDate.size();k++){
                        fruit fruit=new fruit();
                        fruit=searchByDate.get(k);
                        fruitList.add(fruit);
                    }
                }
            }
        }
for(int i=0;i< fruitList.size();i++)
{
    System.out.println(fruitList.get(i).getName());
}
        HttpSession session=req.getSession();
        session.setAttribute("FruitList",fruitList);
        session.setAttribute("merchantID",merchantID);
        resp.sendRedirect("http://localhost:8080/jsp/showSearchResult.jsp");
    }
}
