CREATE DATABASE Triggers;

USE Triggers;

CREATE TABLE categorias (
  idCategoria INT IDENTITY (1,1) NOT NULL PRIMARY KEY,
  descripcion VARCHAR(100) NOT NULL,
  estado INT DEFAULT 1
)

ALTER TABLE categorias ADD CONSTRAINT chk_estado CHECK (estado IN (1, 0));

CREATE TABLE articulos (
  idArticulo INT IDENTITY (1, 1) NOT NULL PRIMARY KEY,
  descripcion VARCHAR(100) NOT NULL,
  estado INT DEFAULT 1,
  idCategoria INT NOT NULL
)

ALTER TABLE articulos ADD CONSTRAINT chk_estado_articulos CHECK (estado IN (1, 0));
ALTER TABLE articulos ADD CONSTRAINT fk_articulos_idCategoria FOREIGN KEY (idCategoria) REFERENCES categorias (idCategoria);



insert into categorias(descripcion)
values ('Carnes'),
('Frutas'),
('Dulces'),
('Ficticios'),
('Vacia');

-- inserto los articulos de Carnes
insert into articulos(descripcion, idCategoria)
select 'Atun', idCategoria from categorias where descripcion = 'Carnes' UNION
select 'Lomito', idCategoria from categorias where descripcion = 'Carnes' UNION
select 'Muslo de pollo', idCategoria from categorias where descripcion = 'Carnes'

-- inserto los articulos de Frutas
insert into articulos(descripcion, idCategoria)
select 'Fresa', idCategoria from categorias where descripcion = 'Frutas' UNION
select 'Naranja', idCategoria from categorias where descripcion = 'Frutas' UNION
select 'Banano', idCategoria from categorias where descripcion = 'Frutas'

-- inserto los articulos de Dulces
insert into articulos(descripcion, idCategoria)
select 'Caramelo', idCategoria from categorias where descripcion = 'Dulces' UNION
select 'Chocolate', idCategoria from categorias where descripcion = 'Dulces' UNION
select 'Leche condensada', idCategoria from categorias where descripcion = 'Dulces'

-- inserto los articulos de Ficticios
insert into articulos(descripcion, idCategoria)
select 'Dinosaurio', idCategoria from categorias where descripcion = 'Ficticios' UNION
select 'Vampiro', idCategoria from categorias where descripcion = 'Ficticios' UNION
select 'Unicornio', idCategoria from categorias where descripcion = 'Ficticios'


SELECT * FROM categorias;







--trigger que se activa cuando una categoria se inactiva (se le cambia el estado)
CREATE TRIGGER tr_categoria_inactivar
  ON categorias
  AFTER UPDATE
AS BEGIN
  IF UPDATE(estado)
    BEGIN UPDATE articulos
    SET ESTADO = (select inserted.estado
                  from inserted
                    where inserted.idCategoria = articulos.idCategoria)
      WHERE idCategoria IN (SELECT inserted.idCategoria --esto evita que los estados de las demas categorias se afecten
                            FROM inserted
                            WHERE inserted.estado = 0)
      END
end

UPDATE categorias
SET estado = 0
WHERE descripcion = 'Carnes'-- se cambia el estado de la categoria 'Carnes" a 0


SELECT * FROM articulos;--los articulos pertenecientes a carnes van a tener el estado en 0 ya que el trigger se disparó


--trigger para modificar la cantidad de lineasCategorias cpara que sea igual a la cantidad de registros en categorias
CREATE TRIGGER tr_categoria_ins
  ON categorias
  AFTER INSERT
  AS BEGIN
  DECLARE @numRegistros INT;
  SELECT @numRegistros = COUNT (idCategoria) FROM categorias
  UPDATE lineas_tablas
    SET lineasCategorias = @numRegistros
end

INSERT INTO categorias
(descripcion) VALUES ('Pruebas'); --hago la insercion, y el trigger se dispara

SELECT * FROM lineas_tablas;--ahora la columna lineasCategorias va a tener el # de categorias que existen

CREATE TRIGGER tr_articulo_ins
  ON articulos
  AFTER INSERT
  AS BEGIN
  DECLARE @numRegistros INT;
  SELECT @numRegistros = COUNT (idArticulo) FROM articulos
  UPDATE lineas_tablas
    SET lineasArticulos = @numRegistros
end

INSERT INTO articulos
(descripcion, estado, idCategoria)
    VALUES ('cordero', 1, 1);

SELECT * FROM lineas_tablas

CREATE TRIGGER tr_articulo_del
  ON articulos
  AFTER DELETE
  AS BEGIN
  DECLARE @numRegistros INT;
  SELECT @numRegistros = COUNT (idArticulo) FROM articulos
  UPDATE lineas_tablas
    SET lineasArticulos = @numRegistros
end

DELETE FROM articulos WHERE idArticulo = 14;

SELECT * FROM lineas_tablas;

--Triggers instead of
CREATE TABLE tablaPruebas (
  llave INT IDENTITY (1, 1) NOT NULL PRIMARY KEY,
  texto VARCHAR(100)
)



CREATE TRIGGER tr_insteadOf
  ON tablaPruebas
  INSTEAD OF INSERT
  AS BEGIN
  DECLARE @var INT;
end



INSERT INTO tablaPruebas (texto) VALUES('VALOR');

SELECT * FROM tablaPruebas;

/*CREATE TRIGGER tr_prueba
  ON tablaPruebas
  INSTEAD OF INSERT
  AS BEGIN
  IF(--REGLA DE NEGOCIO NO SE CUMPLE)
  THROW--...
  INSERT INTO tablaPruebas(texto)
    SELECT texto FROM inserted
end*/

--trigger que no permite que se borren categorias y tira un error
CREATE TRIGGER tr_categorias_del
  ON categorias
  INSTEAD OF DELETE
  AS BEGIN
  THROW 50001, 'No se pueden borrar categorías', 101
  END

  --ahora cambia el estado de la categoria a 0 en lugar de inactivarla
ALTER TRIGGER tr_categorias_del
  ON categorias
  INSTEAD OF DELETE
AS BEGIN
  UPDATE categorias
    SET estado = 0
    WHERE idCategoria IN (SELECT idCategoria FROM deleted)
end

DELETE FROM categorias WHERE idCategoria = 6; --ahora el estado de la categoria 6 es 0

SELECT c.descripcion, c.estado, a.descripcion, a.estado
FROM categorias c
INNER JOIN articulos a ON (c.idCategoria = a.idCategoria)

/*Borra las categorias siempre y cuando no tengan articulos asociados,
si sí tiene artículos asociados, modifica el estado de 1 a 0 (la inactiva)*/
ALTER TRIGGER tr_categorias_del
    ON categorias
INSTEAD OF DELETE
AS BEGIN
    UPDATE categorias
    SET estado = 0
    WHERE idCategoria IN (SELECT idCategoria FROM deleted) AND idCategoria IN (SELECT idCategoria FROM articulos)
    DELETE FROM categorias
    WHERE idCategoria IN (SELECT idCategoria FROM deleted) AND idCategoria NOT IN (SELECT idCategoria FROM articulos)
end


CREATE TRIGGER prevenirCambiosProduccion
  ON DATABASE
  FOR DROP_TABLE, ALTER_TABLE
  AS BEGIN
  THROW 51000, 'No se permite modificar tablas en produccion', 101
end

ALTER TABLE categorias ADD columna INT;


--Práctica en clase


USE Northwind

drop table ProductsStock;



CREATE TABLE ProductsStock (
  productsStockID INT IDENTITY (1, 1) NOT NULL PRIMARY KEY,
  ProductID INT NOT NULL,
  UnitsInStock INT NOT NULL,
  RegistryTime DATETIME
)

INSERT INTO ProductsStock (ProductId, UnitsInStock, RegistryTime)
    SELECT ProductID, UnitsInStock, GETDATE() FROM Products;

SELECT * FROM ProductsStock;

CREATE TRIGGER tr_products_stock
  ON Products
  AFTER INSERT
  AS BEGIN
    INSERT INTO ProductsStock (ProductID, UnitsInStock, RegistryTime)
    SELECT ProductID, UnitsInStock, GETDATE() FROM inserted
end

CREATE TRIGGER tr_change_unitsStock
  ON Products
  AFTER UPDATE
  AS BEGIN
  IF UPDATE UnitsInStock
  BEGIN

  end
end






