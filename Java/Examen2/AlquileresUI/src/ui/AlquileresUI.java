package ui;

import gestores.Gestor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDate;

public class AlquileresUI {

    static Gestor miGestor = new Gestor();

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

    public static void main(String[] args) throws Exception {
        int opc;
        boolean noSalir = true;

        do {
            mostrarMenu();
            try {
            opc = leerOpcion();
            }catch(Exception e) {
                out.println("Debe de digitar un número para la opción. Inténtelo de nuevo.");
                opc = leerOpcion();
            }
            try{
            noSalir = ejecutarAccion(opc);
            }catch(Exception e) {
                out.println("Debe digitar los datos correctamente. Ingrese números donde se solicita, e ingrese las fechas con formato yyyy-mm-dd. Inténtelo de nuevo.");
                noSalir = false;
            }
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
        out.println("10. Manejar reparaciones");
        out.println("11. Listar reparaciones");
        out.println("12. Registrar factura");
        out.println("13. Buscar factura");
        out.println("0. Salir");
    }

    public static int leerOpcion() throws IOException {
        int opcion;
        
        try{
        out.println("Seleccione su opción");
        opcion = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("Debe de digitar un número. Inténtelo de nuevo");
            opcion = Integer.parseInt(in.readLine());
        }
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
                registrarReparacion();//listo
                break;
            case 11:
                listarReparaciones();//listo
                break;
            case 12:
                registrarFactura();
                break;
            case 13:
                listarFactura();
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
        try {
        out.println("Digite el código de la propiedad");
        codPropiedad = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("Debe de digitar un número. Inténtelo de nuevo");
        codPropiedad = Integer.parseInt(in.readLine());
        }
        out.println("Digite el nombre de la propiedad");
        nombre = in.readLine();
        try{
        out.println("Digite el valor de la propiedad");
        valor = Double.parseDouble(in.readLine());
        } catch(Exception e) {
            out.println("Debe de digitar un número. Inténtelo de nuevo");
            valor = Double.parseDouble(in.readLine());
        }
        out.println("Digite la dirección de la propiedad");
        direccion = in.readLine();
        out.println("Digite el residencial de la propiedad");
        residencial = in.readLine();
        try {
        out.println("Digite el número de casa de la propiedad");
        numeroCasa = Integer.parseInt(in.readLine());
        } catch(Exception e) {
            out.println("Debe de digitar un número. Inténtelo de nuevo");
            numeroCasa = Integer.parseInt(in.readLine());
        }
        try {
        out.println("Digite 1 si la casa tiene patio y 0 si no lo tiene");
        patio = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("Debe de digitar un número. Inténtelo de nuevo");
            patio = Integer.parseInt(in.readLine());
        }
        if (patio == 1) {
            tienePatio = true;
        } else {
            tienePatio = false;
        }
        try {
        out.println("Digite la cantidad de habitaciones que tiene la propiedad");
        cantidadHabitaciones = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("Debe de digitar un número. Inténtelo de nuevo");
            cantidadHabitaciones = Integer.parseInt(in.readLine());
        }
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
        } catch (Exception e) {
            out.println(e.getMessage());
        }

    }

    public static void buscarPropiedad() throws Exception {
        String encontrada = "";
        int codigo;
        try {
         out.println("Digite el codigo de la propiedad que desea buscar");
        codigo = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("El código debe de ser un número. Inténtelo de nuevo.");
            codigo = Integer.parseInt(in.readLine());
        }
        
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
        try {
        out.println("Digite la cédula del inquilino");
        id = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("La cédula debe de ser un número. Inténtelo de nuevo");
            id = Integer.parseInt(in.readLine());
        }
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
        try {
        out.println("Digite la cédula del inquilino");
        id = Integer.parseInt(in.readLine());
        } catch(Exception e) {
            out.println("La cédula debe de ser un número. Inténtelo de nuevo.");
            id = Integer.parseInt(in.readLine());
        }
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
        try{
        out.println("Digite la cédula del inquilino del contrato");
        cedula = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("La cédula debe de ser un número. Inténtelo de nuevo.");
            cedula = Integer.parseInt(in.readLine());
        }
        listarPropiedades();
        try{
        out.println("Digite el código de la propiedad del contrato");
        codPropiedad = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("EL código de la propiedad debe de ser un número. Inténtelo de nuevo");
            codPropiedad = Integer.parseInt(in.readLine());
        }
        try{
        out.println("Digite la fecha de incio del contrato en formato yyyy-mm-dd");
        fechaInicio = LocalDate.parse(in.readLine());
        }catch(Exception e) {
            out.println("La fecha debe escribirse en formato yyyy-mm-dd. Inténtelo de nuevo");
            fechaInicio = LocalDate.parse(in.readLine());
        }
        fechaValida = miGestor.validarFecha(fechaInicio);
        if (fechaValida == false) {
            try{
            out.println("La fecha de inicio del contrato debe de ser posterior a hoy. Inténtelo de nuevo.");
            fechaInicio = LocalDate.parse(in.readLine());
            }catch(Exception e) {
                out.println("La fecha debe escribirse en formato yyyy-mm-dd. Inténtelo de nuevo");
                fechaInicio = LocalDate.parse(in.readLine());
            }
        }
        try {
        out.println("Digite la duración del contrato");
        duracionContrato = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("Debe de digitar un número entero. Inténtelo de nuevo");
            duracionContrato = Integer.parseInt(in.readLine());
        }
        try{
        out.println("Digite el monto del alquiler");
        montoAlquiler = Double.parseDouble(in.readLine());
        }catch(Exception e) {
            out.println("El monto del alquiler debe de ser un número. Inténtelo de nuevo");
            montoAlquiler = Double.parseDouble(in.readLine());
        }
        try{
        out.println("Digite el procentaje de aumento del contrato en decimales. (p. ej. 20% = 0.2)");
        porcentajeAumento = Double.parseDouble(in.readLine());
        }catch(Exception e) {
            out.println("El porcentaje de aumento debe de ser un número con decimales.(p. ej. 20% = 0.2)");
            porcentajeAumento = Double.parseDouble(in.readLine());
        }
        out.println("Digite la moneda en la que se va a manejar el contrato (colones o dólares)");
        moneda = in.readLine();
        try {
        out.println("Digite el código del contrato");
        codContrato = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("El código debe de ser un número. Inténtelo de nuevo");
            codContrato = Integer.parseInt(in.readLine());
        }
        try {
            valido = miGestor.registrarContrato(cedula, codPropiedad, fechaInicio, duracionContrato, montoAlquiler, porcentajeAumento, moneda, codContrato);
            if (!valido) {
                out.println("La propiedad o inquilino ya han sido asignados a otro contrato, por favor inténtelo de nuevo");
            } else {
                out.println("Contrato registrado con éxito");
            }
        } catch (Exception e) {
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
        try{
        out.println("Digite el código del contrato a modificar");
        codContrato = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("El código debe de ser un número. Inténtelo de nuevo");
            codContrato = Integer.parseInt(in.readLine());
        }
        try{
        out.println("Digite la cédula del inquilino del contrato");
        cedula = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("La cédula debe de ser un número. Intentelo de nuevo");
            cedula = Integer.parseInt(in.readLine());
        }
        try{
        out.println("Digite el código de la propiedad del contrato");
        codPropiedad = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("El código debe de ser un número. Inténtelo de nuevo");
            codPropiedad = Integer.parseInt(in.readLine());
        }
        try{
        out.println("Digite la fecha de incio del contrato en formato yyyy-mm-dd");
        fechaInicio = LocalDate.parse(in.readLine());
        }catch(Exception e) {
            out.println("La fecha de incio debe estar en formato yyyy-mm-dd");
            fechaInicio = LocalDate.parse(in.readLine());
        }
        fechaValida = miGestor.validarFecha(fechaInicio);
        if (fechaValida == false) {
            try{
        out.println("Digite la fecha de incio del contrato en formato yyyy-mm-dd");
        fechaInicio = LocalDate.parse(in.readLine());
        }catch(Exception e) {
            out.println("La fecha de incio debe estar en formato yyyy-mm-dd");
            fechaInicio = LocalDate.parse(in.readLine());
        }
        }
        try{
        out.println("Digite la duración del contrato");
        duracionContrato = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("Debe de digitar un número para la duración. Intentelo de nuevo");
            duracionContrato = Integer.parseInt(in.readLine());
        }
        try{
        out.println("Digite el monto del alquiler");
        montoAlquiler = Double.parseDouble(in.readLine());
        }catch(Exception e) {
            out.println("El monto debe debe de ser un número. Inténtelo de nuevo");
            montoAlquiler = Double.parseDouble(in.readLine());
        }
        try {
        out.println("Digite el procentaje de aumento del contrato en decimales (p. ej. 20% = 0.2).");
        porcentajeAumento = Double.parseDouble(in.readLine());
        }catch(Exception e) {
            out.println("Digite el procentaje de aumento del contrato en decimales (p. ej. 20% = 0.2).");
            porcentajeAumento = Double.parseDouble(in.readLine());
        }
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
        try {
            out.println("Los contratos registrados son los siguientes: " + "\n");
            out.println(miGestor.listarContratos());
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    public static void programarReparacion() throws Exception {
        boolean valido;
        int codigo, codPropiedad;
        String descripcion;
        LocalDate fechaRealizar;
        try{
        out.println("Digite el código de la propiedad a reparar");
        codPropiedad = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("El código debe de ser un número. Inténtelo de nuevo");
            codPropiedad = Integer.parseInt(in.readLine());
        }
        try{
        out.println("Digite el código de la reparación");
        codigo = Integer.parseInt(in.readLine());
        }catch(Exception e) {
            out.println("El código debe de ser un número. Inténtelo de nuevo");
            codigo = Integer.parseInt(in.readLine());
        }
        out.println("Digite la descripción de la reparación");
        descripcion = in.readLine();
        try {
        out.println("Digite la fecha de realización en formato yyyy-mm-dd");
        fechaRealizar = LocalDate.parse(in.readLine());
        }catch(Exception e) {
            out.println("La fecha debe estar en formato yyyy-mm-dd");
            fechaRealizar = LocalDate.parse(in.readLine());
        }
        try {
            valido = miGestor.programarReparacion(codPropiedad, codigo, descripcion, fechaRealizar);
            if (!valido) {
                out.println("La propiedad a reparar no tiene contrato o no existe. Por favor inténtelo de nuevo");
            } else {
                out.println("Reparación programada con éxito");
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    public static void registrarReparacion() throws Exception {
        int opcion = 0;
        boolean valido, valido1;
        int codigo, codPropiedad;
        String descripcion;
        LocalDate fechaRealizar;

        String proveedor;
        LocalDate fechaRealizacion;
        double costo;
        out.println("Digite 1 si desea progamar una reparacion, y 2 si desea registrar una reparación ya programada o 3 si desea registrar una reparación que no ha sido programada");
        try {
            opcion = Integer.parseInt(in.readLine());
        } catch (Exception e) {
            out.println("Debe de digitar un número para proceder. Inténtelo de nuevo");
            opcion = Integer.parseInt(in.readLine());
        }
        if (opcion == 1) {
            programarReparacion();
        }
        if (opcion == 2) {
            try {
            out.println("Digite el código de la reparación a la que desea agregarle los datos");
            codigo = Integer.parseInt(in.readLine());
            }catch(Exception e) {
                out.println("El código debe de ser un número. Inténtelo de nuevo");
                codigo = Integer.parseInt(in.readLine());
            }
            out.println("Digite el proveedor del servicio");
            proveedor = in.readLine();
            try {
            out.println("Digite la fecha de realización de la reparación en formato yyyy-mm-dd");
            fechaRealizacion = LocalDate.parse(in.readLine());
            }catch(Exception e) {
                out.println("Digite la fecha en formato yyyy-mm-dd. Inténtelo de nuevo");
                fechaRealizacion = LocalDate.parse(in.readLine());
            }
            try{
            out.println("Digite el costo de la reparación");
            costo = Double.parseDouble(in.readLine());
            }catch(Exception e) {
                out.println("El costo debe de ser un número. Vuelva a intentarlo.");
                costo = Double.parseDouble(in.readLine());
            }
            valido = miGestor.registrarReparacionProgramada(codigo, proveedor, fechaRealizacion, costo);
            if (!valido) {
                out.println("El día de la reparación programada solicitada, aún no ha llegado");
            } else {
                out.println("Reparación registrada con éxito");
            }
        } else if (opcion == 3) {
            try {
            out.println("Digite el código de la reparación a la que desea agregarle los datos");
            codigo = Integer.parseInt(in.readLine());
            }catch(Exception e) {
                out.println("El código debe de ser un número. Inténtelo de nuevo");
                codigo = Integer.parseInt(in.readLine());
            }
            out.println("Digite la descripción de la reparación");
            descripcion = in.readLine();
            try {
            out.println("Digite la fecha a realizar la reparación en formato yyyy-mm-dd");
            fechaRealizar = LocalDate.parse(in.readLine());
            }catch(Exception e) {
                out.println("La fecha debe de ir en fromato yyyy-mm-dd. Inténtelo de nuevo.");
                fechaRealizar = LocalDate.parse(in.readLine());
            }
            try{
            out.println("Digite el codigo de la propiedad a asignar a la reparación");
            codPropiedad = Integer.parseInt(in.readLine());
            }catch(Exception e) {
                out.println("El código debe de ser un número. Inténtelo de nuevo.");
                codPropiedad = Integer.parseInt(in.readLine());
            }
            out.println("Digite el proveedor del servicio");
            proveedor = in.readLine();
            try{
            out.println("Digite la fecha de realización de la reparación en formato yyyy-mm-dd");
            fechaRealizacion = LocalDate.parse(in.readLine());
            }catch(Exception e) {
                out.println("Digite la fecha en formato yyyy-mm-dd.");
                fechaRealizacion = LocalDate.parse(in.readLine());
            }
            try {
            out.println("Digite el costo de la reparación");
            costo = Double.parseDouble(in.readLine());
            }catch(Exception e) {
                out.println("El costo debe de ser un número. Inténtelo de nuevo.");
                costo = Double.parseDouble(in.readLine());
            }
            try {
                valido1 = miGestor.registrarReparacionNormal(codigo, descripcion, fechaRealizar, codPropiedad, proveedor, fechaRealizacion, costo);
                if (valido1) {
                    out.println("Reparación registrada con éxito");
                } else {
                    out.println("La propiedad a reparar no tiene contrato o no existe. Por favor inténtelo de nuevo");
                }
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        } else if (opcion != 1 && opcion != 2 && opcion != 3) {
            out.println("Digite un numero dentro de las opciones disponibles");
        }
    }

    public static void listarReparaciones() throws Exception {
        try {
            String reparaciones = miGestor.listarReparaciones();
            out.println("Las reparaciones registradas son las siguientes: " + "\n");
            out.println(reparaciones);
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    public static void registrarFactura() throws Exception {
        String nombre, apellido;
        out.println("Digite el nombre del inquilino al cual desea generarle la factura");
        nombre = in.readLine();
        out.println("Digite el apellido o los apellidos del inquilino");
        apellido = in.readLine();
        try {
            miGestor.registrarFactura(nombre, apellido);
            out.println("Factura registrada con éxito.");
            out.println(miGestor.listarFactura(nombre, apellido));
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    public static void listarFactura() throws Exception {
        String nombre, apellido;
        out.println("Digite el nombre del inquilino al cual desea generarle la factura");
        nombre = in.readLine();
        out.println("Digite el apellido o los apellidos del inquilino");
        apellido = in.readLine();
        try {
            out.println(miGestor.listarFactura(nombre, apellido));
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

}
