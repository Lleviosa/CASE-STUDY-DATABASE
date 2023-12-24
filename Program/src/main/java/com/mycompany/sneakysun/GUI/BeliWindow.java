/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sneakysun.GUI;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.sneakysun.BackEnd.LoginResult;
import com.mycompany.sneakysun.BackEnd.Produk;
import com.mycompany.sneakysun.CRUD.CRUDDetailPesanan;
import com.mycompany.sneakysun.CRUD.CRUDPesanan;
import com.mycompany.sneakysun.Database.ConnectDatabase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Satriock
 */
public class BeliWindow extends javax.swing.JFrame {
    private Connection connection;
    private List<Produk> keranjangBelanja = new ArrayList<>();
    private LoginResult loggedInResult;
    private String usernamePembeli;
    private String role;

    public BeliWindow() {
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

    public void addProduct(Produk produk) {
        keranjangBelanja.add(produk);
        
        String namaProduk = produk.getNama();
        beliProdukField.setText(namaProduk);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        beliProdukField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        beliJumlahField = new javax.swing.JTextField();
        tambahJmlProdukBtn = new javax.swing.JButton();
        kurangJmlProdukBtn = new javax.swing.JButton();
        beliBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel3.setBackground(new java.awt.Color(153, 153, 153));
        jLabel3.setFont(new java.awt.Font("Source Sans Pro", 1, 28)); // NOI18N
        jLabel3.setText("Beli");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(111, 111, 111))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(19, 19, 19))
        );

        jLabel1.setText("Produk");

        beliProdukField.setEditable(false);
        beliProdukField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        beliProdukField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beliProdukFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Jumlah");

        tambahJmlProdukBtn.setText("+");
        tambahJmlProdukBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahJmlProdukBtnActionPerformed(evt);
            }
        });

        kurangJmlProdukBtn.setText("-");
        kurangJmlProdukBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kurangJmlProdukBtnActionPerformed(evt);
            }
        });

        beliBtn.setBackground(new java.awt.Color(102, 255, 102));
        beliBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        beliBtn.setText("BELI");
        beliBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beliBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(beliProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(beliBtn)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(beliJumlahField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(kurangJmlProdukBtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tambahJmlProdukBtn)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(beliProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(beliJumlahField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kurangJmlProdukBtn)
                    .addComponent(tambahJmlProdukBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(beliBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void beliBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beliBtnActionPerformed
        try {
            if (!keranjangBelanja.isEmpty()) {
                Produk produk = keranjangBelanja.get(0);
                String idPesanan = getIdPesanan();
                String idProduk = produk.getIdProduk();
                int jumlah = Integer.parseInt(beliJumlahField.getText());
                System.out.println(idPesanan);
                System.out.println(idProduk);

                CRUDDetailPesanan detailPesanan = new CRUDDetailPesanan();
                detailPesanan.createDetailPesanan(idPesanan, idProduk, jumlah);    
                System.out.println("Produk berhasil ditambahkan ke pesanan");
                dispose();
            } else {
                System.out.println("Keranjang belanja kosong.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_beliBtnActionPerformed

    private void beliProdukFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beliProdukFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_beliProdukFieldActionPerformed

    private void tambahJmlProdukBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahJmlProdukBtnActionPerformed
        String nilaiTextField = beliJumlahField.getText();

        if (!nilaiTextField.isEmpty()) {
            int jumlahSaatIni = Integer.parseInt(beliJumlahField.getText());
            int jumlahBaru = jumlahSaatIni + 1;
            beliJumlahField.setText(Integer.toString(jumlahBaru));
        } else {
            beliJumlahField.setText("1");
        }
    }//GEN-LAST:event_tambahJmlProdukBtnActionPerformed

    private void kurangJmlProdukBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kurangJmlProdukBtnActionPerformed
        String nilaiTextField = beliJumlahField.getText();

        if (!nilaiTextField.isEmpty()) {
            int jumlahSaatIni = Integer.parseInt(beliJumlahField.getText());
            if (jumlahSaatIni > 0) {
                int jumlahBaru = jumlahSaatIni - 1;
                beliJumlahField.setText(Integer.toString(jumlahBaru));
            }
        } else {
            beliJumlahField.setText("0");
        }
    }//GEN-LAST:event_kurangJmlProdukBtnActionPerformed

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
                new BeliWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton beliBtn;
    private javax.swing.JTextField beliJumlahField;
    private javax.swing.JTextField beliProdukField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton kurangJmlProdukBtn;
    private javax.swing.JButton tambahJmlProdukBtn;
    // End of variables declaration//GEN-END:variables
}
