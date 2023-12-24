/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sneakysun.GUI;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.sneakysun.BackEnd.LoginResult;
import com.mycompany.sneakysun.BackEnd.Produk;
import com.mycompany.sneakysun.CRUD.CRUDProduk;
import com.mycompany.sneakysun.Database.ConnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class PesananWindow extends javax.swing.JFrame {
    private Connection connection;
    int currentRow = 0;
    private LoginResult loggedInResult;
    private String usernamePembeli;
    private String role;

    public PesananWindow() {
        initComponents();
        initConnection();
        populatedAllRecords();
        selectRow();
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
    
    public void setLoggedInResult(LoginResult loggedInResult) {
        this.loggedInResult = loggedInResult;
        this.usernamePembeli = loggedInResult.getUsername();
        this.role = loggedInResult.getRole();
//        idPembeliField.setText(getIdPembeli());
        System.out.println(loggedInResult.getUsername());
        System.out.println(getIdPembeli());
        populatedAllRecords();
        selectRow();
        loginPembeliField.setText(loggedInResult.getUsername());
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
    
//    private void populatedAllRecords() {
//        try {
//            DefaultTableModel model = (DefaultTableModel) pesananTable.getModel();
//            model.setRowCount(0);
//
//            if (loggedInResult != null) {
//                System.out.println("Logged in user: " + loggedInResult.getUsername());
//
//                String selectQuery = "SELECT dp.id_produk, dp.jumlah, p.nama, p.harga FROM Detail_Pesanan dp "
//                                    + "JOIN Produk p ON dp.id_produk = p.id_produk "
//                                    + "WHERE dp.id_pembeli = ?";
//
//                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
//                    preparedStatement.setString(1, usernamePembeli);
//
//                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                        while (resultSet.next()) {
//                            String nama = resultSet.getString("nama");
//                            int harga = resultSet.getInt("harga");
//                            int jumlah = resultSet.getInt("jumlah");
//
//                            model.addRow(new Object[]{nama, harga, jumlah});
//                        }
//                    }
//                }
//            } else {
//                System.out.println("\n loggedInResult is null. Login might have failed.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    
    private void populatedAllRecords() {
        try {
            DefaultTableModel model = (DefaultTableModel) pesananTable.getModel();
            model.setRowCount(0);

            if (loggedInResult != null) {
                System.out.println("Logged in user: " + loggedInResult.getUsername());

                String selectQuery = "SELECT dp.id_produk, dp.jumlah FROM Detail_Pesanan dp WHERE dp.id_pesanan IN (SELECT id_pesanan FROM Pesanan WHERE id_pembeli = ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    preparedStatement.setString(1, getIdPembeli());

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            String idProduk = resultSet.getString("id_produk");
                            int jumlah = resultSet.getInt("jumlah");

                            CRUDProduk crudProduk = new CRUDProduk();
                            Produk produk = crudProduk.getProductById(idProduk);

                            if (produk != null) {
                                model.addRow(new Object[]{produk.getNama(), produk.getHarga(), jumlah});
                            }
                        }
                    }
                }
            } else {
                System.out.println("\n loggedInResult is null. Login might have failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void selectRow() {
        pesananTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (pesananTable.getSelectedRow() >= 0) {
                        currentRow = pesananTable.getSelectedRow();
                    }
                    int selectedRow = pesananTable.getSelectedRow();
                    if (selectedRow != -1) {
                        DefaultTableModel model = (DefaultTableModel) pesananTable.getModel();
                        String nama= (String) model.getValueAt(selectedRow, 0);
                        int harga = (int) model.getValueAt(selectedRow, 1);
                        int jumlah = (int) model.getValueAt(selectedRow, 2);

                        setPesananFields(nama, harga, jumlah);
                    }
                }
            }
        });
    }
        
    private void setPesananFields(String produk, int harga, int jumlah) {
        produkPesananField.setText(produk);
        hargaPesananField.setText(String.valueOf(harga));
        jumlahPesananField.setText(String.valueOf(jumlah));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pesananTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        loginPembeliField = new javax.swing.JTextField();
        jPanel39 = new javax.swing.JPanel();
        hapusPesananWindow = new javax.swing.JButton();
        hargaPesananField = new javax.swing.JTextField();
        jumlahPesananField = new javax.swing.JTextField();
        produkPesananField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ubahPesananBtn = new javax.swing.JButton();
        kurangJmlProdukBtn = new javax.swing.JButton();
        tambahJmlProdukBtn = new javax.swing.JButton();
        subTotalHargaField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        totalHargaField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        kembaliPesananBtn = new javax.swing.JButton();
        lanjutPesananBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu Pesanan");

        pesananTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Produk", "Harga", "Jumlah"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pesananTable.setSelectionBackground(new java.awt.Color(255, 153, 153));
        jScrollPane1.setViewportView(pesananTable);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Source Sans Pro", 1, 24)); // NOI18N
        jLabel12.setText("Daftar Pesanan");

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

        jPanel34.setBackground(new java.awt.Color(214, 161, 52));

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        jPanel35.setBackground(new java.awt.Color(102, 51, 0));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        jPanel36.setBackground(new java.awt.Color(102, 51, 0));

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 117, Short.MAX_VALUE)
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        jPanel37.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                        .addComponent(loginPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(21, 21, 21))))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(47, 47, 47)
                .addComponent(loginPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(215, 215, 215))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel29Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(183, 183, 183)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 652, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(228, 228, 228))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        hapusPesananWindow.setText("Hapus");

        hargaPesananField.setEditable(false);

        jumlahPesananField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahPesananFieldActionPerformed(evt);
            }
        });

        produkPesananField.setEditable(false);
        produkPesananField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel1.setText("Produk");

        jLabel2.setText("Harga");

        jLabel3.setText("Jumlah");

        ubahPesananBtn.setText("Ubah");

        kurangJmlProdukBtn.setText("-");
        kurangJmlProdukBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kurangJmlProdukBtnActionPerformed(evt);
            }
        });

        tambahJmlProdukBtn.setText("+");
        tambahJmlProdukBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahJmlProdukBtnActionPerformed(evt);
            }
        });

        subTotalHargaField.setEditable(false);

        jLabel4.setText("Total");

        totalHargaField.setEditable(false);

        jLabel5.setText("SubTotal");

        kembaliPesananBtn.setBackground(new java.awt.Color(255, 153, 153));
        kembaliPesananBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kembaliPesananBtn.setText("KEMBALI");
        kembaliPesananBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliPesananBtnActionPerformed(evt);
            }
        });

        lanjutPesananBtn.setBackground(new java.awt.Color(102, 255, 102));
        lanjutPesananBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lanjutPesananBtn.setText("LANJUT");
        lanjutPesananBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lanjutPesananBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ubahPesananBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hapusPesananWindow))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jumlahPesananField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(kurangJmlProdukBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tambahJmlProdukBtn))
                    .addComponent(produkPesananField)
                    .addComponent(hargaPesananField)
                    .addComponent(subTotalHargaField)
                    .addComponent(totalHargaField))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(kembaliPesananBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lanjutPesananBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(produkPesananField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hargaPesananField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jumlahPesananField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kurangJmlProdukBtn)
                            .addComponent(tambahJmlProdukBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subTotalHargaField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ubahPesananBtn)
                            .addComponent(hapusPesananWindow))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalHargaField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kembaliPesananBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lanjutPesananBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
                    
    private void kembaliPesananBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliPesananBtnActionPerformed
            dispose();
    }//GEN-LAST:event_kembaliPesananBtnActionPerformed

    private void lanjutPesananBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lanjutPesananBtnActionPerformed
        if (loggedInResult != null) {
            System.out.println(loggedInResult.getUsername() + " membuka menu pengiriman");
            PengirimanWindow pengiriman = new PengirimanWindow();
            
            pengiriman.setLoggedInResult(loggedInResult);
            pengiriman.setVisible(true);
            dispose();
        } else{
            System.out.println("tidak ada pembeli pada sesi ini");
        }
    }//GEN-LAST:event_lanjutPesananBtnActionPerformed

    private void loginPembeliFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginPembeliFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginPembeliFieldActionPerformed

    private void jumlahPesananFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahPesananFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahPesananFieldActionPerformed

    private void tambahJmlProdukBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahJmlProdukBtnActionPerformed
        String nilaiTextField = jumlahPesananField.getText();

        if (!nilaiTextField.isEmpty()) {
            int jumlahSaatIni = Integer.parseInt(jumlahPesananField.getText());
            int jumlahBaru = jumlahSaatIni + 1;
            jumlahPesananField.setText(Integer.toString(jumlahBaru));
        } else {
            jumlahPesananField.setText("1");
        }
    }//GEN-LAST:event_tambahJmlProdukBtnActionPerformed

    private void kurangJmlProdukBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kurangJmlProdukBtnActionPerformed
        String nilaiTextField = jumlahPesananField.getText();

        if (!nilaiTextField.isEmpty()) {
            int jumlahSaatIni = Integer.parseInt(jumlahPesananField.getText());
            if (jumlahSaatIni > 0) {
                int jumlahBaru = jumlahSaatIni - 1;
                jumlahPesananField.setText(Integer.toString(jumlahBaru));
            }
        } else {
            jumlahPesananField.setText("0");
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
                new PesananWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hapusPesananWindow;
    private javax.swing.JTextField hargaPesananField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlahPesananField;
    private javax.swing.JButton kembaliPesananBtn;
    private javax.swing.JButton kurangJmlProdukBtn;
    private javax.swing.JButton lanjutPesananBtn;
    private javax.swing.JTextField loginPembeliField;
    private javax.swing.JTable pesananTable;
    private javax.swing.JTextField produkPesananField;
    private javax.swing.JTextField subTotalHargaField;
    private javax.swing.JButton tambahJmlProdukBtn;
    private javax.swing.JTextField totalHargaField;
    private javax.swing.JButton ubahPesananBtn;
    // End of variables declaration//GEN-END:variables
}
