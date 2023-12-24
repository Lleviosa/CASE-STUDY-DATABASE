/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sneakysun.GUI;

import com.mycompany.sneakysun.Database.ConnectDatabase;
import com.mycompany.sneakysun.CRUD.CRUDPembeli;
import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.sneakysun.BackEnd.LoginResult;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class UpdatePembeliWindow extends javax.swing.JFrame {
    private Connection connection;
    private LoginResult loggedInResult;
    private String usernamePembeli;
    private String role;

    public UpdatePembeliWindow() {
        initComponents();
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
    
    public void setLoggedInResult(LoginResult loggedInResult) {
        this.loggedInResult = loggedInResult;
        this.usernamePembeli = loggedInResult.getUsername();
        this.role = loggedInResult.getRole();
        idPembeliField.setText(getIdPembeli());
        System.out.println(loggedInResult.getUsername());
        System.out.println(getIdPembeli());
    }
    
    public LoginResult getLoggedInResult() {
        return loggedInResult;
    }
       
    private String getIdPembeli() {
        if (loggedInResult != null) {
            String usernamePembeli = loggedInResult.getUsername();
            String selectQuery = "SELECT id_pembeli FROM Pembeli WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, usernamePembeli);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("id_pembeli");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ubahPembeliBtn = new javax.swing.JButton();
        simpanPembeliBtn = new javax.swing.JButton();
        usernameField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        alamatField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        namaField = new javax.swing.JTextField();
        kodePosField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        noTelpField = new javax.swing.JTextField();
        kotaField = new javax.swing.JTextField();
        idPembeliField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ubah Profil");

        ubahPembeliBtn.setBackground(new java.awt.Color(255, 153, 153));
        ubahPembeliBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ubahPembeliBtn.setText("UBAH");
        ubahPembeliBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahPembeliBtnActionPerformed(evt);
            }
        });

        simpanPembeliBtn.setBackground(new java.awt.Color(102, 255, 102));
        simpanPembeliBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        simpanPembeliBtn.setText("SIMPAN");
        simpanPembeliBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanPembeliBtnActionPerformed(evt);
            }
        });

        usernameField.setBackground(new java.awt.Color(255, 255, 255));
        usernameField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        usernameField.setToolTipText("");
        usernameField.setName(""); // NOI18N
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        jLabel5.setText("Nomor Telepon");

        jLabel6.setText("Kota");

        jLabel7.setText("Alamat");

        jLabel8.setText("Kode Pos");

        jLabel2.setText("Username");

        passwordField.setBackground(new java.awt.Color(255, 255, 255));
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        alamatField.setBackground(new java.awt.Color(255, 255, 255));
        alamatField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        alamatField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamatFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Password");

        namaField.setBackground(new java.awt.Color(255, 255, 255));
        namaField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        namaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaFieldActionPerformed(evt);
            }
        });

        kodePosField.setBackground(new java.awt.Color(255, 255, 255));
        kodePosField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        kodePosField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodePosFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("Nama");

        noTelpField.setBackground(new java.awt.Color(255, 255, 255));
        noTelpField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        noTelpField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noTelpFieldActionPerformed(evt);
            }
        });

        kotaField.setBackground(new java.awt.Color(255, 255, 255));
        kotaField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        kotaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kotaFieldActionPerformed(evt);
            }
        });

        idPembeliField.setEditable(false);
        idPembeliField.setBackground(new java.awt.Color(255, 255, 255));
        idPembeliField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        idPembeliField.setToolTipText("");
        idPembeliField.setName(""); // NOI18N
        idPembeliField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPembeliFieldActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("(tidak dapat diubah)");

        jLabel11.setText("ID");

        jPanel6.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Source Sans Pro", 1, 36)); // NOI18N
        jLabel1.setText("Ubah Profil");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ubahPembeliBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(simpanPembeliBtn)
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(noTelpField)
                        .addComponent(usernameField)
                        .addComponent(namaField)
                        .addComponent(alamatField)
                        .addComponent(kodePosField)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kotaField)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(passwordField)
                        .addComponent(idPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addGap(3, 3, 3)
                .addComponent(idPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namaField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noTelpField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kotaField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alamatField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kodePosField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpanPembeliBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubahPembeliBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ubahPembeliBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahPembeliBtnActionPerformed
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        String userPassword = new String(password);
        String nama = namaField.getText();
        String noTelp = noTelpField.getText();
        String kota = kotaField.getText();
        String jalan = alamatField.getText();
        String kode_pos = kodePosField.getText();
        String idPembeli = getIdPembeli();

        if(idPembeli != null){
            CRUDPembeli pembeli = new CRUDPembeli();
            pembeli.updatePembeli(username, userPassword, nama, noTelp, kota, jalan, kode_pos, idPembeli);   
        } else{
            System.out.println("ID Pembeli tidak ditemukan");
        }
    }//GEN-LAST:event_ubahPembeliBtnActionPerformed

    private void simpanPembeliBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanPembeliBtnActionPerformed
        dispose();
    }//GEN-LAST:event_simpanPembeliBtnActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void alamatFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamatFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamatFieldActionPerformed

    private void namaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaFieldActionPerformed

    private void kodePosFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodePosFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodePosFieldActionPerformed

    private void noTelpFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noTelpFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noTelpFieldActionPerformed

    private void kotaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kotaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kotaFieldActionPerformed

    private void idPembeliFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPembeliFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPembeliFieldActionPerformed

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
                new UpdatePembeliWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamatField;
    private javax.swing.JTextField idPembeliField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField kodePosField;
    private javax.swing.JTextField kotaField;
    private javax.swing.JTextField namaField;
    private javax.swing.JTextField noTelpField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton simpanPembeliBtn;
    private javax.swing.JButton ubahPembeliBtn;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
