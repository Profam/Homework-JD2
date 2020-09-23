package by.rabtsevich;

import java.sql.*;
import java.util.Date;
import java.util.Properties;


public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "root");
            properties.put("useSSL", "false");
            properties.put("serverTimezone", "UTC");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ListExpenses",
                    properties
            );
            System.out.println("Is connected: " + !connection.isClosed());

            Statement statement = connection.createStatement();

            ResultSet stringResultSet = statement.executeQuery("select * from expenses where num = 3");
            while(stringResultSet.next()) {
                 int num =  stringResultSet.getInt("num");
                 Date payDate = stringResultSet.getDate("paydate");
                 int receiver =stringResultSet.getInt("receiver");
                 double value  = stringResultSet.getDouble("value");
                 String queryString = new String(num + "," + payDate + "," +  receiver+","+value);
                statement.executeUpdate("insert into expenses values(" + queryString   + ")");
            }
            ResultSet resultSet = statement.executeQuery("select * from expenses");
            while(resultSet.next()) {
                System.out.print(resultSet.getInt(1)+ " ");
                System.out.print(resultSet.getDate(2)+ " ");
                System.out.print(resultSet.getInt(3)+ " ");
                System.out.print( resultSet.getBigDecimal(4)+ " ");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Finished");
        }
    }
}