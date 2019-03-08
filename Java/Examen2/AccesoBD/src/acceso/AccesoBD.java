/**
 * Esta clase grea la conexion con la base de datos y ejecuta el codigo de SQL que se pase 
 * como argumentos en sus métodos
 */
package acceso;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author andresosante
 */
public class AccesoBD {

    private Connection conn = null;
    private Statement st;
    private CallableStatement proc;
/**
 * 
 * @param driver de sql server
 * @param conexion conexion con la base de datos
 * @throws SQLException excepcion de SQL
 * @throws Exception excepcion
 */
    public AccesoBD(String driver, String conexion) throws SQLException, Exception {
        Class.forName(driver);
        conn = DriverManager.getConnection(conexion);
        st = conn.createStatement();
    }

    /**
     * 
     * @param sentencia de SQL
     * @throws SQLException excepcion de SQL
     * @throws Exception excepcion
     */
    public void ejecutarSQL(String sentencia) throws SQLException, Exception {
        st.execute(sentencia);
    }
/**
 * 
 * @param sentencia de SQL
 * @param retorno booleano de que sí devuelve un resultado
 * @return ResultSet con los datos de la base de datos
 * @throws SQLException excepcion de SQL
 * @throws Exception excepcion 
 */
    public ResultSet ejecutarSQL(String sentencia, boolean retorno) throws SQLException, Exception {
        ResultSet rs;
        rs = st.executeQuery(sentencia);
        return rs;
    }
/**
 * 
 * @throws java.sql.SQLException excepcion de SQL
 */
    public void iniciarTransaccion()
            throws java.sql.SQLException {
        conn.setAutoCommit(false);
    }
/**
 * 
 * @throws java.sql.SQLException excepcion de SQL
 */
    public void terminarTransaccion()
            throws java.sql.SQLException {
        conn.setAutoCommit(true);
    }
/**
 * 
 * @throws java.sql.SQLException excepcion de SQL
 */
    public void aceptarTransaccion()
            throws java.sql.SQLException {
        conn.commit();
    }
/**
 * 
 * @throws java.sql.SQLException excepcion de SQL
 */
    public void deshacerTransaccion()
            throws java.sql.SQLException {
        conn.rollback();
    }
/**
 * cierra la conexion
 */
    protected void finalize() {
        try {
            conn.close();
        } catch (Exception e) {

        }
    }
}
