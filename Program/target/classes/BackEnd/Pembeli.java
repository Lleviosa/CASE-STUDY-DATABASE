/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.BackEnd;


public class Pembeli {
    private String idPembeli;
    private String username;
    private String passwordPembeli;
    private String nama;
    private String noTelp;
    private String kota;
    private String jalan;
    private String kodePos;

    public Pembeli(String idPembeli, String username, String passwordPembeli, String nama, String noTelp, String kota, 
            String jalan, String kodePos) {
        this.idPembeli = idPembeli;
        this.username = username;
        this.passwordPembeli = passwordPembeli;
        this.nama = nama;
        this.noTelp = noTelp;
        this.kota = kota;
        this.jalan = jalan;
        this.kodePos = kodePos;
    }

    public String getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(String idPembeli) {
        this.idPembeli = idPembeli;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordPembeli() {
        return passwordPembeli;
    }

    public void setPasswordPembeli(String passwordPembeli) {
        this.passwordPembeli = passwordPembeli;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getKodePos() {
       return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }
}
