/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.BackEnd;

/**
 *
 * @author Satriock
 */
public class Pembayaran {
    private String idPembayaran;
    private int total;
    private String idPesanan;
    
    Pembayaran(String idPembayaran, int total, String idPesanan){
        this.idPembayaran = idPembayaran;
        this.total = total;
        this.idPesanan = idPesanan;
    }
    
    public String getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(String idPembayaran) {
        this.idPembayaran = idPembayaran;
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }
}
