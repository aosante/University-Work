/*
 * Nombre del programa: Capa rutinas logicas
 * Descripción: En esta capa iran solamente las rutinas algoritmicas, las cuales
 * nunca van a interactuar con el usuario.
 * Aqui lo unico global serian constantes y arreglos o matrices
 * Fecha de creación: 22/04/18
 * Autor: Irene Flores Bermudez
 * Fecha de modificación: 22/04/18
 * Modificado por: Irene Flores Bermudez
 */
package bingo;

import java.util.Random;

public class RutinasBingo2 {

    private static String[][] cartonUno = new String[5][5];
    private static String[][] cartonDos = new String[5][5];

    // private static String[] cartonValidar = new String[50];
    public static String[] numerosCantados = new String[100];
    public static int cont = 0;

    private static void inicializaMatriz() {
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                cartonUno[fila][columna] = "0";
                cartonDos[fila][columna] = "0";
            }
        }
    }

    // Llena los cartones con numero aleatoreos
    public static void llenarCartones() {
        inicializaMatriz();
        Integer[] techo = new Integer[5];
        techo[0] = 15;
        techo[1] = 30;
        techo[2] = 45;
        techo[3] = 60;
        techo[4] = 75;

        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                Integer piso = techo[columna] - 14;
                int limite = techo[columna];
                cartonUno[fila][columna] = generarNumeroCarton(piso, limite);
                cartonDos[fila][columna] = generarNumeroCarton(piso + 1, limite + 1);
            }
        }

        cartonUno[2][2] = "0";
        cartonDos[2][2] = "0";
    }

    //retorna el dato solicitado del carton indicado
    public static String obtenerCampo(int carton, int fila, int columna) {
        String espacio = "";
        Integer numero = 0;

        if (carton == 1) {
            numero = Integer.parseInt(cartonUno[fila][columna]);
        } else {
            numero = Integer.parseInt(cartonDos[fila][columna]);
        }

        if (numero == 0) {
            return "X" + "   ";
        }

        if (numero > 9) {
            espacio = "  ";
        } else {
            espacio = "   ";
        }

        return numero + espacio;
    }

    public static String obtenerCampoSinEspacios(int carton, int fila, int columna) {
        if (carton == 1) {
            return cartonUno[fila][columna];
        } else {
            return cartonDos[fila][columna];
        }
    }

    public static int[] gana4Esquinas() {
        int gano1 = 0, gano2 = 0;
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                if (cartonUno[0][0].equals("0") && cartonUno[0][4].equals("0") && cartonUno[4][0].equals("0") && cartonUno[4][4].equals("0")) {
                    gano1 = 1;
                } else if (cartonDos[0][0].equals("0") && cartonDos[0][4].equals("0") && cartonDos[4][0] == "0" && cartonDos[4][4] == "0") {
                    gano2 = 1;
                }
            }
        }
        return new int[]{gano1, gano2};
    }

    public static int[] ganaCartonLleno() {
        int gano1 = 0, gano2 = 0;
        boolean bandera1 = true, bandera2 = true;
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                if (!cartonUno[fila][columna].equals("0")) {
                    bandera1 = false;
                }
            }
        }

        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                if (!cartonDos[fila][columna].equals("0")) {
                    bandera2 = false;
                }
            }
        }
        if (bandera1 == true) {
            gano1 = 1;
        }
        if (bandera2 == true) {
            gano2 = 1;
        }
        return new int[]{gano1, gano2};
    }

    // Retorna un numero aleatoreo de un rango
    public static String numRandom(Integer piso, Integer limite) {
        return Integer.toString((int) (Math.random() * (limite - piso + 1) + piso));
    }

    public static Integer numRandomCantar() {
        Boolean numeroRepetido = false;
        String numero = numRandom(1, 75);

        do {
            numeroRepetido = false;
            for (int c = 0; c < cont; c++) {
                if (numerosCantados[c].equals(numero)) {
//                    System.err.println("SE REPITE EL " + numero);
                    numeroRepetido = true;
                    numero = numRandom(1, 75);
                    break;
                }
            }
        } while (numeroRepetido);

        numerosCantados[cont] = numero;
        cont++;
        return Integer.parseInt(numero);
    }

    public static Boolean existeNumeroEnCatones(String numero) {

        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                if (numero.equals(cartonUno[fila][columna])) {
                    return true;
                }
                if (numero.equals(cartonDos[fila][columna])) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String generarNumeroCarton(Integer piso, Integer limite) {
        Boolean repetido = false;
        String numero = numRandom(piso, limite);

        do {
            repetido = existeNumeroEnCatones(numero);
            if (repetido) {
                numero = numRandom(piso, limite);
            }
        } while (repetido);

        return numero;
    }

    public static void tacharNumero(String numero) {
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                String campo = cartonUno[fila][columna];
                if (campo.equals(numero)) {
                    cartonUno[fila][columna] = "0";
                }
                campo = cartonDos[fila][columna];
                if (campo.equals(numero)) {
                    cartonDos[fila][columna] = "0";
                }
            }
        }
    }

    public static void salir() {
        System.exit(0);
    }

    public static String agregaLetra(Integer numero) {
        String cantar = "";

        if (numero <= 15) {
            cantar = "B" + numero;
        } else if (numero >= 16 && numero <= 30) {
            cantar = "I" + numero;
        } else if (numero >= 31 && numero <= 45) {
            cantar = "N" + numero;
        } else if (numero >= 46 && numero <= 60) {
            cantar = "G" + numero;
        } else if (numero >= 61 && numero <= 75) {
            cantar = "O" + numero;
        }
        return cantar;
    }

    public static String obtenerCantados() {
        String cantados = "";
        for (int c = 0; c < cont; c++) {
            if (cont > 1) {
                cantados = agregaLetra(Integer.parseInt(numerosCantados[c])) + ", " + cantados;
            } else {
                cantados = agregaLetra(Integer.parseInt(numerosCantados[c]));
            }
        }
        return cantados;
    }
}
