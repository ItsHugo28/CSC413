/*************************************************
 File: AccountDAOConnection.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: Account DOA connection class
 *************************************************/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccountDataConnection {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void connect() {
        try {

            String url = "jdbc:mysql://localhost:3306/413";
            String username = "root";
            String password = "1234";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection");
            e.printStackTrace();
        }
    }
}
