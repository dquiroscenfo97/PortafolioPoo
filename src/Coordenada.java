public class Coordenada {
    //Atributos
    private final int fila;
    private final int columna;

    //Métodos
    //Constructor
    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    //Metodos de consulta
    public int getFila(){
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
