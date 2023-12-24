/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.CRUD;

import com.mycompany.sneakysun.BackEnd.Penjual;
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
public class CRUDPenjual {
   Connection connection;
    
    public CRUDPenjual(){
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
    
    public void createPenjual(String username, String passwordPenjual, String nama, String noTelp, String kota, String jalan, String kodePos, String namaToko) {
        try {
            String query = "INSERT INTO Penjual(username, password_penjual, nama, no_telp, kota, jalan, kode_pos, nama_toko) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, passwordPenjual);
                preparedStatement.setString(3, nama);
                preparedStatement.setString(4, noTelp);
                preparedStatement.setString(5, kota);
                preparedStatement.setString(6, jalan);
                preparedStatement.setString(7, kodePos);
                preparedStatement.setString(8, namaToko);
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

    public void updatePenjual(String username, String passwordPenjual, String nama, String noTelp, String kota, String jalan, String kodePos, String namaToko, String idPenjual) {
        try {
            String query = "UPDATE Penjual SET username = ?, password_penjual = ?, nama = ?, no_telp = ?, kota = ?, jalan = ?, kode_pos = ?, nama_toko = ? WHERE id_penjual = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, passwordPenjual);
                preparedStatement.setString(3, nama);
                preparedStatement.setString(4, noTelp);
                preparedStatement.setString(5, kota);
                preparedStatement.setString(6, jalan);
                preparedStatement.setString(7, kodePos);
                preparedStatement.setString(8, namaToko);
                preparedStatement.setString(9, idPenjual);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePenjual(String idPenjual) {
        try {
            String query = "DELETE FROM Penjual WHERE id_penjual = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, idPenjual);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Penjual> searchPenjual(String keyword) {
        List<Penjual> listPenjual = new ArrayList<>();

        try {           
            String query = "SELECT * FROM Penjual WHERE LOWER(username) LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idPenjual = resultSet.getString("id_penjual");
                String username = resultSet.getString("username");
                String userPassword = resultSet.getString("password_penjual");
                String nama = resultSet.getString("nama");
                String noTelp = resultSet.getString("no_telp");
                String kota = resultSet.getString("kota");
                String jalan = resultSet.getString("jalan");
                String kodePos = resultSet.getString("kode_pos");
                String namaToko = resultSet.getString("nama_toko");
                
                Penjual penjual = new Penjual(idPenjual, username, userPassword, nama, noTelp, kota, jalan, kodePos, namaToko);
                listPenjual.add(penjual);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPenjual;
    }
}
