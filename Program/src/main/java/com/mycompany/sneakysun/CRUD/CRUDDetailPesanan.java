/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.CRUD;

import com.mycompany.sneakysun.Database.ConnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Satriock
 */
public class CRUDDetailPesanan {
    Connection connection;
    
    public CRUDDetailPesanan(){
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
    
    public void createDetailPesanan(String idPesanan, String idProduk, int jumlah) {
        try {
            String insertQuery = "INSERT INTO Detail_Pesanan (id_pesanan, id_produk, jumlah) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, idPesanan);
                preparedStatement.setString(2, idProduk);
                preparedStatement.setInt(3, jumlah);
                preparedStatement.executeUpdate();
                System.out.println("Pesanan berhasil ditambahkan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan pesanan.");
        }
    }

    public void updateDetailPesanan(String idPesanan, String idProduk, int jumlah, String idDetailPesanan) {
        try {
            String updateQuery = "UPDATE Detail_Pesanan SET id_pesanan = ?, id_produk = ?, jumlah = ? WHERE id_DetailPesanan = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, idPesanan);
                preparedStatement.setString(2, idProduk);
                preparedStatement.setInt(3, jumlah);
                preparedStatement.setString(5, idDetailPesanan);
                preparedStatement.executeUpdate();
                System.out.println("Pesanan berhasil ditambahkan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan pesanan.");
        }
    }

    public void deleteDetailPesanan(String idDetailPesanan) {
        try {
            String deleteQuery = "DELETE FROM Detail_Pesanan WHERE id_DetailPesanan = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, idDetailPesanan);
                preparedStatement.executeUpdate();
                System.out.println("Pesanan berhasil ditambahkan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan pesanan.");
        }
    }
}
