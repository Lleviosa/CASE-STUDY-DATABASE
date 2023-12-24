/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sneakysun.GUI;

import com.mycompany.sneakysun.BackEnd.LoginResult;
import com.mycompany.sneakysun.BackEnd.Produk;
import com.mycompany.sneakysun.Database.ConnectDatabase;
import java.sql.Connection;
import java.sql.SQLException;
import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.sneakysun.BackEnd.Produk;
import com.mycompany.sneakysun.CRUD.CRUDPesanan;
import com.mycompany.sneakysun.CRUD.CRUDProduk;
import com.mycompany.sneakysun.Database.ConnectDatabase;
import com.mycompany.sneakysun.GUI.PesananWindow;
import com.mycompany.sneakysun.GUI.UpdatePembeliWindow;
import java.awt.Image;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Satriock
 */
public class PembeliWindow extends javax.swing.JFrame {
    private Connection connection;
    int currentRow = 0;
    private LoginResult loggedInResult;
    private String usernamePembeli;
    private String role;

    public PembeliWindow() {
        initComponents();
        initConnection();
        populatedAllRecords();
//        selectRow();
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
        loginPembeliField.setText(loggedInResult.getUsername());
        String idPembeli = getIdPembeli();
        buatIdPesanan(idPembeli);
    }
    
    public LoginResult getLoggedInResult() {
        return loggedInResult;
    }
    
    private void buatIdPesanan(String idPembeli) {
        if (idPembeli != null) {
            CRUDPesanan pesanan = new CRUDPesanan();
            pesanan.createPesanan(idPembeli);
            System.out.println("Mengambil keranjang belanja");
        } else {
            System.out.println("Tidak ada pembeli saat ini");
        }
    }
    
    private String getIdPembeli() {
        if (loggedInResult != null) {
            String usernamePembeli = loggedInResult.getUsername();
            String selectQuery = "SELECT id_pembeli FROM Pembeli WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, usernamePembeli);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String idPembeli = resultSet.getString("id_pembeli");
                        System.out.println("ID Pembeli: " + idPembeli);
                        return idPembeli;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
        
    private void populatedAllRecords() {
        DefaultTableModel model = (DefaultTableModel) searchProdukTable.getModel();
        model.setRowCount(0);
            
        try {
            String selectQuery = "SELECT nama, harga, stok FROM Produk";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nama = resultSet.getString("nama");
                int harga = resultSet.getInt("harga");
                int stok = resultSet.getInt("stok");

                model.addRow(new Object[]{nama, harga, stok});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void searchProduk(String keyword) {
        DefaultTableModel model = (DefaultTableModel) searchProdukTable.getModel();
        model.setRowCount(0);

        CRUDProduk cariProduk = new CRUDProduk();
        List<Produk> listProduk = cariProduk.searchProduk(keyword.toLowerCase());

        for (Produk produk : listProduk) {
            model.addRow(new Object[]{produk.getNama(), produk.getHarga(), produk.getStok()});
        }
    }
           
//   private void selectRow() {
//        catalogProdukTabel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    if (catalogProdukTabel.getSelectedRow() >= 0) {
//                        currentRow = catalogProdukTabel.getSelectedRow();
//                    }
//                    int selectedRow = catalogProdukTabel.getSelectedRow();
//                    if (selectedRow != -1) {
//                        DefaultTableModel model = (DefaultTableModel) catalogProdukTabel.getModel();
//                        String namaProduk= (String) model.getValueAt(selectedRow, 0);
//                        int hargaProduk = (int) model.getValueAt(selectedRow, 1);
//                        int stokProduk = (int) model.getValueAt(selectedRow, 2);
//
//                        setProdukFields(namaProduk,stokProduk,hargaProduk);
//                    }
//                }
//            }
//        });
//    }
//        
//    private void setProdukFields(String namaProduk, int jumlahProduk, int subTotal) {
//        showNamaProdukField.setText(namaProduk);
////        jumlahProdukField.setText(String.valueOf(jumlahProduk));
////        subTotalField.setText(String.valueOf(subTotal));
//    }
        
    private void openBeliWindowWithProduct(Produk produk) {
        CRUDPesanan crudPesanan = new CRUDPesanan();
        String idPesanan = crudPesanan.getPesananByPembeli(getIdPembeli());

        BeliWindow beli = new BeliWindow();
        beli.addProduct(produk);
        beli.setLoggedInResult(loggedInResult);
        beli.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addToCartTopiKC5 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        addToCartTopiKC39 = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        addToCartTopiKC40 = new javax.swing.JButton();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        addToCartTopiKC41 = new javax.swing.JButton();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        addToCartTopiKC42 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        addToCartTopiKC = new javax.swing.JButton();
        addToCartPower = new javax.swing.JButton();
        addToCartSepatuKike = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        ubahProfilPembeli = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        loginPembeliField = new javax.swing.JTextField();
        BayarPembeliBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        addToCartTopiKC2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        addToCartTopiKC3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        addToCartTopiKC4 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        addToCartTopiKC6 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        addToCartTopiKC7 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        addToCartTopiKC8 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        addToCartTopiKC9 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        addToCartTopiKC11 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        addToCartTopiKC12 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        addToCartTopiKC13 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        addToCartTopiKC14 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        addToCartTopiKC15 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        addToCartTopiKC16 = new javax.swing.JButton();
        addToCartTopiKC27 = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        addToCartTopiKC28 = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        addToCartTopiKC29 = new javax.swing.JButton();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        addToCartTopiKC17 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        addToCartTopiKC18 = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        addToCartTopiKC19 = new javax.swing.JButton();
        addToCartTopiKC30 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        addToCartTopiKC31 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        addToCartTopiKC32 = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        addToCartTopiKC33 = new javax.swing.JButton();
        addToCartTopiKC20 = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        addToCartTopiKC21 = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        addToCartTopiKC22 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        addToCartTopiKC23 = new javax.swing.JButton();
        addToCartTopiKC34 = new javax.swing.JButton();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        addToCartTopiKC35 = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        addToCartTopiKC36 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        addToCartTopiKC24 = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        addToCartTopiKC25 = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        addToCartTopiKC26 = new javax.swing.JButton();
        addToCartTopiKC37 = new javax.swing.JButton();
        jLabel66 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        addToCartTopiKC38 = new javax.swing.JButton();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        addToCartTopiKC43 = new javax.swing.JButton();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        addToCartTopiKC44 = new javax.swing.JButton();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        addToCartTopiKC45 = new javax.swing.JButton();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        addToCartTopiKC46 = new javax.swing.JButton();
        addToCartTopiKC1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        searchProdukTable = new javax.swing.JTable();
        searchProdukField = new javax.swing.JTextField();
        searchProdukBtn = new javax.swing.JButton();

        addToCartTopiKC5.setText("Beli");
        addToCartTopiKC5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC5ActionPerformed(evt);
            }
        });

        jLabel21.setText("Air Jordan 1 Zoom CMFT");

        jLabel22.setText("Air Jordan 1 Zoom CMFT");
        jLabel22.setPreferredSize(new java.awt.Dimension(125, 125));

        jLabel90.setText("Air Jordan 1 Zoom CMFT");

        jLabel91.setText("Air Jordan 1 Zoom CMFT");
        jLabel91.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC39.setText("Beli");

        jLabel92.setText("Air Jordan 1 Zoom CMFT");

        jLabel93.setText("Air Jordan 1 Zoom CMFT");
        jLabel93.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC40.setText("Beli");

        jLabel94.setText("Air Jordan 1 Zoom CMFT");

        jLabel95.setText("Air Jordan 1 Zoom CMFT");
        jLabel95.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC41.setText("Beli");

        jLabel96.setText("Air Jordan 1 Zoom CMFT");

        jLabel97.setText("Air Jordan 1 Zoom CMFT");
        jLabel97.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC42.setText("Beli");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Pembeli");

        jLabel2.setText("Topi KC Biru");

        jLabel4.setText("Sepatu Kike Hitam");
        jLabel4.setToolTipText("");

        jLabel5.setText("Topi KC Biru");

        jLabel6.setText("Power T-Shirt");

        jLabel7.setText("Sepatu Kike Hitam");

        addToCartTopiKC.setText("Beli");
        addToCartTopiKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKCActionPerformed(evt);
            }
        });

        addToCartPower.setText("Beli");
        addToCartPower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartPowerActionPerformed(evt);
            }
        });

        addToCartSepatuKike.setText("Beli");
        addToCartSepatuKike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartSepatuKikeActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Source Sans Pro", 1, 24)); // NOI18N
        jLabel12.setText("Produk Lain");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Source Sans Pro", 1, 24)); // NOI18N
        jLabel8.setText("Best Seller");

        jPanel40.setBackground(new java.awt.Color(231, 197, 80));

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(375, 375, 375))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jLabel18.setText("Power T-Shirt");
        jLabel18.setPreferredSize(new java.awt.Dimension(150, 150));

        jPanel29.setBackground(new java.awt.Color(255, 189, 167));

        jPanel30.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        jPanel31.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 162, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 365, Short.MAX_VALUE)
        );

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

        jPanel33.setBackground(new java.awt.Color(214, 161, 52));

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
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

        jPanel39.setBackground(new java.awt.Color(199, 114, 61));

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );

        ubahProfilPembeli.setText("Ubah Profil");
        ubahProfilPembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahProfilPembeliActionPerformed(evt);
            }
        });

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
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                        .addComponent(loginPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ubahProfilPembeli)
                        .addContainerGap())
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel32)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(40, 40, 40)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ubahProfilPembeli)
                    .addComponent(loginPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BayarPembeliBtn.setBackground(new java.awt.Color(102, 255, 102));
        BayarPembeliBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BayarPembeliBtn.setText("KONFIRMASI BAYAR");
        BayarPembeliBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BayarPembeliBtnActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 236, 220));

        addToCartTopiKC2.setText("Beli");
        addToCartTopiKC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC2ActionPerformed(evt);
            }
        });

        jLabel14.setText("Air Jordan 1 Zoom CMFT");

        jLabel15.setText("Air Jordan 1 Zoom CMFT");
        jLabel15.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC3.setText("Beli");
        addToCartTopiKC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC3ActionPerformed(evt);
            }
        });

        jLabel16.setText("Air Jordan 1 Zoom CMFT");

        jLabel17.setText("Air Jordan 1 Zoom CMFT");
        jLabel17.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC4.setText("Beli");
        addToCartTopiKC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC4ActionPerformed(evt);
            }
        });

        jLabel19.setText("Air Jordan 1 Zoom CMFT");

        jLabel20.setText("Air Jordan 1 Zoom CMFT");
        jLabel20.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC6.setText("Beli");
        addToCartTopiKC6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC6ActionPerformed(evt);
            }
        });

        jLabel23.setText("Air Jordan 1 Zoom CMFT");

        jLabel24.setText("Air Jordan 1 Zoom CMFT");
        jLabel24.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC7.setText("Beli");
        addToCartTopiKC7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC7ActionPerformed(evt);
            }
        });

        jLabel25.setText("Air Jordan 1 Zoom CMFT");

        jLabel26.setText("Air Jordan 1 Zoom CMFT");
        jLabel26.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC8.setText("Beli");
        addToCartTopiKC8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC8ActionPerformed(evt);
            }
        });

        jLabel27.setText("Air Jordan 1 Zoom CMFT");

        jLabel28.setText("Air Jordan 1 Zoom CMFT");
        jLabel28.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC9.setText("Beli");
        addToCartTopiKC9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC9ActionPerformed(evt);
            }
        });

        jLabel29.setText("Air Jordan 1 Zoom CMFT");

        jLabel30.setText("Air Jordan 1 Zoom CMFT");
        jLabel30.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC11.setText("Beli");
        addToCartTopiKC11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC11ActionPerformed(evt);
            }
        });

        jLabel34.setText("Air Jordan 1 Zoom CMFT");

        jLabel35.setText("Air Jordan 1 Zoom CMFT");
        jLabel35.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC12.setText("Beli");
        addToCartTopiKC12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC12ActionPerformed(evt);
            }
        });

        jLabel36.setText("Air Jordan 1 Zoom CMFT");

        jLabel37.setText("Air Jordan 1 Zoom CMFT");
        jLabel37.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC13.setText("Beli");
        addToCartTopiKC13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC13ActionPerformed(evt);
            }
        });

        jLabel38.setText("Air Jordan 1 Zoom CMFT");

        jLabel39.setText("Air Jordan 1 Zoom CMFT");
        jLabel39.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC14.setText("Beli");
        addToCartTopiKC14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC14ActionPerformed(evt);
            }
        });

        jLabel40.setText("Air Jordan 1 Zoom CMFT");

        jLabel41.setText("Air Jordan 1 Zoom CMFT");
        jLabel41.setPreferredSize(new java.awt.Dimension(125, 125));

        jLabel42.setText("Air Jordan 1 Zoom CMFT");

        jLabel43.setText("Air Jordan 1 Zoom CMFT");
        jLabel43.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC15.setText("Beli");
        addToCartTopiKC15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC15ActionPerformed(evt);
            }
        });

        jLabel44.setText("Air Jordan 1 Zoom CMFT");

        jLabel45.setText("Air Jordan 1 Zoom CMFT");
        jLabel45.setPreferredSize(new java.awt.Dimension(125, 125));

        jLabel67.setText("Air Jordan 1 Zoom CMFT");
        jLabel67.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC16.setText("Beli");
        addToCartTopiKC16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC16ActionPerformed(evt);
            }
        });

        addToCartTopiKC27.setText("Beli");
        addToCartTopiKC27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC27ActionPerformed(evt);
            }
        });

        jLabel46.setText("Air Jordan 1 Zoom CMFT");

        jLabel68.setText("Air Jordan 1 Zoom CMFT");

        jLabel69.setText("Air Jordan 1 Zoom CMFT");
        jLabel69.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC28.setText("Beli");
        addToCartTopiKC28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC28ActionPerformed(evt);
            }
        });

        jLabel70.setText("Air Jordan 1 Zoom CMFT");

        jLabel71.setText("Air Jordan 1 Zoom CMFT");
        jLabel71.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC29.setText("Beli");
        addToCartTopiKC29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC29ActionPerformed(evt);
            }
        });

        jLabel72.setText("Air Jordan 1 Zoom CMFT");

        jLabel73.setText("Air Jordan 1 Zoom CMFT");
        jLabel73.setPreferredSize(new java.awt.Dimension(125, 125));

        jLabel47.setText("Air Jordan 1 Zoom CMFT");
        jLabel47.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC17.setText("Beli");
        addToCartTopiKC17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC17ActionPerformed(evt);
            }
        });

        jLabel48.setText("Air Jordan 1 Zoom CMFT");

        jLabel49.setText("Air Jordan 1 Zoom CMFT");
        jLabel49.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC18.setText("Beli");
        addToCartTopiKC18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC18ActionPerformed(evt);
            }
        });

        jLabel50.setText("Air Jordan 1 Zoom CMFT");

        jLabel51.setText("Air Jordan 1 Zoom CMFT");
        jLabel51.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC19.setText("Beli");
        addToCartTopiKC19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC19ActionPerformed(evt);
            }
        });

        addToCartTopiKC30.setText("Beli");
        addToCartTopiKC30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC30ActionPerformed(evt);
            }
        });

        jLabel52.setText("Air Jordan 1 Zoom CMFT");

        jLabel74.setText("Air Jordan 1 Zoom CMFT");

        jLabel53.setText("Air Jordan 1 Zoom CMFT");
        jLabel53.setPreferredSize(new java.awt.Dimension(125, 125));

        jLabel75.setText("Air Jordan 1 Zoom CMFT");
        jLabel75.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC31.setText("Beli");
        addToCartTopiKC31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC31ActionPerformed(evt);
            }
        });

        jLabel76.setText("Air Jordan 1 Zoom CMFT");

        jLabel77.setText("Air Jordan 1 Zoom CMFT");
        jLabel77.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC32.setText("Beli");
        addToCartTopiKC32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC32ActionPerformed(evt);
            }
        });

        jLabel78.setText("Air Jordan 1 Zoom CMFT");

        jLabel79.setText("Air Jordan 1 Zoom CMFT");
        jLabel79.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC33.setText("Beli");
        addToCartTopiKC33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC33ActionPerformed(evt);
            }
        });

        addToCartTopiKC20.setText("Beli");
        addToCartTopiKC20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC20ActionPerformed(evt);
            }
        });

        jLabel54.setText("Air Jordan 1 Zoom CMFT");

        jLabel55.setText("Air Jordan 1 Zoom CMFT");
        jLabel55.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC21.setText("Beli");
        addToCartTopiKC21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC21ActionPerformed(evt);
            }
        });

        jLabel56.setText("Air Jordan 1 Zoom CMFT");

        jLabel57.setText("Air Jordan 1 Zoom CMFT");
        jLabel57.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC22.setText("Beli");
        addToCartTopiKC22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC22ActionPerformed(evt);
            }
        });

        jLabel58.setText("Air Jordan 1 Zoom CMFT");

        jLabel80.setText("Air Jordan 1 Zoom CMFT");

        jLabel59.setText("Air Jordan 1 Zoom CMFT");
        jLabel59.setPreferredSize(new java.awt.Dimension(125, 125));

        jLabel81.setText("Air Jordan 1 Zoom CMFT");
        jLabel81.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC23.setText("Beli");
        addToCartTopiKC23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC23ActionPerformed(evt);
            }
        });

        addToCartTopiKC34.setText("Beli");
        addToCartTopiKC34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC34ActionPerformed(evt);
            }
        });

        jLabel82.setText("Air Jordan 1 Zoom CMFT");

        jLabel83.setText("Air Jordan 1 Zoom CMFT");
        jLabel83.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC35.setText("Beli");
        addToCartTopiKC35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC35ActionPerformed(evt);
            }
        });

        jLabel84.setText("Air Jordan 1 Zoom CMFT");

        jLabel85.setText("Air Jordan 1 Zoom CMFT");
        jLabel85.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC36.setText("Beli");
        addToCartTopiKC36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC36ActionPerformed(evt);
            }
        });

        jLabel86.setText("Air Jordan 1 Zoom CMFT");

        jLabel60.setText("Air Jordan 1 Zoom CMFT");

        jLabel61.setText("Air Jordan 1 Zoom CMFT");
        jLabel61.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC24.setText("Beli");
        addToCartTopiKC24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC24ActionPerformed(evt);
            }
        });

        jLabel62.setText("Air Jordan 1 Zoom CMFT");

        jLabel63.setText("Air Jordan 1 Zoom CMFT");
        jLabel63.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC25.setText("Beli");
        addToCartTopiKC25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC25ActionPerformed(evt);
            }
        });

        jLabel64.setText("Air Jordan 1 Zoom CMFT");

        jLabel65.setText("Air Jordan 1 Zoom CMFT");
        jLabel65.setPreferredSize(new java.awt.Dimension(125, 125));

        jLabel87.setText("Air Jordan 1 Zoom CMFT");
        jLabel87.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC26.setText("Beli");
        addToCartTopiKC26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC26ActionPerformed(evt);
            }
        });

        addToCartTopiKC37.setText("Beli");
        addToCartTopiKC37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC37ActionPerformed(evt);
            }
        });

        jLabel66.setText("Air Jordan 1 Zoom CMFT");

        jLabel88.setText("Air Jordan 1 Zoom CMFT");

        jLabel89.setText("Air Jordan 1 Zoom CMFT");
        jLabel89.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC38.setText("Beli");
        addToCartTopiKC38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC38ActionPerformed(evt);
            }
        });

        jLabel98.setText("Air Jordan 1 Zoom CMFT");

        jLabel99.setText("Air Jordan 1 Zoom CMFT");
        jLabel99.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC43.setText("Beli");
        addToCartTopiKC43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC43ActionPerformed(evt);
            }
        });

        jLabel100.setText("Air Jordan 1 Zoom CMFT");

        jLabel101.setText("Air Jordan 1 Zoom CMFT");
        jLabel101.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC44.setText("Beli");
        addToCartTopiKC44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC44ActionPerformed(evt);
            }
        });

        jLabel102.setText("Air Jordan 1 Zoom CMFT");

        jLabel103.setText("Air Jordan 1 Zoom CMFT");
        jLabel103.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC45.setText("Beli");
        addToCartTopiKC45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC45ActionPerformed(evt);
            }
        });

        jLabel104.setText("Air Jordan 1 Zoom CMFT");

        jLabel105.setText("Air Jordan 1 Zoom CMFT");
        jLabel105.setPreferredSize(new java.awt.Dimension(125, 125));

        addToCartTopiKC46.setText("Beli");
        addToCartTopiKC46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC46ActionPerformed(evt);
            }
        });

        addToCartTopiKC1.setText("Beli");
        addToCartTopiKC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartTopiKC1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Air Jordan 1 Zoom CMFT");

        jLabel10.setText("Air Jordan 1 Zoom CMFT");
        jLabel10.setPreferredSize(new java.awt.Dimension(125, 125));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 856, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC17))
                                    .addComponent(jLabel48)))
                            .addGap(81, 81, 81)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC16))
                                    .addComponent(jLabel46)))
                            .addGap(73, 73, 73)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC15))
                                    .addComponent(jLabel44))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC21))
                                    .addComponent(jLabel56)))
                            .addGap(81, 81, 81)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC20))
                                    .addComponent(jLabel54)))
                            .addGap(73, 73, 73)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC19))
                                    .addComponent(jLabel52))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC25))
                                    .addComponent(jLabel64)))
                            .addGap(81, 81, 81)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC24))
                                    .addComponent(jLabel62)))
                            .addGap(73, 73, 73)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC23))
                                    .addComponent(jLabel60))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC29))
                                    .addComponent(jLabel72)))
                            .addGap(81, 81, 81)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC28))
                                    .addComponent(jLabel70)))
                            .addGap(73, 73, 73)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC27))
                                    .addComponent(jLabel68))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC33))
                                    .addComponent(jLabel80)))
                            .addGap(81, 81, 81)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC32))
                                    .addComponent(jLabel78)))
                            .addGap(73, 73, 73)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC31))
                                    .addComponent(jLabel76))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC37))
                                    .addComponent(jLabel88)))
                            .addGap(81, 81, 81)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC36))
                                    .addComponent(jLabel86)))
                            .addGap(73, 73, 73)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(addToCartTopiKC35))
                                    .addComponent(jLabel84))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(621, 621, 621)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(addToCartTopiKC18))
                                        .addComponent(jLabel42)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(addToCartTopiKC22))
                                        .addComponent(jLabel50)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(addToCartTopiKC26))
                                        .addComponent(jLabel58)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(addToCartTopiKC30))
                                        .addComponent(jLabel66)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(addToCartTopiKC34))
                                        .addComponent(jLabel74)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(addToCartTopiKC38))
                                        .addComponent(jLabel82)))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(29, 29, 29)
                                                    .addComponent(addToCartTopiKC1))
                                                .addComponent(jLabel1)))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(29, 29, 29)
                                                    .addComponent(addToCartTopiKC6))
                                                .addComponent(jLabel23))))
                                    .addGap(81, 81, 81)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(29, 29, 29)
                                                    .addComponent(addToCartTopiKC2))
                                                .addComponent(jLabel14)))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(29, 29, 29)
                                                    .addComponent(addToCartTopiKC7))
                                                .addComponent(jLabel25))))
                                    .addGap(73, 73, 73)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(29, 29, 29)
                                                    .addComponent(addToCartTopiKC3))
                                                .addComponent(jLabel16)))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(29, 29, 29)
                                                    .addComponent(addToCartTopiKC8))
                                                .addComponent(jLabel27)))))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(addToCartTopiKC13))
                                            .addComponent(jLabel38)))
                                    .addGap(81, 81, 81)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(addToCartTopiKC12))
                                            .addComponent(jLabel36)))
                                    .addGap(73, 73, 73)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(addToCartTopiKC11))
                                            .addComponent(jLabel34)))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(addToCartTopiKC4))
                                        .addComponent(jLabel19)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(addToCartTopiKC9))
                                        .addComponent(jLabel29)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(29, 29, 29)
                                                    .addComponent(addToCartTopiKC14))
                                                .addComponent(jLabel40)))))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(addToCartTopiKC45))
                                        .addComponent(jLabel104)))
                                .addGap(81, 81, 81)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(addToCartTopiKC44))
                                        .addComponent(jLabel102)))
                                .addGap(73, 73, 73)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(addToCartTopiKC43))
                                        .addComponent(jLabel100))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(621, 621, 621)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(addToCartTopiKC46))
                                        .addComponent(jLabel98))))))
                    .addGap(52, 52, 52)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2593, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC4))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC3))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC2))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC1)))
                    .addGap(62, 62, 62)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC6))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC7))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel27)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC8))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC9)))
                    .addGap(69, 69, 69)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel34)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC11))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel36)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC12))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel38)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC13))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel40)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC14)))
                    .addGap(80, 80, 80)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel44)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC15))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel46)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC16))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel48)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC17))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel42)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC18)))
                    .addGap(86, 86, 86)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel52)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC19))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel54)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC20))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel56)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC21))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel50)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC22)))
                    .addGap(101, 101, 101)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel60)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC23))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel62)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC24))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel64)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC25))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel58)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC26)))
                    .addGap(113, 113, 113)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel68)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC27))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel70)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC28))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel72)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC29))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel66)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC30)))
                    .addGap(109, 109, 109)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel76)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC31))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel78)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC32))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel80)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC33))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel74)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC34)))
                    .addGap(107, 107, 107)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel84)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC35))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel86)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC36))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel88)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC37))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel82)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC38)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel100)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC43))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel102)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC44))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel104)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC45))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel98)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartTopiKC46)))
                    .addContainerGap()))
        );

        jScrollPane1.setViewportView(jPanel2);

        searchProdukTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Produk", "Harga", "Stok"
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
        searchProdukTable.setSelectionBackground(new java.awt.Color(255, 153, 153));
        jScrollPane2.setViewportView(searchProdukTable);

        searchProdukBtn.setText("Cari");
        searchProdukBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchProdukBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addToCartTopiKC)
                                    .addComponent(jLabel5)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(addToCartSepatuKike))
                                    .addComponent(jLabel7))))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(addToCartPower)
                                            .addComponent(jLabel6))
                                        .addGap(59, 59, 59))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGap(39, 39, 39)))
                                .addComponent(jLabel3))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(493, 493, 493)
                                .addComponent(searchProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchProdukBtn)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(21, 21, 21))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(BayarPembeliBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(17, 17, 17))))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchProdukBtn))
                        .addGap(19, 19, 19)
                        .addComponent(BayarPembeliBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addToCartTopiKC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addToCartPower)))
                                .addGap(37, 37, 37)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addToCartSepatuKike)
                                .addGap(29, 29, 29)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addToCartTopiKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKCActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1024");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKCActionPerformed

    private void addToCartSepatuKikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartSepatuKikeActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1049");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartSepatuKikeActionPerformed

    private void ubahProfilPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahProfilPembeliActionPerformed
        if (loggedInResult != null) {
            System.out.println(loggedInResult.getUsername() + " membuka menu ubah profil");
            UpdatePembeliWindow update = new UpdatePembeliWindow();
            
            update.setLoggedInResult(loggedInResult);
            update.setVisible(true);
        } else{
            System.out.println("tidak ada pembeli pada sesi ini");
        }
    }//GEN-LAST:event_ubahProfilPembeliActionPerformed

    private void addToCartTopiKC5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC5ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC5ActionPerformed

    private void addToCartTopiKC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC2ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC2ActionPerformed

    private void addToCartTopiKC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC3ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC3ActionPerformed

    private void addToCartTopiKC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC4ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC4ActionPerformed

    private void addToCartTopiKC6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC6ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC6ActionPerformed

    private void addToCartTopiKC7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC7ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC7ActionPerformed

    private void addToCartTopiKC8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC8ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC8ActionPerformed

    private void addToCartTopiKC9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC9ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC9ActionPerformed

    private void addToCartTopiKC11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC11ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC11ActionPerformed

    private void addToCartTopiKC12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC12ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC12ActionPerformed

    private void addToCartTopiKC13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC13ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC13ActionPerformed

    private void addToCartTopiKC14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC14ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC14ActionPerformed

    private void addToCartTopiKC15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC15ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC15ActionPerformed

    private void addToCartTopiKC16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC16ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC16ActionPerformed

    private void addToCartTopiKC27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC27ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC27ActionPerformed

    private void addToCartTopiKC28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC28ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC28ActionPerformed

    private void addToCartTopiKC29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC29ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC29ActionPerformed

    private void addToCartTopiKC17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC17ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC17ActionPerformed

    private void addToCartTopiKC18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC18ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC18ActionPerformed

    private void addToCartTopiKC19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC19ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC19ActionPerformed

    private void addToCartTopiKC30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC30ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC30ActionPerformed

    private void addToCartTopiKC31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC31ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC31ActionPerformed

    private void addToCartTopiKC32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC32ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC32ActionPerformed

    private void addToCartTopiKC33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC33ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC33ActionPerformed

    private void addToCartTopiKC20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC20ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC20ActionPerformed

    private void addToCartTopiKC21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC21ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC21ActionPerformed

    private void addToCartTopiKC22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC22ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC22ActionPerformed

    private void addToCartTopiKC23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC23ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC23ActionPerformed

    private void addToCartTopiKC34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC34ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC34ActionPerformed

    private void addToCartTopiKC35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC35ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC35ActionPerformed

    private void addToCartTopiKC36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC36ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC36ActionPerformed

    private void addToCartTopiKC24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC24ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC24ActionPerformed

    private void addToCartTopiKC25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC25ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC25ActionPerformed

    private void addToCartTopiKC26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC26ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC26ActionPerformed

    private void addToCartTopiKC37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC37ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC37ActionPerformed

    private void addToCartTopiKC38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC38ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC38ActionPerformed

    private void addToCartTopiKC43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC43ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC43ActionPerformed

    private void addToCartTopiKC44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC44ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC44ActionPerformed

    private void addToCartTopiKC45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC45ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC45ActionPerformed

    private void addToCartTopiKC46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC46ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC46ActionPerformed

    private void addToCartTopiKC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartTopiKC1ActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1000");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartTopiKC1ActionPerformed

    private void loginPembeliFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginPembeliFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginPembeliFieldActionPerformed

    private void searchProdukBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchProdukBtnActionPerformed
        String cariProduk = searchProdukField.getText();

        searchProduk(cariProduk);
    }//GEN-LAST:event_searchProdukBtnActionPerformed

    private void BayarPembeliBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BayarPembeliBtnActionPerformed
        if (loggedInResult != null) {
            System.out.println(loggedInResult.getUsername() + " membuka menu pesanan");
            PesananWindow pesanan = new PesananWindow();
            
            pesanan.setLoggedInResult(loggedInResult);
            pesanan.setVisible(true);
        } else{
            System.out.println("tidak ada pembeli pada sesi ini");
        }
    }//GEN-LAST:event_BayarPembeliBtnActionPerformed

    private void addToCartPowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartPowerActionPerformed
        CRUDProduk crudProduk = new CRUDProduk();
        Produk produk = crudProduk.getProductById("P1124");
        openBeliWindowWithProduct(produk);
    }//GEN-LAST:event_addToCartPowerActionPerformed

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
                new PembeliWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BayarPembeliBtn;
    private javax.swing.JButton addToCartPower;
    private javax.swing.JButton addToCartSepatuKike;
    private javax.swing.JButton addToCartTopiKC;
    private javax.swing.JButton addToCartTopiKC1;
    private javax.swing.JButton addToCartTopiKC11;
    private javax.swing.JButton addToCartTopiKC12;
    private javax.swing.JButton addToCartTopiKC13;
    private javax.swing.JButton addToCartTopiKC14;
    private javax.swing.JButton addToCartTopiKC15;
    private javax.swing.JButton addToCartTopiKC16;
    private javax.swing.JButton addToCartTopiKC17;
    private javax.swing.JButton addToCartTopiKC18;
    private javax.swing.JButton addToCartTopiKC19;
    private javax.swing.JButton addToCartTopiKC2;
    private javax.swing.JButton addToCartTopiKC20;
    private javax.swing.JButton addToCartTopiKC21;
    private javax.swing.JButton addToCartTopiKC22;
    private javax.swing.JButton addToCartTopiKC23;
    private javax.swing.JButton addToCartTopiKC24;
    private javax.swing.JButton addToCartTopiKC25;
    private javax.swing.JButton addToCartTopiKC26;
    private javax.swing.JButton addToCartTopiKC27;
    private javax.swing.JButton addToCartTopiKC28;
    private javax.swing.JButton addToCartTopiKC29;
    private javax.swing.JButton addToCartTopiKC3;
    private javax.swing.JButton addToCartTopiKC30;
    private javax.swing.JButton addToCartTopiKC31;
    private javax.swing.JButton addToCartTopiKC32;
    private javax.swing.JButton addToCartTopiKC33;
    private javax.swing.JButton addToCartTopiKC34;
    private javax.swing.JButton addToCartTopiKC35;
    private javax.swing.JButton addToCartTopiKC36;
    private javax.swing.JButton addToCartTopiKC37;
    private javax.swing.JButton addToCartTopiKC38;
    private javax.swing.JButton addToCartTopiKC39;
    private javax.swing.JButton addToCartTopiKC4;
    private javax.swing.JButton addToCartTopiKC40;
    private javax.swing.JButton addToCartTopiKC41;
    private javax.swing.JButton addToCartTopiKC42;
    private javax.swing.JButton addToCartTopiKC43;
    private javax.swing.JButton addToCartTopiKC44;
    private javax.swing.JButton addToCartTopiKC45;
    private javax.swing.JButton addToCartTopiKC46;
    private javax.swing.JButton addToCartTopiKC5;
    private javax.swing.JButton addToCartTopiKC6;
    private javax.swing.JButton addToCartTopiKC7;
    private javax.swing.JButton addToCartTopiKC8;
    private javax.swing.JButton addToCartTopiKC9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField loginPembeliField;
    private javax.swing.JButton searchProdukBtn;
    private javax.swing.JTextField searchProdukField;
    private javax.swing.JTable searchProdukTable;
    private javax.swing.JButton ubahProfilPembeli;
    // End of variables declaration//GEN-END:variables
}
