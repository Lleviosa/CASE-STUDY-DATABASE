/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.CRUD;

import com.mycompany.sneakysun.Database.ConnectDatabase;
import com.mycompany.sneakysun.BackEnd.Pengiriman;
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
public class CRUDPengiriman {
    Connection connection;
    
    public CRUDPengiriman(){
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
    
    public void createPengiriman(String idPembeli, String idPesanan) {
        String insertQuery = "INSERT INTO Pengiriman (id_pembeli, id_pesanan) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, idPembeli);
            preparedStatement.setString(2, idPesanan);
            preparedStatement.executeUpdate();
            System.out.println("Pengiriman berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan pengiriman.");
        }
    }

    public void updatePengiriman(String noResi, String idPembeli, String idPesanan) {
        String updateQuery = "UPDATE Pengiriman SET id_pembeli = ?, id_pesanan = ? WHERE no_resi = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, idPembeli);
            preparedStatement.setString(2, idPesanan);
            preparedStatement.setString(3, noResi);
            preparedStatement.executeUpdate();
            System.out.println("Pengiriman berhasil diupdate.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal mengupdate pengiriman.");
        }
    }

    public void deletePengiriman(String noResi) {
        String deleteQuery = "DELETE FROM Pengiriman WHERE no_resi = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, noResi);
            preparedStatement.executeUpdate();
            System.out.println("Pengiriman berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menghapus pengiriman.");
        }
    }
    
    public List<String> getNoResiList() {
        List<String> noResiList = new ArrayList<>();

        try {
            String selectQuery = "SELECT no_resi FROM Pengiriman";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        noResiList.add(resultSet.getString("no_resi"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return noResiList;
    }
    
    public ResultSet getInfoPengirimanById(String noResi) {
        try {
            String selectQuery = "SELECT pe.no_resi, pb.id_pembeli, pb.kota, pb.jalan, pb.kode_pos " +
                                "FROM Pengiriman pe " +
                                "JOIN Pembeli pb ON pe.id_pembeli = pb.id_pembeli " +
                                "WHERE pe.no_resi = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, noResi);

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
//        public List<Pengiriman> getAllPengiriman() {
//        List<Pengiriman> pengirimanList = new ArrayList<>();
//        String selectQuery = "SELECT * FROM Pengiriman";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//            while (resultSet.next()) {
//                Pengiriman pengiriman = new Pengiriman();
//                pengiriman.setNoResi(resultSet.getString("no_resi"));
//                pengiriman.setNamaKurir(resultSet.getString("nama_kurir"));
//                pengiriman.setIdPembeli(resultSet.getString("id_pembeli"));
//                pengiriman.setIdPesanan(resultSet.getString("id_pesanan"));
//
//                pengirimanList.add(pengiriman);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return pengirimanList;
//    }
}
