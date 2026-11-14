package icc;
import java.util.Scanner;

/**
 * Clase para simular una inversion bancaria a partir de dos fechas.
 * @author Santiago López Paulino
 * @version 1.0
 */
public class TiempoDeInversion {

    /**
     * Metodo principal donde inicia la ejecucion del simulador.
     * @param pps parametros de entrada del programa
     */
    public static void main(String[] pps) {
        Scanner in = new Scanner(System.in);

        final double TASA_ANUAL = 0.12;
        final int DIAS_ANIO = 365;

        String nombre;
        double monto;
        int diaIni, mesIni, anioIni;
        int diaFin, mesFin, anioFin;
        int dias;
        double rendimiento;
        String respuesta;

        // Damos la bienvenida y solicitamos el nombre del inversionista
        System.out.println("Bienvenido al sistema de simulacion de inversiones.");
        System.out.print("Dame tu nombre: ");

        // Recabamos el nombre del inversionista
        nombre = in.nextLine();

        do {
            // Saludar al inversionista y preguntar por el monto a invertir
            System.out.println("Hola " + nombre + ", ¿cual es el monto que deseas invertir?");

            // Recabar el monto
            monto = in.nextDouble();

            // Preguntar por una fecha inicio de inversion y para Recabarla
            System.out.println("Ingresa la fecha de inicio de inversion:");
            System.out.print("Dia: ");
            diaIni = in.nextInt();
            System.out.print("Mes (1 a 12): ");
            mesIni = in.nextInt();
            System.out.print("Anio: ");
            anioIni = in.nextInt();

            Fecha fechaInicio = new Fecha(diaIni, mesIni, anioIni);

            // Preguntar por una fecha fin de inversion y para Recabarla
            System.out.println("Ingresa la fecha de fin de inversion:");
            System.out.print("Dia: ");
            diaFin = in.nextInt();
            System.out.print("Mes (1 a 12): ");
            mesFin = in.nextInt();
            System.out.print("Anio: ");
            anioFin = in.nextInt();

            Fecha fechaFin = new Fecha(diaFin, mesFin, anioFin);

            // Mostrarle al usuario de cuantos dias sería su inversion y su rendimiento al 12%
            dias = fechaInicio.obtenerDiferenciaDias(fechaFin);
            rendimiento = (monto * TASA_ANUAL * dias) / DIAS_ANIO;

            System.out.println("Fecha de inicio: " + fechaInicio);
            System.out.println("Fecha de fin: " + fechaFin);
            System.out.println("Tu inversion seria por " + dias + " dias.");
            System.out.println("Tu rendimiento al 12% por los " + dias + " dias es: $" + rendimiento);

            // Limpia el salto de linea pendiente despues de leer numeros
            in.nextLine();

            // Preguntar si desea hacer otra simulacion [repetir el ciclo]
            System.out.print("¿Deseas hacer otra simulacion? (si/no): ");
            respuesta = in.nextLine();
            respuesta = respuesta.trim().toLowerCase();

        } while (respuesta.equals("si"));

        System.out.println("Fin de la simulacion.");
    }
}
