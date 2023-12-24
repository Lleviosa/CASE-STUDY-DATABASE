/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sneakysun.GUI;

import com.mycompany.sneakysun.CRUD.CRUDProduk;
import com.mycompany.sneakysun.CRUD.CRUDPenjual;
import com.mycompany.sneakysun.CRUD.CRUDPembeli;
import com.mycompany.sneakysun.BackEnd.Produk;
import com.mycompany.sneakysun.BackEnd.Penjual;
import com.mycompany.sneakysun.BackEnd.Pembeli;
import com.mycompany.sneakysun.Database.ConnectDatabase;
import java.sql.Connection;
import java.sql.SQLException;
import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.sneakysun.BackEnd.Kurir;
import com.mycompany.sneakysun.CRUD.CRUDKurir;
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
public class AdminWindow extends javax.swing.JFrame {
    private Connection connection;
    int currentRow = 0;

    public AdminWindow() {
        initComponents();
        initConnection();
        populatedPembeliRecords();
        populatedProdukRecords();
        populatedPenjualRecords();
        populatedPenjualProdukRecords();
        populatedKurirRecords();
        selectRowPembeli();
        selectRowProduk();
        selectRowPenjual();
        selectRowKurir();
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
     
    public void populatedPembeliRecords(){
        DefaultTableModel model = (DefaultTableModel) pembeliTable.getModel();
        model.setRowCount(0);
            
        try {
            String selectQuery = "SELECT * FROM Pembeli";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idPembeli = resultSet.getString("id_pembeli");
                String username = resultSet.getString("username");
                String userPassword = resultSet.getString("password_pembeli");
                String nama = resultSet.getString("nama");
                String noTelp = resultSet.getString("no_telp");
                String kota = resultSet.getString("kota");
                String jalan = resultSet.getString("jalan");
                String kodePos = resultSet.getString("kode_pos");

                model.addRow(new Object[]{idPembeli, username, userPassword, nama, noTelp, kota, jalan, kodePos});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void populatedProdukRecords(){
        DefaultTableModel model = (DefaultTableModel) produkTable.getModel();
        model.setRowCount(0);
            
        try {
            String selectQuery = "SELECT * FROM Produk";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idProduk = resultSet.getString("id_produk");
                String nama = resultSet.getString("nama");
                int harga = resultSet.getInt("harga");
                int stok = resultSet.getInt("stok");
                String idPenjual = resultSet.getString("id_penjual");

                model.addRow(new Object[]{idProduk, nama, harga, stok, idPenjual});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void populatedPenjualRecords(){
        DefaultTableModel model = (DefaultTableModel) penjualTable.getModel();
        model.setRowCount(0);
            
        try {
            String selectQuery = "SELECT * FROM Penjual";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idPenjual = resultSet.getString("id_penjual");
                String username = resultSet.getString("username");
                String userPassword = resultSet.getString("password_penjual");
                String nama = resultSet.getString("nama");
                String noTelp = resultSet.getString("no_telp");
                String kota = resultSet.getString("kota");
                String jalan = resultSet.getString("jalan");
                String kodePos = resultSet.getString("kode_pos");
                String namaToko = resultSet.getString("nama_toko");

                model.addRow(new Object[]{idPenjual, username, userPassword, nama, noTelp, kota, jalan, kodePos, namaToko});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void populatedPenjualProdukRecords(){
        DefaultTableModel model = (DefaultTableModel) cariPenjualProdukTable.getModel();
        model.setRowCount(0);
            
        try {
            String selectQuery = "SELECT id_penjual, username, nama_toko FROM Penjual";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idPenjual = resultSet.getString("id_penjual");
                String username = resultSet.getString("username");
                String namaToko = resultSet.getString("nama_toko");

                model.addRow(new Object[]{idPenjual, username, namaToko});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void populatedKurirRecords(){
        DefaultTableModel model = (DefaultTableModel) kurirTable.getModel();
        model.setRowCount(0);
            
        try {
            String selectQuery = "SELECT * FROM Kurir";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idKurir = resultSet.getString("id_kurir");
                String username = resultSet.getString("username");
                String userPassword = resultSet.getString("password_kurir");
                String nama = resultSet.getString("nama");

                model.addRow(new Object[]{idKurir, username, userPassword, nama});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void searchPembeli(String keyword) {
        DefaultTableModel model = (DefaultTableModel) pembeliTable.getModel();
        model.setRowCount(0);

        CRUDPembeli cariPembeli = new CRUDPembeli();
        List<Pembeli> listPembeli = cariPembeli.searchPembeli(keyword.toLowerCase());

        for (Pembeli pembeli : listPembeli) {
            model.addRow(new Object[]{pembeli.getIdPembeli(), pembeli.getUsername(), pembeli.getPasswordPembeli(), pembeli.getNama(), 
                pembeli.getNoTelp(), pembeli.getKota(), pembeli.getJalan(), pembeli.getKodePos()});
        }
    }
    
    private void searchProduk(String keyword) {
        DefaultTableModel model = (DefaultTableModel) produkTable.getModel();
        model.setRowCount(0);

        CRUDProduk cariProduk = new CRUDProduk();
        List<Produk> listProduk = cariProduk.searchProduk(keyword.toLowerCase());

        for (Produk produk : listProduk) {
            model.addRow(new Object[]{produk.getIdProduk(), produk.getNama(), produk.getHarga(), produk.getStok(), produk.getIdPenjual()});
        }
    }
    
    private void searchPenjualProduk(String keyword) {
        DefaultTableModel model = (DefaultTableModel) cariPenjualProdukTable.getModel();
        model.setRowCount(0);

        CRUDPenjual cariPenjual = new CRUDPenjual();
        List<Penjual> listPenjual = cariPenjual.searchPenjual(keyword.toLowerCase());

        for (Penjual penjual : listPenjual) {
            model.addRow(new Object[]{penjual.getIdPenjual(), penjual.getUsername(), penjual.getNamaToko()});
        }
    }
    
    private void searchPenjual(String keyword) {
        DefaultTableModel model = (DefaultTableModel) penjualTable.getModel();
        model.setRowCount(0);

        CRUDPenjual cariPenjual = new CRUDPenjual();
        List<Penjual> listPenjual = cariPenjual.searchPenjual(keyword.toLowerCase());

        for (Penjual penjual : listPenjual) {
            model.addRow(new Object[]{penjual.getIdPenjual(), penjual.getUsername(), penjual.getPasswordPenjual(), penjual.getNama(), 
                penjual.getNoTelp(), penjual.getKota(), penjual.getJalan(), penjual.getKodePos(), penjual.getNamaToko()});
        }
    }
    
    private void searchKurir(String keyword) {
        DefaultTableModel model = (DefaultTableModel) kurirTable.getModel();
        model.setRowCount(0);

        CRUDKurir cariKurir = new CRUDKurir();
        List<Kurir> listKurir = cariKurir.searchKurir(keyword.toLowerCase());

        for (Kurir kurir : listKurir) {
            model.addRow(new Object[]{kurir.getIdKurir(), kurir.getUsername(), kurir.getPasswordKurir(), kurir.getNama()});
        }
    }
    
    private void setPembeliFields(String idPembeli, String username, String userPassword, String nama, String noTelp, String kota, String jalan, String kodePos) {
        idPembeliField.setText(idPembeli);
        usernamePembeliField.setText(username);
        passwordPembeliField.setText(userPassword);
        namaPembeliField.setText(nama);
        noTelpPembeliField.setText(noTelp);
        kotaPembeliField.setText(kota);
        jalanPembeliField.setText(jalan);
        kodePosPembeliField.setText(kodePos);
    }
    
    private void setPenjualFields(String idPenjual, String username, String userPassword, String nama, String noTelp, String kota, String jalan, String kodePos, String namaToko) {
        idPenjualField.setText(idPenjual);
        usernamePenjualField.setText(username);
        passwordPenjualField.setText(userPassword);
        namaPenjualField.setText(nama);
        noTelpPenjualField.setText(noTelp);
        kotaPenjualField.setText(kota);
        jalanPenjualField.setText(jalan);
        kodePosPenjualField.setText(kodePos);
        namaTokoField.setText(namaToko);
    }
    
    private void setProdukFields(String idProduk, String namaProduk, int harga, int stok, String idPenjual) {
        idProdukField.setText(idProduk);
        namaProdukField.setText(namaProduk);
        hargaProdukField.setText(String.valueOf(harga));
        stokProdukField.setText(String.valueOf(stok));
        idPenjualProdukField.setText(idPenjual);
    }
    
    private void setKurirFields(String idKurir, String username, String userPassword, String nama) {
        idKurirField.setText(idKurir);
        usernameKurirField.setText(username);
        passwordKurirField.setText(userPassword);
        namaKurirField.setText(nama);
    } 
    
    private void selectRowPembeli() {
        pembeliTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (pembeliTable.getSelectedRow() >= 0) {
                        currentRow = pembeliTable.getSelectedRow();
                    }
                    int selectedRow = pembeliTable.getSelectedRow();
                    if (selectedRow != -1) {
                        DefaultTableModel model = (DefaultTableModel) pembeliTable.getModel();
                        String idPembeli = (String) model.getValueAt(selectedRow, 0);
                        String username = (String) model.getValueAt(selectedRow, 1);
                        String userPassword = (String) model.getValueAt(selectedRow, 2);
                        String nama = (String) model.getValueAt(selectedRow, 3);
                        String noTelp = (String) model.getValueAt(selectedRow, 4);
                        String kota = (String) model.getValueAt(selectedRow, 5);
                        String jalan = (String) model.getValueAt(selectedRow, 6);
                        String kodePos = (String) model.getValueAt(selectedRow, 7);

                        setPembeliFields(idPembeli, username, userPassword, nama, noTelp, kota, jalan, kodePos);
                    }
                }
            }
        });
    }
   
    private void selectRowProduk() {
        produkTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (produkTable.getSelectedRow() >= 0) {
                        currentRow = produkTable.getSelectedRow();
                    }
                    int selectedRow = produkTable.getSelectedRow();
                    if (selectedRow != -1) {
                        DefaultTableModel model = (DefaultTableModel) produkTable.getModel();
                        String idProduk = (String) model.getValueAt(selectedRow, 0);
                        String nama = (String) model.getValueAt(selectedRow, 1);
                        int harga = (int) model.getValueAt(selectedRow, 2);
                        int stok = (int) model.getValueAt(selectedRow, 3);
                        String idPenjual = (String) model.getValueAt(selectedRow, 4);

                        setProdukFields(idProduk, nama, harga, stok, idPenjual);
                    }
                }
            }
        });
    }
      
    private void selectRowPenjual() {
        penjualTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (penjualTable.getSelectedRow() >= 0) {
                        currentRow = penjualTable.getSelectedRow();
                    }
                    int selectedRow = penjualTable.getSelectedRow();
                    if (selectedRow != -1) {
                        DefaultTableModel model = (DefaultTableModel) penjualTable.getModel();
                        String idPenjual = (String) model.getValueAt(selectedRow, 0);
                        String username = (String) model.getValueAt(selectedRow, 1);
                        String userPassword = (String) model.getValueAt(selectedRow, 2);
                        String nama = (String) model.getValueAt(selectedRow, 3);
                        String noTelp = (String) model.getValueAt(selectedRow, 4);
                        String kota = (String) model.getValueAt(selectedRow, 5);
                        String jalan = (String) model.getValueAt(selectedRow, 6);
                        String kodePos = (String) model.getValueAt(selectedRow, 7);
                        String namaToko = (String) model.getValueAt(selectedRow, 8);

                        setPenjualFields(idPenjual, username, userPassword, nama, noTelp, kota, jalan, kodePos, namaToko);
                    }
                }
            }
        });
    }
    
    private void selectRowKurir() {
        kurirTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (kurirTable.getSelectedRow() >= 0) {
                        currentRow = kurirTable.getSelectedRow();
                    }
                    int selectedRow = kurirTable.getSelectedRow();
                    if (selectedRow != -1) {
                        DefaultTableModel model = (DefaultTableModel) kurirTable.getModel();
                        String idKurir = (String) model.getValueAt(selectedRow, 0);
                        String username = (String) model.getValueAt(selectedRow, 1);
                        String userPassword = (String) model.getValueAt(selectedRow, 2);
                        String nama = (String) model.getValueAt(selectedRow, 3);

                        setKurirFields(idKurir, username, userPassword, nama);
                    }
                }
            }
        });
    }
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pembeliTable = new javax.swing.JTable();
        namaPembeliField = new javax.swing.JTextField();
        hapusPembeliBtn = new javax.swing.JButton();
        simpanPembeliBtn = new javax.swing.JButton();
        tambahPembeliBtn = new javax.swing.JButton();
        ubahPembeliBtn = new javax.swing.JButton();
        idPembeliField = new javax.swing.JTextField();
        usernamePembeliField = new javax.swing.JTextField();
        kodePosPembeliField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jalanPembeliField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        noTelpPembeliField = new javax.swing.JTextField();
        kotaPembeliField = new javax.swing.JTextField();
        passwordPembeliField = new javax.swing.JPasswordField();
        cariPembeliField = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        cariPembeliBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        produkTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        cariPenjualProdukTable = new javax.swing.JTable();
        tambahProdukBtn = new javax.swing.JButton();
        ubahProdukBtn = new javax.swing.JButton();
        hargaProdukField = new javax.swing.JTextField();
        namaProdukField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        stokProdukField = new javax.swing.JTextField();
        hapusProdukBtn = new javax.swing.JButton();
        tambahJmlProdukBtn = new javax.swing.JButton();
        simpanProdukBtn = new javax.swing.JButton();
        kurangJmlProdukBtn = new javax.swing.JButton();
        idPenjualProdukField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cariPenjualProdukField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cariPenjualProdukBtn = new javax.swing.JButton();
        idProdukField = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        cariProdukField = new javax.swing.JTextField();
        cariProdukBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        kodePosPenjualField = new javax.swing.JTextField();
        namaPenjualField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        hapusPenjualBtn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        simpanPenjualBtn = new javax.swing.JButton();
        jalanPenjualField = new javax.swing.JTextField();
        tambahPenjualBtn = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        noTelpPenjualField = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        ubahPenjualBtn = new javax.swing.JButton();
        kotaPenjualField = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        idPenjualField = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        penjualTable = new javax.swing.JTable();
        usernamePenjualField = new javax.swing.JTextField();
        namaTokoField = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        passwordPenjualField = new javax.swing.JPasswordField();
        cariPenjualField = new javax.swing.JTextField();
        cariPenjualBtn = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        kurirTable = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        cariKurirField = new javax.swing.JTextField();
        cariKurirBtn = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        namaKurirField = new javax.swing.JTextField();
        passwordKurirField = new javax.swing.JPasswordField();
        jLabel35 = new javax.swing.JLabel();
        idKurirField = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        usernameKurirField = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        hapusKurirBtn = new javax.swing.JButton();
        simpanKurirBtn = new javax.swing.JButton();
        tambahKurirBtn = new javax.swing.JButton();
        ubahKurirBtn = new javax.swing.JButton();
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
        jLabel32 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Admin");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Source Sans Pro", 1, 24)); // NOI18N
        jLabel12.setText("ADMIN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(jLabel11)
                .addGap(315, 315, 315)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel11)
                .addContainerGap(21, Short.MAX_VALUE))
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jLabel6.setText("ID Pembeli");

        jLabel7.setText("Username");

        jLabel17.setText("Nama");

        jLabel8.setText("Password");

        pembeliTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pembeli", "Username", "Password", "Nama", "No Telp", "Kota", "Jalan", "Kode Pos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pembeliTable.setSelectionBackground(new java.awt.Color(255, 153, 153));
        jScrollPane3.setViewportView(pembeliTable);

        hapusPembeliBtn.setText("Hapus");
        hapusPembeliBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusPembeliBtnActionPerformed(evt);
            }
        });

        simpanPembeliBtn.setText("Simpan");
        simpanPembeliBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanPembeliBtnActionPerformed(evt);
            }
        });

        tambahPembeliBtn.setText("Tambah");
        tambahPembeliBtn.setToolTipText("");
        tambahPembeliBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahPembeliBtnActionPerformed(evt);
            }
        });

        ubahPembeliBtn.setText("Ubah");
        ubahPembeliBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahPembeliBtnActionPerformed(evt);
            }
        });

        idPembeliField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPembeliFieldActionPerformed(evt);
            }
        });

        usernamePembeliField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernamePembeliFieldActionPerformed(evt);
            }
        });

        jLabel10.setText("No Telp");

        jLabel13.setText("Kota");

        jLabel18.setText("Kode Pos");

        jalanPembeliField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel14.setText("Jalan");

        noTelpPembeliField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noTelpPembeliFieldActionPerformed(evt);
            }
        });

        kotaPembeliField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kotaPembeliFieldActionPerformed(evt);
            }
        });

        cariPembeliField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariPembeliFieldActionPerformed(evt);
            }
        });

        jLabel27.setText("Cari Pembeli");

        cariPembeliBtn.setText("Cari");
        cariPembeliBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariPembeliBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariPembeliBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(tambahPembeliBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ubahPembeliBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hapusPembeliBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(simpanPembeliBtn)
                        .addGap(7, 7, 7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(kotaPembeliField)
                                    .addComponent(jalanPembeliField)
                                    .addComponent(kodePosPembeliField)
                                    .addComponent(noTelpPembeliField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel8))
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(usernamePembeliField)
                                    .addComponent(idPembeliField)
                                    .addComponent(namaPembeliField)
                                    .addComponent(passwordPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cariPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(cariPembeliBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernamePembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namaPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noTelpPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kotaPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jalanPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kodePosPembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tambahPembeliBtn)
                            .addComponent(ubahPembeliBtn)
                            .addComponent(hapusPembeliBtn)
                            .addComponent(simpanPembeliBtn)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pembeli", jPanel3);

        produkTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Produk", "Nama Produk", "Harga", "Stok", "ID Penjual"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        produkTable.setSelectionBackground(new java.awt.Color(255, 153, 153));
        jScrollPane1.setViewportView(produkTable);

        cariPenjualProdukTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Penjual", "Username", "Nama Toko"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        cariPenjualProdukTable.setSelectionBackground(new java.awt.Color(255, 153, 153));
        jScrollPane2.setViewportView(cariPenjualProdukTable);

        tambahProdukBtn.setText("Tambah");
        tambahProdukBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahProdukBtnActionPerformed(evt);
            }
        });

        ubahProdukBtn.setText("Ubah");
        ubahProdukBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahProdukBtnActionPerformed(evt);
            }
        });

        namaProdukField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        namaProdukField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaProdukFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Nama Produk");

        jLabel16.setText("Stok");

        jLabel4.setText("Harga");

        hapusProdukBtn.setText("Hapus");
        hapusProdukBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusProdukBtnActionPerformed(evt);
            }
        });

        tambahJmlProdukBtn.setText("+");
        tambahJmlProdukBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahJmlProdukBtnActionPerformed(evt);
            }
        });

        simpanProdukBtn.setText("Simpan");
        simpanProdukBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanProdukBtnActionPerformed(evt);
            }
        });

        kurangJmlProdukBtn.setText("-");
        kurangJmlProdukBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kurangJmlProdukBtnActionPerformed(evt);
            }
        });

        idPenjualProdukField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPenjualProdukFieldActionPerformed(evt);
            }
        });

        jLabel5.setText("ID Penjual");

        cariPenjualProdukField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariPenjualProdukFieldActionPerformed(evt);
            }
        });

        jLabel19.setText("Cari Penjual");

        cariPenjualProdukBtn.setText("Cari");
        cariPenjualProdukBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariPenjualProdukBtnActionPerformed(evt);
            }
        });

        idProdukField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idProdukFieldActionPerformed(evt);
            }
        });

        jLabel26.setText("ID Produk");

        jLabel30.setText("Cari Produk");

        cariProdukField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariProdukFieldActionPerformed(evt);
            }
        });

        cariProdukBtn.setText("Cari");
        cariProdukBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariProdukBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cariPenjualProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariPenjualProdukBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cariProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cariProdukBtn)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(34, 34, 34)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hargaProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namaProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tambahProdukBtn)
                            .addComponent(idPenjualProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(stokProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(kurangJmlProdukBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tambahJmlProdukBtn))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(ubahProdukBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hapusProdukBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(simpanProdukBtn))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(258, 258, 258)))
                .addGap(114, 114, 114))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(cariProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariProdukBtn))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cariPenjualProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(cariPenjualProdukBtn)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idPenjualProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namaProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hargaProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stokProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(kurangJmlProdukBtn)
                            .addComponent(tambahJmlProdukBtn))
                        .addGap(18, 18, 18)
                        .addComponent(tambahProdukBtn)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idProdukField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ubahProdukBtn)
                            .addComponent(hapusProdukBtn)
                            .addComponent(simpanProdukBtn))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Produk", jPanel4);

        jLabel20.setText("No Telp");

        hapusPenjualBtn.setText("Hapus");
        hapusPenjualBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusPenjualBtnActionPerformed(evt);
            }
        });

        jLabel15.setText("Kota");

        jLabel21.setText("Kode Pos");

        simpanPenjualBtn.setText("Simpan");
        simpanPenjualBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanPenjualBtnActionPerformed(evt);
            }
        });

        jalanPenjualField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        tambahPenjualBtn.setText("Tambah");
        tambahPenjualBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahPenjualBtnActionPerformed(evt);
            }
        });

        jLabel22.setText("Jalan");

        jLabel23.setText("ID Penjual");

        noTelpPenjualField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noTelpPenjualFieldActionPerformed(evt);
            }
        });

        jLabel24.setText("Username");

        ubahPenjualBtn.setText("Ubah");
        ubahPenjualBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahPenjualBtnActionPerformed(evt);
            }
        });

        kotaPenjualField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kotaPenjualFieldActionPerformed(evt);
            }
        });

        jLabel25.setText("Nama");

        jLabel28.setText("Password");

        idPenjualField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPenjualFieldActionPerformed(evt);
            }
        });

        penjualTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Penjual", "Username", "Password", "Nama", "No Telp", "Kota", "Jalan", "Kode Pos", "Nama Toko"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        penjualTable.setSelectionBackground(new java.awt.Color(255, 153, 153));
        jScrollPane4.setViewportView(penjualTable);

        usernamePenjualField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernamePenjualFieldActionPerformed(evt);
            }
        });

        jLabel29.setText("Nama Toko");

        passwordPenjualField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordPenjualFieldActionPerformed(evt);
            }
        });

        cariPenjualField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariPenjualFieldActionPerformed(evt);
            }
        });

        cariPenjualBtn.setText("Cari");
        cariPenjualBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariPenjualBtnActionPerformed(evt);
            }
        });

        jLabel31.setText("Cari Penjual");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariPenjualBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel21)))
                                    .addComponent(jLabel29))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(kodePosPenjualField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jalanPenjualField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kotaPenjualField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(noTelpPenjualField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(namaTokoField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel24)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel28))
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usernamePenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(namaPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(166, 166, 166))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(tambahPenjualBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ubahPenjualBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hapusPenjualBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(simpanPenjualBtn)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(cariPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariPenjualBtn)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernamePenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(passwordPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namaPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noTelpPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kotaPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jalanPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kodePosPenjualField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namaTokoField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tambahPenjualBtn)
                            .addComponent(ubahPenjualBtn)
                            .addComponent(hapusPenjualBtn)
                            .addComponent(simpanPenjualBtn))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Penjual", jPanel2);

        kurirTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Kurir", "Username", "Password", "Nama"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        kurirTable.setSelectionBackground(new java.awt.Color(255, 153, 153));
        jScrollPane5.setViewportView(kurirTable);

        jLabel33.setText("Cari Kurir");

        cariKurirField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariKurirFieldActionPerformed(evt);
            }
        });

        cariKurirBtn.setText("Cari");
        cariKurirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariKurirBtnActionPerformed(evt);
            }
        });

        jLabel34.setText("Password");

        jLabel35.setText("ID Kurir");

        idKurirField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idKurirFieldActionPerformed(evt);
            }
        });

        jLabel36.setText("Username");

        usernameKurirField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameKurirFieldActionPerformed(evt);
            }
        });

        jLabel37.setText("Nama");

        hapusKurirBtn.setText("Hapus");
        hapusKurirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusKurirBtnActionPerformed(evt);
            }
        });

        simpanKurirBtn.setText("Simpan");
        simpanKurirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanKurirBtnActionPerformed(evt);
            }
        });

        tambahKurirBtn.setText("Tambah");
        tambahKurirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahKurirBtnActionPerformed(evt);
            }
        });

        ubahKurirBtn.setText("Ubah");
        ubahKurirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahKurirBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariKurirField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariKurirBtn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel36)
                                        .addComponent(jLabel35)
                                        .addComponent(jLabel34))
                                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(usernameKurirField)
                                    .addComponent(idKurirField)
                                    .addComponent(namaKurirField)
                                    .addComponent(passwordKurirField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(tambahKurirBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ubahKurirBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hapusKurirBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(simpanKurirBtn)))
                        .addGap(64, 64, 64))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(cariKurirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariKurirBtn))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idKurirField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernameKurirField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordKurirField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namaKurirField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tambahKurirBtn)
                            .addComponent(ubahKurirBtn)
                            .addComponent(hapusKurirBtn)
                            .addComponent(simpanKurirBtn))))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Kurir", jPanel5);

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

        jLabel32.setFont(new java.awt.Font("Snap ITC", 1, 36)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Sneaky Sun");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(82, 82, 82))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(70, 70, 70))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1)
                    .addContainerGap())
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(179, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(176, 176, 176)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTabbedPane1)
                    .addContainerGap()))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void hapusPembeliBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusPembeliBtnActionPerformed
        String idPembeli = idPembeliField.getText();

        CRUDPembeli pembeli = new CRUDPembeli();
        pembeli.deletePembeli(idPembeli);
        populatedPembeliRecords();
    }//GEN-LAST:event_hapusPembeliBtnActionPerformed

    private void simpanPembeliBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanPembeliBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_simpanPembeliBtnActionPerformed

    private void tambahPembeliBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahPembeliBtnActionPerformed
        String username = usernamePembeliField.getText();
        char[] password = passwordPembeliField.getPassword();
        String userPassword = new String(password);
        String nama = namaPembeliField.getText();
        String noTelp = noTelpPembeliField.getText();
        String kota = kotaPembeliField.getText();
        String jalan = jalanPembeliField.getText();
        String kodePos = kodePosPembeliField.getText();

        CRUDPembeli pembeli = new CRUDPembeli();
        pembeli.createPembeli(username, userPassword, nama, noTelp, kota, jalan, kodePos);
        populatedPembeliRecords();
    }//GEN-LAST:event_tambahPembeliBtnActionPerformed

    private void ubahPembeliBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahPembeliBtnActionPerformed
        String username = usernamePembeliField.getText();
        char[] password = passwordPembeliField.getPassword();
        String userPassword = new String(password);
        String nama = namaPembeliField.getText();
        String noTelp = noTelpPembeliField.getText();
        String kota = kotaPembeliField.getText();
        String jalan = jalanPembeliField.getText();
        String kodePos = kodePosPembeliField.getText();
        String idPembeli = idPembeliField.getText();

        CRUDPembeli pembeli = new CRUDPembeli();
        pembeli.updatePembeli(username, userPassword, nama, noTelp, kota, jalan, kodePos, idPembeli);
        populatedPembeliRecords();
    }//GEN-LAST:event_ubahPembeliBtnActionPerformed

    private void idPembeliFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPembeliFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPembeliFieldActionPerformed

    private void usernamePembeliFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernamePembeliFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernamePembeliFieldActionPerformed

    private void noTelpPembeliFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noTelpPembeliFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noTelpPembeliFieldActionPerformed

    private void kotaPembeliFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kotaPembeliFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kotaPembeliFieldActionPerformed

    private void cariPembeliFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariPembeliFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariPembeliFieldActionPerformed

    private void cariPembeliBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariPembeliBtnActionPerformed
        String cariPembeli = cariPembeliField.getText();

        searchPembeli(cariPembeli);
    }//GEN-LAST:event_cariPembeliBtnActionPerformed

    private void tambahProdukBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahProdukBtnActionPerformed
        String idPenjual = idPenjualProdukField.getText();
        String nama = namaProdukField.getText();
        int harga = Integer.parseInt(hargaProdukField.getText());
        int stok = Integer.parseInt(stokProdukField.getText());

        CRUDProduk produk = new CRUDProduk();
        produk.createProduk(nama, harga, stok, idPenjual);
        populatedProdukRecords();
    }//GEN-LAST:event_tambahProdukBtnActionPerformed

    private void ubahProdukBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahProdukBtnActionPerformed
        String idPenjual = idPenjualProdukField.getText();
        String nama = namaProdukField.getText();
        int harga = Integer.parseInt(hargaProdukField.getText());
        int stok = Integer.parseInt(stokProdukField.getText());
        String idProduk = idProdukField.getText();

        CRUDProduk produk = new CRUDProduk();
        produk.updateProduk(nama, harga, stok, idPenjual, idProduk);
        populatedProdukRecords();
    }//GEN-LAST:event_ubahProdukBtnActionPerformed

    private void namaProdukFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaProdukFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaProdukFieldActionPerformed

    private void hapusProdukBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusProdukBtnActionPerformed
        String idProduk = idProdukField.getText();

        CRUDProduk produk = new CRUDProduk();
        produk.deleteProduk(idProduk);
        populatedProdukRecords();
    }//GEN-LAST:event_hapusProdukBtnActionPerformed

    private void simpanProdukBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanProdukBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_simpanProdukBtnActionPerformed

    private void idPenjualProdukFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPenjualProdukFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPenjualProdukFieldActionPerformed

    private void cariPenjualProdukFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariPenjualProdukFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariPenjualProdukFieldActionPerformed

    private void cariPenjualProdukBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariPenjualProdukBtnActionPerformed
        String cariPenjual = cariPenjualProdukField.getText();

        searchPenjualProduk(cariPenjual);
    }//GEN-LAST:event_cariPenjualProdukBtnActionPerformed

    private void idProdukFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idProdukFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idProdukFieldActionPerformed

    private void cariProdukFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariProdukFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariProdukFieldActionPerformed

    private void cariProdukBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariProdukBtnActionPerformed
        String cariProduk = cariProdukField.getText();

        searchProduk(cariProduk);
    }//GEN-LAST:event_cariProdukBtnActionPerformed

    private void hapusPenjualBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusPenjualBtnActionPerformed
        String idPenjual = idPenjualField.getText();

        CRUDPenjual penjual = new CRUDPenjual();
        penjual.deletePenjual(idPenjual);
        populatedPenjualRecords();
    }//GEN-LAST:event_hapusPenjualBtnActionPerformed

    private void simpanPenjualBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanPenjualBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_simpanPenjualBtnActionPerformed

    private void tambahPenjualBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahPenjualBtnActionPerformed
        String username = usernamePenjualField.getText();
        char[] password = passwordPenjualField.getPassword();
        String userPassword = new String(password);
        String nama = namaPenjualField.getText();
        String noTelp = noTelpPenjualField.getText();
        String kota = kotaPenjualField.getText();
        String jalan = jalanPenjualField.getText();
        String kode_pos = kodePosPenjualField.getText();
        String namaToko = namaTokoField.getText();

        CRUDPenjual penjual = new CRUDPenjual();
        penjual.createPenjual(username, userPassword, nama, noTelp, kota, jalan, kode_pos, namaToko);
        populatedPenjualRecords();
    }//GEN-LAST:event_tambahPenjualBtnActionPerformed

    private void noTelpPenjualFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noTelpPenjualFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noTelpPenjualFieldActionPerformed

    private void ubahPenjualBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahPenjualBtnActionPerformed
        String username = usernamePenjualField.getText();
        char[] password = passwordPenjualField.getPassword();
        String userPassword = new String(password);
        String nama = namaPenjualField.getText();
        String noTelp = noTelpPenjualField.getText();
        String kota = kotaPenjualField.getText();
        String jalan = jalanPenjualField.getText();
        String kode_pos = kodePosPenjualField.getText();
        String namaToko = namaTokoField.getText();
        String idPenjual = idPenjualField.getText();

        CRUDPenjual penjual = new CRUDPenjual();
        penjual.updatePenjual(username, userPassword, nama, noTelp, kota, jalan, kode_pos, namaToko, idPenjual);
        populatedPenjualRecords();
    }//GEN-LAST:event_ubahPenjualBtnActionPerformed

    private void kotaPenjualFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kotaPenjualFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kotaPenjualFieldActionPerformed

    private void idPenjualFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPenjualFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPenjualFieldActionPerformed

    private void usernamePenjualFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernamePenjualFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernamePenjualFieldActionPerformed

    private void cariPenjualFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariPenjualFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariPenjualFieldActionPerformed

    private void cariPenjualBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariPenjualBtnActionPerformed
        String cariPenjual = cariPenjualField.getText();

        searchPenjual(cariPenjual);
    }//GEN-LAST:event_cariPenjualBtnActionPerformed

    private void cariKurirFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariKurirFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariKurirFieldActionPerformed

    private void cariKurirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariKurirBtnActionPerformed
        String cariKurir = cariKurirField.getText();

        searchKurir(cariKurir);
    }//GEN-LAST:event_cariKurirBtnActionPerformed

    private void idKurirFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idKurirFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idKurirFieldActionPerformed

    private void usernameKurirFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameKurirFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameKurirFieldActionPerformed

    private void hapusKurirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusKurirBtnActionPerformed
        String idKurir = idKurirField.getText();
        
        CRUDKurir kurir = new CRUDKurir();
        kurir.deleteKurir(idKurir);
        populatedKurirRecords();
    }//GEN-LAST:event_hapusKurirBtnActionPerformed

    private void simpanKurirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanKurirBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_simpanKurirBtnActionPerformed

    private void tambahKurirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahKurirBtnActionPerformed
        String username = usernameKurirField.getText();
        char[] password = passwordKurirField.getPassword();
        String userPassword = new String(password);
        String nama = namaKurirField.getText();
        
        CRUDKurir kurir = new CRUDKurir();
        kurir.createKurir(username, userPassword, nama);
        populatedKurirRecords();
    }//GEN-LAST:event_tambahKurirBtnActionPerformed

    private void ubahKurirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahKurirBtnActionPerformed
        String idKurir = idKurirField.getText();
        String username = usernameKurirField.getText();
        char[] password = passwordKurirField.getPassword();
        String userPassword = new String(password);
        String nama = namaKurirField.getText();
        
        CRUDKurir kurir = new CRUDKurir();
        kurir.updateKurir(username, userPassword, nama, idKurir);
        populatedKurirRecords();
    }//GEN-LAST:event_ubahKurirBtnActionPerformed

    private void passwordPenjualFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordPenjualFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordPenjualFieldActionPerformed

    private void tambahJmlProdukBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahJmlProdukBtnActionPerformed
        String nilaiTextField = stokProdukField.getText();

        if (!nilaiTextField.isEmpty()) {
            int jumlahSaatIni = Integer.parseInt(stokProdukField.getText());
            int jumlahBaru = jumlahSaatIni + 1;
            stokProdukField.setText(Integer.toString(jumlahBaru));
        } else {
            stokProdukField.setText("1");
        }
    }//GEN-LAST:event_tambahJmlProdukBtnActionPerformed

    private void kurangJmlProdukBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kurangJmlProdukBtnActionPerformed
        String nilaiTextField = stokProdukField.getText();

        if (!nilaiTextField.isEmpty()) {
            int jumlahSaatIni = Integer.parseInt(stokProdukField.getText());
            if (jumlahSaatIni > 0) {
                int jumlahBaru = jumlahSaatIni - 1;
                stokProdukField.setText(Integer.toString(jumlahBaru));
            }
        } else {
            stokProdukField.setText("0");
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
                new AdminWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cariKurirBtn;
    private javax.swing.JTextField cariKurirField;
    private javax.swing.JButton cariPembeliBtn;
    private javax.swing.JTextField cariPembeliField;
    private javax.swing.JButton cariPenjualBtn;
    private javax.swing.JTextField cariPenjualField;
    private javax.swing.JButton cariPenjualProdukBtn;
    private javax.swing.JTextField cariPenjualProdukField;
    private javax.swing.JTable cariPenjualProdukTable;
    private javax.swing.JButton cariProdukBtn;
    private javax.swing.JTextField cariProdukField;
    private javax.swing.JButton hapusKurirBtn;
    private javax.swing.JButton hapusPembeliBtn;
    private javax.swing.JButton hapusPenjualBtn;
    private javax.swing.JButton hapusProdukBtn;
    private javax.swing.JTextField hargaProdukField;
    private javax.swing.JTextField idKurirField;
    private javax.swing.JTextField idPembeliField;
    private javax.swing.JTextField idPenjualField;
    private javax.swing.JTextField idPenjualProdukField;
    private javax.swing.JTextField idProdukField;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jalanPembeliField;
    private javax.swing.JTextField jalanPenjualField;
    private javax.swing.JTextField kodePosPembeliField;
    private javax.swing.JTextField kodePosPenjualField;
    private javax.swing.JTextField kotaPembeliField;
    private javax.swing.JTextField kotaPenjualField;
    private javax.swing.JButton kurangJmlProdukBtn;
    private javax.swing.JTable kurirTable;
    private javax.swing.JTextField namaKurirField;
    private javax.swing.JTextField namaPembeliField;
    private javax.swing.JTextField namaPenjualField;
    private javax.swing.JTextField namaProdukField;
    private javax.swing.JTextField namaTokoField;
    private javax.swing.JTextField noTelpPembeliField;
    private javax.swing.JTextField noTelpPenjualField;
    private javax.swing.JPasswordField passwordKurirField;
    private javax.swing.JPasswordField passwordPembeliField;
    private javax.swing.JPasswordField passwordPenjualField;
    private javax.swing.JTable pembeliTable;
    private javax.swing.JTable penjualTable;
    private javax.swing.JTable produkTable;
    private javax.swing.JButton simpanKurirBtn;
    private javax.swing.JButton simpanPembeliBtn;
    private javax.swing.JButton simpanPenjualBtn;
    private javax.swing.JButton simpanProdukBtn;
    private javax.swing.JTextField stokProdukField;
    private javax.swing.JButton tambahJmlProdukBtn;
    private javax.swing.JButton tambahKurirBtn;
    private javax.swing.JButton tambahPembeliBtn;
    private javax.swing.JButton tambahPenjualBtn;
    private javax.swing.JButton tambahProdukBtn;
    private javax.swing.JButton ubahKurirBtn;
    private javax.swing.JButton ubahPembeliBtn;
    private javax.swing.JButton ubahPenjualBtn;
    private javax.swing.JButton ubahProdukBtn;
    private javax.swing.JTextField usernameKurirField;
    private javax.swing.JTextField usernamePembeliField;
    private javax.swing.JTextField usernamePenjualField;
    // End of variables declaration//GEN-END:variables
}
