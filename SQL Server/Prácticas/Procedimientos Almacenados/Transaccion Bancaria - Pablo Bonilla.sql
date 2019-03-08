CREATE TABLE Cuentas (
	noCuenta int IDENTITY(1,1) PRIMARY KEY,
	nombre varchar(100),
	saldo money DEFAULT 1000 
)

CREATE TABLE Movimientos (
	idMovimiento int IDENTITY(1,1) PRIMARY KEY,
	cuentaOrigen int NOT NULL,
	cuentaDestino int NOT NULL,
	saldoOriginal money,
	saldoNuevo money,
	fecha datetime DEFAULT GETDATE(),
	montoTransaccion money
)



INSERT INTO Cuentas (nombre) VALUES ('Pablo');
INSERT INTO Cuentas (nombre) VALUES ('Angelica');


GO

ALTER PROC stp_transaccion @pCtaOrigen int, @pCtaDestino int, @pMonto money
AS BEGIN

IF EXISTS (SELECT noCuenta FROM Cuentas WHERE noCuenta = @pCtaOrigen)
	IF EXISTS (SELECT noCuenta FROM Cuentas WHERE noCuenta = @pCtaDestino)
		IF EXISTS (SELECT saldo FROM Cuentas WHERE noCuenta = @pCtaOrigen AND saldo >= @pMonto)
			BEGIN
				BEGIN TRAN
					BEGIN TRY
						
						UPDATE Cuentas SET saldo = (saldo-@pMonto) WHERE noCuenta = @pCtaOrigen;
						UPDATE Cuentas SET saldo = (saldo+@pMonto) WHERE noCuenta = @pCtaDestino;
						COMMIT TRAN
					END TRY

				
					BEGIN CATCH
						ROLLBACK TRAN
						SELECT ERROR_NUMBER() AS 'Error number', ERROR_SEVERITY() AS 'Error severity', ERROR_MESSAGE() AS 'Error message';
					END CATCH
			END
ELSE
	PRINT '[!] Transaction failed';


END

EXEC stp_transaccion @pCtaOrigen = 2, @pCtaDestino = 1, @pMonto = 1500;