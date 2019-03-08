package CapaLogica;

import java.util.*;

public class Gestor {
	public void clienteAgregar(String pnombre, String pidentificacion) throws Exception {
		Cliente client;
		client = (new MultiCliente()).crear(pnombre, pidentificacion);
	}
	
	public TreeMap clienteBuscar(String pidentificacion) throws Exception {
		TreeMap datos = null;
		Cliente client=null;
		String nombre;
		datos = new TreeMap();
		client = (new MultiCliente()).buscar(pidentificacion);
		datos.put("nombre", client.getNombre());
		datos.put("identificacion", client.getId());
		return datos;
	}
		
	
	public void clienteBorrar(String pidCliente) throws Exception {
		Cliente client;
		client = (new MultiCliente()).buscar(pidCliente);
		(new MultiCliente()).borrar(client);
	}
	
	public void clienteModificar(String pidentificacion, String pnombre) throws Exception {
		Cliente client;
		client = (new MultiCliente()).buscar(pidentificacion);
		client.setNombre(pnombre);
		(new MultiCliente()).actualizar(client);
	}
	
	

}