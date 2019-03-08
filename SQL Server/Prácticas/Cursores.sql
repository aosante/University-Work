--declare @ var1 int, var2 varhcar(100), var3 varchar(100)

/*DECLARE cursorEmpleados CURSOR
FOR
SELECT idEmpelado, nombre, apellido
FROM Empleado
OPEN cursorEmpleados
FETCH cursorEmpleados INTO @var1, @var2, @var3

WHILE (@@FETCH_STATUD = 0) BEGIN
  SENTENCIAS
  FETCH cursorEmpleados INTO @var1, @var2, @var3
END

CLOSE cursorEmpleados
DEALLOCATE cursorEmpleados
*/



--imprimir con print, los resultados de un cursos que imprima todos los nombres y descripcion (separados)
--por coma, de todas las categorias de la tabla categorias
USE Northwind;
DECLARE @nombre VARCHAR(100), @descripcion VARCHAR(100);

DECLARE cursorCategorias CURSOR
FOR
  SELECT CategoryName, Description
  FROM Categories
OPEN cursorCategorias
FETCH cursorCategorias INTO @nombre, @descripcion
WHILE (@@FETCH_STATUS = 0) BEGIN
  PRINT @nombre + ' - ' + @descripcion
  FETCH cursorCategorias INTO @nombre, @descripcion
end

CLOSE cursorCategorias
DEALLOCATE cursorCategorias



DECLARE @nombreArticulo VARCHAR(100), @nombreCategoria VARCHAR(100);

DECLARE  cursorCategoriaArticulo CURSOR
FOR
SELECT p.ProductName, c.CategoryName FROM Products p
        INNER JOIN Categories c ON (p.CategoryID = c.CategoryID)
OPEN cursorCategoriaArticulo
FETCH cursorCategoriaArticulo INTO @nombreArticulo, @nombreCategoria
WHILE (@@FETCH_STATUS = 0) BEGIN
  PRINT @nombreArticulo + ' - ' + @nombreCategoria
  FETCH cursorCategoriaArticulo INTO @nombreArticulo, @nombreCategoria
end

CLOSE cursorCategoriaArticulo
DEALLOCATE cursorCategoriaArticulo

ALTER TABLE Orders
    ADD SubTotal MONEY, Quantity INT;

CREATE PROCEDURE resumenOrdenes
  AS BEGIN
  DECLARE @subtotal MONEY, @totalLineas INT
  DECLARE cursorResumenOrdenes CURSOR
  FOR
    SELECT OrderID FROM Orders
  OPEN cursorResumenOrdenes
  FETCH cursorResumenOrdenes INTO @orderId


end



