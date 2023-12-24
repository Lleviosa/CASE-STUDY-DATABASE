/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.BackEnd;

/**
 *
 * @author Satriock
 */
public class Kurir {
    private String idKurir;
    private String username;
    private String passwordKurir;
    private String nama;
    
    public Kurir(String idKurir, String username, String passwordKurir, String nama) {
        this.idKurir = idKurir;
        this.username = username;
        this.passwordKurir = passwordKurir;
        this.nama = nama;
    }

    public String getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(String idKurir) {
        this.idKurir = idKurir;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordKurir() {
        return passwordKurir;
    }

    public void setPasswordKurir(String passwordKurir) {
        this.passwordKurir = passwordKurir;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
