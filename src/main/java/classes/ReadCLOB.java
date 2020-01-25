package classes;

import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.*;

public class ReadCLOB {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        Reader input = null;
        FileWriter output = null;
        try {
            // 1. Get a connection to database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devcolibrijdbc", "root", "root");
            // 2. Execute statement
            statement = connection.createStatement();
            resultset = statement.executeQuery("select clob from employees where email='john.doe@foo.com'");
            // 3. Set up a handle to the file
            File theFile = new File("Clob_from_db.txt");
            output = new FileWriter(theFile);
            if (resultset.next()) {
                input = resultset.getCharacterStream("clob");
                System.out.println("Reading resume from database...");
                int theChar;
                while ((theChar = input.read()) > 0) {
                    output.write(theChar);
                }
                System.out.println("\nSaved to file: " + theFile.getAbsolutePath());
                System.out.println("\nCompleted successfully!");
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
            close(connection, statement);
        }
    }

    private static void close(Connection connection, Statement statement)
            throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
