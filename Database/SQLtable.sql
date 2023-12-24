CREATE DATABASE sneakysun
GO
USE sneakysun
GO

CREATE TABLE Penjual(
	id_penjual_prefix	VARCHAR(1) DEFAULT 'J',
	id_penjual_suffix	INT IDENTITY(1000,1),
	id_penjual			AS (id_penjual_prefix + CAST(id_penjual_suffix AS VARCHAR(10))) PERSISTED PRIMARY KEY,
	username			VARCHAR(20),
	password_penjual	VARCHAR(20),
	nama				VARCHAR(50),
	nama_toko			VARCHAR(20),
	no_telp				CHAR(10),
	kota				VARCHAR(20),
	jalan				VARCHAR(50),
	kode_pos			CHAR(5)
)

CREATE TABLE Pembeli(
	id_pembeli_prefix	VARCHAR(1) DEFAULT 'B',
	id_pembeli_suffix	INT IDENTITY(1000,1),
	id_pembeli			AS (id_pembeli_prefix + CAST(id_pembeli_suffix AS VARCHAR(10))) PERSISTED PRIMARY KEY,
	username			VARCHAR(20),
	password_pembeli	VARCHAR(20),
	nama				VARCHAR(50),
	no_telp				CHAR(10),
	kota				VARCHAR(20),
	jalan				VARCHAR(50),
	kode_pos			CHAR(5)
)

CREATE TABLE Kurir(
	id_kurir_prefix		VARCHAR(1) DEFAULT 'K',
	id_kurir_suffix		INT IDENTITY(1000,1),
	id_kurir			AS (id_kurir_prefix + CAST(id_kurir_suffix AS VARCHAR(10))) PERSISTED PRIMARY KEY,
	username			VARCHAR(20),
	password_kurir		VARCHAR(20),
	nama				VARCHAR(50)
)

CREATE TABLE Produk_Kategori(
	id_kategori_prefix	VARCHAR(2) DEFAULT 'KT',
	id_kategori_suffix	INT IDENTITY(1000,1),
	id_kategori			AS (id_kategori_prefix + CAST(id_kategori_suffix AS VARCHAR(10))) PERSISTED PRIMARY KEY,
	nama				VARCHAR(50),
)

CREATE TABLE Produk(
	id_penjual			VARCHAR(11) FOREIGN KEY REFERENCES Penjual(id_penjual),
	id_produk_prefix	VARCHAR(1) DEFAULT 'P',
	id_produk_suffix	INT IDENTITY(1000,1),
	id_produk			AS (id_produk_prefix + CAST(id_produk_suffix AS VARCHAR(10))) PERSISTED PRIMARY KEY,
	nama				VARCHAR(50),
	id_kategori			VARCHAR(12) FOREIGN KEY REFERENCES Produk_Kategori(id_kategori),
	stok				INT DEFAULT 100,
	harga				INT
)

CREATE TABLE Pesanan(
	id_pesanan_prefix VARCHAR(2) DEFAULT 'PE',
	id_pesanan_suffix INT IDENTITY(1000,1),
	id_pesanan AS (id_pesanan_prefix + CAST(id_pesanan_suffix AS VARCHAR(10))) PERSISTED PRIMARY KEY,
	id_pembeli VARCHAR(11) FOREIGN KEY REFERENCES Pembeli(id_pembeli),
	tanggal_pesanan DATE DEFAULT getdate()
)

CREATE TABLE Detail_Pesanan(
	id_DetailPesanan_prefix VARCHAR(2) DEFAULT 'DP',
	id_DetailPesanan_suffix INT IDENTITY(1000,1),
	id_DetailPesanan AS (id_DetailPesanan_prefix + CAST(id_DetailPesanan_suffix AS VARCHAR(10))) PERSISTED PRIMARY KEY,
	id_pesanan VARCHAR(12) FOREIGN KEY REFERENCES Pesanan(id_pesanan),
	id_produk VARCHAR(11) FOREIGN KEY REFERENCES Produk(id_produk),
	jumlah INT,
	subtotal INT
)

CREATE TABLE Pembayaran(
	id_pembayaran_prefix VARCHAR(2) DEFAULT 'PY',
    id_pembayaran_suffix INT IDENTITY(1000,1),
    id_pembayaran AS (id_pembayaran_prefix + CAST(id_pembayaran_suffix AS VARCHAR(10))) PERSISTED PRIMARY KEY,
	id_pesanan VARCHAR(12) FOREIGN KEY REFERENCES Pesanan(id_pesanan),
	total INT
)

CREATE TABLE Pengiriman(
	no_resi_prefix VARCHAR(1) DEFAULT 'R',
	no_resi_suffix INT IDENTITY(1000,1),
	no_resi AS (no_resi_prefix + CAST(no_resi_suffix AS VARCHAR(10))) PERSISTED PRIMARY KEY,
	id_pesanan VARCHAR(12) FOREIGN KEY REFERENCES Pesanan(id_pesanan),
	id_pembeli VARCHAR(11) FOREIGN KEY REFERENCES Pembeli(id_pembeli)
)

CREATE TABLE Detail_Pengiriman(
	no_resi VARCHAR(11) FOREIGN KEY REFERENCES Pengiriman(no_resi),
	tanggal_pengiriman DATE DEFAULT getdate(),
	kota VARCHAR(20),
	jalan VARCHAR(50),
	kode_pos char(5),
	id_kurir VARCHAR(11) FOREIGN KEY REFERENCES Kurir(id_kurir)
)