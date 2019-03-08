CREATE FUNCTION sumaDosNumeros (
  @pnum1 INT, @pnum2 INT
)
  RETURNS INT
  AS
  BEGIN
DECLARE @suma INT = (@pnum1 + @pnum2);
    RETURN @suma
END

  SELECT dbo.sumaDosNumeros(60, 90) AS Suma;

CREATE FUNCTION sumaTresNumeros(
  @pnum1 INT, @pnum2 INT, @pnum3 INT
)
  RETURNS INT
  AS
  BEGIN
    DECLARE @suma INT = dbo.sumaDosNumeros (@pnum1, @pnum2) + @pnum3
      RETURN @suma
  END

SELECT dbo.sumaTresNumeros(3, 3, 3) AS SumadeTres;

