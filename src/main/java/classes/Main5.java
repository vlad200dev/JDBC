package classes;

import java.sql.*;

/**
 * Stored Procedures. INOUT
 */
public class Main5 {
    public static void main(String[] args) throws Exception {

        Connection myConn = null;
        CallableStatement myStmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/devcolibrijdbc", "root", "root");

            String theDepartment = "Engineering"; // переменная

            // Prepare the stored procedure call
            myStmt = myConn
                    .prepareCall("{call greet_the_department(?)}");

            // Set the parameters
            myStmt.registerOutParameter(1, Types.VARCHAR);
            myStmt.setString(1, theDepartment);

            // Call stored procedure
            System.out.println("Calling stored procedure.  greet_the_department('" + theDepartment + "')");
            myStmt.execute();
            System.out.println("Finished calling stored procedure");

            // Get the value of the INOUT parameter
            String theResult = myStmt.getString(1);

            System.out.println("\nThe result = " + theResult);

        } catch (SQLException exc ) {
            exc.printStackTrace();
        } catch (ClassNotFoundException exc){
            exc.printStackTrace();
        } finally {
            close(myConn, myStmt);
        }
    }

    private static void close(Connection myConn, Statement myStmt) throws SQLException {
        if (myStmt != null) {
            myStmt.close();
        }

        if (myConn != null) {
            myConn.close();
        }
    }
}
