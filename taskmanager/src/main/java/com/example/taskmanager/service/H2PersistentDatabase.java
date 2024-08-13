package com.example.taskmanager.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2PersistentDatabase {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:h2:~/testdb";
        String username = "sa";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            System.out.println("Conectat la baza de date H2 persistentă.");
            // Executați operațiuni cu baza de date
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
