/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.CRUD;

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
public class CRUDPembeli {
    Connection connection;
    
    public CRUDPembeli(){
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
    
//    public void closeDatabaseConnection() {
//        try {
//            if (this.connection != null && !this.connection.isClosed()) {
//                this.connection.close();
//                System.out.print("Database is closed");
//            }
//        } catch (SQLException e) {
//            System.out.print("Failed to closed database");
//            e.printStackTrace();
//        }
//    }
    
    public void createPembeli(String username, String passwordPembeli, String nama, String noTelp, String kota, String jalan, String kodePos) {
        try {
            String query = "INSERT INTO Pembeli(username, password_pembeli, nama, no_telp, kota, jalan, kode_pos) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, passwordPembeli);
                preparedStatement.setString(3, nama);
                preparedStatement.setString(4, noTelp);
                preparedStatement.setString(5, kota);
                preparedStatement.setString(6, jalan);
                preparedStatement.setString(7, kodePos);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metode untuk mendapatkan pembeli berdasarkan ID
//    public Pembeli getPembeliById(String idPembeli) {
//        try {
//            String query = "SELECT * FROM Pembeli WHERE id_pembeli = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setString(1, idPembeli);
//
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    if (resultSet.next()) {
//                        return mapResultSetToPembeli(resultSet);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void updatePembeli(String username, String passwordPembeli, String nama, String noTelp, String kota, String jalan, String kodePos, String idPembeli) {
        try {
            String query = "UPDATE Pembeli SET username = ?, password_pembeli = ?, nama = ?, no_telp = ?, kota = ?, jalan = ?, kode_pos = ? WHERE id_pembeli = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, passwordPembeli);
                preparedStatement.setString(3, nama);
                preparedStatement.setString(4, noTelp);
                preparedStatement.setString(5, kota);
                preparedStatement.setString(6, jalan);
                preparedStatement.setString(7, kodePos);
                preparedStatement.setString(8, idPembeli);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePembeli(String idPembeli) {
        try {
            String query = "DELETE FROM Pembeli WHERE id_pembeli = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, idPembeli);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Pembeli> searchPembeli(String keyword) {
        List<Pembeli> listPembeli = new ArrayList<>();

        try {           
            String query = "SELECT * FROM Pembeli WHERE LOWER(username) LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idPembeli = resultSet.getString("id_pembeli");
                String username = resultSet.getString("username");
                String userPassword = resultSet.getString("password_pembeli");
                String nama = resultSet.getString("nama");
                String noTelp = resultSet.getString("no_telp");
                String kota = resultSet.getString("kota");
                String jalan = resultSet.getString("jalan");
                String kodePos = resultSet.getString("kode_pos");
                
                Pembeli pembeli = new Pembeli(idPembeli, username, userPassword, nama, noTelp, kota, jalan, kodePos);
                listPembeli.add(pembeli);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPembeli;
    }
}
