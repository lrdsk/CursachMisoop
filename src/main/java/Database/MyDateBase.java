package Database;

import java.sql.*;

public class MyDateBase {
    public Connection connection = null; //все переменные для подключения
    private String url = "jdbc:postgresql://localhost:5432/dbtest";
    private String username = "postgres";
    private String password = "postgres";

    public Connection connectionToBase(){ //делает подключение к базе данных, возвращает тип данных Connection
        //не закрывает поток
        try{
            System.out.println("Подключение к базе данных");
            connection = DriverManager.getConnection(url, username, password);
        }
        catch(Exception e){
            System.out.println("Что-то не так");
            e.printStackTrace();
        }
        finally {
            return connection;
        }
    }

}
