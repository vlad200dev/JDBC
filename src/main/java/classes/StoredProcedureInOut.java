package classes;

import java.sql.*;

/**
 * Stored Procedures. INOUT
 */
public class StoredProcedureInOut {
    public static void main(String[] args) throws Exception {

        Connection myConn = null;
        CallableStatement myStmt = null;
        String theDepartment = "Engineering";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devcolibrijdbc", "root", "root");

            // Prepare the stored procedure call
            myStmt = myConn.prepareCall("{call greet_the_department(?)}");

            // Set the parameters
            myStmt.setString(1, theDepartment);
            myStmt.registerOutParameter(1, Types.VARCHAR);

            // Call stored procedure
            myStmt.execute();
            System.out.println("Finished calling stored procedure");

            // Get the value of the INOUT parameter
            String theResult = myStmt.getString(1);

            System.out.println("\nThe result = " + theResult);

        } catch (SQLException|ClassNotFoundException exc ) {
            exc.printStackTrace();
        } finally {
            if (myStmt != null & myConn != null) {
                myConn.close();
                myStmt.close();
            }
        }
    }
}
