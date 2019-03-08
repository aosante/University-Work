CREATE DATABASE Proyecto

USE Proyecto

CREATE TABLE Region (   --Created by Andres
 regionId int IDENTITY (1, 1) NOT NULL PRIMARY KEY ,
 descripcion VARCHAR(50) NOT NULL
)


CREATE TABLE Supplier( --Created by Andres
 supplierId int IDENTITY (1, 1) NOT NULL PRIMARY KEY ,
 name VARCHAR(20) NOT NULL,
 address VARCHAR(50) NOT NULL,
 telephone VARCHAR(15) NOT NULL
);

CREATE  TABLE Client ( --Created by Andres
 clientId INT IDENTITY (1, 1) NOT NULL PRIMARY KEY ,
 name VARCHAR(20) NOT NULL,
 lastName VARCHAR(20) NOT NULL,
 address VARCHAR(50) NOT NULL,
 gender CHAR(1) NOT NULL,
 telephone VARCHAR(15) NOT NULL,
 regionId INT NOT NULL
)

CREATE TABLE Employee ( --Created by Andres
 employeeId INT IDENTITY (1, 1) NOT NULL PRIMARY KEY ,
 name VARCHAR(20) NOT NULL,
 lastName VARCHAR(20) NOT NULL,
 gender CHAR(1) NOT NULL,
 hireDate DATE NOT NULL,
 rating INT NOT NULL
)

CREATE TABLE Category(          --Created by Alex
  categoryId int IDENTITY (1, 1) NOT NULL PRIMARY KEY,
  description varchar(50),
)


CREATE TABLE Item(             --Created by Alex
  itemId int IDENTITY (1, 1) NOT NULL PRIMARY KEY ,
  description varchar(50),
  cost money,
  unitPrice money,
  barcode varchar(50) NOT NULL UNIQUE,
  status varchar(15) NOT NULL DEFAULT 'Active', --(Active/Inactive)
  supplierId int NOT NULL, --FK
  categoryId int NOT NULL, --FK
)

CREATE TABLE Item_X_Invoice ( --created by Andres
  itemId INT NOT NULL,
  invoiceNo INT NOT NULL,
  lineQuantity INT NOT NULL,--FK
  unitPrice MONEY NOT NULL--FK
)

CREATE TABLE InvoiceType( --Created by Alex
  invoiceTypeId int  IDENTITY (1,1) NOT NULL PRIMARY KEY, --PK
  description varchar(50),
)


CREATE TABLE Discount( --Created by Alex
  discountId int IDENTITY (1, 1) NOT NULL PRIMARY KEY, --PK
  description varchar(50),
  percentage float
)


CREATE TABLE Invoice( --Created by Alex
  invoiceNo int IDENTITY (1, 1) NOT NULL PRIMARY KEY, --PK
  description varchar(50),
  creationDate Date,
  status varchar(15) NOT NULL DEFAULT 'Open', --(Open / Close)
  totalAmount money,
  lineQuantity int NOT NULL ,
  invoiceTypeId int NOT NULL, --FK
  clientId int NOT NULL, --FK
  discountId int NULL, --FK --puede aceptar nulls
  employeeId int NOT NULL --FK
)


  -- Constraints for table Region
ALTER TABLE Region ADD CONSTRAINT chk_region_descripcion CHECK (descripcion IN ('Norte', 'Central', 'Sur'));

--Constraints for table Client
ALTER TABLE Client ADD CONSTRAINT fk_client_regionId FOREIGN KEY (regionId) REFERENCES  Region (regionId);
ALTER TABLE Client ADD CONSTRAINT chk_client_gender CHECK (gender IN ('M', 'F'));

--Constraints for table Employee
ALTER TABLE Employee ADD CONSTRAINT  chk_employee_gender CHECK (gender IN ('M', 'F'));

-- Constraints for table Invoice
ALTER TABLE Invoice
  ADD CONSTRAINT chk_invoice_status CHECK (status IN ('Open', 'Close'));
ALTER TABLE Invoice
  ADD CONSTRAINT fk_invoiceTypeId FOREIGN KEY (invoiceTypeId) REFERENCES InvoiceType (invoiceTypeId);
ALTER TABLE Invoice
  ADD CONSTRAINT fk_clientId FOREIGN KEY (clientId) REFERENCES Client(clientId);
ALTER TABLE Invoice
  ADD CONSTRAINT fk_discountId FOREIGN KEY (discountId) REFERENCES Discount(discountId);
ALTER TABLE Invoice
  ADD CONSTRAINT fk_employeeId FOREIGN KEY (employeeId) REFERENCES Employee(employeeId);


-- Constraints for table Item
ALTER TABLE Item
  ADD CONSTRAINT chk_item_status CHECK (status IN ('Active', 'Inactive'));
ALTER TABLE Item
  ADD CONSTRAINT fk_supplierId FOREIGN KEY (supplierId) REFERENCES Supplier(supplierId);
ALTER TABLE Item
  ADD CONSTRAINT fk_categoryId FOREIGN KEY (categoryId) REFERENCES Category(categoryId);

-- Constraints for table Item_X_Invoice
ALTER TABLE Item_X_Invoice ADD CONSTRAINT  pk_item_x_invoice PRIMARY KEY (itemId, invoiceNo);
ALTER TABLE Item_X_Invoice ADD CONSTRAINT fk_item_x_invoice_itemId FOREIGN KEY (itemId) REFERENCES Item (itemId);
ALTER TABLE Item_X_Invoice ADD CONSTRAINT fk_item_x_invoice_invoiceNo FOREIGN KEY (invoiceNo) REFERENCES Invoice (invoiceNo);
