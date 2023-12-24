/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.CRUD;

import com.mycompany.sneakysun.Database.ConnectDatabase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Satriock
 */
public class CRUDPesanan {
    Connection connection;
    
    public CRUDPesanan(){
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
    
    public void createPesanan(String idPembeli) {
        try {
            String insertQuery = "INSERT INTO Pesanan (id_pembeli) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, idPembeli);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Pesanan berhasil ditambahkan.");
                } else {
                    System.out.println("Gagal menambahkan pesanan.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePesanan(String idPesanan, int jumlah, String idPembeli) {
        try {
            String updateQuery = "UPDATE Pesanan SET jumlah = ?, id_pembeli = ? WHERE id_pesanan = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, jumlah);
                preparedStatement.setString(2, idPembeli);
                preparedStatement.setString(3, idPesanan);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Pesanan berhasil diperbarui.");
                } else {
                    System.out.println("Gagal memperbarui pesanan.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePesanan(String idPesanan) {
        try {
            String deleteQuery = "DELETE FROM Pesanan WHERE id_pesanan = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, idPesanan);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Pesanan berhasil dihapus.");
                } else {
                    System.out.println("Gagal menghapus pesanan.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String getPesananByPembeli(String idPembeli) {
        try {
            String selectQuery = "SELECT id_pesanan FROM Pesanan WHERE id_pembeli = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, idPembeli);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("id_pesanan");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
