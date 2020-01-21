package classes;

import java.sql.*;

/**
 * доступ к мета инфо о самой базе данных. Названии, полях, колонках и тд.
 */
public class SchemaInfo {
    public static void main(String[] args) throws SQLException {
        String catalog = null;
        String schemaPattern = null;
        String tableNamePattern = null;
        String columnNamePattern = null;
        String[] types = null;
        Connection myConn = null;
        ResultSet myRs = null;

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devcolibrijdbc", "root", "root");

            // Get metadata
            DatabaseMetaData databaseMetaData = myConn.getMetaData();

            // Get list of tables
            System.out.println("List of Tables");
            myRs = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);

            while (myRs.next()) {
                System.out.println(myRs.getString("TABLE_NAME"));
            }

            // Get list of columns
            System.out.println("\nList of Columns");
            myRs = databaseMetaData.getColumns(catalog, schemaPattern, "employees", columnNamePattern);

            while (myRs.next()) {
                System.out.println(myRs.getString("COLUMN_NAME"));
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null & myConn != null) {
                myConn.close();
                myRs.close();
            }
        }
    }
}