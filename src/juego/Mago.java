package juego;

import java.awt.Image;
import entorno.Herramientas;
import entorno.Entorno;

public class Mago {
    private Image imagen;
    private double x;
    private double y;
    private double velocidad;
    private double escala;

    private boolean mirandoDerecha;

    public Mago(double x, double y) {
        this.x = x;
        this.y = y;
        this.velocidad = 3;
        this.escala = 0.5;
        this.imagen = Herramientas.cargarImagen("juego/img/wizard_dance.gif");
        this.mirandoDerecha = false;
    }

    public void dibujar(Entorno entorno) {
        double escalaFinal = mirandoDerecha ? -escala : escala;
        entorno.dibujarImagen(imagen, x, y, 0, escalaFinal);
        
    }

    public void moverIzquierda() {
        this.x -= velocidad;
        this.mirandoDerecha = false;
    }

    public void moverDerecha() {
        this.x += velocidad;
        this.mirandoDerecha = true;
    }

    public void moverArriba() {
        this.y -= velocidad;
    }

    public void moverAbajo() {
        this.y += velocidad;
    }
}

