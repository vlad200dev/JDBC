package classes;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Доступ к рабочей meta информации.
 */
public class DataBaseInfo {
    public static void main(String[] args) throws SQLException {

        Connection myConn = null;

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devcolibrijdbc", "root", "root");

            // Get metadata
            DatabaseMetaData databaseMetaData = myConn.getMetaData();

            // Info about database type
            System.out.println("Product name: " + databaseMetaData.getDatabaseProductName());
            System.out.println("Product version: " + databaseMetaData.getDatabaseProductVersion());
            System.out.println();

            //  Info about JDBC Driver
            System.out.println("JDBC Driver name: " + databaseMetaData.getDriverName());
            System.out.println("JDBC Driver version: " + databaseMetaData.getDriverVersion());

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            myConn.close();
        }
    }
}
