CREATE DATABASE laboratorio;

USE laboratorio;

CREATE TABLE EMPLEADO(
  cedula VARCHAR(20) NOT NULL PRIMARY KEY,
  nombre VARCHAR(20),
  apellido VARCHAR(20),
  direccion VARCHAR(20),
  telefono VARCHAR(20),
  puesto VARCHAR(20)
)



CREATE TABLE PROFESOR (
  cedula             VARCHAR(100) NOT NULL PRIMARY KEY,
  nombre             VARCHAR(100),
  apellido           VARCHAR(100),
  direccion          VARCHAR(100),
  telefono           VARCHAR(100),
  lugarDeTrabajo     VARCHAR(100),
  aniosDeExperiencia VARCHAR(100)
)


CREATE TABLE LABORATORIO (
  codigo INT NOT NULL PRIMARY KEY,
  nombre VARCHAR(100),
  capacidad INT
)



