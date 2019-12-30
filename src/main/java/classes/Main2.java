package classes;

import java.sql.*;

/**
 * ResultSet
 */
public class Main2 {
        private static Connection connection;
        private static final String url = "jdbc:mysql://localhost:3306/devcolibrijdbc";
        private static final String username = "root";
        private static final String password = "59gkCz5HU7*a";

    public static void main(String[] args) {
        Main2.connect();
        String query = "select * from user"; // можно любой запрос выбирать
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query); // метод executeQuery возращает тип ResultSet
            while (resultSet.next()){ // проходит по всем строкам
                User user = new User();
                user.setId(resultSet.getInt(1)); // цифры и тип get важно. Тип get зависит от типа колонки, а номер это порядковый номер колонки.
                user.setUsername(resultSet.getString(2)); // также можно по имени колонки обращаться (тут можно указать getString("username"))
                user.setPassword(resultSet.getString("password")); // такой способ прдпочтительнее так как если порядок столбцов поменяется будут ошибки
                System.out.println(user);
              }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection !=null){
                try {
                    connection.isClosed();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private static void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            if (!connection.isClosed()){
                System.out.println("connection established");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("connection failed");
        }
    }

}
/*
connection established
Userid1, username test, password test
Userid2, username test2, password test2
 */