/**
 * Esta clase maneja parte de la logica de la aplicacion
 */
package logica;

import java.time.LocalDate;

/**
 *
 * @author andresosante
 */
public class CapaLogica {

    public CapaLogica() {

    }

    /**
     * 
     * @param pmoneda moneda en la que se maneja el contrato
     * @param pduracion duracion del contrato
     * @param pmonto monto del contrato
     * @param pporcentaje porcentaje de aumento anual
     * @return el monto calculado segun la moneda
     */
    public double calcularMonto(String pmoneda, int pduracion, double pmonto, double pporcentaje) {
        if (pmoneda.equals("colones") || pmoneda.equals("Colones") || pmoneda.equals("colon") || pmoneda.equals("Colon")) {
            pmonto = pmonto * Math.pow((1 + pporcentaje), pduracion); //incremento compuesto
        }
        return pmonto;
    }
/**
 * 
 * @param monto del alquiler
 * @param moneda en la que se maneja el alquiler
 * @return impuesto a pagar 
 */
    public double calcularImpuesto(double monto, String moneda) {
        double impuesto = 0;
        if ((moneda.equals("dolares") || moneda.equals("Dolares") || moneda.equals("dolar") || moneda.equals("Dolar")) && monto > 1058) {
            impuesto = monto * 13 / 100;
        } else if (monto > 600000) {
            impuesto = monto * 13 / 100;
        }
        return impuesto;
    }
/**
 * 
 * @param fechaInicio del alquiler
 * @return si la fecha de inicio es posterior a hoy o no
 */
    public boolean validarFecha(LocalDate fechaInicio) {
        boolean valido = true;
        LocalDate hoy = LocalDate.now();
        if (hoy.compareTo(fechaInicio) > 0) {
            valido = false;
        }
        return valido;
    }

}
