package classes;

import java.sql.*;
import java.util.Scanner;
/**
 Транзакции дают нам возможность контролировать когда и где сохранять изменения в БД.
 Можно прописать все необходимые транзакции и их запуск будет контролироваться по выбору пользователя.
 */
public class Transaction {
    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devcolibrijdbc", "root", "root");

            // Turn off auto commit
            myConn.setAutoCommit(false);

            // Show salaries BEFORE
            System.out.println("Salaries BEFORE\n");
            showSalaries(myConn, "HR");
            showSalaries(myConn, "Engineering");

            // Transaction Step 1: Delete all HR employees
            myStmt = myConn.createStatement();
            myStmt.executeUpdate("delete from employees where department='HR'");

            // Transaction Step 2: Set salaries to 300000 for all Engineering employees
            myStmt.executeUpdate("update employees set salary=300000 where department='Engineering'");

            System.out.println("\n>> Transaction steps are ready.\n");

            // запрос к пользователю
            boolean ok = askUserIfOkToSave();

            if (ok) {
                // store in database
                myConn.commit();
                System.out.println("\n>> Transaction COMMITTED.\n");
            } else {
                // discard
                myConn.rollback();
                System.out.println("\n>> Transaction ROLLED BACK.\n");
            }

            // Show salaries AFTER
            System.out.println("Salaries AFTER\n");
            showSalaries(myConn, "HR");
            showSalaries(myConn, "Engineering");

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myStmt != null & myConn != null) {
                myConn.close();
                myStmt.close();
            }
        }
    }

    /**
     * Метод для вывода на консоль запроса к пользователю
     */
    private static boolean askUserIfOkToSave() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Is it okay to save?  yes/no: ");
        String input = scanner.nextLine();
        scanner.close();
        return input.equalsIgnoreCase("yes");
    }

    private static void showSalaries(Connection myConn, String theDepartment) throws SQLException {
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        System.out.println("Show Salaries for Department: " + theDepartment);
        try {
            myStmt = myConn.prepareStatement("select * from employees where department=?");
            myStmt.setString(1, theDepartment);
            myRs = myStmt.executeQuery();
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
                myStmt.close();
        }
    }
}
