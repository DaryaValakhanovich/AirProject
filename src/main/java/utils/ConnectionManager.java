package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    private ConnectionManager() {}

    public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        return con;
    }
}

