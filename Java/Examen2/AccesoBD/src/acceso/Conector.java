/**
 * Esta clase lee el driver y el url de la conexion con la base de datos
 * desde un archivo de texto y le envía estos datos al constructor de AccesoBD para crear la conexion 
 */
package acceso;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author andresosante
 */
public class Conector {
      private static AccesoBD conectorBD = null;
    
    private static String driver = "";
    private static String dburl = "";
    
    
    /**
     * 
     * @return conector de tipo AccesoBD
     * @throws SQLException excepcion de SQL
     * @throws Exception excepcion
     * @throws IOException excepcion de input y output
     */
    public static AccesoBD getConector() throws SQLException, Exception, IOException {
        String[] datosConexion = getDatos();
        driver = datosConexion[0];
        dburl = datosConexion[1];
       if(conectorBD == null) {
            conectorBD = new AccesoBD(driver, dburl);
       }
       return conectorBD;
    }
    /**
     * 
     * @return array de string con los datos de la conexion
     * @throws IOException excepcion de input y output
     */
    public static String[] getDatos() throws IOException{
        TextFileIO conexion = new TextFileIO("alquileres.txt");
        ArrayList<String> lista = conexion.getData();
        String[] listaDatos = new String[lista.size()];
        int i = 0;
        for(String dato : lista) {
            listaDatos[i] = dato;
            i++;
        }
        return listaDatos;
    }
}
