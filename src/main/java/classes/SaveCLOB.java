package classes;

import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class SaveCLOB {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        FileReader input = null;
        try {
            // 1. Get a connection to database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/devcolibrijdbc", "root", "root");
            System.out.println("Connection Established");
            // 2. Create column(clob)
            Statement statement = connection.createStatement();
            statement.execute("ALTER TABLE employees ADD clob LONGTEXT");
            // 3. Prepare statement
            preparedstatement = connection.prepareStatement("update employees set clob=? where email='john.doe@foo.com'");
            // 4. Set parameter for file name
            File theFile = new File("SaveCLOB.txt");
            input = new FileReader(theFile);
            preparedstatement.setCharacterStream(1, input);
            System.out.println("Reading input file: " + theFile.getAbsolutePath());
            // 5. Execute statement
            System.out.println("\nStoring in database: " + theFile);
            preparedstatement.executeUpdate();
            System.out.println("\nCompleted successfully!");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
            close(connection, preparedstatement);
        }
    }
    private static void close(Connection connection, Statement preparedstatement) throws SQLException {
        if (preparedstatement != null) {
            preparedstatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
