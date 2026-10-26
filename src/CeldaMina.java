public class CeldaMina extends Celda {

    //Métodos

    //Constructor
    public CeldaMina(int fila, int columna){
        super(fila, columna);
    }

    @Override
    public boolean esMina(){
        return true;
    }

}
