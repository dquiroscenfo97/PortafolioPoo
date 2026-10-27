import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Nombre del jugador: ");
        String nombre = teclado.nextLine();
        int filas = leerEntero(teclado, "Filas: ");
        int columnas = leerEntero(teclado, "Columnas: ");
        int minas = leerEntero(teclado, "Minas: ");

        // 1. Creamos el objeto de configuración
        ConfiguracionJuego configuracion = new ConfiguracionJuego(nombre, filas, columnas, minas);

        // 2. Validamos la regla de dominio
        if (!configuracion.esValida()) {
            System.out.println("Configuracion invalida. Las minas deben ser mayores a 0 y menores que el total de celdas.");
            teclado.close();
            return;
        }

        // 3. Ensamblamos el modelo pasando la configuración completa
        Jugador jugador = new Jugador(configuracion.getNombreJugador());
        Juego juego = new Juego(jugador, configuracion);

        // 4. Ejecutamos la partida
        correrPartidaEnConsola(juego, jugador, teclado);
        teclado.close();
    }

    private static void correrPartidaEnConsola(Juego juego, Jugador jugador, Scanner teclado) {
        juego.iniciar();

        while (!juego.esJuegoTerminado()) {
            System.out.println(juego.obtenerDibujoTablero());
            System.out.println("1. Revelar celda");
            System.out.println("2. Marcar bandera");

            int opcion = leerEntero(teclado, "Opcion: ");
            int fila = leerEntero(teclado, "Fila: ");
            int columna = leerEntero(teclado, "Columna: ");

            if (opcion == 1) {
                juego.procesarTurno(fila, columna);
            } else if (opcion == 2) {
                if (!juego.marcarBandera(fila, columna)) {
                    System.out.println("No se pudo marcar esa celda.");
                }
            } else {
                System.out.println("Opcion invalida.");
            }
        }

        System.out.println(juego.obtenerDibujoTablero());
        if (juego.esVictoria()) {
            System.out.println("El jugador " + jugador.getNombre() + " ha ganado la partida.");
        } else {
            System.out.println("El jugador " + jugador.getNombre() + " perdio la partida.");
        }
    }

    private static int leerEntero(Scanner teclado, String mensaje) {
        System.out.print(mensaje);
        while (!teclado.hasNextInt()) {
            System.out.println("Debe escribir un numero entero.");
            teclado.next();
            System.out.print(mensaje);
        }
        return teclado.nextInt();
    }
}