package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

    private static Connection connection;
    private static final String name = "postgres";
    private static final String password = "marouned";

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java", name, password);
            }
        } catch (SQLException ex) {
            System.err.println("Can't create connection to DB");
            ex.printStackTrace();
            System.exit(1);
        }
        return connection;
    }
}
