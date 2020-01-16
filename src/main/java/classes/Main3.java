package classes;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Calendar;

/**
 * preparedStatement
 * PreparedStatement запросы изначально скомпилированны это дает прирост скорости выполнения
 */
public class Main3 {
    private static Connection connection;
    private static Statement statement;
    private static final String URL = "jdbc:mysql://localhost:3306/devcolibrijdbc";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static PreparedStatement preparedStatement = null;

    private static final String INSERT_INTO = "INSERT INTO Book VALUES(?,?,?,?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM Book";
    private static final String DELETE_ALL = "DELETE FROM Book WHERE id=?"; // выполняется по тому же принципу

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        Main3.connect();

        System.out.println("connection established");

        preparedStatement = connection.prepareStatement(INSERT_INTO);
        preparedStatement.setInt(1,2);
        preparedStatement.setString(2,"War and Peace");
        preparedStatement.setString(3,"Roman");
        preparedStatement.setDouble(4,126);
        preparedStatement.setDate(5,new Date(Calendar.DATE));
        preparedStatement.setDate(6,new Date(Calendar.DATE-5));
        preparedStatement.setBlob(7, new FileInputStream("tiger.png"));
        preparedStatement.execute();

//        preparedStatement = connection.prepareStatement(SELECT_ALL);
//        ResultSet result = preparedStatement.executeQuery();
//        while (result.next()) {
//            int id = result.getInt("id");
//            String title = result.getString("title");
//            String description = result.getString("description");
//            Double rating = result.getDouble("rating");
//            Date published = result.getDate("published");
//            Date created = result.getDate("created");
//            byte[] icode = result.getBytes("icode");
//            System.out.println("id: " + id + ", title " + title +
//                    ", description " +description+ ", rating " + rating + ", published " +
//                    published + ", created " + created + ", picture length " + icode.length);
//        }

//        preparedStatement = connection.prepareStatement(DELETE_ALL);
//        preparedStatement.setInt(1,2);
//        preparedStatement.executeUpdate();

        connection.close();
        System.out.println("connection closed");

    }


    public static void connect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/*
connection established
id: 2, title War and Peace, description Roman, rating 126.0, published 1970-01-01, created 1970-01-01, picture length 89911
connection closed
 */