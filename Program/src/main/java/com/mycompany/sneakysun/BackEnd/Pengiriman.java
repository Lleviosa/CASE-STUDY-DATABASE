/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.BackEnd;

/**
 *
 * @author Satriock
 */
public class Pengiriman {
    private String noResi;
    private String namaKurir;
    private String idPembeli;
    private String idPesanan;
    
    Pengiriman(String noResi, String namaKurir, String idPembeli, String idPesanan){
        this.noResi = noResi;
        this.namaKurir = namaKurir;
        this.idPembeli = idPembeli;
        this.idPesanan = idPesanan;
    }
    
    public String getNoResi() {
        return noResi;
    }

    public void setNoResi(String noResi) {
        this.noResi = noResi;
    }
    
    public String getNamaKurir() {
        return namaKurir;
    }

    public void setNamaKurir(String idnamaKurir) {
        this.namaKurir = namaKurir;
    }
    
    public String getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(String idPembeli) {
        this.idPembeli = idPembeli;
    }
    
    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanani(String idPesanan) {
        this.idPesanan = idPesanan;
    }
}
