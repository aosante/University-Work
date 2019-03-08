/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    public AccesoBD(String driver, String conexion) throws SQLException, Exception {
        Class.forName(driver);
        conn = DriverManager.getConnection(conexion);
        st = conn.createStatement();
    }

    public void ejecutarSQL(String sentencia) throws SQLException, Exception {
        st.execute(sentencia);
    }

    public ResultSet ejecutarSQL(String sentencia, boolean retorno) throws SQLException, Exception {
        ResultSet rs;
        rs = st.executeQuery(sentencia);
        return rs;
    }

    public void iniciarTransaccion()
            throws java.sql.SQLException {
        conn.setAutoCommit(false);
    }

    public void terminarTransaccion()
            throws java.sql.SQLException {
        conn.setAutoCommit(true);
    }

    public void aceptarTransaccion()
            throws java.sql.SQLException {
        conn.commit();
    }

    public void deshacerTransaccion()
            throws java.sql.SQLException {
        conn.rollback();
    }

    protected void finalize() {
        try {
            conn.close();
        } catch (Exception e) {

        }
    }
    
    
    
}
