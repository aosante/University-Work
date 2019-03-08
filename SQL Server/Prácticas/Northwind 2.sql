
  CREATE TABLE Departamento (
  idDepto int IDENTITY (1, 1) not null primary key,
  nombre varchar (15) not null
  );

  CREATE TABLE Empleado (
  cedula char (10) not null primary key,
  nombre varchar (15) not null,
  apellido varchar (15) not null,
  idDepto int null
  );

  CREATE TABLE Computadora (
  idCompu int IDENTITY (1, 1) not null primary key,
  marca varchar (15) not null
  );

  CREATE TABLE Computadora_Empleado (
  idCompu int not null,
  cedula char (10) not null
  );

  ALTER TABLE Computadora_Empleado ADD CONSTRAINT pk_Computador_Empleado PRIMARY KEY (idCompu, cedula);

  ALTER TABLE Empleado ADD CONSTRAINT fk_Empleado_idDepto FOREIGN KEY (idDepto) REFERENCES Departamento (idDepto);

  ALTER TABLE Computadora_Empleado ADD CONSTRAINT fk_Computadora_Empleado_idCompu FOREIGN KEY (idCompu) REFERENCES Computadora (idCompu);
  ALTER TABLE Computadora_Empleado ADD CONSTRAINT fk_Computadora_Empleado_cedula FOREIGN KEY (cedula) REFERENCES Empleado (cedula);

SELECT * FROM Departamento;







  INSERT INTO Departamento(nombre)
VALUES('Finanzas222')

INSERT INTO Computadora (marca)
VALUES ('DELL'), ('HP'), ('APPLE'), ('ASUS'), ('LENOVO');

INSERT INTO Empleado (cedula, nombre, apellido)
VALUES (1, 'Esteban','Alfaro'), (2, 'Melisa','Lizcano'), (3, 'Elias','Soley'), (4, 'Sofia','Mazon'), (5, 'Carlos','Wong');

INSERT INTO Empleado (idDepto)
VALUES (1), (2), (3), (4);

ALTER TABLE Empleado ALTER COLUMN idDepto

 SELECT * FROM Empleado;
SELECT * FROM Departamento
	SELECT * FROM Computadora;

 SELECT e.nombre, e.apellido, d.nombre as nombre_depto
    FROM Empleado e
    LEFT  JOIN Departamento d ON d.idDepto = e.idDepto;




UPDATE Orders
SET TotalDiscount = 100,
	TotalPrice = 100;

UPDATE Orders
SET TotalDiscount = (SELECT sum(UnitPrice * Quantity * Discount)
					FROM [Order Details]
					 WHERE [Order Details].OrderID= Orders.OrderID);


DELETE FROM [Order Details];