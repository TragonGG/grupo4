package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class MenuRecompensa {
	    private int x, y;
	    private int ancho, alto,escala;
	    public boolean rDurabilidad; //Condicion de mejora de durabilidad
	    private boolean recompensaElegida; // Condicion si eligio una mejora
	    private boolean iniciarSeleccionado;
	    private Image fondo,boton;
	
	    // Posición exacta del botón "Mas Vida"
	    private double vidaX;
	    private double vidaY;
	    
	    //Posición exacta de "Mas Mana"
	    private double manaX;
	    private double manaY;
	    // Posición exacta del botón "Mas velocidad"
	    private double durabilidadX;
	    private double durabilidadY;
	    
	    
	    private int botonAncho = 200;
	    private int botonAlto = 60;
	    
	    public MenuRecompensa(int x, int y, int ancho, int alto) {
	        this.x = x;
	        this.y = y;
	        this.escala = 1;
	        this.fondo =  Herramientas.cargarImagen("juego/img/fondoMenuRecompensa.jpg");
	        this.boton = Herramientas.cargarImagen("juego/img/button_rectangle_border.png");
	        this.alto = this.fondo.getHeight(null) * this.escala;
	    	this.ancho = this.fondo.getWidth(null) * this.escala;
	        
	    	this.recompensaElegida = false;
	    	rDurabilidad = false;
	       
	        this.iniciarSeleccionado = false;
	        
	        // Definir posición del botón "vida"
	        this.vidaX = 500;
	        this.vidaY = 300;
	        
	        // Posicion "mana"
	        this.manaX = 500;
	        this.manaY = 400;
	     
	        // Posición "velocidad"
	        this.durabilidadX = 500;
	        this.durabilidadY = 500;
	        
	        this.botonAncho = 200;
	        this.botonAlto = 60;
	    }
	    
	    public void dibujar(Entorno entorno) {
	        // Dibujar el fondo del menú de victoria (pantalla completa)
	    	entorno.dibujarImagen(fondo, x, y, 0, escala);
	    	entorno.cambiarFont(null, 30, Color.WHITE);
	    	entorno.escribirTexto("Llegaste a la mitad del camino", 400, 100);
	    	entorno.cambiarFont(null, 30, Color.WHITE);
	    	entorno.escribirTexto("Ten tu recompensa!", 475, 150);
	    	entorno.cambiarFont(null, 30, Color.WHITE);
	    	entorno.escribirTexto("Elige sabiamente", 475, 200);
	    		
	    	
	    

	    	// Vida
	    	entorno.cambiarFont("Arial", 30, Color.WHITE);
	    	entorno.dibujarImagen(boton, vidaX + 96, vidaY - 10, 0, 1.2);
	    	entorno.escribirTexto("Más Vida", vidaX + 35, vidaY);
	    	
	    	
	    	
	    	// Maná
	    	entorno.cambiarFont("Arial", 30, Color.WHITE);
	    	entorno.dibujarImagen(boton, manaX + 96, manaY - 10, 0, 1.2);
	    	entorno.escribirTexto("Más Maná", manaX + 30, manaY );
	    	
	    	
	    	
	    	// Velocidad
	    	entorno.cambiarFont("Arial", 30, Color.WHITE);
	    	entorno.dibujarImagen(boton, durabilidadX + 96, durabilidadY - 10, 0, 1.2);
	    	entorno.escribirTexto("Durabilidad", durabilidadX + 25, durabilidadY );
	    	
	    	}

	    private boolean clickEnBoton(int mouseX, int mouseY, double btnX, double btnY) {
	    	return mouseX >= btnX - botonAncho/2 && mouseX <= btnX + botonAncho/2 &&
	    			mouseY >= btnY - botonAlto/2 && mouseY <= btnY + botonAlto/2;	
	    }

	    	public void verificarClickRecompensa(int mouseX, int mouseY, Mago mago) {
	            if (recompensaElegida) return;

	            if (clickEnBoton(mouseX, mouseY,  vidaX + 96, vidaY-10)) {
	                mago.setVidaMaxima();
	            } else if (clickEnBoton(mouseX, mouseY, manaX + 96 , manaY - 10)) {
	                mago.setManaMaxima();
	            } else if (clickEnBoton(mouseX, mouseY, durabilidadX + 96, durabilidadY -10)) {      
	                rDurabilidad = true;
	                
	            } else {
	                return; // No clickeó ninguna opción válida
	            }

	            recompensaElegida = true;
	            iniciarSeleccionado = true;
	        }
	   
	    
	    public boolean isIniciarSeleccionado() {
	        return iniciarSeleccionado;
	    }
	    
	    
	    //setter
	    public void reiniciarEstado()  {
	        this.recompensaElegida = false;
	        this.iniciarSeleccionado = false;
	    }
	    
}