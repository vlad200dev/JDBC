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
             if (!connection.isClosed()){ // для проверки закрыто ли соединение
                 System.out.println("connection established");
             }
            Statement statement = connection.createStatement(); // открытие запроса
//              Пакетная обработка, это если мы хотим за одну команду execute() выполнить НЕСКОЛЬКО запросов, то можно поместить их всех в 1 пакет через метод addBatch
//            statement.execute("INSERT INTO animal(animal_name, animal_description) VALUES ('Frog','lives in mallow')");
//            statement.executeUpdate("UPDATE animal SET animal_name='bird' WHERE animal_description ='lives in mallow'");
//            statement.addBatch("INSERT INTO animal(animal_name, animal_description) VALUES ('Worms','lives in mallow')");
//            statement.addBatch("INSERT INTO animal(animal_name, animal_description) VALUES ('Crocodile','lives in mallow')");
//            statement.addBatch("INSERT INTO animal(animal_name, animal_description) VALUES ('Snake','lives in mallow')");
//            statement.addBatch("INSERT INTO animal(animal_name, animal_description) VALUES ('Bever','lives in mallow')");
//            statement.executeBatch(); для запуска выполнения пакета
//            statement.clearBatch(); чтобы очистить пакет и поместить новые запросы
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
/*
   Статические запросы - не имеют внутри себя параметров
   Динамические запросы - запросы с параметром

  Методы:
  важно! эти методы за 1 раз один запрос!
    execute() универсальный метод. данный метод может получать несколько ResultSet. Не использовать. Не понял.
    executeUpdate() - с помощью этого метода выполняются INSERT, UPDATE, DELETE. Получать этим методом данные нельзя.
    executeQuery() - данный метод для SELECT. Возвращает ResultSet.
 */