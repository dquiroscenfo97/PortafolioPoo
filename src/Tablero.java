public class Tablero {
    //Atributos
    private Celda[][] matrizCeldas;
    private int filas;
    private int columnas;
    private int totalMinas;

    public Tablero(int filas, int columnas, int totalMinas){
        this.filas = filas;
        this.columnas = columnas;
        this.totalMinas = totalMinas;
        this.matrizCeldas = new Celda [filas][columnas];
    }

    public void inicializarTablero(GeneradorPosiciones generador){
        boolean[][] hayMina = new boolean [filas][columnas];
        int minasColocadas = 0;
        while(minasColocadas < totalMinas){
            Coordenada coordenadaGenerada = generador.generarCoordenada(filas, columnas);
            while(hayMina[coordenadaGenerada.getFila()][coordenadaGenerada.getColumna()]){
                coordenadaGenerada = generador.generarCoordenada(filas, columnas);
            }
            hayMina[coordenadaGenerada.getFila()][coordenadaGenerada.getColumna()] = true;
            minasColocadas ++;
        }

        for(int i = 0; i < matrizCeldas.length; i++){
            for(int j = 0; j < matrizCeldas[i].length; j++){
                if(hayMina[i][j]){
                    matrizCeldas[i][j] = new CeldaMina(i,j);
                }else{
                    int minasAlrededor = 0;
                    for(int desplazamientoFila = -1; desplazamientoFila <= 1; desplazamientoFila ++){
                        for(int desplazamientoColumna = -1; desplazamientoColumna <= 1; desplazamientoColumna ++){
                            if(desplazamientoFila != 0 || desplazamientoColumna != 0){
                                int filaVecina = i + desplazamientoFila;
                                int columnaVecina = j + desplazamientoColumna;
                                if(filaVecina >= 0 && filaVecina < hayMina.length &&
                                columnaVecina >= 0 && columnaVecina < hayMina[0].length){
                                    if(hayMina[filaVecina][columnaVecina]){
                                        minasAlrededor ++;                            
                                    }
                                }
                            }
                        }
                    }
                    matrizCeldas[i][j] = new CeldaNumero(i,j,minasAlrededor);           
                }

            }       
        }                                     
    }

    public boolean revelarCelda(int fila, int columna){
        if(fila < 0 || fila >= this.filas || columna < 0 || columna >= this.columnas){
            return false;
        }
        Celda posicionActual = matrizCeldas[fila][columna];

        if(posicionActual == null){
            return false;
        }

        if(posicionActual.estaRevelada()){
            return false;
        }

        if(posicionActual.tieneBandera()){
            return false;
        }
        posicionActual.revelar();

        if(posicionActual.estaRevelada() && posicionActual.esMina()){
            return true;
        }
        if(posicionActual.estaRevelada() && posicionActual.requiereRevelarVecinas()){
            for(int df = -1; df <= 1; df ++){
                for(int dc = -1; dc <= 1; dc ++){
                    if(df == 0 && dc == 0){
                        continue;
                    }
                    int vecinaFila = fila + df;
                    int vecinaColumna = columna + dc;
                    if (vecinaFila >= 0 && vecinaFila < filas &&
                        vecinaColumna >= 0 && vecinaColumna < columnas) {

                        if (revelarCelda(vecinaFila, vecinaColumna)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean marcarBandera(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }

        Celda celda = matrizCeldas[fila][columna];

        if (celda == null) {
            return false;
        }

        return celda.alternarBandera();
    }

    public boolean quedanCeldasSegurasOcultas() {
        for (int i = 0; i < matrizCeldas.length; i++) {
            for (int j = 0; j < matrizCeldas[i].length; j++) {
                Celda celda = matrizCeldas[i][j];

                if (celda != null && !celda.estaRevelada() && !celda.esMina()) {
                    return true;
                }
            }
        }

        return false;
    }

    public EstadoCelda obtenerEstado(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return EstadoCelda.OCULTA;
        }
        Celda celda = matrizCeldas[fila][columna];
        if (celda == null) {
            return EstadoCelda.OCULTA;
        }
        if (celda.estaRevelada()) {
            return EstadoCelda.REVELADA;
        }
        if (celda.tieneBandera()) {
            return EstadoCelda.BANDERA;
        }
        return EstadoCelda.OCULTA;
    }

    public String obtenerValor(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return "";
        }
        Celda celda = matrizCeldas[fila][columna];
        if (celda == null || !celda.estaRevelada()) {
            return "";
        }
        return celda.valorRevelado();
    }

    public String dibujar() {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matrizCeldas[i][j] == null) {
                    texto.append("? ");
                    continue;
                }
                EstadoCelda estado = obtenerEstado(i, j);
                switch (estado) {
                    case REVELADA:
                        texto.append(obtenerValor(i, j)).append(" ");
                        break;
                    case BANDERA:
                        texto.append("F ");
                        break;
                    default:
                        texto.append("- ");
                        break;
                }
            }
            texto.append("\n");
        }
        return texto.toString();
    }
}
