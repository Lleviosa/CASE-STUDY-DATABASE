USE sneakysun

--STORED PROCEDURE TAMBAH PRODUK
GO
CREATE PROCEDURE TambahProduk
    @id_penjual VARCHAR(11),
    @nama_produk VARCHAR(50),
    @id_kategori VARCHAR(12),
    @stok INT,
    @harga INT
AS
BEGIN
    INSERT INTO Produk (id_penjual, nama, id_kategori, stok, harga)
    VALUES (@id_penjual, @nama_produk, @id_kategori, @stok, @harga);
END;
GO

--STORED PROCEDURE TAMBAH PESANAN
GO
CREATE PROCEDURE TambahPesanan
    @id_pembeli VARCHAR(11),
    @id_produk VARCHAR(11),
    @jumlah INT
AS
BEGIN
    DECLARE @id_pesanan VARCHAR(12);

    INSERT INTO Pesanan (id_pembeli) VALUES (@id_pembeli);
    SET @id_pesanan = (SELECT TOP 1 id_pesanan FROM Pesanan ORDER BY id_pesanan DESC);

    INSERT INTO Detail_Pesanan (id_pesanan, id_produk, jumlah, subtotal)
    VALUES (@id_pesanan, @id_produk, @jumlah, (SELECT harga FROM Produk WHERE id_produk = @id_produk) * @jumlah);
END;
GO

--STORED PROCEDURE TAMBAH STOK PRODUK
GO
CREATE PROCEDURE TambahStokProduk
    @id_produk VARCHAR(11),
    @jumlah INT
AS
BEGIN
    UPDATE Produk
    SET stok = stok + @jumlah
    WHERE id_produk = @id_produk;
END;
GO

--STORED PROCEDURE KURANG STOK PRODUK
GO
CREATE PROCEDURE KurangStokProduk
    @id_produk VARCHAR(11),
    @jumlah INT
AS
BEGIN
    UPDATE Produk
    SET stok = stok - @jumlah
    WHERE id_produk = @id_produk;
END;
GO

--STORED PROCEDURE KONFIRMASI PEMBAYARAN
GO
CREATE PROCEDURE KonfirmasiPembayaran
    @id_pesanan VARCHAR(12)
AS
BEGIN
    UPDATE Pesanan
    SET tanggal_pesanan = GETDATE()
    WHERE id_pesanan = @id_pesanan;
END;
GO

GO
-- TAMBAH PRODUK
EXEC TambahProduk
    @id_penjual = 'J1001',
    @nama_produk = 'JAKET GUNUNG EIGER',
    @id_kategori = 'KT1002',
    @stok = 50,
    @harga = 1500000;
GO

GO
--TAMBAH PESANAN
EXEC TambahPesanan
    @id_pembeli = 'B1001',
    @id_produk = 'P1006',
    @jumlah = 5;
GO

GO
--UPDATE STOK
EXEC TambahStokProduk
    @id_produk = 'P1001',
    @jumlah = 2;
GO

GO
--KONFIRMASI PEMBAYARAN
EXEC KonfirmasiPembayaran
    @id_pesanan = 'PE1001';
GO