package juego;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
public class Juego extends InterfaceJuego {
	
    private Entorno entorno;
    private Mago mago; // Instancia de Mago
    private Menu menu;
    private Image fondo; // Instancia de Fondo
    private Murcielago[] murcielagos;
    private Obstaculos[] rocas;
    private boolean arr, aba, izq, der;
    private MenuInicial menuInicial;
    private boolean juegoIniciado = false;
    
    Juego() {
        this.entorno = new Entorno(this, "Proyecto para TP", 1200, 800);
        this.mago = new Mago(400, 300); // 
        this.fondo = Herramientas.cargarImagen("juego/img/fondojuego.png"); //Carga el fondo
        
        
        this.murcielagos = new Murcielago[50];
        for (int i = 0; i < murcielagos.length;i++) {
        	this.murcielagos[i] = new Murcielago(100,50);
        }
        this.menuInicial = new MenuInicial(600, 400, 1200, 800);

        
        rocas = new Obstaculos[5];
        int[] posX = {200, 200, 450, 700, 700};
        int[] posY = {600, 200, 400, 200, 600};
        for (int i = 0; i < rocas.length; i++) {
            rocas[i] = new Obstaculos(posX[i], posY[i]);
        }
        // menu
        this.menu = new Menu(1060, 400, 299, 800);
        //
        this.arr = false;
        this.izq = false;
        this.der = false;
        this.aba = false;
       
        
        this.entorno.iniciar();
    }
    
    public void tick() {
    	   if (!juegoIniciado) {
    	       // Mostrar menú inicial
    	       menuInicial.dibujar(entorno);
    	       
    	       if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
    	           menuInicial.verificarClick(entorno.mouseX(), entorno.mouseY());
    	           if (menuInicial.isIniciarSeleccionado()) {
    	               juegoIniciado = true;
    	           }
    	       }
    	   } else {
    	       // Procesamiento de un instante de tiempo
    	       entorno.dibujarImagen(fondo, 400, 300, 0, 2); // Dibuja el fondo 1
    	       menu.dibujar(entorno, mago);
    	       

    	       if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
    	           menu.verificarClick(entorno.mouseX(), entorno.mouseY());
    	       }
    	       
    	       //Metodo de colisiones
    	       this.colisionPersonaje(mago);
    	       
    	       //Dibuja las rocas
    	       for (int i = 0; i < rocas.length; i++) {
    	           rocas[i].dibujar(entorno);	
    	           this.colisionPersonajeRoca(mago, rocas[i]);
    	       }

    	       
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
    	       for (int i = 0; i < murcielagos.length;i++){
    	          murcielagos[i].dibujar(entorno);
    	          murcielagos[i].mover(mago);
    	       }
    	       
    	       //Asigna el booleano para las colisiones
    	       this.arr = false;
    	       this.izq = false;
    	       this.der = false;
    	       this.aba = false;
    	   }
    	}
 

    //Colisiones
 
    public void colisionPersonaje(Mago m) {
        // Borde izquierdo
        if (Math.abs(m.bordIz - 0) < 2) {
            this.izq = true;
        }

        // Borde superior
        if (Math.abs(m.bordSup - 0) < 2) {
            this.arr = true;
        }

        // Borde derecho - dejando espacio de 200px para hechizos
        if (Math.abs(m.bordDer - (entorno.ancho() - 280)) < 2) {
            this.der = true;
        }

        // Borde inferior
        if (Math.abs(m.bordInf - entorno.alto()) < 15) {
            this.aba = true;
        }
    }
    
    public void colisionPersonajeRoca(Mago m, Obstaculos r) {
        boolean colision = 
            m.bordDer > r.bordIz &&
            m.bordIz < r.bordDer &&
            m.bordInf > r.bordSup &&
            m.bordSup < r.bordInf;

        if (colision) {
            if (Math.abs(m.bordDer - r.bordIz) < 5) this.der = true;
            if (Math.abs(m.bordIz - r.bordDer) < 5) this.izq = true;
            if (Math.abs(m.bordInf - r.bordSup) < 5) this.aba = true;
            if (Math.abs(m.bordSup - r.bordInf) < 5) this.arr = true;
        }
    }

    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}