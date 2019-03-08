/**
 * Esta clase es de donde se instancian todos los objetos de tipo factura
 */
package logica;

import java.time.LocalDate;


/**
 *
 * @author andresosante
 */
public class Factura {
    private int numero;
    private String nombreCliente;
    private String apellido;
    private String nombrePropiedad;
    private LocalDate fecha;
    private double subTotal;
    private double impuesto;
    private double total;

    public Factura() {
        
    }
    
    public Factura(String nombreCliente, String apellido, String nombrePropiedad, LocalDate fecha, double subTotal, double impuesto) {
        this.nombreCliente = nombreCliente; //se pide al usuario
        this.apellido =apellido;
        this.nombrePropiedad = nombrePropiedad;
        this.fecha = fecha;
        this.subTotal = subTotal;
        this.impuesto = impuesto;
        this.total = subTotal + this.impuesto;
    }
    
        public Factura(int numero,String nombreCliente, String apellido, String nombrePropiedad, LocalDate fecha, double subTotal, double impuesto) {
        this.numero = numero;
        this.nombreCliente = nombreCliente; //se pide al usuario
        this.apellido =apellido;
        this.nombrePropiedad = nombrePropiedad;
        this.fecha = fecha;
        this.subTotal = subTotal;
        this.impuesto = impuesto;
        this.total = subTotal + this.impuesto;
    }
    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombrePropiedad() {
        return nombrePropiedad;
    }

    public void setNombrePropiedad(String nombrePropiedad) {
        this.nombrePropiedad = nombrePropiedad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
    
    @Override
    public String toString() {
        String msg;
msg = "================================"+ "\n";
msg = msg + "Alquileres Rico Mac Pato";
msg = msg + "\t\t" + "No. " + numero + "\n";
msg = msg + "cliente: " + nombreCliente + " " + apellido + " ";
msg = msg + "\t" + fecha + "\n";
msg = msg + "------------------------------------------" + "\n";
msg = msg + "Alquiler de la propiedad " + nombrePropiedad + "\n";

msg = msg + "\t\t\t\t" + "----------" + "\n";
msg = msg + "\t\t\t" + "subtotal:"+ subTotal +"\n";
msg = msg + "\t\t\t" + "impuesto:"+ impuesto +"\n";
msg = msg + "\t\t\t" + "total :"+ total +"\n";
msg = msg + "========================" + "\n";
return msg;
    }
    
    
    
}


