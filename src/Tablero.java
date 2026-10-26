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


}
