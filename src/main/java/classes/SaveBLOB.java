package classes;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

public class SaveBLOB {
    public static void main(String[] args) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        FileInputStream input = null;
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devcolibrijdbc", "root", "root");
            System.out.println("Connection established");
            // 2. make new column (resume)
            Statement statement = myConn.createStatement();
            statement.execute("ALTER TABLE employees ADD resume BLOB");
            // 3. Prepare statement
            String sql = "update employees set resume=? where email='john.doe@foo.com'";
            myStmt = myConn.prepareStatement(sql);
            // 4. Set parameter for resume file name
            File theFile = new File("sample_document.pdf");
            input = new FileInputStream(theFile);
            myStmt.setBinaryStream(1, input);
            System.out.println("Reading input file: " + theFile.getAbsolutePath());
            // 5. Execute statement
            System.out.println("\nStoring resume in database: " + theFile);
            System.out.println(sql);
            myStmt.executeUpdate();
            System.out.println("\nCompleted successfully!");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
            close(myConn, myStmt);
        }
    }
    private static void close(Connection myConn, Statement myStmt)
            throws SQLException {

        if (myStmt != null) {
            myStmt.close();
        }

        if (myConn != null) {
            myConn.close();
        }
    }
}
