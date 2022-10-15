package com.datebaseConnection;
import com.pojo.fruit;
import com.pojo.merchant;

import java.util.ArrayList;
import java.sql.*;
public class ConnectionPool {
    ArrayList<Connection> list=new ArrayList<Connection>();
    public ConnectionPool(){
        //建立连接驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动连接失败");
        }
        //与数据库连接
        for(int i=0;i<20;i++) {
            try {
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bigproject?characterEncoding=utf8&serverTimezone=UTC", "root", "123456");
                list.add(con);

            }catch(SQLException e) {
                e.printStackTrace();
                System.out.print("数据库连接失败");
            }
        }
    }
    //获取一个数据库连接
    public synchronized Connection getCon() {
        if(list.size()>0) {
            return list.remove(0);//如果连接池不为空，返回第一个连接对象，并删除
        }
        else {
            return null;
        }
    }
    //释放连接
    public synchronized void releaseCon(Connection con) {
        list.add(con);
    }
}
