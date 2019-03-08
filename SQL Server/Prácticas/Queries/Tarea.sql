SELECT p.ProductName, p.UnitPrice, p.ReorderLevel, c.CategoryName, s.CompanyName, s.ContactName, s.ContactTitle
 FROM Products p, Categories c, Suppliers s WHERE p.CategoryId = c.CategoryId AND p.SupplierId = s.SupplierId;


--TAREA:
SELECT o.OrderId, o.OrderDate, e.FirstName, c.ContactName FROM Orders o, Employees e, Customers c WHERE o.EmployeeId = e.EmployeeId
 AND o.CustomerId = c.CustomerId AND o.OrderId BETWEEN 10248 AND 10258;