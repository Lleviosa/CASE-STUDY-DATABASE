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
public class Pesanan {
    private String idPesanan;
    private String idPembeli;
    
    Pesanan(String idPesanan, String idPembeli){
        this.idPesanan = idPesanan;
        this.idPembeli = idPembeli;
    }
    
    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanani(String idPesanan) {
        this.idPesanan = idPesanan;
    }
    
    public String getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(String idPembeli) {
        this.idPembeli = idPembeli;
    }
}
