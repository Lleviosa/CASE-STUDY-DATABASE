USE sneakysun

--SELECT ALL
GO
SELECT * FROM Penjual
SELECT * FROM Pembeli
SELECT * FROM Kurir
SELECT * FROM Produk_Kategori
SELECT * FROM Produk
SELECT * FROM Pesanan
SELECT * FROM Detail_Pesanan
SELECT * FROM Pembayaran
SELECT * FROM Pengiriman
SELECT * FROM Detail_Pengiriman
GO

--DROP TABLE
GO
DROP TABLE Detail_Pengiriman
DROP TABLE Pengiriman
DROP TABLE Pembayaran
DROP TABLE Detail_Pesanan
DROP TABLE Pesanan
DROP TABLE Produk
DROP TABLE Produk_Kategori
DROP TABLE Kurir
DROP TABLE Pembeli
DROP TABLE Penjual
GO

--DROP TRIGGER
GO
DROP TRIGGER trgHitungTotal
DROP TRIGGER trgHitungSubtotal
DROP TRIGGER trgUpdateProductStock
GO

--DELETE
GO
DELETE FROM Kurir
DELETE FROM Pembayaran
DELETE FROM Detail_Pesanan
GO

--SELECT
GO
SELECT id_penjual, username, password_penjual, nama, nama_toko, no_telp, kota, jalan, kode_pos
FROM Penjual

SELECT id_pembeli, username, password_pembeli, nama, no_telp, kota, jalan, kode_pos
FROM Pembeli

SELECT id_kurir, nama
FROM Kurir

SELECT id_kategori, nama
FROM Produk_Kategori

SELECT id_produk, nama, id_kategori, stok, harga
FROM Produk

SELECT id_pesanan, id_pembeli, tanggal_pesanan
FROM Pesanan

SELECT id_DetailPesanan, id_pesanan,id_produk, jumlah, subtotal
FROM Detail_Pesanan

SELECT id_pembayaran, id_pesanan, total
FROM Pembayaran

SELECT no_resi, id_pesanan, id_pembeli
FROM Pengiriman

SELECT * FROM Detail_Pengiriman
GO

-- 4a

WITH TopProducts AS (
    SELECT 
        ROW_NUMBER() OVER (PARTITION BY p.id_penjual ORDER BY SUM(dp.jumlah) DESC) AS Rank,
        p.nama AS NamaProduk,
        j.nama_toko AS NamaToko,
        SUM(dp.jumlah) AS Terjual
    FROM Detail_Pesanan dp
    INNER JOIN Produk p ON dp.id_produk = p.id_produk
    INNER JOIN Penjual j ON p.id_penjual = j.id_penjual
    GROUP BY p.id_penjual, p.nama, j.nama_toko
)
SELECT 
    NamaProduk,
    NamaToko,
    Terjual
FROM TopProducts
WHERE Rank <= 5;


-- 4b

WITH TopStores AS (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY SUM(dp.jumlah) DESC) AS Rank,
        j.nama_toko AS NamaToko,
        SUM(dp.jumlah) AS Terjual
    FROM Detail_Pesanan dp
    INNER JOIN Produk p ON dp.id_produk = p.id_produk
    INNER JOIN Penjual j ON p.id_penjual = j.id_penjual
    GROUP BY j.nama_toko
)
SELECT 
    NamaToko,
    Terjual
FROM TopStores
WHERE Rank <= 5;


-- 4c

WITH TopProductsTogether AS (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY COUNT(dp2.id_produk) DESC) AS Rank,
        p2.nama AS NamaProduk,
        COUNT(dp2.id_produk) AS JumlahPembelianBersama
    FROM Detail_Pesanan dp1
    INNER JOIN Detail_Pesanan dp2 ON dp1.id_pesanan = dp2.id_pesanan AND dp1.id_produk <> dp2.id_produk
    INNER JOIN Produk p1 ON dp1.id_produk = p1.id_produk
    INNER JOIN Produk p2 ON dp2.id_produk = p2.id_produk
    WHERE p1.nama = 'Lite Racer BYD 2.0 Shoes'
    GROUP BY p2.nama
)
SELECT 
    NamaProduk,
    JumlahPembelianBersama
FROM TopProductsTogether
WHERE Rank <= 3;
