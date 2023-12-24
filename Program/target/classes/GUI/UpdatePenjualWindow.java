/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sneakysun.GUI;

import com.mycompany.sneakysun.Database.ConnectDatabase;
import com.mycompany.sneakysun.CRUD.CRUDPenjual;
import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.sneakysun.BackEnd.LoginResult;
import com.mycompany.sneakysun.CRUD.CRUDPembeli;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class UpdatePenjualWindow extends javax.swing.JFrame {
    private Connection connection;
    private LoginResult loggedInResult;
    private String usernamePenjual;
    private String role;

    public UpdatePenjualWindow() {
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
        this.usernamePenjual = loggedInResult.getUsername();
        this.role = loggedInResult.getRole();
        idPenjualField.setText(getIdPenjual());
    }
    
    public LoginResult getLoggedInResult() {
        return loggedInResult;
    }
       
    private String getIdPenjual() {
        if (loggedInResult != null) {
            String usernamePenjual = loggedInResult.getUsername();
            String selectQuery = "SELECT id_penjual FROM Penjual WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, usernamePenjual);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("id_penjual");
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

        usernameField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ubahPenjualBtn = new javax.swing.JButton();
        simpanPenjualBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        namaField = new javax.swing.JTextField();
        kodePosField = new javax.swing.JTextField();
        usernameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        noTelpField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        kotaField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        alamatField = new javax.swing.JTextField();
        namaTokoField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        idPenjualField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        usernameField1.setEditable(false);
        usernameField1.setBackground(new java.awt.Color(255, 255, 255));
        usernameField1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        usernameField1.setToolTipText("");
        usernameField1.setName(""); // NOI18N
        usernameField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameField1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("(tidak dapat diubah)");

        jLabel12.setText("ID");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ubah Profil");

        ubahPenjualBtn.setBackground(new java.awt.Color(255, 153, 153));
        ubahPenjualBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ubahPenjualBtn.setText("UBAH");
        ubahPenjualBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahPenjualBtnActionPerformed(evt);
            }
        });

        simpanPenjualBtn.setBackground(new java.awt.Color(102, 255, 102));
        simpanPenjualBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        simpanPenjualBtn.setText("SIMPAN");
        simpanPenjualBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanPenjualBtnActionPerformed(evt);
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

        usernameField.setBackground(new java.awt.Color(255, 255, 255));
        usernameField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        usernameField.setToolTipText("");
        usernameField.setName(""); // NOI18N
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("Nama");

        jLabel5.setText("Nomor Telepon");

        noTelpField.setBackground(new java.awt.Color(255, 255, 255));
        noTelpField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        noTelpField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noTelpFieldActionPerformed(evt);
            }
        });

        jLabel6.setText("Kota");

        kotaField.setBackground(new java.awt.Color(255, 255, 255));
        kotaField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        kotaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kotaFieldActionPerformed(evt);
            }
        });

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

        namaTokoField.setBackground(new java.awt.Color(255, 255, 255));
        namaTokoField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        namaTokoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaTokoFieldActionPerformed(evt);
            }
        });

        jLabel9.setText("Nama Toko");

        idPenjualField.setEditable(false);
        idPenjualField.setBackground(new java.awt.Color(255, 255, 255));
        idPenjualField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        idPenjualField.setToolTipText("");
        idPenjualField.setName(""); // NOI18N
        idPenjualField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPenjualFieldActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("(tidak dapat diubah)");

        jLabel14.setText("ID");

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Source Sans Pro", 1, 36)); // NOI18N
        jLabel1.setText("Ubah Profil");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
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
                            .addComponent(namaTokoField, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ubahPenjualBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simpanPenjualBtn)
                        .addGap(15, 15, 15))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addGap(3, 3, 3)
                .addComponent(idPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namaTokoField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpanPenjualBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubahPenjualBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ubahPenjualBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahPenjualBtnActionPerformed
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        String userPassword = new String(password);
        String nama = namaField.getText();
        String noTelp = noTelpField.getText();
        String kota = kotaField.getText();
        String jalan = alamatField.getText();
        String kode_pos = kodePosField.getText();
        String namaToko = namaTokoField.getText();
        String idPenjual = getIdPenjual();

        if(idPenjual != null){
            CRUDPenjual penjual = new CRUDPenjual();
            penjual.updatePenjual(username, userPassword, nama, noTelp, kota, jalan, kode_pos, namaToko, idPenjual); 
        } else{
            System.out.println("ID Pembeli tidak ditemukan");
        }
    }//GEN-LAST:event_ubahPenjualBtnActionPerformed

    private void simpanPenjualBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanPenjualBtnActionPerformed
        dispose();
    }//GEN-LAST:event_simpanPenjualBtnActionPerformed

    private void namaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaFieldActionPerformed

    private void kodePosFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodePosFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodePosFieldActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void noTelpFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noTelpFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noTelpFieldActionPerformed

    private void kotaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kotaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kotaFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void alamatFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamatFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamatFieldActionPerformed

    private void namaTokoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaTokoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaTokoFieldActionPerformed

    private void usernameField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameField1ActionPerformed

    private void idPenjualFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPenjualFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPenjualFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdatePenjualWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamatField;
    private javax.swing.JTextField idPenjualField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField kodePosField;
    private javax.swing.JTextField kotaField;
    private javax.swing.JTextField namaField;
    private javax.swing.JTextField namaTokoField;
    private javax.swing.JTextField noTelpField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton simpanPenjualBtn;
    private javax.swing.JButton ubahPenjualBtn;
    private javax.swing.JTextField usernameField;
    private javax.swing.JTextField usernameField1;
    // End of variables declaration//GEN-END:variables
}
