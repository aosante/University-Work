
use Proyecto

INSERT INTO Region VALUES('Norte');
INSERT INTO Region VALUES('Central');
INSERT INTO Region VALUES('Sur');

INSERT INTO Category VALUES ('Carnes');
INSERT INTO Category VALUES ('Verduras');
INSERT INTO Category VALUES ('Frutas');
INSERT INTO Category VALUES ('Postres');

INSERT INTO Employee VALUES('Juan', 'Brenes', 'M', '2010-01-01', 0)
INSERT INTO Employee VALUES('Andrea', 'Vargas', 'F', '2010-11-21', 0)
INSERT INTO Employee VALUES('Kevin', 'Jones', 'M', '2015-06-15', 0)
INSERT INTO Employee VALUES('Veronica', 'Alvarado', 'F', '2008-04-02', 0)
INSERT INTO Employee VALUES('Marian', 'Torres', 'F', '2012-07-05', 0)

INSERT INTO Client VALUES('Daniel', 'Benavides', 'San Jose', 'M', '8821-3248', 1);
INSERT INTO Client VALUES('Pablo', 'Bonilla', 'Alajuela', 'M', '8363-4212', 2);
INSERT INTO Client VALUES('Sebastian', 'Chacon', 'Cartago', 'M', '7034-7372', 3);
INSERT INTO Client VALUES('Daniel', 'Herrera', 'Heredia', 'M', '3623-4328', 1);
INSERT INTO Client VALUES('Maria', 'Mansilla', 'Puntarenas', 'F', '3673-4234', 2);
INSERT INTO Client VALUES('Maria', 'Monge', 'Guancaste', 'F', '8767-9878', 3);
INSERT INTO Client VALUES('Andres', 'Osante', 'Limon', 'M', '8090-2313', 1);
INSERT INTO Client VALUES('Alexander', 'Ramirez', 'San Jose', 'M', '3834-4921', 2);
INSERT INTO Client VALUES('Silvia', 'Rodriguez', 'Alajuela', 'F', '8043-8363', 3);
INSERT INTO Client VALUES('Angelica', 'Saenz', 'Cartago', 'F', '7333-9472', 1);
INSERT INTO Client VALUES('Jeison', 'Sandoval', 'Heredia', 'M', '8002-9329', 2);
INSERT INTO Client VALUES('Isaac', 'Soto', 'Puntarenas', 'M', '8353-7361', 3);
INSERT INTO Client VALUES('Ricardo', 'Treminio', 'Guancaste', 'M', '3898-9937', 1);
INSERT INTO Client VALUES('Israel', 'Umaña', 'Limon', 'M', '3331-1193', 2);
INSERT INTO Client VALUES('Denilson', 'Vargas', 'San Jose', 'M', '3891-0098', 3);
INSERT INTO Client VALUES('Karol', 'Vargas', 'Alajuela', 'F', '8991-9380', 1);
INSERT INTO Client VALUES('Eduardo', 'Zeledon', 'Cartago', 'M', '8081-0391', 2);

INSERT INTO Supplier VALUES('Carniceria Reyes', 'Curridabat, San Jose', '2525-7744');
INSERT INTO Supplier VALUES('Verduras CR', 'La Union, Cartago', '2399-9427');
INSERT INTO Supplier VALUES('Super Frutas', 'Puerto Viejo, Limon', '7730-9842');
INSERT INTO Supplier VALUES('Dulces Sanos', 'Cañas, Alajuela', '5542-4805');


INSERT INTO Item VALUES('Chorizo de cerdo', 100, 175, '1001', 'Active', 1, 1);
INSERT INTO Item VALUES('Muslo de pollo', 500, 725, '1002', 'Active', 1, 1);
INSERT INTO Item VALUES('Chuleta de cerdo', 600, 1000, '1003', 'Active', 1, 1);
INSERT INTO Item VALUES('Filete tilapia', 600, 1000, '1004', 'Active', 1, 1);
INSERT INTO Item VALUES('Lomito de res', 1500, 2500, '1005', 'Active', 1, 1);
INSERT INTO Item VALUES('Papa', 300, 450, '2001', 'Active', 2, 2);
INSERT INTO Item VALUES('Yuca', 600, 800, '2002', 'Active', 2, 2);
INSERT INTO Item VALUES('Zanahoria', 200, 300, '2003', 'Active', 2, 2);
INSERT INTO Item VALUES('Chayote', 400, 600, '2004', 'Active', 2, 2);
INSERT INTO Item VALUES('Elote', 550, 800, '2005', 'Active', 2, 2);
INSERT INTO Item VALUES('Pera', 200, 400, '3001', 'Active', 3, 3);
INSERT INTO Item VALUES('Manzana roja', 150, 250, '3002', 'Active', 3, 3);
INSERT INTO Item VALUES('Banano', 75, 150, '3003', 'Active', 3, 3);
INSERT INTO Item VALUES('Sandia', 400, 900, '3004', 'Active', 3, 3);
INSERT INTO Item VALUES('Limon dulce', 50, 100, '3005', 'Active', 3, 3);
INSERT INTO Item VALUES('Alfajor', 250, 500, '4001', 'Active', 4, 4);
INSERT INTO Item VALUES('Tres leches', 1000, 1500, '4002', 'Active', 4, 4);
INSERT INTO Item VALUES('Flan de vainilla', 700, 1000, '4003', 'Active', 4, 4);
INSERT INTO Item VALUES('Torta chilena', 1500, 2500, '4004', 'Active', 4, 4);
INSERT INTO Item VALUES('Helado de chocolate', 700, 1100, '4005', 'Active', 4, 4);

INSERT INTO Discount VALUES ('Regular', 0.05);
INSERT INTO Discount VALUES ('Preferencial', 0.10);

INSERT INTO InvoiceType VALUES('Credito');
INSERT INTO InvoiceType VALUES('Contado');



INSERT INTO Invoice VALUES('Factura1', GETDATE(), 'Open', 0, 0, 1, 16, 1, 1);
INSERT INTO Invoice VALUES('Factura2', GETDATE(), 'Open', 0, 0, 2, 2, 2, 2);
INSERT INTO Invoice VALUES('Factura3', GETDATE(), 'Open', 0, 0, 1, 11, Null, 3);
INSERT INTO Invoice VALUES('Factura4', GETDATE(), 'Open', 0, 0, 2, 8, 1, 4);
INSERT INTO Invoice VALUES('Factura5', GETDATE(), 'Open', 0, 0, 1, 5, 2, 5);
INSERT INTO Invoice VALUES('Factura6', GETDATE(), 'Open', 0, 0, 2, 15, Null, 1);
INSERT INTO Invoice VALUES('Factura7', GETDATE(), 'Open', 0, 0, 1, 12, 1, 2);
INSERT INTO Invoice VALUES('Factura8', GETDATE(), 'Open', 0, 0, 2, 13, 2, 3);
INSERT INTO Invoice VALUES('Factura9', GETDATE(), 'Open', 0, 0, 1, 4, Null, 4);
INSERT INTO Invoice VALUES('Factura10', GETDATE(), 'Open', 0, 0, 2, 1, 1, 5);
INSERT INTO Invoice VALUES('Factura11', GETDATE(), 'Open', 0, 0, 1, 1, 2, 1);
INSERT INTO Invoice VALUES('Factura12', GETDATE(), 'Open', 0, 0, 2, 2, Null, 2);
INSERT INTO Invoice VALUES('Factura13', GETDATE(), 'Open', 0, 0, 1, 6, 1, 3);
INSERT INTO Invoice VALUES('Factura14', GETDATE(), 'Open', 0, 0, 2, 3, 2, 4);
INSERT INTO Invoice VALUES('Factura15', GETDATE(), 'Open', 0, 0, 1, 10, Null, 5);
INSERT INTO Invoice VALUES('Factura16', GETDATE(), 'Open', 0, 0, 2, 17, 1, 1);
INSERT INTO Invoice VALUES('Factura17', GETDATE(), 'Open', 0, 0, 1, 9, 2, 2);
INSERT INTO Invoice VALUES('Factura18', GETDATE(), 'Open', 0, 0, 2, 7, Null, 3);
INSERT INTO Invoice VALUES('Factura19', GETDATE(), 'Open', 0, 0, 1, 3, 1, 4);
INSERT INTO Invoice VALUES('Factura20', GETDATE(), 'Open', 0, 0, 2, 14, 2, 5);



INSERT INTO Item_X_Invoice VALUES(1, 1, 1, 0);
INSERT INTO Item_X_Invoice VALUES(20, 1, 9, 0);
INSERT INTO Item_X_Invoice VALUES(7, 1, 7, 0);
INSERT INTO Item_X_Invoice VALUES(10, 1, 2, 0);
INSERT INTO Item_X_Invoice VALUES(13, 1, 5, 0);
INSERT INTO Item_X_Invoice VALUES(9, 2, 7, 0);
INSERT INTO Item_X_Invoice VALUES(15, 2, 8, 0);
INSERT INTO Item_X_Invoice VALUES(10, 2, 4, 0);
INSERT INTO Item_X_Invoice VALUES(2, 2, 1, 0);
INSERT INTO Item_X_Invoice VALUES(14, 2, 4, 0);
INSERT INTO Item_X_Invoice VALUES(16, 3, 3, 0);
INSERT INTO Item_X_Invoice VALUES(18, 3, 5, 0);
INSERT INTO Item_X_Invoice VALUES(3, 3, 9, 0);
INSERT INTO Item_X_Invoice VALUES(1, 3, 5, 0);
INSERT INTO Item_X_Invoice VALUES(5, 3, 10, 0);
INSERT INTO Item_X_Invoice VALUES(17, 4, 5, 0);
INSERT INTO Item_X_Invoice VALUES(12, 4, 9, 0);
INSERT INTO Item_X_Invoice VALUES(8, 4, 1, 0);
INSERT INTO Item_X_Invoice VALUES(4, 4, 7, 0);
INSERT INTO Item_X_Invoice VALUES(19, 4, 4, 0);
INSERT INTO Item_X_Invoice VALUES(9, 5, 3, 0);
INSERT INTO Item_X_Invoice VALUES(11, 5, 9, 0);
INSERT INTO Item_X_Invoice VALUES(17, 5, 8, 0);
INSERT INTO Item_X_Invoice VALUES(18, 5, 2, 0);
INSERT INTO Item_X_Invoice VALUES(19, 5, 7, 0);
INSERT INTO Item_X_Invoice VALUES(15, 6, 5, 0);
INSERT INTO Item_X_Invoice VALUES(17, 6, 1, 0);
INSERT INTO Item_X_Invoice VALUES(13, 6, 6, 0);
INSERT INTO Item_X_Invoice VALUES(9, 6, 10, 0);
INSERT INTO Item_X_Invoice VALUES(4, 6, 7, 0);
INSERT INTO Item_X_Invoice VALUES(10, 7, 6, 0);
INSERT INTO Item_X_Invoice VALUES(5, 7, 8, 0);
INSERT INTO Item_X_Invoice VALUES(12, 7, 4, 0);
INSERT INTO Item_X_Invoice VALUES(14, 7, 6, 0);
INSERT INTO Item_X_Invoice VALUES(20, 7, 9, 0);
INSERT INTO Item_X_Invoice VALUES(5, 8, 8, 0);
INSERT INTO Item_X_Invoice VALUES(16, 8, 6, 0);
INSERT INTO Item_X_Invoice VALUES(19, 8, 4, 0);
INSERT INTO Item_X_Invoice VALUES(3, 8, 8, 0);
INSERT INTO Item_X_Invoice VALUES(17, 8, 7, 0);
INSERT INTO Item_X_Invoice VALUES(16, 9, 6, 0);
INSERT INTO Item_X_Invoice VALUES(19, 9, 4, 0);
INSERT INTO Item_X_Invoice VALUES(13, 9, 8, 0);
INSERT INTO Item_X_Invoice VALUES(6, 9, 10, 0);
INSERT INTO Item_X_Invoice VALUES(14, 9, 9, 0);
INSERT INTO Item_X_Invoice VALUES(18, 10, 2, 0);
INSERT INTO Item_X_Invoice VALUES(8, 10, 6, 0);
INSERT INTO Item_X_Invoice VALUES(20, 10, 5, 0);
INSERT INTO Item_X_Invoice VALUES(7, 10, 3, 0);
INSERT INTO Item_X_Invoice VALUES(10, 10, 3, 0);
INSERT INTO Item_X_Invoice VALUES(3, 11, 2, 0);
INSERT INTO Item_X_Invoice VALUES(9, 11, 6, 0);
INSERT INTO Item_X_Invoice VALUES(19, 11, 10, 0);
INSERT INTO Item_X_Invoice VALUES(15, 11, 8, 0);
INSERT INTO Item_X_Invoice VALUES(12, 11, 9, 0);
INSERT INTO Item_X_Invoice VALUES(11, 12, 10, 0);
INSERT INTO Item_X_Invoice VALUES(16, 12, 7, 0);
INSERT INTO Item_X_Invoice VALUES(8, 12, 5, 0);
INSERT INTO Item_X_Invoice VALUES(6, 12, 1, 0);
INSERT INTO Item_X_Invoice VALUES(3, 12, 8, 0);
INSERT INTO Item_X_Invoice VALUES(8, 13, 4, 0);
INSERT INTO Item_X_Invoice VALUES(18, 13, 9, 0);
INSERT INTO Item_X_Invoice VALUES(2, 13, 8, 0);
INSERT INTO Item_X_Invoice VALUES(4, 13, 9, 0);
INSERT INTO Item_X_Invoice VALUES(1, 13, 1, 0);
INSERT INTO Item_X_Invoice VALUES(13, 14, 3, 0);
INSERT INTO Item_X_Invoice VALUES(6, 14, 1, 0);
INSERT INTO Item_X_Invoice VALUES(1, 14, 4, 0);
INSERT INTO Item_X_Invoice VALUES(5, 14, 3, 0);
INSERT INTO Item_X_Invoice VALUES(9, 14, 7, 0);
INSERT INTO Item_X_Invoice VALUES(16, 15, 3, 0);
INSERT INTO Item_X_Invoice VALUES(12, 15, 10, 0);
INSERT INTO Item_X_Invoice VALUES(2, 15, 1, 0);
INSERT INTO Item_X_Invoice VALUES(7, 15, 10, 0);
INSERT INTO Item_X_Invoice VALUES(17, 15, 6, 0);
INSERT INTO Item_X_Invoice VALUES(6, 16, 10, 0);
INSERT INTO Item_X_Invoice VALUES(8, 16, 5, 0);
INSERT INTO Item_X_Invoice VALUES(5, 16, 4, 0);
INSERT INTO Item_X_Invoice VALUES(19, 16, 3, 0);
INSERT INTO Item_X_Invoice VALUES(2, 16, 10, 0);
INSERT INTO Item_X_Invoice VALUES(4, 17, 1, 0);
INSERT INTO Item_X_Invoice VALUES(11, 17, 9, 0);
INSERT INTO Item_X_Invoice VALUES(12, 17, 2, 0);
INSERT INTO Item_X_Invoice VALUES(13, 17, 2, 0);
INSERT INTO Item_X_Invoice VALUES(6, 17, 6, 0);
INSERT INTO Item_X_Invoice VALUES(2, 18, 3, 0);
INSERT INTO Item_X_Invoice VALUES(1, 18, 2, 0);
INSERT INTO Item_X_Invoice VALUES(8, 18, 2, 0);
INSERT INTO Item_X_Invoice VALUES(7, 18, 1, 0);
INSERT INTO Item_X_Invoice VALUES(4, 18, 3, 0);
INSERT INTO Item_X_Invoice VALUES(14, 19, 2, 0);
INSERT INTO Item_X_Invoice VALUES(15, 19, 6, 0);
INSERT INTO Item_X_Invoice VALUES(20, 19, 7, 0);
INSERT INTO Item_X_Invoice VALUES(12, 19, 5, 0);
INSERT INTO Item_X_Invoice VALUES(10, 19, 7, 0);
INSERT INTO Item_X_Invoice VALUES(11, 20, 2, 0);
INSERT INTO Item_X_Invoice VALUES(16, 20, 4, 0);
INSERT INTO Item_X_Invoice VALUES(20, 20, 5, 0);
INSERT INTO Item_X_Invoice VALUES(3, 20, 8, 0);
INSERT INTO Item_X_Invoice VALUES(15, 20, 3, 0);


---------------------------------
--Estos updates hay que hacerlos ya que los campos de unitPrice, lineQuantity, y totalAmount inicialmente tienen un valor de 0
 UPDATE Item_X_Invoice set unitPrice = (select unitPrice from Item where Item.itemId = Item_X_Invoice.itemId);

 UPDATE Invoice
  SET totalAmount = (select sum(unitPrice * lineQuantity) from Item_X_Invoice where Invoice.invoiceNo = Item_X_Invoice.invoiceNo);

  UPDATE Invoice
  SET lineQuantity = (select sum(lineQuantity) from Item_X_Invoice where Invoice.invoiceNo = Item_X_Invoice.invoiceNo)
---------------------------------
