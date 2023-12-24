/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.BackEnd;

import java.sql.Date;

/**
 *
 * @author Satriock
 */
public class DetailPesanan {
    private String idPesanan;
    private String idProduk;
    private int jumlah;
    private int subtotal;
    
    DetailPesanan(String idPesanan, String idProduk, int jumlah, int subtotal) {
        this.idPesanan = idPesanan;
        this.idProduk = idProduk;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }
    
    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanani(String idPesanan) {
        this.idPesanan = idPesanan;
    }
    
    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }
    
    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}
