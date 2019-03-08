/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.time.LocalDate;

/**
 *
 * @author andresosante
 */
public class Contrato {
    private Inquilino inquilinoAsignado; // solo puede tener uno asociad || FK en la base de datos
    private Propiedad propiedadAsignada; // solo puede tener uno asociad || FK en la base de datos
    private LocalDate fechaInicio;
    private int duracionContrato;
    private LocalDate fechaFin; //calculada a partir de fecha de inicio y duracion del contrato
    private double montoAlquier; // si la moneda es en colones, el monto aumenta por los periodos de duracion del contrato
    private double porcentajeAumentoAnual;
    private String moneda;
    private int codContrato;

    public Contrato() {
    }

    public Contrato(Inquilino inquilinoAsignado, Propiedad propiedadAsignada, LocalDate fechaInicio, int duracionContrato, double montoAlquier, double porcentajeAumentoAnual, String moneda, int codContrato) {
        this.inquilinoAsignado = inquilinoAsignado;
        this.propiedadAsignada = propiedadAsignada;
        this.fechaInicio = fechaInicio;
        this.duracionContrato = duracionContrato;
        this.fechaFin = fechaInicio.plusYears(duracionContrato); //calculo de la fecha final con base en la duración
        this.montoAlquier = montoAlquier;
        this.porcentajeAumentoAnual = porcentajeAumentoAnual;
        this.moneda = moneda;
        this.codContrato = codContrato;
    }

    public Inquilino getInquilinoAsignado() {
        return inquilinoAsignado;
    }

    public void setInquilinoAsignado(Inquilino inquilinoAsignado) {
        this.inquilinoAsignado = inquilinoAsignado;
    }

    public Propiedad getPropiedadAsignada() {
        return propiedadAsignada;
    }

    public void setPropiedadAsignada(Propiedad propiedadAsignada) {
        this.propiedadAsignada = propiedadAsignada;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getDuracionContrato() {
        return duracionContrato;
    }

    public void setDuracionContrato(int duracionContrato) {
        this.duracionContrato = duracionContrato;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getMontoAlquier() {
        return montoAlquier;
    }

    public void setMontoAlquier(double montoAlquier) {
        this.montoAlquier = montoAlquier;
    }

    public double getPorcentajeAumentoAnual() {
        return porcentajeAumentoAnual;
    }

    public void setPorcentajeAumentoAnual(double porcentajeAumentoAnual) {
        this.porcentajeAumentoAnual = porcentajeAumentoAnual;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public int getCodContrato() {
        return codContrato;
    }

    public void setCodContrato(int codContrato) {
        this.codContrato = codContrato;
    }
    
     @Override
    public String toString() {
        return "Fecha de creación: " + getFechaInicio() + ", nombre de la propiedad" + getPropiedadAsignada().getNombre() + ", nombre del inquilino: " + getInquilinoAsignado().getNombre();
    }
}
