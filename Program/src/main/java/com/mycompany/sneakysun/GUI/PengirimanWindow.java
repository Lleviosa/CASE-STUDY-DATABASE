/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sneakysun.GUI;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.sneakysun.BackEnd.LoginResult;
import com.mycompany.sneakysun.BackEnd.Pembeli;
import com.mycompany.sneakysun.CRUD.CRUDPengiriman;
import com.mycompany.sneakysun.Database.ConnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Satriock
 */
public class PengirimanWindow extends javax.swing.JFrame {
    private Connection connection;
    private LoginResult loggedInResult;
    private String usernamePembeli;
    private String role;

    public PengirimanWindow() {
        initComponents();
        initConnection();
        displayPembeliInfo();
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
//        idPembeliField.setText(getIdPembeli());
        System.out.println(loggedInResult.getUsername());
        System.out.println(getIdPembeli());
        loginPembeliField.setText(loggedInResult.getUsername());
        displayPembeliInfo();
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
    
    private String getIdPesanan() {
        if (loggedInResult != null) {
            String usernamePembeli = loggedInResult.getUsername();
            String selectQuery = "SELECT id_pesanan FROM Pesanan WHERE id_pembeli = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, getIdPembeli());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("id_pesanan");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    private void displayPembeliInfo() {
        String idPembeli = getIdPembeli();

        if (idPembeli != null) {
            String selectQuery = "SELECT username, nama, no_telp, kota, jalan, kode_pos FROM Pembeli WHERE id_pembeli = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, idPembeli);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String username = resultSet.getString("username");
                        String nama = resultSet.getString("nama");
                        String nomorTelepon = resultSet.getString("no_telp");
                        String kota = resultSet.getString("kota");
                        String alamat = resultSet.getString("jalan");
                        String kodePos = resultSet.getString("kode_pos");

                        // Set text field dengan nilai yang didapatkan
                        usernamePembeliField.setText(username);
                        namaPembeliField.setText(nama);
                        noTelpPembeliField.setText(nomorTelepon);
                        kotaPembeliField.setText(kota);
                        alamatPembeliField.setText(alamat);
                        kodePosPembeliField.setText(kodePos);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        loginPembeliField = new javax.swing.JTextField();
        namaPembeliField = new javax.swing.JTextField();
        kodePosPembeliField = new javax.swing.JTextField();
        usernamePembeliField = new javax.swing.JTextField();
        noTelpPembeliField = new javax.swing.JTextField();
        kotaPembeliField = new javax.swing.JTextField();
        alamatPembeliField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        kembaliPengirimanBtn = new javax.swing.JButton();
        lanjutPengirimanBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu Pengiriman");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Source Sans Pro", 1, 24)); // NOI18N
        jLabel12.setText("Konfirmasi Pengiriman");

        jPanel29.setBackground(new java.awt.Color(255, 189, 167));

        jPanel32.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );

        jPanel38.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 162, Short.MAX_VALUE)
        );

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));

        jLabel32.setFont(new java.awt.Font("Snap ITC", 1, 36)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("SneakySun");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel32)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(70, 70, 70))
        );

        jPanel39.setBackground(new java.awt.Color(199, 114, 61));

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );

        loginPembeliField.setEditable(false);
        loginPembeliField.setBackground(new java.awt.Color(255, 255, 255));
        loginPembeliField.setFont(new java.awt.Font("Swis721 Hv BT", 0, 12)); // NOI18N
        loginPembeliField.setForeground(new java.awt.Color(51, 51, 51));
        loginPembeliField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        loginPembeliField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        loginPembeliField.setCaretColor(new java.awt.Color(255, 255, 255));
        loginPembeliField.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        loginPembeliField.setSelectedTextColor(new java.awt.Color(255, 255, 51));
        loginPembeliField.setSelectionColor(new java.awt.Color(255, 255, 51));
        loginPembeliField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginPembeliFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(263, 263, 263)
                .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(498, 498, 498))
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(loginPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(loginPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(189, 189, 189)))
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 298, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel12)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        namaPembeliField.setEditable(false);

        kodePosPembeliField.setEditable(false);

        usernamePembeliField.setEditable(false);

        noTelpPembeliField.setEditable(false);

        kotaPembeliField.setEditable(false);

        alamatPembeliField.setEditable(false);

        jLabel1.setText("Username");

        jLabel2.setText("Nama");

        jLabel3.setText("Nomor Telepon");

        jLabel4.setText("Kota");

        jLabel5.setText("Alamat");

        jLabel6.setText("KodePos");

        kembaliPengirimanBtn.setBackground(new java.awt.Color(255, 153, 153));
        kembaliPengirimanBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kembaliPengirimanBtn.setText("KEMBALI");
        kembaliPengirimanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliPengirimanBtnActionPerformed(evt);
            }
        });

        lanjutPengirimanBtn.setBackground(new java.awt.Color(102, 255, 102));
        lanjutPengirimanBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lanjutPengirimanBtn.setText("LANJUT");
        lanjutPengirimanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lanjutPengirimanBtnActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel7.setText("*apabila ada kesalahan bisa mengubah di ubah profil");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(alamatPembeliField)
                    .addComponent(kotaPembeliField)
                    .addComponent(noTelpPembeliField)
                    .addComponent(namaPembeliField)
                    .addComponent(usernamePembeliField)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(kodePosPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(kembaliPengirimanBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lanjutPengirimanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernamePembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namaPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noTelpPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kotaPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alamatPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kodePosPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kembaliPengirimanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lanjutPengirimanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginPembeliFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginPembeliFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginPembeliFieldActionPerformed

    private void kembaliPengirimanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliPengirimanBtnActionPerformed
        PesananWindow pesanan = new PesananWindow();

        pesanan.setVisible(true);
        dispose();
    }//GEN-LAST:event_kembaliPengirimanBtnActionPerformed

    private void lanjutPengirimanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lanjutPengirimanBtnActionPerformed
        if (loggedInResult != null) {
            System.out.println(loggedInResult.getUsername() + " membuka menu pembayaran");
            String idPembeli = getIdPembeli();
            String idPesanan = getIdPesanan();
            CRUDPengiriman pengiriman = new CRUDPengiriman();
            pengiriman.createPengiriman(idPembeli, idPesanan);
            System.out.println(loggedInResult.getUsername() + " telah mengkonfirmasi pengiriman");
            
            PembayaranWindow pembayaran = new PembayaranWindow();
            pembayaran.setLoggedInResult(loggedInResult);
            pembayaran.setVisible(true);
            dispose();
        } else{
            System.out.println("tidak ada pembeli pada sesi ini");
        }
    }//GEN-LAST:event_lanjutPengirimanBtnActionPerformed

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
                new PengirimanWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamatPembeliField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JButton kembaliPengirimanBtn;
    private javax.swing.JTextField kodePosPembeliField;
    private javax.swing.JTextField kotaPembeliField;
    private javax.swing.JButton lanjutPengirimanBtn;
    private javax.swing.JTextField loginPembeliField;
    private javax.swing.JTextField namaPembeliField;
    private javax.swing.JTextField noTelpPembeliField;
    private javax.swing.JTextField usernamePembeliField;
    // End of variables declaration//GEN-END:variables
}
