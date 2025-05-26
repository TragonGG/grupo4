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
    private boolean arr, aba, izq, der;
    
    Juego() {
        this.entorno = new Entorno(this, "Proyecto para TP", 1200, 800);
        this.mago = new Mago(400, 300); // Inicializa el mago en el centro de la ventana
        this.fondo = Herramientas.cargarImagen("juego/img/fondojuego.png"); //Carga el fondo
        this.murcielago = new Murcielago(100,50);
        this.arr = false;
        this.izq = false;
        this.der = false;
        this.aba = false;
        
        
        
        
        
        this.entorno.iniciar();
    }
    
    public void tick() {
      
    	//Metodo de colisiones
    	this.colisionBordes(mago);
    	
    	
    	
    	// Procesamiento de un instante de tiempo
    	entorno.dibujarImagen(fondo, 400, 300, 0, 2); // Dibuja el fondo 1
    

    	
        // Lógica de movimiento con prioridad de teclas
        // Si se presionan múltiples teclas, la última comprobada tendrá prioridad
        boolean seMueve = false;
        
        if (entorno.estaPresionada('W') && !this.arr) { // Tecla W para mover arriba
            mago.moverArriba();
            seMueve = true;
        }
        
        if (entorno.estaPresionada('S') && !this.aba) { // Tecla S para mover abajo
            mago.moverAbajo();
            seMueve = true;
        }
        
        if (entorno.estaPresionada('A') && !this.izq) { // Tecla A para mover izquierda
            mago.moverIzquierda();
            seMueve = true;
        }
        
        if (entorno.estaPresionada('D') && !this.der) { // Tecla D para mover derecha
            mago.moverDerecha();
            seMueve = true;
        }
        
        // Dibuja el mago una sola vez con su dirección actual
        mago.dibujar(entorno);
        
        // Dibuja y mueve el murciélago
        murcielago.dibujar(entorno);
        murcielago.mover(mago);
        
        

        
        
        //Asigna el booleano para las colisiones
        this.arr = false;
        this.izq = false;
        this.der = false;
        this.aba = false;
    }
 
    
    //Colisiones
    public void colisionBordes(Mago m) {
        // Borde izquierdo
        if (Math.abs(m.bordIz - 0) < 2) {
            this.izq = true;
        }

        // Borde superior
        if (Math.abs(m.bordSup - 0) < 2) {
            this.arr = true;
        }

        // Borde derecho
        if (Math.abs(m.bordDer - entorno.ancho()) < 2) {
            this.der = true;
        }

        // Borde inferior
        if (Math.abs(m.bordInf - entorno.alto()) < 15) {
            this.aba = true;
        }
    }
    
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}