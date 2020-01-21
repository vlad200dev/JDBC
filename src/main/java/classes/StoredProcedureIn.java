package classes;

import java.sql.*;

/**
 * Stored Procedures. IN
 * Stored Procedures - Это заранее подготовленный sql код, который можно применять.Применять сразу для всей установленной выборки
 */
public class StoredProcedureIn {

    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        CallableStatement myStmt = null;
        String theDepartment = "Engineering";
        int theIncreaseAmount = 10000;

        try {
            // Get a connection to database
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devcolibrijdbc", "root", "root");

            // Show salaries BEFORE
            System.out.println("Salaries BEFORE\n");
            showSalaries(myConn, theDepartment);

            // Prepare the stored procedure call
            myStmt = myConn.prepareCall("{call increase_salaries_for_department(?, ?)}");

            // Set the parameters
            myStmt.setString(1, theDepartment);
            myStmt.setDouble(2, theIncreaseAmount);

            // Call stored procedure
            myStmt.execute();
            System.out.println("Finished calling stored procedure");

            // Show salaries AFTER
            System.out.println("\nSalaries AFTER\n");
            showSalaries(myConn, theDepartment);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myStmt != null & myConn != null) {
                myConn.close();
                myStmt.close();
            }
        }
    }

    private static void showSalaries(Connection myConn, String theDepartment) throws SQLException {
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // Prepare statement
            myStmt = myConn.prepareStatement("select * from employees where department=?");

            myStmt.setString(1, theDepartment);

            // Execute SQL query
            myRs = myStmt.executeQuery();

            // Process result set
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
            try {
                myRs.close();
            } catch (NullPointerException exc){
                exc.printStackTrace();
            }
        }
    }
}
