package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class MenuMuerte {
	 private double x, y, ancho, alto, escala;
	    private boolean iniciarSeleccionado;
	    private Image boton, fondo;


	    // Posición exacta del botón "Reinciar partida"
	    private double iniciarX;
	    private double iniciarY;
		

	    public MenuMuerte(double x, double y, double ancho, double alto) {
	        this.x = x;
	        this.y = y;
	        this.ancho = ancho;
	        this.alto = alto;
	        this.escala = 1;
	        
	    	this.boton = Herramientas.cargarImagen("juego/img/button_rectangle_border.png");
	    	this.fondo = Herramientas.cargarImagen("juego/img/fondoNegro.png");
	    	this.alto = this.fondo.getHeight(null) * this.escala;
	    	this.ancho = this.fondo.getWidth(null) * this.escala;
	        
	        this.iniciarSeleccionado = false;

	        // Definir posición del botón "Reinciar partida"
	        this.iniciarX = x;
	        this.iniciarY = y + 50;
	    }

	    public void dibujar(Entorno entorno) {
	        // Dibujar el fondo del menú de muerte (pantalla completa)
	    	entorno.dibujarImagen(fondo, x, y, 0, escala);

	        // Texto de muerte
	        entorno.cambiarFont("Gabriola", 80, Color.RED);
	        entorno.escribirTexto("¡HAS MUERTO!", x - 200 , y - 100);

	        // Dibujar boton detrás del botón "Reinciar partida"

	        entorno.dibujarImagen(boton, 605, 443, 0, 1.1);

	        // Dibujar texto "Reiniciar partida"
	        if (iniciarSeleccionado) {
	            entorno.cambiarFont("Gabriola", 30, Color.GREEN);
	        } else {
	            entorno.cambiarFont("Gabriola", 30, Color.WHITE);
	        }
	        entorno.escribirTexto("Reiniciar partida", iniciarX - 70, iniciarY);
	    }

	    public void verificarClick(int mouseX, int mouseY) {
	        // Verificar si se hizo clic en "Reiniciar partida"
	        // Definimos un margen de área clickeable alrededor del botón
	        double margenX = 100;
	        double margenY = 25;

	        if (mouseX >= iniciarX - margenX && mouseX <= iniciarX + margenX &&
	            mouseY >= iniciarY - margenY && mouseY <= iniciarY + margenY) {
	            iniciarSeleccionado = !iniciarSeleccionado;
	        }
	    }

	    public boolean isIniciarSeleccionado() {
	        return iniciarSeleccionado;
	    }
}
