package com.factory;
import com.dao.*;
import com.daoImpl.*;
public class fruitFactory {
    public static fruitDao getFruitDaoInstance(){
        return new fruitDaoImpl();
    }
}
