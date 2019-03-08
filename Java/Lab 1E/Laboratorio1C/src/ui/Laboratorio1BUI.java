/**
 * Nombre del programa: Mantenimiento de laboratorios cursos profesores empleados y carreras.
 * Descripción del programa: El programa registra y lista carreras, cursos, profesores, empleados y laboratorios
 * ingresados por el usuario. Adicionalmente, permite eliminar laboratorios ya registrados
 * Autor: Andrés Osante Alfaro
 * Fecha, de creacion: 21/05/18
 * Modificado por: Andrés Osante Alfaro
 * Fecha de modificacion: 16/07/18
 *
 */
package ui;

import gestores.Gestor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import jdk.nashorn.internal.ir.Statement;

public class Laboratorio1BUI {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

    static Gestor miGestor;

    public static void main(String[] args) throws Exception {
        miGestor = new Gestor();

        int opc;
        boolean noSalir = true;

        do {
            mostrarMenu();
            opc = leerOpcion();
            noSalir = ejecutarAccion(opc);
        } while (noSalir);
    }

    static void mostrarMenu() {
        out.println();
        out.println("1. Registrar empleado");
        out.println("2. Listar empleados");
        out.println("3. Modificar empleados");
        out.println("4. Registrar profesor");
        out.println("5. Listar profesores");
        out.println("6. Modificar profesores");
        out.println("7. Registrar carrera");
        out.println("8. Listar carreras");
        out.println("9. Registrar cursos");
        out.println("10. Listar cursos");
        out.println("11. Registrar laboratorios");
        out.println("12. Listar laboratorios");
        out.println("13. Modificar laboratorio");
        out.println("14. Eliminar laboratorio");
        out.println("15. Registrar reserva de laboratorio");
        out.println("16. Listar reservas de laboratorio");
        out.println("0. Salir");
    }

    static int leerOpcion() throws IOException {
        int opcion;

        out.println("Seleccione su opcion");
        opcion = Integer.parseInt(in.readLine());
        out.println();

        return opcion;
    }

    static boolean ejecutarAccion(int popcion) throws Exception {
        boolean noSalir = true;

        switch (popcion) {

            case 1:
                registrarEmpleado();
                break;
            case 2:
                listarEmpleados();
                break;
            case 3:
                modificarEmpleado();
                break;
            case 4:
                registrarProfesor();
                break;
            case 5:
                listarProfesores();
                break;
            case 6:
                modificarProfesor();
                break;
            case 7:
                registrarCarrera();
                break;
            case 8:
                listarCarreras();
                break;
            case 9:
                registrarCurso();
                break;
            case 10:
                listarCursos();
                break;
            case 11:
                registrarLab();
                break;
            case 12:
                listarLabs();
                break;
            case 13:
                modificarLab();
                break;
            case 14:
                eliminarLab();
                break;
            case 15:
                registrarReservas();
                break;
            case 16:
                listarReservas();
                break;
            case 0: //opcion para salir
                noSalir = false;
                break;

            default:
                out.println("Opción inválida. Inténtelo de nuevo.");
                out.println();
                break;

        }
        return noSalir;
    }

    static void registrarEmpleado() throws Exception {

        String cedula, nombre, apellido, direccion, telefono, puesto;
        out.println("Digite la cedula del empleado");
        cedula = in.readLine();
        out.println("Digite el nombre del empleado");
        nombre = in.readLine();
        out.println("Digite el apellido del empleado");
        apellido = in.readLine();
        out.println("Digite la direccion del empleado");
        direccion = in.readLine();
        out.println("Digite el telefono del empleado");
        telefono = in.readLine();
        out.println("Digite el puesto del empleado");
        puesto = in.readLine();

        miGestor.registrarEmpleado(cedula, nombre, apellido, direccion, telefono, puesto);
        out.println("Empleado registrado con éxito");
    }

    static void listarEmpleados() throws Exception {
        out.println("Los empleados registrados son los siguientes: ");
        out.println(miGestor.listarEmpleado());
    }

    static void modificarEmpleado() throws Exception {
        String cedula, nombre, apellido, direccion, telefono, puesto;
        out.println("Digite la cédula del empleado que desea modificar");
        cedula = in.readLine();
        out.println("Digite el nombre del empleado nuevamente");
        nombre = in.readLine();
        out.println("Digite el apellido del empleado nuevamente");
        apellido = in.readLine();
        out.println("Digite la direccion del empleado nuevamente");
        direccion = in.readLine();
        out.println("Digite el telefono del empleado nuevamente");
        telefono = in.readLine();
        out.println("Digite el puesto del empleado nuevamente");
        puesto = in.readLine();
        miGestor.modificarEmpleado(cedula, nombre, apellido, direccion, telefono, puesto);
        out.println("Empleado actualizado");
    }

    static void registrarProfesor() throws Exception {

        String cedula, nombre, apellido, direccion, telefono, lugarDeTrabajo;
        int aniosDeExperiencia;
        out.println("Digite la cedula del profesor");
        cedula = in.readLine();
        out.println("Digite el nombre del profesor");
        nombre = in.readLine();
        out.println("Digite el apellido del profesor");
        apellido = in.readLine();
        out.println("Digite la direccion del profesor");
        direccion = in.readLine();
        out.println("Digite el telefono del profesor");
        telefono = in.readLine();
        out.println("Digite el lugar de trabajo del profesor");
        lugarDeTrabajo = in.readLine();
        out.println("Digite los años de experiencia del profesor");
        aniosDeExperiencia = Integer.parseInt(in.readLine());

        miGestor.registrarProfesor(cedula, nombre, apellido, direccion, telefono, lugarDeTrabajo, aniosDeExperiencia);
        out.println("Profesor registrado con éxito");

    }

    static void listarProfesores() throws Exception {
        out.println("Los profesores registrados son los siguientes: ");
        out.println(miGestor.listarProfesores());
    }

    static void modificarProfesor() throws Exception {
        String cedula, nombre, apellido, direccion, telefono, lugarDeTrabajo;
        int aniosDeExperiencia;
        out.println("Digite la cédula del profesor que desea modificar");
        cedula = in.readLine();
        out.println("Digite el nombre del profesor nuevamente");
        nombre = in.readLine();
        out.println("Digite el apellido del profesor nuevamente");
        apellido = in.readLine();
        out.println("Digite la direccion del profesor nuevamente");
        direccion = in.readLine();
        out.println("Digite el telefono del profesor nuevamente");
        telefono = in.readLine();
        out.println("Digite el lugar de trabajo del profesor nuevamente");
        lugarDeTrabajo = in.readLine();
        out.println("Digite los años de experiencia del profesor nuevamente");
        aniosDeExperiencia = Integer.parseInt(in.readLine());

        miGestor.modificarProfesor(cedula, nombre, apellido, direccion, telefono, lugarDeTrabajo, aniosDeExperiencia);
        out.println("Profesor actualizado");
    }

    static void registrarCarrera() throws IOException {
        String codigo, nombre;
        int grado;
        boolean acreditada, existe;

        out.println("Digite el código de la carrera a registrar");
        codigo = in.readLine();
        out.println("Digite el nombre de la carrera a registrar");
        nombre = in.readLine();
        out.println("Digite el grado de la carrera a registrar. 1 para técnico, 2 para Diplomado,"
                + "3 para Bachillerato, 4 para Licenciatura y 5 para Maestría");
        grado = Integer.parseInt(in.readLine());
        out.println("Digite 1 si la carrera está acréditada y 0 si no lo está");
        int acred = Integer.parseInt(in.readLine());
        if (acred == 1) {
            acreditada = true;
        } else {
            acreditada = false;
        }
        existe = miGestor.registrarCarrera(codigo, nombre, grado, acreditada);

        if (!existe) {

            out.println("Carrera registrada con éxito");
        } else {
            out.println("La carrera ya está registrada en el sistema");
        }
    }

    static void listarCarreras() throws IOException {
        out.println("Las carreras registradas son las siguientes: " + "\n");
        for (String dato : miGestor.listarCarreras()) {
            out.println(dato);
        }
    }

    static void registrarCurso() throws IOException {
        boolean existe = false;
        String codigo, nombre;
        int creditos;
        out.println("Digite el código del curso a registrar");
        codigo = in.readLine();
        out.println("Digite el nombre del curso a registrar");
        nombre = in.readLine();
        out.println("Digite  número de créditos del curso a registrar");
        creditos = Integer.parseInt(in.readLine());

        existe = miGestor.registrarCurso(codigo, nombre, creditos);
        if (!existe) {
            out.println("Curso registrado con éxito");
        } else {
            out.println("El curso ya está registrado en el sistema");
        }
    }

    static void listarCursos() throws IOException {
        out.println("Los cursos registrados son los siguientes: " + "\n");
        for (String dato : miGestor.listarCursos()) {
            out.println(dato);
        }
    }

    static void registrarLab() throws Exception {

        int codigo, capacidad;
        String nombre;
        out.println("Digite el código del laboratorio a registrar");
        codigo = Integer.parseInt(in.readLine());
        out.println("Digite el nombre del laboratorio a registrar");
        nombre = in.readLine();
        out.println("Digite la capacidad del laboratorio a registrar");
        capacidad = Integer.parseInt(in.readLine());
        miGestor.registrarLab(codigo, nombre, capacidad);
        out.println("Laboratorio registrado con éxito");
    }

    static void listarLabs() throws Exception {
        out.println("Los laboratorios registrados son los siguientes: " + "\n");
        out.println(miGestor.listarLabs());
    }

    static void modificarLab() throws Exception {
        int codigo, capacidad;
        String nombre;
        out.println("Digite el código del laboratorio que desea modificar");
        codigo = Integer.parseInt(in.readLine());
        out.println("Digite el nombre del laboratorio nuevamente");
        nombre = in.readLine();
        out.println("Digite la capacidad del laboratorio nuevamente");
        capacidad = Integer.parseInt(in.readLine());
        miGestor.modificarLab(codigo, nombre, capacidad);
        out.println("Laboratorio modificado con éxito");
    }

    static void eliminarLab() throws Exception {
        out.println("Digite el código del laboratorio que desea eliminar");
        int codigo = Integer.parseInt(in.readLine());
        miGestor.eliminarLaboratorio(codigo);
        out.println("Laboratorio eliminado con éxito");
    }

    static void registrarReservas() throws Exception {
        String codigo, codCurso, cedulaProfe;
        int codLab;
        int cantidad;
        LocalDate fechaOcupacion;
        out.println("Digite el código de la reserva");
        codigo = in.readLine();
        listarLabs();
        out.println("Digite el código del laboratorio a asignar para la reserva");
        codLab = Integer.parseInt(in.readLine());
        listarCursos();
        out.println("Digite el código del curso a asignar para la reserva");
        codCurso = in.readLine();
        listarProfesores();
        out.println("Digite la cédula del profesor a asignar para la reserva");
        cedulaProfe = in.readLine();
        out.println("Digite la cantidad de estudiantes que van a ocupar el laboratorio");
        cantidad = Integer.parseInt(in.readLine());
        out.println("Digite la fecha en la que se va a ocupar el laboratorio en formato \"yyyy-mm-dd\" ");
        fechaOcupacion = LocalDate.parse(in.readLine());

        miGestor.registrarReserva(codigo, codLab, codCurso, cedulaProfe, cantidad, fechaOcupacion);
        out.println("Reserva registrada con éxito");
    }

    static void listarReservas() throws IOException {
        out.println("Las reservas registradas son las siguientes: " + "\n");
        for (String dato : miGestor.listarReservas()) {
            out.println(dato);
        }

    }
}
