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

    @Override
    public boolean requiereRevelarVecinas(){
        return false;
    }

    @Override
    public String valorRevelado(){
        return "*";
    }

}
