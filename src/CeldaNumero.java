public class CeldaNumero extends Celda {
    //Atributos
    private int minasAdyacentes;

    //Métodos

    //Constructor
    public CeldaNumero(int fila, int columna, int minasAdyacentes){
        super(fila, columna);
        this.minasAdyacentes = minasAdyacentes;
    }

    @Override
    public boolean esMina(){
        return false;
    }

    //Metodos de consulta
    public int getMinasAdyacentes(){
        return minasAdyacentes;
    }



}
