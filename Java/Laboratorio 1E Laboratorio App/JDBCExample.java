/*

NOTA: PARA LOS QUE USAN SQL SERVER ESTO ES LO QUE DEBEN HACER SI VAN A USAR AUTENTICACIÓN INTEGRADA

1. DESCARGAR EL DIRVER Y DESOOMPRIMIRLO. ESTO LES CREARÁ UNA CARPETA Microsoft JDBC Driver 6.0 for SQL Server
2. DEBER IR A LA CARPETA \sqljdbc_6.0\enu\auth\x64, SI USA 64 BITS O X86 EN CASO CONTRARIO.
3. DEBE TOMAR LA DLL sqljdbc_auth.dll Y COPIARLA EN LA CARPETA BIN UBICADA EN EL DIRECTORIO DONDE ESTÁ INTALADO JAVA, POR LO GENERAL
   C:\Program Files\Java\jdk1.8.0_92\bin.
4. LUEGO DEBE CERRAR EL IDE RESPECTIVO Y VOLVERLO A INICIAL.
 */

import java.sql.*;
public class JDBCExample{
    public static void main (String args[]) {
        try {
            MySql();
            //sqlServer();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void MySql()
    {
       try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            conn = DriverManager.getConnection("jdbc:mysql://localhost/laboratorio?" +
                    "user=root&password=root");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM personas");
            while(rs.next())
            {
                System.out.println(rs.getString("nombre"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public  static void sqlServer()
    {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
/* ESTA ES LA LINEA DE CÓDIGO PARA CUANDO NO SE USA AUTENTICACIÓN INTEGRADA. COMO SE VE,
DEBE INCLUIR EL USUARIO Y LA CLAVE.
            String connectionUrl = "jdbc:sqlserver://DESKTOP-R3FHVB2\\BANKAI;DatabaseName=laboratorio;user=sa;password=123456";
            */
/* ESTA ES LA LINEA DE CÓDIGO PARA CUANDO SE USA AUTENTICACIÓN INTEGRADA (FAVOR VER NOTAS AL INICIO) COMO SE VE SOLO BASTA CON EL
NOMBRE DE LA BASE DE DATOS Y AGREGAR EL SIGUIENTE PARÁMETRO AL FINAL integratedsecurity = true*/

            String connectionUrl = "jdbc:sqlserver://DESKTOP-R3FHVB2\\BANKAI;DatabaseName=laboratorio;integratedsecurity = true";
            conn = DriverManager.getConnection(connectionUrl);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Persona");
            while(rs.next())
            {
                System.out.println(rs.getString("nombre"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}


