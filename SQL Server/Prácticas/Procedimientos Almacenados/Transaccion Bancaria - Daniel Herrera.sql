CREATE DATABASE dbBanco

CREATE TABLE cuentas
	(noCuenta int PRIMARY KEY IDENTITY(1,1),
	nombre varchar(100),
	saldo money DEFAULT 1000);

CREATE TABLE movimientos 
	(idMovimiento int PRIMARY KEY IDENTITY(1,1),
	cuentaOrigen int not null, --Agregar fk a noCuenta--
	cuentaDestino int not null, --Agregar fk a noCuenta--
	saldoOriginal money,
	saldoNuevo money,
	montoTransaccion money,
	fecha datetime DEFAULT GETDATE());

ALTER TABLE movimientos
ADD CONSTRAINT FK_noCuenta_cuentaOrigen
FOREIGN KEY (cuentaOrigen) REFERENCES cuentas(noCuenta);

ALTER TABLE movimientos
ADD CONSTRAINT FK_noCuenta_cuentaDestino
FOREIGN KEY (cuentaDestino) REFERENCES cuentas(noCuenta);
GO

-- Crear 2 cuentas --
INSERT INTO cuentas(nombre)
VALUES ('Daniel');

INSERT INTO cuentas(nombre)
VALUES ('David');

SELECT * FROM cuentas;
GO

--Script con transacciones y trycatch que simule una transaccion bancaria
CREATE PROCEDURE stp_transaccion @pCuentaOrigen int, @pCuentaDestino int, @pMonto money
AS BEGIN
	BEGIN TRAN
		BEGIN TRY
			IF NOT EXISTS(
				SELECT noCuenta
				FROM cuentas
				WHERE noCuenta = @pCuentaOrigen)
				THROW 50001, 'Cuenta de origen no existe', 1;
			IF NOT EXISTS(
				SELECT noCuenta
				FROM cuentas
				WHERE noCuenta = @pCuentaDestino)
				THROW 50002, 'Cuenta de destino no existe', 1;
			IF NOT EXISTS(
				SELECT noCuenta
				FROM cuentas
				WHERE noCuenta = @pCuentaOrigen AND saldo >= @pMonto)
				THROW 50003, 'El monto a transferir es menor al saldo', 1;
	
			DECLARE @saldoInicial money = (SELECT saldo FROM cuentas WHERE noCuenta = @pCuentaOrigen);
			DECLARE @saldoFinal money = (@saldoInicial - @pMonto);

			UPDATE cuentas
			SET saldo = @saldoFinal
			WHERE (noCuenta = @pCuentaOrigen);

			UPDATE cuentas
			SET saldo = saldo + @pMonto
			WHERE (noCuenta = @pCuentaDestino);

			INSERT INTO movimientos
				(cuentaOrigen, CuentaDestino, saldoOriginal, saldoNuevo, montoTransaccion)
			VALUES (@pCuentaOrigen, @pCuentaDestino, @saldoInicial, @saldoFinal, @pMonto);

			COMMIT TRAN
		END TRY
		BEGIN CATCH
			SELECT ERROR_MESSAGE();
			ROLLBACK TRAN
		END CATCH
END
--validar cuenta origen != cuenta destino

EXECUTE stp_transaccion @pCuentaOrigen = 1, @pCuentaDestino = 2, @pMonto = 1000;

SELECT * FROM cuentas