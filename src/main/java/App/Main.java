package App;

import Database.MyDateBase;
import Models.Employee.Employee;
import Models.Employee.EmployeeService;
import Models.Shop.Shop;
import Models.Shop.ShopService;

import java.io.IOException;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        try (MyDateBase dateBase = new MyDateBase()) {
            Shop shop = new Shop("Adidas", "Chern", "Wear", "Yura");
            Employee employee = new Employee(10, "Artur", 5500);

            ShopService shopService = new ShopService(dateBase, shop);
            EmployeeService employeeService = new EmployeeService(dateBase);

            employeeService.setInfoAboutEmployeeToDB(employee);

           shopService.setInfoAboutShopToBase(shop.getNameOfShop(), shop.getAddress(),
                    shop.getSpecializationOfShop(), shop.getNameOfDirector());

            System.out.println(shopService.getInfoAboutShopFromBase());


            System.out.println(employeeService.getInfoAboutEmployeeFromDB("Artur"));
        }
    }
}