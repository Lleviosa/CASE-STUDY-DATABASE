/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sneakysun.BackEnd;


public class Penjual {
    private String idPenjual;
    private String username;
    private String passwordPenjual;
    private String nama;
    private String noTelp;
    private String kota;
    private String jalan;
    private String kodePos;
    private String namaToko;

    public Penjual(String idPenjual, String username, String passwordPenjual, String nama, String noTelp, String kota, 
            String jalan, String kodePos, String namaToko) {
        this.idPenjual = idPenjual;
        this.username = username;
        this.passwordPenjual = passwordPenjual;
        this.nama = nama;
        this.noTelp = noTelp;
        this.kota = kota;
        this.jalan = jalan;
        this.kodePos = kodePos;
        this.namaToko = namaToko;
    }

    public String getIdPenjual() {
        return idPenjual;
    }

    public void setIdPenjual(String idPenjual) {
        this.idPenjual = idPenjual;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordPenjual() {
        return passwordPenjual;
    }

    public void setPasswordPenjual(String passwordPenjual) {
        this.passwordPenjual = passwordPenjual;
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

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    } 
}
