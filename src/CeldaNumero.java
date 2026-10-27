public class CeldaNumero extends Celda {
    //Atributos
    private int minasAdyacentes;

    //Métodos

    //Constructor
    public CeldaNumero(int fila, int columna, int minasAdyacentes){
        super(fila, columna);
        this.minasAdyacentes = minasAdyacentes;
    }

    //Metodos sobreescritos
    @Override
    public boolean esMina(){
        return false;
    }

    @Override
    public boolean requiereRevelarVecinas(){
        return minasAdyacentes == 0;
    }

    @Override
    public String valorRevelado(){
        return Integer.toString(minasAdyacentes);
    }


}
