package com.daoImpl;
import com.dao.fruitDao;
import com.datebaseConnection.ConnectionPool;
import com.pojo.fruit;

import java.sql.*;
import java.util.ArrayList;

public class fruitDaoImpl implements fruitDao{
    static ConnectionPool pool=new ConnectionPool();
    @Override
    public boolean deleteFruit(int id) throws SQLException {
        Connection con=pool.getCon();
        String delete="delete from message where id='"+id+"'";
        Statement st=con.createStatement();
        int res=st.executeUpdate(delete);
        st.close();
        pool.releaseCon(con);
        if(res!=0)
            return true;
        else
            return  false;
    }

    @Override
    public ArrayList<fruit> searchFruit(String input,int merchantID) throws SQLException {//场地名称和果蔬名称的模糊查询
        ArrayList<fruit> FruitList=new ArrayList<>();
        Connection con=pool.getCon();
        PreparedStatement ps=null;
        String sel="select * from message where name LIKE ? OR place LIKE ? and merchantID=?";
        ps=con.prepareStatement(sel);
        ps.setString(1,"%"+input+"%");
        ps.setString(2,"%"+input+"%");
        ps.setString(3,"%"+merchantID+"%");
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            fruit fruit=new fruit();
            fruit.setFeature(rs.getString("feature"));
            fruit.setReleaseDate(rs.getDate("releaseDate"));
            fruit.setMerchantID(rs.getInt("merchantID"));
            fruit.setPlace(rs.getString("place"));
            fruit.setWeight(rs.getString("weight"));
            fruit.setPrice(rs.getDouble("price"));
            fruit.setName(rs.getString("name"));
            fruit.setJurisdiction(rs.getInt("jurisdiction"));
            fruit.setId(rs.getInt("id"));
            FruitList.add(fruit);
        }
        return  FruitList;
    }

    @Override
    public ArrayList<fruit> searchPublicFruit(String input,int merchantID) throws SQLException {
        ArrayList<fruit> FruitList=new ArrayList<>();
        Connection con=pool.getCon();
        PreparedStatement ps=null;
        String sel="SELECT * from message where (name LIKE ? OR place LIKE ?)  AND merchantID!=? AND jurisdiction=1";
        ps=con.prepareStatement(sel);
        ps.setString(1,"%"+input+"%");
        ps.setString(2,"%"+input+"%");
        ps.setString(3,"%"+merchantID+"%");
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            fruit fruit=new fruit();
            fruit.setFeature(rs.getString("feature"));
            fruit.setReleaseDate(rs.getDate("releaseDate"));
            fruit.setMerchantID(rs.getInt("merchantID"));
            fruit.setPlace(rs.getString("place"));
            fruit.setWeight(rs.getString("weight"));
            fruit.setPrice(rs.getDouble("price"));
            fruit.setName(rs.getString("name"));
            fruit.setJurisdiction(rs.getInt("jurisdiction"));
            fruit.setId(rs.getInt("id"));
            FruitList.add(fruit);
        }
        return  FruitList;
    }

    @Override
    public ArrayList<fruit> searchByDate(String startDate, String endDate,int merchantID) throws SQLException {
        ArrayList<fruit> FruitList=new ArrayList<>();
        Connection con=pool.getCon();
        PreparedStatement ps=null;
        String sel="SELECT * from message where (releaseDate BETWEEN ? AND ?) AND merchantID=? OR(merchantID!=? AND jurisdiction=1)";
        ps=con.prepareStatement(sel);
        ps.setString(1,startDate);
        ps.setString(2,endDate);
        ps.setInt(3,merchantID);
        ps.setInt(4,merchantID);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            fruit fruit=new fruit();
            fruit.setFeature(rs.getString("feature"));
            fruit.setReleaseDate(rs.getDate("releaseDate"));
            fruit.setMerchantID(rs.getInt("merchantID"));
            fruit.setPlace(rs.getString("place"));
            fruit.setWeight(rs.getString("weight"));
            fruit.setPrice(rs.getDouble("price"));
            fruit.setName(rs.getString("name"));
            fruit.setJurisdiction(rs.getInt("jurisdiction"));
            fruit.setId(rs.getInt("id"));
            FruitList.add(fruit);
        }
        return  FruitList;
    }

}
