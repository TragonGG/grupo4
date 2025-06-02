package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class MenuVictoria {
	private double x, y, ancho, alto, escala;
    private boolean iniciarSeleccionado;
    private Image boton, fondo;


    // Posición exacta del botón "Reinciar partida"
    private double iniciarX;
    private double iniciarY;
    
    //Posion de "Salir"
    private double salirX;
    private double salirY;
	

    public MenuVictoria(double x, double y, double ancho, double alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.escala = 1.8;
        
    	this.boton = Herramientas.cargarImagen("juego/img/button_rectangle_border.png");
    	this.fondo = Herramientas.cargarImagen("juego/img/fondoVictoria.png");
    	this.alto = this.fondo.getHeight(null) * this.escala;
    	this.ancho = this.fondo.getWidth(null) * this.escala;
        
        this.iniciarSeleccionado = false;

        // Definir posición del botón "Volver a jugar"
        this.iniciarX = x;
        this.iniciarY = y + 50;
        
        // Posicion "Salir"
        this.salirX = x;
        this.salirY = y + 200;
    }

    public void dibujar(Entorno entorno) {
        // Dibujar el fondo del menú de victoria (pantalla completa)
    	entorno.dibujarImagen(fondo, x, y, 0, escala);

        // Texto de victoria
        entorno.cambiarFont(null, 60, Color.BLACK);
        entorno.escribirTexto("¡FELICIDADES, HAS GANADO!", x - 400 , y - 100);

        // Dibujar boton detrás del botón "Volver a jugar"
        entorno.dibujarImagen(boton, 605, 443, 0, 1.1);
        // Dibujar texto "Volver a jugar"
        if (iniciarSeleccionado) {
            entorno.cambiarFont("Gabriola", 30, Color.GREEN);
        } else {
            entorno.cambiarFont("Gabriola", 30, Color.WHITE);
        }
        entorno.escribirTexto("Volver a jugar", iniciarX - 57, iniciarY);
        
     // Dibujar boton detrás del botón "Salir"
        entorno.dibujarImagen(boton, 605, 600, 0, 1.1);
        // Dibujar texto "salir"
        if (iniciarSeleccionado) {
            entorno.cambiarFont("Gabriola", 40, Color.GREEN);
        } else {
            entorno.cambiarFont("Gabriola", 40, Color.WHITE);
        }
        entorno.escribirTexto("Salir", 575,610);
    }

    public void verificarClick(int mouseX, int mouseY) {
        // Verificar si se hizo clic en "Volver a jugar"
        // Definimos un margen de área clickeable alrededor del botón
        double margenX = 100;
        double margenY = 25;

        if (mouseX >= iniciarX - margenX && mouseX <= iniciarX + margenX &&
            mouseY >= iniciarY - margenY && mouseY <= iniciarY + margenY) {
            iniciarSeleccionado = !iniciarSeleccionado;
        }
    }
    
    public void verificarClickSalir(int mouseX, int mouseY) {
        double margenX = 100;
        double margenY = 25;

        if (mouseX >= salirX - margenX && mouseX <= salirX + margenX &&
            mouseY >= salirY - margenY && mouseY <= salirY + margenY) {
            System.exit(0); // Cierra el programa
        }
    }


    public boolean isIniciarSeleccionado() {
        return iniciarSeleccionado;
    }
    
    public void deseleccionar() {
        iniciarSeleccionado = false;
    }
}
