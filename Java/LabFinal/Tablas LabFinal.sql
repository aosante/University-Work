create database LABORATORIO_FINAL;

USE LABORATORIO_FINAL;

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

select * from PROPIEDAD;
select * from INQUILINO;
select * from CONTRATO;
