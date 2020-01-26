package classes;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionViaProperties {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        try {
            // 1. Load the properties file
            Properties props = new Properties();
            // learn to put source file in any place on hard drive, not just only the resources folder
            props.load(new FileInputStream("c:\\Projects\\JDBC\\demo.properties"));
            // 2. Read the props
            String theUser = props.getProperty("user");
            String thePassword = props.getProperty("password");
            String theDburl = props.getProperty("dburl");
            System.out.println("Connecting to database...");
            System.out.println("Database URL: " + theDburl);
            System.out.println("User: " + theUser);
            // 3. Get a connection to database
            connection = DriverManager.getConnection(theDburl, theUser, thePassword);
            System.out.println("\nConnection successful!\n");
            // 4. Create a statement and execute
            statement = connection.createStatement();
            resultset = statement.executeQuery("select * from employees");
            // 5. Process the result set
            while (resultset.next()) {
                System.out.println(resultset.getString("last_name") + ", " + resultset.getString("first_name"));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(connection, statement, resultset);
        }
    }

    private static void close(Connection connection, Statement statement, ResultSet resultset) throws SQLException {
        if (resultset != null) {
            resultset.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}
