CREATE VIEW "Ordenes1996" AS
SELECT O.OrderID,  O.OrderDate, D.Quantity, O.TotalPrice
FROM Orders O
LEFT JOIN [Order Details] D on O.OrderID = D.OrderID
  GO

UPDATE Orders
SET TotalPrice = (SELECT SUM(UnitPrice * Quantity) FROM [Order Details]
                 WHERE [Order Details].OrderID = Orders.OrderID)

