USE sneakysun
GO
CREATE PROCEDURE ProsesPesanan
    @id_pembeli VARCHAR(11),
    @id_produk VARCHAR(11),
    @jumlah INT
AS
BEGIN
    DECLARE @id_pesanan VARCHAR(12);
    BEGIN TRANSACTION;

    -- Menambahkan pesanan baru
    INSERT INTO Pesanan (id_pembeli) VALUES (@id_pembeli);

    -- Mendapatkan id_pesanan yang baru saja dimasukkan
    SET @id_pesanan = (SELECT TOP 1 id_pesanan FROM Pesanan ORDER BY id_pesanan DESC);

    -- Menambahkan detail pesanan
    INSERT INTO Detail_Pesanan (id_pesanan, id_produk, jumlah, subtotal)
    VALUES (@id_pesanan, @id_produk, @jumlah, (SELECT harga FROM Produk WHERE id_produk = @id_produk) * @jumlah);

    -- Mengurangi stok produk
    UPDATE Produk
    SET stok = stok - @jumlah
    WHERE id_produk = @id_produk;

    COMMIT TRANSACTION;
END;
GO

-- PROSES PESANAN
BEGIN TRANSACTION;
EXEC ProsesPesanan
    @id_pembeli = 'B1001',
    @id_produk = 'P1001',
    @jumlah = 2;
COMMIT TRANSACTION;

-- MEMBATALKAN PESANAN
ROLLBACK TRANSACTION;

DROP PROCEDURE ProsesPesanan
