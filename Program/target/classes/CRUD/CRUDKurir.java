/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.CRUD;

import com.mycompany.sneakysun.BackEnd.Kurir;
import com.mycompany.sneakysun.BackEnd.Pembeli;
import com.mycompany.sneakysun.Database.ConnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Satriock
 */
public class CRUDKurir {
    Connection connection;
    
    public CRUDKurir(){
        initConnection();
//        closeDatabaseConnection();
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
    
    public void createKurir(String username, String passwordKurir, String nama) {
        String insertQuery = "INSERT INTO Kurir (username, password_kurir, nama) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passwordKurir);
            preparedStatement.setString(3, nama);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateKurir(String username, String passwordKurir, String nama, String idKurir) {
        String updateQuery = "UPDATE Kurir SET username = ?, password_kurir = ?, nama = ? WHERE id_kurir = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passwordKurir);
            preparedStatement.setString(3, nama);
            preparedStatement.setString(4, idKurir);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteKurir(String idKurir) {
        String deleteQuery = "DELETE FROM Kurir WHERE id_kurir = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, idKurir);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Kurir> searchKurir(String keyword) {
        List<Kurir> listKurir = new ArrayList<>();

        try {           
            String query = "SELECT * FROM Kurir WHERE LOWER(username) LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idKurir = resultSet.getString("id_kurir");
                String username = resultSet.getString("username");
                String userPassword = resultSet.getString("password_kurir");
                String nama = resultSet.getString("nama");

                Kurir kurir = new Kurir(idKurir, username, userPassword, nama);
                listKurir.add(kurir);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listKurir;
    }
}
