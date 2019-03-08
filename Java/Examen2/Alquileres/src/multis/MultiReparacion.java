
/**
 * Esta clase se encarga de registrar, buscar, y listar las reparaciones 
 * de la aplicación
 */
package multis;

import acceso.Conector;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Propiedad;
import logica.RepNormal;



/**
 *
 * @author andresosante
 */
public class MultiReparacion {
    /**
     * 
     * @param reparacion reparacion a programar
     * @throws SQLException excepcion de SQL
     * @throws Exception excepcion normal
     */
    public void programarReparacion(RepNormal reparacion) throws SQLException, Exception {
        String sql;
        sql = "INSERT INTO REPARACION (codigo, descripcion, fechaARealizar, codPropiedad) VALUES ( "
                + reparacion.getCodigo() + ", '" + reparacion.getDescripcion() + "', '" + reparacion.getFechaRealizar() + "', " + reparacion.getPropiedadAsignada().getCodPropiedad() + ");";
        try {
            Conector.getConector().ejecutarSQL(sql);
        } catch(Exception e) {
            throw new Exception("Ya existe una reparación registrada bajo ese código");
        }
    }
    /**
     * 
     * @param pcodigo de la reparacion programada
     * @return la reparacion a la cual se le van a agregar datos
     * @throws SQLException excepcion de SQL
     * @throws Exception excepcion 
     */
    public RepNormal buscarReparacionProgramada(int pcodigo) throws SQLException, Exception {
        String sql;
        sql = "SELECT * FROM REPARACION WHERE codigo = " + pcodigo + ";";
        RepNormal reparacion = null;
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        
        if(rs.next()) {
            Propiedad prop = new Propiedad();
            reparacion = new RepNormal(rs.getInt("codigo"), rs.getString("descripcion"), rs.getDate("fechaARealizar").toLocalDate(), prop);
        } else {
            throw new Exception("No hay reparaciones programadas bajo ese código");
        }
            rs.close();
            return reparacion;
        }
    
    /**
     * 
     * @param rep reparacion a registrar
     * @throws SQLException excepcion de SQL
     * @throws Exception excepcion
     */
    public void registrarReparacionProgramada(RepNormal rep) throws SQLException, Exception {
        String sql;
        sql = "UPDATE REPARACION SET proveedor = '" + rep.getProveedor() + "', fechaRealizacion = '" + rep.getFechaRealizacion() + "', costo = " + rep.getCosto() + " WHERE codigo = " + rep.getCodigo() + ";";
        try {
            Conector.getConector().ejecutarSQL(sql);
        }catch(Exception e) {
            throw new Exception ("Ocurrió un error a la hora de registrar una reparacion ya programada");
        }
}
    /**
     * 
     * @param reparacion reparacion a registrar
     * @throws SQLException excepcion de sql
     * @throws Exception excepcion
     */
    public void registrarReparacionNormal(RepNormal reparacion) throws SQLException, Exception {
        String sql;
        sql = "INSERT INTO REPARACION VALUES( " + reparacion.getCodigo() + ", '" + reparacion.getDescripcion() + "', '" + reparacion.getFechaRealizar() + "', " + reparacion.getPropiedadAsignada().getCodPropiedad() + 
                ", '" + reparacion.getProveedor() + "', '" + reparacion.getFechaRealizacion() + "', " + reparacion.getCosto() + " );";
        try {
            Conector.getConector().ejecutarSQL(sql);
        }catch(Exception e) {
            throw new Exception("Ya hay una reparación registrada bajo ese código");
        }
    }
    
    /**
     * 
     * @return string de reparaciones
     * @throws SQLException excepcion de SQL
     * @throws Exception excepcion
     */
    public String listarReparaciones() throws SQLException, Exception {
        String lista = "";
        RepNormal rep = null;
        String sql;
        sql = "SELECT * FROM REPARACION;";
        try {
            ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
            while(rs.next()) {
                lista += "Fecha a realizar: " + rs.getDate("fechaARealizar") + ", encargado: " + rs.getString("proveedor") + ", costo: " + rs.getInt("costo") + "\n";
            }
            rs.close();
        } catch(Exception e) {
            throw new Exception("No hay reparaciones registradas");
        }
        return lista;
    }
    
    
    }




