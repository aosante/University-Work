/**
 * De esta clase se instancian todos los objetos de tipo reparacion
 */
package logica;

import java.time.LocalDate;

/**
 *
 * @author andresosante
 */
public class RepNormal {
      private int codigo;
    private String descripcion;
    private LocalDate fechaRealizar;
    private Propiedad propiedadAsignada;
    private String proveedor;
    private LocalDate fechaRealizacion;
    private double costo;
    
    
    public RepNormal() {
        
    }
    
    public RepNormal(int codigo, String descripcion, LocalDate fechaRealizar, Propiedad propiedadAsignada) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fechaRealizar = fechaRealizar;
        this.propiedadAsignada = propiedadAsignada;
    }

    public RepNormal(int codigo, String descripcion, LocalDate fechaRealizar, Propiedad propiedadAsignada, String proveedor, LocalDate fechaRealizacion, double costo) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fechaRealizar = fechaRealizar;
        this.propiedadAsignada = propiedadAsignada;
        this.proveedor = proveedor;
        this.fechaRealizacion = fechaRealizacion;
        this.costo = costo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaRealizar() {
        return fechaRealizar;
    }

    public void setFechaRealizar(LocalDate fechaRealizar) {
        this.fechaRealizar = fechaRealizar;
    }

    public Propiedad getPropiedadAsignada() {
        return propiedadAsignada;
    }

    public void setPropiedadAsignada(Propiedad propiedadAsignada) {
        this.propiedadAsignada = propiedadAsignada;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDate fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    
    

    @Override
    public String toString() {
        return "Fecha a realizar: " + fechaRealizar + ", encargado: " + proveedor + ", costo: " + costo;
    }
    
}
