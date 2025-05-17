package juego;
import java.awt.Color;
import entorno.Entorno;
import entorno.InterfaceJuego;
public class Juego extends InterfaceJuego {
	
    private Entorno entorno;
    private Mago mago; // Instancia de Mago
    
    Juego() {
        this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
        this.mago = new Mago(400, 300); // Inicializa el mago en el centro de la ventana
        this.entorno.iniciar();
    }
    public void tick() {
        // Procesamiento de un instante de tiempo
        entorno.colorFondo(Color.DARK_GRAY); // Cambia el color de fondo
        mago.dibujar(entorno); // Dibuja el mago en cada tick
        // Verificar teclas presionadas para mover el mago
        if (entorno.estaPresionada('W')) { // Tecla W para mover arriba
            mago.moverArriba();
        }
        if (entorno.estaPresionada('S')) { // Tecla S para mover abajo
            mago.moverAbajo();
        }
        if (entorno.estaPresionada('A')) { // Tecla A para mover izquierda
            mago.moverIzquierda();
        }
        if (entorno.estaPresionada('D')) { // Tecla D para mover derecha
            mago.moverDerecha();
        }
    }
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}