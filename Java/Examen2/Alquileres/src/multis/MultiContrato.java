/**
 * Esta clase se encarga de registrar, modificar, y eliminar contratos.
 * Adicionalmente, verifica si las propiedades e inquilinos ya han sido asignados a 
 * un contrato ya que estos no se pueden repetir
 */
package multis;

import acceso.Conector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import logica.Contrato;



/**
 *
 * @author andresosante
 */
public class MultiContrato {
    
    /**
     * 
     * @param alquiler contrato a registrar
     * @throws SQLException excepcion del query de SQL. No permite duplicar codigo del contato
     * @throws Exception excepcion
     */
    public void registrarContrato(Contrato alquiler) throws SQLException, Exception {
        String sql;
        sql = "INSERT INTO CONTRATO (cedulaInquilino, codPropiedad, fechaInicio, duracionContrato, fechaFin, montoAlquiler, porcentajeAumento, moneda, codContrato) VALUES ("
                + alquiler.getInquilinoAsignado().getId() + ", " + alquiler.getPropiedadAsignada().getCodPropiedad() + ", '" + alquiler.getFechaInicio() + "', " + alquiler.getDuracionContrato() + ", '" + alquiler.getFechaFin() + "', " + alquiler.getMontoAlquier() + ", " + alquiler.getPorcentajeAumentoAnual() + ", '" + alquiler.getMoneda() + "', " + alquiler.getCodContrato() + " );";
        try {
            Conector.getConector().ejecutarSQL(sql);
        } catch(Exception e) {
            throw new Exception("El inquilino o la propiedad asignadas no existen en el sistema. Inténtelo de nuevo.");
        }
    }
    
    /**
     * 
     * @return arraylist de los codigos de propiedad ya asociados a un contrato
     * @throws SQLException excepcion del query de sql si no encuentra propiedades asignadas
     * @throws Exception excepcion
     */
    public ArrayList<Integer> propiedadesAsignadas() throws SQLException, Exception {
        String sql;
        sql = "SELECT codPropiedad FROM PROPIEDAD WHERE codPropiedad IN (SELECT codPropiedad FROM CONTRATO);";
        ArrayList<Integer> props = new ArrayList();
        try {
            ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
            while(rs.next()) {
                props.add(rs.getInt("codPropiedad"));
            }
            rs.close();
        } catch(Exception e) {
            throw new Exception ("No hay propiedades ya asignadas.");
        }
        return props;
    }
    
    /**
     * 
     * @return arraylist de enteros con las cedulas de los inquilinos ya asociados a un contrato
     * @throws SQLException excepcion si el query no encuentra inquilinos asociados
     * @throws Exception excepcion 
     */
    public ArrayList<Integer> inquilinosAsignados() throws SQLException, Exception {
        String sql;
        sql = "SELECT cedula FROM INQUILINO  WHERE cedula IN (SELECT cedulaInquilino FROM CONTRATO);";
        ArrayList<Integer> props = new ArrayList();
        try {
            ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
            while(rs.next()) {
                props.add(rs.getInt("cedula"));
            }
            rs.close();
        } catch(Exception e) {
            throw new Exception ("No hay inquilinos ya asignados.");
        }
        return props;
    }
    
    /**
     * 
     * @param pcedula del inquilino a asociar
     * @param codPropiedad codigo de la propiedad a asociar
     * @param fechaInicio fecha de inicio del contrato
     * @param duracionContrato duracion del contrato
     * @param montoAlquiler monto del alquiler del conrtato
     * @param porcentajeAumentoAnual porcentaje de aumento anual del contrato
     * @param moneda en la que se va a manejar el alquiler
     * @param codContrato codigo del contrato
     * @throws SQLException excepcion del query si se pasa un codigo de contrato que no existe
     * @throws Exception excepcion
     */
    public void modificarContrato(int pcedula, int codPropiedad, LocalDate fechaInicio, int duracionContrato, double montoAlquiler, double porcentajeAumentoAnual, String moneda, int codContrato) throws SQLException, Exception {
        LocalDate fechaFin = fechaInicio.plusYears(duracionContrato);
        String sql;
        sql = "UPDATE CONTRATO SET cedulaInquilino = " + pcedula + ", codPropiedad = " + codPropiedad + ", fechaInicio = '" + fechaInicio + "', duracionContrato = " + duracionContrato + ", fechaFin = '" + fechaFin + "', montoAlquiler = " + montoAlquiler + ", porcentajeAumento = " + porcentajeAumentoAnual + ", moneda = '" + moneda + "' WHERE codContrato = " + codContrato + ";";
        try {
            Conector.getConector().ejecutarSQL(sql);
        }catch(Exception e) {
            throw new Exception("El contrato a modificar no se encuentra en el sistema");
        }
    }
    
    /**
     * 
     * @param codContrato codigo del contrato 
     * @throws SQLException excepcion de sql 
     * @throws Exception excepcion 
     */
    public void limpiarContrato(int codContrato) throws SQLException, Exception {
        String sql;
        sql = "UPDATE CONTRATO SET cedulaInquilino = NULL, codPropiedad = NULL WHERE codContrato = " + codContrato + ";";
        try {
            Conector.getConector().ejecutarSQL(sql);
        } catch(Exception e) {
            throw new Exception("Ocurrió un error al limpiar los datos");
        }
    }
    
    /**
     * 
     * @return string con los contratos registrados
     * @throws SQLException excepcion si el query no devuelve ningun contrato
     * @throws Exception excepcion
     */
    public String listarContratos() throws SQLException, Exception {
        String sql, lista = "";
        sql = "SELECT c.codContrato as codigoContrato, p.nombre as nombrePropiedad, i.nombre as nombreInquilino, c.fechaInicio as fechaInicio FROM PROPIEDAD p INNER JOIN CONTRATO c ON (p.codPropiedad = c.codPropiedad) INNER JOIN INQUILINO i ON (i.cedula = c.cedulaInquilino)";
        try {
            ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
            while(rs.next()) {
                lista += "Código del contrato: " + rs.getInt("codigoContrato")+ ", Nombre de la propiedad: " + rs.getString("nombrePropiedad") + ", nombre del inquilino: " + rs.getString("nombreInquilino") + ", fecha de inicio: " + rs.getDate("fechaInicio") + "\n";
            }
            rs.close();
        }catch(Exception e) {
            throw new Exception("No hay contratos registrados en el sistema");
        }
        return lista;
    }
    
}
