/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sneakysun.GUI;

import com.mycompany.sneakysun.GUI.AdminWindow;
import com.mycompany.sneakysun.BackEnd.LoginResult;
import com.mycompany.sneakysun.Database.ConnectDatabase;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.mycompany.sneakysun.Database.ConnectDatabase;
import com.mycompany.sneakysun.Database.ConnectDatabase;
import com.mycompany.sneakysun.GUI.AdminWindow;
import com.mycompany.sneakysun.GUI.PembeliWindow;
import com.mycompany.sneakysun.GUI.PenjualWindow;
import com.mycompany.sneakysun.GUI.SignUpPembeliWindow;
import com.mycompany.sneakysun.GUI.SignUpPenjualWindow;
import com.mycompany.sneakysun.GUI.PembeliWindow;
import com.mycompany.sneakysun.GUI.PenjualWindow;
import com.mycompany.sneakysun.GUI.SignUpPembeliWindow;
import com.mycompany.sneakysun.GUI.SignUpPenjualWindow;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Satriock
 */
public class LoginWindow extends javax.swing.JFrame {
    private Connection connection;
    private String loggedInUsername;

    public LoginWindow() {
        initComponents();
        initConnection();
//        closeDatabaseConnection();
    }
    
    public void initConnection() {
        try {
            if (this.connection == null) {
                this.connection = ConnectDatabase.getConnection();
                System.out.println("Database connection initialized.");
            } else if (this.connection.isClosed()) {
            this.connection = ConnectDatabase.getConnection();
            System.out.println("Database connection reinitialized.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("Failed to opened database");
        }
    }
    
    public String getLoggedInUsername() {
        return loggedInUsername;
    }
    
    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
    }
    
//    public void closeDatabaseConnection() {
//        try {
//            if (this.connection != null && !this.connection.isClosed()) {
//                this.connection.close();
//                System.out.print("Database is closed");
//            }
//        } catch (SQLException e) {
//            System.out.print("Failed to closed database");
//            e.printStackTrace();
//        }
//    } 
    
    public LoginResult loginPage(String username, String password) {
        ConnectDatabase connectDatabase = new ConnectDatabase();

        try {
            String penjualSql = "SELECT id_penjual, username, password_penjual FROM Penjual WHERE username = ? AND password_penjual = ?";
            PreparedStatement penjualStatement = connection.prepareStatement(penjualSql);
            penjualStatement.setString(1, username);
            penjualStatement.setString(2, password);
            ResultSet penjualResultSet = penjualStatement.executeQuery();

            if (penjualResultSet.next()) {
                String idPenjual = penjualResultSet.getString("id_penjual");
                String usernamePenjual = penjualResultSet.getString("username");
                String storedPassword = penjualResultSet.getString("password_penjual");
                System.out.println("Logged in as penjual: " + usernamePenjual);
                LoginResult loginResult = new LoginResult(usernamePenjual, "penjual");
                return loginResult;
            }

            String pembeliSql = "SELECT id_pembeli, username, password_pembeli FROM Pembeli WHERE username = ? AND password_pembeli = ?";
            PreparedStatement pembeliStatement = connection.prepareStatement(pembeliSql);
            pembeliStatement.setString(1, username);
            pembeliStatement.setString(2, password);
            ResultSet pembeliResultSet = pembeliStatement.executeQuery();

            if (pembeliResultSet.next()) {
                String idPembeli = pembeliResultSet.getString("id_pembeli");
                String usernamePembeli = pembeliResultSet.getString("username");
                String storedPassword = pembeliResultSet.getString("password_pembeli");
                System.out.println("Logged in as pembeli: " + usernamePembeli);
                LoginResult loginResult = new LoginResult(usernamePembeli, "pembeli");
                return loginResult;
            }
            
            String kurirSql = "SELECT id_kurir, username, password_kurir FROM Kurir WHERE username = ? AND password_kurir = ?";
            PreparedStatement kurirStatement = connection.prepareStatement(kurirSql);
            kurirStatement.setString(1, username);
            kurirStatement.setString(2, password);
            ResultSet kurirResultSet = kurirStatement.executeQuery();

            if (kurirResultSet.next()) {
                String idKurir = kurirResultSet.getString("id_kurir");
                String usernameKurir = kurirResultSet.getString("username");
                String storedPassword = kurirResultSet.getString("password_kurir");
                System.out.println("Logged in as pembeli: " + usernameKurir);
                LoginResult loginResult = new LoginResult(usernameKurir, "kurir");
                return loginResult;
            }
            
            if (username.equals("admin") && password.equals("admin")) {
                System.out.println("Logged in as admin");
                return new LoginResult("admin", "admin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameField = new javax.swing.JTextField();
        loginMasuk = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        loginDaftarPembeli = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        loginDaftarPenjual = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        usernamePasswordSalahLabel = new javax.swing.JLabel();
        loginDaftarKurir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Log In");
        setBackground(new java.awt.Color(255, 255, 255));

        usernameField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        usernameField.setToolTipText("");
        usernameField.setName(""); // NOI18N
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        loginMasuk.setBackground(new java.awt.Color(255, 153, 153));
        loginMasuk.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        loginMasuk.setText("MASUK");
        loginMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginMasukActionPerformed(evt);
            }
        });

        jLabel3.setText("atau");

        loginDaftarPembeli.setBackground(new java.awt.Color(102, 255, 102));
        loginDaftarPembeli.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        loginDaftarPembeli.setText("DAFTAR SEBAGAI PEMBELI");
        loginDaftarPembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginDaftarPembeliActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Source Sans Pro", 1, 36)); // NOI18N
        jLabel1.setText("Welcome!");

        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Username");

        jLabel4.setText("Password");

        loginDaftarPenjual.setBackground(new java.awt.Color(102, 204, 255));
        loginDaftarPenjual.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        loginDaftarPenjual.setText("DAFTAR SEBAGAI PENJUAL");
        loginDaftarPenjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginDaftarPenjualActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 189, 167));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Snap ITC", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SneakySun");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        usernamePasswordSalahLabel.setForeground(new java.awt.Color(255, 51, 51));

        loginDaftarKurir.setBackground(new java.awt.Color(255, 255, 51));
        loginDaftarKurir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        loginDaftarKurir.setText("DAFTAR SEBAGAI KURIR");
        loginDaftarKurir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginDaftarKurirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1))
                    .addComponent(passwordField)
                    .addComponent(usernameField)
                    .addComponent(loginMasuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loginDaftarPembeli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(loginDaftarPenjual, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(usernamePasswordSalahLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loginDaftarKurir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(2, 2, 2)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernamePasswordSalahLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loginMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(8, 8, 8)
                .addComponent(loginDaftarPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginDaftarPenjual, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginDaftarKurir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        usernameField.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void loginDaftarPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginDaftarPembeliActionPerformed
        dispose();
        SignUpPembeliWindow signup = new SignUpPembeliWindow();
        signup.setVisible(true);
    }//GEN-LAST:event_loginDaftarPembeliActionPerformed

    private void loginMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginMasukActionPerformed
        try {
            String username = usernameField.getText();
            char[] userPassword = passwordField.getPassword();
            String password = new String(userPassword);

            LoginResult login = loginPage(username, password);
            System.out.print(login.getUsername());

            if (login != null) {
                String role = login.getRole();
                if (role.equals("pembeli")) {
                    PembeliWindow pembeli = new PembeliWindow();
                    UpdatePembeliWindow updatePembeli = new UpdatePembeliWindow();
                    LoginResult loginResult = new LoginResult(username, role);
                    pembeli.setLoggedInResult(login);
                    updatePembeli.setLoggedInResult(login);
                    pembeli.setVisible(true);
                    dispose();
                } else if (role.equals("penjual")) {
                    PenjualWindow penjual = new PenjualWindow();
                    UpdatePenjualWindow updatePenjual = new UpdatePenjualWindow();
                    LoginResult loginResult = new LoginResult(username, role);
                    penjual.setLoggedInResult(login);
                    updatePenjual.setLoggedInResult(login);
                    penjual.setVisible(true);
                    dispose();
                } else if (role.equals("kurir")) {
                    KurirWindow kurir = new KurirWindow();
                    UpdateKurirWindow updateKurir = new UpdateKurirWindow();
                    LoginResult loginResult = new LoginResult(username, role);
                    kurir.setLoggedInResult(login);
                    updateKurir.setLoggedInResult(login);
                    kurir.setVisible(true);
                    dispose();
                } else if (role.equals("admin")){
                    AdminWindow admin = new AdminWindow();
                    admin.setVisible(true);
                    dispose();
                }
            } else {
                System.out.println("Username atau password salah");
                usernamePasswordSalahLabel.setText("*username atau password salah");
            }
        } catch (Exception e) {
            System.out.println("Username atau password salah");
            usernamePasswordSalahLabel.setText("*username atau password salah");
        }
    }//GEN-LAST:event_loginMasukActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void loginDaftarPenjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginDaftarPenjualActionPerformed
        dispose();
        SignUpPenjualWindow signup = new SignUpPenjualWindow();
        signup.setVisible(true);
    }//GEN-LAST:event_loginDaftarPenjualActionPerformed

    private void loginDaftarKurirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginDaftarKurirActionPerformed
        dispose();
        SignUpKurirWindow signup = new SignUpKurirWindow();
        signup.setVisible(true);
    }//GEN-LAST:event_loginDaftarKurirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton loginDaftarKurir;
    private javax.swing.JButton loginDaftarPembeli;
    private javax.swing.JButton loginDaftarPenjual;
    private javax.swing.JButton loginMasuk;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernamePasswordSalahLabel;
    // End of variables declaration//GEN-END:variables
}
