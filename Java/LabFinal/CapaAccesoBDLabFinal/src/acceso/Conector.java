/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author andresosante
 */
public class Conector  {
    
    private static AccesoBD conectorBD = null;
    
    private static String driver = "";
    private static String dburl = "";
    
    
    
    public static AccesoBD getConector() throws SQLException, Exception, IOException {
        String[] datosConexion = getDatos();
        driver = datosConexion[0];
        dburl = datosConexion[1];
       if(conectorBD == null) {
            conectorBD = new AccesoBD(driver, dburl);
       }
       return conectorBD;
    }
    
    public static String[] getDatos() throws IOException{
        TextFileIO conexion = new TextFileIO("conexion.txt");
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
