/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multis;

import acceso.Conector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.Propiedad;

/**
 *
 * @author andresosante
 */
public class MultiPropiedad {

    public void registrarPropiedad(Propiedad property) throws SQLException, Exception {
        int booleano = 0;
        if (property.isTienePatio()) {
            booleano = 1;
        }
        String sql;
        sql = "INSERT INTO PROPIEDAD (codPropiedad, nombre, valor, direccion, residencial, numeroCasa, tienePatio, cantidadHabitaciones) "
                + "VALUES (" + property.getCodPropiedad() + ", '" + property.getNombre() + "', " + property.getValor() + ", '" + property.getDireccion() + "', '"
                + property.getResidencial() + "', " + property.getNumeroCasa() + ", " + booleano + ", " + property.getCantidadHabitaciones() + ");";
        try {
            Conector.getConector().ejecutarSQL(sql);
        } catch (Exception e) {
            throw new Exception("Ya existe una propiedad registrada con ese código. Por favor inténtelo de nuevo");
        }
    }

    public ArrayList<Propiedad> listarPropiedades() throws SQLException, Exception {
        ArrayList<Propiedad> lista = new ArrayList();
        String sql;
        sql = "SELECT * FROM PROPIEDAD";
        try {
            ResultSet rs = null;
            rs = Conector.getConector().ejecutarSQL(sql, true);
            while (rs.next()) {
                Propiedad property = new Propiedad(rs.getInt("codPropiedad"), rs.getString("nombre"), rs.getInt("valor"), rs.getString("direccion"), rs.getString("residencial"), rs.getInt("numeroCasa"), rs.getBoolean("tienePatio"), rs.getInt("cantidadHabitaciones"));
                lista.add(property);
            }
            rs.close();
        } catch (Exception e) {
            throw new Exception("No hay propiedades registradas");
        }
        return lista;
    }

   

    public String listarPropiedadesAlquiladas() throws SQLException, Exception {
        String sql, listas = "";
        sql =  "SELECT p.codPropiedad as codPropiedad, p.nombre as nombrePropiedad, p.valor as valor, p.direccion as direccion, p.residencial as residencial, p.numeroCasa as numeroCasa, p.cantidadHabitaciones as cantidadHabitaciones, i.nombre as nombreInquilino, i.apellidos as apellidos FROM PROPIEDAD p INNER JOIN CONTRATO c ON(c.codPropiedad = p.codPropiedad) INNER JOIN INQUILINO i ON (i.cedula = c.cedulaInquilino);";
        try {
            ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
            while (rs.next()) {
                listas += "Código propiedad: " + rs.getInt("codPropiedad") + ", nombre: " + rs.getString("nombrePropiedad") + ", valor: " + rs.getDouble("valor") + ", dirección: " + rs.getString("direccion") + ", residencial: " + rs.getString("residencial") + ", número de casa: " + rs.getInt("numeroCasa") + ", cantidad de habitaciones: " + 
                        rs.getInt("cantidadHabitaciones") + ", nombre del inquilino: " + rs.getString("nombreInquilino") + ", apellido/s: " + rs.getString("apellidos") + "\n";
            }
            rs.close();
        } catch (Exception e) {
            throw new Exception("Puta picha");
        }
        return listas;
    }

    public Propiedad buscarPropiedad(int codigo) throws SQLException, Exception {
        String sql;
        sql = "SELECT * FROM PROPIEDAD WHERE codPropiedad = " + codigo + ";";
        Propiedad property = null;
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        if (rs.next()) {
            property = new Propiedad(rs.getInt("codPropiedad"), rs.getString("nombre"), rs.getInt("valor"), rs.getString("direccion"), rs.getString("residencial"), rs.getInt("numeroCasa"), rs.getBoolean("tienePatio"), rs.getInt("cantidadHabitaciones"));
        } else {
            throw new Exception("No existe ninguna propiedad registrada bajo ese código");
        }
        rs.close();
        return property;
    }

}
