--  DECLARE @valor1 int;
--  SELECT @valor1;
--  SET @valor1 = 5;
--  SELECT @valor1;
--ó
-- DECLARE @valor1 int = 5;
-- SELECT @valor1;



-- DECLARE @valor1 int;
-- DECLARE @valor2 int;

-- SET @valor1 = 2;
-- SET @valor2 = 4;

-- DECLARE @suma int;
-- SET @suma = SUM(@valor1 + @valor2);
-- SELECT @suma as 'Suma';

-- DECLARE @variable1 int = 3;
-- GO

-- DECLARE @variable1 int; 

-- DECLARE @maxId int;
-- SET @maxId = (SELECT MAX(EmployeeId) FROM Employees);
-- SELECT @maxId as 'Mayor Id'; 

--Inicializacion de dos variables mediante un select
-- DECLARE @maxId int;
-- DECLARE @minId int;
-- SELECT @maxId = MAX(EmployeeId), @minId = MIN(EmployeeId)
-- FROM Employees;

-- CREATE TABLE ##tempLocal (
--     id INT IDENTITY(1,1) PRIMARY KEY,
--     nombre VARCHAR(100),
-- )

-- INSERT INTO ##tempLocal (nombre)
-- VALUES ('Andres'), ('Esteban'), ('Mario');

-- SELECT * FROM ##tempLocal; 

--DROP TABLE ##tempLocal






--  CREATE PROCEDURE sp_hola_mundo1
-- AS BEGIN
-- Select 'HOLA MUNDO';
-- END
-- GO

-- ALTER PROCEDURE sp_hola_mundo1
-- @pPalabra VARCHAR(50)
-- AS BEGIN
-- SELECT CONCAT('HOLA ', @pPalabra)
-- END

-- EXEC sp_hola_mundo1 @pPalabra = ' MUNDO';
-- GO

-- ALTER PROCEDURE sp_hola_mundo1
-- @pPalabra VARCHAR(50)
-- AS BEGIN 
-- SELECT CONCAT('HOLA', @pPalabra) as mensaje
-- SELECT CONCAT('HOLA 2', @pPalabra) as mensaje2
-- END

-- EXEC sp_hola_mundo1 @pPalabra = ' MUNDO';
-- GO

-- ALTER PROCEDURE sp_hola_mundo1 @pPalabra VARCHAR(50)
-- AS BEGIN

-- SELECT CONCAT(@pPalabra, 'HOLA') as mensaje
-- RETURN 1
-- END 
-- GO

-- DECLARE @VAR1 INT
-- EXEC @VAR1 = sp_hola_mundo1 @pPalabra = 'Texto'
-- SELECT @VAR1;
-- GO




-- CREATE PROCEDURE sp_modificaNum @pNum INT
-- AS BEGIN

-- SET @pNum = 1000
-- RETURN 1
-- END 
-- GO

-- DECLARE @numCambio int = 50
-- EXEC sp_modificaNum @pNum = @numCambio
-- SELECT @numCambio;
-- GO




-- -- CREATE PROCEDURE sp_orders_totalesValor @monto money
-- -- AS BEGIN
-- -- UPDATE Orders 
-- -- SET TotalDiscount = @monto, TotalPrice = @monto
-- -- END


-- BEGIN TRY
-- SELECT 1/0
-- END TRY
-- BEGIN CATCH
--     SELECT ERROR_NUMBER() AS ErrorNumber,
--         ERROR_SEVERITY() AS ErrorSeverity,
--         ERROR_STATE() AS ErrorState,
--         ERROR_PROCEDURE() AS ErrorProcedure,
--         ERROR_LINE() as EL,
--         ERROR_MESSAGE() as mensaje
-- END CATCH;



-- BEGIN TRAN --se trabaja con una transaccion

-- SELECT COUNT(*) as cuenta FROM [Order Details]

-- DELETE FROM [Order Details]

-- SELECT COUNT(*) AS cuenta FROM [Order Details]

-- ROLLBACK TRAN --se revierten las acciones anteriores

-- BEGIN TRAN
--     BEGIN TRY
--     DELETE FROM Products
--     WHERE ProductID = 1
--     END TRY
--     BEGIN CATCH
--          SELECT ERROR_NUMBER() AS ErrorNumber,
--          ERROR_SEVERITY() AS ErrorSeverity,
--          ERROR_STATE() AS ErrorState,
--          ERROR_PROCEDURE() AS ErrorProcedure,
--          ERROR_LINE() as EL,
--          ERROR_MESSAGE() as mensaje
--          IF @@TRANCOUNT > 0
--             ROLLBACK TRAN
--     END CATCH

--     IF @@TRANCOUNT > 0
--         COMMIT TRAN  

-- SELECT * FROM Products;

-- INSERT INTO Products (ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued)
-- VALUES ('Apple Pie', 2, 1, '12 - 12 oz jars', 100.0000, 17, 2, 25, 0 );

-- SELECT * FROM Products;

-- DELETE FROM Products 
-- WHERE ProductID = 78;

-- CREATE PROCEDURE sp_eliminarArticulo @pid INT
--     AS BEGIN
--         BEGIN TRAN
--     BEGIN TRY
--     DELETE FROM Products
--     WHERE ProductID = @pid
--     END TRY
--     BEGIN CATCH
--          SELECT ERROR_NUMBER() AS ErrorNumber,
--          ERROR_SEVERITY() AS ErrorSeverity,
--          ERROR_STATE() AS ErrorState,
--          ERROR_PROCEDURE() AS ErrorProcedure,
--          ERROR_LINE() as EL,
--          ERROR_MESSAGE() as mensaje
--          IF @@TRANCOUNT > 0
--             ROLLBACK TRAN
--     END CATCH
--     END

--     EXEC sp_eliminarArticulo @pid = 1;

--     SELECT * FROM Products;







