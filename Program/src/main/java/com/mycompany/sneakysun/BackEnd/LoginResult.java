/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.sneakysun.BackEnd;

/**
 *
 * @author Satriock
 */
public class LoginResult {
    private String username;
    private String role;

    public LoginResult(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setRole(String role){
        this.role = role;
    }
}
