use Proyecto
--1
CREATE PROCEDURE facturasPorEmpleado
  @fechaInicio DATE, @fechaFin DATE
  AS BEGIN
    SELECT distinct COALESCE(COUNT(i.employeeId), 0) as #OfInvoices, e.employeeId, e.name, e.lastName, e.gender, e.hireDate, e.rating
    FROM Employee e LEFT JOIN Invoice I on e.employeeId = I.employeeId
    WHERE i.creationDate BETWEEN @fechaInicio AND @fechaFin
    group by e.employeeId, e.name, e.lastName, e.gender, e.hireDate, e.rating
end

EXECUTE facturasPorEmpleado @fechaInicio = '2018-01-01', @fechaFin = '2018-12-12';

--2
CREATE PROCEDURE regionClientes
  @region VARCHAR(50)
  AS BEGIN
  SELECT c.*, r.descripcion FROM Client c
INNER JOIN Region r ON c.regionId = r.regionId
WHERE r.descripcion = @region
end

EXECUTE regionClientes @region = 'Sur';

--3
  CREATE PROCEDURE top3ItemsVendidos
    @fechaInicio DATE, @fechaFin DATE
  AS BEGIN
    SELECT TOP 3 i.*, SUM(x.lineQuantity) AS unidadesVendidas
    FROM Item i INNER JOIN
    Item_X_Invoice x ON (x.itemId = i.itemId)
    INNER JOIN Invoice f ON (x.invoiceNo = f.invoiceNo)
    WHERE f.creationDate BETWEEN @fechaInicio AND @fechaFin
    GROUP BY i.itemId, i.description, i.cost, i.unitPrice, i.barcode, i.status, i.supplierId, i.categoryId
    ORDER BY unidadesVendidas DESC
  end

  EXECUTE top3ItemsVendidos @fechaInicio = '2018-01-01', @fechaFin = '2018-12-12';

--4
CREATE PROCEDURE montoPorCategoria
  @fechaInicio DATE, @fechaFin DATE
  AS BEGIN
    SELECT c.categoryId, c.description, COALESCE(SUM(x.lineQuantity * x.unitPrice), 0) as montoVendido
    FROM Category c
    LEFT JOIN Item i ON (i.categoryId = c.categoryId)
    INNER JOIN Item_X_Invoice x ON (x.itemId = i.itemId)
    INNER JOIN Invoice f ON (f.invoiceNo = x.invoiceNo)
    WHERE f.creationDate BETWEEN @fechaInicio AND @fechaFin
    GROUP BY c.categoryId, c.description
end

  EXECUTE montoPorCategoria @fechaInicio = '2018-01-01', @fechaFin = '2018-12-12';


--5
CREATE PROCEDURE montoPorProveedor
  AS BEGIN
  SELECT s.*, COALESCE(SUM(x.lineQuantity * x.unitPrice), 0) as montoVendido
  FROM Supplier s
  LEFT JOIN Item i ON (s.supplierId = i.supplierId)
  INNER JOIN Item_X_Invoice x ON (i.itemId = x.itemId)
  GROUP BY s.supplierId, s.name, s.address, s.telephone
  ORDER BY montoVendido ASC
end

EXECUTE montoPorProveedor;

--6

CREATE PROCEDURE menorArticuloPorCategoria
  AS BEGIN

CREATE TABLE #temp(
  descripcion VARCHAR(50),
  articulo VARCHAR(100),
  cantidadVendida INT
)

  INSERT INTO  #temp

SELECT DISTINCT c.description, i.description, sum(x.lineQuantity) as cantidadVendida
FROM Category c
LEFT JOIN Item i ON (i.categoryId = c.categoryId)
LEFT JOIN Item_X_Invoice x ON (x.itemId = i.itemId)
  GROUP BY  i.description, c.description

  SELECT descripcion, min(articulo) as articulo, min(cantidadVendida) as menorCantidadVendida FROM #temp GROUP BY descripcion
  DROP TABLE #temp
end


  EXECUTE menorArticuloPorCategoria


--7
  CREATE PROCEDURE ventasYdescuentoXmes
    @year INT
    AS BEGIN
    SELECT DATENAME(Month, f.creationDate) Mes, SUM(f.totalAmount) as totalVentas, SUM(f.totalAmount * d.percentage) as totalDescuento
FROM Invoice f INNER JOIN Discount d ON (f.discountId = d.discountId)
  WHERE YEAR(f.creationDate) = @year
GROUP BY DATENAME(Month, f.creationDate)
  end

EXECUTE ventasYdescuentoXmes @year = 2018;





