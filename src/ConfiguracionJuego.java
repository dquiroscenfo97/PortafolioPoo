public class ConfiguracionJuego {
    //Atributos (inmutables: es un objeto de valor, igual que Coordenada)
    private final String nombreJugador;
    private final int filas;
    private final int columnas;
    private final int minas;

    //Constructor
    public ConfiguracionJuego(String nombreJugador, int filas, int columnas, int minas) {
        this.nombreJugador = nombreJugador;
        this.filas = filas;
        this.columnas = columnas;
        this.minas = minas;
    }

    //Regla de dominio: la validación NO va en la consola ni en Main
    public boolean esValida() {
        return filas > 0 && columnas > 0 && minas > 0 && minas < filas * columnas;
    }

    //Métodos de consulta
    public String getNombreJugador() {
        return nombreJugador;
    }
    public int getFilas() {
        return filas;
    }
    public int getColumnas() {
        return columnas;
    }
    public int getMinas() {
        return minas;
    }
}