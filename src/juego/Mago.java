package juego;

import java.awt.Image;
import entorno.Herramientas;
import entorno.Entorno;

public class Mago {
    private Image[] imagen;
    private double x;
    private double y;
    private double velocidad;
    private double escala;
    private String direccionActual; // Estado que representa la dirección actual del mago

    public Mago(double x, double y) {
        this.x = x;
        this.y = y;
        this.velocidad = 3;
        this.escala = 0.5;
        this.direccionActual = "derecha"; // Dirección inicial por defecto
        
        this.imagen = new Image[5];
        this.imagen[0] = Herramientas.cargarImagen("juego/img/magoA-V1.gif");
        this.imagen[1] = Herramientas.cargarImagen("juego/img/magoD-V1.gif");
        this.imagen[2] = Herramientas.cargarImagen("juego/img/magoW-V1.gif");
        this.imagen[3] = Herramientas.cargarImagen("juego/img/magoS-V1.gif");
        this.imagen[4] = Herramientas.cargarImagen("juego/img/magoBomba-V1.gif");
    } 

    public void moverIzquierda() {
        if (this.x > 10) {
            this.x -= velocidad;
        }
        this.direccionActual = "izquierda";
    }

    public void moverDerecha() {
        if (this.x < 895) {
            this.x += velocidad;
        }
        this.direccionActual = "derecha";
    }

    public void moverArriba() {
        if (this.y > 15) {
            this.y -= velocidad;
        }
        this.direccionActual = "arriba";
    }

    public void moverAbajo() {
        if (this.y < 760) {
            this.y += velocidad;
        }
        this.direccionActual = "abajo";
    }

    // Getter para obtener la dirección actual
    public String getDireccionActual() {
        return direccionActual;
    }

    // Setter para establecer la dirección explícitamente si es necesario
    public void setDireccionActual(String direccion) {
        this.direccionActual = direccion;
    }

    public void dibujar(Entorno entorno) {
        // Ahora solo usamos la dirección actual almacenada en el objeto
        if (direccionActual.equals("abajo")) {
            entorno.dibujarImagen(imagen[3], x, y, 0, escala);
        }
        else if (direccionActual.equals("arriba")) {
            entorno.dibujarImagen(imagen[2], x, y, 0, escala);
        }
        else if (direccionActual.equals("derecha")) {
            entorno.dibujarImagen(imagen[1], x, y, 0, escala);
        }
        else if (direccionActual.equals("izquierda")) {
            entorno.dibujarImagen(imagen[0], x, y, 0, escala);
        }
    }  
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}