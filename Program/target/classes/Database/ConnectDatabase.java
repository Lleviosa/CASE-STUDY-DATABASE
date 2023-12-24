/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Satriock
 */
public class ConnectDatabase{
    Connection connection;

    public ConnectDatabase(){
        initConnection();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> closeDatabaseConnection()));
    }
    
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=sneakysun;encrypt=false";
    private static final String DB_USER = "satriock";
    private static final String DB_PASSWORD = "walawe";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void initConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                this.connection = ConnectDatabase.getConnection();
            }
            System.out.print("Database is opened");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("Failed to opened database");
        }
    }

    public void closeDatabaseConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
                System.out.print("Database is closed");
            }
        } catch (SQLException e) {
            System.out.print("Failed to closed database");
            e.printStackTrace();
        }
    }
}