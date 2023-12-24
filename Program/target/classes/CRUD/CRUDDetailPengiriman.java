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
public class CRUDDetailPengiriman {
    Connection connection;
    
    public CRUDDetailPengiriman(){
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
    
    public void createDetailPengiriman(String noResi, String kota, String jalan, String kodePos, String idKurir) {
        String insertQuery = "INSERT INTO Detail_Pengiriman (no_resi, kota, jalan, kode_pos, id_kurir) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, noResi);
            preparedStatement.setString(2, kota);
            preparedStatement.setString(3, jalan);
            preparedStatement.setString(4, kodePos);
            preparedStatement.setString(5, idKurir);
            preparedStatement.executeUpdate();
            System.out.println("Pengiriman berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan pengiriman.");
        }
    }

    public void updateDetailPengiriman(String noResi, String kota, String jalan, String kodePos, String idKurir) {
        String updateQuery = "UPDATE Detail_Pengiriman SET kota = ?, jalan = ?, kode_pos = ?, id_kurir = ? WHERE no_resi = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, noResi);
            preparedStatement.setString(2, kota);
            preparedStatement.setString(3, jalan);
            preparedStatement.setString(4, kodePos);
            preparedStatement.setString(5, idKurir);
            preparedStatement.executeUpdate();
            System.out.println("Pengiriman berhasil diupdate.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal mengupdate pengiriman.");
        }
    }

    public void deletePengiriman(String noResi) {
        String deleteQuery = "DELETE FROM Detail_Pengiriman WHERE no_resi = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, noResi);
            preparedStatement.executeUpdate();
            System.out.println("Pengiriman berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menghapus pengiriman.");
        }
    }
}
