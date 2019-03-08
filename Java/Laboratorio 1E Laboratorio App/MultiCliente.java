package CapaLogica;

import java.sql.*;
import java.io.*;
import java.util.*;
import CapaAccesoBD.Conector;

public class MultiCliente {

	public  Cliente crear(String pnombre, String pidentificacion) 
			throws Exception{
		Cliente cliente=null;
		String sql;
		sql = "INSERT INTO TCliente "+
		"(nombre, identificacion) "+
		"VALUES ('"+pnombre+"', '"+pidentificacion+"');";
		try {
			//aca debe poner el codigo para conectar a la base de datos y ejecutar la consulta.
			cliente = new Cliente (pnombre, pidentificacion);
		}
		catch (Exception e) {
			throw new Exception ("El número de identificación ya está en el sistema.");
		}
		return cliente;
	}
	
	public  Cliente buscar(String pidentificacion) throws 
			java.sql.SQLException,Exception{
		Cliente cliente=null;
		java.sql.ResultSet rs;
		String sql;
		sql = "SELECT nombre, identificacion "+
		"FROM TCliente "+
		"WHERE identificacion='"+ pidentificacion +"';";
					//aca debe poner el codigo para conectar a la base de datos y ejecutar la consulta.
		if (rs.next()) {
			cliente = new Cliente (rs.getString("nombre"), rs.getString("identificacion"));
		} else {
			throw new Exception ("El cliente no está registrado.");
		}
		rs.close();
		return cliente;
	}
	
	
	
	public  void actualizar(Cliente pcliente) throws 
			java.sql.SQLException,Exception{
		String sql;
		sql = "UPDATE TCliente "+
		"SET nombre='"+pcliente.getNombre()+"' "+
		"WHERE identificacion='"+pcliente.getId()+"';";
		try {
				//aca debe poner el codigo para conectar a la base de datos y ejecutar la consulta.
		}
		catch (Exception e) {
			throw new Exception ("El cliente no está registrado.");
		}
	}
	
	public  void borrar(Cliente pcliente) throws 
			java.sql.SQLException,Exception{
		String sql;
		sql = "DELETE FROM TCliente "+
		"WHERE identificacion='"+pcliente.getId()+"';";
		try {
			//aca debe poner el codigo para conectar a la base de datos y ejecutar la consulta.
		}
		catch (Exception e) {
			throw new Exception ("El cliente tiene cuentas.");
		}
	}
}