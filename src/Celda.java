public abstract class Celda {
    //Atributos (No son static por que deben ser atributos de instancia)
    //cada celda tiene su propia fila, su propia columna, su propio estado de revelada.
    protected int fila;
    protected int columna;
    protected boolean estaRevelada;
    protected boolean tieneBandera;

    //Métodos

    //Constructor (El constructor existe unicamente como base para las subclases)
    protected Celda(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        estaRevelada = false;
        tieneBandera = false;
    }
    // Métodos de consulta (Se colocan como final porque no deben de ser sobreescritos en las clases hijas)
    public final int getFila(){
        return fila;
    }

    public final int getColumna(){
        return columna;
    }

    public final boolean estaRevelada(){
        return estaRevelada;
    }

    public final boolean tieneBandera(){
        return tieneBandera;
    }

    public final void alternarBandera() {
        if (!estaRevelada) {
            tieneBandera = !tieneBandera;
        }
    }

    public final void revelar() {
        if (estaRevelada || tieneBandera) {
            return;
        }
        estaRevelada = true;
    }

    //Métodos abstractos


    public abstract boolean esMina();

}
