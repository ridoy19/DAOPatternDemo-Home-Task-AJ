package com.ridoy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;
    private static DBConnection instance = new DBConnection();

    private DBConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/home_task?useSSL=false",
                    "root","1234");
            System.out.println("Connection Established!");
        } catch (SQLException e) {
            System.err.println("Error connecting to database!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
