use LABORATORIO2;

--1
CREATE TABLE Articulos(
  articuloId INT NOT NULL PRIMARY KEY,
  descripcion VARCHAR(100),
  precioReferencia MONEY
)

CREATE TABLE Clientes(
  clienteId INT NOT NULL PRIMARY KEY,
  nombre VARCHAR(100)
)

CREATE TABLE Facturas(
  noFactura INT NOT NULL PRIMARY KEY,
  fecha DATE,
  montoTotal MONEY,
  clienteId INT NOT NULL FOREIGN KEY REFERENCES Clientes(ClienteId)
)

CREATE TABLE FacturaDet(
  noFactura INT NOT NULL,
  articuloId INT NOT NULL,
  cantidad INT,
  precioUn MONEY
)

ALTER TABLE FacturaDet ADD CONSTRAINT pk_FacturaDet PRIMARY KEY (noFactura, articuloId);
ALTER TABLE FacturaDet ADD CONSTRAINT fk_FacturaDet_noFactura FOREIGN KEY (noFactura) REFERENCES Facturas (noFactura);
ALTER TABLE FacturaDet ADD CONSTRAINT fk_FacturaDet_articuloId FOREIGN KEY (articuloId) REFERENCES Articulos (articuloId);

--2
INSERT INTO Clientes VALUES(100, 'Andres');
INSERT INTO Clientes VALUES(200, 'Karina');
INSERT INTO Clientes VALUES(300, 'Miguel');
INSERT INTO Clientes VALUES(400, 'Mario ');
INSERT INTO Clientes VALUES(500, 'John D');

INSERT INTO Articulos VALUES(20101,  'CAMISA MANGA CORTA AZUL', 12000);
INSERT INTO Articulos VALUES(30224,  'PANTALONETA NATACION', 15000);
INSERT INTO Articulos VALUES(23100,  'CAMISETA BLANCA CUELLO V', 3500);
INSERT INTO Articulos VALUES(50910,  'PANTALON CARGO BEIGE', 18000);
INSERT INTO Articulos VALUES(50875,  'PANTALON MUJER NEGRO', 22000);
INSERT INTO Articulos VALUES(31020,  'PANTALONETA FUTBOL NEGRA', 6000);
INSERT INTO Articulos VALUES(60308,  'MEDIAS DE FUTBOL NEGRAS', 3000);
INSERT INTO Articulos VALUES(23840,  'CAMISETA REAL MADRID', 9000);

INSERT INTO Facturas VALUES(6, '2018-06-18', 0, 100);
INSERT INTO Facturas VALUES(5, '2018-05-21', 0, 200);
INSERT INTO Facturas VALUES(4, '2018-07-11', 0, 300);
INSERT INTO Facturas VALUES(3, '2018-06-04', 0, 400);
INSERT INTO Facturas VALUES(2, '2018-05-12', 0, 200);
INSERT INTO Facturas VALUES(1, '2018-05-02', 0, 100);
INSERT INTO Facturas VALUES(7, '2018-08-06', 0, 300);


INSERT INTO FacturaDet VALUES(1, 20101, 1, 12000);
INSERT INTO FacturaDet VALUES(1, 30224, 1, 15000);
INSERT INTO FacturaDet VALUES(1, 23100, 3, 3500);
INSERT INTO FacturaDet VALUES(2, 50910, 1, 18000);
INSERT INTO FacturaDet VALUES(2, 50875, 1, 22000);
INSERT INTO FacturaDet VALUES(3, 31020, 2, 6000);
INSERT INTO FacturaDet VALUES(3, 60308, 2, 3000);
INSERT INTO FacturaDet VALUES(3, 23840, 2, 9000);
INSERT INTO FacturaDet VALUES(4, 50875, 2, 7500);
INSERT INTO FacturaDet VALUES(5, 23100, 3, 2800);
INSERT INTO FacturaDet VALUES(6, 23840, 2, 7200);
INSERT INTO FacturaDet VALUES(6, 31020, 2, 5000);
INSERT INTO FacturaDet VALUES(6, 23100, 1, 14800);
INSERT INTO FacturaDet VALUES(7, 23840, 3, 9000);

--3
ALTER TABLE FacturaDet ADD precioLinea MONEY NOT NULL DEFAULT 0;

--4
DECLARE @cantidad INT, @precioUn MONEY, @total MONEY

DEClARE cursorPrecioLinea CURSOR
FOR
  SELECT cantidad, precioUn, precioLinea
  FROM FacturaDet
OPEN cursorPrecioLinea
FETCH cursorPrecioLinea INTO @cantidad, @precioUn, @total
WHILE (@@FETCH_STATUS = 0) BEGIN
  SET @total = @cantidad * @precioUn
  UPDATE FacturaDet
  SET precioLinea = @total
  WHERE CURRENT OF cursorPrecioLinea
  FETCH cursorPrecioLinea INTO @cantidad, @precioUn, @total
end

close cursorPrecioLinea
deallocate cursorPrecioLinea

select * from FacturaDet;

--5
CREATE PROCEDURE actualizarMontoTotal
  AS BEGIN
  UPDATE Facturas
  SET montoTotal = (SELECT SUM(precioLinea)
                   FROM FacturaDet
                   WHERE FacturaDet.noFactura = Facturas.noFactura)

end

EXECUTE actualizarMontoTotal;

select * from Facturas;

--6

CREATE PROCEDURE facturasClientes
  AS BEGIN
  SELECT c.clienteId, c.nombre, COALESCE(SUM(f.montoTotal), 0) as MontoTotal, COUNT(f.clienteId) as CantidadFacturas
FROM Clientes c
LEFT JOIN Facturas f ON (c.clienteId = f.clienteId)
GROUP BY c.clienteId, c.nombre
end


EXECUTE facturasClientes;

  select * from Facturas;


--7

CREATE TRIGGER tr_cantidad_precioUn
  ON FacturaDet
  AFTER UPDATE
  AS BEGIN
  IF UPDATE (cantidad) OR UPDATE (precioUn)
    BEGIN UPDATE FacturaDet
    SET precioLinea = cantidad * precioUn
      WHERE noFactura IN (SELECT noFactura FROM inserted);
      EXECUTE actualizarMontoTotal
end
  END

--8
CREATE FUNCTION montoVendidoPorArticulo (

)
  RETURNS TABLE
  AS
  RETURN (
            SELECT a.articuloId, a.descripcion, COALESCE(SUM(d.precioLinea), 0) as MontoVendido
            FROM Articulos a
            LEFT JOIN FacturaDet d ON (a.articuloId = d.articuloId)
            GROUP BY a.articuloId, a.descripcion
            ORDER BY descripcion DESC OFFSET 0 ROWS
         )

  SELECT * FROM dbo.montoVendidoPorArticulo();








