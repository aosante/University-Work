package CapaLogica;

import java.util.Vector;

public class Cliente {
	private String identificacion;
	private String nombre;
	private Vector cuentas;
	
	public Cliente(String pnombre, String pidentificacion) {
		setNombre(pnombre);
		setId(pidentificacion);
		cuentas=null;
	}
	
	public String getId() {
		return identificacion;
	}
	
	public void setId(String pidentificacion) {
		identificacion=pidentificacion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String pnombre) {
		nombre=pnombre;
	}
	
	
	
}		