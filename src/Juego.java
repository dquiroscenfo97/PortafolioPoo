public class Juego {
    //Atributos
    private Tablero tablero;
    private Jugador jugador;
    private boolean juegoTerminado;
    private boolean partidaIniciada;
    private boolean victoria;

    //Metodos

    //Constructor
    //Constructor
    public Juego(Jugador jugador, ConfiguracionJuego configuracion) {
        this.jugador = jugador;
        this.juegoTerminado = false;
        this.partidaIniciada = false;
        this.victoria = false;
        inicializarTablero(configuracion.getFilas(), configuracion.getColumnas(), configuracion.getMinas());
    }

    private void inicializarTablero(int filas, int columnas, int minas){
        Tablero nuevoTablero = new Tablero(filas, columnas, minas);
        GeneradorPosiciones generador = new GeneradorPosiciones();
        nuevoTablero.inicializarTablero(generador);
        this.tablero = nuevoTablero;
    }

    public void iniciar(){
        this.partidaIniciada = true;
    }

    public void procesarTurno(int fila, int columna){
        if(!partidaIniciada || esJuegoTerminado()){
            return;
        }

        if (tablero.revelarCelda(fila, columna)){
            this.juegoTerminado = true;
            this.victoria = false;
        }else if(verificarVictoria()){
            this.juegoTerminado = true;
            this.victoria = true;
        }
    }
    public boolean marcarBandera(int fila, int columna) {
        if (!partidaIniciada || esJuegoTerminado()) {
            return false;
        }

        return tablero.marcarBandera(fila, columna);
    }

    public boolean verificarVictoria (){
        return  !tablero.quedanCeldasSegurasOcultas();
    }

    public boolean esVictoria(){
        return this.victoria;
    }

    public boolean esJuegoTerminado() {
        return juegoTerminado;
    }

    public String obtenerDibujoTablero() {
        return this.tablero.dibujar();
    }
}
