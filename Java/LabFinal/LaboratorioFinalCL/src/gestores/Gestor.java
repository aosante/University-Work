
package gestores;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import logica.CapaLogica;
import logica.Contrato;
import logica.Inquilino;
import logica.Propiedad;
import logica.RepNormal;
import multis.MultiContrato;
import multis.MultiInquilino;
import multis.MultiPropiedad;



/**
 *
 * @author andresosante
 */
public class Gestor {
    
    CapaLogica cl = new CapaLogica();
    
    public void registrarPropiedad(int codPropiedad, String nombre, double valor, String direccion, String residencial, int numeroCasa, boolean tienePatio, int cantidadHabitaciones) throws Exception{
        Propiedad property =new Propiedad(codPropiedad, nombre, valor, direccion, residencial, numeroCasa, tienePatio, cantidadHabitaciones);
        (new MultiPropiedad()).registrarPropiedad(property);  
    }
    
    public String listarPropiedades() throws Exception {
        String propiedades = "";
        ArrayList<Propiedad> lista = new ArrayList();
        lista = (new MultiPropiedad()).listarPropiedades();
        for(Propiedad item : lista) {
            propiedades += item.toString();
        }
        return propiedades;
    }
    
    public String listarPropiedadesAsignadas() throws Exception {
        return (new MultiPropiedad()).listarPropiedadesAlquiladas();
    }
    
    public String buscarPropiedad(int codigo) throws Exception {
        Propiedad property = (new MultiPropiedad()).buscarPropiedad(codigo);
        String propiedad = property.toString();
        return propiedad;
    }
    
    public void registrarInquilino(String nombre, String apellidos, String correo, String direccion, String telefono, int id, char genero) throws Exception {
        Inquilino persona = new Inquilino(nombre, apellidos, correo, direccion, telefono, id, genero);
        (new MultiInquilino()).registrarInquilino(persona);
    }
    
    public String listarInquilinos() throws Exception {
        String inquilinos = "";
        ArrayList<Inquilino> lista = new ArrayList();
        lista = (new MultiInquilino()).listarInquilinos();
        for(Inquilino item : lista) {
            inquilinos += item.toString();
        }
        return inquilinos;
    }
    
    
    public String buscarInquilino(int id) throws Exception {
        Inquilino inqui = (new MultiInquilino()).buscarInquilino(id);
        String inquilino = inqui.toString();
        return inquilino;
    }
    
    /*la validacion de que los inquilinos/propiedades existan 
    se hace en la base de datos con constraints*/
    public boolean registrarContrato(int pcedula, int codPropiedad, LocalDate fechaInicio, int duracionContrato, double montoAlquiler, double porcentajeAumentoAnual, String moneda, int codContrato) throws Exception {
        boolean registroValido = true, propRepetida = true, inqRepetido = false;
        propRepetida = verificarPropiedadesAsignadas(codPropiedad);
        inqRepetido = verificarInquilinosAsignados(pcedula);
        montoAlquiler = cl.calcularMonto(moneda, duracionContrato, montoAlquiler, porcentajeAumentoAnual);
        Inquilino inq = (new MultiInquilino()).buscarInquilino(pcedula);
        Propiedad prop = (new MultiPropiedad()).buscarPropiedad(codPropiedad);
        Contrato alquiler = new Contrato(inq, prop, fechaInicio, duracionContrato, montoAlquiler, porcentajeAumentoAnual, moneda, codContrato);
        if(propRepetida || inqRepetido) {
            registroValido = false;
        } else {
            (new MultiContrato()).registrarContrato(alquiler);
        }
        return registroValido;
    }
    
    public boolean validarFecha(LocalDate fechaInicio) throws Exception {
        boolean fechaValida = cl.validarFecha(fechaInicio);
        return fechaValida;
    }
    
    public boolean verificarPropiedadesAsignadas( int codPropiedad) throws Exception{
        ArrayList<Integer> propiedadesAsignadas = (new MultiContrato()).propiedadesAsignadas();
        boolean repetida = false;
        if(propiedadesAsignadas.contains(codPropiedad)) {
            repetida = true;
        }
        return repetida;
    }
    
    public boolean verificarInquilinosAsignados(int cedula) throws Exception {
        ArrayList<Integer> inquilinosAsignados = (new MultiContrato()).inquilinosAsignados();
        boolean repetido = false;
        if(inquilinosAsignados.contains(cedula)) {
            repetido = true;
        }
        return repetido;
    }
    
    public boolean modificarContrato(int pcedula, int codPropiedad, LocalDate fechaInicio, int duracionContrato, double montoAlquiler, double porcentajeAumentoAnual, String moneda, int codContrato) throws Exception {
          boolean registroValido = true, propRepetida = true, inqRepetido = false;
          (new MultiContrato()).limpiarContrato(codContrato);
        propRepetida = verificarPropiedadesAsignadas(codPropiedad);
        inqRepetido = verificarInquilinosAsignados(pcedula);
        montoAlquiler = cl.calcularMonto(moneda, duracionContrato, montoAlquiler, porcentajeAumentoAnual);
        if(propRepetida || inqRepetido) {
            registroValido = false;
        } else {
            (new MultiContrato()).modificarContrato(pcedula,  codPropiedad,  fechaInicio,  duracionContrato,  montoAlquiler,  porcentajeAumentoAnual,  moneda,  codContrato);
        }
        return registroValido;
    }
    
    public String listarContratos() throws Exception {
        return (new MultiContrato()).listarContratos();
    }
    
    public boolean programarReparacion(int codPropiedad, int codigo, String descripcion, LocalDate fechaRealizar) throws Exception {
        boolean valido;
        Propiedad asignada = (new MultiPropiedad()).buscarPropiedad(codPropiedad);
        RepNormal reparacion = new RepNormal(codigo, descripcion, fechaRealizar, asignada);
        valido = cl.programarReparacion(reparacion);
        return valido;
    }
    
      public boolean registrarReparacion(int pcodigo, String proveedor, LocalDate fechaRealizacion, double costo) {
        boolean fechaValida = true;
        RepNormal encontrada = buscarReparacionProgramada(pcodigo);
        fechaValida = cl.registrarReparacion(pcodigo, encontrada);
        if(fechaValida) {
            encontrada.setProveedor(proveedor);
            encontrada.setFechaRealizacion(fechaRealizacion);
            encontrada.setCosto(costo);
        } else {
            fechaValida = false;
        }
        return fechaValida;
        
    }
    
    public RepNormal buscarReparacionProgramada(int pcodigo) {
        RepNormal encontrada = cl.buscarReparacionProgramada(pcodigo);
        return encontrada;
    }
    
      public void registrarReparacionNormal(int codigo, String descripcion, LocalDate fechaRealizar, int codPropiedad, String proveedor, LocalDate fechaRealizacion, double costo) throws Exception {
        Propiedad asignada = new Propiedad();
        asignada = (new MultiPropiedad()).buscarPropiedad(codigo);
        RepNormal reparacion = new RepNormal(codigo, descripcion, fechaRealizar, asignada, proveedor, fechaRealizacion, costo);
        cl.registrarReparacionNormal(reparacion);
    }
      

      
          public String[] listarReparaciones() throws IOException {
        String [] lista = cl.listarReparaciones();
        return lista;
    }
    
}
