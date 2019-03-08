/**
 * Esta clase gestiona los datos entre la interfaz de usuario y 
 * la capa logica y base de datos
 */
package gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import logica.CapaLogica;
import logica.Contrato;
import logica.Factura;
import logica.Inquilino;
import logica.Propiedad;
import logica.RepNormal;
import multis.MultiContrato;
import multis.MultiFactura;
import multis.MultiInquilino;
import multis.MultiPropiedad;
import multis.MultiReparacion;



/**
 *
 * @author andresosante
 */
public class Gestor {
    
    CapaLogica cl = new CapaLogica();
    /**
     * 
     * @param codPropiedad de la propiedad
     * @param nombre de la propiedad
     * @param valor de la propiedad
     * @param direccion de la propiedad
     * @param residencial de la propiedad
     * @param numeroCasa de la propiedad
     * @param tienePatio si la propiedad tiene o no un patio
     * @param cantidadHabitaciones de la propiedad
     * @throws Exception 
     */
    public void registrarPropiedad(int codPropiedad, String nombre, double valor, String direccion, String residencial, int numeroCasa, boolean tienePatio, int cantidadHabitaciones) throws Exception{
        Propiedad property =new Propiedad(codPropiedad, nombre, valor, direccion, residencial, numeroCasa, tienePatio, cantidadHabitaciones);
        (new MultiPropiedad()).registrarPropiedad(property);  
    }
    
    /**
     * 
     * @return string de propiedades registradas
     * @throws Exception excepcion
     */
    public String listarPropiedades() throws Exception {
        String propiedades = "";
        ArrayList<Propiedad> lista = new ArrayList();
        lista = (new MultiPropiedad()).listarPropiedades();
        for(Propiedad item : lista) {
            propiedades += item.toString();
        }
        return propiedades;
    }
    
    /**
     * 
     * @return string de propiedades ya asignadas
     * @throws Exception excepcion
     */
    public String listarPropiedadesAsignadas() throws Exception {
        return (new MultiPropiedad()).listarPropiedadesAlquiladas();
    }
    /**
     * 
     * @param codigo de la propiedad
     * @return String de propiedad buscada
     * @throws Exception excepcion
     */
    public String buscarPropiedad(int codigo) throws Exception {
        Propiedad property = (new MultiPropiedad()).buscarPropiedad(codigo);
        String propiedad = property.toString();
        return propiedad;
    }
    
    /**
     * 
     * @param nombre del inquilino
     * @param apellidos del inquilino
     * @param correo del inquilino
     * @param direccion del inquilino
     * @param telefono del inquilino
     * @param id cedula del inquilino
     * @param genero del inquilino
     * @throws Exception excepcion
     */
    public void registrarInquilino(String nombre, String apellidos, String correo, String direccion, String telefono, int id, char genero) throws Exception {
        Inquilino persona = new Inquilino(nombre, apellidos, correo, direccion, telefono, id, genero);
        (new MultiInquilino()).registrarInquilino(persona);
    }
    
    /**
     * 
     * @return string de inquilinos registrados
     * @throws Exception excepcion
     */
    public String listarInquilinos() throws Exception {
        String inquilinos = "";
        ArrayList<Inquilino> lista = new ArrayList();
        lista = (new MultiInquilino()).listarInquilinos();
        for(Inquilino item : lista) {
            inquilinos += item.toString();
        }
        return inquilinos;
    }
    
    /**
     * 
     * @param id del inquilino
     * @return string de inquilino buscado
     * @throws Exception excepcion
     */
    public String buscarInquilino(int id) throws Exception {
        Inquilino inqui = (new MultiInquilino()).buscarInquilino(id);
        String inquilino = inqui.toString();
        return inquilino;
    }
    
    /**
     * 
     * @param pcedula del inquilino asignado
     * @param codPropiedad de la propiedad asignada
     * @param fechaInicio del contrato
     * @param duracionContrato
     * @param montoAlquiler del contrato
     * @param porcentajeAumentoAnual del contrato
     * @param moneda del contrato
     * @param codContrato codigo del contrato
     * @return boolean que verifica si el registro cumple con las restricciones
     * @throws Exception excepcion
     */
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
    /**
     * 
     * @param fechaInicio
     * @return boolean que verifica si la fecha de inicio es valida
     * @throws Exception excepcion
     */
    public boolean validarFecha(LocalDate fechaInicio) throws Exception {
        boolean fechaValida = cl.validarFecha(fechaInicio);
        return fechaValida;
    }
    
    /**
     * 
     * @param codPropiedad codigo de la propiedad
     * @return boolean que verifica si la propiedad ha sido asignada
     * @throws Exception excepcion
     */
    public boolean verificarPropiedadesAsignadas( int codPropiedad) throws Exception{
        ArrayList<Integer> propiedadesAsignadas = (new MultiContrato()).propiedadesAsignadas();
        boolean repetida = false;
        if(propiedadesAsignadas.contains(codPropiedad)) {
            repetida = true;
        }
        return repetida;
    }
    
    /**
     * 
     * @param cedula
     * @return boolean qeu verifica si el inquilino ya ha sido asignado 
     * @throws Exception excepcion
     */
    public boolean verificarInquilinosAsignados(int cedula) throws Exception {
        ArrayList<Integer> inquilinosAsignados = (new MultiContrato()).inquilinosAsignados();
        boolean repetido = false;
        if(inquilinosAsignados.contains(cedula)) {
            repetido = true;
        }
        return repetido;
    }
    
    /**
     * 
     * @param pcedula cedula del inquilino asociado
     * @param codPropiedad codigo de la propiedad asignada
     * @param fechaInicio del contrato
     * @param duracionContrato duracion
     * @param montoAlquiler monto del contrato
     * @param porcentajeAumentoAnual interes sobre el monto inicial
     * @param moneda moneda en la que se maneja el contrato
     * @param codContrato codigo del contrato
     * @return boolean que verifica si lso nuevos datos cumplen con las restricciones
     * @throws Exception 
     */
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
    
    /**
     * 
     * @return String con los contratos registrados
     * @throws Exception excepcion
     */
    public String listarContratos() throws Exception {
        return (new MultiContrato()).listarContratos();
    }
    
    /**
     * 
     * @param codPropiedad codigo de la propiedad
     * @param codigo de la reparacion
     * @param descripcion de la reparacion
     * @param fechaRealizar fecha programada para comenzar la reparacion
     * @return boolean que verifica si la propiedad tiene contrato y se le puede hacer una reparacion
     * @throws Exception 
     */
    public boolean programarReparacion(int codPropiedad, int codigo, String descripcion, LocalDate fechaRealizar) throws Exception {
        
        boolean valido = true, propiedadConContrato = true;
        Propiedad asignada = (new MultiPropiedad()).buscarPropiedad(codPropiedad);
        RepNormal reparacion = new RepNormal(codigo, descripcion, fechaRealizar, asignada);
        propiedadConContrato = verificarPropiedadesAsignadas(codPropiedad);
        if(propiedadConContrato) {
            (new MultiReparacion()).programarReparacion(reparacion);
        } else {
            valido = false;
        }
        return valido;
    }
    
    /**
     * 
     * @param pcodigo de la reparacion
     * @param proveedor
     * @param fechaRealizacion
     * @param costo
     * @return
     * @throws Exception 
     */
    public boolean registrarReparacionProgramada(int pcodigo, String proveedor, LocalDate fechaRealizacion, double costo) throws Exception {
    boolean llegoFecha = true;
    RepNormal encontrada = buscarReparacionProgramada(pcodigo);
     if(LocalDate.now().compareTo(encontrada.getFechaRealizar()) >= 0) {
            encontrada.setProveedor(proveedor);
            encontrada.setFechaRealizacion(fechaRealizacion);
            encontrada.setCosto(costo);
         (new MultiReparacion()).registrarReparacionProgramada(encontrada);
     } else {
         llegoFecha = false;
     }
    return llegoFecha;
}
    
    
    /**
     * 
     * @param pcodigo de la reparacion
     * @return reparacion ya programada
     * @throws Exception excepcion
     */
    public RepNormal buscarReparacionProgramada(int pcodigo) throws Exception {
        RepNormal encontrada = (new MultiReparacion()).buscarReparacionProgramada(pcodigo);
        return encontrada;
    }
    
    
    /**
     * 
     * @param codigo de la reparacion
     * @param descripcion de la reparacion
     * @param fechaRealizar de la reparacion
     * @param codPropiedad de la reparacion
     * @param proveedor de la reparacion
     * @param fechaRealizacion de la reparacion
     * @param costo de la reparacion
     * @throws Exception excepcion
     */
      public boolean registrarReparacionNormal(int codigo, String descripcion, LocalDate fechaRealizar, int codPropiedad, String proveedor, LocalDate fechaRealizacion, double costo) throws Exception {
        boolean valido = true, propiedadConContrato = true;
        Propiedad asignada = new Propiedad();
        asignada = (new MultiPropiedad()).buscarPropiedad(codPropiedad);
        RepNormal reparacion = new RepNormal(codigo, descripcion, fechaRealizar, asignada, proveedor, fechaRealizacion, costo);
        propiedadConContrato = verificarPropiedadesAsignadas(codPropiedad);
        if(propiedadConContrato) {
            (new MultiReparacion()).registrarReparacionNormal(reparacion);
        } else {
            valido = false;
        }
        return valido;
    }
      

      /**
       * 
       * @return string con las reparaciones registradas
       * @throws Exception excepcion
       */
     public String listarReparaciones() throws Exception {
        return (new MultiReparacion()).listarReparaciones();
    }
     
     /**
      * 
      * @param nombre del inquilino
      * @param apellido del inquilino
      * @throws Exception 
      */
     public void registrarFactura(String nombre, String apellido) throws Exception {
         int codPropiedad = (new MultiFactura()).obtenerCodigoPropiedad(nombre, apellido);
         String nombrePropiedad = (new MultiFactura()).obtenerNombrePropiedad(codPropiedad);
         Contrato cont = (new MultiFactura()).obtenerContrato(codPropiedad);
         double impuesto = cl.calcularImpuesto(cont.getMontoAlquier(), cont.getMoneda());
         Factura fact = new Factura(nombre, apellido, nombrePropiedad, cont.getFechaFin(), cont.getMontoAlquier(),impuesto);
          (new MultiFactura()).registrarFactura(fact);
         
         
     }
     
     /**
      * 
      * @param nombre del inquilino
      * @param apellido del inquilino
      * @return string con la factura asociada
      * @throws Exception excepcion
      */
     public String listarFactura(String nombre, String apellido) throws Exception {
         String listaFactura;
         Factura fact = (new MultiFactura()).listarFactura(nombre, apellido);
         listaFactura = fact.toString();
         return listaFactura;
     }
    
}
