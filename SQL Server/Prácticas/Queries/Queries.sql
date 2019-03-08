SELECT EmployeeId, LastName, FirstName
FROM Employees

SELECT *
FROM Employees

SELECT OrderID, ProductID, UnitPrice, Quantity, (UnitPrice * Quantity) --la operacion agrega una nueva columna en el resultado
FROM [Order Details]

SELECT *
FROM Employees
WHERE Country = 'USA' --selecciona toda la información de empleados de aquellos que son gringos

/* 

*/

SELECT *
FROM Employees
WHERE Country = 'USAW'

SELECT *
FROM Employees
WHERE Country = 'USA' And TitleOfCourtesy in ('Ms.','Mr.')



SELECT *
FROM Employees
WHERE Country = 'USA' And TitleOfCourtesy <> 'Ms.'


SELECT *
FROM Employees
WHERE HireDate >= '1990-01-01'


SELECT *
FROM Employees
WHERE EmployeeId in (1,2,3,7,9)

SELECT *
FROM Employees
WHERE EmployeeId in (1,2,3,7,9)


SELECT ContactType
FROM Contacts


SELECT Distinct ContactType
FROM Contacts

SELECT EmployeeId, FirstName, LastName
FROM Employees
WHERE TitleOfCourtesy = 'Ms.'

SELECT ProductID, ProductName, UnitPrice
FROM Products 
WHERE UnitPrice < 25



SELECT ProductID, ProductName, UnitsInStock, UnitsOnOrder, ReorderLevel
FROM Products
WHERE Discontinued = 0


SELECT ProductID, ProductName
FROM Products
WHERE UnitsInStock <= ReorderLevel

SELECT FirstName, LastName FROM Employees ORDER BY FirstName, LastName;
SELECT FirstName, LastName FROM Employees ORDER BY TitleOfCourtesy, HireDate DESC;

SELECT COUNT(1) from Employees; --cuenta el numero de registros en la tabla
SELECT COUNT(1) from Orders;
SELECT COUNT(1) from OrderDetails;






