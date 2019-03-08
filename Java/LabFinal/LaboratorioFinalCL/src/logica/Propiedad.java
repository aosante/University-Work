/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author andresosante
 */
public class Propiedad {
    private int codPropiedad;
    private String nombre;
    private double valor;
    private String direccion;
    private String residencial;
    private int numeroCasa;
    private boolean tienePatio;
    private int cantidadHabitaciones;

    public Propiedad() {
    }
    
    

    public Propiedad(int codPropiedad, String nombre, double valor, String direccion, String residencial, int numeroCasa, boolean tienePatio, int cantidadHabitaciones) {
        this.codPropiedad = codPropiedad;
        this.nombre = nombre;
        this.valor = valor;
        this.direccion = direccion;
        this.residencial = residencial;
        this.numeroCasa = numeroCasa;
        this.tienePatio = tienePatio;
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    public int getCodPropiedad() {
        return codPropiedad;
    }

    public void setCodPropiedad(int codPropiedad) {
        this.codPropiedad = codPropiedad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getResidencial() {
        return residencial;
    }

    public void setResidencial(String residencial) {
        this.residencial = residencial;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public boolean isTienePatio() {
        return tienePatio;
    }

    public void setTienePatio(boolean tienePatio) {
        this.tienePatio = tienePatio;
    }

    public int getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(int cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }
    
    @Override
    public String toString() {
        String patio = "No";
        if(tienePatio) {
            patio = "Sí";
        }
        return "Código de propiedad: " + codPropiedad + ", nombre: " + nombre + ", valor: " + valor + ", dirección: " + direccion + ", residencial: " + residencial + ", numero de casa: " + numeroCasa + ", ¿tiene patio?: " + patio + ", cantidad de habitaciones: " + cantidadHabitaciones + "\n";
    }
}
