USE sneakysun
--VIEW
GO
CREATE VIEW Daftar_Produk AS
SELECT 
    P.id_produk,
    P.nama AS nama_produk,
    PK.nama AS kategori_produk,
    P.stok,
    P.harga,
    J.nama AS nama_penjual
FROM Produk P
JOIN Produk_Kategori PK ON P.id_kategori = PK.id_kategori
JOIN Penjual J ON P.id_penjual = J.id_penjual;
GO

GO
CREATE VIEW Pesanan_DetailPesanan AS
SELECT
    PS.id_pesanan,
    PS.tanggal_pesanan,
    DP.id_DetailPesanan,
    DP.id_produk,
    DP.jumlah,
    DP.subtotal
FROM Pesanan PS
JOIN Detail_Pesanan DP ON PS.id_pesanan = DP.id_pesanan;
GO

GO
CREATE VIEW Pengiriman_DetailPengiriman AS
SELECT
    PG.no_resi,
    DP.tanggal_pengiriman,
    DP.kota,
    DP.jalan,
    DP.kode_pos,
    K.nama AS nama_kurir
FROM Pengiriman PG
JOIN Detail_Pengiriman DP ON PG.no_resi = DP.no_resi
JOIN Kurir K ON DP.id_kurir = K.id_kurir;
GO

GO
SELECT * FROM Daftar_Produk
SELECT * FROM Pesanan_DetailPesanan
SELECT * FROM Pengiriman_DetailPengiriman
GO