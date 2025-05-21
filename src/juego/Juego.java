package juego;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
public class Juego extends InterfaceJuego {
	
    private Entorno entorno;
    private Mago mago; // Instancia de Mago
    private Image fondo; // Instancia de Fondo
    private Murcielago murcielago;
    
    Juego() {
        this.entorno = new Entorno(this, "Proyecto para TP", 1200, 800);
        this.mago = new Mago(400, 300); // Inicializa el mago en el centro de la ventana
        this.fondo = Herramientas.cargarImagen("juego/img/fondojuego.png"); //Carga el fondo
        this.murcielago = new Murcielago(100,50);
        
        
        
        
        
        this.entorno.iniciar();
        		
    }
    public void tick() {
        // Procesamiento de un instante de tiempo
    	entorno.dibujarImagen(fondo, 400, 300, 0, 2); // Dibuja el fondo 1
        
    	
    	
    	mago.dibujar(entorno, "derecha");
    	 // Dibuja el mago en cada tick
    	if (entorno.estaPresionada('W')) { // Tecla W para mover arriba
            mago.moverArriba();
            mago.dibujar(entorno, "arriba");
            
    		}
    	if (entorno.estaPresionada('S')) { // Tecla S para mover abajo
            mago.moverAbajo();
            mago.dibujar(entorno, "abajo" );
    		}
    	if (entorno.estaPresionada('A')) { // Tecla A para mover izquierda
            mago.moverIzquierda();
            mago.dibujar(entorno, "izquierda");
    		}
    	if (entorno.estaPresionada('D')) { // Tecla D para mover derecha
            mago.moverDerecha();
            mago.dibujar(entorno, "derecha");
    	}
    	
  
    	murcielago.dibujar(entorno); // Dibuja el murcielago
    	murcielago.mover(mago); // Mueve al murcielago

		}	
 
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}