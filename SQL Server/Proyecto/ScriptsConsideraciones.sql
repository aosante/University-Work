--1 *Creado
CREATE TRIGGER tr_eliminacion_facturas
  ON Invoice
  INSTEAD OF DELETE
  AS BEGIN
  DECLARE @status VARCHAR(15)
  SELECT @status = status FROM deleted
  IF @status = 'Close'
      THROW 50001, 'La factura se encuentra cerrada y no se puede eliminar', 102
  ALTER TABLE Item_X_Invoice DROP CONSTRAINT fk_item_x_invoice_invoiceNo
  DELETE FROM Invoice
  WHERE invoiceNo IN (SELECT invoiceNo FROM deleted) AND @status = 'Open'
  IF @status = 'Open'
    DELETE FROM Item_X_Invoice WHERE invoiceNo IN (SELECT invoiceNo FROM deleted)
  ALTER TABLE Item_X_Invoice ADD CONSTRAINT fk_item_x_invoice_invoiceNo FOREIGN KEY (invoiceNo) REFERENCES Invoice (invoiceNo);
END

  --1
  CREATE TRIGGER tr_eliminacion_facturas2
  ON Invoice
  INSTEAD OF DELETE
  AS BEGIN
  IF (select status from deleted) = 'Close'
      THROW 50001, 'La factura se encuentra cerrada y no se puede eliminar', 102
  ALTER TABLE Item_X_Invoice DROP CONSTRAINT fk_item_x_invoice_invoiceNo
  DELETE FROM Invoice
  WHERE invoiceNo IN (SELECT invoiceNo FROM deleted) AND (select status from deleted) = 'Open'
  IF (select status from deleted) = 'Open'
    DELETE FROM Item_X_Invoice WHERE invoiceNo IN (SELECT invoiceNo FROM deleted)
  ALTER TABLE Item_X_Invoice ADD CONSTRAINT fk_item_x_invoice_invoiceNo FOREIGN KEY (invoiceNo) REFERENCES Invoice (invoiceNo);
END

  select * from Invoice

--2
CREATE TRIGGER tr_eliminacion_articulo
  ON Item
  INSTEAD OF DELETE
  AS BEGIN
  IF (select itemId from deleted) IN (SELECT itemId FROM Item_X_Invoice)
  THROW 50002, 'No se permite eliminar el artículo ya que este está asociado a una factura', 102
end




--3 *Creado
CREATE TRIGGER tr_eliminacion_cliente
  ON Client
  INSTEAD OF DELETE
  AS BEGIN
  DECLARE @clientId INT
  SELECT @clientId = clientId FROM deleted
  IF @clientId IN (SELECT clientId FROM Invoice)
    THROW 50003, 'No se permite eliminar al cliente ya que este está asocidado a una factura', 103
end


--4
--*creado
CREATE TRIGGER tr_insercion_lineas
  ON Item_X_Invoice
  AFTER INSERT
  AS BEGIN
  UPDATE Invoice
  SET totalAmount = (select sum(unitPrice * lineQuantity) from Item_X_Invoice where Invoice.invoiceNo = Item_X_Invoice.invoiceNo)
  UPDATE Invoice
  SET lineQuantity = (select sum(lineQuantity) from Item_X_Invoice where Invoice.invoiceNo = Item_X_Invoice.invoiceNo)
end



--*creado
CREATE TRIGGER tr_remocion_lineas
  ON Item_X_Invoice
  AFTER DELETE
  AS BEGIN
   UPDATE Invoice
  SET totalAmount = (select sum(unitPrice * lineQuantity) from Item_X_Invoice where Invoice.invoiceNo = Item_X_Invoice.invoiceNo)
  UPDATE Invoice
  SET lineQuantity = (select sum(lineQuantity) from Item_X_Invoice where Invoice.invoiceNo = Item_X_Invoice.invoiceNo)
end

--5 *creada
CREATE FUNCTION calculaDescuento (
@monto MONEY, @descuento DECIMAL(15,4)
)
  RETURNS MONEY
  AS BEGIN
  DECLARE @montoDescuento MONEY, @porcentaje DECIMAL(15,4)
  SET @porcentaje = @descuento / 100
  set @montoDescuento = @monto * @porcentaje
  return @montoDescuento
end

select dbo.calculaDescuento(900, 20) as montoDeDescuento










