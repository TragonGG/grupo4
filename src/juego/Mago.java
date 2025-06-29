package juego;

import java.awt.Color; 
import java.awt.Image;
import entorno.Herramientas;
import entorno.Entorno;

public class Mago {
    private Image[] imagen;
    private double x, y, velocidad, escala, alto, ancho;
    public double bordIz, bordSup, bordDer, bordInf;
    private String direccionActual;
    
    // Sistema de vida y mana
    private double vidaMaxima, vidaActual;
    private double manaMaximo, manaActual;

    public Mago(double x, double y) {
        this.x = x;
        this.y = y;
        this.velocidad = 3;
        this.escala = 0.2;
        this.direccionActual = "derecha";
        
        // Inicializar vida y mana
        this.vidaMaxima = 100;
        this.vidaActual = 100;
        this.manaMaximo = 100;
        this.manaActual = 100;
        
        this.imagen = new Image[4];
        this.imagen[0] = Herramientas.cargarImagen("juego/img/magoIzquierdaVFinal.png");
        this.imagen[1] = Herramientas.cargarImagen("juego/img/magoDerechaVFinal.png");
        this.imagen[2] = Herramientas.cargarImagen("juego/img/magoArribaVFinal.png");
        this.imagen[3] = Herramientas.cargarImagen("juego/img/magoAbajoVFinal.png");
        
        this.alto = this.imagen[1].getHeight(null) * this.escala;
        this.ancho = this.imagen[1].getWidth(null) * this.escala;
        
        this.bordIz = this.x - this.ancho / 3;
    	this.bordDer = this.x + this.ancho / 2;
    	this.bordSup = this.y - this.alto / 3;
    	this.bordInf = this.y + this.alto / 3.5;
    } 
    
    //MOVIMIENTO Y COLISIONES
    public void actualizarBordes() {
    	this.bordIz = this.x - this.ancho / 3;
    	this.bordDer = this.x + this.ancho / 2;
    	this.bordSup = this.y - this.alto / 3;
    	this.bordInf = this.y + this.alto / 3.5;
    }
    
    
    public void moverIzquierda() {
        this.x -= velocidad;
        this.direccionActual = "izquierda";
        actualizarBordes();
    }
    
    public void moverDerecha() {
        this.x += velocidad;
        this.direccionActual = "derecha";
        actualizarBordes();
    }

    public void moverArriba() {
        this.y -= velocidad;
        this.direccionActual = "arriba";
        actualizarBordes();
    }

    public void moverAbajo() {
        this.y += velocidad;
        this.direccionActual = "abajo";
        actualizarBordes();
    }

    public String getDireccionActual() {
        return direccionActual;
    }
    
    public void setDireccionActual(String direccion) {
        this.direccionActual = direccion;
    }

    
    public void dibujar(Entorno entorno) {
        // Dibujar el mago
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
    
    // === MÉTODOS PARA VIDA ===
    public void recibirDanio(double danio) {
        this.vidaActual -= danio;
        if (this.vidaActual < 0) {
            this.vidaActual = 0;
        }
    }
    
    public void curar(double curacion) {
        this.vidaActual += curacion;
        if (this.vidaActual > this.vidaMaxima) {
            this.vidaActual = this.vidaMaxima;
        }
    }
    
    public boolean estaVivo() {
        return vidaActual > 0;
    }
    
    // === MÉTODOS PARA MANA ===
    public boolean usarMana(double costo) {
        if (this.manaActual >= costo) {
            this.manaActual -= costo;
            return true;
        }
        return false;
    }
    
    public void recuperarMana(double cantidad) {
        this.manaActual += cantidad;
        if (this.manaActual > this.manaMaximo) {
            this.manaActual = this.manaMaximo;
        }
    }
    
    public void regenerarMana() {
        recuperarMana(0.2);
    }
    
    //getters 
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    // Getters para vida y mana
    public double getVidaActual() { return vidaActual; }
    public double getVidaMaxima() { return vidaMaxima; }
    public double getManaActual() { return manaActual; }
    public double getManaMaximo() { return manaMaximo; }
		
    // Setters para vida, mana y velocidad
    public void setVidaMaxima() {
        this.vidaMaxima = 200;
        this.vidaActual = this.vidaMaxima; // Update current vida to the new maximum
    }

    public void setManaMaxima() {
        this.manaMaximo = 200;
        this.manaActual = this.manaMaximo; // Update current mana to the new maximum
    }

    public void setVelocidadMaxima() {
        this.velocidad = 1;
    }
}