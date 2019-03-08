CREATE TABLE provincias 
(  
    provincia_id    int primary key identity(1,1), 
    nombre          varchar(100)
); 
GO 

CREATE TABLE cantones 
(  
    canton_id       int primary key identity(1,1), 
    provincia_id    int not null foreign key references provincias(provincia_id), 
    nombre          varchar(100)
); 
GO 

CREATE TABLE distritos 
(  
    distrito_id     int primary key identity(1,1), 
    canton_id       int not null foreign key references cantones(canton_id), 
    nombre          varchar(100)
); 
GO 

INSERT provincias (nombre) 
VALUES 
    ('San José')
GO  

INSERT cantones (provincia_id, nombre) 
SELECT provincia_id, 'San José' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Escazú' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Desamparados' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Puriscal' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Tarrazú' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Aserrí' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Mora' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Goicoechea' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Santa Ana' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Alajuelita' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Vásquez de Coronado' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Acosta' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Tibás' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Moravia' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Montes de Oca' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Turrubares' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Dota' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Curridabat' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'Pérez Zeledón' FROM provincias where nombre = 'San José' UNION
SELECT provincia_id, 'León Cortés' FROM provincias where nombre = 'San José' 
GO  

INSERT distritos (canton_id, nombre) 
SELECT canton_id, 'Carmen' FROM cantones where nombre = 'San José' UNION
SELECT canton_id, 'Merced' FROM cantones where nombre = 'San José' UNION
SELECT canton_id, 'Hospital' FROM cantones where nombre = 'San José' UNION
SELECT canton_id, 'Catedral' FROM cantones where nombre = 'San José' UNION
SELECT canton_id, 'Zapote' FROM cantones where nombre = 'San José' UNION
SELECT canton_id, 'San Francisco de Dos Ríos' FROM cantones where nombre = 'San José' UNION
SELECT canton_id, 'Uruca' FROM cantones where nombre = 'San José' UNION
SELECT canton_id, 'Mata Redonda' FROM cantones where nombre = 'San José' UNION
SELECT canton_id, 'Pavas' FROM cantones where nombre = 'San José' UNION
SELECT canton_id, 'Hatillo' FROM cantones where nombre = 'San José' UNION
SELECT canton_id, 'San Sebastián' FROM cantones where nombre = 'San José' 
GO

CREATE FUNCTION direccionCompleta (
  @pidDistrito INT
)
   RETURNS VARCHAR(100)
    AS
    BEGIN
        DECLARE @nombreDist VARCHAR(100)

        SELECT @nombreDist     =   nombre
                                         FROM distritos
                                         where distrito_id = @pidDistrito

        DECLARE @nombreCanton VARCHAR(100)

        SELECT @nombreCanton =     c.nombre
                                           FROM cantones c
                                            INNER JOIN distritos d ON (c.canton_id = d.canton_id)
                                            WHERE distrito_id = @pidDistrito
        DECLARE @nombreProvincia VARCHAR(100)
        SELECT @nombreProvincia = p.nombre
                                    FROM provincias p
                                    INNER JOIN cantones c on (p.provincia_id = c.provincia_id)
                                    INNER JOIN distritos d ON (c.canton_id = d.canton_id)
                                    WHERE distrito_id = @pidDistrito
        DECLARE @resultado VARCHAR(100) = CONCAT(COALESCE(@nombreDist, 'Distrito inexistente'), ', ' , @nombreCanton , ', ' , @nombreProvincia)
        RETURN @resultado;
    end

    SELECT dbo.direccionCompleta(1) AS [Distrito Canton y Provincia];



--esta es una manera mas eficiente de crear la funcion anterior
CREATE FUNCTION direccionCompleta2 (
@pIdDistrito INT
)
  RETURNS VARCHAR(100)
AS
  BEGIN
    DECLARE @resultado VARCHAR(100)
    SELECT @resultado = CONCAT_WS(', ', d.nombre, c.nombre, p.nombre)
                                      FROM provincias p
                                      INNER JOIN cantones c on (p.provincia_id = c.provincia_id)
                                    INNER JOIN distritos d ON (c.canton_id = d.canton_id)
                                    WHERE distrito_id = @pidDistrito
    RETURN @resultado

  end

SELECT dbo.direccionCompleta2(1) AS [Distrito Canton y Provincia 2];

CREATE FUNCTION orderWithDetails (
@numOrden INT
)
  RETURNS TABLE
  AS
    RETURN(
      SELECT d.ProductId, d.UnitPrice, Quantity, Discount, p.ProductName, c.CategoryName FROM [Order Details] d
      INNER JOIN Products p ON(p.ProductID = d.ProductID)
      INNER JOIN Categories c ON (p.CategoryID = c.CategoryID)
      WHERE d.OrderID = @numOrden
    )

SELECT * FROM dbo.orderWithDetails(10248)

-- SELECT ProductId, UnitPrice, Quantity, Discount FROM [Order Details] WHERE OrderID = 10248
-- SELECT p.ProductName FROM Products p INNER JOIN [Order Details] d ON(p.ProductID = d.ProductID) WHERE OrderID = 10248;
-- SELECT c.CategoryName FROM Categories c INNER JOIN Products p ON (p.CategoryID = c.CategoryID) WHERE OrderID = 10248
