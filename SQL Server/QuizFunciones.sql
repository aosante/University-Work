--2.1
CREATE FUNCTION direccionCompletaTabla (
@pIdProvincia INT
)
  RETURNS VARCHAR(100)
  AS
  BEGIN
    DECLARE @resultado VARCHAR (100)
    SELECT @resultado = CONCAT_WS(', ', d.nombre, c.nombre, p.nombre)
            FROM provincias p
                                      INNER JOIN cantones c on (p.provincia_id = c.provincia_id)
                                    INNER JOIN distritos d ON (c.canton_id = d.canton_id)
                                    WHERE p.provincia_id = @pIdProvincia
    RETURN @resultado
  end

  SELECT dbo.direccionCompletaTabla(1) AS DireccionCompleta;

--1
CREATE FUNCTION potencia (
  @pbase INT, @ppotencia INT
)
  RETURNS INT
  AS
  BEGIN
    DECLARE @potencia INT =  POWER(@pbase, @ppotencia)
    RETURN @potencia
  end

SELECT dbo.potencia(9, 2) AS Potencia;

--2.2
CREATE FUNCTION nombrePuntoCardinal(
@pLetra CHAR(1)
)
  RETURNS VARCHAR(10)
  AS
  BEGIN
    DECLARE @punto VARCHAR(10)
    SET @punto = CASE @pLetra
      WHEN 'N' THEN 'Norte'
      WHEN 'E' THEN 'Este'
      WHEN 'S' THEN 'Sur'
      WHEN 'O' THEN 'Oeste'
      ELSE 'No'
      END
      RETURN @punto
  end

SELECT dbo.nombrePuntoCardinal('S') AS PuntoCardinal;


--3
CREATE FUNCTION nombreDia (
@pfecha DATE
)
    RETURNS VARCHAR(12)
  AS
  BEGIN
    DECLARE @dia VARCHAR (12) = DATENAME(dw, @pfecha)
    RETURN @dia
end

  SELECT dbo.nombreDia('2018-07-20');

--4

CREATE FUNCTION nombreMes(
@pfecha DATE
)
  RETURNS VARCHAR(12)
  AS
  BEGIN
    DECLARE @mes VARCHAR(12) = DATENAME(mm, @pfecha)
    RETURN @mes
  end

SELECT dbo.nombreMes('2018-07-20');

--5*

CREATE FUNCTION fechaLarga(
@pfecha DATE
)
  RETURNS VARCHAR(50)
  AS
  BEGIN
    DECLARE @dia VARCHAR(12) = dbo.nombreDia(@pfecha)
    DECLARE @diaNum INT = DATENAME(d, @pfecha)
    DECLARE @mes VARCHAR(12) = dbo.nombreMes(@pfecha)
    DECLARE @anio INT = DATENAME(YY, @pfecha)
    DECLARE @fechaLarga VARCHAR(60) = CONCAT_WS(' ', CONVERT(VARCHAR(10), @dia) + ',', @mes, CONVERT(VARCHAR(10), @diaNum) + ',', @anio);
    RETURN @fechaLarga
  end

SELECT dbo.fechaLarga('2018-07-20');

--6
CREATE FUNCTION articulosPorCategoria(
@numCategoria int
)
  RETURNS TABLE
  AS
  RETURN (
      SELECT c.CategoryID, c.CategoryName, p.ProductID, p.ProductName, s.SupplierID, s.CompanyName FROM Categories c
      INNER JOIN Products p ON (c.CategoryId = p.CategoryID)
      INNER JOIN Suppliers s ON(s.SupplierID = p.SupplierID)
      WHERE c.CategoryID = @numCategoria
  )

  SELECT * from dbo.articulosPorCategoria(4);














