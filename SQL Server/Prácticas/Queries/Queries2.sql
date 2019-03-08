--a partir de la filmina 15
SELECT e.FirstName, e.LastName, e.ReportsTo, s.FirstName as SupervisorNombre, s.LastName as SupervisorApellido FROM Employees e INNER JOIN Employees s ON (s.EmployeeID = e.ReportsTo);

SELECT e.FirstName, e.LastName, e.ReportsTo, s.FirstName as SupervisorNombre, s.LastName as SupervisorApellido FROM Employees e LEFT JOIN Employees s ON (s.EmployeeID = e.ReportsTo);

SELECT e.FirstName, e.LastName, e.ReportsTo, COALESCE(s.FirstName, 'Sin')as SupNombre, COALESCE(s.LastName, 'Sin')as SupApellido FROM Employees e LEFT JOIN Employees s ON (s.EmployeeID = e.ReportsTo);

SELECT s.FirstName, s.LastName, COUNT(DISTINCT e.EmployeeID)as Emple_A_Cargo FROM Employees s LEFT JOIN Employees e ON (e.ReportsTo = s.EmployeeID) GROUP BY s.FirstName, s.LastName;

INSERT INTO Categories (CategoryName, Description) VALUES ('Verduras', 'Verduras frescas');
INSERT INTO Categories (CategoryName, Description) VALUES ('Frutas', 'Frutas dulces');
INSERT INTO Categories (CategoryName, Description) VALUES ('Envolturas', 'Envolturas de calidad');
INSERT INTO Categories (CategoryName, Description)
SELECT 'Copia Seafood', Description FROM Categories WHERE CategoryName = 'Seafood';

SELECT * FROM Categories WHERE CategoryName LIKE '%Seafood%';

SELECT * FROM Orders;

--ejemplos en clase (en pizarra) de INNER JOIN y LEFT JOIN
SELECT e.idEmpleado, e.nombre, c.descripcion FROM Empleados e INNER JOIN Computadoras c ON (c.IdComputadora = e.IdComputadora);


