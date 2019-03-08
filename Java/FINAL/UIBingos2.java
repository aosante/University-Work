/*La empresa BINGUITOS de Costa Rica, necesita que se haga un programa para
* administrar juegos de bingo que ellos utilizan.
* El programa debe presentar un menú con las siguientes opciones:
* 1. Generar cartones.
* 2. Definir el tipo de juego.
* 3. Comenzar el juego (permitir sacar las bolitas que jugarán).
* 4. Salir.
* Generar cartones: Debe desplegar en pantalla dos cartones completos, los
* cuales muestras los números que tienen (que serán controlados mediante
* matrices o vectores según lo analice).
* Definir el tipo de Juego: Existen solo dos opciones de juego: que tenga las
* cuatro esquinas o cantón lleno. Comenzar el juego: Se deben mostrar los
* cartones en pantalla de la mejor manera. Una vez listo esto, deberá pedir la
* bolita a “cantar”, que será seleccionada en forma aleatoria (deberá investigar
* el uso de RANDOM para generación aleatoria de valores). Si se “canta” un
* número (que se debe hacer mediante una opción del menú), se cambia el número
* en el cartón por una equis (“X”). Por ejemplo: 57 pasa a X.
* Notas:
*(a) Cada vez se jugarán dos bingos UNICAMENTE. El despliegue de los mismos
* sería:
* 1. Se debe validar que para un mismo bingo no haya números repetidos. Para
* esto se puede llenar el primer cartón en forma aleatoria y el segundo se
* podría llenar haciendo una copia del primer cartón con la variación de
* “sumar 1 a cada número”, verificando que no se vayan a repetir números en el
* nuevo cartón (es una opción).
* 2. Cada columna consta de 15 números (B va de 1 a 15; I va de 16 a 30, N va de
* 31 a 45, etc.)
* 3. Si se canta un número, cambia de forma:
* EJEMPLO: Si sale G57 sucede esto:
* 57pasa a X
*(a) El despliegue puede hacerlo de la manera en la que usted mejor considere,
* pero siempre jugarán SOLO dos cartones por partida.
*(b) Cada bolita que se “canta”, se hace mediante una opción del menú, y salen
* los números aleatoriamente, eso sí, al desplegar el número que salió se hace
* de la siguiente forma:
* EJEMPLO: Si sale el 36, se despliega: N36
*(a) Dependiendo el TIPO DE JUEGO, la aplicación determinará cuál de los dos
* cartones fue el ganador.
 */
package bingo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class UIBingos2 {

    static BufferedReader in
            = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;// variables objetos in y out para
    static Integer modoJuego = 0;

    public static void main(String[] args) throws java.io.IOException {
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
        out.println("1.  Generar cartones");
        out.println("2.  Definir tipo de juego");
        out.println("3.  Comenzar el juego");
        out.println("4.  Salir");
        out.println();
    }

    static int leerOpcion() throws java.io.IOException {
        int opcion;

        out.print("Seleccione su opci\u00a2n: ");
        opcion = Integer.parseInt(in.readLine());
        out.println();

        return opcion;
    }

    static boolean ejecutarAccion(int popcion) throws java.io.IOException {
        boolean noSalir = true;

        switch (popcion) {

            case 1://Generar Cartones de bingo
                RutinasBingo2.llenarCartones();
                imprimirCartones();
                break;
            case 2:// Tipo de juego
                seleccionarModoJuego();
                imprimirCartones();
                break;
            case 3: //Comenzar el juego (sacar bolitas)
                if (modoJuego == 1) {
                    jugar1();
                } else if (modoJuego == 2) {
                    jugar2();
                }
                break;
            case 4: //Salir
                noSalir = false;
                break;
            default://Cualquier otro valor dado por el usuario
                //se considera inválido
                out.println("Opcion inv\u00a0lida");
                out.println();
                break;
        }
        return noSalir;
    }

    static void imprimirCartones() {
        out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        out.println("B   I   N   G   O        B   I   N   G   O");
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                System.out.print(RutinasBingo2.obtenerCampo(1, fila, columna));
            }

            System.out.print("     ");

            for (int columna = 0; columna < 5; columna++) {
                System.out.print(RutinasBingo2.obtenerCampo(2, fila, columna));
            }
            System.out.println("");
        }

        System.out.println("");
        imprimirNumerosCantados();
    }

    static void imprimirNumerosCantados() {
        Integer limite = RutinasBingo.cont;
        System.out.print("Numeros cantados: " + RutinasBingo2.obtenerCantados());
    }

    static void seleccionarModoJuego() throws IOException {
        out.println("Seleccione el modo de juego: ");
        out.println(" 1- Cuatro Esquinas ");
        out.println(" 2- Carton lleno ");
        out.println("");
        out.print("-> ");

        modoJuego = Integer.parseInt(in.readLine());
    }

    static void jugar1() throws IOException {
        Boolean continuar = true;
        int num;
        int bingo[] = new int[2];
        do {
            imprimirCartones();

            bingo = RutinasBingo2.gana4Esquinas();
            if (bingo[0] == 1) {
                out.println();
                out.println("Ganó el jugador 1");
            } else if (bingo[1] == 1) {
                out.println();
                out.println("Ganó el jugador 2");
            }

            cantarNumero();
            out.println("Presione 1 para cantar un nuevo numero y cualquier otro "
                    + "número para salir");
            num = Integer.parseInt(in.readLine());
            if (num != 1) {
                System.exit(0);
            }
        } while (continuar);
    }

    //rutinas jugar2() que es para carton lleno.
    //falta hacer la rutina que busque que todos sean X
    static void jugar2() throws IOException {
        Boolean continuar = true;
        int num;
        int bingo[] = new int[2];
        do {
            imprimirCartones();

            bingo = RutinasBingo2.ganaCartonLleno();
            if (bingo[0] == 1) {
                out.println();
                out.println("Ganó el jugador 1");
            } else if (bingo[1] == 1) {
                out.println();
                out.println("Ganó el jugador 2");
            }
            cantarNumero();
            out.println("Presione 1 para cantar un nuevo numero y cualquier otro "
                    + "número para salir");
            num = Integer.parseInt(in.readLine());
            if (num != 1) {
                System.exit(0);
            }
        } while (continuar);
    }

    static void cantarNumero() throws IOException {
        Integer numero = RutinasBingo2.numRandomCantar();

        String cantar = "";
        cantar = RutinasBingo2.agregaLetra(numero);

        out.print("\n         Salio el: " + cantar + "\n\n");

        RutinasBingo2.tacharNumero("" + numero);

    }

}
