public abstract class Celda {
    //Atributos (No son static por que deben ser atributos de instancia)
    //cada celda tiene su propia fila, su propia columna, su propio estado de revelada.)
    //Se colocan en private por que las subclases no requiere que sean hederados, ya que no se utilizan en ninguno de sus métodos
    //Me baso en el sisguiente parrafo de las diapositivas de herencia:
    // " es importante tener en cuenta que si una clase tiene subclases, sus
    // - atributos de interés que se requiere que sean heredados -deben definirse como protected."
    private int fila;
    private int columna;
    private boolean estaRevelada;
    private boolean tieneBandera;

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

    public final boolean alternarBandera() {
        if (estaRevelada) {
            return false;
        }

        tieneBandera = !tieneBandera;
        return true;
    }

    public final void revelar() {
        if (estaRevelada || tieneBandera) {
            return;
        }
        estaRevelada = true;
    }

    //Métodos abstractos


    public abstract boolean esMina();
    public abstract boolean requiereRevelarVecinas();
    public abstract String valorRevelado();

}
