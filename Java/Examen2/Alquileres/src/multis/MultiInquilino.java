/**
 * Esta clase se encarga de registrar, buscar, y listar inquilinos
 */
package multis;

import acceso.Conector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.Inquilino;



/**
 *
 * @author andresosante
 */
public class MultiInquilino {
    /**
     * 
     * @param pinquilino inquilino a registrar
     * @throws SQLException excepcion en caso de que el inquilino ya exista 
     * @throws Exception excepcion
     */
    public void registrarInquilino(Inquilino pinquilino) throws SQLException, Exception {
        String sql;
        sql = "INSERT INTO INQUILINO (nombre, apellidos, correo, direccion, telefono, cedula, genero) VALUES( '" + pinquilino.getNombre() + "', '" +
                pinquilino.getApellidos() + "', '" + pinquilino.getCorreo() + "', '" + pinquilino.getDireccion() + "', '" + pinquilino.getTelefono()+ "', " + pinquilino.getId() +
                ", '" + pinquilino.getGenero() + "' );";
        try {
            Conector.getConector().ejecutarSQL(sql);
        } catch(Exception e) {
            throw new Exception("Ya existe un inquilino registrado con ese número de cédula");
        }
    }
    
    /**
     * 
     * @return arraylist de inquilinos
     * @throws SQLException excepcion de SQL en caso de que no hayan inquilinos registrados
     * @throws Exception excepcion
     */
    public ArrayList<Inquilino> listarInquilinos() throws SQLException, Exception {
        ArrayList<Inquilino> lista = new ArrayList();
        String sql;
        sql = "SELECT * FROM INQUILINO";
        try {
            ResultSet rs = null;
            rs = Conector.getConector().ejecutarSQL(sql, true);
            while(rs.next()) {
                Inquilino inqui = new Inquilino(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), rs.getString("direccion"), rs.getString("telefono"), rs.getInt("cedula"), rs.getString("genero").charAt(0));
                lista.add(inqui);
            }
            rs.close();
        }catch(Exception e) {
            throw new Exception("No hay inquilinos registrados en el sistema");
        }
        return lista;
    }
    /**
     * 
     * @param id cedula del inquilino
     * @return el inquilino buscado
     * @throws SQLException excepcion de SQL en caso de que el inquilino buscado no exista
     * @throws Exception excepcion
     */
    public Inquilino buscarInquilino(int id) throws SQLException, Exception {
        String sql;
        sql = "SELECT * FROM INQUILINO WHERE cedula = " + id + ";";
        Inquilino inqui = null;
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        if(rs.next()) {
            inqui = new Inquilino(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), rs.getString("direccion"), rs.getString("telefono"), rs.getInt("cedula"), rs.getString("genero").charAt(0)); 
        } else {
            throw new Exception("No existe ningun inquilino registrado bajo ese número de cédula");
        }
        rs.close();
        return inqui;
    }
    
}
