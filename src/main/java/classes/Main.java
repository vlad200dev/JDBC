package classes;

import java.sql.*;

/**
 * Внести или изменить данные в таблице
 */
public class Main {
    private static Connection connection;
    private static final String username = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://localhost:3306/devcolibrijdbc";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // регистрация драйвера
            connection = DriverManager.getConnection(url, username, password);// установление соединения
             if (!connection.isClosed()){
                 System.out.println("connection established");
             }
            Statement statement = connection.createStatement(); // открытие запроса

//            statement.execute("INSERT INTO animal(animal_name, animal_description) VALUES ('Frog','lives in mallow')");
//            statement.executeUpdate("UPDATE animal SET animal_name='bird' WHERE animal_description ='lives in mallow'");
//            statement.addBatch("INSERT INTO animal(animal_name, animal_description) VALUES ('Worms','lives in mallow')");
//            statement.addBatch("INSERT INTO animal(animal_name, animal_description) VALUES ('Crocodile','lives in mallow')");
////            statement.addBatch("INSERT INTO animal(animal_name, animal_description) VALUES ('Snake','lives in mallow')");
////            statement.addBatch("INSERT INTO animal(animal_name, animal_description) VALUES ('Bever','lives in mallow')");
////            statement.executeBatch();
////            statement.clearBatch();
         } catch (SQLException | ClassNotFoundException e) {
            System.out.println("connection failed");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
