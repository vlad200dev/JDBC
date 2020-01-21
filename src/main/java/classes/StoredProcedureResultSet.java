package classes;

import java.sql.*;

/**
 * Stored Procedures. ResultSet
 */
public class StoredProcedureResultSet {
    public static void main(String[] args) throws Exception {

        Connection myConn = null;
        CallableStatement myStmt = null;
        ResultSet myRs = null;
        String theDepartment = "Engineering";

        try {
            // Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devcolibrijdbc", "root", "root");

            // Prepare the stored procedure call
            myStmt = myConn.prepareCall("{call get_employees_for_department(?)}");

            // Set the parameter
            myStmt.setString(1, theDepartment);

            // Call stored procedure
            myStmt.execute();
            System.out.println("Finished calling stored procedure.\n");

            // Get the result set
            myRs = myStmt.getResultSet();

            // Display the result set
            while (myRs.next()) {
                String lastName = myRs.getString("last_name");
                String firstName = myRs.getString("first_name");
                double salary = myRs.getDouble("salary");
                String department = myRs.getString("department");

                System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(myConn, myStmt, myRs);
        }
    }


    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
        if (myRs != null ) {
            myRs.close();
        }
        if (myStmt != null) {
            myStmt.close();
        }
        if (myConn != null) {
            myConn.close();
        }
    }

}
