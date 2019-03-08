/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import gestores.Gestor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDate;

/**
 *
 * @author andresosante
 */
public class LaboratorioFinalUI {

    /**
     * @param args the command line arguments
     */
    static Gestor miGestor = new Gestor();

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

    public static void main(String[] args) throws Exception {
        int opc;
        boolean noSalir = true;

        do {
            mostrarMenu();
            opc = leerOpcion();
            noSalir = ejecutarAccion(opc);
        } while (noSalir);
    }

    public static void mostrarMenu() {
        out.println();
        out.println("1. Registrar propiedades");
        out.println("2. Listar propiedades");
        out.println("3. Buscar propiedades");
        out.println("4. Registrar inquilinos");
        out.println("5. Listar inquilinos");
        out.println("6. Buscar inquilino");
        out.println("7. Registrar contrato de alquiler");
        out.println("8. Modificar contrato de alquiler");//**
        out.println("9. Listar contrato de alquiler");
        out.println("10. Programar reparaciones");
        out.println("11. Registrar reparaciones");
        out.println("12. Listar reparaciones");
        out.println("0. Salir");
    }

    public static int leerOpcion() throws IOException {
        int opcion;

        out.println("Seleccione su opción");
        opcion = Integer.parseInt(in.readLine());
        out.println();

        return opcion;

    }

    public static boolean ejecutarAccion(int popcion) throws Exception {
        boolean noSalir = true;

        switch (popcion) {
            case 1:
                registrarPropiedad();//listo
                break;
            case 2:
                listarPropiedades();//listo
                break;
            case 3:
                buscarPropiedad(); //listo
                break;
            case 4:
                registrarInquilino(); //listo
                break;
            case 5:
                listarInquilinos(); //listo
                break;
            case 6:
                buscarInquilino(); //listo
                break;
            case 7:
                registrarContrato(); //listo
                break;
            case 8:
                modificarContrato(); //listo
                break;
            case 9:
                listarContratos(); //listo
                break;
            case 10:
                programarReparacion();//listo
                break;
            case 11:
                registrarReparacion();//listo
                break;
            case 12:
                listarReparaciones();//listo
                break;
            case 0:
                out.println("Gracias por utilizar la aplicación");
                noSalir = false;
                break;
            default:
                out.println("Opción inválida. Inténtelo de nuevo");
                out.println();
        }
        return noSalir;
    }

    public static void registrarPropiedad() throws Exception {
        int codPropiedad, numeroCasa, cantidadHabitaciones;
        String nombre, direccion, residencial;
        double valor;
        boolean tienePatio;
        int patio;
        out.println("Digite el código de la propiedad");
        codPropiedad = Integer.parseInt(in.readLine());
        out.println("Digite el nombre de la propiedad");
        nombre = in.readLine();
        out.println("Digite el valor de la propiedad");
        valor = Double.parseDouble(in.readLine());
        out.println("Digite la dirección de la propiedad");
        direccion = in.readLine();
        out.println("Digite el residencial de la propiedad");
        residencial = in.readLine();
        out.println("Digite el número de casa de la propiedad");
        numeroCasa = Integer.parseInt(in.readLine());
        out.println("Digite 1 si la casa tiene patio y 0 si no lo tiene");
        patio = Integer.parseInt(in.readLine());
        if (patio == 1) {
            tienePatio = true;
        } else {
            tienePatio = false;
        }
        out.println("Digite la cantidad de habitaciones que tiene la propiedad");
        cantidadHabitaciones = Integer.parseInt(in.readLine());
        try {
            miGestor.registrarPropiedad(codPropiedad, nombre, valor, direccion, residencial, numeroCasa, tienePatio, cantidadHabitaciones);
            out.println("Propiedad registrada con éxito.");
        } catch (Exception e) {
            out.println(e.getMessage());
        }

    }

    public static void listarPropiedades() throws Exception {
        try {
            String propiedades = miGestor.listarPropiedades();
            out.println("Las propiedades registradas son las siguientes: " + "\n");
            out.println(propiedades);
        } catch (Exception e) {
            out.println(e.getMessage());
        }
        try {
            String propiedadesAlquiladas = miGestor.listarPropiedadesAsignadas();
            out.println("Las propiedades alquiladas y sus inquilinos respectivos son las siguientes: " + "\n");
            out.println(propiedadesAlquiladas);
        } catch(Exception e) {
            out.println(e.getMessage());
        }

    }

    public static void buscarPropiedad() throws Exception {
        String encontrada = "";
        int codigo;
        out.println("Digite el codigo de la propiedad que desea buscar");
        codigo = Integer.parseInt(in.readLine());
        try {
            encontrada = miGestor.buscarPropiedad(codigo);
            out.println("La propiedad registrada es la siguiente: ");
            out.println(encontrada);
        } catch (Exception e) {
            out.println(e.getMessage());
        }

    }

    public static void registrarInquilino() throws Exception {
        String nombre, apellidos, correo, direccion, telefono;
        String generoTmp;
        int id;
        char genero;
        out.println("Digite el nombre del inquilino");
        nombre = in.readLine();
        out.println("Digite el o los apellidos del inquilino");
        apellidos = in.readLine();
        out.println("Digite el correo del inquilino");
        correo = in.readLine();
        out.println("Digite la direccion del inquilino");
        direccion = in.readLine();
        out.println("Digite el número de teléfono del inquilino");
        telefono = in.readLine();
        out.println("Digite la cédula del inquilino");
        id = Integer.parseInt(in.readLine());
        out.println("Digite el género del inquilino (M o F)");
        generoTmp = in.readLine();
        genero = generoTmp.charAt(0);
        try {
            miGestor.registrarInquilino(nombre, apellidos, correo, direccion, telefono, id, genero);
            out.println("Inquilino registrado con éxito.");
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    public static void listarInquilinos() throws IOException {
        try {
            String inquilinos = miGestor.listarInquilinos();
            out.println("Los inquilinos registrados son los siguientes");
            out.println(inquilinos);
        } catch (Exception e) {
            out.println(e.getMessage());
        }

    }

    public static void buscarInquilino() throws Exception {
        String encontrado = "";
        int id;
        out.println("Digite la cédula del inquilino");
        id = Integer.parseInt(in.readLine());
        try {
            encontrado = miGestor.buscarInquilino(id);
            out.println("El inquilino registrado es el siguiente: ");
            out.println(encontrado);
        } catch (Exception e) {
            out.println(e.getMessage());
        }

    }

    public static void registrarContrato() throws Exception {
        boolean valido, fechaValida;
        int cedula, codPropiedad, duracionContrato, codContrato;
        LocalDate fechaInicio;
        double montoAlquiler, porcentajeAumento;
        String moneda;
        listarInquilinos();
        out.println("Digite la cédula del inquilino del contrato");
        cedula = Integer.parseInt(in.readLine());
        listarPropiedades();
        out.println("Digite el código de la propiedad del contrato");
        codPropiedad = Integer.parseInt(in.readLine());
        out.println("Digite la fecha de incio del contrato en formato yyyy-mm-dd");
        fechaInicio = LocalDate.parse(in.readLine());
        fechaValida = miGestor.validarFecha(fechaInicio);
        if(fechaValida == false) {
            out.println("La fecha de inicio del contrato debe de ser posterior a hoy. Inténtelo de nuevo.");
            fechaInicio = LocalDate.parse(in.readLine());
        }
        out.println("Digite la duración del contrato");
        duracionContrato = Integer.parseInt(in.readLine()); 
        out.println("Digite el monto del alquiler");
        montoAlquiler = Double.parseDouble(in.readLine());
        out.println("Digite el procentaje de aumento del contrato en decimales");
        porcentajeAumento = Double.parseDouble(in.readLine());
        out.println("Digite la moneda en la que se va a manejar el contrato (colones o dólares)");
        moneda = in.readLine();
        out.println("Digite el código del contrato");
        codContrato = Integer.parseInt(in.readLine());
        try {
              valido = miGestor.registrarContrato(cedula, codPropiedad, fechaInicio, duracionContrato, montoAlquiler, porcentajeAumento, moneda, codContrato);
        if (!valido) {
            out.println("La propiedad o inquilino ya han sido asignados a otro contrato, por favor inténtelo de nuevo");
        } else {
            out.println("Contrato registrado con éxito");
        }
        } catch(Exception e) {
            out.println(e.getMessage());
        }
      
    }

    public static void modificarContrato() throws Exception {

        boolean valido, fechaValida;
        int cedula, codPropiedad, duracionContrato, codContrato;
        LocalDate fechaInicio;
        double montoAlquiler, porcentajeAumento;
        String moneda;
        listarContratos();
        out.println("Digite el código del contrato a modificar");
        codContrato = Integer.parseInt(in.readLine());
        out.println("Digite la cédula del inquilino del contrato");
        cedula = Integer.parseInt(in.readLine());
        out.println("Digite el código de la propiedad del contrato");
        codPropiedad = Integer.parseInt(in.readLine());
        out.println("Digite la fecha de incio del contrato en formato yyyy-mm-dd");
        fechaInicio = LocalDate.parse(in.readLine());
        fechaValida = miGestor.validarFecha(fechaInicio);
        if(fechaValida == false) {
            out.println("La fecha de inicio del contrato debe de ser posterior a hoy. Inténtelo de nuevo");
            fechaInicio = LocalDate.parse(in.readLine());
        }
        out.println("Digite la duración del contrato");
        duracionContrato = Integer.parseInt(in.readLine());
        out.println("Digite el monto del alquiler");
        montoAlquiler = Double.parseDouble(in.readLine());
        out.println("Digite el procentaje de aumento del contrato en decimales");
        porcentajeAumento = Double.parseDouble(in.readLine());
        out.println("Digite la moneda en la que se va a manejar el contrato");
        moneda = in.readLine();

        valido = miGestor.modificarContrato(cedula, codPropiedad, fechaInicio, duracionContrato, montoAlquiler, porcentajeAumento, moneda, codContrato);
        if (!valido) {
            out.println("La propiedad o inquilino ya han sido asignados a otro contrato, por favor inténtelo de nuevo");
        } else {
            out.println("Contrato modificado con éxito");
        }

    }

    public static void listarContratos() throws Exception {
        out.println("Los contratos registrados son los siguientes: " + "\n");
        out.println(miGestor.listarContratos());
    }

    public static void programarReparacion() throws Exception {
        boolean valido;
        int codigo, codPropiedad;
        String descripcion;
        LocalDate fechaRealizar;
        out.println("Digite el código de la propiedad a reparar");
        codPropiedad = Integer.parseInt(in.readLine());
        out.println("Digite el código de la reparación");
        codigo = Integer.parseInt(in.readLine());
        out.println("Digite la descripción de la reparación");
        descripcion = in.readLine();
        out.println("Digite la fecha de realización en formato yyyy-mm-dd");
        fechaRealizar = LocalDate.parse(in.readLine());
        valido = miGestor.programarReparacion(codPropiedad, codigo, descripcion, fechaRealizar);
        if (!valido) {
            out.println("La propiedad a reparar no tiene contrato o no existe");
        } else {
            out.println("Reparación programada con éxito");
        }
    }

    public static void registrarReparacion() throws Exception {
        int opcion;
        boolean valido;
        int codigo, codPropiedad;
        String descripcion;
        LocalDate fechaRealizar;

        String proveedor;
        LocalDate fechaRealizacion;
        double costo;
        out.println("Digite 1 si desea registrar una reparacion ya programada, y 0 si desea registrar una reparación que no ha sido programada");
        opcion = Integer.parseInt(in.readLine());
        if (opcion == 1) {
            out.println("Digite el código de la reparación a la que desea agregarle los datos");
            codigo = Integer.parseInt(in.readLine());
            out.println("Digite el proveedor del servicio");
            proveedor = in.readLine();
            out.println("Digite la fecha de realización de la reparación en formato yyyy-mm-dd");
            fechaRealizacion = LocalDate.parse(in.readLine());
            out.println("Digite el costo de la reparación");
            costo = Double.parseDouble(in.readLine());
            valido = miGestor.registrarReparacion(codigo, proveedor, fechaRealizacion, costo);
            if (!valido) {
                out.println("El día de la reparación programada solicitada, aún no ha llegado");
            } else {
                out.println("Reparación registrada con éxito");
            }
        } else {
            out.println("Digite el código de la reparación");
            codigo = Integer.parseInt(in.readLine());
            out.println("Digite la descripción de la reparación");
            descripcion = in.readLine();
            out.println("Digite la fecha a realizar la reparación en formato yyyy-mm-dd");
            fechaRealizar = LocalDate.parse(in.readLine());
            out.println("Digite el codigo de la propiedad a asignar a la reparación");
            codPropiedad = Integer.parseInt(in.readLine());
            out.println("Digite el proveedor del servicio");
            proveedor = in.readLine();
            out.println("Digite la fecha de realización de la reparación en formato yyyy-mm-dd");
            fechaRealizacion = LocalDate.parse(in.readLine());
            out.println("Digite el costo de la reparación");
            costo = Double.parseDouble(in.readLine());
            miGestor.registrarReparacionNormal(codigo, descripcion, fechaRealizar, codPropiedad, proveedor, fechaRealizacion, costo);
            out.println("Reparación registrada con éxito");
        }
    }

    public static void listarReparaciones() throws Exception {
        out.println("Las reparaciones registradas son las siguientes: ");
        for (String dato : miGestor.listarReparaciones()) {
            out.println(dato);
        }
    }

}
