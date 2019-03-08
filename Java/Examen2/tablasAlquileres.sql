--CREATE DATABASE ALQUILERES

USE ALQUILERES

CREATE TABLE PROPIEDAD (
  codPropiedad INT NOT NULL PRIMARY KEY,
  nombre VARCHAR(100),
  valor MONEY,
  direccion VARCHAR(100),
  residencial VARCHAR(100),
  numeroCasa INT,
  tienePatio BIT NOT NULL,
  cantidadHabitaciones INT
)

CREATE TABLE INQUILINO(
  nombre VARCHAR(100),
  apellidos VARCHAR(100),
  correo VARCHAR(100),
  direccion VARCHAR(100),
  telefono VARCHAR(100),
  cedula INT NOT NULL PRIMARY KEY,
  genero CHAR(1)
)

CREATE TABLE CONTRATO (
  cedulaInquilino INT UNIQUE ,
  codPropiedad INT UNIQUE,
  fechaInicio DATE,
  duracionContrato INT,
  fechaFin DATE,
  montoAlquiler MONEY,
  porcentajeAumento DECIMAL(15,4),
  moneda VARCHAR(100),
  codContrato INT PRIMARY KEY NOT NULL
)

CREATE TABLE REPARACION (
  codigo INT NOT NULL PRIMARY KEY,
  descripcion VARCHAR(100),
  fechaARealizar DATE,
  codPropiedad INT,
  proveedor VARCHAR(100),
  fechaRealizacion DATE,
  costo MONEY
)

  CREATE TABLE FACTURA (
    numero INT IDENTITY (1, 1) PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    nombrePropiedad VARCHAR(100),
    fecha DATE,
    subTotal MONEY,
    impuesto MONEY,
    total MONEY
  )



select * from PROPIEDAD
select * from CONTRATO
select * from REPARACION
select * from INQUILINO
select * from FACTURA

