/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import multis.MultiContrato;

/**
 *
 * @author andresosante
 */
public class CapaLogica {
    
    private ArrayList<RepNormal>reparaciones;

    public CapaLogica() {
        reparaciones = new ArrayList();
    }
    
    
    
    //hay que pasarle estos parametros antes de registrar, e igualar la variable de monto a la funcion
    public double calcularMonto(String pmoneda, int pduracion, double pmonto, double pporcentaje) {
        if(pmoneda.equals("colones") || pmoneda.equals("Colones")) {
            pmonto = pmonto * Math.pow((1 + pporcentaje), pduracion); //incremento compuesto
        }
        return pmonto;
    } 
    
    public boolean validarFecha(LocalDate fechaInicio) {
        boolean valido = true;
        LocalDate hoy = LocalDate.now();
        if(hoy.compareTo(fechaInicio) > 0) {
            valido = false;
        }
        return valido;
    }
    
    public boolean programarReparacion(RepNormal reparacion) throws Exception {
        boolean propiedadConContrato = true;
        ArrayList<Integer> propsAsignadas= (new MultiContrato()).propiedadesAsignadas();
        if(propsAsignadas.contains(reparacion.getPropiedadAsignada().getCodPropiedad())) {
            reparaciones.add(reparacion);
        } else {
            propiedadConContrato = false;
        }
                return propiedadConContrato;
    }
    
      
     public boolean registrarReparacion(int pcodigo, RepNormal reparacion) {
        boolean llegoFecha = true;
        RepNormal encontrada = buscarReparacionProgramada(pcodigo);
        if(LocalDate.now().compareTo(encontrada.getFechaRealizar()) <= 0) {
            reparaciones.remove(reparacion);
            reparaciones.add(reparacion);
        } else {
            llegoFecha = false;
        }
        return llegoFecha;
  
    }
     
     
    
    public RepNormal buscarReparacionProgramada(int pcodigo) {
        RepNormal encontrada = new RepNormal();
        for(RepNormal repEncontrada : reparaciones) {
            if(repEncontrada.getCodigo() == pcodigo) {
                encontrada = repEncontrada;
            }
        }
        return encontrada;
    }
    
      public void registrarReparacionNormal(RepNormal reparacion) {
        reparaciones.add(reparacion);
    }
    
      
          public String[] listarReparaciones() {
        int i = 0;
        String [] lista = new String[reparaciones.size()];
        for(RepNormal dato : reparaciones) {
            lista[i] = dato.toString();
            i++;
        }
        return lista;
    }
    
}
