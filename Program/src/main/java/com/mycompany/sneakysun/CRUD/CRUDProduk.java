/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.CRUD;

import com.mycompany.sneakysun.BackEnd.Produk;
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
public class CRUDProduk {
    Connection connection;

    public CRUDProduk(){
        initConnection();
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
        
    public void createProduk(String nama, int harga, int stok, String idPenjual) {
        try {
            String insertQuery = "INSERT INTO Produk (nama, harga, stok, id_penjual) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, nama);
                preparedStatement.setInt(2, harga);
                preparedStatement.setInt(3, stok);
                preparedStatement.setString(4, idPenjual);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    public void updateProduk(String nama, int harga, int stok, String idPenjual, String idProduk) {
        try {
            String updateQuery = "UPDATE Produk SET nama = ?, harga = ?, stok = ?, id_penjual = ? WHERE id_produk = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, nama);
                preparedStatement.setInt(2, harga);
                preparedStatement.setInt(3, stok);
                preparedStatement.setString(4, idPenjual);
                preparedStatement.setString(5, idProduk);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduk(String idProduk) {
        try {
            String deleteTokoQuery = "DELETE FROM Toko WHERE id_produk = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteTokoQuery)) {
                preparedStatement.setString(1, idProduk);
                preparedStatement.executeUpdate();
            }
            String deleteProdukQuery = "DELETE FROM Produk WHERE id_produk = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteProdukQuery)) {
                preparedStatement.setString(1, idProduk);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    public ResultSet getProdukByPenjual(String idPenjual) {
        try {
            String selectQuery = "SELECT nama, harga, stok FROM Produk WHERE id_penjual = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, idPenjual);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ResultSet showProduk() {
        try {
            String selectQuery = "SELECT nama, harga, stok FROM Produk";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Produk getProductById(String idProduk) {
        String selectQuery = "SELECT nama, harga, stok, id_penjual FROM Produk WHERE id_produk = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, idProduk);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nama = resultSet.getString("nama");
                    int harga = resultSet.getInt("harga");
                    int stok = resultSet.getInt("stok");
                    String idPenjual = resultSet.getString("id_penjual");

                    return new Produk(idProduk, nama, harga, stok, idPenjual);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
//    public List<Produk> getAllProduk() {
//        List<Produk> produkList = new ArrayList<>();
//        try {
//            String selectQuery = "SELECT * FROM produk";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
//                 ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    Produk produk = new Produk();
//                    produk.setIdProduk(resultSet.getString("id_produk")
//                    produk.setNama(resultSet.getString("nama"));
//                    produk.setHarga(resultSet.getInt("harga"));
//                    produk.setStok(resultSet.getInt("stok"));
//                    produk.setIdPenjual(resultSet.getInt("id_penjual"));
//                    produkList.add(produk);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return produkList;
//    }
    
    public List<Produk> searchProduk(String keyword) {
        List<Produk> listProduk = new ArrayList<>();

        try {           
            String query = "SELECT * FROM Produk WHERE LOWER(nama) LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idProduk = resultSet.getString("id_produk");
                String nama = resultSet.getString("nama");
                int harga = resultSet.getInt("harga");
                int stok = resultSet.getInt("stok");
                String idPenjual = resultSet.getString("id_penjual");
                
                Produk produk = new Produk(idProduk, nama, harga, stok, idPenjual);
                listProduk.add(produk);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProduk;
    } 
}