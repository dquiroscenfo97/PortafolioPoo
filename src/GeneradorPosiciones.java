import java.util.Random;
public class GeneradorPosiciones {
    //Atributos
    private final Random generadorAleatorio = new Random();
    //Metodo unico para generar una coordenada aleatoria
    public Coordenada generarCoordenada(int filas, int columnas){
        int filaGenerada = generadorAleatorio.nextInt(filas);
        int columnaGenerada = generadorAleatorio.nextInt(columnas);
        return new Coordenada(filaGenerada,columnaGenerada);
    }
}
