package classes;

import java.sql.*;

/**
 * Stored Procedures. Out
 */
public class StoredProcedureOut {
    public static void main(String[] args) throws Exception {

        Connection myConn = null;
        CallableStatement myStmt = null;
        String theDepartment = "Engineering";

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devcolibrijdbc", "root", "root");

            // Prepare the stored procedure call
            myStmt = myConn.prepareCall("{call get_count_for_department(?, ?)}");

            // Set the parameters
            myStmt.setString(1, theDepartment);
            myStmt.registerOutParameter(2, Types.INTEGER);

            // Call stored procedure
            myStmt.execute();
            System.out.println("Finished calling stored procedure");

            // Get the value of the OUT parameter
            int theCount = myStmt.getInt(2);

            System.out.println("\nThe count = " + theCount);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(myConn, myStmt);
        }
    }

    private static void close(Connection myConn, Statement myStmt) throws SQLException {
        if (myStmt != null & myConn != null) {
            myConn.close();
            myStmt.close();
        }
    }
}
