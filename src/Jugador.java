public class Jugador {
    //Atributos
    private final String nombre;

    //Metodos
    //Constructor
    public Jugador(String nombre){
        this.nombre = nombre;
    }

    //Metodo de consulta
    public String getNombre(){
        return nombre;
    }

    //Utilidades

    @Override
    public String toString(){
        return "nombre del jugador: " + nombre;
    }
}
