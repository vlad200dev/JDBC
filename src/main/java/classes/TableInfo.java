package classes;
import java.sql.*;
/**
 * Мета информация о конкретной таблице
 */
public class TableInfo {
    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet resultSet = null;

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devcolibrijdbc", "root", "root");

            // Run query
            myStmt = myConn.createStatement();
            resultSet = myStmt.executeQuery("select id, last_name, first_name, salary from employees");

            // Get result set metadata
            ResultSetMetaData rsMetaData = resultSet.getMetaData();

            // Display info
            int columnCount = rsMetaData.getColumnCount();
            System.out.println("Column count: " + columnCount + "\n");

            for (int column=1; column <= columnCount; column++) {
                System.out.println("Column name: " + rsMetaData.getColumnName(column));
                System.out.println("Column type name: " + rsMetaData.getColumnTypeName(column));
                System.out.println("Is Nullable: " + rsMetaData.isNullable(column));
                System.out.println("Is Auto Increment: " + rsMetaData.isAutoIncrement(column) + "\n");
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (resultSet != null & myStmt != null & myConn != null) {
                myStmt.close();
                resultSet.close();
                myConn.close();
            }
        }
    }
}
