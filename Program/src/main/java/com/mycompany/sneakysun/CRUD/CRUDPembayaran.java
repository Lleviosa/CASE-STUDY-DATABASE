/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.CRUD;

import com.mycompany.sneakysun.BackEnd.Pembayaran;
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
public class CRUDPembayaran {
    Connection connection;
    
    public CRUDPembayaran(){
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
    
    public void createPembayaran(String idPesanan) {
        String insertQuery = "INSERT INTO Pembayaran (id_pesanan) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, idPesanan);
            preparedStatement.executeUpdate();
            System.out.println("Pembayaran berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan pembayaran.");
        }
    }

    public void updatePembayaran(String idPembayaran, String idPesanan) {
        String updateQuery = "UPDATE Pembayaran SET id_pesanan = ? WHERE id_pembayaran = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, idPesanan);
            preparedStatement.setString(2, idPembayaran);
            preparedStatement.executeUpdate();
            System.out.println("Pembayaran berhasil diupdate.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal mengupdate pembayaran.");
        }
    }

    public void deletePembayaran(String idPembayaran) {
        String deleteQuery = "DELETE FROM Pembayaran WHERE id_pembayaran = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, idPembayaran);
            preparedStatement.executeUpdate();
            System.out.println("Pembayaran berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menghapus pembayaran.");
        }
    }
    
//        public List<Pembayaran> getAllPembayaran() {
//        List<Pembayaran> pembayaranList = new ArrayList<>();
//        String selectQuery = "SELECT * FROM Pembayaran";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//            while (resultSet.next()) {
//                Pembayaran pembayaran = new Pembayaran();
//                pembayaran.setIdPembayaran(resultSet.getString("id_pembayaran"));
//                pembayaran.setIdPesanan(resultSet.getString("id_pesanan"));
//                pembayaran.setTotal(resultSet.getInt("total"));
//
//                pembayaranList.add(pembayaran);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return pembayaranList;
//    }
}
