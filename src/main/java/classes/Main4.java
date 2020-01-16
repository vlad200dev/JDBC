package classes;

import java.sql.*;
/**
 * Stored Procedures. IN
 * Stored Procedures - Это заранее подготовленный sql код, который можно применять.Применять сразу для всей установленной выборки
 */
public class Main4 {
    private static Connection connection;
    private static Statement statement;
    private static String URL = "jdbc:mysql://localhost:3306/devcolibrijdbc";
    private static String PASSWORD = "root";
    private static String USERNAME = "root";

    public static void main(String[] args) throws SQLException {
        Main4.connect();
        String theDepartment = "Engineering";
        int theIncreaseAmount = 1000;

        //show salaries before the increase
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees WHERE department='Engineering'");
        while (resultSet.next()){
            String last_name=resultSet.getString("last_name");
            String first_name =resultSet.getString("first_name");
            String email =resultSet.getString("email");
            String department =resultSet.getString("department");
            Double salary =resultSet.getDouble("salary");
            System.out.println(last_name+", "+first_name+", "+email+", "+department+", "+salary);
        }

        //PrepareStored procedure call
        System.out.println("Calling stored procedure");
        CallableStatement myCall = connection.prepareCall("{call increase_salaries_for_department(?,?)}");
        // Set parameters for required pool
        myCall.setString(1,theDepartment);
        myCall.setDouble(2,theIncreaseAmount);
        //call stored procedure
        myCall.execute();
        System.out.println("Finished calling stored procedure");

        //show salaries after the increase
        System.out.println("Salaries after called store procedure");
        ResultSet resultSet2 = statement.executeQuery("SELECT * FROM employees WHERE department='Engineering'");
        while (resultSet2.next()){
            String last_name=resultSet2.getString("last_name");
            String first_name =resultSet2.getString("first_name");
            String email =resultSet2.getString("email");
            String department =resultSet2.getString("department");
            Double salary =resultSet2.getDouble("salary");
            System.out.println(last_name+", "+first_name+", "+email+", "+department+", "+salary);
        }
        connection.close();
    }



    private static void connect() throws SQLException {
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        if (!connection.isClosed()){
            System.out.println("connection is set");
        }
        statement = connection.createStatement();
    }
}
