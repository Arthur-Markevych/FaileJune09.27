package com.shit.databasesource;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/userdata";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";





    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            throw new NullPointerException(e+ "");
        } catch (ClassNotFoundException e) {
            throw new NullPointerException(e+ "");        }

    }
}
