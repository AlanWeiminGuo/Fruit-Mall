package com.dao;
import com.pojo.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface fruitDao {
    public boolean deleteFruit(int id) throws SQLException;//根据水果id删除水果
    public ArrayList<fruit> searchFruit(String input,int merchantID) throws SQLException;
    public ArrayList<fruit> searchPublicFruit(String input,int merchantID) throws SQLException;
    public ArrayList<fruit> searchByDate(String startDate,String endDate,int merchantID) throws SQLException;
}
