/**
 * Esta clase se encarga de registrar y obtener las facturas
 * de los inquilinos que tengan contrato
 */
package multis;

import acceso.Conector;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Contrato;
import logica.Factura;
import logica.Inquilino;
import logica.Propiedad;

/**
 *
 * @author andresosante
 */
public class MultiFactura {
    
    
    /**
     * 
     * @param nombre del inquilino
     * @param apellido del inquilino
     * @return la cedula del inquilino
     * @throws SQLException excepcion si falla el query de sql
     * @throws Exception excepcion
     */
    public int obtenerCedulaInquilino(String nombre, String apellido) throws SQLException, Exception {
        int cedula;
        String sql;
        sql = "SELECT cedula FROM INQUILINO WHERE nombre = '" + nombre + "' AND apellidos = '" + apellido + "';";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        if(rs.next()) {
            cedula = rs.getInt("cedula");
        } else {
            throw new Exception("No existe nigún inquilino registrado bajo ese nombre. Asegúrese de digitar el nombre con exactitud");
        }
        rs.close();
        return cedula;
    }
    
    /**
     * 
     * @param nombre del inquilino
     * @param apellido dei inquilino
     * @return el codigo de propiedad del inquilino
     * @throws SQLException excepcion si falla el query de SQL
     * @throws Exception excepcion
     */
    public int obtenerCodigoPropiedad(String nombre, String apellido) throws SQLException, Exception {
        int cedula = obtenerCedulaInquilino(nombre, apellido);
        int codigo;
        String sql;
        sql = "SELECT codPropiedad FROM CONTRATO WHERE cedulaInquilino = " + cedula + ";";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        if(rs.next()) {
            codigo = rs.getInt("codPropiedad");
        } else {
            throw new Exception("El inquilino digitado no tiene un contrato aún. Inténtelo de nuevo");
        }
        rs.close();
        return codigo;
    }
    
    /**
     * 
     * @param codigo codigo de la propiedad asociada con el inquilino
     * @return el nombre de la propiedad
     * @throws SQLException excepcion si falla el query SQL
     * @throws Exception excepcion
     */
    public String obtenerNombrePropiedad(int codigo) throws SQLException, Exception {
        String nombre = "";
        Propiedad prop = (new MultiPropiedad().buscarPropiedad(codigo));
        nombre = prop.getNombre();
        return nombre;
    }
    
    /**
     * 
     * @param codPropiedad codigo de la propiedad
     * @return conrtato asociado con la propiedad
     * @throws SQLException excepcion de SQL 
     * @throws Exception excepcion 
     */
    public Contrato obtenerContrato(int codPropiedad) throws SQLException, Exception {
        Contrato cont = null;
        String sql;
        sql = "SELECT * FROM CONTRATO WHERE codPropiedad = " + codPropiedad + ";";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        if(rs.next()) {
            Inquilino inqui = new Inquilino();
            Propiedad prop = new Propiedad();
            cont = new Contrato(inqui, prop, rs.getDate("fechaInicio").toLocalDate(), rs.getInt("duracionContrato"), rs.getDouble("montoAlquiler"), rs.getDouble("porcentajeAumento"), rs.getString("moneda"), rs.getInt("codContrato"));
        } else {
            throw new Exception("Ocurrió un error al obtener el contrato a facturar");
        }
        rs.close();
        return cont;
    }
    
    /**
     * 
     * @param fact factura a registrar
     * @throws SQLException excepcion si falla el query de SQL
     * @throws Exception excepcion 
     */
    public void registrarFactura(Factura fact) throws SQLException, Exception {
        String sql;
        sql = "INSERT INTO FACTURA VALUES ( '" + fact.getNombreCliente() + "', '" + fact.getApellido() + "', '" + fact.getNombrePropiedad() + "', '" + fact.getFecha() + "', " + fact.getSubTotal() + ", " + fact.getImpuesto() + ", " + fact.getTotal() + " );";
        try {
            Conector.getConector().ejecutarSQL(sql);
        }catch(Exception e) {
            throw new Exception("Ocurrió un error al registrar la factura");
        }
    }
    
    /**
     * 
     * @param nombre  del inquilino 
     * @param apellido del inquilino
     * @return factura registrada
     * @throws SQLException excepcion si falla el query de SQL
     * @throws Exception excepcion 
     */
    public Factura listarFactura(String nombre, String apellido) throws SQLException, Exception {
        String sql;
        sql = "SELECT * FROM FACTURA WHERE nombre = '" + nombre + "' AND apellido = '" + apellido + "';";
        ResultSet rs = null;
        Factura fact = null;
        rs = Conector.getConector().ejecutarSQL(sql, true);
        if(rs.next()){
            fact = new Factura(rs.getInt("numero"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("nombrePropiedad"), rs.getDate("fecha").toLocalDate(), rs.getDouble("subTotal"), rs.getDouble("impuesto"));
        } else {
            throw new Exception("No hay facturas registradas con ese nombre de inquilino");
        }
        rs.close();
        return fact;
    }
    
}
