USE sneakysun
GO

CREATE TRIGGER trgHitungSubtotal
ON Detail_Pesanan
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE dp
    SET dp.subtotal = p.harga * dp.jumlah
    FROM Detail_Pesanan dp
    INNER JOIN inserted i ON dp.id_DetailPesanan = i.id_DetailPesanan
    INNER JOIN Produk p ON dp.id_produk = p.id_produk;
END;

GO

GO
CREATE TRIGGER trgHitungTotal
ON Detail_Pesanan
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;

    -- Update Total for affected rows
    UPDATE pb
    SET pb.total = ((SELECT SUM(dp.subtotal) FROM Detail_Pesanan dp WHERE dp.id_pesanan = pb.id_pesanan))
    FROM Pembayaran pb
    INNER JOIN inserted i ON pb.id_pesanan = i.id_pesanan
    WHERE i.id_pesanan IS NOT NULL;

    -- Update Total for all rows when a delete operation occurs
    UPDATE pb
    SET pb.total = ((SELECT SUM(dp.subtotal) FROM Detail_Pesanan dp WHERE dp.id_pesanan = pb.id_pesanan))
    FROM Pembayaran pb
    WHERE NOT EXISTS (SELECT 1 FROM deleted d WHERE d.id_pesanan = pb.id_pesanan);
END;
GO

CREATE TRIGGER trgUpdateProductStock
ON Detail_Pesanan
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE p
    SET p.stok = p.stok - dp.jumlah
    FROM Produk p
    INNER JOIN inserted i ON p.id_produk = i.id_produk
    INNER JOIN Detail_Pesanan dp ON i.id_DetailPesanan = dp.id_DetailPesanan;
END;
