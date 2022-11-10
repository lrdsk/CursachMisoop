package App;

import Database.MyDateBase;
import Models.Shop.Shop;
import Models.Shop.ShopService;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        MyDateBase dateBase = new MyDateBase();
        Shop shop = new Shop("Adidas", "Chern", "Wear", "Yura");

        ShopService shopService = new ShopService(dateBase,shop);
        shopService.getInfoAboutShop();
        System.out.println(shop.getNameOfShop());
        System.out.println(shop.getSpecializationOfShop());
        System.out.println(shop.getAddress());
        System.out.println(shop.getNameOfDirector());
    }
}