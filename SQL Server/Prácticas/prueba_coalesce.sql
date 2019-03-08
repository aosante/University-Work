CREATE TABLE salarios_coalesce
(  
    empleado_id     int         identity(1,1) primary key,  
    salario_hora    decimal     NULL,
    salario_mes     decimal     NULL,
    comision        decimal     NULL,
    cant_ventas     int         NULL
);  
GO  

INSERT salarios_coalesce (salario_hora, salario_mes, comision, cant_ventas)  
VALUES  
    (10.00, NULL, NULL, NULL),  
    (12.00, NULL, NULL, NULL),  
    (15.00, NULL, NULL, NULL),  
    (20.00, NULL, NULL, NULL),  
    (NULL, 1000.00, NULL, NULL),  
    (NULL, 2000.00, NULL, NULL),  
    (NULL, 3000.00, NULL, NULL),  
    (NULL, 4000.00, NULL, NULL),  
    (NULL, NULL, 500, 3),  
    (NULL, NULL, 700, 2),  
    (NULL, NULL, 400, 6),  
    (NULL, NULL, 550, 4);  
GO  





CREATE TABLE telefonos_coalesce
(  
    persona_id      int         identity(1,1) primary key,  
    telefono1       varchar(10) NULL,
    telefono2       varchar(10) NULL,
    telefono3       varchar(10) NULL
);  
GO  

INSERT telefonos_coalesce (telefono1, telefono2, telefono3)  
VALUES  
    ('7387-3782', NULL, NULL),  
    ('8472-8560', '8124-9857', NULL),  
    (NULL, NULL, '9289-2783'),  
    (NULL, NULL, NULL);  
GO

SELECT persona_id, COALESCE(telefono1, telefono2, telefono3, 'No tiene teléfono') FROM telefonos_coalesce;
--otras funciones
SELECT CONCAT('Nombre ', 'Apellido1 ', 'Apellido2') as resultado;

SELECT CONCAT_WS('-', 'Andres', 'Osante', 'SSS') as resultado;

SELECT LTRIM('   3 espacios   ') as resultado;
SELECT RTRIM('   3 espacios   ') as resultado;
SELECT TRIM('   3 espacios   ') as resultado;

SELECT getdate(), CONCAT_WS(',', day(getdate()), month(getdate()), year(getdate())) as Fecha;




