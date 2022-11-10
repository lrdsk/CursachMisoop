package Models.Shop;

import Database.MyDateBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShopService { //класс для взаимодействия с шопом, он берет все, что содержит шоп и работает с его переменными
    private MyDateBase dateBase = null;  //база данных для подключения к ней
    private Shop shop = null; //класс шоп из которого мы будем брать переменные
    public ShopService(MyDateBase dateBase,Shop shop){
        this.dateBase = dateBase;
        this.shop = shop;
    }
    public void getInfoAboutShop() throws SQLException { //берет всю информацию о магазине из таблицы в постгресе
        ResultSet result = null;
        Statement statement = null;
        String SQL = "SELECT * FROM shop";

        dateBase.connectionToBase(); //получает коннект к базе для запросов
        statement = dateBase.connection.createStatement(); //создает поток для запросов
        result = statement.executeQuery(SQL); //выполняет запрос из того, что написано в переменной sql

        while(result.next()){ //проходится по результату как по строчкам
           shop.setNameOfShop(result.getString("shop_name"));
           shop.setAddress(result.getString("address"));
           shop.setSpecializationOfShop(result.getString("specialization"));
           shop.setNameOfDirector(result.getString("shop_namedirector"));
        }
        dateBase.connection.close();
    }
}
